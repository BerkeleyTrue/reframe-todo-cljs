(ns todo.core
  (:require
   [reitit.ring :as ring]
   [todo.api.core :refer [routes]]))


(defn handler [_]
  {:status 200
   :body "ok"})

(def router
  (ring/router
    [["/ping" {:get handler}]
     routes]))


(def app
  (ring/ring-handler
    router
    (ring/create-default-handler)))

(comment (app {:request-method :get :uri "/api"}))
