(ns konkan.db.core
  (:require [clojure.data.json :as json]
            [next.jdbc.result-set :as result-set]
            [next.jdbc.prepare :as prepare])
  (:import java.sql.PreparedStatement
           java.sql.Timestamp
           org.postgresql.util.PGobject))

(defn- value-to-json-pgobject [value]
  (doto (PGobject.)
    (.setType "json")
    (.setValue (json/write-str value))))

(defn- json-pgobject-to-value [^PGobject pgobject]
  (-> pgobject
      (.getValue)
      (json/read-str :key-fn keyword)))

(defn extend-db-protocols []
  (extend-protocol prepare/SettableParameter
    clojure.lang.IPersistentMap
    (set-parameter [^clojure.lang.IPersistentMap v ^PreparedStatement ps ^long i]
      (.setObject ps i (value-to-json-pgobject v))))

  (extend-protocol result-set/ReadableColumn
    org.postgresql.util.PGobject
    (result-set/read-column-by-label ^clojure.lang.IPersistentMap [^org.postgresql.util.PGobject v _]
      (json-pgobject-to-value v))
    (result-set/read-column-by-index ^clojure.lang.IPersistentMap [^org.postgresql.util.PGobject v _2 _3]
      (json-pgobject-to-value v))))
