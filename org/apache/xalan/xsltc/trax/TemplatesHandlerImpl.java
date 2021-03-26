/*     */ package org.apache.xalan.xsltc.trax;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.Templates;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.URIResolver;
/*     */ import javax.xml.transform.sax.TemplatesHandler;
/*     */ import org.apache.xalan.xsltc.compiler.CompilerException;
/*     */ import org.apache.xalan.xsltc.compiler.Parser;
/*     */ import org.apache.xalan.xsltc.compiler.SourceLoader;
/*     */ import org.apache.xalan.xsltc.compiler.Stylesheet;
/*     */ import org.apache.xalan.xsltc.compiler.SyntaxTreeNode;
/*     */ import org.apache.xalan.xsltc.compiler.XSLTC;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
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
/*     */ public class TemplatesHandlerImpl
/*     */   implements TemplatesHandler, SourceLoader, ContentHandler
/*     */ {
/*     */   private String _systemId;
/*     */   private int _indentNumber;
/*  65 */   private URIResolver _uriResolver = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   private TransformerFactoryImpl _tfactory = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   private Parser _parser = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   private TemplatesImpl _templates = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected TemplatesHandlerImpl(int indentNumber, TransformerFactoryImpl tfactory) {
/*  89 */     this._indentNumber = indentNumber;
/*  90 */     this._tfactory = tfactory;
/*     */ 
/*     */     
/*  93 */     this._parser = (new XSLTC()).getParser();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSystemId() {
/* 103 */     return this._systemId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSystemId(String id) {
/* 113 */     this._systemId = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setURIResolver(URIResolver resolver) {
/* 120 */     this._uriResolver = resolver;
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
/*     */   public Templates getTemplates() {
/* 133 */     return this._templates;
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
/*     */   public InputSource loadSource(String href, String context, XSLTC xsltc) {
/*     */     
/* 148 */     try { Source source = this._uriResolver.resolve(href, context);
/* 149 */       if (source != null)
/* 150 */         return Util.getInputSource(xsltc, source);  } catch (TransformerException transformerException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDocument() {
/* 165 */     XSLTC xsltc = this._parser.getXSLTC();
/* 166 */     xsltc.init();
/* 167 */     xsltc.setOutputType(2);
/* 168 */     this._parser.startDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDocument() throws SAXException {
/* 175 */     this._parser.endDocument();
/*     */ 
/*     */ 
/*     */     
/* 179 */     try { XSLTC xsltc = this._parser.getXSLTC();
/*     */ 
/*     */       
/* 182 */       String transletName = null;
/* 183 */       if (this._systemId != null) {
/* 184 */         transletName = Util.baseName(this._systemId);
/*     */       } else {
/*     */         
/* 187 */         transletName = (String)this._tfactory.getAttribute("translet-name");
/*     */       } 
/* 189 */       xsltc.setClassName(transletName);
/*     */ 
/*     */       
/* 192 */       transletName = xsltc.getClassName();
/*     */       
/* 194 */       Stylesheet stylesheet = null;
/* 195 */       SyntaxTreeNode root = this._parser.getDocumentRoot();
/*     */ 
/*     */       
/* 198 */       if (!this._parser.errorsFound() && root != null) {
/*     */         
/* 200 */         stylesheet = this._parser.makeStylesheet(root);
/* 201 */         stylesheet.setSystemId(this._systemId);
/* 202 */         stylesheet.setParentStylesheet(null);
/*     */ 
/*     */         
/* 205 */         if (this._uriResolver != null) {
/* 206 */           stylesheet.setSourceLoader(this);
/*     */         }
/*     */         
/* 209 */         this._parser.setCurrentStylesheet(stylesheet);
/*     */ 
/*     */         
/* 212 */         xsltc.setStylesheet(stylesheet);
/*     */ 
/*     */         
/* 215 */         this._parser.createAST(stylesheet);
/*     */       } 
/*     */ 
/*     */       
/* 219 */       if (!this._parser.errorsFound() && stylesheet != null) {
/* 220 */         stylesheet.setMultiDocument(xsltc.isMultiDocument());
/* 221 */         stylesheet.setHasIdCall(xsltc.hasIdCall());
/*     */ 
/*     */         
/* 224 */         synchronized (xsltc.getClass()) {
/* 225 */           stylesheet.translate();
/*     */         } 
/*     */       } 
/*     */       
/* 229 */       if (!this._parser.errorsFound())
/*     */       
/* 231 */       { byte[][] bytecodes = xsltc.getBytecodes();
/* 232 */         if (bytecodes != null) {
/* 233 */           this._templates = new TemplatesImpl(xsltc.getBytecodes(), transletName, this._parser.getOutputProperties(), this._indentNumber, this._tfactory);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 238 */           if (this._uriResolver != null) {
/* 239 */             this._templates.setURIResolver(this._uriResolver);
/*     */           }
/*     */         }  }
/*     */       else
/*     */       
/* 244 */       { StringBuffer errorMessage = new StringBuffer();
/* 245 */         Vector errors = this._parser.getErrors();
/* 246 */         int count = errors.size();
/* 247 */         for (int i = 0; i < count; i++) {
/* 248 */           if (errorMessage.length() > 0)
/* 249 */             errorMessage.append('\n'); 
/* 250 */           errorMessage.append(errors.elementAt(i).toString());
/*     */         } 
/* 252 */         throw new SAXException("JAXP_COMPILE_ERR", new TransformerException(errorMessage.toString())); }  } catch (CompilerException e)
/*     */     
/*     */     { 
/*     */       
/* 256 */       throw new SAXException("JAXP_COMPILE_ERR", e); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) {
/* 264 */     this._parser.startPrefixMapping(prefix, uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endPrefixMapping(String prefix) {
/* 271 */     this._parser.endPrefixMapping(prefix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String uri, String localname, String qname, Attributes attributes) throws SAXException {
/* 280 */     this._parser.startElement(uri, localname, qname, attributes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(String uri, String localname, String qname) {
/* 287 */     this._parser.endElement(uri, localname, qname);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(char[] ch, int start, int length) {
/* 294 */     this._parser.characters(ch, start, length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processingInstruction(String name, String value) {
/* 301 */     this._parser.processingInstruction(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) {
/* 308 */     this._parser.ignorableWhitespace(ch, start, length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void skippedEntity(String name) {
/* 315 */     this._parser.skippedEntity(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocumentLocator(Locator locator) {
/* 322 */     setSystemId(locator.getSystemId());
/* 323 */     this._parser.setDocumentLocator(locator);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/trax/TemplatesHandlerImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */