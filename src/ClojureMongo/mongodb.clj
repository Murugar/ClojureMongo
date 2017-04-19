(ns ClojureMongo.mongodb
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:require [hickory.core :refer :all])
  (:import org.bson.types.ObjectId))


(def conn (mg/connect {:host "localhost" :port 27017}))
(def db (mg/get-db conn "robomongodb"))


(defn add-location-to-db
  [x y]
  (def oid (ObjectId.))
    (mc/insert db "locations" { :_id oid :x x :y y })
    oid)

(defn get-all-locations
  []
  (println "### do I get called too?")
  ;(println "name is " (:name (nth (mc/find-maps db "robotest") 2)))
  (mc/find-maps db "locations"))
  ;'({:_id "#<ObjectId 5494562ede968f65be4cc769>", :name "Gary Black"} {:_id "#<ObjectId 54945673de968f65be4cc76a>", :name "Rahul Ramji"} {:_id "#<ObjectId 549458cc578a9cf68ea5dbc0>", :name "Bob"} {:_id "#<ObjectId 54945ad5578a9cf68ea5dbc1>", :name "Jayne"}))

(defn get-xy
  [loc-id]
  (mc/find-one-as-map db "locations" { :_id (ObjectId. loc-id) })
  )
