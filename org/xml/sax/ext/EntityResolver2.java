package org.xml.sax.ext;

import java.io.IOException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public interface EntityResolver2 extends EntityResolver {
  InputSource getExternalSubset(String paramString1, String paramString2) throws SAXException, IOException;
  
  InputSource resolveEntity(String paramString1, String paramString2, String paramString3, String paramString4) throws SAXException, IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/ext/EntityResolver2.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */