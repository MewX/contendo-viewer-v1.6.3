/*    */ package com.a.a.e;
/*    */ import com.a.a.b.h;
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
/*    */ import java.nio.BufferOverflowException;
/*    */ import java.nio.BufferUnderflowException;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.ByteOrder;
/*    */ import java.nio.ReadOnlyBufferException;
/*    */ 
/*    */ public class i extends h {
/*    */   static class a extends h.a {
/* 13 */     int g = -1; h f;
/*    */     
/*    */     a(h raf) throws IOException {
/* 16 */       super(13, Math.max(2, Math.min(8, (int)(raf.b() >> 16L))));
/* 17 */       this.f = raf;
/*    */     }
/*    */ 
/*    */     
/*    */     protected int a() {
/* 22 */       if (this.g < 0) {
/*    */         try {
/* 24 */           this.g = (int)this.f.b();
/* 25 */         } catch (IOException e) {
/* 26 */           throw new RuntimeException(e);
/*    */         } 
/*    */       }
/* 29 */       return this.g;
/*    */     }
/*    */ 
/*    */     
/*    */     protected h.a.b b(int abspos) throws IOException {
/* 34 */       h.a.b page = g(abspos);
/* 35 */       if (page == null) {
/* 36 */         synchronized (this.f) {
/*    */           
/* 38 */           page = g(abspos);
/* 39 */           if (page != null) return page;
/*    */           
/* 41 */           int pagetop = d(abspos);
/*    */           
/* 43 */           this.f.a(pagetop);
/*    */           
/* 45 */           byte[] b = f(a() - pagetop);
/* 46 */           int n = this.f.a(b);
/*    */           
/* 48 */           if (n != b.length) {
/* 49 */             throw new IOException();
/*    */           }
/*    */           
/* 52 */           page = a(pagetop, b);
/*    */         } 
/*    */       }
/* 55 */       return page;
/*    */     }
/*    */ 
/*    */     
/*    */     protected void finalize() throws Throwable {
/*    */       
/* 61 */       try { if (this.f != null) this.f.close();  }
/* 62 */       catch (Throwable throwable) {  }
/*    */       finally
/* 64 */       { this.f = null;
/* 65 */         super.finalize(); }
/*    */     
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public i(h raf) throws IOException {
/* 75 */     super(new a(raf));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public i(RandomAccessFile raf) throws IOException {
/* 83 */     this((h)new j(raf));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/e/i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */