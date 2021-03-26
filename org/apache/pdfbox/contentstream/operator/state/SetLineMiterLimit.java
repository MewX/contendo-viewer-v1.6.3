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
/*    */ public class SetLineMiterLimit
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 39 */     if (arguments.isEmpty())
/*    */     {
/* 41 */       throw new MissingOperandException(operator, arguments);
/*    */     }
/* 43 */     COSNumber miterLimit = (COSNumber)arguments.get(0);
/* 44 */     this.context.getGraphicsState().setMiterLimit(miterLimit.floatValue());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 50 */     return "M";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/state/SetLineMiterLimit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */