(ns todo.core
  (:require
   [reitit.ring :as ring]
   [reitit.swagger :as swagger]
   [reitit.swagger-ui :as swagger-ui]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [reitit.ring.middleware.parameters :as parameters]
   [reitit.ring.middleware.exception :as exception]
   ; [reitit.coercion.malli]
   [reitit.ring.coercion :as coercion]
   [muuntaja.core :as m]
   [todo.api.core :refer [routes]]))



(defn handler [_]
  {:status 200
   :body "ping"})

(def router
  (ring/router
    [["/ping" {:get handler}]
     routes
     ["" {:no-doc true}
      ["/swagger.json" {:get (swagger/create-swagger-handler)}]
      ["/api-docs/*" {:get (swagger-ui/create-swagger-ui-handler)}]]]
    {:data {:muuntaja m/instance
            :middleware
            [parameters/parameters-middleware
             muuntaja/format-negotiate-middleware
             muuntaja/format-response-middleware
             exception/exception-middleware
             muuntaja/format-request-middleware
             coercion/coerce-response-middleware
             coercion/coerce-request-middleware]}}))

(def app
  (ring/ring-handler
    router
    (ring/create-default-handler)))

(comment
  (app {:request-method :get :uri "/api/commands"})
  (app {:request-method :get :uri "/swagger.json"}))
