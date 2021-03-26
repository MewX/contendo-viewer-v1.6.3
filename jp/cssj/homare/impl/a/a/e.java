/*     */ package jp.cssj.homare.impl.a.a;
/*     */ 
/*     */ import jp.cssj.homare.css.b.a;
/*     */ import jp.cssj.homare.css.c;
/*     */ import jp.cssj.homare.css.f.Q;
/*     */ import jp.cssj.homare.css.f.Z;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.ae;
/*     */ import jp.cssj.homare.css.f.b.a;
/*     */ import jp.cssj.homare.impl.a.c.a.I;
/*     */ import jp.cssj.homare.impl.a.c.b.a;
/*     */ import jp.cssj.homare.impl.a.c.b.d;
/*     */ import jp.cssj.sakae.c.d.a.a;
/*     */ import jp.cssj.sakae.c.d.a.a.c;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class e
/*     */   implements a
/*     */ {
/*  23 */   private static final ae a = new ae(new ad[] { (ad)new Q("「", "」"), (ad)new Q("『", "』") });
/*     */ 
/*     */   
/*  26 */   private final a b = (a)new c();
/*     */   
/*  28 */   private final a c = new a();
/*     */   
/*  30 */   private final a d = (a)new d();
/*     */   
/*     */   public String a() {
/*  33 */     return "ja";
/*     */   }
/*     */   
/*     */   public boolean a(char c) {
/*  37 */     if (c == '　' || c == ' ') {
/*  38 */       return false;
/*     */     }
/*     */     
/*  41 */     return Character.isWhitespace(c);
/*     */   }
/*     */   
/*     */   public int a(char[] ch, int off, int len) {
/*  45 */     int i = 0;
/*     */ 
/*     */     
/*  48 */     for (; i < len && 
/*  49 */       a(ch[off + i]); i++);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  55 */     short state = 0;
/*  56 */     for (; i < len; i++) {
/*  57 */       int type = Character.getType(ch[off + i]);
/*  58 */       switch (state) {
/*     */         case 0:
/*  60 */           switch (type) {
/*     */             
/*     */             case 21:
/*     */             case 22:
/*     */             case 24:
/*  65 */               state = 0;
/*     */               break;
/*     */ 
/*     */ 
/*     */             
/*     */             case 9:
/*     */             case 10:
/*     */             case 11:
/*  73 */               state = 1;
/*     */               break;
/*     */           } 
/*     */ 
/*     */           
/*  78 */           return i + 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 1:
/*  85 */           switch (type) {
/*     */             case 9:
/*     */             case 10:
/*     */             case 11:
/*     */               break;
/*     */           } 
/*     */           
/*  92 */           return i;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/*  99 */     return len;
/*     */   }
/*     */   
/*     */   public ae b() {
/* 103 */     return a; } public void a(Z transform, char[] ch, int off, int len) {
/*     */     boolean spaceBefore;
/*     */     int i;
/*     */     int j;
/* 107 */     switch (transform.b()) {
/*     */       case 1:
/* 109 */         spaceBefore = true;
/* 110 */         for (j = 0; j < len; j++) {
/* 111 */           char c = ch[j + off];
/* 112 */           if (Character.isLetter(c)) {
/* 113 */             if (spaceBefore) {
/* 114 */               ch[j + off] = Character.toUpperCase(c);
/*     */             }
/* 116 */             spaceBefore = false;
/*     */           } else {
/* 118 */             spaceBefore = true;
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 125 */         for (i = 0; i < len; i++) {
/* 126 */           ch[i + off] = Character.toLowerCase(ch[i + off]);
/*     */         }
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 132 */         for (i = 0; i < len; i++) {
/* 133 */           ch[i + off] = Character.toUpperCase(ch[i + off]);
/*     */         }
/*     */ 
/*     */       
/*     */       case 0:
/*     */         return;
/*     */     } 
/*     */ 
/*     */     
/* 142 */     throw new IllegalStateException();
/*     */   }
/*     */   
/*     */   public a a(c style) {
/*     */     a include;
/*     */     a exclude;
/* 148 */     switch (I.c(style)) {
/*     */       case 1:
/* 150 */         include = d.c(style);
/* 151 */         exclude = a.c(style);
/* 152 */         if (include != a.a || exclude != a.a) {
/* 153 */           return (a)new b(include, exclude);
/*     */         }
/*     */         
/* 156 */         return this.b;
/*     */       
/*     */       case 3:
/* 159 */         return this.d;
/*     */       
/*     */       case 2:
/* 162 */         return this.c;
/*     */     } 
/* 164 */     throw new IllegalStateException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/a/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */