(ns konkan-ui.core
  (:require [reagent.core :as r]))

(defn root []
  [:h1 "hello there!"])

(defn main! []
  (r/render [root]
            (.getElementById js/document "konkan-app")))

(defn reload! []
  (main!))
