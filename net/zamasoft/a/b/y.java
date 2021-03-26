/*    */ package net.zamasoft.a.b;
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
/*    */ public class y
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   private short c;
/*    */   
/*    */   protected y(RandomAccessFile raf) throws IOException {
/* 38 */     this.a = raf.readUnsignedShort();
/* 39 */     this.b = raf.readUnsignedShort();
/* 40 */     this.c = raf.readShort();
/*    */   }
/*    */   
/*    */   public int a() {
/* 44 */     return this.a;
/*    */   }
/*    */   
/*    */   public int b() {
/* 48 */     return this.b;
/*    */   }
/*    */   
/*    */   public short c() {
/* 52 */     return this.c;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/y.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */