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
/*    */ public class UnparsedDSCComment
/*    */   extends AbstractEvent
/*    */   implements DSCComment
/*    */ {
/*    */   private String name;
/*    */   private String value;
/*    */   
/*    */   public UnparsedDSCComment(String name) {
/* 41 */     this.name = name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 48 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasValues() {
/* 55 */     return (this.value != null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAtend() {
/* 62 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void parseValue(String value) {
/* 69 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void generate(PSGenerator gen) throws IOException {
/* 76 */     gen.writeln("%%" + this.name + (hasValues() ? (": " + this.value) : ""));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isDSCComment() {
/* 83 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEventType() {
/* 90 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DSCComment asDSCComment() {
/* 97 */     return this;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/UnparsedDSCComment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */