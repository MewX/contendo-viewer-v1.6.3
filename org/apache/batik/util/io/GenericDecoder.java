/*    */ package org.apache.batik.util.io;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
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
/*    */ public class GenericDecoder
/*    */   implements CharDecoder
/*    */ {
/*    */   protected Reader reader;
/*    */   
/*    */   public GenericDecoder(InputStream is, String enc) throws IOException {
/* 46 */     this.reader = new InputStreamReader(is, enc);
/* 47 */     this.reader = new BufferedReader(this.reader);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GenericDecoder(Reader r) {
/* 55 */     this.reader = r;
/* 56 */     if (!(r instanceof BufferedReader)) {
/* 57 */       this.reader = new BufferedReader(this.reader);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int readChar() throws IOException {
/* 66 */     return this.reader.read();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void dispose() throws IOException {
/* 73 */     this.reader.close();
/* 74 */     this.reader = null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/io/GenericDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */