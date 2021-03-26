/*    */ package jp.cssj.sakae.pdf.c.a.d;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import jp.cssj.sakae.a.a;
/*    */ import jp.cssj.sakae.a.e;
/*    */ import jp.cssj.sakae.c.a.j;
/*    */ import jp.cssj.sakae.pdf.c.a.b;
/*    */ import jp.cssj.sakae.pdf.c.e;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends a
/*    */   implements b
/*    */ {
/*    */   private static final long k = 5L;
/* 23 */   private static final jp.cssj.sakae.a.b l = new jp.cssj.sakae.a.b((short)0, (short)140, (short)1000, (short)860);
/*    */   
/* 25 */   public static final b i = new b((byte)1);
/* 26 */   public static final b j = new b((byte)3);
/*    */   
/*    */   private final byte m;
/*    */   
/*    */   private b(byte direction) {
/* 31 */     this.m = direction;
/*    */   }
/*    */   
/*    */   public byte e() {
/* 35 */     return this.m;
/*    */   }
/*    */   
/*    */   public String d() {
/* 39 */     return "MISSING";
/*    */   }
/*    */   
/*    */   public jp.cssj.sakae.a.b f() {
/* 43 */     return l;
/*    */   }
/*    */   
/*    */   public short g() {
/* 47 */     return 860;
/*    */   }
/*    */   
/*    */   public short i() {
/* 51 */     return 140;
/*    */   }
/*    */   
/*    */   public short h() {
/* 55 */     return 700;
/*    */   }
/*    */   
/*    */   public short l() {
/* 59 */     return 500;
/*    */   }
/*    */   
/*    */   public short m() {
/* 63 */     return 500;
/*    */   }
/*    */   
/*    */   public short j() {
/* 67 */     return 0;
/*    */   }
/*    */   
/*    */   public short k() {
/* 71 */     return 0;
/*    */   }
/*    */   
/*    */   public Font q() {
/* 75 */     return null;
/*    */   }
/*    */   
/*    */   public byte h_() {
/* 79 */     return 0;
/*    */   }
/*    */   
/*    */   public boolean a(int c) {
/* 83 */     return true;
/*    */   }
/*    */   
/*    */   public j p() {
/* 87 */     return null;
/*    */   }
/*    */   
/*    */   public e a(String name, jp.cssj.sakae.pdf.b fontRef) {
/* 91 */     return (e)new a(this, name, fontRef);
/*    */   }
/*    */   
/*    */   public e n() {
/* 95 */     return (e)a(null, null);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/d/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */