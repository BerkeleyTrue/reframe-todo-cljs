(ns todo.views
  (:require
   [re-frame.core :as re-frame]
   [todo.subs :as subs]))


(defn Todo [todo]
  [:li {:className (str
                     "todo"
                     (if (:completed? todo) " completed" ""))
        :key (:title todo)}
    [:div
      (:title todo)]])

(defn Todos [todos]
  [:div {:className "todos-wrap"}
    [:ul {:className "todos"}
      (map Todo todos)]])

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        todos (re-frame/subscribe [::subs/todos])]

    [:div
      [:header
        [:h1 @name]
        [:h2 "What needs to be done"]]
      [:main
        [:div {:className "input-wrap"}
          (Todos @todos)
          [:label "Todo"
            [:input {:className "todo-input"}]]]]]))
