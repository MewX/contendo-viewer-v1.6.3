/*     */ package jp.cssj.homare.css;
/*     */ 
/*     */ import jp.cssj.homare.css.c.f;
/*     */ import jp.cssj.homare.css.c.j;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.impl.a.c.F;
/*     */ import jp.cssj.homare.impl.a.c.I;
/*     */ import jp.cssj.homare.impl.a.c.K;
/*     */ import jp.cssj.homare.impl.a.c.w;
/*     */ import jp.cssj.homare.impl.a.c.x;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.c.a.g;
/*     */ import jp.cssj.sakae.c.a.h;
/*     */ import jp.cssj.sakae.c.a.i;
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
/*     */ public class c
/*     */ {
/*     */   static class a
/*     */     extends c
/*     */   {
/*     */     public boolean g() {
/*  42 */       return true;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  46 */       return "ANON:" + super.toString();
/*     */     }
/*     */   }
/*     */   
/*     */   static class b extends a {
/*     */     public boolean h() {
/*  52 */       return true;
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
/*  71 */   private ad[] h = null;
/*  72 */   private ad[] i = null;
/*  73 */   private boolean[] j = null;
/*  74 */   private h k = null; public static final byte a = 0; public static final byte b = -1; public static final byte c = 1;
/*     */   
/*     */   public static c a(m ua, c parentStyle, a ce) {
/*  77 */     c style = new c();
/*  78 */     style.a(ce, ua, parentStyle);
/*  79 */     return style;
/*     */   }
/*     */   private a e; private m f; private c g;
/*     */   private static c a(a anone, m ua, c parentStyle, boolean inserted) {
/*     */     a style;
/*  84 */     if (inserted) {
/*  85 */       style = new b();
/*     */     } else {
/*  87 */       style = new a();
/*     */     } 
/*  89 */     style.a(anone, ua, parentStyle);
/*  90 */     return style;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(a ce, m ua, c parentStyle) {
/*  98 */     this.e = ce;
/*  99 */     this.f = ua;
/* 100 */     this.g = parentStyle;
/*     */   }
/*     */   
/*     */   public a a() {
/* 104 */     return this.e;
/*     */   }
/*     */   
/*     */   public m b() {
/* 108 */     return this.f;
/*     */   }
/*     */   
/*     */   public c c() {
/* 112 */     return this.g;
/*     */   }
/*     */   
/*     */   public c d() {
/* 116 */     if (this.g == null) {
/* 117 */       return this;
/*     */     }
/* 119 */     return this.g.d();
/*     */   }
/*     */   
/*     */   public c e() {
/* 123 */     if (!g()) {
/* 124 */       return this;
/*     */     }
/* 126 */     return this.g.e();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c a(a anone) {
/* 135 */     return this.g = a(anone, this.f, this.g, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c b(a anone) {
/* 144 */     return a(anone, this.f, this, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void f() {
/* 151 */     if (!d && !this.g.g()) throw new AssertionError(); 
/* 152 */     this.g = this.g.g;
/*     */   }
/*     */   
/*     */   public boolean g() {
/* 156 */     return false;
/*     */   }
/*     */   
/*     */   public boolean h() {
/* 160 */     return false;
/*     */   }
/*     */   
/*     */   public ad a(j info) {
/* 164 */     ad value = null;
/* 165 */     short code = f.a(info);
/* 166 */     if (code == -1) {
/* 167 */       return info.b(this);
/*     */     }
/* 169 */     if (this.i != null) {
/* 170 */       value = this.i[code];
/*     */     }
/* 172 */     if (value != null) {
/* 173 */       return value;
/*     */     }
/* 175 */     if (this.h != null) {
/* 176 */       value = this.h[code];
/*     */     }
/* 178 */     if (value != null) {
/*     */       
/* 180 */       this.h[code] = null;
/* 181 */       if (value.a() == 1003)
/*     */       {
/* 183 */         value = (this.g != null) ? this.g.a(info) : info.b(this);
/*     */       }
/*     */     } 
/* 186 */     if (value == null)
/*     */     {
/*     */       
/* 189 */       value = (this.g != null && info.c()) ? this.g.a(info) : info.b(this);
/*     */     }
/*     */     
/* 192 */     value = info.a(value, this);
/* 193 */     if (this.i == null) {
/* 194 */       this.i = new ad[f.a()];
/*     */     }
/* 196 */     this.i[code] = value;
/* 197 */     return value;
/*     */   }
/*     */   
/*     */   public void a(j info, ad value) {
/* 201 */     a(info, value, (byte)0);
/*     */   }
/*     */   
/*     */   public void a(j info, ad value, byte mode) {
/* 205 */     short code = f.a(info);
/* 206 */     if (code == -1) {
/* 207 */       this.f.a((short)10242, info.b());
/*     */       return;
/*     */     } 
/* 210 */     if (mode == 1) {
/* 211 */       if (this.j == null) {
/* 212 */         this.j = new boolean[f.a()];
/*     */       }
/* 214 */       this.j[code] = true;
/*     */     }
/* 216 */     else if (this.j != null && this.j[code]) {
/*     */       return;
/*     */     } 
/*     */     
/* 220 */     if (this.h == null) {
/* 221 */       this.h = new ad[f.a()];
/*     */     }
/* 223 */     if (mode == -1 && 
/* 224 */       this.h[code] != null) {
/*     */       return;
/*     */     }
/*     */     
/* 228 */     this.h[code] = value;
/* 229 */     if (this.i != null) {
/* 230 */       this.i[code] = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public h i() {
/* 235 */     if (this.k != null) {
/* 236 */       return this.k;
/*     */     }
/* 238 */     jp.cssj.sakae.c.a.c family = w.c(this);
/* 239 */     double size = I.c(this);
/* 240 */     byte style = x.c(this);
/* 241 */     short weight = K.c(this);
/* 242 */     byte direction = F.c(this);
/* 243 */     g policy = jp.cssj.homare.impl.a.c.b.c.c(this);
/*     */     
/* 245 */     this.k = (h)new i(family, size, style, weight, direction, policy);
/* 246 */     return this.k;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 250 */     StringBuffer buff = new StringBuffer(super.toString());
/* 251 */     buff.append("\n").append(this.e).append("\n");
/* 252 */     if (this.h != null) {
/* 253 */       buff.append("values[");
/* 254 */       for (int i = 0; i < this.h.length; i++) {
/* 255 */         ad value = this.h[i];
/* 256 */         if (value != null)
/*     */         {
/*     */           
/* 259 */           buff.append(value).append(";"); } 
/*     */       } 
/* 261 */       buff.deleteCharAt(buff.length() - 1);
/* 262 */       buff.append("]\n");
/*     */     } 
/* 264 */     if (this.i != null) {
/* 265 */       buff.append("computed values[");
/* 266 */       for (int i = 0; i < this.i.length; i++) {
/* 267 */         ad value = this.i[i];
/* 268 */         if (value != null)
/*     */         {
/*     */           
/* 271 */           buff.append(value).append(";"); } 
/*     */       } 
/* 273 */       buff.deleteCharAt(buff.length() - 1);
/* 274 */       buff.append("]\n");
/*     */     } 
/* 276 */     buff.deleteCharAt(buff.length() - 1);
/* 277 */     return buff.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String j() {
/* 283 */     StringBuffer disp = new StringBuffer(String.valueOf(this.e.C));
/* 284 */     if (g()) {
/* 285 */       disp.insert(0, '(');
/* 286 */       disp.append(')');
/*     */     } 
/* 288 */     if (this.g == null) {
/* 289 */       return disp.toString();
/*     */     }
/* 291 */     return this.g.j() + "/" + disp;
/*     */   }
/*     */   
/*     */   private c() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */