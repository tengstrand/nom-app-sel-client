(ns nomappsel.dev
  (:require [nomappsel.core :as r]
            [figwheel.client :as fw]))

(fw/start {:on-jsload r/run
           :websocket-url "ws://localhost:3449/figwheel-ws"})
