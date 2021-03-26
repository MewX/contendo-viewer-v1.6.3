/*     */ package jp.cssj.sakae.pdf.c.c;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   extends FilterOutputStream
/*     */ {
/*  15 */   public static final byte[] a = new byte[] { 0 };
/*     */   
/*  17 */   public static final byte[] b = new byte[] { 1 };
/*     */   
/*  19 */   public static final byte[] c = new byte[] { 2 };
/*     */   
/*  21 */   public static final byte[] d = new byte[] { 3 };
/*     */   
/*  23 */   public static final byte[] e = new byte[] { 4 };
/*     */   
/*  25 */   public static final byte[] f = new byte[] { 5 };
/*     */   
/*  27 */   public static final byte[] g = new byte[] { 6 };
/*     */   
/*  29 */   public static final byte[] h = new byte[] { 7 };
/*     */   
/*  31 */   public static final byte[] i = new byte[] { 8 };
/*     */   
/*  33 */   public static final byte[] j = new byte[] { 9 };
/*     */   
/*  35 */   public static final byte[] k = new byte[] { 10 };
/*     */   
/*  37 */   public static final byte[] l = new byte[] { 11 };
/*     */   
/*  39 */   public static final byte[] m = new byte[] { 13 };
/*     */   
/*  41 */   public static final byte[] n = new byte[] { 14 };
/*     */   
/*  43 */   public static final byte[] o = new byte[] { 15 };
/*     */   
/*  45 */   public static final byte[] p = new byte[] { 16 };
/*     */   
/*  47 */   public static final byte[] q = new byte[] { 17 };
/*     */   
/*  49 */   public static final byte[] r = new byte[] { 18 };
/*     */   
/*  51 */   public static final byte[] s = new byte[] { 19 };
/*     */   
/*  53 */   public static final byte[] t = new byte[] { 20 };
/*     */   
/*  55 */   public static final byte[] u = new byte[] { 21 };
/*     */   
/*  57 */   public static final byte[] v = new byte[] { 12, 0 };
/*     */   
/*  59 */   public static final byte[] w = new byte[] { 12, 1 };
/*     */   
/*  61 */   public static final byte[] x = new byte[] { 12, 2 };
/*     */   
/*  63 */   public static final byte[] y = new byte[] { 12, 3 };
/*     */   
/*  65 */   public static final byte[] z = new byte[] { 12, 4 };
/*     */   
/*  67 */   public static final byte[] A = new byte[] { 12, 5 };
/*     */   
/*  69 */   public static final byte[] B = new byte[] { 12, 6 };
/*     */   
/*  71 */   public static final byte[] C = new byte[] { 12, 7 };
/*     */   
/*  73 */   public static final byte[] D = new byte[] { 12, 8 };
/*     */   
/*  75 */   public static final byte[] E = new byte[] { 12, 9 };
/*     */   
/*  77 */   public static final byte[] F = new byte[] { 12, 10 };
/*     */   
/*  79 */   public static final byte[] G = new byte[] { 12, 11 };
/*     */   
/*  81 */   public static final byte[] H = new byte[] { 12, 12 };
/*     */   
/*  83 */   public static final byte[] I = new byte[] { 12, 13 };
/*     */   
/*  85 */   public static final byte[] J = new byte[] { 12, 14 };
/*     */   
/*  87 */   public static final byte[] K = new byte[] { 12, 17 };
/*     */   
/*  89 */   public static final byte[] L = new byte[] { 12, 18 };
/*     */   
/*  91 */   public static final byte[] M = new byte[] { 12, 19 };
/*     */   
/*  93 */   public static final byte[] N = new byte[] { 12, 20 };
/*     */   
/*  95 */   public static final byte[] O = new byte[] { 12, 21 };
/*     */   
/*  97 */   public static final byte[] P = new byte[] { 12, 22 };
/*     */   
/*  99 */   public static final byte[] Q = new byte[] { 12, 23 };
/*     */   
/* 101 */   public static final byte[] R = new byte[] { 12, 30 };
/*     */   
/* 103 */   public static final byte[] S = new byte[] { 12, 31 };
/*     */   
/* 105 */   public static final byte[] T = new byte[] { 12, 32 };
/*     */   
/* 107 */   public static final byte[] U = new byte[] { 12, 33 };
/*     */   
/* 109 */   public static final byte[] V = new byte[] { 12, 34 };
/*     */   
/* 111 */   public static final byte[] W = new byte[] { 12, 35 };
/*     */   
/* 113 */   public static final byte[] X = new byte[] { 12, 36 };
/*     */   
/* 115 */   public static final byte[] Y = new byte[] { 12, 37 };
/*     */   
/* 117 */   public static final byte[] Z = new byte[] { 12, 38 };
/*     */   
/*     */   private static final int aa = 391;
/*     */   
/* 121 */   private int ab = 0;
/*     */   
/*     */   public b(OutputStream out) {
/* 124 */     super(out);
/*     */   }
/*     */   
/*     */   public void write(byte[] arrayOfByte, int off, int len) throws IOException {
/* 128 */     this.ab += len;
/* 129 */     this.out.write(arrayOfByte, off, len);
/*     */   }
/*     */   
/*     */   public void write(byte[] arrayOfByte) throws IOException {
/* 133 */     this.ab += arrayOfByte.length;
/* 134 */     this.out.write(arrayOfByte);
/*     */   }
/*     */   
/*     */   public void write(int i) throws IOException {
/* 138 */     this.ab++;
/* 139 */     this.out.write(i);
/*     */   }
/*     */   
/*     */   public int a() {
/* 143 */     return this.ab;
/*     */   }
/*     */   
/*     */   public static byte[] a(String str) {
/*     */     try {
/* 148 */       return str.getBytes("ISO-8859-1");
/* 149 */     } catch (UnsupportedEncodingException e) {
/* 150 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(byte b1) throws IOException {
/* 155 */     write(b1);
/*     */   }
/*     */   
/*     */   public void a(int a) throws IOException {
/* 159 */     write(a >> 8);
/* 160 */     write(a);
/*     */   }
/*     */   
/*     */   public void b(byte offSize) throws IOException {
/* 164 */     if (offSize < 1 || offSize > 4) {
/* 165 */       throw new IllegalArgumentException();
/*     */     }
/* 167 */     write(offSize);
/*     */   }
/*     */   
/*     */   public void a(int a, int size) throws IOException {
/* 171 */     switch (size) {
/*     */       case 4:
/* 173 */         write(a >> 24);
/*     */       case 3:
/* 175 */         write(a >> 16);
/*     */       case 2:
/* 177 */         write(a >> 8);
/*     */       case 1:
/* 179 */         write(a);
/*     */         return;
/*     */     } 
/*     */     
/* 183 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(int sid) throws IOException {
/* 188 */     sid += 391;
/* 189 */     if (sid < 0 || sid > 64999) {
/* 190 */       throw new IllegalArgumentException();
/*     */     }
/* 192 */     c(sid);
/*     */   }
/*     */   
/*     */   public int a(byte[] o) throws IOException {
/* 196 */     write(o);
/* 197 */     return o.length;
/*     */   }
/*     */   
/*     */   public int c(int a) throws IOException {
/* 201 */     if (a >= -107 && a <= 107) {
/* 202 */       write(a + 139);
/* 203 */       return 1;
/* 204 */     }  if (a >= 108 && a <= 1131) {
/* 205 */       a -= 108;
/* 206 */       write((a >> 8) + 247);
/* 207 */       write(a);
/* 208 */       return 2;
/* 209 */     }  if (a >= -1131 && a <= -108) {
/* 210 */       a += 108;
/* 211 */       write((-a >> 8) + 251);
/* 212 */       write(-a);
/* 213 */       return 2;
/* 214 */     }  if (a >= -32768 && a <= 32767) {
/* 215 */       write(28);
/* 216 */       write(a >> 8);
/* 217 */       write(a);
/* 218 */       return 3;
/*     */     } 
/* 220 */     write(29);
/* 221 */     write(a >> 24);
/* 222 */     write(a >> 16);
/* 223 */     write(a >> 8);
/* 224 */     write(a);
/* 225 */     return 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(String real) throws IOException {
/* 230 */     write(30);
/*     */     
/* 232 */     int count = 1;
/* 233 */     byte b1 = 0;
/* 234 */     boolean low = false;
/*     */     
/* 236 */     int len = real.length();
/* 237 */     for (int i = 0; i < len; i++) {
/* 238 */       byte hex; char c = real.charAt(i);
/*     */       
/* 240 */       switch (c) {
/*     */         case '.':
/* 242 */           hex = 10;
/*     */           break;
/*     */         
/*     */         case 'E':
/* 246 */           if (real.charAt(i + 1) == '-') {
/* 247 */             i++;
/* 248 */             hex = 12; break;
/*     */           } 
/* 250 */           hex = 11;
/*     */           break;
/*     */ 
/*     */         
/*     */         case '-':
/* 255 */           hex = 14;
/*     */           break;
/*     */         
/*     */         default:
/* 259 */           if (c < '0' || c > '9') {
/* 260 */             throw new IllegalArgumentException();
/*     */           }
/* 262 */           hex = (byte)(c - 48);
/*     */           break;
/*     */       } 
/*     */       
/* 266 */       if (low) {
/* 267 */         count++;
/* 268 */         write(b1 | hex);
/* 269 */         low = false;
/*     */       } else {
/* 271 */         b1 = (byte)(hex << 4);
/* 272 */         low = true;
/*     */       } 
/*     */     } 
/* 275 */     if (low) {
/* 276 */       write(b1 | 0xF);
/*     */     } else {
/* 278 */       write(255);
/*     */     } 
/* 280 */     return count + 1;
/*     */   }
/*     */   
/*     */   public void a(byte major, byte minor, byte hdrSize, byte offSize) throws IOException {
/* 284 */     a(major);
/* 285 */     a(minor);
/* 286 */     a(hdrSize);
/* 287 */     b(offSize);
/*     */   }
/*     */   
/*     */   public void a(byte[][] objects, byte offSize) throws IOException {
/* 291 */     a((short)objects.length);
/* 292 */     if (objects.length <= 0) {
/*     */       return;
/*     */     }
/*     */     
/* 296 */     b(offSize);
/*     */ 
/*     */     
/* 299 */     int offset = 1; int i;
/* 300 */     for (i = 0; i < objects.length; i++) {
/* 301 */       byte[] object = objects[i];
/* 302 */       a(offset, offSize);
/* 303 */       offset += object.length;
/*     */     } 
/*     */ 
/*     */     
/* 307 */     a(offset, offSize);
/*     */ 
/*     */     
/* 310 */     for (i = 0; i < objects.length; i++) {
/* 311 */       byte[] object = objects[i];
/* 312 */       write(object);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/c/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */