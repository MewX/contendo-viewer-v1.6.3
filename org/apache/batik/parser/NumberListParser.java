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
/*    */ public class NumberListParser
/*    */   extends NumberParser
/*    */ {
/* 39 */   protected NumberListHandler numberListHandler = DefaultNumberListHandler.INSTANCE;
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
/*    */   public void setNumberListHandler(NumberListHandler handler) {
/* 54 */     this.numberListHandler = handler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NumberListHandler getNumberListHandler() {
/* 61 */     return this.numberListHandler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void doParse() throws ParseException, IOException {
/* 68 */     this.numberListHandler.startNumberList();
/*    */     
/* 70 */     this.current = this.reader.read();
/* 71 */     skipSpaces();
/*    */     
/*    */     try {
/*    */       do {
/* 75 */         this.numberListHandler.startNumber();
/* 76 */         float f = parseFloat();
/* 77 */         this.numberListHandler.numberValue(f);
/* 78 */         this.numberListHandler.endNumber();
/* 79 */         skipCommaSpaces();
/* 80 */       } while (this.current != -1);
/*    */ 
/*    */     
/*    */     }
/* 84 */     catch (NumberFormatException e) {
/* 85 */       reportUnexpectedCharacterError(this.current);
/*    */     } 
/* 87 */     this.numberListHandler.endNumberList();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/NumberListParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */