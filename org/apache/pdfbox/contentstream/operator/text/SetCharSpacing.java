/*    */ package org.apache.pdfbox.contentstream.operator.text;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.MissingOperandException;
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
/*    */ public class SetCharSpacing
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 39 */     if (arguments.isEmpty())
/*    */     {
/* 41 */       throw new MissingOperandException(operator, arguments);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 47 */     Object charSpacing = arguments.get(arguments.size() - 1);
/* 48 */     if (charSpacing instanceof COSNumber) {
/*    */       
/* 50 */       COSNumber characterSpacing = (COSNumber)charSpacing;
/* 51 */       this.context.getGraphicsState().getTextState().setCharacterSpacing(characterSpacing.floatValue());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 58 */     return "Tc";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/text/SetCharSpacing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */