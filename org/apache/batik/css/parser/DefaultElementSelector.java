/*    */ package org.apache.batik.css.parser;
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
/*    */ public class DefaultElementSelector
/*    */   extends AbstractElementSelector
/*    */ {
/*    */   public DefaultElementSelector(String uri, String name) {
/* 32 */     super(uri, name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getSelectorType() {
/* 40 */     return 4;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     String name = getLocalName();
/* 48 */     if (name == null) {
/* 49 */       return "*";
/*    */     }
/* 51 */     return name;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/DefaultElementSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */