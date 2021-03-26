/*     */ package jp.cssj.homare.xml.b;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.text.ParseException;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.sakae.sac.css.InputSource;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ import org.xml.sax.helpers.DefaultHandler;
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
/*     */ public final class d
/*     */ {
/*  27 */   public static DefaultHandler a = new DefaultHandler();
/*     */ 
/*     */   
/*     */   public static InputSource a(b source, String charset, String mediaTypes, String title) throws IOException {
/*  31 */     InputSource inputSource = new InputSource();
/*  32 */     String encoding = source.a();
/*  33 */     if (encoding != null) {
/*  34 */       inputSource.setEncoding(encoding);
/*  35 */       if (source.i()) {
/*  36 */         inputSource.setCharacterStream(source.j());
/*     */       } else {
/*  38 */         inputSource.setByteStream(source.h());
/*     */       }
/*     */     
/*  41 */     } else if (charset != null) {
/*  42 */       inputSource.setEncoding(charset);
/*  43 */       if (source.i()) {
/*  44 */         inputSource.setCharacterStream(source.j());
/*     */       } else {
/*  46 */         inputSource.setCharacterStream(new InputStreamReader(source.h(), charset));
/*     */       }
/*     */     
/*  49 */     } else if (source.i()) {
/*  50 */       inputSource.setCharacterStream(source.j());
/*     */     } else {
/*  52 */       inputSource.setByteStream(source.h());
/*     */     } 
/*     */ 
/*     */     
/*  56 */     inputSource.setMedia(mediaTypes);
/*  57 */     inputSource.setTitle(title);
/*  58 */     inputSource.setURI(source.d().toString());
/*  59 */     return inputSource;
/*     */   }
/*     */   
/*     */   public static InputSource a(b source) throws IOException {
/*     */     InputSource inputSource;
/*  64 */     if (source.i()) {
/*  65 */       inputSource = new InputSource(source.j());
/*     */     } else {
/*  67 */       inputSource = new InputSource(source.h());
/*     */     } 
/*  69 */     inputSource.setSystemId(source.d().toString());
/*  70 */     return inputSource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String a(InputStream in) throws IOException {
/*  81 */     in.mark(3);
/*  82 */     String encoding = null;
/*  83 */     int c = in.read();
/*  84 */     if (c == 254) {
/*  85 */       if (in.read() == 255) {
/*  86 */         encoding = "UTF-16BE";
/*     */       }
/*  88 */     } else if (c == 255) {
/*  89 */       if (in.read() == 254) {
/*  90 */         encoding = "UTF-16LE";
/*     */       }
/*  92 */     } else if (c == 239 && 
/*  93 */       in.read() == 187 && in.read() == 191) {
/*  94 */       encoding = "UTF-8";
/*     */     } 
/*     */     
/*  97 */     if (encoding == null) {
/*  98 */       in.reset();
/*     */     }
/* 100 */     return encoding;
/*     */   }
/*     */   
/*     */   public static String b(InputStream in) throws IOException {
/* 104 */     in.mark(100);
/* 105 */     for (int i = 0; i < 10; i++) {
/* 106 */       if (in.read() == 60)
/*     */       {
/*     */         
/* 109 */         if (in.read() == 63)
/*     */         {
/*     */           
/* 112 */           if (in.read() == 120)
/*     */           {
/*     */             
/* 115 */             if (in.read() == 109)
/*     */             {
/*     */               
/* 118 */               if (in.read() == 108)
/*     */               {
/*     */                 
/* 121 */                 if (in.read() == 32) {
/*     */ 
/*     */                   
/* 124 */                   StringBuffer buff = new StringBuffer();
/* 125 */                   for (; i < 80; i++) {
/* 126 */                     int c = in.read();
/* 127 */                     if (c != 62) {
/* 128 */                       buff.append(c);
/*     */                     } else {
/*     */                       
/* 131 */                       in.reset();
/* 132 */                       AttributesImpl attsi = new AttributesImpl();
/*     */                       
/* 134 */                       try { a(buff.toString(), attsi);
/* 135 */                         String encoding = attsi.getValue("encoding");
/* 136 */                         return (encoding == null) ? "UTF-8" : encoding; }
/* 137 */                       catch (ParseException e)
/* 138 */                       { return null; } 
/*     */                     } 
/*     */                   } 
/*     */                 }  }  }  }  }  } 
/* 142 */     }  in.reset();
/* 143 */     return null;
/*     */   }
/*     */   
/*     */   public static InputSource b(b source) throws IOException {
/*     */     InputSource inputSource;
/* 148 */     if (source.i()) {
/* 149 */       inputSource = new InputSource(new BufferedReader(source.j()));
/*     */     } else {
/*     */       
/* 152 */       InputStream in = new BufferedInputStream(source.h());
/* 153 */       String encoding = a(in);
/*     */       
/* 155 */       if (encoding != null) {
/* 156 */         Reader r = new InputStreamReader(in, encoding);
/* 157 */         inputSource = new InputSource(r);
/*     */       } else {
/* 159 */         inputSource = new InputSource(in);
/*     */       } 
/*     */     } 
/* 162 */     inputSource.setSystemId(source.d().toString());
/* 163 */     return inputSource;
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
/*     */   public static String a(char[] ch, int off, int len, AttributesImpl atts) throws ParseException {
/* 176 */     StringBuffer data = null, name = null, value = null, escape = null;
/* 177 */     short state = 0;
/* 178 */     char delim = '"';
/* 179 */     for (int i = 0; i < len; i++) {
/* 180 */       char c = ch[i + off];
/* 181 */       switch (state) {
/*     */         case 0:
/* 183 */           if (c == '[') {
/* 184 */             data = new StringBuffer();
/* 185 */             state = 9; break;
/* 186 */           }  if (!Character.isWhitespace(c) && c != ',') {
/* 187 */             name = new StringBuffer();
/* 188 */             name.append(c);
/* 189 */             state = 1;
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 1:
/* 194 */           if (c == '=') {
/* 195 */             value = new StringBuffer();
/* 196 */             state = 2; break;
/* 197 */           }  if (!Character.isWhitespace(c)) {
/* 198 */             name.append(c);
/*     */           }
/*     */           break;
/*     */         
/*     */         case 2:
/* 203 */           if (c == '"' || c == '\'') {
/* 204 */             delim = c;
/* 205 */             state = 3; break;
/* 206 */           }  if (!Character.isWhitespace(c)) {
/* 207 */             value.append(c);
/* 208 */             delim = Character.MIN_VALUE;
/* 209 */             state = 3;
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 3:
/* 214 */           if (c == delim || (delim == '\000' && (Character.isWhitespace(c) || c == ','))) {
/* 215 */             String nameStr = name.toString();
/* 216 */             atts.addAttribute("", nameStr, nameStr, "CDATA", value.toString());
/* 217 */             name = value = null;
/* 218 */             state = 0; break;
/* 219 */           }  if (c == '&') {
/* 220 */             escape = new StringBuffer();
/* 221 */             state = 4; break;
/*     */           } 
/* 223 */           value.append(c);
/* 224 */           if (i == len - 1) {
/* 225 */             String nameStr = name.toString();
/* 226 */             atts.addAttribute("", nameStr, nameStr, "CDATA", value.toString());
/*     */           } 
/*     */           break;
/*     */ 
/*     */         
/*     */         case 4:
/* 232 */           if (c == '#') {
/* 233 */             state = 6; break;
/*     */           } 
/* 235 */           escape.append(c);
/* 236 */           state = 5;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 5:
/* 241 */           if (c == ';') {
/* 242 */             String escapeStr = escape.toString();
/* 243 */             escape = null;
/* 244 */             if (escapeStr.equals("amp")) {
/* 245 */               value.append('&');
/* 246 */             } else if (escapeStr.equals("lt")) {
/* 247 */               value.append('<');
/* 248 */             } else if (escapeStr.equals("gt")) {
/* 249 */               value.append('>');
/* 250 */             } else if (escapeStr.equals("quot")) {
/* 251 */               value.append('"');
/* 252 */             } else if (escapeStr.equals("apos")) {
/* 253 */               value.append('\'');
/*     */             } else {
/* 255 */               throw new ParseException("不正なエンティティです:" + escapeStr, i);
/*     */             } 
/* 257 */             state = 3; break;
/*     */           } 
/* 259 */           escape.append(c);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 6:
/* 264 */           if (c == 'x' || c == 'X') {
/* 265 */             state = 8; break;
/*     */           } 
/* 267 */           escape.append(c);
/* 268 */           state = 7;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 7:
/* 273 */           if (c == ';') {
/* 274 */             String escapeStr = escape.toString();
/* 275 */             escape = null;
/*     */             try {
/* 277 */               int unicode = Integer.parseInt(escapeStr);
/* 278 */               value.append((char)unicode);
/* 279 */             } catch (NumberFormatException e) {
/* 280 */               throw new ParseException("不正なエンティティです:" + escapeStr, i);
/*     */             } 
/* 282 */             state = 3; break;
/*     */           } 
/* 284 */           escape.append(c);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 8:
/* 289 */           if (c == ';') {
/* 290 */             String escapeStr = escape.toString();
/* 291 */             escape = null;
/*     */             try {
/* 293 */               int unicode = Integer.parseInt(escapeStr, 16);
/* 294 */               value.append((char)unicode);
/* 295 */             } catch (NumberFormatException e) {
/* 296 */               throw new ParseException("不正なエンティテです:" + escapeStr, i);
/*     */             } 
/* 298 */             state = 3; break;
/*     */           } 
/* 300 */           escape.append(c);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 9:
/* 305 */           if (c == ']') {
/* 306 */             if (++i >= len) {
/*     */               break;
/*     */             }
/* 309 */             if (ch[i + off] != ']') {
/* 310 */               i--;
/*     */             }
/* 312 */             data.append(c); break;
/*     */           } 
/* 314 */           data.append(c);
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 319 */     return (data == null) ? "" : data.toString();
/*     */   }
/*     */   
/*     */   public static String a(String str, AttributesImpl atts) throws ParseException {
/* 323 */     char[] ch = str.toCharArray();
/* 324 */     return a(ch, 0, ch.length, atts);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String a(String val) {
/* 334 */     StringBuffer result = new StringBuffer(val.length());
/* 335 */     for (int i = 0; i < val.length(); i++) {
/* 336 */       char c = val.charAt(i);
/* 337 */       switch (c) {
/*     */         case '&':
/* 339 */           result.append("&amp;");
/*     */           break;
/*     */         case '"':
/* 342 */           result.append("&quot;");
/*     */           break;
/*     */         case '\'':
/* 345 */           result.append("&apos;");
/*     */           break;
/*     */         default:
/* 348 */           result.append(c);
/*     */           break;
/*     */       } 
/*     */     } 
/* 352 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String b(String val) {
/* 362 */     StringBuffer result = new StringBuffer(val.length());
/* 363 */     for (int i = 0; i < val.length(); i++) {
/* 364 */       char c = val.charAt(i);
/* 365 */       switch (c) {
/*     */         case ']':
/* 367 */           result.append("]]");
/*     */           break;
/*     */         default:
/* 370 */           result.append(c);
/*     */           break;
/*     */       } 
/*     */     } 
/* 374 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/b/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */