(ns konkan.ping.handler
  (:require [ring.util.response :as resp]))

(defn ping [request]
  (resp/response "pong"))
