/*     */ package jp.cssj.b.a;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.BitSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class k
/*     */ {
/*  19 */   private static final char[] b = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*     */ 
/*     */ 
/*     */   
/*  23 */   private static BitSet c = new BitSet(256);
/*     */   static {
/*     */     int i;
/*  26 */     for (i = 97; i <= 122; i++) {
/*  27 */       c.set(i);
/*     */     }
/*     */     
/*  30 */     for (i = 65; i <= 90; i++) {
/*  31 */       c.set(i);
/*     */     }
/*     */     
/*  34 */     for (i = 48; i <= 57; i++) {
/*  35 */       c.set(i);
/*     */     }
/*     */ 
/*     */     
/*  39 */     c.set(36);
/*  40 */     c.set(45);
/*  41 */     c.set(95);
/*  42 */     c.set(46);
/*  43 */     c.set(43);
/*  44 */     c.set(37);
/*     */ 
/*     */     
/*  47 */     c.set(33);
/*  48 */     c.set(42);
/*  49 */     c.set(39);
/*  50 */     c.set(40);
/*  51 */     c.set(41);
/*  52 */     c.set(44);
/*     */ 
/*     */ 
/*     */     
/*  56 */     c.set(47);
/*  57 */     c.set(58);
/*  58 */     c.set(59);
/*  59 */     c.set(64);
/*  60 */     c.set(38);
/*  61 */     c.set(61);
/*  62 */     c.set(63);
/*  63 */     c.set(35);
/*     */   }
/*  65 */   private static final boolean d = System.getProperty("os.name").startsWith("Windows");
/*     */   
/*  67 */   public static final URI a = URI.create(".");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String b(String encoding, String uri) {
/*  74 */     uri = uri.trim();
/*  75 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/*     */     
/*  77 */     try (Writer writer = new OutputStreamWriter(out, encoding)) {
/*  78 */       for (int j = 0; j < uri.length(); j++) {
/*  79 */         char c = uri.charAt(j);
/*  80 */         if (!Character.isISOControl(c)) {
/*  81 */           writer.write(c);
/*     */         }
/*     */       }
/*     */     
/*  85 */     } catch (IOException e) {
/*  86 */       throw new RuntimeException(e);
/*     */     } 
/*  88 */     byte[] bytes = out.toByteArray();
/*  89 */     StringBuffer buff = new StringBuffer(bytes.length);
/*  90 */     for (int i = 0; i < bytes.length; i++) {
/*  91 */       int b = bytes[i] & 0xFF;
/*  92 */       if (k.c.get(b)) {
/*  93 */         buff.append((char)b);
/*     */       } else {
/*  95 */         buff.append('%');
/*  96 */         buff.append(k.b[b >> 4]);
/*  97 */         buff.append(k.b[b & 0xF]);
/*     */       } 
/*     */     } 
/* 100 */     uri = buff.toString();
/* 101 */     return uri;
/*     */   }
/*     */   
/*     */   private static URI a(URI uri) throws URISyntaxException {
/* 105 */     String path = uri.getPath();
/* 106 */     if (path == null) {
/* 107 */       return uri;
/*     */     }
/* 109 */     while (path.startsWith("/../")) {
/* 110 */       path = path.substring(3);
/*     */       
/* 112 */       uri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), path, uri.getQuery(), uri.getFragment());
/*     */     } 
/* 114 */     String schema = uri.getScheme();
/* 115 */     if (schema != null && schema.equals("file")) {
/* 116 */       String ssp = uri.getSchemeSpecificPart();
/* 117 */       if (ssp != null && ssp.length() >= 2 && ssp.charAt(0) == '/' && ssp.charAt(1) != '/')
/*     */       {
/* 119 */         uri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), "//" + path, uri.getQuery(), uri.getFragment());
/*     */       }
/*     */     } 
/* 122 */     return uri;
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
/*     */   public static URI a(String encoding, String href) throws URISyntaxException {
/* 136 */     href = b(encoding, href);
/* 137 */     URI uri = new URI(href);
/* 138 */     return a(uri);
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
/*     */   public static URI a(String encoding, URI baseURI, String href) throws URISyntaxException {
/* 154 */     href = b(encoding, href);
/* 155 */     if (a(href)) {
/* 156 */       href = "file:///" + href;
/*     */     }
/* 158 */     URI hrefURI = new URI(href);
/* 159 */     URI uri = baseURI.resolve(hrefURI);
/* 160 */     if (uri.getScheme() == null && baseURI.getScheme() != null) {
/* 161 */       uri = new URI(baseURI.getScheme(), uri.getSchemeSpecificPart(), uri.getFragment());
/*     */     }
/* 163 */     if ((b(baseURI) && !hrefURI.isAbsolute()) || b(hrefURI)) {
/*     */       
/* 165 */       String path = uri.getSchemeSpecificPart();
/* 166 */       int start = 0;
/* 167 */       for (; start < path.length() && path.charAt(start) == '/'; start++);
/*     */       
/* 169 */       start++;
/* 170 */       if (start <= path.length() && path.charAt(start) != ':')
/*     */       {
/* 172 */         uri = new URI(uri.getScheme(), path.substring(0, start) + ':' + path.substring(start), uri.getFragment());
/*     */       }
/*     */     } 
/*     */     
/* 176 */     uri = a(uri);
/* 177 */     return uri;
/*     */   }
/*     */   
/*     */   public static URI a(String encoding, String baseURI, String href) throws URISyntaxException {
/* 181 */     return a(encoding, a(encoding, baseURI), href);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean b(URI uri) {
/* 191 */     if (!d) {
/* 192 */       return false;
/*     */     }
/* 194 */     if (!"file".equalsIgnoreCase(uri.getScheme())) {
/* 195 */       return false;
/*     */     }
/* 197 */     String path = uri.getSchemeSpecificPart();
/* 198 */     int start = 0;
/* 199 */     for (; start < path.length() && path.charAt(start) == '/'; start++);
/*     */     
/* 201 */     return (start > 0 && path.length() >= start + 2 && path.charAt(start + 1) == ':' && ((path
/* 202 */       .charAt(start) >= 'A' && path.charAt(start) <= 'Z') || (path
/* 203 */       .charAt(start) >= 'a' && path.charAt(start) <= 'z')));
/*     */   }
/*     */   
/*     */   private static boolean a(String href) {
/* 207 */     if (!d) {
/* 208 */       return false;
/*     */     }
/* 210 */     return (href.length() >= 2 && ((href.charAt(0) >= 'A' && href.charAt(0) <= 'Z') || (href
/* 211 */       .charAt(0) >= 'a' && href.charAt(0) <= 'z')) && href.charAt(1) == ':');
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/b/a/k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */