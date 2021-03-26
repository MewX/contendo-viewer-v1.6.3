/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDAppearanceContentStream;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationSquareCircle;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderEffectDictionary;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
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
/*     */ public class PDSquareAppearanceHandler
/*     */   extends PDAbstractAppearanceHandler
/*     */ {
/*  43 */   private static final Log LOG = LogFactory.getLog(PDSquareAppearanceHandler.class);
/*     */ 
/*     */   
/*     */   public PDSquareAppearanceHandler(PDAnnotation annotation) {
/*  47 */     super(annotation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateAppearanceStreams() {
/*  53 */     generateNormalAppearance();
/*  54 */     generateRolloverAppearance();
/*  55 */     generateDownAppearance();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateNormalAppearance() {
/*  61 */     float lineWidth = getLineWidth();
/*  62 */     PDAnnotationSquareCircle annotation = (PDAnnotationSquareCircle)getAnnotation();
/*  63 */     PDAppearanceContentStream contentStream = null;
/*     */ 
/*     */     
/*     */     try {
/*  67 */       contentStream = getNormalAppearanceAsContentStream();
/*  68 */       boolean hasStroke = contentStream.setStrokingColorOnDemand(getColor());
/*     */       
/*  70 */       boolean hasBackground = contentStream.setNonStrokingColorOnDemand(annotation.getInteriorColor());
/*     */       
/*  72 */       setOpacity(contentStream, annotation.getConstantOpacity());
/*     */       
/*  74 */       contentStream.setBorderLine(lineWidth, annotation.getBorderStyle(), annotation.getBorder());
/*  75 */       PDBorderEffectDictionary borderEffect = annotation.getBorderEffect();
/*     */       
/*  77 */       if (borderEffect != null && borderEffect.getStyle().equals("C")) {
/*     */ 
/*     */         
/*  80 */         CloudyBorder cloudyBorder = new CloudyBorder(contentStream, borderEffect.getIntensity(), lineWidth, getRectangle());
/*  81 */         cloudyBorder.createCloudyRectangle(annotation.getRectDifference());
/*  82 */         annotation.setRectangle(cloudyBorder.getRectangle());
/*  83 */         annotation.setRectDifference(cloudyBorder.getRectDifference());
/*  84 */         PDAppearanceStream appearanceStream = annotation.getNormalAppearanceStream();
/*  85 */         appearanceStream.setBBox(cloudyBorder.getBBox());
/*  86 */         appearanceStream.setMatrix(cloudyBorder.getMatrix());
/*     */       }
/*     */       else {
/*     */         
/*  90 */         PDRectangle borderBox = handleBorderBox(annotation, lineWidth);
/*     */         
/*  92 */         contentStream.addRect(borderBox.getLowerLeftX(), borderBox.getLowerLeftY(), borderBox
/*  93 */             .getWidth(), borderBox.getHeight());
/*     */       } 
/*     */       
/*  96 */       contentStream.drawShape(lineWidth, hasStroke, hasBackground);
/*     */     }
/*  98 */     catch (IOException e) {
/*     */       
/* 100 */       LOG.error(e);
/*     */     } finally {
/*     */       
/* 103 */       IOUtils.closeQuietly((Closeable)contentStream);
/*     */     } 
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
/*     */   public void generateRolloverAppearance() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateDownAppearance() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float getLineWidth() {
/* 136 */     PDAnnotationMarkup annotation = (PDAnnotationMarkup)getAnnotation();
/*     */     
/* 138 */     PDBorderStyleDictionary bs = annotation.getBorderStyle();
/*     */     
/* 140 */     if (bs != null)
/*     */     {
/* 142 */       return bs.getWidth();
/*     */     }
/*     */     
/* 145 */     COSArray borderCharacteristics = annotation.getBorder();
/* 146 */     if (borderCharacteristics.size() >= 3) {
/*     */       
/* 148 */       COSBase base = borderCharacteristics.getObject(2);
/* 149 */       if (base instanceof COSNumber)
/*     */       {
/* 151 */         return ((COSNumber)base).floatValue();
/*     */       }
/*     */     } 
/*     */     
/* 155 */     return 1.0F;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDSquareAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */