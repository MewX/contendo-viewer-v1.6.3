/*     */ package org.apache.pdfbox.pdmodel.common;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Arrays;
/*     */ import org.apache.fontbox.util.BoundingBox;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSNumber;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDRectangle
/*     */   implements COSObjectable
/*     */ {
/*     */   private static final float POINTS_PER_INCH = 72.0F;
/*     */   private static final float POINTS_PER_MM = 2.8346457F;
/*  45 */   public static final PDRectangle LETTER = new PDRectangle(612.0F, 792.0F);
/*     */ 
/*     */   
/*  48 */   public static final PDRectangle LEGAL = new PDRectangle(612.0F, 1008.0F);
/*     */ 
/*     */   
/*  51 */   public static final PDRectangle A0 = new PDRectangle(2383.937F, 3370.3938F);
/*     */ 
/*     */   
/*  54 */   public static final PDRectangle A1 = new PDRectangle(1683.7795F, 2383.937F);
/*     */ 
/*     */   
/*  57 */   public static final PDRectangle A2 = new PDRectangle(1190.5513F, 1683.7795F);
/*     */ 
/*     */   
/*  60 */   public static final PDRectangle A3 = new PDRectangle(841.8898F, 1190.5513F);
/*     */ 
/*     */   
/*  63 */   public static final PDRectangle A4 = new PDRectangle(595.27563F, 841.8898F);
/*     */ 
/*     */   
/*  66 */   public static final PDRectangle A5 = new PDRectangle(419.52756F, 595.27563F);
/*     */ 
/*     */   
/*  69 */   public static final PDRectangle A6 = new PDRectangle(297.63782F, 419.52756F);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final COSArray rectArray;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle() {
/*  80 */     this(0.0F, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle(float width, float height) {
/*  91 */     this(0.0F, 0.0F, width, height);
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
/*     */   public PDRectangle(float x, float y, float width, float height) {
/* 104 */     this.rectArray = new COSArray();
/* 105 */     this.rectArray.add((COSBase)new COSFloat(x));
/* 106 */     this.rectArray.add((COSBase)new COSFloat(y));
/* 107 */     this.rectArray.add((COSBase)new COSFloat(x + width));
/* 108 */     this.rectArray.add((COSBase)new COSFloat(y + height));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle(BoundingBox box) {
/* 118 */     this.rectArray = new COSArray();
/* 119 */     this.rectArray.add((COSBase)new COSFloat(box.getLowerLeftX()));
/* 120 */     this.rectArray.add((COSBase)new COSFloat(box.getLowerLeftY()));
/* 121 */     this.rectArray.add((COSBase)new COSFloat(box.getUpperRightX()));
/* 122 */     this.rectArray.add((COSBase)new COSFloat(box.getUpperRightY()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle(COSArray array) {
/* 132 */     float[] values = Arrays.copyOf(array.toFloatArray(), 4);
/* 133 */     this.rectArray = new COSArray();
/*     */     
/* 135 */     this.rectArray.add((COSBase)new COSFloat(Math.min(values[0], values[2])));
/* 136 */     this.rectArray.add((COSBase)new COSFloat(Math.min(values[1], values[3])));
/* 137 */     this.rectArray.add((COSBase)new COSFloat(Math.max(values[0], values[2])));
/* 138 */     this.rectArray.add((COSBase)new COSFloat(Math.max(values[1], values[3])));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(float x, float y) {
/* 149 */     float llx = getLowerLeftX();
/* 150 */     float urx = getUpperRightX();
/* 151 */     float lly = getLowerLeftY();
/* 152 */     float ury = getUpperRightY();
/* 153 */     return (x >= llx && x <= urx && y >= lly && y <= ury);
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
/*     */   public PDRectangle createRetranslatedRectangle() {
/* 168 */     PDRectangle retval = new PDRectangle();
/* 169 */     retval.setUpperRightX(getWidth());
/* 170 */     retval.setUpperRightY(getHeight());
/* 171 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getCOSArray() {
/* 181 */     return this.rectArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLowerLeftX() {
/* 191 */     return ((COSNumber)this.rectArray.get(0)).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLowerLeftX(float value) {
/* 201 */     this.rectArray.set(0, (COSBase)new COSFloat(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLowerLeftY() {
/* 211 */     return ((COSNumber)this.rectArray.get(1)).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLowerLeftY(float value) {
/* 221 */     this.rectArray.set(1, (COSBase)new COSFloat(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUpperRightX() {
/* 231 */     return ((COSNumber)this.rectArray.get(2)).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUpperRightX(float value) {
/* 241 */     this.rectArray.set(2, (COSBase)new COSFloat(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUpperRightY() {
/* 251 */     return ((COSNumber)this.rectArray.get(3)).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUpperRightY(float value) {
/* 261 */     this.rectArray.set(3, (COSBase)new COSFloat(value));
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
/* 272 */     return getUpperRightX() - getLowerLeftX();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeight() {
/* 283 */     return getUpperRightY() - getLowerLeftY();
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
/*     */   public GeneralPath transform(Matrix matrix) {
/* 296 */     float x1 = getLowerLeftX();
/* 297 */     float y1 = getLowerLeftY();
/* 298 */     float x2 = getUpperRightX();
/* 299 */     float y2 = getUpperRightY();
/*     */     
/* 301 */     Point2D.Float p0 = matrix.transformPoint(x1, y1);
/* 302 */     Point2D.Float p1 = matrix.transformPoint(x2, y1);
/* 303 */     Point2D.Float p2 = matrix.transformPoint(x2, y2);
/* 304 */     Point2D.Float p3 = matrix.transformPoint(x1, y2);
/*     */     
/* 306 */     GeneralPath path = new GeneralPath();
/* 307 */     path.moveTo(p0.getX(), p0.getY());
/* 308 */     path.lineTo(p1.getX(), p1.getY());
/* 309 */     path.lineTo(p2.getX(), p2.getY());
/* 310 */     path.lineTo(p3.getX(), p3.getY());
/* 311 */     path.closePath();
/* 312 */     return path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/* 323 */     return (COSBase)this.rectArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath toGeneralPath() {
/* 334 */     float x1 = getLowerLeftX();
/* 335 */     float y1 = getLowerLeftY();
/* 336 */     float x2 = getUpperRightX();
/* 337 */     float y2 = getUpperRightY();
/* 338 */     GeneralPath path = new GeneralPath();
/* 339 */     path.moveTo(x1, y1);
/* 340 */     path.lineTo(x2, y1);
/* 341 */     path.lineTo(x2, y2);
/* 342 */     path.lineTo(x1, y2);
/* 343 */     path.closePath();
/* 344 */     return path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 355 */     return "[" + getLowerLeftX() + "," + getLowerLeftY() + "," + 
/* 356 */       getUpperRightX() + "," + getUpperRightY() + "]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/PDRectangle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */