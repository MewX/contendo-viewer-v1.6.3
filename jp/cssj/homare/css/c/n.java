/*    */ package jp.cssj.homare.css.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class n
/*    */ {
/* 16 */   private static final Logger a = Logger.getLogger(n.class.getName());
/*    */   
/*    */   protected abstract m a(String paramString);
/*    */   
/*    */   public final k a(String name, LexicalUnit lu, m ua, URI uri, boolean important) {
/* 21 */     m ph = a(name.toLowerCase());
/* 22 */     if (ph != null) {
/*    */       k property;
/*    */       try {
/* 25 */         property = ph.a(lu, ua, uri, important);
/* 26 */       } catch (l e) {
/* 27 */         String str = name + ":" + lu + ":" + e.getMessage();
/* 28 */         a.log(Level.FINE, str, e);
/* 29 */         ua.a((short)10262, name, lu.toString(), e.getMessage());
/* 30 */         return null;
/*    */       } 
/* 32 */       return property;
/*    */     } 
/* 34 */     ua.a((short)10242, name);
/* 35 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/c/n.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */