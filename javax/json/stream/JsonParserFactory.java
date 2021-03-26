package javax.json.stream;

import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Map;
import javax.json.JsonArray;
import javax.json.JsonObject;

public interface JsonParserFactory {
  JsonParser createParser(Reader paramReader);
  
  JsonParser createParser(InputStream paramInputStream);
  
  JsonParser createParser(InputStream paramInputStream, Charset paramCharset);
  
  JsonParser createParser(JsonObject paramJsonObject);
  
  JsonParser createParser(JsonArray paramJsonArray);
  
  Map<String, ?> getConfigInUse();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/json/stream/JsonParserFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */