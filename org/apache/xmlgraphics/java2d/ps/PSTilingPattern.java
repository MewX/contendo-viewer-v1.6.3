/*     */ package org.apache.xmlgraphics.java2d.ps;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.TexturePaint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.List;
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
/*     */ 
/*     */ public class PSTilingPattern
/*     */ {
/*     */   public static final int PATTERN_TYPE_TILING = 1;
/*     */   public static final int PATTERN_TYPE_SHADING = 2;
/*  45 */   protected int patternType = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String patternName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List xUID;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected StringBuffer paintProc;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Rectangle2D bBox;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected double xStep;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected double yStep;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   protected int paintType = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   protected int tilingType = 1;
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
/*     */   protected TexturePaint texture;
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
/*     */   public PSTilingPattern(String patternName, StringBuffer paintProc, Rectangle bBox, double xStep, double yStep, int paintType, int tilingType, List xUID) {
/* 124 */     this.patternName = patternName;
/* 125 */     this.paintProc = paintProc;
/* 126 */     setBoundingBox(bBox);
/* 127 */     setXStep(xStep);
/* 128 */     setYStep(yStep);
/* 129 */     setPaintType(paintType);
/* 130 */     setTilingType(tilingType);
/* 131 */     this.xUID = xUID;
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
/*     */   public PSTilingPattern(String patternName, TexturePaint texture, double xStep, double yStep, int tilingType, List xUID) {
/* 150 */     this(patternName, null, new Rectangle(), 1.0D, 1.0D, 1, tilingType, xUID);
/*     */     
/* 152 */     this.texture = texture;
/*     */     
/* 154 */     Rectangle2D anchor = texture.getAnchorRect();
/* 155 */     this.bBox = new Rectangle2D.Double(anchor.getX(), anchor.getY(), anchor.getX() + anchor.getWidth(), anchor.getY() + anchor.getHeight());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     this.xStep = (xStep == 0.0D) ? anchor.getWidth() : xStep;
/* 162 */     this.yStep = (yStep == 0.0D) ? anchor.getHeight() : yStep;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 171 */     return this.patternName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 179 */     if (name == null) {
/* 180 */       throw new NullPointerException("Parameter patternName must not be null");
/*     */     }
/* 182 */     if (name.length() == 0) {
/* 183 */       throw new IllegalArgumentException("Parameter patternName must not be empty");
/*     */     }
/* 185 */     if (name.indexOf(" ") >= 0) {
/* 186 */       throw new IllegalArgumentException("Pattern name must not contain any spaces");
/*     */     }
/*     */     
/* 189 */     this.patternName = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBoundingBox() {
/* 198 */     return this.bBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBoundingBox(Rectangle2D bBox) {
/* 207 */     if (bBox == null) {
/* 208 */       throw new NullPointerException("Parameter bBox must not be null");
/*     */     }
/* 210 */     this.bBox = bBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringBuffer getPaintProc() {
/* 219 */     return this.paintProc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPaintProc(StringBuffer paintProc) {
/* 228 */     this.paintProc = paintProc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getXStep() {
/* 237 */     return this.xStep;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXStep(double xStep) {
/* 246 */     if (xStep == 0.0D) {
/* 247 */       throw new IllegalArgumentException("Parameter xStep must not be 0");
/*     */     }
/* 249 */     this.xStep = xStep;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getYStep() {
/* 258 */     return this.yStep;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setYStep(double yStep) {
/* 267 */     if (yStep == 0.0D) {
/* 268 */       throw new IllegalArgumentException("Parameter yStep must not be 0");
/*     */     }
/* 270 */     this.yStep = yStep;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPaintType() {
/* 280 */     return this.paintType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPaintType(int paintType) {
/* 290 */     if (paintType != 1 && paintType != 2) {
/* 291 */       throw new IllegalArgumentException("Parameter paintType must not be " + paintType + " (only 1 or 2)");
/*     */     }
/*     */     
/* 294 */     this.paintType = paintType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTilingType() {
/* 305 */     return this.tilingType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTilingType(int tilingType) {
/* 316 */     if (tilingType > 3 || tilingType < 1) {
/* 317 */       throw new IllegalArgumentException("Parameter tilingType must not be " + tilingType + " (only 1, 2 or 3)");
/*     */     }
/*     */     
/* 320 */     this.tilingType = tilingType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TexturePaint getTexturePaint() {
/* 329 */     return this.texture;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTexturePaint(TexturePaint texturePaint) {
/* 338 */     this.texture = texturePaint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getXUID() {
/* 347 */     return this.xUID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXUID(List xUID) {
/* 356 */     this.xUID = xUID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 365 */     StringBuffer sb = new StringBuffer("<<\n");
/* 366 */     sb.append("/PatternType " + this.patternType + "\n");
/* 367 */     sb.append("/PaintType " + this.paintType + "\n");
/* 368 */     sb.append("/TilingType " + this.tilingType + "\n");
/* 369 */     sb.append("/XStep " + this.xStep + "\n");
/* 370 */     sb.append("/YStep " + this.yStep + "\n");
/* 371 */     sb.append("/BBox [" + this.bBox.getX() + " " + this.bBox.getY() + " " + this.bBox.getWidth() + " " + this.bBox.getHeight() + "]" + "\n");
/*     */     
/* 373 */     sb.append("/PaintProc\n{\n");
/*     */ 
/*     */     
/* 376 */     if (this.paintProc == null || this.paintProc.indexOf("pop") != 0) {
/* 377 */       sb.append("pop\n");
/*     */     }
/*     */     
/* 380 */     if (this.texture != null) {
/* 381 */       int width = this.texture.getImage().getWidth();
/* 382 */       int height = this.texture.getImage().getHeight();
/*     */       
/* 384 */       Rectangle2D anchor = this.texture.getAnchorRect();
/* 385 */       if (anchor.getX() != 0.0D || anchor.getY() != 0.0D) {
/* 386 */         sb.append(anchor.getX() + " " + anchor.getY() + " translate\n");
/*     */       }
/* 388 */       double scaleX = anchor.getWidth() / width;
/* 389 */       double scaleY = anchor.getHeight() / height;
/* 390 */       if (scaleX != 1.0D || scaleY != 1.0D) {
/* 391 */         sb.append(scaleX + " " + scaleY + " scale\n");
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 396 */       sb.append(width + " " + height + " 8 " + "matrix\n");
/* 397 */       int[] argb = new int[width * height];
/* 398 */       sb.append("{<");
/* 399 */       this.texture.getImage().getRGB(0, 0, width, height, argb, 0, width);
/* 400 */       int count = 0;
/* 401 */       for (int i = 0; i < argb.length; i++) {
/* 402 */         if (i % width == 0 || count > 249) {
/* 403 */           sb.append("\n");
/* 404 */           count = 0;
/*     */         } 
/*     */         
/* 407 */         StringBuffer sRGB = new StringBuffer(Integer.toHexString(argb[i] & 0xFFFFFF));
/* 408 */         if (sRGB.length() != 6) {
/* 409 */           sRGB.insert(0, "000000");
/* 410 */           sRGB = new StringBuffer(sRGB.substring(sRGB.length() - 6));
/*     */         } 
/* 412 */         sb.append(sRGB);
/* 413 */         count += 6;
/*     */       } 
/* 415 */       sb.append("\n>} false 3 colorimage");
/*     */     } else {
/* 417 */       sb.append(this.paintProc);
/*     */     } 
/* 419 */     sb.append("\n} bind \n");
/* 420 */     sb.append(">>\n");
/*     */ 
/*     */     
/* 423 */     sb.append("matrix\n");
/* 424 */     sb.append("makepattern\n");
/*     */ 
/*     */     
/* 427 */     sb.append("/" + this.patternName + " exch def\n");
/*     */     
/* 429 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 434 */     return 0x0 ^ this.patternType ^ ((this.xUID != null) ? this.xUID.hashCode() : 0) ^ ((this.paintProc != null) ? this.paintProc.hashCode() : 0) ^ ((this.bBox != null) ? this.bBox.hashCode() : 0) ^ Double.valueOf(this.xStep).hashCode() ^ Double.valueOf(this.yStep).hashCode() ^ this.paintType ^ this.tilingType ^ ((this.texture != null) ? this.texture.hashCode() : 0);
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
/*     */   public boolean equals(Object pattern) {
/* 452 */     if (pattern == null) {
/* 453 */       return false;
/*     */     }
/* 455 */     if (!(pattern instanceof PSTilingPattern)) {
/* 456 */       return false;
/*     */     }
/* 458 */     if (this == pattern) {
/* 459 */       return true;
/*     */     }
/*     */     
/* 462 */     PSTilingPattern patternObj = (PSTilingPattern)pattern;
/* 463 */     if (this.patternType != patternObj.patternType) {
/* 464 */       return false;
/*     */     }
/*     */     
/* 467 */     TexturePaint patternTexture = patternObj.getTexturePaint();
/*     */     
/* 469 */     if ((patternTexture == null && this.texture != null) || (patternTexture != null && this.texture == null))
/*     */     {
/* 471 */       return false;
/*     */     }
/*     */     
/* 474 */     if (patternTexture != null && this.texture != null) {
/*     */       
/* 476 */       int width = this.texture.getImage().getWidth();
/* 477 */       int height = this.texture.getImage().getHeight();
/*     */       
/* 479 */       int widthPattern = patternTexture.getImage().getWidth();
/* 480 */       int heightPattern = patternTexture.getImage().getHeight();
/*     */       
/* 482 */       if (width != widthPattern) {
/* 483 */         return false;
/*     */       }
/* 485 */       if (height != heightPattern) {
/* 486 */         return false;
/*     */       }
/* 488 */       int[] rgbData = new int[width * height];
/* 489 */       int[] rgbDataPattern = new int[widthPattern * heightPattern];
/*     */       
/* 491 */       this.texture.getImage().getRGB(0, 0, width, height, rgbData, 0, width);
/* 492 */       patternTexture.getImage().getRGB(0, 0, widthPattern, heightPattern, rgbDataPattern, 0, widthPattern);
/*     */ 
/*     */       
/* 495 */       for (int i = 0; i < rgbData.length; i++) {
/* 496 */         if (rgbData[i] != rgbDataPattern[i]) {
/* 497 */           return false;
/*     */         }
/*     */       }
/*     */     
/*     */     }
/* 502 */     else if (!this.paintProc.toString().equals(patternObj.getPaintProc().toString())) {
/* 503 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 508 */     if (this.xStep != patternObj.getXStep()) {
/* 509 */       return false;
/*     */     }
/* 511 */     if (this.yStep != patternObj.getYStep()) {
/* 512 */       return false;
/*     */     }
/* 514 */     if (this.paintType != patternObj.getPaintType()) {
/* 515 */       return false;
/*     */     }
/* 517 */     if (this.tilingType != patternObj.getTilingType()) {
/* 518 */       return false;
/*     */     }
/* 520 */     if (!this.bBox.equals(patternObj.getBoundingBox())) {
/* 521 */       return false;
/*     */     }
/* 523 */     if (this.xUID != null && patternObj.getXUID() != null && 
/* 524 */       !this.xUID.equals(patternObj.getXUID())) {
/* 525 */       return false;
/*     */     }
/*     */     
/* 528 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/ps/PSTilingPattern.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */