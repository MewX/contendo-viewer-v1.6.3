/*     */ package org.cyberneko.html;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class e
/*     */ {
/*     */   protected static final Map a;
/*  43 */   protected static final a b = new a();
/*     */ 
/*     */   
/*     */   static Class c;
/*     */ 
/*     */   
/*     */   static {
/*  50 */     Properties props = new Properties();
/*     */     
/*  52 */     a(props, "res/HTMLlat1.properties");
/*  53 */     a(props, "res/HTMLspecial.properties");
/*  54 */     a(props, "res/HTMLsymbol.properties");
/*  55 */     a(props, "res/XMLbuiltin.properties");
/*     */ 
/*     */     
/*  58 */     Enumeration keys = props.propertyNames();
/*  59 */     while (keys.hasMoreElements()) {
/*  60 */       String key = (String)keys.nextElement();
/*  61 */       String value = props.getProperty(key);
/*  62 */       if (value.length() == 1) {
/*  63 */         int ivalue = value.charAt(0);
/*  64 */         b.a(ivalue, key);
/*     */       } 
/*     */     } 
/*     */     
/*  68 */     a = Collections.unmodifiableMap(new HashMap(props));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int a(String name) {
/*  80 */     String value = (String)a.get(name);
/*  81 */     return (value != null) ? value.charAt(0) : -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String a(int c) {
/*  89 */     return b.a(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void a(Properties props, String filename) {
/*     */     try {
/*  99 */       props.load(((c == null) ? (c = b("org.cyberneko.html.e")) : c).getResourceAsStream(filename));
/*     */     }
/* 101 */     catch (IOException iOException) {
/* 102 */       System.err.println("error: unable to load resource \"" + filename + "\"");
/*     */     } 
/*     */   } static Class b(String x0) {
/*     */     try {
/*     */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/*     */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/* 111 */   static class a { private a[] a = new a[101];
/*     */     public void a(int key, String value) {
/* 113 */       int hash = key % this.a.length;
/* 114 */       a entry = new a(key, value, this.a[hash]);
/* 115 */       this.a[hash] = entry;
/*     */     }
/*     */     public String a(int key) {
/* 118 */       int hash = key % this.a.length;
/* 119 */       a entry = this.a[hash];
/* 120 */       while (entry != null) {
/* 121 */         if (entry.a == key) {
/* 122 */           return entry.b;
/*     */         }
/* 124 */         entry = entry.c;
/*     */       } 
/* 126 */       return null;
/*     */     }
/*     */     static class a { public int a;
/*     */       public String b;
/*     */       public a c;
/*     */       
/*     */       public a(int key, String value, a next) {
/* 133 */         this.a = key;
/* 134 */         this.b = value;
/* 135 */         this.c = next;
/*     */       } }
/*     */      }
/*     */ 
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/e.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */