/*    */ package org.apache.pdfbox.contentstream.operator;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
/*    */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*    */ import org.apache.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
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
/*    */ public class DrawObject
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 39 */     if (arguments.isEmpty())
/*    */     {
/* 41 */       throw new MissingOperandException(operator, arguments);
/*    */     }
/* 43 */     COSBase base0 = arguments.get(0);
/* 44 */     if (!(base0 instanceof COSName)) {
/*    */       return;
/*    */     }
/*    */     
/* 48 */     COSName name = (COSName)base0;
/*    */     
/* 50 */     if (this.context.getResources().isImageXObject(name)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 56 */     PDXObject xobject = this.context.getResources().getXObject(name);
/*    */     
/* 58 */     if (xobject instanceof PDTransparencyGroup) {
/*    */       
/* 60 */       this.context.showTransparencyGroup((PDTransparencyGroup)xobject);
/*    */     }
/* 62 */     else if (xobject instanceof PDFormXObject) {
/*    */       
/* 64 */       PDFormXObject form = (PDFormXObject)xobject;
/* 65 */       this.context.showForm(form);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 72 */     return "Do";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/DrawObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */