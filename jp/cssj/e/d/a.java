/*     */ package jp.cssj.e.d;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.net.URI;
/*     */ import jp.cssj.e.d;
/*     */ import jp.cssj.e.e.a;
/*     */ import jp.cssj.e.e.d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends a
/*     */ {
/*     */   private final File a;
/*     */   private final String c;
/*  26 */   private String d = null;
/*     */   
/*     */   public a(File file, URI uri, String mimeType, String encoding) {
/*  29 */     super(uri);
/*  30 */     if (file == null) {
/*  31 */       throw new NullPointerException();
/*     */     }
/*  33 */     this.a = file;
/*  34 */     this.d = mimeType;
/*  35 */     this.c = encoding;
/*     */   }
/*     */   
/*     */   public a(URI uri) throws IOException {
/*  39 */     super(uri);
/*  40 */     String path = uri.getSchemeSpecificPart();
/*  41 */     path = d.a(path);
/*  42 */     this.a = new File(path);
/*  43 */     this.d = null;
/*  44 */     this.c = null;
/*     */   }
/*     */   
/*     */   public a(File file, String mimeType, String encoding) {
/*  48 */     this(file, file.toURI(), mimeType, encoding);
/*     */   }
/*     */   
/*     */   public a(File file, String mimeType) {
/*  52 */     this(file, mimeType, null);
/*     */   }
/*     */   
/*     */   public a(File file) {
/*  56 */     this(file, null);
/*     */   }
/*     */   
/*     */   public String c() throws IOException {
/*  60 */     if (this.d == null) {
/*  61 */       String filename = this.a.getName();
/*  62 */       int dot = filename.indexOf('.');
/*  63 */       if (dot != -1) {
/*  64 */         String suffix = filename.substring(dot, filename.length());
/*  65 */         if (suffix.equalsIgnoreCase(".html") || suffix
/*  66 */           .equalsIgnoreCase(".htm")) {
/*  67 */           this.d = "text/html";
/*  68 */         } else if (suffix.equalsIgnoreCase(".xml") || suffix
/*  69 */           .equalsIgnoreCase(".xhtml") || suffix
/*  70 */           .equalsIgnoreCase(".xht")) {
/*  71 */           this.d = "text/xml";
/*     */         } 
/*     */       } 
/*     */     } 
/*  75 */     return this.d;
/*     */   }
/*     */   
/*     */   public String a() {
/*  79 */     return this.c;
/*     */   }
/*     */   
/*     */   public boolean f() throws IOException {
/*  83 */     return this.a.exists();
/*     */   }
/*     */   
/*     */   public boolean k() throws IOException {
/*  87 */     return true;
/*     */   }
/*     */   
/*     */   public boolean g() throws IOException {
/*  91 */     return true;
/*     */   }
/*     */   
/*     */   public boolean i() throws IOException {
/*  95 */     return (this.c != null);
/*     */   }
/*     */   
/*     */   public InputStream h() throws IOException {
/*  99 */     return new FileInputStream(this.a);
/*     */   }
/*     */   
/*     */   public Reader j() throws IOException {
/* 103 */     if (!i()) {
/* 104 */       throw new UnsupportedOperationException();
/*     */     }
/* 106 */     return new InputStreamReader(h(), this.c);
/*     */   }
/*     */   
/*     */   public File l() {
/* 110 */     return this.a;
/*     */   }
/*     */   
/*     */   public long b() throws IOException {
/* 114 */     return this.a.length();
/*     */   }
/*     */   
/*     */   public d m() throws IOException {
/* 118 */     long timestamp = this.a.lastModified();
/* 119 */     return new c(timestamp, this.a);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/d/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */