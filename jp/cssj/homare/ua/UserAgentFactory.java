/*    */ package jp.cssj.homare.ua;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import jp.cssj.c.a;
/*    */ 
/*    */ public interface UserAgentFactory
/*    */   extends a<String>
/*    */ {
/*    */   Iterator<a> types();
/*    */   
/*    */   m createUserAgent();
/*    */   
/*    */   public static final class a
/*    */   {
/*    */     public final String a;
/*    */     public final String b;
/*    */     public final String c;
/*    */     
/*    */     public a(String name, String mimeType, String suffix) {
/* 20 */       this.a = name;
/* 21 */       this.b = mimeType;
/* 22 */       this.c = suffix;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/ua/UserAgentFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */