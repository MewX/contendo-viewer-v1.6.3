/*     */ package net.a.a.e.d.b;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.EnumMap;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ public abstract class a
/*     */   implements Serializable, c
/*     */ {
/*     */   private static final long m = 1L;
/*     */   private final Map<b, Map<String, Map<f, String>>> n;
/*     */   
/*     */   protected a() {
/*  48 */     this.n = new EnumMap<b, Map<String, Map<f, String>>>(b.class);
/*     */     
/*  50 */     a(this.n);
/*  51 */     a();
/*     */   }
/*     */ 
/*     */   
/*     */   private void a() {
/*  56 */     Map map = this.n.get(b.f);
/*  57 */     for (Map.Entry entry : map
/*  58 */       .entrySet()) {
/*  59 */       String str = g.a((String)entry
/*  60 */           .getKey());
/*  61 */       if (str != null) {
/*  62 */         for (Map.Entry entry1 : ((Map)entry.getValue())
/*  63 */           .entrySet()) {
/*  64 */           if (Boolean.parseBoolean((String)entry1.getValue())) {
/*  65 */             entry1.setValue(str);
/*     */           }
/*     */         } 
/*     */       }
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
/*     */   protected static c a(String paramString) {
/*  80 */     a a1 = null;
/*     */     
/*     */     try {
/*  83 */       InputStream inputStream = a.class.getResourceAsStream(paramString);
/*  84 */       ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
/*  85 */       a1 = (a)objectInputStream.readObject();
/*  86 */       objectInputStream.close();
/*  87 */     } catch (ClassNotFoundException classNotFoundException) {
/*  88 */       a1 = null;
/*  89 */     } catch (IllegalArgumentException illegalArgumentException) {
/*  90 */       a1 = null;
/*  91 */     } catch (IOException iOException) {
/*  92 */       a1 = null;
/*  93 */     } catch (NullPointerException nullPointerException) {
/*  94 */       a1 = null;
/*     */     } 
/*  96 */     return a1;
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
/*     */   protected abstract void a(Map<b, Map<String, Map<f, String>>> paramMap);
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
/*     */   public String a(String paramString1, String paramString2, String paramString3) throws h {
/* 124 */     f f = f.b(paramString2);
/* 125 */     return a(paramString1, f, 
/* 126 */         b.b(paramString3));
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
/*     */ 
/*     */ 
/*     */   
/*     */   private String a(String paramString, f paramf, b paramb) {
/*     */     String str;
/* 147 */     Map map1 = this.n.get(paramb);
/* 148 */     if (map1 == null) {
/* 149 */       return paramb.b();
/*     */     }
/* 151 */     Map map2 = (Map)map1.get(paramString);
/*     */     
/* 153 */     if (map2 == null) {
/* 154 */       str = paramb.b();
/*     */     } else {
/* 156 */       str = (String)map2.get(paramf);
/* 157 */       if (str == null) {
/* 158 */         str = (String)map2.get(f.b);
/*     */       }
/* 160 */       if (str == null) {
/* 161 */         str = (String)map2.get(f.c);
/*     */       }
/* 163 */       if (str == null) {
/* 164 */         str = (String)map2.get(f.a);
/*     */       }
/* 166 */       if (str == null) {
/* 167 */         str = paramb.b();
/*     */       }
/*     */     } 
/* 170 */     return str;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/b/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */