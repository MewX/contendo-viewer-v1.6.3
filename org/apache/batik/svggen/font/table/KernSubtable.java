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
/*    */ public abstract class KernSubtable
/*    */ {
/*    */   public abstract int getKerningPairCount();
/*    */   
/*    */   public abstract KerningPair getKerningPair(int paramInt);
/*    */   
/*    */   public static KernSubtable read(RandomAccessFile raf) throws IOException {
/* 40 */     KernSubtable table = null;
/* 41 */     raf.readUnsignedShort();
/* 42 */     raf.readUnsignedShort();
/* 43 */     int coverage = raf.readUnsignedShort();
/* 44 */     int format = coverage >> 8;
/*    */     
/* 46 */     switch (format) {
/*    */       case 0:
/* 48 */         table = new KernSubtableFormat0(raf);
/*    */         break;
/*    */       case 2:
/* 51 */         table = new KernSubtableFormat2(raf);
/*    */         break;
/*    */     } 
/*    */ 
/*    */     
/* 56 */     return table;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/KernSubtable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */