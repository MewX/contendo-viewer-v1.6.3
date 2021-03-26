/*     */ package net.a.a.e.d.a;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.a.a.c;
/*     */ import net.a.a.e.d.c.b;
/*     */ import net.a.a.f.b;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ public final class d
/*     */   implements Serializable
/*     */ {
/*  48 */   public static final d a = new d(1, b.e);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public static final d b = new d(1, b.d);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public static final d c = new d(3, b.e);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public static final d d = new d(1, b.b);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public static final d e = new d(1, b.c);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public static final d f = new d(0, b.f);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public static final d g = new d(0, b.d);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public static final d h = new d(2, b.e);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public static final d i = new d(0, b.a);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public static final d j = new d(0, b.e);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public static final d k = new d(0, b.b);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public static final d l = new d(3, b.b);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public static final d m = new d(2, b.b);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public static final d n = new d(0, b.c);
/*     */ 
/*     */   
/* 129 */   private static final Map<String, d> o = new HashMap<String, d>();
/*     */   
/* 131 */   private static final Map<b, net.a.a.c.d> p = new HashMap<b, net.a.a.c.d>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final long q = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   private static final Log r = LogFactory.getLog(b.class);
/*     */   
/* 144 */   private static final Set<Integer> s = new HashSet<Integer>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int t;
/*     */ 
/*     */ 
/*     */   
/*     */   private final b u;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public d(int paramInt, b paramb) {
/* 159 */     this.t = paramInt;
/* 160 */     this.u = paramb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static d a(String paramString) {
/* 171 */     synchronized (o) {
/*     */       
/* 173 */       if (o.isEmpty()) {
/* 174 */         o.put("normal", j);
/* 175 */         o.put("bold", a);
/* 176 */         o.put("italic", h);
/* 177 */         o.put("bold-italic", c);
/*     */         
/* 179 */         o.put("double-struck", f);
/*     */         
/* 181 */         o.put("bold-fraktur", b);
/*     */         
/* 183 */         o.put("script", n);
/* 184 */         o.put("bold-script", e);
/*     */         
/* 186 */         o.put("fraktur", g);
/* 187 */         o.put("sans-serif", k);
/*     */         
/* 189 */         o.put("bold-sans-serif", d);
/*     */         
/* 191 */         o.put("sans-serif-italic", m);
/*     */         
/* 193 */         o.put("sans-serif-bold-italic", l);
/*     */         
/* 195 */         o.put("monospace", i);
/*     */       } 
/*     */       
/* 198 */       return o.get(paramString
/* 199 */           .toLowerCase(Locale.ENGLISH));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Font a(float paramFloat, int paramInt, c paramc, boolean paramBoolean) {
/* 222 */     net.a.a.c.d d1 = p.get(this.u);
/* 223 */     Font font = b.b().a((List)paramc
/* 224 */         .a(d1), paramInt, this.t, paramFloat);
/*     */     
/* 226 */     if (paramBoolean && font == null) {
/* 227 */       if (!s.contains(Integer.valueOf(paramInt))) {
/* 228 */         s.add(Integer.valueOf(paramInt));
/* 229 */         String str = Integer.toHexString(paramInt);
/* 230 */         r
/* 231 */           .warn("No font available to display character " + str);
/*     */         
/* 233 */         r
/* 234 */           .info("Find a font at  http://www.fileformat.info/info/unicode/char/" + str + "/fontsupport.htm or http://www.alanwood.net/unicode/search.html");
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 239 */       return b.b().a("sansserif", this.t, paramFloat);
/*     */     } 
/*     */     
/* 242 */     return font;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/* 249 */     return this.t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b b() {
/* 256 */     return this.u;
/*     */   }
/*     */   
/*     */   static {
/* 260 */     p.put(b.e, net.a.a.c.d.l);
/* 261 */     p.put(b.b, net.a.a.c.d.k);
/*     */     
/* 263 */     p.put(b.a, net.a.a.c.d.m);
/*     */     
/* 265 */     p.put(b.c, net.a.a.c.d.n);
/*     */     
/* 267 */     p.put(b.d, net.a.a.c.d.o);
/*     */     
/* 269 */     p.put(b.f, net.a.a.c.d.p);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 278 */     if (this.u == null) {
/* 279 */       i = 0;
/*     */     } else {
/* 281 */       i = this.u.hashCode();
/*     */     } 
/* 283 */     int i = 31 * i + this.t;
/* 284 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 290 */     if (this == paramObject) {
/* 291 */       return true;
/*     */     }
/* 293 */     if (paramObject == null) {
/* 294 */       return false;
/*     */     }
/* 296 */     if (getClass() != paramObject.getClass()) {
/* 297 */       return false;
/*     */     }
/* 299 */     d d1 = (d)paramObject;
/* 300 */     if (this.t != d1.t) {
/* 301 */       return false;
/*     */     }
/* 303 */     if (this.u == null) {
/* 304 */       if (d1.u != null) {
/* 305 */         return false;
/*     */       }
/* 307 */     } else if (!this.u.equals(d1.u)) {
/* 308 */       return false;
/*     */     } 
/* 310 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 316 */     StringBuilder stringBuilder = new StringBuilder();
/* 317 */     stringBuilder.append('[');
/* 318 */     stringBuilder.append(this.u);
/* 319 */     if (this.t > 0) {
/* 320 */       stringBuilder.append(' ');
/*     */     }
/* 322 */     if ((this.t & 0x1) > 0) {
/* 323 */       stringBuilder.append('B');
/*     */     }
/* 325 */     if ((this.t & 0x2) > 0) {
/* 326 */       stringBuilder.append('I');
/*     */     }
/* 328 */     stringBuilder.append(']');
/* 329 */     return stringBuilder.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/a/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */