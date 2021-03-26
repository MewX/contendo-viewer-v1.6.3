/*     */ package org.apache.batik.ext.awt.image.codec.png;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class PNGChunk
/*     */ {
/*     */   int length;
/*     */   int type;
/*     */   byte[] data;
/*     */   int crc;
/*     */   final String typeString;
/*     */   
/*     */   PNGChunk(int length, int type, byte[] data, int crc) {
/*  81 */     this.length = length;
/*  82 */     this.type = type;
/*  83 */     this.data = data;
/*  84 */     this.crc = crc;
/*     */     
/*  86 */     this.typeString = "" + (char)(type >>> 24 & 0xFF) + (char)(type >>> 16 & 0xFF) + (char)(type >>> 8 & 0xFF) + (char)(type & 0xFF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/*  94 */     return this.length;
/*     */   }
/*     */   
/*     */   public int getType() {
/*  98 */     return this.type;
/*     */   }
/*     */   
/*     */   public String getTypeString() {
/* 102 */     return this.typeString;
/*     */   }
/*     */   
/*     */   public byte[] getData() {
/* 106 */     return this.data;
/*     */   }
/*     */   
/*     */   public byte getByte(int offset) {
/* 110 */     return this.data[offset];
/*     */   }
/*     */   
/*     */   public int getInt1(int offset) {
/* 114 */     return this.data[offset] & 0xFF;
/*     */   }
/*     */   
/*     */   public int getInt2(int offset) {
/* 118 */     return (this.data[offset] & 0xFF) << 8 | this.data[offset + 1] & 0xFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInt4(int offset) {
/* 123 */     return (this.data[offset] & 0xFF) << 24 | (this.data[offset + 1] & 0xFF) << 16 | (this.data[offset + 2] & 0xFF) << 8 | this.data[offset + 3] & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString4(int offset) {
/* 130 */     return "" + (char)this.data[offset] + (char)this.data[offset + 1] + (char)this.data[offset + 2] + (char)this.data[offset + 3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isType(String typeName) {
/* 138 */     return this.typeString.equals(typeName);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/png/PNGChunk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */