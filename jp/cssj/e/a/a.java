/*     */ package jp.cssj.e.a;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.net.URI;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.e.d;
/*     */ import jp.cssj.e.e.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   implements b
/*     */ {
/*  24 */   private static final Logger a = Logger.getLogger(a.class
/*  25 */       .getName());
/*     */   
/*     */   private final URI b;
/*     */   
/*     */   private final String c;
/*     */   
/*     */   private final String d;
/*     */   private final File e;
/*  33 */   private InputStream f = null;
/*     */   
/*     */   public a(URI uri, String mimeType, String encoding, File file) {
/*  36 */     this.b = uri;
/*  37 */     this.c = mimeType;
/*  38 */     this.d = encoding;
/*  39 */     this.e = file;
/*  40 */     if (uri == null) {
/*  41 */       throw new NullPointerException("uri");
/*     */     }
/*  43 */     if (file == null) {
/*  44 */       throw new NullPointerException("file");
/*     */     }
/*     */   }
/*     */   
/*     */   public URI d() {
/*  49 */     return this.b;
/*     */   }
/*     */   
/*     */   public String a() {
/*  53 */     return this.d;
/*     */   }
/*     */   
/*     */   public String c() {
/*  57 */     return this.c;
/*     */   }
/*     */   
/*     */   public InputStream h() throws IOException {
/*  61 */     if (this.f != null) {
/*  62 */       e();
/*     */     }
/*  64 */     return this.f = new FileInputStream(this.e);
/*     */   }
/*     */   
/*     */   public Reader j() throws IOException {
/*  68 */     if (!i()) {
/*  69 */       throw new UnsupportedOperationException();
/*     */     }
/*  71 */     return new InputStreamReader(h(), this.d);
/*     */   }
/*     */   
/*     */   public boolean k() {
/*  75 */     return true;
/*     */   }
/*     */   
/*     */   public File l() {
/*  79 */     return this.e;
/*     */   }
/*     */   
/*     */   public void e() {
/*  83 */     if (this.f != null) {
/*     */       try {
/*  85 */         this.f.close();
/*  86 */       } catch (Exception e) {
/*  87 */         a.log(Level.FINE, "リソースへの接続を中断した際に例外が発生しました", e);
/*     */       } finally {
/*  89 */         this.f = null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean f() throws IOException {
/*  95 */     return true;
/*     */   }
/*     */   
/*     */   public boolean g() throws IOException {
/*  99 */     return true;
/*     */   }
/*     */   
/*     */   public long b() throws IOException {
/* 103 */     return this.e.length();
/*     */   }
/*     */   
/*     */   public boolean i() throws IOException {
/* 107 */     return (this.d != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void finalize() {
/* 112 */     e();
/*     */   }
/*     */   
/*     */   public d m() {
/* 116 */     return (d)e.d;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */