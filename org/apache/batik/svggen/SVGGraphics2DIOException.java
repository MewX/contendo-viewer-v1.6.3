/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ public class SVGGraphics2DIOException
/*    */   extends IOException
/*    */ {
/*    */   private IOException embedded;
/*    */   
/*    */   public SVGGraphics2DIOException(String s) {
/* 39 */     this(s, null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SVGGraphics2DIOException(IOException ex) {
/* 48 */     this(null, ex);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SVGGraphics2DIOException(String s, IOException ex) {
/* 58 */     super(s);
/* 59 */     this.embedded = ex;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 68 */     String msg = super.getMessage();
/* 69 */     if (msg != null)
/* 70 */       return msg; 
/* 71 */     if (this.embedded != null) {
/* 72 */       return this.embedded.getMessage();
/*    */     }
/* 74 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IOException getException() {
/* 82 */     return this.embedded;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGGraphics2DIOException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */