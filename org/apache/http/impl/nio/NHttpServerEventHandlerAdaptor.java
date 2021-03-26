/*     */ package org.apache.http.impl.nio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.NHttpServerConnection;
/*     */ import org.apache.http.nio.NHttpServerEventHandler;
/*     */ import org.apache.http.nio.NHttpServiceHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ class NHttpServerEventHandlerAdaptor
/*     */   implements NHttpServerEventHandler
/*     */ {
/*     */   private final NHttpServiceHandler handler;
/*     */   
/*     */   public NHttpServerEventHandlerAdaptor(NHttpServiceHandler handler) {
/*  49 */     this.handler = handler;
/*     */   }
/*     */ 
/*     */   
/*     */   public void connected(NHttpServerConnection conn) {
/*  54 */     this.handler.connected(conn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void responseReady(NHttpServerConnection conn) throws IOException, HttpException {
/*  60 */     this.handler.responseReady(conn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void requestReceived(NHttpServerConnection conn) throws IOException, HttpException {
/*  66 */     this.handler.requestReceived(conn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inputReady(NHttpServerConnection conn, ContentDecoder decoder) throws IOException, HttpException {
/*  73 */     this.handler.inputReady(conn, decoder);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void outputReady(NHttpServerConnection conn, ContentEncoder encoder) throws IOException, HttpException {
/*  80 */     this.handler.outputReady(conn, encoder);
/*     */   }
/*     */ 
/*     */   
/*     */   public void exception(NHttpServerConnection conn, Exception ex) {
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
/*     */   public void endOfInput(NHttpServerConnection conn) throws IOException {
/*  99 */     conn.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public void timeout(NHttpServerConnection conn) {
/* 104 */     this.handler.timeout(conn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void closed(NHttpServerConnection conn) {
/* 109 */     this.handler.closed(conn);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/NHttpServerEventHandlerAdaptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */