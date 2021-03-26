/*    */ package com.a.a.e;
/*    */ 
/*    */ import java.nio.BufferUnderflowException;
/*    */ import java.nio.ReadOnlyBufferException;
/*    */ 
/*    */ public class f {
/*    */   public static void a(e<?> dst, e<?> src) throws ReadOnlyBufferException {
/*  8 */     int remain = Integer.MAX_VALUE;
/*  9 */     if (src instanceof b) {
/* 10 */       remain = ((b)src).g();
/*    */     }
/*    */     try {
/* 13 */       for (int i = 0; i < remain; i++) {
/* 14 */         dst.b(src.i());
/*    */       }
/* 16 */     } catch (BufferUnderflowException bufferUnderflowException) {}
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void a(e<?> dst, e<?> src, int position, int length) throws ReadOnlyBufferException, IndexOutOfBoundsException {
/* 23 */     for (int i = 0; i < length; i++)
/* 24 */       dst.b(src.b(position++)); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/e/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */