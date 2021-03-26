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
/*     */ public class ComputedValue
/*     */   implements Value
/*     */ {
/*     */   protected Value cascadedValue;
/*     */   protected Value computedValue;
/*     */   
/*     */   public ComputedValue(Value cv) {
/*  46 */     this.cascadedValue = cv;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getComputedValue() {
/*  53 */     return this.computedValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getCascadedValue() {
/*  60 */     return this.cascadedValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComputedValue(Value v) {
/*  67 */     this.computedValue = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/*  74 */     return this.computedValue.getCssText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getCssValueType() {
/*  81 */     return this.computedValue.getCssValueType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getPrimitiveType() {
/*  88 */     return this.computedValue.getPrimitiveType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloatValue() throws DOMException {
/*  95 */     return this.computedValue.getFloatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() throws DOMException {
/* 102 */     return this.computedValue.getStringValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getRed() throws DOMException {
/* 109 */     return this.computedValue.getRed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getGreen() throws DOMException {
/* 116 */     return this.computedValue.getGreen();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getBlue() throws DOMException {
/* 123 */     return this.computedValue.getBlue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() throws DOMException {
/* 130 */     return this.computedValue.getLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value item(int index) throws DOMException {
/* 137 */     return this.computedValue.item(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getTop() throws DOMException {
/* 144 */     return this.computedValue.getTop();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getRight() throws DOMException {
/* 151 */     return this.computedValue.getRight();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getBottom() throws DOMException {
/* 158 */     return this.computedValue.getBottom();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getLeft() throws DOMException {
/* 165 */     return this.computedValue.getLeft();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIdentifier() throws DOMException {
/* 172 */     return this.computedValue.getIdentifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getListStyle() throws DOMException {
/* 179 */     return this.computedValue.getListStyle();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSeparator() throws DOMException {
/* 186 */     return this.computedValue.getSeparator();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/ComputedValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */