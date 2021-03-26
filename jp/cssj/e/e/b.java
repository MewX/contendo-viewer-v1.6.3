/*    */ package jp.cssj.e.e;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import jp.cssj.e.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   implements a
/*    */ {
/* 16 */   private static final URI a = URI.create(".");
/*    */   
/*    */   private URI b;
/*    */   private String c;
/*    */   private String d;
/*    */   private long e;
/*    */   
/*    */   public b() {
/* 24 */     this((URI)null);
/*    */   }
/*    */   
/*    */   public b(URI uri) {
/* 28 */     this(uri, null);
/*    */   }
/*    */   
/*    */   public b(URI uri, String mimeType) {
/* 32 */     this(uri, mimeType, null);
/*    */   }
/*    */   
/*    */   public b(URI uri, String mimeType, String encoding) {
/* 36 */     this(uri, mimeType, encoding, -1L);
/*    */   }
/*    */   
/*    */   public b(URI uri, String mimeType, String encoding, long length) {
/* 40 */     if (uri == null) {
/* 41 */       uri = a;
/*    */     }
/* 43 */     this.b = uri;
/* 44 */     this.d = mimeType;
/* 45 */     this.c = encoding;
/* 46 */     this.e = length;
/*    */   }
/*    */   
/*    */   public b(jp.cssj.e.b source) throws IOException {
/* 50 */     this(source.d(), source.c(), source.a(), source
/* 51 */         .b());
/*    */   }
/*    */   
/*    */   public URI d() {
/* 55 */     return this.b;
/*    */   }
/*    */   
/*    */   public void a(URI uri) {
/* 59 */     this.b = uri;
/*    */   }
/*    */   
/*    */   public String a() {
/* 63 */     return this.c;
/*    */   }
/*    */   
/*    */   public void a(String encoding) {
/* 67 */     this.c = encoding;
/*    */   }
/*    */   
/*    */   public String c() {
/* 71 */     return this.d;
/*    */   }
/*    */   
/*    */   public void b(String mimeType) {
/* 75 */     this.d = mimeType;
/*    */   }
/*    */   
/*    */   public long b() {
/* 79 */     return this.e;
/*    */   }
/*    */   
/*    */   public void a(long length) {
/* 83 */     this.e = length;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/e/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */