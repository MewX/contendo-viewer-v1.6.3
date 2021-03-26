/*     */ package net.a.a.e.d.b;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.EnumMap;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.xpath.XPath;
/*     */ import javax.xml.xpath.XPathConstants;
/*     */ import javax.xml.xpath.XPathExpression;
/*     */ import javax.xml.xpath.XPathExpressionException;
/*     */ import javax.xml.xpath.XPathFactory;
/*     */ import net.a.a.e.d.f;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public final class e
/*     */   extends a
/*     */   implements Serializable
/*     */ {
/*  60 */   private static final Log m = LogFactory.getLog(e.class);
/*     */ 
/*     */   
/*     */   private static final String n = "0em";
/*     */ 
/*     */   
/*     */   private static final int o = 0;
/*     */ 
/*     */   
/*     */   private static final int p = 1;
/*     */ 
/*     */   
/*     */   private static final int q = 2;
/*     */ 
/*     */   
/*     */   private static final int r = 3;
/*     */ 
/*     */   
/*     */   private static final int s = 4;
/*     */ 
/*     */   
/*     */   private static final int t = 5;
/*     */ 
/*     */   
/*     */   private static final int u = 6;
/*     */ 
/*     */   
/*     */   private static final int v = 7;
/*     */ 
/*     */   
/*     */   private static final long w = 1L;
/*     */ 
/*     */   
/*     */   private static final String x = "/net/sourceforge/jeuclid/appendixc.xml";
/*     */   
/*     */   private static final String y = "/net/sourceforge/jeuclid/appendixc.ser";
/*     */   
/*     */   private static c z;
/*     */ 
/*     */   
/*     */   private class a
/*     */     implements NamespaceContext
/*     */   {
/*     */     public a(e this$0) {}
/*     */ 
/*     */     
/*     */     public String getNamespaceURI(String param1String) {
/*     */       String str;
/* 108 */       if ("html".equals(param1String)) {
/* 109 */         str = "http://www.w3.org/1999/xhtml";
/* 110 */       } else if ("xml".equals(param1String)) {
/* 111 */         str = "http://www.w3.org/XML/1998/namespace";
/*     */       } else {
/* 113 */         str = "";
/*     */       } 
/* 115 */       return str;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String getPrefix(String param1String) {
/* 121 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Iterator<String> getPrefixes(String param1String) {
/* 127 */       throw new UnsupportedOperationException();
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
/*     */   public static c a() {
/* 142 */     synchronized (e.class) {
/* 143 */       if (z == null) {
/*     */         
/* 145 */         c c1 = a.a("/net/sourceforge/jeuclid/appendixc.ser");
/* 146 */         if (c1 == null) {
/* 147 */           z = new e();
/*     */         } else {
/* 149 */           z = c1;
/*     */         } 
/*     */       } 
/*     */     } 
/* 153 */     return z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(Map<b, Map<String, Map<f, String>>> paramMap) {
/*     */     try {
/* 161 */       Document document = b();
/* 162 */       XPath xPath = c();
/* 163 */       a(document, xPath, paramMap);
/* 164 */     } catch (SAXException sAXException) {
/* 165 */       m.warn("XML Could not be parsed in operator dictionary", sAXException);
/*     */     }
/* 167 */     catch (IOException iOException) {
/* 168 */       m.warn("IO error reading operator dictionary", iOException);
/*     */     }
/* 170 */     catch (XPathExpressionException xPathExpressionException) {
/* 171 */       m.warn("XPath error in operator dictionary", xPathExpressionException);
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
/*     */   private void a(Document paramDocument, XPath paramXPath, Map<b, Map<String, Map<f, String>>> paramMap) throws XPathExpressionException {
/* 187 */     XPathExpression xPathExpression1 = paramXPath.compile("//html:table[@class='sortable']/html:tbody/html:tr");
/*     */ 
/*     */     
/* 190 */     XPathExpression xPathExpression2 = paramXPath.compile("html:th[2]/text()");
/* 191 */     XPathExpression xPathExpression3 = paramXPath.compile("html:th[4]/text()");
/* 192 */     XPathExpression xPathExpression4 = paramXPath.compile("html:td[2]/text()");
/* 193 */     XPathExpression xPathExpression5 = paramXPath.compile("html:td[3]/text()");
/*     */     
/* 195 */     XPathExpression xPathExpression6 = paramXPath.compile("html:td[4]/text()");
/*     */     
/* 197 */     XPathExpression xPathExpression7 = paramXPath.compile("html:td[5]/text()");
/*     */     
/* 199 */     NodeList nodeList = (NodeList)xPathExpression1.evaluate(paramDocument, XPathConstants.NODESET);
/*     */ 
/*     */     
/* 202 */     for (byte b = 0; b < nodeList.getLength(); b++) {
/* 203 */       Node node = nodeList.item(b);
/* 204 */       String str1 = (String)xPathExpression2.evaluate(node, XPathConstants.STRING);
/*     */       
/* 206 */       String str2 = (String)xPathExpression3.evaluate(node, XPathConstants.STRING);
/*     */       
/* 208 */       f f = f.b(str2);
/* 209 */       String str3 = (String)xPathExpression4.evaluate(node, XPathConstants.STRING);
/*     */       
/* 211 */       String str4 = (String)xPathExpression5.evaluate(node, XPathConstants.STRING);
/*     */       
/* 213 */       String str5 = (String)xPathExpression6.evaluate(node, XPathConstants.STRING);
/*     */       
/* 215 */       String str6 = (String)xPathExpression7.evaluate(node, XPathConstants.STRING);
/*     */ 
/*     */       
/* 218 */       a(str1, f, b.d, 
/* 219 */           b(str3), paramMap);
/* 220 */       a(str1, f, b.e, 
/* 221 */           b(str4), paramMap);
/* 222 */       if (str5.length() > 0) {
/* 223 */         a(str1, f, b.i, str5, paramMap);
/*     */       }
/*     */       
/* 226 */       a(str1, f, str6, paramMap);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Document b() throws SAXException, IOException {
/* 237 */     InputStream inputStream = e.class.getResourceAsStream("/net/sourceforge/jeuclid/appendixc.xml");
/* 238 */     return net.a.a.h.a.a().c().parse(inputStream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private XPath c() {
/* 247 */     XPathFactory xPathFactory = XPathFactory.newInstance();
/* 248 */     XPath xPath = xPathFactory.newXPath();
/* 249 */     f f1 = new f("xml", "http://www.w3.org/XML/1998/namespace", null);
/*     */     
/* 251 */     f f2 = new f("html", "http://www.w3.org/1999/xhtml", (NamespaceContext)f1);
/*     */     
/* 253 */     xPath.setNamespaceContext((NamespaceContext)f2);
/* 254 */     return xPath;
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
/*     */   private void a(String paramString1, f paramf, String paramString2, Map<b, Map<String, Map<f, String>>> paramMap) {
/* 267 */     String[] arrayOfString = paramString2.split(" ");
/* 268 */     for (String str : arrayOfString) {
/* 269 */       if (str.length() > 0) {
/*     */         
/*     */         try {
/* 272 */           b b = b.b(str);
/* 273 */           a(paramString1, paramf, b, "true", paramMap);
/* 274 */         } catch (h h) {
/* 275 */           m.warn("Unkown Attribute when reading operator dictionary: " + str, h);
/*     */         } 
/*     */       }
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
/*     */ 
/*     */   
/*     */   private void a(String paramString1, f paramf, b paramb, String paramString2, Map<b, Map<String, Map<f, String>>> paramMap) {
/* 297 */     Map<Object, Object> map = (Map)paramMap.get(paramb);
/* 298 */     if (map == null) {
/* 299 */       map = new HashMap<Object, Object>();
/* 300 */       paramMap.put(paramb, map);
/*     */     } 
/*     */     
/* 303 */     Map<f, Object> map1 = (Map)map.get(paramString1);
/* 304 */     if (map1 == null) {
/* 305 */       map1 = new EnumMap<f, Object>(f.class);
/* 306 */       map.put(paramString1, map1);
/*     */     } 
/*     */     
/* 309 */     map1.put(paramf, paramString2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String b(String paramString) {
/*     */     String str;
/*     */     
/* 319 */     try { int i = Integer.parseInt(paramString);
/* 320 */       switch (i)
/*     */       { case 0:
/* 322 */           str = "0em";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 351 */           return str;case 1: str = "veryverythinmathspace"; return str;case 2: str = "verythinmathspace"; return str;case 3: str = "thinmathspace"; return str;case 4: str = "mediummathspace"; return str;case 5: str = "thickmathspace"; return str;case 6: str = "verythickmathspace"; return str;case 7: str = "veryverythickmathspace"; return str; }  str = "0em"; } catch (NumberFormatException numberFormatException) { str = "0em"; }  return str;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/b/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */