(ns my-webapp.core.repl
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:require [hickory.core :refer :all])
  (:import org.bson.types.ObjectId))

(use 'hickory.core)

(def parsed-doc (parse "<!DOCTYPE html>\n<html><head><title>Locations: Added a Location</title><link href=\"/css/styles.css\" rel=\"stylesheet\" type=\"text/css\"></head><div id=\"header-links\">[ <a href=\"/\">Home</a> | <a href=\"/add-location\">Add a Location</a> | <a href=\"/all-locations\">View All Locations</a> ]</div><h1>Added a Location</h1><p>Added [2, 3] (id: 40) to the db. <a href=\"/location/40\">See for yourself</a>.</p></html>"))
(def parsed-document-hiccup (as-hiccup parsed-doc))
parsed-document-hiccup
(nth parsed-document-hiccup 1)
(def parsed-document-hickory (as-hickory parsed-doc))
parsed-document-hickory
((:content ((:content((:content ((:content parsed-document-hickory) 1)) 1)) 2)) 0)


(def conn (mg/connect {:host "localhost" :port 27017}))
(def db (mg/get-db conn "robomongodb"))

(let [conn (mg/connect)]
  (mg/disconnect conn))

;(:name (nth (mc/find-maps db "locations") 2))

;(let []
;  (:_id (mc/insert-and-return db "robotesting" { :_id (ObjectId.) :name "Jayne" }))
;)


;(def oid (ObjectId.))
;oid

;(defn add-location-to-db
;  [x y]
;  (def oid (ObjectId.))
;    (mc/insert db "robotesting" { :_id oid :x x :y y })
;    oid)

;(add-location-to-db 2 7)

;(defn get-xy
;  [loc-id]
;  (mc/find-one-as-map db "robotesting" { :_id (ObjectId. loc-id) })
;
;  )

;(mc/find-one-as-map db "robotesting" { :_id (ObjectId. "54949d0ee4b0b009a2ebc95d") })

;(:x (get-xy "54949d0ee4b0b009a2ebc95d"))

; '({:_id "#<ObjectId 5494562ede968f65be4cc769>", :name "Gary Black"} {:_id "#<ObjectId 54945673de968f65be4cc76a>", :name "Rahul Ramji"} {:_id "#<ObjectId 549458cc578a9cf68ea5dbc0>", :name "Bob"} {:_id "#<ObjectId 54945ad5578a9cf68ea5dbc1>", :name "Jayne"})
