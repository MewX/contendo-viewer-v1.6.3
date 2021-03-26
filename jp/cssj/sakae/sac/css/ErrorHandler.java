package jp.cssj.sakae.sac.css;

public interface ErrorHandler {
  void warning(CSSParseException paramCSSParseException) throws CSSException;
  
  void error(CSSParseException paramCSSParseException) throws CSSException;
  
  void fatalError(CSSParseException paramCSSParseException) throws CSSException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/css/ErrorHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */