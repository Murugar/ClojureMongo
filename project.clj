(defproject ClojureMongo "0.1.0"
  :description "Locations Info"
  :url "http://test.com"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.novemberain/monger "3.1.0"]
                 [instaparse "1.4.1"]
                 [compojure "1.3.1"]
                 [hickory "0.5.4"]
                 [ring/ring-defaults "0.1.2"]
                 [hiccup "1.0.2"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [com.h2database/h2 "1.3.170"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler ClojureMongo.core.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
