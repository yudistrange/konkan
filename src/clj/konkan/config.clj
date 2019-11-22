(ns konkan.config
  (:require [aero.core :as aero]))

(defonce ^:private entries (atom nil))

(defn- default-profile []
  (keyword (or (System/getenv "CONFIG_PROFILE") "dev")))

(defn init!
  ([]
   (-> (default-profile)
       (init!)))

  ([profile]
   (swap! entries
          (fn [old-config]
            (aero/read-config (clojure.java.io/resource "config.edn") {:profile profile})))))

(defn db-spec []
  (:db-spec @entries))
