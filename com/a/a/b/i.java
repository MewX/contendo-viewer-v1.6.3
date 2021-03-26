/*    */ package com.a.a.b;
/*    */ 
/*    */ import com.a.a.e.b;
/*    */ import java.io.EOFException;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class i
/*    */   extends h
/*    */ {
/*    */   b a;
/*    */   
/*    */   public i(b buf) {
/* 15 */     this.a = buf.x();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int a(byte[] buf, int off, int length) throws IOException {
/* 23 */     length = Math.min(length, this.a.g());
/* 24 */     this.a.c(buf, off, length);
/* 25 */     return length;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void b(byte[] buf, int off, int length) throws EOFException, IOException {
/* 34 */     if (this.a.g() < length) throw new EOFException(); 
/* 35 */     this.a.c(buf, off, length);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long a() throws IOException {
/* 43 */     return this.a.k();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(long pos) throws IOException {
/* 51 */     this.a.h((int)pos);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long b() throws IOException {
/* 59 */     return this.a.f();
/*    */   }
/*    */   
/*    */   public void close() throws IOException {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/b/i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */