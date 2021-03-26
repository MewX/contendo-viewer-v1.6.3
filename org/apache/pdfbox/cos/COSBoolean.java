/*     */ package org.apache.pdfbox.cos;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
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
/*     */ public final class COSBoolean
/*     */   extends COSBase
/*     */ {
/*  32 */   public static final byte[] TRUE_BYTES = new byte[] { 116, 114, 117, 101 };
/*     */ 
/*     */ 
/*     */   
/*  36 */   public static final byte[] FALSE_BYTES = new byte[] { 102, 97, 108, 115, 101 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public static final COSBoolean TRUE = new COSBoolean(true);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public static final COSBoolean FALSE = new COSBoolean(false);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean value;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSBoolean(boolean aValue) {
/*  57 */     this.value = aValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getValue() {
/*  67 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean getValueAsObject() {
/*  77 */     return this.value ? Boolean.TRUE : Boolean.FALSE;
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
/*     */   public static COSBoolean getBoolean(boolean value) {
/*  89 */     return value ? TRUE : FALSE;
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
/*     */   public static COSBoolean getBoolean(Boolean value) {
/* 101 */     return getBoolean(value.booleanValue());
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
/*     */   public Object accept(ICOSVisitor visitor) throws IOException {
/* 114 */     return visitor.visitFromBoolean(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 125 */     return String.valueOf(this.value);
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
/*     */   public void writePDF(OutputStream output) throws IOException {
/* 137 */     if (this.value) {
/*     */       
/* 139 */       output.write(TRUE_BYTES);
/*     */     }
/*     */     else {
/*     */       
/* 143 */       output.write(FALSE_BYTES);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/COSBoolean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */