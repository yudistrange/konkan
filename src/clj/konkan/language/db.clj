(ns konkan.language.db
  (:require [next.jdbc :as jdbc]))

(defn create [conn name]
  (try
    (jdbc/execute! conn ["insert into languages (name) values (?)" name] {:return-keys true})
    (catch Exception e
      {:exception e :message (.getMessage e)})))

(defn get [conn name]
  (try
    (jdbc/execute! conn ["select * from languages where name = ?" name])
    (catch Exception e
      {:exception e :message (.getMessage e)})))
