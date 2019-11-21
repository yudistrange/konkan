(ns konkan.fixtures
  (:require [konkan.config :as config]
            [konkan.db.pool :as db-pool]
            [konkan.db.migration :as db-migration]
            [next.jdbc :as jdbc]))

(defn setup [test]
  (config/init!)
  (db-pool/init!)
  (db-migration/migrate)
  (test))

(defn wrap-transaction [test]
  (jdbc/with-transaction [txn (db-pool/get-connection)]
    [:rollback-only true]
    (with-redefs [db-pool/get-connection (fn [] txn)]
      (test))))
