/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpResponse;
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
/*     */ public class BasicAsyncResponseProducer
/*     */   implements HttpAsyncResponseProducer
/*     */ {
/*     */   private final HttpResponse response;
/*     */   private final HttpAsyncContentProducer producer;
/*     */   
/*     */   protected BasicAsyncResponseProducer(HttpResponse response, HttpAsyncContentProducer producer) {
/*  71 */     Args.notNull(response, "HTTP response");
/*  72 */     Args.notNull(producer, "HTTP content producer");
/*  73 */     this.response = response;
/*  74 */     this.producer = producer;
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
/*     */   public BasicAsyncResponseProducer(HttpResponse response) {
/*  86 */     Args.notNull(response, "HTTP response");
/*  87 */     this.response = response;
/*  88 */     HttpEntity entity = response.getEntity();
/*  89 */     if (entity != null) {
/*  90 */       if (entity instanceof HttpAsyncContentProducer) {
/*  91 */         this.producer = (HttpAsyncContentProducer)entity;
/*     */       } else {
/*  93 */         this.producer = (HttpAsyncContentProducer)new EntityAsyncContentProducer(entity);
/*     */       } 
/*     */     } else {
/*  96 */       this.producer = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpResponse generateResponse() {
/* 102 */     return this.response;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void produceContent(ContentEncoder encoder, IOControl ioControl) throws IOException {
/* 108 */     if (this.producer != null) {
/* 109 */       this.producer.produceContent(encoder, ioControl);
/* 110 */       if (encoder.isCompleted()) {
/* 111 */         this.producer.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void responseCompleted(HttpContext context) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void failed(Exception ex) {}
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 126 */     if (this.producer != null) {
/* 127 */       this.producer.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 133 */     StringBuilder buf = new StringBuilder();
/* 134 */     buf.append(this.response);
/* 135 */     if (this.producer != null) {
/* 136 */       buf.append(" ").append(this.producer);
/*     */     }
/* 138 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/BasicAsyncResponseProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */