/*    */ package org.apache.batik.parser;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ public class LengthPairListParser
/*    */   extends LengthListParser
/*    */ {
/*    */   protected void doParse() throws ParseException, IOException {
/* 37 */     ((LengthListHandler)this.lengthHandler).startLengthList();
/*    */     
/* 39 */     this.current = this.reader.read();
/* 40 */     skipSpaces();
/*    */     
/*    */     try {
/*    */       while (true) {
/* 44 */         this.lengthHandler.startLength();
/* 45 */         parseLength();
/* 46 */         this.lengthHandler.endLength();
/* 47 */         skipCommaSpaces();
/* 48 */         this.lengthHandler.startLength();
/* 49 */         parseLength();
/* 50 */         this.lengthHandler.endLength();
/* 51 */         skipSpaces();
/* 52 */         if (this.current == -1) {
/*    */           break;
/*    */         }
/* 55 */         if (this.current != 59) {
/* 56 */           reportUnexpectedCharacterError(this.current);
/*    */         }
/* 58 */         this.current = this.reader.read();
/* 59 */         skipSpaces();
/*    */       } 
/* 61 */     } catch (NumberFormatException e) {
/* 62 */       reportUnexpectedCharacterError(this.current);
/*    */     } 
/* 64 */     ((LengthListHandler)this.lengthHandler).endLengthList();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/LengthPairListParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */