/*    */ package org.apache.batik.util.gui.xmleditor;
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
/*    */ public class XMLToken
/*    */ {
/*    */   private int context;
/*    */   private int startOffset;
/*    */   private int endOffset;
/*    */   
/*    */   public XMLToken(int context, int startOffset, int endOffset) {
/* 35 */     this.context = context;
/* 36 */     this.startOffset = startOffset;
/* 37 */     this.endOffset = endOffset;
/*    */   }
/*    */   
/*    */   public int getContext() {
/* 41 */     return this.context;
/*    */   }
/*    */   
/*    */   public int getStartOffset() {
/* 45 */     return this.startOffset;
/*    */   }
/*    */   
/*    */   public int getEndOffset() {
/* 49 */     return this.endOffset;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/xmleditor/XMLToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */