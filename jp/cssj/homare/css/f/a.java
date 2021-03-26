/*     */ package jp.cssj.homare.css.f;
/*     */ 
/*     */ import jp.cssj.homare.css.c;
/*     */ import jp.cssj.homare.ua.m;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class a
/*     */   implements Comparable<a>, E
/*     */ {
/*     */   public short a() {
/*  12 */     return 1000;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  21 */   public static final a a = new a() {
/*     */       public short b() {
/*  23 */         return 21;
/*     */       }
/*     */       
/*     */       public double a(short unitType) {
/*  27 */         return 0.0D;
/*     */       }
/*     */       
/*     */       public double c() {
/*  31 */         return 0.0D;
/*     */       }
/*     */       
/*     */       public int a(a o) {
/*  35 */         a length = o;
/*  36 */         if (length.e()) {
/*  37 */           return 0;
/*     */         }
/*  39 */         if (length.d()) {
/*  40 */           return 1;
/*     */         }
/*  42 */         return -1;
/*     */       }
/*     */       
/*     */       public boolean d() {
/*  46 */         return false;
/*     */       }
/*     */       
/*     */       public boolean e() {
/*  50 */         return true;
/*     */       }
/*     */     };
/*     */   public abstract short b();
/*     */   public static a a(m ua, double value, short unitType) {
/*  55 */     if (value == 0.0D) {
/*  56 */       return a;
/*     */     }
/*  58 */     return new b(ua, unitType, value);
/*     */   } public abstract double a(short paramShort);
/*     */   public abstract double c();
/*     */   public static a a(m ua, double value) {
/*  62 */     if (value == 0.0D) {
/*  63 */       return a;
/*     */     }
/*  65 */     return new b(ua, value);
/*     */   }
/*     */   
/*     */   public a a(c style) {
/*  69 */     return this;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  73 */     StringBuffer str = new StringBuffer();
/*  74 */     str.append(a(b()));
/*  75 */     switch (b()) {
/*     */       case 18:
/*  77 */         str.append("in");
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
/* 103 */         return str.toString();case 19: str.append("cm"); return str.toString();case 20: str.append("mm"); return str.toString();case 21: str.append("pt"); return str.toString();case 22: str.append("pc"); return str.toString();case 17: str.append("px"); return str.toString();
/*     */     } 
/*     */     throw new IllegalStateException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */