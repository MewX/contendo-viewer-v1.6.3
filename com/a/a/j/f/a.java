/*    */ package com.a.a.j.f;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
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
/*    */ public class a
/*    */   extends d
/*    */ {
/*    */   byte[] a;
/*    */   int b;
/*    */   int c;
/*    */   
/*    */   public a(byte[] buf, int port) throws IOException {
/* 24 */     this(buf, 0, buf.length, port);
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
/*    */   public a(byte[] buf, int offset, int length, int port) throws IOException {
/* 36 */     super(port);
/* 37 */     this.a = buf;
/* 38 */     this.b = offset;
/* 39 */     this.c = length;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InputStream a() throws IOException {
/* 47 */     return new ByteArrayInputStream(this.a, this.b, this.c);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/f/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */