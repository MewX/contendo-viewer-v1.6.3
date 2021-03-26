/*     */ package org.apache.batik.css.engine.sac;
/*     */ 
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ public class CSSAttributeCondition
/*     */   extends AbstractAttributeCondition
/*     */ {
/*     */   protected String localName;
/*     */   protected String namespaceURI;
/*     */   protected boolean specified;
/*     */   
/*     */   public CSSAttributeCondition(String localName, String namespaceURI, boolean specified, String value) {
/*  55 */     super(value);
/*  56 */     this.localName = localName;
/*  57 */     this.namespaceURI = namespaceURI;
/*  58 */     this.specified = specified;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  66 */     if (!super.equals(obj)) {
/*  67 */       return false;
/*     */     }
/*  69 */     CSSAttributeCondition c = (CSSAttributeCondition)obj;
/*  70 */     return (c.namespaceURI.equals(this.namespaceURI) && c.localName.equals(this.localName) && c.specified == this.specified);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  80 */     return this.namespaceURI.hashCode() ^ this.localName.hashCode() ^ (this.specified ? -1 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getConditionType() {
/*  90 */     return 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/*  98 */     return this.namespaceURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 106 */     return this.localName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getSpecified() {
/* 114 */     return this.specified;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean match(Element e, String pseudoE) {
/* 121 */     String val = getValue();
/* 122 */     if (val == null) {
/* 123 */       return !e.getAttribute(getLocalName()).equals("");
/*     */     }
/* 125 */     return e.getAttribute(getLocalName()).equals(val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillAttributeSet(Set<String> attrSet) {
/* 132 */     attrSet.add(this.localName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 139 */     if (this.value == null) {
/* 140 */       return '[' + this.localName + ']';
/*     */     }
/* 142 */     return '[' + this.localName + "=\"" + this.value + "\"]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/CSSAttributeCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */