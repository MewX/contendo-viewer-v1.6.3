/*     */ package org.apache.batik.util;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Iterator;
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
/*     */ public class ParsedURLDataProtocolHandler
/*     */   extends AbstractParsedURLProtocolHandler
/*     */ {
/*     */   static final String DATA_PROTOCOL = "data";
/*     */   static final String BASE64 = "base64";
/*     */   static final String CHARSET = "charset";
/*     */   
/*     */   public ParsedURLDataProtocolHandler() {
/*  42 */     super("data");
/*     */   }
/*     */ 
/*     */   
/*     */   public ParsedURLData parseURL(ParsedURL baseURL, String urlStr) {
/*  47 */     return parseURL(urlStr);
/*     */   }
/*     */   
/*     */   public ParsedURLData parseURL(String urlStr) {
/*  51 */     DataParsedURLData ret = new DataParsedURLData();
/*     */     
/*  53 */     int pidx = 0;
/*  54 */     int len = urlStr.length();
/*     */ 
/*     */     
/*  57 */     int idx = urlStr.indexOf('#');
/*  58 */     ret.ref = null;
/*  59 */     if (idx != -1) {
/*  60 */       if (idx + 1 < len) {
/*  61 */         ret.ref = urlStr.substring(idx + 1);
/*     */       }
/*  63 */       urlStr = urlStr.substring(0, idx);
/*  64 */       len = urlStr.length();
/*     */     } 
/*     */     
/*  67 */     idx = urlStr.indexOf(':');
/*  68 */     if (idx != -1) {
/*     */       
/*  70 */       ret.protocol = urlStr.substring(pidx, idx);
/*  71 */       if (ret.protocol.indexOf('/') == -1) {
/*  72 */         pidx = idx + 1;
/*     */       }
/*     */       else {
/*     */         
/*  76 */         ret.protocol = null;
/*  77 */         pidx = 0;
/*     */       } 
/*     */     } 
/*     */     
/*  81 */     idx = urlStr.indexOf(',', pidx);
/*  82 */     if (idx != -1 && idx != pidx) {
/*  83 */       ret.host = urlStr.substring(pidx, idx);
/*  84 */       pidx = idx + 1;
/*     */       
/*  86 */       int aidx = ret.host.lastIndexOf(';');
/*  87 */       if (aidx == -1 || aidx == ret.host.length()) {
/*  88 */         ret.contentType = ret.host;
/*     */       } else {
/*  90 */         String enc = ret.host.substring(aidx + 1);
/*  91 */         idx = enc.indexOf('=');
/*  92 */         if (idx == -1) {
/*     */           
/*  94 */           ret.contentEncoding = enc;
/*  95 */           ret.contentType = ret.host.substring(0, aidx);
/*     */         } else {
/*  97 */           ret.contentType = ret.host;
/*     */         } 
/*     */         
/* 100 */         aidx = 0;
/* 101 */         idx = ret.contentType.indexOf(';', aidx);
/* 102 */         if (idx != -1) {
/* 103 */           aidx = idx + 1;
/* 104 */           while (aidx < ret.contentType.length()) {
/* 105 */             idx = ret.contentType.indexOf(';', aidx);
/* 106 */             if (idx == -1) idx = ret.contentType.length(); 
/* 107 */             String param = ret.contentType.substring(aidx, idx);
/* 108 */             int eqIdx = param.indexOf('=');
/* 109 */             if (eqIdx != -1 && "charset".equals(param.substring(0, eqIdx)))
/*     */             {
/* 111 */               ret.charset = param.substring(eqIdx + 1); } 
/* 112 */             aidx = idx + 1;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 118 */     if (pidx < urlStr.length()) {
/* 119 */       ret.path = urlStr.substring(pidx);
/*     */     }
/*     */     
/* 122 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   static class DataParsedURLData
/*     */     extends ParsedURLData
/*     */   {
/*     */     String charset;
/*     */ 
/*     */     
/*     */     public boolean complete() {
/* 133 */       return (this.path != null);
/*     */     }
/*     */     
/*     */     public String getPortStr() {
/* 137 */       String portStr = "data:";
/* 138 */       if (this.host != null) {
/* 139 */         portStr = portStr + this.host;
/*     */       }
/* 141 */       portStr = portStr + ",";
/* 142 */       return portStr;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 146 */       String ret = getPortStr();
/* 147 */       if (this.path != null) {
/* 148 */         ret = ret + this.path;
/*     */       }
/* 150 */       if (this.ref != null) {
/* 151 */         ret = ret + '#' + this.ref;
/*     */       }
/* 153 */       return ret;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getContentType(String userAgent) {
/* 161 */       return this.contentType;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getContentEncoding(String userAgent) {
/* 169 */       return this.contentEncoding;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected InputStream openStreamInternal(String userAgent, Iterator mimeTypes, Iterator encodingTypes) throws IOException {
/* 175 */       this.stream = decode(this.path);
/* 176 */       if ("base64".equals(this.contentEncoding)) {
/* 177 */         this.stream = new Base64DecodeStream(this.stream);
/*     */       }
/* 179 */       return this.stream;
/*     */     }
/*     */     
/*     */     public static InputStream decode(String s) {
/* 183 */       int len = s.length();
/* 184 */       byte[] data = new byte[len];
/* 185 */       int j = 0;
/* 186 */       for (int i = 0; i < len; i++) {
/* 187 */         char c = s.charAt(i);
/* 188 */         switch (c) { default:
/* 189 */             data[j++] = (byte)c; break;
/*     */           case '%':
/* 191 */             if (i + 2 < len) {
/* 192 */               i += 2;
/*     */               
/* 194 */               char c1 = s.charAt(i - 1);
/* 195 */               if (c1 >= '0' && c1 <= '9') { b = (byte)(c1 - 48); }
/* 196 */               else if (c1 >= 'a' && c1 <= 'z') { b = (byte)(c1 - 97 + 10); }
/* 197 */               else if (c1 >= 'A' && c1 <= 'Z') { b = (byte)(c1 - 65 + 10); }
/*     */               else { break; }
/* 199 */                byte b = (byte)(b * 16);
/*     */               
/* 201 */               char c2 = s.charAt(i);
/* 202 */               if (c2 >= '0' && c2 <= '9') { b = (byte)(b + (byte)(c2 - 48)); }
/* 203 */               else if (c2 >= 'a' && c2 <= 'z') { b = (byte)(b + (byte)(c2 - 97 + 10)); }
/* 204 */               else if (c2 >= 'A' && c2 <= 'Z') { b = (byte)(b + (byte)(c2 - 65 + 10)); }
/*     */               else { break; }
/* 206 */                data[j++] = b;
/*     */             } 
/*     */             break; }
/*     */ 
/*     */       
/*     */       } 
/* 212 */       return new ByteArrayInputStream(data, 0, j);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/ParsedURLDataProtocolHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */