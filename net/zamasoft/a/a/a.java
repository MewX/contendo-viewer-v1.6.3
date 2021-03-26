/*    */ package net.zamasoft.a.a;
/*    */ 
/*    */ import java.lang.ref.SoftReference;
/*    */ import net.zamasoft.a.b;
/*    */ import net.zamasoft.a.b.K;
/*    */ import net.zamasoft.a.b.r;
/*    */ import net.zamasoft.a.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class a
/*    */   implements c
/*    */ {
/*    */   private final c a;
/*    */   private final r b;
/*    */   private SoftReference<b>[] c;
/*    */   
/*    */   public a(c cff, r head, K maxp) {
/* 20 */     this.a = cff;
/* 21 */     this.b = head;
/* 22 */     this.c = (SoftReference<b>[])new SoftReference[maxp.o()];
/*    */   }
/*    */   
/*    */   public synchronized b a(int ix) {
/* 26 */     if (ix >= this.c.length) {
/* 27 */       return null;
/*    */     }
/* 29 */     b glyph = (this.c[ix] == null) ? null : this.c[ix].get();
/* 30 */     if (glyph != null) {
/* 31 */       return glyph;
/*    */     }
/* 33 */     short upm = this.b.l();
/* 34 */     glyph = this.a.a(ix, upm);
/* 35 */     this.c[ix] = new SoftReference<>(glyph);
/* 36 */     return glyph;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */