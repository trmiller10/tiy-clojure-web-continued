(defproject tiy-clojure-web-continued "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.2"]
                 [hiccup "1.0.5"]]
  :main ^:skip-aot tiy_clojure_web_continued.web
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler tiy-clojure-web-continued.web/application
         :init tiy-clojure-web-continued.models.migration/migrate}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}})
