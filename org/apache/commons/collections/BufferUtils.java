/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import org.apache.commons.collections.buffer.BlockingBuffer;
/*     */ import org.apache.commons.collections.buffer.PredicatedBuffer;
/*     */ import org.apache.commons.collections.buffer.SynchronizedBuffer;
/*     */ import org.apache.commons.collections.buffer.TransformedBuffer;
/*     */ import org.apache.commons.collections.buffer.TypedBuffer;
/*     */ import org.apache.commons.collections.buffer.UnmodifiableBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BufferUtils
/*     */ {
/*  39 */   public static final Buffer EMPTY_BUFFER = UnmodifiableBuffer.decorate(new ArrayStack(1));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Buffer synchronizedBuffer(Buffer buffer) {
/*  69 */     return SynchronizedBuffer.decorate(buffer);
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
/*     */   public static Buffer blockingBuffer(Buffer buffer) {
/*  85 */     return BlockingBuffer.decorate(buffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Buffer unmodifiableBuffer(Buffer buffer) {
/*  96 */     return UnmodifiableBuffer.decorate(buffer);
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
/*     */   public static Buffer predicatedBuffer(Buffer buffer, Predicate predicate) {
/* 113 */     return PredicatedBuffer.decorate(buffer, predicate);
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
/*     */   public static Buffer typedBuffer(Buffer buffer, Class type) {
/* 127 */     return TypedBuffer.decorate(buffer, type);
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
/*     */   public static Buffer transformedBuffer(Buffer buffer, Transformer transformer) {
/* 143 */     return TransformedBuffer.decorate(buffer, transformer);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/BufferUtils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */