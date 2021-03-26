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
/*    */ public abstract class CmapFormat
/*    */ {
/*    */   protected int format;
/*    */   protected int length;
/*    */   protected int version;
/*    */   
/*    */   protected CmapFormat(RandomAccessFile raf) throws IOException {
/* 35 */     this.length = raf.readUnsignedShort();
/* 36 */     this.version = raf.readUnsignedShort();
/*    */   }
/*    */ 
/*    */   
/*    */   protected static CmapFormat create(int format, RandomAccessFile raf) throws IOException {
/* 41 */     switch (format) {
/*    */       case 0:
/* 43 */         return new CmapFormat0(raf);
/*    */       case 2:
/* 45 */         return new CmapFormat2(raf);
/*    */       case 4:
/* 47 */         return new CmapFormat4(raf);
/*    */       case 6:
/* 49 */         return new CmapFormat6(raf);
/*    */     } 
/* 51 */     return null;
/*    */   }
/*    */   
/*    */   public int getFormat() {
/* 55 */     return this.format;
/*    */   }
/*    */   
/*    */   public int getLength() {
/* 59 */     return this.length;
/*    */   }
/*    */   
/*    */   public int getVersion() {
/* 63 */     return this.version;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract int mapCharCode(int paramInt);
/*    */ 
/*    */   
/*    */   public String toString() {
/* 72 */     return "format: " + this.format + ", length: " + this.length + ", version: " + this.version;
/*    */   }
/*    */   
/*    */   public abstract int getFirst();
/*    */   
/*    */   public abstract int getLast();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/CmapFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */