(ns cdcompiler.version)

(defn version
  "打印jdk版本"
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        r3 (.read fis)
        r4 (.read fis)]
    (println (str "JDK Version is 1." (- r4 44)
                  " Major Version " r4 " Minor Version " r1)))
  fis)
