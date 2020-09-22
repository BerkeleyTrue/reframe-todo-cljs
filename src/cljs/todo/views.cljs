(ns todo.views
  (:require
   [re-frame.core :as re-frame]
   [todo.subs :as subs]))


(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
      [:header
        [:h1 @name]
        [:h2 "What needs to be done"]]
      [:main
        [:div {:className "input-wrap"}
          [:label "Todo"
            [:input {:className "todo-input"}]]]]]))
