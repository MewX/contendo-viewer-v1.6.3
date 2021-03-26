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
/*    */ public class ColorRenderingManager
/*    */   extends IdentifierManager
/*    */ {
/* 39 */   protected static final StringMap values = new StringMap();
/*    */   static {
/* 41 */     values.put("auto", SVGValueConstants.AUTO_VALUE);
/*    */     
/* 43 */     values.put("optimizequality", SVGValueConstants.OPTIMIZEQUALITY_VALUE);
/*    */     
/* 45 */     values.put("optimizespeed", SVGValueConstants.OPTIMIZESPEED_VALUE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isInheritedProperty() {
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimatableProperty() {
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAdditiveProperty() {
/* 68 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPropertyType() {
/* 75 */     return 15;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPropertyName() {
/* 83 */     return "color-rendering";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Value getDefaultValue() {
/* 91 */     return SVGValueConstants.AUTO_VALUE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StringMap getIdentifiers() {
/* 98 */     return values;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/ColorRenderingManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */