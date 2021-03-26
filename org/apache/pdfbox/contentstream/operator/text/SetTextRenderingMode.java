/*    */ package org.apache.pdfbox.contentstream.operator.text;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.MissingOperandException;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSNumber;
/*    */ import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;
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
/*    */ public class SetTextRenderingMode
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 40 */     if (arguments.isEmpty())
/*    */     {
/* 42 */       throw new MissingOperandException(operator, arguments);
/*    */     }
/* 44 */     COSBase base0 = arguments.get(0);
/* 45 */     if (!(base0 instanceof COSNumber)) {
/*    */       return;
/*    */     }
/*    */     
/* 49 */     COSNumber mode = (COSNumber)base0;
/* 50 */     int val = mode.intValue();
/* 51 */     if (val < 0 || val >= (RenderingMode.values()).length) {
/*    */       return;
/*    */     }
/*    */     
/* 55 */     RenderingMode renderingMode = RenderingMode.fromInt(val);
/* 56 */     this.context.getGraphicsState().getTextState().setRenderingMode(renderingMode);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 62 */     return "Tr";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/text/SetTextRenderingMode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */