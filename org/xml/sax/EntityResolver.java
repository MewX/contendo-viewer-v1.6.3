package org.xml.sax;

import java.io.IOException;

public interface EntityResolver {
  InputSource resolveEntity(String paramString1, String paramString2) throws SAXException, IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/EntityResolver.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */