/*    */ package org.apache.pdfbox.contentstream.operator.text;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.MissingOperandException;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSFloat;
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
/*    */ public class MoveTextSetLeading
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 41 */     if (arguments.size() < 2)
/*    */     {
/* 43 */       throw new MissingOperandException(operator, arguments);
/*    */     }
/*    */ 
/*    */     
/* 47 */     COSBase base1 = arguments.get(1);
/* 48 */     if (!(base1 instanceof COSNumber)) {
/*    */       return;
/*    */     }
/*    */     
/* 52 */     COSNumber y = (COSNumber)base1;
/*    */     
/* 54 */     List<COSBase> args = new ArrayList<COSBase>();
/* 55 */     args.add(new COSFloat(-1.0F * y.floatValue()));
/* 56 */     this.context.processOperator("TL", args);
/* 57 */     this.context.processOperator("Td", arguments);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 63 */     return "TD";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/text/MoveTextSetLeading.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */