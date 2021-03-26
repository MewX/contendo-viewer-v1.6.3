/*    */ package org.apache.xmlgraphics.image.writer;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractImageWriter
/*    */   implements ImageWriter
/*    */ {
/*    */   public MultiImageWriter createMultiImageWriter(OutputStream out) throws IOException {
/* 38 */     throw new UnsupportedOperationException("This ImageWriter does not support writing multiple images to a single image file.");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isFunctional() {
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean supportsMultiImageWriter() {
/* 49 */     return false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/writer/AbstractImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */