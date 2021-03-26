/*     */ package org.apache.batik.util;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import java.util.zip.InflaterInputStream;
/*     */ import java.util.zip.ZipException;
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
/*     */ public class ParsedURLData
/*     */ {
/*     */   protected static final String HTTP_USER_AGENT_HEADER = "User-Agent";
/*     */   protected static final String HTTP_ACCEPT_HEADER = "Accept";
/*     */   protected static final String HTTP_ACCEPT_LANGUAGE_HEADER = "Accept-Language";
/*     */   protected static final String HTTP_ACCEPT_ENCODING_HEADER = "Accept-Encoding";
/*  49 */   protected static List acceptedEncodings = new LinkedList();
/*     */   static {
/*  51 */     acceptedEncodings.add("gzip");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public static final byte[] GZIP_MAGIC = new byte[] { 31, -117 };
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
/*     */   public static InputStream checkGZIP(InputStream is) throws IOException {
/*  70 */     if (!is.markSupported())
/*  71 */       is = new BufferedInputStream(is); 
/*  72 */     byte[] data = new byte[2];
/*     */     try {
/*  74 */       is.mark(2);
/*  75 */       is.read(data);
/*  76 */       is.reset();
/*  77 */     } catch (Exception ex) {
/*  78 */       is.reset();
/*  79 */       return is;
/*     */     } 
/*  81 */     if (data[0] == GZIP_MAGIC[0] && data[1] == GZIP_MAGIC[1])
/*     */     {
/*  83 */       return new GZIPInputStream(is);
/*     */     }
/*  85 */     if ((data[0] & 0xF) == 8 && data[0] >>> 4 <= 7) {
/*     */ 
/*     */       
/*  88 */       int chk = (data[0] & 0xFF) * 256 + (data[1] & 0xFF);
/*     */       
/*  90 */       if (chk % 31 == 0) {
/*     */         
/*     */         try {
/*     */ 
/*     */           
/*  95 */           is.mark(100);
/*  96 */           InputStream ret = new InflaterInputStream(is);
/*  97 */           if (!ret.markSupported())
/*  98 */             ret = new BufferedInputStream(ret); 
/*  99 */           ret.mark(2);
/* 100 */           ret.read(data);
/* 101 */           is.reset();
/* 102 */           ret = new InflaterInputStream(is);
/* 103 */           return ret;
/* 104 */         } catch (ZipException ze) {
/* 105 */           is.reset();
/* 106 */           return is;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 111 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public String protocol = null;
/* 121 */   public String host = null;
/* 122 */   public int port = -1;
/* 123 */   public String path = null;
/* 124 */   public String ref = null;
/* 125 */   public String contentType = null;
/* 126 */   public String contentEncoding = null;
/*     */   
/* 128 */   public InputStream stream = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasBeenOpened = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String contentTypeMediaType;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String contentTypeCharset;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected URL postConnectionURL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParsedURLData(URL url) {
/* 156 */     this.protocol = url.getProtocol();
/* 157 */     if (this.protocol != null && this.protocol.length() == 0) {
/* 158 */       this.protocol = null;
/*     */     }
/* 160 */     this.host = url.getHost();
/* 161 */     if (this.host != null && this.host.length() == 0) {
/* 162 */       this.host = null;
/*     */     }
/* 164 */     this.port = url.getPort();
/*     */     
/* 166 */     this.path = url.getFile();
/* 167 */     if (this.path != null && this.path.length() == 0) {
/* 168 */       this.path = null;
/*     */     }
/* 170 */     this.ref = url.getRef();
/* 171 */     if (this.ref != null && this.ref.length() == 0) {
/* 172 */       this.ref = null;
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
/*     */   protected URL buildURL() throws MalformedURLException {
/* 187 */     if (this.protocol != null && this.host != null) {
/* 188 */       String file = "";
/* 189 */       if (this.path != null)
/* 190 */         file = this.path; 
/* 191 */       if (this.port == -1) {
/* 192 */         return new URL(this.protocol, this.host, file);
/*     */       }
/* 194 */       return new URL(this.protocol, this.host, this.port, file);
/*     */     } 
/*     */     
/* 197 */     return new URL(toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 204 */     int hc = this.port;
/* 205 */     if (this.protocol != null)
/* 206 */       hc ^= this.protocol.hashCode(); 
/* 207 */     if (this.host != null) {
/* 208 */       hc ^= this.host.hashCode();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 213 */     if (this.path != null) {
/* 214 */       int len = this.path.length();
/* 215 */       if (len > 20) {
/* 216 */         hc ^= this.path.substring(len - 20).hashCode();
/*     */       } else {
/* 218 */         hc ^= this.path.hashCode();
/*     */       } 
/* 220 */     }  if (this.ref != null) {
/* 221 */       int len = this.ref.length();
/* 222 */       if (len > 20) {
/* 223 */         hc ^= this.ref.substring(len - 20).hashCode();
/*     */       } else {
/* 225 */         hc ^= this.ref.hashCode();
/*     */       } 
/*     */     } 
/* 228 */     return hc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 235 */     if (obj == null) return false; 
/* 236 */     if (!(obj instanceof ParsedURLData)) {
/* 237 */       return false;
/*     */     }
/* 239 */     ParsedURLData ud = (ParsedURLData)obj;
/* 240 */     if (ud.port != this.port) {
/* 241 */       return false;
/*     */     }
/* 243 */     if (ud.protocol == null)
/* 244 */     { if (this.protocol != null)
/* 245 */         return false;  }
/* 246 */     else { if (this.protocol == null)
/* 247 */         return false; 
/* 248 */       if (!ud.protocol.equals(this.protocol))
/* 249 */         return false;  }
/*     */     
/* 251 */     if (ud.host == null)
/* 252 */     { if (this.host != null)
/* 253 */         return false;  }
/* 254 */     else { if (this.host == null)
/* 255 */         return false; 
/* 256 */       if (!ud.host.equals(this.host))
/* 257 */         return false;  }
/*     */     
/* 259 */     if (ud.ref == null)
/* 260 */     { if (this.ref != null)
/* 261 */         return false;  }
/* 262 */     else { if (this.ref == null)
/* 263 */         return false; 
/* 264 */       if (!ud.ref.equals(this.ref))
/* 265 */         return false;  }
/*     */     
/* 267 */     if (ud.path == null)
/* 268 */     { if (this.path != null)
/* 269 */         return false;  }
/* 270 */     else { if (this.path == null)
/* 271 */         return false; 
/* 272 */       if (!ud.path.equals(this.path))
/* 273 */         return false;  }
/*     */     
/* 275 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContentType(String userAgent) {
/* 283 */     if (this.contentType != null) {
/* 284 */       return this.contentType;
/*     */     }
/* 286 */     if (!this.hasBeenOpened) {
/*     */       try {
/* 288 */         openStreamInternal(userAgent, null, null);
/* 289 */       } catch (IOException iOException) {}
/*     */     }
/*     */     
/* 292 */     return this.contentType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContentTypeMediaType(String userAgent) {
/* 300 */     if (this.contentTypeMediaType != null) {
/* 301 */       return this.contentTypeMediaType;
/*     */     }
/*     */     
/* 304 */     extractContentTypeParts(userAgent);
/*     */     
/* 306 */     return this.contentTypeMediaType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContentTypeCharset(String userAgent) {
/* 314 */     if (this.contentTypeMediaType != null) {
/* 315 */       return this.contentTypeCharset;
/*     */     }
/*     */     
/* 318 */     extractContentTypeParts(userAgent);
/*     */     
/* 320 */     return this.contentTypeCharset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasContentTypeParameter(String userAgent, String param) {
/* 327 */     getContentType(userAgent);
/* 328 */     if (this.contentType == null) {
/* 329 */       return false;
/*     */     }
/* 331 */     int i = 0;
/* 332 */     int len = this.contentType.length();
/* 333 */     int plen = param.length();
/* 334 */     while (i < len) {
/* 335 */       switch (this.contentType.charAt(i)) {
/*     */         case ' ':
/*     */         case ';':
/*     */           break;
/*     */       } 
/* 340 */       i++;
/*     */     } 
/* 342 */     if (i == len) {
/* 343 */       this.contentTypeMediaType = this.contentType;
/*     */     } else {
/* 345 */       this.contentTypeMediaType = this.contentType.substring(0, i);
/*     */     } 
/*     */     label39: while (true) {
/* 348 */       if (i < len && this.contentType.charAt(i) != ';') {
/* 349 */         i++; continue;
/*     */       } 
/* 351 */       if (i == len) {
/* 352 */         return false;
/*     */       }
/* 354 */       i++;
/* 355 */       while (i < len && this.contentType.charAt(i) == ' ') {
/* 356 */         i++;
/*     */       }
/* 358 */       if (i >= len - plen - 1) {
/* 359 */         return false;
/*     */       }
/* 361 */       for (int j = 0; j < plen; j++) {
/* 362 */         if (this.contentType.charAt(i++) != param.charAt(j)) {
/*     */           continue label39;
/*     */         }
/*     */       } 
/* 366 */       if (this.contentType.charAt(i) == '=') {
/* 367 */         return true;
/*     */       }
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
/*     */   protected void extractContentTypeParts(String userAgent) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: invokevirtual getContentType : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   5: pop
/*     */     //   6: aload_0
/*     */     //   7: getfield contentType : Ljava/lang/String;
/*     */     //   10: ifnonnull -> 14
/*     */     //   13: return
/*     */     //   14: iconst_0
/*     */     //   15: istore_2
/*     */     //   16: aload_0
/*     */     //   17: getfield contentType : Ljava/lang/String;
/*     */     //   20: invokevirtual length : ()I
/*     */     //   23: istore_3
/*     */     //   24: iload_2
/*     */     //   25: iload_3
/*     */     //   26: if_icmpge -> 73
/*     */     //   29: aload_0
/*     */     //   30: getfield contentType : Ljava/lang/String;
/*     */     //   33: iload_2
/*     */     //   34: invokevirtual charAt : (I)C
/*     */     //   37: lookupswitch default -> 67, 32 -> 64, 59 -> 64
/*     */     //   64: goto -> 73
/*     */     //   67: iinc #2, 1
/*     */     //   70: goto -> 24
/*     */     //   73: iload_2
/*     */     //   74: iload_3
/*     */     //   75: if_icmpne -> 89
/*     */     //   78: aload_0
/*     */     //   79: aload_0
/*     */     //   80: getfield contentType : Ljava/lang/String;
/*     */     //   83: putfield contentTypeMediaType : Ljava/lang/String;
/*     */     //   86: goto -> 102
/*     */     //   89: aload_0
/*     */     //   90: aload_0
/*     */     //   91: getfield contentType : Ljava/lang/String;
/*     */     //   94: iconst_0
/*     */     //   95: iload_2
/*     */     //   96: invokevirtual substring : (II)Ljava/lang/String;
/*     */     //   99: putfield contentTypeMediaType : Ljava/lang/String;
/*     */     //   102: iload_2
/*     */     //   103: iload_3
/*     */     //   104: if_icmpge -> 126
/*     */     //   107: aload_0
/*     */     //   108: getfield contentType : Ljava/lang/String;
/*     */     //   111: iload_2
/*     */     //   112: invokevirtual charAt : (I)C
/*     */     //   115: bipush #59
/*     */     //   117: if_icmpeq -> 126
/*     */     //   120: iinc #2, 1
/*     */     //   123: goto -> 102
/*     */     //   126: iload_2
/*     */     //   127: iload_3
/*     */     //   128: if_icmpne -> 132
/*     */     //   131: return
/*     */     //   132: iinc #2, 1
/*     */     //   135: iload_2
/*     */     //   136: iload_3
/*     */     //   137: if_icmpge -> 159
/*     */     //   140: aload_0
/*     */     //   141: getfield contentType : Ljava/lang/String;
/*     */     //   144: iload_2
/*     */     //   145: invokevirtual charAt : (I)C
/*     */     //   148: bipush #32
/*     */     //   150: if_icmpne -> 159
/*     */     //   153: iinc #2, 1
/*     */     //   156: goto -> 135
/*     */     //   159: iload_2
/*     */     //   160: iload_3
/*     */     //   161: bipush #8
/*     */     //   163: isub
/*     */     //   164: if_icmplt -> 168
/*     */     //   167: return
/*     */     //   168: aload_0
/*     */     //   169: getfield contentType : Ljava/lang/String;
/*     */     //   172: iload_2
/*     */     //   173: iinc #2, 1
/*     */     //   176: invokevirtual charAt : (I)C
/*     */     //   179: bipush #99
/*     */     //   181: if_icmpne -> 102
/*     */     //   184: aload_0
/*     */     //   185: getfield contentType : Ljava/lang/String;
/*     */     //   188: iload_2
/*     */     //   189: iinc #2, 1
/*     */     //   192: invokevirtual charAt : (I)C
/*     */     //   195: bipush #104
/*     */     //   197: if_icmpeq -> 203
/*     */     //   200: goto -> 102
/*     */     //   203: aload_0
/*     */     //   204: getfield contentType : Ljava/lang/String;
/*     */     //   207: iload_2
/*     */     //   208: iinc #2, 1
/*     */     //   211: invokevirtual charAt : (I)C
/*     */     //   214: bipush #97
/*     */     //   216: if_icmpeq -> 222
/*     */     //   219: goto -> 102
/*     */     //   222: aload_0
/*     */     //   223: getfield contentType : Ljava/lang/String;
/*     */     //   226: iload_2
/*     */     //   227: iinc #2, 1
/*     */     //   230: invokevirtual charAt : (I)C
/*     */     //   233: bipush #114
/*     */     //   235: if_icmpeq -> 241
/*     */     //   238: goto -> 102
/*     */     //   241: aload_0
/*     */     //   242: getfield contentType : Ljava/lang/String;
/*     */     //   245: iload_2
/*     */     //   246: iinc #2, 1
/*     */     //   249: invokevirtual charAt : (I)C
/*     */     //   252: bipush #115
/*     */     //   254: if_icmpeq -> 260
/*     */     //   257: goto -> 102
/*     */     //   260: aload_0
/*     */     //   261: getfield contentType : Ljava/lang/String;
/*     */     //   264: iload_2
/*     */     //   265: iinc #2, 1
/*     */     //   268: invokevirtual charAt : (I)C
/*     */     //   271: bipush #101
/*     */     //   273: if_icmpeq -> 279
/*     */     //   276: goto -> 102
/*     */     //   279: aload_0
/*     */     //   280: getfield contentType : Ljava/lang/String;
/*     */     //   283: iload_2
/*     */     //   284: iinc #2, 1
/*     */     //   287: invokevirtual charAt : (I)C
/*     */     //   290: bipush #116
/*     */     //   292: if_icmpeq -> 298
/*     */     //   295: goto -> 102
/*     */     //   298: aload_0
/*     */     //   299: getfield contentType : Ljava/lang/String;
/*     */     //   302: iload_2
/*     */     //   303: iinc #2, 1
/*     */     //   306: invokevirtual charAt : (I)C
/*     */     //   309: bipush #61
/*     */     //   311: if_icmpeq -> 317
/*     */     //   314: goto -> 102
/*     */     //   317: iload_2
/*     */     //   318: istore #4
/*     */     //   320: iload_2
/*     */     //   321: iload_3
/*     */     //   322: if_icmpge -> 369
/*     */     //   325: aload_0
/*     */     //   326: getfield contentType : Ljava/lang/String;
/*     */     //   329: iload_2
/*     */     //   330: invokevirtual charAt : (I)C
/*     */     //   333: lookupswitch default -> 363, 32 -> 360, 59 -> 360
/*     */     //   360: goto -> 369
/*     */     //   363: iinc #2, 1
/*     */     //   366: goto -> 320
/*     */     //   369: aload_0
/*     */     //   370: aload_0
/*     */     //   371: getfield contentType : Ljava/lang/String;
/*     */     //   374: iload #4
/*     */     //   376: iload_2
/*     */     //   377: invokevirtual substring : (II)Ljava/lang/String;
/*     */     //   380: putfield contentTypeCharset : Ljava/lang/String;
/*     */     //   383: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #377	-> 0
/*     */     //   #378	-> 6
/*     */     //   #379	-> 13
/*     */     //   #381	-> 14
/*     */     //   #382	-> 16
/*     */     //   #383	-> 24
/*     */     //   #384	-> 29
/*     */     //   #387	-> 64
/*     */     //   #389	-> 67
/*     */     //   #391	-> 73
/*     */     //   #392	-> 78
/*     */     //   #394	-> 89
/*     */     //   #397	-> 102
/*     */     //   #398	-> 120
/*     */     //   #400	-> 126
/*     */     //   #401	-> 131
/*     */     //   #403	-> 132
/*     */     //   #404	-> 135
/*     */     //   #405	-> 153
/*     */     //   #407	-> 159
/*     */     //   #408	-> 167
/*     */     //   #410	-> 168
/*     */     //   #411	-> 184
/*     */     //   #412	-> 203
/*     */     //   #413	-> 222
/*     */     //   #414	-> 241
/*     */     //   #415	-> 260
/*     */     //   #416	-> 279
/*     */     //   #417	-> 298
/*     */     //   #418	-> 317
/*     */     //   #419	-> 320
/*     */     //   #420	-> 325
/*     */     //   #423	-> 360
/*     */     //   #425	-> 363
/*     */     //   #427	-> 369
/*     */     //   #428	-> 383
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	384	0	this	Lorg/apache/batik/util/ParsedURLData;
/*     */     //   0	384	1	userAgent	Ljava/lang/String;
/*     */     //   16	368	2	i	I
/*     */     //   24	360	3	len	I
/*     */     //   320	64	4	j	I
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
/*     */   public String getContentEncoding(String userAgent) {
/* 438 */     if (this.contentEncoding != null) {
/* 439 */       return this.contentEncoding;
/*     */     }
/* 441 */     if (!this.hasBeenOpened) {
/*     */       try {
/* 443 */         openStreamInternal(userAgent, null, null);
/* 444 */       } catch (IOException iOException) {}
/*     */     }
/*     */     
/* 447 */     return this.contentEncoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean complete() {
/*     */     try {
/* 457 */       buildURL();
/* 458 */     } catch (MalformedURLException mue) {
/* 459 */       return false;
/*     */     } 
/* 461 */     return true;
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
/*     */   public InputStream openStream(String userAgent, Iterator mimeTypes) throws IOException {
/* 476 */     InputStream raw = openStreamInternal(userAgent, mimeTypes, acceptedEncodings.iterator());
/*     */     
/* 478 */     if (raw == null)
/* 479 */       return null; 
/* 480 */     this.stream = null;
/*     */     
/* 482 */     return checkGZIP(raw);
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
/*     */   public InputStream openStreamRaw(String userAgent, Iterator mimeTypes) throws IOException {
/* 497 */     InputStream ret = openStreamInternal(userAgent, mimeTypes, null);
/* 498 */     this.stream = null;
/* 499 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected InputStream openStreamInternal(String userAgent, Iterator<String> mimeTypes, Iterator<String> encodingTypes) throws IOException {
/* 506 */     if (this.stream != null) {
/* 507 */       return this.stream;
/*     */     }
/* 509 */     this.hasBeenOpened = true;
/*     */     
/* 511 */     URL url = null;
/*     */     try {
/* 513 */       url = buildURL();
/* 514 */     } catch (MalformedURLException mue) {
/* 515 */       throw new IOException("Unable to make sense of URL for connection");
/*     */     } 
/*     */ 
/*     */     
/* 519 */     if (url == null) {
/* 520 */       return null;
/*     */     }
/* 522 */     URLConnection urlC = url.openConnection();
/* 523 */     if (urlC instanceof HttpURLConnection) {
/* 524 */       if (userAgent != null) {
/* 525 */         urlC.setRequestProperty("User-Agent", userAgent);
/*     */       }
/* 527 */       if (mimeTypes != null) {
/* 528 */         String acceptHeader = "";
/* 529 */         while (mimeTypes.hasNext()) {
/* 530 */           acceptHeader = acceptHeader + mimeTypes.next();
/* 531 */           if (mimeTypes.hasNext())
/* 532 */             acceptHeader = acceptHeader + ","; 
/*     */         } 
/* 534 */         urlC.setRequestProperty("Accept", acceptHeader);
/*     */       } 
/*     */       
/* 537 */       if (encodingTypes != null) {
/* 538 */         String encodingHeader = "";
/* 539 */         while (encodingTypes.hasNext()) {
/* 540 */           encodingHeader = encodingHeader + encodingTypes.next();
/* 541 */           if (encodingTypes.hasNext())
/* 542 */             encodingHeader = encodingHeader + ","; 
/*     */         } 
/* 544 */         urlC.setRequestProperty("Accept-Encoding", encodingHeader);
/*     */       } 
/*     */ 
/*     */       
/* 548 */       this.contentType = urlC.getContentType();
/* 549 */       this.contentEncoding = urlC.getContentEncoding();
/* 550 */       this.postConnectionURL = urlC.getURL();
/*     */     } 
/*     */     
/*     */     try {
/* 554 */       return this.stream = urlC.getInputStream();
/* 555 */     } catch (IOException e) {
/* 556 */       if (urlC instanceof HttpURLConnection) {
/*     */ 
/*     */         
/* 559 */         this.stream = ((HttpURLConnection)urlC).getErrorStream();
/* 560 */         if (this.stream == null) {
/* 561 */           throw e;
/*     */         }
/* 563 */         return this.stream;
/*     */       } 
/* 565 */       throw e;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPortStr() {
/* 576 */     String portStr = "";
/* 577 */     if (this.protocol != null) {
/* 578 */       portStr = portStr + this.protocol + ":";
/*     */     }
/* 580 */     if (this.host != null || this.port != -1) {
/* 581 */       portStr = portStr + "//";
/* 582 */       if (this.host != null) portStr = portStr + this.host; 
/* 583 */       if (this.port != -1) portStr = portStr + ":" + this.port;
/*     */     
/*     */     } 
/* 586 */     return portStr;
/*     */   }
/*     */   
/*     */   protected boolean sameFile(ParsedURLData other) {
/* 590 */     if (this == other) return true;
/*     */ 
/*     */ 
/*     */     
/* 594 */     if (this.port == other.port && (this.path == other.path || (this.path != null && this.path.equals(other.path))) && (this.host == other.host || (this.host != null && this.host.equals(other.host))) && (this.protocol == other.protocol || (this.protocol != null && this.protocol.equals(other.protocol))))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 601 */       return true;
/*     */     }
/* 603 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 611 */     String ret = getPortStr();
/* 612 */     if (this.path != null) {
/* 613 */       ret = ret + this.path;
/*     */     }
/* 615 */     if (this.ref != null) {
/* 616 */       ret = ret + "#" + this.ref;
/*     */     }
/* 618 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPostConnectionURL() {
/* 626 */     if (this.postConnectionURL != null) {
/* 627 */       if (this.ref != null) {
/* 628 */         return this.postConnectionURL.toString() + '#' + this.ref;
/*     */       }
/* 630 */       return this.postConnectionURL.toString();
/*     */     } 
/* 632 */     return toString();
/*     */   }
/*     */   
/*     */   public ParsedURLData() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/ParsedURLData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */