/*    */ package com.a.a.j.c;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends i
/*    */ {
/*    */   byte[] a;
/*    */   
/*    */   public b(byte[] arrayOfByte) {
/* 17 */     this.a = arrayOfByte;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InputStream d() throws IOException {
/* 25 */     return new ByteArrayInputStream(this.a);
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
/* 41 */     return this.a.length;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public com.a.a.e.b e() throws IOException {
/* 49 */     return com.a.a.e.b.e(this.a);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/c/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */