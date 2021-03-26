/*    */ package jp.cssj.homare.ua.a;
/*    */ 
/*    */ import java.util.Map;
/*    */ import jp.cssj.homare.ua.m;
/*    */ 
/*    */ 
/*    */ public class c
/*    */   extends a
/*    */ {
/*    */   public final String[] b;
/*    */   public final short c;
/*    */   
/*    */   public c(String name, String[] idents, short defaultCode) {
/* 14 */     super(name);
/* 15 */     this.b = idents;
/* 16 */     this.c = defaultCode;
/*    */   }
/*    */   
/*    */   public String b() {
/* 20 */     return this.b[this.c - 1];
/*    */   }
/*    */   
/*    */   public short a(m ua) {
/* 24 */     String str = ua.a(this.a);
/* 25 */     return a(str, (jp.cssj.homare.a.c)ua);
/*    */   }
/*    */   
/*    */   public short a(Map<String, String> props, jp.cssj.homare.a.c mh) {
/* 29 */     String str = props.get(this.a);
/* 30 */     return a(str, mh);
/*    */   }
/*    */   
/*    */   private short a(String str, jp.cssj.homare.a.c mh) {
/* 34 */     if (str == null) {
/* 35 */       return this.c;
/*    */     }
/* 37 */     for (short i = 0; i < this.b.length; i = (short)(i + 1)) {
/* 38 */       if (str.equalsIgnoreCase(this.b[i])) {
/* 39 */         return (short)(i + 1);
/*    */       }
/*    */     } 
/* 42 */     mh.a((short)10244, new String[] { this.a, str });
/* 43 */     return this.c;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/ua/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */