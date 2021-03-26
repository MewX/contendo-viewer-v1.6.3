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
/*    */ 
/*    */ 
/*    */ public class FillNonZeroAndStrokePath
/*    */   extends GraphicsOperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> operands) throws IOException {
/* 39 */     this.context.fillAndStrokePath(1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 45 */     return "B";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/graphics/FillNonZeroAndStrokePath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */