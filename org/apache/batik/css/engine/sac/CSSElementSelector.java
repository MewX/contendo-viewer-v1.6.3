/*    */ package org.apache.batik.css.engine.sac;
/*    */ 
/*    */ import org.w3c.dom.Element;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CSSElementSelector
/*    */   extends AbstractElementSelector
/*    */ {
/*    */   public CSSElementSelector(String uri, String name) {
/* 35 */     super(uri, name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getSelectorType() {
/* 43 */     return 4;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean match(Element e, String pseudoE) {
/* 50 */     String eName, name = getLocalName();
/* 51 */     if (name == null) {
/* 52 */       return true;
/*    */     }
/*    */     
/* 55 */     if (e.getPrefix() == null) { eName = e.getNodeName(); }
/* 56 */     else { eName = e.getLocalName(); }
/*    */ 
/*    */     
/* 59 */     return eName.equals(name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSpecificity() {
/* 68 */     return (getLocalName() == null) ? 0 : 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     String name = getLocalName();
/* 76 */     if (name == null) {
/* 77 */       return "*";
/*    */     }
/* 79 */     return name;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/CSSElementSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */