/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.PDAppearanceContentStream;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationSquareCircle;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceEntry;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
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
/*     */ public abstract class PDAbstractAppearanceHandler
/*     */   implements PDAppearanceHandler
/*     */ {
/*     */   private final PDAnnotation annotation;
/*  53 */   protected static final Set<String> SHORT_STYLES = createShortStyles();
/*     */   
/*  55 */   static final double ARROW_ANGLE = Math.toRadians(30.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected static final Set<String> INTERIOR_COLOR_STYLES = createInteriorColorStyles();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   protected static final Set<String> ANGLED_STYLES = createAngledStyles();
/*     */ 
/*     */   
/*     */   public PDAbstractAppearanceHandler(PDAnnotation annotation) {
/*  69 */     this.annotation = annotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract void generateNormalAppearance();
/*     */ 
/*     */   
/*     */   public abstract void generateRolloverAppearance();
/*     */ 
/*     */   
/*     */   public abstract void generateDownAppearance();
/*     */ 
/*     */   
/*     */   PDAnnotation getAnnotation() {
/*  83 */     return this.annotation;
/*     */   }
/*     */ 
/*     */   
/*     */   PDColor getColor() {
/*  88 */     return this.annotation.getColor();
/*     */   }
/*     */ 
/*     */   
/*     */   PDRectangle getRectangle() {
/*  93 */     return this.annotation.getRectangle();
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
/*     */   
/*     */   PDAppearanceDictionary getAppearance() {
/* 107 */     PDAppearanceDictionary appearanceDictionary = this.annotation.getAppearance();
/* 108 */     if (appearanceDictionary == null) {
/*     */       
/* 110 */       appearanceDictionary = new PDAppearanceDictionary();
/* 111 */       this.annotation.setAppearance(appearanceDictionary);
/*     */     } 
/* 113 */     return appearanceDictionary;
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
/*     */ 
/*     */   
/*     */   PDAppearanceContentStream getNormalAppearanceAsContentStream() throws IOException {
/* 128 */     return getNormalAppearanceAsContentStream(false);
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
/*     */ 
/*     */ 
/*     */   
/*     */   PDAppearanceContentStream getNormalAppearanceAsContentStream(boolean compress) throws IOException {
/* 144 */     PDAppearanceEntry appearanceEntry = getNormalAppearance();
/* 145 */     return getAppearanceEntryAsContentStream(appearanceEntry, compress);
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
/*     */   
/*     */   PDAppearanceEntry getDownAppearance() {
/* 159 */     PDAppearanceDictionary appearanceDictionary = getAppearance();
/* 160 */     PDAppearanceEntry downAppearanceEntry = appearanceDictionary.getDownAppearance();
/*     */     
/* 162 */     if (downAppearanceEntry.isSubDictionary()) {
/*     */ 
/*     */       
/* 165 */       downAppearanceEntry = new PDAppearanceEntry((COSBase)new COSStream());
/* 166 */       appearanceDictionary.setDownAppearance(downAppearanceEntry);
/*     */     } 
/*     */     
/* 169 */     return downAppearanceEntry;
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
/*     */   
/*     */   PDAppearanceEntry getRolloverAppearance() {
/* 183 */     PDAppearanceDictionary appearanceDictionary = getAppearance();
/* 184 */     PDAppearanceEntry rolloverAppearanceEntry = appearanceDictionary.getRolloverAppearance();
/*     */     
/* 186 */     if (rolloverAppearanceEntry.isSubDictionary()) {
/*     */ 
/*     */       
/* 189 */       rolloverAppearanceEntry = new PDAppearanceEntry((COSBase)new COSStream());
/* 190 */       appearanceDictionary.setRolloverAppearance(rolloverAppearanceEntry);
/*     */     } 
/*     */     
/* 193 */     return rolloverAppearanceEntry;
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
/*     */   
/*     */   PDRectangle getPaddedRectangle(PDRectangle rectangle, float padding) {
/* 207 */     return new PDRectangle(rectangle.getLowerLeftX() + padding, rectangle.getLowerLeftY() + padding, rectangle
/* 208 */         .getWidth() - 2.0F * padding, rectangle.getHeight() - 2.0F * padding);
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
/*     */ 
/*     */ 
/*     */   
/*     */   PDRectangle addRectDifferences(PDRectangle rectangle, float[] differences) {
/* 224 */     if (differences == null || differences.length != 4)
/*     */     {
/* 226 */       return rectangle;
/*     */     }
/*     */     
/* 229 */     return new PDRectangle(rectangle.getLowerLeftX() - differences[0], rectangle
/* 230 */         .getLowerLeftY() - differences[1], rectangle
/* 231 */         .getWidth() + differences[0] + differences[2], rectangle
/* 232 */         .getHeight() + differences[1] + differences[3]);
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
/*     */ 
/*     */ 
/*     */   
/*     */   PDRectangle applyRectDifferences(PDRectangle rectangle, float[] differences) {
/* 248 */     if (differences == null || differences.length != 4)
/*     */     {
/* 250 */       return rectangle;
/*     */     }
/* 252 */     return new PDRectangle(rectangle.getLowerLeftX() + differences[0], rectangle
/* 253 */         .getLowerLeftY() + differences[1], rectangle
/* 254 */         .getWidth() - differences[0] - differences[2], rectangle
/* 255 */         .getHeight() - differences[1] - differences[3]);
/*     */   }
/*     */ 
/*     */   
/*     */   void setOpacity(PDAppearanceContentStream contentStream, float opacity) throws IOException {
/* 260 */     if (opacity < 1.0F) {
/*     */       
/* 262 */       PDExtendedGraphicsState gs = new PDExtendedGraphicsState();
/* 263 */       gs.setStrokingAlphaConstant(Float.valueOf(opacity));
/* 264 */       gs.setNonStrokingAlphaConstant(Float.valueOf(opacity));
/*     */       
/* 266 */       contentStream.setGraphicsStateParameters(gs);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void drawStyle(String style, PDAppearanceContentStream cs, float x, float y, float width, boolean hasStroke, boolean hasBackground, boolean ending) throws IOException {
/* 288 */     int sign = ending ? -1 : 1;
/*     */     
/* 290 */     if ("OpenArrow".equals(style) || "ClosedArrow".equals(style)) {
/*     */       
/* 292 */       drawArrow(cs, x + sign * width, y, sign * width * 9.0F);
/*     */     }
/* 294 */     else if ("Butt".equals(style)) {
/*     */       
/* 296 */       cs.moveTo(x, y - width * 3.0F);
/* 297 */       cs.lineTo(x, y + width * 3.0F);
/*     */     }
/* 299 */     else if ("Diamond".equals(style)) {
/*     */       
/* 301 */       drawDiamond(cs, x, y, width * 3.0F);
/*     */     }
/* 303 */     else if ("Square".equals(style)) {
/*     */       
/* 305 */       cs.addRect(x - width * 3.0F, y - width * 3.0F, width * 6.0F, width * 6.0F);
/*     */     }
/* 307 */     else if ("Circle".equals(style)) {
/*     */       
/* 309 */       drawCircle(cs, x, y, width * 3.0F);
/*     */     }
/* 311 */     else if ("ROpenArrow".equals(style) || "RClosedArrow".equals(style)) {
/*     */       
/* 313 */       drawArrow(cs, x + (0 - sign) * width, y, (0 - sign) * width * 9.0F);
/*     */     }
/* 315 */     else if ("Slash".equals(style)) {
/*     */ 
/*     */       
/* 318 */       cs.moveTo(x + (float)(Math.cos(Math.toRadians(60.0D)) * width * 9.0D), y + 
/* 319 */           (float)(Math.sin(Math.toRadians(60.0D)) * width * 9.0D));
/* 320 */       cs.lineTo(x + (float)(Math.cos(Math.toRadians(240.0D)) * width * 9.0D), y + 
/* 321 */           (float)(Math.sin(Math.toRadians(240.0D)) * width * 9.0D));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 326 */     if ("RClosedArrow".equals(style) || "ClosedArrow"
/* 327 */       .equals(style))
/*     */     {
/* 329 */       cs.closePath();
/*     */     }
/* 331 */     cs.drawShape(width, hasStroke, 
/*     */ 
/*     */         
/* 334 */         INTERIOR_COLOR_STYLES.contains(style) ? hasBackground : false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void drawArrow(PDAppearanceContentStream cs, float x, float y, float len) throws IOException {
/* 353 */     cs.moveTo(x + (float)(Math.cos(ARROW_ANGLE) * len), y + (float)(Math.sin(ARROW_ANGLE) * len));
/* 354 */     cs.lineTo(x, y);
/* 355 */     cs.lineTo(x + (float)(Math.cos(ARROW_ANGLE) * len), y - (float)(Math.sin(ARROW_ANGLE) * len));
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
/*     */ 
/*     */   
/*     */   void drawDiamond(PDAppearanceContentStream cs, float x, float y, float r) throws IOException {
/* 370 */     cs.moveTo(x - r, y);
/* 371 */     cs.lineTo(x, y + r);
/* 372 */     cs.lineTo(x + r, y);
/* 373 */     cs.lineTo(x, y - r);
/* 374 */     cs.closePath();
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
/*     */ 
/*     */ 
/*     */   
/*     */   void drawCircle(PDAppearanceContentStream cs, float x, float y, float r) throws IOException {
/* 390 */     float magic = r * 0.551784F;
/* 391 */     cs.moveTo(x, y + r);
/* 392 */     cs.curveTo(x + magic, y + r, x + r, y + magic, x + r, y);
/* 393 */     cs.curveTo(x + r, y - magic, x + magic, y - r, x, y - r);
/* 394 */     cs.curveTo(x - magic, y - r, x - r, y - magic, x - r, y);
/* 395 */     cs.curveTo(x - r, y + magic, x - magic, y + r, x, y + r);
/* 396 */     cs.closePath();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void drawCircle2(PDAppearanceContentStream cs, float x, float y, float r) throws IOException {
/* 413 */     float magic = r * 0.551784F;
/* 414 */     cs.moveTo(x, y + r);
/* 415 */     cs.curveTo(x - magic, y + r, x - r, y + magic, x - r, y);
/* 416 */     cs.curveTo(x - r, y - magic, x - magic, y - r, x, y - r);
/* 417 */     cs.curveTo(x + magic, y - r, x + r, y - magic, x + r, y);
/* 418 */     cs.curveTo(x + r, y + magic, x + magic, y + r, x, y + r);
/* 419 */     cs.closePath();
/*     */   }
/*     */ 
/*     */   
/*     */   private static Set<String> createShortStyles() {
/* 424 */     Set<String> shortStyles = new HashSet<String>();
/* 425 */     shortStyles.add("OpenArrow");
/* 426 */     shortStyles.add("ClosedArrow");
/* 427 */     shortStyles.add("Square");
/* 428 */     shortStyles.add("Circle");
/* 429 */     shortStyles.add("Diamond");
/* 430 */     return Collections.unmodifiableSet(shortStyles);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Set<String> createInteriorColorStyles() {
/* 435 */     Set<String> interiorColorStyles = new HashSet<String>();
/* 436 */     interiorColorStyles.add("ClosedArrow");
/* 437 */     interiorColorStyles.add("Circle");
/* 438 */     interiorColorStyles.add("Diamond");
/* 439 */     interiorColorStyles.add("RClosedArrow");
/* 440 */     interiorColorStyles.add("Square");
/* 441 */     return Collections.unmodifiableSet(interiorColorStyles);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Set<String> createAngledStyles() {
/* 446 */     Set<String> angledStyles = new HashSet<String>();
/* 447 */     angledStyles.add("ClosedArrow");
/* 448 */     angledStyles.add("OpenArrow");
/* 449 */     angledStyles.add("RClosedArrow");
/* 450 */     angledStyles.add("ROpenArrow");
/* 451 */     angledStyles.add("Butt");
/* 452 */     angledStyles.add("Slash");
/* 453 */     return Collections.unmodifiableSet(angledStyles);
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
/*     */   
/*     */   private PDAppearanceEntry getNormalAppearance() {
/* 467 */     PDAppearanceDictionary appearanceDictionary = getAppearance();
/* 468 */     PDAppearanceEntry normalAppearanceEntry = appearanceDictionary.getNormalAppearance();
/*     */     
/* 470 */     if (normalAppearanceEntry == null || normalAppearanceEntry.isSubDictionary()) {
/*     */ 
/*     */       
/* 473 */       normalAppearanceEntry = new PDAppearanceEntry((COSBase)new COSStream());
/* 474 */       appearanceDictionary.setNormalAppearance(normalAppearanceEntry);
/*     */     } 
/*     */     
/* 477 */     return normalAppearanceEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PDAppearanceContentStream getAppearanceEntryAsContentStream(PDAppearanceEntry appearanceEntry, boolean compress) throws IOException {
/* 484 */     PDAppearanceStream appearanceStream = appearanceEntry.getAppearanceStream();
/* 485 */     setTransformationMatrix(appearanceStream);
/*     */ 
/*     */     
/* 488 */     PDResources resources = appearanceStream.getResources();
/* 489 */     if (resources == null) {
/*     */       
/* 491 */       resources = new PDResources();
/* 492 */       appearanceStream.setResources(resources);
/*     */     } 
/*     */     
/* 495 */     return new PDAppearanceContentStream(appearanceStream, compress);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setTransformationMatrix(PDAppearanceStream appearanceStream) {
/* 500 */     PDRectangle bbox = getRectangle();
/* 501 */     appearanceStream.setBBox(bbox);
/* 502 */     AffineTransform transform = AffineTransform.getTranslateInstance(-bbox.getLowerLeftX(), 
/* 503 */         -bbox.getLowerLeftY());
/* 504 */     appearanceStream.setMatrix(transform);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PDRectangle handleBorderBox(PDAnnotationSquareCircle annotation, float lineWidth) {
/*     */     PDRectangle borderBox;
/* 516 */     float[] rectDifferences = annotation.getRectDifferences();
/* 517 */     if (rectDifferences.length == 0) {
/*     */       
/* 519 */       borderBox = getPaddedRectangle(getRectangle(), lineWidth / 2.0F);
/*     */       
/* 521 */       annotation.setRectDifferences(lineWidth / 2.0F);
/* 522 */       annotation.setRectangle(addRectDifferences(getRectangle(), annotation.getRectDifferences()));
/*     */ 
/*     */       
/* 525 */       annotation.getNormalAppearanceStream().setBBox(getRectangle());
/* 526 */       AffineTransform transform = AffineTransform.getTranslateInstance(-getRectangle().getLowerLeftX(), -getRectangle().getLowerLeftY());
/* 527 */       annotation.getNormalAppearanceStream().setMatrix(transform);
/*     */     }
/*     */     else {
/*     */       
/* 531 */       borderBox = applyRectDifferences(getRectangle(), rectDifferences);
/* 532 */       borderBox = getPaddedRectangle(borderBox, lineWidth / 2.0F);
/*     */     } 
/* 534 */     return borderBox;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDAbstractAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */