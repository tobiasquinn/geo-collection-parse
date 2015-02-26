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

(defn create-spatial-db! []
  (println "Create spatial db")
  (try (db-do-commands spatialite-db
                       (create-table-ddl :locations
                                         [:description :text]))
       (catch Exception e (println e)))
  (with-db-connection [con-db spatialite-db]
    (query con-db ["SELECT load_extension('mod_spatialite')"])
    (println "Creating spatial metadata")
    (query con-db ["SELECT InitSpatialMetaData()"])
    (println "Adding geometry column")
    (let [result (query con-db ["SELECT AddGeometryColumn('locations', 'point', 4326, 'POINT', 'XY')"])]
      (println "RESULTS:" result))))

(defn- save-to-spatialite [record]
  (println "Should save:" record))

;; we want to read in the records and enter then into the spatial db
(defn convert-geo-entries! []
  (create-spatial-db!))
;  (doall (map save-to-spatialite (query geo-collection-db ["SELECT * FROM locations"]))))
