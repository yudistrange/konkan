(ns konkan.word.db
  (:require [next.jdbc.sql :as sql]
            [konkan.db.core]))

(defn create [conn word language-id meaning metadata]
  (try
    (sql/insert! conn :words
     {:word word :language_id language-id :meaning meaning :metadata metadata})
    (catch Exception e
      {:exception e :message (.getMessage e)})))

(defn get
  ([conn word]
   (try
     (sql/find-by-keys conn :words {:word word})
     (catch Exception e
       {:exception e :message (.getMessage e)})))
  ([conn word lang-id]
   (try
     (sql/find-by-keys conn :words {:word word :language_id lang-id})
     (catch Exception e
       {:exception e :message (.getMessage e)}))))
