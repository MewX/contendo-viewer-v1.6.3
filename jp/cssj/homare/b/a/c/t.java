/*     */ package jp.cssj.homare.b.a.c;
/*     */ 
/*     */ public class t
/*     */ {
/*     */   public static final short a = 1;
/*     */   public static final short b = 2;
/*     */   public static final short c = 3;
/*   8 */   public static final t d = new t(0.0D, 0.0D, 0.0D, 0.0D, (short)1, (short)1, (short)1, (short)1);
/*     */ 
/*     */   
/*  11 */   public static final t e = new t(0.0D, 0.0D, 0.0D, 0.0D, (short)3, (short)3, (short)3, (short)3);
/*     */   
/*     */   private final double f;
/*     */   
/*     */   private final double g;
/*     */   private final double h;
/*     */   private final double i;
/*     */   private final byte j;
/*     */   
/*     */   public static t a(double top, double right, double bottom, double left, short topType, short rightType, short bottomType, short leftType) {
/*  21 */     if (topType == 3 && rightType == 3 && bottomType == 3 && leftType == 3) {
/*  22 */       return e;
/*     */     }
/*  24 */     if (topType != 3 && rightType != 3 && bottomType != 3 && leftType != 3 && top == 0.0D && right == 0.0D && bottom == 0.0D && left == 0.0D)
/*     */     {
/*  26 */       return d;
/*     */     }
/*  28 */     return new t(top, right, bottom, left, topType, rightType, bottomType, leftType);
/*     */   }
/*     */ 
/*     */   
/*     */   private t(double top, double right, double bottom, double left, short topType, short rightType, short bottomType, short leftType) {
/*  33 */     this.f = top;
/*  34 */     this.g = right;
/*  35 */     this.h = bottom;
/*  36 */     this.i = left;
/*  37 */     this.j = (byte)(topType | rightType << 2 | bottomType << 4 | leftType << 6);
/*     */   }
/*     */   
/*     */   public short a() {
/*  41 */     return (short)(this.j & 0x3);
/*     */   }
/*     */   
/*     */   public short b() {
/*  45 */     return (short)(this.j >> 2 & 0x3);
/*     */   }
/*     */   
/*     */   public short c() {
/*  49 */     return (short)(this.j >> 4 & 0x3);
/*     */   }
/*     */   
/*     */   public short d() {
/*  53 */     return (short)(this.j >> 6 & 0x3);
/*     */   }
/*     */   
/*     */   public boolean e() {
/*  57 */     return (a() != 3 && f() == 0.0D && 
/*  58 */       b() != 3 && g() == 0.0D && 
/*  59 */       c() != 3 && h() == 0.0D && 
/*  60 */       d() != 3 && i() == 0.0D);
/*     */   }
/*     */   
/*     */   public double f() {
/*  64 */     return this.f;
/*     */   }
/*     */   
/*     */   public double g() {
/*  68 */     return this.g;
/*     */   }
/*     */   
/*     */   public double h() {
/*  72 */     return this.h;
/*     */   }
/*     */   
/*     */   public double i() {
/*  76 */     return this.i;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  80 */     StringBuffer buff = new StringBuffer();
/*  81 */     buff.append("[top=");
/*  82 */     switch (a()) {
/*     */       case 1:
/*  84 */         buff.append(this.f);
/*     */         break;
/*     */       case 2:
/*  87 */         buff.append(this.f * 100.0D).append('%');
/*     */         break;
/*     */       case 3:
/*  90 */         buff.append("auto");
/*     */         break;
/*     */       default:
/*  93 */         throw new IllegalStateException();
/*     */     } 
/*  95 */     buff.append(",right=");
/*  96 */     switch (b()) {
/*     */       case 1:
/*  98 */         buff.append(this.g);
/*     */         break;
/*     */       case 2:
/* 101 */         buff.append(this.g * 100.0D).append('%');
/*     */         break;
/*     */       case 3:
/* 104 */         buff.append("auto");
/*     */         break;
/*     */       default:
/* 107 */         throw new IllegalStateException();
/*     */     } 
/* 109 */     buff.append(",bottom=");
/* 110 */     switch (c()) {
/*     */       case 1:
/* 112 */         buff.append(this.h);
/*     */         break;
/*     */       case 2:
/* 115 */         buff.append(this.h * 100.0D).append('%');
/*     */         break;
/*     */       case 3:
/* 118 */         buff.append("auto");
/*     */         break;
/*     */       default:
/* 121 */         throw new IllegalStateException();
/*     */     } 
/* 123 */     buff.append(",left=");
/* 124 */     switch (d()) {
/*     */       case 1:
/* 126 */         buff.append(this.i);
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
/* 137 */         buff.append(']');
/* 138 */         return buff.toString();case 2: buff.append(this.i * 100.0D).append('%'); buff.append(']'); return buff.toString();case 3: buff.append("auto"); buff.append(']'); return buff.toString();
/*     */     } 
/*     */     throw new IllegalStateException();
/*     */   }
/*     */   public t a(boolean top, boolean right, boolean bottom, boolean left) {
/* 143 */     t insets = e() ? this : a(top ? f() : 0.0D, right ? g() : 0.0D, bottom ? h() : 0.0D, left ? 
/* 144 */         i() : 0.0D, top ? a() : 1, right ? 
/* 145 */         b() : 1, bottom ? 
/* 146 */         c() : 1, left ? 
/* 147 */         d() : 1);
/* 148 */     return insets;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/c/t.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */