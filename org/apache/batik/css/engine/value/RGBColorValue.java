/*    */ package org.apache.batik.css.engine.value;
/*    */ 
/*    */ import org.w3c.dom.DOMException;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RGBColorValue
/*    */   extends AbstractValue
/*    */ {
/*    */   protected Value red;
/*    */   protected Value green;
/*    */   protected Value blue;
/*    */   
/*    */   public RGBColorValue(Value r, Value g, Value b) {
/* 51 */     this.red = r;
/* 52 */     this.green = g;
/* 53 */     this.blue = b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getPrimitiveType() {
/* 60 */     return 25;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCssText() {
/* 67 */     return "rgb(" + this.red.getCssText() + ", " + this.green.getCssText() + ", " + this.blue.getCssText() + ')';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Value getRed() throws DOMException {
/* 77 */     return this.red;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Value getGreen() throws DOMException {
/* 84 */     return this.green;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Value getBlue() throws DOMException {
/* 91 */     return this.blue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 98 */     return getCssText();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/RGBColorValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */