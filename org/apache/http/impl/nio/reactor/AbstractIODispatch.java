/*     */ package org.apache.http.impl.nio.reactor;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.http.nio.reactor.IOEventDispatch;
/*     */ import org.apache.http.nio.reactor.IOSession;
/*     */ import org.apache.http.nio.reactor.ssl.SSLIOSession;
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
/*     */ public abstract class AbstractIODispatch<T>
/*     */   implements IOEventDispatch
/*     */ {
/*     */   protected abstract T createConnection(IOSession paramIOSession);
/*     */   
/*     */   protected abstract void onConnected(T paramT);
/*     */   
/*     */   protected abstract void onClosed(T paramT);
/*     */   
/*     */   protected abstract void onException(T paramT, IOException paramIOException);
/*     */   
/*     */   protected abstract void onInputReady(T paramT);
/*     */   
/*     */   protected abstract void onOutputReady(T paramT);
/*     */   
/*     */   protected abstract void onTimeout(T paramT);
/*     */   
/*     */   private void ensureNotNull(T conn) {
/*  61 */     Asserts.notNull(conn, "HTTP connection");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void connected(IOSession session) {
/*  67 */     T conn = (T)session.getAttribute("http.connection");
/*     */     try {
/*  69 */       if (conn == null) {
/*  70 */         conn = createConnection(session);
/*  71 */         session.setAttribute("http.connection", conn);
/*     */       } 
/*  73 */       onConnected(conn);
/*  74 */       SSLIOSession sslioSession = (SSLIOSession)session.getAttribute("http.session.ssl");
/*     */       
/*  76 */       if (sslioSession != null) {
/*     */         try {
/*  78 */           synchronized (sslioSession) {
/*  79 */             if (!sslioSession.isInitialized()) {
/*  80 */               sslioSession.initialize();
/*     */             }
/*     */           } 
/*  83 */         } catch (IOException ex) {
/*  84 */           onException(conn, ex);
/*  85 */           sslioSession.shutdown();
/*     */         } 
/*     */       }
/*  88 */     } catch (RuntimeException ex) {
/*  89 */       session.shutdown();
/*  90 */       throw ex;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void disconnected(IOSession session) {
/*  98 */     T conn = (T)session.getAttribute("http.connection");
/*  99 */     if (conn != null) {
/* 100 */       onClosed(conn);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inputReady(IOSession session) {
/* 108 */     T conn = (T)session.getAttribute("http.connection");
/*     */     try {
/* 110 */       ensureNotNull(conn);
/* 111 */       SSLIOSession sslioSession = (SSLIOSession)session.getAttribute("http.session.ssl");
/*     */       
/* 113 */       if (sslioSession == null) {
/* 114 */         onInputReady(conn);
/*     */       } else {
/*     */         try {
/* 117 */           if (!sslioSession.isInitialized()) {
/* 118 */             sslioSession.initialize();
/*     */           }
/* 120 */           if (sslioSession.isAppInputReady()) {
/* 121 */             onInputReady(conn);
/*     */           }
/* 123 */           sslioSession.inboundTransport();
/* 124 */         } catch (IOException ex) {
/* 125 */           onException(conn, ex);
/* 126 */           sslioSession.shutdown();
/*     */         } 
/*     */       } 
/* 129 */     } catch (RuntimeException ex) {
/* 130 */       session.shutdown();
/* 131 */       throw ex;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void outputReady(IOSession session) {
/* 139 */     T conn = (T)session.getAttribute("http.connection");
/*     */     try {
/* 141 */       ensureNotNull(conn);
/* 142 */       SSLIOSession sslioSession = (SSLIOSession)session.getAttribute("http.session.ssl");
/*     */       
/* 144 */       if (sslioSession == null) {
/* 145 */         onOutputReady(conn);
/*     */       } else {
/*     */         try {
/* 148 */           if (!sslioSession.isInitialized()) {
/* 149 */             sslioSession.initialize();
/*     */           }
/* 151 */           if (sslioSession.isAppOutputReady()) {
/* 152 */             onOutputReady(conn);
/*     */           }
/* 154 */           sslioSession.outboundTransport();
/* 155 */         } catch (IOException ex) {
/* 156 */           onException(conn, ex);
/* 157 */           sslioSession.shutdown();
/*     */         } 
/*     */       } 
/* 160 */     } catch (RuntimeException ex) {
/* 161 */       session.shutdown();
/* 162 */       throw ex;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void timeout(IOSession session) {
/* 170 */     T conn = (T)session.getAttribute("http.connection");
/*     */     try {
/* 172 */       SSLIOSession sslioSession = (SSLIOSession)session.getAttribute("http.session.ssl");
/*     */       
/* 174 */       ensureNotNull(conn);
/* 175 */       onTimeout(conn);
/* 176 */       if (sslioSession != null) {
/* 177 */         synchronized (sslioSession) {
/* 178 */           if (sslioSession.isOutboundDone() && !sslioSession.isInboundDone())
/*     */           {
/* 180 */             sslioSession.shutdown();
/*     */           }
/*     */         } 
/*     */       }
/* 184 */     } catch (RuntimeException ex) {
/* 185 */       session.shutdown();
/* 186 */       throw ex;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/AbstractIODispatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */