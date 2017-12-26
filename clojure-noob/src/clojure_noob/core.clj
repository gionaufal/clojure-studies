(ns clojure-noob.core
  (:gen-class))

(defn -main
 "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a litte teapot"))
(println "Cleanliness is next to godliness")
(defn train
  []
  (println "Choo choo!"))
(train)

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])
(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
  :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

(symmetrize-body-parts asym-hobbit-body-parts)

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

(better-symmetrize-body-parts asym-hobbit-body-parts)

;; Exercise 3.2
(defn addhundred
  [number]
  (+ number 100))

;; Exercise 3.3
(defn dec-maker
  "Creates a custom decremantor"
  [dec-by]
  #(- % dec-by))

(def dec3 (dec-maker 3))

(dec3 7)

;; Exercise 3.4
(defn mapset
  "Receives a funcition and a vector and returns a set"
  [fun col]
  (set (for [x col]
     (fun x))))

(mapset inc [1 1 2 2])

;; Exercise 3.5
(def asym-alien-parts [{:name "0-eye" :size 1}
                       {:name "0-ear" :size 1}
                       {:name "mouth" :size 1}
                       {:name "nose" :size 1}
                       ])


(defn matching-alien-part
  [part number]
  {:name (clojure.string/replace (:name part) #"^0" (str number))
   :size (:size part)})

(defn symmetrize-alien-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part
                                         (matching-alien-part part 1)
                                         (matching-alien-part part 2)
                                         (matching-alien-part part 3)
                                         (matching-alien-part part 4)
                                         ])))
          []
          asym-body-parts))

;; Exercise 3.6
(def asym-something-parts [{:name "1-eye" :size 1}
                           {:name "1-ear" :size 1}
                           {:name "mouth" :size 1}
                           {:name "nose" :size 1}
                           ])

(defn generalized-matching-parts
  [part n]
  (loop [current n
         total-matching-parts [part]]
    (if (< current 1)
      total-matching-parts
      (recur (dec current)
             (set (into total-matching-parts
                        [{:name (clojure.string/replace (:name part) #"^1" (str current))
                         :size (:size part)}]))))))


(defn symmetrize-generalized-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts number]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set (generalized-matching-parts part number))))
          []
          asym-body-parts))
