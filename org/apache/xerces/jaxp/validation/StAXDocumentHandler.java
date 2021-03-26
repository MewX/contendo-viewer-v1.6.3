package org.apache.xerces.jaxp.validation;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.StartDocument;
import javax.xml.transform.stax.StAXResult;
import org.apache.xerces.xni.XMLDocumentHandler;

interface StAXDocumentHandler extends XMLDocumentHandler {
  void setStAXResult(StAXResult paramStAXResult);
  
  void startDocument(XMLStreamReader paramXMLStreamReader) throws XMLStreamException;
  
  void endDocument(XMLStreamReader paramXMLStreamReader) throws XMLStreamException;
  
  void comment(XMLStreamReader paramXMLStreamReader) throws XMLStreamException;
  
  void processingInstruction(XMLStreamReader paramXMLStreamReader) throws XMLStreamException;
  
  void entityReference(XMLStreamReader paramXMLStreamReader) throws XMLStreamException;
  
  void startDocument(StartDocument paramStartDocument) throws XMLStreamException;
  
  void endDocument(EndDocument paramEndDocument) throws XMLStreamException;
  
  void doctypeDecl(DTD paramDTD) throws XMLStreamException;
  
  void characters(Characters paramCharacters) throws XMLStreamException;
  
  void cdata(Characters paramCharacters) throws XMLStreamException;
  
  void comment(Comment paramComment) throws XMLStreamException;
  
  void processingInstruction(ProcessingInstruction paramProcessingInstruction) throws XMLStreamException;
  
  void entityReference(EntityReference paramEntityReference) throws XMLStreamException;
  
  void setIgnoringCharacters(boolean paramBoolean);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/jaxp/validation/StAXDocumentHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */