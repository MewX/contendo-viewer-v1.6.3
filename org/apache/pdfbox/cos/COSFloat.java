/*     */ package org.apache.pdfbox.cos;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.math.BigDecimal;
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
/*     */ public class COSFloat
/*     */   extends COSNumber
/*     */ {
/*     */   private BigDecimal value;
/*     */   private String valueAsString;
/*     */   
/*     */   public COSFloat(float aFloat) {
/*  43 */     this.value = new BigDecimal(String.valueOf(aFloat));
/*  44 */     this.valueAsString = removeNullDigits(this.value.toPlainString());
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
/*     */   public COSFloat(String aFloat) throws IOException {
/*     */     try {
/*  58 */       this.valueAsString = aFloat;
/*  59 */       this.value = new BigDecimal(this.valueAsString);
/*  60 */       checkMinMaxValues();
/*     */     }
/*  62 */     catch (NumberFormatException e) {
/*     */       
/*  64 */       if (aFloat.startsWith("--")) {
/*     */ 
/*     */         
/*  67 */         this.valueAsString = aFloat.substring(1);
/*     */       }
/*  69 */       else if (aFloat.matches("^0\\.0*\\-\\d+")) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  74 */         this.valueAsString = "-" + this.valueAsString.replaceFirst("\\-", "");
/*     */       }
/*     */       else {
/*     */         
/*  78 */         throw new IOException("Error expected floating point number actual='" + aFloat + "'", e);
/*     */       } 
/*     */       
/*     */       try {
/*  82 */         this.value = new BigDecimal(this.valueAsString);
/*  83 */         checkMinMaxValues();
/*     */       }
/*  85 */       catch (NumberFormatException e2) {
/*     */         
/*  87 */         throw new IOException("Error expected floating point number actual='" + aFloat + "'", e2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkMinMaxValues() {
/*  94 */     float floatValue = this.value.floatValue();
/*  95 */     double doubleValue = this.value.doubleValue();
/*  96 */     boolean valueReplaced = false;
/*     */     
/*  98 */     if (floatValue == Float.NEGATIVE_INFINITY || floatValue == Float.POSITIVE_INFINITY) {
/*     */ 
/*     */       
/* 101 */       if (Math.abs(doubleValue) > 3.4028234663852886E38D)
/*     */       {
/* 103 */         floatValue = Float.MAX_VALUE * ((floatValue == Float.POSITIVE_INFINITY) ? true : -1);
/* 104 */         valueReplaced = true;
/*     */       }
/*     */     
/*     */     }
/* 108 */     else if (floatValue == 0.0F && doubleValue != 0.0D) {
/*     */       
/* 110 */       if (Math.abs(doubleValue) < 1.1754943508222875E-38D) {
/*     */         
/* 112 */         floatValue = 1.17549435E-38F;
/* 113 */         floatValue *= (doubleValue >= 0.0D) ? 1.0F : -1.0F;
/* 114 */         valueReplaced = true;
/*     */       } 
/*     */     } 
/* 117 */     if (valueReplaced) {
/*     */       
/* 119 */       this.value = new BigDecimal(floatValue);
/* 120 */       this.valueAsString = removeNullDigits(this.value.toPlainString());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String removeNullDigits(String plainStringValue) {
/* 127 */     if (plainStringValue.indexOf('.') > -1 && !plainStringValue.endsWith(".0"))
/*     */     {
/* 129 */       while (plainStringValue.endsWith("0") && !plainStringValue.endsWith(".0"))
/*     */       {
/* 131 */         plainStringValue = plainStringValue.substring(0, plainStringValue.length() - 1);
/*     */       }
/*     */     }
/* 134 */     return plainStringValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float floatValue() {
/* 145 */     return this.value.floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double doubleValue() {
/* 156 */     return this.value.doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long longValue() {
/* 167 */     return this.value.longValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int intValue() {
/* 178 */     return this.value.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 187 */     return (o instanceof COSFloat && 
/* 188 */       Float.floatToIntBits(((COSFloat)o).value.floatValue()) == Float.floatToIntBits(this.value.floatValue()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 197 */     return this.value.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 206 */     return "COSFloat{" + this.valueAsString + "}";
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
/* 219 */     return visitor.visitFromFloat(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePDF(OutputStream output) throws IOException {
/* 230 */     output.write(this.valueAsString.getBytes("ISO-8859-1"));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/COSFloat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */