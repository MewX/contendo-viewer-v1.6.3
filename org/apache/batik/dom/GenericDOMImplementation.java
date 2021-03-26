/*    */ package org.apache.batik.dom;
/*    */ 
/*    */ import org.apache.batik.xml.XMLUtilities;
/*    */ import org.w3c.dom.DOMException;
/*    */ import org.w3c.dom.DOMImplementation;
/*    */ import org.w3c.dom.Document;
/*    */ import org.w3c.dom.DocumentType;
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
/*    */ public class GenericDOMImplementation
/*    */   extends AbstractDOMImplementation
/*    */ {
/* 39 */   protected static final DOMImplementation DOM_IMPLEMENTATION = new GenericDOMImplementation();
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
/*    */   public static DOMImplementation getDOMImplementation() {
/* 52 */     return DOM_IMPLEMENTATION;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Document createDocument(String namespaceURI, String qualifiedName, DocumentType doctype) throws DOMException {
/* 64 */     Document result = new GenericDocument(doctype, this);
/* 65 */     result.appendChild(result.createElementNS(namespaceURI, qualifiedName));
/*    */     
/* 67 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DocumentType createDocumentType(String qualifiedName, String publicId, String systemId) {
/* 78 */     if (qualifiedName == null) {
/* 79 */       qualifiedName = "";
/*    */     }
/* 81 */     int test = XMLUtilities.testXMLQName(qualifiedName);
/* 82 */     if ((test & 0x1) == 0) {
/* 83 */       throw new DOMException((short)5, formatMessage("xml.name", new Object[] { qualifiedName }));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 88 */     if ((test & 0x2) == 0) {
/* 89 */       throw new DOMException((short)5, formatMessage("invalid.qname", new Object[] { qualifiedName }));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 94 */     return new GenericDocumentType(qualifiedName, publicId, systemId);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/GenericDOMImplementation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */