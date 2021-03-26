/*    */ package org.apache.xmlgraphics.ps.dsc.events;
/*    */ 
/*    */ import java.util.Collection;
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
/*    */ public class DSCCommentDocumentSuppliedResources
/*    */   extends AbstractResourcesDSCComment
/*    */ {
/*    */   public DSCCommentDocumentSuppliedResources() {}
/*    */   
/*    */   public DSCCommentDocumentSuppliedResources(Collection resources) {
/* 43 */     super(resources);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 50 */     return "DocumentSuppliedResources";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/DSCCommentDocumentSuppliedResources.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */