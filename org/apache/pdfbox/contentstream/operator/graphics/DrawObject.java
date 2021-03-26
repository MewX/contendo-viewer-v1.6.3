/*    */ package org.apache.pdfbox.contentstream.operator.graphics;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.MissingOperandException;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.pdmodel.MissingResourceException;
/*    */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
/*    */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*    */ import org.apache.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
/*    */ import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
/*    */ import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
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
/*    */ public final class DrawObject
/*    */   extends GraphicsOperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> operands) throws IOException {
/* 44 */     if (operands.isEmpty())
/*    */     {
/* 46 */       throw new MissingOperandException(operator, operands);
/*    */     }
/* 48 */     COSBase base0 = operands.get(0);
/* 49 */     if (!(base0 instanceof COSName)) {
/*    */       return;
/*    */     }
/*    */     
/* 53 */     COSName objectName = (COSName)base0;
/* 54 */     PDXObject xobject = this.context.getResources().getXObject(objectName);
/*    */     
/* 56 */     if (xobject == null)
/*    */     {
/* 58 */       throw new MissingResourceException("Missing XObject: " + objectName.getName());
/*    */     }
/* 60 */     if (xobject instanceof PDImageXObject) {
/*    */       
/* 62 */       PDImageXObject image = (PDImageXObject)xobject;
/* 63 */       this.context.drawImage((PDImage)image);
/*    */     }
/* 65 */     else if (xobject instanceof PDTransparencyGroup) {
/*    */       
/* 67 */       getContext().showTransparencyGroup((PDTransparencyGroup)xobject);
/*    */     }
/* 69 */     else if (xobject instanceof PDFormXObject) {
/*    */       
/* 71 */       getContext().showForm((PDFormXObject)xobject);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 78 */     return "Do";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/graphics/DrawObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */