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
/*    */ public class KernSubtableFormat2
/*    */   extends KernSubtable
/*    */ {
/*    */   private int rowWidth;
/*    */   private int leftClassTable;
/*    */   private int rightClassTable;
/*    */   private int array;
/*    */   
/*    */   protected KernSubtableFormat2(RandomAccessFile raf) throws IOException {
/* 38 */     this.rowWidth = raf.readUnsignedShort();
/* 39 */     this.leftClassTable = raf.readUnsignedShort();
/* 40 */     this.rightClassTable = raf.readUnsignedShort();
/* 41 */     this.array = raf.readUnsignedShort();
/*    */   }
/*    */   
/*    */   public int getKerningPairCount() {
/* 45 */     return 0;
/*    */   }
/*    */   
/*    */   public KerningPair getKerningPair(int i) {
/* 49 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/KernSubtableFormat2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */