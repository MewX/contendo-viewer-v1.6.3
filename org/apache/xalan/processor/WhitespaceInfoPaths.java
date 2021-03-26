/*    */ package org.apache.xalan.processor;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.xalan.templates.Stylesheet;
/*    */ import org.apache.xalan.templates.WhiteSpaceInfo;
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
/*    */ public class WhitespaceInfoPaths
/*    */   extends WhiteSpaceInfo
/*    */ {
/*    */   private Vector m_elements;
/*    */   
/*    */   public void setElements(Vector elems) {
/* 45 */     this.m_elements = elems;
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
/*    */   Vector getElements() {
/* 58 */     return this.m_elements;
/*    */   }
/*    */ 
/*    */   
/*    */   public void clearElements() {
/* 63 */     this.m_elements = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WhitespaceInfoPaths(Stylesheet thisSheet) {
/* 73 */     super(thisSheet);
/* 74 */     setStylesheet(thisSheet);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/WhitespaceInfoPaths.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */