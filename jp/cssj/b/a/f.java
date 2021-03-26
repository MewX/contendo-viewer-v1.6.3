/*    */ package jp.cssj.b.a;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import jp.cssj.e.a;
/*    */ import jp.cssj.e.b;
/*    */ import jp.cssj.e.e.d;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class f
/*    */   implements a
/*    */ {
/*    */   private URI a;
/*    */   private String b;
/*    */   private String c;
/*    */   private long d;
/*    */   
/*    */   public f() {
/* 24 */     this((URI)null);
/*    */   }
/*    */   
/*    */   public f(URI uri) {
/* 28 */     this(uri, null);
/*    */   }
/*    */   
/*    */   public f(URI uri, String mimeType) {
/* 32 */     this(uri, mimeType, null);
/*    */   }
/*    */   
/*    */   public f(URI uri, String mimeType, String encoding) {
/* 36 */     this(uri, mimeType, encoding, -1L);
/*    */   }
/*    */   
/*    */   public f(URI uri, String mimeType, String encoding, long length) {
/* 40 */     if (uri == null) {
/* 41 */       uri = d.a;
/*    */     }
/* 43 */     this.a = uri;
/* 44 */     this.c = mimeType;
/* 45 */     this.b = encoding;
/* 46 */     this.d = length;
/*    */   }
/*    */   
/*    */   public f(b source) throws IOException {
/* 50 */     this(source.d(), source.c(), source.a(), source.b());
/*    */   }
/*    */   
/*    */   public URI d() {
/* 54 */     return this.a;
/*    */   }
/*    */   
/*    */   public void a(URI uri) {
/* 58 */     this.a = uri;
/*    */   }
/*    */   
/*    */   public String a() {
/* 62 */     return this.b;
/*    */   }
/*    */   
/*    */   public void a(String encoding) {
/* 66 */     this.b = encoding;
/*    */   }
/*    */   
/*    */   public String c() {
/* 70 */     return this.c;
/*    */   }
/*    */   
/*    */   public void b(String mimeType) {
/* 74 */     this.c = mimeType;
/*    */   }
/*    */   
/*    */   public long b() {
/* 78 */     return this.d;
/*    */   }
/*    */   
/*    */   public void a(long length) {
/* 82 */     this.d = length;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/b/a/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */