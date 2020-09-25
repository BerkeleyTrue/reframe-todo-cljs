(ns todo.views
  (:require
   [re-frame.core :as rf]
   [todo.subs :as subs]))


(defn Todo [todo]
  [:li {:className (str "todo" (if (:completed? todo) " completed" ""))
        :key (:title todo)}
    [:div
      (:title todo)]])

(defn Todos []
  (let [todos (rf/subscribe [::subs/todos])]

    [:div {:className "todos-wrap"}
      [:ul {:className "todos"}
        (map Todo @todos)]]))

(defn Input []
  (let [new-todo (rf/subscribe [::subs/new-todo])]
    [:div {:className "input-wrap"}
      [:label "Todo"
       [:input {:className "todo-input"
                :value @new-todo
                :onKeyDown (fn [e] (when (= (-> e .-key) "Enter")
                                     (rf/dispatch [:new-todo-on-enter @new-todo])))

                :onChange (fn [e] (rf/dispatch [:todo-on-change (-> e .-target .-value)]))}]]]))

(defn main-panel []
  (let [name (rf/subscribe [::subs/name])]

    [:div
      [:header
        [:h1 @name]
        [:h2 "What needs to be done"]]
      [:main
          (Todos)
          (Input)]]))
