/*    */ package org.apache.pdfbox.contentstream.operator.text;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSString;
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
/*    */ public class ShowText
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 39 */     if (arguments.isEmpty()) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 44 */     COSBase base = arguments.get(0);
/* 45 */     if (!(base instanceof COSString)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 50 */     if (this.context.getTextMatrix() == null) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 55 */     COSString string = (COSString)base;
/* 56 */     this.context.showTextString(string.getBytes());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 62 */     return "Tj";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/text/ShowText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */