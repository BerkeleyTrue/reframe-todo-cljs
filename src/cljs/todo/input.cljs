(ns todo.input
  (:require
   [re-frame.core :as rf]
   [todo.subs :as subs]))
    
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
