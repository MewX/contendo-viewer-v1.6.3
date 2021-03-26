/*    */ package com.a.a.d;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class c {
/*    */   public static final char a = '/';
/*  9 */   public static final URI b = URI.create("./");
/*    */   
/*    */   public static List<String> a(URI uri) {
/* 12 */     String path = uri.getPath();
/* 13 */     ArrayList<String> list = new ArrayList<String>();
/* 14 */     for (int i = 0, m = path.length(); i < m; i++) {
/* 15 */       int n = path.indexOf('/', i);
/* 16 */       if (n != 0) {
/* 17 */         if (n < 0) {
/* 18 */           list.add(path.substring(i, m));
/*    */           break;
/*    */         } 
/* 21 */         list.add(path.substring(i, n));
/* 22 */         i = n;
/*    */       } 
/* 24 */     }  return list;
/*    */   }
/*    */   
/*    */   public static String b(URI uri) {
/* 28 */     String path = uri.getPath();
/* 29 */     int n = path.lastIndexOf('/');
/* 30 */     if (n >= 0) {
/* 31 */       return path.substring(n + 1);
/*    */     }
/* 33 */     return path;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static URI a(URI uri, String path) {
/* 43 */     String txt = uri.toString();
/* 44 */     int n = txt.indexOf("?");
/* 45 */     if (n > 0) {
/* 46 */       if (txt.charAt(n - 1) != '/') {
/* 47 */         uri = URI.create(String.valueOf(txt.substring(0, n)) + "/" + txt.substring(n));
/*    */       }
/* 49 */     } else if (!txt.endsWith("/")) {
/* 50 */       uri = URI.create(String.valueOf(txt) + "/");
/*    */     } 
/* 52 */     return uri.resolve(path);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static URI a(URI uri, URI path) {
/* 61 */     String txt = uri.toString();
/* 62 */     int n = txt.indexOf("?");
/* 63 */     if (n > 0) {
/* 64 */       if (txt.charAt(n - 1) != '/') {
/* 65 */         uri = URI.create(String.valueOf(txt.substring(0, n)) + "/" + txt.substring(n));
/*    */       }
/* 67 */     } else if (!txt.endsWith("/")) {
/* 68 */       uri = URI.create(String.valueOf(txt) + "/");
/*    */     } 
/* 70 */     return uri.resolve(path);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/d/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */