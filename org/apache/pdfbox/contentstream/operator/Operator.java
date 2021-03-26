/*     */ package org.apache.pdfbox.contentstream.operator;
/*     */ 
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
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
/*     */ public final class Operator
/*     */ {
/*     */   private final String theOperator;
/*     */   private byte[] imageData;
/*     */   private COSDictionary imageParameters;
/*  36 */   private static final ConcurrentMap<String, Operator> operators = new ConcurrentHashMap<String, Operator>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Operator(String aOperator) {
/*  46 */     this.theOperator = aOperator;
/*  47 */     if (aOperator.startsWith("/"))
/*     */     {
/*  49 */       throw new IllegalArgumentException("Operators are not allowed to start with / '" + aOperator + "'");
/*     */     }
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
/*     */   public static Operator getOperator(String operator) {
/*     */     Operator operation;
/*  63 */     if (operator.equals("ID") || "BI"
/*  64 */       .equals(operator)) {
/*     */ 
/*     */       
/*  67 */       operation = new Operator(operator);
/*     */     }
/*     */     else {
/*     */       
/*  71 */       operation = operators.get(operator);
/*  72 */       if (operation == null) {
/*     */ 
/*     */ 
/*     */         
/*  76 */         operation = operators.putIfAbsent(operator, new Operator(operator));
/*  77 */         if (operation == null)
/*     */         {
/*  79 */           operation = operators.get(operator);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  84 */     return operation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  94 */     return this.theOperator;
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
/* 105 */     return "PDFOperator{" + this.theOperator + "}";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getImageData() {
/* 116 */     return this.imageData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImageData(byte[] imageDataArray) {
/* 126 */     this.imageData = imageDataArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getImageParameters() {
/* 136 */     return this.imageParameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImageParameters(COSDictionary params) {
/* 146 */     this.imageParameters = params;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/Operator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */