/*    */ package org.apache.xmlgraphics.ps.dsc.events;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ public class PostScriptComment
/*    */   extends AbstractEvent
/*    */ {
/*    */   private String comment;
/*    */   
/*    */   public PostScriptComment(String comment) {
/* 38 */     if (comment != null && comment.startsWith("%")) {
/* 39 */       this.comment = comment.substring(1);
/*    */     } else {
/* 41 */       this.comment = comment;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getComment() {
/* 50 */     return this.comment;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void generate(PSGenerator gen) throws IOException {
/* 57 */     gen.commentln("%" + getComment());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEventType() {
/* 64 */     return 2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isComment() {
/* 71 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/PostScriptComment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */