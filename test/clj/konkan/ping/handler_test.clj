(ns konkan.ping.handler-test
  (:require [konkan.ping.handler :as ping-handler]
            [clojure.test :refer :all]))

(deftest ping
  (testing "Hitting /ping returns pong"
    (is (ping-handler/ping {})
        "pong")))
