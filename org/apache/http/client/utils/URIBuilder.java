/*     */ package org.apache.http.client.utils;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.http.Consts;
/*     */ import org.apache.http.NameValuePair;
/*     */ import org.apache.http.conn.util.InetAddressUtils;
/*     */ import org.apache.http.message.BasicNameValuePair;
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
/*     */ public class URIBuilder
/*     */ {
/*     */   private String scheme;
/*     */   private String encodedSchemeSpecificPart;
/*     */   private String encodedAuthority;
/*     */   private String userInfo;
/*     */   private String encodedUserInfo;
/*     */   private String host;
/*     */   private int port;
/*     */   private String encodedPath;
/*     */   private List<String> pathSegments;
/*     */   private String encodedQuery;
/*     */   private List<NameValuePair> queryParams;
/*     */   private String query;
/*     */   private Charset charset;
/*     */   private String fragment;
/*     */   private String encodedFragment;
/*     */   
/*     */   public URIBuilder() {
/*  72 */     this.port = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIBuilder(String string) throws URISyntaxException {
/*  83 */     digestURI(new URI(string));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIBuilder(URI uri) {
/*  92 */     digestURI(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIBuilder setCharset(Charset charset) {
/*  99 */     this.charset = charset;
/* 100 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Charset getCharset() {
/* 107 */     return this.charset;
/*     */   }
/*     */   
/*     */   private List<NameValuePair> parseQuery(String query, Charset charset) {
/* 111 */     if (query != null && !query.isEmpty()) {
/* 112 */       return URLEncodedUtils.parse(query, charset);
/*     */     }
/* 114 */     return null;
/*     */   }
/*     */   
/*     */   private List<String> parsePath(String path, Charset charset) {
/* 118 */     if (path != null && !path.isEmpty()) {
/* 119 */       return URLEncodedUtils.parsePathSegments(path, charset);
/*     */     }
/* 121 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URI build() throws URISyntaxException {
/* 128 */     return new URI(buildString());
/*     */   }
/*     */   
/*     */   private String buildString() {
/* 132 */     StringBuilder sb = new StringBuilder();
/* 133 */     if (this.scheme != null) {
/* 134 */       sb.append(this.scheme).append(':');
/*     */     }
/* 136 */     if (this.encodedSchemeSpecificPart != null) {
/* 137 */       sb.append(this.encodedSchemeSpecificPart);
/*     */     } else {
/* 139 */       if (this.encodedAuthority != null) {
/* 140 */         sb.append("//").append(this.encodedAuthority);
/* 141 */       } else if (this.host != null) {
/* 142 */         sb.append("//");
/* 143 */         if (this.encodedUserInfo != null) {
/* 144 */           sb.append(this.encodedUserInfo).append("@");
/* 145 */         } else if (this.userInfo != null) {
/* 146 */           sb.append(encodeUserInfo(this.userInfo)).append("@");
/*     */         } 
/* 148 */         if (InetAddressUtils.isIPv6Address(this.host)) {
/* 149 */           sb.append("[").append(this.host).append("]");
/*     */         } else {
/* 151 */           sb.append(this.host);
/*     */         } 
/* 153 */         if (this.port >= 0) {
/* 154 */           sb.append(":").append(this.port);
/*     */         }
/*     */       } 
/* 157 */       if (this.encodedPath != null) {
/* 158 */         sb.append(normalizePath(this.encodedPath, (sb.length() == 0)));
/* 159 */       } else if (this.pathSegments != null) {
/* 160 */         sb.append(encodePath(this.pathSegments));
/*     */       } 
/* 162 */       if (this.encodedQuery != null) {
/* 163 */         sb.append("?").append(this.encodedQuery);
/* 164 */       } else if (this.queryParams != null && !this.queryParams.isEmpty()) {
/* 165 */         sb.append("?").append(encodeUrlForm(this.queryParams));
/* 166 */       } else if (this.query != null) {
/* 167 */         sb.append("?").append(encodeUric(this.query));
/*     */       } 
/*     */     } 
/* 170 */     if (this.encodedFragment != null) {
/* 171 */       sb.append("#").append(this.encodedFragment);
/* 172 */     } else if (this.fragment != null) {
/* 173 */       sb.append("#").append(encodeUric(this.fragment));
/*     */     } 
/* 175 */     return sb.toString();
/*     */   }
/*     */   
/*     */   private static String normalizePath(String path, boolean relative) {
/* 179 */     String s = path;
/* 180 */     if (TextUtils.isBlank(s)) {
/* 181 */       return "";
/*     */     }
/* 183 */     if (!relative && !s.startsWith("/")) {
/* 184 */       s = "/" + s;
/*     */     }
/* 186 */     return s;
/*     */   }
/*     */   
/*     */   private void digestURI(URI uri) {
/* 190 */     this.scheme = uri.getScheme();
/* 191 */     this.encodedSchemeSpecificPart = uri.getRawSchemeSpecificPart();
/* 192 */     this.encodedAuthority = uri.getRawAuthority();
/* 193 */     this.host = uri.getHost();
/* 194 */     this.port = uri.getPort();
/* 195 */     this.encodedUserInfo = uri.getRawUserInfo();
/* 196 */     this.userInfo = uri.getUserInfo();
/* 197 */     this.encodedPath = uri.getRawPath();
/* 198 */     this.pathSegments = parsePath(uri.getRawPath(), (this.charset != null) ? this.charset : Consts.UTF_8);
/* 199 */     this.encodedQuery = uri.getRawQuery();
/* 200 */     this.queryParams = parseQuery(uri.getRawQuery(), (this.charset != null) ? this.charset : Consts.UTF_8);
/* 201 */     this.encodedFragment = uri.getRawFragment();
/* 202 */     this.fragment = uri.getFragment();
/*     */   }
/*     */   
/*     */   private String encodeUserInfo(String userInfo) {
/* 206 */     return URLEncodedUtils.encUserInfo(userInfo, (this.charset != null) ? this.charset : Consts.UTF_8);
/*     */   }
/*     */   
/*     */   private String encodePath(List<String> pathSegments) {
/* 210 */     return URLEncodedUtils.formatSegments(pathSegments, (this.charset != null) ? this.charset : Consts.UTF_8);
/*     */   }
/*     */   
/*     */   private String encodeUrlForm(List<NameValuePair> params) {
/* 214 */     return URLEncodedUtils.format(params, (this.charset != null) ? this.charset : Consts.UTF_8);
/*     */   }
/*     */   
/*     */   private String encodeUric(String fragment) {
/* 218 */     return URLEncodedUtils.encUric(fragment, (this.charset != null) ? this.charset : Consts.UTF_8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIBuilder setScheme(String scheme) {
/* 225 */     this.scheme = scheme;
/* 226 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIBuilder setUserInfo(String userInfo) {
/* 234 */     this.userInfo = userInfo;
/* 235 */     this.encodedSchemeSpecificPart = null;
/* 236 */     this.encodedAuthority = null;
/* 237 */     this.encodedUserInfo = null;
/* 238 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIBuilder setUserInfo(String username, String password) {
/* 246 */     return setUserInfo(username + ':' + password);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIBuilder setHost(String host) {
/* 253 */     this.host = host;
/* 254 */     this.encodedSchemeSpecificPart = null;
/* 255 */     this.encodedAuthority = null;
/* 256 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIBuilder setPort(int port) {
/* 263 */     this.port = (port < 0) ? -1 : port;
/* 264 */     this.encodedSchemeSpecificPart = null;
/* 265 */     this.encodedAuthority = null;
/* 266 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIBuilder setPath(String path) {
/* 275 */     return setPathSegments((path != null) ? URLEncodedUtils.splitPathSegments(path) : null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIBuilder setPathSegments(String... pathSegments) {
/* 286 */     this.pathSegments = (pathSegments.length > 0) ? Arrays.<String>asList(pathSegments) : null;
/* 287 */     this.encodedSchemeSpecificPart = null;
/* 288 */     this.encodedPath = null;
/* 289 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIBuilder setPathSegments(List<String> pathSegments) {
/* 300 */     this.pathSegments = (pathSegments != null && pathSegments.size() > 0) ? new ArrayList<String>(pathSegments) : null;
/* 301 */     this.encodedSchemeSpecificPart = null;
/* 302 */     this.encodedPath = null;
/* 303 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIBuilder removeQuery() {
/* 310 */     this.queryParams = null;
/* 311 */     this.query = null;
/* 312 */     this.encodedQuery = null;
/* 313 */     this.encodedSchemeSpecificPart = null;
/* 314 */     return this;
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
/*     */   @Deprecated
/*     */   public URIBuilder setQuery(String query) {
/* 328 */     this.queryParams = parseQuery(query, (this.charset != null) ? this.charset : Consts.UTF_8);
/* 329 */     this.query = null;
/* 330 */     this.encodedQuery = null;
/* 331 */     this.encodedSchemeSpecificPart = null;
/* 332 */     return this;
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
/*     */   public URIBuilder setParameters(List<NameValuePair> nvps) {
/* 346 */     if (this.queryParams == null) {
/* 347 */       this.queryParams = new ArrayList<NameValuePair>();
/*     */     } else {
/* 349 */       this.queryParams.clear();
/*     */     } 
/* 351 */     this.queryParams.addAll(nvps);
/* 352 */     this.encodedQuery = null;
/* 353 */     this.encodedSchemeSpecificPart = null;
/* 354 */     this.query = null;
/* 355 */     return this;
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
/*     */   public URIBuilder addParameters(List<NameValuePair> nvps) {
/* 369 */     if (this.queryParams == null) {
/* 370 */       this.queryParams = new ArrayList<NameValuePair>();
/*     */     }
/* 372 */     this.queryParams.addAll(nvps);
/* 373 */     this.encodedQuery = null;
/* 374 */     this.encodedSchemeSpecificPart = null;
/* 375 */     this.query = null;
/* 376 */     return this;
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
/*     */   public URIBuilder setParameters(NameValuePair... nvps) {
/* 390 */     if (this.queryParams == null) {
/* 391 */       this.queryParams = new ArrayList<NameValuePair>();
/*     */     } else {
/* 393 */       this.queryParams.clear();
/*     */     } 
/* 395 */     for (NameValuePair nvp : nvps) {
/* 396 */       this.queryParams.add(nvp);
/*     */     }
/* 398 */     this.encodedQuery = null;
/* 399 */     this.encodedSchemeSpecificPart = null;
/* 400 */     this.query = null;
/* 401 */     return this;
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
/*     */   public URIBuilder addParameter(String param, String value) {
/* 413 */     if (this.queryParams == null) {
/* 414 */       this.queryParams = new ArrayList<NameValuePair>();
/*     */     }
/* 416 */     this.queryParams.add(new BasicNameValuePair(param, value));
/* 417 */     this.encodedQuery = null;
/* 418 */     this.encodedSchemeSpecificPart = null;
/* 419 */     this.query = null;
/* 420 */     return this;
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
/*     */   public URIBuilder setParameter(String param, String value) {
/* 432 */     if (this.queryParams == null) {
/* 433 */       this.queryParams = new ArrayList<NameValuePair>();
/*     */     }
/* 435 */     if (!this.queryParams.isEmpty()) {
/* 436 */       for (Iterator<NameValuePair> it = this.queryParams.iterator(); it.hasNext(); ) {
/* 437 */         NameValuePair nvp = it.next();
/* 438 */         if (nvp.getName().equals(param)) {
/* 439 */           it.remove();
/*     */         }
/*     */       } 
/*     */     }
/* 443 */     this.queryParams.add(new BasicNameValuePair(param, value));
/* 444 */     this.encodedQuery = null;
/* 445 */     this.encodedSchemeSpecificPart = null;
/* 446 */     this.query = null;
/* 447 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIBuilder clearParameters() {
/* 456 */     this.queryParams = null;
/* 457 */     this.encodedQuery = null;
/* 458 */     this.encodedSchemeSpecificPart = null;
/* 459 */     return this;
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
/*     */   public URIBuilder setCustomQuery(String query) {
/* 473 */     this.query = query;
/* 474 */     this.encodedQuery = null;
/* 475 */     this.encodedSchemeSpecificPart = null;
/* 476 */     this.queryParams = null;
/* 477 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIBuilder setFragment(String fragment) {
/* 485 */     this.fragment = fragment;
/* 486 */     this.encodedFragment = null;
/* 487 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAbsolute() {
/* 494 */     return (this.scheme != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOpaque() {
/* 501 */     return isPathEmpty();
/*     */   }
/*     */   
/*     */   public String getScheme() {
/* 505 */     return this.scheme;
/*     */   }
/*     */   
/*     */   public String getUserInfo() {
/* 509 */     return this.userInfo;
/*     */   }
/*     */   
/*     */   public String getHost() {
/* 513 */     return this.host;
/*     */   }
/*     */   
/*     */   public int getPort() {
/* 517 */     return this.port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPathEmpty() {
/* 524 */     return ((this.pathSegments == null || this.pathSegments.isEmpty()) && this.encodedPath == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getPathSegments() {
/* 531 */     return (this.pathSegments != null) ? new ArrayList<String>(this.pathSegments) : Collections.<String>emptyList();
/*     */   }
/*     */   
/*     */   public String getPath() {
/* 535 */     if (this.pathSegments == null) {
/* 536 */       return null;
/*     */     }
/* 538 */     StringBuilder result = new StringBuilder();
/* 539 */     for (String segment : this.pathSegments) {
/* 540 */       result.append('/').append(segment);
/*     */     }
/* 542 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isQueryEmpty() {
/* 549 */     return ((this.queryParams == null || this.queryParams.isEmpty()) && this.encodedQuery == null);
/*     */   }
/*     */   
/*     */   public List<NameValuePair> getQueryParams() {
/* 553 */     return (this.queryParams != null) ? new ArrayList<NameValuePair>(this.queryParams) : Collections.<NameValuePair>emptyList();
/*     */   }
/*     */   
/*     */   public String getFragment() {
/* 557 */     return this.fragment;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 562 */     return buildString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/client/utils/URIBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */