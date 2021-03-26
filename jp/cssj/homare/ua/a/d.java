/*    */ package jp.cssj.homare.ua.a;
/*    */ 
/*    */ import java.util.Map;
/*    */ import jp.cssj.homare.a.c;
/*    */ import jp.cssj.homare.ua.m;
/*    */ 
/*    */ 
/*    */ public class d
/*    */   extends a
/*    */ {
/*    */   public final double b;
/*    */   
/*    */   public d(String name, double defaultDouble) {
/* 14 */     super(name);
/* 15 */     this.b = defaultDouble;
/*    */   }
/*    */   
/*    */   public String b() {
/* 19 */     return String.valueOf(this.b);
/*    */   }
/*    */   
/*    */   public double a(m ua) {
/* 23 */     return a(ua.a(this.a), (c)ua);
/*    */   }
/*    */   
/*    */   public double a(Map<String, String> props, c mh) {
/* 27 */     return a(props.get(this.a), mh);
/*    */   }
/*    */   
/*    */   public double a(String str, c mh) {
/* 31 */     if (str == null) {
/* 32 */       return this.b;
/*    */     }
/*    */     try {
/* 35 */       return jp.cssj.sakae.e.d.a(str);
/* 36 */     } catch (NumberFormatException e) {
/* 37 */       mh.a((short)10244, new String[] { this.a, str });
/*    */       
/* 39 */       return this.b;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/ua/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */