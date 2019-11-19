(ns konkan.config
  (:require [aero.core :as aero]))

(defonce entries (atom nil))

(defn init!
  ([]
   (init! "config.edn"))

  ([file-name]
   (swap! entries
          (fn [old-config]
            (aero/read-config (clojure.java.io/resource file-name))))))

(defn db-spec []
  (:db-spec @entries))
