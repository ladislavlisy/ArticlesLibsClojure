(ns articles-libs.configure
 (:require [articles-libs.process-category :as process :refer :all]
  [articles-libs.config-article-code :as article-code :refer :all]
  [articles-libs.article :as article :refer :all]))

(def EMPTY_PENDING_NAMES ())

(defn pending-article-names1 [code1]
  (list code1))

(defn pending-article-names2 [code1 code2]
  (list code1 code2))

(defn configure-contract-term-articles []
  (def config-category process/CATEGORY_TERMS)
  (list 
    (article/->Article article-code/ARTICLE_CONTRACT_EMPL_TERM config-category 
      EMPTY_PENDING_NAMES)
    (article/->Article article-code/ARTICLE_CONTRACT_EMPL_TERM config-category 
      (pending-article-names1 article-code/ARTICLE_CONTRACT_EMPL_TERM))))

(defn configure-position-time-articles []
  (def config-category process/CATEGORY_TIMES)
  (list 
    (article/->Article article-code/ARTICLE_SCHEDULE_WORK config-category 
      (pending-article-names1 article-code/ARTICLE_POSITION_EMPL_TERM))
    (article/->Article article-code/ARTICLE_TIMESHEET_SCHEDULE config-category 
      (pending-article-names1 article-code/ARTICLE_SCHEDULE_WORK))
    (article/->Article article-code/ARTICLE_TIMESHEET_WORKING config-category 
      (pending-article-names2 
        article-code/ARTICLE_TIMESHEET_SCHEDULE
        article-code/ARTICLE_POSITION_EMPL_TERM))
    (article/->Article article-code/ARTICLE_TIMESHEET_ABSENCE config-category 
      (pending-article-names1 article-code/ARTICLE_TIMESHEET_WORKING))
    (article/->Article article-code/ARTICLE_TIMEHOURS_WORKING config-category 
      (pending-article-names1 article-code/ARTICLE_TIMESHEET_WORKING))
    (article/->Article article-code/ARTICLE_TIMEHOURS_ABSENCE config-category 
      (pending-article-names1 article-code/ARTICLE_TIMESHEET_ABSENCE))))

(defn configure-articles []
  (let [articles-lists 
        (list 
          (configure-contract-term-articles) 
          (configure-position-time-articles))]
    (flatten articles-lists)))
  