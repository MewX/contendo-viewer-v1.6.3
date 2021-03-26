/*    */ package jp.cssj.homare.impl.a.a;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import jp.cssj.homare.b.a.c.f;
/*    */ import jp.cssj.homare.b.b.b;
/*    */ import jp.cssj.sakae.c.d.a.a;
/*    */ import jp.cssj.sakae.c.d.a.a.d;
/*    */ import jp.cssj.sakae.c.d.g;
/*    */ 
/*    */ 
/*    */ public class c
/*    */   extends d
/*    */ {
/* 15 */   private List<a> b = new ArrayList<>();
/*    */   
/*    */   public c(a hyph) {
/* 18 */     super(hyph);
/* 19 */     this.b.add(hyph);
/*    */   }
/*    */   
/*    */   public void a(g quad) {
/* 23 */     if (quad instanceof b) {
/* 24 */       b.e inlineStartQuad; a hyph; f params; b inlineQuad = (b)quad;
/* 25 */       switch (inlineQuad.b()) {
/*    */         case 1:
/* 27 */           inlineStartQuad = (b.e)inlineQuad;
/* 28 */           params = inlineStartQuad.g.g();
/* 29 */           this.b.add(params.G);
/* 30 */           a(params.G);
/*    */           break;
/*    */ 
/*    */         
/*    */         case 2:
/* 35 */           this.b.remove(this.b.size() - 1);
/* 36 */           hyph = this.b.get(this.b.size() - 1);
/* 37 */           a(hyph);
/*    */           break;
/*    */ 
/*    */         
/*    */         case 3:
/*    */         case 4:
/*    */         case 5:
/*    */           break;
/*    */         
/*    */         default:
/* 47 */           throw new IllegalStateException();
/*    */       } 
/*    */     } 
/* 50 */     super.a(quad);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */