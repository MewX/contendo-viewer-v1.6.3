/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDAppearanceContentStream;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*     */ import org.apache.pdfbox.pdmodel.font.PDType1Font;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLine;
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
/*     */ public class PDLineAppearanceHandler
/*     */   extends PDAbstractAppearanceHandler
/*     */ {
/*  37 */   private static final Log LOG = LogFactory.getLog(PDLineAppearanceHandler.class);
/*     */   
/*     */   static final int FONT_SIZE = 9;
/*     */ 
/*     */   
/*     */   public PDLineAppearanceHandler(PDAnnotation annotation) {
/*  43 */     super(annotation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateAppearanceStreams() {
/*  49 */     generateNormalAppearance();
/*  50 */     generateRolloverAppearance();
/*  51 */     generateDownAppearance();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateNormalAppearance() {
/*  57 */     PDAnnotationLine annotation = (PDAnnotationLine)getAnnotation();
/*  58 */     PDRectangle rect = annotation.getRectangle();
/*  59 */     float[] pathsArray = annotation.getLine();
/*  60 */     if (pathsArray == null) {
/*     */       return;
/*     */     }
/*     */     
/*  64 */     AnnotationBorder ab = AnnotationBorder.getAnnotationBorder((PDAnnotation)annotation, annotation.getBorderStyle());
/*  65 */     PDColor color = annotation.getColor();
/*  66 */     if (color == null || (color.getComponents()).length == 0) {
/*     */       return;
/*     */     }
/*     */     
/*  70 */     float ll = annotation.getLeaderLineLength();
/*  71 */     float lle = annotation.getLeaderLineExtensionLength();
/*  72 */     float llo = annotation.getLeaderLineOffsetLength();
/*     */ 
/*     */     
/*  75 */     float minX = Float.MAX_VALUE;
/*  76 */     float minY = Float.MAX_VALUE;
/*  77 */     float maxX = Float.MIN_VALUE;
/*  78 */     float maxY = Float.MIN_VALUE;
/*  79 */     for (int i = 0; i < pathsArray.length / 2; i++) {
/*     */       
/*  81 */       float x = pathsArray[i * 2];
/*  82 */       float y = pathsArray[i * 2 + 1];
/*  83 */       minX = Math.min(minX, x);
/*  84 */       minY = Math.min(minY, y);
/*  85 */       maxX = Math.max(maxX, x);
/*  86 */       maxY = Math.max(maxY, y);
/*     */     } 
/*     */ 
/*     */     
/*  90 */     if (ll < 0.0F) {
/*     */ 
/*     */       
/*  93 */       llo = -llo;
/*  94 */       lle = -lle;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     float lineEndingSize = (ab.width < 1.0E-5D) ? 1.0F : ab.width;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     rect.setLowerLeftX(Math.min(minX - Math.max(lineEndingSize * 10.0F, Math.abs(llo + ll + lle)), rect.getLowerLeftX()));
/* 107 */     rect.setLowerLeftY(Math.min(minY - Math.max(lineEndingSize * 10.0F, Math.abs(llo + ll + lle)), rect.getLowerLeftY()));
/* 108 */     rect.setUpperRightX(Math.max(maxX + Math.max(lineEndingSize * 10.0F, Math.abs(llo + ll + lle)), rect.getUpperRightX()));
/* 109 */     rect.setUpperRightY(Math.max(maxY + Math.max(lineEndingSize * 10.0F, Math.abs(llo + ll + lle)), rect.getUpperRightY()));
/*     */     
/* 111 */     annotation.setRectangle(rect);
/*     */     
/* 113 */     PDAppearanceContentStream cs = null;
/*     */ 
/*     */     
/*     */     try {
/* 117 */       cs = getNormalAppearanceAsContentStream();
/*     */       
/* 119 */       setOpacity(cs, annotation.getConstantOpacity());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 127 */       boolean hasStroke = cs.setStrokingColorOnDemand(color);
/*     */       
/* 129 */       if (ab.dashArray != null)
/*     */       {
/* 131 */         cs.setLineDashPattern(ab.dashArray, 0.0F);
/*     */       }
/* 133 */       cs.setLineWidth(ab.width);
/*     */       
/* 135 */       float x1 = pathsArray[0];
/* 136 */       float y1 = pathsArray[1];
/* 137 */       float x2 = pathsArray[2];
/* 138 */       float y2 = pathsArray[3];
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 143 */       float y = llo + ll;
/*     */       
/* 145 */       String contents = annotation.getContents();
/* 146 */       if (contents == null)
/*     */       {
/* 148 */         contents = "";
/*     */       }
/*     */       
/* 151 */       cs.saveGraphicsState();
/* 152 */       double angle = Math.atan2((y2 - y1), (x2 - x1));
/* 153 */       cs.transform(Matrix.getRotateInstance(angle, x1, y1));
/* 154 */       float lineLength = (float)Math.sqrt(((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
/*     */ 
/*     */       
/* 157 */       cs.moveTo(0.0F, llo);
/* 158 */       cs.lineTo(0.0F, llo + ll + lle);
/* 159 */       cs.moveTo(lineLength, llo);
/* 160 */       cs.lineTo(lineLength, llo + ll + lle);
/*     */       
/* 162 */       if (annotation.getCaption() && !contents.isEmpty()) {
/*     */         float yOffset;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 168 */         PDType1Font font = PDType1Font.HELVETICA;
/*     */ 
/*     */         
/* 171 */         float contentLength = 0.0F;
/*     */         
/*     */         try {
/* 174 */           contentLength = font.getStringWidth(annotation.getContents()) / 1000.0F * 9.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 180 */         catch (IllegalArgumentException ex) {
/*     */ 
/*     */           
/* 183 */           LOG.error("line text '" + annotation.getContents() + "' can't be shown", ex);
/*     */         } 
/* 185 */         float xOffset = (lineLength - contentLength) / 2.0F;
/*     */ 
/*     */         
/* 188 */         String captionPositioning = annotation.getCaptionPositioning();
/*     */ 
/*     */ 
/*     */         
/* 192 */         if (SHORT_STYLES.contains(annotation.getStartPointEndingStyle())) {
/*     */           
/* 194 */           cs.moveTo(lineEndingSize, y);
/*     */         }
/*     */         else {
/*     */           
/* 198 */           cs.moveTo(0.0F, y);
/*     */         } 
/* 200 */         if ("Top".equals(captionPositioning)) {
/*     */ 
/*     */           
/* 203 */           yOffset = 1.908F;
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 209 */           yOffset = -2.6F;
/*     */           
/* 211 */           cs.lineTo(xOffset - lineEndingSize, y);
/* 212 */           cs.moveTo(lineLength - xOffset + lineEndingSize, y);
/*     */         } 
/* 214 */         if (SHORT_STYLES.contains(annotation.getEndPointEndingStyle())) {
/*     */           
/* 216 */           cs.lineTo(lineLength - lineEndingSize, y);
/*     */         }
/*     */         else {
/*     */           
/* 220 */           cs.lineTo(lineLength, y);
/*     */         } 
/* 222 */         cs.drawShape(lineEndingSize, hasStroke, false);
/*     */ 
/*     */         
/* 225 */         float captionHorizontalOffset = annotation.getCaptionHorizontalOffset();
/* 226 */         float captionVerticalOffset = annotation.getCaptionVerticalOffset();
/*     */ 
/*     */         
/* 229 */         if (contentLength > 0.0F) {
/*     */           
/* 231 */           cs.beginText();
/* 232 */           cs.setFont((PDFont)font, 9.0F);
/* 233 */           cs.newLineAtOffset(xOffset + captionHorizontalOffset, y + yOffset + captionVerticalOffset);
/*     */           
/* 235 */           cs.showText(annotation.getContents());
/* 236 */           cs.endText();
/*     */         } 
/*     */         
/* 239 */         if (Float.compare(captionVerticalOffset, 0.0F) != 0)
/*     */         {
/*     */           
/* 242 */           cs.moveTo(0.0F + lineLength / 2.0F, y);
/* 243 */           cs.lineTo(0.0F + lineLength / 2.0F, y + captionVerticalOffset);
/* 244 */           cs.drawShape(lineEndingSize, hasStroke, false);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 249 */         if (SHORT_STYLES.contains(annotation.getStartPointEndingStyle())) {
/*     */           
/* 251 */           cs.moveTo(lineEndingSize, y);
/*     */         }
/*     */         else {
/*     */           
/* 255 */           cs.moveTo(0.0F, y);
/*     */         } 
/* 257 */         if (SHORT_STYLES.contains(annotation.getEndPointEndingStyle())) {
/*     */           
/* 259 */           cs.lineTo(lineLength - lineEndingSize, y);
/*     */         }
/*     */         else {
/*     */           
/* 263 */           cs.lineTo(lineLength, y);
/*     */         } 
/* 265 */         cs.drawShape(lineEndingSize, hasStroke, false);
/*     */       } 
/* 267 */       cs.restoreGraphicsState();
/*     */ 
/*     */ 
/*     */       
/* 271 */       boolean hasBackground = cs.setNonStrokingColorOnDemand(annotation.getInteriorColor());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 276 */       if (ab.width < 1.0E-5D)
/*     */       {
/* 278 */         hasStroke = false;
/*     */       }
/*     */ 
/*     */       
/* 282 */       if (!"None".equals(annotation.getStartPointEndingStyle())) {
/*     */         
/* 284 */         cs.saveGraphicsState();
/* 285 */         if (ANGLED_STYLES.contains(annotation.getStartPointEndingStyle())) {
/*     */           
/* 287 */           cs.transform(Matrix.getRotateInstance(angle, x1, y1));
/* 288 */           drawStyle(annotation.getStartPointEndingStyle(), cs, 0.0F, y, lineEndingSize, hasStroke, hasBackground, false);
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */           
/* 296 */           float xx1 = x1 - (float)(y * Math.sin(angle));
/* 297 */           float yy1 = y1 + (float)(y * Math.cos(angle));
/* 298 */           drawStyle(annotation.getStartPointEndingStyle(), cs, xx1, yy1, lineEndingSize, hasStroke, hasBackground, false);
/*     */         } 
/* 300 */         cs.restoreGraphicsState();
/*     */       } 
/*     */ 
/*     */       
/* 304 */       if (!"None".equals(annotation.getEndPointEndingStyle()))
/*     */       {
/*     */         
/* 307 */         if (ANGLED_STYLES.contains(annotation.getEndPointEndingStyle()))
/*     */         {
/* 309 */           cs.transform(Matrix.getRotateInstance(angle, x2, y2));
/* 310 */           drawStyle(annotation.getEndPointEndingStyle(), cs, 0.0F, y, lineEndingSize, hasStroke, hasBackground, true);
/*     */ 
/*     */         
/*     */         }
/*     */         else
/*     */         {
/*     */ 
/*     */           
/* 318 */           float xx2 = x2 - (float)(y * Math.sin(angle));
/* 319 */           float yy2 = y2 + (float)(y * Math.cos(angle));
/* 320 */           drawStyle(annotation.getEndPointEndingStyle(), cs, xx2, yy2, lineEndingSize, hasStroke, hasBackground, true);
/*     */         }
/*     */       
/*     */       }
/* 324 */     } catch (IOException ex) {
/*     */       
/* 326 */       LOG.error(ex);
/*     */     } finally {
/*     */       
/* 329 */       IOUtils.closeQuietly((Closeable)cs);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void generateRolloverAppearance() {}
/*     */   
/*     */   public void generateDownAppearance() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDLineAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */