/*    */ package jp.cssj.sakae.pdf.c.a;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class m
/*    */   implements Serializable
/*    */ {
/*    */   private static final long e = 0L;
/*    */   int a;
/*    */   int b;
/*    */   short[] c;
/*    */   
/*    */   public m(int firstCode, int lastCode, short[] widths) {
/* 25 */     this.a = firstCode;
/* 26 */     this.b = lastCode;
/* 27 */     this.c = widths;
/*    */   }
/*    */   
/*    */   public m(int code, short[] widths) {
/* 31 */     this(code, code, widths);
/*    */   }
/*    */   
/*    */   public m(short[] widths) {
/* 35 */     this(0, 0, widths);
/*    */   }
/*    */   
/*    */   public int a() {
/* 39 */     return this.a;
/*    */   }
/*    */   
/*    */   public int b() {
/* 43 */     return this.b;
/*    */   }
/*    */   
/*    */   public short[] c() {
/* 47 */     return this.c;
/*    */   }
/*    */   
/*    */   public short a(int code) {
/* 51 */     if (!d && (code < this.a || code > this.b)) throw new AssertionError(); 
/* 52 */     int index = code - this.a;
/* 53 */     if (index >= this.c.length) {
/* 54 */       return this.c[this.c.length - 1];
/*    */     }
/* 56 */     return this.c[index];
/*    */   }
/*    */   
/*    */   public String toString() {
/* 60 */     StringBuffer buff = new StringBuffer();
/* 61 */     buff.append(this.a).append(' ');
/* 62 */     buff.append(this.b);
/* 63 */     for (int i = 0; i < this.c.length; i++) {
/* 64 */       buff.append(' ');
/* 65 */       buff.append(this.c[i]);
/*    */     } 
/* 67 */     return buff.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/m.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */