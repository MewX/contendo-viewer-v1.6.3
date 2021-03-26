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
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
/*     */ import org.apache.pdfbox.util.Matrix;
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
/*     */ public class PDPolylineAppearanceHandler
/*     */   extends PDAbstractAppearanceHandler
/*     */ {
/*  42 */   private static final Log LOG = LogFactory.getLog(PDPolylineAppearanceHandler.class);
/*     */ 
/*     */   
/*     */   public PDPolylineAppearanceHandler(PDAnnotation annotation) {
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
/*  60 */     PDAnnotationMarkup annotation = (PDAnnotationMarkup)getAnnotation();
/*  61 */     PDRectangle rect = annotation.getRectangle();
/*  62 */     float[] pathsArray = annotation.getVertices();
/*  63 */     if (pathsArray == null || pathsArray.length < 4) {
/*     */       return;
/*     */     }
/*     */     
/*  67 */     AnnotationBorder ab = AnnotationBorder.getAnnotationBorder((PDAnnotation)annotation, annotation.getBorderStyle());
/*  68 */     PDColor color = annotation.getColor();
/*  69 */     if (color == null || (color.getComponents()).length == 0 || Float.compare(ab.width, 0.0F) == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     float minX = Float.MAX_VALUE;
/*  78 */     float minY = Float.MAX_VALUE;
/*  79 */     float maxX = Float.MIN_VALUE;
/*  80 */     float maxY = Float.MIN_VALUE;
/*  81 */     for (int i = 0; i < pathsArray.length / 2; i++) {
/*     */       
/*  83 */       float x = pathsArray[i * 2];
/*  84 */       float y = pathsArray[i * 2 + 1];
/*  85 */       minX = Math.min(minX, x);
/*  86 */       minY = Math.min(minY, y);
/*  87 */       maxX = Math.max(maxX, x);
/*  88 */       maxY = Math.max(maxY, y);
/*     */     } 
/*     */     
/*  91 */     rect.setLowerLeftX(Math.min(minX - ab.width * 10.0F, rect.getLowerLeftX()));
/*  92 */     rect.setLowerLeftY(Math.min(minY - ab.width * 10.0F, rect.getLowerLeftY()));
/*  93 */     rect.setUpperRightX(Math.max(maxX + ab.width * 10.0F, rect.getUpperRightX()));
/*  94 */     rect.setUpperRightY(Math.max(maxY + ab.width * 10.0F, rect.getUpperRightY()));
/*  95 */     annotation.setRectangle(rect);
/*     */     
/*  97 */     PDAppearanceContentStream cs = null;
/*     */ 
/*     */     
/*     */     try {
/* 101 */       cs = getNormalAppearanceAsContentStream();
/*     */       
/* 103 */       boolean hasBackground = cs.setNonStrokingColorOnDemand(annotation.getInteriorColor());
/* 104 */       setOpacity(cs, annotation.getConstantOpacity());
/* 105 */       boolean hasStroke = cs.setStrokingColorOnDemand(color);
/*     */       
/* 107 */       if (ab.dashArray != null)
/*     */       {
/* 109 */         cs.setLineDashPattern(ab.dashArray, 0.0F);
/*     */       }
/* 111 */       cs.setLineWidth(ab.width);
/*     */       
/* 113 */       for (int j = 0; j < pathsArray.length / 2; j++) {
/*     */         
/* 115 */         float x = pathsArray[j * 2];
/* 116 */         float y = pathsArray[j * 2 + 1];
/* 117 */         if (j == 0) {
/*     */           
/* 119 */           if (SHORT_STYLES.contains(annotation.getStartPointEndingStyle())) {
/*     */ 
/*     */ 
/*     */             
/* 123 */             float x1 = pathsArray[2];
/* 124 */             float y1 = pathsArray[3];
/* 125 */             float len = (float)Math.sqrt(Math.pow((x - x1), 2.0D) + Math.pow((y - y1), 2.0D));
/* 126 */             if (Float.compare(len, 0.0F) != 0) {
/*     */               
/* 128 */               x += (x1 - x) / len * ab.width;
/* 129 */               y += (y1 - y) / len * ab.width;
/*     */             } 
/*     */           } 
/* 132 */           cs.moveTo(x, y);
/*     */         }
/*     */         else {
/*     */           
/* 136 */           if (j == pathsArray.length / 2 - 1 && SHORT_STYLES
/* 137 */             .contains(annotation.getEndPointEndingStyle())) {
/*     */ 
/*     */ 
/*     */             
/* 141 */             float x0 = pathsArray[pathsArray.length - 4];
/* 142 */             float y0 = pathsArray[pathsArray.length - 3];
/* 143 */             float len = (float)Math.sqrt(Math.pow((x0 - x), 2.0D) + Math.pow((y0 - y), 2.0D));
/* 144 */             if (Float.compare(len, 0.0F) != 0) {
/*     */               
/* 146 */               x -= (x - x0) / len * ab.width;
/* 147 */               y -= (y - y0) / len * ab.width;
/*     */             } 
/*     */           } 
/* 150 */           cs.lineTo(x, y);
/*     */         } 
/*     */       } 
/* 153 */       cs.stroke();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 160 */       if (!"None".equals(annotation.getStartPointEndingStyle())) {
/*     */ 
/*     */         
/* 163 */         float x2 = pathsArray[2];
/* 164 */         float y2 = pathsArray[3];
/* 165 */         float x1 = pathsArray[0];
/* 166 */         float y1 = pathsArray[1];
/* 167 */         cs.saveGraphicsState();
/* 168 */         if (ANGLED_STYLES.contains(annotation.getStartPointEndingStyle())) {
/*     */           
/* 170 */           double angle = Math.atan2((y2 - y1), (x2 - x1));
/* 171 */           cs.transform(Matrix.getRotateInstance(angle, x1, y1));
/*     */         }
/*     */         else {
/*     */           
/* 175 */           cs.transform(Matrix.getTranslateInstance(x1, y1));
/*     */         } 
/* 177 */         drawStyle(annotation.getStartPointEndingStyle(), cs, 0.0F, 0.0F, ab.width, hasStroke, hasBackground, false);
/* 178 */         cs.restoreGraphicsState();
/*     */       } 
/*     */       
/* 181 */       if (!"None".equals(annotation.getEndPointEndingStyle()))
/*     */       {
/*     */         
/* 184 */         float x1 = pathsArray[pathsArray.length - 4];
/* 185 */         float y1 = pathsArray[pathsArray.length - 3];
/* 186 */         float x2 = pathsArray[pathsArray.length - 2];
/* 187 */         float y2 = pathsArray[pathsArray.length - 1];
/*     */         
/* 189 */         if (ANGLED_STYLES.contains(annotation.getEndPointEndingStyle())) {
/*     */           
/* 191 */           double angle = Math.atan2((y2 - y1), (x2 - x1));
/* 192 */           cs.transform(Matrix.getRotateInstance(angle, x2, y2));
/*     */         }
/*     */         else {
/*     */           
/* 196 */           cs.transform(Matrix.getTranslateInstance(x2, y2));
/*     */         } 
/* 198 */         drawStyle(annotation.getEndPointEndingStyle(), cs, 0.0F, 0.0F, ab.width, hasStroke, hasBackground, true);
/*     */       }
/*     */     
/* 201 */     } catch (IOException ex) {
/*     */       
/* 203 */       LOG.error(ex);
/*     */     }
/*     */     finally {
/*     */       
/* 207 */       IOUtils.closeQuietly((Closeable)cs);
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
/* 242 */     PDAnnotationMarkup annotation = (PDAnnotationMarkup)getAnnotation();
/*     */     
/* 244 */     PDBorderStyleDictionary bs = annotation.getBorderStyle();
/*     */     
/* 246 */     if (bs != null)
/*     */     {
/* 248 */       return bs.getWidth();
/*     */     }
/*     */     
/* 251 */     COSArray borderCharacteristics = annotation.getBorder();
/* 252 */     if (borderCharacteristics.size() >= 3) {
/*     */       
/* 254 */       COSBase base = borderCharacteristics.getObject(2);
/* 255 */       if (base instanceof COSNumber)
/*     */       {
/* 257 */         return ((COSNumber)base).floatValue();
/*     */       }
/*     */     } 
/*     */     
/* 261 */     return 1.0F;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDPolylineAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */