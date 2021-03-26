/*    */ package net.zamasoft.a.b;
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
/*    */ public abstract class R
/*    */ {
/*    */   private short[] a;
/*    */   
/*    */   public short[] b() {
/* 33 */     return this.a;
/*    */   }
/*    */   
/*    */   protected void a(RandomAccessFile raf, int count) throws IOException {
/* 37 */     if (count < 0) {
/*    */       return;
/*    */     }
/* 40 */     this.a = new short[count];
/* 41 */     for (int i = 0; i < count; i++) {
/* 42 */       this.a[i] = (short)raf.readUnsignedByte();
/*    */     }
/*    */   }
/*    */   
/*    */   protected void a(ByteArrayInputStream bais, int count) {
/* 47 */     if (count < 0) {
/*    */       return;
/*    */     }
/* 50 */     this.a = new short[count];
/* 51 */     for (int i = 0; i < count; i++)
/* 52 */       this.a[i] = (short)bais.read(); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/R.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */