/*     */ package jp.cssj.sakae.sac.parser;
/*     */ 
/*     */ import jp.cssj.sakae.sac.css.AttributeCondition;
/*     */ import jp.cssj.sakae.sac.css.CSSException;
/*     */ import jp.cssj.sakae.sac.css.CombinatorCondition;
/*     */ import jp.cssj.sakae.sac.css.Condition;
/*     */ import jp.cssj.sakae.sac.css.ConditionFactory;
/*     */ import jp.cssj.sakae.sac.css.ContentCondition;
/*     */ import jp.cssj.sakae.sac.css.LangCondition;
/*     */ import jp.cssj.sakae.sac.css.NegativeCondition;
/*     */ import jp.cssj.sakae.sac.css.PositionalCondition;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultConditionFactory
/*     */   implements ConditionFactory
/*     */ {
/*  77 */   public static final ConditionFactory INSTANCE = new DefaultConditionFactory();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CombinatorCondition createAndCondition(Condition first, Condition second) throws CSSException {
/*  90 */     return new DefaultAndCondition(first, second);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CombinatorCondition createOrCondition(Condition first, Condition second) throws CSSException {
/*  98 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NegativeCondition createNegativeCondition(Condition condition) throws CSSException {
/* 107 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PositionalCondition createPositionalCondition(int position, boolean typeNode, boolean type) throws CSSException {
/* 116 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeCondition createAttributeCondition(String localName, String namespaceURI, boolean specified, String value) throws CSSException {
/* 126 */     return new DefaultAttributeCondition(localName, namespaceURI, specified, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeCondition createIdCondition(String value) throws CSSException {
/* 134 */     return new DefaultIdCondition(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LangCondition createLangCondition(String lang) throws CSSException {
/* 142 */     return new DefaultLangCondition(lang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeCondition createOneOfAttributeCondition(String localName, String nsURI, boolean specified, String value) throws CSSException {
/* 152 */     return new DefaultOneOfAttributeCondition(localName, nsURI, specified, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeCondition createBeginHyphenAttributeCondition(String localName, String namespaceURI, boolean specified, String value) throws CSSException {
/* 162 */     return new DefaultBeginHyphenAttributeCondition(localName, namespaceURI, specified, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeCondition createClassCondition(String namespaceURI, String value) throws CSSException {
/* 171 */     return new DefaultClassCondition(namespaceURI, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeCondition createPseudoClassCondition(String namespaceURI, String value) throws CSSException {
/* 179 */     return new DefaultPseudoClassCondition(namespaceURI, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Condition createOnlyChildCondition() throws CSSException {
/* 187 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Condition createOnlyTypeCondition() throws CSSException {
/* 195 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContentCondition createContentCondition(String data) throws CSSException {
/* 203 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/DefaultConditionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */