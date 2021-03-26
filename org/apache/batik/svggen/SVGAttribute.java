/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
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
/*    */ public class SVGAttribute
/*    */ {
/*    */   private String name;
/*    */   private Set applicabilitySet;
/*    */   private boolean isSetInclusive;
/*    */   
/*    */   public SVGAttribute(Set applicabilitySet, boolean isSetInclusive) {
/* 61 */     if (applicabilitySet == null) {
/* 62 */       applicabilitySet = new HashSet();
/*    */     }
/* 64 */     this.applicabilitySet = applicabilitySet;
/* 65 */     this.isSetInclusive = isSetInclusive;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean appliesTo(String tag) {
/* 74 */     boolean tagInMap = this.applicabilitySet.contains(tag);
/* 75 */     if (this.isSetInclusive) {
/* 76 */       return tagInMap;
/*    */     }
/* 78 */     return !tagInMap;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGAttribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */