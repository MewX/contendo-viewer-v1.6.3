/*    */ package com.a.a.b;
/*    */ 
/*    */ import java.io.FilterOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends FilterOutputStream
/*    */ {
/* 13 */   private f a = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public b(OutputStream out) {
/* 19 */     super(out);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(f closeListener) {
/* 26 */     this.a = closeListener;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 34 */     boolean success = false;
/*    */     try {
/* 36 */       super.close();
/* 37 */       success = true;
/*    */     } finally {
/* 39 */       if (this.a != null)
/* 40 */         this.a.a(this, success); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/b/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */