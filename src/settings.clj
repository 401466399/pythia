(ns settings)


;; GLOBAL

(def domain :dbpedia)
(def language :en)

(def task-content "QuestionAnswering")
(def task-modality nil)


;; SETUP 

;; Note: All compiled grammar files need to be in gf-server-grammars. 
;; Running pythia in setup mode will automatically copy them there, under the name specified in target (or "Application", if target is nil).

(def target "DBpedia") 

(def verbose true) ; print messages to STDOUT when processing?


;; GF 

(def gf-server-grammars (str (java.lang.System/getProperty "user.home") "/.cabal/share/gf-3.6.10/www/grammars/"))
(def localhost "http://localhost:41296/grammars/")

(def startcat "Utterance_Utt")


;; NLU

(def semantic-definitions "src/core/definitions/")
(def domain-definitions 
  (domain 
  { :dbpedia "DBpedia.clj" }))

; Pre-compiled grammars
(def grammar 
  (domain 
  { :dbpedia "DBpedia.pgf" }))

; Reasoner 

(def ontology-path  
  (let [folder "resources/ontology/"]
    (settings/domain 
    { :dbpedia "dbpedia/DBpedia3.8.owl" })))

; NER 

(def ner-endpoint 
  (settings/language (settings/domain 
  { :dbpedia { :de "http://de.dbpedia.org/spotlight/rest/annotate"
               :en "http://spotlight.sztaki.hu:2222/rest/annotate" 
               :es "http://spotlight.sztaki.hu:2231/rest/annotate" }})))

(defn ner-options [input]
  (str "?text=" input "&spotter=Default"))


;; NLG


;; APPLICATIONS 

;; Question Answering 

; Endpoints

(def sparql-endpoint  
  (settings/domain 
  { :dbpedia "http://dbpedia.org/sparql/" }))

