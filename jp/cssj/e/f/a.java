/*     */ package jp.cssj.e.f;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.net.URI;
/*     */ import java.nio.charset.Charset;
/*     */ import jp.cssj.e.d;
/*     */ import jp.cssj.e.e.a;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.client.HttpClient;
/*     */ import org.apache.http.client.methods.HttpGet;
/*     */ import org.apache.http.client.methods.HttpUriRequest;
/*     */ import org.apache.http.client.utils.DateUtils;
/*     */ import org.apache.http.impl.client.CloseableHttpClient;
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
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends a
/*     */ {
/*     */   private final CloseableHttpClient a;
/*     */   private String c;
/*     */   private String d;
/*     */   private HttpUriRequest e;
/*     */   private HttpResponse f;
/*     */   private InputStream g;
/*     */   private boolean h;
/*  43 */   private long i = -1L;
/*     */   
/*  45 */   private long j = -1L;
/*     */   
/*     */   public a(URI uri, CloseableHttpClient httpClient) {
/*  48 */     super(uri);
/*  49 */     this.a = httpClient;
/*     */   }
/*     */   
/*     */   public HttpClient n() {
/*  53 */     return (HttpClient)this.a;
/*     */   }
/*     */   
/*     */   public String c() throws IOException {
/*  57 */     o();
/*  58 */     return this.c;
/*     */   }
/*     */   
/*     */   public String a() throws IOException {
/*  62 */     o();
/*  63 */     return this.d;
/*     */   }
/*     */   
/*     */   public boolean f() throws IOException {
/*  67 */     o();
/*  68 */     return this.h;
/*     */   }
/*     */   
/*     */   public boolean g() throws IOException {
/*  72 */     return true;
/*     */   }
/*     */   
/*     */   public boolean i() throws IOException {
/*  76 */     o();
/*  77 */     return (this.d != null);
/*     */   }
/*     */   
/*     */   public synchronized InputStream h() throws IOException {
/*  81 */     if (this.g != null) {
/*  82 */       if (this.f != null) {
/*     */         
/*     */         try {
/*  85 */           HttpEntity e = this.f.getEntity();
/*  86 */           if (e != null) {
/*  87 */             InputStream in = e.getContent();
/*  88 */             if (in != null) {
/*  89 */               in.close();
/*     */             }
/*     */           } 
/*  92 */         } catch (IOException iOException) {}
/*     */ 
/*     */         
/*  95 */         this.f = null;
/*     */       } 
/*  97 */       this.e = null;
/*  98 */       this.g = null;
/*     */     } 
/* 100 */     o();
/* 101 */     HttpEntity entity = this.f.getEntity();
/* 102 */     if (entity == null) {
/* 103 */       throw new FileNotFoundException();
/*     */     }
/* 105 */     this.g = entity.getContent();
/* 106 */     return this.g;
/*     */   }
/*     */   protected void o() throws IOException {
/*     */     int status;
/* 110 */     if (this.e != null) {
/*     */       return;
/*     */     }
/* 113 */     this.e = e();
/*     */     
/*     */     try {
/* 116 */       this.f = (HttpResponse)this.a.execute(this.e);
/* 117 */       status = this.f.getStatusLine().getStatusCode();
/* 118 */     } catch (Exception e) {
/* 119 */       IOException ioe = new IOException();
/* 120 */       ioe.initCause(e);
/* 121 */       throw ioe;
/*     */     } 
/* 123 */     this.h = (status != 404);
/* 124 */     HttpEntity entity = this.f.getEntity();
/* 125 */     this.d = null;
/* 126 */     if (entity != null) {
/* 127 */       Header encodingHeader = entity.getContentEncoding();
/* 128 */       if (encodingHeader != null) {
/* 129 */         this.d = encodingHeader.getValue();
/*     */         try {
/* 131 */           if (this.d.equalsIgnoreCase("ISO-8859-1") || !Charset.isSupported(this.d)) {
/* 132 */             this.d = null;
/*     */           }
/* 134 */         } catch (Exception exception) {}
/*     */       } 
/*     */ 
/*     */       
/* 138 */       Header mimeType = entity.getContentType();
/* 139 */       if (mimeType != null) {
/* 140 */         this.c = mimeType.getValue();
/*     */       }
/*     */     } 
/* 143 */     Header lastModified = this.f.getLastHeader("Last-Modified");
/* 144 */     if (lastModified != null) {
/* 145 */       this.i = DateUtils.parseDate(lastModified.getValue()).getTime();
/*     */     }
/* 147 */     Header contentLength = this.f.getLastHeader("Content-Length");
/* 148 */     if (contentLength != null) {
/*     */       try {
/* 150 */         this.j = Long.parseLong(contentLength.getValue());
/* 151 */       } catch (NumberFormatException numberFormatException) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected HttpUriRequest e() {
/* 158 */     HttpGet method = new HttpGet(this.b);
/* 159 */     return (HttpUriRequest)method;
/*     */   }
/*     */   
/*     */   public Reader j() throws IOException {
/* 163 */     if (this.d == null) {
/* 164 */       throw new UnsupportedOperationException();
/*     */     }
/* 166 */     return new InputStreamReader(h(), this.d);
/*     */   }
/*     */   
/*     */   public File l() {
/* 170 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public long b() throws IOException {
/* 174 */     o();
/* 175 */     return this.j;
/*     */   }
/*     */   
/*     */   public d m() {
/* 179 */     return new c(this.i);
/*     */   }
/*     */   
/*     */   public void close() {
/* 183 */     if (this.e != null)
/*     */       try {
/* 185 */         if (this.g != null) {
/*     */           try {
/* 187 */             this.g.close();
/* 188 */           } catch (IOException iOException) {}
/*     */         }
/*     */       }
/*     */       finally {
/*     */         
/* 193 */         this.f = null;
/* 194 */         this.e = null;
/* 195 */         this.g = null;
/*     */         try {
/* 197 */           this.a.close();
/* 198 */         } catch (IOException iOException) {}
/*     */       }  
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/f/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */