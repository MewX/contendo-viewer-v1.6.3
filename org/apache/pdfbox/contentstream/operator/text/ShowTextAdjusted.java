/*    */ package org.apache.pdfbox.contentstream.operator.text;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSArray;
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
/*    */ public class ShowTextAdjusted
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 38 */     if (arguments.isEmpty()) {
/*    */       return;
/*    */     }
/*    */     
/* 42 */     COSBase base = arguments.get(0);
/* 43 */     if (!(base instanceof COSArray)) {
/*    */       return;
/*    */     }
/*    */     
/* 47 */     if (this.context.getTextMatrix() == null) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 52 */     COSArray array = (COSArray)base;
/* 53 */     this.context.showTextStrings(array);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 59 */     return "TJ";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/text/ShowTextAdjusted.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */