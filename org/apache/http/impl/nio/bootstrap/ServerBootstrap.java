/*     */ package org.apache.http.impl.nio.bootstrap;
/*     */ 
/*     */ import java.net.InetAddress;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import org.apache.http.ConnectionReuseStrategy;
/*     */ import org.apache.http.ExceptionLogger;
/*     */ import org.apache.http.HttpRequestInterceptor;
/*     */ import org.apache.http.HttpResponseFactory;
/*     */ import org.apache.http.HttpResponseInterceptor;
/*     */ import org.apache.http.config.ConnectionConfig;
/*     */ import org.apache.http.impl.DefaultConnectionReuseStrategy;
/*     */ import org.apache.http.impl.DefaultHttpResponseFactory;
/*     */ import org.apache.http.impl.nio.DefaultNHttpServerConnection;
/*     */ import org.apache.http.impl.nio.DefaultNHttpServerConnectionFactory;
/*     */ import org.apache.http.impl.nio.SSLNHttpServerConnectionFactory;
/*     */ import org.apache.http.impl.nio.reactor.IOReactorConfig;
/*     */ import org.apache.http.nio.NHttpConnectionFactory;
/*     */ import org.apache.http.nio.NHttpServerEventHandler;
/*     */ import org.apache.http.nio.protocol.HttpAsyncExpectationVerifier;
/*     */ import org.apache.http.nio.protocol.HttpAsyncRequestHandler;
/*     */ import org.apache.http.nio.protocol.HttpAsyncRequestHandlerMapper;
/*     */ import org.apache.http.nio.protocol.HttpAsyncService;
/*     */ import org.apache.http.nio.protocol.UriHttpAsyncRequestHandlerMapper;
/*     */ import org.apache.http.nio.reactor.ssl.SSLSetupHandler;
/*     */ import org.apache.http.protocol.HttpProcessor;
/*     */ import org.apache.http.protocol.HttpProcessorBuilder;
/*     */ import org.apache.http.protocol.ResponseConnControl;
/*     */ import org.apache.http.protocol.ResponseContent;
/*     */ import org.apache.http.protocol.ResponseDate;
/*     */ import org.apache.http.protocol.ResponseServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServerBootstrap
/*     */ {
/*     */   private int listenerPort;
/*     */   private InetAddress localAddress;
/*     */   private IOReactorConfig ioReactorConfig;
/*     */   private ConnectionConfig connectionConfig;
/*     */   private LinkedList<HttpRequestInterceptor> requestFirst;
/*     */   private LinkedList<HttpRequestInterceptor> requestLast;
/*     */   private LinkedList<HttpResponseInterceptor> responseFirst;
/*     */   private LinkedList<HttpResponseInterceptor> responseLast;
/*     */   private String serverInfo;
/*     */   private HttpProcessor httpProcessor;
/*     */   private ConnectionReuseStrategy connStrategy;
/*     */   private HttpResponseFactory responseFactory;
/*     */   private HttpAsyncRequestHandlerMapper handlerMapper;
/*     */   private Map<String, HttpAsyncRequestHandler<?>> handlerMap;
/*     */   private HttpAsyncExpectationVerifier expectationVerifier;
/*     */   private SSLContext sslContext;
/*     */   private SSLSetupHandler sslSetupHandler;
/*     */   private NHttpConnectionFactory<? extends DefaultNHttpServerConnection> connectionFactory;
/*     */   private ExceptionLogger exceptionLogger;
/*     */   
/*     */   public static ServerBootstrap bootstrap() {
/*  91 */     return new ServerBootstrap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap setListenerPort(int listenerPort) {
/*  98 */     this.listenerPort = listenerPort;
/*  99 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap setLocalAddress(InetAddress localAddress) {
/* 106 */     this.localAddress = localAddress;
/* 107 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap setIOReactorConfig(IOReactorConfig ioReactorConfig) {
/* 114 */     this.ioReactorConfig = ioReactorConfig;
/* 115 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap setConnectionConfig(ConnectionConfig connectionConfig) {
/* 125 */     this.connectionConfig = connectionConfig;
/* 126 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap setHttpProcessor(HttpProcessor httpProcessor) {
/* 133 */     this.httpProcessor = httpProcessor;
/* 134 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap addInterceptorFirst(HttpResponseInterceptor itcp) {
/* 144 */     if (itcp == null) {
/* 145 */       return this;
/*     */     }
/* 147 */     if (this.responseFirst == null) {
/* 148 */       this.responseFirst = new LinkedList<HttpResponseInterceptor>();
/*     */     }
/* 150 */     this.responseFirst.addFirst(itcp);
/* 151 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap addInterceptorLast(HttpResponseInterceptor itcp) {
/* 161 */     if (itcp == null) {
/* 162 */       return this;
/*     */     }
/* 164 */     if (this.responseLast == null) {
/* 165 */       this.responseLast = new LinkedList<HttpResponseInterceptor>();
/*     */     }
/* 167 */     this.responseLast.addLast(itcp);
/* 168 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap addInterceptorFirst(HttpRequestInterceptor itcp) {
/* 178 */     if (itcp == null) {
/* 179 */       return this;
/*     */     }
/* 181 */     if (this.requestFirst == null) {
/* 182 */       this.requestFirst = new LinkedList<HttpRequestInterceptor>();
/*     */     }
/* 184 */     this.requestFirst.addFirst(itcp);
/* 185 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap addInterceptorLast(HttpRequestInterceptor itcp) {
/* 195 */     if (itcp == null) {
/* 196 */       return this;
/*     */     }
/* 198 */     if (this.requestLast == null) {
/* 199 */       this.requestLast = new LinkedList<HttpRequestInterceptor>();
/*     */     }
/* 201 */     this.requestLast.addLast(itcp);
/* 202 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap setServerInfo(String serverInfo) {
/* 212 */     this.serverInfo = serverInfo;
/* 213 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap setConnectionReuseStrategy(ConnectionReuseStrategy connStrategy) {
/* 220 */     this.connStrategy = connStrategy;
/* 221 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap setResponseFactory(HttpResponseFactory responseFactory) {
/* 228 */     this.responseFactory = responseFactory;
/* 229 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap setHandlerMapper(HttpAsyncRequestHandlerMapper handlerMapper) {
/* 236 */     this.handlerMapper = handlerMapper;
/* 237 */     return this;
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
/*     */   public final ServerBootstrap registerHandler(String pattern, HttpAsyncRequestHandler<?> handler) {
/* 251 */     if (pattern == null || handler == null) {
/* 252 */       return this;
/*     */     }
/* 254 */     if (this.handlerMap == null) {
/* 255 */       this.handlerMap = new HashMap<String, HttpAsyncRequestHandler<?>>();
/*     */     }
/* 257 */     this.handlerMap.put(pattern, handler);
/* 258 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap setExpectationVerifier(HttpAsyncExpectationVerifier expectationVerifier) {
/* 265 */     this.expectationVerifier = expectationVerifier;
/* 266 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap setConnectionFactory(NHttpConnectionFactory<? extends DefaultNHttpServerConnection> connectionFactory) {
/* 274 */     this.connectionFactory = connectionFactory;
/* 275 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap setSslContext(SSLContext sslContext) {
/* 285 */     this.sslContext = sslContext;
/* 286 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ServerBootstrap setSslSetupHandler(SSLSetupHandler sslSetupHandler) {
/* 296 */     this.sslSetupHandler = sslSetupHandler;
/* 297 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ServerBootstrap setExceptionLogger(ExceptionLogger exceptionLogger) {
/* 304 */     this.exceptionLogger = exceptionLogger;
/* 305 */     return this; } public HttpServer create() {
/*     */     UriHttpAsyncRequestHandlerMapper uriHttpAsyncRequestHandlerMapper;
/*     */     DefaultConnectionReuseStrategy defaultConnectionReuseStrategy;
/*     */     DefaultHttpResponseFactory defaultHttpResponseFactory;
/*     */     DefaultNHttpServerConnectionFactory defaultNHttpServerConnectionFactory;
/* 310 */     HttpProcessor httpProcessorCopy = this.httpProcessor;
/* 311 */     if (httpProcessorCopy == null) {
/*     */       
/* 313 */       HttpProcessorBuilder b = HttpProcessorBuilder.create();
/* 314 */       if (this.requestFirst != null) {
/* 315 */         for (HttpRequestInterceptor i : this.requestFirst) {
/* 316 */           b.addFirst(i);
/*     */         }
/*     */       }
/* 319 */       if (this.responseFirst != null) {
/* 320 */         for (HttpResponseInterceptor i : this.responseFirst) {
/* 321 */           b.addFirst(i);
/*     */         }
/*     */       }
/*     */       
/* 325 */       String serverInfoCopy = this.serverInfo;
/* 326 */       if (serverInfoCopy == null) {
/* 327 */         serverInfoCopy = "Apache-HttpCore-NIO/1.1";
/*     */       }
/*     */       
/* 330 */       b.addAll(new HttpResponseInterceptor[] { (HttpResponseInterceptor)new ResponseDate(), (HttpResponseInterceptor)new ResponseServer(serverInfoCopy), (HttpResponseInterceptor)new ResponseContent(), (HttpResponseInterceptor)new ResponseConnControl() });
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 335 */       if (this.requestLast != null) {
/* 336 */         for (HttpRequestInterceptor i : this.requestLast) {
/* 337 */           b.addLast(i);
/*     */         }
/*     */       }
/* 340 */       if (this.responseLast != null) {
/* 341 */         for (HttpResponseInterceptor i : this.responseLast) {
/* 342 */           b.addLast(i);
/*     */         }
/*     */       }
/* 345 */       httpProcessorCopy = b.build();
/*     */     } 
/*     */     
/* 348 */     HttpAsyncRequestHandlerMapper handlerMapperCopy = this.handlerMapper;
/* 349 */     if (handlerMapperCopy == null) {
/* 350 */       UriHttpAsyncRequestHandlerMapper reqistry = new UriHttpAsyncRequestHandlerMapper();
/* 351 */       if (this.handlerMap != null) {
/* 352 */         for (Map.Entry<String, HttpAsyncRequestHandler<?>> entry : this.handlerMap.entrySet()) {
/* 353 */           reqistry.register(entry.getKey(), entry.getValue());
/*     */         }
/*     */       }
/* 356 */       uriHttpAsyncRequestHandlerMapper = reqistry;
/*     */     } 
/*     */     
/* 359 */     ConnectionReuseStrategy connStrategyCopy = this.connStrategy;
/* 360 */     if (connStrategyCopy == null) {
/* 361 */       defaultConnectionReuseStrategy = DefaultConnectionReuseStrategy.INSTANCE;
/*     */     }
/*     */     
/* 364 */     HttpResponseFactory responseFactoryCopy = this.responseFactory;
/* 365 */     if (responseFactoryCopy == null) {
/* 366 */       defaultHttpResponseFactory = DefaultHttpResponseFactory.INSTANCE;
/*     */     }
/*     */     
/* 369 */     NHttpConnectionFactory<? extends DefaultNHttpServerConnection> connectionFactoryCopy = this.connectionFactory;
/* 370 */     if (connectionFactoryCopy == null) {
/* 371 */       if (this.sslContext != null) {
/* 372 */         SSLNHttpServerConnectionFactory sSLNHttpServerConnectionFactory = new SSLNHttpServerConnectionFactory(this.sslContext, this.sslSetupHandler, this.connectionConfig);
/*     */       } else {
/*     */         
/* 375 */         defaultNHttpServerConnectionFactory = new DefaultNHttpServerConnectionFactory(this.connectionConfig);
/*     */       } 
/*     */     }
/*     */     
/* 379 */     ExceptionLogger exceptionLoggerCopy = this.exceptionLogger;
/* 380 */     if (exceptionLoggerCopy == null) {
/* 381 */       exceptionLoggerCopy = ExceptionLogger.NO_OP;
/*     */     }
/*     */     
/* 384 */     HttpAsyncService httpService = new HttpAsyncService(httpProcessorCopy, (ConnectionReuseStrategy)defaultConnectionReuseStrategy, (HttpResponseFactory)defaultHttpResponseFactory, (HttpAsyncRequestHandlerMapper)uriHttpAsyncRequestHandlerMapper, this.expectationVerifier, exceptionLoggerCopy);
/*     */ 
/*     */ 
/*     */     
/* 388 */     return new HttpServer(this.listenerPort, this.localAddress, this.ioReactorConfig, (NHttpServerEventHandler)httpService, (NHttpConnectionFactory<? extends DefaultNHttpServerConnection>)defaultNHttpServerConnectionFactory, exceptionLoggerCopy);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/bootstrap/ServerBootstrap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */