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

(def columns (to-columns (file-to-rows "input.txt")))
(def occurences (atom {}))

(doseq [right (second columns)]
  (swap! occurences assoc right (inc (get @occurences right 0))))

(defn multiply-by-occurences [^String num]
  (* (Integer. num) (get @occurences num 0)))

(apply +
  (map multiply-by-occurences (first columns)))
