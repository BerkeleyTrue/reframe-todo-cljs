(ns todo.subs
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 ::title
 (fn [db]
   (:title db)))

(rf/reg-sub
  ::todos
  (fn [db]
    (:todos db)))

(rf/reg-sub
  ::todo
  (fn [{:keys [todos-by-id]} [_ id]]
    (get todos-by-id id)))

(rf/reg-sub
  ::new-todo
  (fn [db] (:new-todo db)))
