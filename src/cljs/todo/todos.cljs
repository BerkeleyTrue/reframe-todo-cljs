(ns todo.todos
  (:require
    [re-frame.core :as rf]
    [todo.subs :as subs]
    [todo.todo :refer [Todo]]))
     

(defn Todos []
  (let [todos (rf/subscribe [::subs/todos])]

    [:div {:className "todos-wrap"}
        [:ul {:className "todos"}
          (doall (map Todo @todos))]]))
