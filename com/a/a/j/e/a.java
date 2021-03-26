/*    */ package com.a.a.j.e;
/*    */ import com.a.a.e.b;
/*    */ import com.a.a.e.e;
/*    */ import com.a.a.e.h;
/*    */ import com.a.a.j.c.h;
/*    */ import java.nio.BufferUnderflowException;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.ReadOnlyBufferException;
/*    */ 
/*    */ public class a extends h {
/*    */   static class a extends h.b {
/* 12 */     private int j = -1;
/*    */ 
/*    */     
/*    */     private h i;
/*    */ 
/*    */ 
/*    */     
/*    */     a(h file, int count) {
/* 20 */       super(count);
/* 21 */       this.i = file;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     protected InputStream b() throws IOException {
/* 29 */       return this.i.d();
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     protected int a() {
/* 37 */       if (this.j < 0) {
/* 38 */         synchronized (this.i) {
/* 39 */           if (this.j < 0) {
/* 40 */             this.j = (int)this.i.f();
/*    */           }
/*    */         } 
/*    */         
/* 44 */         if (this.j >= 0) {
/*    */           
/* 46 */           int limit = Math.max(2, Math.min(8, this.j >> 16));
/* 47 */           c(limit);
/*    */         } 
/*    */       } 
/* 50 */       return this.j;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public a(h file) {
/* 59 */     super((h.a)new a(file, 8));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/e/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */