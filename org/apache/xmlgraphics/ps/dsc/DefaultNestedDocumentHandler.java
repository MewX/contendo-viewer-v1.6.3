/*    */ package org.apache.xmlgraphics.ps.dsc;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.xmlgraphics.ps.PSGenerator;
/*    */ import org.apache.xmlgraphics.ps.dsc.events.DSCComment;
/*    */ import org.apache.xmlgraphics.ps.dsc.events.DSCEvent;
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
/*    */ public class DefaultNestedDocumentHandler
/*    */   implements DSCListener, DSCParserConstants, NestedDocumentHandler
/*    */ {
/*    */   private PSGenerator gen;
/*    */   
/*    */   public DefaultNestedDocumentHandler(PSGenerator gen) {
/* 43 */     this.gen = gen;
/*    */   }
/*    */ 
/*    */   
/*    */   public void handle(DSCEvent event, DSCParser parser) throws IOException, DSCException {
/* 48 */     processEvent(event, parser);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processEvent(DSCEvent event, DSCParser parser) throws IOException, DSCException {
/* 53 */     if (event.isDSCComment()) {
/* 54 */       DSCComment comment = event.asDSCComment();
/* 55 */       if ("BeginDocument".equals(comment.getName())) {
/* 56 */         if (this.gen != null) {
/* 57 */           comment.generate(this.gen);
/*    */         }
/* 59 */         boolean checkEOF = parser.isCheckEOF();
/* 60 */         parser.setCheckEOF(false);
/* 61 */         parser.setListenersDisabled(true);
/* 62 */         comment = parser.nextDSCComment("EndDocument", this.gen);
/* 63 */         if (comment == null) {
/* 64 */           throw new DSCException("File is not DSC-compliant: Didn't find an EndDocument");
/*    */         }
/*    */         
/* 67 */         if (this.gen != null) {
/* 68 */           comment.generate(this.gen);
/*    */         }
/* 70 */         parser.setCheckEOF(checkEOF);
/* 71 */         parser.setListenersDisabled(false);
/* 72 */         parser.next();
/* 73 */       } else if ("BeginData".equals(comment.getName())) {
/* 74 */         if (this.gen != null) {
/* 75 */           comment.generate(this.gen);
/*    */         }
/* 77 */         boolean checkEOF = parser.isCheckEOF();
/* 78 */         parser.setCheckEOF(false);
/* 79 */         parser.setListenersDisabled(true);
/* 80 */         comment = parser.nextDSCComment("EndData", this.gen);
/* 81 */         if (comment == null) {
/* 82 */           throw new DSCException("File is not DSC-compliant: Didn't find an EndData");
/*    */         }
/*    */         
/* 85 */         if (this.gen != null) {
/* 86 */           comment.generate(this.gen);
/*    */         }
/* 88 */         parser.setCheckEOF(checkEOF);
/* 89 */         parser.setListenersDisabled(false);
/* 90 */         parser.next();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/DefaultNestedDocumentHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */