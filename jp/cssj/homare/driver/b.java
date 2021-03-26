/*    */ package jp.cssj.homare.driver;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import jp.cssj.b.d.c;
/*    */ import jp.cssj.e.a;
/*    */ import jp.cssj.f.a;
/*    */ import jp.cssj.f.a.d;
/*    */ import jp.cssj.homare.ua.a;
/*    */ import jp.cssj.homare.ua.m;
/*    */ 
/*    */ 
/*    */ public class b
/*    */   implements c
/*    */ {
/*    */   private final c a;
/*    */   private final long b;
/*    */   private final m c;
/* 18 */   private long d = 0L;
/*    */   
/*    */   public b(c results, long limit, m ua) {
/* 21 */     this.a = results;
/* 22 */     this.b = limit;
/* 23 */     this.c = ua;
/*    */   }
/*    */   
/*    */   public long b() {
/* 27 */     return this.d;
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 31 */     return this.a.a();
/*    */   }
/*    */   
/*    */   public a a(a metaSource) throws IOException {
/* 35 */     a builder = this.a.a(metaSource);
/* 36 */     return (a)new d(this, builder) {
/*    */         public void a(int id, byte[] arrayOfByte, int off, int len) throws IOException {
/* 38 */           b.a(this.a, b.a(this.a) + len);
/* 39 */           if (b.a(this.a) > b.b(this.a)) {
/* 40 */             short code = 14340;
/* 41 */             String[] args = { String.valueOf(b.b(this.a)) };
/* 42 */             b.c(this.a).a(code, args);
/* 43 */             throw new a((byte)2);
/*    */           } 
/* 45 */           super.a(id, arrayOfByte, off, len);
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   public void c() throws IOException {
/* 51 */     this.a.c();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/driver/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */