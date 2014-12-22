(ns cdcompiler.acc-flag)

(defn acc-flag
  "打印Java访问标志"
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        s (.toUpperCase
           (str (Integer/toHexString r1)
                (Integer/toHexString r2)))]
      (println "当前访问标识符为:" s))
  fis)
