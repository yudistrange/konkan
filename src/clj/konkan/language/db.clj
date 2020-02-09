(ns konkan.language.db
  (:require [next.jdbc.sql :as sql]
            [konkan.util :as util]))

(defn create [conn name]
  (util/safely-execute
    (sql/insert! conn :languages {:name name})))

(defn get [conn name]
  (util/safely-execute
    (sql/find-by-keys conn :languages {:name name})))
