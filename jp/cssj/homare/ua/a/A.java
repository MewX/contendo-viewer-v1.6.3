/*    */ package jp.cssj.homare.ua.a;
/*    */ 
/*    */ import java.util.Map;
/*    */ import jp.cssj.homare.ua.m;
/*    */ 
/*    */ public class A
/*    */   extends a {
/*    */   public final String b;
/*    */   
/*    */   public A(String name, String defaultStr) {
/* 11 */     super(name);
/* 12 */     this.b = defaultStr;
/*    */   }
/*    */   
/*    */   public String b() {
/* 16 */     return this.b;
/*    */   }
/*    */   
/*    */   public String a(m ua) {
/* 20 */     String str = ua.a(this.a);
/* 21 */     if (str == null) {
/* 22 */       return this.b;
/*    */     }
/* 24 */     return str;
/*    */   }
/*    */   
/*    */   public String a(Map<String, String> props) {
/* 28 */     String str = props.get(this.a);
/* 29 */     if (str == null) {
/* 30 */       str = this.b;
/*    */     }
/* 32 */     return str;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/ua/a/A.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */