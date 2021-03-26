/*    */ package org.apache.xmlgraphics.xmp.schemas;
/*    */ 
/*    */ import org.apache.xmlgraphics.util.QName;
/*    */ import org.apache.xmlgraphics.xmp.Metadata;
/*    */ import org.apache.xmlgraphics.xmp.XMPSchema;
/*    */ import org.apache.xmlgraphics.xmp.merge.ArrayAddPropertyMerger;
/*    */ import org.apache.xmlgraphics.xmp.merge.MergeRuleSet;
/*    */ import org.apache.xmlgraphics.xmp.merge.PropertyMerger;
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
/*    */ public class DublinCoreSchema
/*    */   extends XMPSchema
/*    */ {
/*    */   public static final String NAMESPACE = "http://purl.org/dc/elements/1.1/";
/* 40 */   private static MergeRuleSet dcMergeRuleSet = new MergeRuleSet();
/*    */   static {
/* 42 */     dcMergeRuleSet.addRule(new QName("http://purl.org/dc/elements/1.1/", "date"), (PropertyMerger)new ArrayAddPropertyMerger());
/*    */   }
/*    */ 
/*    */   
/*    */   public DublinCoreSchema() {
/* 47 */     super("http://purl.org/dc/elements/1.1/", "dc");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static DublinCoreAdapter getAdapter(Metadata meta) {
/* 56 */     return new DublinCoreAdapter(meta);
/*    */   }
/*    */ 
/*    */   
/*    */   public MergeRuleSet getDefaultMergeRuleSet() {
/* 61 */     return dcMergeRuleSet;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/schemas/DublinCoreSchema.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */