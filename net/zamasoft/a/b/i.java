/*    */ package net.zamasoft.a.b;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
/*    */ import java.io.Serializable;
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
/*    */ public class i
/*    */   implements Serializable
/*    */ {
/*    */   private static final long a = 0L;
/*    */   private int b;
/*    */   private int c;
/*    */   private int d;
/*    */   private int e;
/*    */   
/*    */   protected i(RandomAccessFile raf) throws IOException {
/* 40 */     this.b = raf.readInt();
/* 41 */     this.c = raf.readInt();
/* 42 */     this.d = raf.readInt();
/* 43 */     this.e = raf.readInt();
/*    */   }
/*    */   
/*    */   public int a() {
/* 47 */     return this.c;
/*    */   }
/*    */   
/*    */   public int b() {
/* 51 */     return this.e;
/*    */   }
/*    */   
/*    */   public int c() {
/* 55 */     return this.d;
/*    */   }
/*    */   
/*    */   public int d() {
/* 59 */     return this.b;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 63 */     return (char)(this.b >> 24 & 0xFF) + (char)(this.b >> 16 & 0xFF) + (char)(this.b >> 8 & 0xFF) + 
/* 64 */       (char)(this.b & 0xFF) + ", offset: " + this.d + 
/* 65 */       ", length: " + this.e + ", checksum: 0x" + 
/* 66 */       Integer.toHexString(this.c);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */