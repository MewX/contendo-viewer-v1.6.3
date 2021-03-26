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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CSSIdCondition
/*     */   extends AbstractAttributeCondition
/*     */ {
/*     */   protected String namespaceURI;
/*     */   protected String localName;
/*     */   
/*     */   public CSSIdCondition(String ns, String ln, String value) {
/*  50 */     super(value);
/*  51 */     this.namespaceURI = ns;
/*  52 */     this.localName = ln;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getConditionType() {
/*  60 */     return 5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/*  68 */     return this.namespaceURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  76 */     return this.localName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getSpecified() {
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean match(Element e, String pseudoE) {
/*  91 */     return (e instanceof CSSStylableElement) ? ((CSSStylableElement)e).getXMLId().equals(getValue()) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillAttributeSet(Set<String> attrSet) {
/* 100 */     attrSet.add(this.localName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpecificity() {
/* 107 */     return 65536;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 114 */     return '#' + getValue();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/CSSIdCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */