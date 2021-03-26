/*    */ package org.apache.xmlgraphics.xmp.schemas.pdf;
/*    */ 
/*    */ import org.apache.xmlgraphics.xmp.Metadata;
/*    */ import org.apache.xmlgraphics.xmp.XMPSchema;
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
/*    */ 
/*    */ 
/*    */ public class AdobePDFSchema
/*    */   extends XMPSchema
/*    */ {
/*    */   public static final String NAMESPACE = "http://ns.adobe.com/pdf/1.3/";
/* 35 */   private static MergeRuleSet mergeRuleSet = new MergeRuleSet();
/*    */ 
/*    */   
/*    */   public AdobePDFSchema() {
/* 39 */     super("http://ns.adobe.com/pdf/1.3/", "pdf");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static AdobePDFAdapter getAdapter(Metadata meta) {
/* 48 */     return new AdobePDFAdapter(meta, "http://ns.adobe.com/pdf/1.3/");
/*    */   }
/*    */ 
/*    */   
/*    */   public MergeRuleSet getDefaultMergeRuleSet() {
/* 53 */     return mergeRuleSet;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/schemas/pdf/AdobePDFSchema.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */