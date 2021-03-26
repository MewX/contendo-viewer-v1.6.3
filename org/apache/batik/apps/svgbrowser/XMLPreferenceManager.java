/*     */ package org.apache.batik.apps.svgbrowser;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import org.apache.batik.dom.GenericDOMImplementation;
/*     */ import org.apache.batik.dom.util.DOMUtilities;
/*     */ import org.apache.batik.dom.util.SAXDocumentFactory;
/*     */ import org.apache.batik.util.PreferenceManager;
/*     */ import org.apache.batik.util.XMLResourceDescriptor;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLPreferenceManager
/*     */   extends PreferenceManager
/*     */ {
/*     */   protected String xmlParserClassName;
/*     */   public static final String PREFERENCE_ENCODING = "8859_1";
/*     */   
/*     */   public XMLPreferenceManager(String prefFileName) {
/*  67 */     this(prefFileName, null, XMLResourceDescriptor.getXMLParserClassName());
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
/*     */   public XMLPreferenceManager(String prefFileName, Map defaults) {
/*  79 */     this(prefFileName, defaults, XMLResourceDescriptor.getXMLParserClassName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLPreferenceManager(String prefFileName, String parser) {
/*  89 */     this(prefFileName, null, parser);
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
/*     */   public XMLPreferenceManager(String prefFileName, Map defaults, String parser) {
/* 101 */     super(prefFileName, defaults);
/* 102 */     this.internal = new XMLProperties();
/* 103 */     this.xmlParserClassName = parser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class XMLProperties
/*     */     extends Properties
/*     */   {
/*     */     public synchronized void load(InputStream is) throws IOException {
/* 117 */       BufferedReader r = new BufferedReader(new InputStreamReader(is, "8859_1"));
/* 118 */       SAXDocumentFactory sAXDocumentFactory = new SAXDocumentFactory(GenericDOMImplementation.getDOMImplementation(), XMLPreferenceManager.this.xmlParserClassName);
/*     */ 
/*     */       
/* 121 */       Document doc = sAXDocumentFactory.createDocument("http://xml.apache.org/batik/preferences", "preferences", null, r);
/*     */ 
/*     */ 
/*     */       
/* 125 */       Element elt = doc.getDocumentElement();
/* 126 */       for (Node n = elt.getFirstChild(); n != null; n = n.getNextSibling()) {
/* 127 */         if (n.getNodeType() == 1 && 
/* 128 */           n.getNodeName().equals("property")) {
/* 129 */           String name = ((Element)n).getAttributeNS(null, "name");
/*     */           
/* 131 */           StringBuffer cont = new StringBuffer();
/* 132 */           Node c = n.getFirstChild();
/* 133 */           while (c != null) {
/*     */             
/* 135 */             if (c.getNodeType() == 3) {
/* 136 */               cont.append(c.getNodeValue());
/*     */               
/*     */               c = c.getNextSibling();
/*     */             } 
/*     */           } 
/* 141 */           String val = cont.toString();
/* 142 */           put(name, val);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public synchronized void store(OutputStream os, String header) throws IOException {
/* 158 */       BufferedWriter w = new BufferedWriter(new OutputStreamWriter(os, "8859_1"));
/*     */       
/* 160 */       Map<Object, Object> m = new HashMap<Object, Object>();
/* 161 */       enumerate(m);
/*     */       
/* 163 */       w.write("<preferences xmlns=\"http://xml.apache.org/batik/preferences\">\n");
/*     */       
/* 165 */       for (Object o : m.keySet()) {
/* 166 */         String n = (String)o;
/* 167 */         String v = (String)m.get(n);
/*     */         
/* 169 */         w.write("<property name=\"" + n + "\">");
/*     */         try {
/* 171 */           w.write(DOMUtilities.contentToString(v, false));
/* 172 */         } catch (IOException iOException) {}
/*     */ 
/*     */         
/* 175 */         w.write("</property>\n");
/*     */       } 
/*     */       
/* 178 */       w.write("</preferences>\n");
/* 179 */       w.flush();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private synchronized void enumerate(Map<Object, Object> m) {
/* 187 */       if (this.defaults != null) {
/* 188 */         for (Object k : m.keySet()) {
/* 189 */           m.put(k, this.defaults.get(k));
/*     */         }
/*     */       }
/* 192 */       for (Object k : keySet())
/* 193 */         m.put(k, get(k)); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/XMLPreferenceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */