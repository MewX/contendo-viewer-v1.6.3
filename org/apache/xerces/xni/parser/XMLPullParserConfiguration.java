package org.apache.xerces.xni.parser;

import java.io.IOException;
import org.apache.xerces.xni.XNIException;

public interface XMLPullParserConfiguration extends XMLParserConfiguration {
  void setInputSource(XMLInputSource paramXMLInputSource) throws XMLConfigurationException, IOException;
  
  boolean parse(boolean paramBoolean) throws XNIException, IOException;
  
  void cleanup();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xni/parser/XMLPullParserConfiguration.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */