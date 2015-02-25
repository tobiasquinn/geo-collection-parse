(defproject geo-collection-parse "0.1.0-SNAPSHOT"
  :description "spatialite geo tests"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [org.xerial/sqlite-jdbc "3.8.7"]]
  :main ^:skip-aot geo-collection-parse.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
