/*    */ package org.apache.xmlgraphics.xmp;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.xmlgraphics.xmp.schemas.DublinCoreSchema;
/*    */ import org.apache.xmlgraphics.xmp.schemas.XMPBasicSchema;
/*    */ import org.apache.xmlgraphics.xmp.schemas.pdf.AdobePDFSchema;
/*    */ import org.apache.xmlgraphics.xmp.schemas.pdf.PDFAXMPSchema;
/*    */ import org.apache.xmlgraphics.xmp.schemas.pdf.PDFUAXMPSchema;
/*    */ import org.apache.xmlgraphics.xmp.schemas.pdf.PDFVTXMPSchema;
/*    */ import org.apache.xmlgraphics.xmp.schemas.pdf.PDFXXMPSchema;
/*    */ import org.apache.xmlgraphics.xmp.schemas.pdf.XAPMMXMPSchema;
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
/*    */ public final class XMPSchemaRegistry
/*    */ {
/* 38 */   private static XMPSchemaRegistry instance = new XMPSchemaRegistry();
/*    */   
/* 40 */   private Map schemas = new HashMap<Object, Object>();
/*    */   
/*    */   private XMPSchemaRegistry() {
/* 43 */     init();
/*    */   }
/*    */ 
/*    */   
/*    */   public static XMPSchemaRegistry getInstance() {
/* 48 */     return instance;
/*    */   }
/*    */   
/*    */   private void init() {
/* 52 */     addSchema((XMPSchema)new DublinCoreSchema());
/* 53 */     addSchema((XMPSchema)new PDFAXMPSchema());
/* 54 */     addSchema((XMPSchema)new XMPBasicSchema());
/* 55 */     addSchema((XMPSchema)new AdobePDFSchema());
/* 56 */     addSchema((XMPSchema)new PDFXXMPSchema());
/* 57 */     addSchema((XMPSchema)new PDFVTXMPSchema());
/* 58 */     addSchema((XMPSchema)new XAPMMXMPSchema());
/* 59 */     addSchema((XMPSchema)new PDFUAXMPSchema());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addSchema(XMPSchema schema) {
/* 67 */     this.schemas.put(schema.getNamespace(), schema);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public XMPSchema getSchema(String namespace) {
/* 76 */     return (XMPSchema)this.schemas.get(namespace);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/XMPSchemaRegistry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */