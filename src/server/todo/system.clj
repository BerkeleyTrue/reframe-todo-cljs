(ns todo.system
  (:require
    [integrant.core :as ig]
    [org.httpkit.server :as http]
    [taoensso.timbre :refer [info]]))



(def config
  {:system/http
   {:port 8129
    :handler (ig/ref :core/app)}
   :core/app nil})

(defmethod ig/init-key :system/http
  [_ {:keys [port handler]}]
  (info "starting server")
  (http/run-server handler {:port port}))

(defmethod ig/halt-key! :system/http
  [_ stop-server]
  (info "stopping server")
  (stop-server :timeout 100))
