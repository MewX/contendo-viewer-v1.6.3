/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDAppearanceContentStream;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.font.PDType1Font;
/*     */ import org.apache.pdfbox.pdmodel.graphics.blend.BlendMode;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationText;
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
/*     */ public class PDTextAppearanceHandler
/*     */   extends PDAbstractAppearanceHandler
/*     */ {
/*  44 */   private static final Log LOG = LogFactory.getLog(PDTextAppearanceHandler.class);
/*     */   
/*  46 */   private static final Set<String> SUPPORTED_NAMES = new HashSet<String>();
/*     */ 
/*     */   
/*     */   static {
/*  50 */     SUPPORTED_NAMES.add("Note");
/*  51 */     SUPPORTED_NAMES.add("Insert");
/*  52 */     SUPPORTED_NAMES.add("Cross");
/*  53 */     SUPPORTED_NAMES.add("Help");
/*  54 */     SUPPORTED_NAMES.add("Circle");
/*  55 */     SUPPORTED_NAMES.add("Paragraph");
/*  56 */     SUPPORTED_NAMES.add("NewParagraph");
/*  57 */     SUPPORTED_NAMES.add("Check");
/*  58 */     SUPPORTED_NAMES.add("Star");
/*  59 */     SUPPORTED_NAMES.add("RightArrow");
/*  60 */     SUPPORTED_NAMES.add("RightPointer");
/*  61 */     SUPPORTED_NAMES.add("CrossHairs");
/*  62 */     SUPPORTED_NAMES.add("UpArrow");
/*  63 */     SUPPORTED_NAMES.add("UpLeftArrow");
/*  64 */     SUPPORTED_NAMES.add("Comment");
/*  65 */     SUPPORTED_NAMES.add("Key");
/*     */   }
/*     */ 
/*     */   
/*     */   public PDTextAppearanceHandler(PDAnnotation annotation) {
/*  70 */     super(annotation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateAppearanceStreams() {
/*  76 */     generateNormalAppearance();
/*  77 */     generateRolloverAppearance();
/*  78 */     generateDownAppearance();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateNormalAppearance() {
/*  84 */     PDAnnotationText annotation = (PDAnnotationText)getAnnotation();
/*  85 */     if (!SUPPORTED_NAMES.contains(annotation.getName())) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  90 */     PDAppearanceContentStream contentStream = null;
/*     */ 
/*     */     
/*     */     try {
/*  94 */       contentStream = getNormalAppearanceAsContentStream();
/*     */       
/*  96 */       PDColor bgColor = getColor();
/*  97 */       if (bgColor == null) {
/*     */ 
/*     */         
/* 100 */         contentStream.setNonStrokingColor(1.0F);
/*     */       }
/*     */       else {
/*     */         
/* 104 */         contentStream.setNonStrokingColor(bgColor);
/*     */       } 
/*     */ 
/*     */       
/* 108 */       setOpacity(contentStream, annotation.getConstantOpacity());
/*     */       
/* 110 */       String annotationTypeName = annotation.getName();
/*     */       
/* 112 */       if ("Note".equals(annotationTypeName))
/*     */       {
/* 114 */         drawNote(annotation, contentStream);
/*     */       }
/* 116 */       else if ("Cross".equals(annotationTypeName))
/*     */       {
/* 118 */         drawCross(annotation, contentStream);
/*     */       }
/* 120 */       else if ("Circle".equals(annotationTypeName))
/*     */       {
/* 122 */         drawCircles(annotation, contentStream);
/*     */       }
/* 124 */       else if ("Insert".equals(annotationTypeName))
/*     */       {
/* 126 */         drawInsert(annotation, contentStream);
/*     */       }
/* 128 */       else if ("Help".equals(annotationTypeName))
/*     */       {
/* 130 */         drawHelp(annotation, contentStream);
/*     */       }
/* 132 */       else if ("Paragraph".equals(annotationTypeName))
/*     */       {
/* 134 */         drawParagraph(annotation, contentStream);
/*     */       }
/* 136 */       else if ("NewParagraph".equals(annotationTypeName))
/*     */       {
/* 138 */         drawNewParagraph(annotation, contentStream);
/*     */       }
/* 140 */       else if ("Star".equals(annotationTypeName))
/*     */       {
/* 142 */         drawStar(annotation, contentStream);
/*     */       }
/* 144 */       else if ("Check".equals(annotationTypeName))
/*     */       {
/* 146 */         drawCheck(annotation, contentStream);
/*     */       }
/* 148 */       else if ("RightArrow".equals(annotationTypeName))
/*     */       {
/* 150 */         drawRightArrow(annotation, contentStream);
/*     */       }
/* 152 */       else if ("RightPointer".equals(annotationTypeName))
/*     */       {
/* 154 */         drawRightPointer(annotation, contentStream);
/*     */       }
/* 156 */       else if ("CrossHairs".equals(annotationTypeName))
/*     */       {
/* 158 */         drawCrossHairs(annotation, contentStream);
/*     */       }
/* 160 */       else if ("UpArrow".equals(annotationTypeName))
/*     */       {
/* 162 */         drawUpArrow(annotation, contentStream);
/*     */       }
/* 164 */       else if ("UpLeftArrow".equals(annotationTypeName))
/*     */       {
/* 166 */         drawUpLeftArrow(annotation, contentStream);
/*     */       }
/* 168 */       else if ("Comment".equals(annotationTypeName))
/*     */       {
/* 170 */         drawComment(annotation, contentStream);
/*     */       }
/* 172 */       else if ("Key".equals(annotationTypeName))
/*     */       {
/* 174 */         drawKey(annotation, contentStream);
/*     */       }
/*     */     
/* 177 */     } catch (IOException e) {
/*     */       
/* 179 */       LOG.error(e);
/*     */     }
/*     */     finally {
/*     */       
/* 183 */       IOUtils.closeQuietly((Closeable)contentStream);
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
/*     */   private PDRectangle adjustRectAndBBox(PDAnnotationText annotation, float width, float height) {
/* 196 */     PDRectangle rect = getRectangle();
/*     */     
/* 198 */     if (!annotation.isNoZoom()) {
/*     */       
/* 200 */       rect.setUpperRightX(rect.getLowerLeftX() + width);
/* 201 */       rect.setLowerLeftY(rect.getUpperRightY() - height);
/* 202 */       annotation.setRectangle(rect);
/*     */     } 
/* 204 */     if (!annotation.getCOSObject().containsKey(COSName.F)) {
/*     */ 
/*     */       
/* 207 */       annotation.setNoRotate(true);
/* 208 */       annotation.setNoZoom(true);
/*     */     } 
/* 210 */     PDRectangle bbox = new PDRectangle(width, height);
/* 211 */     annotation.getNormalAppearanceStream().setBBox(bbox);
/* 212 */     return bbox;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawNote(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 218 */     PDRectangle bbox = adjustRectAndBBox(annotation, 18.0F, 20.0F);
/* 219 */     contentStream.setMiterLimit(4.0F);
/*     */ 
/*     */     
/* 222 */     contentStream.setLineJoinStyle(1);
/*     */     
/* 224 */     contentStream.setLineCapStyle(0);
/* 225 */     contentStream.setLineWidth(0.61F);
/* 226 */     contentStream.addRect(1.0F, 1.0F, bbox.getWidth() - 2.0F, bbox.getHeight() - 2.0F);
/* 227 */     contentStream.moveTo(bbox.getWidth() / 4.0F, bbox.getHeight() / 7.0F * 2.0F);
/* 228 */     contentStream.lineTo(bbox.getWidth() * 3.0F / 4.0F - 1.0F, bbox.getHeight() / 7.0F * 2.0F);
/* 229 */     contentStream.moveTo(bbox.getWidth() / 4.0F, bbox.getHeight() / 7.0F * 3.0F);
/* 230 */     contentStream.lineTo(bbox.getWidth() * 3.0F / 4.0F - 1.0F, bbox.getHeight() / 7.0F * 3.0F);
/* 231 */     contentStream.moveTo(bbox.getWidth() / 4.0F, bbox.getHeight() / 7.0F * 4.0F);
/* 232 */     contentStream.lineTo(bbox.getWidth() * 3.0F / 4.0F - 1.0F, bbox.getHeight() / 7.0F * 4.0F);
/* 233 */     contentStream.moveTo(bbox.getWidth() / 4.0F, bbox.getHeight() / 7.0F * 5.0F);
/* 234 */     contentStream.lineTo(bbox.getWidth() * 3.0F / 4.0F - 1.0F, bbox.getHeight() / 7.0F * 5.0F);
/* 235 */     contentStream.fillAndStroke();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawCircles(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 241 */     PDRectangle bbox = adjustRectAndBBox(annotation, 20.0F, 20.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 251 */     float smallR = 6.36F;
/* 252 */     float largeR = 9.756F;
/*     */     
/* 254 */     contentStream.setMiterLimit(4.0F);
/* 255 */     contentStream.setLineJoinStyle(1);
/* 256 */     contentStream.setLineCapStyle(0);
/* 257 */     contentStream.saveGraphicsState();
/* 258 */     contentStream.setLineWidth(1.0F);
/* 259 */     PDExtendedGraphicsState gs = new PDExtendedGraphicsState();
/* 260 */     gs.setAlphaSourceFlag(false);
/* 261 */     gs.setStrokingAlphaConstant(Float.valueOf(0.6F));
/* 262 */     gs.setNonStrokingAlphaConstant(Float.valueOf(0.6F));
/* 263 */     gs.setBlendMode((BlendMode)BlendMode.NORMAL);
/* 264 */     contentStream.setGraphicsStateParameters(gs);
/* 265 */     contentStream.setNonStrokingColor(1.0F);
/* 266 */     drawCircle(contentStream, bbox.getWidth() / 2.0F, bbox.getHeight() / 2.0F, smallR);
/* 267 */     contentStream.fill();
/* 268 */     contentStream.restoreGraphicsState();
/*     */     
/* 270 */     contentStream.setLineWidth(0.59F);
/* 271 */     drawCircle(contentStream, bbox.getWidth() / 2.0F, bbox.getHeight() / 2.0F, smallR);
/* 272 */     drawCircle2(contentStream, bbox.getWidth() / 2.0F, bbox.getHeight() / 2.0F, largeR);
/* 273 */     contentStream.fillAndStroke();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawInsert(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 279 */     PDRectangle bbox = adjustRectAndBBox(annotation, 17.0F, 20.0F);
/*     */     
/* 281 */     contentStream.setMiterLimit(4.0F);
/* 282 */     contentStream.setLineJoinStyle(0);
/* 283 */     contentStream.setLineCapStyle(0);
/* 284 */     contentStream.setLineWidth(0.59F);
/* 285 */     contentStream.moveTo(bbox.getWidth() / 2.0F - 1.0F, bbox.getHeight() - 2.0F);
/* 286 */     contentStream.lineTo(1.0F, 1.0F);
/* 287 */     contentStream.lineTo(bbox.getWidth() - 2.0F, 1.0F);
/* 288 */     contentStream.closeAndFillAndStroke();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawCross(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 294 */     PDRectangle bbox = adjustRectAndBBox(annotation, 19.0F, 19.0F);
/*     */ 
/*     */     
/* 297 */     float min = Math.min(bbox.getWidth(), bbox.getHeight());
/*     */ 
/*     */ 
/*     */     
/* 301 */     float small = min / 10.0F;
/* 302 */     float large = min / 5.0F;
/*     */     
/* 304 */     contentStream.setMiterLimit(4.0F);
/* 305 */     contentStream.setLineJoinStyle(1);
/* 306 */     contentStream.setLineCapStyle(0);
/* 307 */     contentStream.setLineWidth(0.59F);
/*     */     
/* 309 */     contentStream.moveTo(small, large);
/* 310 */     contentStream.lineTo(large, small);
/* 311 */     contentStream.lineTo(min / 2.0F, min / 2.0F - small);
/* 312 */     contentStream.lineTo(min - large, small);
/* 313 */     contentStream.lineTo(min - small, large);
/* 314 */     contentStream.lineTo(min / 2.0F + small, min / 2.0F);
/* 315 */     contentStream.lineTo(min - small, min - large);
/* 316 */     contentStream.lineTo(min - large, min - small);
/* 317 */     contentStream.lineTo(min / 2.0F, min / 2.0F + small);
/* 318 */     contentStream.lineTo(large, min - small);
/* 319 */     contentStream.lineTo(small, min - large);
/* 320 */     contentStream.lineTo(min / 2.0F - small, min / 2.0F);
/* 321 */     contentStream.closeAndFillAndStroke();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawHelp(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 330 */     PDRectangle bbox = adjustRectAndBBox(annotation, 20.0F, 20.0F);
/*     */     
/* 332 */     float min = Math.min(bbox.getWidth(), bbox.getHeight());
/*     */     
/* 334 */     contentStream.setMiterLimit(4.0F);
/* 335 */     contentStream.setLineJoinStyle(1);
/* 336 */     contentStream.setLineCapStyle(0);
/* 337 */     contentStream.setLineWidth(0.59F);
/*     */ 
/*     */     
/* 340 */     contentStream.saveGraphicsState();
/* 341 */     contentStream.setLineWidth(1.0F);
/* 342 */     PDExtendedGraphicsState gs = new PDExtendedGraphicsState();
/* 343 */     gs.setAlphaSourceFlag(false);
/* 344 */     gs.setStrokingAlphaConstant(Float.valueOf(0.6F));
/* 345 */     gs.setNonStrokingAlphaConstant(Float.valueOf(0.6F));
/* 346 */     gs.setBlendMode((BlendMode)BlendMode.NORMAL);
/* 347 */     contentStream.setGraphicsStateParameters(gs);
/* 348 */     contentStream.setNonStrokingColor(1.0F);
/* 349 */     drawCircle2(contentStream, min / 2.0F, min / 2.0F, min / 2.0F - 1.0F);
/* 350 */     contentStream.fill();
/* 351 */     contentStream.restoreGraphicsState();
/*     */     
/* 353 */     contentStream.saveGraphicsState();
/*     */ 
/*     */     
/* 356 */     contentStream.transform(Matrix.getScaleInstance(0.001F * min / 2.25F, 0.001F * min / 2.25F));
/* 357 */     contentStream.transform(Matrix.getTranslateInstance(500.0F, 375.0F));
/*     */ 
/*     */ 
/*     */     
/* 361 */     GeneralPath path = PDType1Font.HELVETICA_BOLD.getPath("question");
/* 362 */     addPath(contentStream, path);
/* 363 */     contentStream.restoreGraphicsState();
/*     */     
/* 365 */     drawCircle2(contentStream, min / 2.0F, min / 2.0F, min / 2.0F - 1.0F);
/* 366 */     contentStream.fillAndStroke();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawParagraph(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 372 */     PDRectangle bbox = adjustRectAndBBox(annotation, 20.0F, 20.0F);
/*     */     
/* 374 */     float min = Math.min(bbox.getWidth(), bbox.getHeight());
/*     */     
/* 376 */     contentStream.setMiterLimit(4.0F);
/* 377 */     contentStream.setLineJoinStyle(1);
/* 378 */     contentStream.setLineCapStyle(0);
/* 379 */     contentStream.setLineWidth(0.59F);
/*     */ 
/*     */     
/* 382 */     contentStream.saveGraphicsState();
/* 383 */     contentStream.setLineWidth(1.0F);
/* 384 */     PDExtendedGraphicsState gs = new PDExtendedGraphicsState();
/* 385 */     gs.setAlphaSourceFlag(false);
/* 386 */     gs.setStrokingAlphaConstant(Float.valueOf(0.6F));
/* 387 */     gs.setNonStrokingAlphaConstant(Float.valueOf(0.6F));
/* 388 */     gs.setBlendMode((BlendMode)BlendMode.NORMAL);
/* 389 */     contentStream.setGraphicsStateParameters(gs);
/* 390 */     contentStream.setNonStrokingColor(1.0F);
/* 391 */     drawCircle2(contentStream, min / 2.0F, min / 2.0F, min / 2.0F - 1.0F);
/* 392 */     contentStream.fill();
/* 393 */     contentStream.restoreGraphicsState();
/*     */     
/* 395 */     contentStream.saveGraphicsState();
/*     */ 
/*     */     
/* 398 */     contentStream.transform(Matrix.getScaleInstance(0.001F * min / 3.0F, 0.001F * min / 3.0F));
/* 399 */     contentStream.transform(Matrix.getTranslateInstance(850.0F, 900.0F));
/*     */ 
/*     */ 
/*     */     
/* 403 */     GeneralPath path = PDType1Font.HELVETICA.getPath("paragraph");
/* 404 */     addPath(contentStream, path);
/* 405 */     contentStream.restoreGraphicsState();
/* 406 */     contentStream.fillAndStroke();
/* 407 */     drawCircle(contentStream, min / 2.0F, min / 2.0F, min / 2.0F - 1.0F);
/* 408 */     contentStream.stroke();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawNewParagraph(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 414 */     adjustRectAndBBox(annotation, 13.0F, 20.0F);
/*     */     
/* 416 */     contentStream.setMiterLimit(4.0F);
/* 417 */     contentStream.setLineJoinStyle(0);
/* 418 */     contentStream.setLineCapStyle(0);
/* 419 */     contentStream.setLineWidth(0.59F);
/*     */ 
/*     */     
/* 422 */     contentStream.moveTo(6.4995F, 20.0F);
/* 423 */     contentStream.lineTo(0.295F, 7.287F);
/* 424 */     contentStream.lineTo(12.705F, 7.287F);
/* 425 */     contentStream.closeAndFillAndStroke();
/*     */ 
/*     */ 
/*     */     
/* 429 */     contentStream.transform(Matrix.getScaleInstance(0.004F, 0.004F));
/* 430 */     contentStream.transform(Matrix.getTranslateInstance(200.0F, 0.0F));
/* 431 */     addPath(contentStream, PDType1Font.HELVETICA_BOLD.getPath("N"));
/* 432 */     contentStream.transform(Matrix.getTranslateInstance(1300.0F, 0.0F));
/* 433 */     addPath(contentStream, PDType1Font.HELVETICA_BOLD.getPath("P"));
/* 434 */     contentStream.fill();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawStar(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 440 */     PDRectangle bbox = adjustRectAndBBox(annotation, 20.0F, 19.0F);
/*     */     
/* 442 */     float min = Math.min(bbox.getWidth(), bbox.getHeight());
/*     */     
/* 444 */     contentStream.setMiterLimit(4.0F);
/* 445 */     contentStream.setLineJoinStyle(1);
/* 446 */     contentStream.setLineCapStyle(0);
/* 447 */     contentStream.setLineWidth(0.59F);
/*     */     
/* 449 */     contentStream.transform(Matrix.getScaleInstance(0.001F * min / 0.8F, 0.001F * min / 0.8F));
/*     */ 
/*     */ 
/*     */     
/* 453 */     GeneralPath path = PDType1Font.ZAPF_DINGBATS.getPath("a35");
/* 454 */     addPath(contentStream, path);
/* 455 */     contentStream.fillAndStroke();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawCheck(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 464 */     PDRectangle bbox = adjustRectAndBBox(annotation, 20.0F, 19.0F);
/*     */     
/* 466 */     float min = Math.min(bbox.getWidth(), bbox.getHeight());
/*     */     
/* 468 */     contentStream.setMiterLimit(4.0F);
/* 469 */     contentStream.setLineJoinStyle(1);
/* 470 */     contentStream.setLineCapStyle(0);
/* 471 */     contentStream.setLineWidth(0.59F);
/*     */     
/* 473 */     contentStream.transform(Matrix.getScaleInstance(0.001F * min / 0.8F, 0.001F * min / 0.8F));
/* 474 */     contentStream.transform(Matrix.getTranslateInstance(0.0F, 50.0F));
/*     */ 
/*     */ 
/*     */     
/* 478 */     GeneralPath path = PDType1Font.ZAPF_DINGBATS.getPath("a20");
/* 479 */     addPath(contentStream, path);
/* 480 */     contentStream.fillAndStroke();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawRightPointer(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 487 */     PDRectangle bbox = adjustRectAndBBox(annotation, 20.0F, 17.0F);
/*     */     
/* 489 */     float min = Math.min(bbox.getWidth(), bbox.getHeight());
/*     */     
/* 491 */     contentStream.setMiterLimit(4.0F);
/* 492 */     contentStream.setLineJoinStyle(1);
/* 493 */     contentStream.setLineCapStyle(0);
/* 494 */     contentStream.setLineWidth(0.59F);
/*     */     
/* 496 */     contentStream.transform(Matrix.getScaleInstance(0.001F * min / 0.8F, 0.001F * min / 0.8F));
/* 497 */     contentStream.transform(Matrix.getTranslateInstance(0.0F, 50.0F));
/*     */ 
/*     */ 
/*     */     
/* 501 */     GeneralPath path = PDType1Font.ZAPF_DINGBATS.getPath("a174");
/* 502 */     addPath(contentStream, path);
/* 503 */     contentStream.fillAndStroke();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawCrossHairs(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 509 */     PDRectangle bbox = adjustRectAndBBox(annotation, 20.0F, 20.0F);
/*     */     
/* 511 */     float min = Math.min(bbox.getWidth(), bbox.getHeight());
/*     */     
/* 513 */     contentStream.setMiterLimit(4.0F);
/* 514 */     contentStream.setLineJoinStyle(0);
/* 515 */     contentStream.setLineCapStyle(0);
/* 516 */     contentStream.setLineWidth(0.61F);
/*     */     
/* 518 */     contentStream.transform(Matrix.getScaleInstance(0.001F * min / 1.5F, 0.001F * min / 1.5F));
/* 519 */     contentStream.transform(Matrix.getTranslateInstance(0.0F, 50.0F));
/*     */ 
/*     */ 
/*     */     
/* 523 */     GeneralPath path = PDType1Font.SYMBOL.getPath("circleplus");
/* 524 */     addPath(contentStream, path);
/* 525 */     contentStream.fillAndStroke();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawUpArrow(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 531 */     adjustRectAndBBox(annotation, 17.0F, 20.0F);
/*     */     
/* 533 */     contentStream.setMiterLimit(4.0F);
/* 534 */     contentStream.setLineJoinStyle(1);
/* 535 */     contentStream.setLineCapStyle(0);
/* 536 */     contentStream.setLineWidth(0.59F);
/*     */     
/* 538 */     contentStream.moveTo(1.0F, 7.0F);
/* 539 */     contentStream.lineTo(5.0F, 7.0F);
/* 540 */     contentStream.lineTo(5.0F, 1.0F);
/* 541 */     contentStream.lineTo(12.0F, 1.0F);
/* 542 */     contentStream.lineTo(12.0F, 7.0F);
/* 543 */     contentStream.lineTo(16.0F, 7.0F);
/* 544 */     contentStream.lineTo(8.5F, 19.0F);
/* 545 */     contentStream.closeAndFillAndStroke();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawUpLeftArrow(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 551 */     adjustRectAndBBox(annotation, 17.0F, 17.0F);
/*     */     
/* 553 */     contentStream.setMiterLimit(4.0F);
/* 554 */     contentStream.setLineJoinStyle(1);
/* 555 */     contentStream.setLineCapStyle(0);
/* 556 */     contentStream.setLineWidth(0.59F);
/*     */     
/* 558 */     contentStream.transform(Matrix.getRotateInstance(Math.toRadians(45.0D), 8.0F, -4.0F));
/*     */     
/* 560 */     contentStream.moveTo(1.0F, 7.0F);
/* 561 */     contentStream.lineTo(5.0F, 7.0F);
/* 562 */     contentStream.lineTo(5.0F, 1.0F);
/* 563 */     contentStream.lineTo(12.0F, 1.0F);
/* 564 */     contentStream.lineTo(12.0F, 7.0F);
/* 565 */     contentStream.lineTo(16.0F, 7.0F);
/* 566 */     contentStream.lineTo(8.5F, 19.0F);
/* 567 */     contentStream.closeAndFillAndStroke();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawRightArrow(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 573 */     PDRectangle bbox = adjustRectAndBBox(annotation, 20.0F, 20.0F);
/*     */     
/* 575 */     float min = Math.min(bbox.getWidth(), bbox.getHeight());
/*     */     
/* 577 */     contentStream.setMiterLimit(4.0F);
/* 578 */     contentStream.setLineJoinStyle(1);
/* 579 */     contentStream.setLineCapStyle(0);
/* 580 */     contentStream.setLineWidth(0.59F);
/*     */ 
/*     */     
/* 583 */     contentStream.saveGraphicsState();
/* 584 */     contentStream.setLineWidth(1.0F);
/* 585 */     PDExtendedGraphicsState gs = new PDExtendedGraphicsState();
/* 586 */     gs.setAlphaSourceFlag(false);
/* 587 */     gs.setStrokingAlphaConstant(Float.valueOf(0.6F));
/* 588 */     gs.setNonStrokingAlphaConstant(Float.valueOf(0.6F));
/* 589 */     gs.setBlendMode((BlendMode)BlendMode.NORMAL);
/* 590 */     contentStream.setGraphicsStateParameters(gs);
/* 591 */     contentStream.setNonStrokingColor(1.0F);
/* 592 */     drawCircle2(contentStream, min / 2.0F, min / 2.0F, min / 2.0F - 1.0F);
/* 593 */     contentStream.fill();
/* 594 */     contentStream.restoreGraphicsState();
/*     */     
/* 596 */     contentStream.saveGraphicsState();
/*     */ 
/*     */     
/* 599 */     contentStream.transform(Matrix.getScaleInstance(0.001F * min / 1.3F, 0.001F * min / 1.3F));
/* 600 */     contentStream.transform(Matrix.getTranslateInstance(200.0F, 300.0F));
/*     */ 
/*     */ 
/*     */     
/* 604 */     GeneralPath path = PDType1Font.ZAPF_DINGBATS.getPath("a160");
/* 605 */     addPath(contentStream, path);
/* 606 */     contentStream.restoreGraphicsState();
/*     */     
/* 608 */     drawCircle(contentStream, min / 2.0F, min / 2.0F, min / 2.0F - 1.0F);
/* 609 */     contentStream.fillAndStroke();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawComment(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 615 */     adjustRectAndBBox(annotation, 18.0F, 18.0F);
/*     */     
/* 617 */     contentStream.setMiterLimit(4.0F);
/* 618 */     contentStream.setLineJoinStyle(1);
/* 619 */     contentStream.setLineCapStyle(0);
/* 620 */     contentStream.setLineWidth(200.0F);
/*     */ 
/*     */     
/* 623 */     contentStream.saveGraphicsState();
/* 624 */     contentStream.setLineWidth(1.0F);
/* 625 */     PDExtendedGraphicsState gs = new PDExtendedGraphicsState();
/* 626 */     gs.setAlphaSourceFlag(false);
/* 627 */     gs.setStrokingAlphaConstant(Float.valueOf(0.6F));
/* 628 */     gs.setNonStrokingAlphaConstant(Float.valueOf(0.6F));
/* 629 */     gs.setBlendMode((BlendMode)BlendMode.NORMAL);
/* 630 */     contentStream.setGraphicsStateParameters(gs);
/* 631 */     contentStream.setNonStrokingColor(1.0F);
/* 632 */     contentStream.addRect(0.3F, 0.3F, 17.4F, 17.4F);
/* 633 */     contentStream.fill();
/* 634 */     contentStream.restoreGraphicsState();
/*     */     
/* 636 */     contentStream.transform(Matrix.getScaleInstance(0.003F, 0.003F));
/* 637 */     contentStream.transform(Matrix.getTranslateInstance(500.0F, -300.0F));
/*     */ 
/*     */ 
/*     */     
/* 641 */     contentStream.moveTo(2549.0F, 5269.0F);
/* 642 */     contentStream.curveTo(1307.0F, 5269.0F, 300.0F, 4451.0F, 300.0F, 3441.0F);
/* 643 */     contentStream.curveTo(300.0F, 3023.0F, 474.0F, 2640.0F, 764.0F, 2331.0F);
/* 644 */     contentStream.curveTo(633.0F, 1985.0F, 361.0F, 1691.0F, 357.0F, 1688.0F);
/* 645 */     contentStream.curveTo(299.0F, 1626.0F, 283.0F, 1537.0F, 316.0F, 1459.0F);
/* 646 */     contentStream.curveTo(350.0F, 1382.0F, 426.0F, 1332.0F, 510.0F, 1332.0F);
/* 647 */     contentStream.curveTo(1051.0F, 1332.0F, 1477.0F, 1558.0F, 1733.0F, 1739.0F);
/* 648 */     contentStream.curveTo(1987.0F, 1659.0F, 2261.0F, 1613.0F, 2549.0F, 1613.0F);
/* 649 */     contentStream.curveTo(3792.0F, 1613.0F, 4799.0F, 2431.0F, 4799.0F, 3441.0F);
/* 650 */     contentStream.curveTo(4799.0F, 4451.0F, 3792.0F, 5269.0F, 2549.0F, 5269.0F);
/* 651 */     contentStream.closePath();
/*     */ 
/*     */     
/* 654 */     contentStream.moveTo(-400.0F, 400.0F);
/* 655 */     contentStream.lineTo(-400.0F, 6200.0F);
/* 656 */     contentStream.lineTo(5400.0F, 6200.0F);
/* 657 */     contentStream.lineTo(5400.0F, 400.0F);
/*     */     
/* 659 */     contentStream.closeAndFillAndStroke();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawKey(PDAnnotationText annotation, PDAppearanceContentStream contentStream) throws IOException {
/* 665 */     adjustRectAndBBox(annotation, 13.0F, 18.0F);
/*     */     
/* 667 */     contentStream.setMiterLimit(4.0F);
/* 668 */     contentStream.setLineJoinStyle(1);
/* 669 */     contentStream.setLineCapStyle(0);
/* 670 */     contentStream.setLineWidth(200.0F);
/*     */     
/* 672 */     contentStream.transform(Matrix.getScaleInstance(0.003F, 0.003F));
/* 673 */     contentStream.transform(Matrix.getRotateInstance(Math.toRadians(45.0D), 2500.0F, -800.0F));
/*     */ 
/*     */ 
/*     */     
/* 677 */     contentStream.moveTo(4799.0F, 4004.0F);
/* 678 */     contentStream.curveTo(4799.0F, 3149.0F, 4107.0F, 2457.0F, 3253.0F, 2457.0F);
/* 679 */     contentStream.curveTo(3154.0F, 2457.0F, 3058.0F, 2466.0F, 2964.0F, 2484.0F);
/* 680 */     contentStream.lineTo(2753.0F, 2246.0F);
/* 681 */     contentStream.curveTo(2713.0F, 2201.0F, 2656.0F, 2175.0F, 2595.0F, 2175.0F);
/* 682 */     contentStream.lineTo(2268.0F, 2175.0F);
/* 683 */     contentStream.lineTo(2268.0F, 1824.0F);
/* 684 */     contentStream.curveTo(2268.0F, 1707.0F, 2174.0F, 1613.0F, 2057.0F, 1613.0F);
/* 685 */     contentStream.lineTo(1706.0F, 1613.0F);
/* 686 */     contentStream.lineTo(1706.0F, 1261.0F);
/* 687 */     contentStream.curveTo(1706.0F, 1145.0F, 1611.0F, 1050.0F, 1495.0F, 1050.0F);
/* 688 */     contentStream.lineTo(510.0F, 1050.0F);
/* 689 */     contentStream.curveTo(394.0F, 1050.0F, 300.0F, 1145.0F, 300.0F, 1261.0F);
/* 690 */     contentStream.lineTo(300.0F, 1947.0F);
/* 691 */     contentStream.curveTo(300.0F, 2003.0F, 322.0F, 2057.0F, 361.0F, 2097.0F);
/* 692 */     contentStream.lineTo(1783.0F, 3519.0F);
/* 693 */     contentStream.curveTo(1733.0F, 3671.0F, 1706.0F, 3834.0F, 1706.0F, 4004.0F);
/* 694 */     contentStream.curveTo(1706.0F, 4858.0F, 2398.0F, 5550.0F, 3253.0F, 5550.0F);
/* 695 */     contentStream.curveTo(4109.0F, 5550.0F, 4799.0F, 4860.0F, 4799.0F, 4004.0F);
/* 696 */     contentStream.closePath();
/* 697 */     contentStream.moveTo(3253.0F, 4425.0F);
/* 698 */     contentStream.curveTo(3253.0F, 4192.0F, 3441.0F, 4004.0F, 3674.0F, 4004.0F);
/* 699 */     contentStream.curveTo(3907.0F, 4004.0F, 4096.0F, 4192.0F, 4096.0F, 4425.0F);
/* 700 */     contentStream.curveTo(4096.0F, 4658.0F, 3907.0F, 4847.0F, 3674.0F, 4847.0F);
/* 701 */     contentStream.curveTo(3441.0F, 4847.0F, 3253.0F, 4658.0F, 3253.0F, 4425.0F);
/* 702 */     contentStream.fillAndStroke();
/*     */   }
/*     */ 
/*     */   
/*     */   private void addPath(PDAppearanceContentStream contentStream, GeneralPath path) throws IOException {
/* 707 */     double curX = 0.0D;
/* 708 */     double curY = 0.0D;
/* 709 */     PathIterator it = path.getPathIterator(new AffineTransform());
/* 710 */     double[] coords = new double[6];
/* 711 */     while (!it.isDone()) {
/*     */       double cp1x, cp1y, cp2x, cp2y;
/* 713 */       int type = it.currentSegment(coords);
/* 714 */       switch (type) {
/*     */         
/*     */         case 4:
/* 717 */           contentStream.closePath();
/*     */           break;
/*     */         case 3:
/* 720 */           contentStream.curveTo((float)coords[0], (float)coords[1], (float)coords[2], (float)coords[3], (float)coords[4], (float)coords[5]);
/*     */           
/* 722 */           curX = coords[4];
/* 723 */           curY = coords[5];
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 730 */           cp1x = curX + 0.6666666666666666D * (coords[0] - curX);
/* 731 */           cp1y = curY + 0.6666666666666666D * (coords[1] - curY);
/* 732 */           cp2x = coords[2] + 0.6666666666666666D * (coords[0] - coords[2]);
/* 733 */           cp2y = coords[3] + 0.6666666666666666D * (coords[1] - coords[3]);
/* 734 */           contentStream.curveTo((float)cp1x, (float)cp1y, (float)cp2x, (float)cp2y, (float)coords[2], (float)coords[3]);
/*     */ 
/*     */           
/* 737 */           curX = coords[2];
/* 738 */           curY = coords[3];
/*     */           break;
/*     */         case 1:
/* 741 */           contentStream.lineTo((float)coords[0], (float)coords[1]);
/* 742 */           curX = coords[0];
/* 743 */           curY = coords[1];
/*     */           break;
/*     */         case 0:
/* 746 */           contentStream.moveTo((float)coords[0], (float)coords[1]);
/* 747 */           curX = coords[0];
/* 748 */           curY = coords[1];
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/* 753 */       it.next();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void generateRolloverAppearance() {}
/*     */   
/*     */   public void generateDownAppearance() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDTextAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */