/*     */ package jp.cssj.sakae.pdf.c.c;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   extends FilterOutputStream
/*     */ {
/*  14 */   public static final byte[] a = new byte[] { 1 };
/*     */   
/*  16 */   public static final byte[] b = new byte[] { 3 };
/*     */   
/*  18 */   public static final byte[] c = new byte[] { 4 };
/*     */   
/*  20 */   public static final byte[] d = new byte[] { 5 };
/*     */   
/*  22 */   public static final byte[] e = new byte[] { 6 };
/*     */   
/*  24 */   public static final byte[] f = new byte[] { 7 };
/*     */   
/*  26 */   public static final byte[] g = new byte[] { 8 };
/*     */   
/*  28 */   public static final byte[] h = new byte[] { 10 };
/*     */   
/*  30 */   public static final byte[] i = new byte[] { 11 };
/*     */   
/*  32 */   public static final byte[] j = new byte[] { 14 };
/*     */   
/*  34 */   public static final byte[] k = new byte[] { 18 };
/*     */   
/*  36 */   public static final byte[] l = new byte[] { 19 };
/*     */   
/*  38 */   public static final byte[] m = new byte[] { 20 };
/*     */   
/*  40 */   public static final byte[] n = new byte[] { 21 };
/*     */   
/*  42 */   public static final byte[] o = new byte[] { 22 };
/*     */   
/*  44 */   public static final byte[] p = new byte[] { 23 };
/*     */   
/*  46 */   public static final byte[] q = new byte[] { 24 };
/*     */   
/*  48 */   public static final byte[] r = new byte[] { 25 };
/*     */   
/*  50 */   public static final byte[] s = new byte[] { 26 };
/*     */   
/*  52 */   public static final byte[] t = new byte[] { 27 };
/*     */   
/*  54 */   public static final byte[] u = new byte[] { 28 };
/*     */   
/*  56 */   public static final byte[] v = new byte[] { 29 };
/*     */   
/*  58 */   public static final byte[] w = new byte[] { 30 };
/*     */   
/*  60 */   public static final byte[] x = new byte[] { 31 };
/*     */   
/*  62 */   public static final byte[] y = new byte[] { 12, 3 };
/*     */   
/*  64 */   public static final byte[] z = new byte[] { 12, 4 };
/*     */   
/*  66 */   public static final byte[] A = new byte[] { 12, 5 };
/*     */   
/*  68 */   public static final byte[] B = new byte[] { 12, 9 };
/*     */   
/*  70 */   public static final byte[] C = new byte[] { 12, 10 };
/*     */   
/*  72 */   public static final byte[] D = new byte[] { 12, 11 };
/*     */   
/*  74 */   public static final byte[] E = new byte[] { 12, 12 };
/*     */   
/*  76 */   public static final byte[] F = new byte[] { 12, 14 };
/*     */   
/*  78 */   public static final byte[] G = new byte[] { 12, 15 };
/*     */   
/*  80 */   public static final byte[] H = new byte[] { 12, 18 };
/*     */   
/*  82 */   public static final byte[] I = new byte[] { 12, 20 };
/*     */   
/*  84 */   public static final byte[] J = new byte[] { 12, 21 };
/*     */   
/*  86 */   public static final byte[] K = new byte[] { 12, 22 };
/*     */   
/*  88 */   public static final byte[] L = new byte[] { 12, 23 };
/*     */   
/*  90 */   public static final byte[] M = new byte[] { 12, 24 };
/*     */   
/*  92 */   public static final byte[] N = new byte[] { 12, 26 };
/*     */   
/*  94 */   public static final byte[] O = new byte[] { 12, 27 };
/*     */   
/*  96 */   public static final byte[] P = new byte[] { 12, 28 };
/*     */   
/*  98 */   public static final byte[] Q = new byte[] { 12, 29 };
/*     */   
/* 100 */   public static final byte[] R = new byte[] { 12, 30 };
/*     */   
/* 102 */   public static final byte[] S = new byte[] { 12, 34 };
/*     */   
/* 104 */   public static final byte[] T = new byte[] { 12, 35 };
/*     */   
/* 106 */   public static final byte[] U = new byte[] { 12, 36 };
/*     */   
/* 108 */   public static final byte[] V = new byte[] { 12, 37 };
/*     */   
/*     */   public c(OutputStream out) {
/* 111 */     super(out);
/*     */   }
/*     */   
/*     */   public int a(byte[] o) throws IOException {
/* 115 */     write(o);
/* 116 */     return o.length;
/*     */   }
/*     */   
/*     */   public int a(short a) throws IOException {
/* 120 */     if (a >= -107 && a <= 107) {
/* 121 */       write(a + 139);
/* 122 */       return 1;
/* 123 */     }  if (a >= 108 && a <= 1131) {
/* 124 */       a = (short)(a - 108);
/* 125 */       write((a >> 8) + 247);
/* 126 */       write(a);
/* 127 */       return 2;
/* 128 */     }  if (a >= -1131 && a <= -108) {
/* 129 */       a = (short)(a + 108);
/* 130 */       write((-a >> 8) + 251);
/* 131 */       write(-a);
/* 132 */       return 2;
/*     */     } 
/* 134 */     write(28);
/* 135 */     write(a >> 8);
/* 136 */     write(a);
/* 137 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(double f) throws IOException {
/* 142 */     if (f > 32767.0D || f < -32768.0D) {
/* 143 */       throw new IllegalArgumentException();
/*     */     }
/* 145 */     write(255);
/*     */     
/* 147 */     short a = (short)(int)f;
/* 148 */     write(a >> 8);
/* 149 */     write(a);
/*     */     
/* 151 */     f -= a;
/* 152 */     int b = (int)(f * 1048575.0D);
/* 153 */     write(b >> 8);
/* 154 */     write(b);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/c/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */