/*     */ package org.apache.batik.css.engine.sac;
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
/*     */ public class CSSConditionFactory
/*     */   implements ConditionFactory
/*     */ {
/*     */   protected String classNamespaceURI;
/*     */   protected String classLocalName;
/*     */   protected String idNamespaceURI;
/*     */   protected String idLocalName;
/*     */   
/*     */   public CSSConditionFactory(String cns, String cln, String idns, String idln) {
/*  66 */     this.classNamespaceURI = cns;
/*  67 */     this.classLocalName = cln;
/*  68 */     this.idNamespaceURI = idns;
/*  69 */     this.idLocalName = idln;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CombinatorCondition createAndCondition(Condition first, Condition second) throws CSSException {
/*  79 */     return new CSSAndCondition(first, second);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CombinatorCondition createOrCondition(Condition first, Condition second) throws CSSException {
/*  89 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NegativeCondition createNegativeCondition(Condition condition) throws CSSException {
/*  98 */     throw new CSSException("Not implemented in CSS2");
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
/* 109 */     throw new CSSException("Not implemented in CSS2");
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
/* 121 */     return new CSSAttributeCondition(localName, namespaceURI, specified, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeCondition createIdCondition(String value) throws CSSException {
/* 131 */     return new CSSIdCondition(this.idNamespaceURI, this.idLocalName, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LangCondition createLangCondition(String lang) throws CSSException {
/* 139 */     return new CSSLangCondition(lang);
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
/* 151 */     return new CSSOneOfAttributeCondition(localName, nsURI, specified, value);
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
/* 165 */     return new CSSBeginHyphenAttributeCondition(localName, namespaceURI, specified, value);
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
/* 176 */     return new CSSClassCondition(this.classLocalName, this.classNamespaceURI, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeCondition createPseudoClassCondition(String namespaceURI, String value) throws CSSException {
/* 186 */     return new CSSPseudoClassCondition(namespaceURI, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Condition createOnlyChildCondition() throws CSSException {
/* 194 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Condition createOnlyTypeCondition() throws CSSException {
/* 202 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContentCondition createContentCondition(String data) throws CSSException {
/* 211 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/CSSConditionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */