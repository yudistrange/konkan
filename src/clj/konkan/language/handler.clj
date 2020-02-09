(ns konkan.language.handler
  (:require [ring.util.response :as resp]
            [konkan.db.pool :as db-pool]
            [konkan.language.model :as lang-model]))

(defn get [{params :params}]
  (let [conn (db-pool/get-connection)
        lang (->> params
                  :language-name
                  (lang-model/get conn))]
    (cond
      (= (:status lang)
         :not-found) (resp/not-found)
      (= (:status lang)
         :error) (resp/status (resp/response "failed") 500)
      :else (resp/response lang))))

(defn create [{params :params}]
  (let [conn (db-pool/get-connection)
        lang (->> params
                  :language-name
                  (lang-model/create conn))]
    (cond
      (= (:status lang)
         :error) (resp/status (resp/response "failed") 500)
      :else (resp/created))))
