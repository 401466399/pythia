concrete FallbackGer of Fallback = 
  TenseGer,
  NounGer - [PPartNP],
  AdjectiveGer,
  NumeralGer,
  SymbolGer [
    PN, Symb, String, CN, Card, NP, MkSymb, SymbPN, CNNumNP
    ],
  ConjunctionGer,
  VerbGer -  [
    UseCopula,  
    PassV2
    ],
  AdverbGer,
  PhraseGer,
  SentenceGer,
  QuestionGer,
  RelativeGer,
  IdiomGer,
  ConstructionGer,
  DocumentationGer,

  ChunkGer,
  ExtensionsGer [
    CompoundN,AdAdV,UttAdV,ApposNP,MkVPI, MkVPS, PredVPS, PassVPSlash, PassAgentVPSlash, CompoundAP
    , PastPartAP, PastPartAgentAP, PresPartAP, GerundNP, GerundAdv
    , WithoutVP, InOrderToVP, ByVP
    ],

  DictionaryGer ** 
open MorphoGer, ResGer, ParadigmsGer, SyntaxGer, CommonScand, (E = ExtraGer), Prelude in {

flags
  literal=Symb ;

}
