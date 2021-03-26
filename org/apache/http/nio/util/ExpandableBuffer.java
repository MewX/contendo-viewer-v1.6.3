/*     */ package org.apache.http.nio.util;
/*     */ 
/*     */ import java.nio.BufferOverflowException;
/*     */ import java.nio.ByteBuffer;
/*     */ import org.apache.http.io.BufferInfo;
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
/*     */ public class ExpandableBuffer
/*     */   implements BufferInfo, BufferInfo
/*     */ {
/*     */   public static final int INPUT_MODE = 0;
/*     */   public static final int OUTPUT_MODE = 1;
/*     */   private final ByteBufferAllocator allocator;
/*     */   private int mode;
/*  54 */   protected ByteBuffer buffer = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpandableBuffer(int bufferSize, ByteBufferAllocator allocator) {
/*  64 */     Args.notNull(allocator, "ByteBuffer allocator");
/*  65 */     this.allocator = allocator;
/*  66 */     this.buffer = allocator.allocate(bufferSize);
/*  67 */     this.mode = 0;
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
/*     */   protected int getMode() {
/*  80 */     return this.mode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setOutputMode() {
/*  87 */     if (this.mode != 1) {
/*  88 */       this.buffer.flip();
/*  89 */       this.mode = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setInputMode() {
/*  97 */     if (this.mode != 0) {
/*  98 */       if (this.buffer.hasRemaining()) {
/*  99 */         this.buffer.compact();
/*     */       } else {
/* 101 */         this.buffer.clear();
/*     */       } 
/* 103 */       this.mode = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void expandCapacity(int capacity) {
/* 108 */     ByteBuffer oldbuffer = this.buffer;
/* 109 */     this.buffer = this.allocator.allocate(capacity);
/* 110 */     oldbuffer.flip();
/* 111 */     this.buffer.put(oldbuffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void expand() throws BufferOverflowException {
/* 120 */     int newCapacity = this.buffer.capacity() + 1 << 1;
/* 121 */     if (newCapacity < 0) {
/* 122 */       int vmBytes = 8;
/* 123 */       int javaBytes = 8;
/*     */       
/* 125 */       int headRoom = 8;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 134 */       newCapacity = 2147483639;
/*     */       
/* 136 */       if (newCapacity <= this.buffer.capacity()) {
/* 137 */         throw new BufferOverflowException();
/*     */       }
/*     */     } 
/* 140 */     expandCapacity(newCapacity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void ensureCapacity(int requiredCapacity) {
/* 147 */     if (requiredCapacity > this.buffer.capacity()) {
/* 148 */       expandCapacity(requiredCapacity);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int capacity() {
/* 159 */     return this.buffer.capacity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasData() {
/* 169 */     setOutputMode();
/* 170 */     return this.buffer.hasRemaining();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int length() {
/* 180 */     setOutputMode();
/* 181 */     return this.buffer.remaining();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int available() {
/* 191 */     setInputMode();
/* 192 */     return this.buffer.remaining();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void clear() {
/* 199 */     this.buffer.clear();
/* 200 */     this.mode = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 205 */     StringBuilder sb = new StringBuilder();
/* 206 */     sb.append("[mode=");
/* 207 */     if (getMode() == 0) {
/* 208 */       sb.append("in");
/*     */     } else {
/* 210 */       sb.append("out");
/*     */     } 
/* 212 */     sb.append(" pos=");
/* 213 */     sb.append(this.buffer.position());
/* 214 */     sb.append(" lim=");
/* 215 */     sb.append(this.buffer.limit());
/* 216 */     sb.append(" cap=");
/* 217 */     sb.append(this.buffer.capacity());
/* 218 */     sb.append("]");
/* 219 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/util/ExpandableBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */