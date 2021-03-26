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
/*    */ public abstract class ClassDef
/*    */ {
/*    */   public abstract int getFormat();
/*    */   
/*    */   protected static ClassDef read(RandomAccessFile raf) throws IOException {
/* 34 */     ClassDef c = null;
/* 35 */     int format = raf.readUnsignedShort();
/* 36 */     if (format == 1) {
/* 37 */       c = new ClassDefFormat1(raf);
/* 38 */     } else if (format == 2) {
/* 39 */       c = new ClassDefFormat2(raf);
/*    */     } 
/* 41 */     return c;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/ClassDef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */