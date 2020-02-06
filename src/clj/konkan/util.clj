(ns konkan.util)

(defn exception? [val]
  (contains? val :exception))

(defmacro safely-execute [f]
  `(try
     ~f
     (catch Exception e#
      {:exception e# :message (.getMessage e#)})))
