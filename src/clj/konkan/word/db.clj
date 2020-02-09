(ns konkan.word.db
  (:require [next.jdbc.sql :as sql]
            [konkan.util :as util]))

(defn create [conn word language-id meaning metadata]
  (util/safely-execute
    (sql/insert! conn :words
     {:word word :language_id language-id :meaning meaning :metadata metadata})))

(defn get
  ([conn word]
   (util/safely-execute
     (sql/find-by-keys conn :words {:word word})))
  ([conn word lang-id]
   (util/safely-execute
     (sql/find-by-keys conn :words {:word word :language_id lang-id}))))
