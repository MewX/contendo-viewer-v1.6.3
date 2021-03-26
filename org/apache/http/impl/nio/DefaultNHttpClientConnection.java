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
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.HttpResponseFactory;
/*     */ import org.apache.http.config.MessageConstraints;
/*     */ import org.apache.http.entity.ContentLengthStrategy;
/*     */ import org.apache.http.impl.nio.codecs.DefaultHttpRequestWriter;
/*     */ import org.apache.http.impl.nio.codecs.DefaultHttpRequestWriterFactory;
/*     */ import org.apache.http.impl.nio.codecs.DefaultHttpResponseParser;
/*     */ import org.apache.http.impl.nio.codecs.DefaultHttpResponseParserFactory;
/*     */ import org.apache.http.nio.NHttpClientConnection;
/*     */ import org.apache.http.nio.NHttpClientEventHandler;
/*     */ import org.apache.http.nio.NHttpClientHandler;
/*     */ import org.apache.http.nio.NHttpClientIOTarget;
/*     */ import org.apache.http.nio.NHttpMessageParser;
/*     */ import org.apache.http.nio.NHttpMessageParserFactory;
/*     */ import org.apache.http.nio.NHttpMessageWriter;
/*     */ import org.apache.http.nio.NHttpMessageWriterFactory;
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
/*     */ public class DefaultNHttpClientConnection
/*     */   extends NHttpConnectionBase
/*     */   implements NHttpClientIOTarget
/*     */ {
/*     */   protected final NHttpMessageParser<HttpResponse> responseParser;
/*     */   protected final NHttpMessageWriter<HttpRequest> requestWriter;
/*     */   
/*     */   @Deprecated
/*     */   public DefaultNHttpClientConnection(IOSession session, HttpResponseFactory responseFactory, ByteBufferAllocator allocator, HttpParams params) {
/*  95 */     super(session, allocator, params);
/*  96 */     Args.notNull(responseFactory, "Response factory");
/*  97 */     this.responseParser = createResponseParser((SessionInputBuffer)this.inbuf, responseFactory, params);
/*  98 */     this.requestWriter = createRequestWriter((SessionOutputBuffer)this.outbuf, params);
/*  99 */     this.hasBufferedInput = false;
/* 100 */     this.hasBufferedOutput = false;
/* 101 */     this.session.setBufferStatus(this);
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
/*     */   public DefaultNHttpClientConnection(IOSession session, int bufferSize, int fragmentSizeHint, ByteBufferAllocator allocator, CharsetDecoder charDecoder, CharsetEncoder charEncoder, MessageConstraints constraints, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy, NHttpMessageWriterFactory<HttpRequest> requestWriterFactory, NHttpMessageParserFactory<HttpResponse> responseParserFactory) {
/* 138 */     super(session, bufferSize, fragmentSizeHint, allocator, charDecoder, charEncoder, constraints, incomingContentStrategy, outgoingContentStrategy);
/*     */     
/* 140 */     this.requestWriter = ((requestWriterFactory != null) ? requestWriterFactory : DefaultHttpRequestWriterFactory.INSTANCE).create((SessionOutputBuffer)this.outbuf);
/*     */     
/* 142 */     this.responseParser = ((responseParserFactory != null) ? responseParserFactory : DefaultHttpResponseParserFactory.INSTANCE).create((SessionInputBuffer)this.inbuf, constraints);
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
/*     */   public DefaultNHttpClientConnection(IOSession session, int bufferSize, CharsetDecoder charDecoder, CharsetEncoder charEncoder, MessageConstraints constraints) {
/* 155 */     this(session, bufferSize, bufferSize, (ByteBufferAllocator)null, charDecoder, charEncoder, constraints, (ContentLengthStrategy)null, (ContentLengthStrategy)null, (NHttpMessageWriterFactory<HttpRequest>)null, (NHttpMessageParserFactory<HttpResponse>)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultNHttpClientConnection(IOSession session, int bufferSize) {
/* 163 */     this(session, bufferSize, bufferSize, (ByteBufferAllocator)null, (CharsetDecoder)null, (CharsetEncoder)null, (MessageConstraints)null, (ContentLengthStrategy)null, (ContentLengthStrategy)null, (NHttpMessageWriterFactory<HttpRequest>)null, (NHttpMessageParserFactory<HttpResponse>)null);
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
/*     */   protected NHttpMessageParser<HttpResponse> createResponseParser(SessionInputBuffer buffer, HttpResponseFactory responseFactory, HttpParams params) {
/* 183 */     MessageConstraints constraints = HttpParamConfig.getMessageConstraints(params);
/* 184 */     return (NHttpMessageParser<HttpResponse>)new DefaultHttpResponseParser(buffer, null, responseFactory, constraints);
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
/*     */   protected NHttpMessageWriter<HttpRequest> createRequestWriter(SessionOutputBuffer buffer, HttpParams params) {
/* 203 */     return (NHttpMessageWriter<HttpRequest>)new DefaultHttpRequestWriter(buffer, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onResponseReceived(HttpResponse response) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onRequestSubmitted(HttpRequest request) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetInput() {
/* 220 */     this.response = null;
/* 221 */     this.contentDecoder = null;
/* 222 */     this.responseParser.reset();
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetOutput() {
/* 227 */     this.request = null;
/* 228 */     this.contentEncoder = null;
/* 229 */     this.requestWriter.reset();
/*     */   }
/*     */   
/*     */   public void consumeInput(NHttpClientEventHandler handler) {
/* 233 */     if (this.status != 0) {
/* 234 */       this.session.clearEvent(1);
/*     */       return;
/*     */     } 
/*     */     try {
/* 238 */       if (this.response == null) {
/*     */         int bytesRead;
/*     */         do {
/* 241 */           bytesRead = this.responseParser.fillBuffer(this.session.channel());
/* 242 */           if (bytesRead > 0) {
/* 243 */             this.inTransportMetrics.incrementBytesTransferred(bytesRead);
/*     */           }
/* 245 */           this.response = (HttpResponse)this.responseParser.parse();
/* 246 */         } while (bytesRead > 0 && this.response == null);
/* 247 */         if (this.response != null) {
/* 248 */           if (this.response.getStatusLine().getStatusCode() >= 200) {
/* 249 */             HttpEntity entity = prepareDecoder((HttpMessage)this.response);
/* 250 */             this.response.setEntity(entity);
/* 251 */             this.connMetrics.incrementResponseCount();
/*     */           } 
/* 253 */           this.hasBufferedInput = this.inbuf.hasData();
/* 254 */           onResponseReceived(this.response);
/* 255 */           handler.responseReceived((NHttpClientConnection)this);
/* 256 */           if (this.contentDecoder == null) {
/* 257 */             resetInput();
/*     */           }
/*     */         } 
/* 260 */         if (bytesRead == -1 && !this.inbuf.hasData()) {
/* 261 */           handler.endOfInput((NHttpClientConnection)this);
/*     */         }
/*     */       } 
/* 264 */       if (this.contentDecoder != null && (this.session.getEventMask() & 0x1) > 0) {
/* 265 */         handler.inputReady((NHttpClientConnection)this, this.contentDecoder);
/* 266 */         if (this.contentDecoder.isCompleted())
/*     */         {
/*     */           
/* 269 */           resetInput();
/*     */         }
/*     */       } 
/* 272 */     } catch (HttpException ex) {
/* 273 */       resetInput();
/* 274 */       handler.exception((NHttpClientConnection)this, (Exception)ex);
/* 275 */     } catch (Exception ex) {
/* 276 */       handler.exception((NHttpClientConnection)this, ex);
/*     */     } finally {
/*     */       
/* 279 */       this.hasBufferedInput = this.inbuf.hasData();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void produceOutput(NHttpClientEventHandler handler) {
/*     */     try {
/* 285 */       if (this.status == 0) {
/* 286 */         if (this.contentEncoder == null && !this.outbuf.hasData()) {
/* 287 */           handler.requestReady((NHttpClientConnection)this);
/*     */         }
/* 289 */         if (this.contentEncoder != null) {
/* 290 */           handler.outputReady((NHttpClientConnection)this, this.contentEncoder);
/* 291 */           if (this.contentEncoder.isCompleted()) {
/* 292 */             resetOutput();
/*     */           }
/*     */         } 
/*     */       } 
/* 296 */       if (this.outbuf.hasData()) {
/* 297 */         int bytesWritten = this.outbuf.flush(this.session.channel());
/* 298 */         if (bytesWritten > 0) {
/* 299 */           this.outTransportMetrics.incrementBytesTransferred(bytesWritten);
/*     */         }
/*     */       } 
/* 302 */       if (!this.outbuf.hasData() && 
/* 303 */         this.status == 1) {
/* 304 */         this.session.close();
/* 305 */         this.status = 2;
/* 306 */         resetOutput();
/*     */       }
/*     */     
/* 309 */     } catch (Exception ex) {
/* 310 */       handler.exception((NHttpClientConnection)this, ex);
/*     */     } finally {
/*     */       
/* 313 */       this.hasBufferedOutput = this.outbuf.hasData();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void submitRequest(HttpRequest request) throws IOException, HttpException {
/* 319 */     Args.notNull(request, "HTTP request");
/* 320 */     assertNotClosed();
/* 321 */     if (this.request != null) {
/* 322 */       throw new HttpException("Request already submitted");
/*     */     }
/* 324 */     onRequestSubmitted(request);
/* 325 */     this.requestWriter.write((HttpMessage)request);
/* 326 */     this.hasBufferedOutput = this.outbuf.hasData();
/*     */     
/* 328 */     if (request instanceof HttpEntityEnclosingRequest && ((HttpEntityEnclosingRequest)request).getEntity() != null) {
/*     */       
/* 330 */       prepareEncoder((HttpMessage)request);
/* 331 */       this.request = request;
/*     */     } 
/* 333 */     this.connMetrics.incrementRequestCount();
/* 334 */     this.session.setEvent(4);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRequestSubmitted() {
/* 339 */     return (this.request != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void consumeInput(NHttpClientHandler handler) {
/* 344 */     consumeInput(new NHttpClientEventHandlerAdaptor(handler));
/*     */   }
/*     */ 
/*     */   
/*     */   public void produceOutput(NHttpClientHandler handler) {
/* 349 */     produceOutput(new NHttpClientEventHandlerAdaptor(handler));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/DefaultNHttpClientConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */