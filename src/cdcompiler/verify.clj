(ns cdcompiler.verify)

(defn verify
  "打印Java魔数，并验证是否为cafebabe.如果不是抛出异常"
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        r3 (.read fis)
        r4 (.read fis)
        s (.toUpperCase
           (str (Integer/toHexString r1)
                (Integer/toHexString r2)
                (Integer/toHexString r3)
                (Integer/toHexString r4)))]
    (if (= "CAFEBABE" s)
      (println s)
      (throw (Exception. "非法的Class文件"))))
  fis)
