(ns articles-libs.articles-test
  (:use midje.sweet)
  (:require [articles-libs.configure :as configure :refer :all]))

(facts "about `Articles`"
  (fact "it Should_Return_53_For_Articles_Length"
       (let [articles-all (configure/configure-articles)]
         (count articles-all) => 53)))

