/*    */ package org.apache.pdfbox.contentstream.operator.graphics;
/*    */ 
/*    */ import org.apache.pdfbox.contentstream.PDFGraphicsStreamEngine;
/*    */ import org.apache.pdfbox.contentstream.PDFStreamEngine;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
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
/*    */ public abstract class GraphicsOperatorProcessor
/*    */   extends OperatorProcessor
/*    */ {
/*    */   protected PDFGraphicsStreamEngine context;
/*    */   
/*    */   public void setContext(PDFStreamEngine context) {
/* 36 */     super.setContext(context);
/* 37 */     this.context = (PDFGraphicsStreamEngine)context;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/graphics/GraphicsOperatorProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */