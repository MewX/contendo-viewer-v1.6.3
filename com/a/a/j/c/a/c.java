/*    */ package com.a.a.j.c.a;
/*    */ 
/*    */ import com.a.a.b.b.a;
/*    */ import com.a.a.j.c.g;
/*    */ import com.a.a.j.c.m;
/*    */ import com.a.a.j.c.o;
/*    */ import com.a.a.j.c.p;
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class c
/*    */   extends o
/*    */ {
/*    */   private final String c;
/* 20 */   private final ArrayList<File> d = new ArrayList<File>();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public c(File cacheDir) {
/* 26 */     super(cacheDir);
/* 27 */     this.c = String.format("%h", new Object[] { this });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public p a(String path) throws FileNotFoundException {
/* 36 */     path = String.valueOf(this.c) + "." + String.format("%h", new Object[] { path });
/* 37 */     if (!b().exists()) {
/* 38 */       b().mkdirs();
/*    */     }
/* 40 */     File f = new File(b(), path);
/* 41 */     this.d.add(f);
/* 42 */     return new b((m)this, path, f);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public g c(String path) throws FileNotFoundException {
/* 50 */     return (g)a(path);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized void a() {
/* 58 */     c();
/* 59 */     super.a();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void finalize() throws Throwable {
/*    */     try {
/* 68 */       c();
/*    */     } finally {
/* 70 */       super.finalize();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void c() {
/* 78 */     if (this.d != null)
/* 79 */       while (!this.d.isEmpty())
/* 80 */         a.a(this.d.remove(0));  
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/c/a/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */