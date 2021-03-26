/*     */ package org.apache.http.impl.client;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.ProtocolException;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.client.CircularRedirectException;
/*     */ import org.apache.http.client.RedirectStrategy;
/*     */ import org.apache.http.client.config.RequestConfig;
/*     */ import org.apache.http.client.methods.HttpGet;
/*     */ import org.apache.http.client.methods.HttpHead;
/*     */ import org.apache.http.client.methods.HttpUriRequest;
/*     */ import org.apache.http.client.methods.RequestBuilder;
/*     */ import org.apache.http.client.protocol.HttpClientContext;
/*     */ import org.apache.http.client.utils.URIUtils;
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ import org.apache.http.util.Args;
/*     */ import org.apache.http.util.Asserts;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ public class DefaultRedirectStrategy
/*     */   implements RedirectStrategy
/*     */ {
/*  74 */   private final Log log = LogFactory.getLog(getClass());
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";
/*     */ 
/*     */   
/*  82 */   public static final DefaultRedirectStrategy INSTANCE = new DefaultRedirectStrategy();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static final String[] REDIRECT_METHODS = new String[] { "GET", "HEAD" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
/* 101 */     Args.notNull(request, "HTTP request");
/* 102 */     Args.notNull(response, "HTTP response");
/*     */     
/* 104 */     int statusCode = response.getStatusLine().getStatusCode();
/* 105 */     String method = request.getRequestLine().getMethod();
/* 106 */     Header locationHeader = response.getFirstHeader("location");
/* 107 */     switch (statusCode) {
/*     */       case 302:
/* 109 */         return (isRedirectable(method) && locationHeader != null);
/*     */       case 301:
/*     */       case 307:
/* 112 */         return isRedirectable(method);
/*     */       case 303:
/* 114 */         return true;
/*     */     } 
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URI getLocationURI(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
/* 124 */     Args.notNull(request, "HTTP request");
/* 125 */     Args.notNull(response, "HTTP response");
/* 126 */     Args.notNull(context, "HTTP context");
/*     */     
/* 128 */     HttpClientContext clientContext = HttpClientContext.adapt(context);
/*     */ 
/*     */     
/* 131 */     Header locationHeader = response.getFirstHeader("location");
/* 132 */     if (locationHeader == null)
/*     */     {
/* 134 */       throw new ProtocolException("Received redirect response " + response.getStatusLine() + " but no location header");
/*     */     }
/*     */ 
/*     */     
/* 138 */     String location = locationHeader.getValue();
/* 139 */     if (this.log.isDebugEnabled()) {
/* 140 */       this.log.debug("Redirect requested to location '" + location + "'");
/*     */     }
/*     */     
/* 143 */     RequestConfig config = clientContext.getRequestConfig();
/*     */     
/* 145 */     URI uri = createLocationURI(location);
/*     */     
/*     */     try {
/* 148 */       if (config.isNormalizeUri()) {
/* 149 */         uri = URIUtils.normalizeSyntax(uri);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 154 */       if (!uri.isAbsolute()) {
/* 155 */         if (!config.isRelativeRedirectsAllowed()) {
/* 156 */           throw new ProtocolException("Relative redirect location '" + uri + "' not allowed");
/*     */         }
/*     */ 
/*     */         
/* 160 */         HttpHost target = clientContext.getTargetHost();
/* 161 */         Asserts.notNull(target, "Target host");
/* 162 */         URI requestURI = new URI(request.getRequestLine().getUri());
/* 163 */         URI absoluteRequestURI = URIUtils.rewriteURI(requestURI, target, config.isNormalizeUri() ? URIUtils.NORMALIZE : URIUtils.NO_FLAGS);
/*     */         
/* 165 */         uri = URIUtils.resolve(absoluteRequestURI, uri);
/*     */       } 
/* 167 */     } catch (URISyntaxException ex) {
/* 168 */       throw new ProtocolException(ex.getMessage(), ex);
/*     */     } 
/*     */     
/* 171 */     RedirectLocations redirectLocations = (RedirectLocations)clientContext.getAttribute("http.protocol.redirect-locations");
/*     */     
/* 173 */     if (redirectLocations == null) {
/* 174 */       redirectLocations = new RedirectLocations();
/* 175 */       context.setAttribute("http.protocol.redirect-locations", redirectLocations);
/*     */     } 
/* 177 */     if (!config.isCircularRedirectsAllowed() && 
/* 178 */       redirectLocations.contains(uri)) {
/* 179 */       throw new CircularRedirectException("Circular redirect to '" + uri + "'");
/*     */     }
/*     */     
/* 182 */     redirectLocations.add(uri);
/* 183 */     return uri;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected URI createLocationURI(String location) throws ProtocolException {
/*     */     try {
/* 191 */       return new URI(location);
/* 192 */     } catch (URISyntaxException ex) {
/* 193 */       throw new ProtocolException("Invalid redirect URI: " + location, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isRedirectable(String method) {
/* 201 */     for (String m : REDIRECT_METHODS) {
/* 202 */       if (m.equalsIgnoreCase(method)) {
/* 203 */         return true;
/*     */       }
/*     */     } 
/* 206 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HttpUriRequest getRedirect(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
/* 214 */     URI uri = getLocationURI(request, response, context);
/* 215 */     String method = request.getRequestLine().getMethod();
/* 216 */     if (method.equalsIgnoreCase("HEAD"))
/* 217 */       return (HttpUriRequest)new HttpHead(uri); 
/* 218 */     if (method.equalsIgnoreCase("GET")) {
/* 219 */       return (HttpUriRequest)new HttpGet(uri);
/*     */     }
/* 221 */     int status = response.getStatusLine().getStatusCode();
/* 222 */     return (status == 307) ? RequestBuilder.copy(request).setUri(uri).build() : (HttpUriRequest)new HttpGet(uri);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/client/DefaultRedirectStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */