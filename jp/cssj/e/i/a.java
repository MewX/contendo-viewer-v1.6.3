/*     */ package jp.cssj.e.i;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import jp.cssj.e.d;
/*     */ import jp.cssj.e.e.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends a
/*     */ {
/*  27 */   private static final Logger a = Logger.getLogger(a.class
/*  28 */       .getName());
/*     */   
/*     */   private final URL c;
/*     */   
/*     */   private final String d;
/*     */   
/*  34 */   private String e = null;
/*     */   
/*  36 */   private transient URLConnection f = null;
/*     */   
/*  38 */   private transient InputStream g = null;
/*     */   
/*  40 */   private long h = -1L;
/*     */   
/*     */   public a(URI uri, URL url, String mimeType, String encoding) {
/*  43 */     super(uri);
/*  44 */     if (url == null) {
/*  45 */       throw new NullPointerException();
/*     */     }
/*  47 */     this.c = url;
/*  48 */     this.e = mimeType;
/*  49 */     this.d = encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public a(URL url, String mimeType, String encoding) throws URISyntaxException {
/*  55 */     this(new URI(url.toString()), url, mimeType, encoding);
/*     */   }
/*     */   
/*     */   public a(URL url, String mimeType) throws URISyntaxException {
/*  59 */     this(url, mimeType, (String)null);
/*     */   }
/*     */   
/*     */   public a(URL url) throws URISyntaxException {
/*  63 */     this(url, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public a(URI uri, String mimeType, String encoding) throws MalformedURLException {
/*  68 */     this(uri, uri.toURL(), mimeType, encoding);
/*     */   }
/*     */   
/*     */   public a(URI uri, String mimeType) throws MalformedURLException {
/*  72 */     this(uri, uri.toURL(), mimeType, null);
/*     */   }
/*     */   
/*     */   public a(URI uri) throws MalformedURLException {
/*  76 */     this(uri, uri.toURL(), null, null);
/*     */   }
/*     */   
/*     */   public String c() throws IOException {
/*  80 */     if (this.e == null) {
/*  81 */       if (k()) {
/*  82 */         String filename = l().getName();
/*  83 */         int dot = filename.indexOf('.');
/*  84 */         if (dot != -1) {
/*  85 */           String suffix = filename.substring(dot, filename.length());
/*  86 */           if (suffix.equalsIgnoreCase(".html") || suffix
/*  87 */             .equalsIgnoreCase(".htm")) {
/*  88 */             this.e = "text/html";
/*  89 */           } else if (suffix.equalsIgnoreCase(".xml") || suffix
/*  90 */             .equalsIgnoreCase(".xhtml") || suffix
/*  91 */             .equalsIgnoreCase(".xht")) {
/*  92 */             this.e = "text/xml";
/*     */           } 
/*  94 */           if (this.e != null) {
/*  95 */             return this.e;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       try {
/* 100 */         if (this.f == null) {
/* 101 */           e();
/*     */         }
/* 103 */         return this.e = this.f.getContentType();
/* 104 */       } catch (IOException e) {
/* 105 */         this.f = null;
/*     */       } 
/*     */     } 
/* 108 */     return this.e;
/*     */   }
/*     */   
/*     */   public String a() {
/* 112 */     return this.d;
/*     */   }
/*     */   
/*     */   public boolean f() throws IOException {
/* 116 */     if (k()) {
/* 117 */       return l().exists();
/*     */     }
/* 119 */     return true;
/*     */   }
/*     */   
/*     */   public boolean k() throws IOException {
/* 123 */     return "file".equals(this.b.getScheme());
/*     */   }
/*     */   
/*     */   public boolean g() throws IOException {
/* 127 */     return true;
/*     */   }
/*     */   
/*     */   public boolean i() throws IOException {
/* 131 */     return (this.d != null);
/*     */   }
/*     */   
/*     */   public synchronized InputStream h() throws IOException {
/* 135 */     if (this.g != null) {
/* 136 */       this.g = null;
/* 137 */       this.f = null;
/* 138 */       this.h = -1L;
/*     */     } 
/* 140 */     if (k()) {
/* 141 */       return this.g = new FileInputStream(l());
/*     */     }
/* 143 */     if (this.f == null) {
/* 144 */       e();
/*     */     }
/* 146 */     return this.g = this.f.getInputStream();
/*     */   }
/*     */   
/*     */   public Reader j() throws IOException {
/* 150 */     if (this.d == null) {
/* 151 */       throw new UnsupportedOperationException();
/*     */     }
/* 153 */     return new InputStreamReader(h(), this.d);
/*     */   }
/*     */   
/*     */   public synchronized void close() {
/* 157 */     if (this.g != null) {
/*     */       try {
/* 159 */         this.g.close();
/* 160 */       } catch (Exception e) {
/* 161 */         a.log(Level.FINE, "URLへの接続を中断した際に例外が発生しました", e);
/*     */       } finally {
/* 163 */         this.g = null;
/* 164 */         this.f = null;
/* 165 */         this.h = -1L;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void e() throws IOException {
/* 171 */     this.f = this.c.openConnection();
/* 172 */     this.h = this.f.getLastModified();
/*     */   }
/*     */   
/*     */   public File l() {
/* 176 */     String path = this.b.getPath();
/* 177 */     if (path == null) {
/* 178 */       path = this.b.getSchemeSpecificPart();
/*     */     }
/* 180 */     return new File(path);
/*     */   }
/*     */   
/*     */   public long b() throws IOException {
/* 184 */     if (k()) {
/* 185 */       return l().length();
/*     */     }
/* 187 */     if (this.f == null) {
/* 188 */       e();
/*     */     }
/* 190 */     return this.f.getContentLength();
/*     */   }
/*     */   
/*     */   public d m() throws IOException {
/* 194 */     e();
/* 195 */     return new c(this.h, this.c);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/i/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */