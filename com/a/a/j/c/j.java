/*    */ package com.a.a.j.c;
/*    */ 
/*    */ import com.a.a.b.b.b;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import java.net.URLStreamHandler;
/*    */ 
/*    */ 
/*    */ public class j
/*    */   extends URLStreamHandler
/*    */ {
/*    */   final g a;
/*    */   
/*    */   class a
/*    */     extends URLConnection
/*    */   {
/*    */     protected a(j this$0, URL url) {
/* 20 */       super(url);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public void connect() throws IOException {}
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public InputStream getInputStream() throws IOException {
/* 32 */       InputStream is = this.a.a.d();
/* 33 */       if (is != null && !is.markSupported()) {
/* 34 */         is = b.a(is);
/*    */       }
/* 36 */       return is;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public j(g file) {
/* 45 */     this.a = file;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected URLConnection openConnection(URL u) throws IOException {
/* 53 */     return new a(this, u);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/c/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */