(ns cdcompiler.core
  (:import [java.io FileInputStream])
  (:require [cdcompiler.constant :refer :all]
            [cdcompiler.verify :refer [verify]]
            [cdcompiler.version :refer [version]]
            [cdcompiler.acc-flag :refer [acc-flag]]
            [cdcompiler.clz :refer :all]
            [cdcompiler.field :refer [field-info]]
            [cdcompiler.method :refer [method-info]]
            [cdcompiler.const-pool :refer :all])
  (:gen-class))


(defn -main
  [& args]
  (let [fis (FileInputStream. "Hello.class")]
    (->> fis
         verify
         version
         constant-pool
         acc-flag
         clz-idx
;;          field-info
;;          method-info
         )
    ))


(-main)

(reset! pool {})
(reset! idx 1)
(get @pool 26)
