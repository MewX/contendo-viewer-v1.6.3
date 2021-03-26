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
/*    */ public abstract class SingleSubst
/*    */   extends LookupSubtable
/*    */ {
/*    */   public abstract int getFormat();
/*    */   
/*    */   public abstract int substitute(int paramInt);
/*    */   
/*    */   public static SingleSubst read(RandomAccessFile raf, int offset) throws IOException {
/* 36 */     SingleSubst s = null;
/* 37 */     raf.seek(offset);
/* 38 */     int format = raf.readUnsignedShort();
/* 39 */     if (format == 1) {
/* 40 */       s = new SingleSubstFormat1(raf, offset);
/* 41 */     } else if (format == 2) {
/* 42 */       s = new SingleSubstFormat2(raf, offset);
/*    */     } 
/* 44 */     return s;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/SingleSubst.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */