/*    */ package org.apache.pdfbox.cos;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
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
/*    */ public final class COSNull
/*    */   extends COSBase
/*    */ {
/* 32 */   public static final byte[] NULL_BYTES = new byte[] { 110, 117, 108, 108 };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public static final COSNull NULL = new COSNull();
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
/*    */   public Object accept(ICOSVisitor visitor) throws IOException {
/* 57 */     return visitor.visitFromNull(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePDF(OutputStream output) throws IOException {
/* 68 */     output.write(NULL_BYTES);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     return "COSNull{}";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/COSNull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */