/*     */ package org.apache.pdfbox.text;
/*     */ 
/*     */ import java.text.Normalizer;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFont;
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
/*     */ 
/*     */ public final class TextPosition
/*     */ {
/*  35 */   private static final Log LOG = LogFactory.getLog(TextPosition.class);
/*     */   
/*  37 */   private static final Map<Integer, String> DIACRITICS = createDiacritics();
/*     */   
/*     */   private final Matrix textMatrix;
/*     */   
/*     */   private final float endX;
/*     */   
/*     */   private final float endY;
/*     */   
/*     */   private final float maxHeight;
/*     */   
/*     */   private final int rotation;
/*     */   
/*     */   private final float x;
/*     */   
/*     */   private final float y;
/*     */   
/*     */   private final float pageHeight;
/*     */   
/*     */   private final float pageWidth;
/*     */   
/*     */   private final float widthOfSpace;
/*     */   private final int[] charCodes;
/*     */   private final PDFont font;
/*     */   private final float fontSize;
/*     */   private final int fontSizePt;
/*     */   private float[] widths;
/*     */   private String unicode;
/*  64 */   private float direction = -1.0F;
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
/*     */   public TextPosition(int pageRotation, float pageWidth, float pageHeight, Matrix textMatrix, float endX, float endY, float maxHeight, float individualWidth, float spaceWidth, String unicode, int[] charCodes, PDFont font, float fontSize, int fontSizeInPt) {
/*  89 */     this.textMatrix = textMatrix;
/*     */     
/*  91 */     this.endX = endX;
/*  92 */     this.endY = endY;
/*     */     
/*  94 */     int rotationAngle = pageRotation;
/*  95 */     this.rotation = rotationAngle;
/*     */     
/*  97 */     this.maxHeight = maxHeight;
/*  98 */     this.pageHeight = pageHeight;
/*  99 */     this.pageWidth = pageWidth;
/*     */     
/* 101 */     this.widths = new float[] { individualWidth };
/* 102 */     this.widthOfSpace = spaceWidth;
/* 103 */     this.unicode = unicode;
/* 104 */     this.charCodes = charCodes;
/* 105 */     this.font = font;
/* 106 */     this.fontSize = fontSize;
/* 107 */     this.fontSizePt = fontSizeInPt;
/*     */     
/* 109 */     this.x = getXRot(rotationAngle);
/* 110 */     if (rotationAngle == 0 || rotationAngle == 180) {
/*     */       
/* 112 */       this.y = this.pageHeight - getYLowerLeftRot(rotationAngle);
/*     */     }
/*     */     else {
/*     */       
/* 116 */       this.y = this.pageWidth - getYLowerLeftRot(rotationAngle);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Map<Integer, String> createDiacritics() {
/* 127 */     Map<Integer, String> map = new HashMap<Integer, String>(31);
/* 128 */     map.put(Integer.valueOf(96), "̀");
/* 129 */     map.put(Integer.valueOf(715), "̀");
/* 130 */     map.put(Integer.valueOf(39), "́");
/* 131 */     map.put(Integer.valueOf(697), "́");
/* 132 */     map.put(Integer.valueOf(714), "́");
/* 133 */     map.put(Integer.valueOf(94), "̂");
/* 134 */     map.put(Integer.valueOf(710), "̂");
/* 135 */     map.put(Integer.valueOf(126), "̃");
/* 136 */     map.put(Integer.valueOf(713), "̄");
/* 137 */     map.put(Integer.valueOf(176), "̊");
/* 138 */     map.put(Integer.valueOf(698), "̋");
/* 139 */     map.put(Integer.valueOf(711), "̌");
/* 140 */     map.put(Integer.valueOf(712), "̍");
/* 141 */     map.put(Integer.valueOf(34), "̎");
/* 142 */     map.put(Integer.valueOf(699), "̒");
/* 143 */     map.put(Integer.valueOf(700), "̓");
/* 144 */     map.put(Integer.valueOf(1158), "̓");
/* 145 */     map.put(Integer.valueOf(1370), "̓");
/* 146 */     map.put(Integer.valueOf(701), "̔");
/* 147 */     map.put(Integer.valueOf(1157), "̔");
/* 148 */     map.put(Integer.valueOf(1369), "̔");
/* 149 */     map.put(Integer.valueOf(724), "̝");
/* 150 */     map.put(Integer.valueOf(725), "̞");
/* 151 */     map.put(Integer.valueOf(726), "̟");
/* 152 */     map.put(Integer.valueOf(727), "̠");
/* 153 */     map.put(Integer.valueOf(690), "̡");
/* 154 */     map.put(Integer.valueOf(716), "̩");
/* 155 */     map.put(Integer.valueOf(695), "̫");
/* 156 */     map.put(Integer.valueOf(717), "̱");
/* 157 */     map.put(Integer.valueOf(95), "̲");
/* 158 */     map.put(Integer.valueOf(8270), "͙");
/* 159 */     return map;
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
/*     */   public String getUnicode() {
/* 171 */     return this.unicode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getCharacterCodes() {
/* 181 */     return this.charCodes;
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
/*     */   public Matrix getTextMatrix() {
/* 194 */     return this.textMatrix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDir() {
/* 203 */     if (this.direction < 0.0F) {
/*     */       
/* 205 */       float a = this.textMatrix.getScaleY();
/* 206 */       float b = this.textMatrix.getShearY();
/* 207 */       float c = this.textMatrix.getShearX();
/* 208 */       float d = this.textMatrix.getScaleX();
/*     */ 
/*     */ 
/*     */       
/* 212 */       if (a > 0.0F && Math.abs(b) < d && Math.abs(c) < a && d > 0.0F) {
/*     */         
/* 214 */         this.direction = 0.0F;
/*     */ 
/*     */       
/*     */       }
/* 218 */       else if (a < 0.0F && Math.abs(b) < Math.abs(d) && Math.abs(c) < Math.abs(a) && d < 0.0F) {
/*     */         
/* 220 */         this.direction = 180.0F;
/*     */ 
/*     */       
/*     */       }
/* 224 */       else if (Math.abs(a) < Math.abs(c) && b > 0.0F && c < 0.0F && Math.abs(d) < b) {
/*     */         
/* 226 */         this.direction = 90.0F;
/*     */ 
/*     */       
/*     */       }
/* 230 */       else if (Math.abs(a) < c && b < 0.0F && c > 0.0F && Math.abs(d) < Math.abs(b)) {
/*     */         
/* 232 */         this.direction = 270.0F;
/*     */       }
/*     */       else {
/*     */         
/* 236 */         this.direction = 0.0F;
/*     */       } 
/*     */     } 
/* 239 */     return this.direction;
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
/*     */   private float getXRot(float rotation) {
/* 251 */     if (rotation == 0.0F)
/*     */     {
/* 253 */       return this.textMatrix.getTranslateX();
/*     */     }
/* 255 */     if (rotation == 90.0F)
/*     */     {
/* 257 */       return this.textMatrix.getTranslateY();
/*     */     }
/* 259 */     if (rotation == 180.0F)
/*     */     {
/* 261 */       return this.pageWidth - this.textMatrix.getTranslateX();
/*     */     }
/* 263 */     if (rotation == 270.0F)
/*     */     {
/* 265 */       return this.pageHeight - this.textMatrix.getTranslateY();
/*     */     }
/* 267 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getX() {
/* 278 */     return this.x;
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
/*     */   public float getXDirAdj() {
/* 290 */     return getXRot(getDir());
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
/*     */   private float getYLowerLeftRot(float rotation) {
/* 302 */     if (rotation == 0.0F)
/*     */     {
/* 304 */       return this.textMatrix.getTranslateY();
/*     */     }
/* 306 */     if (rotation == 90.0F)
/*     */     {
/* 308 */       return this.pageWidth - this.textMatrix.getTranslateX();
/*     */     }
/* 310 */     if (rotation == 180.0F)
/*     */     {
/* 312 */       return this.pageHeight - this.textMatrix.getTranslateY();
/*     */     }
/* 314 */     if (rotation == 270.0F)
/*     */     {
/* 316 */       return this.textMatrix.getTranslateX();
/*     */     }
/* 318 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getY() {
/* 329 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getYDirAdj() {
/* 340 */     float dir = getDir();
/*     */     
/* 342 */     if (dir == 0.0F || dir == 180.0F)
/*     */     {
/* 344 */       return this.pageHeight - getYLowerLeftRot(dir);
/*     */     }
/*     */ 
/*     */     
/* 348 */     return this.pageWidth - getYLowerLeftRot(dir);
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
/*     */   private float getWidthRot(float rotation) {
/* 360 */     if (rotation == 90.0F || rotation == 270.0F)
/*     */     {
/* 362 */       return Math.abs(this.endY - this.textMatrix.getTranslateY());
/*     */     }
/*     */ 
/*     */     
/* 366 */     return Math.abs(this.endX - this.textMatrix.getTranslateX());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidth() {
/* 377 */     return getWidthRot(this.rotation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidthDirAdj() {
/* 387 */     return getWidthRot(getDir());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeight() {
/* 397 */     return this.maxHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeightDir() {
/* 408 */     return this.maxHeight;
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
/*     */   public float getFontSize() {
/* 421 */     return this.fontSize;
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
/*     */   public float getFontSizeInPt() {
/* 435 */     return this.fontSizePt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFont getFont() {
/* 445 */     return this.font;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidthOfSpace() {
/* 456 */     return this.widthOfSpace;
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
/*     */   public float getXScale() {
/* 468 */     return this.textMatrix.getScalingFactorX();
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
/*     */   public float getYScale() {
/* 480 */     return this.textMatrix.getScalingFactorY();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getIndividualWidths() {
/* 490 */     return this.widths;
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
/*     */   public boolean contains(TextPosition tp2) {
/* 502 */     double thisXstart = getXDirAdj();
/* 503 */     double thisWidth = getWidthDirAdj();
/* 504 */     double thisXend = thisXstart + thisWidth;
/*     */     
/* 506 */     double tp2Xstart = tp2.getXDirAdj();
/* 507 */     double tp2Xend = tp2Xstart + tp2.getWidthDirAdj();
/*     */ 
/*     */     
/* 510 */     if (tp2Xend <= thisXstart || tp2Xstart >= thisXend)
/*     */     {
/* 512 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 517 */     double thisYstart = getYDirAdj();
/* 518 */     double tp2Ystart = tp2.getYDirAdj();
/* 519 */     if (tp2Ystart + tp2.getHeightDir() < thisYstart || tp2Ystart > thisYstart + 
/* 520 */       getHeightDir())
/*     */     {
/* 522 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 527 */     if (tp2Xstart > thisXstart && tp2Xend > thisXend) {
/*     */       
/* 529 */       double overlap = thisXend - tp2Xstart;
/* 530 */       double overlapPercent = overlap / thisWidth;
/* 531 */       return (overlapPercent > 0.15D);
/*     */     } 
/* 533 */     if (tp2Xstart < thisXstart && tp2Xend < thisXend) {
/*     */       
/* 535 */       double overlap = tp2Xend - thisXstart;
/* 536 */       double overlapPercent = overlap / thisWidth;
/* 537 */       return (overlapPercent > 0.15D);
/*     */     } 
/* 539 */     return true;
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
/*     */   public void mergeDiacritic(TextPosition diacritic) {
/* 552 */     if (diacritic.getUnicode().length() > 1) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 557 */     float diacXStart = diacritic.getXDirAdj();
/* 558 */     float diacXEnd = diacXStart + diacritic.widths[0];
/*     */     
/* 560 */     float currCharXStart = getXDirAdj();
/*     */     
/* 562 */     int strLen = this.unicode.length();
/* 563 */     boolean wasAdded = false;
/*     */     
/* 565 */     for (int i = 0; i < strLen && !wasAdded; i++) {
/*     */       
/* 567 */       if (i >= this.widths.length) {
/*     */         
/* 569 */         LOG.info("diacritic " + diacritic.getUnicode() + " on ligature " + this.unicode + " is not supported yet and is ignored (PDFBOX-2831)");
/*     */         
/*     */         break;
/*     */       } 
/* 573 */       float currCharXEnd = currCharXStart + this.widths[i];
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 578 */       if (diacXStart < currCharXStart && diacXEnd <= currCharXEnd) {
/*     */         
/* 580 */         if (i == 0) {
/*     */           
/* 582 */           insertDiacritic(i, diacritic);
/*     */         }
/*     */         else {
/*     */           
/* 586 */           float distanceOverlapping1 = diacXEnd - currCharXStart;
/* 587 */           float percentage1 = distanceOverlapping1 / this.widths[i];
/*     */           
/* 589 */           float distanceOverlapping2 = currCharXStart - diacXStart;
/* 590 */           float percentage2 = distanceOverlapping2 / this.widths[i - 1];
/*     */           
/* 592 */           if (percentage1 >= percentage2) {
/*     */             
/* 594 */             insertDiacritic(i, diacritic);
/*     */           }
/*     */           else {
/*     */             
/* 598 */             insertDiacritic(i - 1, diacritic);
/*     */           } 
/*     */         } 
/* 601 */         wasAdded = true;
/*     */ 
/*     */       
/*     */       }
/* 605 */       else if (diacXStart < currCharXStart && diacXEnd > currCharXEnd) {
/*     */         
/* 607 */         insertDiacritic(i, diacritic);
/* 608 */         wasAdded = true;
/*     */ 
/*     */       
/*     */       }
/* 612 */       else if (diacXStart >= currCharXStart && diacXEnd <= currCharXEnd) {
/*     */         
/* 614 */         insertDiacritic(i, diacritic);
/* 615 */         wasAdded = true;
/*     */       
/*     */       }
/* 618 */       else if (diacXStart >= currCharXStart && diacXEnd > currCharXEnd && i == strLen - 1) {
/*     */         
/* 620 */         insertDiacritic(i, diacritic);
/* 621 */         wasAdded = true;
/*     */       } 
/*     */ 
/*     */       
/* 625 */       currCharXStart += this.widths[i];
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
/*     */   private void insertDiacritic(int i, TextPosition diacritic) {
/* 638 */     StringBuilder sb = new StringBuilder();
/* 639 */     sb.append(this.unicode.substring(0, i));
/*     */     
/* 641 */     float[] widths2 = new float[this.widths.length + 1];
/* 642 */     System.arraycopy(this.widths, 0, widths2, 0, i);
/*     */ 
/*     */ 
/*     */     
/* 646 */     sb.append(this.unicode.charAt(i));
/* 647 */     widths2[i] = this.widths[i];
/* 648 */     sb.append(combineDiacritic(diacritic.getUnicode()));
/* 649 */     widths2[i + 1] = 0.0F;
/*     */ 
/*     */     
/* 652 */     sb.append(this.unicode.substring(i + 1, this.unicode.length()));
/* 653 */     System.arraycopy(this.widths, i + 1, widths2, i + 2, this.widths.length - i - 1);
/*     */     
/* 655 */     this.unicode = sb.toString();
/* 656 */     this.widths = widths2;
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
/*     */   private String combineDiacritic(String str) {
/* 669 */     int codePoint = str.codePointAt(0);
/*     */ 
/*     */     
/* 672 */     if (DIACRITICS.containsKey(Integer.valueOf(codePoint)))
/*     */     {
/* 674 */       return DIACRITICS.get(Integer.valueOf(codePoint));
/*     */     }
/*     */ 
/*     */     
/* 678 */     return Normalizer.normalize(str, Normalizer.Form.NFKC).trim();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDiacritic() {
/* 687 */     String text = getUnicode();
/* 688 */     if (text.length() != 1)
/*     */     {
/* 690 */       return false;
/*     */     }
/* 692 */     if ("ー".equals(text))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 698 */       return false;
/*     */     }
/* 700 */     int type = Character.getType(text.charAt(0));
/* 701 */     return (type == 6 || type == 27 || type == 4);
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
/*     */   public String toString() {
/* 715 */     return getUnicode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getEndX() {
/* 726 */     return this.endX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getEndY() {
/* 737 */     return this.endY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRotation() {
/* 748 */     return this.rotation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPageHeight() {
/* 759 */     return this.pageHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPageWidth() {
/* 770 */     return this.pageWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 776 */     if (this == o)
/*     */     {
/* 778 */       return true;
/*     */     }
/* 780 */     if (!(o instanceof TextPosition))
/*     */     {
/* 782 */       return false;
/*     */     }
/*     */     
/* 785 */     TextPosition that = (TextPosition)o;
/*     */     
/* 787 */     if (Float.compare(that.endX, this.endX) != 0)
/*     */     {
/* 789 */       return false;
/*     */     }
/* 791 */     if (Float.compare(that.endY, this.endY) != 0)
/*     */     {
/* 793 */       return false;
/*     */     }
/* 795 */     if (Float.compare(that.maxHeight, this.maxHeight) != 0)
/*     */     {
/* 797 */       return false;
/*     */     }
/* 799 */     if (this.rotation != that.rotation)
/*     */     {
/* 801 */       return false;
/*     */     }
/* 803 */     if (Float.compare(that.x, this.x) != 0)
/*     */     {
/* 805 */       return false;
/*     */     }
/* 807 */     if (Float.compare(that.y, this.y) != 0)
/*     */     {
/* 809 */       return false;
/*     */     }
/* 811 */     if (Float.compare(that.pageHeight, this.pageHeight) != 0)
/*     */     {
/* 813 */       return false;
/*     */     }
/* 815 */     if (Float.compare(that.pageWidth, this.pageWidth) != 0)
/*     */     {
/* 817 */       return false;
/*     */     }
/* 819 */     if (Float.compare(that.widthOfSpace, this.widthOfSpace) != 0)
/*     */     {
/* 821 */       return false;
/*     */     }
/* 823 */     if (Float.compare(that.fontSize, this.fontSize) != 0)
/*     */     {
/* 825 */       return false;
/*     */     }
/* 827 */     if (this.fontSizePt != that.fontSizePt)
/*     */     {
/* 829 */       return false;
/*     */     }
/* 831 */     if (Float.compare(that.direction, this.direction) != 0)
/*     */     {
/* 833 */       return false;
/*     */     }
/* 835 */     if ((this.textMatrix != null) ? !this.textMatrix.equals(that.textMatrix) : (that.textMatrix != null))
/*     */     {
/* 837 */       return false;
/*     */     }
/* 839 */     if (!Arrays.equals(this.charCodes, that.charCodes))
/*     */     {
/* 841 */       return false;
/*     */     }
/* 843 */     if ((this.font != null) ? !this.font.equals(that.font) : (that.font != null))
/*     */     {
/* 845 */       return false;
/*     */     }
/* 847 */     if (!Arrays.equals(this.widths, that.widths))
/*     */     {
/* 849 */       return false;
/*     */     }
/* 851 */     return (this.unicode != null) ? this.unicode.equals(that.unicode) : ((that.unicode == null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 858 */     int result = (this.textMatrix != null) ? this.textMatrix.hashCode() : 0;
/* 859 */     result = 31 * result + Float.floatToIntBits(this.endX);
/* 860 */     result = 31 * result + Float.floatToIntBits(this.endY);
/* 861 */     result = 31 * result + Float.floatToIntBits(this.maxHeight);
/* 862 */     result = 31 * result + this.rotation;
/* 863 */     result = 31 * result + Float.floatToIntBits(this.x);
/* 864 */     result = 31 * result + Float.floatToIntBits(this.y);
/* 865 */     result = 31 * result + Float.floatToIntBits(this.pageHeight);
/* 866 */     result = 31 * result + Float.floatToIntBits(this.pageWidth);
/* 867 */     result = 31 * result + Float.floatToIntBits(this.widthOfSpace);
/* 868 */     result = 31 * result + Arrays.hashCode(this.charCodes);
/* 869 */     result = 31 * result + ((this.font != null) ? this.font.hashCode() : 0);
/* 870 */     result = 31 * result + Float.floatToIntBits(this.fontSize);
/* 871 */     result = 31 * result + this.fontSizePt;
/* 872 */     return result;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/text/TextPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */