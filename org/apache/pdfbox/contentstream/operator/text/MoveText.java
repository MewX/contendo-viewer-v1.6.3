/*    */ package org.apache.pdfbox.contentstream.operator.text;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
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
/*    */ public class MoveText
/*    */   extends OperatorProcessor
/*    */ {
/* 38 */   private static final Log LOG = LogFactory.getLog(MoveText.class);
/*    */ 
/*    */ 
/*    */   
/*    */   public void process(Operator operator, List<COSBase> arguments) throws MissingOperandException {
/* 43 */     if (arguments.size() < 2)
/*    */     {
/* 45 */       throw new MissingOperandException(operator, arguments);
/*    */     }
/* 47 */     Matrix textLineMatrix = this.context.getTextLineMatrix();
/* 48 */     if (textLineMatrix == null) {
/*    */       
/* 50 */       LOG.warn("TextLineMatrix is null, " + getName() + " operator will be ignored");
/*    */       
/*    */       return;
/*    */     } 
/* 54 */     COSBase base0 = arguments.get(0);
/* 55 */     COSBase base1 = arguments.get(1);
/* 56 */     if (!(base0 instanceof COSNumber)) {
/*    */       return;
/*    */     }
/*    */     
/* 60 */     if (!(base1 instanceof COSNumber)) {
/*    */       return;
/*    */     }
/*    */     
/* 64 */     COSNumber x = (COSNumber)base0;
/* 65 */     COSNumber y = (COSNumber)base1;
/*    */     
/* 67 */     Matrix matrix = new Matrix(1.0F, 0.0F, 0.0F, 1.0F, x.floatValue(), y.floatValue());
/* 68 */     textLineMatrix.concatenate(matrix);
/* 69 */     this.context.setTextMatrix(textLineMatrix.clone());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 75 */     return "Td";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/text/MoveText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */