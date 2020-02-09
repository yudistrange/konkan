(ns konkan.word.model-test
  (:require [konkan.word.model :as word-model]
            [konkan.language.model :as lang-model]
            [konkan.db.pool :as db-pool]
            [konkan.fixtures :as fixtures]
            [clojure.test :refer :all]))

(use-fixtures :once fixtures/setup)
(use-fixtures :each fixtures/wrap-transaction)

(deftest create
  (testing "Can create new word"
    (is (do (lang-model/create (db-pool/get-connection) "konkani")
            (word-model/create (db-pool/get-connection) "saturi" "konkani" "umbrella" {}))
        {:id 1 :word "saturi" :meaning "umbrella"})))

(deftest get
  (testing "Can search existing words"
    (is (do (lang-model/create (db-pool/get-connection) "konkani")
            (word-model/create (db-pool/get-connection) "saturi" "konkani" "umbrella" {})
            (word-model/get (db-pool/get-connection) "saturi"))
        {:id 1 :word "saturi" :meaning "umbrella"})))
