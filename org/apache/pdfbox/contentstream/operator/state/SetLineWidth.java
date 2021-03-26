/*    */ package org.apache.pdfbox.contentstream.operator.state;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.MissingOperandException;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSNumber;
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
/*    */ public class SetLineWidth
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 40 */     if (arguments.isEmpty())
/*    */     {
/* 42 */       throw new MissingOperandException(operator, arguments);
/*    */     }
/* 44 */     COSNumber width = (COSNumber)arguments.get(0);
/* 45 */     this.context.getGraphicsState().setLineWidth(width.floatValue());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 51 */     return "w";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/state/SetLineWidth.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */