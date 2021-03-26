/*    */ package jp.cssj.sakae.a;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Serializable;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class c
/*    */   implements Serializable, j {
/*    */   private static final long a = 0L;
/* 11 */   private Map<g, e> b = new HashMap<>();
/*    */   
/*    */   public e a(g source) throws IOException {
/* 14 */     e font = this.b.get(source);
/* 15 */     if (font == null) {
/* 16 */       font = source.n();
/* 17 */       this.b.put(source, font);
/*    */     } 
/* 19 */     return font;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */