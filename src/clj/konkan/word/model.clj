(ns konkan.word.model
  (:require [clojure.string :as str]
            [konkan.word.db :as word-db]
            [konkan.language.model :as lang-model]
            [konkan.util :as util]))

(defn get
  ([conn word]
   (let [w (word-db/get conn word)]
     (cond
       (util/exception? w) {:status :error :message (:message w)}
       (empty? w) {:status :not-found}
       :else {:status :ok :word w})))
  ([conn word _language]
   (get conn word)))

(defn create [conn word language meaning metadata]
  (let [{lang :language} (lang-model/get conn language)
        lang-id (:languages/id lang)
        normalized-word (str/lower-case word)
        created-word (word-db/create conn normalized-word lang-id meaning metadata)]
    (cond
      (util/exception? created-word) {:status :error :message (:message created-word)}
      :else {:status :ok :word created-word})))
