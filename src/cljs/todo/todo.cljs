(ns todo.todo
  (:require
    [re-frame.core :as rf]
    [todo.subs :as subs]))

(defn Todo [id]
  (let [
        todo (deref (rf/subscribe [::subs/todo id]))
        onComplete (fn [] (rf/dispatch [:todo/on-complete id]))
        className (str "todo" (if (:completed? todo) " completed" ""))]

    [:li {:className className
          :key id} 
      [:div
        (:title todo)]

      [:button {:onClick onComplete}
        "Completed"]]))
