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
/*    */ public class FDFAnnotationSound
/*    */   extends FDFAnnotation
/*    */ {
/*    */   public static final String SUBTYPE = "Sound";
/*    */   
/*    */   public FDFAnnotationSound() {
/* 43 */     this.annot.setName(COSName.SUBTYPE, "Sound");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FDFAnnotationSound(COSDictionary a) {
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
/*    */   public FDFAnnotationSound(Element element) throws IOException {
/* 65 */     super(element);
/* 66 */     this.annot.setName(COSName.SUBTYPE, "Sound");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationSound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */