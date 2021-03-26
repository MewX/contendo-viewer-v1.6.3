/*    */ package com.a.a.e.a;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.CharBuffer;
/*    */ import java.nio.charset.CharsetDecoder;
/*    */ import java.nio.charset.CoderResult;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class a
/*    */ {
/*    */   public static boolean a(StringBuilder sb, CharsetDecoder decoder, ByteBuffer bbuf, CharBuffer cbuf, boolean end) {
/* 19 */     boolean end2 = false;
/* 20 */     if (cbuf.position() == 0) {
/* 21 */       cbuf.flip();
/*    */     }
/*    */     while (true) {
/* 24 */       if (cbuf.hasRemaining()) {
/* 25 */         boolean flgCR = false;
/* 26 */         for (int i = cbuf.position(), m = cbuf.limit(); i < m; i++) {
/* 27 */           char c = cbuf.get(i);
/*    */           
/* 29 */           if (c == '\n') {
/* 30 */             cbuf.position(i + 1);
/* 31 */             return true;
/*    */           } 
/*    */           
/* 34 */           if (c == '\r') {
/* 35 */             flgCR = true;
/*    */           } else {
/* 37 */             if (flgCR) {
/* 38 */               cbuf.position(i);
/* 39 */               return true;
/*    */             } 
/* 41 */             sb.append(c);
/* 42 */             flgCR = false;
/*    */           } 
/*    */         } 
/*    */       } 
/* 46 */       if (end2) return true;
/*    */       
/* 48 */       if (!bbuf.hasRemaining()) {
/* 49 */         if (end) {
/* 50 */           return (sb.length() > 0);
/*    */         }
/* 52 */         bbuf.compact();
/*    */         break;
/*    */       } 
/* 55 */       cbuf.clear();
/* 56 */       CoderResult cr = decoder.decode(bbuf, cbuf, end);
/* 57 */       cbuf.flip();
/*    */       
/* 59 */       end2 = end;
/*    */     } 
/*    */ 
/*    */     
/* 63 */     return false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/e/a/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */