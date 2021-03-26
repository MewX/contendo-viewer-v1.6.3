/*    */ package com.a.a.j.c;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class o
/*    */   extends l
/*    */ {
/*    */   protected File a;
/*    */   int b;
/*    */   
/*    */   public o(File dir) {
/* 21 */     this.a = dir;
/* 22 */     this.b = this.a.getAbsolutePath().length();
/* 23 */     if (!this.a.getAbsolutePath().endsWith(File.separator)) {
/* 24 */       this.b++;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected File b() {
/* 33 */     return this.a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public g c(String path) throws FileNotFoundException {
/* 40 */     return new n(this, path, new File(this.a, path));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String b(String path) {
/* 55 */     if (path == null || path.length() == 0) {
/* 56 */       return "";
/*    */     }
/* 58 */     return path;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String[] a(String path, boolean subdir) {
/* 66 */     List<String> list = a(new ArrayList<String>(), this.a, d(path), 0, subdir);
/* 67 */     return list.<String>toArray(new String[list.size()]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected List<String> a(List<String> list, File dir, String[] path, int depth, boolean subdir) {
/* 78 */     if (path.length > depth) {
/* 79 */       return a(list, new File(dir, path[depth]), path, depth + 1, subdir);
/*    */     }
/*    */     
/* 82 */     File[] files = dir.listFiles(); byte b; int i; File[] arrayOfFile1;
/* 83 */     for (i = (arrayOfFile1 = files).length, b = 0; b < i; ) { File f = arrayOfFile1[b];
/* 84 */       if (subdir && f.isDirectory()) {
/* 85 */         list = a(list, f, path, depth + 1, subdir);
/*    */       } else {
/* 87 */         list.add(f.getAbsolutePath().substring(this.b));
/*    */       }  b++; }
/*    */     
/* 90 */     return list;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public g n() {
/* 98 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/c/o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */