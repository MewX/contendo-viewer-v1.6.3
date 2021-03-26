/*     */ package jp.cssj.homare.b.b.a;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.a.b.h;
/*     */ import jp.cssj.homare.b.a.b.i;
/*     */ import jp.cssj.homare.b.a.c;
/*     */ import jp.cssj.homare.b.a.c.f;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.c.q;
/*     */ import jp.cssj.homare.b.a.f;
/*     */ import jp.cssj.homare.b.a.i;
/*     */ import jp.cssj.homare.b.b.a;
/*     */ import jp.cssj.homare.b.b.b;
/*     */ import jp.cssj.homare.impl.a.a.c;
/*     */ import jp.cssj.sakae.c.d.e;
/*     */ import jp.cssj.sakae.c.d.f;
/*     */ import jp.cssj.sakae.c.d.g;
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
/*     */ public class j
/*     */ {
/*  33 */   private final List<f> d = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   private final List<g> e = new ArrayList<>();
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
/*  56 */   private f k = null; private static final boolean b = false; private final a c; private c f;
/*     */   
/*     */   public j(a builder) {
/*  59 */     this.c = builder;
/*     */   }
/*     */   private boolean g; private boolean h; private char i; private double j;
/*     */   private f f() {
/*  63 */     return this.d.get(this.d.size() - 1);
/*     */   }
/*     */   
/*     */   public void a() {
/*  67 */     if (this.k != null) {
/*     */       return;
/*     */     }
/*  70 */     f params = f();
/*  71 */     c c1 = new c(params.G);
/*  72 */     c1.a(this.f);
/*  73 */     this.k = params.F.a();
/*  74 */     this.k.a((e)c1);
/*  75 */     this.k.a(params.C);
/*     */   }
/*     */   
/*     */   private void a(f params) {
/*  79 */     this.j = params.I;
/*  80 */     switch (params.K) {
/*     */       case 2:
/*  82 */         this.g = false;
/*  83 */         this.h = true;
/*     */         return;
/*     */       
/*     */       case 3:
/*  87 */         this.g = true;
/*  88 */         this.h = false;
/*     */         return;
/*     */       
/*     */       case 1:
/*  92 */         this.g = true;
/*  93 */         this.h = false;
/*     */         return;
/*     */       
/*     */       case 5:
/*  97 */         this.g = true;
/*  98 */         this.h = true;
/*     */         return;
/*     */       
/*     */       case 4:
/* 102 */         this.g = false;
/* 103 */         this.h = true;
/*     */         return;
/*     */     } 
/* 106 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void b() {
/* 111 */     this.i = ' ';
/* 112 */     i params = this.c.k().c_();
/* 113 */     this.d.add(params);
/*     */ 
/*     */ 
/*     */     
/* 117 */     if (this.f == null) {
/* 118 */       this.f = new c(this.c);
/*     */     }
/* 120 */     else if (this.d.size() > 1) {
/* 121 */       this.f.a((f)params);
/*     */     } else {
/* 123 */       this.f.d();
/*     */     } 
/*     */     
/* 126 */     a((f)params);
/*     */   }
/*     */   
/*     */   public void c() {
/* 130 */     if (this.k != null) {
/* 131 */       this.k.a();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/* 137 */     f params = this.d.remove(this.d.size() - 1);
/* 138 */     if (this.k != null) {
/* 139 */       this.k.a();
/* 140 */       this.k = null;
/* 141 */       this.f.a.f();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 146 */     if (this.d.size() >= 1) {
/* 147 */       this.f.c();
/*     */     }
/*     */   }
/*     */   
/*     */   public void a(i inlineBox) {
/* 152 */     c containerBox = this.f.a.k();
/* 153 */     inlineBox.a(containerBox);
/* 154 */     a();
/*     */     
/* 156 */     b b1 = b.b(inlineBox);
/* 157 */     this.e.add(b1);
/* 158 */     q q = inlineBox.f();
/* 159 */     this.d.add(q);
/* 160 */     this.k.a(((f)q).C);
/* 161 */     b b2 = b.a(inlineBox);
/* 162 */     this.k.a((g)b2);
/* 163 */     a((f)q);
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/* 168 */     a();
/*     */     
/* 170 */     b.c c1 = (b.c)this.e.remove(this.e.size() - 1);
/* 171 */     this.k.a((g)c1);
/* 172 */     this.d.remove(this.d.size() - 1);
/* 173 */     f params = f();
/* 174 */     this.k.a(params.C);
/* 175 */     a(params);
/*     */   }
/*     */   
/*     */   public void a(f inlineReplacedBox) {
/* 179 */     a();
/* 180 */     b b = b.a(inlineReplacedBox);
/* 181 */     this.k.a((g)b);
/* 182 */     this.i = 'x';
/*     */   }
/*     */   
/*     */   public void a(h inlineBlockBox) {
/* 186 */     a();
/* 187 */     b b = b.a(inlineBlockBox);
/* 188 */     this.k.a((g)b);
/* 189 */     this.i = 'x';
/*     */   }
/*     */   
/*     */   public void a(i absoluteBox) {
/* 193 */     a();
/* 194 */     b b = b.a(absoluteBox);
/* 195 */     this.k.a((g)b);
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
/*     */   public void a(int charOffset, char[] ch, int off, int len, boolean lineFeed) {
/*     */     // Byte code:
/*     */     //   0: getstatic jp/cssj/homare/b/b/a/j.a : Z
/*     */     //   3: ifne -> 19
/*     */     //   6: iload #4
/*     */     //   8: ifgt -> 19
/*     */     //   11: new java/lang/AssertionError
/*     */     //   14: dup
/*     */     //   15: invokespecial <init> : ()V
/*     */     //   18: athrow
/*     */     //   19: aload_0
/*     */     //   20: invokespecial f : ()Ljp/cssj/homare/b/a/c/f;
/*     */     //   23: astore #6
/*     */     //   25: iconst_0
/*     */     //   26: istore #7
/*     */     //   28: aload #6
/*     */     //   30: invokevirtual a : ()Ljp/cssj/sakae/c/a/d;
/*     */     //   33: astore #8
/*     */     //   35: iconst_0
/*     */     //   36: istore #9
/*     */     //   38: iload #9
/*     */     //   40: iload #4
/*     */     //   42: if_icmpge -> 415
/*     */     //   45: aload_2
/*     */     //   46: iload #9
/*     */     //   48: iload_3
/*     */     //   49: iadd
/*     */     //   50: caload
/*     */     //   51: istore #10
/*     */     //   53: iload #10
/*     */     //   55: invokestatic b : (C)Z
/*     */     //   58: ifeq -> 293
/*     */     //   61: aconst_null
/*     */     //   62: astore #11
/*     */     //   64: iload #10
/*     */     //   66: lookupswitch default -> 229, 9 -> 207, 10 -> 92
/*     */     //   92: iload #5
/*     */     //   94: ifne -> 104
/*     */     //   97: aload_0
/*     */     //   98: getfield h : Z
/*     */     //   101: ifeq -> 122
/*     */     //   104: new jp/cssj/sakae/c/d/b/a/b
/*     */     //   107: dup
/*     */     //   108: aload #8
/*     */     //   110: iload_1
/*     */     //   111: iload #9
/*     */     //   113: iadd
/*     */     //   114: invokespecial <init> : (Ljp/cssj/sakae/c/a/d;I)V
/*     */     //   117: astore #11
/*     */     //   119: goto -> 229
/*     */     //   122: aload_0
/*     */     //   123: getfield g : Z
/*     */     //   126: ifeq -> 229
/*     */     //   129: aload_0
/*     */     //   130: getfield i : C
/*     */     //   133: invokestatic of : (C)Ljava/lang/Character$UnicodeBlock;
/*     */     //   136: astore #12
/*     */     //   138: aload #12
/*     */     //   140: getstatic java/lang/Character$UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION : Ljava/lang/Character$UnicodeBlock;
/*     */     //   143: if_acmpeq -> 170
/*     */     //   146: aload #12
/*     */     //   148: getstatic java/lang/Character$UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS : Ljava/lang/Character$UnicodeBlock;
/*     */     //   151: if_acmpeq -> 170
/*     */     //   154: aload #12
/*     */     //   156: getstatic java/lang/Character$UnicodeBlock.HIRAGANA : Ljava/lang/Character$UnicodeBlock;
/*     */     //   159: if_acmpeq -> 170
/*     */     //   162: aload #12
/*     */     //   164: getstatic java/lang/Character$UnicodeBlock.KATAKANA : Ljava/lang/Character$UnicodeBlock;
/*     */     //   167: if_acmpne -> 204
/*     */     //   170: iload #9
/*     */     //   172: iload #7
/*     */     //   174: if_icmple -> 195
/*     */     //   177: aload_0
/*     */     //   178: iload_1
/*     */     //   179: iload #7
/*     */     //   181: iadd
/*     */     //   182: aload_2
/*     */     //   183: iload_3
/*     */     //   184: iload #7
/*     */     //   186: iadd
/*     */     //   187: iload #9
/*     */     //   189: iload #7
/*     */     //   191: isub
/*     */     //   192: invokespecial a : (I[CII)V
/*     */     //   195: iload #9
/*     */     //   197: iconst_1
/*     */     //   198: iadd
/*     */     //   199: istore #7
/*     */     //   201: goto -> 409
/*     */     //   204: goto -> 229
/*     */     //   207: aload_0
/*     */     //   208: getfield g : Z
/*     */     //   211: ifne -> 229
/*     */     //   214: new jp/cssj/sakae/c/d/b/a/c
/*     */     //   217: dup
/*     */     //   218: aload #8
/*     */     //   220: iload_1
/*     */     //   221: iload #9
/*     */     //   223: iadd
/*     */     //   224: invokespecial <init> : (Ljp/cssj/sakae/c/a/d;I)V
/*     */     //   227: astore #11
/*     */     //   229: aload #11
/*     */     //   231: ifnull -> 289
/*     */     //   234: iload #9
/*     */     //   236: iload #7
/*     */     //   238: if_icmple -> 259
/*     */     //   241: aload_0
/*     */     //   242: iload_1
/*     */     //   243: iload #7
/*     */     //   245: iadd
/*     */     //   246: aload_2
/*     */     //   247: iload_3
/*     */     //   248: iload #7
/*     */     //   250: iadd
/*     */     //   251: iload #9
/*     */     //   253: iload #7
/*     */     //   255: isub
/*     */     //   256: invokespecial a : (I[CII)V
/*     */     //   259: iload #9
/*     */     //   261: iconst_1
/*     */     //   262: iadd
/*     */     //   263: istore #7
/*     */     //   265: aload_0
/*     */     //   266: invokevirtual a : ()V
/*     */     //   269: aload_0
/*     */     //   270: getfield k : Ljp/cssj/sakae/c/d/f;
/*     */     //   273: aload #11
/*     */     //   275: invokeinterface a : (Ljp/cssj/sakae/c/d/g;)V
/*     */     //   280: aload_0
/*     */     //   281: iload #10
/*     */     //   283: putfield i : C
/*     */     //   286: goto -> 409
/*     */     //   289: bipush #32
/*     */     //   291: istore #10
/*     */     //   293: iload #10
/*     */     //   295: bipush #32
/*     */     //   297: if_icmpne -> 395
/*     */     //   300: iload #9
/*     */     //   302: iload #7
/*     */     //   304: if_icmple -> 325
/*     */     //   307: aload_0
/*     */     //   308: iload_1
/*     */     //   309: iload #7
/*     */     //   311: iadd
/*     */     //   312: aload_2
/*     */     //   313: iload_3
/*     */     //   314: iload #7
/*     */     //   316: iadd
/*     */     //   317: iload #9
/*     */     //   319: iload #7
/*     */     //   321: isub
/*     */     //   322: invokespecial a : (I[CII)V
/*     */     //   325: iload #9
/*     */     //   327: iconst_1
/*     */     //   328: iadd
/*     */     //   329: istore #7
/*     */     //   331: aload_0
/*     */     //   332: getfield i : C
/*     */     //   335: bipush #32
/*     */     //   337: if_icmpne -> 347
/*     */     //   340: aload_0
/*     */     //   341: getfield g : Z
/*     */     //   344: ifne -> 386
/*     */     //   347: new jp/cssj/sakae/c/d/b/a/d
/*     */     //   350: dup
/*     */     //   351: aload #8
/*     */     //   353: iload_1
/*     */     //   354: iload #9
/*     */     //   356: iadd
/*     */     //   357: invokespecial <init> : (Ljp/cssj/sakae/c/a/d;I)V
/*     */     //   360: astore #11
/*     */     //   362: aload #11
/*     */     //   364: aload_0
/*     */     //   365: getfield j : D
/*     */     //   368: invokevirtual a : (D)V
/*     */     //   371: aload_0
/*     */     //   372: invokevirtual a : ()V
/*     */     //   375: aload_0
/*     */     //   376: getfield k : Ljp/cssj/sakae/c/d/f;
/*     */     //   379: aload #11
/*     */     //   381: invokeinterface a : (Ljp/cssj/sakae/c/d/g;)V
/*     */     //   386: aload_0
/*     */     //   387: iload #10
/*     */     //   389: putfield i : C
/*     */     //   392: goto -> 409
/*     */     //   395: aload_0
/*     */     //   396: iload #10
/*     */     //   398: putfield i : C
/*     */     //   401: aload_2
/*     */     //   402: iload #9
/*     */     //   404: iload_3
/*     */     //   405: iadd
/*     */     //   406: iload #10
/*     */     //   408: castore
/*     */     //   409: iinc #9, 1
/*     */     //   412: goto -> 38
/*     */     //   415: iload #4
/*     */     //   417: iload #7
/*     */     //   419: if_icmple -> 440
/*     */     //   422: aload_0
/*     */     //   423: iload_1
/*     */     //   424: iload #7
/*     */     //   426: iadd
/*     */     //   427: aload_2
/*     */     //   428: iload_3
/*     */     //   429: iload #7
/*     */     //   431: iadd
/*     */     //   432: iload #4
/*     */     //   434: iload #7
/*     */     //   436: isub
/*     */     //   437: invokespecial a : (I[CII)V
/*     */     //   440: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #199	-> 0
/*     */     //   #200	-> 19
/*     */     //   #203	-> 25
/*     */     //   #204	-> 28
/*     */     //   #205	-> 35
/*     */     //   #206	-> 45
/*     */     //   #207	-> 53
/*     */     //   #208	-> 61
/*     */     //   #209	-> 64
/*     */     //   #212	-> 92
/*     */     //   #213	-> 104
/*     */     //   #214	-> 122
/*     */     //   #215	-> 129
/*     */     //   #216	-> 138
/*     */     //   #220	-> 170
/*     */     //   #221	-> 177
/*     */     //   #223	-> 195
/*     */     //   #224	-> 201
/*     */     //   #226	-> 204
/*     */     //   #230	-> 207
/*     */     //   #231	-> 214
/*     */     //   #235	-> 229
/*     */     //   #237	-> 234
/*     */     //   #238	-> 241
/*     */     //   #240	-> 259
/*     */     //   #241	-> 265
/*     */     //   #242	-> 269
/*     */     //   #243	-> 280
/*     */     //   #244	-> 286
/*     */     //   #247	-> 289
/*     */     //   #249	-> 293
/*     */     //   #251	-> 300
/*     */     //   #252	-> 307
/*     */     //   #254	-> 325
/*     */     //   #255	-> 331
/*     */     //   #257	-> 347
/*     */     //   #258	-> 362
/*     */     //   #259	-> 371
/*     */     //   #260	-> 375
/*     */     //   #262	-> 386
/*     */     //   #263	-> 392
/*     */     //   #265	-> 395
/*     */     //   #266	-> 401
/*     */     //   #205	-> 409
/*     */     //   #268	-> 415
/*     */     //   #269	-> 422
/*     */     //   #271	-> 440
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	441	0	this	Ljp/cssj/homare/b/b/a/j;
/*     */     //   0	441	1	charOffset	I
/*     */     //   0	441	2	ch	[C
/*     */     //   0	441	3	off	I
/*     */     //   0	441	4	len	I
/*     */     //   0	441	5	lineFeed	Z
/*     */     //   25	416	6	params	Ljp/cssj/homare/b/a/c/f;
/*     */     //   28	413	7	ooff	I
/*     */     //   35	406	8	flm	Ljp/cssj/sakae/c/a/d;
/*     */     //   38	377	9	i	I
/*     */     //   53	356	10	c	C
/*     */     //   64	229	11	quad	Ljp/cssj/sakae/c/d/g;
/*     */     //   138	66	12	block	Ljava/lang/Character$UnicodeBlock;
/*     */     //   362	24	11	ws	Ljp/cssj/sakae/c/d/b/a/d;
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
/*     */   private void a(int charOffset, char[] ch, int off, int len) {
/*     */     int i;
/*     */     boolean spaceBefore;
/*     */     int k;
/* 274 */     switch ((f()).J) {
/*     */       case 3:
/* 276 */         for (i = 0; i < len; i++) {
/* 277 */           char c1 = ch[i + off];
/* 278 */           ch[i + off] = Character.toLowerCase(c1);
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 282 */         for (i = 0; i < len; i++) {
/* 283 */           char c1 = ch[i + off];
/* 284 */           ch[i + off] = Character.toUpperCase(c1);
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 288 */         spaceBefore = true;
/* 289 */         for (k = 0; k < len; k++) {
/* 290 */           char c1 = ch[k + off];
/* 291 */           if (Character.isLetter(c1)) {
/* 292 */             if (spaceBefore) {
/* 293 */               ch[k + off] = Character.toUpperCase(c1);
/*     */             }
/* 295 */             spaceBefore = false;
/*     */           } else {
/* 297 */             spaceBefore = true;
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 0:
/*     */         break;
/*     */       default:
/* 304 */         throw new IllegalStateException();
/*     */     } 
/* 306 */     a();
/* 307 */     this.k.a(charOffset, ch, off, len);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/b/a/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */