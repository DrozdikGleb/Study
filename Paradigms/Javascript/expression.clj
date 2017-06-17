(defn constant [value]
  (fn [args] value))
(defn variable [v]
  (fn [args] (args v)))
(defn operation [f]
  (fn [& ops]
    (fn [args]
      (apply f (map (fn [fun] (fun args)) ops)))))

(def div (fn [a b] (/ (double a) (double b))))
(def sinCalc (fn [arg] (Math/sin arg)))
(def cosCalc (fn [arg] (Math/cos arg)))

(def add (operation +))
(def subtract (operation -))
(def multiply (operation *))
(def divide (operation div))
(def negate (operation -))
(def sin (operation sinCalc))
(def cos (operation cosCalc))

(def op {'+ add, '- subtract, '* multiply, '/ divide, 'negate negate, 'sin sin, 'cos cos})

(defn parse [m v c]
  (fn [expr]
    ((fn parseList [l]
       (cond
         (symbol? l) (v (str l))
         (number? l) (c l)
         (list? l) (apply (m (first l)) (map parseList (rest l)))))
      (read-string expr))))