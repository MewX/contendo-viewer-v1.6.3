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
/*    */ public class FillNonZeroRule
/*    */   extends GraphicsOperatorProcessor
/*    */ {
/*    */   public final void process(Operator operator, List<COSBase> operands) throws IOException {
/* 37 */     this.context.fillPath(1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 43 */     return "f";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/graphics/FillNonZeroRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */