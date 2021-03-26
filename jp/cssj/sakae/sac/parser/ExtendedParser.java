package jp.cssj.sakae.sac.parser;

import java.io.IOException;
import jp.cssj.sakae.sac.css.CSSException;
import jp.cssj.sakae.sac.css.LexicalUnit;
import jp.cssj.sakae.sac.css.Parser;
import jp.cssj.sakae.sac.css.SACMediaList;
import jp.cssj.sakae.sac.css.SelectorList;

public interface ExtendedParser extends Parser {
  void parseStyleDeclaration(String paramString) throws CSSException, IOException;
  
  void parseRule(String paramString) throws CSSException, IOException;
  
  SelectorList parseSelectors(String paramString) throws CSSException, IOException;
  
  LexicalUnit parsePropertyValue(String paramString) throws CSSException, IOException;
  
  SACMediaList parseMedia(String paramString) throws CSSException, IOException;
  
  boolean parsePriority(String paramString) throws CSSException, IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/ExtendedParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */