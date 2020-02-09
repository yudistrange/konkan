(ns konkan.word.handler
  (:require [ring.util.response :as resp]
            [konkan.db.pool :as db-pool]
            [konkan.word.model :as word-model]))

(defn get [{{word :word} :params}]
  (let [conn (db-pool/get-connection)
        searched-word (word-model/get conn word)]
    (prn searched-word)
    (cond
      (= (:status searched-word)
         :not-found) (resp/not-found)
      (= (:status searched-word)
         :error) (resp/status (resp/response "failed") 500)
      :else (resp/response searched-word))))

(defn create [{{word :word lang :language-name meaning :meaning metadata :metadata} :params}]
  (let [conn (db-pool/get-connection)
        word (word-model/create conn word lang meaning metadata)]
    (cond
      (= (:status word)
         :error) (resp/status (resp/response "failed") 500)
      :else (resp/created))))
