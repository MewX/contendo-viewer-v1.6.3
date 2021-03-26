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
/*    */ public class FDFAnnotationSquiggly
/*    */   extends FDFAnnotationTextMarkup
/*    */ {
/*    */   public static final String SUBTYPE = "Squiggly";
/*    */   
/*    */   public FDFAnnotationSquiggly() {
/* 43 */     this.annot.setName(COSName.SUBTYPE, "Squiggly");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FDFAnnotationSquiggly(COSDictionary a) {
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
/*    */   public FDFAnnotationSquiggly(Element element) throws IOException {
/* 65 */     super(element);
/* 66 */     this.annot.setName(COSName.SUBTYPE, "Squiggly");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationSquiggly.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */