(ns konkan.db.pool
  (:require [hikari-cp.core :as hikari]
            [clojure.java.jdbc :as jdbc]
            [konkan.config :as config]))

(defonce ^:private connection (atom nil))

(defn init!
  ([]
   (let [db-spec (config/db-spec)]
     (init! db-spec)))

  ([db-spec]
   (swap! connection
          (fn [old-ds]
            (hikari/make-datasource db-spec)))))

(defn get-connection []
  @connection)
