(ns geo-collection-parse.core
  (:require [geo-collection-parse.db :as db])
  (:gen-class))

(defn -main
  [& args]
  (println "Starting geo utils")
  (db/convert-geo-entries!))
