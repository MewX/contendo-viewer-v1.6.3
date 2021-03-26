/*     */ package jp.cssj.sakae.pdf.c.a;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class k
/*     */   implements Serializable
/*     */ {
/*     */   private static final long b = 0L;
/*     */   protected final a[] a;
/*     */   
/*     */   public k(a[] unicodes) {
/*  17 */     this.a = unicodes;
/*     */   }
/*     */   
/*     */   public a[] a() {
/*  21 */     return this.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class a
/*     */     implements Serializable
/*     */   {
/*     */     private static final long d = 0L;
/*     */ 
/*     */     
/*     */     int a;
/*     */ 
/*     */     
/*     */     int b;
/*     */ 
/*     */     
/*     */     int[] c;
/*     */ 
/*     */ 
/*     */     
/*     */     public a(int firstCode, int lastCode, int[] unicodes) {
/*  44 */       this.a = firstCode;
/*  45 */       this.b = lastCode;
/*  46 */       this.c = unicodes;
/*     */     }
/*     */     
/*     */     public a(int code, int[] unicodes) {
/*  50 */       this(code, code, unicodes);
/*     */     }
/*     */     
/*     */     public a(int[] unicodes) {
/*  54 */       this(0, 0, unicodes);
/*     */     }
/*     */     
/*     */     public int a() {
/*  58 */       return this.a;
/*     */     }
/*     */     
/*     */     public int b() {
/*  62 */       return this.b;
/*     */     }
/*     */     
/*     */     public int[] c() {
/*  66 */       return this.c;
/*     */     }
/*     */     
/*     */     public int a(int code) {
/*  70 */       if (code < this.a || code > this.b) {
/*  71 */         throw new ArrayIndexOutOfBoundsException(code);
/*     */       }
/*  73 */       int index = code - this.a;
/*  74 */       if (index >= this.c.length) {
/*  75 */         return this.c[this.c.length - 1];
/*     */       }
/*  77 */       return this.c[index];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static k a(int[] unicodes) {
/*  88 */     List<a> list = new ArrayList<>();
/*  89 */     int[] runUnicodes = new int[256];
/*  90 */     int position = 0;
/*  91 */     int startCid = -1;
/*  92 */     for (int cid = 0; cid < unicodes.length; cid++) {
/*  93 */       int unicode = unicodes[cid];
/*  94 */       if (unicode == 0) {
/*  95 */         if (position == 0) {
/*     */           continue;
/*     */         }
/*  98 */         unicode = runUnicodes[position - 1] + cid - startCid;
/*     */       } 
/*     */       
/* 101 */       if (startCid == -1) {
/*     */         
/* 103 */         startCid = cid;
/* 104 */         runUnicodes[position++] = unicode;
/*     */       }
/* 106 */       else if (cid % 256 != 0) {
/* 107 */         runUnicodes[position++] = unicode;
/*     */       }
/*     */       else {
/*     */         
/* 111 */         int[] temp = new int[position];
/* 112 */         System.arraycopy(runUnicodes, 0, temp, 0, position);
/* 113 */         list.add(new a(startCid, cid - 1, temp));
/* 114 */         startCid = cid;
/* 115 */         runUnicodes[0] = unicode;
/* 116 */         position = 1;
/*     */       }  continue;
/* 118 */     }  if (startCid != -1) {
/* 119 */       int[] temp = new int[position];
/* 120 */       System.arraycopy(runUnicodes, 0, temp, 0, position);
/* 121 */       list.add(new a(startCid, unicodes.length - 1, temp));
/*     */     } 
/* 123 */     return new k(list.<a>toArray(new a[list.size()]));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */