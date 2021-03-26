/*     */ package org.apache.xmlgraphics.image.codec.png;
/*     */ 
/*     */ import java.io.DataInputStream;
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
/*     */ 
/*     */ 
/*     */ public class PNGChunk
/*     */ {
/*     */   int length;
/*     */   int type;
/*     */   byte[] data;
/*     */   int crc;
/*     */   String typeString;
/*  36 */   protected static final Log log = LogFactory.getLog(PNGChunk.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum ChunkType
/*     */   {
/*  43 */     IHDR,
/*  44 */     PLTE,
/*  45 */     IDAT,
/*  46 */     IEND,
/*  47 */     bKGD,
/*  48 */     cHRM,
/*  49 */     gAMA,
/*  50 */     hIST,
/*  51 */     iCCP,
/*  52 */     iTXt,
/*  53 */     pHYs,
/*  54 */     sBIT,
/*  55 */     sPLT,
/*  56 */     sRGB,
/*  57 */     sTER,
/*  58 */     tEXt,
/*  59 */     tIME,
/*  60 */     tRNS,
/*  61 */     zTXt;
/*     */   }
/*     */   
/*     */   public PNGChunk(int length, int type, byte[] data, int crc) {
/*  65 */     this.length = length;
/*  66 */     this.type = type;
/*  67 */     this.data = data;
/*  68 */     this.crc = crc;
/*  69 */     this.typeString = typeIntToString(this.type);
/*     */   }
/*     */   
/*     */   public int getLength() {
/*  73 */     return this.length;
/*     */   }
/*     */   
/*     */   public int getType() {
/*  77 */     return this.type;
/*     */   }
/*     */   
/*     */   public String getTypeString() {
/*  81 */     return this.typeString;
/*     */   }
/*     */   
/*     */   public byte[] getData() {
/*  85 */     return this.data;
/*     */   }
/*     */   
/*     */   public byte getByte(int offset) {
/*  89 */     return this.data[offset];
/*     */   }
/*     */   
/*     */   public int getInt1(int offset) {
/*  93 */     return this.data[offset] & 0xFF;
/*     */   }
/*     */   
/*     */   public int getInt2(int offset) {
/*  97 */     return (this.data[offset] & 0xFF) << 8 | this.data[offset + 1] & 0xFF;
/*     */   }
/*     */   
/*     */   public int getInt4(int offset) {
/* 101 */     return (this.data[offset] & 0xFF) << 24 | (this.data[offset + 1] & 0xFF) << 16 | (this.data[offset + 2] & 0xFF) << 8 | this.data[offset + 3] & 0xFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getString4(int offset) {
/* 106 */     return "" + (char)this.data[offset] + (char)this.data[offset + 1] + (char)this.data[offset + 2] + (char)this.data[offset + 3];
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isType(String typeName) {
/* 111 */     return this.typeString.equals(typeName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PNGChunk readChunk(DataInputStream distream) {
/*     */     try {
/* 121 */       int length = distream.readInt();
/* 122 */       int type = distream.readInt();
/* 123 */       byte[] data = new byte[length];
/* 124 */       distream.readFully(data);
/* 125 */       int crc = distream.readInt();
/*     */       
/* 127 */       return new PNGChunk(length, type, data, crc);
/* 128 */     } catch (Exception e) {
/* 129 */       e.printStackTrace();
/* 130 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getChunkType(DataInputStream distream) {
/*     */     try {
/* 141 */       distream.mark(8);
/* 142 */       distream.readInt();
/* 143 */       int type = distream.readInt();
/* 144 */       distream.reset();
/*     */       
/* 146 */       return typeIntToString(type);
/* 147 */     } catch (Exception e) {
/* 148 */       e.printStackTrace();
/* 149 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String typeIntToString(int type) {
/* 154 */     String typeString = "";
/* 155 */     typeString = typeString + (char)(type >> 24);
/* 156 */     typeString = typeString + (char)(type >> 16 & 0xFF);
/* 157 */     typeString = typeString + (char)(type >> 8 & 0xFF);
/* 158 */     typeString = typeString + (char)(type & 0xFF);
/* 159 */     return typeString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean skipChunk(DataInputStream distream) {
/*     */     try {
/* 169 */       int length = distream.readInt();
/* 170 */       distream.readInt();
/*     */       
/* 172 */       int skipped = distream.skipBytes(length);
/* 173 */       distream.readInt();
/* 174 */       if (skipped != length) {
/* 175 */         log.warn("Incorrect number of bytes skipped.");
/* 176 */         return false;
/*     */       } 
/* 178 */       return true;
/* 179 */     } catch (Exception e) {
/* 180 */       log.warn(e.getMessage());
/* 181 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/png/PNGChunk.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */