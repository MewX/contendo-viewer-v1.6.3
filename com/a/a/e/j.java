/*    */ package com.a.a.e;
/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.URL;
/*    */ import java.nio.BufferOverflowException;
/*    */ import java.nio.BufferUnderflowException;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.ByteOrder;
/*    */ import java.nio.InvalidMarkException;
/*    */ import java.nio.ReadOnlyBufferException;
/*    */ 
/*    */ public class j extends h {
/*    */   static class a extends h.b {
/* 15 */     private int j = -1; private URL i;
/*    */     
/*    */     a(URL url, int count) {
/* 18 */       super(count);
/* 19 */       this.i = url;
/*    */     }
/*    */ 
/*    */     
/*    */     protected InputStream b() throws IOException {
/* 24 */       InputStream is = this.i.openStream();
/* 25 */       if (is instanceof java.io.FileInputStream) {
/* 26 */         is = new BufferedInputStream(is);
/*    */       }
/* 28 */       return is;
/*    */     }
/*    */ 
/*    */     
/*    */     protected int a() {
/* 33 */       if (this.j < 0) {
/* 34 */         InputStream isTmp = null;
/*    */         
/*    */         try {
/* 37 */           isTmp = this.i.openStream();
/* 38 */           this.j = isTmp.available();
/* 39 */           if (this.j < 0) {
/*    */             
/* 41 */             this.j = (int)isTmp.skip(2147483647L);
/* 42 */             if (this.j == Integer.MAX_VALUE) {
/* 43 */               this.j = -1;
/*    */               try {
/* 45 */                 isTmp.close();
/* 46 */               } catch (IOException iOException) {}
/*    */               
/* 48 */               isTmp = this.i.openStream();
/* 49 */               int n = 0;
/* 50 */               for (; isTmp.read() >= 0; n++);
/* 51 */               this.j = n;
/*    */             } 
/*    */           } 
/*    */ 
/*    */           
/* 56 */           if (this.j >= 0) {
/*    */             
/* 58 */             int limit = Math.max(2, Math.min(8, this.j >> 16));
/* 59 */             c(limit);
/*    */           } 
/* 61 */         } catch (IOException e) {
/* 62 */           throw new RuntimeException(e);
/*    */         } finally {
/*    */           try {
/* 65 */             isTmp.close();
/* 66 */           } catch (IOException iOException) {}
/*    */         } 
/*    */       } 
/*    */       
/* 70 */       return this.j;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public j(URL url) {
/* 78 */     super(new a(url, 8));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/e/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */