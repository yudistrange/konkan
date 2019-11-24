(ns konkan.routes
  (:require [konkan.ping.handler :as ping-handler]
            [ring.util.response :as resp]))

(defn not-found [request]
  (resp/not-found "not found"))

(def routes ["/" {"ping" ping-handler/ping
                  true   not-found}])
