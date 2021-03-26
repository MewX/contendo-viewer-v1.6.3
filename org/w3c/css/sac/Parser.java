package org.w3c.css.sac;

import java.io.IOException;
import java.util.Locale;

public interface Parser {
  void setLocale(Locale paramLocale) throws CSSException;
  
  void setDocumentHandler(DocumentHandler paramDocumentHandler);
  
  void setSelectorFactory(SelectorFactory paramSelectorFactory);
  
  void setConditionFactory(ConditionFactory paramConditionFactory);
  
  void setErrorHandler(ErrorHandler paramErrorHandler);
  
  void parseStyleSheet(InputSource paramInputSource) throws CSSException, IOException;
  
  void parseStyleSheet(String paramString) throws CSSException, IOException;
  
  void parseStyleDeclaration(InputSource paramInputSource) throws CSSException, IOException;
  
  void parseRule(InputSource paramInputSource) throws CSSException, IOException;
  
  String getParserVersion();
  
  SelectorList parseSelectors(InputSource paramInputSource) throws CSSException, IOException;
  
  LexicalUnit parsePropertyValue(InputSource paramInputSource) throws CSSException, IOException;
  
  boolean parsePriority(InputSource paramInputSource) throws CSSException, IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/css/sac/Parser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */