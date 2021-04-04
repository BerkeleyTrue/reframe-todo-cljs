(ns todo.system
  (:require
    [todo.core :as todo]
    [integrant.core :as ig]
    [org.httpkit.server :as http]))


(def config
  {:system/http
   {:port 8129
    :handler (ig/ref :system/app)}
   :system/app nil})

(defmethod ig/init-key :system/app
  [_ _]
  (println "initiating app")
  todo/app)

(defmethod ig/init-key :system/http
  [_ {:keys [port handler]}]
  (http/run-server handler {:port port}))

(defmethod ig/halt-key! :system/http
  [_ server]
  (server :timeout 100))
