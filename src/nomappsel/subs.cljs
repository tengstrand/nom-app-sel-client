(ns nomappsel.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :username
  (fn
    [db _]
    (:username db)))

(reg-sub
  :password
  (fn
    [db _]
    (:password db)))

