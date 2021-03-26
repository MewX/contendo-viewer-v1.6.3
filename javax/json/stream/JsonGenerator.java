package javax.json.stream;

import java.io.Closeable;
import java.io.Flushable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.json.JsonValue;

public interface JsonGenerator extends Closeable, Flushable {
  public static final String PRETTY_PRINTING = "javax.json.stream.JsonGenerator.prettyPrinting";
  
  JsonGenerator writeStartObject();
  
  JsonGenerator writeStartObject(String paramString);
  
  JsonGenerator writeKey(String paramString);
  
  JsonGenerator writeStartArray();
  
  JsonGenerator writeStartArray(String paramString);
  
  JsonGenerator write(String paramString, JsonValue paramJsonValue);
  
  JsonGenerator write(String paramString1, String paramString2);
  
  JsonGenerator write(String paramString, BigInteger paramBigInteger);
  
  JsonGenerator write(String paramString, BigDecimal paramBigDecimal);
  
  JsonGenerator write(String paramString, int paramInt);
  
  JsonGenerator write(String paramString, long paramLong);
  
  JsonGenerator write(String paramString, double paramDouble);
  
  JsonGenerator write(String paramString, boolean paramBoolean);
  
  JsonGenerator writeNull(String paramString);
  
  JsonGenerator writeEnd();
  
  JsonGenerator write(JsonValue paramJsonValue);
  
  JsonGenerator write(String paramString);
  
  JsonGenerator write(BigDecimal paramBigDecimal);
  
  JsonGenerator write(BigInteger paramBigInteger);
  
  JsonGenerator write(int paramInt);
  
  JsonGenerator write(long paramLong);
  
  JsonGenerator write(double paramDouble);
  
  JsonGenerator write(boolean paramBoolean);
  
  JsonGenerator writeNull();
  
  void close();
  
  void flush();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/json/stream/JsonGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */