/*     */ package org.apache.batik.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.Version;
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
/*     */ public class ParsedURL
/*     */ {
/*     */   ParsedURLData data;
/*     */   String userAgent;
/*  80 */   private static Map handlersMap = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static ParsedURLProtocolHandler defaultHandler = new ParsedURLDefaultProtocolHandler();
/*     */ 
/*     */   
/*  90 */   private static String globalUserAgent = "Batik/" + Version.getVersion();
/*     */   public static String getGlobalUserAgent() {
/*  92 */     return globalUserAgent;
/*     */   }
/*     */   public static void setGlobalUserAgent(String userAgent) {
/*  95 */     globalUserAgent = userAgent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static synchronized Map getHandlersMap() {
/* 105 */     if (handlersMap != null) return handlersMap;
/*     */     
/* 107 */     handlersMap = new HashMap<Object, Object>();
/* 108 */     registerHandler(new ParsedURLDataProtocolHandler());
/* 109 */     registerHandler(new ParsedURLJarProtocolHandler());
/*     */     
/* 111 */     Iterator<ParsedURLProtocolHandler> iter = Service.providers(ParsedURLProtocolHandler.class);
/* 112 */     while (iter.hasNext()) {
/*     */       
/* 114 */       ParsedURLProtocolHandler handler = iter.next();
/*     */ 
/*     */       
/* 117 */       registerHandler(handler);
/*     */     } 
/*     */ 
/*     */     
/* 121 */     return handlersMap;
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
/*     */   public static synchronized ParsedURLProtocolHandler getHandler(String protocol) {
/* 133 */     if (protocol == null) {
/* 134 */       return defaultHandler;
/*     */     }
/* 136 */     Map handlers = getHandlersMap();
/*     */     
/* 138 */     ParsedURLProtocolHandler ret = (ParsedURLProtocolHandler)handlers.get(protocol);
/* 139 */     if (ret == null)
/* 140 */       ret = defaultHandler; 
/* 141 */     return ret;
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
/*     */   public static synchronized void registerHandler(ParsedURLProtocolHandler handler) {
/* 153 */     if (handler.getProtocolHandled() == null) {
/* 154 */       defaultHandler = handler;
/*     */       
/*     */       return;
/*     */     } 
/* 158 */     Map<String, ParsedURLProtocolHandler> handlers = getHandlersMap();
/* 159 */     handlers.put(handler.getProtocolHandled(), handler);
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
/*     */   public static InputStream checkGZIP(InputStream is) throws IOException {
/* 171 */     return ParsedURLData.checkGZIP(is);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParsedURL(String urlStr) {
/* 179 */     this.userAgent = getGlobalUserAgent();
/* 180 */     this.data = parseURL(urlStr);
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
/*     */   public ParsedURL(URL url) {
/* 193 */     this.userAgent = getGlobalUserAgent();
/* 194 */     this.data = new ParsedURLData(url);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParsedURL(String baseStr, String urlStr) {
/* 204 */     this.userAgent = getGlobalUserAgent();
/* 205 */     if (baseStr != null) {
/* 206 */       this.data = parseURL(baseStr, urlStr);
/*     */     } else {
/* 208 */       this.data = parseURL(urlStr);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParsedURL(URL baseURL, String urlStr) {
/* 218 */     this.userAgent = getGlobalUserAgent();
/*     */     
/* 220 */     if (baseURL != null) {
/* 221 */       this.data = parseURL(new ParsedURL(baseURL), urlStr);
/*     */     } else {
/* 223 */       this.data = parseURL(urlStr);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParsedURL(ParsedURL baseURL, String urlStr) {
/* 233 */     if (baseURL != null) {
/* 234 */       this.userAgent = baseURL.getUserAgent();
/* 235 */       this.data = parseURL(baseURL, urlStr);
/*     */     } else {
/* 237 */       this.data = parseURL(urlStr);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 246 */     return this.data.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPostConnectionURL() {
/* 257 */     return this.data.getPostConnectionURL();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 266 */     if (obj == null) return false; 
/* 267 */     if (!(obj instanceof ParsedURL))
/* 268 */       return false; 
/* 269 */     ParsedURL purl = (ParsedURL)obj;
/* 270 */     return this.data.equals(purl.data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 279 */     return this.data.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean complete() {
/* 288 */     return this.data.complete();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserAgent() {
/* 296 */     return this.userAgent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserAgent(String userAgent) {
/* 303 */     this.userAgent = userAgent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProtocol() {
/* 311 */     if (this.data.protocol == null) return null; 
/* 312 */     return this.data.protocol;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHost() {
/* 320 */     if (this.data.host == null) return null; 
/* 321 */     return this.data.host;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPort() {
/* 328 */     return this.data.port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPath() {
/* 337 */     if (this.data.path == null) return null; 
/* 338 */     return this.data.path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRef() {
/* 345 */     if (this.data.ref == null) return null; 
/* 346 */     return this.data.ref;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPortStr() {
/* 355 */     return this.data.getPortStr();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContentType() {
/* 363 */     return this.data.getContentType(this.userAgent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContentTypeMediaType() {
/* 371 */     return this.data.getContentTypeMediaType(this.userAgent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContentTypeCharset() {
/* 379 */     return this.data.getContentTypeCharset(this.userAgent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasContentTypeParameter(String param) {
/* 386 */     return this.data.hasContentTypeParameter(this.userAgent, param);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContentEncoding() {
/* 394 */     return this.data.getContentEncoding(this.userAgent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream openStream() throws IOException {
/* 402 */     return this.data.openStream(this.userAgent, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream openStream(String mimeType) throws IOException {
/* 413 */     List<String> mt = new ArrayList(1);
/* 414 */     mt.add(mimeType);
/* 415 */     return this.data.openStream(this.userAgent, mt.iterator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream openStream(String[] mimeTypes) throws IOException {
/* 426 */     List<String> mt = new ArrayList(mimeTypes.length);
/* 427 */     for (String mimeType : mimeTypes) mt.add(mimeType); 
/* 428 */     return this.data.openStream(this.userAgent, mt.iterator());
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
/*     */   public InputStream openStream(Iterator mimeTypes) throws IOException {
/* 440 */     return this.data.openStream(this.userAgent, mimeTypes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream openStreamRaw() throws IOException {
/* 448 */     return this.data.openStreamRaw(this.userAgent, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream openStreamRaw(String mimeType) throws IOException {
/* 459 */     List<String> mt = new ArrayList(1);
/* 460 */     mt.add(mimeType);
/* 461 */     return this.data.openStreamRaw(this.userAgent, mt.iterator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream openStreamRaw(String[] mimeTypes) throws IOException {
/* 472 */     List mt = new ArrayList(mimeTypes.length);
/* 473 */     mt.addAll(Arrays.asList(mimeTypes));
/* 474 */     return this.data.openStreamRaw(this.userAgent, mt.iterator());
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
/*     */   public InputStream openStreamRaw(Iterator mimeTypes) throws IOException {
/* 486 */     return this.data.openStreamRaw(this.userAgent, mimeTypes);
/*     */   }
/*     */   
/*     */   public boolean sameFile(ParsedURL other) {
/* 490 */     return this.data.sameFile(other.data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String getProtocol(String urlStr) {
/* 500 */     if (urlStr == null) return null; 
/* 501 */     int idx = 0, len = urlStr.length();
/*     */     
/* 503 */     if (len == 0) return null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 509 */     char ch = urlStr.charAt(idx);
/*     */ 
/*     */ 
/*     */     
/* 513 */     while (ch == '-' || ch == '+' || ch == '.' || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
/*     */       
/* 515 */       idx++;
/* 516 */       if (idx == len) {
/* 517 */         ch = Character.MIN_VALUE;
/*     */         break;
/*     */       } 
/* 520 */       ch = urlStr.charAt(idx);
/*     */     } 
/* 522 */     if (ch == ':')
/*     */     {
/* 524 */       return urlStr.substring(0, idx).toLowerCase();
/*     */     }
/* 526 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ParsedURLData parseURL(String urlStr) {
/* 534 */     if (urlStr != null && !urlStr.contains(":") && !urlStr.startsWith("#"))
/*     */     {
/* 536 */       urlStr = "file:" + urlStr;
/*     */     }
/* 538 */     ParsedURLProtocolHandler handler = getHandler(getProtocol(urlStr));
/* 539 */     return handler.parseURL(urlStr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ParsedURLData parseURL(String baseStr, String urlStr) {
/* 549 */     if (baseStr == null) {
/* 550 */       return parseURL(urlStr);
/*     */     }
/* 552 */     ParsedURL purl = new ParsedURL(baseStr);
/* 553 */     return parseURL(purl, urlStr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ParsedURLData parseURL(ParsedURL baseURL, String urlStr) {
/* 563 */     if (baseURL == null) {
/* 564 */       return parseURL(urlStr);
/*     */     }
/* 566 */     String protocol = getProtocol(urlStr);
/* 567 */     if (protocol == null)
/* 568 */       protocol = baseURL.getProtocol(); 
/* 569 */     ParsedURLProtocolHandler handler = getHandler(protocol);
/* 570 */     return handler.parseURL(baseURL, urlStr);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/ParsedURL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */