(ns todo.views
  (:require
   [re-frame.core :as rf]
   [todo.db :as db]
   [todo.todos :refer [Todos]]
   [todo.input :refer [Input]]))

(defn main-panel []
  (let [title (rf/subscribe [::db/title])]
    [:div.container.mx-auto.flex.flex-col
      [:header.flex.flex-col.justify-center.items-center
        [:h1 @title]
        [:h2 "What needs to be done"]]
      [:main.flex.flex-col.justify-items-center.items-center
          (Todos)
          (Input)]]))
