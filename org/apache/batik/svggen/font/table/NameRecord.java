/*    */ package org.apache.batik.svggen.font.table;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NameRecord
/*    */ {
/*    */   private short platformId;
/*    */   private short encodingId;
/*    */   private short languageId;
/*    */   private short nameId;
/*    */   private short stringLength;
/*    */   private short stringOffset;
/*    */   private String record;
/*    */   
/*    */   protected NameRecord(RandomAccessFile raf) throws IOException {
/* 39 */     this.platformId = raf.readShort();
/* 40 */     this.encodingId = raf.readShort();
/* 41 */     this.languageId = raf.readShort();
/* 42 */     this.nameId = raf.readShort();
/* 43 */     this.stringLength = raf.readShort();
/* 44 */     this.stringOffset = raf.readShort();
/*    */   }
/*    */   
/*    */   public short getEncodingId() {
/* 48 */     return this.encodingId;
/*    */   }
/*    */   
/*    */   public short getLanguageId() {
/* 52 */     return this.languageId;
/*    */   }
/*    */   
/*    */   public short getNameId() {
/* 56 */     return this.nameId;
/*    */   }
/*    */   
/*    */   public short getPlatformId() {
/* 60 */     return this.platformId;
/*    */   }
/*    */   
/*    */   public String getRecordString() {
/* 64 */     return this.record;
/*    */   }
/*    */   
/*    */   protected void loadString(RandomAccessFile raf, int stringStorageOffset) throws IOException {
/* 68 */     StringBuffer sb = new StringBuffer();
/* 69 */     raf.seek((stringStorageOffset + this.stringOffset));
/* 70 */     if (this.platformId == 0) {
/*    */ 
/*    */       
/* 73 */       for (int i = 0; i < this.stringLength / 2; i++) {
/* 74 */         sb.append(raf.readChar());
/*    */       }
/* 76 */     } else if (this.platformId == 1) {
/*    */ 
/*    */       
/* 79 */       for (int i = 0; i < this.stringLength; i++) {
/* 80 */         sb.append((char)raf.readByte());
/*    */       }
/* 82 */     } else if (this.platformId == 2) {
/*    */ 
/*    */       
/* 85 */       for (int i = 0; i < this.stringLength; i++) {
/* 86 */         sb.append((char)raf.readByte());
/*    */       }
/* 88 */     } else if (this.platformId == 3) {
/*    */ 
/*    */ 
/*    */       
/* 92 */       for (int i = 0; i < this.stringLength / 2; i++) {
/* 93 */         char c = raf.readChar();
/* 94 */         sb.append(c);
/*    */       } 
/*    */     } 
/* 97 */     this.record = sb.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/NameRecord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */