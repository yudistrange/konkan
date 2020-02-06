(ns konkan.language.model-test
  (:require [konkan.language.model :as lang-model]
            [konkan.db.pool :as db-pool]
            [konkan.fixtures :as fixtures]
            [clojure.test :refer :all]))

(use-fixtures :once fixtures/setup)
(use-fixtures :each fixtures/wrap-transaction)

(deftest create
  (testing "Can create a new language"
    (is (lang-model/create (db-pool/get-connection) "konkani")
        {:id 1 :name "konkani"}))

  (testing "Should create language with lowecase names only"
    (is (lang-model/create (db-pool/get-connection) "ENGLISH")
        {:id 1 :name "english"}))

  (testing "Should return error when exception is raised during creation"
    (is (do
          (lang-model/create (db-pool/get-connection) "ENGLISH")
          (lang-model/create (db-pool/get-connection) "ENGLISH"))
        {:status :error})))

(deftest get
  (testing "Can search for existing language"
    (is (do
          (lang-model/create (db-pool/get-connection) "ENGLISH")
          (lang-model/get (db-pool/get-connection) "ENGLISH"))
        {:id 1 :name "english"}))

  (testing "Should return not-found when language not present"
    (is (lang-model/get (db-pool/get-connection) "ENGLISH")
        {:status :not-found})))
