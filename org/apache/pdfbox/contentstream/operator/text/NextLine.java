/*    */ package org.apache.pdfbox.contentstream.operator.text;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSFloat;
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
/*    */ public class NextLine
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 40 */     List<COSBase> args = new ArrayList<COSBase>();
/* 41 */     args.add(new COSFloat(0.0F));
/*    */ 
/*    */     
/* 44 */     args.add(new COSFloat(-1.0F * this.context.getGraphicsState().getTextState().getLeading()));
/*    */     
/* 46 */     this.context.processOperator("Td", args);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 52 */     return "T*";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/text/NextLine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */