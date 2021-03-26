/*    */ package jp.cssj.f.b;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import jp.cssj.f.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class e
/*    */   extends a
/*    */   implements b
/*    */ {
/*    */   protected final OutputStream j;
/*    */   
/*    */   public e(OutputStream out, int fragmentBufferSize, int totalBufferSize, int threshold) {
/* 18 */     super(fragmentBufferSize, totalBufferSize, threshold);
/* 19 */     this.j = out;
/*    */   }
/*    */ 
/*    */   
/*    */   public e(OutputStream out) {
/* 24 */     this.j = out;
/*    */   }
/*    */   
/*    */   public void a(byte[] arrayOfByte, int off, int len) throws IOException {
/* 28 */     this.j.write(arrayOfByte, off, len);
/*    */   }
/*    */   
/*    */   public void a() throws IOException {
/* 32 */     a(this.j);
/*    */   }
/*    */   
/*    */   public void e() {
/* 36 */     super.e();
/*    */     try {
/* 38 */       this.j.close();
/* 39 */     } catch (IOException iOException) {}
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/f/b/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */