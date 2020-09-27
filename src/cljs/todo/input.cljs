(ns todo.input
  (:require
   [re-frame.core :as rf]
   [todo.db :as db]
   [cljs-css-modules.macro :refer-macros [defstyle]]))
   
   
    
(defstyle styles
  [".input-wrap" {
                  :width "400px"
                  :height "44px"}])

(defn Input []
  (let [new-todo (rf/subscribe [::db/new-todo])
        onPressEnter (fn [e] (when (= (-> e .-key) "Enter")
                                   (rf/dispatch [:new-todo/press-enter @new-todo])))
        onChange (fn [e] (rf/dispatch [:new-todo/on-change (-> e .-target .-value)]))]

    [:div {:className (:input-wrap styles)} 
      [:label "Todo"
        [:input {:className "todo-input"
                 :value @new-todo
                 :onKeyDown onPressEnter
                 :onChange onChange}]]]))
