package javax.xml.transform.sax;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ext.LexicalHandler;

public interface TransformerHandler extends ContentHandler, DTDHandler, LexicalHandler {
  void setResult(Result paramResult) throws IllegalArgumentException;
  
  void setSystemId(String paramString);
  
  String getSystemId();
  
  Transformer getTransformer();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/sax/TransformerHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */