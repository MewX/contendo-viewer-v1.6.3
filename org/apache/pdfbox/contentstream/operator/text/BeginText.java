/*    */ package org.apache.pdfbox.contentstream.operator.text;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSBase;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BeginText
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 39 */     this.context.setTextMatrix(new Matrix());
/* 40 */     this.context.setTextLineMatrix(new Matrix());
/* 41 */     this.context.beginText();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 47 */     return "BT";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/text/BeginText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */