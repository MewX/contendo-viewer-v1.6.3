/*    */ package org.apache.xmlgraphics.xmp.merge;
/*    */ 
/*    */ import org.apache.xmlgraphics.xmp.Metadata;
/*    */ import org.apache.xmlgraphics.xmp.XMPProperty;
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
/*    */ public class ReplacePropertyMerger
/*    */   implements PropertyMerger
/*    */ {
/*    */   public void merge(XMPProperty sourceProp, Metadata target) {
/* 35 */     target.setProperty(sourceProp);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/merge/ReplacePropertyMerger.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */