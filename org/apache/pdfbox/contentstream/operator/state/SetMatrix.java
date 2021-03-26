/*    */ package org.apache.pdfbox.contentstream.operator.state;
/*    */ 
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
/*    */ public class SetMatrix
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws MissingOperandException {
/* 39 */     if (arguments.size() < 6)
/*    */     {
/* 41 */       throw new MissingOperandException(operator, arguments);
/*    */     }
/* 43 */     if (!checkArrayTypesClass(arguments, COSNumber.class)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 48 */     COSNumber a = (COSNumber)arguments.get(0);
/* 49 */     COSNumber b = (COSNumber)arguments.get(1);
/* 50 */     COSNumber c = (COSNumber)arguments.get(2);
/* 51 */     COSNumber d = (COSNumber)arguments.get(3);
/* 52 */     COSNumber e = (COSNumber)arguments.get(4);
/* 53 */     COSNumber f = (COSNumber)arguments.get(5);
/*    */ 
/*    */     
/* 56 */     Matrix matrix = new Matrix(a.floatValue(), b.floatValue(), c.floatValue(), d.floatValue(), e.floatValue(), f.floatValue());
/*    */     
/* 58 */     this.context.setTextMatrix(matrix);
/* 59 */     this.context.setTextLineMatrix(matrix.clone());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 65 */     return "Tm";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/state/SetMatrix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */