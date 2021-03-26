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
/*    */ public class FDFAnnotationLink
/*    */   extends FDFAnnotation
/*    */ {
/*    */   public static final String SUBTYPE = "Link";
/*    */   
/*    */   public FDFAnnotationLink() {
/* 41 */     this.annot.setName(COSName.SUBTYPE, "Link");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FDFAnnotationLink(COSDictionary a) {
/* 51 */     super(a);
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
/*    */   public FDFAnnotationLink(Element element) throws IOException {
/* 63 */     super(element);
/* 64 */     this.annot.setName(COSName.SUBTYPE, "Link");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationLink.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */