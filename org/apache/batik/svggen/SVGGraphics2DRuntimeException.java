/*    */ package org.apache.batik.svggen;
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
/*    */ public class SVGGraphics2DRuntimeException
/*    */   extends RuntimeException
/*    */ {
/*    */   private Exception embedded;
/*    */   
/*    */   public SVGGraphics2DRuntimeException(String s) {
/* 37 */     this(s, null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SVGGraphics2DRuntimeException(Exception ex) {
/* 46 */     this(null, ex);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SVGGraphics2DRuntimeException(String s, Exception ex) {
/* 56 */     super(s);
/* 57 */     this.embedded = ex;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 66 */     String msg = super.getMessage();
/* 67 */     if (msg != null)
/* 68 */       return msg; 
/* 69 */     if (this.embedded != null) {
/* 70 */       return this.embedded.getMessage();
/*    */     }
/* 72 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Exception getException() {
/* 80 */     return this.embedded;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGGraphics2DRuntimeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */