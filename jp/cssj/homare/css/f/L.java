/*     */ package jp.cssj.homare.css.f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class L
/*     */   implements ad
/*     */ {
/*     */   public static final byte a = 0;
/*     */   public static final byte b = 1;
/*     */   public static final byte c = 2;
/*     */   public static final byte d = 3;
/*     */   public static final byte e = 4;
/*     */   public static final byte f = 5;
/*     */   public static final byte g = 6;
/*     */   public static final byte h = 9;
/*     */   public static final byte i = 10;
/*     */   public static final byte j = 11;
/*     */   public static final byte k = 12;
/*     */   public static final byte l = 13;
/*     */   public static final byte m = 14;
/*  24 */   public static final L n = new L((byte)0);
/*     */   
/*  26 */   public static final L o = new L((byte)2);
/*     */   
/*  28 */   public static final L p = new L((byte)1);
/*     */   
/*  30 */   public static final L q = new L((byte)3);
/*     */   
/*  32 */   public static final L r = new L((byte)4);
/*     */   
/*  34 */   public static final L s = new L((byte)5);
/*     */   
/*  36 */   public static final L t = new L((byte)6);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public static final L u = new L((byte)9);
/*     */   
/*  47 */   public static final L v = new L((byte)10);
/*  48 */   public static final L w = new L((byte)11);
/*     */   
/*  50 */   public static final L x = new L((byte)12);
/*  51 */   public static final L y = new L((byte)13);
/*     */   
/*  53 */   public static final L z = new L((byte)14);
/*     */   
/*     */   private final byte A;
/*     */   
/*     */   private L(byte pageBreak) {
/*  58 */     this.A = pageBreak;
/*     */   }
/*     */   
/*     */   public short a() {
/*  62 */     return 1047;
/*     */   }
/*     */   
/*     */   public byte b() {
/*  66 */     return this.A;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  70 */     switch (this.A) {
/*     */       case 0:
/*  72 */         return "auto";
/*     */       
/*     */       case 2:
/*  75 */         return "always";
/*     */       
/*     */       case 1:
/*  78 */         return "avoid";
/*     */       
/*     */       case 3:
/*  81 */         return "left";
/*     */       
/*     */       case 4:
/*  84 */         return "right";
/*     */       
/*     */       case 5:
/*  87 */         return "page";
/*     */       
/*     */       case 6:
/*  90 */         return "column";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 9:
/*  99 */         return "verso";
/*     */       
/*     */       case 10:
/* 102 */         return "recto";
/*     */       
/*     */       case 11:
/* 105 */         return "if-left";
/*     */       
/*     */       case 12:
/* 108 */         return "if-right";
/*     */       
/*     */       case 13:
/* 111 */         return "if-verso";
/*     */       
/*     */       case 14:
/* 114 */         return "if-recto";
/*     */     } 
/*     */     
/* 117 */     throw new IllegalStateException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/L.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */