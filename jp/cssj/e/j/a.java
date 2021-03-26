/*     */ package jp.cssj.e.j;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.net.URI;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import jp.cssj.e.d;
/*     */ import jp.cssj.e.e.a;
/*     */ import jp.cssj.e.e.d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends a
/*     */ {
/*  26 */   private static final Logger a = Logger.getLogger(a.class
/*  27 */       .getName());
/*     */   
/*     */   private final ZipFile c;
/*     */   
/*     */   private final ZipEntry d;
/*     */   
/*     */   private final String e;
/*     */   
/*  35 */   private String f = null;
/*     */ 
/*     */   
/*     */   public a(ZipFile zip, String path, URI uri, String mimeType, String encoding) {
/*  39 */     super(uri);
/*  40 */     if (zip == null) {
/*  41 */       throw new NullPointerException();
/*     */     }
/*  43 */     if (path == null) {
/*  44 */       path = uri.getSchemeSpecificPart();
/*     */       try {
/*  46 */         path = d.a(path);
/*  47 */       } catch (Exception e) {
/*  48 */         a.log(Level.WARNING, "URIをデコードできません。", e);
/*     */       } 
/*     */     } 
/*  51 */     this.d = zip.getEntry(path);
/*  52 */     this.c = zip;
/*  53 */     this.f = mimeType;
/*  54 */     this.e = encoding;
/*     */   }
/*     */   
/*     */   public a(ZipFile zip, URI uri, String mimeType, String encoding) {
/*  58 */     this(zip, null, uri, mimeType, encoding);
/*     */   }
/*     */   
/*     */   public a(ZipFile zip, URI uri, String mimeType) {
/*  62 */     this(zip, uri, mimeType, (String)null);
/*     */   }
/*     */   
/*     */   public a(ZipFile zip, URI uri) throws IOException {
/*  66 */     this(zip, uri, (String)null);
/*     */   }
/*     */   
/*     */   public a(ZipFile zip, String path, URI uri, String mimeType) {
/*  70 */     this(zip, path, uri, mimeType, null);
/*     */   }
/*     */   
/*     */   public a(ZipFile zip, String path, URI uri) {
/*  74 */     this(zip, path, uri, null, null);
/*     */   }
/*     */   
/*     */   public String c() throws IOException {
/*  78 */     if (this.f == null) {
/*  79 */       String name = this.d.getName();
/*  80 */       int dot = name.indexOf('.');
/*  81 */       if (dot != -1) {
/*  82 */         String suffix = name.substring(dot, name.length());
/*  83 */         if (suffix.equalsIgnoreCase(".html") || suffix
/*  84 */           .equalsIgnoreCase(".htm")) {
/*  85 */           this.f = "text/html";
/*  86 */         } else if (suffix.equalsIgnoreCase(".xml") || suffix
/*  87 */           .equalsIgnoreCase(".xhtml") || suffix
/*  88 */           .equalsIgnoreCase(".xht")) {
/*  89 */           this.f = "text/xml";
/*     */         } 
/*     */       } 
/*     */     } 
/*  93 */     return this.f;
/*     */   }
/*     */   
/*     */   public String a() {
/*  97 */     return this.e;
/*     */   }
/*     */   
/*     */   public boolean f() throws IOException {
/* 101 */     return (this.d != null);
/*     */   }
/*     */   
/*     */   public boolean k() throws IOException {
/* 105 */     return false;
/*     */   }
/*     */   
/*     */   public boolean g() throws IOException {
/* 109 */     return true;
/*     */   }
/*     */   
/*     */   public boolean i() throws IOException {
/* 113 */     return (this.e != null);
/*     */   }
/*     */   
/*     */   public InputStream h() throws IOException {
/* 117 */     if (this.d == null) {
/* 118 */       throw new FileNotFoundException(this.b.toString());
/*     */     }
/* 120 */     return this.c.getInputStream(this.d);
/*     */   }
/*     */   
/*     */   public Reader j() throws IOException {
/* 124 */     if (!i()) {
/* 125 */       throw new UnsupportedOperationException();
/*     */     }
/* 127 */     return new InputStreamReader(h(), this.e);
/*     */   }
/*     */   
/*     */   public File l() {
/* 131 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public long b() throws IOException {
/* 135 */     if (f()) {
/* 136 */       return -1L;
/*     */     }
/* 138 */     return 0L;
/*     */   }
/*     */   
/*     */   public d m() throws IOException {
/* 142 */     File file = new File(this.c.getName());
/* 143 */     long timestamp = file.lastModified();
/* 144 */     return new c(timestamp, file);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/j/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */