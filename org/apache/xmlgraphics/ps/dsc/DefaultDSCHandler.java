/*    */ package org.apache.xmlgraphics.ps.dsc;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.xmlgraphics.ps.PSGenerator;
/*    */ import org.apache.xmlgraphics.ps.dsc.events.DSCComment;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultDSCHandler
/*    */   implements DSCHandler
/*    */ {
/*    */   protected OutputStream out;
/*    */   protected PSGenerator gen;
/*    */   
/*    */   public DefaultDSCHandler(OutputStream out) {
/* 44 */     this.out = out;
/* 45 */     this.gen = new PSGenerator(this.out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void startDocument(String header) throws IOException {
/* 50 */     this.gen.writeln(header);
/*    */   }
/*    */ 
/*    */   
/*    */   public void endDocument() throws IOException {
/* 55 */     this.gen.writeDSCComment("EOF");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleDSCComment(DSCComment comment) throws IOException {
/* 63 */     comment.generate(this.gen);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void line(String line) throws IOException {
/* 69 */     this.gen.writeln(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public void comment(String comment) throws IOException {
/* 74 */     this.gen.commentln("%" + comment);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/DefaultDSCHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */