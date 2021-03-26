/*     */ package net.a.a.e.d.b;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.EnumMap;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class d
/*     */   extends a
/*     */   implements Serializable
/*     */ {
/*  53 */   private static final Log m = LogFactory.getLog(d.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final long n = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String o = "/net/sourceforge/jeuclid/moDictionary.xml";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String p = "/net/sourceforge/jeuclid/moDictionary.ser";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static c q;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static c a() {
/*  85 */     synchronized (d.class) {
/*  86 */       if (q == null) {
/*     */         
/*  88 */         c c1 = a.a("/net/sourceforge/jeuclid/moDictionary.ser");
/*  89 */         if (c1 == null) {
/*  90 */           q = new d();
/*     */         } else {
/*  92 */           q = c1;
/*     */         } 
/*     */       } 
/*     */     } 
/*  96 */     return q;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(Map<b, Map<String, Map<f, String>>> paramMap) {
/* 103 */     InputStream inputStream = null;
/*     */     
/*     */     try {
/* 106 */       inputStream = d.class.getResourceAsStream("/net/sourceforge/jeuclid/moDictionary.xml");
/* 107 */       SAXParserFactory sAXParserFactory = SAXParserFactory.newInstance();
/* 108 */       XMLReader xMLReader = sAXParserFactory.newSAXParser().getXMLReader();
/* 109 */       xMLReader.setContentHandler(new a(this, paramMap));
/* 110 */       xMLReader.parse(new InputSource(inputStream));
/* 111 */     } catch (ParserConfigurationException parserConfigurationException) {
/* 112 */       m.warn("Cannot get SAXParser:" + parserConfigurationException
/* 113 */           .getMessage());
/* 114 */     } catch (SAXException sAXException) {
/* 115 */       m
/* 116 */         .warn("SAXException while parsing dictionary:" + sAXException
/* 117 */           .getMessage());
/* 118 */     } catch (IOException iOException) {
/* 119 */       m.warn("Read error while accessing XML dictionary", iOException);
/*     */     } finally {
/*     */       
/* 122 */       if (inputStream != null) {
/*     */         try {
/* 124 */           inputStream.close();
/* 125 */         } catch (IOException iOException) {
/* 126 */           m.warn("Error closing XML dictionary", iOException);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class a
/*     */     extends DefaultHandler
/*     */   {
/*     */     private static final String b = "element";
/*     */ 
/*     */     
/*     */     private String c;
/*     */     
/*     */     private f d;
/*     */     
/*     */     private Map<b, String> e;
/*     */     
/*     */     private final Map<b, Map<String, Map<f, String>>> f;
/*     */ 
/*     */     
/*     */     public a(d this$0, Map<b, Map<String, Map<f, String>>> param1Map) {
/* 150 */       this.f = param1Map;
/* 151 */       this.e = null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void startDocument() throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void endDocument() throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void startElement(String param1String1, String param1String2, String param1String3, Attributes param1Attributes) throws SAXException {
/* 169 */       if (param1String3
/* 170 */         .equals("element")) {
/* 171 */         this.e = new TreeMap<b, String>();
/* 172 */         String str = param1Attributes.getValue("form");
/* 173 */         if (str == null) {
/*     */ 
/*     */           
/* 176 */           d.b()
/* 177 */             .fatal("Error in dictionary, attribute 'form' is required attribute for the dictionary");
/* 178 */           this.d = f.b;
/*     */         } else {
/* 180 */           this
/* 181 */             .d = f.b(str);
/*     */         } 
/* 183 */         for (byte b = 0; b < param1Attributes.getLength(); b++) {
/* 184 */           String str1 = param1Attributes.getQName(b);
/* 185 */           String str2 = param1Attributes.getValue(b);
/* 186 */           if (!str1.equals("form")) {
/*     */             try {
/* 188 */               this.e.put(
/* 189 */                   b.b(str1), str2);
/* 190 */             } catch (h h) {
/* 191 */               d.b().fatal(h.getMessage());
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void endElement(String param1String1, String param1String2, String param1String3) throws SAXException {
/* 201 */       if (param1String3
/* 202 */         .equals("element"))
/*     */       {
/* 204 */         for (Map.Entry<b, String> entry : this.e
/* 205 */           .entrySet()) {
/*     */           
/* 207 */           b b = (b)entry.getKey();
/* 208 */           String str = (String)entry.getValue();
/*     */           
/* 210 */           Map<Object, Object> map = (Map)this.f.get(b);
/* 211 */           if (map == null) {
/* 212 */             map = new TreeMap<Object, Object>();
/* 213 */             this.f.put(b, map);
/*     */           } 
/*     */           
/* 216 */           Map<f, Object> map1 = (Map)map.get(this.c);
/* 217 */           if (map1 == null) {
/* 218 */             map1 = new EnumMap<f, Object>(f.class);
/*     */             
/* 220 */             map.put(this.c, map1);
/*     */           } 
/* 222 */           map1.put(this.d, str);
/*     */         } 
/*     */       }
/* 225 */       this.e = null;
/* 226 */       this.c = null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void characters(char[] param1ArrayOfchar, int param1Int1, int param1Int2) throws SAXException {
/* 232 */       if (this.e != null) {
/* 233 */         char[] arrayOfChar = new char[param1Int2];
/* 234 */         System.arraycopy(param1ArrayOfchar, param1Int1, arrayOfChar, 0, param1Int2);
/* 235 */         if (this.c == null) {
/* 236 */           this.c = new String(arrayOfChar);
/*     */         } else {
/* 238 */           this.c += new String(arrayOfChar);
/*     */         } 
/* 240 */         this.c = this.c.trim();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/b/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */