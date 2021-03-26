/*     */ package jp.cssj.e.e;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.io.Writer;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URLDecoder;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetEncoder;
/*     */ import java.util.BitSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class d
/*     */ {
/*  22 */   private static final char[] b = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*     */ 
/*     */ 
/*     */   
/*  26 */   private static BitSet c = new BitSet(256);
/*     */   static {
/*     */     int i;
/*  29 */     for (i = 97; i <= 122; i++) {
/*  30 */       c.set(i);
/*     */     }
/*     */     
/*  33 */     for (i = 65; i <= 90; i++) {
/*  34 */       c.set(i);
/*     */     }
/*     */     
/*  37 */     for (i = 48; i <= 57; i++) {
/*  38 */       c.set(i);
/*     */     }
/*     */ 
/*     */     
/*  42 */     c.set(36);
/*  43 */     c.set(45);
/*  44 */     c.set(95);
/*  45 */     c.set(46);
/*  46 */     c.set(43);
/*  47 */     c.set(37);
/*     */ 
/*     */     
/*  50 */     c.set(33);
/*  51 */     c.set(42);
/*  52 */     c.set(39);
/*  53 */     c.set(40);
/*  54 */     c.set(41);
/*  55 */     c.set(44);
/*     */ 
/*     */ 
/*     */     
/*  59 */     c.set(47);
/*  60 */     c.set(58);
/*  61 */     c.set(59);
/*  62 */     c.set(64);
/*  63 */     c.set(38);
/*  64 */     c.set(61);
/*  65 */     c.set(63);
/*  66 */     c.set(35);
/*     */   }
/*  68 */   private static final boolean d = System.getProperty("os.name").startsWith("Windows");
/*     */   
/*  70 */   public static final URI a = URI.create(".");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String b(String encoding, String uri) {
/*     */     CharsetEncoder enc;
/*  77 */     uri = uri.trim();
/*     */     
/*  79 */     Charset cs = Charset.forName(encoding);
/*  80 */     if (cs.name().startsWith("UTF-")) {
/*  81 */       cs = Charset.forName("UTF-8");
/*     */     }
/*     */     
/*  84 */     if (cs.canEncode()) {
/*  85 */       enc = cs.newEncoder();
/*     */     } else {
/*  87 */       enc = Charset.forName("UTF-8").newEncoder();
/*     */     } 
/*     */     
/*  90 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/*     */     
/*  92 */     try (Writer writer = new OutputStreamWriter(out, enc)) {
/*  93 */       for (int j = 0; j < uri.length(); j++) {
/*  94 */         char c = uri.charAt(j);
/*  95 */         if (!Character.isISOControl(c)) {
/*  96 */           writer.write(c);
/*     */         }
/*     */       }
/*     */     
/* 100 */     } catch (IOException e) {
/* 101 */       throw new RuntimeException(e);
/*     */     } 
/* 103 */     byte[] bytes = out.toByteArray();
/* 104 */     StringBuffer buff = new StringBuffer(bytes.length);
/* 105 */     for (int i = 0; i < bytes.length; i++) {
/* 106 */       int b = bytes[i] & 0xFF;
/* 107 */       if (d.c.get(b)) {
/* 108 */         buff.append((char)b);
/*     */       } else {
/* 110 */         buff.append('%');
/* 111 */         buff.append(d.b[b >> 4]);
/* 112 */         buff.append(d.b[b & 0xF]);
/*     */       } 
/*     */     } 
/* 115 */     uri = buff.toString();
/* 116 */     return uri;
/*     */   }
/*     */   
/*     */   private static URI a(URI uri) throws URISyntaxException {
/* 120 */     String path = uri.getPath();
/* 121 */     if (path == null) {
/* 122 */       return uri;
/*     */     }
/* 124 */     while (path.startsWith("/../")) {
/* 125 */       path = path.substring(3);
/*     */       
/* 127 */       uri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), path, uri.getQuery(), uri.getFragment());
/*     */     } 
/* 129 */     String schema = uri.getScheme();
/* 130 */     if (schema != null && schema.equals("file")) {
/* 131 */       String ssp = uri.getSchemeSpecificPart();
/* 132 */       if (ssp != null && ssp.length() >= 2 && ssp.charAt(0) == '/' && ssp.charAt(1) != '/')
/*     */       {
/* 134 */         uri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), "//" + path, uri.getQuery(), uri.getFragment());
/*     */       }
/*     */     } 
/* 137 */     return uri;
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
/* 151 */     href = b(encoding, href);
/* 152 */     URI uri = new URI(href);
/* 153 */     return a(uri);
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
/* 169 */     String scheme = baseURI.getScheme();
/* 170 */     if (("http".equals(scheme) || "https".equals(scheme)) && !href.startsWith("file:")) {
/* 171 */       href = b(encoding, href);
/*     */     }
/* 173 */     if (b(href)) {
/* 174 */       href = "file:///" + href;
/*     */     }
/* 176 */     URI hrefURI = new URI(href);
/* 177 */     URI uri = baseURI.resolve(hrefURI);
/* 178 */     if (uri.getScheme() == null && baseURI.getScheme() != null) {
/* 179 */       uri = new URI(baseURI.getScheme(), uri.getSchemeSpecificPart(), uri.getFragment());
/*     */     }
/* 181 */     if ((b(baseURI) && !hrefURI.isAbsolute()) || b(hrefURI)) {
/*     */       
/* 183 */       String path = uri.getSchemeSpecificPart();
/* 184 */       int start = 0;
/* 185 */       for (; start < path.length() && path.charAt(start) == '/'; start++);
/*     */       
/* 187 */       start++;
/* 188 */       if (start <= path.length() && path.charAt(start) != ':')
/*     */       {
/* 190 */         uri = new URI(uri.getScheme(), path.substring(0, start) + ':' + path.substring(start), uri.getFragment());
/*     */       }
/*     */     } 
/*     */     
/* 194 */     uri = a(uri);
/* 195 */     return uri;
/*     */   }
/*     */   
/*     */   public static URI a(String encoding, String baseURI, String href) throws URISyntaxException {
/* 199 */     return a(encoding, a(encoding, baseURI), href);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean b(URI uri) {
/* 209 */     if (!d) {
/* 210 */       return false;
/*     */     }
/* 212 */     if (!"file".equalsIgnoreCase(uri.getScheme())) {
/* 213 */       return false;
/*     */     }
/* 215 */     String path = uri.getSchemeSpecificPart();
/* 216 */     int start = 0;
/* 217 */     for (; start < path.length() && path.charAt(start) == '/'; start++);
/*     */     
/* 219 */     return (start > 0 && path.length() >= start + 2 && path.charAt(start + 1) == ':' && ((path
/* 220 */       .charAt(start) >= 'A' && path.charAt(start) <= 'Z') || (path
/* 221 */       .charAt(start) >= 'a' && path.charAt(start) <= 'z')));
/*     */   }
/*     */   
/*     */   private static boolean b(String href) {
/* 225 */     if (!d) {
/* 226 */       return false;
/*     */     }
/* 228 */     return (href.length() >= 2 && ((href.charAt(0) >= 'A' && href.charAt(0) <= 'Z') || (href
/* 229 */       .charAt(0) >= 'a' && href.charAt(0) <= 'z')) && href.charAt(1) == ':');
/*     */   }
/*     */ 
/*     */   
/*     */   public static String a(String uri) {
/* 234 */     uri = uri.replace("+", "%2B");
/*     */     try {
/* 236 */       return URLDecoder.decode(uri, "UTF-8");
/* 237 */     } catch (UnsupportedEncodingException e) {
/* 238 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/e/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */