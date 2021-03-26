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
/*    */ 
/*    */ public abstract class Coverage
/*    */ {
/*    */   public abstract int getFormat();
/*    */   
/*    */   public abstract int findGlyph(int paramInt);
/*    */   
/*    */   protected static Coverage read(RandomAccessFile raf) throws IOException {
/* 41 */     Coverage c = null;
/* 42 */     int format = raf.readUnsignedShort();
/* 43 */     if (format == 1) {
/* 44 */       c = new CoverageFormat1(raf);
/* 45 */     } else if (format == 2) {
/* 46 */       c = new CoverageFormat2(raf);
/*    */     } 
/* 48 */     return c;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/Coverage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */