/*     */ package org.apache.batik.util;
/*     */ 
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
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
/*     */ public class ParsedURLDefaultProtocolHandler
/*     */   extends AbstractParsedURLProtocolHandler
/*     */ {
/*     */   public ParsedURLDefaultProtocolHandler() {
/*  42 */     super(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ParsedURLDefaultProtocolHandler(String protocol) {
/*  50 */     super(protocol);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ParsedURLData constructParsedURLData() {
/*  58 */     return new ParsedURLData();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ParsedURLData constructParsedURLData(URL url) {
/*  67 */     return new ParsedURLData(url);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParsedURLData parseURL(String urlStr) {
/*     */     try {
/*  77 */       URL url = new URL(urlStr);
/*     */       
/*  79 */       return constructParsedURLData(url);
/*  80 */     } catch (MalformedURLException malformedURLException) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  88 */       ParsedURLData ret = constructParsedURLData();
/*     */       
/*  90 */       if (urlStr == null) return ret;
/*     */       
/*  92 */       int pidx = 0;
/*  93 */       int len = urlStr.length();
/*     */ 
/*     */       
/*  96 */       int idx = urlStr.indexOf('#');
/*  97 */       ret.ref = null;
/*  98 */       if (idx != -1) {
/*  99 */         if (idx + 1 < len)
/* 100 */           ret.ref = urlStr.substring(idx + 1); 
/* 101 */         urlStr = urlStr.substring(0, idx);
/* 102 */         len = urlStr.length();
/*     */       } 
/*     */       
/* 105 */       if (len == 0) {
/* 106 */         return ret;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 112 */       idx = 0;
/* 113 */       char ch = urlStr.charAt(idx);
/*     */ 
/*     */ 
/*     */       
/* 117 */       while (ch == '-' || ch == '+' || ch == '.' || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
/*     */         
/* 119 */         idx++;
/* 120 */         if (idx == len) {
/* 121 */           ch = Character.MIN_VALUE;
/*     */           break;
/*     */         } 
/* 124 */         ch = urlStr.charAt(idx);
/*     */       } 
/*     */       
/* 127 */       if (ch == ':') {
/*     */         
/* 129 */         ret.protocol = urlStr.substring(pidx, idx).toLowerCase();
/* 130 */         pidx = idx + 1;
/*     */       } 
/*     */ 
/*     */       
/* 134 */       idx = urlStr.indexOf('/');
/* 135 */       if (idx == -1 || (pidx + 2 < len && urlStr.charAt(pidx) == '/' && urlStr.charAt(pidx + 1) == '/')) {
/*     */         String hostPort;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 141 */         if (idx != -1) {
/* 142 */           pidx += 2;
/*     */         }
/* 144 */         idx = urlStr.indexOf('/', pidx);
/*     */         
/* 146 */         if (idx == -1) {
/*     */           
/* 148 */           hostPort = urlStr.substring(pidx);
/*     */         } else {
/*     */           
/* 151 */           hostPort = urlStr.substring(pidx, idx);
/*     */         } 
/* 153 */         int hidx = idx;
/*     */ 
/*     */         
/* 156 */         idx = hostPort.indexOf(':');
/* 157 */         ret.port = -1;
/* 158 */         if (idx == -1) {
/*     */           
/* 160 */           if (hostPort.length() == 0) {
/* 161 */             ret.host = null;
/*     */           } else {
/* 163 */             ret.host = hostPort;
/*     */           } 
/*     */         } else {
/* 166 */           if (idx == 0) { ret.host = null; }
/* 167 */           else { ret.host = hostPort.substring(0, idx); }
/*     */           
/* 169 */           if (idx + 1 < hostPort.length()) {
/* 170 */             String portStr = hostPort.substring(idx + 1);
/*     */             try {
/* 172 */               ret.port = Integer.parseInt(portStr);
/* 173 */             } catch (NumberFormatException numberFormatException) {}
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 178 */         if ((ret.host == null || ret.host.indexOf('.') == -1) && ret.port == -1) {
/*     */ 
/*     */ 
/*     */           
/* 182 */           ret.host = null;
/*     */         } else {
/* 184 */           pidx = hidx;
/*     */         } 
/*     */       } 
/* 187 */       if (pidx == -1 || pidx >= len) return ret;
/*     */       
/* 189 */       ret.path = urlStr.substring(pidx);
/* 190 */       return ret;
/*     */     } 
/*     */   }
/*     */   public static String unescapeStr(String str) {
/* 194 */     int idx = str.indexOf('%');
/* 195 */     if (idx == -1) return str;
/*     */     
/* 197 */     int prev = 0;
/* 198 */     StringBuffer ret = new StringBuffer();
/* 199 */     while (idx != -1) {
/* 200 */       if (idx != prev) {
/* 201 */         ret.append(str.substring(prev, idx));
/*     */       }
/* 203 */       if (idx + 2 >= str.length())
/* 204 */         break;  prev = idx + 3;
/* 205 */       idx = str.indexOf('%', prev);
/*     */       
/* 207 */       int ch1 = charToHex(str.charAt(idx + 1));
/* 208 */       int ch2 = charToHex(str.charAt(idx + 1));
/* 209 */       if (ch1 == -1 || ch2 == -1)
/* 210 */         continue;  ret.append((char)(ch1 << 4 | ch2));
/*     */     } 
/*     */     
/* 213 */     return ret.toString();
/*     */   }
/*     */   
/*     */   public static int charToHex(int ch) {
/* 217 */     switch (ch) { case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55:
/*     */       case 56:
/*     */       case 57:
/* 220 */         return ch - 48;
/* 221 */       case 65: case 97: return 10;
/* 222 */       case 66: case 98: return 11;
/* 223 */       case 67: case 99: return 12;
/* 224 */       case 68: case 100: return 13;
/* 225 */       case 69: case 101: return 14;
/* 226 */       case 70: case 102: return 15; }
/* 227 */      return -1;
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
/*     */   public ParsedURLData parseURL(ParsedURL baseURL, String urlStr) {
/* 239 */     if (urlStr.length() == 0) {
/* 240 */       return baseURL.data;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 245 */     int idx = 0, len = urlStr.length();
/* 246 */     if (len == 0) return baseURL.data;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 252 */     char ch = urlStr.charAt(idx);
/*     */ 
/*     */ 
/*     */     
/* 256 */     while (ch == '-' || ch == '+' || ch == '.' || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
/*     */       
/* 258 */       idx++;
/* 259 */       if (idx == len) {
/* 260 */         ch = Character.MIN_VALUE;
/*     */         break;
/*     */       } 
/* 263 */       ch = urlStr.charAt(idx);
/*     */     } 
/* 265 */     String protocol = null;
/* 266 */     if (ch == ':')
/*     */     {
/* 268 */       protocol = urlStr.substring(0, idx).toLowerCase();
/*     */     }
/*     */     
/* 271 */     if (protocol != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 278 */       if (!protocol.equals(baseURL.getProtocol()))
/*     */       {
/* 280 */         return parseURL(urlStr);
/*     */       }
/*     */ 
/*     */       
/* 284 */       idx++;
/* 285 */       if (idx == urlStr.length())
/*     */       {
/* 287 */         return parseURL(urlStr);
/*     */       }
/* 289 */       if (urlStr.charAt(idx) == '/')
/*     */       {
/* 291 */         return parseURL(urlStr);
/*     */       }
/*     */ 
/*     */       
/* 295 */       urlStr = urlStr.substring(idx);
/*     */     } 
/*     */     
/* 298 */     if (urlStr.startsWith("/")) {
/* 299 */       if (urlStr.length() > 1 && urlStr.charAt(1) == '/')
/*     */       {
/*     */         
/* 302 */         return parseURL(baseURL.getProtocol() + ":" + urlStr);
/*     */       }
/*     */ 
/*     */       
/* 306 */       return parseURL(baseURL.getPortStr() + urlStr);
/*     */     } 
/*     */     
/* 309 */     if (urlStr.startsWith("#")) {
/* 310 */       String base = baseURL.getPortStr();
/* 311 */       if (baseURL.getPath() != null) base = base + baseURL.getPath(); 
/* 312 */       return parseURL(base + urlStr);
/*     */     } 
/*     */     
/* 315 */     String path = baseURL.getPath();
/*     */     
/* 317 */     if (path == null) path = ""; 
/* 318 */     idx = path.lastIndexOf('/');
/* 319 */     if (idx == -1) {
/*     */ 
/*     */       
/* 322 */       path = "";
/*     */     } else {
/* 324 */       path = path.substring(0, idx + 1);
/* 325 */       if (urlStr.startsWith(path)) {
/* 326 */         urlStr = urlStr.substring(path.length());
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 332 */     return parseURL(baseURL.getPortStr() + path + urlStr);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/ParsedURLDefaultProtocolHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */