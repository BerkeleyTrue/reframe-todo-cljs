(ns todo.events
  (:require
   [re-frame.core :as rf]
   [todo.db :as db]))


(rf/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(rf/reg-event-db
  :todo-on-change
  (fn [db [_ val]]
    (assoc db :new-todo val)))

(rf/reg-event-db
  :new-todo-on-enter
  (fn [db [_ new-todo]]
    (-> db
      (assoc :new-todo "")
      (assoc :todos (conj (:todos db)
                          {
                           :title new-todo
                           :completed? false})))))
