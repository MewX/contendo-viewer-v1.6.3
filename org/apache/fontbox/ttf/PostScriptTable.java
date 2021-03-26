/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ public class PostScriptTable
/*     */   extends TTFTable
/*     */ {
/*  30 */   private static final Log LOG = LogFactory.getLog(PostScriptTable.class);
/*     */   private float formatType;
/*     */   private float italicAngle;
/*     */   private short underlinePosition;
/*     */   private short underlineThickness;
/*     */   private long isFixedPitch;
/*     */   private long minMemType42;
/*     */   private long maxMemType42;
/*     */   private long mimMemType1;
/*     */   private long maxMemType1;
/*  40 */   private String[] glyphNames = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TAG = "post";
/*     */ 
/*     */ 
/*     */   
/*     */   PostScriptTable(TrueTypeFont font) {
/*  49 */     super(font);
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
/*     */   public void read(TrueTypeFont ttf, TTFDataStream data) throws IOException {
/*  62 */     this.formatType = data.read32Fixed();
/*  63 */     this.italicAngle = data.read32Fixed();
/*  64 */     this.underlinePosition = data.readSignedShort();
/*  65 */     this.underlineThickness = data.readSignedShort();
/*  66 */     this.isFixedPitch = data.readUnsignedInt();
/*  67 */     this.minMemType42 = data.readUnsignedInt();
/*  68 */     this.maxMemType42 = data.readUnsignedInt();
/*  69 */     this.mimMemType1 = data.readUnsignedInt();
/*  70 */     this.maxMemType1 = data.readUnsignedInt();
/*     */     
/*  72 */     if (this.formatType == 1.0F) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  77 */       this.glyphNames = new String[258];
/*  78 */       System.arraycopy(WGL4Names.MAC_GLYPH_NAMES, 0, this.glyphNames, 0, 258);
/*     */     }
/*  80 */     else if (this.formatType == 2.0F) {
/*     */       
/*  82 */       int numGlyphs = data.readUnsignedShort();
/*  83 */       int[] glyphNameIndex = new int[numGlyphs];
/*  84 */       this.glyphNames = new String[numGlyphs];
/*  85 */       int maxIndex = Integer.MIN_VALUE;
/*  86 */       for (int i = 0; i < numGlyphs; i++) {
/*     */         
/*  88 */         int index = data.readUnsignedShort();
/*  89 */         glyphNameIndex[i] = index;
/*     */ 
/*     */         
/*  92 */         if (index <= 32767)
/*     */         {
/*  94 */           maxIndex = Math.max(maxIndex, index);
/*     */         }
/*     */       } 
/*  97 */       String[] nameArray = null;
/*  98 */       if (maxIndex >= 258) {
/*     */         
/* 100 */         nameArray = new String[maxIndex - 258 + 1];
/* 101 */         for (int k = 0; k < maxIndex - 258 + 1; k++) {
/*     */           
/* 103 */           int numberOfChars = data.readUnsignedByte();
/* 104 */           nameArray[k] = data.readString(numberOfChars);
/*     */         } 
/*     */       } 
/* 107 */       for (int j = 0; j < numGlyphs; j++) {
/*     */         
/* 109 */         int index = glyphNameIndex[j];
/* 110 */         if (index < 258)
/*     */         {
/* 112 */           this.glyphNames[j] = WGL4Names.MAC_GLYPH_NAMES[index];
/*     */         }
/* 114 */         else if (index >= 258 && index <= 32767)
/*     */         {
/* 116 */           this.glyphNames[j] = nameArray[index - 258];
/*     */         
/*     */         }
/*     */         else
/*     */         {
/*     */           
/* 122 */           this.glyphNames[j] = ".undefined";
/*     */         }
/*     */       
/*     */       } 
/* 126 */     } else if (this.formatType == 2.5F) {
/*     */       
/* 128 */       int[] glyphNameIndex = new int[ttf.getNumberOfGlyphs()]; int i;
/* 129 */       for (i = 0; i < glyphNameIndex.length; i++) {
/*     */         
/* 131 */         int offset = data.readSignedByte();
/* 132 */         glyphNameIndex[i] = i + 1 + offset;
/*     */       } 
/* 134 */       this.glyphNames = new String[glyphNameIndex.length];
/* 135 */       for (i = 0; i < this.glyphNames.length; i++)
/*     */       {
/* 137 */         String name = WGL4Names.MAC_GLYPH_NAMES[glyphNameIndex[i]];
/* 138 */         if (name != null)
/*     */         {
/* 140 */           this.glyphNames[i] = name;
/*     */         }
/*     */       }
/*     */     
/*     */     }
/* 145 */     else if (this.formatType == 3.0F) {
/*     */ 
/*     */       
/* 148 */       LOG.debug("No PostScript name information is provided for the font " + this.font.getName());
/*     */     } 
/* 150 */     this.initialized = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFormatType() {
/* 158 */     return this.formatType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormatType(float formatTypeValue) {
/* 166 */     this.formatType = formatTypeValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getIsFixedPitch() {
/* 174 */     return this.isFixedPitch;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsFixedPitch(long isFixedPitchValue) {
/* 182 */     this.isFixedPitch = isFixedPitchValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getItalicAngle() {
/* 190 */     return this.italicAngle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItalicAngle(float italicAngleValue) {
/* 198 */     this.italicAngle = italicAngleValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMaxMemType1() {
/* 206 */     return this.maxMemType1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxMemType1(long maxMemType1Value) {
/* 214 */     this.maxMemType1 = maxMemType1Value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMaxMemType42() {
/* 222 */     return this.maxMemType42;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxMemType42(long maxMemType42Value) {
/* 230 */     this.maxMemType42 = maxMemType42Value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMinMemType1() {
/* 238 */     return this.mimMemType1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMimMemType1(long mimMemType1Value) {
/* 246 */     this.mimMemType1 = mimMemType1Value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMinMemType42() {
/* 254 */     return this.minMemType42;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMinMemType42(long minMemType42Value) {
/* 262 */     this.minMemType42 = minMemType42Value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getUnderlinePosition() {
/* 270 */     return this.underlinePosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnderlinePosition(short underlinePositionValue) {
/* 278 */     this.underlinePosition = underlinePositionValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getUnderlineThickness() {
/* 286 */     return this.underlineThickness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnderlineThickness(short underlineThicknessValue) {
/* 294 */     this.underlineThickness = underlineThicknessValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getGlyphNames() {
/* 302 */     return this.glyphNames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlyphNames(String[] glyphNamesValue) {
/* 310 */     this.glyphNames = glyphNamesValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName(int gid) {
/* 318 */     if (gid < 0 || this.glyphNames == null || gid >= this.glyphNames.length)
/*     */     {
/* 320 */       return null;
/*     */     }
/* 322 */     return this.glyphNames[gid];
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/PostScriptTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */