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
/*    */ public class LengthListParser
/*    */   extends LengthParser
/*    */ {
/*    */   public void setLengthListHandler(LengthListHandler handler) {
/* 51 */     this.lengthHandler = handler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LengthListHandler getLengthListHandler() {
/* 58 */     return (LengthListHandler)this.lengthHandler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void doParse() throws ParseException, IOException {
/* 65 */     ((LengthListHandler)this.lengthHandler).startLengthList();
/*    */     
/* 67 */     this.current = this.reader.read();
/* 68 */     skipSpaces();
/*    */     
/*    */     try {
/*    */       do {
/* 72 */         this.lengthHandler.startLength();
/* 73 */         parseLength();
/* 74 */         this.lengthHandler.endLength();
/* 75 */         skipCommaSpaces();
/* 76 */       } while (this.current != -1);
/*    */ 
/*    */     
/*    */     }
/* 80 */     catch (NumberFormatException e) {
/* 81 */       reportUnexpectedCharacterError(this.current);
/*    */     } 
/* 83 */     ((LengthListHandler)this.lengthHandler).endLengthList();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/LengthListParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */