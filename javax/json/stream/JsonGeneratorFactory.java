package javax.json.stream;

import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

public interface JsonGeneratorFactory {
  JsonGenerator createGenerator(Writer paramWriter);
  
  JsonGenerator createGenerator(OutputStream paramOutputStream);
  
  JsonGenerator createGenerator(OutputStream paramOutputStream, Charset paramCharset);
  
  Map<String, ?> getConfigInUse();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/json/stream/JsonGeneratorFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */