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
/*    */ public class v
/*    */   extends u
/*    */ {
/*    */   private int a;
/*    */   private y[] b;
/*    */   
/*    */   protected v(RandomAccessFile raf) throws IOException {
/* 36 */     this.a = raf.readUnsignedShort();
/* 37 */     raf.readUnsignedShort();
/* 38 */     raf.readUnsignedShort();
/* 39 */     raf.readUnsignedShort();
/* 40 */     this.b = new y[this.a];
/* 41 */     for (int i = 0; i < this.a; i++) {
/* 42 */       this.b[i] = new y(raf);
/*    */     }
/*    */   }
/*    */   
/*    */   public int a() {
/* 47 */     return this.a;
/*    */   }
/*    */   
/*    */   public y a(int i) {
/* 51 */     return this.b[i];
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/v.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */