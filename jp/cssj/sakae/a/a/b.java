/*    */ package jp.cssj.sakae.a.a;
/*    */ 
/*    */ import jp.cssj.sakae.a.a;
/*    */ import jp.cssj.sakae.a.e;
/*    */ import jp.cssj.sakae.c.a.j;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends a
/*    */ {
/*    */   private static final long k = 1L;
/* 17 */   private static final jp.cssj.sakae.a.b l = new jp.cssj.sakae.a.b((short)0, (short)140, (short)1000, (short)860);
/*    */   
/* 19 */   public static final b i = new b((byte)1);
/* 20 */   public static final b j = new b((byte)3);
/*    */   
/*    */   private final byte m;
/*    */   
/*    */   private b(byte direction) {
/* 25 */     this.m = direction;
/*    */   }
/*    */   
/*    */   public byte e() {
/* 29 */     return this.m;
/*    */   }
/*    */   
/*    */   public String d() {
/* 33 */     return "emoji";
/*    */   }
/*    */   
/*    */   public jp.cssj.sakae.a.b f() {
/* 37 */     return l;
/*    */   }
/*    */   
/*    */   public short g() {
/* 41 */     return 860;
/*    */   }
/*    */   
/*    */   public short i() {
/* 45 */     return 140;
/*    */   }
/*    */   
/*    */   public short h() {
/* 49 */     return 700;
/*    */   }
/*    */   
/*    */   public short l() {
/* 53 */     return 500;
/*    */   }
/*    */   
/*    */   public short m() {
/* 57 */     return 500;
/*    */   }
/*    */   
/*    */   public short j() {
/* 61 */     return 0;
/*    */   }
/*    */   
/*    */   public short k() {
/* 65 */     return 0;
/*    */   }
/*    */   
/*    */   public boolean a(int c) {
/* 69 */     return (b.class.getResource("emoji_u" + Integer.toHexString(c).toLowerCase() + ".svg") != null);
/*    */   }
/*    */   
/*    */   public j o() {
/* 73 */     return null;
/*    */   }
/*    */   
/*    */   public e n() {
/* 77 */     return (e)new a(this);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/a/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */