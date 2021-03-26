package org.apache.xerces.xni.parser;

import java.io.IOException;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XNIException;

public interface XMLEntityResolver {
  XMLInputSource resolveEntity(XMLResourceIdentifier paramXMLResourceIdentifier) throws XNIException, IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xni/parser/XMLEntityResolver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */