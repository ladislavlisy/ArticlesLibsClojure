(ns articles-libs.configure
 (:require [articles-libs.process-category :as process :refer :all]
  [articles-libs.config-article-code :as article-code :refer :all]
  [articles-libs.article :as article :refer :all]))

(def EMPTY_PENDING_NAMES ())

(defn pending-article-names1 [code1]
  (list code1))

(defn pending-article-names2 [code1 code2]
  (list code1 code2))

(defn pending_article_names3 [code1 code2 code3]
    (list code1 code2 code3))

(defn pending_article_names4 [code1 code2 code3 code4]
    (list code1 code2 code3 code4))
 
(defn pending_article_names5 [code1 code2 code3 code4 code5]
    (list code1 code2 code3 code4 code5))

(defn pending_article_names9 [code1 code2 code3 code4 code5 code6 code7 code8 code9]
    (list code1 code2 code3 code4 code5 code6 code7 code8 code9))

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

(defn configure-gross-income-articles []
  (def config-category process/CATEGORY_AMOUNT)
  (list
    (article/->Article article-code/ARTICLE_SALARY_BASE config-category
      (pending-article-names2 article-code/ARTICLE_TIMEHOURS_WORKING
        article-code/ARTICLE_TIMEHOURS_ABSENCE))))
                

(defn configure-total-income-articles []
  (def config-category process/CATEGORY_FINAL)
  (list
    (article/->Article article-code/ARTICLE_INCOME_GROSS config-category
      EMPTY_PENDING_NAMES)
    (article/->Article article-code/ARTICLE_INCOME_NETTO config-category
      (pending-article-names9 
        article-code/ARTICLE_INCOME_GROSS
        article-code/ARTICLE_TAXING_ADVANCES_TOTAL
        article-code/ARTICLE_TAXING_BONUS_CHILD
        article-code/ARTICLE_TAXING_WITHHOLD_GENERAL
        article-code/ARTICLE_HEALTH_EMPLOYEE_GENERAL
        article-code/ARTICLE_HEALTH_EMPLOYEE_MANDATORY
        article-code/ARTICLE_SOCIAL_EMPLOYEE_GENERAL
        article-code/ARTICLE_SOCIAL_EMPLOYEE_PENSION
        article-code/ARTICLE_GARANT_EMPLOYEE_PENSION))))
 
(defn configure-netto-deducts-articles []
  (def config-category process/CATEGORY_NETTO)
  (list
    (article/->Article article-code/ARTICLE_TAXING_ADVANCES_TOTAL config-category
      (pending-article-names2 
        article-code/ARTICLE_TAXING_ADVANCES_GENERAL
        article-code/ARTICLE_TAXING_ADVANCES_SOLIDARY)
      (article/->Article article-code/ARTICLE_TAXING_ADVANCES_GENERAL config-category
        (pending-article-names1
          article-code/ARTICLE_TAXING_ADVANCES_BASIS_GENERAL))
      (article/->Article article-code/ARTICLE_TAXING_ADVANCES_SOLIDARY config-category
        (pending-article-names1
          article-code/ARTICLE_TAXING_ADVANCES_BASIS_SOLIDARY))
      (article/->Article article-code/ARTICLE_TAXING_WITHHOLD_GENERAL config-category
        (pending-article-names1 
          article-code/ARTICLE_TAXING_WITHHOLD_BASIS_GENERAL))
      (article/->Article article-code/ARTICLE_HEALTH_EMPLOYEE_GENERAL config-category
        (pending-article-names1
          article-code/ARTICLE_HEALTH_BASIS_GENERAL))
      (article/->Article article-code/ARTICLE_HEALTH_EMPLOYEE_MANDATORY config-category
        (pending-article-names1 
          article-code/ARTICLE_HEALTH_BASIS_MANDATORY))
      (article/->Article article-code/ARTICLE_SOCIAL_EMPLOYEE_GENERAL config-category
        (pending-article-names1 
          article-code/ARTICLE_SOCIAL_BASIS_GENERAL))
      (article/->Article article-code/ARTICLE_SOCIAL_EMPLOYEE_PENSION config-category
        (pending-article-names1
          article-code/ARTICLE_SOCIAL_BASIS_PENSION))
      (article/->Article article-code/ARTICLE_GARANT_EMPLOYEE_PENSION config-category
        (pending-article-names1 
          article-code/ARTICLE_GARANT_BASIS_PENSION)))))

(defn configure-basis-health-articles []
  (def config-category process/CATEGORY_NETTO)
  (list
    (article/->Article article-code/ARTICLE_HEALTH_INCOME_SUBJECT config-category EMPTY_PENDING_NAMES)
    (article/->Article article-code/ARTICLE_HEALTH_INCOME_PARTICIP config-category
      (pending-article-names1
        article-code/ARTICLE_HEALTH_INCOME_SUBJECT))
    (article/->Article article-code/ARTICLE_HEALTH_BASIS_GENERAL config-category
      (pending-article-names1
        article-code/ARTICLE_HEALTH_INCOME_PARTICIP))
    (article/->Article article-code/ARTICLE_HEALTH_BASIS_MANDATORY config-category
      (pending-article-names2
        article-code/ARTICLE_HEALTH_BASIS_GENERAL
        article-code/ARTICLE_HEALTH_INCOME_PARTICIP))
    (article/->Article article-code/ARTICLE_HEALTH_BASIS_LEGALCAP config-category
      (pending-article-names2
        article-code/ARTICLE_HEALTH_BASIS_GENERAL
        article-code/ARTICLE_HEALTH_INCOME_PARTICIP))))

(defn configure-basis-social-articles []
  (def config-category process/CATEGORY_NETTO)
  (list
     (article/->Article article-code/ARTICLE_SOCIAL_INCOME_SUBJECT config-category
       EMPTY_PENDING_NAMES)
     (article/->Article article-code/ARTICLE_SOCIAL_INCOME_PARTICIP config-category
       (pending-article-names1
          article-code/ARTICLE_SOCIAL_INCOME_SUBJECT))
     (article/->Article article-code/ARTICLE_SOCIAL_BASIS_GENERAL config-category
       (pending-article-names1
          article-code/ARTICLE_SOCIAL_INCOME_PARTICIP))
     (article/->Article article-code/ARTICLE_SOCIAL_BASIS_PENSION config-category
       (pending-article-names1
          article-code/ARTICLE_SOCIAL_INCOME_PARTICIP))
     (article/->Article article-code/ARTICLE_SOCIAL_BASIS_LEGALCAP config-category
       (pending-article-names1
          article-code/ARTICLE_SOCIAL_INCOME_PARTICIP))))
                

(defn configure-basis-garant-articles []
  (def config-category process/CATEGORY_NETTO)
  (list
    (article/->Article article-code/ARTICLE_GARANT_INCOME_SUBJECT config-category
       EMPTY_PENDING_NAMES)
    (article/->Article article-code/ARTICLE_GARANT_INCOME_PARTICIP config-category
       (pending-article-names1
          article-code/ARTICLE_GARANT_INCOME_SUBJECT))
    (article/->Article article-code/ARTICLE_GARANT_BASIS_PENSION config-category
       (pending-article-names1
          article-code/ARTICLE_GARANT_INCOME_PARTICIP))
    (article/->Article article-code/ARTICLE_GARANT_BASIS_LEGALCAP config-category
       (pending-article-names1
          article-code/ARTICLE_GARANT_INCOME_PARTICIP))))
                

(defn configure-basis-taxing-articles []
  (def config-category process/CATEGORY_NETTO)
  (list
    (article/->Article article-code/ARTICLE_TAXING_INCOME_SUBJECT config-category
        EMPTY_PENDING_NAMES)
    (article/->Article article-code/ARTICLE_TAXING_INCOME_HEALTH config-category
        EMPTY_PENDING_NAMES)
    (article/->Article article-code/ARTICLE_TAXING_INCOME_SOCIAL config-category
        EMPTY_PENDING_NAMES)))

(defn configure-basis-advances-articles []
  (def config-category process/CATEGORY_NETTO)
  (list
    (article/->Article article-code/ARTICLE_TAXING_ADVANCES_INCOME config-category
      (pending-article-names1
        article-code/ARTICLE_TAXING_INCOME_SUBJECT))
    (article/->Article article-code/ARTICLE_TAXING_ADVANCES_HEALTH config-category
      (pending-article-names1
        article-code/ARTICLE_TAXING_INCOME_HEALTH))
    (article/->Article article-code/ARTICLE_TAXING_ADVANCES_SOCIAL config-category
      (pending-article-names1
        article-code/ARTICLE_TAXING_INCOME_SOCIAL))
    (article/->Article article-code/ARTICLE_TAXING_ADVANCES_BASIS_GENERAL config-category
      (pending-article-names3
        article-code/ARTICLE_TAXING_ADVANCES_INCOME
        article-code/ARTICLE_TAXING_ADVANCES_HEALTH
        article-code/ARTICLE_TAXING_ADVANCES_SOCIAL))
    (article/->Article article-code/ARTICLE_TAXING_ADVANCES_BASIS_SOLIDARY config-category
      (pending-article-names1
        article-code/ARTICLE_TAXING_ADVANCES_BASIS_GENERAL))))
        

(defn configure-basis-withhold-articles []
  (def config-category process/CATEGORY_NETTO)
  (list
    (article/->Article article-code/ARTICLE_TAXING_WITHHOLD_INCOME config-category
        (pending-article-names1
            article-code/ARTICLE_TAXING_INCOME_SUBJECT))
    (article/->Article article-code/ARTICLE_TAXING_WITHHOLD_HEALTH config-category
        (pending-article-names1
            article-code/ARTICLE_TAXING_INCOME_HEALTH))
    (article/->Article article-code/ARTICLE_TAXING_WITHHOLD_SOCIAL config-category
        (pending-article-names1
            article-code/ARTICLE_TAXING_INCOME_SOCIAL))
    (article/->Article article-code/ARTICLE_TAXING_WITHHOLD_BASIS_GENERAL config-category
        (pending-article-names3
            article-code/ARTICLE_TAXING_WITHHOLD_INCOME
            article-code/ARTICLE_TAXING_WITHHOLD_HEALTH
            article-code/ARTICLE_TAXING_WITHHOLD_SOCIAL))))
          
    
(defn configure-allowance-taxis-articles []
  (def config-category process/CATEGORY_NETTO)
  (list
    (article/->Article article-code/ARTICLE_TAXING_ALLOWANCE_PAYER config-category
        (pending-article-names1
            article-code/ARTICLE_TAXING_ADVANCES_INCOME))
    (article/->Article article-code/ARTICLE_TAXING_ALLOWANCE_DISABILITY config-category
        (pending-article-names1
            article-code/ARTICLE_TAXING_ADVANCES_INCOME))
    (article/->Article article-code/ARTICLE_TAXING_ALLOWANCE_STUDYING config-category
        (pending-article-names1
            article-code/ARTICLE_TAXING_ADVANCES_INCOME))
    (article/->Article article-code/ARTICLE_TAXING_ALLOWANCE_CHILD config-category
        (pending-article-names1
            article-code/ARTICLE_TAXING_ADVANCES_INCOME))))

(defn configure-rebate-taxis-articles []
  (def config-category process/CATEGORY_NETTO)
  (list
    (article/->Article article-code/ARTICLE_TAXING_REBATE_PAYER config-category
      (pending-article-names4()
          article-code/ARTICLE_TAXING_ALLOWANCE_PAYER
          article-code/ARTICLE_TAXING_ADVANCES_TOTAL
          article-code/ARTICLE_TAXING_ALLOWANCE_DISABILITY
          article-code/ARTICLE_TAXING_ALLOWANCE_STUDYING))
    (article/->Article article-code/ARTICLE_TAXING_REBATE_CHILD config-category
      (pending-article-names3
          article-code/ARTICLE_TAXING_ALLOWANCE_CHILD
          article-code/ARTICLE_TAXING_ADVANCES_TOTAL
          article-code/ARTICLE_TAXING_REBATE_PAYER))
    (article/->Article article-code/ARTICLE_TAXING_BONUS_CHILD config-category
      (pending-article-names3
          article-code/ARTICLE_TAXING_ADVANCES_TOTAL
          article-code/ARTICLE_TAXING_REBATE_PAYER
          article-code/ARTICLE_TAXING_REBATE_CHILD))))

(defn configure-articles []
  (let [articles-lists 
        (list 
          (configure-contract-term-articles) 
          (configure-position-time-articles))]
    (flatten articles-lists)))
  