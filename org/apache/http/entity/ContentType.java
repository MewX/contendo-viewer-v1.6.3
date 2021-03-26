/*     */ package org.apache.http.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.UnsupportedCharsetException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.apache.http.Consts;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.HeaderElement;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.NameValuePair;
/*     */ import org.apache.http.ParseException;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.message.BasicHeaderValueFormatter;
/*     */ import org.apache.http.message.BasicHeaderValueParser;
/*     */ import org.apache.http.message.BasicNameValuePair;
/*     */ import org.apache.http.message.ParserCursor;
/*     */ import org.apache.http.util.Args;
/*     */ import org.apache.http.util.CharArrayBuffer;
/*     */ import org.apache.http.util.TextUtils;
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
/*     */ @Contract(threading = ThreadingBehavior.IMMUTABLE)
/*     */ public final class ContentType
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7768694718232371896L;
/*  72 */   public static final ContentType APPLICATION_ATOM_XML = create("application/atom+xml", Consts.ISO_8859_1);
/*     */   
/*  74 */   public static final ContentType APPLICATION_FORM_URLENCODED = create("application/x-www-form-urlencoded", Consts.ISO_8859_1);
/*     */   
/*  76 */   public static final ContentType APPLICATION_JSON = create("application/json", Consts.UTF_8);
/*     */   
/*  78 */   public static final ContentType APPLICATION_OCTET_STREAM = create("application/octet-stream", (Charset)null);
/*     */   
/*  80 */   public static final ContentType APPLICATION_SVG_XML = create("application/svg+xml", Consts.ISO_8859_1);
/*     */   
/*  82 */   public static final ContentType APPLICATION_XHTML_XML = create("application/xhtml+xml", Consts.ISO_8859_1);
/*     */   
/*  84 */   public static final ContentType APPLICATION_XML = create("application/xml", Consts.ISO_8859_1);
/*     */   
/*  86 */   public static final ContentType IMAGE_BMP = create("image/bmp");
/*     */   
/*  88 */   public static final ContentType IMAGE_GIF = create("image/gif");
/*     */   
/*  90 */   public static final ContentType IMAGE_JPEG = create("image/jpeg");
/*     */   
/*  92 */   public static final ContentType IMAGE_PNG = create("image/png");
/*     */   
/*  94 */   public static final ContentType IMAGE_SVG = create("image/svg+xml");
/*     */   
/*  96 */   public static final ContentType IMAGE_TIFF = create("image/tiff");
/*     */   
/*  98 */   public static final ContentType IMAGE_WEBP = create("image/webp");
/*     */   
/* 100 */   public static final ContentType MULTIPART_FORM_DATA = create("multipart/form-data", Consts.ISO_8859_1);
/*     */   
/* 102 */   public static final ContentType TEXT_HTML = create("text/html", Consts.ISO_8859_1);
/*     */   
/* 104 */   public static final ContentType TEXT_PLAIN = create("text/plain", Consts.ISO_8859_1);
/*     */   
/* 106 */   public static final ContentType TEXT_XML = create("text/xml", Consts.ISO_8859_1);
/*     */   
/* 108 */   public static final ContentType WILDCARD = create("*/*", (Charset)null);
/*     */ 
/*     */   
/*     */   private static final Map<String, ContentType> CONTENT_TYPE_MAP;
/*     */ 
/*     */   
/*     */   static {
/* 115 */     ContentType[] contentTypes = { APPLICATION_ATOM_XML, APPLICATION_FORM_URLENCODED, APPLICATION_JSON, APPLICATION_SVG_XML, APPLICATION_XHTML_XML, APPLICATION_XML, IMAGE_BMP, IMAGE_GIF, IMAGE_JPEG, IMAGE_PNG, IMAGE_SVG, IMAGE_TIFF, IMAGE_WEBP, MULTIPART_FORM_DATA, TEXT_HTML, TEXT_PLAIN, TEXT_XML };
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
/* 133 */     HashMap<String, ContentType> map = new HashMap<String, ContentType>();
/* 134 */     for (ContentType contentType : contentTypes) {
/* 135 */       map.put(contentType.getMimeType(), contentType);
/*     */     }
/* 137 */     CONTENT_TYPE_MAP = Collections.unmodifiableMap(map);
/*     */   }
/*     */ 
/*     */   
/* 141 */   public static final ContentType DEFAULT_TEXT = TEXT_PLAIN;
/* 142 */   public static final ContentType DEFAULT_BINARY = APPLICATION_OCTET_STREAM;
/*     */   
/*     */   private final String mimeType;
/*     */   
/*     */   private final Charset charset;
/*     */   
/*     */   private final NameValuePair[] params;
/*     */   
/*     */   ContentType(String mimeType, Charset charset) {
/* 151 */     this.mimeType = mimeType;
/* 152 */     this.charset = charset;
/* 153 */     this.params = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ContentType(String mimeType, Charset charset, NameValuePair[] params) {
/* 160 */     this.mimeType = mimeType;
/* 161 */     this.charset = charset;
/* 162 */     this.params = params;
/*     */   }
/*     */   
/*     */   public String getMimeType() {
/* 166 */     return this.mimeType;
/*     */   }
/*     */   
/*     */   public Charset getCharset() {
/* 170 */     return this.charset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getParameter(String name) {
/* 177 */     Args.notEmpty(name, "Parameter name");
/* 178 */     if (this.params == null) {
/* 179 */       return null;
/*     */     }
/* 181 */     for (NameValuePair param : this.params) {
/* 182 */       if (param.getName().equalsIgnoreCase(name)) {
/* 183 */         return param.getValue();
/*     */       }
/*     */     } 
/* 186 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 195 */     CharArrayBuffer buf = new CharArrayBuffer(64);
/* 196 */     buf.append(this.mimeType);
/* 197 */     if (this.params != null) {
/* 198 */       buf.append("; ");
/* 199 */       BasicHeaderValueFormatter.INSTANCE.formatParameters(buf, this.params, false);
/* 200 */     } else if (this.charset != null) {
/* 201 */       buf.append("; charset=");
/* 202 */       buf.append(this.charset.name());
/*     */     } 
/* 204 */     return buf.toString();
/*     */   }
/*     */   
/*     */   private static boolean valid(String s) {
/* 208 */     for (int i = 0; i < s.length(); i++) {
/* 209 */       char ch = s.charAt(i);
/* 210 */       if (ch == '"' || ch == ',' || ch == ';') {
/* 211 */         return false;
/*     */       }
/*     */     } 
/* 214 */     return true;
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
/*     */   public static ContentType create(String mimeType, Charset charset) {
/* 226 */     String normalizedMimeType = ((String)Args.notBlank(mimeType, "MIME type")).toLowerCase(Locale.ROOT);
/* 227 */     Args.check(valid(normalizedMimeType), "MIME type may not contain reserved characters");
/* 228 */     return new ContentType(normalizedMimeType, charset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ContentType create(String mimeType) {
/* 239 */     return create(mimeType, (Charset)null);
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
/*     */   public static ContentType create(String mimeType, String charset) throws UnsupportedCharsetException {
/* 255 */     return create(mimeType, !TextUtils.isBlank(charset) ? Charset.forName(charset) : null);
/*     */   }
/*     */   
/*     */   private static ContentType create(HeaderElement helem, boolean strict) {
/* 259 */     return create(helem.getName(), helem.getParameters(), strict);
/*     */   }
/*     */   
/*     */   private static ContentType create(String mimeType, NameValuePair[] params, boolean strict) {
/* 263 */     Charset charset = null;
/* 264 */     for (NameValuePair param : params) {
/* 265 */       if (param.getName().equalsIgnoreCase("charset")) {
/* 266 */         String s = param.getValue();
/* 267 */         if (!TextUtils.isBlank(s)) {
/*     */           try {
/* 269 */             charset = Charset.forName(s);
/* 270 */           } catch (UnsupportedCharsetException ex) {
/* 271 */             if (strict) {
/* 272 */               throw ex;
/*     */             }
/*     */           } 
/*     */         }
/*     */         break;
/*     */       } 
/*     */     } 
/* 279 */     return new ContentType(mimeType, charset, (params != null && params.length > 0) ? params : null);
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
/*     */   public static ContentType create(String mimeType, NameValuePair... params) throws UnsupportedCharsetException {
/* 294 */     String type = ((String)Args.notBlank(mimeType, "MIME type")).toLowerCase(Locale.ROOT);
/* 295 */     Args.check(valid(type), "MIME type may not contain reserved characters");
/* 296 */     return create(mimeType, params, true);
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
/*     */   public static ContentType parse(String s) throws ParseException, UnsupportedCharsetException {
/* 311 */     Args.notNull(s, "Content type");
/* 312 */     CharArrayBuffer buf = new CharArrayBuffer(s.length());
/* 313 */     buf.append(s);
/* 314 */     ParserCursor cursor = new ParserCursor(0, s.length());
/* 315 */     HeaderElement[] elements = BasicHeaderValueParser.INSTANCE.parseElements(buf, cursor);
/* 316 */     if (elements.length > 0) {
/* 317 */       return create(elements[0], true);
/*     */     }
/* 319 */     throw new ParseException("Invalid content type: " + s);
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
/*     */   public static ContentType get(HttpEntity entity) throws ParseException, UnsupportedCharsetException {
/* 336 */     if (entity == null) {
/* 337 */       return null;
/*     */     }
/* 339 */     Header header = entity.getContentType();
/* 340 */     if (header != null) {
/* 341 */       HeaderElement[] elements = header.getElements();
/* 342 */       if (elements.length > 0) {
/* 343 */         return create(elements[0], true);
/*     */       }
/*     */     } 
/* 346 */     return null;
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
/*     */   public static ContentType getLenient(HttpEntity entity) {
/* 360 */     if (entity == null) {
/* 361 */       return null;
/*     */     }
/* 363 */     Header header = entity.getContentType();
/* 364 */     if (header != null) {
/*     */       try {
/* 366 */         HeaderElement[] elements = header.getElements();
/* 367 */         if (elements.length > 0) {
/* 368 */           return create(elements[0], false);
/*     */         }
/* 370 */       } catch (ParseException ex) {
/* 371 */         return null;
/*     */       } 
/*     */     }
/* 374 */     return null;
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
/*     */   public static ContentType getOrDefault(HttpEntity entity) throws ParseException, UnsupportedCharsetException {
/* 390 */     ContentType contentType = get(entity);
/* 391 */     return (contentType != null) ? contentType : DEFAULT_TEXT;
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
/*     */   public static ContentType getLenientOrDefault(HttpEntity entity) throws ParseException, UnsupportedCharsetException {
/* 405 */     ContentType contentType = get(entity);
/* 406 */     return (contentType != null) ? contentType : DEFAULT_TEXT;
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
/*     */   public static ContentType getByMimeType(String mimeType) {
/* 419 */     if (mimeType == null) {
/* 420 */       return null;
/*     */     }
/* 422 */     return CONTENT_TYPE_MAP.get(mimeType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContentType withCharset(Charset charset) {
/* 433 */     return create(getMimeType(), charset);
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
/*     */   public ContentType withCharset(String charset) {
/* 446 */     return create(getMimeType(), charset);
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
/*     */   public ContentType withParameters(NameValuePair... params) throws UnsupportedCharsetException {
/* 458 */     if (params.length == 0) {
/* 459 */       return this;
/*     */     }
/* 461 */     Map<String, String> paramMap = new LinkedHashMap<String, String>();
/* 462 */     if (this.params != null) {
/* 463 */       for (NameValuePair param : this.params) {
/* 464 */         paramMap.put(param.getName(), param.getValue());
/*     */       }
/*     */     }
/* 467 */     for (NameValuePair param : params) {
/* 468 */       paramMap.put(param.getName(), param.getValue());
/*     */     }
/* 470 */     List<NameValuePair> newParams = new ArrayList<NameValuePair>(paramMap.size() + 1);
/* 471 */     if (this.charset != null && !paramMap.containsKey("charset")) {
/* 472 */       newParams.add(new BasicNameValuePair("charset", this.charset.name()));
/*     */     }
/* 474 */     for (Map.Entry<String, String> entry : paramMap.entrySet()) {
/* 475 */       newParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
/*     */     }
/* 477 */     return create(getMimeType(), newParams.<NameValuePair>toArray(new NameValuePair[newParams.size()]), true);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/ContentType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */