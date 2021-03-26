package org.apache.xerces.xinclude;

import java.io.IOException;
import org.apache.xerces.util.XML11Char;
import org.apache.xerces.xni.parser.XMLInputSource;

public class XInclude11TextReader extends XIncludeTextReader {
  public XInclude11TextReader(XMLInputSource paramXMLInputSource, XIncludeHandler paramXIncludeHandler, int paramInt) throws IOException {
    super(paramXMLInputSource, paramXIncludeHandler, paramInt);
  }
  
  protected boolean isValid(int paramInt) {
    return XML11Char.isXML11Valid(paramInt);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xinclude/XInclude11TextReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */