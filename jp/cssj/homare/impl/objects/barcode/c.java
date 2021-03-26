/*     */ package jp.cssj.homare.impl.objects.barcode;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.sakae.c.a.f;
/*     */ import jp.cssj.sakae.c.a.h;
/*     */ import jp.cssj.sakae.c.d.e;
/*     */ import jp.cssj.sakae.c.d.g;
/*     */ import jp.cssj.sakae.c.d.i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class c
/*     */   implements e
/*     */ {
/* 181 */   List<jp.cssj.sakae.c.d.c> a = new ArrayList<>();
/* 182 */   double b = 0.0D;
/*     */   private i c;
/*     */   
/*     */   public void a(int charOffset, h fontStyle, f fontMetrics) {
/* 186 */     this.c = new i(charOffset, fontStyle, fontMetrics);
/*     */   }
/*     */   
/*     */   public void a(int charOffset, char[] ch, int coff, byte clen, int gid) {
/* 190 */     this.b += this.c.a(ch, coff, clen, gid);
/*     */   }
/*     */   
/*     */   public void a(g quad) {
/* 194 */     if (this.c.g > 0) {
/* 195 */       this.a.add(this.c);
/* 196 */       this.c = new i(-1, this.c.a, this.c.b);
/*     */     } 
/* 198 */     this.a.add(quad);
/* 199 */     this.b += quad.c();
/*     */   }
/*     */   
/*     */   public void a() {
/* 203 */     if (this.c.g > 0) {
/* 204 */       this.a.add(this.c);
/* 205 */       this.c = new i(-1, this.c.a, this.c.b);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void b() {
/* 210 */     if (this.c.g > 0)
/* 211 */       this.a.add(this.c); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/barcode/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */