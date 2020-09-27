(ns todo.todos
  (:require
    [re-frame.core :as rf]
    [todo.db :as db]
    [todo.todo :refer [Todo]]))

(defn Todos []
  (let [todos (rf/subscribe [::db/todos])]

    [:div {:className "todos-wrap"}
        [:ul {:className "todos"}
          (doall (map Todo @todos))]]))
