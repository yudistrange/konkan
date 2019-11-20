(ns konkan.db.migration
  (:require [ragtime.repl :as ragtime]
            [ragtime.jdbc :as jdbc]
            [konkan.db.pool :as pool]))

(defn- config []
  {:datastore  (jdbc/sql-database {:datasource @pool/connection})
   :migrations (jdbc/load-resources "migrations")})

(defn migrate []
    (try
    (ragtime/migrate (config))
    (catch Exception ex
      (println ex))))

(defn rollback []
  (try
    (ragtime/rollback (config))
    (catch Exception ex
      (println ex))))
