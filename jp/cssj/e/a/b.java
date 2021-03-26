/*     */ package jp.cssj.e.a;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URI;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import jp.cssj.e.c;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   implements c
/*     */ {
/*     */   protected static class a
/*     */   {
/*     */     public final URI a;
/*     */     public final String b;
/*     */     public final String c;
/*     */     public final File d;
/*     */     
/*     */     public a(URI uri, String mimeType, String encoding, File file) {
/*  34 */       this.a = uri;
/*  35 */       this.b = mimeType;
/*  36 */       this.c = encoding;
/*  37 */       this.d = file;
/*     */     }
/*     */   }
/*     */   
/*  41 */   private final Map<String, a> a = new HashMap<>();
/*     */   private final File b;
/*     */   
/*     */   public b(File tmpDir) {
/*  45 */     this.b = tmpDir;
/*     */   }
/*     */   
/*     */   public b() {
/*  49 */     this(null);
/*     */   }
/*     */   
/*     */   private static char a(char c1) {
/*  53 */     if (c1 >= '0' && c1 <= '9')
/*  54 */       return (char)(c1 - 48); 
/*  55 */     if (c1 >= 'a' && c1 <= 'f')
/*  56 */       return (char)(c1 - 97 + 10); 
/*  57 */     if (c1 >= 'A' && c1 <= 'F')
/*  58 */       return (char)(c1 - 65 + 10); 
/*  59 */     return Character.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public static String a(URI uri) {
/*  63 */     String key = uri.toString();
/*  64 */     String scheme = uri.getScheme();
/*  65 */     if (scheme == null) {
/*  66 */       return key;
/*     */     }
/*  68 */     if (scheme.equals("http") || scheme.equals("https")) {
/*     */       
/*  70 */       String exclude = "?#";
/*  71 */       char[] ch = key.toCharArray();
/*  72 */       int len = ch.length;
/*  73 */       int ix = 0;
/*  74 */       int ox = 0;
/*  75 */       while (ix < len) {
/*  76 */         char c1 = ch[ix++];
/*  77 */         if (c1 == '?') {
/*  78 */           exclude = "&=#";
/*  79 */         } else if (c1 == '+') {
/*  80 */           c1 = ' ';
/*  81 */         } else if (c1 == '%') {
/*  82 */           char c2 = (char)((a(ch[ix]) << 4) + a(ch[ix + 1]));
/*  83 */           if (exclude.indexOf(c2) == -1) {
/*  84 */             c1 = c2;
/*  85 */             ix += 2;
/*     */           } else {
/*  87 */             ch[ix] = Character.toUpperCase(ch[ix]);
/*  88 */             ch[ix + 1] = Character.toUpperCase(ch[ix + 1]);
/*     */           } 
/*     */         } 
/*  91 */         ch[ox++] = c1;
/*     */       } 
/*  93 */       key = new String(ch, 0, ox);
/*     */     } 
/*  95 */     return key;
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
/*     */   public File a(jp.cssj.e.a metaSource) throws IOException {
/* 107 */     URI uri = metaSource.d().normalize();
/* 108 */     String key = a(uri);
/* 109 */     a info = this.a.get(key);
/* 110 */     if (info != null) {
/* 111 */       info.d.delete();
/*     */     }
/*     */     
/* 114 */     String mimeType = metaSource.c();
/* 115 */     String encoding = metaSource.a();
/* 116 */     File file = File.createTempFile("cssj-cache-", ".dat", this.b);
/* 117 */     file.deleteOnExit();
/* 118 */     info = new a(uri, mimeType, encoding, file);
/* 119 */     this.a.put(key, info);
/* 120 */     return file;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(jp.cssj.e.b source) throws IOException {
/* 131 */     File file = a((jp.cssj.e.a)source);
/* 132 */     try(InputStream in = source.h(); OutputStream out = new FileOutputStream(file)) {
/* 133 */       IOUtils.copy(in, out);
/*     */     } 
/*     */   }
/*     */   
/*     */   public jp.cssj.e.b b(URI uri) throws IOException, SecurityException {
/* 138 */     uri = uri.normalize();
/* 139 */     String key = a(uri);
/* 140 */     a info = this.a.get(key);
/* 141 */     if (info != null) {
/* 142 */       jp.cssj.e.b source = new a(info.a, info.b, info.c, info.d);
/*     */       
/* 144 */       return source;
/*     */     } 
/* 146 */     throw new FileNotFoundException(uri.toString());
/*     */   }
/*     */   
/*     */   public void a(jp.cssj.e.b source) {
/* 150 */     ((a)source).e();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a() {
/* 159 */     for (Iterator<a> i = this.a.values().iterator(); i.hasNext(); ) {
/* 160 */       a info = i.next();
/* 161 */       info.d.delete();
/*     */     } 
/* 163 */     this.a.clear();
/*     */   }
/*     */   
/*     */   public void b() {
/* 167 */     a();
/*     */   }
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 171 */     b();
/* 172 */     super.finalize();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */