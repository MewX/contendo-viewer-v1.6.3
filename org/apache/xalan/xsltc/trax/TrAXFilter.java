/*     */ package org.apache.xalan.xsltc.trax;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.xml.parsers.FactoryConfigurationError;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import javax.xml.transform.ErrorListener;
/*     */ import javax.xml.transform.Templates;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerConfigurationException;
/*     */ import javax.xml.transform.sax.SAXResult;
/*     */ import org.apache.xml.utils.XMLReaderManager;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.helpers.XMLFilterImpl;
/*     */ import org.xml.sax.helpers.XMLReaderFactory;
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
/*     */ public class TrAXFilter
/*     */   extends XMLFilterImpl
/*     */ {
/*     */   private Templates _templates;
/*     */   private TransformerImpl _transformer;
/*     */   private TransformerHandlerImpl _transformerHandler;
/*     */   
/*     */   public TrAXFilter(Templates templates) throws TransformerConfigurationException {
/*  57 */     this._templates = templates;
/*  58 */     this._transformer = (TransformerImpl)templates.newTransformer();
/*  59 */     this._transformerHandler = new TransformerHandlerImpl(this._transformer);
/*     */   }
/*     */   
/*     */   public Transformer getTransformer() {
/*  63 */     return this._transformer;
/*     */   }
/*     */   
/*     */   private void createParent() throws SAXException {
/*  67 */     XMLReader parent = null;
/*     */     
/*  69 */     try { SAXParserFactory pfactory = SAXParserFactory.newInstance();
/*  70 */       pfactory.setNamespaceAware(true);
/*  71 */       SAXParser saxparser = pfactory.newSAXParser();
/*  72 */       parent = saxparser.getXMLReader(); } catch (ParserConfigurationException e)
/*     */     
/*     */     { 
/*  75 */       throw new SAXException(e); } catch (FactoryConfigurationError e)
/*     */     
/*     */     { 
/*  78 */       throw new SAXException(e.toString()); }
/*     */ 
/*     */     
/*  81 */     if (parent == null) {
/*  82 */       parent = XMLReaderFactory.createXMLReader();
/*     */     }
/*     */ 
/*     */     
/*  86 */     setParent(parent);
/*     */   }
/*     */ 
/*     */   
/*     */   public void parse(InputSource input) throws SAXException, IOException {
/*  91 */     XMLReader managedReader = null;
/*     */     
/*     */     try {
/*  94 */       if (getParent() == null) {
/*     */         
/*  96 */         try { managedReader = XMLReaderManager.getInstance().getXMLReader();
/*     */           
/*  98 */           setParent(managedReader); } catch (SAXException e)
/*     */         
/* 100 */         { throw new SAXException(e.toString()); }
/*     */       
/*     */       }
/*     */ 
/*     */       
/* 105 */       getParent().parse(input);
/*     */     } finally {
/* 107 */       if (managedReader != null) {
/* 108 */         XMLReaderManager.getInstance().releaseXMLReader(managedReader);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void parse(String systemId) throws SAXException, IOException {
/* 115 */     parse(new InputSource(systemId));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setContentHandler(ContentHandler handler) {
/* 120 */     this._transformerHandler.setResult(new SAXResult(handler));
/* 121 */     if (getParent() == null) {
/*     */       
/* 123 */       try { createParent(); } catch (SAXException e)
/*     */       { return; }
/*     */     
/*     */     }
/*     */ 
/*     */     
/* 129 */     getParent().setContentHandler(this._transformerHandler);
/*     */   }
/*     */   
/*     */   public void setErrorListener(ErrorListener handler) {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/trax/TrAXFilter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */