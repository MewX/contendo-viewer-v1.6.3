/*     */ package org.apache.batik.svggen.font.table;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Os2Table
/*     */   implements Table
/*     */ {
/*     */   private int version;
/*     */   private short xAvgCharWidth;
/*     */   private int usWeightClass;
/*     */   private int usWidthClass;
/*     */   private short fsType;
/*     */   private short ySubscriptXSize;
/*     */   private short ySubscriptYSize;
/*     */   private short ySubscriptXOffset;
/*     */   private short ySubscriptYOffset;
/*     */   private short ySuperscriptXSize;
/*     */   private short ySuperscriptYSize;
/*     */   private short ySuperscriptXOffset;
/*     */   private short ySuperscriptYOffset;
/*     */   private short yStrikeoutSize;
/*     */   private short yStrikeoutPosition;
/*     */   private short sFamilyClass;
/*     */   private Panose panose;
/*     */   private int ulUnicodeRange1;
/*     */   private int ulUnicodeRange2;
/*     */   private int ulUnicodeRange3;
/*     */   private int ulUnicodeRange4;
/*     */   private int achVendorID;
/*     */   private short fsSelection;
/*     */   private int usFirstCharIndex;
/*     */   private int usLastCharIndex;
/*     */   private short sTypoAscender;
/*     */   private short sTypoDescender;
/*     */   private short sTypoLineGap;
/*     */   private int usWinAscent;
/*     */   private int usWinDescent;
/*     */   private int ulCodePageRange1;
/*     */   private int ulCodePageRange2;
/*     */   
/*     */   protected Os2Table(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/*  64 */     raf.seek(de.getOffset());
/*  65 */     this.version = raf.readUnsignedShort();
/*  66 */     this.xAvgCharWidth = raf.readShort();
/*  67 */     this.usWeightClass = raf.readUnsignedShort();
/*  68 */     this.usWidthClass = raf.readUnsignedShort();
/*  69 */     this.fsType = raf.readShort();
/*  70 */     this.ySubscriptXSize = raf.readShort();
/*  71 */     this.ySubscriptYSize = raf.readShort();
/*  72 */     this.ySubscriptXOffset = raf.readShort();
/*  73 */     this.ySubscriptYOffset = raf.readShort();
/*  74 */     this.ySuperscriptXSize = raf.readShort();
/*  75 */     this.ySuperscriptYSize = raf.readShort();
/*  76 */     this.ySuperscriptXOffset = raf.readShort();
/*  77 */     this.ySuperscriptYOffset = raf.readShort();
/*  78 */     this.yStrikeoutSize = raf.readShort();
/*  79 */     this.yStrikeoutPosition = raf.readShort();
/*  80 */     this.sFamilyClass = raf.readShort();
/*  81 */     byte[] buf = new byte[10];
/*  82 */     raf.read(buf);
/*  83 */     this.panose = new Panose(buf);
/*  84 */     this.ulUnicodeRange1 = raf.readInt();
/*  85 */     this.ulUnicodeRange2 = raf.readInt();
/*  86 */     this.ulUnicodeRange3 = raf.readInt();
/*  87 */     this.ulUnicodeRange4 = raf.readInt();
/*  88 */     this.achVendorID = raf.readInt();
/*  89 */     this.fsSelection = raf.readShort();
/*  90 */     this.usFirstCharIndex = raf.readUnsignedShort();
/*  91 */     this.usLastCharIndex = raf.readUnsignedShort();
/*  92 */     this.sTypoAscender = raf.readShort();
/*  93 */     this.sTypoDescender = raf.readShort();
/*  94 */     this.sTypoLineGap = raf.readShort();
/*  95 */     this.usWinAscent = raf.readUnsignedShort();
/*  96 */     this.usWinDescent = raf.readUnsignedShort();
/*  97 */     this.ulCodePageRange1 = raf.readInt();
/*  98 */     this.ulCodePageRange2 = raf.readInt();
/*     */   }
/*     */   
/*     */   public int getVersion() {
/* 102 */     return this.version;
/*     */   }
/*     */   
/*     */   public short getAvgCharWidth() {
/* 106 */     return this.xAvgCharWidth;
/*     */   }
/*     */   
/*     */   public int getWeightClass() {
/* 110 */     return this.usWeightClass;
/*     */   }
/*     */   
/*     */   public int getWidthClass() {
/* 114 */     return this.usWidthClass;
/*     */   }
/*     */   
/*     */   public short getLicenseType() {
/* 118 */     return this.fsType;
/*     */   }
/*     */   
/*     */   public short getSubscriptXSize() {
/* 122 */     return this.ySubscriptXSize;
/*     */   }
/*     */   
/*     */   public short getSubscriptYSize() {
/* 126 */     return this.ySubscriptYSize;
/*     */   }
/*     */   
/*     */   public short getSubscriptXOffset() {
/* 130 */     return this.ySubscriptXOffset;
/*     */   }
/*     */   
/*     */   public short getSubscriptYOffset() {
/* 134 */     return this.ySubscriptYOffset;
/*     */   }
/*     */   
/*     */   public short getSuperscriptXSize() {
/* 138 */     return this.ySuperscriptXSize;
/*     */   }
/*     */   
/*     */   public short getSuperscriptYSize() {
/* 142 */     return this.ySuperscriptYSize;
/*     */   }
/*     */   
/*     */   public short getSuperscriptXOffset() {
/* 146 */     return this.ySuperscriptXOffset;
/*     */   }
/*     */   
/*     */   public short getSuperscriptYOffset() {
/* 150 */     return this.ySuperscriptYOffset;
/*     */   }
/*     */   
/*     */   public short getStrikeoutSize() {
/* 154 */     return this.yStrikeoutSize;
/*     */   }
/*     */   
/*     */   public short getStrikeoutPosition() {
/* 158 */     return this.yStrikeoutPosition;
/*     */   }
/*     */   
/*     */   public short getFamilyClass() {
/* 162 */     return this.sFamilyClass;
/*     */   }
/*     */   
/*     */   public Panose getPanose() {
/* 166 */     return this.panose;
/*     */   }
/*     */   
/*     */   public int getUnicodeRange1() {
/* 170 */     return this.ulUnicodeRange1;
/*     */   }
/*     */   
/*     */   public int getUnicodeRange2() {
/* 174 */     return this.ulUnicodeRange2;
/*     */   }
/*     */   
/*     */   public int getUnicodeRange3() {
/* 178 */     return this.ulUnicodeRange3;
/*     */   }
/*     */   
/*     */   public int getUnicodeRange4() {
/* 182 */     return this.ulUnicodeRange4;
/*     */   }
/*     */   
/*     */   public int getVendorID() {
/* 186 */     return this.achVendorID;
/*     */   }
/*     */   
/*     */   public short getSelection() {
/* 190 */     return this.fsSelection;
/*     */   }
/*     */   
/*     */   public int getFirstCharIndex() {
/* 194 */     return this.usFirstCharIndex;
/*     */   }
/*     */   
/*     */   public int getLastCharIndex() {
/* 198 */     return this.usLastCharIndex;
/*     */   }
/*     */   
/*     */   public short getTypoAscender() {
/* 202 */     return this.sTypoAscender;
/*     */   }
/*     */   
/*     */   public short getTypoDescender() {
/* 206 */     return this.sTypoDescender;
/*     */   }
/*     */   
/*     */   public short getTypoLineGap() {
/* 210 */     return this.sTypoLineGap;
/*     */   }
/*     */   
/*     */   public int getWinAscent() {
/* 214 */     return this.usWinAscent;
/*     */   }
/*     */   
/*     */   public int getWinDescent() {
/* 218 */     return this.usWinDescent;
/*     */   }
/*     */   
/*     */   public int getCodePageRange1() {
/* 222 */     return this.ulCodePageRange1;
/*     */   }
/*     */   
/*     */   public int getCodePageRange2() {
/* 226 */     return this.ulCodePageRange2;
/*     */   }
/*     */   
/*     */   public int getType() {
/* 230 */     return 1330851634;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/Os2Table.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */