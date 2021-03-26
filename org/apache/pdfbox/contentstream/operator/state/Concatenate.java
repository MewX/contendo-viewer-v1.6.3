/*    */ package org.apache.pdfbox.contentstream.operator.state;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.MissingOperandException;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSNumber;
/*    */ import org.apache.pdfbox.util.Matrix;
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
/*    */ public class Concatenate
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 40 */     if (arguments.size() < 6)
/*    */     {
/* 42 */       throw new MissingOperandException(operator, arguments);
/*    */     }
/* 44 */     if (!checkArrayTypesClass(arguments, COSNumber.class)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 50 */     COSNumber a = (COSNumber)arguments.get(0);
/* 51 */     COSNumber b = (COSNumber)arguments.get(1);
/* 52 */     COSNumber c = (COSNumber)arguments.get(2);
/* 53 */     COSNumber d = (COSNumber)arguments.get(3);
/* 54 */     COSNumber e = (COSNumber)arguments.get(4);
/* 55 */     COSNumber f = (COSNumber)arguments.get(5);
/*    */ 
/*    */     
/* 58 */     Matrix matrix = new Matrix(a.floatValue(), b.floatValue(), c.floatValue(), d.floatValue(), e.floatValue(), f.floatValue());
/*    */     
/* 60 */     this.context.getGraphicsState().getCurrentTransformationMatrix().concatenate(matrix);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 66 */     return "cm";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/state/Concatenate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */