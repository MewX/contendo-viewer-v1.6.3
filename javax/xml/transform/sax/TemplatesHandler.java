package javax.xml.transform.sax;

import javax.xml.transform.Templates;
import org.xml.sax.ContentHandler;

public interface TemplatesHandler extends ContentHandler {
  Templates getTemplates();
  
  void setSystemId(String paramString);
  
  String getSystemId();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/sax/TemplatesHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */