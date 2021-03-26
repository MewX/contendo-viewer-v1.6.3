/*    */ package org.apache.xmlgraphics.ps.dsc.events;
/*    */ 
/*    */ import org.apache.xmlgraphics.ps.PSResource;
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
/*    */ public class DSCCommentIncludeResource
/*    */   extends AbstractResourceDSCComment
/*    */ {
/*    */   public DSCCommentIncludeResource() {}
/*    */   
/*    */   public DSCCommentIncludeResource(PSResource resource) {
/* 42 */     super(resource);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 47 */     return "IncludeResource";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/DSCCommentIncludeResource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */