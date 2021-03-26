/*     */ package org.apache.http.impl.nio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import java.nio.charset.CharsetEncoder;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpEntityEnclosingRequest;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.HttpMessage;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpRequestFactory;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.config.MessageConstraints;
/*     */ import org.apache.http.entity.ContentLengthStrategy;
/*     */ import org.apache.http.impl.entity.DisallowIdentityContentLengthStrategy;
/*     */ import org.apache.http.impl.entity.LaxContentLengthStrategy;
/*     */ import org.apache.http.impl.entity.StrictContentLengthStrategy;
/*     */ import org.apache.http.impl.nio.codecs.DefaultHttpRequestParser;
/*     */ import org.apache.http.impl.nio.codecs.DefaultHttpRequestParserFactory;
/*     */ import org.apache.http.impl.nio.codecs.DefaultHttpResponseWriter;
/*     */ import org.apache.http.impl.nio.codecs.DefaultHttpResponseWriterFactory;
/*     */ import org.apache.http.nio.NHttpMessageParser;
/*     */ import org.apache.http.nio.NHttpMessageParserFactory;
/*     */ import org.apache.http.nio.NHttpMessageWriter;
/*     */ import org.apache.http.nio.NHttpMessageWriterFactory;
/*     */ import org.apache.http.nio.NHttpServerConnection;
/*     */ import org.apache.http.nio.NHttpServerEventHandler;
/*     */ import org.apache.http.nio.NHttpServerIOTarget;
/*     */ import org.apache.http.nio.NHttpServiceHandler;
/*     */ import org.apache.http.nio.reactor.IOSession;
/*     */ import org.apache.http.nio.reactor.SessionInputBuffer;
/*     */ import org.apache.http.nio.reactor.SessionOutputBuffer;
/*     */ import org.apache.http.nio.util.ByteBufferAllocator;
/*     */ import org.apache.http.params.HttpParamConfig;
/*     */ import org.apache.http.params.HttpParams;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultNHttpServerConnection
/*     */   extends NHttpConnectionBase
/*     */   implements NHttpServerIOTarget
/*     */ {
/*     */   protected final NHttpMessageParser<HttpRequest> requestParser;
/*     */   protected final NHttpMessageWriter<HttpResponse> responseWriter;
/*     */   
/*     */   @Deprecated
/*     */   public DefaultNHttpServerConnection(IOSession session, HttpRequestFactory requestFactory, ByteBufferAllocator allocator, HttpParams params) {
/*  98 */     super(session, allocator, params);
/*  99 */     Args.notNull(requestFactory, "Request factory");
/* 100 */     this.requestParser = createRequestParser((SessionInputBuffer)this.inbuf, requestFactory, params);
/* 101 */     this.responseWriter = createResponseWriter((SessionOutputBuffer)this.outbuf, params);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultNHttpServerConnection(IOSession session, int bufferSize, int fragmentSizeHint, ByteBufferAllocator allocator, CharsetDecoder charDecoder, CharsetEncoder charEncoder, MessageConstraints constraints, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy, NHttpMessageParserFactory<HttpRequest> requestParserFactory, NHttpMessageWriterFactory<HttpResponse> responseWriterFactory) {
/* 142 */     super(session, bufferSize, fragmentSizeHint, allocator, charDecoder, charEncoder, constraints, (incomingContentStrategy != null) ? incomingContentStrategy : (ContentLengthStrategy)DisallowIdentityContentLengthStrategy.INSTANCE, (outgoingContentStrategy != null) ? outgoingContentStrategy : (ContentLengthStrategy)StrictContentLengthStrategy.INSTANCE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     this.requestParser = ((requestParserFactory != null) ? requestParserFactory : DefaultHttpRequestParserFactory.INSTANCE).create((SessionInputBuffer)this.inbuf, constraints);
/*     */     
/* 150 */     this.responseWriter = ((responseWriterFactory != null) ? responseWriterFactory : DefaultHttpResponseWriterFactory.INSTANCE).create((SessionOutputBuffer)this.outbuf);
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
/*     */   public DefaultNHttpServerConnection(IOSession session, int bufferSize, CharsetDecoder charDecoder, CharsetEncoder charEncoder, MessageConstraints constraints) {
/* 163 */     this(session, bufferSize, bufferSize, (ByteBufferAllocator)null, charDecoder, charEncoder, constraints, (ContentLengthStrategy)null, (ContentLengthStrategy)null, (NHttpMessageParserFactory<HttpRequest>)null, (NHttpMessageWriterFactory<HttpResponse>)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultNHttpServerConnection(IOSession session, int bufferSize) {
/* 171 */     this(session, bufferSize, bufferSize, (ByteBufferAllocator)null, (CharsetDecoder)null, (CharsetEncoder)null, (MessageConstraints)null, (ContentLengthStrategy)null, (ContentLengthStrategy)null, (NHttpMessageParserFactory<HttpRequest>)null, (NHttpMessageWriterFactory<HttpResponse>)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected ContentLengthStrategy createIncomingContentStrategy() {
/* 180 */     return (ContentLengthStrategy)new DisallowIdentityContentLengthStrategy((ContentLengthStrategy)new LaxContentLengthStrategy(0));
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
/*     */   @Deprecated
/*     */   protected NHttpMessageParser<HttpRequest> createRequestParser(SessionInputBuffer buffer, HttpRequestFactory requestFactory, HttpParams params) {
/* 199 */     MessageConstraints constraints = HttpParamConfig.getMessageConstraints(params);
/* 200 */     return (NHttpMessageParser<HttpRequest>)new DefaultHttpRequestParser(buffer, null, requestFactory, constraints);
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
/*     */   @Deprecated
/*     */   protected NHttpMessageWriter<HttpResponse> createResponseWriter(SessionOutputBuffer buffer, HttpParams params) {
/* 220 */     return (NHttpMessageWriter<HttpResponse>)new DefaultHttpResponseWriter(buffer, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onRequestReceived(HttpRequest request) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onResponseSubmitted(HttpResponse response) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetInput() {
/* 237 */     this.request = null;
/* 238 */     this.contentDecoder = null;
/* 239 */     this.requestParser.reset();
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetOutput() {
/* 244 */     this.response = null;
/* 245 */     this.contentEncoder = null;
/* 246 */     this.responseWriter.reset();
/*     */   }
/*     */   
/*     */   public void consumeInput(NHttpServerEventHandler handler) {
/* 250 */     if (this.status != 0) {
/* 251 */       this.session.clearEvent(1);
/*     */       return;
/*     */     } 
/*     */     try {
/* 255 */       if (this.request == null) {
/*     */         int bytesRead;
/*     */         do {
/* 258 */           bytesRead = this.requestParser.fillBuffer(this.session.channel());
/* 259 */           if (bytesRead > 0) {
/* 260 */             this.inTransportMetrics.incrementBytesTransferred(bytesRead);
/*     */           }
/* 262 */           this.request = (HttpRequest)this.requestParser.parse();
/* 263 */         } while (bytesRead > 0 && this.request == null);
/* 264 */         if (this.request != null) {
/* 265 */           if (this.request instanceof HttpEntityEnclosingRequest) {
/*     */             
/* 267 */             HttpEntity entity = prepareDecoder((HttpMessage)this.request);
/* 268 */             ((HttpEntityEnclosingRequest)this.request).setEntity(entity);
/*     */           } 
/* 270 */           this.connMetrics.incrementRequestCount();
/* 271 */           this.hasBufferedInput = this.inbuf.hasData();
/* 272 */           onRequestReceived(this.request);
/* 273 */           handler.requestReceived((NHttpServerConnection)this);
/* 274 */           if (this.contentDecoder == null)
/*     */           {
/*     */             
/* 277 */             resetInput();
/*     */           }
/*     */         } 
/* 280 */         if (bytesRead == -1 && !this.inbuf.hasData()) {
/* 281 */           handler.endOfInput((NHttpServerConnection)this);
/*     */         }
/*     */       } 
/* 284 */       if (this.contentDecoder != null && (this.session.getEventMask() & 0x1) > 0) {
/* 285 */         handler.inputReady((NHttpServerConnection)this, this.contentDecoder);
/* 286 */         if (this.contentDecoder.isCompleted())
/*     */         {
/*     */           
/* 289 */           resetInput();
/*     */         }
/*     */       } 
/* 292 */     } catch (HttpException ex) {
/* 293 */       resetInput();
/* 294 */       handler.exception((NHttpServerConnection)this, (Exception)ex);
/* 295 */     } catch (Exception ex) {
/* 296 */       handler.exception((NHttpServerConnection)this, ex);
/*     */     } finally {
/*     */       
/* 299 */       this.hasBufferedInput = this.inbuf.hasData();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void produceOutput(NHttpServerEventHandler handler) {
/*     */     try {
/* 305 */       if (this.status == 0) {
/* 306 */         if (this.contentEncoder == null && !this.outbuf.hasData()) {
/* 307 */           handler.responseReady((NHttpServerConnection)this);
/*     */         }
/* 309 */         if (this.contentEncoder != null) {
/* 310 */           handler.outputReady((NHttpServerConnection)this, this.contentEncoder);
/* 311 */           if (this.contentEncoder.isCompleted()) {
/* 312 */             resetOutput();
/*     */           }
/*     */         } 
/*     */       } 
/* 316 */       if (this.outbuf.hasData()) {
/* 317 */         int bytesWritten = this.outbuf.flush(this.session.channel());
/* 318 */         if (bytesWritten > 0) {
/* 319 */           this.outTransportMetrics.incrementBytesTransferred(bytesWritten);
/*     */         }
/*     */       } 
/* 322 */       if (!this.outbuf.hasData() && 
/* 323 */         this.status == 1) {
/* 324 */         this.session.close();
/* 325 */         this.status = 2;
/* 326 */         resetOutput();
/*     */       }
/*     */     
/* 329 */     } catch (Exception ex) {
/* 330 */       handler.exception((NHttpServerConnection)this, ex);
/*     */     } finally {
/*     */       
/* 333 */       this.hasBufferedOutput = this.outbuf.hasData();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void submitResponse(HttpResponse response) throws IOException, HttpException {
/* 339 */     Args.notNull(response, "HTTP response");
/* 340 */     assertNotClosed();
/* 341 */     if (this.response != null) {
/* 342 */       throw new HttpException("Response already submitted");
/*     */     }
/* 344 */     onResponseSubmitted(response);
/* 345 */     this.responseWriter.write((HttpMessage)response);
/* 346 */     this.hasBufferedOutput = this.outbuf.hasData();
/*     */     
/* 348 */     if (response.getStatusLine().getStatusCode() >= 200) {
/* 349 */       this.connMetrics.incrementResponseCount();
/* 350 */       if (response.getEntity() != null) {
/* 351 */         this.response = response;
/* 352 */         prepareEncoder((HttpMessage)response);
/*     */       } 
/*     */     } 
/*     */     
/* 356 */     this.session.setEvent(4);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isResponseSubmitted() {
/* 361 */     return (this.response != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void consumeInput(NHttpServiceHandler handler) {
/* 366 */     consumeInput(new NHttpServerEventHandlerAdaptor(handler));
/*     */   }
/*     */ 
/*     */   
/*     */   public void produceOutput(NHttpServiceHandler handler) {
/* 371 */     produceOutput(new NHttpServerEventHandlerAdaptor(handler));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/DefaultNHttpServerConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */