/*     */ package net.a.a.c;
/*     */ 
/*     */ import net.a.a.c.a.b;
/*     */ import net.a.a.c.a.c;
/*     */ import net.a.a.c.a.d;
/*     */ import net.a.a.c.a.e;
/*     */ import net.a.a.c.a.g;
/*     */ import net.a.a.c.a.h;
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
/*     */ public enum d
/*     */ {
/*  37 */   a(d.a(a.class), false, "display", "display style"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   b(e.a(Float.class), false, "fontSize", "font size used for the output (mathsize)"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   c(e.a(Float.class), false, "scriptMinSize", "font size to be used for smallest script"),
/*     */ 
/*     */ 
/*     */   
/*  54 */   d(e.a(Float.class), false, "scriptSizeMult", "script size multiplier"),
/*     */ 
/*     */ 
/*     */   
/*  58 */   e(e.a(Integer.class), false, "scriptLevel", "script level"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   f(e.a(Float.class), false, "antiAliasMinSize", "minimum font size for which anti-alias is turned on"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   g(b.b(), false, "debug", "debug mode - if on, elements will have borders drawn around them"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   h(b.b(), false, "antiAlias", "anti-alias mode"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   i(c.b(), false, "foregroundColor", "default foreground color (mathcolor)"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   j(c.b(), true, "backgroundColor", "default background color (mathbackground)"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   k(g.b(), false, "fontsSansSerif", "list of font families for Sans-Serif"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   l(g.b(), false, "fontsSerif", "list of font families for Serif"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   m(g.b(), false, "fontsMonospaced", "list of font families for Monospaced"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   n(g.b(), false, "fontsScript", "list of font families for Script"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   o(g.b(), false, "fontsFraktur", "list of font families for Fraktur"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   p(g.b(), false, "fontsDoublestruck", "list of font families for Double-Struck"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   q(
/* 147 */     b.b(), false, "mfracKeepScriptLevel", "if true, <mfrac> element will NEVER increase children's scriptlevel (in violation of the spec)");
/*     */   
/*     */   public static d[] a() {
/*     */     return (d[])v.clone();
/*     */   }
/*     */   
/*     */   public static d a(String paramString) {
/*     */     return Enum.<d>valueOf(d.class, paramString);
/*     */   }
/*     */   
/*     */   private final h r;
/*     */   private final boolean s;
/*     */   private final String t;
/*     */   private final String u;
/*     */   
/*     */   d(h paramh, boolean paramBoolean, String paramString1, String paramString2) {
/* 163 */     this.r = paramh;
/* 164 */     this.s = paramBoolean;
/* 165 */     this.t = paramString1;
/* 166 */     this.u = paramString2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public h b() {
/* 173 */     return this.r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String c() {
/* 180 */     return this.t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String d() {
/* 187 */     return this.u;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(Object paramObject) {
/* 198 */     return ((paramObject == null && this.s) || this.r.a(paramObject));
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
/*     */   public Object b(String paramString) {
/* 210 */     return this.r.a(paramString);
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
/*     */   public String b(Object paramObject) {
/* 222 */     return this.r.b(paramObject);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/c/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */