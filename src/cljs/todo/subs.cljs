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
    (:todos db)))

(re-frame/reg-sub
  ::new-todo
  (fn [db] (:new-todo db)))
