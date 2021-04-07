(ns clj.user
  "Commonly used symbols for easy access in the Clojure REPL during
  development."
  (:require
    [clojure.pprint :refer (pprint)]
    [clojure.string :as str]
    [integrant.repl :as ig-repl :refer [go halt reset reset-all]]
    [todo.system :refer [config]]
    [todo.core :refer [app]]))


(ig-repl/set-prep! (constantly config))

(comment
  (app {:request-method :post :uri "/commands/create-todo"})
  (go)
  (halt)
  (reset)
  (reset-all))
