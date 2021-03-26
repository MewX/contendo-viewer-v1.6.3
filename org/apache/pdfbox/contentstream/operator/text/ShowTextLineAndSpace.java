/*    */ package org.apache.pdfbox.contentstream.operator.text;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.MissingOperandException;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
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
/*    */ public class ShowTextLineAndSpace
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 39 */     if (arguments.size() < 3)
/*    */     {
/* 41 */       throw new MissingOperandException(operator, arguments);
/*    */     }
/* 43 */     this.context.processOperator("Tw", arguments.subList(0, 1));
/* 44 */     this.context.processOperator("Tc", arguments.subList(1, 2));
/* 45 */     this.context.processOperator("'", arguments.subList(2, 3));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 51 */     return "\"";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/text/ShowTextLineAndSpace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */