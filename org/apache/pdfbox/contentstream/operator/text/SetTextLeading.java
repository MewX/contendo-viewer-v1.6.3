/*    */ package org.apache.pdfbox.contentstream.operator.text;
/*    */ 
/*    */ import java.util.List;
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
/*    */ public class SetTextLeading
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) {
/* 37 */     COSNumber leading = (COSNumber)arguments.get(0);
/* 38 */     this.context.getGraphicsState().getTextState().setLeading(leading.floatValue());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 44 */     return "TL";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/text/SetTextLeading.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */