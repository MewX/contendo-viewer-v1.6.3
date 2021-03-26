package org.apache.batik.dom.svg;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.apache.batik.dom.util.DocumentFactory;
import org.w3c.dom.svg.SVGDocument;

public interface SVGDocumentFactory extends DocumentFactory {
  SVGDocument createSVGDocument(String paramString) throws IOException;
  
  SVGDocument createSVGDocument(String paramString, InputStream paramInputStream) throws IOException;
  
  SVGDocument createSVGDocument(String paramString, Reader paramReader) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGDocumentFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */