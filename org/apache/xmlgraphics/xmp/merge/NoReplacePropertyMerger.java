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
/*    */ 
/*    */ public class NoReplacePropertyMerger
/*    */   implements PropertyMerger
/*    */ {
/*    */   public void merge(XMPProperty sourceProp, Metadata target) {
/* 36 */     XMPProperty prop = target.getProperty(sourceProp.getName());
/* 37 */     if (prop == null)
/* 38 */       target.setProperty(sourceProp); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/merge/NoReplacePropertyMerger.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */