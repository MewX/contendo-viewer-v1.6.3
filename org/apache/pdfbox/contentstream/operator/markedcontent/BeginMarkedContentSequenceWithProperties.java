/*    */ package org.apache.pdfbox.contentstream.operator.markedcontent;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
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
/*    */ public class BeginMarkedContentSequenceWithProperties
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 39 */     COSName tag = null;
/* 40 */     COSDictionary properties = null;
/* 41 */     for (COSBase argument : arguments) {
/*    */       
/* 43 */       if (argument instanceof COSName) {
/*    */         
/* 45 */         tag = (COSName)argument; continue;
/*    */       } 
/* 47 */       if (argument instanceof COSDictionary)
/*    */       {
/* 49 */         properties = (COSDictionary)argument;
/*    */       }
/*    */     } 
/* 52 */     this.context.beginMarkedContentSequence(tag, properties);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 58 */     return "BDC";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/markedcontent/BeginMarkedContentSequenceWithProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */