/*    */ package org.apache.xmlgraphics.xmp.schemas;
/*    */ 
/*    */ import org.apache.xmlgraphics.util.QName;
/*    */ import org.apache.xmlgraphics.xmp.Metadata;
/*    */ import org.apache.xmlgraphics.xmp.XMPSchema;
/*    */ import org.apache.xmlgraphics.xmp.merge.MergeRuleSet;
/*    */ import org.apache.xmlgraphics.xmp.merge.NoReplacePropertyMerger;
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
/*    */ 
/*    */ 
/*    */ public class XMPBasicSchema
/*    */   extends XMPSchema
/*    */ {
/*    */   public static final String NAMESPACE = "http://ns.adobe.com/xap/1.0/";
/*    */   public static final String PREFERRED_PREFIX = "xmp";
/*    */   public static final String QUALIFIER_NAMESPACE = "http://ns.adobe.com/xmp/Identifier/qual/1.0/";
/* 44 */   public static final QName SCHEME_QUALIFIER = new QName("http://ns.adobe.com/xmp/Identifier/qual/1.0/", "xmpidq:Scheme");
/*    */   
/* 46 */   private static MergeRuleSet mergeRuleSet = new MergeRuleSet();
/*    */ 
/*    */   
/*    */   public XMPBasicSchema() {
/* 50 */     super("http://ns.adobe.com/xap/1.0/", "xmp");
/*    */   }
/*    */ 
/*    */   
/*    */   static {
/* 55 */     mergeRuleSet.addRule(new QName("http://ns.adobe.com/xap/1.0/", "CreateDate"), (PropertyMerger)new NoReplacePropertyMerger());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static XMPBasicAdapter getAdapter(Metadata meta) {
/* 64 */     return new XMPBasicAdapter(meta, "http://ns.adobe.com/xap/1.0/");
/*    */   }
/*    */ 
/*    */   
/*    */   public MergeRuleSet getDefaultMergeRuleSet() {
/* 69 */     return mergeRuleSet;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/schemas/XMPBasicSchema.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */