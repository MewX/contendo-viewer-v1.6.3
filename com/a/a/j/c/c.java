/*    */ package com.a.a.j.c;
/*    */ 
/*    */ import com.a.a.e.b;
/*    */ import com.a.a.e.b.a;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.nio.ByteBuffer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class c
/*    */   extends i
/*    */ {
/*    */   b a;
/*    */   
/*    */   public c(ByteBuffer byteBuffer) {
/* 17 */     this.a = b.c(byteBuffer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public c(b b1) {
/* 25 */     this.a = b1.x();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InputStream d() throws IOException {
/* 33 */     return (InputStream)new a(this.a.x());
/*    */   }
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
/*    */   public long f() {
/* 49 */     return this.a.f();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public b e() throws IOException {
/* 57 */     return this.a.x();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/c/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */