(ns learn-clojure.core
  (:gen-class))


; 1) Define a function greet that takes no arguments and prints "Hello". Replace the ___ with the implementation: (defn greet [] _)
(defn greet
  []
  (println "Hello"))


; 2) Redefine greet using def, first with the fn special form and then with the #() reader macro.
;; using fn
(def greet-fn
  (fn [] (println "Hello with fn")))


;; using #()
(def greet-anon
  #(println "anon hello"))


; 3) Define a function greeting which:
;    Given no arguments, returns "Hello, World!"
;    Given one argument x, returns "Hello, x!"
;    Given two arguments x and y, returns "x, y!"
(defn greet-args
  ([] "Hello, World!")
  ([x] (str "Hello, " x "!"))
  ([x y] (str x ", " y "!")))
; tests
(assert (= "Hello, World!" (greet-args)))
(assert (= "Hello, Clojure!" (greet-args "Clojure")))
(assert (= "Good morning, Clojure!" (greet-args "Good morning" "Clojure")))


; 4) Define a function do-nothing which takes a single argument x and returns it, unchanged.
(defn do-nothing
  [x] x)


; 5) Define a function always-thing which takes any number of arguments, ignores all of them, and returns the number 100.
(defn always-thing
  [& _] 100)


; 6) Define a function make-thingy which takes a single argument x. It should return another function, which takes any number of arguments and always returns x.
(defn make-thing
  [x] (fn [& _] x))

;; Tests
(let [n (rand-int Integer/MAX_VALUE)
      f (make-thing n)]
  (assert (= n (f)))
  (assert (= n (f 123)))
  (assert (= n (apply f 123 (range)))))


; 7) Define a function triplicate which takes another function and calls it three times, without any arguments.
(defn triplicate
  [f] (do (f)
          (f)
          (f)))


; 8) Define a function opposite which takes a single argument f. It should return another function which takes any number of arguments, applies f on them, and then calls not on the result. The not function in Clojure does logical negation.
(defn opposite
  [f] (fn [& args] (not (apply f args))))


; 9) Define a function triplicate2 which takes another function and any number of arguments, then calls that function three times on those arguments. Re-use the function you defined in the earlier triplicate exercise.
(defn triplicate2
  [f & args] (do (apply f args)
                 (apply f args)
                 (apply f args)))


; 10) Using the java.lang.Math class (Math/pow, Math/cos, Math/sin, Math/PI), demonstrate the following mathematical facts:
;     The cosine of pi is -1
;     For some x, sin(x)^2 + cos(x)^2 = 1
(assert (= -1.0 (Math/cos Math/PI)))

(defn foo
  [x] (+ (Math/pow (Math/sin x) 2) (Math/pow (Math/cos x) 2)))
(assert (= (foo 55) 1.0))


; 11) Define a function that takes an HTTP URL as a string, fetches that URL from the web, and returns the content as a string.
; Hint: Using the java.net.URL class and its openStream method. Then use the Clojure slurp function to get the content as a string.
(defn http-get
  [url]
  (slurp (java.net.URL. url)))

(assert (.contains (http-get "https://www.w3.org") "html"))

; In fact, the Clojure slurp function interprets its argument as a URL first before trying it as a file name. Write a simplified http-get:
(defn http-get
  [url]
  (slurp url))


; 12) Define a function one-less-arg that takes two arguments:
;     f, a function
;     x, a value
; and returns another function which calls f on x plus any additional arguments.
(defn one-less-arg [f x]
  (fn [& args] (f x args)))
