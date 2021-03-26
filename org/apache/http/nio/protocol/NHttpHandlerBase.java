/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.http.ConnectionReuseStrategy;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.nio.NHttpConnection;
/*     */ import org.apache.http.nio.util.ByteBufferAllocator;
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.protocol.HttpProcessor;
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
/*     */ @Deprecated
/*     */ public abstract class NHttpHandlerBase
/*     */ {
/*     */   protected static final String CONN_STATE = "http.nio.conn-state";
/*     */   protected final HttpProcessor httpProcessor;
/*     */   protected final ConnectionReuseStrategy connStrategy;
/*     */   protected final ByteBufferAllocator allocator;
/*     */   protected final HttpParams params;
/*     */   protected EventListener eventListener;
/*     */   
/*     */   public NHttpHandlerBase(HttpProcessor httpProcessor, ConnectionReuseStrategy connStrategy, ByteBufferAllocator allocator, HttpParams params) {
/*  65 */     Args.notNull(httpProcessor, "HTTP processor");
/*  66 */     Args.notNull(connStrategy, "Connection reuse strategy");
/*  67 */     Args.notNull(allocator, "ByteBuffer allocator");
/*  68 */     Args.notNull(params, "HTTP parameters");
/*  69 */     this.httpProcessor = httpProcessor;
/*  70 */     this.connStrategy = connStrategy;
/*  71 */     this.allocator = allocator;
/*  72 */     this.params = params;
/*     */   }
/*     */   
/*     */   public HttpParams getParams() {
/*  76 */     return this.params;
/*     */   }
/*     */   
/*     */   public void setEventListener(EventListener eventListener) {
/*  80 */     this.eventListener = eventListener;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void closeConnection(NHttpConnection conn, Throwable cause) {
/*     */     try {
/*  86 */       conn.close();
/*  87 */     } catch (IOException ex) {
/*     */       
/*     */       try {
/*  90 */         conn.shutdown();
/*  91 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void shutdownConnection(NHttpConnection conn, Throwable cause) {
/*     */     try {
/*  98 */       conn.shutdown();
/*  99 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */   
/*     */   protected void handleTimeout(NHttpConnection conn) {
/*     */     try {
/* 105 */       if (conn.getStatus() == 0) {
/* 106 */         conn.close();
/* 107 */         if (conn.getStatus() == 1)
/*     */         {
/*     */           
/* 110 */           conn.setSocketTimeout(250);
/*     */         }
/* 112 */         if (this.eventListener != null) {
/* 113 */           this.eventListener.connectionTimeout(conn);
/*     */         }
/*     */       } else {
/* 116 */         conn.shutdown();
/*     */       } 
/* 118 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canResponseHaveBody(HttpRequest request, HttpResponse response) {
/* 125 */     if (request != null && "HEAD".equalsIgnoreCase(request.getRequestLine().getMethod())) {
/* 126 */       return false;
/*     */     }
/*     */     
/* 129 */     int status = response.getStatusLine().getStatusCode();
/* 130 */     return (status >= 200 && status != 204 && status != 304 && status != 205);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/NHttpHandlerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */