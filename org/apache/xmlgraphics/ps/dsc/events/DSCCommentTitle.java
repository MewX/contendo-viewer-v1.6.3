/*    */ package org.apache.xmlgraphics.ps.dsc.events;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.apache.xmlgraphics.ps.PSGenerator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DSCCommentTitle
/*    */   extends AbstractDSCComment
/*    */ {
/*    */   private String title;
/*    */   
/*    */   public DSCCommentTitle() {}
/*    */   
/*    */   public DSCCommentTitle(String title) {
/* 47 */     this.title = title;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTitle() {
/* 55 */     return this.title;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 60 */     return "Title";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasValues() {
/* 65 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void parseValue(String value) {
/* 70 */     List params = splitParams(value);
/* 71 */     Iterator<String> iter = params.iterator();
/* 72 */     this.title = iter.next();
/*    */   }
/*    */ 
/*    */   
/*    */   public void generate(PSGenerator gen) throws IOException {
/* 77 */     gen.writeDSCComment(getName(), getTitle());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/DSCCommentTitle.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */