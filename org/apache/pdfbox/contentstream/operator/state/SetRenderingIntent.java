/*    */ package org.apache.pdfbox.contentstream.operator.state;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.MissingOperandException;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.pdmodel.graphics.state.RenderingIntent;
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
/*    */ public class SetRenderingIntent
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> operands) throws IOException {
/* 40 */     if (operands.isEmpty())
/*    */     {
/* 42 */       throw new MissingOperandException(operator, operands);
/*    */     }
/* 44 */     COSBase base = operands.get(0);
/* 45 */     if (!(base instanceof COSName)) {
/*    */       return;
/*    */     }
/*    */     
/* 49 */     this.context.getGraphicsState().setRenderingIntent(
/* 50 */         RenderingIntent.fromString(((COSName)base).getName()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 56 */     return "ri";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/state/SetRenderingIntent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */