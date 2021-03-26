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
/*    */ public class NameTable
/*    */   implements Table
/*    */ {
/*    */   private short formatSelector;
/*    */   private short numberOfNameRecords;
/*    */   private short stringStorageOffset;
/*    */   private NameRecord[] records;
/*    */   
/*    */   protected NameTable(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/* 36 */     raf.seek(de.getOffset());
/* 37 */     this.formatSelector = raf.readShort();
/* 38 */     this.numberOfNameRecords = raf.readShort();
/* 39 */     this.stringStorageOffset = raf.readShort();
/* 40 */     this.records = new NameRecord[this.numberOfNameRecords];
/*    */     
/*    */     int i;
/* 43 */     for (i = 0; i < this.numberOfNameRecords; i++) {
/* 44 */       this.records[i] = new NameRecord(raf);
/*    */     }
/*    */ 
/*    */     
/* 48 */     for (i = 0; i < this.numberOfNameRecords; i++) {
/* 49 */       this.records[i].loadString(raf, de.getOffset() + this.stringStorageOffset);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getRecord(short nameId) {
/* 56 */     for (int i = 0; i < this.numberOfNameRecords; i++) {
/* 57 */       if (this.records[i].getNameId() == nameId) {
/* 58 */         return this.records[i].getRecordString();
/*    */       }
/*    */     } 
/* 61 */     return "";
/*    */   }
/*    */   
/*    */   public int getType() {
/* 65 */     return 1851878757;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/NameTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */