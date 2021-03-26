/*    */ package org.apache.xalan.templates;
/*    */ 
/*    */ import org.apache.xpath.XPath;
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
/*    */ public class WhiteSpaceInfo
/*    */   extends ElemTemplate
/*    */ {
/*    */   private boolean m_shouldStripSpace;
/*    */   
/*    */   public boolean getShouldStripSpace() {
/* 44 */     return this.m_shouldStripSpace;
/*    */   }
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
/*    */   public WhiteSpaceInfo(Stylesheet thisSheet) {
/* 58 */     setStylesheet(thisSheet);
/*    */   }
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
/*    */   public WhiteSpaceInfo(XPath matchPattern, boolean shouldStripSpace, Stylesheet thisSheet) {
/* 74 */     this.m_shouldStripSpace = shouldStripSpace;
/*    */     
/* 76 */     setMatch(matchPattern);
/*    */     
/* 78 */     setStylesheet(thisSheet);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void recompose(StylesheetRoot root) {
/* 86 */     root.recomposeWhiteSpaceInfo(this);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/WhiteSpaceInfo.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */