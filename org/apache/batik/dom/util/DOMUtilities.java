/*      */ package org.apache.batik.dom.util;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.StringReader;
/*      */ import java.io.StringWriter;
/*      */ import java.io.Writer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import org.apache.batik.dom.AbstractDocument;
/*      */ import org.apache.batik.util.XMLConstants;
/*      */ import org.apache.batik.xml.XMLUtilities;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.DOMException;
/*      */ import org.w3c.dom.DOMImplementation;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.DocumentType;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class DOMUtilities
/*      */   extends XMLUtilities
/*      */   implements XMLConstants
/*      */ {
/*      */   private static final class NSMap
/*      */   {
/*      */     private String prefix;
/*      */     private String ns;
/*      */     private NSMap next;
/*      */     private int nextPrefixNumber;
/*      */     
/*      */     public static NSMap create() {
/*   91 */       return (new NSMap()).declare("xml", "http://www.w3.org/XML/1998/namespace").declare("xmlns", "http://www.w3.org/2000/xmlns/");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NSMap declare(String prefix, String ns) {
/*  108 */       NSMap m = new NSMap();
/*  109 */       m.prefix = prefix;
/*  110 */       m.ns = ns;
/*  111 */       m.next = this;
/*  112 */       m.nextPrefixNumber = this.nextPrefixNumber;
/*  113 */       return m;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getNewPrefix() {
/*      */       while (true) {
/*  122 */         String prefix = "a" + this.nextPrefixNumber++;
/*  123 */         if (getNamespace(prefix) == null) {
/*  124 */           return prefix;
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getNamespace(String prefix) {
/*  133 */       for (NSMap m = this; m.next != null; m = m.next) {
/*  134 */         if (m.prefix.equals(prefix)) {
/*  135 */           return m.ns;
/*      */         }
/*      */       } 
/*  138 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getPrefixForElement(String ns) {
/*  149 */       for (NSMap m = this; m.next != null; m = m.next) {
/*  150 */         if (ns.equals(m.ns)) {
/*  151 */           return m.prefix;
/*      */         }
/*      */       } 
/*  154 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getPrefixForAttr(String ns) {
/*  163 */       for (NSMap m = this; m.next != null; m = m.next) {
/*  164 */         if (ns.equals(m.ns) && !m.prefix.equals("")) {
/*  165 */           return m.prefix;
/*      */         }
/*      */       } 
/*  168 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeDocument(Document doc, Writer w) throws IOException {
/*  177 */     AbstractDocument d = (AbstractDocument)doc;
/*  178 */     if (doc.getDocumentElement() == null) {
/*  179 */       throw new IOException("No document element");
/*      */     }
/*  181 */     NSMap m = NSMap.create();
/*  182 */     Node n = doc.getFirstChild();
/*  183 */     for (; n != null; 
/*  184 */       n = n.getNextSibling())
/*  185 */       writeNode(n, w, m, "1.1".equals(d.getXmlVersion()));  } protected static void writeNode(Node n, Writer w, NSMap m, boolean isXML11) throws IOException { String ns; String str1; String target; String data; DocumentType dt; String tagName; String str2;
/*      */     int len;
/*      */     String pubID;
/*      */     Node c;
/*      */     String sysID;
/*      */     String subset;
/*  191 */     switch (n.getNodeType()) {
/*      */       case 1:
/*  193 */         if (n.hasAttributes()) {
/*  194 */           NamedNodeMap attr = n.getAttributes();
/*  195 */           int j = attr.getLength();
/*  196 */           for (int i = 0; i < j; i++) {
/*  197 */             Attr a = (Attr)attr.item(i);
/*  198 */             String name = a.getNodeName();
/*  199 */             if (name.startsWith("xmlns")) {
/*  200 */               if (name.length() == 5) {
/*  201 */                 m = m.declare("", a.getNodeValue());
/*      */               } else {
/*  203 */                 String prefix = name.substring(6);
/*  204 */                 m = m.declare(prefix, a.getNodeValue());
/*      */               } 
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/*  210 */         w.write(60);
/*  211 */         ns = n.getNamespaceURI();
/*      */         
/*  213 */         if (ns == null) {
/*  214 */           tagName = n.getNodeName();
/*  215 */           w.write(tagName);
/*  216 */           if (!"".equals(m.getNamespace(""))) {
/*  217 */             w.write(" xmlns=\"\"");
/*  218 */             m = m.declare("", "");
/*      */           } 
/*      */         } else {
/*  221 */           String prefix = n.getPrefix();
/*  222 */           if (prefix == null) {
/*  223 */             prefix = "";
/*      */           }
/*  225 */           if (ns.equals(m.getNamespace(prefix))) {
/*  226 */             tagName = n.getNodeName();
/*  227 */             w.write(tagName);
/*      */           } else {
/*  229 */             prefix = m.getPrefixForElement(ns);
/*  230 */             if (prefix == null) {
/*  231 */               prefix = m.getNewPrefix();
/*  232 */               tagName = prefix + ':' + n.getLocalName();
/*  233 */               w.write(tagName + " xmlns:" + prefix + "=\"" + contentToString(ns, isXML11) + '"');
/*      */               
/*  235 */               m = m.declare(prefix, ns);
/*      */             } else {
/*  237 */               if (prefix.equals("")) {
/*  238 */                 tagName = n.getLocalName();
/*      */               } else {
/*  240 */                 tagName = prefix + ':' + n.getLocalName();
/*      */               } 
/*  242 */               w.write(tagName);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/*  247 */         if (n.hasAttributes()) {
/*  248 */           NamedNodeMap attr = n.getAttributes();
/*  249 */           int j = attr.getLength();
/*  250 */           for (int i = 0; i < j; i++) {
/*  251 */             Attr a = (Attr)attr.item(i);
/*  252 */             String name = a.getNodeName();
/*  253 */             String prefix = a.getPrefix();
/*  254 */             String ans = a.getNamespaceURI();
/*  255 */             if (ans != null && !"xmlns".equals(prefix) && !name.equals("xmlns"))
/*      */             {
/*  257 */               if ((prefix != null && !ans.equals(m.getNamespace(prefix))) || prefix == null) {
/*      */ 
/*      */                 
/*  260 */                 prefix = m.getPrefixForAttr(ans);
/*  261 */                 if (prefix == null) {
/*  262 */                   prefix = m.getNewPrefix();
/*  263 */                   m = m.declare(prefix, ans);
/*  264 */                   w.write(" xmlns:" + prefix + "=\"" + contentToString(ans, isXML11) + '"');
/*      */                 } 
/*      */                 
/*  267 */                 name = prefix + ':' + a.getLocalName();
/*      */               } 
/*      */             }
/*  270 */             w.write(' ' + name + "=\"" + contentToString(a.getNodeValue(), isXML11) + '"');
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  276 */         c = n.getFirstChild();
/*  277 */         if (c != null) {
/*  278 */           w.write(62);
/*      */           while (true) {
/*  280 */             writeNode(c, w, m, isXML11);
/*  281 */             c = c.getNextSibling();
/*  282 */             if (c == null)
/*  283 */             { w.write("</" + tagName + '>'); return; } 
/*      */           } 
/*  285 */         }  w.write("/>");
/*      */         return;
/*      */ 
/*      */       
/*      */       case 3:
/*  290 */         w.write(contentToString(n.getNodeValue(), isXML11));
/*      */         return;
/*      */       case 4:
/*  293 */         str1 = n.getNodeValue();
/*  294 */         if (str1.indexOf("]]>") != -1) {
/*  295 */           throw new IOException("Unserializable CDATA section node");
/*      */         }
/*  297 */         w.write("<![CDATA[" + assertValidCharacters(str1, isXML11) + "]]>");
/*      */         return;
/*      */ 
/*      */ 
/*      */       
/*      */       case 5:
/*  303 */         w.write('&' + n.getNodeName() + ';');
/*      */         return;
/*      */       case 7:
/*  306 */         target = n.getNodeName();
/*  307 */         str2 = n.getNodeValue();
/*  308 */         if (target.equalsIgnoreCase("xml") || target.indexOf(':') != -1 || str2.indexOf("?>") != -1)
/*      */         {
/*      */           
/*  311 */           throw new IOException("Unserializable processing instruction node");
/*      */         }
/*      */         
/*  314 */         w.write("<?" + target + ' ' + str2 + "?>");
/*      */         return;
/*      */       
/*      */       case 8:
/*  318 */         w.write("<!--");
/*  319 */         data = n.getNodeValue();
/*  320 */         len = data.length();
/*  321 */         if ((len != 0 && data.charAt(len - 1) == '-') || data.indexOf("--") != -1)
/*      */         {
/*  323 */           throw new IOException("Unserializable comment node");
/*      */         }
/*  325 */         w.write(data);
/*  326 */         w.write("-->");
/*      */         return;
/*      */       
/*      */       case 10:
/*  330 */         dt = (DocumentType)n;
/*  331 */         w.write("<!DOCTYPE " + n.getOwnerDocument().getDocumentElement().getNodeName());
/*      */         
/*  333 */         pubID = dt.getPublicId();
/*  334 */         if (pubID != null) {
/*  335 */           char q = getUsableQuote(pubID);
/*  336 */           if (q == '\000') {
/*  337 */             throw new IOException("Unserializable DOCTYPE node");
/*      */           }
/*  339 */           w.write(" PUBLIC " + q + pubID + q);
/*      */         } 
/*  341 */         sysID = dt.getSystemId();
/*  342 */         if (sysID != null) {
/*  343 */           char q = getUsableQuote(sysID);
/*  344 */           if (q == '\000') {
/*  345 */             throw new IOException("Unserializable DOCTYPE node");
/*      */           }
/*  347 */           if (pubID == null) {
/*  348 */             w.write(" SYSTEM");
/*      */           }
/*  350 */           w.write(" " + q + sysID + q);
/*      */         } 
/*  352 */         subset = dt.getInternalSubset();
/*  353 */         if (subset != null) {
/*  354 */           w.write('[' + subset + ']');
/*      */         }
/*  356 */         w.write(62);
/*      */         return;
/*      */     } 
/*      */     
/*  360 */     throw new IOException("Unknown DOM node type " + n.getNodeType()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeNode(Node n, Writer w) throws IOException {
/*  368 */     if (n.getNodeType() == 9) {
/*  369 */       writeDocument((Document)n, w);
/*      */     } else {
/*  371 */       AbstractDocument d = (AbstractDocument)n.getOwnerDocument();
/*  372 */       writeNode(n, w, NSMap.create(), (d == null) ? false : "1.1".equals(d.getXmlVersion()));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static char getUsableQuote(String s) {
/*  383 */     char ret = Character.MIN_VALUE;
/*  384 */     int i = s.length() - 1;
/*  385 */     while (i >= 0) {
/*  386 */       char c = s.charAt(i);
/*  387 */       if (c == '"') {
/*  388 */         if (ret == '\000') {
/*  389 */           ret = '\'';
/*      */         } else {
/*  391 */           return Character.MIN_VALUE;
/*      */         } 
/*  393 */       } else if (c == '\'') {
/*  394 */         if (ret == '\000') {
/*  395 */           ret = '"';
/*      */         } else {
/*  397 */           return Character.MIN_VALUE;
/*      */         } 
/*      */       } 
/*  400 */       i--;
/*      */     } 
/*  402 */     return (ret == '\000') ? '"' : ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getXML(Node n) {
/*  414 */     Writer writer = new StringWriter();
/*      */     try {
/*  416 */       writeNode(n, writer);
/*  417 */       writer.close();
/*  418 */     } catch (IOException ex) {
/*  419 */       return "";
/*      */     } 
/*  421 */     return writer.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static String assertValidCharacters(String s, boolean isXML11) throws IOException {
/*  427 */     int len = s.length();
/*  428 */     for (int i = 0; i < len; i++) {
/*  429 */       char c = s.charAt(i);
/*  430 */       if ((!isXML11 && !isXMLCharacter(c)) || (isXML11 && !isXML11Character(c)))
/*      */       {
/*  432 */         throw new IOException("Invalid character");
/*      */       }
/*      */     } 
/*  435 */     return s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String contentToString(String s, boolean isXML11) throws IOException {
/*  445 */     StringBuffer result = new StringBuffer(s.length());
/*      */     
/*  447 */     int len = s.length();
/*  448 */     for (int i = 0; i < len; i++) {
/*  449 */       char c = s.charAt(i);
/*  450 */       if ((!isXML11 && !isXMLCharacter(c)) || (isXML11 && !isXML11Character(c)))
/*      */       {
/*  452 */         throw new IOException("Invalid character");
/*      */       }
/*      */       
/*  455 */       switch (c) {
/*      */         case '<':
/*  457 */           result.append("&lt;");
/*      */           break;
/*      */         case '>':
/*  460 */           result.append("&gt;");
/*      */           break;
/*      */         case '&':
/*  463 */           result.append("&amp;");
/*      */           break;
/*      */         case '"':
/*  466 */           result.append("&quot;");
/*      */           break;
/*      */         case '\'':
/*  469 */           result.append("&apos;");
/*      */           break;
/*      */         default:
/*  472 */           result.append(c);
/*      */           break;
/*      */       } 
/*      */     } 
/*  476 */     return result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getChildIndex(Node child, Node parent) {
/*  490 */     if (child == null || child.getParentNode() != parent || child.getParentNode() == null)
/*      */     {
/*  492 */       return -1;
/*      */     }
/*  494 */     return getChildIndex(child);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getChildIndex(Node child) {
/*  505 */     NodeList children = child.getParentNode().getChildNodes();
/*  506 */     for (int i = 0; i < children.getLength(); i++) {
/*  507 */       Node currentChild = children.item(i);
/*  508 */       if (currentChild == child) {
/*  509 */         return i;
/*      */       }
/*      */     } 
/*  512 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAnyNodeAncestorOf(ArrayList ancestorNodes, Node node) {
/*  526 */     int n = ancestorNodes.size();
/*  527 */     for (Object ancestorNode : ancestorNodes) {
/*  528 */       Node ancestor = (Node)ancestorNode;
/*  529 */       if (isAncestorOf(ancestor, node)) {
/*  530 */         return true;
/*      */       }
/*      */     } 
/*  533 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAncestorOf(Node node, Node descendant) {
/*  546 */     if (node == null || descendant == null) {
/*  547 */       return false;
/*      */     }
/*  549 */     for (Node currentNode = descendant.getParentNode(); currentNode != null; currentNode = currentNode.getParentNode()) {
/*      */       
/*  551 */       if (currentNode == node) {
/*  552 */         return true;
/*      */       }
/*      */     } 
/*  555 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isParentOf(Node node, Node parentNode) {
/*  568 */     if (node == null || parentNode == null || node.getParentNode() != parentNode)
/*      */     {
/*  570 */       return false;
/*      */     }
/*  572 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean canAppend(Node node, Node parentNode) {
/*  585 */     if (node == null || parentNode == null || node == parentNode || isAncestorOf(node, parentNode))
/*      */     {
/*  587 */       return false;
/*      */     }
/*  589 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean canAppendAny(ArrayList children, Node parentNode) {
/*  603 */     if (!canHaveChildren(parentNode)) {
/*  604 */       return false;
/*      */     }
/*  606 */     int n = children.size();
/*  607 */     for (Object aChildren : children) {
/*  608 */       Node child = (Node)aChildren;
/*  609 */       if (canAppend(child, parentNode)) {
/*  610 */         return true;
/*      */       }
/*      */     } 
/*  613 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean canHaveChildren(Node parentNode) {
/*  624 */     if (parentNode == null) {
/*  625 */       return false;
/*      */     }
/*  627 */     switch (parentNode.getNodeType()) {
/*      */       case 3:
/*      */       case 4:
/*      */       case 7:
/*      */       case 8:
/*      */       case 9:
/*  633 */         return false;
/*      */     } 
/*  635 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Node parseXML(String text, Document doc, String uri, Map prefixes, String wrapperElementName, SAXDocumentFactory documentFactory) {
/*  666 */     String wrapperElementPrefix = "";
/*  667 */     String wrapperElementSuffix = "";
/*  668 */     if (wrapperElementName != null) {
/*  669 */       wrapperElementPrefix = "<" + wrapperElementName;
/*      */       
/*  671 */       if (prefixes != null) {
/*  672 */         wrapperElementPrefix = wrapperElementPrefix + " ";
/*  673 */         for (Object o : prefixes.entrySet()) {
/*  674 */           Map.Entry e = (Map.Entry)o;
/*  675 */           String currentKey = (String)e.getKey();
/*  676 */           String currentValue = (String)e.getValue();
/*  677 */           wrapperElementPrefix = wrapperElementPrefix + currentKey + "=\"" + currentValue + "\" ";
/*      */         } 
/*      */       } 
/*      */       
/*  681 */       wrapperElementPrefix = wrapperElementPrefix + ">";
/*  682 */       wrapperElementSuffix = wrapperElementSuffix + "</" + wrapperElementName + '>';
/*      */     } 
/*      */ 
/*      */     
/*  686 */     if (wrapperElementPrefix.trim().length() == 0 && wrapperElementSuffix.trim().length() == 0) {
/*      */       
/*      */       try {
/*  689 */         Document d = documentFactory.createDocument(uri, new StringReader(text));
/*      */         
/*  691 */         if (doc == null) {
/*  692 */           return d;
/*      */         }
/*  694 */         Node result = doc.createDocumentFragment();
/*  695 */         result.appendChild(doc.importNode(d.getDocumentElement(), true));
/*      */ 
/*      */         
/*  698 */         return result;
/*  699 */       } catch (Exception exception) {}
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  705 */     StringBuffer sb = new StringBuffer(wrapperElementPrefix.length() + text.length() + wrapperElementSuffix.length());
/*      */     
/*  707 */     sb.append(wrapperElementPrefix);
/*  708 */     sb.append(text);
/*  709 */     sb.append(wrapperElementSuffix);
/*  710 */     String newText = sb.toString();
/*      */     try {
/*  712 */       Document d = documentFactory.createDocument(uri, new StringReader(newText));
/*      */       
/*  714 */       if (doc == null) {
/*  715 */         return d;
/*      */       }
/*  717 */       for (Node node = d.getDocumentElement().getFirstChild(); node != null; 
/*  718 */         node = node.getNextSibling()) {
/*  719 */         if (node.getNodeType() == 1) {
/*  720 */           node = doc.importNode(node, true);
/*  721 */           Node result = doc.createDocumentFragment();
/*  722 */           result.appendChild(node);
/*  723 */           return result;
/*      */         } 
/*      */       } 
/*  726 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/*  729 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Document deepCloneDocument(Document doc, DOMImplementation impl) {
/*  736 */     Element root = doc.getDocumentElement();
/*  737 */     Document result = impl.createDocument(root.getNamespaceURI(), root.getNodeName(), null);
/*      */ 
/*      */     
/*  740 */     Element rroot = result.getDocumentElement();
/*  741 */     boolean before = true;
/*  742 */     for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling()) {
/*  743 */       if (n == root) {
/*  744 */         before = false;
/*  745 */         if (root.hasAttributes()) {
/*  746 */           NamedNodeMap attr = root.getAttributes();
/*  747 */           int len = attr.getLength();
/*  748 */           for (int i = 0; i < len; i++) {
/*  749 */             rroot.setAttributeNode((Attr)result.importNode(attr.item(i), true));
/*      */           }
/*      */         } 
/*      */         
/*  753 */         Node c = root.getFirstChild();
/*  754 */         for (; c != null; 
/*  755 */           c = c.getNextSibling()) {
/*  756 */           rroot.appendChild(result.importNode(c, true));
/*      */         }
/*      */       }
/*  759 */       else if (n.getNodeType() != 10) {
/*  760 */         if (before) {
/*  761 */           result.insertBefore(result.importNode(n, true), rroot);
/*      */         } else {
/*  763 */           result.appendChild(result.importNode(n, true));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  768 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isValidName(String s) {
/*  775 */     int len = s.length();
/*  776 */     if (len == 0) {
/*  777 */       return false;
/*      */     }
/*  779 */     char c = s.charAt(0);
/*  780 */     int d = c / 32;
/*  781 */     int m = c % 32;
/*  782 */     if ((NAME_FIRST_CHARACTER[d] & 1 << m) == 0) {
/*  783 */       return false;
/*      */     }
/*  785 */     for (int i = 1; i < len; i++) {
/*  786 */       c = s.charAt(i);
/*  787 */       d = c / 32;
/*  788 */       m = c % 32;
/*  789 */       if ((NAME_CHARACTER[d] & 1 << m) == 0) {
/*  790 */         return false;
/*      */       }
/*      */     } 
/*  793 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isValidName11(String s) {
/*  800 */     int len = s.length();
/*  801 */     if (len == 0) {
/*  802 */       return false;
/*      */     }
/*  804 */     char c = s.charAt(0);
/*  805 */     int d = c / 32;
/*  806 */     int m = c % 32;
/*  807 */     if ((NAME11_FIRST_CHARACTER[d] & 1 << m) == 0) {
/*  808 */       return false;
/*      */     }
/*  810 */     for (int i = 1; i < len; i++) {
/*  811 */       c = s.charAt(i);
/*  812 */       d = c / 32;
/*  813 */       m = c % 32;
/*  814 */       if ((NAME11_CHARACTER[d] & 1 << m) == 0) {
/*  815 */         return false;
/*      */       }
/*      */     } 
/*  818 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isValidPrefix(String s) {
/*  826 */     return (s.indexOf(':') == -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getPrefix(String s) {
/*  834 */     int i = s.indexOf(':');
/*  835 */     return (i == -1 || i == s.length() - 1) ? null : s.substring(0, i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getLocalName(String s) {
/*  845 */     int i = s.indexOf(':');
/*  846 */     return (i == -1 || i == s.length() - 1) ? s : s.substring(i + 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void parseStyleSheetPIData(String data, HashMap<String, String> table) {
/*  858 */     int i = 0;
/*      */     
/*  860 */     while (i < data.length()) {
/*  861 */       char c = data.charAt(i);
/*  862 */       if (!XMLUtilities.isXMLSpace(c)) {
/*      */         break;
/*      */       }
/*  865 */       i++;
/*      */     } 
/*  867 */     while (i < data.length()) {
/*      */       
/*  869 */       char c = data.charAt(i);
/*  870 */       int d = c / 32;
/*  871 */       int m = c % 32;
/*  872 */       if ((NAME_FIRST_CHARACTER[d] & 1 << m) == 0) {
/*  873 */         throw new DOMException((short)5, "Wrong name initial:  " + c);
/*      */       }
/*      */       
/*  876 */       StringBuffer ident = new StringBuffer();
/*  877 */       ident.append(c);
/*  878 */       while (++i < data.length()) {
/*  879 */         c = data.charAt(i);
/*  880 */         d = c / 32;
/*  881 */         m = c % 32;
/*  882 */         if ((NAME_CHARACTER[d] & 1 << m) == 0) {
/*      */           break;
/*      */         }
/*  885 */         ident.append(c);
/*      */       } 
/*  887 */       if (i >= data.length()) {
/*  888 */         throw new DOMException((short)12, "Wrong xml-stylesheet data: " + data);
/*      */       }
/*      */ 
/*      */       
/*  892 */       while (i < data.length()) {
/*  893 */         c = data.charAt(i);
/*  894 */         if (!XMLUtilities.isXMLSpace(c)) {
/*      */           break;
/*      */         }
/*  897 */         i++;
/*      */       } 
/*  899 */       if (i >= data.length()) {
/*  900 */         throw new DOMException((short)12, "Wrong xml-stylesheet data: " + data);
/*      */       }
/*      */ 
/*      */       
/*  904 */       if (data.charAt(i) != '=') {
/*  905 */         throw new DOMException((short)12, "Wrong xml-stylesheet data: " + data);
/*      */       }
/*      */       
/*  908 */       i++;
/*      */       
/*  910 */       while (i < data.length()) {
/*  911 */         c = data.charAt(i);
/*  912 */         if (!XMLUtilities.isXMLSpace(c)) {
/*      */           break;
/*      */         }
/*  915 */         i++;
/*      */       } 
/*  917 */       if (i >= data.length()) {
/*  918 */         throw new DOMException((short)12, "Wrong xml-stylesheet data: " + data);
/*      */       }
/*      */ 
/*      */       
/*  922 */       c = data.charAt(i);
/*  923 */       i++;
/*  924 */       StringBuffer value = new StringBuffer();
/*  925 */       if (c == '\'') {
/*  926 */         while (i < data.length()) {
/*  927 */           c = data.charAt(i);
/*  928 */           if (c == '\'') {
/*      */             break;
/*      */           }
/*  931 */           value.append(c);
/*  932 */           i++;
/*      */         } 
/*  934 */         if (i >= data.length()) {
/*  935 */           throw new DOMException((short)12, "Wrong xml-stylesheet data: " + data);
/*      */         
/*      */         }
/*      */       }
/*  939 */       else if (c == '"') {
/*  940 */         while (i < data.length()) {
/*  941 */           c = data.charAt(i);
/*  942 */           if (c == '"') {
/*      */             break;
/*      */           }
/*  945 */           value.append(c);
/*  946 */           i++;
/*      */         } 
/*  948 */         if (i >= data.length()) {
/*  949 */           throw new DOMException((short)12, "Wrong xml-stylesheet data: " + data);
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/*  954 */         throw new DOMException((short)12, "Wrong xml-stylesheet data: " + data);
/*      */       } 
/*      */       
/*  957 */       table.put(ident.toString().intern(), value.toString());
/*  958 */       i++;
/*      */       
/*  960 */       while (i < data.length()) {
/*  961 */         c = data.charAt(i);
/*  962 */         if (!XMLUtilities.isXMLSpace(c)) {
/*      */           break;
/*      */         }
/*  965 */         i++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  974 */   protected static final String[] LOCK_STRINGS = new String[] { "", "CapsLock", "NumLock", "NumLock CapsLock", "Scroll", "Scroll CapsLock", "Scroll NumLock", "Scroll NumLock CapsLock", "KanaMode", "KanaMode CapsLock", "KanaMode NumLock", "KanaMode NumLock CapsLock", "KanaMode Scroll", "KanaMode Scroll CapsLock", "KanaMode Scroll NumLock", "KanaMode Scroll NumLock CapsLock" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  997 */   protected static final String[] MODIFIER_STRINGS = new String[] { "", "Shift", "Control", "Control Shift", "Meta", "Meta Shift", "Control Meta", "Control Meta Shift", "Alt", "Alt Shift", "Alt Control", "Alt Control Shift", "Alt Meta", "Alt Meta Shift", "Alt Control Meta", "Alt Control Meta Shift", "AltGraph", "AltGraph Shift", "AltGraph Control", "AltGraph Control Shift", "AltGraph Meta", "AltGraph Meta Shift", "AltGraph Control Meta", "AltGraph Control Meta Shift", "Alt AltGraph", "Alt AltGraph Shift", "Alt AltGraph Control", "Alt AltGraph Control Shift", "Alt AltGraph Meta", "Alt AltGraph Meta Shift", "Alt AltGraph Control Meta", "Alt AltGraph Control Meta Shift" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getModifiersList(int lockState, int modifiersEx) {
/* 1037 */     if ((modifiersEx & 0x2000) != 0) {
/* 1038 */       modifiersEx = 0x10 | modifiersEx >> 6 & 0xF;
/*      */     } else {
/* 1040 */       modifiersEx = modifiersEx >> 6 & 0xF;
/*      */     } 
/* 1042 */     String s = LOCK_STRINGS[lockState & 0xF];
/* 1043 */     if (s.length() != 0) {
/* 1044 */       return s + ' ' + MODIFIER_STRINGS[modifiersEx];
/*      */     }
/* 1046 */     return MODIFIER_STRINGS[modifiersEx];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAttributeSpecifiedNS(Element e, String namespaceURI, String localName) {
/* 1057 */     Attr a = e.getAttributeNodeNS(namespaceURI, localName);
/* 1058 */     return (a != null && a.getSpecified());
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/util/DOMUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */