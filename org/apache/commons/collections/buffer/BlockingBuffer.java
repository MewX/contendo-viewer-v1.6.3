/*     */ package org.apache.commons.collections.buffer;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.Buffer;
/*     */ import org.apache.commons.collections.BufferUnderflowException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockingBuffer
/*     */   extends SynchronizedBuffer
/*     */ {
/*     */   private static final long serialVersionUID = 1719328905017860541L;
/*     */   
/*     */   public static Buffer decorate(Buffer buffer) {
/*  59 */     return new BlockingBuffer(buffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockingBuffer(Buffer buffer) {
/*  70 */     super(buffer);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Object o) {
/*  75 */     synchronized (this.lock) {
/*  76 */       boolean result = this.collection.add(o);
/*  77 */       notifyAll();
/*  78 */       return result;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection c) {
/*  83 */     synchronized (this.lock) {
/*  84 */       boolean result = this.collection.addAll(c);
/*  85 */       notifyAll();
/*  86 */       return result;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object get() {
/*  91 */     synchronized (this.lock) {
/*  92 */       while (this.collection.isEmpty()) {
/*     */         try {
/*  94 */           wait();
/*  95 */         } catch (InterruptedException e) {
/*  96 */           throw new BufferUnderflowException();
/*     */         } 
/*     */       } 
/*  99 */       return getBuffer().get();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object remove() {
/* 104 */     synchronized (this.lock) {
/* 105 */       while (this.collection.isEmpty()) {
/*     */         try {
/* 107 */           wait();
/* 108 */         } catch (InterruptedException e) {
/* 109 */           throw new BufferUnderflowException();
/*     */         } 
/*     */       } 
/* 112 */       return getBuffer().remove();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/buffer/BlockingBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */