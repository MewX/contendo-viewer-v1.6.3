/*    */ package org.apache.bcel.util;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.DataInputStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ByteSequence
/*    */   extends DataInputStream
/*    */ {
/*    */   private ByteArrayStream byte_stream;
/*    */   
/*    */   public ByteSequence(byte[] bytes) {
/* 70 */     super(new ByteArrayStream(bytes));
/* 71 */     this.byte_stream = (ByteArrayStream)this.in;
/*    */   }
/*    */   
/* 74 */   public final int getIndex() { return this.byte_stream.getPosition(); } final void unreadByte() {
/* 75 */     this.byte_stream.unreadByte();
/*    */   }
/*    */   private static final class ByteArrayStream extends ByteArrayInputStream {
/* 78 */     ByteArrayStream(byte[] bytes) { super(bytes); }
/* 79 */     final int getPosition() { return this.pos; } final void unreadByte() {
/* 80 */       if (this.pos > 0) this.pos--; 
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/util/ByteSequence.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */