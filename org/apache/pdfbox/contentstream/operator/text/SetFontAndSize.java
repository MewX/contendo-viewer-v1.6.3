/*    */ package org.apache.pdfbox.contentstream.operator.text;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.MissingOperandException;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.cos.COSNumber;
/*    */ import org.apache.pdfbox.pdmodel.font.PDFont;
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
/*    */ 
/*    */ public class SetFontAndSize
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 43 */     if (arguments.size() < 2)
/*    */     {
/* 45 */       throw new MissingOperandException(operator, arguments);
/*    */     }
/*    */     
/* 48 */     COSBase base0 = arguments.get(0);
/* 49 */     COSBase base1 = arguments.get(1);
/* 50 */     if (!(base0 instanceof COSName)) {
/*    */       return;
/*    */     }
/*    */     
/* 54 */     if (!(base1 instanceof COSNumber)) {
/*    */       return;
/*    */     }
/*    */     
/* 58 */     COSName fontName = (COSName)base0;
/* 59 */     float fontSize = ((COSNumber)base1).floatValue();
/* 60 */     this.context.getGraphicsState().getTextState().setFontSize(fontSize);
/* 61 */     PDFont font = this.context.getResources().getFont(fontName);
/* 62 */     this.context.getGraphicsState().getTextState().setFont(font);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 68 */     return "Tf";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/text/SetFontAndSize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */