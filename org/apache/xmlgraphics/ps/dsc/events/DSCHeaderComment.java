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
/*    */ 
/*    */ public class DSCHeaderComment
/*    */   extends AbstractEvent
/*    */ {
/*    */   private String comment;
/*    */   
/*    */   public DSCHeaderComment(String comment) {
/* 39 */     this.comment = comment;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getComment() {
/* 47 */     return this.comment;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isPSAdobe30() {
/* 55 */     return getComment().startsWith("%!PS-Adobe-3.0".substring(2));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void generate(PSGenerator gen) throws IOException {
/* 63 */     gen.writeln("%!" + getComment());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEventType() {
/* 70 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isHeaderComment() {
/* 77 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/DSCHeaderComment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */