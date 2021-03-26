package org.apache.xerces.jaxp;

import java.util.HashMap;
import org.apache.xerces.impl.validation.EntityState;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLDTDFilter;
import org.apache.xerces.xni.parser.XMLDTDSource;

final class UnparsedEntityHandler implements EntityState, XMLDTDFilter {
  private XMLDTDSource fDTDSource;
  
  private XMLDTDHandler fDTDHandler;
  
  private final ValidationManager fValidationManager;
  
  private HashMap fUnparsedEntities = null;
  
  UnparsedEntityHandler(ValidationManager paramValidationManager) {
    this.fValidationManager = paramValidationManager;
  }
  
  public void startDTD(XMLLocator paramXMLLocator, Augmentations paramAugmentations) throws XNIException {
    this.fValidationManager.setEntityState(this);
    if (this.fDTDHandler != null)
      this.fDTDHandler.startDTD(paramXMLLocator, paramAugmentations); 
  }
  
  public void startParameterEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.startParameterEntity(paramString1, paramXMLResourceIdentifier, paramString2, paramAugmentations); 
  }
  
  public void textDecl(String paramString1, String paramString2, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.textDecl(paramString1, paramString2, paramAugmentations); 
  }
  
  public void endParameterEntity(String paramString, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.endParameterEntity(paramString, paramAugmentations); 
  }
  
  public void startExternalSubset(XMLResourceIdentifier paramXMLResourceIdentifier, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.startExternalSubset(paramXMLResourceIdentifier, paramAugmentations); 
  }
  
  public void endExternalSubset(Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.endExternalSubset(paramAugmentations); 
  }
  
  public void comment(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.comment(paramXMLString, paramAugmentations); 
  }
  
  public void processingInstruction(String paramString, XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.processingInstruction(paramString, paramXMLString, paramAugmentations); 
  }
  
  public void elementDecl(String paramString1, String paramString2, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.elementDecl(paramString1, paramString2, paramAugmentations); 
  }
  
  public void startAttlist(String paramString, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.startAttlist(paramString, paramAugmentations); 
  }
  
  public void attributeDecl(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString, String paramString4, XMLString paramXMLString1, XMLString paramXMLString2, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.attributeDecl(paramString1, paramString2, paramString3, paramArrayOfString, paramString4, paramXMLString1, paramXMLString2, paramAugmentations); 
  }
  
  public void endAttlist(Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.endAttlist(paramAugmentations); 
  }
  
  public void internalEntityDecl(String paramString, XMLString paramXMLString1, XMLString paramXMLString2, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.internalEntityDecl(paramString, paramXMLString1, paramXMLString2, paramAugmentations); 
  }
  
  public void externalEntityDecl(String paramString, XMLResourceIdentifier paramXMLResourceIdentifier, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.externalEntityDecl(paramString, paramXMLResourceIdentifier, paramAugmentations); 
  }
  
  public void unparsedEntityDecl(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {
    if (this.fUnparsedEntities == null)
      this.fUnparsedEntities = new HashMap(); 
    this.fUnparsedEntities.put(paramString1, paramString1);
    if (this.fDTDHandler != null)
      this.fDTDHandler.unparsedEntityDecl(paramString1, paramXMLResourceIdentifier, paramString2, paramAugmentations); 
  }
  
  public void notationDecl(String paramString, XMLResourceIdentifier paramXMLResourceIdentifier, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.notationDecl(paramString, paramXMLResourceIdentifier, paramAugmentations); 
  }
  
  public void startConditional(short paramShort, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.startConditional(paramShort, paramAugmentations); 
  }
  
  public void ignoredCharacters(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.ignoredCharacters(paramXMLString, paramAugmentations); 
  }
  
  public void endConditional(Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.endConditional(paramAugmentations); 
  }
  
  public void endDTD(Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.endDTD(paramAugmentations); 
  }
  
  public void setDTDSource(XMLDTDSource paramXMLDTDSource) {
    this.fDTDSource = paramXMLDTDSource;
  }
  
  public XMLDTDSource getDTDSource() {
    return this.fDTDSource;
  }
  
  public void setDTDHandler(XMLDTDHandler paramXMLDTDHandler) {
    this.fDTDHandler = paramXMLDTDHandler;
  }
  
  public XMLDTDHandler getDTDHandler() {
    return this.fDTDHandler;
  }
  
  public boolean isEntityDeclared(String paramString) {
    return false;
  }
  
  public boolean isEntityUnparsed(String paramString) {
    return (this.fUnparsedEntities != null) ? this.fUnparsedEntities.containsKey(paramString) : false;
  }
  
  public void reset() {
    if (this.fUnparsedEntities != null && !this.fUnparsedEntities.isEmpty())
      this.fUnparsedEntities.clear(); 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/jaxp/UnparsedEntityHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */