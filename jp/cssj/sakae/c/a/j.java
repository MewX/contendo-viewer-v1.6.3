/*    */ package jp.cssj.sakae.c.a;
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
/*    */ public class j
/*    */   implements Serializable
/*    */ {
/*    */   private static final long m = 0L;
/*    */   public final byte a;
/*    */   public final byte b;
/*    */   public final byte c;
/*    */   public final byte d;
/*    */   public final byte e;
/*    */   public final byte f;
/*    */   public final byte g;
/*    */   public final byte h;
/*    */   public final byte i;
/*    */   public final byte j;
/*    */   public final byte k;
/*    */   public final byte l;
/*    */   
/*    */   public j(int sFamilyClass, byte[] panose) {
/* 33 */     this.a = (byte)(sFamilyClass >> 8 & 0xFF);
/* 34 */     this.b = (byte)(sFamilyClass & 0xFF);
/* 35 */     this.c = panose[0];
/* 36 */     this.d = panose[1];
/* 37 */     this.e = panose[2];
/* 38 */     this.f = panose[3];
/* 39 */     this.g = panose[4];
/* 40 */     this.h = panose[5];
/* 41 */     this.i = panose[6];
/* 42 */     this.j = panose[7];
/* 43 */     this.k = panose[8];
/* 44 */     this.l = panose[9];
/*    */   }
/*    */   
/*    */   public String toString() {
/* 48 */     StringBuffer sb = new StringBuffer();
/* 49 */     sb.append(String.valueOf(this.a)).append(" ").append(String.valueOf(this.b))
/* 50 */       .append(" ").append(String.valueOf(this.c)).append(" ").append(String.valueOf(this.d))
/* 51 */       .append(" ").append(String.valueOf(this.e)).append(" ").append(String.valueOf(this.f))
/* 52 */       .append(" ").append(String.valueOf(this.g)).append(" ")
/* 53 */       .append(String.valueOf(this.h)).append(" ").append(String.valueOf(this.i))
/* 54 */       .append(" ").append(String.valueOf(this.j)).append(" ").append(String.valueOf(this.k))
/* 55 */       .append(" ").append(String.valueOf(this.l));
/* 56 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/a/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */