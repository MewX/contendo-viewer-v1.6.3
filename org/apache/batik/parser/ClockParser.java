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
/*    */ public class ClockParser
/*    */   extends TimingParser
/*    */ {
/*    */   protected ClockHandler clockHandler;
/*    */   protected boolean parseOffset;
/*    */   
/*    */   public ClockParser(boolean parseOffset) {
/* 45 */     super(false, false);
/* 46 */     this.parseOffset = parseOffset;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setClockHandler(ClockHandler handler) {
/* 53 */     this.clockHandler = handler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ClockHandler getClockHandler() {
/* 60 */     return this.clockHandler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void doParse() throws ParseException, IOException {
/* 67 */     this.current = this.reader.read();
/* 68 */     float clockValue = this.parseOffset ? parseOffset() : parseClockValue();
/* 69 */     if (this.current != -1) {
/* 70 */       reportError("end.of.stream.expected", new Object[] { Integer.valueOf(this.current) });
/*    */     }
/*    */     
/* 73 */     if (this.clockHandler != null)
/* 74 */       this.clockHandler.clockValue(clockValue); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/ClockParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */