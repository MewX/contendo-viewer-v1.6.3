/*    */ package com.a.a.j.c;
/*    */ 
/*    */ import com.a.a.d.b;
/*    */ import com.a.a.j.d;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public abstract class i
/*    */   implements h {
/* 10 */   protected b e = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static b a(h file) {
/* 17 */     b mimetype = null;
/*    */     try {
/* 19 */       mimetype = b.a(b.a(file.d()));
/* 20 */     } catch (IOException iOException) {}
/*    */     
/* 22 */     if (mimetype == null) {
/* 23 */       mimetype = d.e;
/*    */     }
/* 25 */     return mimetype;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public b j() {
/* 33 */     if (this.e == null) {
/* 34 */       synchronized (this) {
/* 35 */         if (this.e == null) {
/* 36 */           this.e = a(this);
/*    */         }
/*    */       } 
/*    */     }
/* 40 */     return this.e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean k() {
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InputStream l() throws IOException {
/* 56 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long m() {
/* 63 */     return -1L;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/c/i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */