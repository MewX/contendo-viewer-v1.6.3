/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
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
/*     */ public final class PDFontDescriptor
/*     */   implements COSObjectable
/*     */ {
/*     */   private static final int FLAG_FIXED_PITCH = 1;
/*     */   private static final int FLAG_SERIF = 2;
/*     */   private static final int FLAG_SYMBOLIC = 4;
/*     */   private static final int FLAG_SCRIPT = 8;
/*     */   private static final int FLAG_NON_SYMBOLIC = 32;
/*     */   private static final int FLAG_ITALIC = 64;
/*     */   private static final int FLAG_ALL_CAP = 65536;
/*     */   private static final int FLAG_SMALL_CAP = 131072;
/*     */   private static final int FLAG_FORCE_BOLD = 262144;
/*     */   private final COSDictionary dic;
/*  47 */   private float xHeight = Float.NEGATIVE_INFINITY;
/*  48 */   private float capHeight = Float.NEGATIVE_INFINITY;
/*  49 */   private int flags = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PDFontDescriptor() {
/*  57 */     this.dic = new COSDictionary();
/*  58 */     this.dic.setItem(COSName.TYPE, (COSBase)COSName.FONT_DESC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFontDescriptor(COSDictionary desc) {
/*  68 */     this.dic = desc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFixedPitch() {
/*  78 */     return isFlagBitOn(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFixedPitch(boolean flag) {
/*  88 */     setFlagBit(1, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSerif() {
/*  98 */     return isFlagBitOn(2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSerif(boolean flag) {
/* 108 */     setFlagBit(2, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSymbolic() {
/* 118 */     return isFlagBitOn(4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSymbolic(boolean flag) {
/* 128 */     setFlagBit(4, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isScript() {
/* 138 */     return isFlagBitOn(8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScript(boolean flag) {
/* 148 */     setFlagBit(8, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNonSymbolic() {
/* 158 */     return isFlagBitOn(32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNonSymbolic(boolean flag) {
/* 168 */     setFlagBit(32, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItalic() {
/* 178 */     return isFlagBitOn(64);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItalic(boolean flag) {
/* 188 */     setFlagBit(64, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAllCap() {
/* 198 */     return isFlagBitOn(65536);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAllCap(boolean flag) {
/* 208 */     setFlagBit(65536, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSmallCap() {
/* 218 */     return isFlagBitOn(131072);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSmallCap(boolean flag) {
/* 228 */     setFlagBit(131072, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isForceBold() {
/* 238 */     return isFlagBitOn(262144);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setForceBold(boolean flag) {
/* 248 */     setFlagBit(262144, flag);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isFlagBitOn(int bit) {
/* 253 */     return ((getFlags() & bit) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setFlagBit(int bit, boolean value) {
/* 258 */     int flags = getFlags();
/* 259 */     if (value) {
/*     */       
/* 261 */       flags |= bit;
/*     */     }
/*     */     else {
/*     */       
/* 265 */       flags &= bit ^ 0xFFFFFFFF;
/*     */     } 
/* 267 */     setFlags(flags);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/* 278 */     return this.dic;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFontName() {
/* 288 */     String retval = null;
/* 289 */     COSBase base = this.dic.getDictionaryObject(COSName.FONT_NAME);
/* 290 */     if (base instanceof COSName)
/*     */     {
/* 292 */       retval = ((COSName)base).getName();
/*     */     }
/* 294 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontName(String fontName) {
/* 304 */     COSName name = null;
/* 305 */     if (fontName != null)
/*     */     {
/* 307 */       name = COSName.getPDFName(fontName);
/*     */     }
/* 309 */     this.dic.setItem(COSName.FONT_NAME, (COSBase)name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFontFamily() {
/* 319 */     String retval = null;
/* 320 */     COSString name = (COSString)this.dic.getDictionaryObject(COSName.FONT_FAMILY);
/* 321 */     if (name != null)
/*     */     {
/* 323 */       retval = name.getString();
/*     */     }
/* 325 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontFamily(String fontFamily) {
/* 335 */     COSString name = null;
/* 336 */     if (fontFamily != null)
/*     */     {
/* 338 */       name = new COSString(fontFamily);
/*     */     }
/* 340 */     this.dic.setItem(COSName.FONT_FAMILY, (COSBase)name);
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
/*     */   public float getFontWeight() {
/* 352 */     return this.dic.getFloat(COSName.FONT_WEIGHT, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontWeight(float fontWeight) {
/* 362 */     this.dic.setFloat(COSName.FONT_WEIGHT, fontWeight);
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
/*     */   public String getFontStretch() {
/* 376 */     String retval = null;
/* 377 */     COSName name = (COSName)this.dic.getDictionaryObject(COSName.FONT_STRETCH);
/* 378 */     if (name != null)
/*     */     {
/* 380 */       retval = name.getName();
/*     */     }
/* 382 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontStretch(String fontStretch) {
/* 392 */     COSName name = null;
/* 393 */     if (fontStretch != null)
/*     */     {
/* 395 */       name = COSName.getPDFName(fontStretch);
/*     */     }
/* 397 */     this.dic.setItem(COSName.FONT_STRETCH, (COSBase)name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 407 */     if (this.flags == -1)
/*     */     {
/* 409 */       this.flags = this.dic.getInt(COSName.FLAGS, 0);
/*     */     }
/* 411 */     return this.flags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int flags) {
/* 421 */     this.dic.setInt(COSName.FLAGS, flags);
/* 422 */     this.flags = flags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle getFontBoundingBox() {
/* 432 */     COSArray rect = (COSArray)this.dic.getDictionaryObject(COSName.FONT_BBOX);
/* 433 */     PDRectangle retval = null;
/* 434 */     if (rect != null)
/*     */     {
/* 436 */       retval = new PDRectangle(rect);
/*     */     }
/* 438 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontBoundingBox(PDRectangle rect) {
/* 448 */     COSArray array = null;
/* 449 */     if (rect != null)
/*     */     {
/* 451 */       array = rect.getCOSArray();
/*     */     }
/* 453 */     this.dic.setItem(COSName.FONT_BBOX, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getItalicAngle() {
/* 463 */     return this.dic.getFloat(COSName.ITALIC_ANGLE, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItalicAngle(float angle) {
/* 473 */     this.dic.setFloat(COSName.ITALIC_ANGLE, angle);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAscent() {
/* 483 */     return this.dic.getFloat(COSName.ASCENT, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAscent(float ascent) {
/* 493 */     this.dic.setFloat(COSName.ASCENT, ascent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDescent() {
/* 503 */     return this.dic.getFloat(COSName.DESCENT, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescent(float descent) {
/* 513 */     this.dic.setFloat(COSName.DESCENT, descent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLeading() {
/* 523 */     return this.dic.getFloat(COSName.LEADING, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeading(float leading) {
/* 533 */     this.dic.setFloat(COSName.LEADING, leading);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCapHeight() {
/* 543 */     if (this.capHeight == Float.NEGATIVE_INFINITY)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 549 */       this.capHeight = Math.abs(this.dic.getFloat(COSName.CAP_HEIGHT, 0.0F));
/*     */     }
/* 551 */     return this.capHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCapHeight(float capHeight) {
/* 562 */     this.dic.setFloat(COSName.CAP_HEIGHT, capHeight);
/* 563 */     this.capHeight = capHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getXHeight() {
/* 573 */     if (this.xHeight == Float.NEGATIVE_INFINITY)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 579 */       this.xHeight = Math.abs(this.dic.getFloat(COSName.XHEIGHT, 0.0F));
/*     */     }
/* 581 */     return this.xHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXHeight(float xHeight) {
/* 591 */     this.dic.setFloat(COSName.XHEIGHT, xHeight);
/* 592 */     this.xHeight = xHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStemV() {
/* 602 */     return this.dic.getFloat(COSName.STEM_V, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStemV(float stemV) {
/* 612 */     this.dic.setFloat(COSName.STEM_V, stemV);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStemH() {
/* 622 */     return this.dic.getFloat(COSName.STEM_H, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStemH(float stemH) {
/* 632 */     this.dic.setFloat(COSName.STEM_H, stemH);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAverageWidth() {
/* 642 */     return this.dic.getFloat(COSName.AVG_WIDTH, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAverageWidth(float averageWidth) {
/* 652 */     this.dic.setFloat(COSName.AVG_WIDTH, averageWidth);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMaxWidth() {
/* 662 */     return this.dic.getFloat(COSName.MAX_WIDTH, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxWidth(float maxWidth) {
/* 672 */     this.dic.setFloat(COSName.MAX_WIDTH, maxWidth);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasWidths() {
/* 680 */     return (this.dic.containsKey(COSName.WIDTHS) || this.dic.containsKey(COSName.MISSING_WIDTH));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasMissingWidth() {
/* 688 */     return this.dic.containsKey(COSName.MISSING_WIDTH);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMissingWidth() {
/* 698 */     return this.dic.getFloat(COSName.MISSING_WIDTH, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMissingWidth(float missingWidth) {
/* 708 */     this.dic.setFloat(COSName.MISSING_WIDTH, missingWidth);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCharSet() {
/* 718 */     String retval = null;
/* 719 */     COSString name = (COSString)this.dic.getDictionaryObject(COSName.CHAR_SET);
/* 720 */     if (name != null)
/*     */     {
/* 722 */       retval = name.getString();
/*     */     }
/* 724 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCharacterSet(String charSet) {
/* 734 */     COSString name = null;
/* 735 */     if (charSet != null)
/*     */     {
/* 737 */       name = new COSString(charSet);
/*     */     }
/* 739 */     this.dic.setItem(COSName.CHAR_SET, (COSBase)name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStream getFontFile() {
/* 749 */     PDStream retval = null;
/* 750 */     COSBase obj = this.dic.getDictionaryObject(COSName.FONT_FILE);
/* 751 */     if (obj instanceof COSStream)
/*     */     {
/* 753 */       retval = new PDStream((COSStream)obj);
/*     */     }
/* 755 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontFile(PDStream type1Stream) {
/* 765 */     this.dic.setItem(COSName.FONT_FILE, (COSObjectable)type1Stream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStream getFontFile2() {
/* 775 */     PDStream retval = null;
/* 776 */     COSBase obj = this.dic.getDictionaryObject(COSName.FONT_FILE2);
/* 777 */     if (obj instanceof COSStream)
/*     */     {
/* 779 */       retval = new PDStream((COSStream)obj);
/*     */     }
/* 781 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontFile2(PDStream ttfStream) {
/* 791 */     this.dic.setItem(COSName.FONT_FILE2, (COSObjectable)ttfStream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStream getFontFile3() {
/* 801 */     PDStream retval = null;
/* 802 */     COSBase obj = this.dic.getDictionaryObject(COSName.FONT_FILE3);
/* 803 */     if (obj instanceof COSStream)
/*     */     {
/* 805 */       retval = new PDStream((COSStream)obj);
/*     */     }
/* 807 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontFile3(PDStream stream) {
/* 817 */     this.dic.setItem(COSName.FONT_FILE3, (COSObjectable)stream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStream getCIDSet() {
/* 827 */     COSBase cOSBase = this.dic.getDictionaryObject(COSName.CID_SET);
/* 828 */     if (cOSBase instanceof COSStream)
/*     */     {
/* 830 */       return new PDStream((COSStream)cOSBase);
/*     */     }
/* 832 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCIDSet(PDStream stream) {
/* 842 */     this.dic.setItem(COSName.CID_SET, (COSObjectable)stream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPanose getPanose() {
/* 852 */     COSDictionary style = (COSDictionary)this.dic.getDictionaryObject(COSName.STYLE);
/* 853 */     if (style != null) {
/*     */       
/* 855 */       COSString panose = (COSString)style.getDictionaryObject(COSName.PANOSE);
/* 856 */       byte[] bytes = panose.getBytes();
/* 857 */       return new PDPanose(bytes);
/*     */     } 
/* 859 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDFontDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */