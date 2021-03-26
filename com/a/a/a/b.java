/*    */ package com.a.a.a;
/*    */ 
/*    */ import java.util.LinkedList;
/*    */ 
/*    */ public class b {
/*    */   static final int a = 8;
/*  7 */   LinkedList<byte[]> b = (LinkedList)new LinkedList<byte>();
/*    */ 
/*    */   
/*    */   int c;
/*    */ 
/*    */   
/*    */   public b() {
/* 14 */     this(8);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public b(int size) {
/* 22 */     this.c = size;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized byte[] a() {
/* 29 */     if (this.b.isEmpty()) {
/* 30 */       return new byte[this.c];
/*    */     }
/* 32 */     return this.b.removeFirst();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized void a(byte[] arrayOfByte) {
/* 39 */     if (arrayOfByte != null && arrayOfByte.length == this.c && 
/* 40 */       this.b.size() < 4)
/* 41 */       this.b.add(arrayOfByte); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/a/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */