/*     */ package jp.cssj.e.h;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URI;
/*     */ import jp.cssj.e.d;
/*     */ import jp.cssj.e.e.a;
/*     */ import jp.cssj.e.e.e;
/*     */ 
/*     */ 
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
/*     */   private static final int a = 8192;
/*     */   private final String c;
/*     */   private final String d;
/*     */   private final BufferedInputStream e;
/*     */   private final BufferedReader f;
/*     */   private final long g;
/*     */   
/*     */   public a(URI uri, InputStream in, String mimeType, String encoding, long length) throws UnsupportedEncodingException {
/*  36 */     super(uri);
/*  37 */     if (in == null) {
/*  38 */       throw new NullPointerException();
/*     */     }
/*  40 */     this.c = mimeType;
/*  41 */     this.d = encoding;
/*  42 */     this.e = new BufferedInputStream(this, in)
/*     */       {
/*     */         public void close() throws IOException {}
/*     */       };
/*     */     
/*  47 */     this.e.mark(8192);
/*  48 */     this.f = null;
/*  49 */     this.g = length;
/*     */   }
/*     */   
/*     */   public a(URI uri, InputStream in, String mimeType, long length) {
/*  53 */     super(uri);
/*  54 */     if (in == null) {
/*  55 */       throw new NullPointerException();
/*     */     }
/*  57 */     this.c = mimeType;
/*  58 */     this.e = new BufferedInputStream(this, in)
/*     */       {
/*     */         public void close() throws IOException {}
/*     */       };
/*     */     
/*  63 */     this.e.mark(8192);
/*  64 */     this.d = null;
/*  65 */     this.f = null;
/*  66 */     this.g = length;
/*     */   }
/*     */ 
/*     */   
/*     */   public a(URI uri, Reader reader, String mimeType, String encoding, long length) throws IOException {
/*  71 */     super(uri);
/*  72 */     if (reader == null) {
/*  73 */       throw new NullPointerException();
/*     */     }
/*  75 */     this.c = mimeType;
/*  76 */     this.e = null;
/*  77 */     this.d = encoding;
/*  78 */     this.f = new BufferedReader(this, reader)
/*     */       {
/*     */         public void close() throws IOException {}
/*     */       };
/*     */     
/*  83 */     this.f.mark(8192);
/*  84 */     this.g = length;
/*     */   }
/*     */ 
/*     */   
/*     */   public a(URI uri, InputStream in, String mimeType, String encoding) throws UnsupportedEncodingException {
/*  89 */     this(uri, in, mimeType, encoding, -1L);
/*     */   }
/*     */   
/*     */   public a(URI uri, InputStream in, String mimeType) {
/*  93 */     this(uri, in, mimeType, -1L);
/*     */   }
/*     */   
/*     */   public a(URI uri, InputStream in) {
/*  97 */     this(uri, in, (String)null, -1L);
/*     */   }
/*     */ 
/*     */   
/*     */   public a(URI uri, Reader reader, String mimeType, String encoding) throws IOException {
/* 102 */     this(uri, reader, mimeType, encoding, -1L);
/*     */   }
/*     */ 
/*     */   
/*     */   public a(URI uri, Reader reader, String mimeType) throws IOException {
/* 107 */     this(uri, reader, mimeType, (String)null, -1L);
/*     */   }
/*     */   
/*     */   public a(URI uri, Reader reader) throws IOException {
/* 111 */     this(uri, reader, (String)null, (String)null, -1L);
/*     */   }
/*     */   
/*     */   public URI d() {
/* 115 */     return this.b;
/*     */   }
/*     */   
/*     */   public String c() {
/* 119 */     return this.c;
/*     */   }
/*     */   
/*     */   public boolean f() {
/* 123 */     return true;
/*     */   }
/*     */   
/*     */   public String a() {
/* 127 */     return this.d;
/*     */   }
/*     */   
/*     */   public boolean g() {
/* 131 */     return (this.e != null);
/*     */   }
/*     */   
/*     */   public boolean i() {
/* 135 */     return (this.f != null || this.d != null);
/*     */   }
/*     */   
/*     */   public InputStream h() throws IOException {
/* 139 */     if (this.e == null) {
/* 140 */       throw new UnsupportedOperationException();
/*     */     }
/* 142 */     this.e.reset();
/* 143 */     this.e.mark(8192);
/* 144 */     return this.e;
/*     */   }
/*     */   
/*     */   public Reader j() throws IOException {
/* 148 */     if (this.f == null) {
/* 149 */       if (this.d == null) {
/* 150 */         throw new UnsupportedOperationException();
/*     */       }
/* 152 */       return new InputStreamReader(h(), this.d);
/*     */     } 
/* 154 */     this.f.reset();
/* 155 */     this.f.mark(8192);
/* 156 */     return this.f;
/*     */   }
/*     */   
/*     */   public File l() {
/* 160 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public long b() throws IOException {
/* 164 */     return this.g;
/*     */   }
/*     */   
/*     */   public d m() {
/* 168 */     return (d)e.d;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/h/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */