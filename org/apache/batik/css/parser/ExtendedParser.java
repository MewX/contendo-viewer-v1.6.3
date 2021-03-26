package org.apache.batik.css.parser;

import java.io.IOException;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.css.sac.Parser;
import org.w3c.css.sac.SACMediaList;
import org.w3c.css.sac.SelectorList;

public interface ExtendedParser extends Parser {
  void parseStyleDeclaration(String paramString) throws CSSException, IOException;
  
  void parseRule(String paramString) throws CSSException, IOException;
  
  SelectorList parseSelectors(String paramString) throws CSSException, IOException;
  
  LexicalUnit parsePropertyValue(String paramString) throws CSSException, IOException;
  
  SACMediaList parseMedia(String paramString) throws CSSException, IOException;
  
  boolean parsePriority(String paramString) throws CSSException, IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/ExtendedParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */