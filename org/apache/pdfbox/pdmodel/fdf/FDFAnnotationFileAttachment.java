/*    */ package org.apache.pdfbox.pdmodel.fdf;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.w3c.dom.Element;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FDFAnnotationFileAttachment
/*    */   extends FDFAnnotation
/*    */ {
/*    */   public static final String SUBTYPE = "FileAttachment";
/*    */   
/*    */   public FDFAnnotationFileAttachment() {
/* 43 */     this.annot.setName(COSName.SUBTYPE, "FileAttachment");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FDFAnnotationFileAttachment(COSDictionary a) {
/* 53 */     super(a);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FDFAnnotationFileAttachment(Element element) throws IOException {
/* 65 */     super(element);
/* 66 */     this.annot.setName(COSName.SUBTYPE, "FileAttachment");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationFileAttachment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */