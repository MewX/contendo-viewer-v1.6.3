/*     */ package net.a.a.c;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import net.a.a.f;
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
/*     */ public class c
/*     */   implements Serializable, f
/*     */ {
/*     */   private static final long a = 1L;
/*     */   private final Map<d, Object> b;
/*     */   
/*     */   private static final class a
/*     */   {
/*  44 */     private static final c a = new c();
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
/*     */   protected c() {
/*  57 */     this.b = new TreeMap<d, Object>();
/*  58 */     this.b.put(d.b, Float.valueOf(12.0F));
/*  59 */     this.b.put(d.c, Float.valueOf(8.0F));
/*  60 */     this.b.put(d.f, Float.valueOf(10.0F));
/*  61 */     this.b.put(d.d, 
/*  62 */         Float.valueOf(0.71F));
/*  63 */     this.b.put(d.e, Integer.valueOf(0));
/*  64 */     this.b.put(d.a, a.a);
/*  65 */     this.b.put(d.g, Boolean.valueOf(false));
/*  66 */     this.b.put(d.h, Boolean.valueOf(true));
/*  67 */     this.b.put(d.i, Color.BLACK);
/*  68 */     this.b.put(d.j, null);
/*     */     
/*  70 */     ArrayList<String> arrayList1 = new ArrayList(12);
/*  71 */     arrayList1.add("Verdana");
/*  72 */     arrayList1.add("Helvetica");
/*  73 */     arrayList1.add("Arial");
/*  74 */     arrayList1.add("Arial Unicode MS");
/*  75 */     arrayList1.add("Lucida Sans Unicode");
/*  76 */     arrayList1.add("Lucida Sans");
/*  77 */     arrayList1.add("Lucida Grande");
/*  78 */     arrayList1.add("DejaVu Sans");
/*  79 */     arrayList1.add("DejaVuSans");
/*  80 */     arrayList1.add("Bitstream Vera Sans");
/*  81 */     arrayList1.add("Luxi Sans");
/*  82 */     arrayList1.add("FreeSans");
/*  83 */     arrayList1.add("sansserif");
/*  84 */     this.b.put(d.k, 
/*  85 */         Collections.unmodifiableList(arrayList1));
/*     */     
/*  87 */     ArrayList<String> arrayList2 = new ArrayList(10);
/*  88 */     arrayList2.add("Constantina");
/*  89 */     arrayList2.add("Cambria");
/*  90 */     arrayList2.add("Times");
/*  91 */     arrayList2.add("Times New Roman");
/*  92 */     arrayList2.add("Lucida Bright");
/*  93 */     arrayList2.add("DejaVu Serif");
/*  94 */     arrayList2.add("DejaVuSerif");
/*  95 */     arrayList2.add("Bitstream Vera Serif");
/*  96 */     arrayList2.add("Luxi Serif");
/*  97 */     arrayList2.add("FreeSerif");
/*  98 */     arrayList2.add("serif");
/*  99 */     this.b.put(d.l, 
/* 100 */         Collections.unmodifiableList(arrayList2));
/*     */     
/* 102 */     ArrayList<String> arrayList3 = new ArrayList(10);
/* 103 */     arrayList3.add("Andale Mono");
/* 104 */     arrayList3.add("Courier");
/* 105 */     arrayList3.add("Courier Mono");
/* 106 */     arrayList3.add("Courier New");
/* 107 */     arrayList3.add("Lucida Sans Typewriter");
/* 108 */     arrayList3.add("DejaVu Sans Mono");
/* 109 */     arrayList3.add("DejaVuSansMono");
/* 110 */     arrayList3.add("Bitstream Vera Sans Mono");
/* 111 */     arrayList3.add("Luxi Mono");
/* 112 */     arrayList3.add("FreeMono");
/* 113 */     arrayList3.add("monospaced");
/* 114 */     this.b.put(d.m, 
/* 115 */         Collections.unmodifiableList(arrayList3));
/*     */     
/* 117 */     ArrayList<String> arrayList4 = new ArrayList(12);
/* 118 */     arrayList4.add("EUSM10");
/* 119 */     arrayList4.add("cmsy10");
/* 120 */     arrayList4.add("Math5");
/* 121 */     arrayList4.add("Mathematica5");
/* 122 */     arrayList4.add("Savoye LET");
/* 123 */     arrayList4.add("Brush Script MT");
/* 124 */     arrayList4.add("Zapfino");
/* 125 */     arrayList4.add("Apple Chancery");
/* 126 */     arrayList4.add("Edwardian Script ITC");
/* 127 */     arrayList4.add("Lucida Handwriting");
/* 128 */     arrayList4.add("Monotype Corsiva");
/* 129 */     arrayList4.add("Santa Fe LET");
/* 130 */     this.b.put(d.n, 
/* 131 */         Collections.unmodifiableList(arrayList4));
/*     */     
/* 133 */     ArrayList<String> arrayList5 = new ArrayList(7);
/* 134 */     arrayList5.add("EUFM10");
/* 135 */     arrayList5.add("Mathematica6");
/* 136 */     arrayList5.add("FetteFraktur");
/* 137 */     arrayList5.add("Fette Fraktur");
/* 138 */     arrayList5.add("Euclid Fraktur");
/* 139 */     arrayList5.add("Lucida Blackletter");
/* 140 */     arrayList5.add("Blackmoor LET");
/* 141 */     this.b.put(d.o, 
/* 142 */         Collections.unmodifiableList(arrayList5));
/*     */     
/* 144 */     ArrayList<String> arrayList6 = new ArrayList(8);
/* 145 */     arrayList6.add("MSBM10");
/* 146 */     arrayList6.add("Mathematica7");
/* 147 */     arrayList6.add("Caslon Open Face");
/* 148 */     arrayList6.add("Caslon Openface");
/* 149 */     arrayList6.add("Cloister Open Face");
/* 150 */     arrayList6.add("Academy Engraved LET");
/* 151 */     arrayList6.add("Colonna MT");
/* 152 */     arrayList6.add("Imprint MT Shadow");
/* 153 */     this.b.put(d.p, 
/* 154 */         Collections.unmodifiableList(arrayList6));
/*     */     
/* 156 */     this.b.put(d.q, Boolean.FALSE);
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
/*     */   public c(net.a.a.c paramc) {
/* 168 */     if (paramc instanceof c) {
/* 169 */       this
/* 170 */         .b = new TreeMap<d, Object>(((c)paramc).b());
/*     */     } else {
/* 172 */       throw new UnsupportedOperationException("LayoutContextImpl(" + paramc
/* 173 */           .getClass() + ") not supported.");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static net.a.a.c a() {
/* 183 */     return (net.a.a.c)a.a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public net.a.a.c a(d paramd, Object paramObject) {
/* 189 */     if (paramd.a(paramObject)) {
/* 190 */       this.b.put(paramd, paramObject);
/*     */     } else {
/* 192 */       this.b.put(paramd, paramd.b(paramObject.toString()));
/*     */     } 
/* 194 */     return (net.a.a.c)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object a(d paramd) {
/* 199 */     return this.b.get(paramd);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map<d, Object> b() {
/* 209 */     return Collections.unmodifiableMap(this.b);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/c/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */