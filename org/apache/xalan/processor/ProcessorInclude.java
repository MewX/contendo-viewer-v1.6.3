/*     */ package org.apache.xalan.processor;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.xml.parsers.FactoryConfigurationError;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.URIResolver;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.sax.SAXSource;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xml.utils.DOM2Helper;
/*     */ import org.apache.xml.utils.DOMHelper;
/*     */ import org.apache.xml.utils.SystemIDResolver;
/*     */ import org.apache.xml.utils.TreeWalker;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.XMLReader;
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
/*     */ class ProcessorInclude
/*     */   extends XSLTElementProcessor
/*     */ {
/*  54 */   private String m_href = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHref() {
/*  64 */     return this.m_href;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHref(String baseIdent) {
/*  75 */     this.m_href = baseIdent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getStylesheetType() {
/*  85 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getStylesheetInclErr() {
/*  95 */     return "ER_STYLESHEET_INCLUDES_ITSELF";
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
/*     */   public void startElement(StylesheetHandler handler, String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/* 123 */     setPropertiesFromAttributes(handler, rawName, attributes, this);
/*     */ 
/*     */ 
/*     */     
/* 127 */     try { String hrefUrl = SystemIDResolver.getAbsoluteURI(getHref(), handler.getBaseIdentifier());
/*     */ 
/*     */       
/* 130 */       if (handler.importStackContains(hrefUrl))
/*     */       {
/* 132 */         throw new SAXException(XSLMessages.createMessage(getStylesheetInclErr(), new Object[] { hrefUrl }));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 137 */       handler.pushImportURL(hrefUrl);
/*     */       
/* 139 */       int savedStylesheetType = handler.getStylesheetType();
/*     */       
/* 141 */       handler.setStylesheetType(getStylesheetType());
/* 142 */       handler.pushNewNamespaceSupport();
/*     */ 
/*     */ 
/*     */       
/* 146 */       try { parse(handler, uri, localName, rawName, attributes); }
/*     */       
/*     */       finally
/*     */       
/* 150 */       { handler.setStylesheetType(savedStylesheetType);
/* 151 */         handler.popImportURL();
/* 152 */         handler.popNamespaceSupport(); }  } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 157 */       handler.error(te.getMessage(), te); }
/*     */   
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
/*     */   protected void parse(StylesheetHandler handler, String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/* 180 */     TransformerFactoryImpl processor = handler.getStylesheetProcessor();
/* 181 */     URIResolver uriresolver = processor.getURIResolver();
/*     */ 
/*     */ 
/*     */     
/* 185 */     try { Source source = null;
/*     */       
/* 187 */       if (null != uriresolver) {
/*     */         
/* 189 */         source = uriresolver.resolve(getHref(), handler.getBaseIdentifier());
/*     */ 
/*     */         
/* 192 */         if (null != source && source instanceof DOMSource) {
/*     */           
/* 194 */           Node node = ((DOMSource)source).getNode();
/*     */           
/* 196 */           String systemId = source.getSystemId();
/* 197 */           if (systemId == null)
/*     */           {
/* 199 */             systemId = SystemIDResolver.getAbsoluteURI(getHref(), handler.getBaseIdentifier());
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 204 */           TreeWalker walker = new TreeWalker(handler, (DOMHelper)new DOM2Helper(), systemId);
/*     */ 
/*     */ 
/*     */           
/* 208 */           try { walker.traverse(node); } catch (SAXException se)
/*     */           
/*     */           { 
/*     */             
/* 212 */             throw new TransformerException(se); }
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 218 */       if (null == source) {
/*     */         
/* 220 */         String absURL = SystemIDResolver.getAbsoluteURI(getHref(), handler.getBaseIdentifier());
/*     */ 
/*     */         
/* 223 */         source = new StreamSource(absURL);
/*     */       } 
/*     */       
/* 226 */       XMLReader reader = null;
/*     */       
/* 228 */       if (source instanceof SAXSource) {
/*     */         
/* 230 */         SAXSource saxSource = (SAXSource)source;
/* 231 */         reader = saxSource.getXMLReader();
/*     */       } 
/*     */       
/* 234 */       InputSource inputSource = SAXSource.sourceToInputSource(source);
/*     */       
/* 236 */       if (null == reader) {
/*     */ 
/*     */         
/*     */         try { 
/* 240 */           SAXParserFactory factory = SAXParserFactory.newInstance();
/*     */           
/* 242 */           factory.setNamespaceAware(true);
/* 243 */           SAXParser jaxpParser = factory.newSAXParser();
/*     */           
/* 245 */           reader = jaxpParser.getXMLReader(); } catch (ParserConfigurationException ex)
/*     */         
/*     */         { 
/* 248 */           throw new SAXException(ex); } catch (FactoryConfigurationError ex1)
/*     */         
/* 250 */         { throw new SAXException(ex1.toString()); } catch (NoSuchMethodError ex2)
/*     */         
/*     */         {  }
/* 253 */         catch (AbstractMethodError abstractMethodError) {}
/*     */       }
/*     */ 
/*     */       
/* 257 */       if (null == reader) {
/* 258 */         reader = XMLReaderFactory.createXMLReader();
/*     */       }
/* 260 */       if (null != reader)
/*     */       
/* 262 */       { reader.setContentHandler(handler);
/* 263 */         handler.pushBaseIndentifier(inputSource.getSystemId());
/*     */ 
/*     */ 
/*     */         
/* 267 */         try { reader.parse(inputSource); }
/*     */         
/*     */         finally
/*     */         
/* 271 */         { handler.popBaseIndentifier(); }  }  } catch (IOException ioe)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */       
/* 277 */       handler.error("ER_IOEXCEPTION", new Object[] { getHref() }, ioe); } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 282 */       handler.error(te.getMessage(), te); }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorInclude.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */