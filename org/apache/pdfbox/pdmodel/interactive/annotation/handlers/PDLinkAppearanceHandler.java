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
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
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
/*     */ public class PDLinkAppearanceHandler
/*     */   extends PDAbstractAppearanceHandler
/*     */ {
/*  42 */   private static final Log LOG = LogFactory.getLog(PDLinkAppearanceHandler.class);
/*     */ 
/*     */   
/*     */   public PDLinkAppearanceHandler(PDAnnotation annotation) {
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
/*  60 */     PDAnnotationLink annotation = (PDAnnotationLink)getAnnotation();
/*  61 */     if (annotation.getRectangle() == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  68 */     float lineWidth = getLineWidth();
/*     */     
/*  70 */     PDAppearanceContentStream contentStream = null;
/*     */ 
/*     */     
/*     */     try {
/*  74 */       contentStream = getNormalAppearanceAsContentStream();
/*     */       
/*  76 */       PDColor color = annotation.getColor();
/*  77 */       if (color == null)
/*     */       {
/*     */         
/*  80 */         color = new PDColor(new float[] { 0.0F }, (PDColorSpace)PDDeviceGray.INSTANCE);
/*     */       }
/*  82 */       boolean hasStroke = contentStream.setStrokingColorOnDemand(color);
/*     */       
/*  84 */       contentStream.setBorderLine(lineWidth, annotation.getBorderStyle(), annotation.getBorder());
/*     */ 
/*     */ 
/*     */       
/*  88 */       PDRectangle borderEdge = getPaddedRectangle(getRectangle(), lineWidth / 2.0F);
/*     */       
/*  90 */       float[] pathsArray = annotation.getQuadPoints();
/*     */       
/*  92 */       if (pathsArray != null) {
/*     */ 
/*     */ 
/*     */         
/*  96 */         PDRectangle rect = annotation.getRectangle();
/*  97 */         for (int i = 0; i < pathsArray.length / 2; i++) {
/*     */           
/*  99 */           if (!rect.contains(pathsArray[i * 2], pathsArray[i * 2 + 1])) {
/*     */             
/* 101 */             LOG.warn("At least one /QuadPoints entry (" + pathsArray[i * 2] + ";" + pathsArray[i * 2 + 1] + ") is outside of rectangle, " + rect + ", /QuadPoints are ignored and /Rect is used instead");
/*     */ 
/*     */ 
/*     */             
/* 105 */             pathsArray = null;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 111 */       if (pathsArray == null) {
/*     */ 
/*     */         
/* 114 */         pathsArray = new float[8];
/* 115 */         pathsArray[0] = borderEdge.getLowerLeftX();
/* 116 */         pathsArray[1] = borderEdge.getLowerLeftY();
/* 117 */         pathsArray[2] = borderEdge.getUpperRightX();
/* 118 */         pathsArray[3] = borderEdge.getLowerLeftY();
/* 119 */         pathsArray[4] = borderEdge.getUpperRightX();
/* 120 */         pathsArray[5] = borderEdge.getUpperRightY();
/* 121 */         pathsArray[6] = borderEdge.getLowerLeftX();
/* 122 */         pathsArray[7] = borderEdge.getUpperRightY();
/*     */       } 
/*     */       
/* 125 */       int of = 0;
/* 126 */       while (of + 7 < pathsArray.length) {
/*     */         
/* 128 */         if (annotation.getBorderStyle() != null && annotation
/* 129 */           .getBorderStyle().getStyle().equals("U")) {
/*     */           
/* 131 */           contentStream.moveTo(pathsArray[of], pathsArray[of + 1]);
/* 132 */           contentStream.lineTo(pathsArray[of + 2], pathsArray[of + 3]);
/*     */         }
/*     */         else {
/*     */           
/* 136 */           contentStream.moveTo(pathsArray[of], pathsArray[of + 1]);
/* 137 */           contentStream.lineTo(pathsArray[of + 2], pathsArray[of + 3]);
/* 138 */           contentStream.lineTo(pathsArray[of + 4], pathsArray[of + 5]);
/* 139 */           contentStream.lineTo(pathsArray[of + 6], pathsArray[of + 7]);
/* 140 */           contentStream.closePath();
/*     */         } 
/* 142 */         of += 8;
/*     */       } 
/*     */       
/* 145 */       contentStream.drawShape(lineWidth, hasStroke, false);
/*     */     }
/* 147 */     catch (IOException e) {
/*     */       
/* 149 */       LOG.error(e);
/*     */     } finally {
/*     */       
/* 152 */       IOUtils.closeQuietly((Closeable)contentStream);
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
/* 185 */     PDAnnotationLink annotation = (PDAnnotationLink)getAnnotation();
/*     */     
/* 187 */     PDBorderStyleDictionary bs = annotation.getBorderStyle();
/*     */     
/* 189 */     if (bs != null)
/*     */     {
/* 191 */       return bs.getWidth();
/*     */     }
/*     */     
/* 194 */     COSArray borderCharacteristics = annotation.getBorder();
/* 195 */     if (borderCharacteristics.size() >= 3) {
/*     */       
/* 197 */       COSBase base = borderCharacteristics.getObject(2);
/* 198 */       if (base instanceof COSNumber)
/*     */       {
/* 200 */         return ((COSNumber)base).floatValue();
/*     */       }
/*     */     } 
/*     */     
/* 204 */     return 1.0F;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDLinkAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */