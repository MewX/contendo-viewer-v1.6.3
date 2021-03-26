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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class G
/*     */   implements ad
/*     */ {
/*     */   public static final short a = 0;
/*     */   public static final short b = 1;
/*     */   public static final short c = 2;
/*     */   public static final short d = 3;
/*     */   public static final short e = 4;
/*     */   public static final short f = 5;
/*     */   public static final short g = 6;
/*     */   public static final short h = 7;
/*     */   public static final short i = 8;
/*     */   public static final short j = 9;
/*     */   public static final short k = 10;
/*     */   public static final short l = 11;
/*     */   public static final short m = 12;
/*     */   public static final short n = 13;
/*     */   public static final short o = 14;
/*     */   public static final short p = 15;
/*     */   public static final short q = 16;
/*     */   public static final short r = 17;
/*     */   public static final short s = 18;
/*     */   public static final short t = 19;
/*     */   public static final short u = 20;
/*     */   public static final short v = 21;
/*     */   public static final short w = 22;
/*  54 */   public static final G x = new G((short)0);
/*     */   
/*  56 */   public static final G y = new G((short)1);
/*     */   
/*  58 */   public static final G z = new G((short)2);
/*     */   
/*  60 */   public static final G A = new G((short)3);
/*     */   
/*  62 */   public static final G B = new G((short)4);
/*     */   
/*  64 */   public static final G C = new G((short)5);
/*     */   
/*  66 */   public static final G D = new G((short)6);
/*     */   
/*  68 */   public static final G E = new G((short)7);
/*     */   
/*  70 */   public static final G F = new G((short)8);
/*     */   
/*  72 */   public static final G G = new G((short)9);
/*     */   
/*  74 */   public static final G H = new G((short)10);
/*     */   
/*  76 */   public static final G I = new G((short)11);
/*     */   
/*  78 */   public static final G J = new G((short)12);
/*     */   
/*  80 */   public static final G K = new G((short)13);
/*     */   
/*  82 */   public static final G L = new G((short)14);
/*     */   
/*  84 */   public static final G M = new G((short)15);
/*     */   
/*  86 */   public static final G N = new G((short)16);
/*     */   
/*  88 */   public static final G O = new G((short)17);
/*     */   
/*  90 */   public static final G P = new G((short)18);
/*     */   
/*  92 */   public static final G Q = new G((short)19);
/*     */   
/*  94 */   public static final G R = new G((short)20);
/*     */   
/*  96 */   public static final G S = new G((short)21);
/*     */ 
/*     */   
/*  99 */   public static final G T = new G((short)22);
/*     */   
/*     */   private final short aV;
/*     */   
/*     */   private G(short listStyleType) {
/* 104 */     this.aV = listStyleType;
/*     */   }
/*     */   
/*     */   public short a() {
/* 108 */     return 1038;
/*     */   }
/*     */   
/*     */   public short b() {
/* 112 */     return this.aV;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 116 */     switch (this.aV) {
/*     */       case 1:
/* 118 */         return "disc";
/*     */       
/*     */       case 2:
/* 121 */         return "circle";
/*     */       
/*     */       case 3:
/* 124 */         return "square";
/*     */       
/*     */       case 4:
/* 127 */         return "decimal";
/*     */       
/*     */       case 5:
/* 130 */         return "decimal-leading-zero";
/*     */       
/*     */       case 6:
/* 133 */         return "lower-roman";
/*     */       
/*     */       case 7:
/* 136 */         return "upper-roman";
/*     */       
/*     */       case 8:
/* 139 */         return "lower-greek";
/*     */       
/*     */       case 9:
/* 142 */         return "lower-alpha";
/*     */       
/*     */       case 10:
/* 145 */         return "lower-latin";
/*     */       
/*     */       case 11:
/* 148 */         return "upper-alpha";
/*     */       
/*     */       case 13:
/* 151 */         return "hebrew";
/*     */       
/*     */       case 14:
/* 154 */         return "armenian";
/*     */       
/*     */       case 15:
/* 157 */         return "georgian";
/*     */       
/*     */       case 16:
/* 160 */         return "cjk-ideographic";
/*     */       
/*     */       case 17:
/* 163 */         return "hiragana";
/*     */       
/*     */       case 18:
/* 166 */         return "katakana";
/*     */       
/*     */       case 19:
/* 169 */         return "hiragana-iroha";
/*     */       
/*     */       case 20:
/* 172 */         return "katakana-iroha";
/*     */       
/*     */       case 21:
/* 175 */         return "-cssj-full-width-decimal";
/*     */       
/*     */       case 22:
/* 178 */         return "-cssj-cjk-decimal";
/*     */       
/*     */       case 0:
/* 181 */         return "none";
/*     */     } 
/*     */     
/* 184 */     throw new IllegalStateException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/G.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */