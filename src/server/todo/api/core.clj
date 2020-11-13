(ns todo.api.core)
  ; (:require))

(defn ok-handler [_]
  {:status 200
   :body "ok"})

(def routes
  ["/api" {:get ok-handler}])
