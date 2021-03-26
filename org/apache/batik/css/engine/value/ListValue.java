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
/*     */ public class ListValue
/*     */   extends AbstractValue
/*     */ {
/*     */   protected int length;
/*  40 */   protected Value[] items = new Value[5];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   protected char separator = ',';
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListValue() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListValue(char s) {
/*  57 */     this.separator = s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getSeparatorChar() {
/*  64 */     return this.separator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getCssValueType() {
/*  71 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/*  78 */     StringBuffer sb = new StringBuffer(this.length * 8);
/*  79 */     if (this.length > 0) {
/*  80 */       sb.append(this.items[0].getCssText());
/*     */     }
/*  82 */     for (int i = 1; i < this.length; i++) {
/*  83 */       sb.append(this.separator);
/*  84 */       sb.append(this.items[i].getCssText());
/*     */     } 
/*  86 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() throws DOMException {
/*  93 */     return this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value item(int index) throws DOMException {
/* 100 */     return this.items[index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 107 */     return getCssText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(Value v) {
/* 114 */     if (this.length == this.items.length) {
/* 115 */       Value[] t = new Value[this.length * 2];
/* 116 */       System.arraycopy(this.items, 0, t, 0, this.length);
/* 117 */       this.items = t;
/*     */     } 
/* 119 */     this.items[this.length++] = v;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/ListValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */