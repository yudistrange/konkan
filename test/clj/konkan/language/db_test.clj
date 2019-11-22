(ns konkan.language.db-test
  (:require [konkan.language.db :as lang-db]
            [konkan.db.pool :as db-pool]
            [konkan.fixtures :as fixtures]
            [clojure.test :refer :all]))

(use-fixtures :once fixtures/setup)
(use-fixtures :each fixtures/wrap-transaction)

(deftest create
  (testing "Can create a new language in the db"
    (is (lang-db/create (db-pool/get-connection) "konkani")
        {:id 1 :name "konkani"}))

  (testing "Cannot create language with an exiting name"
    (is (do
          (lang-db/create (db-pool/get-connection) "konkani")
          (lang-db/create (db-pool/get-connection) "konkani"))
        {:message "Detail: Key (name)=(konkani) already exists."})))

(deftest get
  (testing "Can get a language by name"
    (is (lang-db/create (db-pool/get-connection) "konkani")
        (lang-db/get (db-pool/get-connection) "konkani")))

  (testing "Should get empty vector for non-existent languages"
    (is []
        (lang-db/get (db-pool/get-connection) "hindi"))))
