(ns todo.todos
  (:require
    [re-frame.core :as rf]
    [todo.db :as db]
    [todo.todo :refer [Todo]]
    [cljs-css-modules.macro :as cx]))
    
(cx/defstyle styles
  [".todos-wrap" {
                  :max-width "400px"}]
  [".todos" {
             :width "100%"}])
  

(defn Todos []
  (let [todos (rf/subscribe [::db/todos])]

    [:div {:className (:todos-wrap styles)}
        [:ul {:className (:todos styles)}
          (doall (map Todo @todos))]]))
