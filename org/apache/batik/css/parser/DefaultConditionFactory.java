/*     */ package org.apache.batik.css.parser;
/*     */ 
/*     */ import org.w3c.css.sac.AttributeCondition;
/*     */ import org.w3c.css.sac.CSSException;
/*     */ import org.w3c.css.sac.CombinatorCondition;
/*     */ import org.w3c.css.sac.Condition;
/*     */ import org.w3c.css.sac.ConditionFactory;
/*     */ import org.w3c.css.sac.ContentCondition;
/*     */ import org.w3c.css.sac.LangCondition;
/*     */ import org.w3c.css.sac.NegativeCondition;
/*     */ import org.w3c.css.sac.PositionalCondition;
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
/*  45 */   public static final ConditionFactory INSTANCE = new DefaultConditionFactory();
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
/*     */   public CombinatorCondition createAndCondition(Condition first, Condition second) throws CSSException {
/*  61 */     return new DefaultAndCondition(first, second);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CombinatorCondition createOrCondition(Condition first, Condition second) throws CSSException {
/*  71 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NegativeCondition createNegativeCondition(Condition condition) throws CSSException {
/*  80 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PositionalCondition createPositionalCondition(int position, boolean typeNode, boolean type) throws CSSException {
/*  91 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeCondition createAttributeCondition(String localName, String namespaceURI, boolean specified, String value) throws CSSException {
/* 103 */     return new DefaultAttributeCondition(localName, namespaceURI, specified, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeCondition createIdCondition(String value) throws CSSException {
/* 113 */     return new DefaultIdCondition(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LangCondition createLangCondition(String lang) throws CSSException {
/* 121 */     return new DefaultLangCondition(lang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeCondition createOneOfAttributeCondition(String localName, String nsURI, boolean specified, String value) throws CSSException {
/* 133 */     return new DefaultOneOfAttributeCondition(localName, nsURI, specified, value);
/*     */   }
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
/*     */   public AttributeCondition createBeginHyphenAttributeCondition(String localName, String namespaceURI, boolean specified, String value) throws CSSException {
/* 147 */     return new DefaultBeginHyphenAttributeCondition(localName, namespaceURI, specified, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeCondition createClassCondition(String namespaceURI, String value) throws CSSException {
/* 158 */     return new DefaultClassCondition(namespaceURI, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeCondition createPseudoClassCondition(String namespaceURI, String value) throws CSSException {
/* 168 */     return new DefaultPseudoClassCondition(namespaceURI, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Condition createOnlyChildCondition() throws CSSException {
/* 176 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Condition createOnlyTypeCondition() throws CSSException {
/* 184 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContentCondition createContentCondition(String data) throws CSSException {
/* 193 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/DefaultConditionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */