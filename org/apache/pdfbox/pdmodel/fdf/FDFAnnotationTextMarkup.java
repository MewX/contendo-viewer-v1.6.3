/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class FDFAnnotationTextMarkup
/*     */   extends FDFAnnotation
/*     */ {
/*     */   public FDFAnnotationTextMarkup() {}
/*     */   
/*     */   public FDFAnnotationTextMarkup(COSDictionary a) {
/*  48 */     super(a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationTextMarkup(Element element) throws IOException {
/*  60 */     super(element);
/*     */     
/*  62 */     String coords = element.getAttribute("coords");
/*  63 */     if (coords == null || coords.isEmpty())
/*     */     {
/*  65 */       throw new IOException("Error: missing attribute 'coords'");
/*     */     }
/*  67 */     String[] coordsValues = coords.split(",");
/*  68 */     if (coordsValues.length < 8)
/*     */     {
/*  70 */       throw new IOException("Error: too little numbers in attribute 'coords'");
/*     */     }
/*  72 */     float[] values = new float[coordsValues.length];
/*  73 */     for (int i = 0; i < coordsValues.length; i++)
/*     */     {
/*  75 */       values[i] = Float.parseFloat(coordsValues[i]);
/*     */     }
/*  77 */     setCoords(values);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCoords(float[] coords) {
/*  90 */     COSArray newQuadPoints = new COSArray();
/*  91 */     newQuadPoints.setFloatArray(coords);
/*  92 */     this.annot.setItem(COSName.QUADPOINTS, (COSBase)newQuadPoints);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getCoords() {
/* 103 */     COSArray quadPoints = (COSArray)this.annot.getItem(COSName.QUADPOINTS);
/* 104 */     if (quadPoints != null)
/*     */     {
/* 106 */       return quadPoints.toFloatArray();
/*     */     }
/*     */ 
/*     */     
/* 110 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationTextMarkup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */