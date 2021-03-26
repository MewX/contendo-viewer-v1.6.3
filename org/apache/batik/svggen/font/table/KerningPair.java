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
/*    */ public class KerningPair
/*    */ {
/*    */   private int left;
/*    */   private int right;
/*    */   private short value;
/*    */   
/*    */   protected KerningPair(RandomAccessFile raf) throws IOException {
/* 37 */     this.left = raf.readUnsignedShort();
/* 38 */     this.right = raf.readUnsignedShort();
/* 39 */     this.value = raf.readShort();
/*    */   }
/*    */   
/*    */   public int getLeft() {
/* 43 */     return this.left;
/*    */   }
/*    */   
/*    */   public int getRight() {
/* 47 */     return this.right;
/*    */   }
/*    */   
/*    */   public short getValue() {
/* 51 */     return this.value;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/KerningPair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */