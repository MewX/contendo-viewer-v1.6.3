/*    */ package org.apache.pdfbox.contentstream.operator.graphics;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
/*    */ import org.apache.pdfbox.pdmodel.graphics.image.PDInlineImage;
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
/*    */ public final class BeginInlineImage
/*    */   extends GraphicsOperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> operands) throws IOException {
/* 38 */     if (operator.getImageData() == null || (operator.getImageData()).length == 0) {
/*    */       return;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 44 */     PDInlineImage pDInlineImage = new PDInlineImage(operator.getImageParameters(), operator.getImageData(), this.context.getResources());
/* 45 */     this.context.drawImage((PDImage)pDInlineImage);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 51 */     return "BI";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/graphics/BeginInlineImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */