/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import javax.xml.parsers.FactoryConfigurationError;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParserFactory;
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
/*     */ 
/*     */ public class XMLReaderManager
/*     */ {
/*     */   private static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
/*     */   private static final String NAMESPACE_PREFIXES_FEATURE = "http://xml.org/sax/features/namespace-prefixes";
/*  41 */   private static final XMLReaderManager m_singletonManager = new XMLReaderManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static SAXParserFactory m_parserFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ThreadLocal m_readers;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Hashtable m_inUse;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static XMLReaderManager getInstance() {
/*  69 */     return m_singletonManager;
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
/*     */   public synchronized XMLReader getXMLReader() throws SAXException {
/*  82 */     if (this.m_readers == null)
/*     */     {
/*     */       
/*  85 */       this.m_readers = new ThreadLocal();
/*     */     }
/*     */     
/*  88 */     if (this.m_inUse == null) {
/*  89 */       this.m_inUse = new Hashtable();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  94 */     XMLReader reader = this.m_readers.get();
/*  95 */     boolean threadHasReader = (reader != null);
/*  96 */     if (!threadHasReader || this.m_inUse.get(reader) == Boolean.TRUE) {
/*     */ 
/*     */ 
/*     */       
/*     */       try { 
/*     */ 
/*     */         
/* 103 */         try { reader = XMLReaderFactory.createXMLReader(); } catch (Exception e)
/*     */         
/*     */         { 
/*     */           
/*     */           try { 
/* 108 */             if (m_parserFactory == null) {
/* 109 */               m_parserFactory = SAXParserFactory.newInstance();
/* 110 */               m_parserFactory.setNamespaceAware(true);
/*     */             } 
/*     */             
/* 113 */             reader = m_parserFactory.newSAXParser().getXMLReader(); } catch (ParserConfigurationException pce)
/*     */           
/* 115 */           { throw pce; }
/*     */            }
/*     */ 
/*     */         
/* 119 */         try { reader.setFeature("http://xml.org/sax/features/namespaces", true);
/* 120 */           reader.setFeature("http://xml.org/sax/features/namespace-prefixes", false); } catch (SAXException sAXException) {} }
/* 121 */       catch (ParserConfigurationException ex)
/*     */       
/*     */       { 
/*     */ 
/*     */         
/* 126 */         throw new SAXException(ex); } catch (FactoryConfigurationError ex1)
/*     */       
/* 128 */       { throw new SAXException(ex1.toString()); } catch (NoSuchMethodError ex2) {  }
/* 129 */       catch (AbstractMethodError abstractMethodError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 135 */       if (!threadHasReader) {
/* 136 */         this.m_readers.set(reader);
/* 137 */         this.m_inUse.put(reader, Boolean.TRUE);
/*     */       } 
/*     */     } else {
/* 140 */       this.m_inUse.put(reader, Boolean.TRUE);
/*     */     } 
/*     */     
/* 143 */     return reader;
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
/*     */   public synchronized void releaseXMLReader(XMLReader reader) {
/* 155 */     if (this.m_readers.get() == reader)
/* 156 */       this.m_inUse.put(reader, Boolean.FALSE); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/XMLReaderManager.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */