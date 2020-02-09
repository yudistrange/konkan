(ns konkan.word.db-test
  (:require [konkan.word.db :as word-db]
            [konkan.language.db :as lang-db]
            [konkan.db.pool :as db-pool]
            [konkan.fixtures :as fixtures]
            [clojure.test :refer :all]))

(use-fixtures :once fixtures/setup)
(use-fixtures :each fixtures/wrap-transaction)

(deftest create
  (testing "Can create a new word in the db"
    (let [lang-id (-> (db-pool/get-connection) (lang-db/create "marathi") :languages/id)]
      (is (word-db/create (db-pool/get-connection) "hello" lang-id "hi" {})
          {:id 1 :word "hello" :language-id lang-id :metadata {}})))

  (testing "Cannot create duplicate words in the for the same langugage"
    (let [lang-id (-> (db-pool/get-connection) (lang-db/create "english") :languages/id)]
      (is (:message (do
                      (word-db/create (db-pool/get-connection) "hello" lang-id "hi" {})
                      (word-db/create (db-pool/get-connection) "hello" lang-id "hi" {})))
         "Detail: Key (word)=(hello) already exists.")))

  (testing "Can create same words belonging to different languages"
    (let [konkani-lang-id (-> (db-pool/get-connection) (lang-db/create "konkani") :languages/id)
          _               (word-db/create (db-pool/get-connection) "hello" konkani-lang-id "hi" {})
          hindi-lang-id   (-> (db-pool/get-connection) (lang-db/create "hindi") :languages/id)
          _               (word-db/create (db-pool/get-connection) "hello" hindi-lang-id "hi" {})]
      (is (word-db/get (db-pool/get-connection) "hello" konkani-lang-id)
          {:id 1 :word "hello" :language-id konkani-lang-id :metadata {}})
      (is (word-db/get (db-pool/get-connection) "hello" hindi-lang-id)
          {:id 1 :word "hello" :language-id hindi-lang-id :metadata {}})
      (is (word-db/get (db-pool/get-connection) "hello")
          [{:id 1 :word "hello" :language-id konkani-lang-id :metadata {}}
           {:id 1 :word "hello" :language-id hindi-lang-id :metadata {}}]))))

(deftest get
  (testing "Can get a word by name"
    (let [language (lang-db/create (db-pool/get-connection) "konkani")
          lang-id  (-> language :languages/id)
          word     (word-db/create (db-pool/get-connection) "hello" lang-id "hi" {})
          word2    (word-db/get (db-pool/get-connection) "hello" lang-id)]
      (is word
          (word-db/get (db-pool/get-connection) "hello"))
      (is word
          (word-db/get (db-pool/get-connection) "hello" lang-id))))

  (testing "Should get empty vector for non-existent words"
    (is []
        (word-db/get (db-pool/get-connection) "hello"))))
