;;MAKES SURE THAT WE HAVE A DATABASE READY TO WORK WITH
(ns tiy-clojure-web-continued.models.migration
  (:require [clojure.java.jdbc :as sql]))

;;check if schema has been migrated to database
;;aka, does the database exist already?
(defn db-schema-migrated? [] (->(sql/query postgres
                                           [(str
                                              "select count(*) from information_schema.tables"
                                             "where table_name='notes'")])
                                first :count pos?))

(defn apply-schema-migration
  []
  (when (not (db-schema-migrated?))
    (sql/db-do-commands postgres
                        (sql/create-table-dll
                          :notes [[:id :serial "PRIMARY KEY"]
                                  [:body :varchar "NOT NULL"]
                                  [:created_at :timestamp
                                   "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]]))))