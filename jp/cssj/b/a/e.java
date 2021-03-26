/*     */ package jp.cssj.b.a;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import jp.cssj.b.c;
/*     */ import jp.cssj.b.d;
/*     */ import jp.cssj.b.d.c;
/*     */ import jp.cssj.b.d.d;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.e.d.a;
/*     */ import jp.cssj.e.h.a;
/*     */ import jp.cssj.e.i.a;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class e
/*     */ {
/*     */   public static void a(c session, File file) throws IOException {
/*  41 */     d d = new d(file);
/*  42 */     session.a((c)d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(c session, OutputStream out) throws IOException {
/*  53 */     d d = new d(out);
/*  54 */     session.a((c)d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(c session, Properties props) throws IOException {
/*  65 */     for (Iterator<Map.Entry<Object, Object>> i = props.entrySet().iterator(); i.hasNext(); ) {
/*  66 */       Map.Entry<Object, Object> entry = i.next();
/*  67 */       session.a((String)entry.getKey(), (String)entry.getValue());
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(c session, File file, String mimeType, String encoding) throws IOException {
/*  86 */     session.a((b)new a(file, mimeType, encoding));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(c session, URL url, String mimeType, String encoding) throws IOException {
/*     */     try {
/* 105 */       session.a((b)new a(url, mimeType, encoding));
/* 106 */     } catch (URISyntaxException uRISyntaxException) {
/* 107 */       IOException ioe = new IOException();
/* 108 */       ioe.initCause(uRISyntaxException);
/* 109 */       throw ioe;
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(c session, InputStream in, URI uri, String mimeType, String encoding) throws IOException {
/* 128 */     session.a((b)new a(uri, in, mimeType, encoding, -1L));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void b(c session, File dir, String mimeType, String encoding) throws IOException {
/* 146 */     if (dir.isDirectory()) {
/* 147 */       File[] files = dir.listFiles();
/* 148 */       if (files != null) {
/* 149 */         for (int i = 0; i < files.length; i++) {
/* 150 */           b(session, files[i], mimeType, encoding);
/*     */         }
/*     */       }
/*     */     } else {
/* 154 */       a(session, dir, mimeType, encoding);
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void c(c session, File file, String mimeType, String encoding) throws IOException, d {
/* 173 */     session.b((b)new a(file, mimeType, encoding));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void b(c session, URL url, String mimeType, String encoding) throws IOException, d {
/*     */     try {
/* 192 */       session.b((b)new a(url, mimeType, encoding));
/* 193 */     } catch (URISyntaxException uRISyntaxException) {
/* 194 */       IOException ioe = new IOException();
/* 195 */       ioe.initCause(uRISyntaxException);
/* 196 */       throw ioe;
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void b(c session, InputStream in, URI uri, String mimeType, String encoding) throws IOException, d {
/* 215 */     session.b((b)new a(uri, in, mimeType, encoding, -1L));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/b/a/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */