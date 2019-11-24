(ns konkan.web
  (:require [konkan.routes :refer [routes]]
            [bidi.ring :refer [make-handler]]))

(def handler
  (make-handler routes))
