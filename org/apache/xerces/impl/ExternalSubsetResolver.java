package org.apache.xerces.impl;

import java.io.IOException;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.grammars.XMLDTDDescription;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLInputSource;

public interface ExternalSubsetResolver extends XMLEntityResolver {
  XMLInputSource getExternalSubset(XMLDTDDescription paramXMLDTDDescription) throws XNIException, IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/ExternalSubsetResolver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */