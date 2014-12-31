(ns cdcompiler.method)


(defn- method
  [fis s]
  (let [r1 (.read fis)
        r2 (.read fis)
        idx (+ (* r1 100) r2)]
    (println s idx))
  fis)

(defn acc-flag
  "打印访问权限控制符"
  [fis]
  (method fis "方法acc-flag为:"))

(defn name-idx
  "方法名称索引"
  [fis]
  (method fis "方法名称索引为:"))

(defn descriptor-idx
  "方法描述索引"
  [fis]
  (method fis "方法描述索引为:"))

(defn attr-name-idx
  "属性名称索引"
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        idx (+ (* r1 100) r2)]
    (println "属性名称索引:" idx)
    )
  fis)

(defn attr-length
  "属性长度"
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        r3 (.read fis)
        r4 (.read fis)
        idx (+ (* r1 1000000) (* r2 10000) (* r3 100) r4)
        r5 (.read fis)
        r6 (.read fis)
        i (+ (* r5 100) r6)
        r7 (.read fis)
        r8 (.read fis)
        a (+ (* r7 100) r8)
        r9 (.read fis)
        r10 (.read fis)
        b (+ (* r9 100) r10)]
    (println "属性长度:" idx)
    (println "length:" i)
    (println "linenum:" a " " b)
;;     (if (> idx 0)
;;       (dotimes [x idx] (->> fis
;;                             attr-name-idx)))
    )
fis)

(defn attr-num
  "方法属性数量及信息"
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        idx (+ (* r1 100) r2)]
    (println "方法属性数量:" idx)
    (if (> idx 0)
      (dotimes [x 1] (->> fis
                            attr-name-idx
                            attr-length)))
    )
  fis)

(defn method-num
  "方法数量及信息"
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        idx (+ (* r1 100) r2)]
    (println "方法数量:" idx)
    (if (> idx 0)
      (dotimes [x 1] (->> fis
                            acc-flag
                            name-idx
                            descriptor-idx
                            attr-num)))
    )
  fis)

(defn method-info
  "打印方法表集合"
  [fis]
  (->> fis
       method-num))
