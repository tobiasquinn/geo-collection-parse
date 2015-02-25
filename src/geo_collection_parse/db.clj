(ns geo-collection-parse.db
  (:require [clojure.java.jdbc :refer :all]))

(def geo-collection-db
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "db/geo.db"})

(def spatialite-db
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :enable_load_extension true
   :subname "db/spatialite.db"})

(with-db-connection [con-db spatialite-db]
  (query con-db ["SELECT load_extension('mod_spatialite')"])
  (let [version (query con-db ["SELECT spatialite_version()"])]
    (println "spatialite driver version:" version)))

(defn- save-to-spatialite [record]
  (println "Should save:" record))

;; we want to read in the records and enter then into the spatial db
(defn convert-geo-entries! []
  (doall (map save-to-spatialite (query geo-collection-db ["SELECT * FROM locations"]))))
