(ns nomappsel.events
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [path
                                   reg-event-db
                                   reg-event-fx
                                   register-handler]]
            [ajax.core :as ajax]))

(register-handler
  :process-response
  (fn
    [db [_ response]]
    (js/alert "Login OK")
    (-> db
        (assoc :loading? false)
        (assoc :data (js->clj response)))))

(register-handler
  :bad-response
  (fn
    [db [_ response]]
    (js/alert "Login failed")
    (-> db
        (assoc :loading? false)
        (assoc :data (js->clj response)))))


(def initial-state
  {:username "furkan.bayraktar@nova.com"
   :password "pwd"})

(reg-event-db                 ;; setup initial state
  :initialize                     ;; usage:  (dispatch [:initialize])
  (fn
    [db _]
    (merge db initial-state)))    ;; what it returns becomes the new state

(reg-event-db
  :username                     ;; usage:  (dispatch [:username 34562])
  (path [:username])            ;; this is middleware
  (fn
    [username [_ value]]        ;; path middleware adjusts the first parameter
    value))

(reg-event-db
  :password                     ;; usage:  (dispatch [:username 34562])
  (path [:password])            ;; this is middleware
  (fn
    [password [_ value]]        ;; path middleware adjusts the first parameter
    value))

(reg-event-fx
  :login
  (fn [{db :db} _]
    {:http-xhrio {:method          :post
                  :format          (ajax/url-request-format)
                  :params {:email "furkan.bayraktar@nova.com"
                           :password "pwd"}
                  :uri             "http://localhost:6003/login"
                  :response-format :json
                  :on-success      [:process-response]
                  :on-failure      [:bad-response]}}))

