/*    */ package org.apache.pdfbox.contentstream.operator.text;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ 
/*    */ public class SetTextRise
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 39 */     if (arguments.isEmpty()) {
/*    */       return;
/*    */     }
/*    */     
/* 43 */     COSBase base = arguments.get(0);
/* 44 */     if (!(base instanceof COSNumber)) {
/*    */       return;
/*    */     }
/*    */     
/* 48 */     COSNumber rise = (COSNumber)base;
/* 49 */     this.context.getGraphicsState().getTextState().setRise(rise.floatValue());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 55 */     return "Ts";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/text/SetTextRise.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */