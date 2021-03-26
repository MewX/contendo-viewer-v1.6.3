/*    */ package jp.cssj.homare.ua.a;
/*    */ 
/*    */ import java.util.Map;
/*    */ import jp.cssj.homare.a.c;
/*    */ import jp.cssj.homare.ua.m;
/*    */ 
/*    */ public class f
/*    */   extends a
/*    */ {
/*    */   public final long b;
/*    */   
/*    */   public f(String name, long defaultLong) {
/* 13 */     super(name);
/* 14 */     this.b = defaultLong;
/*    */   }
/*    */   
/*    */   public String b() {
/* 18 */     return String.valueOf(this.b);
/*    */   }
/*    */   
/*    */   public long a(m ua) {
/* 22 */     return a(ua.a(this.a), (c)ua);
/*    */   }
/*    */   
/*    */   public long a(Map<String, String> props, c mh) {
/* 26 */     return a(props.get(this.a), mh);
/*    */   }
/*    */   
/*    */   public long a(String str, c mh) {
/* 30 */     if (str == null) {
/* 31 */       return this.b;
/*    */     }
/*    */     try {
/* 34 */       return Long.parseLong(str);
/* 35 */     } catch (NumberFormatException e) {
/* 36 */       mh.a((short)10244, new String[] { this.a, str });
/*    */       
/* 38 */       return this.b;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/ua/a/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */