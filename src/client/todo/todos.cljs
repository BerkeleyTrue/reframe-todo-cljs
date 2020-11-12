(ns todo.todos
  (:require
    [re-frame.core :as rf]
    [todo.db :as db]
    [todo.todo :refer [Todo]]))
    
(defn Todos []
  (let [todos (rf/subscribe [::db/todos])]

    [:div.container.flex.flex-col.items-center.mb-10
        [:ul.w-full.flex.flex-col.items-center
          (doall (map Todo @todos))]]))
