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
/*    */ public class PDFAXMPSchema
/*    */   extends XMPSchema
/*    */ {
/*    */   public static final String NAMESPACE = "http://www.aiim.org/pdfa/ns/id/";
/* 35 */   private static MergeRuleSet mergeRuleSet = new MergeRuleSet();
/*    */ 
/*    */   
/*    */   public PDFAXMPSchema() {
/* 39 */     super("http://www.aiim.org/pdfa/ns/id/", "pdfaid");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static PDFAAdapter getAdapter(Metadata meta) {
/* 48 */     return new PDFAAdapter(meta, "http://www.aiim.org/pdfa/ns/id/");
/*    */   }
/*    */ 
/*    */   
/*    */   public MergeRuleSet getDefaultMergeRuleSet() {
/* 53 */     return mergeRuleSet;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/schemas/pdf/PDFAXMPSchema.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */