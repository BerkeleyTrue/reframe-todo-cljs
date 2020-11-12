(ns todo.core
  (:require
   [reitit.ring :as ring]))

(defn handler [_]
  {:status 200
   :body "ok"})

(def router
  (ring/router
    ["/ping" {:get handler}]))

(def app (ring/ring-handler router))
