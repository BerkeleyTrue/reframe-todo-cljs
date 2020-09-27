(ns todo.todo
  (:require
    [re-frame.core :as rf]
    [todo.db :as db]))

(defn Todo [id]
  (let [
        todo (deref (rf/subscribe [::db/todo id]))
        onComplete (fn [] (rf/dispatch [:todo/on-complete id]))
        className (str "todo" (if (:completed? todo) " completed" ""))]

    [:li {:className className
          :key id} 
      [:div
        (:title todo)]

      [:button {:onClick onComplete}
        "Completed"]]))
