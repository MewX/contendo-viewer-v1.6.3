/*     */ package org.apache.http.client.protocol;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.List;
/*     */ import org.apache.http.auth.AuthSchemeProvider;
/*     */ import org.apache.http.auth.AuthState;
/*     */ import org.apache.http.client.AuthCache;
/*     */ import org.apache.http.client.CookieStore;
/*     */ import org.apache.http.client.CredentialsProvider;
/*     */ import org.apache.http.client.config.RequestConfig;
/*     */ import org.apache.http.config.Lookup;
/*     */ import org.apache.http.conn.routing.HttpRoute;
/*     */ import org.apache.http.conn.routing.RouteInfo;
/*     */ import org.apache.http.cookie.CookieOrigin;
/*     */ import org.apache.http.cookie.CookieSpec;
/*     */ import org.apache.http.cookie.CookieSpecProvider;
/*     */ import org.apache.http.protocol.BasicHttpContext;
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ import org.apache.http.protocol.HttpCoreContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HttpClientContext
/*     */   extends HttpCoreContext
/*     */ {
/*     */   public static final String HTTP_ROUTE = "http.route";
/*     */   public static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";
/*     */   public static final String COOKIESPEC_REGISTRY = "http.cookiespec-registry";
/*     */   public static final String COOKIE_SPEC = "http.cookie-spec";
/*     */   public static final String COOKIE_ORIGIN = "http.cookie-origin";
/*     */   public static final String COOKIE_STORE = "http.cookie-store";
/*     */   public static final String CREDS_PROVIDER = "http.auth.credentials-provider";
/*     */   public static final String AUTH_CACHE = "http.auth.auth-cache";
/*     */   public static final String TARGET_AUTH_STATE = "http.auth.target-scope";
/*     */   public static final String PROXY_AUTH_STATE = "http.auth.proxy-scope";
/*     */   public static final String USER_TOKEN = "http.user-token";
/*     */   public static final String AUTHSCHEME_REGISTRY = "http.authscheme-registry";
/*     */   public static final String REQUEST_CONFIG = "http.request-config";
/*     */   
/*     */   public static HttpClientContext adapt(HttpContext context) {
/* 137 */     return (context instanceof HttpClientContext) ? (HttpClientContext)context : new HttpClientContext(context);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static HttpClientContext create() {
/* 143 */     return new HttpClientContext((HttpContext)new BasicHttpContext());
/*     */   }
/*     */   
/*     */   public HttpClientContext(HttpContext context) {
/* 147 */     super(context);
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpClientContext() {}
/*     */ 
/*     */   
/*     */   public RouteInfo getHttpRoute() {
/* 155 */     return (RouteInfo)getAttribute("http.route", HttpRoute.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<URI> getRedirectLocations() {
/* 160 */     return (List<URI>)getAttribute("http.protocol.redirect-locations", List.class);
/*     */   }
/*     */   
/*     */   public CookieStore getCookieStore() {
/* 164 */     return (CookieStore)getAttribute("http.cookie-store", CookieStore.class);
/*     */   }
/*     */   
/*     */   public void setCookieStore(CookieStore cookieStore) {
/* 168 */     setAttribute("http.cookie-store", cookieStore);
/*     */   }
/*     */   
/*     */   public CookieSpec getCookieSpec() {
/* 172 */     return (CookieSpec)getAttribute("http.cookie-spec", CookieSpec.class);
/*     */   }
/*     */   
/*     */   public CookieOrigin getCookieOrigin() {
/* 176 */     return (CookieOrigin)getAttribute("http.cookie-origin", CookieOrigin.class);
/*     */   }
/*     */ 
/*     */   
/*     */   private <T> Lookup<T> getLookup(String name, Class<T> clazz) {
/* 181 */     return (Lookup<T>)getAttribute(name, Lookup.class);
/*     */   }
/*     */   
/*     */   public Lookup<CookieSpecProvider> getCookieSpecRegistry() {
/* 185 */     return getLookup("http.cookiespec-registry", CookieSpecProvider.class);
/*     */   }
/*     */   
/*     */   public void setCookieSpecRegistry(Lookup<CookieSpecProvider> lookup) {
/* 189 */     setAttribute("http.cookiespec-registry", lookup);
/*     */   }
/*     */   
/*     */   public Lookup<AuthSchemeProvider> getAuthSchemeRegistry() {
/* 193 */     return getLookup("http.authscheme-registry", AuthSchemeProvider.class);
/*     */   }
/*     */   
/*     */   public void setAuthSchemeRegistry(Lookup<AuthSchemeProvider> lookup) {
/* 197 */     setAttribute("http.authscheme-registry", lookup);
/*     */   }
/*     */   
/*     */   public CredentialsProvider getCredentialsProvider() {
/* 201 */     return (CredentialsProvider)getAttribute("http.auth.credentials-provider", CredentialsProvider.class);
/*     */   }
/*     */   
/*     */   public void setCredentialsProvider(CredentialsProvider credentialsProvider) {
/* 205 */     setAttribute("http.auth.credentials-provider", credentialsProvider);
/*     */   }
/*     */   
/*     */   public AuthCache getAuthCache() {
/* 209 */     return (AuthCache)getAttribute("http.auth.auth-cache", AuthCache.class);
/*     */   }
/*     */   
/*     */   public void setAuthCache(AuthCache authCache) {
/* 213 */     setAttribute("http.auth.auth-cache", authCache);
/*     */   }
/*     */   
/*     */   public AuthState getTargetAuthState() {
/* 217 */     return (AuthState)getAttribute("http.auth.target-scope", AuthState.class);
/*     */   }
/*     */   
/*     */   public AuthState getProxyAuthState() {
/* 221 */     return (AuthState)getAttribute("http.auth.proxy-scope", AuthState.class);
/*     */   }
/*     */   
/*     */   public <T> T getUserToken(Class<T> clazz) {
/* 225 */     return (T)getAttribute("http.user-token", clazz);
/*     */   }
/*     */   
/*     */   public Object getUserToken() {
/* 229 */     return getAttribute("http.user-token");
/*     */   }
/*     */   
/*     */   public void setUserToken(Object obj) {
/* 233 */     setAttribute("http.user-token", obj);
/*     */   }
/*     */   
/*     */   public RequestConfig getRequestConfig() {
/* 237 */     RequestConfig config = (RequestConfig)getAttribute("http.request-config", RequestConfig.class);
/* 238 */     return (config != null) ? config : RequestConfig.DEFAULT;
/*     */   }
/*     */   
/*     */   public void setRequestConfig(RequestConfig config) {
/* 242 */     setAttribute("http.request-config", config);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/client/protocol/HttpClientContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */