(ns todo.input
  (:require
   [re-frame.core :as rf]
   [todo.db :as db]))

(defn Input []
  (let [new-todo (rf/subscribe [::db/new-todo])
        onPressEnter (fn [e] (when (= (-> e .-key) "Enter")
                                   (rf/dispatch [:new-todo/press-enter @new-todo])))
        onChange (fn [e] (rf/dispatch [:new-todo/on-change (-> e .-target .-value)]))]

    [:div.h-10.min-w-40.shadow-xl.flex.justify-center.items-center.border-2.border-solid.rounded-md
      [:label "+:"
        [:input.text-base.px-2
         {
          :value @new-todo
          :onKeyDown onPressEnter
          :onChange onChange}]]]))
