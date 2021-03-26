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
/*    */ 
/*    */ public class S
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   private int c;
/*    */   
/*    */   public S(RandomAccessFile raf) throws IOException {
/* 39 */     this.a = raf.readUnsignedShort();
/* 40 */     this.b = raf.readUnsignedShort();
/* 41 */     this.c = raf.readUnsignedShort();
/*    */   }
/*    */   
/*    */   public boolean a(int glyphId) {
/* 45 */     return (this.a <= glyphId && glyphId <= this.b);
/*    */   }
/*    */   
/*    */   public int b(int glyphId) {
/* 49 */     if (a(glyphId)) {
/* 50 */       return this.c + glyphId - this.a;
/*    */     }
/* 52 */     return -1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/S.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */