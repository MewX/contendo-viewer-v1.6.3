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
/*    */ public class SetLineCapStyle
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 38 */     if (arguments.isEmpty())
/*    */     {
/* 40 */       throw new MissingOperandException(operator, arguments);
/*    */     }
/* 42 */     int lineCapStyle = ((COSNumber)arguments.get(0)).intValue();
/* 43 */     this.context.getGraphicsState().setLineCap(lineCapStyle);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 49 */     return "J";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/state/SetLineCapStyle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */