/*    */ package org.apache.batik.css.engine.value.svg12;
/*    */ 
/*    */ import org.apache.batik.css.engine.value.FloatValue;
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
/*    */ 
/*    */ 
/*    */ public class LineHeightValue
/*    */   extends FloatValue
/*    */ {
/*    */   protected boolean fontSizeRelative;
/*    */   
/*    */   public LineHeightValue(short unitType, float floatValue, boolean fontSizeRelative) {
/* 42 */     super(unitType, floatValue);
/* 43 */     this.fontSizeRelative = fontSizeRelative;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getFontSizeRelative() {
/* 50 */     return this.fontSizeRelative;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg12/LineHeightValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */