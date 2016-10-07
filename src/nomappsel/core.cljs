(ns nomappsel.core
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent.core :as reagent]
            [day8.re-frame.http-fx]
            [nomappsel.subs]
            [nomappsel.events]
            [nomappsel.views :as v]
            [re-frame.core :refer [dispatch-sync]]))

;; Entry point
(defn ^:export run
  []
  (dispatch-sync [:initialize])
  (reagent/render [v/login]
                  (js/document.getElementById "app")))
