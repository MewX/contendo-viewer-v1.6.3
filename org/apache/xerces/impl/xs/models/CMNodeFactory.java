package org.apache.xerces.impl.xs.models;

import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.dtd.models.CMNode;
import org.apache.xerces.util.SecurityManager;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;

public class CMNodeFactory {
  private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
  
  private static final boolean DEBUG = false;
  
  private static final int MULTIPLICITY = 1;
  
  private int nodeCount = 0;
  
  private int maxNodeLimit;
  
  private XMLErrorReporter fErrorReporter;
  
  private SecurityManager fSecurityManager = null;
  
  public void reset(XMLComponentManager paramXMLComponentManager) {
    this.fErrorReporter = (XMLErrorReporter)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
    try {
      this.fSecurityManager = (SecurityManager)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/security-manager");
      reset();
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fSecurityManager = null;
    } 
  }
  
  public void reset() {
    if (this.fSecurityManager != null)
      this.maxNodeLimit = this.fSecurityManager.getMaxOccurNodeLimit() * 1; 
  }
  
  public CMNode getCMLeafNode(int paramInt1, Object paramObject, int paramInt2, int paramInt3) {
    nodeCountCheck();
    return new XSCMLeaf(paramInt1, paramObject, paramInt2, paramInt3);
  }
  
  public CMNode getCMRepeatingLeafNode(int paramInt1, Object paramObject, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    nodeCountCheck();
    return new XSCMRepeatingLeaf(paramInt1, paramObject, paramInt2, paramInt3, paramInt4, paramInt5);
  }
  
  public CMNode getCMUniOpNode(int paramInt, CMNode paramCMNode) {
    nodeCountCheck();
    return new XSCMUniOp(paramInt, paramCMNode);
  }
  
  public CMNode getCMBinOpNode(int paramInt, CMNode paramCMNode1, CMNode paramCMNode2) {
    nodeCountCheck();
    return new XSCMBinOp(paramInt, paramCMNode1, paramCMNode2);
  }
  
  public void nodeCountCheck() {
    if (this.fSecurityManager != null && this.nodeCount++ > this.maxNodeLimit) {
      this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "maxOccurLimit", new Object[] { new Integer(this.maxNodeLimit) }, (short)2);
      this.nodeCount = 0;
    } 
  }
  
  public void resetNodeCount() {
    this.nodeCount = 0;
  }
  
  public void setProperty(String paramString, Object paramObject) throws XMLConfigurationException {
    if (paramString.startsWith("http://apache.org/xml/properties/")) {
      int i = paramString.length() - "http://apache.org/xml/properties/".length();
      if (i == "security-manager".length() && paramString.endsWith("security-manager")) {
        this.fSecurityManager = (SecurityManager)paramObject;
        this.maxNodeLimit = (this.fSecurityManager != null) ? (this.fSecurityManager.getMaxOccurNodeLimit() * 1) : 0;
        return;
      } 
      if (i == "internal/error-reporter".length() && paramString.endsWith("internal/error-reporter")) {
        this.fErrorReporter = (XMLErrorReporter)paramObject;
        return;
      } 
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/models/CMNodeFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */