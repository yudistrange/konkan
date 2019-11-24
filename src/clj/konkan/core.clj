(ns konkan.core
  (:require [konkan.web :as web]
            [konkan.config :as config]
            [konkan.db.pool :as db-pool]
            [ring.adapter.jetty :as jetty]))

(defn -main
  [& args]
  (config/init!)
  (db-pool/init!)
  (jetty/run-jetty web/handler (config/http-spec)))
