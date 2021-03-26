package org.apache.xml.serialize;

import java.io.IOException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class TextSerializer extends BaseMarkupSerializer {
  public TextSerializer() {
    super(new OutputFormat("text", null, false));
  }
  
  public void setOutputFormat(OutputFormat paramOutputFormat) {
    super.setOutputFormat((paramOutputFormat != null) ? paramOutputFormat : new OutputFormat("text", null, false));
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes) throws SAXException {
    startElement((paramString3 == null) ? paramString2 : paramString3, (AttributeList)null);
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3) throws SAXException {
    endElement((paramString3 == null) ? paramString2 : paramString3);
  }
  
  public void startElement(String paramString, AttributeList paramAttributeList) throws SAXException {
    try {
      ElementState elementState = getElementState();
      if (isDocumentState() && !this._started)
        startDocument(paramString); 
      boolean bool = elementState.preserveSpace;
      elementState = enterElementState(null, null, paramString, bool);
    } catch (IOException iOException) {
      throw new SAXException(iOException);
    } 
  }
  
  public void endElement(String paramString) throws SAXException {
    try {
      endElementIO(paramString);
    } catch (IOException iOException) {
      throw new SAXException(iOException);
    } 
  }
  
  public void endElementIO(String paramString) throws IOException {
    ElementState elementState = getElementState();
    elementState = leaveElementState();
    elementState.afterElement = true;
    elementState.empty = false;
    if (isDocumentState())
      this._printer.flush(); 
  }
  
  public void processingInstructionIO(String paramString1, String paramString2) throws IOException {}
  
  public void comment(String paramString) {}
  
  public void comment(char[] paramArrayOfchar, int paramInt1, int paramInt2) {}
  
  public void characters(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException {
    try {
      ElementState elementState = content();
      elementState.doCData = elementState.inCData = false;
      printText(paramArrayOfchar, paramInt1, paramInt2, true, true);
    } catch (IOException iOException) {
      throw new SAXException(iOException);
    } 
  }
  
  protected void characters(String paramString, boolean paramBoolean) throws IOException {
    ElementState elementState = content();
    elementState.doCData = elementState.inCData = false;
    printText(paramString, true, true);
  }
  
  protected void startDocument(String paramString) throws IOException {
    this._printer.leaveDTD();
    this._started = true;
    serializePreRoot();
  }
  
  protected void serializeElement(Element paramElement) throws IOException {
    String str = paramElement.getTagName();
    ElementState elementState = getElementState();
    if (isDocumentState() && !this._started)
      startDocument(str); 
    boolean bool = elementState.preserveSpace;
    if (paramElement.hasChildNodes()) {
      elementState = enterElementState(null, null, str, bool);
      for (Node node = paramElement.getFirstChild(); node != null; node = node.getNextSibling())
        serializeNode(node); 
      endElementIO(str);
    } else if (!isDocumentState()) {
      elementState.afterElement = true;
      elementState.empty = false;
    } 
  }
  
  protected void serializeNode(Node paramNode) throws IOException {
    String str;
    Node node;
    switch (paramNode.getNodeType()) {
      case 3:
        str = paramNode.getNodeValue();
        if (str != null)
          characters(paramNode.getNodeValue(), true); 
        break;
      case 4:
        str = paramNode.getNodeValue();
        if (str != null)
          characters(paramNode.getNodeValue(), true); 
        break;
      case 1:
        serializeElement((Element)paramNode);
        break;
      case 9:
      case 11:
        for (node = paramNode.getFirstChild(); node != null; node = node.getNextSibling())
          serializeNode(node); 
        break;
    } 
  }
  
  protected ElementState content() {
    ElementState elementState = getElementState();
    if (!isDocumentState()) {
      if (elementState.empty)
        elementState.empty = false; 
      elementState.afterElement = false;
    } 
    return elementState;
  }
  
  protected String getEntityRef(int paramInt) {
    return null;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serialize/TextSerializer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */