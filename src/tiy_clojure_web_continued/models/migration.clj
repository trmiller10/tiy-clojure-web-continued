;;MAKES SURE THAT WE HAVE A DATABASE READY TO WORK WITH
;(ns tiy-clojure-web-continued.models.migration
;  (:require [clojure.java.jdbc :as sql]
;            [tiy_clojure_web_continued.models.note :as note]))

;;check if schema has been migrated to database
;;aka, does the database exist already?
;(defn db-schema-migrated? [] (->(sql/query note/spec
 ;                                          [(str
  ;                                            "select count(*) from information_schema.tables"
   ;                                          "where table_name='notes'")])
    ;                            first :count pos?))

;(defn apply-schema-migration
 ; []
  ;(when (not (db-schema-migrated?))
   ; (sql/db-do-commands postgres
    ;                    (sql/create-table-dll
     ;                     :notes [[:id :serial "PRIMARY KEY"]
      ;                            [:body :varchar "NOT NULL"]
       ;                           [:created_at :timestamp
        ;                           "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]]))))

(ns tiy-clojure-web-continued.models.migration
    (:require [clojure.java.jdbc :as sql]
              [tiy-clojure-web-continued.models.note :as note]))

(defn migrated? []
  (-> (sql/query note/spec
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='notes'")])
      first :count pos?))

(defn migrate []
  (when (not (migrated?))
    (print "Creating database structure...") (flush)
    (sql/db-do-commands note/spec "CREATE EXTENSION IF NOT EXISTS hstore;")
    (sql/db-do-commands note/spec
                        (sql/create-table-ddl
                          :notes
                          [[:id :serial "PRIMARY KEY"]
                           [:body :varchar "NOT NULL"]
                           [:created_at :timestamp
                            "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]]))
    (println " done")))