/*    */ package org.apache.xmlgraphics.ps.dsc.events;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.xmlgraphics.ps.DSCConstants;
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
/*    */ public class DSCCommentPages
/*    */   extends AbstractDSCComment
/*    */ {
/* 32 */   private int pageCount = -1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DSCCommentPages() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DSCCommentPages(int pageCount) {
/* 45 */     this.pageCount = pageCount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPageCount() {
/* 53 */     return this.pageCount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPageCount(int count) {
/* 61 */     this.pageCount = count;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 68 */     return "Pages";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasValues() {
/* 75 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void parseValue(String value) {
/* 82 */     this.pageCount = Integer.parseInt(value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void generate(PSGenerator gen) throws IOException {
/* 89 */     if (getPageCount() > 0) {
/* 90 */       gen.writeDSCComment(getName(), Integer.valueOf(getPageCount()));
/*    */     } else {
/* 92 */       gen.writeDSCComment(getName(), DSCConstants.ATEND);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/DSCCommentPages.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */