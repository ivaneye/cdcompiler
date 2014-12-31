(ns cdcompiler.const-pool)

(def pool (atom {}))
(def idx (atom 1))


(defn pool-add
  [v]
  (reset! pool (assoc @pool @idx v))
  (swap! idx inc))

(defn pool-get
  [k]
  (get @pool k))
