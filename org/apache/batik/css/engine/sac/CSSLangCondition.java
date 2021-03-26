/*     */ package org.apache.batik.css.engine.sac;
/*     */ 
/*     */ import java.util.Set;
/*     */ import org.w3c.css.sac.LangCondition;
/*     */ import org.w3c.dom.Element;
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
/*     */ public class CSSLangCondition
/*     */   implements ExtendedCondition, LangCondition
/*     */ {
/*     */   protected String lang;
/*     */   protected String langHyphen;
/*     */   
/*     */   public CSSLangCondition(String lang) {
/*  53 */     this.lang = lang.toLowerCase();
/*  54 */     this.langHyphen = lang + '-';
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  62 */     if (obj == null || obj.getClass() != getClass()) {
/*  63 */       return false;
/*     */     }
/*  65 */     CSSLangCondition c = (CSSLangCondition)obj;
/*  66 */     return c.lang.equals(this.lang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getConditionType() {
/*  74 */     return 6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLang() {
/*  81 */     return this.lang;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpecificity() {
/*  88 */     return 256;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean match(Element e, String pseudoE) {
/*  95 */     String s = e.getAttribute("lang").toLowerCase();
/*  96 */     if (s.equals(this.lang) || s.startsWith(this.langHyphen)) {
/*  97 */       return true;
/*     */     }
/*  99 */     s = e.getAttributeNS("http://www.w3.org/XML/1998/namespace", "lang").toLowerCase();
/*     */     
/* 101 */     return (s.equals(this.lang) || s.startsWith(this.langHyphen));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillAttributeSet(Set<String> attrSet) {
/* 108 */     attrSet.add("lang");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 115 */     return ":lang(" + this.lang + ')';
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/CSSLangCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */