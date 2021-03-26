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
/*     */ public class PDCircleAppearanceHandler
/*     */   extends PDAbstractAppearanceHandler
/*     */ {
/*  42 */   private static final Log LOG = LogFactory.getLog(PDCircleAppearanceHandler.class);
/*     */ 
/*     */   
/*     */   public PDCircleAppearanceHandler(PDAnnotation annotation) {
/*  46 */     super(annotation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateAppearanceStreams() {
/*  52 */     generateNormalAppearance();
/*  53 */     generateRolloverAppearance();
/*  54 */     generateDownAppearance();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateNormalAppearance() {
/*  60 */     float lineWidth = getLineWidth();
/*  61 */     PDAnnotationSquareCircle annotation = (PDAnnotationSquareCircle)getAnnotation();
/*  62 */     PDAppearanceContentStream contentStream = null;
/*     */ 
/*     */     
/*     */     try {
/*  66 */       contentStream = getNormalAppearanceAsContentStream();
/*  67 */       boolean hasStroke = contentStream.setStrokingColorOnDemand(getColor());
/*     */       
/*  69 */       boolean hasBackground = contentStream.setNonStrokingColorOnDemand(annotation.getInteriorColor());
/*     */       
/*  71 */       setOpacity(contentStream, annotation.getConstantOpacity());
/*     */       
/*  73 */       contentStream.setBorderLine(lineWidth, annotation.getBorderStyle(), annotation.getBorder());
/*  74 */       PDBorderEffectDictionary borderEffect = annotation.getBorderEffect();
/*     */       
/*  76 */       if (borderEffect != null && borderEffect.getStyle().equals("C")) {
/*     */ 
/*     */         
/*  79 */         CloudyBorder cloudyBorder = new CloudyBorder(contentStream, borderEffect.getIntensity(), lineWidth, getRectangle());
/*  80 */         cloudyBorder.createCloudyEllipse(annotation.getRectDifference());
/*  81 */         annotation.setRectangle(cloudyBorder.getRectangle());
/*  82 */         annotation.setRectDifference(cloudyBorder.getRectDifference());
/*  83 */         PDAppearanceStream appearanceStream = annotation.getNormalAppearanceStream();
/*  84 */         appearanceStream.setBBox(cloudyBorder.getBBox());
/*  85 */         appearanceStream.setMatrix(cloudyBorder.getMatrix());
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/*  92 */         PDRectangle borderBox = handleBorderBox(annotation, lineWidth);
/*     */ 
/*     */         
/*  95 */         float x0 = borderBox.getLowerLeftX();
/*  96 */         float y0 = borderBox.getLowerLeftY();
/*     */         
/*  98 */         float x1 = borderBox.getUpperRightX();
/*  99 */         float y1 = borderBox.getUpperRightY();
/*     */         
/* 101 */         float xm = x0 + borderBox.getWidth() / 2.0F;
/* 102 */         float ym = y0 + borderBox.getHeight() / 2.0F;
/*     */ 
/*     */ 
/*     */         
/* 106 */         float magic = 0.55555415F;
/*     */         
/* 108 */         float vOffset = borderBox.getHeight() / 2.0F * magic;
/* 109 */         float hOffset = borderBox.getWidth() / 2.0F * magic;
/*     */         
/* 111 */         contentStream.moveTo(xm, y1);
/* 112 */         contentStream.curveTo(xm + hOffset, y1, x1, ym + vOffset, x1, ym);
/* 113 */         contentStream.curveTo(x1, ym - vOffset, xm + hOffset, y0, xm, y0);
/* 114 */         contentStream.curveTo(xm - hOffset, y0, x0, ym - vOffset, x0, ym);
/* 115 */         contentStream.curveTo(x0, ym + vOffset, xm - hOffset, y1, xm, y1);
/* 116 */         contentStream.closePath();
/*     */       } 
/*     */       
/* 119 */       contentStream.drawShape(lineWidth, hasStroke, hasBackground);
/*     */     }
/* 121 */     catch (IOException e) {
/*     */       
/* 123 */       LOG.error(e);
/*     */     } finally {
/*     */       
/* 126 */       IOUtils.closeQuietly((Closeable)contentStream);
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
/* 159 */     PDAnnotationMarkup annotation = (PDAnnotationMarkup)getAnnotation();
/*     */     
/* 161 */     PDBorderStyleDictionary bs = annotation.getBorderStyle();
/*     */     
/* 163 */     if (bs != null)
/*     */     {
/* 165 */       return bs.getWidth();
/*     */     }
/*     */     
/* 168 */     COSArray borderCharacteristics = annotation.getBorder();
/* 169 */     if (borderCharacteristics.size() >= 3) {
/*     */       
/* 171 */       COSBase base = borderCharacteristics.getObject(2);
/* 172 */       if (base instanceof COSNumber)
/*     */       {
/* 174 */         return ((COSNumber)base).floatValue();
/*     */       }
/*     */     } 
/*     */     
/* 178 */     return 1.0F;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDCircleAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */