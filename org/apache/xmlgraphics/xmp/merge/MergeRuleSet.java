/*    */ package org.apache.xmlgraphics.xmp.merge;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.xmlgraphics.util.QName;
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
/*    */ public class MergeRuleSet
/*    */ {
/* 33 */   private Map rules = new HashMap<Object, Object>();
/* 34 */   private PropertyMerger defaultMerger = new ReplacePropertyMerger();
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
/*    */   public PropertyMerger getPropertyMergerFor(XMPProperty prop) {
/* 46 */     PropertyMerger merger = (PropertyMerger)this.rules.get(prop.getName());
/* 47 */     return (merger != null) ? merger : this.defaultMerger;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addRule(QName propName, PropertyMerger merger) {
/* 56 */     this.rules.put(propName, merger);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/merge/MergeRuleSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */