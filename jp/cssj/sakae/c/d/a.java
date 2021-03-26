/*    */ package jp.cssj.sakae.c.d;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class a
/*    */   implements h
/*    */ {
/*    */   public short f_() {
/* 11 */     return 1;
/*    */   }
/*    */   
/*    */   public void a(e gh) {
/* 15 */     int glen = l();
/* 16 */     int charOffset = e();
/* 17 */     gh.a(charOffset, b(), d());
/* 18 */     byte[] clens = k();
/* 19 */     char[] ch = h();
/* 20 */     int[] gids = j();
/* 21 */     for (int i = 0, coff = 0; i < glen; i++) {
/* 22 */       byte clen = clens[i];
/* 23 */       gh.a(charOffset, ch, coff, clen, gids[i]);
/* 24 */       coff += clen;
/* 25 */       charOffset += clen;
/*    */     } 
/* 27 */     gh.a();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 34 */     StringBuffer buff = new StringBuffer();
/* 35 */     buff.append("[Text]");
/* 36 */     buff.append(h(), 0, i());
/* 37 */     buff.append("[");
/* 38 */     int glen = l();
/* 39 */     int[] gids = j();
/* 40 */     for (int i = 0; i < glen; i++) {
/* 41 */       int gid = gids[i];
/* 42 */       if (i > 0) {
/* 43 */         buff.append(',');
/*    */       }
/* 45 */       buff.append(Integer.toHexString(gid));
/*    */     } 
/* 47 */     buff.append("]");
/* 48 */     buff.append("[/Text]");
/* 49 */     return buff.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/d/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */