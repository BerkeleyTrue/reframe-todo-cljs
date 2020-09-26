(ns todo.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
  ::todos
  (fn [db]
    (let [todos (:todos db)
          by-id (:todos-by-id db)]
      (map (fn [id] (get by-id id)) todos))))

(re-frame/reg-sub
  ::new-todo
  (fn [db] (:new-todo db)))
