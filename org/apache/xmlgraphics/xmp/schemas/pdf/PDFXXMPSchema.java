/*    */ package org.apache.xmlgraphics.xmp.schemas.pdf;
/*    */ 
/*    */ import org.apache.xmlgraphics.xmp.Metadata;
/*    */ import org.apache.xmlgraphics.xmp.XMPSchema;
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
/*    */ public class PDFXXMPSchema
/*    */   extends XMPSchema
/*    */ {
/*    */   public static final String NAMESPACE = "http://www.npes.org/pdfx/ns/id/";
/*    */   
/*    */   public PDFXXMPSchema() {
/* 31 */     super("http://www.npes.org/pdfx/ns/id/", "pdfxid");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static PDFXAdapter getAdapter(Metadata meta) {
/* 40 */     return new PDFXAdapter(meta, "http://www.npes.org/pdfx/ns/id/");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/schemas/pdf/PDFXXMPSchema.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */