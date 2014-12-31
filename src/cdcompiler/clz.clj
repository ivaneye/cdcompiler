(ns cdcompiler.clz)

(defn- clz
  [fis s]
  (let [r1 (.read fis)
        r2 (.read fis)
        idx (+ (* r1 100) r2)]
      (println s idx))
  fis)

(defn this-clz
  "打印当前class索引"
  [fis]
  (clz fis "当前类索引为:"))

(defn super-clz
  "打印父class索引"
  [fis]
  (clz fis "父类索引为:"))

(defn- interface-idx
  "打印接口相关索引"
  [fis]
  (clz fis "接口索引为:"))

(defn interface-num
  "打印接口数量及相关索引"
  [fis]
  (let [r1 (.read fis)
        r2 (.read fis)
        idx (+ (* r1 100) r2)]
      (println "接口数量:" idx)
      (if (> idx 0)
        (dotimes [x idx] (interface-idx fis)))
    )
  fis)

(defn clz-idx
  "打印类索引，父类索引，接口索引"
  [fis]
  (->> fis
       this-clz
       super-clz
       interface-num))
