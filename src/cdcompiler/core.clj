(ns cdcompiler.core
  (:import [java.io FileInputStream])
  (:require [cdcompiler.constant :refer :all]
            [cdcompiler.verify :refer [verify]]
            [cdcompiler.version :refer [version]]
            [cdcompiler.acc-flag :refer [acc-flag]]
            [cdcompiler.clz :refer :all])
  (:gen-class))


(defn -main
  [& args]
  (let [fis (FileInputStream. "Hello.class")]
    (->> fis
         verify
         version
         constant-pool
         acc-flag
         clz-idx)
    ))


(-main)
