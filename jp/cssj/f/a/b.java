/*    */ package jp.cssj.f.a;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import jp.cssj.f.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends OutputStream
/*    */ {
/*    */   private final a a;
/*    */   private final int b;
/* 20 */   private final byte[] c = new byte[1];
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public b(a builder, int fragmentId) {
/* 30 */     if (builder == null) {
/* 31 */       throw new NullPointerException();
/*    */     }
/* 33 */     this.a = builder;
/* 34 */     this.b = fragmentId;
/*    */   }
/*    */   
/*    */   public void write(int i) throws IOException {
/* 38 */     this.c[0] = (byte)i;
/* 39 */     this.a.a(this.b, this.c, 0, 1);
/*    */   }
/*    */   
/*    */   public void write(byte[] arrayOfByte, int off, int len) throws IOException {
/* 43 */     this.a.a(this.b, arrayOfByte, off, len);
/*    */   }
/*    */   
/*    */   public void write(byte[] arrayOfByte) throws IOException {
/* 47 */     this.a.a(this.b, arrayOfByte, 0, arrayOfByte.length);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 54 */     this.a.b(this.b);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/f/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */