(ns nomappsel.views
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [dispatch
                                   subscribe]]))

(defn login-button []
  [:button#login {:on-click #(dispatch [:login])} "Login"])

(defn create-text-input [title attribute]
  (fn []
    (let [value (subscribe [attribute])]
      (fn []
        [:div.color-input
         (str title ": ")
         [:input {:type "text"
                  :value (deref value)
                  :on-change #(dispatch
                               [attribute (-> % .-target .-value)])}]]))))

(defn username-output []
  (let [username (subscribe [:username])
        password (subscribe [:password])]
    (fn []
      [:h1 @username " / " @password])))

(defn login
  []
  [:div
   [create-text-input "Username" :username]
   [create-text-input "Password" :password]
   [login-button]
   [username-output]])
