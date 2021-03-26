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
/*    */ public class Device
/*    */ {
/*    */   private int startSize;
/*    */   private int endSize;
/*    */   private int deltaFormat;
/*    */   private int[] deltaValues;
/*    */   
/*    */   public Device(RandomAccessFile raf) throws IOException {
/* 38 */     this.startSize = raf.readUnsignedShort();
/* 39 */     this.endSize = raf.readUnsignedShort();
/* 40 */     this.deltaFormat = raf.readUnsignedShort();
/* 41 */     int size = this.startSize - this.endSize;
/* 42 */     switch (this.deltaFormat) {
/*    */       case 1:
/* 44 */         size = (size % 8 == 0) ? (size / 8) : (size / 8 + 1);
/*    */         break;
/*    */       case 2:
/* 47 */         size = (size % 4 == 0) ? (size / 4) : (size / 4 + 1);
/*    */         break;
/*    */       case 3:
/* 50 */         size = (size % 2 == 0) ? (size / 2) : (size / 2 + 1);
/*    */         break;
/*    */     } 
/* 53 */     this.deltaValues = new int[size];
/* 54 */     for (int i = 0; i < size; i++)
/* 55 */       this.deltaValues[i] = raf.readUnsignedShort(); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/Device.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */