/*    */ package org.apache.xmlgraphics.xmp;
/*    */ 
/*    */ import org.apache.xmlgraphics.util.QName;
/*    */ import org.apache.xmlgraphics.xmp.merge.MergeRuleSet;
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
/*    */ public class XMPSchema
/*    */ {
/* 30 */   private static MergeRuleSet defaultMergeRuleSet = new MergeRuleSet();
/*    */ 
/*    */   
/*    */   private String namespace;
/*    */ 
/*    */   
/*    */   private String prefix;
/*    */ 
/*    */ 
/*    */   
/*    */   public XMPSchema(String namespace, String preferredPrefix) {
/* 41 */     this.namespace = namespace;
/* 42 */     this.prefix = preferredPrefix;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNamespace() {
/* 47 */     return this.namespace;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPreferredPrefix() {
/* 52 */     return this.prefix;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected QName getQName(String propName) {
/* 61 */     return new QName(getNamespace(), propName);
/*    */   }
/*    */ 
/*    */   
/*    */   public MergeRuleSet getDefaultMergeRuleSet() {
/* 66 */     return defaultMergeRuleSet;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/XMPSchema.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */