/*     */ package jp.cssj.homare.css;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import jp.cssj.sakae.sac.css.Selector;
/*     */ import jp.cssj.sakae.sac.css.SelectorList;
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
/*     */ public class d
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long g = 0L;
/*  35 */   final LinkedHashMap<String, i> a = new LinkedHashMap<>();
/*     */   
/*     */   f b;
/*     */   f c;
/*     */   f d;
/*     */   f e;
/*  41 */   final List<a> f = new ArrayList<>();
/*     */   
/*     */   public static class a
/*     */   {
/*     */     public final String a;
/*     */     
/*     */     a(String name, String pseudoPage, f decleration) {
/*  48 */       this.a = name;
/*  49 */       this.b = pseudoPage;
/*  50 */       this.c = decleration;
/*     */     }
/*     */     public final String b; public final f c; }
/*     */   
/*     */   public Object clone() {
/*  55 */     d styleSheet = new d();
/*  56 */     for (Iterator<Map.Entry<String, i>> i = this.a.entrySet().iterator(); i.hasNext(); ) {
/*  57 */       Map.Entry<String, i> entry = i.next();
/*  58 */       styleSheet.a.put(entry.getKey(), (i)((i)entry.getValue()).clone());
/*     */     } 
/*  60 */     if (this.b != null) {
/*  61 */       styleSheet.b = (f)this.b.clone();
/*     */     }
/*  63 */     if (this.c != null) {
/*  64 */       styleSheet.c = (f)this.c.clone();
/*     */     }
/*  66 */     if (this.d != null) {
/*  67 */       styleSheet.d = (f)this.d.clone();
/*     */     }
/*  69 */     if (this.e != null) {
/*  70 */       styleSheet.e = (f)this.e.clone();
/*     */     }
/*     */     
/*  73 */     return styleSheet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(SelectorList selectors, f declaration) {
/*  83 */     if (declaration == null) {
/*     */       return;
/*     */     }
/*  86 */     for (int i = 0; i < selectors.getLength(); i++) {
/*  87 */       Selector selector = selectors.item(i);
/*  88 */       String selectorString = selector.toString();
/*     */       
/*  90 */       i rule = this.a.get(selectorString);
/*  91 */       if (rule == null) {
/*  92 */         rule = new i(selector, new f(declaration));
/*  93 */         this.a.put(selectorString, rule);
/*     */       } else {
/*  95 */         f f1 = rule.b();
/*  96 */         f1.a(declaration);
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
/*     */   public void a(String pseudoPage, f declaration) {
/* 108 */     if (declaration == null) {
/*     */       return;
/*     */     }
/* 111 */     if (pseudoPage == null) {
/* 112 */       if (this.b == null) {
/* 113 */         this.b = new f();
/*     */       }
/* 115 */       this.b.a(declaration);
/* 116 */     } else if (pseudoPage.equals("first")) {
/* 117 */       if (this.c == null) {
/* 118 */         this.c = new f();
/*     */       }
/* 120 */       this.c.a(declaration);
/* 121 */     } else if (pseudoPage.equals("left")) {
/* 122 */       if (this.d == null) {
/* 123 */         this.d = new f();
/*     */       }
/* 125 */       this.d.a(declaration);
/* 126 */     } else if (pseudoPage.equals("right")) {
/* 127 */       if (this.e == null) {
/* 128 */         this.e = new f();
/*     */       }
/* 130 */       this.e.a(declaration);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String name, String pseudoPage, f declaration) {
/* 141 */     this.f.add(new a(name, pseudoPage, declaration));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */