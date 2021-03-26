package org.apache.batik.script;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import org.apache.batik.i18n.Localizable;

public interface Interpreter extends Localizable {
  String[] getMimeTypes();
  
  Object evaluate(Reader paramReader, String paramString) throws InterpreterException, IOException;
  
  Object evaluate(Reader paramReader) throws InterpreterException, IOException;
  
  Object evaluate(String paramString) throws InterpreterException;
  
  void bindObject(String paramString, Object paramObject);
  
  void setOut(Writer paramWriter);
  
  void dispose();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/script/Interpreter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */