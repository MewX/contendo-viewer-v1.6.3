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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DSCCommentLanguageLevel
/*    */   extends AbstractDSCComment
/*    */ {
/*    */   private int level;
/*    */   
/*    */   public DSCCommentLanguageLevel() {}
/*    */   
/*    */   public DSCCommentLanguageLevel(int level) {
/* 45 */     this.level = level;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLanguageLevel() {
/* 53 */     return this.level;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 60 */     return "LanguageLevel";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasValues() {
/* 67 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void parseValue(String value) {
/* 74 */     this.level = Integer.parseInt(value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void generate(PSGenerator gen) throws IOException {
/* 81 */     if (this.level <= 0) {
/* 82 */       throw new IllegalStateException("Language Level was not properly set");
/*    */     }
/* 84 */     gen.writeDSCComment(getName(), Integer.valueOf(getLanguageLevel()));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/DSCCommentLanguageLevel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */