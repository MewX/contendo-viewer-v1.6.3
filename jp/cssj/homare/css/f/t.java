/*     */ package jp.cssj.homare.css.f;
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
/*     */ public class t
/*     */   implements ad
/*     */ {
/*     */   public static final byte a = 0;
/*     */   public static final byte b = 1;
/*     */   public static final byte c = 2;
/*     */   public static final byte d = 3;
/*     */   public static final byte e = 4;
/*     */   public static final byte f = 5;
/*     */   public static final byte g = 6;
/*     */   public static final byte h = 7;
/*     */   public static final byte i = 8;
/*     */   public static final byte j = 9;
/*     */   public static final byte k = 10;
/*     */   public static final byte l = 11;
/*     */   public static final byte m = 12;
/*     */   public static final byte n = 13;
/*     */   public static final byte o = 14;
/*     */   public static final byte p = 15;
/*  40 */   public static final t q = new t((byte)0);
/*     */   
/*  42 */   public static final t r = new t((byte)1);
/*     */   
/*  44 */   public static final t s = new t((byte)2);
/*     */   
/*  46 */   public static final t t = new t((byte)3);
/*     */   
/*  48 */   public static final t u = new t((byte)4);
/*     */   
/*  50 */   public static final t v = new t((byte)5);
/*     */   
/*  52 */   public static final t w = new t((byte)6);
/*     */   
/*  54 */   public static final t x = new t((byte)7);
/*     */   
/*  56 */   public static final t y = new t((byte)8);
/*     */   
/*  58 */   public static final t z = new t((byte)9);
/*     */   
/*  60 */   public static final t A = new t((byte)10);
/*     */   
/*  62 */   public static final t B = new t((byte)11);
/*     */   
/*  64 */   public static final t C = new t((byte)12);
/*     */   
/*  66 */   public static final t D = new t((byte)13);
/*     */   
/*  68 */   public static final t E = new t((byte)14);
/*     */   
/*  70 */   public static final t F = new t((byte)15);
/*     */   
/*     */   private final byte G;
/*     */   
/*     */   private t(byte display) {
/*  75 */     this.G = display;
/*     */   }
/*     */   
/*     */   public short a() {
/*  79 */     return 1004;
/*     */   }
/*     */   
/*     */   public byte b() {
/*  83 */     return this.G;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  87 */     switch (this.G) {
/*     */       case 0:
/*  89 */         return "none";
/*     */       case 1:
/*  91 */         return "block";
/*     */       case 2:
/*  93 */         return "inline";
/*     */       case 3:
/*  95 */         return "inline-block";
/*     */       case 4:
/*  97 */         return "list-item";
/*     */       case 5:
/*  99 */         return "run-in";
/*     */       case 6:
/* 101 */         return "table";
/*     */       case 7:
/* 103 */         return "inline-table";
/*     */       case 8:
/* 105 */         return "table-row-group";
/*     */       case 9:
/* 107 */         return "table-column";
/*     */       case 10:
/* 109 */         return "table-column-group";
/*     */       case 11:
/* 111 */         return "table-header-group";
/*     */       case 12:
/* 113 */         return "table-footer-group";
/*     */       case 13:
/* 115 */         return "table-row";
/*     */       case 14:
/* 117 */         return "table-cell";
/*     */       case 15:
/* 119 */         return "table-caption";
/*     */     } 
/* 121 */     throw new IllegalStateException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/t.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */