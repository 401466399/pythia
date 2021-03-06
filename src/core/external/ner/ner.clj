(ns core.external.ner.ner
  (:require [core.external.ner.spotlight :as spotlight]
            [core.external.ner.fox       :as fox]
            [core.nlu.context.short_term_memory :as stm]
            [core.nlu.context.long_term_memory  :as ltm]))


(declare replace-entities)


;; Main 

(defn recognize [input]
  (let [;spotlight (spotlight/filter-entities (spotlight/get-entities input))
        fox       (fox/filter-entities (fox/get-entities input))
        entities  fox ; (concat spotlight fox) with filtering duplicates (based on :form)
        new-input (replace-entities input entities 1 0)]
    (if (empty? entities)
        input
      (do 
        ; print entities and new-input
        (println "\nEntities:\n")
        (doseq [e entities] (println (str e)))
        (println (str "\nAdapted input: " new-input))
        ; return new-input
        new-input))))


;; Aux

(defn replace-entities [input entities i d] 
  ; d is the offset deviation 
  (if (empty? entities)
      input
      (let [entity     (first entities)
            form       (:form entity)
            identifier (str "Entity" i)
            offset     (println (:offset entity) (- (read-string (:offset entity)) d)) ; DEBUG
            new-input  (str (subs input 0 offset) (clojure.string/replace-first (subs input offset) form identifier))
            new-d      (+ d (- (count form) 1))]
        (if-not (some #{form} (ltm/tokens))
          (do
          ; add entity to short term memory
          (swap! stm/named-entities assoc (keyword identifier) entity)
          ; recur
          (replace-entities new-input (rest entities) (+ i 1) new-d))))))


;; Semantic definition

(defn resolve-entity [identifier] 
  (:uri (get @stm/named-entities identifier)))
