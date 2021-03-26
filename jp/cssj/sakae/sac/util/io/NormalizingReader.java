/*    */ package jp.cssj.sakae.sac.util.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class NormalizingReader
/*    */   extends Reader
/*    */ {
/*    */   public int read(char[] cbuf, int off, int len) throws IOException {
/* 79 */     if (len == 0) {
/* 80 */       return 0;
/*    */     }
/*    */     
/* 83 */     int c = read();
/* 84 */     if (c == -1) {
/* 85 */       return -1;
/*    */     }
/* 87 */     int result = 0;
/*    */     do {
/* 89 */       cbuf[result + off] = (char)c;
/* 90 */       result++;
/* 91 */       c = read();
/* 92 */     } while (c != -1 && result < len);
/* 93 */     return result;
/*    */   }
/*    */   
/*    */   public abstract int getLine();
/*    */   
/*    */   public abstract int getColumn();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/util/io/NormalizingReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */