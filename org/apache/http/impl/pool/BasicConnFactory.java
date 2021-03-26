/*     */ package org.apache.http.impl.pool;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.Socket;
/*     */ import javax.net.SocketFactory;
/*     */ import javax.net.ssl.SSLSocketFactory;
/*     */ import org.apache.http.HttpClientConnection;
/*     */ import org.apache.http.HttpConnectionFactory;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.config.ConnectionConfig;
/*     */ import org.apache.http.config.SocketConfig;
/*     */ import org.apache.http.impl.DefaultBHttpClientConnection;
/*     */ import org.apache.http.impl.DefaultBHttpClientConnectionFactory;
/*     */ import org.apache.http.params.HttpParamConfig;
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.pool.ConnFactory;
/*     */ import org.apache.http.util.Args;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Contract(threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL)
/*     */ public class BasicConnFactory
/*     */   implements ConnFactory<HttpHost, HttpClientConnection>
/*     */ {
/*     */   private final SocketFactory plainfactory;
/*     */   private final SSLSocketFactory sslfactory;
/*     */   private final int connectTimeout;
/*     */   private final SocketConfig sconfig;
/*     */   private final HttpConnectionFactory<? extends HttpClientConnection> connFactory;
/*     */   
/*     */   @Deprecated
/*     */   public BasicConnFactory(SSLSocketFactory sslfactory, HttpParams params) {
/*  76 */     Args.notNull(params, "HTTP params");
/*  77 */     this.plainfactory = null;
/*  78 */     this.sslfactory = sslfactory;
/*  79 */     this.connectTimeout = params.getIntParameter("http.connection.timeout", 0);
/*  80 */     this.sconfig = HttpParamConfig.getSocketConfig(params);
/*  81 */     this.connFactory = (HttpConnectionFactory<? extends HttpClientConnection>)new DefaultBHttpClientConnectionFactory(HttpParamConfig.getConnectionConfig(params));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public BasicConnFactory(HttpParams params) {
/*  91 */     this((SSLSocketFactory)null, params);
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
/*     */   public BasicConnFactory(SocketFactory plainfactory, SSLSocketFactory sslfactory, int connectTimeout, SocketConfig sconfig, ConnectionConfig cconfig) {
/* 104 */     this.plainfactory = plainfactory;
/* 105 */     this.sslfactory = sslfactory;
/* 106 */     this.connectTimeout = connectTimeout;
/* 107 */     this.sconfig = (sconfig != null) ? sconfig : SocketConfig.DEFAULT;
/* 108 */     this.connFactory = (HttpConnectionFactory<? extends HttpClientConnection>)new DefaultBHttpClientConnectionFactory((cconfig != null) ? cconfig : ConnectionConfig.DEFAULT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicConnFactory(int connectTimeout, SocketConfig sconfig, ConnectionConfig cconfig) {
/* 117 */     this(null, null, connectTimeout, sconfig, cconfig);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicConnFactory(SocketConfig sconfig, ConnectionConfig cconfig) {
/* 124 */     this(null, null, 0, sconfig, cconfig);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicConnFactory() {
/* 131 */     this(null, null, 0, SocketConfig.DEFAULT, ConnectionConfig.DEFAULT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected HttpClientConnection create(Socket socket, HttpParams params) throws IOException {
/* 139 */     int bufsize = params.getIntParameter("http.socket.buffer-size", 8192);
/* 140 */     DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(bufsize);
/* 141 */     conn.bind(socket);
/* 142 */     return (HttpClientConnection)conn;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpClientConnection create(HttpHost host) throws IOException {
/* 147 */     String scheme = host.getSchemeName();
/* 148 */     Socket socket = null;
/* 149 */     if ("http".equalsIgnoreCase(scheme)) {
/* 150 */       socket = (this.plainfactory != null) ? this.plainfactory.createSocket() : new Socket();
/*     */     }
/* 152 */     if ("https".equalsIgnoreCase(scheme)) {
/* 153 */       socket = ((this.sslfactory != null) ? this.sslfactory : SSLSocketFactory.getDefault()).createSocket();
/*     */     }
/*     */     
/* 156 */     if (socket == null) {
/* 157 */       throw new IOException(scheme + " scheme is not supported");
/*     */     }
/* 159 */     String hostname = host.getHostName();
/* 160 */     int port = host.getPort();
/* 161 */     if (port == -1) {
/* 162 */       if (host.getSchemeName().equalsIgnoreCase("http")) {
/* 163 */         port = 80;
/* 164 */       } else if (host.getSchemeName().equalsIgnoreCase("https")) {
/* 165 */         port = 443;
/*     */       } 
/*     */     }
/* 168 */     socket.setSoTimeout(this.sconfig.getSoTimeout());
/* 169 */     if (this.sconfig.getSndBufSize() > 0) {
/* 170 */       socket.setSendBufferSize(this.sconfig.getSndBufSize());
/*     */     }
/* 172 */     if (this.sconfig.getRcvBufSize() > 0) {
/* 173 */       socket.setReceiveBufferSize(this.sconfig.getRcvBufSize());
/*     */     }
/* 175 */     socket.setTcpNoDelay(this.sconfig.isTcpNoDelay());
/* 176 */     int linger = this.sconfig.getSoLinger();
/* 177 */     if (linger >= 0) {
/* 178 */       socket.setSoLinger(true, linger);
/*     */     }
/* 180 */     socket.setKeepAlive(this.sconfig.isSoKeepAlive());
/* 181 */     socket.connect(new InetSocketAddress(hostname, port), this.connectTimeout);
/* 182 */     return (HttpClientConnection)this.connFactory.createConnection(socket);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/pool/BasicConnFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */