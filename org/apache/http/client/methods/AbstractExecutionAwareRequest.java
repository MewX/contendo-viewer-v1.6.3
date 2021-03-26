/*     */ package org.apache.http.client.methods;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.client.utils.CloneUtils;
/*     */ import org.apache.http.concurrent.Cancellable;
/*     */ import org.apache.http.conn.ClientConnectionRequest;
/*     */ import org.apache.http.conn.ConnectionReleaseTrigger;
/*     */ import org.apache.http.message.AbstractHttpMessage;
/*     */ import org.apache.http.message.HeaderGroup;
/*     */ import org.apache.http.params.HttpParams;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractExecutionAwareRequest
/*     */   extends AbstractHttpMessage
/*     */   implements Cloneable, HttpRequest, AbortableHttpRequest, HttpExecutionAware
/*     */ {
/*  49 */   private final AtomicBoolean aborted = new AtomicBoolean(false);
/*  50 */   private final AtomicReference<Cancellable> cancellableRef = new AtomicReference<Cancellable>(null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setConnectionRequest(final ClientConnectionRequest connRequest) {
/*  59 */     setCancellable(new Cancellable()
/*     */         {
/*     */           public boolean cancel()
/*     */           {
/*  63 */             connRequest.abortRequest();
/*  64 */             return true;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setReleaseTrigger(final ConnectionReleaseTrigger releaseTrigger) {
/*  76 */     setCancellable(new Cancellable()
/*     */         {
/*     */           public boolean cancel()
/*     */           {
/*     */             try {
/*  81 */               releaseTrigger.abortConnection();
/*  82 */               return true;
/*  83 */             } catch (IOException ex) {
/*  84 */               return false;
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void abort() {
/*  93 */     if (this.aborted.compareAndSet(false, true)) {
/*  94 */       Cancellable cancellable = this.cancellableRef.getAndSet(null);
/*  95 */       if (cancellable != null) {
/*  96 */         cancellable.cancel();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAborted() {
/* 103 */     return this.aborted.get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCancellable(Cancellable cancellable) {
/* 111 */     if (!this.aborted.get()) {
/* 112 */       this.cancellableRef.set(cancellable);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 118 */     AbstractExecutionAwareRequest clone = (AbstractExecutionAwareRequest)super.clone();
/* 119 */     clone.headergroup = (HeaderGroup)CloneUtils.cloneObject(this.headergroup);
/* 120 */     clone.params = (HttpParams)CloneUtils.cloneObject(this.params);
/* 121 */     return clone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void completed() {
/* 128 */     this.cancellableRef.set(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 137 */     Cancellable cancellable = this.cancellableRef.getAndSet(null);
/* 138 */     if (cancellable != null) {
/* 139 */       cancellable.cancel();
/*     */     }
/* 141 */     this.aborted.set(false);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/client/methods/AbstractExecutionAwareRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */