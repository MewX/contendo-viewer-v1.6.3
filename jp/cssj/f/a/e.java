/*    */ package jp.cssj.f.a;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import jp.cssj.f.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class e
/*    */   extends OutputStream
/*    */ {
/*    */   private final b a;
/* 18 */   private final byte[] b = new byte[1];
/*    */   
/*    */   public e(b builder) {
/* 21 */     this.a = builder;
/*    */   }
/*    */   
/*    */   public void write(int i) throws IOException {
/* 25 */     this.b[0] = (byte)i;
/* 26 */     this.a.a(this.b, 0, 1);
/*    */   }
/*    */   
/*    */   public void write(byte[] arrayOfByte, int off, int len) throws IOException {
/* 30 */     this.a.a(arrayOfByte, off, len);
/*    */   }
/*    */   
/*    */   public void write(byte[] arrayOfByte) throws IOException {
/* 34 */     this.a.a(arrayOfByte, 0, arrayOfByte.length);
/*    */   }
/*    */   
/*    */   public void close() throws IOException {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/f/a/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */