/*    */ package jp.cssj.sakae.sac.parser;
/*    */ 
/*    */ import jp.cssj.sakae.sac.css.CSSException;
/*    */ import jp.cssj.sakae.sac.css.DocumentHandler;
/*    */ import jp.cssj.sakae.sac.css.InputSource;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ import jp.cssj.sakae.sac.css.SACMediaList;
/*    */ import jp.cssj.sakae.sac.css.SelectorList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultDocumentHandler
/*    */   implements DocumentHandler
/*    */ {
/* 71 */   public static final DocumentHandler INSTANCE = new DefaultDocumentHandler();
/*    */   
/*    */   public void startDocument(InputSource source) throws CSSException {}
/*    */   
/*    */   public void endDocument(InputSource source) throws CSSException {}
/*    */   
/*    */   public void comment(String text) throws CSSException {}
/*    */   
/*    */   public void ignorableAtRule(String atRule) throws CSSException {}
/*    */   
/*    */   public void namespaceDeclaration(String prefix, String uri) throws CSSException {}
/*    */   
/*    */   public void importStyle(String uri, SACMediaList media, String defaultNamespaceURI) throws CSSException {}
/*    */   
/*    */   public void startMedia(SACMediaList media) throws CSSException {}
/*    */   
/*    */   public void endMedia(SACMediaList media) throws CSSException {}
/*    */   
/*    */   public void startPage(String name, String pseudo_page) throws CSSException {}
/*    */   
/*    */   public void endPage(String name, String pseudo_page) throws CSSException {}
/*    */   
/*    */   public void startFontFace() throws CSSException {}
/*    */   
/*    */   public void endFontFace() throws CSSException {}
/*    */   
/*    */   public void startSelector(SelectorList selectors) throws CSSException {}
/*    */   
/*    */   public void endSelector(SelectorList selectors) throws CSSException {}
/*    */   
/*    */   public void property(String name, LexicalUnit value, boolean important) throws CSSException {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/DefaultDocumentHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */