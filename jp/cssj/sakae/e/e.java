/*    */ package jp.cssj.sakae.e;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class e
/*    */   implements Serializable
/*    */ {
/*    */   private static final long a = 0L;
/* 14 */   private static final short[] b = new short[0];
/*    */   
/* 16 */   private short[] c = b;
/*    */   
/*    */   private short d;
/*    */   
/* 20 */   private int e = 0;
/*    */   
/*    */   public e() {
/* 23 */     this((short)0);
/*    */   }
/*    */   
/*    */   public e(short defaultValue) {
/* 27 */     this.d = defaultValue;
/*    */   }
/*    */   
/*    */   public void a(int pos, short value) {
/* 31 */     if (this.e <= pos) {
/* 32 */       this.e = pos + 1;
/* 33 */       if (this.c.length <= pos) {
/* 34 */         short[] array = new short[Math.max(this.e + 10, this.c.length * 3 / 2)];
/* 35 */         for (int i = this.c.length; i < array.length; i++) {
/* 36 */           array[i] = this.d;
/*    */         }
/* 38 */         System.arraycopy(this.c, 0, array, 0, this.c.length);
/* 39 */         this.c = array;
/*    */       } 
/*    */     } 
/* 42 */     this.c[pos] = value;
/*    */   }
/*    */   
/*    */   public short[] a() {
/* 46 */     c();
/* 47 */     return this.c;
/*    */   }
/*    */   
/*    */   public short a(int i) {
/* 51 */     if (i >= this.c.length) {
/* 52 */       return this.d;
/*    */     }
/* 54 */     return this.c[i];
/*    */   }
/*    */   
/*    */   public int b() {
/* 58 */     return this.e;
/*    */   }
/*    */   
/*    */   public void c() {
/* 62 */     if (this.e != this.c.length) {
/* 63 */       short[] array = new short[this.e];
/* 64 */       System.arraycopy(this.c, 0, array, 0, this.e);
/* 65 */       this.c = array;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 70 */     return (this.e == 0);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/e/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */