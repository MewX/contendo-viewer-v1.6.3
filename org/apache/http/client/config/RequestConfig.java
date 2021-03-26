/*     */ package org.apache.http.client.config;
/*     */ 
/*     */ import java.net.InetAddress;
/*     */ import java.util.Collection;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ public class RequestConfig
/*     */   implements Cloneable
/*     */ {
/*  45 */   public static final RequestConfig DEFAULT = (new Builder()).build();
/*     */   
/*     */   private final boolean expectContinueEnabled;
/*     */   
/*     */   private final HttpHost proxy;
/*     */   
/*     */   private final InetAddress localAddress;
/*     */   
/*     */   private final boolean staleConnectionCheckEnabled;
/*     */   private final String cookieSpec;
/*     */   private final boolean redirectsEnabled;
/*     */   private final boolean relativeRedirectsAllowed;
/*     */   private final boolean circularRedirectsAllowed;
/*     */   private final int maxRedirects;
/*     */   private final boolean authenticationEnabled;
/*     */   private final Collection<String> targetPreferredAuthSchemes;
/*     */   private final Collection<String> proxyPreferredAuthSchemes;
/*     */   private final int connectionRequestTimeout;
/*     */   private final int connectTimeout;
/*     */   private final int socketTimeout;
/*     */   private final boolean contentCompressionEnabled;
/*     */   private final boolean normalizeUri;
/*     */   
/*     */   protected RequestConfig() {
/*  69 */     this(false, null, null, false, null, false, false, false, 0, false, null, null, 0, 0, 0, true, true);
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
/*     */   RequestConfig(boolean expectContinueEnabled, HttpHost proxy, InetAddress localAddress, boolean staleConnectionCheckEnabled, String cookieSpec, boolean redirectsEnabled, boolean relativeRedirectsAllowed, boolean circularRedirectsAllowed, int maxRedirects, boolean authenticationEnabled, Collection<String> targetPreferredAuthSchemes, Collection<String> proxyPreferredAuthSchemes, int connectionRequestTimeout, int connectTimeout, int socketTimeout, boolean contentCompressionEnabled, boolean normalizeUri) {
/*  91 */     this.expectContinueEnabled = expectContinueEnabled;
/*  92 */     this.proxy = proxy;
/*  93 */     this.localAddress = localAddress;
/*  94 */     this.staleConnectionCheckEnabled = staleConnectionCheckEnabled;
/*  95 */     this.cookieSpec = cookieSpec;
/*  96 */     this.redirectsEnabled = redirectsEnabled;
/*  97 */     this.relativeRedirectsAllowed = relativeRedirectsAllowed;
/*  98 */     this.circularRedirectsAllowed = circularRedirectsAllowed;
/*  99 */     this.maxRedirects = maxRedirects;
/* 100 */     this.authenticationEnabled = authenticationEnabled;
/* 101 */     this.targetPreferredAuthSchemes = targetPreferredAuthSchemes;
/* 102 */     this.proxyPreferredAuthSchemes = proxyPreferredAuthSchemes;
/* 103 */     this.connectionRequestTimeout = connectionRequestTimeout;
/* 104 */     this.connectTimeout = connectTimeout;
/* 105 */     this.socketTimeout = socketTimeout;
/* 106 */     this.contentCompressionEnabled = contentCompressionEnabled;
/* 107 */     this.normalizeUri = normalizeUri;
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
/*     */   public boolean isExpectContinueEnabled() {
/* 133 */     return this.expectContinueEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HttpHost getProxy() {
/* 143 */     return this.proxy;
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
/*     */   public InetAddress getLocalAddress() {
/* 158 */     return this.localAddress;
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
/*     */   @Deprecated
/*     */   public boolean isStaleConnectionCheckEnabled() {
/* 175 */     return this.staleConnectionCheckEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCookieSpec() {
/* 186 */     return this.cookieSpec;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRedirectsEnabled() {
/* 196 */     return this.redirectsEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRelativeRedirectsAllowed() {
/* 207 */     return this.relativeRedirectsAllowed;
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
/*     */   public boolean isCircularRedirectsAllowed() {
/* 219 */     return this.circularRedirectsAllowed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxRedirects() {
/* 230 */     return this.maxRedirects;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAuthenticationEnabled() {
/* 240 */     return this.authenticationEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<String> getTargetPreferredAuthSchemes() {
/* 251 */     return this.targetPreferredAuthSchemes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<String> getProxyPreferredAuthSchemes() {
/* 262 */     return this.proxyPreferredAuthSchemes;
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
/*     */   public int getConnectionRequestTimeout() {
/* 278 */     return this.connectionRequestTimeout;
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
/*     */   public int getConnectTimeout() {
/* 293 */     return this.connectTimeout;
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
/*     */   public int getSocketTimeout() {
/* 309 */     return this.socketTimeout;
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
/*     */   public boolean isDecompressionEnabled() {
/* 323 */     return this.contentCompressionEnabled;
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
/*     */   public boolean isContentCompressionEnabled() {
/* 335 */     return this.contentCompressionEnabled;
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
/*     */   public boolean isNormalizeUri() {
/* 347 */     return this.normalizeUri;
/*     */   }
/*     */ 
/*     */   
/*     */   protected RequestConfig clone() throws CloneNotSupportedException {
/* 352 */     return (RequestConfig)super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 357 */     StringBuilder builder = new StringBuilder();
/* 358 */     builder.append("[");
/* 359 */     builder.append("expectContinueEnabled=").append(this.expectContinueEnabled);
/* 360 */     builder.append(", proxy=").append(this.proxy);
/* 361 */     builder.append(", localAddress=").append(this.localAddress);
/* 362 */     builder.append(", cookieSpec=").append(this.cookieSpec);
/* 363 */     builder.append(", redirectsEnabled=").append(this.redirectsEnabled);
/* 364 */     builder.append(", relativeRedirectsAllowed=").append(this.relativeRedirectsAllowed);
/* 365 */     builder.append(", maxRedirects=").append(this.maxRedirects);
/* 366 */     builder.append(", circularRedirectsAllowed=").append(this.circularRedirectsAllowed);
/* 367 */     builder.append(", authenticationEnabled=").append(this.authenticationEnabled);
/* 368 */     builder.append(", targetPreferredAuthSchemes=").append(this.targetPreferredAuthSchemes);
/* 369 */     builder.append(", proxyPreferredAuthSchemes=").append(this.proxyPreferredAuthSchemes);
/* 370 */     builder.append(", connectionRequestTimeout=").append(this.connectionRequestTimeout);
/* 371 */     builder.append(", connectTimeout=").append(this.connectTimeout);
/* 372 */     builder.append(", socketTimeout=").append(this.socketTimeout);
/* 373 */     builder.append(", contentCompressionEnabled=").append(this.contentCompressionEnabled);
/* 374 */     builder.append(", normalizeUri=").append(this.normalizeUri);
/* 375 */     builder.append("]");
/* 376 */     return builder.toString();
/*     */   }
/*     */   
/*     */   public static Builder custom() {
/* 380 */     return new Builder();
/*     */   }
/*     */ 
/*     */   
/*     */   public static Builder copy(RequestConfig config) {
/* 385 */     return (new Builder()).setExpectContinueEnabled(config.isExpectContinueEnabled()).setProxy(config.getProxy()).setLocalAddress(config.getLocalAddress()).setStaleConnectionCheckEnabled(config.isStaleConnectionCheckEnabled()).setCookieSpec(config.getCookieSpec()).setRedirectsEnabled(config.isRedirectsEnabled()).setRelativeRedirectsAllowed(config.isRelativeRedirectsAllowed()).setCircularRedirectsAllowed(config.isCircularRedirectsAllowed()).setMaxRedirects(config.getMaxRedirects()).setAuthenticationEnabled(config.isAuthenticationEnabled()).setTargetPreferredAuthSchemes(config.getTargetPreferredAuthSchemes()).setProxyPreferredAuthSchemes(config.getProxyPreferredAuthSchemes()).setConnectionRequestTimeout(config.getConnectionRequestTimeout()).setConnectTimeout(config.getConnectTimeout()).setSocketTimeout(config.getSocketTimeout()).setDecompressionEnabled(config.isDecompressionEnabled()).setContentCompressionEnabled(config.isContentCompressionEnabled());
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
/*     */   public static class Builder
/*     */   {
/*     */     private boolean staleConnectionCheckEnabled = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean redirectsEnabled = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 429 */     private int maxRedirects = 50;
/*     */     private boolean relativeRedirectsAllowed = true;
/*     */     private boolean authenticationEnabled = true;
/* 432 */     private int connectionRequestTimeout = -1;
/* 433 */     private int connectTimeout = -1;
/* 434 */     private int socketTimeout = -1; private boolean contentCompressionEnabled = true;
/*     */     private boolean normalizeUri = true;
/*     */     private boolean expectContinueEnabled;
/*     */     private HttpHost proxy;
/*     */     
/*     */     public Builder setExpectContinueEnabled(boolean expectContinueEnabled) {
/* 440 */       this.expectContinueEnabled = expectContinueEnabled;
/* 441 */       return this;
/*     */     }
/*     */     private InetAddress localAddress; private String cookieSpec; private boolean circularRedirectsAllowed; private Collection<String> targetPreferredAuthSchemes; private Collection<String> proxyPreferredAuthSchemes;
/*     */     public Builder setProxy(HttpHost proxy) {
/* 445 */       this.proxy = proxy;
/* 446 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setLocalAddress(InetAddress localAddress) {
/* 450 */       this.localAddress = localAddress;
/* 451 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public Builder setStaleConnectionCheckEnabled(boolean staleConnectionCheckEnabled) {
/* 460 */       this.staleConnectionCheckEnabled = staleConnectionCheckEnabled;
/* 461 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setCookieSpec(String cookieSpec) {
/* 465 */       this.cookieSpec = cookieSpec;
/* 466 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setRedirectsEnabled(boolean redirectsEnabled) {
/* 470 */       this.redirectsEnabled = redirectsEnabled;
/* 471 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setRelativeRedirectsAllowed(boolean relativeRedirectsAllowed) {
/* 475 */       this.relativeRedirectsAllowed = relativeRedirectsAllowed;
/* 476 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setCircularRedirectsAllowed(boolean circularRedirectsAllowed) {
/* 480 */       this.circularRedirectsAllowed = circularRedirectsAllowed;
/* 481 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setMaxRedirects(int maxRedirects) {
/* 485 */       this.maxRedirects = maxRedirects;
/* 486 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setAuthenticationEnabled(boolean authenticationEnabled) {
/* 490 */       this.authenticationEnabled = authenticationEnabled;
/* 491 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setTargetPreferredAuthSchemes(Collection<String> targetPreferredAuthSchemes) {
/* 495 */       this.targetPreferredAuthSchemes = targetPreferredAuthSchemes;
/* 496 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setProxyPreferredAuthSchemes(Collection<String> proxyPreferredAuthSchemes) {
/* 500 */       this.proxyPreferredAuthSchemes = proxyPreferredAuthSchemes;
/* 501 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setConnectionRequestTimeout(int connectionRequestTimeout) {
/* 505 */       this.connectionRequestTimeout = connectionRequestTimeout;
/* 506 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setConnectTimeout(int connectTimeout) {
/* 510 */       this.connectTimeout = connectTimeout;
/* 511 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setSocketTimeout(int socketTimeout) {
/* 515 */       this.socketTimeout = socketTimeout;
/* 516 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public Builder setDecompressionEnabled(boolean decompressionEnabled) {
/* 525 */       this.contentCompressionEnabled = decompressionEnabled;
/* 526 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setContentCompressionEnabled(boolean contentCompressionEnabled) {
/* 530 */       this.contentCompressionEnabled = contentCompressionEnabled;
/* 531 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setNormalizeUri(boolean normalizeUri) {
/* 535 */       this.normalizeUri = normalizeUri;
/* 536 */       return this;
/*     */     }
/*     */     
/*     */     public RequestConfig build() {
/* 540 */       return new RequestConfig(this.expectContinueEnabled, this.proxy, this.localAddress, this.staleConnectionCheckEnabled, this.cookieSpec, this.redirectsEnabled, this.relativeRedirectsAllowed, this.circularRedirectsAllowed, this.maxRedirects, this.authenticationEnabled, this.targetPreferredAuthSchemes, this.proxyPreferredAuthSchemes, this.connectionRequestTimeout, this.connectTimeout, this.socketTimeout, this.contentCompressionEnabled, this.normalizeUri);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/client/config/RequestConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */