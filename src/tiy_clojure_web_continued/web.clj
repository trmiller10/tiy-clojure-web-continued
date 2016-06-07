 (ns web
   (:require [compojure.core :refer [defroutes]]
             [ring.adapter.jetty :as ring]
             [compojure.route :as route]
             [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
             [shouter.controllers.shouts :as shouts]
             [shouter.views.layout :as layout]
             [shouter.models.migration :as schema])
   (:gen-class)))

(defonce server (atom nil))

(defn start [port]
  (reset! server (ring/run-jetty application {:port port
                                              :join? false})))

(defn -main []
  (when @server (.stop @server))
  (schema/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8080"))]
    (start port)))
