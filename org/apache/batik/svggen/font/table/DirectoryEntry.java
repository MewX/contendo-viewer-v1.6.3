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
/*    */ public class DirectoryEntry
/*    */ {
/*    */   private int tag;
/*    */   private int checksum;
/*    */   private int offset;
/*    */   private int length;
/* 34 */   private Table table = null;
/*    */   
/*    */   protected DirectoryEntry(RandomAccessFile raf) throws IOException {
/* 37 */     this.tag = raf.readInt();
/* 38 */     this.checksum = raf.readInt();
/* 39 */     this.offset = raf.readInt();
/* 40 */     this.length = raf.readInt();
/*    */   }
/*    */   
/*    */   public int getChecksum() {
/* 44 */     return this.checksum;
/*    */   }
/*    */   
/*    */   public int getLength() {
/* 48 */     return this.length;
/*    */   }
/*    */   
/*    */   public int getOffset() {
/* 52 */     return this.offset;
/*    */   }
/*    */   
/*    */   public int getTag() {
/* 56 */     return this.tag;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 60 */     return (char)(this.tag >> 24 & 0xFF) + (char)(this.tag >> 16 & 0xFF) + (char)(this.tag >> 8 & 0xFF) + (char)(this.tag & 0xFF) + ", offset: " + this.offset + ", length: " + this.length + ", checksum: 0x" + Integer.toHexString(this.checksum);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/DirectoryEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */