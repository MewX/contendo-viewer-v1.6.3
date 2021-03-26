/*     */ package org.apache.batik.css.engine.sac;
/*     */ 
/*     */ import java.util.Set;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
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
/*     */ public class CSSPseudoClassCondition
/*     */   extends AbstractAttributeCondition
/*     */ {
/*     */   protected String namespaceURI;
/*     */   
/*     */   public CSSPseudoClassCondition(String namespaceURI, String value) {
/*  43 */     super(value);
/*  44 */     this.namespaceURI = namespaceURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  52 */     if (!super.equals(obj)) {
/*  53 */       return false;
/*     */     }
/*  55 */     CSSPseudoClassCondition c = (CSSPseudoClassCondition)obj;
/*  56 */     return c.namespaceURI.equals(this.namespaceURI);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  64 */     return this.namespaceURI.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getConditionType() {
/*  73 */     return 10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/*  81 */     return this.namespaceURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  89 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getSpecified() {
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean match(Element e, String pseudoE) {
/* 104 */     return (e instanceof CSSStylableElement) ? ((CSSStylableElement)e).isPseudoInstanceOf(getValue()) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillAttributeSet(Set attrSet) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 119 */     return ":" + getValue();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/CSSPseudoClassCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */