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
/*    */ public class TimingSpecifierListParser
/*    */   extends TimingSpecifierParser
/*    */ {
/*    */   public TimingSpecifierListParser(boolean useSVG11AccessKeys, boolean useSVG12AccessKeys) {
/* 41 */     super(useSVG11AccessKeys, useSVG12AccessKeys);
/* 42 */     this.timingSpecifierHandler = DefaultTimingSpecifierListHandler.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTimingSpecifierListHandler(TimingSpecifierListHandler handler) {
/* 50 */     this.timingSpecifierHandler = handler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TimingSpecifierListHandler getTimingSpecifierListHandler() {
/* 57 */     return (TimingSpecifierListHandler)this.timingSpecifierHandler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void doParse() throws ParseException, IOException {
/* 64 */     this.current = this.reader.read();
/*    */     
/* 66 */     ((TimingSpecifierListHandler)this.timingSpecifierHandler).startTimingSpecifierList();
/*    */ 
/*    */     
/* 69 */     skipSpaces();
/*    */     
/* 71 */     if (this.current != -1) {
/*    */       while (true) {
/* 73 */         Object[] spec = parseTimingSpecifier();
/* 74 */         handleTimingSpecifier(spec);
/* 75 */         skipSpaces();
/* 76 */         if (this.current == -1) {
/*    */           break;
/*    */         }
/* 79 */         if (this.current == 59) {
/* 80 */           this.current = this.reader.read();
/*    */           continue;
/*    */         } 
/* 83 */         reportUnexpectedCharacterError(this.current);
/*    */       } 
/*    */     }
/*    */     
/* 87 */     skipSpaces();
/*    */     
/* 89 */     if (this.current != -1) {
/* 90 */       reportUnexpectedCharacterError(this.current);
/*    */     }
/*    */     
/* 93 */     ((TimingSpecifierListHandler)this.timingSpecifierHandler).endTimingSpecifierList();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/TimingSpecifierListParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */