(ns konkan.util)

(defn check-for-exception [val]
  (if (contains? val :exception)
    :error
    val))

(defn exception? [val]
  (contains? val :exception))

(defmacro safely-execute [f]
  `(try
     ~f
     (catch Exception e#
      {:exception e# :message (.getMessage e#)})))
