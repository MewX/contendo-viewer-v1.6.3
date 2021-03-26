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
/*    */ public class TableDirectory
/*    */ {
/* 30 */   private int version = 0;
/* 31 */   private short numTables = 0;
/* 32 */   private short searchRange = 0;
/* 33 */   private short entrySelector = 0;
/* 34 */   private short rangeShift = 0;
/*    */   private DirectoryEntry[] entries;
/*    */   
/*    */   public TableDirectory(RandomAccessFile raf) throws IOException {
/* 38 */     this.version = raf.readInt();
/* 39 */     this.numTables = raf.readShort();
/* 40 */     this.searchRange = raf.readShort();
/* 41 */     this.entrySelector = raf.readShort();
/* 42 */     this.rangeShift = raf.readShort();
/* 43 */     this.entries = new DirectoryEntry[this.numTables];
/* 44 */     for (int i = 0; i < this.numTables; i++) {
/* 45 */       this.entries[i] = new DirectoryEntry(raf);
/*    */     }
/*    */ 
/*    */     
/* 49 */     boolean modified = true;
/* 50 */     while (modified) {
/* 51 */       modified = false;
/* 52 */       for (int j = 0; j < this.numTables - 1; j++) {
/* 53 */         if (this.entries[j].getOffset() > this.entries[j + 1].getOffset()) {
/* 54 */           DirectoryEntry temp = this.entries[j];
/* 55 */           this.entries[j] = this.entries[j + 1];
/* 56 */           this.entries[j + 1] = temp;
/* 57 */           modified = true;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public DirectoryEntry getEntry(int index) {
/* 64 */     return this.entries[index];
/*    */   }
/*    */   
/*    */   public DirectoryEntry getEntryByTag(int tag) {
/* 68 */     for (int i = 0; i < this.numTables; i++) {
/* 69 */       if (this.entries[i].getTag() == tag) {
/* 70 */         return this.entries[i];
/*    */       }
/*    */     } 
/* 73 */     return null;
/*    */   }
/*    */   
/*    */   public short getEntrySelector() {
/* 77 */     return this.entrySelector;
/*    */   }
/*    */   
/*    */   public short getNumTables() {
/* 81 */     return this.numTables;
/*    */   }
/*    */   
/*    */   public short getRangeShift() {
/* 85 */     return this.rangeShift;
/*    */   }
/*    */   
/*    */   public short getSearchRange() {
/* 89 */     return this.searchRange;
/*    */   }
/*    */   
/*    */   public int getVersion() {
/* 93 */     return this.version;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/TableDirectory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */