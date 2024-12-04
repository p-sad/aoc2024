(require '[clojure.java.io :as io])
(require '[clojure.string :as str])

(defn file-to-lines [^String filename]
  (with-open [reader (io/reader filename)]
    (vec (line-seq reader))))

(defn line-to-vec [^String line]
  (str/split line #" +"))

(defn file-to-rows [^String filename]
  (map line-to-vec (file-to-lines filename)))

(defn to-columns [vecs]
  [
   (map first vecs)
   (map second vecs)
  ])

(defn to-rows [columns]
  (if (empty? (first columns))
      []
      (cons
        [(first (first columns)) (first (second columns))]
        (to-rows [(rest (first columns)) (rest (second columns))]))))

(defn sub-row [row]
  (abs
    (apply -
      (map Integer/parseInt row))))


(apply + (map sub-row (to-rows (map sort (to-columns (file-to-rows "input.txt"))))))
