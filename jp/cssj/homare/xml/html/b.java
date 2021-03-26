/*    */ package jp.cssj.homare.xml.html;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.CharBuffer;
/*    */ import java.nio.charset.Charset;
/*    */ import java.nio.charset.CoderResult;
/*    */ 
/*    */ public final class b {
/*    */   private static final int a = 8192;
/* 12 */   private static final Charset[] b = new Charset[] { Charset.forName("ISO-2022-JP"), Charset.forName("x-eucJP-Open"), 
/* 13 */       Charset.forName("Windows-31J"), Charset.forName("UTF-8") };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Charset a(InputStream in) throws IOException {
/* 20 */     in.mark(8192);
/* 21 */     byte[] arrayOfByte = new byte[8192];
/* 22 */     int len = in.read(arrayOfByte);
/* 23 */     in.reset();
/* 24 */     if (len == -1) {
/* 25 */       return b.b[b.b.length - 1];
/*    */     }
/* 27 */     ByteBuffer bbuff = ByteBuffer.wrap(arrayOfByte, 0, len);
/* 28 */     CharBuffer cbuff = CharBuffer.allocate(len);
/*    */     
/* 30 */     for (int i = 0; i < b.b.length; i++) {
/* 31 */       Charset cs = b.b[i];
/* 32 */       CoderResult r = cs.newDecoder().decode(bbuff, cbuff, false);
/* 33 */       if (!r.isMalformed() && !r.isUnmappable()) {
/* 34 */         return cs;
/*    */       }
/*    */     } 
/* 37 */     return b.b[b.b.length - 1];
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/html/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */