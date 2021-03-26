/*     */ package jp.cssj.e.c;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.net.URI;
/*     */ import java.util.StringTokenizer;
/*     */ import jp.cssj.e.d;
/*     */ import jp.cssj.e.e.a;
/*     */ import jp.cssj.e.e.f;
/*     */ import org.apache.commons.codec.DecoderException;
/*     */ import org.apache.commons.codec.binary.Base64;
/*     */ import org.apache.commons.codec.net.URLCodec;
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
/*  27 */   private String a = null;
/*     */   
/*  29 */   private byte[] c = null;
/*     */   
/*  31 */   private String d = null;
/*     */   
/*     */   private boolean e = false;
/*     */   
/*     */   public a(URI uri) {
/*  36 */     super(uri);
/*     */   }
/*     */   
/*     */   private void e() throws IOException {
/*  40 */     if (!this.e) {
/*  41 */       this.e = true;
/*     */       try {
/*  43 */         String spec = this.b.getRawSchemeSpecificPart();
/*  44 */         int comma = spec.indexOf(',');
/*  45 */         if (comma != -1) {
/*  46 */           String type = spec.substring(0, comma);
/*  47 */           String data = spec.substring(comma + 1);
/*  48 */           boolean base64 = false;
/*  49 */           StringTokenizer st = new StringTokenizer(type, ";");
/*  50 */           while (st.hasMoreElements()) {
/*  51 */             String token = st.nextToken();
/*  52 */             if (this.a == null) {
/*  53 */               if (token.indexOf('/') != -1) {
/*  54 */                 this.a = token;
/*     */                 continue;
/*     */               } 
/*  57 */               this.a = "text/plain";
/*  58 */               this.d = "US-ASCII";
/*     */             } 
/*     */             
/*  61 */             int equal = token.indexOf('=');
/*  62 */             if (equal != -1) {
/*  63 */               String name = token.substring(0, equal);
/*  64 */               if (name.equalsIgnoreCase("charset"))
/*  65 */                 this.d = token.substring(equal + 1); 
/*     */               continue;
/*     */             } 
/*  68 */             if (token.equalsIgnoreCase("base64")) {
/*  69 */               base64 = true;
/*     */             }
/*     */           } 
/*     */           
/*  73 */           if (base64) {
/*     */             byte[] bytes;
/*  75 */             if (data.indexOf('%') != -1) {
/*     */ 
/*     */               
/*  78 */               data = data.replaceAll("\\+", "%2B");
/*  79 */               bytes = data.getBytes("iso-8859-1");
/*  80 */               bytes = URLCodec.decodeUrl(bytes);
/*     */             } else {
/*  82 */               bytes = data.getBytes("iso-8859-1");
/*     */             } 
/*  84 */             this.c = Base64.decodeBase64(bytes);
/*     */           } else {
/*  86 */             this.c = URLCodec.decodeUrl(data
/*  87 */                 .getBytes("iso-8859-1"));
/*     */           } 
/*     */         } else {
/*  90 */           throw new IOException("data:スキーマのデータがありません");
/*     */         } 
/*  92 */       } catch (DecoderException e) {
/*  93 */         IOException ioe = new IOException(e.getMessage());
/*  94 */         ioe.initCause((Throwable)e);
/*  95 */         throw ioe;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String c() throws IOException {
/* 101 */     e();
/* 102 */     return this.a;
/*     */   }
/*     */   
/*     */   public String a() {
/* 106 */     return this.d;
/*     */   }
/*     */   
/*     */   public boolean f() throws IOException {
/* 110 */     e();
/* 111 */     return (this.c != null);
/*     */   }
/*     */   
/*     */   public boolean k() throws IOException {
/* 115 */     return false;
/*     */   }
/*     */   
/*     */   public boolean g() throws IOException {
/* 119 */     return true;
/*     */   }
/*     */   
/*     */   public boolean i() throws IOException {
/* 123 */     e();
/* 124 */     return (this.d != null);
/*     */   }
/*     */   
/*     */   public synchronized InputStream h() throws IOException {
/* 128 */     e();
/* 129 */     return new ByteArrayInputStream(this.c);
/*     */   }
/*     */   
/*     */   public Reader j() throws IOException {
/* 133 */     if (!i()) {
/* 134 */       throw new UnsupportedOperationException();
/*     */     }
/* 136 */     return new InputStreamReader(h(), this.d);
/*     */   }
/*     */   
/*     */   public File l() {
/* 140 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public long b() throws IOException {
/* 144 */     e();
/* 145 */     return this.c.length;
/*     */   }
/*     */   
/*     */   public d m() throws IOException {
/* 149 */     return (d)f.d;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/c/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */