/*    */ package jp.cssj.homare.b.a.c;
/*    */ 
/*    */ import jp.cssj.sakae.c.b.b;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class h
/*    */ {
/*    */   public static final byte a = 0;
/*    */   public static final byte b = 1;
/*    */   public static final byte c = 2;
/*    */   public static final byte d = 3;
/*    */   public static final byte e = 0;
/*    */   public static final byte f = 1;
/*    */   public final b g;
/*    */   public final byte h;
/*    */   public final byte i;
/*    */   public final w j;
/*    */   public final m k;
/*    */   
/*    */   public static h a(b image, byte repeat, byte attachment, w position, m size) {
/* 74 */     return new h(image, repeat, attachment, position, size);
/*    */   }
/*    */   
/*    */   private h(b image, byte repeat, byte attachment, w position, m size) {
/* 78 */     if (!l && (image == null || position == null || size == null)) throw new AssertionError(); 
/* 79 */     this.g = image;
/* 80 */     this.h = repeat;
/* 81 */     this.i = attachment;
/* 82 */     this.j = position;
/* 83 */     this.k = size;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 87 */     return super.toString() + "[image=" + this.g + ",repeat=" + this.h + ",attachment=" + this.i + ",position=" + this.j + ",size=" + this.k + "]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/c/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */