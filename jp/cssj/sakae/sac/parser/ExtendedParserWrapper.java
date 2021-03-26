/*     */ package jp.cssj.sakae.sac.parser;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import java.util.Locale;
/*     */ import java.util.StringTokenizer;
/*     */ import jp.cssj.sakae.sac.css.CSSException;
/*     */ import jp.cssj.sakae.sac.css.ConditionFactory;
/*     */ import jp.cssj.sakae.sac.css.DocumentHandler;
/*     */ import jp.cssj.sakae.sac.css.ErrorHandler;
/*     */ import jp.cssj.sakae.sac.css.InputSource;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ import jp.cssj.sakae.sac.css.Parser;
/*     */ import jp.cssj.sakae.sac.css.SACMediaList;
/*     */ import jp.cssj.sakae.sac.css.SelectorFactory;
/*     */ import jp.cssj.sakae.sac.css.SelectorList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExtendedParserWrapper
/*     */   implements ExtendedParser
/*     */ {
/*     */   public Parser parser;
/*     */   
/*     */   public static ExtendedParser wrap(Parser p) {
/*  87 */     if (p instanceof ExtendedParser) {
/*  88 */       return (ExtendedParser)p;
/*     */     }
/*  90 */     return new ExtendedParserWrapper(p);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtendedParserWrapper(Parser parser) {
/*  96 */     this.parser = parser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getParserVersion() {
/* 104 */     return this.parser.getParserVersion();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocale(Locale locale) throws CSSException {
/* 112 */     this.parser.setLocale(locale);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocumentHandler(DocumentHandler handler) {
/* 120 */     this.parser.setDocumentHandler(handler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelectorFactory(SelectorFactory selectorFactory) {
/* 128 */     this.parser.setSelectorFactory(selectorFactory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConditionFactory(ConditionFactory conditionFactory) {
/* 136 */     this.parser.setConditionFactory(conditionFactory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setErrorHandler(ErrorHandler handler) {
/* 144 */     this.parser.setErrorHandler(handler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseStyleSheet(InputSource source) throws CSSException, IOException {
/* 152 */     this.parser.parseStyleSheet(source);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseStyleSheet(String uri) throws CSSException, IOException {
/* 182 */     this.parser.parseStyleSheet(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseStyleDeclaration(InputSource source) throws CSSException, IOException {
/* 190 */     this.parser.parseStyleDeclaration(source);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseStyleDeclaration(String source) throws CSSException, IOException {
/* 205 */     this.parser.parseStyleDeclaration(new InputSource(new StringReader(source)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseRule(InputSource source) throws CSSException, IOException {
/* 213 */     this.parser.parseRule(source);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseRule(String source) throws CSSException, IOException {
/* 226 */     this.parser.parseRule(new InputSource(new StringReader(source)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SelectorList parseSelectors(InputSource source) throws CSSException, IOException {
/* 234 */     return this.parser.parseSelectors(source);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SelectorList parseSelectors(String source) throws CSSException, IOException {
/* 248 */     return this.parser.parseSelectors(new InputSource(new StringReader(source)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexicalUnit parsePropertyValue(InputSource source) throws CSSException, IOException {
/* 256 */     return this.parser.parsePropertyValue(source);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexicalUnit parsePropertyValue(String source) throws CSSException, IOException {
/* 270 */     return this.parser.parsePropertyValue(new InputSource(new StringReader(source)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean parsePriority(InputSource source) throws CSSException, IOException {
/* 278 */     return this.parser.parsePriority(source);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SACMediaList parseMedia(String mediaText) throws CSSException, IOException {
/* 285 */     CSSSACMediaList result = new CSSSACMediaList();
/* 286 */     if (!"all".equalsIgnoreCase(mediaText)) {
/* 287 */       StringTokenizer st = new StringTokenizer(mediaText, " ,");
/* 288 */       while (st.hasMoreTokens()) {
/* 289 */         result.append(st.nextToken());
/*     */       }
/*     */     } 
/* 292 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean parsePriority(String source) throws CSSException, IOException {
/* 306 */     return this.parser.parsePriority(new InputSource(new StringReader(source)));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/ExtendedParserWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */