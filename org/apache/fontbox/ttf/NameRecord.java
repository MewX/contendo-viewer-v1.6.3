/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NameRecord
/*     */ {
/*     */   public static final int PLATFORM_UNICODE = 0;
/*     */   public static final int PLATFORM_MACINTOSH = 1;
/*     */   public static final int PLATFORM_ISO = 2;
/*     */   public static final int PLATFORM_WINDOWS = 3;
/*     */   public static final int ENCODING_UNICODE_1_0 = 0;
/*     */   public static final int ENCODING_UNICODE_1_1 = 1;
/*     */   public static final int ENCODING_UNICODE_2_0_BMP = 3;
/*     */   public static final int ENCODING_UNICODE_2_0_FULL = 4;
/*     */   public static final int LANGUGAE_UNICODE = 0;
/*     */   public static final int ENCODING_WINDOWS_SYMBOL = 0;
/*     */   public static final int ENCODING_WINDOWS_UNICODE_BMP = 1;
/*     */   public static final int ENCODING_WINDOWS_UNICODE_UCS4 = 10;
/*     */   public static final int LANGUGAE_WINDOWS_EN_US = 1033;
/*     */   public static final int ENCODING_MACINTOSH_ROMAN = 0;
/*     */   public static final int LANGUGAE_MACINTOSH_ENGLISH = 0;
/*     */   public static final int NAME_COPYRIGHT = 0;
/*     */   public static final int NAME_FONT_FAMILY_NAME = 1;
/*     */   public static final int NAME_FONT_SUB_FAMILY_NAME = 2;
/*     */   public static final int NAME_UNIQUE_FONT_ID = 3;
/*     */   public static final int NAME_FULL_FONT_NAME = 4;
/*     */   public static final int NAME_VERSION = 5;
/*     */   public static final int NAME_POSTSCRIPT_NAME = 6;
/*     */   public static final int NAME_TRADEMARK = 7;
/*     */   private int platformId;
/*     */   private int platformEncodingId;
/*     */   private int languageId;
/*     */   private int nameId;
/*     */   private int stringLength;
/*     */   private int stringOffset;
/*     */   private String string;
/*     */   
/*     */   public int getStringLength() {
/*  80 */     return this.stringLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStringLength(int stringLengthValue) {
/*  87 */     this.stringLength = stringLengthValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStringOffset() {
/*  94 */     return this.stringOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStringOffset(int stringOffsetValue) {
/* 101 */     this.stringOffset = stringOffsetValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLanguageId() {
/* 109 */     return this.languageId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLanguageId(int languageIdValue) {
/* 116 */     this.languageId = languageIdValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNameId() {
/* 123 */     return this.nameId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNameId(int nameIdValue) {
/* 130 */     this.nameId = nameIdValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPlatformEncodingId() {
/* 137 */     return this.platformEncodingId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlatformEncodingId(int platformEncodingIdValue) {
/* 144 */     this.platformEncodingId = platformEncodingIdValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPlatformId() {
/* 151 */     return this.platformId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlatformId(int platformIdValue) {
/* 158 */     this.platformId = platformIdValue;
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
/*     */   public void initData(TrueTypeFont ttf, TTFDataStream data) throws IOException {
/* 170 */     this.platformId = data.readUnsignedShort();
/* 171 */     this.platformEncodingId = data.readUnsignedShort();
/* 172 */     this.languageId = data.readUnsignedShort();
/* 173 */     this.nameId = data.readUnsignedShort();
/* 174 */     this.stringLength = data.readUnsignedShort();
/* 175 */     this.stringOffset = data.readUnsignedShort();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 185 */     return "platform=" + this.platformId + " pEncoding=" + this.platformEncodingId + " language=" + this.languageId + " name=" + this.nameId + " " + this.string;
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
/*     */   public String getString() {
/* 197 */     return this.string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setString(String stringValue) {
/* 204 */     this.string = stringValue;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/NameRecord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */