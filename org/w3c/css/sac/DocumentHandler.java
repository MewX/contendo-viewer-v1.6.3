package org.w3c.css.sac;

public interface DocumentHandler {
  void startDocument(InputSource paramInputSource) throws CSSException;
  
  void endDocument(InputSource paramInputSource) throws CSSException;
  
  void comment(String paramString) throws CSSException;
  
  void ignorableAtRule(String paramString) throws CSSException;
  
  void namespaceDeclaration(String paramString1, String paramString2) throws CSSException;
  
  void importStyle(String paramString1, SACMediaList paramSACMediaList, String paramString2) throws CSSException;
  
  void startMedia(SACMediaList paramSACMediaList) throws CSSException;
  
  void endMedia(SACMediaList paramSACMediaList) throws CSSException;
  
  void startPage(String paramString1, String paramString2) throws CSSException;
  
  void endPage(String paramString1, String paramString2) throws CSSException;
  
  void startFontFace() throws CSSException;
  
  void endFontFace() throws CSSException;
  
  void startSelector(SelectorList paramSelectorList) throws CSSException;
  
  void endSelector(SelectorList paramSelectorList) throws CSSException;
  
  void property(String paramString, LexicalUnit paramLexicalUnit, boolean paramBoolean) throws CSSException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/css/sac/DocumentHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */