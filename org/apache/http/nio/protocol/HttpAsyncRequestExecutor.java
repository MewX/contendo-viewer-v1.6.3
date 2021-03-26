/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.SocketTimeoutException;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import org.apache.http.ConnectionClosedException;
/*     */ import org.apache.http.ExceptionLogger;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpEntityEnclosingRequest;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.HttpVersion;
/*     */ import org.apache.http.ProtocolException;
/*     */ import org.apache.http.ProtocolVersion;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.IOControl;
/*     */ import org.apache.http.nio.NHttpClientConnection;
/*     */ import org.apache.http.nio.NHttpClientEventHandler;
/*     */ import org.apache.http.nio.NHttpConnection;
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ import org.apache.http.util.Args;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ public class HttpAsyncRequestExecutor
/*     */   implements NHttpClientEventHandler
/*     */ {
/*     */   public static final int DEFAULT_WAIT_FOR_CONTINUE = 3000;
/*     */   public static final String HTTP_HANDLER = "http.nio.exchange-handler";
/*     */   private final int waitForContinue;
/*     */   private final ExceptionLogger exceptionLogger;
/*     */   static final String HTTP_EXCHANGE_STATE = "http.nio.http-exchange-state";
/*     */   
/*     */   public HttpAsyncRequestExecutor(int waitForContinue, ExceptionLogger exceptionLogger) {
/* 106 */     this.waitForContinue = Args.positive(waitForContinue, "Wait for continue time");
/* 107 */     this.exceptionLogger = (exceptionLogger != null) ? exceptionLogger : ExceptionLogger.NO_OP;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HttpAsyncRequestExecutor(int waitForContinue) {
/* 116 */     this(waitForContinue, null);
/*     */   }
/*     */   
/*     */   public HttpAsyncRequestExecutor() {
/* 120 */     this(3000, null);
/*     */   }
/*     */   
/*     */   private static boolean pipelining(HttpAsyncClientExchangeHandler handler) {
/* 124 */     return (handler.getClass().getAnnotation(Pipelined.class) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void connected(NHttpClientConnection conn, Object attachment) throws IOException, HttpException {
/* 131 */     State state = new State();
/* 132 */     HttpContext context = conn.getContext();
/* 133 */     context.setAttribute("http.nio.http-exchange-state", state);
/* 134 */     requestReady(conn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void closed(NHttpClientConnection conn) {
/* 139 */     HttpAsyncClientExchangeHandler handler = getHandler((NHttpConnection)conn);
/* 140 */     if (handler == null) {
/*     */       return;
/*     */     }
/* 143 */     State state = getState((NHttpConnection)conn);
/* 144 */     if (state != null && (
/* 145 */       state.getRequestState() != MessageState.READY || state.getResponseState() != MessageState.READY)) {
/* 146 */       handler.failed((Exception)new ConnectionClosedException("Connection closed unexpectedly"));
/*     */     }
/*     */     
/* 149 */     if (!handler.isDone() && pipelining(handler)) {
/* 150 */       handler.failed((Exception)new ConnectionClosedException("Connection closed unexpectedly"));
/*     */     }
/* 152 */     if (state == null || handler.isDone()) {
/* 153 */       closeHandler(handler);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void exception(NHttpClientConnection conn, Exception cause) {
/* 160 */     shutdownConnection((NHttpConnection)conn);
/* 161 */     HttpAsyncClientExchangeHandler handler = getHandler((NHttpConnection)conn);
/* 162 */     if (handler != null) {
/* 163 */       handler.failed(cause);
/*     */     } else {
/* 165 */       log(cause);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestReady(NHttpClientConnection conn) throws IOException, HttpException {
/*     */     HttpAsyncClientExchangeHandler handler;
/* 172 */     State state = getState((NHttpConnection)conn);
/* 173 */     Asserts.notNull(state, "Connection state");
/* 174 */     Asserts.check((state.getRequestState() == MessageState.READY || state.getRequestState() == MessageState.COMPLETED), "Unexpected request state %s", state.getRequestState());
/*     */ 
/*     */ 
/*     */     
/* 178 */     if (state.getRequestState() == MessageState.COMPLETED) {
/* 179 */       conn.suspendOutput();
/*     */       return;
/*     */     } 
/* 182 */     HttpContext context = conn.getContext();
/*     */     
/* 184 */     synchronized (context) {
/* 185 */       handler = getHandler((NHttpConnection)conn);
/* 186 */       if (handler == null || handler.isDone()) {
/* 187 */         conn.suspendOutput();
/*     */         return;
/*     */       } 
/*     */     } 
/* 191 */     boolean pipelined = pipelining(handler);
/*     */     
/* 193 */     HttpRequest request = handler.generateRequest();
/* 194 */     if (request == null) {
/* 195 */       conn.suspendOutput();
/*     */       return;
/*     */     } 
/* 198 */     ProtocolVersion version = request.getRequestLine().getProtocolVersion();
/* 199 */     if (pipelined && version.lessEquals((ProtocolVersion)HttpVersion.HTTP_1_0)) {
/* 200 */       throw new ProtocolException(version + " cannot be used with request pipelining");
/*     */     }
/* 202 */     state.setRequest(request);
/* 203 */     if (pipelined) {
/* 204 */       state.getRequestQueue().add(request);
/*     */     }
/* 206 */     if (request instanceof HttpEntityEnclosingRequest) {
/* 207 */       boolean expectContinue = ((HttpEntityEnclosingRequest)request).expectContinue();
/* 208 */       if (expectContinue && pipelined) {
/* 209 */         throw new ProtocolException("Expect-continue handshake cannot be used with request pipelining");
/*     */       }
/* 211 */       conn.submitRequest(request);
/* 212 */       if (expectContinue) {
/* 213 */         int timeout = conn.getSocketTimeout();
/* 214 */         state.setTimeout(timeout);
/* 215 */         conn.setSocketTimeout(this.waitForContinue);
/* 216 */         state.setRequestState(MessageState.ACK_EXPECTED);
/*     */       } else {
/* 218 */         HttpEntity entity = ((HttpEntityEnclosingRequest)request).getEntity();
/* 219 */         if (entity != null) {
/* 220 */           state.setRequestState(MessageState.BODY_STREAM);
/*     */         } else {
/* 222 */           handler.requestCompleted();
/* 223 */           state.setRequestState(pipelined ? MessageState.READY : MessageState.COMPLETED);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 227 */       conn.submitRequest(request);
/* 228 */       handler.requestCompleted();
/* 229 */       state.setRequestState(pipelined ? MessageState.READY : MessageState.COMPLETED);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void outputReady(NHttpClientConnection conn, ContentEncoder encoder) throws IOException, HttpException {
/* 237 */     State state = getState((NHttpConnection)conn);
/* 238 */     Asserts.notNull(state, "Connection state");
/* 239 */     Asserts.check((state.getRequestState() == MessageState.BODY_STREAM || state.getRequestState() == MessageState.ACK_EXPECTED), "Unexpected request state %s", state.getRequestState());
/*     */ 
/*     */ 
/*     */     
/* 243 */     HttpAsyncClientExchangeHandler handler = getHandler((NHttpConnection)conn);
/* 244 */     Asserts.notNull(handler, "Client exchange handler");
/* 245 */     if (state.getRequestState() == MessageState.ACK_EXPECTED) {
/* 246 */       conn.suspendOutput();
/*     */       return;
/*     */     } 
/* 249 */     handler.produceContent(encoder, (IOControl)conn);
/* 250 */     if (encoder.isCompleted()) {
/* 251 */       handler.requestCompleted();
/* 252 */       state.setRequestState(pipelining(handler) ? MessageState.READY : MessageState.COMPLETED);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void responseReceived(NHttpClientConnection conn) throws HttpException, IOException {
/*     */     HttpRequest request;
/* 259 */     State state = getState((NHttpConnection)conn);
/* 260 */     Asserts.notNull(state, "Connection state");
/* 261 */     Asserts.check((state.getResponseState() == MessageState.READY), "Unexpected request state %s", state.getResponseState());
/*     */ 
/*     */     
/* 264 */     HttpAsyncClientExchangeHandler handler = getHandler((NHttpConnection)conn);
/* 265 */     Asserts.notNull(handler, "Client exchange handler");
/*     */ 
/*     */     
/* 268 */     if (pipelining(handler)) {
/* 269 */       request = state.getRequestQueue().poll();
/* 270 */       Asserts.notNull(request, "HTTP request");
/*     */     } else {
/* 272 */       request = state.getRequest();
/* 273 */       if (request == null) {
/* 274 */         throw new HttpException("Out of sequence response");
/*     */       }
/*     */     } 
/*     */     
/* 278 */     HttpResponse response = conn.getHttpResponse();
/*     */     
/* 280 */     int statusCode = response.getStatusLine().getStatusCode();
/* 281 */     if (statusCode < 100) {
/* 282 */       throw new ProtocolException("Invalid response: " + response.getStatusLine());
/*     */     }
/* 284 */     if (statusCode < 200) {
/*     */       
/* 286 */       if (statusCode != 100) {
/* 287 */         throw new ProtocolException("Unexpected response: " + response.getStatusLine());
/*     */       }
/*     */       
/* 290 */       if (state.getRequestState() == MessageState.ACK_EXPECTED) {
/* 291 */         int timeout = state.getTimeout();
/* 292 */         conn.setSocketTimeout(timeout);
/* 293 */         conn.requestOutput();
/* 294 */         state.setRequestState(MessageState.BODY_STREAM);
/*     */       } 
/*     */       return;
/*     */     } 
/* 298 */     state.setResponse(response);
/* 299 */     if (state.getRequestState() == MessageState.ACK_EXPECTED) {
/* 300 */       int timeout = state.getTimeout();
/* 301 */       conn.setSocketTimeout(timeout);
/* 302 */       conn.resetOutput();
/* 303 */       state.setRequestState(MessageState.COMPLETED);
/* 304 */     } else if (state.getRequestState() == MessageState.BODY_STREAM) {
/*     */       
/* 306 */       if (statusCode >= 400) {
/* 307 */         conn.resetOutput();
/* 308 */         conn.suspendOutput();
/* 309 */         state.setRequestState(MessageState.COMPLETED);
/* 310 */         state.invalidate();
/*     */       } 
/*     */     } 
/*     */     
/* 314 */     if (canResponseHaveBody(request, response)) {
/* 315 */       handler.responseReceived(response);
/* 316 */       state.setResponseState(MessageState.BODY_STREAM);
/*     */     } else {
/* 318 */       response.setEntity(null);
/* 319 */       handler.responseReceived(response);
/* 320 */       conn.resetInput();
/* 321 */       processResponse(conn, state, handler);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inputReady(NHttpClientConnection conn, ContentDecoder decoder) throws IOException, HttpException {
/* 329 */     State state = getState((NHttpConnection)conn);
/* 330 */     Asserts.notNull(state, "Connection state");
/* 331 */     Asserts.check((state.getResponseState() == MessageState.BODY_STREAM), "Unexpected request state %s", state.getResponseState());
/*     */ 
/*     */     
/* 334 */     HttpAsyncClientExchangeHandler handler = getHandler((NHttpConnection)conn);
/* 335 */     Asserts.notNull(handler, "Client exchange handler");
/* 336 */     handler.consumeContent(decoder, (IOControl)conn);
/* 337 */     if (decoder.isCompleted()) {
/* 338 */       processResponse(conn, state, handler);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void endOfInput(NHttpClientConnection conn) throws IOException {
/* 344 */     State state = getState((NHttpConnection)conn);
/* 345 */     HttpContext context = conn.getContext();
/* 346 */     synchronized (context) {
/* 347 */       if (state != null) {
/* 348 */         if (state.getRequestState().compareTo(MessageState.READY) != 0) {
/* 349 */           state.invalidate();
/*     */         }
/* 351 */         HttpAsyncClientExchangeHandler handler = getHandler((NHttpConnection)conn);
/* 352 */         if (handler != null) {
/* 353 */           if (state.isValid()) {
/* 354 */             handler.inputTerminated();
/*     */           } else {
/* 356 */             handler.failed((Exception)new ConnectionClosedException());
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 364 */       if (conn.getSocketTimeout() <= 0) {
/* 365 */         conn.setSocketTimeout(1000);
/*     */       }
/* 367 */       conn.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void timeout(NHttpClientConnection conn) throws IOException {
/* 374 */     State state = getState((NHttpConnection)conn);
/* 375 */     if (state != null) {
/* 376 */       if (state.getRequestState() == MessageState.ACK_EXPECTED) {
/* 377 */         int timeout = state.getTimeout();
/* 378 */         conn.setSocketTimeout(timeout);
/* 379 */         conn.requestOutput();
/* 380 */         state.setRequestState(MessageState.BODY_STREAM);
/* 381 */         state.setTimeout(0);
/*     */         return;
/*     */       } 
/* 384 */       state.invalidate();
/* 385 */       HttpAsyncClientExchangeHandler handler = getHandler((NHttpConnection)conn);
/* 386 */       if (handler != null) {
/* 387 */         handler.failed(new SocketTimeoutException(String.format("%,d milliseconds timeout on connection %s", new Object[] { Integer.valueOf(conn.getSocketTimeout()), conn })));
/*     */         
/* 389 */         handler.close();
/*     */       } 
/*     */     } 
/* 392 */     if (conn.getStatus() == 0) {
/* 393 */       conn.close();
/* 394 */       if (conn.getStatus() == 1)
/*     */       {
/*     */         
/* 397 */         conn.setSocketTimeout(250);
/*     */       }
/*     */     } else {
/* 400 */       conn.shutdown();
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
/*     */   protected void log(Exception ex) {
/* 412 */     this.exceptionLogger.log(ex);
/*     */   }
/*     */   
/*     */   private static State getState(NHttpConnection conn) {
/* 416 */     return (State)conn.getContext().getAttribute("http.nio.http-exchange-state");
/*     */   }
/*     */   
/*     */   private static HttpAsyncClientExchangeHandler getHandler(NHttpConnection conn) {
/* 420 */     return (HttpAsyncClientExchangeHandler)conn.getContext().getAttribute("http.nio.exchange-handler");
/*     */   }
/*     */   
/*     */   private void shutdownConnection(NHttpConnection conn) {
/*     */     try {
/* 425 */       conn.shutdown();
/* 426 */     } catch (IOException ex) {
/* 427 */       log(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void closeHandler(HttpAsyncClientExchangeHandler handler) {
/* 432 */     if (handler != null) {
/*     */       try {
/* 434 */         handler.close();
/* 435 */       } catch (IOException ioex) {
/* 436 */         log(ioex);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processResponse(NHttpClientConnection conn, State state, HttpAsyncClientExchangeHandler handler) throws IOException, HttpException {
/* 445 */     if (!state.isValid()) {
/* 446 */       conn.close();
/*     */     }
/* 448 */     handler.responseCompleted();
/*     */     
/* 450 */     if (!pipelining(handler)) {
/* 451 */       state.setRequestState(MessageState.READY);
/* 452 */       state.setRequest(null);
/*     */     } 
/* 454 */     state.setResponseState(MessageState.READY);
/* 455 */     state.setResponse(null);
/* 456 */     if (!handler.isDone() && conn.isOpen()) {
/* 457 */       conn.requestOutput();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canResponseHaveBody(HttpRequest request, HttpResponse response) {
/* 463 */     String method = request.getRequestLine().getMethod();
/* 464 */     int status = response.getStatusLine().getStatusCode();
/*     */     
/* 466 */     if (method.equalsIgnoreCase("HEAD")) {
/* 467 */       return false;
/*     */     }
/* 469 */     if (method.equalsIgnoreCase("CONNECT") && status < 300) {
/* 470 */       return false;
/*     */     }
/* 472 */     return (status >= 200 && status != 204 && status != 304 && status != 205);
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
/*     */   static class State
/*     */   {
/* 492 */     private final Queue<HttpRequest> requestQueue = new ConcurrentLinkedQueue<HttpRequest>();
/*     */     private volatile boolean valid = true;
/* 494 */     private volatile MessageState requestState = MessageState.READY;
/* 495 */     private volatile MessageState responseState = MessageState.READY;
/*     */     private volatile HttpRequest request;
/*     */     
/*     */     public MessageState getRequestState() {
/* 499 */       return this.requestState;
/*     */     }
/*     */     private volatile HttpResponse response; private volatile int timeout;
/*     */     public void setRequestState(MessageState state) {
/* 503 */       this.requestState = state;
/*     */     }
/*     */     
/*     */     public MessageState getResponseState() {
/* 507 */       return this.responseState;
/*     */     }
/*     */     
/*     */     public void setResponseState(MessageState state) {
/* 511 */       this.responseState = state;
/*     */     }
/*     */     
/*     */     public HttpRequest getRequest() {
/* 515 */       return this.request;
/*     */     }
/*     */     
/*     */     public void setRequest(HttpRequest request) {
/* 519 */       this.request = request;
/*     */     }
/*     */     
/*     */     public HttpResponse getResponse() {
/* 523 */       return this.response;
/*     */     }
/*     */     
/*     */     public void setResponse(HttpResponse response) {
/* 527 */       this.response = response;
/*     */     }
/*     */     
/*     */     public Queue<HttpRequest> getRequestQueue() {
/* 531 */       return this.requestQueue;
/*     */     }
/*     */     
/*     */     public int getTimeout() {
/* 535 */       return this.timeout;
/*     */     }
/*     */     
/*     */     public void setTimeout(int timeout) {
/* 539 */       this.timeout = timeout;
/*     */     }
/*     */     
/*     */     public boolean isValid() {
/* 543 */       return this.valid;
/*     */     }
/*     */     
/*     */     public void invalidate() {
/* 547 */       this.valid = false;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 552 */       StringBuilder buf = new StringBuilder();
/* 553 */       buf.append("request state: ");
/* 554 */       buf.append(this.requestState);
/* 555 */       buf.append("; request: ");
/* 556 */       if (this.request != null) {
/* 557 */         buf.append(this.request.getRequestLine());
/*     */       }
/* 559 */       buf.append("; response state: ");
/* 560 */       buf.append(this.responseState);
/* 561 */       buf.append("; response: ");
/* 562 */       if (this.response != null) {
/* 563 */         buf.append(this.response.getStatusLine());
/*     */       }
/* 565 */       buf.append("; valid: ");
/* 566 */       buf.append(this.valid);
/* 567 */       buf.append(";");
/* 568 */       return buf.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/HttpAsyncRequestExecutor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */