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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Lookup
/*    */ {
/*    */   public static final int IGNORE_BASE_GLYPHS = 2;
/*    */   public static final int IGNORE_BASE_LIGATURES = 4;
/*    */   public static final int IGNORE_BASE_MARKS = 8;
/*    */   public static final int MARK_ATTACHMENT_TYPE = 65280;
/*    */   private int type;
/*    */   private int flag;
/*    */   private int subTableCount;
/*    */   private int[] subTableOffsets;
/*    */   private LookupSubtable[] subTables;
/*    */   
/*    */   public Lookup(LookupSubtableFactory factory, RandomAccessFile raf, int offset) throws IOException {
/* 46 */     raf.seek(offset);
/* 47 */     this.type = raf.readUnsignedShort();
/* 48 */     this.flag = raf.readUnsignedShort();
/* 49 */     this.subTableCount = raf.readUnsignedShort();
/* 50 */     this.subTableOffsets = new int[this.subTableCount];
/* 51 */     this.subTables = new LookupSubtable[this.subTableCount]; int i;
/* 52 */     for (i = 0; i < this.subTableCount; i++) {
/* 53 */       this.subTableOffsets[i] = raf.readUnsignedShort();
/*    */     }
/* 55 */     for (i = 0; i < this.subTableCount; i++) {
/* 56 */       this.subTables[i] = factory.read(this.type, raf, offset + this.subTableOffsets[i]);
/*    */     }
/*    */   }
/*    */   
/*    */   public int getType() {
/* 61 */     return this.type;
/*    */   }
/*    */   
/*    */   public int getSubtableCount() {
/* 65 */     return this.subTableCount;
/*    */   }
/*    */   
/*    */   public LookupSubtable getSubtable(int i) {
/* 69 */     return this.subTables[i];
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/Lookup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */