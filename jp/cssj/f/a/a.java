/*    */ package jp.cssj.f.a;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class a
/*    */   extends d
/*    */ {
/*    */   protected long b;
/*    */   
/*    */   public a(jp.cssj.f.a builder) {
/* 17 */     super(builder);
/*    */   }
/*    */   
/*    */   public void e() {
/* 21 */     super.e();
/* 22 */     this.b = 0L;
/*    */   }
/*    */   
/*    */   public void a(int id, byte[] b, int off, int len) throws IOException {
/* 26 */     super.a(id, b, off, len);
/* 27 */     this.b += len;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long f() {
/* 36 */     return this.b;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/f/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */