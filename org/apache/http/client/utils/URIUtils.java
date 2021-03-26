/*     */ package org.apache.http.client.utils;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Stack;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.conn.routing.RouteInfo;
/*     */ import org.apache.http.util.Args;
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
/*     */ public class URIUtils
/*     */ {
/*     */   public enum UriFlag
/*     */   {
/*  57 */     DROP_FRAGMENT,
/*  58 */     NORMALIZE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public static final EnumSet<UriFlag> NO_FLAGS = EnumSet.noneOf(UriFlag.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public static final EnumSet<UriFlag> DROP_FRAGMENT = EnumSet.of(UriFlag.DROP_FRAGMENT);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public static final EnumSet<UriFlag> NORMALIZE = EnumSet.of(UriFlag.NORMALIZE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public static final EnumSet<UriFlag> DROP_FRAGMENT_AND_NORMALIZE = EnumSet.of(UriFlag.DROP_FRAGMENT, UriFlag.NORMALIZE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   public static URI createURI(String scheme, String host, int port, String path, String query, String fragment) throws URISyntaxException {
/* 125 */     StringBuilder buffer = new StringBuilder();
/* 126 */     if (host != null) {
/* 127 */       if (scheme != null) {
/* 128 */         buffer.append(scheme);
/* 129 */         buffer.append("://");
/*     */       } 
/* 131 */       buffer.append(host);
/* 132 */       if (port > 0) {
/* 133 */         buffer.append(':');
/* 134 */         buffer.append(port);
/*     */       } 
/*     */     } 
/* 137 */     if (path == null || !path.startsWith("/")) {
/* 138 */       buffer.append('/');
/*     */     }
/* 140 */     if (path != null) {
/* 141 */       buffer.append(path);
/*     */     }
/* 143 */     if (query != null) {
/* 144 */       buffer.append('?');
/* 145 */       buffer.append(query);
/*     */     } 
/* 147 */     if (fragment != null) {
/* 148 */       buffer.append('#');
/* 149 */       buffer.append(fragment);
/*     */     } 
/* 151 */     return new URI(buffer.toString());
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
/*     */   @Deprecated
/*     */   public static URI rewriteURI(URI uri, HttpHost target, boolean dropFragment) throws URISyntaxException {
/* 177 */     return rewriteURI(uri, target, dropFragment ? DROP_FRAGMENT : NO_FLAGS);
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
/*     */   public static URI rewriteURI(URI uri, HttpHost target, EnumSet<UriFlag> flags) throws URISyntaxException {
/* 201 */     Args.notNull(uri, "URI");
/* 202 */     Args.notNull(flags, "URI flags");
/* 203 */     if (uri.isOpaque()) {
/* 204 */       return uri;
/*     */     }
/* 206 */     URIBuilder uribuilder = new URIBuilder(uri);
/* 207 */     if (target != null) {
/* 208 */       uribuilder.setScheme(target.getSchemeName());
/* 209 */       uribuilder.setHost(target.getHostName());
/* 210 */       uribuilder.setPort(target.getPort());
/*     */     } else {
/* 212 */       uribuilder.setScheme(null);
/* 213 */       uribuilder.setHost(null);
/* 214 */       uribuilder.setPort(-1);
/*     */     } 
/* 216 */     if (flags.contains(UriFlag.DROP_FRAGMENT)) {
/* 217 */       uribuilder.setFragment(null);
/*     */     }
/* 219 */     if (flags.contains(UriFlag.NORMALIZE)) {
/* 220 */       List<String> pathSegments = new ArrayList<String>(uribuilder.getPathSegments());
/* 221 */       for (Iterator<String> it = pathSegments.iterator(); it.hasNext(); ) {
/* 222 */         String pathSegment = it.next();
/* 223 */         if (pathSegment.isEmpty() && it.hasNext()) {
/* 224 */           it.remove();
/*     */         }
/*     */       } 
/* 227 */       uribuilder.setPathSegments(pathSegments);
/*     */     } 
/* 229 */     if (uribuilder.isPathEmpty()) {
/* 230 */       uribuilder.setPathSegments(new String[] { "" });
/*     */     }
/* 232 */     return uribuilder.build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static URI rewriteURI(URI uri, HttpHost target) throws URISyntaxException {
/* 243 */     return rewriteURI(uri, target, NORMALIZE);
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
/*     */   public static URI rewriteURI(URI uri) throws URISyntaxException {
/* 258 */     Args.notNull(uri, "URI");
/* 259 */     if (uri.isOpaque()) {
/* 260 */       return uri;
/*     */     }
/* 262 */     URIBuilder uribuilder = new URIBuilder(uri);
/* 263 */     if (uribuilder.getUserInfo() != null) {
/* 264 */       uribuilder.setUserInfo(null);
/*     */     }
/* 266 */     if (uribuilder.getPathSegments().isEmpty()) {
/* 267 */       uribuilder.setPathSegments(new String[] { "" });
/*     */     }
/* 269 */     if (TextUtils.isEmpty(uribuilder.getPath())) {
/* 270 */       uribuilder.setPath("/");
/*     */     }
/* 272 */     if (uribuilder.getHost() != null) {
/* 273 */       uribuilder.setHost(uribuilder.getHost().toLowerCase(Locale.ROOT));
/*     */     }
/* 275 */     uribuilder.setFragment(null);
/* 276 */     return uribuilder.build();
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
/*     */   public static URI rewriteURIForRoute(URI uri, RouteInfo route) throws URISyntaxException {
/* 291 */     return rewriteURIForRoute(uri, route, true);
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
/*     */   public static URI rewriteURIForRoute(URI uri, RouteInfo route, boolean normalizeUri) throws URISyntaxException {
/* 306 */     if (uri == null) {
/* 307 */       return null;
/*     */     }
/* 309 */     if (route.getProxyHost() != null && !route.isTunnelled())
/*     */     {
/* 311 */       return uri.isAbsolute() ? rewriteURI(uri) : rewriteURI(uri, route.getTargetHost(), normalizeUri ? DROP_FRAGMENT_AND_NORMALIZE : DROP_FRAGMENT);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 316 */     return uri.isAbsolute() ? rewriteURI(uri, (HttpHost)null, normalizeUri ? DROP_FRAGMENT_AND_NORMALIZE : DROP_FRAGMENT) : rewriteURI(uri);
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
/*     */   public static URI resolve(URI baseURI, String reference) {
/* 328 */     return resolve(baseURI, URI.create(reference));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static URI resolve(URI baseURI, URI reference) {
/*     */     URI resolved;
/* 340 */     Args.notNull(baseURI, "Base URI");
/* 341 */     Args.notNull(reference, "Reference URI");
/* 342 */     String s = reference.toASCIIString();
/* 343 */     if (s.startsWith("?")) {
/* 344 */       String baseUri = baseURI.toASCIIString();
/* 345 */       int i = baseUri.indexOf('?');
/* 346 */       baseUri = (i > -1) ? baseUri.substring(0, i) : baseUri;
/* 347 */       return URI.create(baseUri + s);
/*     */     } 
/* 349 */     boolean emptyReference = s.isEmpty();
/*     */     
/* 351 */     if (emptyReference) {
/* 352 */       resolved = baseURI.resolve(URI.create("#"));
/* 353 */       String resolvedString = resolved.toASCIIString();
/* 354 */       resolved = URI.create(resolvedString.substring(0, resolvedString.indexOf('#')));
/*     */     } else {
/* 356 */       resolved = baseURI.resolve(reference);
/*     */     } 
/*     */     try {
/* 359 */       return normalizeSyntax(resolved);
/* 360 */     } catch (URISyntaxException ex) {
/* 361 */       throw new IllegalArgumentException(ex);
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
/*     */   public static URI normalizeSyntax(URI uri) throws URISyntaxException {
/* 375 */     if (uri.isOpaque() || uri.getAuthority() == null)
/*     */     {
/* 377 */       return uri;
/*     */     }
/* 379 */     URIBuilder builder = new URIBuilder(uri);
/* 380 */     List<String> inputSegments = builder.getPathSegments();
/* 381 */     Stack<String> outputSegments = new Stack<String>();
/* 382 */     for (String inputSegment : inputSegments) {
/* 383 */       if (".".equals(inputSegment))
/*     */         continue; 
/* 385 */       if ("..".equals(inputSegment)) {
/* 386 */         if (!outputSegments.isEmpty())
/* 387 */           outputSegments.pop(); 
/*     */         continue;
/*     */       } 
/* 390 */       outputSegments.push(inputSegment);
/*     */     } 
/*     */     
/* 393 */     if (outputSegments.size() == 0) {
/* 394 */       outputSegments.add("");
/*     */     }
/* 396 */     builder.setPathSegments(outputSegments);
/* 397 */     if (builder.getScheme() != null) {
/* 398 */       builder.setScheme(builder.getScheme().toLowerCase(Locale.ROOT));
/*     */     }
/* 400 */     if (builder.getHost() != null) {
/* 401 */       builder.setHost(builder.getHost().toLowerCase(Locale.ROOT));
/*     */     }
/* 403 */     return builder.build();
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
/*     */   public static HttpHost extractHost(URI uri) {
/* 416 */     if (uri == null) {
/* 417 */       return null;
/*     */     }
/* 419 */     HttpHost target = null;
/* 420 */     if (uri.isAbsolute()) {
/* 421 */       int port = uri.getPort();
/* 422 */       String host = uri.getHost();
/* 423 */       if (host == null) {
/*     */         
/* 425 */         host = uri.getAuthority();
/* 426 */         if (host != null) {
/*     */           
/* 428 */           int at = host.indexOf('@');
/* 429 */           if (at >= 0) {
/* 430 */             if (host.length() > at + 1) {
/* 431 */               host = host.substring(at + 1);
/*     */             } else {
/* 433 */               host = null;
/*     */             } 
/*     */           }
/*     */           
/* 437 */           if (host != null) {
/* 438 */             int colon = host.indexOf(':');
/* 439 */             if (colon >= 0) {
/* 440 */               int pos = colon + 1;
/* 441 */               int len = 0;
/* 442 */               for (int i = pos; i < host.length() && 
/* 443 */                 Character.isDigit(host.charAt(i)); i++) {
/* 444 */                 len++;
/*     */               }
/*     */ 
/*     */ 
/*     */               
/* 449 */               if (len > 0) {
/*     */                 try {
/* 451 */                   port = Integer.parseInt(host.substring(pos, pos + len));
/* 452 */                 } catch (NumberFormatException numberFormatException) {}
/*     */               }
/*     */               
/* 455 */               host = host.substring(0, colon);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 460 */       String scheme = uri.getScheme();
/* 461 */       if (!TextUtils.isBlank(host)) {
/*     */         try {
/* 463 */           target = new HttpHost(host, port, scheme);
/* 464 */         } catch (IllegalArgumentException illegalArgumentException) {}
/*     */       }
/*     */     } 
/*     */     
/* 468 */     return target;
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
/*     */   public static URI resolve(URI originalURI, HttpHost target, List<URI> redirects) throws URISyntaxException {
/*     */     URIBuilder uribuilder;
/* 491 */     Args.notNull(originalURI, "Request URI");
/*     */     
/* 493 */     if (redirects == null || redirects.isEmpty()) {
/* 494 */       uribuilder = new URIBuilder(originalURI);
/*     */     } else {
/* 496 */       uribuilder = new URIBuilder(redirects.get(redirects.size() - 1));
/* 497 */       String frag = uribuilder.getFragment();
/*     */       
/* 499 */       for (int i = redirects.size() - 1; frag == null && i >= 0; i--) {
/* 500 */         frag = ((URI)redirects.get(i)).getFragment();
/*     */       }
/* 502 */       uribuilder.setFragment(frag);
/*     */     } 
/*     */     
/* 505 */     if (uribuilder.getFragment() == null) {
/* 506 */       uribuilder.setFragment(originalURI.getFragment());
/*     */     }
/*     */     
/* 509 */     if (target != null && !uribuilder.isAbsolute()) {
/* 510 */       uribuilder.setScheme(target.getSchemeName());
/* 511 */       uribuilder.setHost(target.getHostName());
/* 512 */       uribuilder.setPort(target.getPort());
/*     */     } 
/* 514 */     return uribuilder.build();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/client/utils/URIUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */