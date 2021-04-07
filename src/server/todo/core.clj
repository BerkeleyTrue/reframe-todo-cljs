(ns todo.core
  (:require
   [reitit.ring :as ring]
   [reitit.ring.spec :as rrs]
   [reitit.spec :as rs]
   [expound.alpha :as e]
   [muuntaja.core :as m]
   [todo.infrastructure.core :as infra]))

(def router
  (ring/router
    [infra/api-routes
     infra/swagger-routes]
    {:validate rrs/validate
     ::rs/explain e/expound-str
     :data {:muuntaja m/instance
            :middleware infra/middlewares}}))

(def app
  (ring/ring-handler
    router
    infra/default-handler))

(comment
  (app {:request-method :post :uri "/commands/create-todo" :body {:title "foo"}}))
