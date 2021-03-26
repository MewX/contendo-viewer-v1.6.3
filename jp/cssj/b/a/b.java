/*     */ package jp.cssj.b.a;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.URI;
/*     */ import javax.servlet.ServletOutputStream;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpServletResponseWrapper;
/*     */ import jp.cssj.b.c;
/*     */ import jp.cssj.e.a;
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
/*     */ public class b
/*     */   extends HttpServletResponseWrapper
/*     */   implements Closeable, a
/*     */ {
/*     */   private final c a;
/*     */   private final URI b;
/*     */   private final boolean c;
/*  31 */   private long d = -1L;
/*     */   
/*     */   private String e;
/*     */   private String f;
/*  35 */   private ServletOutputStream g = null;
/*     */   
/*  37 */   private PrintWriter h = null;
/*     */   
/*     */   private class a extends ServletOutputStream {
/*     */     private OutputStream b;
/*  41 */     private byte[] c = new byte[8192];
/*  42 */     private int d = 0;
/*     */     
/*     */     public void a(int i) throws IOException {
/*  45 */       if (this.c != null) {
/*  46 */         if (this.d < this.c.length) {
/*  47 */           this.c[this.d++] = (byte)i;
/*     */           return;
/*     */         } 
/*  50 */         a();
/*     */       } 
/*  52 */       if (this.b != null) {
/*  53 */         this.b.write(i);
/*     */       }
/*     */     }
/*     */     
/*     */     public void a(byte[] arrayOfByte) throws IOException {
/*  58 */       a(arrayOfByte, 0, arrayOfByte.length);
/*     */     }
/*     */     
/*     */     public void a(byte[] arrayOfByte, int off, int len) throws IOException {
/*  62 */       if (this.c != null) {
/*  63 */         if (len <= this.c.length - this.d) {
/*  64 */           System.arraycopy(arrayOfByte, off, this.c, this.d, len);
/*  65 */           this.d += len;
/*     */           return;
/*     */         } 
/*  68 */         a();
/*     */       } 
/*  70 */       if (this.b != null) {
/*  71 */         this.b.write(arrayOfByte, off, len);
/*     */       }
/*     */     }
/*     */     
/*     */     public void a() throws IOException {
/*  76 */       if (this.b == null) {
/*  77 */         b o = this.a;
/*  78 */         if (b.a(o)) {
/*  79 */           this.b = b.b(o).b(o);
/*     */         } else {
/*  81 */           this.b = b.b(o).a(o);
/*     */         } 
/*  83 */         if (this.d > 0) {
/*  84 */           this.b.write(this.c, 0, this.d);
/*     */         }
/*  86 */         this.c = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void b() throws IOException {
/*  91 */       a();
/*  92 */       this.b.close();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private a(b this$0) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b(HttpServletResponse response, c session, URI uri, boolean transcode) {
/* 109 */     super(response);
/* 110 */     this.a = session;
/* 111 */     this.b = uri;
/* 112 */     this.c = true;
/* 113 */     this.e = "UTF-8";
/* 114 */     this.f = response.getContentType();
/*     */   }
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
/*     */   public b(HttpServletResponse response, c session, URI uri) {
/* 128 */     this(response, session, uri, true);
/*     */   }
/*     */   
/*     */   public void a(int contentLength) {
/* 132 */     this.d = contentLength;
/*     */   }
/*     */   
/*     */   public void a(String contentType) {
/* 136 */     this.f = contentType;
/*     */   }
/*     */   
/*     */   public void b(String encoding) {
/* 140 */     this.e = encoding;
/*     */   }
/*     */   
/*     */   public String a() throws IOException {
/* 144 */     return this.e;
/*     */   }
/*     */   
/*     */   public long b() throws IOException {
/* 148 */     return this.d;
/*     */   }
/*     */   
/*     */   public String c() throws IOException {
/* 152 */     return this.f;
/*     */   }
/*     */   
/*     */   public URI d() {
/* 156 */     return this.b;
/*     */   }
/*     */   
/*     */   public ServletOutputStream e() {
/* 160 */     if (this.g == null) {
/* 161 */       this.g = new a();
/*     */     }
/* 163 */     return this.g;
/*     */   }
/*     */   
/*     */   public PrintWriter f() throws IOException {
/* 167 */     if (this.h == null) {
/* 168 */       this.h = new PrintWriter(new OutputStreamWriter((OutputStream)e(), this.e));
/*     */     }
/* 170 */     return this.h;
/*     */   }
/*     */   
/*     */   public void g() throws IOException {
/* 174 */     if (this.h != null) {
/* 175 */       this.h.flush();
/* 176 */     } else if (this.g != null) {
/* 177 */       this.g.flush();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 182 */     if (this.h != null) {
/* 183 */       this.h.close();
/* 184 */     } else if (this.g != null) {
/* 185 */       this.g.close();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/b/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */