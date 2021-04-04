(ns todo.core
  (:require
   [reitit.ring :as ring]
   [muuntaja.core :as m]
   [todo.application.core :as app]
   [todo.infrastructure.core :as infra]))


(def router
  (ring/router
    [app/swagger-routes]
    {:data {:muuntaja m/instance
            :middleware infra/middlewares}}))

(def app
  (ring/ring-handler
    router
    infra/default-handler))

(comment
  (app {:request-method :get :uri "/api/commands"})
  (app {:request-method :get :uri "/api-docs/"}))
