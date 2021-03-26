/*     */ package org.apache.batik.css.engine.value;
/*     */ 
/*     */ import org.w3c.dom.DOMException;
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
/*     */ 
/*     */ public class RectValue
/*     */   extends AbstractValue
/*     */ {
/*     */   protected Value top;
/*     */   protected Value right;
/*     */   protected Value bottom;
/*     */   protected Value left;
/*     */   
/*     */   public RectValue(Value t, Value r, Value b, Value l) {
/*  56 */     this.top = t;
/*  57 */     this.right = r;
/*  58 */     this.bottom = b;
/*  59 */     this.left = l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getPrimitiveType() {
/*  66 */     return 24;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/*  73 */     return "rect(" + this.top.getCssText() + ", " + this.right.getCssText() + ", " + this.bottom.getCssText() + ", " + this.left.getCssText() + ')';
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getTop() throws DOMException {
/*  83 */     return this.top;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getRight() throws DOMException {
/*  90 */     return this.right;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getBottom() throws DOMException {
/*  97 */     return this.bottom;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getLeft() throws DOMException {
/* 104 */     return this.left;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 111 */     return getCssText();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/RectValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */