/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.xml.parsers.FactoryConfigurationError;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import javax.xml.transform.ErrorListener;
/*     */ import javax.xml.transform.Templates;
/*     */ import javax.xml.transform.TransformerConfigurationException;
/*     */ import org.apache.xalan.res.XSLMessages;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TrAXFilter
/*     */   extends XMLFilterImpl
/*     */ {
/*     */   private Templates m_templates;
/*     */   private TransformerImpl m_transformer;
/*     */   
/*     */   public TrAXFilter(Templates templates) throws TransformerConfigurationException {
/*  57 */     this.m_templates = templates;
/*  58 */     this.m_transformer = (TransformerImpl)templates.newTransformer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformerImpl getTransformer() {
/*  66 */     return this.m_transformer;
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
/*     */   public void setParent(XMLReader parent) {
/*  83 */     super.setParent(parent);
/*     */     
/*  85 */     if (null != parent.getContentHandler()) {
/*  86 */       setContentHandler(parent.getContentHandler());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  91 */     setupParse();
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
/*     */   public void parse(InputSource input) throws SAXException, IOException {
/* 108 */     if (null == getParent()) {
/*     */       
/* 110 */       XMLReader xMLReader1, reader = null;
/*     */ 
/*     */ 
/*     */       
/* 114 */       try { SAXParserFactory factory = SAXParserFactory.newInstance();
/*     */         
/* 116 */         factory.setNamespaceAware(true);
/* 117 */         SAXParser jaxpParser = factory.newSAXParser();
/*     */         
/* 119 */         reader = jaxpParser.getXMLReader(); } catch (ParserConfigurationException ex)
/*     */       
/*     */       { 
/* 122 */         throw new SAXException(ex); } catch (FactoryConfigurationError ex1)
/*     */       
/* 124 */       { throw new SAXException(ex1.toString()); } catch (NoSuchMethodError ex2) {  }
/* 125 */       catch (AbstractMethodError abstractMethodError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 130 */       if (reader == null) {
/* 131 */         xMLReader1 = XMLReaderFactory.createXMLReader();
/*     */       } else {
/* 133 */         xMLReader1 = reader;
/*     */       } 
/*     */       
/* 136 */       try { xMLReader1.setFeature("http://xml.org/sax/features/namespace-prefixes", true); } catch (SAXException sAXException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 141 */       setParent(xMLReader1);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 146 */       setupParse();
/*     */     } 
/* 148 */     if (null == this.m_transformer.getContentHandler())
/*     */     {
/* 150 */       throw new SAXException(XSLMessages.createMessage("ER_CANNOT_CALL_PARSE", null));
/*     */     }
/*     */     
/* 153 */     getParent().parse(input);
/* 154 */     Exception e = this.m_transformer.getExceptionThrown();
/* 155 */     if (null != e) {
/*     */       
/* 157 */       if (e instanceof SAXException) {
/* 158 */         throw (SAXException)e;
/*     */       }
/* 160 */       throw new SAXException(e);
/*     */     } 
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
/*     */   public void parse(String systemId) throws SAXException, IOException {
/* 178 */     parse(new InputSource(systemId));
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
/*     */   private void setupParse() {
/* 191 */     XMLReader p = getParent();
/* 192 */     if (p == null) {
/* 193 */       throw new NullPointerException(XSLMessages.createMessage("ER_NO_PARENT_FOR_FILTER", null));
/*     */     }
/*     */     
/* 196 */     ContentHandler ch = this.m_transformer.getInputContentHandler();
/*     */ 
/*     */     
/* 199 */     p.setContentHandler(ch);
/* 200 */     p.setEntityResolver(this);
/* 201 */     p.setDTDHandler(this);
/* 202 */     p.setErrorHandler(this);
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
/*     */   public void setContentHandler(ContentHandler handler) {
/* 215 */     this.m_transformer.setContentHandler(handler);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setErrorListener(ErrorListener handler) {
/* 221 */     this.m_transformer.setErrorListener(handler);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/TrAXFilter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */