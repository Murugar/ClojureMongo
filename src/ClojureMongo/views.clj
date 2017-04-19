(ns ClojureMongo.views
  (:require [ClojureMongo.mongodb :as db]
            [clojure.string :as str]
            [hiccup.page :as hic-p]))

(defn gen-page-head
  [title]
  [:head
   [:title (str "Locations Info: " title)]
   (hic-p/include-css "/css/styles.css")
   (hic-p/include-css "/css/bootstrap.min.css")])


(def header-links
  [:div {:class "container-fluid bg-info"}
  [:div#header-links
   "[ "
   [:a {:href "#"} "Clojure Mongo"]
   " ====== "
   [:a {:href "/"} "Home"]
   " | "
   [:a {:href "/add-location"} "Add a Location"]
   " | "
   [:a {:href "/all-locations"} "View All Locations"]
   " ]"]])


(defn home-page
  []
  (hic-p/html5
   (gen-page-head "Home")
   header-links
   [:br]
   [:div {:class "container"}
   [:div {:class "panel panel-primary"} 
   [:div {:class "panel-heading"} "Add Location"]  
   [:div {:class "panel-body"}
   [:h1 {:class "text-success"} "Home"]
   [:p {:class "text-primary"} "Webapp to store and display some 2D (x,y) locations."]]]]))


(defn add-location-page
  []
  (hic-p/html5
   (gen-page-head "Add a Location")
   header-links
   [:br]
   [:div {:class "container"}
   [:div {:class "panel panel-primary"} 
   [:div {:class "panel-heading"} "Add Location"]  
   [:div {:class "panel-body"} 
   [:h1 {:class "text-success"} "Add a Location"]
   [:br]
   [:form {:action "/add-location" :method "POST"}
    [:p {:class "text-primary"} "x value: " [:input {:type "text" :name "x"}]]
    [:p {:class "text-primary"} "y value: " [:input {:type "text" :name "y"}]]
    [:br]
    [:p [:input {:class "btn btn-danger" :type "submit" :value "Submit"}]]]]]]))


(defn add-location-results-page
  [{:keys [x y]} id]
    (hic-p/html5
     (gen-page-head "Added a Location")
     header-links
       [:br]
     [:div {:class "container"}
     [:div {:class "panel panel-primary"} 
     [:div {:class "panel-heading"} "Add Location"]  
     [:div {:class "panel-body"} 
     [:h1 "Added a Location"]
     [:p {:class "text-primary"} "Added [" x ", " y "] (_id: " id ") to the db. "
      [:a {:href (str "/location/" id) :class "text-danger"} "See for yourself"]
      "."]]]])
  )


(defn location-page
  [loc-id]
  (let [{x :x y :y} (db/get-xy loc-id)]
    (hic-p/html5
     (gen-page-head (str "Location " loc-id))
     header-links
       [:br]
     [:div {:class "container"}
     [:div {:class "panel panel-primary"} 
     [:div {:class "panel-heading"} "Added a Location"]  
     [:div {:class "panel-body"} 
     [:h1 {:class "text-danger"}  "A Single Location"]
     [:p {:class "text-info"} "id: " loc-id]
     [:p {:class "text-warning"} "x: " x]
     [:p {:class "text-success"} "y: " y]]]])))


(defn all-locations-page
  []
  (let [all-locs (db/get-all-locations)]
    (println "*** do I get here: " all-locs)
    (hic-p/html5
     (gen-page-head "All Locations in the db")
     header-links
       [:br]
         [:div {:class "container"}
         [:div {:class "panel panel-primary"} 
         [:div {:class "panel-heading"} "Add Location"]  
         [:div {:class "panel-body"} 
     [:h1 {:class "text-primary"} "All Locations"]
     [:br]
     [:table {:class "table table-bordered"}
      [:tr {:class "success"} [:th "x"] [:th "y"]]
      (for [loc all-locs]
        [:tr  {:class "warning"} [:td (:x loc)] [:td (:y loc)]])]]]])))


