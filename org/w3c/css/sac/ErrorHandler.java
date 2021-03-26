package org.w3c.css.sac;

public interface ErrorHandler {
  void warning(CSSParseException paramCSSParseException) throws CSSException;
  
  void error(CSSParseException paramCSSParseException) throws CSSException;
  
  void fatalError(CSSParseException paramCSSParseException) throws CSSException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/css/sac/ErrorHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */