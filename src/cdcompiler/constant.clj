(ns cdcompiler.constant)

(defmulti constant
  (fn [fis] (.read fis)))

(defmethod constant
   12
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        const-ref (+ (* 100 r1) r2)
        r3 (.read fis)
        r4 (.read fis)
        name-ref (+ (* 100 r3) r4)]
    (println "NameAndType_info")
    (println "字段或方法名称常量索引地址:" const-ref)
    (println "字段或方法描述符常量索引地址:" name-ref)))

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
    (println "String_info")
    (println "字符串索引地址:" method-ref)))

(defmethod constant
   7
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        method-ref (+ (* 100 r1) r2) ]
    (println "Class_info")
    (println "类型索引地址:" method-ref)))

(defmethod constant
   1
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        len (+ (* 100 r1) r2)
        arr (byte-array len)
        r (.read fis arr)]
    (println "Utf8_info")
    (println "Utf8字符串长度:"  len)
    (println "Utf8字符串为:" (String. arr))))

(defn constant-pool
  "打印常量池"
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        constant-num (+ (* 100 r1) r2)]
    (println "常量池开始！常量数量:" constant-num)
    (dotimes [n (- constant-num 1)] (constant fis))
    (println "常量池结束!"))
  fis)

