/*     */ package net.a.a.d;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.xmlgraphics.util.Service;
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
/*     */ public final class f
/*     */ {
/*     */   private static final class a
/*     */   {
/*  38 */     private static final f a = new f();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   private final Map<String, e> a = new HashMap<String, e>();
/*     */   
/*  46 */   private final Map<String, String> b = new HashMap<String, String>();
/*     */   
/*  48 */   private final Map<String, String> c = new HashMap<String, String>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected f() {
/*  56 */     Iterator<d> iterator = Service.providers(d.class);
/*  57 */     while (iterator.hasNext()) {
/*  58 */       d d = iterator.next();
/*  59 */       d.a(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static f a() {
/*  69 */     return a.a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static f b() {
/*  80 */     return a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> c() {
/*  89 */     return this.a.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> d() {
/*  98 */     HashSet<String> hashSet = new HashSet();
/*  99 */     for (Map.Entry<String, String> entry : this.c
/* 100 */       .entrySet()) {
/* 101 */       if (this.a.containsKey(entry.getValue())) {
/* 102 */         hashSet.add(entry.getKey());
/*     */       }
/*     */     } 
/* 105 */     return hashSet;
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
/*     */ 
/*     */   
/*     */   public String a(String paramString) {
/* 119 */     return this.b.get(paramString.toLowerCase(Locale.ENGLISH));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String b(String paramString) {
/* 130 */     return this.c.get(paramString.toLowerCase(Locale.ENGLISH));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String paramString1, String paramString2, boolean paramBoolean) {
/* 147 */     String str1 = paramString1.toLowerCase(Locale.ENGLISH);
/* 148 */     String str2 = paramString2.toLowerCase(Locale.ENGLISH);
/*     */     
/* 150 */     if (paramBoolean || !this.c.containsKey(str2)) {
/* 151 */       this.c.put(str2, str1);
/*     */     }
/* 153 */     if (paramBoolean || !this.b.containsKey(str1)) {
/* 154 */       this.b.put(str1, str2);
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String paramString, e parame, boolean paramBoolean) {
/* 172 */     if (paramBoolean || !this.a.containsKey(paramString)) {
/* 173 */       this.a.put(paramString, parame);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public e c(String paramString) {
/* 185 */     return this.a
/* 186 */       .get(paramString.toLowerCase(Locale.ENGLISH));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/d/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */