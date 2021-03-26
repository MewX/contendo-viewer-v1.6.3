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
/*    */ public class l
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   
/*    */   public l(RandomAccessFile raf) throws IOException {
/* 36 */     this.a = raf.readInt();
/* 37 */     this.b = raf.readUnsignedShort();
/*    */   }
/*    */   
/*    */   public int a() {
/* 41 */     return this.a;
/*    */   }
/*    */   
/*    */   public int b() {
/* 45 */     return this.b;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/l.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */