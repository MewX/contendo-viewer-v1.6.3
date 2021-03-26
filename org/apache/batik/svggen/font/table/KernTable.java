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
/*    */ 
/*    */ public class KernTable
/*    */   implements Table
/*    */ {
/*    */   private int version;
/*    */   private int nTables;
/*    */   private KernSubtable[] tables;
/*    */   
/*    */   protected KernTable(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/* 37 */     raf.seek(de.getOffset());
/* 38 */     this.version = raf.readUnsignedShort();
/* 39 */     this.nTables = raf.readUnsignedShort();
/* 40 */     this.tables = new KernSubtable[this.nTables];
/* 41 */     for (int i = 0; i < this.nTables; i++) {
/* 42 */       this.tables[i] = KernSubtable.read(raf);
/*    */     }
/*    */   }
/*    */   
/*    */   public int getSubtableCount() {
/* 47 */     return this.nTables;
/*    */   }
/*    */   
/*    */   public KernSubtable getSubtable(int i) {
/* 51 */     return this.tables[i];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getType() {
/* 58 */     return 1801810542;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/KernTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */