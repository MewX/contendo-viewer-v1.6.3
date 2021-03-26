package jp.cssj.sakae.sac.css;

public interface ConditionFactory {
  CombinatorCondition createAndCondition(Condition paramCondition1, Condition paramCondition2) throws CSSException;
  
  CombinatorCondition createOrCondition(Condition paramCondition1, Condition paramCondition2) throws CSSException;
  
  NegativeCondition createNegativeCondition(Condition paramCondition) throws CSSException;
  
  PositionalCondition createPositionalCondition(int paramInt, boolean paramBoolean1, boolean paramBoolean2) throws CSSException;
  
  AttributeCondition createAttributeCondition(String paramString1, String paramString2, boolean paramBoolean, String paramString3) throws CSSException;
  
  AttributeCondition createIdCondition(String paramString) throws CSSException;
  
  LangCondition createLangCondition(String paramString) throws CSSException;
  
  AttributeCondition createOneOfAttributeCondition(String paramString1, String paramString2, boolean paramBoolean, String paramString3) throws CSSException;
  
  AttributeCondition createBeginHyphenAttributeCondition(String paramString1, String paramString2, boolean paramBoolean, String paramString3) throws CSSException;
  
  AttributeCondition createClassCondition(String paramString1, String paramString2) throws CSSException;
  
  AttributeCondition createPseudoClassCondition(String paramString1, String paramString2) throws CSSException;
  
  Condition createOnlyChildCondition() throws CSSException;
  
  Condition createOnlyTypeCondition() throws CSSException;
  
  ContentCondition createContentCondition(String paramString) throws CSSException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/css/ConditionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */