/*    */ package org.apache.pdfbox.filter;
/*    */ 
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.pdmodel.graphics.color.PDJPXColorSpace;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class DecodeResult
/*    */ {
/* 31 */   public static final DecodeResult DEFAULT = new DecodeResult(new COSDictionary());
/*    */   
/*    */   private final COSDictionary parameters;
/*    */   
/*    */   private PDJPXColorSpace colorSpace;
/*    */   
/*    */   DecodeResult(COSDictionary parameters) {
/* 38 */     this.parameters = parameters;
/*    */   }
/*    */ 
/*    */   
/*    */   DecodeResult(COSDictionary parameters, PDJPXColorSpace colorSpace) {
/* 43 */     this.parameters = parameters;
/* 44 */     this.colorSpace = colorSpace;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public COSDictionary getParameters() {
/* 53 */     return this.parameters;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDJPXColorSpace getJPXColorSpace() {
/* 62 */     return this.colorSpace;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void setColorSpace(PDJPXColorSpace colorSpace) {
/* 68 */     this.colorSpace = colorSpace;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/DecodeResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */