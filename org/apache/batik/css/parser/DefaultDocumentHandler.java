/*    */ package org.apache.batik.css.parser;
/*    */ 
/*    */ import org.w3c.css.sac.CSSException;
/*    */ import org.w3c.css.sac.DocumentHandler;
/*    */ import org.w3c.css.sac.InputSource;
/*    */ import org.w3c.css.sac.LexicalUnit;
/*    */ import org.w3c.css.sac.SACMediaList;
/*    */ import org.w3c.css.sac.SelectorList;
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
/* 38 */   public static final DocumentHandler INSTANCE = new DefaultDocumentHandler();
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/DefaultDocumentHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */