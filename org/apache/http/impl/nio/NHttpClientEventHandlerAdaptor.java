/*     */ package org.apache.http.impl.nio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.NHttpClientConnection;
/*     */ import org.apache.http.nio.NHttpClientEventHandler;
/*     */ import org.apache.http.nio.NHttpClientHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ class NHttpClientEventHandlerAdaptor
/*     */   implements NHttpClientEventHandler
/*     */ {
/*     */   private final NHttpClientHandler handler;
/*     */   
/*     */   public NHttpClientEventHandlerAdaptor(NHttpClientHandler handler) {
/*  49 */     this.handler = handler;
/*     */   }
/*     */ 
/*     */   
/*     */   public void connected(NHttpClientConnection conn, Object attachment) {
/*  54 */     this.handler.connected(conn, attachment);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void requestReady(NHttpClientConnection conn) throws IOException, HttpException {
/*  60 */     this.handler.requestReady(conn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void responseReceived(NHttpClientConnection conn) throws IOException, HttpException {
/*  66 */     this.handler.responseReceived(conn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inputReady(NHttpClientConnection conn, ContentDecoder decoder) throws IOException, HttpException {
/*  73 */     this.handler.inputReady(conn, decoder);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void outputReady(NHttpClientConnection conn, ContentEncoder encoder) throws IOException, HttpException {
/*  80 */     this.handler.outputReady(conn, encoder);
/*     */   }
/*     */ 
/*     */   
/*     */   public void exception(NHttpClientConnection conn, Exception ex) {
/*  85 */     if (ex instanceof HttpException) {
/*  86 */       this.handler.exception(conn, (HttpException)ex);
/*  87 */     } else if (ex instanceof IOException) {
/*  88 */       this.handler.exception(conn, (IOException)ex);
/*     */     } else {
/*  90 */       if (ex instanceof RuntimeException) {
/*  91 */         throw (RuntimeException)ex;
/*     */       }
/*  93 */       throw new Error("Unexpected exception: ", ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void endOfInput(NHttpClientConnection conn) throws IOException {
/*  99 */     conn.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public void timeout(NHttpClientConnection conn) {
/* 104 */     this.handler.timeout(conn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void closed(NHttpClientConnection conn) {
/* 109 */     this.handler.closed(conn);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/NHttpClientEventHandlerAdaptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */