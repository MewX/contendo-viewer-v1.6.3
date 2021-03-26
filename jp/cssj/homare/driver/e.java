/*     */ package jp.cssj.homare.driver;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.util.Map;
/*     */ import jp.cssj.e.a;
/*     */ import jp.cssj.e.a.b;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.e.b.a;
/*     */ import jp.cssj.e.c;
/*     */ import jp.cssj.e.g.a;
/*     */ import jp.cssj.homare.a.c;
/*     */ import jp.cssj.homare.ua.a.B;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpRequestInterceptor;
/*     */ import org.apache.http.auth.AuthScheme;
/*     */ import org.apache.http.auth.AuthScope;
/*     */ import org.apache.http.auth.AuthState;
/*     */ import org.apache.http.auth.Credentials;
/*     */ import org.apache.http.auth.UsernamePasswordCredentials;
/*     */ import org.apache.http.client.CredentialsProvider;
/*     */ import org.apache.http.client.config.RequestConfig;
/*     */ import org.apache.http.client.protocol.HttpClientContext;
/*     */ import org.apache.http.cookie.Cookie;
/*     */ import org.apache.http.impl.auth.BasicScheme;
/*     */ import org.apache.http.impl.cookie.BasicClientCookie;
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class e
/*     */   implements c
/*     */ {
/*  48 */   protected b a = new b();
/*  49 */   protected c b = null;
/*  50 */   protected a c = new a();
/*     */   
/*     */   public void a(URI uri, Map<String, String> props, c mh) {
/*  53 */     a resolver = a.a();
/*  54 */     c httpResolver = new c();
/*  55 */     if (B.g.a(props, mh)) {
/*  56 */       httpResolver.a(uri);
/*     */     }
/*     */ 
/*     */     
/*  60 */     for (int i = 0;; i++) {
/*  61 */       String prefix = "input.http.header." + i + ".";
/*  62 */       String name = props.get(prefix + "name");
/*  63 */       if (name == null) {
/*     */         break;
/*     */       }
/*  66 */       String value = props.get(prefix + "value");
/*  67 */       httpResolver.a(name, value);
/*     */     } 
/*     */     
/*  70 */     RequestConfig.Builder config = httpResolver.a;
/*  71 */     CredentialsProvider credsProvider = httpResolver.b;
/*  72 */     config.setConnectionRequestTimeout(B.h.a(props, mh));
/*  73 */     config.setSocketTimeout(B.i.a(props, mh));
/*     */ 
/*     */     
/*  76 */     String proxyHost = B.j.a(props);
/*  77 */     if (proxyHost != null) {
/*  78 */       int proxyPort = B.k.a(props, mh);
/*  79 */       HttpHost proxy = new HttpHost(proxyHost, proxyPort);
/*  80 */       config.setProxy(proxy);
/*  81 */       String user = B.l.a(props);
/*  82 */       String password = B.m.a(props);
/*  83 */       if (password == null) {
/*  84 */         password = "";
/*     */       }
/*  86 */       if (user != null) {
/*  87 */         UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(user, password);
/*  88 */         credsProvider.setCredentials(new AuthScope(proxyHost, proxyPort), (Credentials)usernamePasswordCredentials);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  93 */     httpResolver.d = null;
/*  94 */     boolean preemptive = B.n.a(props, mh);
/*  95 */     if (preemptive) {
/*  96 */       HttpRequestInterceptor preemptiveAuth = new HttpRequestInterceptor(this)
/*     */         {
/*     */           public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
/*  99 */             HttpClientContext clientContext = (HttpClientContext)context;
/* 100 */             AuthState authState = clientContext.getTargetAuthState();
/* 101 */             CredentialsProvider credsProvider = clientContext.getCredentialsProvider();
/* 102 */             HttpHost targetHost = clientContext.getTargetHost();
/*     */             
/* 104 */             if (authState.getAuthScheme() == null) {
/* 105 */               AuthScope authScope = new AuthScope(targetHost.getHostName(), targetHost.getPort());
/*     */               
/* 107 */               Credentials creds = credsProvider.getCredentials(authScope);
/*     */               
/* 109 */               if (creds != null) {
/* 110 */                 authState.update((AuthScheme)new BasicScheme(), creds);
/*     */               }
/*     */             } 
/*     */           }
/*     */         };
/*     */       
/* 116 */       httpResolver.d = preemptiveAuth;
/*     */     }  int j;
/* 118 */     for (j = 0;; j++) {
/* 119 */       int port; String prefix = "input.http.authentication." + j + ".";
/* 120 */       String host = props.get(prefix + "host");
/* 121 */       if (host == null) {
/*     */         break;
/*     */       }
/* 124 */       String user = props.get(prefix + "user");
/* 125 */       if (user == null) {
/*     */         break;
/*     */       }
/* 128 */       String _port = props.get(prefix + "port");
/*     */       
/* 130 */       if (_port == null) {
/* 131 */         port = -1;
/*     */       } else {
/*     */         try {
/* 134 */           port = Integer.parseInt(_port);
/* 135 */         } catch (NumberFormatException numberFormatException) {
/* 136 */           port = -1;
/*     */         } 
/*     */       } 
/* 139 */       String realm = props.get(prefix + "realm");
/* 140 */       String scheme = props.get(prefix + "scheme");
/* 141 */       String password = props.get(prefix + "password");
/* 142 */       if (password == null) {
/* 143 */         password = "";
/*     */       }
/*     */       
/* 146 */       AuthScope authScope = new AuthScope(host, port, realm, scheme);
/* 147 */       UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(user, password);
/* 148 */       credsProvider.setCredentials(authScope, (Credentials)usernamePasswordCredentials);
/*     */     } 
/*     */ 
/*     */     
/* 152 */     for (j = 0;; j++) {
/* 153 */       String prefix = "input.http.cookie." + j + ".";
/* 154 */       String domain = props.get(prefix + "domain");
/* 155 */       if (domain == null) {
/*     */         break;
/*     */       }
/* 158 */       String name = props.get(prefix + "name");
/* 159 */       if (name == null) {
/*     */         break;
/*     */       }
/* 162 */       String value = props.get(prefix + "value");
/* 163 */       if (value == null) {
/* 164 */         value = "";
/*     */       }
/* 166 */       String path = props.get(prefix + "path");
/* 167 */       if (path == null) {
/* 168 */         path = "/";
/*     */       }
/*     */       
/* 171 */       BasicClientCookie cookie = new BasicClientCookie(name, value);
/* 172 */       cookie.setDomain(domain);
/* 173 */       cookie.setPath(path);
/* 174 */       cookie.setSecure(false);
/* 175 */       httpResolver.c.addCookie((Cookie)cookie);
/*     */     } 
/*     */     
/* 178 */     resolver.a("http", (c)httpResolver);
/* 179 */     resolver.a("https", (c)httpResolver);
/*     */     
/* 181 */     this.c.a((c)resolver);
/*     */   }
/*     */   
/*     */   public void a(URI uriPattern) {
/* 185 */     this.c.c(uriPattern);
/*     */   }
/*     */   
/*     */   public void c(URI uriPattern) {
/* 189 */     this.c.d(uriPattern);
/*     */   }
/*     */   
/*     */   public File a(a metaSource) throws IOException {
/* 193 */     return this.a.a(metaSource);
/*     */   }
/*     */   
/*     */   public void a(c userResolver) {
/* 197 */     this.b = userResolver;
/*     */   }
/*     */   
/*     */   public void a() {
/* 201 */     this.c.b();
/* 202 */     this.a.a();
/* 203 */     this.b = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b b(URI uri) throws IOException, FileNotFoundException {
/* 212 */     return a(uri, false);
/*     */   }
/*     */   
/*     */   public b a(URI uri, boolean force) throws IOException, SecurityException {
/*     */     try {
/* 217 */       b source = this.a.b(uri);
/* 218 */       return (b)new d(source, (c)this.a);
/* 219 */     } catch (FileNotFoundException fileNotFoundException) {
/* 220 */       if (this.b != null) {
/*     */         try {
/* 222 */           b b1 = this.b.b(uri);
/* 223 */           return (b)new d(b1, this.b);
/* 224 */         } catch (FileNotFoundException fileNotFoundException1) {}
/*     */       }
/*     */ 
/*     */       
/* 228 */       b source = this.c.a(uri, force);
/* 229 */       return (b)new d(source, (c)this.c);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(b source) {
/* 234 */     ((d)source).e();
/*     */   }
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 238 */     a();
/* 239 */     super.finalize();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/driver/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */