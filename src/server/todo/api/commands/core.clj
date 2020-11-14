(ns todo.api.commands.core)

(defn commands [_]
  {:status 200
   :body "commands"})



(def routes
  ["/commands" {:get commands}])
