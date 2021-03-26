/*     */ package jp.cssj.sakae.c.d;
/*     */ 
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import jp.cssj.sakae.c.a.c;
/*     */ import jp.cssj.sakae.c.a.d;
/*     */ import jp.cssj.sakae.c.a.g;
/*     */ import jp.cssj.sakae.c.a.h;
/*     */ import jp.cssj.sakae.c.a.i;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.d.a.a;
/*     */ import jp.cssj.sakae.c.d.a.a.d;
/*     */ import jp.cssj.sakae.c.d.b.a;
/*     */ import jp.cssj.sakae.c.d.b.a.b;
/*     */ import jp.cssj.sakae.c.d.b.a.c;
/*     */ import jp.cssj.sakae.c.d.c.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class j
/*     */   extends a
/*     */ {
/*     */   private final b c;
/*     */   private boolean d = true;
/*  29 */   private c e = c.f;
/*     */   
/*  31 */   private double f = 12.0D;
/*     */   
/*  33 */   private byte g = 1;
/*     */   
/*  35 */   private short h = 400;
/*     */   
/*  37 */   private byte i = 1;
/*     */   
/*  39 */   public static final g a = new g(new byte[] { 1, 3, 2 });
/*     */ 
/*     */ 
/*     */   
/*  43 */   private g j = a;
/*     */ 
/*     */ 
/*     */   
/*     */   private h k;
/*     */ 
/*     */ 
/*     */   
/*     */   private char[] l;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(h fontStyle) {
/*  57 */     this.k = fontStyle;
/*  58 */     this.d = false;
/*  59 */     super.a(fontStyle);
/*     */   }
/*     */   
/*     */   public h b() {
/*  63 */     return this.k;
/*     */   }
/*     */   
/*  66 */   public j(b gc, a hyphenation, e glyphHandler) { this.l = new char[10]; this.c = gc; d d = new d(hyphenation); d.a(glyphHandler); f glypher = gc.a().a(); glypher.a((e)d);
/*  67 */     a(glypher); } private static final Set<TextAttribute> m = new HashSet<>(Arrays.asList(new TextAttribute[] { TextAttribute.FAMILY, TextAttribute.WEIGHT, TextAttribute.SIZE, TextAttribute.POSTURE }));
/*     */ 
/*     */   
/*     */   public void a(AttributedCharacterIterator aci) {
/*  71 */     c defaultFamily = this.e;
/*  72 */     double defaultSize = this.f;
/*  73 */     short defaultWeight = this.h;
/*  74 */     byte defaultStyle = this.g;
/*  75 */     byte defaultDirection = this.i;
/*  76 */     while (aci.current() != Character.MAX_VALUE) {
/*  77 */       a(
/*  78 */           a.a((String)aci.getAttribute(TextAttribute.FAMILY), defaultFamily));
/*  79 */       a(a.a((Float)aci.getAttribute(TextAttribute.WEIGHT), defaultWeight));
/*  80 */       a(a.a((Float)aci.getAttribute(TextAttribute.SIZE), defaultSize));
/*  81 */       a(a.a((Float)aci.getAttribute(TextAttribute.POSTURE), defaultStyle));
/*  82 */       Byte direction = (Byte)aci.getAttribute(a.a);
/*  83 */       b((direction == null) ? defaultDirection : direction.byteValue());
/*     */       
/*  85 */       int nextRun = aci.getRunLimit((Set)m);
/*  86 */       int len = nextRun - aci.getIndex();
/*  87 */       if (len > this.l.length) {
/*  88 */         this.l = new char[len];
/*     */       }
/*  90 */       for (int i = 0; aci.getIndex() != nextRun; i++) {
/*  91 */         this.l[i] = aci.current();
/*  92 */         aci.next();
/*     */       } 
/*     */       
/*  95 */       a(-1, this.l, 0, len);
/*     */     } 
/*  97 */     a(defaultFamily);
/*  98 */     a(defaultSize);
/*  99 */     a(defaultWeight);
/* 100 */     a(defaultStyle);
/* 101 */     b(defaultDirection);
/*     */   }
/*     */   
/*     */   public void c() {
/* 105 */     if (this.d) {
/* 106 */       a((h)new i(this.e, this.f, this.g, this.h, this.i, this.j));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int charOffset, char[] ch, int off, int len) {
/* 112 */     if (len == 0) {
/*     */       return;
/*     */     }
/* 115 */     c();
/* 116 */     int ooff = 0;
/* 117 */     for (int i = 0; i < len; i++) {
/* 118 */       char c1 = ch[i + off];
/* 119 */       g quad = null;
/* 120 */       if (Character.isISOControl(c1)) {
/* 121 */         b b1; c c2; d flm = this.c.a().a(this.k);
/* 122 */         switch (c1) {
/*     */           case '\n':
/* 124 */             b1 = new b(flm, i);
/*     */             break;
/*     */           case '\t':
/* 127 */             c2 = new c(flm, i);
/*     */             break;
/*     */         } 
/* 130 */         if (c2 != null) {
/* 131 */           if (i > ooff) {
/* 132 */             super.a(charOffset + ooff, ch, off + ooff, i - ooff);
/*     */           }
/* 134 */           ooff = i + 1;
/* 135 */           a((g)c2);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 140 */     if (len > ooff) {
/* 141 */       super.a(charOffset + ooff, ch, off + ooff, len - ooff);
/*     */     }
/*     */   }
/*     */   
/*     */   private void e() {
/* 146 */     a();
/* 147 */     this.d = true;
/*     */   }
/*     */   
/*     */   public void a(c families) {
/* 151 */     if (this.e.equals(families)) {
/*     */       return;
/*     */     }
/* 154 */     e();
/* 155 */     this.e = families;
/*     */   }
/*     */   
/*     */   public void a(double size) {
/* 159 */     if (this.f == size) {
/*     */       return;
/*     */     }
/* 162 */     e();
/* 163 */     this.f = size;
/*     */   }
/*     */   
/*     */   public void a(byte style) {
/* 167 */     if (this.g == style) {
/*     */       return;
/*     */     }
/* 170 */     e();
/* 171 */     this.g = style;
/*     */   }
/*     */   
/*     */   public void a(short weight) {
/* 175 */     if (this.h == weight) {
/*     */       return;
/*     */     }
/* 178 */     e();
/* 179 */     this.h = weight;
/*     */   }
/*     */   
/*     */   public void b(byte direction) {
/* 183 */     if (this.i == direction) {
/*     */       return;
/*     */     }
/* 186 */     e();
/* 187 */     this.i = direction;
/*     */   }
/*     */   
/*     */   public void a(g fontPolicy) {
/* 191 */     if (this.j.equals(fontPolicy)) {
/*     */       return;
/*     */     }
/* 194 */     e();
/* 195 */     this.j = fontPolicy;
/*     */   }
/*     */   
/*     */   public void a(String text) throws c {
/* 199 */     char[] ch = text.toCharArray();
/* 200 */     a(-1, ch, 0, ch.length);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/d/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */