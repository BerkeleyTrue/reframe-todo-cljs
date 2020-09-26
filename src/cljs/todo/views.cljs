(ns todo.views
  (:require
   [re-frame.core :as rf]
   [todo.subs :as subs]
   [todo.todos :refer [Todos]]
   [todo.input :refer [Input]]))
   
   
(defn main-panel []
  (let [title (rf/subscribe [::subs/title])]

    [:div
      [:header
        [:h1 @title]
        [:h2 "What needs to be done"]]
      [:main
          (Todos)
          (Input)]]))
