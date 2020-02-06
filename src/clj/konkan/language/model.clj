(ns konkan.language.model
  (:require [clojure.string :as str]
            [konkan.language.db :as lang-db]
            [konkan.util :as util]))

(defn get [conn name]
  (let [normalized-name (str/lower-case name)
        lang (lang-db/get conn normalized-name)]
    (cond
      (util/exception? lang) {:status :error :message (:message lang)}
      (empty? lang) {:status :not-found}
      :else {:status :ok :language (first lang)})))

(defn create [conn name]
  (let [normalized-name (str/lower-case name)
        lang (lang-db/create conn normalized-name)]
    (cond
      (util/exception? lang) {:status :error :message (:message lang)}
      :else {:status :ok :language lang})))
