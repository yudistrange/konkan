(ns konkan.core
  (:require [konkan.web :as web]
            [konkan.config :as config]
            [konkan.db.pool :as db-pool]
            [konkan.db.migration :as db-migration]
            [ring.adapter.jetty :as jetty]
            [next.jdbc :as jdbc]
            [konkan.language.db :as lang-db]))

(defn -main
  [& args]
  (config/init!)
  (db-pool/init!)
  (if-not (empty? args)
    (case (first args)
      "migrate"  (db-migration/migrate)
      "rollback" (db-migration/rollback))
    (jetty/run-jetty web/handler (config/http-spec))))
