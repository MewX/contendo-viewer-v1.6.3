/*    */ package org.apache.xmlgraphics.xmp.schemas.pdf;
/*    */ 
/*    */ import org.apache.xmlgraphics.xmp.Metadata;
/*    */ import org.apache.xmlgraphics.xmp.XMPSchemaAdapter;
/*    */ import org.apache.xmlgraphics.xmp.XMPSchemaRegistry;
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
/*    */ public class PDFAAdapter
/*    */   extends XMPSchemaAdapter
/*    */ {
/*    */   private static final String PART = "part";
/*    */   private static final String AMD = "amd";
/*    */   private static final String CONFORMANCE = "conformance";
/*    */   
/*    */   public PDFAAdapter(Metadata meta, String namespace) {
/* 43 */     super(meta, XMPSchemaRegistry.getInstance().getSchema(namespace));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPart(int value) {
/* 51 */     setValue("part", Integer.toString(value));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPart() {
/* 56 */     return Integer.parseInt(getValue("part"));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAmendment(String value) {
/* 64 */     setValue("amd", value);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAmendment() {
/* 69 */     return getValue("amd");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setConformance(String value) {
/* 77 */     setValue("conformance", value);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getConformance() {
/* 82 */     return getValue("conformance");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/schemas/pdf/PDFAAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */