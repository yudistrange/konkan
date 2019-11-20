(defproject konkan "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.postgresql/postgresql "42.2.8.jre7"]
                 [seancorfield/next.jdbc "1.0.10"]
                 [aero "1.1.3"]
                 [hikari-cp "2.9.0"]
                 [ragtime "0.8.0"]]
  :repl-options {:init-ns konkan.core}
  :main konkan.core)
