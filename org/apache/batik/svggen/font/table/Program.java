/*    */ package org.apache.batik.svggen.font.table;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
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
/*    */ public abstract class Program
/*    */ {
/*    */   private short[] instructions;
/*    */   
/*    */   public short[] getInstructions() {
/* 34 */     return this.instructions;
/*    */   }
/*    */   
/*    */   protected void readInstructions(RandomAccessFile raf, int count) throws IOException {
/* 38 */     this.instructions = new short[count];
/* 39 */     for (int i = 0; i < count; i++) {
/* 40 */       this.instructions[i] = (short)raf.readUnsignedByte();
/*    */     }
/*    */   }
/*    */   
/*    */   protected void readInstructions(ByteArrayInputStream bais, int count) {
/* 45 */     this.instructions = new short[count];
/* 46 */     for (int i = 0; i < count; i++)
/* 47 */       this.instructions[i] = (short)bais.read(); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/Program.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */