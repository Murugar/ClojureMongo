(ns my-webapp.core.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [my-webapp.core.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "<!DOCTYPE html>\n<html><head><title>Locations: Home</title><link href=\"/css/styles.css\" rel=\"stylesheet\" type=\"text/css\"></head><div id=\"header-links\">[ <a href=\"/\">Home</a> | <a href=\"/add-location\">Add a Location</a> | <a href=\"/all-locations\">View All Locations</a> ]</div><h1>Home</h1><p>Webapp to store and display some 2D (x,y) locations.</p></html>"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404))))

  (testing "add-location"
    (let [response (app (mock/request :get "/add-location"))]
      (is (= (:status response) 200))
      (is (= (:body response) "<!DOCTYPE html>\n<html><head><title>Locations: Add a Location</title><link href=\"/css/styles.css\" rel=\"stylesheet\" type=\"text/css\"></head><div id=\"header-links\">[ <a href=\"/\">Home</a> | <a href=\"/add-location\">Add a Location</a> | <a href=\"/all-locations\">View All Locations</a> ]</div><h1>Add a Location</h1><form action=\"/add-location\" method=\"POST\"><p>x value: <input name=\"x\" type=\"text\"></p><p>y value: <input name=\"y\" type=\"text\"></p><p><input type=\"submit\" value=\"submit location\"></p></form></html>"))
      ))

  (testing "add-location-post"
    (let [response (app (mock/request :post "/add-location" {"x" "2" "y" "3"}))]
      (is (= (:status response) 200))
      ;(println "***\n\n" (:body response) "\n\n")
      ;(is (= (:body response) "<!DOCTYPE html>\n<html><head><title>Locations: Added a Location</title><link href=\"/css/styles.css\" rel=\"stylesheet\" type=\"text/css\"></head><div id=\"header-links\">[ <a href=\"/\">Home</a> | <a href=\"/add-location\">Add a Location</a> | <a href=\"/all-locations\">View All Locations</a> ]</div><h1>Added a Location</h1><p>Added [2, 3] (id: 40) to the db. <a href=\"/location/40\">See for yourself</a>.</p></html>"))
      ))
)
