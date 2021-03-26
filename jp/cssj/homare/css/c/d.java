/*    */ package jp.cssj.homare.css.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class d
/*    */   extends c
/*    */   implements o
/*    */ {
/*    */   protected d(String name) {
/* 21 */     super(name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected static final class a
/*    */   {
/* 32 */     private final List<e.a> a = new ArrayList<>();
/*    */     
/*    */     public void a(j info, ad value) {
/* 35 */       e.a entry = new e.a(info, value);
/* 36 */       for (int i = 0; i < this.a.size(); i++) {
/* 37 */         e.a e = this.a.get(i);
/* 38 */         if (e.a() == info) {
/* 39 */           this.a.set(i, entry);
/*    */           return;
/*    */         } 
/*    */       } 
/* 43 */       this.a.add(entry);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 47 */       StringBuffer buff = new StringBuffer();
/* 48 */       for (int i = 0; i < this.a.size(); i++) {
/* 49 */         e.a e = this.a.get(i);
/* 50 */         buff.append(e).append(' ');
/*    */       } 
/* 52 */       return buff.toString();
/*    */     }
/*    */   }
/*    */   
/*    */   public k a(LexicalUnit lu, m ua, URI uri, boolean important) throws l {
/* 57 */     a primitives = new a();
/* 58 */     a(lu, ua, uri, primitives);
/* 59 */     e.a[] entries = (e.a[])a.a(primitives).toArray((Object[])new e.a[a.a(primitives).size()]);
/* 60 */     return new e(b(), entries, uri, important);
/*    */   }
/*    */   
/*    */   public abstract void a(LexicalUnit paramLexicalUnit, m paramm, URI paramURI, a parama) throws l;
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/c/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */