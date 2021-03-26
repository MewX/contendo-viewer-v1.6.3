/*    */ package org.apache.pdfbox.contentstream.operator.markedcontent;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSName;
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
/*    */ public class BeginMarkedContentSequence
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 38 */     COSName tag = null;
/* 39 */     for (COSBase argument : arguments) {
/*    */       
/* 41 */       if (argument instanceof COSName)
/*    */       {
/* 43 */         tag = (COSName)argument;
/*    */       }
/*    */     } 
/* 46 */     this.context.beginMarkedContentSequence(tag, null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 52 */     return "BMC";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/markedcontent/BeginMarkedContentSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */