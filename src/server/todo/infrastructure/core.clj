(ns todo.infrastructure.core
  (:require
   [reitit.ring :as ring]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [reitit.ring.middleware.parameters :as parameters]
   [reitit.ring.middleware.exception :as exception]
   [reitit.ring.coercion :as coercion]
   [reitit.swagger :as swagger]
   [reitit.swagger-ui :as swagger-ui]))

(def middlewares
  [parameters/parameters-middleware
   muuntaja/format-negotiate-middleware
   muuntaja/format-response-middleware
   exception/exception-middleware
   muuntaja/format-request-middleware
   coercion/coerce-response-middleware
   coercion/coerce-request-middleware])

(def default-handler
  (ring/routes
    (ring/redirect-trailing-slash-handler)
    (ring/create-default-handler)))

(def swagger-routes
  ["" {:no-doc true}
   ["/swagger.json" {:get (swagger/create-swagger-handler)}]
   ["/swagger/*" {:get (swagger-ui/create-swagger-ui-handler)}]])
