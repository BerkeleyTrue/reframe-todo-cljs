(ns todo.events
  (:require
   [re-frame.core :as rf]
   [todo.db :as db]))


(rf/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(rf/reg-event-db
  :new-todo/on-change
  (fn [db [_ val]]
    (assoc db :new-todo val)))

(rf/reg-event-db
  :new-todo/press-enter
  (fn [db [_ title]]
      (let [ id (random-uuid)]
        (-> db
            (assoc :new-todo "")
            (assoc :todos (conj (:todos db) id))
            (assoc-in [:todos-by-id id] 
                      {:title title 
                       :completed? false
                       :id id})))))

(rf/reg-event-db
  :todo/on-complete
  (fn [db [_ id]]
    (-> db 
        (update-in [:todos-by-id id :completed?] not))))

(rf/reg-event-db
  :todo/on-delete
  (fn [db [_ id]]
    (-> db
        (dissoc id)
        (assoc :todos (filter (:todos db) id)))))
