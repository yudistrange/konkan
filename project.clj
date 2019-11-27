(defproject konkan "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.postgresql/postgresql "42.2.8.jre7"]

                 [seancorfield/next.jdbc "1.0.10" :exclusions [org.clojure/clojure]]
                 [aero "1.1.3"]
                 [hikari-cp "2.9.0"]
                 [ragtime "0.8.0"]

                 [ring/ring-core "1.8.0"]
                 [ring/ring-jetty-adapter "1.8.0"]
                 [bidi "2.1.6"]

                 [org.clojure/data.json "0.2.7"]]
  :repl-options {:init-ns konkan.core}
  :main konkan.core
  :source-paths ["src/clj" "src/cljs"]
  :test-paths ["test/clj" "test/cljs"])
