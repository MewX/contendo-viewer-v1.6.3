/*    */ package org.apache.fontbox.cff;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ public class CFFDataInput
/*    */   extends DataInput
/*    */ {
/*    */   public CFFDataInput(byte[] buffer) {
/* 35 */     super(buffer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int readCard8() throws IOException {
/* 45 */     return readUnsignedByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int readCard16() throws IOException {
/* 55 */     return readUnsignedShort();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int readOffset(int offSize) throws IOException {
/* 66 */     int value = 0;
/* 67 */     for (int i = 0; i < offSize; i++)
/*    */     {
/* 69 */       value = value << 8 | readUnsignedByte();
/*    */     }
/* 71 */     return value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int readOffSize() throws IOException {
/* 81 */     return readUnsignedByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int readSID() throws IOException {
/* 91 */     return readUnsignedShort();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/CFFDataInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */