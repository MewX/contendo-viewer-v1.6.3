/*    */ package org.apache.batik.util.io;
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
/*    */ public abstract class NormalizingReader
/*    */   extends Reader
/*    */ {
/*    */   public int read(char[] cbuf, int off, int len) throws IOException {
/* 43 */     if (len == 0) {
/* 44 */       return 0;
/*    */     }
/*    */     
/* 47 */     int c = read();
/* 48 */     if (c == -1) {
/* 49 */       return -1;
/*    */     }
/* 51 */     int result = 0;
/*    */     do {
/* 53 */       cbuf[result + off] = (char)c;
/* 54 */       result++;
/* 55 */       c = read();
/* 56 */     } while (c != -1 && result < len);
/* 57 */     return result;
/*    */   }
/*    */   
/*    */   public abstract int getLine();
/*    */   
/*    */   public abstract int getColumn();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/io/NormalizingReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */