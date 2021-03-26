/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSName;
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
/*     */ public abstract class PDCIEDictionaryBasedColorSpace
/*     */   extends PDCIEBasedColorSpace
/*     */ {
/*     */   protected COSDictionary dictionary;
/*  35 */   private static final ColorSpace CIEXYZ = ColorSpace.getInstance(1001);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   protected float wpX = 1.0F;
/*  41 */   protected float wpY = 1.0F;
/*  42 */   protected float wpZ = 1.0F;
/*     */ 
/*     */   
/*     */   protected PDCIEDictionaryBasedColorSpace(COSName cosName) {
/*  46 */     this.array = new COSArray();
/*  47 */     this.dictionary = new COSDictionary();
/*  48 */     this.array.add((COSBase)cosName);
/*  49 */     this.array.add((COSBase)this.dictionary);
/*     */     
/*  51 */     fillWhitepointCache(getWhitepoint());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PDCIEDictionaryBasedColorSpace(COSArray rgb) {
/*  61 */     this.array = rgb;
/*  62 */     this.dictionary = (COSDictionary)this.array.getObject(1);
/*     */     
/*  64 */     fillWhitepointCache(getWhitepoint());
/*     */   }
/*     */ 
/*     */   
/*     */   private void fillWhitepointCache(PDTristimulus whitepoint) {
/*  69 */     this.wpX = whitepoint.getX();
/*  70 */     this.wpY = whitepoint.getY();
/*  71 */     this.wpZ = whitepoint.getZ();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float[] convXYZtoRGB(float x, float y, float z) {
/*  79 */     if (x < 0.0F)
/*     */     {
/*  81 */       x = 0.0F;
/*     */     }
/*  83 */     if (y < 0.0F)
/*     */     {
/*  85 */       y = 0.0F;
/*     */     }
/*  87 */     if (z < 0.0F)
/*     */     {
/*  89 */       z = 0.0F;
/*     */     }
/*  91 */     return CIEXYZ.toRGB(new float[] { x, y, z });
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
/*     */   public final PDTristimulus getWhitepoint() {
/* 106 */     COSArray wp = (COSArray)this.dictionary.getDictionaryObject(COSName.WHITE_POINT);
/* 107 */     if (wp == null) {
/*     */       
/* 109 */       wp = new COSArray();
/* 110 */       wp.add((COSBase)new COSFloat(1.0F));
/* 111 */       wp.add((COSBase)new COSFloat(1.0F));
/* 112 */       wp.add((COSBase)new COSFloat(1.0F));
/*     */     } 
/* 114 */     return new PDTristimulus(wp);
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
/*     */   public final PDTristimulus getBlackPoint() {
/* 126 */     COSArray bp = (COSArray)this.dictionary.getDictionaryObject(COSName.BLACK_POINT);
/* 127 */     if (bp == null) {
/*     */       
/* 129 */       bp = new COSArray();
/* 130 */       bp.add((COSBase)new COSFloat(0.0F));
/* 131 */       bp.add((COSBase)new COSFloat(0.0F));
/* 132 */       bp.add((COSBase)new COSFloat(0.0F));
/*     */     } 
/* 134 */     return new PDTristimulus(bp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWhitePoint(PDTristimulus whitepoint) {
/* 145 */     COSBase wpArray = whitepoint.getCOSObject();
/* 146 */     if (wpArray != null)
/*     */     {
/* 148 */       this.dictionary.setItem(COSName.WHITE_POINT, wpArray);
/*     */     }
/* 150 */     fillWhitepointCache(whitepoint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlackPoint(PDTristimulus blackpoint) {
/* 161 */     COSBase bpArray = null;
/* 162 */     if (blackpoint != null)
/*     */     {
/* 164 */       bpArray = blackpoint.getCOSObject();
/*     */     }
/* 166 */     this.dictionary.setItem(COSName.BLACK_POINT, bpArray);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDCIEDictionaryBasedColorSpace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */