(ns todo.application.core)


(defn createTodo []
  (println "Create Todo")
  "done")


(defn updateTodo []
  (println "todo updated")
  "done")

(def commands
  [{:handler createTodo
    :name "create-todo"
    :parameters
    {:body
     {:title string?}}}])
