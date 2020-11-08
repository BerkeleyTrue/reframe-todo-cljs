(ns todo.todo
  (:require
    [re-frame.core :as rf]
    [todo.db :as db]))

(defn Todo [id]
  (let [
        todo (deref (rf/subscribe [::db/todo id]))
        onComplete (fn [] (rf/dispatch [:todo/on-complete id]))
        className (str "todo" (if (:completed? todo) " line-through" ""))]

    [:li.flex.flex-row.justify-between.items-center.text-lg.max-w-md.w-full.mb-2
      {:class className
        :key id}
      [:div
        (:title todo)]

      [:button.flex.flex-row.justify-center.items-center.rounded-md.px-4.uppercase.text-md.font-bold.tracker-wide.text-primary.border-primary.border-2.min-w-40.h-10.shadow-sm
        {:onClick onComplete}
        "Completed"]]))
