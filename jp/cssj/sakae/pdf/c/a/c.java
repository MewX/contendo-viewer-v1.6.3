/*     */ package jp.cssj.sakae.pdf.c.a;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import jp.cssj.e.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   implements Serializable
/*     */ {
/*  22 */   private static final Logger g = Logger.getLogger(c.class.getName());
/*     */   
/*     */   private static final long h = 0L;
/*     */   
/*     */   public static final char a = '?';
/*     */   
/*     */   protected final b b;
/*     */   
/*     */   protected final String c;
/*     */   
/*  32 */   protected transient WeakReference<int[]> d = null;
/*     */   
/*  34 */   protected transient int e = 0;
/*     */   
/*  36 */   protected transient Charset f = null;
/*     */   
/*     */   public c(b cmapSource, String javaEncoding) {
/*  39 */     this.b = cmapSource;
/*  40 */     this.c = javaEncoding;
/*     */   }
/*     */   
/*     */   private int[] d() {
/*  44 */     int[] toCid = null;
/*  45 */     if (this.d != null) {
/*  46 */       toCid = this.d.get();
/*  47 */       if (toCid != null) {
/*  48 */         return toCid;
/*     */       }
/*     */     } 
/*  51 */     if (g.isLoggable(Level.FINE)) {
/*  52 */       g.fine("load cid table:" + this.b);
/*     */     }
/*     */     
/*  55 */     d parser = new d();
/*     */     try {
/*  57 */       toCid = parser.a(this.b);
/*  58 */       Charset charset = a();
/*     */       
/*  60 */       if (!charset.name().equalsIgnoreCase("UTF-16BE")) {
/*  61 */         jp.cssj.sakae.e.c intList = new jp.cssj.sakae.e.c();
/*  62 */         CharsetDecoder decoder = charset.newDecoder();
/*  63 */         ByteBuffer in = ByteBuffer.allocate(4);
/*  64 */         CharBuffer out = CharBuffer.allocate(1);
/*  65 */         for (int j = 0; j < toCid.length; j++) {
/*  66 */           int cid = toCid[j];
/*  67 */           if (cid != 0) {
/*  68 */             in.clear();
/*  69 */             if (j < 255) {
/*  70 */               in.put((byte)(j & 0xFF));
/*  71 */             } else if (j <= 65535) {
/*  72 */               in.put((byte)(j >> 8 & 0xFF));
/*  73 */               in.put((byte)(j & 0xFF));
/*  74 */             } else if (j <= 16777215) {
/*  75 */               in.put((byte)(j >> 16 & 0xFF));
/*  76 */               in.put((byte)(j >> 8 & 0xFF));
/*  77 */               in.put((byte)(j & 0xFF));
/*     */             } else {
/*  79 */               in.put((byte)(j >> 24 & 0xFF));
/*  80 */               in.put((byte)(j >> 16 & 0xFF));
/*  81 */               in.put((byte)(j >> 8 & 0xFF));
/*  82 */               in.put((byte)(j & 0xFF));
/*     */             } 
/*  84 */             in.flip();
/*  85 */             out.clear();
/*  86 */             decoder.reset();
/*  87 */             decoder.decode(in, out, true);
/*  88 */             decoder.flush(out);
/*  89 */             intList.a(out.get(0), cid);
/*     */           } 
/*     */         } 
/*  92 */         toCid = intList.a();
/*     */       } 
/*  94 */       for (int i = 0; i < toCid.length; i++) {
/*  95 */         if (toCid[i] == 0) {
/*  96 */           toCid[i] = -1;
/*     */         }
/*     */       } 
/*  99 */     } catch (Exception e) {
/* 100 */       throw new RuntimeException(e);
/*     */     } 
/* 102 */     this.d = (WeakReference)new WeakReference<>(toCid);
/* 103 */     return toCid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Charset a() {
/* 112 */     if (this.f == null) {
/* 113 */       this.f = Charset.forName(this.c);
/*     */     }
/* 115 */     return this.f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/* 124 */     if (this.e == 0) {
/* 125 */       this.e = a(63);
/*     */     }
/* 127 */     return this.e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(int i) {
/* 138 */     int[] toCid = d();
/* 139 */     if (i < 0 || i >= toCid.length) {
/* 140 */       return b();
/*     */     }
/* 142 */     int cid = toCid[i];
/* 143 */     if (cid == -1) {
/* 144 */       return b();
/*     */     }
/* 146 */     return cid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b(int i) {
/* 156 */     int[] toCid = d();
/* 157 */     if (i < 0 || i >= toCid.length) {
/* 158 */       return false;
/*     */     }
/* 160 */     int cid = toCid[i];
/* 161 */     if (cid == -1) {
/* 162 */       return false;
/*     */     }
/* 164 */     return true;
/*     */   }
/*     */   
/*     */   public int c() {
/* 168 */     int[] toCid = d();
/* 169 */     return toCid.length;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */