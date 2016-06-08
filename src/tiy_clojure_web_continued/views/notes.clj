(ns tiy-clojure-web-continued.views.notes
  (:require [tiy-clojure-web-continued.views.layout :as layout]
            [hiccup.core :refer [h]]
            [ring.util.anti-forgery :as x]))

(defn note-form []
  [:div {:id "note-form" :class "sixteen columns alpha omega"}
   [:form {:action "/" :method "post"}
    (x/anti-forgery-field)
    [:label {:for "note"} "Type in that note"]
    [:textarea {:name "note"}]
    [:button "Submit note"]]])

(defn display-notes [notes]
  [:div {:class "notes sixteen columns alpha omega"}
   (map (fn [note]
          [:h2 {:class "note"} (h (:body note))]) notes)])

(defn index [notes]
  (layout/common "NOTE-TAKER"
                 (note-form)
                 [:div {:class "clear"}]
                 (display-notes notes)))
