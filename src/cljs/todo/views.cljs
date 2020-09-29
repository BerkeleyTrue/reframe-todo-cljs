(ns todo.views
  (:require
   [re-frame.core :as rf]
   [todo.db :as db]
   [todo.todos :refer [Todos]]
   [todo.input :refer [Input]]
   [cljs-css-modules.macro :as cx]))
   
   
(cx/defstyle styles
  [".root" {
            :display "flex"
            :flex-flow "column nowrap"
            :height "100vh"
            :width "100vw"}]
  [".header" {
              :text-align "center"}]
  [".main" {
            :align-items "center"
            :display "flex"
            :flex-flow "column nowrap"
            :justify-content "flex-start"
            :width "100%"}])
  

(defn main-panel []
  (let [title (rf/subscribe [::db/title])]
    [:div {:className (:root styles)}
      [:header {:className (:header styles)}
        [:h1 @title]
        [:h2 "What needs to be done"]]
      [:main {:className (:main styles)}
          (Todos)
          (Input)]]))
