(ns todo.views
  (:require
   [re-frame.core :as rf]
   [todo.subs :as subs]))


(defn Todo [todo]
  (let [
        onComplete (fn [] (rf/dispatch [:todo/on-complete (:id todo)]))
        className (str "todo" (if (:completed? todo) " completed" ""))]
        

    [:li {:className className
          :key (:id todo)}
      [:div
        (:title todo)]

      [:button {:onClick onComplete}
        "Completed"]]))


(defn Todos []
  (let [todos (rf/subscribe [::subs/todos])]

    [:div {:className "todos-wrap"}
        [:ul {:className "todos"}
          (map Todo @todos)]]))


(defn Input []
  (let [new-todo (rf/subscribe [::subs/new-todo])
        onPressEnter (fn [e] (when (= (-> e .-key) "Enter")
                                   (rf/dispatch [:new-todo/press-enter @new-todo])))
        onChange (fn [e] (rf/dispatch [:new-todo/on-change (-> e .-target .-value)]))]

    [:div {:className "input-wrap"}
      [:label "Todo"
        [:input {:className "todo-input"
                 :value @new-todo
                 :onKeyDown onPressEnter
                 :onChange onChange}]]]))


(defn main-panel []
  (let [name (rf/subscribe [::subs/name])]

    [:div
      [:header
        [:h1 @name]
        [:h2 "What needs to be done"]]
      [:main
          (Todos)
          (Input)]]))
