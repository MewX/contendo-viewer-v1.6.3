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
/*    */ public class AdobePDFAdapter
/*    */   extends XMPSchemaAdapter
/*    */ {
/*    */   private static final String KEYWORDS = "Keywords";
/*    */   private static final String PDFVERSION = "PDFVersion";
/*    */   private static final String PRODUCER = "Producer";
/*    */   private static final String TRAPPED = "Trapped";
/*    */   
/*    */   public AdobePDFAdapter(Metadata meta, String namespace) {
/* 41 */     super(meta, XMPSchemaRegistry.getInstance().getSchema(namespace));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getKeywords() {
/* 46 */     return getValue("Keywords");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setKeywords(String value) {
/* 54 */     setValue("Keywords", value);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPDFVersion() {
/* 59 */     return getValue("PDFVersion");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPDFVersion(String value) {
/* 67 */     setValue("PDFVersion", value);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getProducer() {
/* 72 */     return getValue("Producer");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setProducer(String value) {
/* 80 */     setValue("Producer", value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTrapped(String v) {
/* 88 */     setValue("Trapped", v);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/schemas/pdf/AdobePDFAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */