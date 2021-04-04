(ns todo.application.core
  (:require
   [reitit.swagger :as swagger]
   [reitit.swagger-ui :as swagger-ui]))

(def swagger-routes
  ["" {:no-doc true}
   ["/swagger.json" {:get (swagger/create-swagger-handler)}]
   ["/api-docs/*" {:get (swagger-ui/create-swagger-ui-handler)}]])
