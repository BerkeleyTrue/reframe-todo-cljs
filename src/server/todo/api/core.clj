(ns todo.api.core
  (:require
    [todo.api.commands.core :as commands]))


(defn ok-handler [_]
  {:status 200
   :body "ok"})

(def routes
  ["/api"
   commands/routes
   ["" {:get ok-handler}]])
