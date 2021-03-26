/*    */ package org.apache.pdfbox.contentstream.operator.graphics;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.cos.COSBase;
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
/*    */ public final class StrokePath
/*    */   extends GraphicsOperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> operands) throws IOException {
/* 37 */     this.context.strokePath();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 43 */     return "S";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/graphics/StrokePath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */