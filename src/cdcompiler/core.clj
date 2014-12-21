(ns cdcompiler.core
  (:import [java.io FileInputStream])
  (:gen-class))

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

(defmulti constant
  (fn [fis] (.read fis)))

(defmethod constant
   10
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        method-ref (+ (* 100 r1) r2)
        r3 (.read fis)
        r4 (.read fis)
        type-ref (+ (* 100 r3) r4)]
    (println "Methodref_info")
    (println "方法索引地址:" method-ref)
    (println "类型索引地址:" type-ref)))

(defmethod constant
   9
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        method-ref (+ (* 100 r1) r2)
        r3 (.read fis)
        r4 (.read fis)
        type-ref (+ (* 100 r3) r4)]
    (println "Fieldref_info")
    (println "字段所在类索引地址:" method-ref)
    (println "字段描述索引地址:" type-ref)))

(defmethod constant
   8
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        method-ref (+ (* 100 r1) r2) ]
    (println "Stringref_info")
    (println "字符串索引地址:" method-ref)))

(defn constant-pool
  "打印常量池"
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        constant-num (+ (* 100 r1) r2)]
    (dotimes [n 3] (constant fis))))

(defn -main
  [& args]
  (let [fis (FileInputStream. "Hello.class")]
    (->> fis
         verify
         version
         constant-pool)
    ))
