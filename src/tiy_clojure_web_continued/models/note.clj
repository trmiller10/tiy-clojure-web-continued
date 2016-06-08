;(ns tiy-clojure-web-continued.models.note
 ; (:require [clojure.java.jdbc :as sql]))

;;'or' evaluates expressions one at a time from left to right
;;System/getenv: Clojure sits on JVM and JVM's 'System' NS provides utilities and classes for accessing
;;the running system's features"
;; "add environment variables for any addons you may activate such as DATABASE_URL for the Postgres DB addon"
;(def spec (or (System/getenv "DATABASE_URL")
 ;             "postgresql://localhost:5432/notes"))

;;(sql/db-do-commands "postgresql://localhost:5432/notes" (sql/create-table-ddl :testing [[:data :text]]))
;(defn all []
 ; (into [] (sql/query spec ["select * from notes order by id desc"])))

;(defn create [note]
;  (sql/insert! spec :notes [:body] [note]))


(ns tiy-clojure-web-continued.models.note
  (:require [clojure.java.jdbc :as sql]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/notes"))

(defn all []
  (into [] (sql/query spec ["select * from notes order by id desc"])))

(defn create [note]
  (sql/insert! spec :notes [:body] [note]))
