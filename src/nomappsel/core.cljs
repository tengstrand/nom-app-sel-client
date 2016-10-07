(ns nomappsel.core
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent.core :as reagent]
            [nomappsel.views :as v]
            [re-frame.core :refer [dispatch-sync]]))

;; Entry point
(defn ^:export run
  []
  (dispatch-sync [:initialize])
  (reagent/render [v/login]
                  (js/document.getElementById "app")))
