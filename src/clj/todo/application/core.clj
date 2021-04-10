(ns todo.application.core)

(def create-todo
  {:handler (fn [_] "done")
   :name "create-todo"
   :summary "create a new todo"
   :parameters
   {:body
    [:map
      [:title string?]]}})

(defn change-title [_]
  "done")

(def commands
  [create-todo])
