/*     */ package org.apache.xalan.xsltc.trax;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.TransformerConfigurationException;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.sax.SAXSource;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.apache.xalan.xsltc.compiler.XSLTC;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.w3c.dom.Document;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.SAXNotSupportedException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Util
/*     */ {
/*     */   public static String baseName(String name) {
/*  53 */     return org.apache.xalan.xsltc.compiler.util.Util.baseName(name);
/*     */   }
/*     */   
/*     */   public static String noExtName(String name) {
/*  57 */     return org.apache.xalan.xsltc.compiler.util.Util.noExtName(name);
/*     */   }
/*     */   
/*     */   public static String toJavaName(String name) {
/*  61 */     return org.apache.xalan.xsltc.compiler.util.Util.toJavaName(name);
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
/*     */   public static InputSource getInputSource(XSLTC xsltc, Source source) throws TransformerConfigurationException {
/*  73 */     InputSource input = null;
/*     */     
/*  75 */     String systemId = source.getSystemId();
/*     */ 
/*     */ 
/*     */     
/*  79 */     try { if (source instanceof SAXSource) {
/*  80 */         SAXSource sax = (SAXSource)source;
/*  81 */         input = sax.getInputSource();
/*     */ 
/*     */         
/*  84 */         try { XMLReader reader = sax.getXMLReader();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  94 */           if (reader == null) {
/*     */             
/*  96 */             try { reader = XMLReaderFactory.createXMLReader(); } catch (Exception e)
/*     */             
/*     */             { 
/*     */               
/*     */               try { 
/*     */                 
/* 102 */                 SAXParserFactory parserFactory = SAXParserFactory.newInstance();
/*     */                 
/* 104 */                 parserFactory.setNamespaceAware(true);
/* 105 */                 reader = parserFactory.newSAXParser().getXMLReader(); } catch (ParserConfigurationException pce)
/*     */               
/*     */               { 
/*     */ 
/*     */                 
/* 110 */                 throw new TransformerConfigurationException("ParserConfigurationException", pce); }
/*     */                }
/*     */           
/*     */           }
/*     */           
/* 115 */           reader.setFeature("http://xml.org/sax/features/namespaces", true);
/*     */           
/* 117 */           reader.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
/*     */ 
/*     */           
/* 120 */           xsltc.setXMLReader(reader); } catch (SAXNotRecognizedException snre)
/*     */         
/* 122 */         { throw new TransformerConfigurationException("SAXNotRecognizedException ", snre); } catch (SAXNotSupportedException snse)
/*     */         
/*     */         { 
/* 125 */           throw new TransformerConfigurationException("SAXNotSupportedException ", snse); } catch (SAXException se)
/*     */         
/*     */         { 
/* 128 */           throw new TransformerConfigurationException("SAXException ", se);
/*     */            }
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 134 */       else if (source instanceof DOMSource) {
/* 135 */         DOMSource domsrc = (DOMSource)source;
/* 136 */         Document dom = (Document)domsrc.getNode();
/* 137 */         DOM2SAX dom2sax = new DOM2SAX(dom);
/* 138 */         xsltc.setXMLReader(dom2sax);
/*     */ 
/*     */         
/* 141 */         input = SAXSource.sourceToInputSource(source);
/* 142 */         if (input == null) {
/* 143 */           input = new InputSource(domsrc.getSystemId());
/*     */         
/*     */         }
/*     */       }
/* 147 */       else if (source instanceof StreamSource) {
/* 148 */         StreamSource stream = (StreamSource)source;
/* 149 */         InputStream istream = stream.getInputStream();
/* 150 */         Reader reader = stream.getReader();
/*     */ 
/*     */         
/* 153 */         if (istream != null) {
/* 154 */           input = new InputSource(istream);
/*     */         }
/* 156 */         else if (reader != null) {
/* 157 */           input = new InputSource(reader);
/*     */         } else {
/*     */           
/* 160 */           input = new InputSource(systemId);
/*     */         } 
/*     */       } else {
/*     */         
/* 164 */         ErrorMsg err = new ErrorMsg("JAXP_UNKNOWN_SOURCE_ERR");
/* 165 */         throw new TransformerConfigurationException(err.toString());
/*     */       } 
/* 167 */       input.setSystemId(systemId); } catch (NullPointerException e)
/*     */     
/*     */     { 
/* 170 */       ErrorMsg err = new ErrorMsg("JAXP_NO_SOURCE_ERR", "TransformerFactory.newTemplates()");
/*     */       
/* 172 */       throw new TransformerConfigurationException(err.toString()); } catch (SecurityException e)
/*     */     
/*     */     { 
/* 175 */       ErrorMsg err = new ErrorMsg("FILE_ACCESS_ERR", systemId);
/* 176 */       throw new TransformerConfigurationException(err.toString()); }
/*     */     
/* 178 */     return input;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/trax/Util.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */