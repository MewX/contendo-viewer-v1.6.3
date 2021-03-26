/*    */ package org.apache.batik.apps.rasterizer;
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
/*    */ public class SVGConverterException
/*    */   extends Exception
/*    */ {
/*    */   protected String errorCode;
/*    */   protected Object[] errorInfo;
/*    */   protected boolean isFatal;
/*    */   
/*    */   public SVGConverterException(String errorCode) {
/* 44 */     this(errorCode, null, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public SVGConverterException(String errorCode, Object[] errorInfo) {
/* 49 */     this(errorCode, errorInfo, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public SVGConverterException(String errorCode, Object[] errorInfo, boolean isFatal) {
/* 55 */     this.errorCode = errorCode;
/* 56 */     this.errorInfo = errorInfo;
/* 57 */     this.isFatal = isFatal;
/*    */   }
/*    */ 
/*    */   
/*    */   public SVGConverterException(String errorCode, boolean isFatal) {
/* 62 */     this(errorCode, null, isFatal);
/*    */   }
/*    */   
/*    */   public boolean isFatal() {
/* 66 */     return this.isFatal;
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 70 */     return Messages.formatMessage(this.errorCode, this.errorInfo);
/*    */   }
/*    */   
/*    */   public String getErrorCode() {
/* 74 */     return this.errorCode;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/rasterizer/SVGConverterException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */