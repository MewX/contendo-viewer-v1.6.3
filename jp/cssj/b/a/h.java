/*    */ package jp.cssj.b.a;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletResponse;
/*    */ import jp.cssj.b.c;
/*    */ import jp.cssj.e.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class h
/*    */ {
/*    */   public static void a(c session, ServletResponse response) throws IOException {
/* 31 */     session.a(new i(response));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String a(a metaSource) {
/*    */     String mimeType;
/*    */     try {
/* 44 */       mimeType = metaSource.c();
/* 45 */       if (mimeType == null) {
/* 46 */         return null;
/*    */       }
/* 48 */       String encoding = metaSource.a();
/* 49 */       if (encoding != null) {
/* 50 */         mimeType = mimeType + "; charset=" + encoding;
/*    */       }
/* 52 */     } catch (Exception e) {
/* 53 */       return null;
/*    */     } 
/* 55 */     return mimeType;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/b/a/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */