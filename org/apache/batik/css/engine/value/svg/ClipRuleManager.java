/*    */ package org.apache.batik.css.engine.value.svg;
/*    */ 
/*    */ import org.apache.batik.css.engine.value.IdentifierManager;
/*    */ import org.apache.batik.css.engine.value.StringMap;
/*    */ import org.apache.batik.css.engine.value.Value;
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
/*    */ 
/*    */ 
/*    */ public class ClipRuleManager
/*    */   extends IdentifierManager
/*    */ {
/* 39 */   protected static final StringMap values = new StringMap();
/*    */   static {
/* 41 */     values.put("evenodd", SVGValueConstants.EVENODD_VALUE);
/*    */     
/* 43 */     values.put("nonzero", SVGValueConstants.NONZERO_VALUE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isInheritedProperty() {
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimatableProperty() {
/* 59 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAdditiveProperty() {
/* 66 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPropertyType() {
/* 73 */     return 15;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPropertyName() {
/* 81 */     return "clip-rule";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Value getDefaultValue() {
/* 89 */     return SVGValueConstants.NONZERO_VALUE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StringMap getIdentifiers() {
/* 96 */     return values;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/ClipRuleManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */