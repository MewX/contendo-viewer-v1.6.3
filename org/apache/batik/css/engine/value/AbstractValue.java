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
/*     */ public abstract class AbstractValue
/*     */   implements Value
/*     */ {
/*     */   public short getCssValueType() {
/*  36 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getPrimitiveType() {
/*  43 */     throw createDOMException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloatValue() throws DOMException {
/*  50 */     throw createDOMException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() throws DOMException {
/*  57 */     throw createDOMException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getRed() throws DOMException {
/*  64 */     throw createDOMException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getGreen() throws DOMException {
/*  71 */     throw createDOMException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getBlue() throws DOMException {
/*  78 */     throw createDOMException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() throws DOMException {
/*  85 */     throw createDOMException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value item(int index) throws DOMException {
/*  92 */     throw createDOMException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getTop() throws DOMException {
/*  99 */     throw createDOMException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getRight() throws DOMException {
/* 106 */     throw createDOMException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getBottom() throws DOMException {
/* 113 */     throw createDOMException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getLeft() throws DOMException {
/* 120 */     throw createDOMException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIdentifier() throws DOMException {
/* 127 */     throw createDOMException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getListStyle() throws DOMException {
/* 134 */     throw createDOMException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSeparator() throws DOMException {
/* 141 */     throw createDOMException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DOMException createDOMException() {
/* 148 */     Object[] p = { Integer.valueOf(getCssValueType()) };
/* 149 */     String s = Messages.formatMessage("invalid.value.access", p);
/* 150 */     return new DOMException((short)15, s);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/AbstractValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */