(ns todo.todo
  (:require
    [re-frame.core :as rf]
    [todo.db :as db]))

(defn Todo [id]
  (let [
        todo (deref (rf/subscribe [::db/todo id]))
        onComplete (fn [] (rf/dispatch [:todo/on-complete id]))
        className (str "todo" (if (:completed? todo) " line-through" ""))]

    [:button.items-center.px-4.text-lg.tracker-wide.text-secondary.min-w-40.h-10.max-w-md.w-full.mb-2
      {:onClick onComplete
       :key id
       :class className}

      [:li.w-full.text-left
        (:title todo)]]))
