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
/*    */ public abstract class LigatureSubst
/*    */   extends LookupSubtable
/*    */ {
/*    */   public static LigatureSubst read(RandomAccessFile raf, int offset) throws IOException {
/* 32 */     LigatureSubst ls = null;
/* 33 */     raf.seek(offset);
/* 34 */     int format = raf.readUnsignedShort();
/* 35 */     if (format == 1) {
/* 36 */       ls = new LigatureSubstFormat1(raf, offset);
/*    */     }
/* 38 */     return ls;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/LigatureSubst.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */