/*    */ package org.apache.batik.css.engine.value.css2;
/*    */ 
/*    */ import org.apache.batik.css.engine.value.IdentifierManager;
/*    */ import org.apache.batik.css.engine.value.StringMap;
/*    */ import org.apache.batik.css.engine.value.Value;
/*    */ import org.apache.batik.css.engine.value.ValueConstants;
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
/*    */ public class FontVariantManager
/*    */   extends IdentifierManager
/*    */ {
/* 40 */   protected static final StringMap values = new StringMap();
/*    */   static {
/* 42 */     values.put("normal", ValueConstants.NORMAL_VALUE);
/*    */     
/* 44 */     values.put("small-caps", ValueConstants.SMALL_CAPS_VALUE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isInheritedProperty() {
/* 53 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimatableProperty() {
/* 60 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAdditiveProperty() {
/* 67 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPropertyType() {
/* 74 */     return 15;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPropertyName() {
/* 82 */     return "font-variant";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Value getDefaultValue() {
/* 90 */     return ValueConstants.NORMAL_VALUE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StringMap getIdentifiers() {
/* 97 */     return values;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/css2/FontVariantManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */