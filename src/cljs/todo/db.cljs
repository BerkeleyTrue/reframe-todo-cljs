(ns todo.db
  (:require
   [re-frame.core :as rf]))

(def default-db
  {
   :title "Todo App"
   :todos []
   :todos-by-id {}
   :new-todo ""})
     
(rf/reg-sub
  ::todos
  (fn [db]
    (:todos db)))

(rf/reg-sub
 ::title
 (fn [db]
   (:title db)))

(rf/reg-sub
  ::todo
  (fn [{:keys [todos-by-id]} [_ id]]
    (get todos-by-id id)))

(rf/reg-sub
  ::new-todo
  (fn [db] (:new-todo db)))
