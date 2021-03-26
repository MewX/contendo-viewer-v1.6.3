package org.apache.batik.parser;

import java.io.Reader;
import org.apache.batik.i18n.Localizable;

public interface Parser extends Localizable {
  void parse(Reader paramReader) throws ParseException;
  
  void parse(String paramString) throws ParseException;
  
  void setErrorHandler(ErrorHandler paramErrorHandler);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */