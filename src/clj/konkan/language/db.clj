(ns konkan.language.db
  (:require [next.jdbc.sql :as sql]))

(defn create [conn name]
  (try
    (sql/insert! conn :languages {:name name})
    (catch Exception e
      {:exception e :message (.getMessage e)})))

(defn get [conn name]
  (try
    (sql/find-by-keys conn :languages {:name name})
    (catch Exception e
      {:exception e :message (.getMessage e)})))
