/*    */ package jp.cssj.homare.ua.a;
/*    */ 
/*    */ import java.util.Map;
/*    */ import jp.cssj.homare.a.c;
/*    */ import jp.cssj.homare.ua.m;
/*    */ 
/*    */ public class b
/*    */   extends a
/*    */ {
/*    */   public final boolean b;
/*    */   
/*    */   public b(String name, boolean defaultBoolean) {
/* 13 */     super(name);
/* 14 */     this.b = defaultBoolean;
/*    */   }
/*    */   
/*    */   public String b() {
/* 18 */     return String.valueOf(this.b);
/*    */   }
/*    */   
/*    */   public boolean a(Map<String, String> props, c mh) {
/* 22 */     String str = props.get(this.a);
/* 23 */     return a(str, mh);
/*    */   }
/*    */   
/*    */   public boolean a(m ua) {
/* 27 */     String str = ua.a(this.a);
/* 28 */     return a(str, (c)ua);
/*    */   }
/*    */   
/*    */   public boolean a(String str, c mh) {
/* 32 */     if (str == null) {
/* 33 */       return this.b;
/*    */     }
/* 35 */     if (str.equalsIgnoreCase("true")) {
/* 36 */       return true;
/*    */     }
/* 38 */     if (str.equalsIgnoreCase("false")) {
/* 39 */       return false;
/*    */     }
/* 41 */     mh.a((short)10244, new String[] { this.a, str });
/* 42 */     return false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/ua/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */