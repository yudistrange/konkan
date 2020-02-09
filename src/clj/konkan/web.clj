(ns konkan.web
  (:require [konkan.routes :refer [routes]]
            [ring.middleware.json :refer [wrap-json-response]]
            [bidi.ring :refer [make-handler]]))

(def handler
  (wrap-json-response
   (make-handler routes)))
