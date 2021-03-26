/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpEntityEnclosingRequest;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.IOControl;
/*     */ import org.apache.http.nio.entity.EntityAsyncContentProducer;
/*     */ import org.apache.http.nio.entity.HttpAsyncContentProducer;
/*     */ import org.apache.http.protocol.HttpContext;
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
/*     */ public class BasicAsyncRequestProducer
/*     */   implements HttpAsyncRequestProducer
/*     */ {
/*     */   private final HttpHost target;
/*     */   private final HttpRequest request;
/*     */   private final HttpAsyncContentProducer producer;
/*     */   
/*     */   protected BasicAsyncRequestProducer(HttpHost target, HttpEntityEnclosingRequest request, HttpAsyncContentProducer producer) {
/*  74 */     Args.notNull(target, "HTTP host");
/*  75 */     Args.notNull(request, "HTTP request");
/*  76 */     Args.notNull(producer, "HTTP content producer");
/*  77 */     this.target = target;
/*  78 */     this.request = (HttpRequest)request;
/*  79 */     this.producer = producer;
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
/*     */   public BasicAsyncRequestProducer(HttpHost target, HttpRequest request) {
/*  91 */     Args.notNull(target, "HTTP host");
/*  92 */     Args.notNull(request, "HTTP request");
/*  93 */     this.target = target;
/*  94 */     this.request = request;
/*  95 */     if (request instanceof HttpEntityEnclosingRequest) {
/*  96 */       HttpEntity entity = ((HttpEntityEnclosingRequest)request).getEntity();
/*  97 */       if (entity != null) {
/*  98 */         if (entity instanceof HttpAsyncContentProducer) {
/*  99 */           this.producer = (HttpAsyncContentProducer)entity;
/*     */         } else {
/* 101 */           this.producer = (HttpAsyncContentProducer)new EntityAsyncContentProducer(entity);
/*     */         } 
/*     */       } else {
/* 104 */         this.producer = null;
/*     */       } 
/*     */     } else {
/* 107 */       this.producer = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpRequest generateRequest() {
/* 113 */     return this.request;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpHost getTarget() {
/* 118 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void produceContent(ContentEncoder encoder, IOControl ioControl) throws IOException {
/* 124 */     if (this.producer != null) {
/* 125 */       this.producer.produceContent(encoder, ioControl);
/* 126 */       if (encoder.isCompleted()) {
/* 127 */         this.producer.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void requestCompleted(HttpContext context) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void failed(Exception ex) {}
/*     */ 
/*     */   
/*     */   public boolean isRepeatable() {
/* 142 */     return (this.producer == null || this.producer.isRepeatable());
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetRequest() throws IOException {
/* 147 */     if (this.producer != null) {
/* 148 */       this.producer.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 154 */     if (this.producer != null) {
/* 155 */       this.producer.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 161 */     StringBuilder sb = new StringBuilder();
/* 162 */     sb.append(this.target);
/* 163 */     sb.append(' ');
/* 164 */     sb.append(this.request);
/* 165 */     if (this.producer != null) {
/* 166 */       sb.append(' ');
/* 167 */       sb.append(this.producer);
/*     */     } 
/* 169 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/BasicAsyncRequestProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */