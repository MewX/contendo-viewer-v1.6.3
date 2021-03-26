/*    */ package org.apache.pdfbox.contentstream.operator.markedcontent;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.MissingOperandException;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
/*    */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*    */ import org.apache.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
/*    */ import org.apache.pdfbox.text.PDFMarkedContentExtractor;
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
/*    */ public class DrawObject
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 44 */     if (arguments.isEmpty())
/*    */     {
/* 46 */       throw new MissingOperandException(operator, arguments);
/*    */     }
/* 48 */     COSBase base0 = arguments.get(0);
/* 49 */     if (!(base0 instanceof COSName)) {
/*    */       return;
/*    */     }
/*    */     
/* 53 */     COSName name = (COSName)base0;
/* 54 */     PDXObject xobject = this.context.getResources().getXObject(name);
/* 55 */     ((PDFMarkedContentExtractor)this.context).xobject(xobject);
/*    */     
/* 57 */     if (xobject instanceof PDTransparencyGroup) {
/*    */       
/* 59 */       this.context.showTransparencyGroup((PDTransparencyGroup)xobject);
/*    */     }
/* 61 */     else if (xobject instanceof PDFormXObject) {
/*    */       
/* 63 */       PDFormXObject form = (PDFormXObject)xobject;
/* 64 */       this.context.showForm(form);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 71 */     return "Do";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/markedcontent/DrawObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */