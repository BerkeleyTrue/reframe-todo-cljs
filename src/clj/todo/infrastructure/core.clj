(ns todo.infrastructure.core
  (:require
   [reitit.ring :as ring]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [reitit.ring.middleware.parameters :as parameters]
   [reitit.ring.middleware.exception :as exception]
   [reitit.ring.coercion :as coercion]
   [reitit.coercion.malli :as rmalli]
   [reitit.swagger :as swagger]
   [reitit.swagger-ui :as swagger-ui]
   [todo.application.core :as app]))

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

(defn- name->command-uri [name]
  (str "/commands/" name))

(defn- wrap-command [handler]
  (fn [request]
    (let [res (handler request)]
      (println res)
      {:status 200
       :body res})))

(def api-routes
  (->>
    app/commands
    (mapv
      (fn [{:keys [name handler summary parameters]}]
        [(name->command-uri name)
         {:name (keyword name)
          :coercion rmalli/coercion
          :post
          {:handler (wrap-command handler)
           :summary summary
           :parameters parameters}}]))))
