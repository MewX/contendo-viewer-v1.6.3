/*    */ package org.apache.xmlgraphics.ps.dsc.events;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.xmlgraphics.ps.PSGenerator;
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
/*    */ 
/*    */ public class DSCCommentBeginResource
/*    */   extends AbstractResourceDSCComment
/*    */ {
/*    */   private Integer min;
/*    */   private Integer max;
/*    */   
/*    */   public DSCCommentBeginResource() {}
/*    */   
/*    */   public DSCCommentBeginResource(PSResource resource) {
/* 48 */     super(resource);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DSCCommentBeginResource(PSResource resource, int min, int max) {
/* 58 */     super(resource);
/* 59 */     this.min = Integer.valueOf(min);
/* 60 */     this.max = Integer.valueOf(max);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Integer getMin() {
/* 68 */     return this.min;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Integer getMax() {
/* 76 */     return this.max;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 81 */     return "BeginResource";
/*    */   }
/*    */ 
/*    */   
/*    */   public void generate(PSGenerator gen) throws IOException {
/* 86 */     if (getMin() != null) {
/* 87 */       Object[] params = { getResource(), getMin(), getMax() };
/* 88 */       gen.writeDSCComment(getName(), params);
/*    */     } else {
/* 90 */       super.generate(gen);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/DSCCommentBeginResource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */