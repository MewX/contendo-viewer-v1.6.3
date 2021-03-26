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
/*     */ public class StringValue
/*     */   extends AbstractValue
/*     */ {
/*     */   protected String value;
/*     */   protected short unitType;
/*     */   
/*     */   public static String getCssText(short type, String value) {
/*     */     char q;
/*  36 */     switch (type) {
/*     */       case 20:
/*  38 */         return "url(" + value + ')';
/*     */       
/*     */       case 19:
/*  41 */         q = (value.indexOf('"') != -1) ? '\'' : '"';
/*  42 */         return q + value + q;
/*     */     } 
/*  44 */     return value;
/*     */   }
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
/*     */   public StringValue(short type, String s) {
/*  61 */     this.unitType = type;
/*  62 */     this.value = s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getPrimitiveType() {
/*  69 */     return this.unitType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  77 */     if (obj == null || !(obj instanceof StringValue)) {
/*  78 */       return false;
/*     */     }
/*  80 */     StringValue v = (StringValue)obj;
/*  81 */     if (this.unitType != v.unitType) {
/*  82 */       return false;
/*     */     }
/*  84 */     return this.value.equals(v.value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/*  91 */     return getCssText(this.unitType, this.value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() throws DOMException {
/* 101 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 108 */     return getCssText();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/StringValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */