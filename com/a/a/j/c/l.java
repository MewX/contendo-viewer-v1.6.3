/*    */ package com.a.a.j.c;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class l
/*    */   implements m
/*    */ {
/*    */   public int a(f o) {
/* 13 */     return a_().compareTo(o.a_());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String[] p() {
/* 21 */     return a("", true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String[] a(g file, boolean subdir) {
/* 30 */     return a((file == null) ? "" : file.b(), subdir);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String a_() {
/* 38 */     return b(null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected String[] d(String path) {
/* 46 */     if (path == null || path.length() == 0) return new String[0]; 
/* 47 */     return a(new File(path));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected String[] a(File f) {
/* 55 */     ArrayList<String> list = new ArrayList<String>();
/* 56 */     while (f != null) {
/* 57 */       list.add(f.getName());
/* 58 */       f = f.getParentFile();
/*    */     } 
/*    */     
/* 61 */     int i = list.size();
/* 62 */     String[] tmp = new String[i];
/* 63 */     for (String n : list) tmp[--i] = n;
/*    */     
/* 65 */     return tmp;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/c/l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */