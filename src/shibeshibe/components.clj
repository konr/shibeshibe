(ns shibeshibe.components
  (:require [shibeshibe.datomic.core :as db]
            [shibeshibe.web.server :as server]
            [shibeshibe.models :as mo]))


(def system (atom nil))

(def default-db "datomic:free://localhost:4334/shibeshibe-3")

(defn bootstrap! [uri]
  (db/init-db!
   {:uri uri
    :extensions []
    :seed []
    :schemata [(db/gen-attribute-seq mo/all-models)]}))


(defn components [uri]
  {:db     (db/from-uri uri)
   :server (apply server/create-server nil)})

(defn gather-troops! []
  (alter-var-root system (constantly (components default-db))))