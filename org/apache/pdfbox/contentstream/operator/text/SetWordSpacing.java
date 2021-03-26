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
/*    */ public class SetWordSpacing
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) {
/* 37 */     if (arguments.isEmpty()) {
/*    */       return;
/*    */     }
/*    */     
/* 41 */     COSBase base = arguments.get(0);
/* 42 */     if (!(base instanceof COSNumber)) {
/*    */       return;
/*    */     }
/*    */     
/* 46 */     COSNumber wordSpacing = (COSNumber)base;
/* 47 */     this.context.getGraphicsState().getTextState().setWordSpacing(wordSpacing.floatValue());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 53 */     return "Tw";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/text/SetWordSpacing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */