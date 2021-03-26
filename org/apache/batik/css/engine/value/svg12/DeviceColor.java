/*     */ package org.apache.batik.css.engine.value.svg12;
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
/*     */ public class DeviceColor
/*     */   extends AbstractValue
/*     */ {
/*     */   public static final String DEVICE_GRAY_COLOR_FUNCTION = "device-gray";
/*     */   public static final String DEVICE_RGB_COLOR_FUNCTION = "device-rgb";
/*     */   public static final String DEVICE_CMYK_COLOR_FUNCTION = "device-cmyk";
/*     */   public static final String DEVICE_NCHANNEL_COLOR_FUNCTION = "device-nchannel";
/*     */   protected boolean nChannel;
/*     */   protected int count;
/*  47 */   protected float[] colors = new float[5];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DeviceColor(boolean nChannel) {
/*  54 */     this.nChannel = nChannel;
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
/*     */   
/*     */   public boolean isNChannel() {
/*  70 */     return this.nChannel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfColors() throws DOMException {
/*  77 */     return this.count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getColor(int i) throws DOMException {
/*  84 */     return this.colors[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/*  91 */     StringBuffer sb = new StringBuffer(this.count * 8);
/*  92 */     if (this.nChannel) {
/*  93 */       sb.append("device-nchannel");
/*     */     } else {
/*  95 */       switch (this.count) {
/*     */         case 1:
/*  97 */           sb.append("device-gray");
/*     */           break;
/*     */         case 3:
/* 100 */           sb.append("device-rgb");
/*     */           break;
/*     */         case 4:
/* 103 */           sb.append("device-cmyk");
/*     */           break;
/*     */         default:
/* 106 */           throw new IllegalStateException("Invalid number of components encountered");
/*     */       } 
/*     */     } 
/* 109 */     sb.append('(');
/* 110 */     for (int i = 0; i < this.count; i++) {
/* 111 */       if (i > 0) {
/* 112 */         sb.append(", ");
/*     */       }
/* 114 */       sb.append(this.colors[i]);
/*     */     } 
/* 116 */     sb.append(')');
/* 117 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(float c) {
/* 124 */     if (this.count == this.colors.length) {
/* 125 */       float[] t = new float[this.count * 2];
/* 126 */       System.arraycopy(this.colors, 0, t, 0, this.count);
/* 127 */       this.colors = t;
/*     */     } 
/* 129 */     this.colors[this.count++] = c;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 134 */     return getCssText();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg12/DeviceColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */