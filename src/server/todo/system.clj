(ns todo.system
  (:require
    [integrant.core :as ig]
    [org.httpkit.server :as http]))


(def config
  {:system/http
   {:port 8129
    :handler (ig/ref :core/app)}
   :core/app nil})

(defmethod ig/init-key :system/http
  [_ {:keys [port handler]}]
  (http/run-server handler {:port port}))

(defmethod ig/halt-key! :system/http
  [_ stop-server]
  (stop-server :timeout 100))
