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
/*    */ public class FDFAnnotationUnderline
/*    */   extends FDFAnnotationTextMarkup
/*    */ {
/*    */   public static final String SUBTYPE = "Underline";
/*    */   
/*    */   public FDFAnnotationUnderline() {
/* 43 */     this.annot.setName(COSName.SUBTYPE, "Underline");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FDFAnnotationUnderline(COSDictionary a) {
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
/*    */   public FDFAnnotationUnderline(Element element) throws IOException {
/* 65 */     super(element);
/* 66 */     this.annot.setName(COSName.SUBTYPE, "Underline");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationUnderline.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */