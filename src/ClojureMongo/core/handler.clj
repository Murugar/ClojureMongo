(ns ClojureMongo.core.handler
  (:require
            [ClojureMongo.views :as views]
            [ClojureMongo.mongodb :as db]
            [compojure.core :as cc]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn add-location-results
  [{:keys [x y]}]
  (db/add-location-to-db x y)
  )

(cc/defroutes app-routes
  (cc/GET "/"
       []
       (views/home-page))

  (cc/GET "/add-location"
       []
       (views/add-location-page))

  (cc/POST "/add-location"
        {params :params}
        (def id (add-location-results params))
        (views/add-location-results-page params id))

  (cc/GET "/location/:loc-id"
       [loc-id]
       (views/location-page loc-id))

  (cc/GET "/all-locations"
       []
       (views/all-locations-page))

  (cc/GET "/rahul"
      []
      "Hello Rahul")

  (route/resources "/")

  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
