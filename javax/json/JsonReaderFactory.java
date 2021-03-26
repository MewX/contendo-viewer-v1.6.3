package javax.json;

import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Map;

public interface JsonReaderFactory {
  JsonReader createReader(Reader paramReader);
  
  JsonReader createReader(InputStream paramInputStream);
  
  JsonReader createReader(InputStream paramInputStream, Charset paramCharset);
  
  Map<String, ?> getConfigInUse();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/json/JsonReaderFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */