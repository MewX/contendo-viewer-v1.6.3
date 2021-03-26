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
/*    */ public class CmapTable
/*    */   implements Table
/*    */ {
/*    */   private int version;
/*    */   private int numTables;
/*    */   private CmapIndexEntry[] entries;
/*    */   private CmapFormat[] formats;
/*    */   
/*    */   protected CmapTable(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/* 36 */     raf.seek(de.getOffset());
/* 37 */     long fp = raf.getFilePointer();
/* 38 */     this.version = raf.readUnsignedShort();
/* 39 */     this.numTables = raf.readUnsignedShort();
/* 40 */     this.entries = new CmapIndexEntry[this.numTables];
/* 41 */     this.formats = new CmapFormat[this.numTables];
/*    */     
/*    */     int i;
/* 44 */     for (i = 0; i < this.numTables; i++) {
/* 45 */       this.entries[i] = new CmapIndexEntry(raf);
/*    */     }
/*    */ 
/*    */     
/* 49 */     for (i = 0; i < this.numTables; i++) {
/* 50 */       raf.seek(fp + this.entries[i].getOffset());
/* 51 */       int format = raf.readUnsignedShort();
/* 52 */       this.formats[i] = CmapFormat.create(format, raf);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CmapFormat getCmapFormat(short platformId, short encodingId) {
/* 59 */     for (int i = 0; i < this.numTables; i++) {
/* 60 */       if (this.entries[i].getPlatformId() == platformId && this.entries[i].getEncodingId() == encodingId)
/*    */       {
/* 62 */         return this.formats[i];
/*    */       }
/*    */     } 
/* 65 */     return null;
/*    */   }
/*    */   
/*    */   public int getType() {
/* 69 */     return 1668112752;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 73 */     StringBuffer sb = (new StringBuffer(this.numTables * 8)).append("cmap\n");
/*    */     
/*    */     int i;
/* 76 */     for (i = 0; i < this.numTables; i++) {
/* 77 */       sb.append('\t').append(this.entries[i].toString()).append('\n');
/*    */     }
/*    */ 
/*    */     
/* 81 */     for (i = 0; i < this.numTables; i++) {
/* 82 */       sb.append('\t').append(this.formats[i].toString()).append('\n');
/*    */     }
/* 84 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/CmapTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */