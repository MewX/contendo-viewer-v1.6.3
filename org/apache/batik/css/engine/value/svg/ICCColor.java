/*     */ package org.apache.batik.css.engine.value.svg;
/*     */ 
/*     */ import org.apache.batik.css.engine.value.AbstractValue;
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
/*     */ public class ICCColor
/*     */   extends AbstractValue
/*     */ {
/*     */   public static final String ICC_COLOR_FUNCTION = "icc-color";
/*     */   protected String colorProfile;
/*     */   protected int count;
/*  48 */   protected float[] colors = new float[5];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ICCColor(String name) {
/*  54 */     this.colorProfile = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getCssValueType() {
/*  62 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getColorProfile() throws DOMException {
/*  69 */     return this.colorProfile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfColors() throws DOMException {
/*  76 */     return this.count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getColor(int i) throws DOMException {
/*  83 */     return this.colors[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/*  90 */     StringBuffer sb = new StringBuffer(this.count * 8);
/*  91 */     sb.append("icc-color").append('(');
/*  92 */     sb.append(this.colorProfile);
/*  93 */     for (int i = 0; i < this.count; i++) {
/*  94 */       sb.append(", ");
/*  95 */       sb.append(this.colors[i]);
/*     */     } 
/*  97 */     sb.append(')');
/*  98 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(float c) {
/* 105 */     if (this.count == this.colors.length) {
/* 106 */       float[] t = new float[this.count * 2];
/* 107 */       System.arraycopy(this.colors, 0, t, 0, this.count);
/* 108 */       this.colors = t;
/*     */     } 
/* 110 */     this.colors[this.count++] = c;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 115 */     return getCssText();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/ICCColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */