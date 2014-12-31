(ns cdcompiler.field)


(defn- field
  [fis s]
  (let [r1 (.read fis)
        r2 (.read fis)
        idx (+ (* r1 100) r2)]
    (println s idx))
  fis)

(defn acc-flag
  "打印访问权限控制符"
  [fis]
  (field fis "字段acc-flag为:"))

(defn name-idx
  "字段名称索引"
  [fis]
  (field fis "字段名称索引为:"))

(defn descriptor-idx
  "字段描述索引"
  [fis]
  (field fis "字段描述索引为:"))

(defn attr-num
  "字段属性数量及信息"
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        idx (+ (* r1 100) r2)]
    (println "字段属性数量:" idx)
;;     (if s(> idx 0)
;;       (dotimes [x idx] (->> fis
;;                             acc-flag
;;                             name-idx
;;                             descriptor-idx)))
    )
  fis)

(defn field-num
  "字段数量及信息"
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        idx (+ (* r1 100) r2)]
    (println "字段属性数量:" idx)
    (if (> idx 0)
      (dotimes [x idx] (->> fis
                            acc-flag
                            name-idx
                            descriptor-idx
                            attr-num)))
    )
  fis)

(defn field-info
  "打印字段表集合"
  [fis]
  (->> fis
       field-num))
