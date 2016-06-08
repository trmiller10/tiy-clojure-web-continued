 (ns tiy-clojure-web-continued.web
   (:require [compojure.core :refer [defroutes]]
             [ring.adapter.jetty :as ring]
             [compojure.route :as route]
             [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
             [tiy-clojure-web-continued.controllers.notes :as notes]
             [tiy-clojure-web-continued.views.layout :as layout]
             [tiy-clojure-web-continued.models.migration :as schema])
   (:gen-class))

(defroutes routes
           notes/routes
           (route/resources "/")
           (route/not-found (layout/four-oh-four)))

(def application (wrap-defaults routes site-defaults))
(defonce server (atom nil))

(defn start [port]
  (reset! server (ring/run-jetty application {:port port
                                              :join? false})))

(defn -main []
  (when @server (.stop @server))
  (schema/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8080"))]
    (start port)))
