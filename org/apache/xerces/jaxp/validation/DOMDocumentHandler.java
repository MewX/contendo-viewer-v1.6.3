package org.apache.xerces.jaxp.validation;

import javax.xml.transform.dom.DOMResult;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.XNIException;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

interface DOMDocumentHandler extends XMLDocumentHandler {
  void setDOMResult(DOMResult paramDOMResult);
  
  void doctypeDecl(DocumentType paramDocumentType) throws XNIException;
  
  void characters(Text paramText) throws XNIException;
  
  void cdata(CDATASection paramCDATASection) throws XNIException;
  
  void comment(Comment paramComment) throws XNIException;
  
  void processingInstruction(ProcessingInstruction paramProcessingInstruction) throws XNIException;
  
  void setIgnoringCharacters(boolean paramBoolean);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/jaxp/validation/DOMDocumentHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */