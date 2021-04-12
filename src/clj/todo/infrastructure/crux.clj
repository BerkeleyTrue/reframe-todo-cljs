(ns todo.infrastructure.crux
  (:require
    [crux.api :as crux]
    [integrant.core :as ig]
    [taoensso.timbre :refer [info]]))

(defmethod ig/init-key :infra/crux
  [_ _]
  (info "starting crux server")
  (crux/start-node {}))

(defmethod ig/halt-key! :infra/crux
  [_ crux]
  (info "stopping crux server")
  (.close crux))
