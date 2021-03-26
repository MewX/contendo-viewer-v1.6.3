/*     */ package org.apache.http.impl.nio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.nio.channels.WritableByteChannel;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import java.nio.charset.CharsetEncoder;
/*     */ import java.nio.charset.CodingErrorAction;
/*     */ import org.apache.http.ConnectionClosedException;
/*     */ import org.apache.http.Consts;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.HttpConnectionMetrics;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.HttpInetConnection;
/*     */ import org.apache.http.HttpMessage;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.config.MessageConstraints;
/*     */ import org.apache.http.entity.BasicHttpEntity;
/*     */ import org.apache.http.entity.ContentLengthStrategy;
/*     */ import org.apache.http.impl.HttpConnectionMetricsImpl;
/*     */ import org.apache.http.impl.entity.LaxContentLengthStrategy;
/*     */ import org.apache.http.impl.entity.StrictContentLengthStrategy;
/*     */ import org.apache.http.impl.io.HttpTransportMetricsImpl;
/*     */ import org.apache.http.impl.nio.codecs.ChunkDecoder;
/*     */ import org.apache.http.impl.nio.codecs.ChunkEncoder;
/*     */ import org.apache.http.impl.nio.codecs.IdentityDecoder;
/*     */ import org.apache.http.impl.nio.codecs.IdentityEncoder;
/*     */ import org.apache.http.impl.nio.codecs.LengthDelimitedDecoder;
/*     */ import org.apache.http.impl.nio.codecs.LengthDelimitedEncoder;
/*     */ import org.apache.http.impl.nio.reactor.SessionInputBufferImpl;
/*     */ import org.apache.http.impl.nio.reactor.SessionOutputBufferImpl;
/*     */ import org.apache.http.io.HttpTransportMetrics;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.NHttpConnection;
/*     */ import org.apache.http.nio.reactor.IOSession;
/*     */ import org.apache.http.nio.reactor.SessionBufferStatus;
/*     */ import org.apache.http.nio.reactor.SessionInputBuffer;
/*     */ import org.apache.http.nio.reactor.SessionOutputBuffer;
/*     */ import org.apache.http.nio.reactor.SocketAccessor;
/*     */ import org.apache.http.nio.util.ByteBufferAllocator;
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ import org.apache.http.util.Args;
/*     */ import org.apache.http.util.CharsetUtils;
/*     */ import org.apache.http.util.NetUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NHttpConnectionBase
/*     */   implements HttpInetConnection, NHttpConnection, SessionBufferStatus, SocketAccessor
/*     */ {
/*     */   protected final ContentLengthStrategy incomingContentStrategy;
/*     */   protected final ContentLengthStrategy outgoingContentStrategy;
/*     */   protected final SessionInputBufferImpl inbuf;
/*     */   protected final SessionOutputBufferImpl outbuf;
/*     */   private final int fragmentSizeHint;
/*     */   private final MessageConstraints constraints;
/*     */   protected final HttpTransportMetricsImpl inTransportMetrics;
/*     */   protected final HttpTransportMetricsImpl outTransportMetrics;
/*     */   protected final HttpConnectionMetricsImpl connMetrics;
/*     */   protected HttpContext context;
/*     */   protected IOSession session;
/*     */   protected SocketAddress remote;
/*     */   protected volatile ContentDecoder contentDecoder;
/*     */   protected volatile boolean hasBufferedInput;
/*     */   protected volatile ContentEncoder contentEncoder;
/*     */   protected volatile boolean hasBufferedOutput;
/*     */   protected volatile HttpRequest request;
/*     */   protected volatile HttpResponse response;
/*     */   protected volatile int status;
/*     */   
/*     */   @Deprecated
/*     */   public NHttpConnectionBase(IOSession session, ByteBufferAllocator allocator, HttpParams params) {
/* 138 */     Args.notNull(session, "I/O session");
/* 139 */     Args.notNull(params, "HTTP params");
/*     */     
/* 141 */     int bufferSize = params.getIntParameter("http.socket.buffer-size", -1);
/* 142 */     if (bufferSize <= 0) {
/* 143 */       bufferSize = 4096;
/*     */     }
/* 145 */     int lineBufferSize = bufferSize;
/* 146 */     if (lineBufferSize > 512) {
/* 147 */       lineBufferSize = 512;
/*     */     }
/*     */     
/* 150 */     CharsetDecoder decoder = null;
/* 151 */     CharsetEncoder encoder = null;
/* 152 */     Charset charset = CharsetUtils.lookup((String)params.getParameter("http.protocol.element-charset"));
/*     */     
/* 154 */     if (charset != null) {
/* 155 */       charset = Consts.ASCII;
/* 156 */       decoder = charset.newDecoder();
/* 157 */       encoder = charset.newEncoder();
/* 158 */       CodingErrorAction malformedCharAction = (CodingErrorAction)params.getParameter("http.malformed.input.action");
/*     */       
/* 160 */       CodingErrorAction unmappableCharAction = (CodingErrorAction)params.getParameter("http.unmappable.input.action");
/*     */       
/* 162 */       decoder.onMalformedInput(malformedCharAction).onUnmappableCharacter(unmappableCharAction);
/* 163 */       encoder.onMalformedInput(malformedCharAction).onUnmappableCharacter(unmappableCharAction);
/*     */     } 
/* 165 */     this.inbuf = new SessionInputBufferImpl(bufferSize, lineBufferSize, decoder, allocator);
/* 166 */     this.outbuf = new SessionOutputBufferImpl(bufferSize, lineBufferSize, encoder, allocator);
/* 167 */     this.fragmentSizeHint = bufferSize;
/* 168 */     this.constraints = MessageConstraints.DEFAULT;
/*     */     
/* 170 */     this.incomingContentStrategy = createIncomingContentStrategy();
/* 171 */     this.outgoingContentStrategy = createOutgoingContentStrategy();
/*     */     
/* 173 */     this.inTransportMetrics = createTransportMetrics();
/* 174 */     this.outTransportMetrics = createTransportMetrics();
/* 175 */     this.connMetrics = createConnectionMetrics((HttpTransportMetrics)this.inTransportMetrics, (HttpTransportMetrics)this.outTransportMetrics);
/*     */ 
/*     */ 
/*     */     
/* 179 */     setSession(session);
/* 180 */     this.status = 0;
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
/*     */   protected NHttpConnectionBase(IOSession session, int bufferSize, int fragmentSizeHint, ByteBufferAllocator allocator, CharsetDecoder charDecoder, CharsetEncoder charEncoder, MessageConstraints constraints, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy) {
/* 215 */     Args.notNull(session, "I/O session");
/* 216 */     Args.positive(bufferSize, "Buffer size");
/* 217 */     int lineBufferSize = bufferSize;
/* 218 */     if (lineBufferSize > 512) {
/* 219 */       lineBufferSize = 512;
/*     */     }
/* 221 */     this.inbuf = new SessionInputBufferImpl(bufferSize, lineBufferSize, charDecoder, allocator);
/* 222 */     this.outbuf = new SessionOutputBufferImpl(bufferSize, lineBufferSize, charEncoder, allocator);
/* 223 */     this.fragmentSizeHint = (fragmentSizeHint >= 0) ? fragmentSizeHint : bufferSize;
/*     */     
/* 225 */     this.inTransportMetrics = new HttpTransportMetricsImpl();
/* 226 */     this.outTransportMetrics = new HttpTransportMetricsImpl();
/* 227 */     this.connMetrics = new HttpConnectionMetricsImpl((HttpTransportMetrics)this.inTransportMetrics, (HttpTransportMetrics)this.outTransportMetrics);
/* 228 */     this.constraints = (constraints != null) ? constraints : MessageConstraints.DEFAULT;
/* 229 */     this.incomingContentStrategy = (incomingContentStrategy != null) ? incomingContentStrategy : (ContentLengthStrategy)LaxContentLengthStrategy.INSTANCE;
/*     */     
/* 231 */     this.outgoingContentStrategy = (outgoingContentStrategy != null) ? outgoingContentStrategy : (ContentLengthStrategy)StrictContentLengthStrategy.INSTANCE;
/*     */ 
/*     */     
/* 234 */     setSession(session);
/* 235 */     this.status = 0;
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
/*     */   protected NHttpConnectionBase(IOSession session, int bufferSize, int fragmentSizeHint, ByteBufferAllocator allocator, CharsetDecoder charDecoder, CharsetEncoder charEncoder, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy) {
/* 267 */     this(session, bufferSize, fragmentSizeHint, allocator, charDecoder, charEncoder, null, incomingContentStrategy, outgoingContentStrategy);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setSession(IOSession session) {
/* 272 */     this.session = session;
/* 273 */     this.context = new SessionHttpContext(this.session);
/* 274 */     this.session.setBufferStatus(this);
/* 275 */     this.remote = this.session.getRemoteAddress();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void bind(IOSession session) {
/* 285 */     Args.notNull(session, "I/O session");
/* 286 */     setSession(session);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected ContentLengthStrategy createIncomingContentStrategy() {
/* 296 */     return (ContentLengthStrategy)new LaxContentLengthStrategy();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected ContentLengthStrategy createOutgoingContentStrategy() {
/* 306 */     return (ContentLengthStrategy)new StrictContentLengthStrategy();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected HttpTransportMetricsImpl createTransportMetrics() {
/* 316 */     return new HttpTransportMetricsImpl();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected HttpConnectionMetricsImpl createConnectionMetrics(HttpTransportMetrics inTransportMetric, HttpTransportMetrics outTransportMetric) {
/* 328 */     return new HttpConnectionMetricsImpl(inTransportMetric, outTransportMetric);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStatus() {
/* 333 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpContext getContext() {
/* 338 */     return this.context;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpRequest getHttpRequest() {
/* 343 */     return this.request;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpResponse getHttpResponse() {
/* 348 */     return this.response;
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestInput() {
/* 353 */     this.session.setEvent(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestOutput() {
/* 358 */     this.session.setEvent(4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void suspendInput() {
/* 363 */     this.session.clearEvent(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void suspendOutput() {
/* 368 */     synchronized (this.session) {
/* 369 */       if (!this.outbuf.hasData()) {
/* 370 */         this.session.clearEvent(4);
/*     */       }
/*     */     } 
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
/*     */   protected HttpEntity prepareDecoder(HttpMessage message) throws HttpException {
/* 385 */     BasicHttpEntity entity = new BasicHttpEntity();
/* 386 */     long len = this.incomingContentStrategy.determineLength(message);
/* 387 */     this.contentDecoder = createContentDecoder(len, this.session.channel(), (SessionInputBuffer)this.inbuf, this.inTransportMetrics);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 392 */     if (len == -2L) {
/* 393 */       entity.setChunked(true);
/* 394 */       entity.setContentLength(-1L);
/* 395 */     } else if (len == -1L) {
/* 396 */       entity.setChunked(false);
/* 397 */       entity.setContentLength(-1L);
/*     */     } else {
/* 399 */       entity.setChunked(false);
/* 400 */       entity.setContentLength(len);
/*     */     } 
/*     */     
/* 403 */     Header contentTypeHeader = message.getFirstHeader("Content-Type");
/* 404 */     if (contentTypeHeader != null) {
/* 405 */       entity.setContentType(contentTypeHeader);
/*     */     }
/* 407 */     Header contentEncodingHeader = message.getFirstHeader("Content-Encoding");
/* 408 */     if (contentEncodingHeader != null) {
/* 409 */       entity.setContentEncoding(contentEncodingHeader);
/*     */     }
/* 411 */     return (HttpEntity)entity;
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
/*     */   protected ContentDecoder createContentDecoder(long len, ReadableByteChannel channel, SessionInputBuffer buffer, HttpTransportMetricsImpl metrics) {
/* 432 */     if (len == -2L)
/* 433 */       return (ContentDecoder)new ChunkDecoder(channel, buffer, this.constraints, metrics); 
/* 434 */     if (len == -1L) {
/* 435 */       return (ContentDecoder)new IdentityDecoder(channel, buffer, metrics);
/*     */     }
/* 437 */     return (ContentDecoder)new LengthDelimitedDecoder(channel, buffer, metrics, len);
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
/*     */   protected void prepareEncoder(HttpMessage message) throws HttpException {
/* 449 */     long len = this.outgoingContentStrategy.determineLength(message);
/* 450 */     this.contentEncoder = createContentEncoder(len, this.session.channel(), (SessionOutputBuffer)this.outbuf, this.outTransportMetrics);
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
/*     */   protected ContentEncoder createContentEncoder(long len, WritableByteChannel channel, SessionOutputBuffer buffer, HttpTransportMetricsImpl metrics) {
/* 475 */     if (len == -2L)
/* 476 */       return (ContentEncoder)new ChunkEncoder(channel, buffer, metrics, this.fragmentSizeHint); 
/* 477 */     if (len == -1L) {
/* 478 */       return (ContentEncoder)new IdentityEncoder(channel, buffer, metrics, this.fragmentSizeHint);
/*     */     }
/* 480 */     return (ContentEncoder)new LengthDelimitedEncoder(channel, buffer, metrics, len, this.fragmentSizeHint);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasBufferedInput() {
/* 486 */     return this.hasBufferedInput;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasBufferedOutput() {
/* 491 */     return this.hasBufferedOutput;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void assertNotClosed() throws ConnectionClosedException {
/* 501 */     if (this.status != 0) {
/* 502 */       throw new ConnectionClosedException();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 508 */     if (this.status != 0) {
/*     */       return;
/*     */     }
/* 511 */     this.status = 1;
/* 512 */     if (this.outbuf.hasData()) {
/* 513 */       this.session.setEvent(4);
/*     */     } else {
/* 515 */       this.session.close();
/* 516 */       this.status = 2;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpen() {
/* 522 */     return (this.status == 0 && !this.session.isClosed());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStale() {
/* 527 */     return this.session.isClosed();
/*     */   }
/*     */ 
/*     */   
/*     */   public InetAddress getLocalAddress() {
/* 532 */     SocketAddress address = this.session.getLocalAddress();
/* 533 */     return (address instanceof InetSocketAddress) ? ((InetSocketAddress)address).getAddress() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLocalPort() {
/* 538 */     SocketAddress address = this.session.getLocalAddress();
/* 539 */     return (address instanceof InetSocketAddress) ? ((InetSocketAddress)address).getPort() : -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public InetAddress getRemoteAddress() {
/* 544 */     SocketAddress address = this.session.getRemoteAddress();
/* 545 */     return (address instanceof InetSocketAddress) ? ((InetSocketAddress)address).getAddress() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRemotePort() {
/* 550 */     SocketAddress address = this.session.getRemoteAddress();
/* 551 */     return (address instanceof InetSocketAddress) ? ((InetSocketAddress)address).getPort() : -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSocketTimeout(int timeout) {
/* 556 */     this.session.setSocketTimeout(timeout);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSocketTimeout() {
/* 561 */     return this.session.getSocketTimeout();
/*     */   }
/*     */ 
/*     */   
/*     */   public void shutdown() throws IOException {
/* 566 */     this.status = 2;
/* 567 */     this.session.shutdown();
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpConnectionMetrics getMetrics() {
/* 572 */     return (HttpConnectionMetrics)this.connMetrics;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 577 */     SocketAddress remoteAddress = this.session.getRemoteAddress();
/* 578 */     SocketAddress localAddress = this.session.getLocalAddress();
/* 579 */     if (remoteAddress != null && localAddress != null) {
/* 580 */       StringBuilder buffer = new StringBuilder();
/* 581 */       NetUtils.formatAddress(buffer, localAddress);
/* 582 */       buffer.append("<->");
/* 583 */       NetUtils.formatAddress(buffer, remoteAddress);
/* 584 */       return buffer.toString();
/*     */     } 
/* 586 */     return "[Not bound]";
/*     */   }
/*     */ 
/*     */   
/*     */   public Socket getSocket() {
/* 591 */     return (this.session instanceof SocketAccessor) ? ((SocketAccessor)this.session).getSocket() : null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/NHttpConnectionBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */