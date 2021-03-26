/*     */ package com.a.a.j.d;
/*     */ 
/*     */ import com.a.a.d.b;
/*     */ import com.a.a.e.b;
/*     */ import com.a.a.j.c.h;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class e
/*     */ {
/*  17 */   private static final b[] a = new b[] {
/*  18 */       new b("audio/*"), 
/*  19 */       new b("video/*") }; private final List<a> b; private long c;
/*     */   private final String d;
/*     */   private InputStream e;
/*     */   private long f;
/*     */   
/*  24 */   public static class a { long b = -1L; String a;
/*  25 */     long c = -1L;
/*  26 */     InputStream d = null;
/*     */ 
/*     */     
/*     */     public a(String txt, long begin, long end) {
/*  30 */       this.a = String.valueOf(begin) + "-" + end;
/*  31 */       this.b = begin;
/*  32 */       this.c = end;
/*     */     }
/*     */     
/*     */     public long a() {
/*  36 */       return this.c - this.b + 1L;
/*     */     }
/*     */     
/*     */     public String b() {
/*  40 */       return this.a;
/*     */     }
/*     */     
/*     */     public InputStream c() {
/*  44 */       return this.d;
/*     */     }
/*     */     
/*     */     private void a(InputStream istream) {
/*  48 */       this.d = istream;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static e a(h file, boolean zipsupported, String ranges) throws c, IOException {
/*  59 */     e range = null;
/*     */     
/*  61 */     InputStream is = null;
/*  62 */     long size = 0L;
/*  63 */     b mime = file.j();
/*  64 */     String enc = null;
/*     */ 
/*     */     
/*  67 */     LinkedList<a> list = null;
/*  68 */     if (ranges != null) {
/*  69 */       list = new LinkedList<a>();
/*  70 */       int i = 0;
/*  71 */       int m = ranges.length();
/*  72 */       boolean loop = true;
/*     */       
/*  74 */       int n = ranges.indexOf("=");
/*  75 */       if (n >= 0) {
/*  76 */         i = n + 1;
/*     */       }
/*     */       
/*  79 */       for (; loop && i < m; i++) {
/*  80 */         Long start = null;
/*  81 */         Long end = null;
/*  82 */         int s = -1;
/*  83 */         int j = -1;
/*  84 */         for (; i < m; i++) {
/*  85 */           char c = ranges.charAt(i);
/*  86 */           if (c == ',')
/*  87 */             break;  switch (c) {
/*     */             case ' ':
/*  89 */               if (s >= 0 && j < 0) j = i;
/*     */               
/*     */               break;
/*     */             case '-':
/*  93 */               if (start != null) throw new c("Range request error : Syntax error, detect multi '-' chars."); 
/*  94 */               start = a(ranges, s, j, i);
/*  95 */               s = -1;
/*  96 */               j = -1;
/*     */               break;
/*     */             
/*     */             case '0':
/*     */             case '1':
/*     */             case '2':
/*     */             case '3':
/*     */             case '4':
/*     */             case '5':
/*     */             case '6':
/*     */             case '7':
/*     */             case '8':
/*     */             case '9':
/* 109 */               if (j >= 0) throw new c("Range request error : Illegal number format."); 
/* 110 */               if (s < 0) s = i; 
/*     */               break;
/*     */             default:
/* 113 */               loop = false;
/*     */               break;
/*     */           } 
/*     */         
/*     */         } 
/* 118 */         if (start == null) throw new c("Range request error : Start offset not defined."); 
/* 119 */         end = a(ranges, s, j, i);
/* 120 */         if (start.longValue() < 0L) {
/* 121 */           if (end.longValue() < 0L) throw new c("Range request error : Syntax error, Offset not detected.");
/*     */           
/* 123 */           list.add(new a("-" + end.toString(), Math.max(file.f() - end.longValue(), 0L), file.f() - 1L));
/*     */         }
/* 125 */         else if (end.longValue() < 0L) {
/*     */           
/* 127 */           list.add(new a(String.valueOf(start.toString()) + "-", start.longValue(), file.f() - 1L));
/*     */         } else {
/*     */           
/* 130 */           end = Long.valueOf(Math.min(end.longValue(), file.f() - 1L));
/* 131 */           if (start.longValue() > end.longValue()) throw new c("Range request error : Illegal range offset."); 
/* 132 */           list.add(new a(String.valueOf(start.toString()) + "-" + end.toString(), start.longValue(), end.longValue()));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 139 */     if (list == null || list.size() == 0) {
/* 140 */       if (zipsupported) {
/* 141 */         if (file.k()) {
/* 142 */           byte b1; int i; b[] arrayOfB; for (i = (arrayOfB = a).length, b1 = 0; b1 < i; ) { b m = arrayOfB[b1];
/* 143 */             if (mime.equals(m)) {
/* 144 */               zipsupported = false; break;
/*     */             } 
/*     */             b1++; }
/*     */         
/*     */         } else {
/* 149 */           zipsupported = false;
/*     */         } 
/*     */       }
/* 152 */       if (zipsupported) {
/* 153 */         is = file.l();
/* 154 */         size = file.m();
/* 155 */         enc = "gzip";
/*     */       } else {
/* 157 */         is = file.d();
/* 158 */         size = file.f();
/*     */       } 
/*     */     } else {
/*     */       com.a.a.j.e.a a;
/* 162 */       if (list.size() > 1) {
/* 163 */         ListIterator<a> it = list.listIterator();
/* 164 */         a prev = null;
/* 165 */         while (it.hasNext()) {
/* 166 */           a cur = it.next();
/* 167 */           if (prev != null && 
/* 168 */             prev.c + 1L == cur.b) {
/* 169 */             prev.c = cur.c;
/* 170 */             it.remove();
/*     */             
/*     */             continue;
/*     */           } 
/* 174 */           prev = cur;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 179 */       b buf = null;
/*     */       try {
/* 181 */         buf = file.e();
/* 182 */       } catch (IOException iOException) {}
/*     */       
/* 184 */       if (buf == null) a = new com.a.a.j.e.a(file); 
/* 185 */       size = file.f();
/* 186 */       for (a r : list) {
/* 187 */         int n = (int)r.a();
/* 188 */         a.a(r, (InputStream)new com.a.a.e.b.a(a.h((int)r.b).u().g(n)));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 193 */     range = new e(is, list, size, enc);
/*     */     
/* 195 */     return range;
/*     */   }
/*     */   
/*     */   private static Long a(String txt, int s, int j, int i) {
/* 199 */     if (s >= 0) {
/* 200 */       String num = txt.substring(s, (j < 0) ? i : j);
/* 201 */       if (num.length() > 0) {
/* 202 */         return Long.valueOf(Long.parseLong(num));
/*     */       }
/*     */     } 
/* 205 */     return Long.valueOf(-1L);
/*     */   }
/*     */   
/*     */   private e(InputStream istream, List<a> list, long size, String enc) {
/* 209 */     this.e = istream;
/* 210 */     this.b = list;
/* 211 */     this.c = size;
/* 212 */     this.d = enc;
/* 213 */     this.f = this.c;
/* 214 */     if (list != null && list.size() > 0) {
/* 215 */       this.f = 0L;
/* 216 */       for (a r : list) {
/* 217 */         this.f += r.a();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 226 */     StringBuilder sb = new StringBuilder();
/* 227 */     for (int i = 0; i < this.b.size(); i++) {
/* 228 */       if (i > 0) sb.append(","); 
/* 229 */       sb.append(((a)this.b.get(i)).b());
/*     */     } 
/* 231 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public boolean a() {
/* 235 */     return (this.b.size() > 1);
/*     */   }
/*     */   
/*     */   public boolean b() {
/* 239 */     return (this.d != null);
/*     */   }
/*     */   
/*     */   public String c() {
/* 243 */     return this.d;
/*     */   }
/*     */   
/*     */   public long d() {
/* 247 */     return this.c;
/*     */   }
/*     */   
/*     */   public long e() {
/* 251 */     return this.f;
/*     */   }
/*     */   
/*     */   public List<a> f() {
/* 255 */     return Collections.unmodifiableList(this.b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int g() {
/* 262 */     return (this.b == null) ? 0 : this.b.size();
/*     */   }
/*     */   
/*     */   public InputStream h() {
/* 266 */     if (this.e != null) return this.e; 
/* 267 */     if (this.b != null && !this.b.isEmpty()) {
/* 268 */       return ((a)this.b.get(0)).c();
/*     */     }
/* 270 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/d/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */