/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Stack;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.xml.res.XMLMessages;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QName
/*     */   implements Serializable
/*     */ {
/*     */   protected String _localName;
/*     */   protected String _namespaceURI;
/*     */   protected String _prefix;
/*     */   public static final String S_XMLNAMESPACEURI = "http://www.w3.org/XML/1998/namespace";
/*     */   private int m_hashCode;
/*     */   
/*     */   public QName() {}
/*     */   
/*     */   public QName(String namespaceURI, String localName) {
/*  90 */     this(namespaceURI, localName, false);
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
/*     */   public QName(String namespaceURI, String localName, boolean validate) {
/* 107 */     if (localName == null) {
/* 108 */       throw new IllegalArgumentException(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_NULL", null));
/*     */     }
/*     */     
/* 111 */     if (validate)
/*     */     {
/* 113 */       if (!XMLChar.isValidNCName(localName))
/*     */       {
/* 115 */         throw new IllegalArgumentException(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_INVALID", null));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 120 */     this._namespaceURI = namespaceURI;
/* 121 */     this._localName = localName;
/* 122 */     this.m_hashCode = toString().hashCode();
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
/*     */   public QName(String namespaceURI, String prefix, String localName) {
/* 136 */     this(namespaceURI, prefix, localName, false);
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
/*     */   public QName(String namespaceURI, String prefix, String localName, boolean validate) {
/* 154 */     if (localName == null) {
/* 155 */       throw new IllegalArgumentException(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_NULL", null));
/*     */     }
/*     */     
/* 158 */     if (validate) {
/*     */       
/* 160 */       if (!XMLChar.isValidNCName(localName))
/*     */       {
/* 162 */         throw new IllegalArgumentException(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_INVALID", null));
/*     */       }
/*     */ 
/*     */       
/* 166 */       if (null != prefix && !XMLChar.isValidNCName(prefix))
/*     */       {
/* 168 */         throw new IllegalArgumentException(XMLMessages.createXMLMessage("ER_ARG_PREFIX_INVALID", null));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 173 */     this._namespaceURI = namespaceURI;
/* 174 */     this._prefix = prefix;
/* 175 */     this._localName = localName;
/* 176 */     this.m_hashCode = toString().hashCode();
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
/*     */   public QName(String localName) {
/* 188 */     this(localName, false);
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
/*     */   public QName(String localName, boolean validate) {
/* 204 */     if (localName == null) {
/* 205 */       throw new IllegalArgumentException(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_NULL", null));
/*     */     }
/*     */     
/* 208 */     if (validate)
/*     */     {
/* 210 */       if (!XMLChar.isValidNCName(localName))
/*     */       {
/* 212 */         throw new IllegalArgumentException(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_INVALID", null));
/*     */       }
/*     */     }
/*     */     
/* 216 */     this._namespaceURI = null;
/* 217 */     this._localName = localName;
/* 218 */     this.m_hashCode = toString().hashCode();
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
/*     */   public QName(String qname, Stack namespaces) {
/* 231 */     this(qname, namespaces, false);
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
/*     */   public QName(String qname, Stack namespaces, boolean validate) {
/* 247 */     String namespace = null;
/* 248 */     String prefix = null;
/* 249 */     int indexOfNSSep = qname.indexOf(':');
/*     */     
/* 251 */     if (indexOfNSSep > 0) {
/*     */       
/* 253 */       prefix = qname.substring(0, indexOfNSSep);
/*     */       
/* 255 */       if (prefix.equals("xml")) {
/*     */         
/* 257 */         namespace = "http://www.w3.org/XML/1998/namespace";
/*     */       } else {
/*     */         
/* 260 */         if (prefix.equals("xmlns")) {
/*     */           return;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 266 */         int depth = namespaces.size();
/*     */         
/* 268 */         for (int i = depth - 1; i >= 0; i--) {
/*     */           
/* 270 */           NameSpace ns = namespaces.elementAt(i);
/*     */           
/* 272 */           while (null != ns) {
/*     */             
/* 274 */             if (null != ns.m_prefix && prefix.equals(ns.m_prefix)) {
/*     */               
/* 276 */               namespace = ns.m_uri;
/* 277 */               i = -1;
/*     */               
/*     */               break;
/*     */             } 
/*     */             
/* 282 */             ns = ns.m_next;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 287 */       if (null == namespace)
/*     */       {
/* 289 */         throw new RuntimeException(XMLMessages.createXMLMessage("ER_PREFIX_MUST_RESOLVE", new Object[] { prefix }));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 296 */     this._localName = (indexOfNSSep < 0) ? qname : qname.substring(indexOfNSSep + 1);
/*     */ 
/*     */     
/* 299 */     if (validate)
/*     */     {
/* 301 */       if (this._localName == null || !XMLChar.isValidNCName(this._localName))
/*     */       {
/* 303 */         throw new IllegalArgumentException(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_INVALID", null));
/*     */       }
/*     */     }
/*     */     
/* 307 */     this._namespaceURI = namespace;
/* 308 */     this._prefix = prefix;
/* 309 */     this.m_hashCode = toString().hashCode();
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
/*     */   public QName(String qname, Element namespaceContext, PrefixResolver resolver) {
/* 324 */     this(qname, namespaceContext, resolver, false);
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
/*     */   public QName(String qname, Element namespaceContext, PrefixResolver resolver, boolean validate) {
/* 342 */     this._namespaceURI = null;
/*     */     
/* 344 */     int indexOfNSSep = qname.indexOf(':');
/*     */     
/* 346 */     if (indexOfNSSep > 0)
/*     */     {
/* 348 */       if (null != namespaceContext) {
/*     */         
/* 350 */         String prefix = qname.substring(0, indexOfNSSep);
/*     */         
/* 352 */         this._prefix = prefix;
/*     */         
/* 354 */         if (prefix.equals("xml")) {
/*     */           
/* 356 */           this._namespaceURI = "http://www.w3.org/XML/1998/namespace";
/*     */         }
/*     */         else {
/*     */           
/* 360 */           if (prefix.equals("xmlns")) {
/*     */             return;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 366 */           this._namespaceURI = resolver.getNamespaceForPrefix(prefix, namespaceContext);
/*     */         } 
/*     */ 
/*     */         
/* 370 */         if (null == this._namespaceURI)
/*     */         {
/* 372 */           throw new RuntimeException(XMLMessages.createXMLMessage("ER_PREFIX_MUST_RESOLVE", new Object[] { prefix }));
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
/* 385 */     this._localName = (indexOfNSSep < 0) ? qname : qname.substring(indexOfNSSep + 1);
/*     */ 
/*     */     
/* 388 */     if (validate)
/*     */     {
/* 390 */       if (this._localName == null || !XMLChar.isValidNCName(this._localName))
/*     */       {
/* 392 */         throw new IllegalArgumentException(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_INVALID", null));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 397 */     this.m_hashCode = toString().hashCode();
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
/*     */   public QName(String qname, PrefixResolver resolver) {
/* 411 */     this(qname, resolver, false);
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
/*     */   public QName(String qname, PrefixResolver resolver, boolean validate) {
/* 427 */     String prefix = null;
/* 428 */     this._namespaceURI = null;
/*     */     
/* 430 */     int indexOfNSSep = qname.indexOf(':');
/*     */     
/* 432 */     if (indexOfNSSep > 0) {
/*     */       
/* 434 */       prefix = qname.substring(0, indexOfNSSep);
/*     */       
/* 436 */       if (prefix.equals("xml")) {
/*     */         
/* 438 */         this._namespaceURI = "http://www.w3.org/XML/1998/namespace";
/*     */       }
/*     */       else {
/*     */         
/* 442 */         this._namespaceURI = resolver.getNamespaceForPrefix(prefix);
/*     */       } 
/*     */       
/* 445 */       if (null == this._namespaceURI)
/*     */       {
/* 447 */         throw new RuntimeException(XMLMessages.createXMLMessage("ER_PREFIX_MUST_RESOLVE", new Object[] { prefix }));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 452 */       this._localName = qname.substring(indexOfNSSep + 1);
/*     */     } else {
/* 454 */       if (indexOfNSSep == 0)
/*     */       {
/* 456 */         throw new RuntimeException(XMLMessages.createXMLMessage("ER_NAME_CANT_START_WITH_COLON", null));
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 463 */       this._localName = qname;
/*     */     } 
/*     */     
/* 466 */     if (validate)
/*     */     {
/* 468 */       if (this._localName == null || !XMLChar.isValidNCName(this._localName))
/*     */       {
/* 470 */         throw new IllegalArgumentException(XMLMessages.createXMLMessage("ER_ARG_LOCALNAME_INVALID", null));
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 476 */     this.m_hashCode = toString().hashCode();
/* 477 */     this._prefix = prefix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/* 488 */     return this._namespaceURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrefix() {
/* 499 */     return this._prefix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 509 */     return this._localName;
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
/*     */   public String toString() {
/* 522 */     return (this._prefix != null) ? (this._prefix + ":" + this._localName) : ((this._namespaceURI != null) ? ("{" + this._namespaceURI + "}" + this._localName) : this._localName);
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
/*     */   public String toNamespacedString() {
/* 538 */     return (this._namespaceURI != null) ? ("{" + this._namespaceURI + "}" + this._localName) : this._localName;
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
/*     */   public String getNamespace() {
/* 550 */     return getNamespaceURI();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalPart() {
/* 560 */     return getLocalName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 570 */     return this.m_hashCode;
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
/*     */   public boolean equals(String ns, String localPart) {
/* 586 */     String thisnamespace = getNamespaceURI();
/*     */     
/* 588 */     if (getLocalName().equals(localPart)) if ((null != thisnamespace && null != ns) ? thisnamespace.equals(ns) : ((null == thisnamespace && null == ns)));  return false;
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
/*     */   public boolean equals(Object object) {
/* 606 */     if (object == this) {
/* 607 */       return true;
/*     */     }
/* 609 */     if (object instanceof QName) {
/* 610 */       QName qname = (QName)object;
/* 611 */       String thisnamespace = getNamespaceURI();
/* 612 */       String thatnamespace = qname.getNamespaceURI();
/*     */       
/* 614 */       if (getLocalName().equals(qname.getLocalName())) if ((null != thisnamespace && null != thatnamespace) ? thisnamespace.equals(thatnamespace) : ((null == thisnamespace && null == thatnamespace)));  return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 620 */     return false;
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
/*     */   public static QName getQNameFromString(String name) {
/*     */     QName qName;
/* 634 */     StringTokenizer tokenizer = new StringTokenizer(name, "{}", false);
/*     */     
/* 636 */     String s1 = tokenizer.nextToken();
/* 637 */     String s2 = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
/*     */     
/* 639 */     if (null == s2) {
/* 640 */       qName = new QName(null, s1);
/*     */     } else {
/* 642 */       qName = new QName(s1, s2);
/*     */     } 
/* 644 */     return qName;
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
/*     */   public static boolean isXMLNSDecl(String attRawName) {
/* 658 */     return (attRawName.startsWith("xmlns") && (attRawName.equals("xmlns") || attRawName.startsWith("xmlns:")));
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
/*     */   public static String getPrefixFromXMLNSDecl(String attRawName) {
/* 674 */     int index = attRawName.indexOf(':');
/*     */     
/* 676 */     return (index >= 0) ? attRawName.substring(index + 1) : "";
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
/*     */   public static String getLocalPart(String qname) {
/* 689 */     int index = qname.indexOf(':');
/*     */     
/* 691 */     return (index < 0) ? qname : qname.substring(index + 1);
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
/*     */   public static String getPrefixPart(String qname) {
/* 704 */     int index = qname.indexOf(':');
/*     */     
/* 706 */     return (index >= 0) ? qname.substring(0, index) : "";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/QName.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */