/*    */ package org.apache.batik.css.engine.value;
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
/*    */ 
/*    */ public class InheritValue
/*    */   extends AbstractValue
/*    */ {
/* 33 */   public static final InheritValue INSTANCE = new InheritValue();
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
/*    */   public String getCssText() {
/* 45 */     return "inherit";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getCssValueType() {
/* 52 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     return getCssText();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/InheritValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */