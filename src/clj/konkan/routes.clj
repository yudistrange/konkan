(ns konkan.routes
  (:require [konkan.ping.handler :as ping-handler]
            [konkan.language.handler :as lang-handler]
            [konkan.word.handler :as word-handler]
            [ring.util.response :as resp]))

(defn not-found [request]
  (resp/not-found "not found"))

(def routes ["/" {"ping" ping-handler/ping
                  "language" {:get {["/" :language-name] lang-handler/get}
                              :post {"" lang-handler/create}}
                  "word" {:get {["/" :word] word-handler/get}
                          :post {"" word-handler/create}}
                  true   not-found}])
