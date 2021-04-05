(ns todo.core
  (:require
   [reitit.ring :as ring]
   [muuntaja.core :as m]
   [todo.infrastructure.core :as infra]))

(def router
  (ring/router
    [infra/swagger-routes
     infra/api-routes]
    {:data {:muuntaja m/instance
            :middleware infra/middlewares}}))

(def app
  (ring/ring-handler
    router
    infra/default-handler))
