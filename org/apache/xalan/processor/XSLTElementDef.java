/*     */ package org.apache.xalan.processor;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import org.apache.xml.utils.QName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XSLTElementDef
/*     */ {
/*     */   static final int T_ELEMENT = 1;
/*     */   static final int T_PCDATA = 2;
/*     */   static final int T_ANY = 3;
/*     */   
/*     */   XSLTElementDef() {}
/*     */   
/*     */   XSLTElementDef(XSLTSchema schema, String namespace, String name, String nameAlias, XSLTElementDef[] elements, XSLTAttributeDef[] attributes, XSLTElementProcessor contentHandler, Class classObject) {
/*  57 */     build(namespace, name, nameAlias, elements, attributes, contentHandler, classObject);
/*     */     
/*  59 */     if (null != namespace && (namespace.equals("http://www.w3.org/1999/XSL/Transform") || namespace.equals("http://xml.apache.org/xalan") || namespace.equals("http://xml.apache.org/xslt"))) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  64 */       schema.addAvailableElement(new QName(namespace, name));
/*  65 */       if (null != nameAlias) {
/*  66 */         schema.addAvailableElement(new QName(namespace, nameAlias));
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
/*     */   XSLTElementDef(XSLTSchema schema, String namespace, String name, String nameAlias, XSLTElementDef[] elements, XSLTAttributeDef[] attributes, XSLTElementProcessor contentHandler, Class classObject, boolean has_required) {
/*  86 */     this.m_has_required = has_required;
/*  87 */     build(namespace, name, nameAlias, elements, attributes, contentHandler, classObject);
/*     */     
/*  89 */     if (null != namespace && (namespace.equals("http://www.w3.org/1999/XSL/Transform") || namespace.equals("http://xml.apache.org/xalan") || namespace.equals("http://xml.apache.org/xslt"))) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  94 */       schema.addAvailableElement(new QName(namespace, name));
/*  95 */       if (null != nameAlias) {
/*  96 */         schema.addAvailableElement(new QName(namespace, nameAlias));
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
/*     */ 
/*     */   
/*     */   XSLTElementDef(XSLTSchema schema, String namespace, String name, String nameAlias, XSLTElementDef[] elements, XSLTAttributeDef[] attributes, XSLTElementProcessor contentHandler, Class classObject, boolean has_required, boolean required) {
/* 119 */     this(schema, namespace, name, nameAlias, elements, attributes, contentHandler, classObject, has_required);
/*     */ 
/*     */     
/* 122 */     this.m_required = required;
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
/*     */   XSLTElementDef(XSLTSchema schema, String namespace, String name, String nameAlias, XSLTElementDef[] elements, XSLTAttributeDef[] attributes, XSLTElementProcessor contentHandler, Class classObject, boolean has_required, boolean required, int order, boolean multiAllowed) {
/* 146 */     this(schema, namespace, name, nameAlias, elements, attributes, contentHandler, classObject, has_required, required);
/*     */ 
/*     */     
/* 149 */     this.m_order = order;
/* 150 */     this.m_multiAllowed = multiAllowed;
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
/*     */   XSLTElementDef(XSLTSchema schema, String namespace, String name, String nameAlias, XSLTElementDef[] elements, XSLTAttributeDef[] attributes, XSLTElementProcessor contentHandler, Class classObject, boolean has_required, boolean required, boolean has_order, int order, boolean multiAllowed) {
/* 175 */     this(schema, namespace, name, nameAlias, elements, attributes, contentHandler, classObject, has_required, required);
/*     */ 
/*     */     
/* 178 */     this.m_order = order;
/* 179 */     this.m_multiAllowed = multiAllowed;
/* 180 */     this.m_isOrdered = has_order;
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
/*     */   XSLTElementDef(XSLTSchema schema, String namespace, String name, String nameAlias, XSLTElementDef[] elements, XSLTAttributeDef[] attributes, XSLTElementProcessor contentHandler, Class classObject, boolean has_order, int order, boolean multiAllowed) {
/* 202 */     this(schema, namespace, name, nameAlias, elements, attributes, contentHandler, classObject, order, multiAllowed);
/*     */ 
/*     */ 
/*     */     
/* 206 */     this.m_isOrdered = has_order;
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
/*     */   XSLTElementDef(XSLTSchema schema, String namespace, String name, String nameAlias, XSLTElementDef[] elements, XSLTAttributeDef[] attributes, XSLTElementProcessor contentHandler, Class classObject, int order, boolean multiAllowed) {
/* 227 */     this(schema, namespace, name, nameAlias, elements, attributes, contentHandler, classObject);
/*     */     
/* 229 */     this.m_order = order;
/* 230 */     this.m_multiAllowed = multiAllowed;
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
/*     */   XSLTElementDef(Class classObject, XSLTElementProcessor contentHandler, int type) {
/* 244 */     this.m_classObject = classObject;
/* 245 */     this.m_type = type;
/*     */     
/* 247 */     setElementProcessor(contentHandler);
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
/*     */   void build(String namespace, String name, String nameAlias, XSLTElementDef[] elements, XSLTAttributeDef[] attributes, XSLTElementProcessor contentHandler, Class classObject) {
/* 266 */     this.m_namespace = namespace;
/* 267 */     this.m_name = name;
/* 268 */     this.m_nameAlias = nameAlias;
/* 269 */     this.m_elements = elements;
/* 270 */     this.m_attributes = attributes;
/*     */     
/* 272 */     setElementProcessor(contentHandler);
/*     */     
/* 274 */     this.m_classObject = classObject;
/*     */     
/* 276 */     if (hasRequired() && this.m_elements != null) {
/*     */       
/* 278 */       int n = this.m_elements.length;
/* 279 */       for (int i = 0; i < n; i++) {
/*     */         
/* 281 */         XSLTElementDef def = this.m_elements[i];
/*     */         
/* 283 */         if (def != null && def.getRequired()) {
/*     */           
/* 285 */           if (this.m_requiredFound == null)
/* 286 */             this.m_requiredFound = new Hashtable(); 
/* 287 */           this.m_requiredFound.put(def.getName(), "xsl:" + def.getName());
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
/*     */   private static boolean equalsMayBeNull(Object obj1, Object obj2) {
/* 305 */     return (obj2 == obj1 || (null != obj1 && null != obj2 && obj2.equals(obj1)));
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
/*     */   private static boolean equalsMayBeNullOrZeroLen(String s1, String s2) {
/* 327 */     int len1 = (s1 == null) ? 0 : s1.length();
/* 328 */     int len2 = (s2 == null) ? 0 : s2.length();
/*     */     
/* 330 */     return (len1 != len2) ? false : ((len1 == 0) ? true : s1.equals(s2));
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
/* 341 */   private int m_type = 1;
/*     */   
/*     */   private String m_namespace;
/*     */   
/*     */   private String m_name;
/*     */   
/*     */   private String m_nameAlias;
/*     */   
/*     */   int getType() {
/* 350 */     return this.m_type;
/*     */   }
/*     */ 
/*     */   
/*     */   private XSLTElementDef[] m_elements;
/*     */   private XSLTAttributeDef[] m_attributes;
/*     */   private XSLTElementProcessor m_elementProcessor;
/*     */   private Class m_classObject;
/*     */   
/*     */   void setType(int t) {
/* 360 */     this.m_type = t;
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
/*     */   String getNamespace() {
/* 375 */     return this.m_namespace;
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
/*     */   String getName() {
/* 390 */     return this.m_name;
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
/*     */   String getNameAlias() {
/* 405 */     return this.m_nameAlias;
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
/*     */   XSLTElementDef[] getElements() {
/* 420 */     return this.m_elements;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setElements(XSLTElementDef[] defs) {
/* 430 */     this.m_elements = defs;
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
/*     */   private boolean QNameEquals(String uri, String localName) {
/* 445 */     return (equalsMayBeNullOrZeroLen(this.m_namespace, uri) && (equalsMayBeNullOrZeroLen(this.m_name, localName) || equalsMayBeNullOrZeroLen(this.m_nameAlias, localName)));
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
/*     */   XSLTElementProcessor getProcessorFor(String uri, String localName) {
/* 462 */     XSLTElementProcessor elemDef = null;
/*     */     
/* 464 */     if (null == this.m_elements) {
/* 465 */       return null;
/*     */     }
/* 467 */     int n = this.m_elements.length;
/* 468 */     int order = -1;
/* 469 */     boolean multiAllowed = true;
/* 470 */     for (int i = 0; i < n; i++) {
/*     */       
/* 472 */       XSLTElementDef def = this.m_elements[i];
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 477 */       if (def.m_name.equals("*")) {
/*     */ 
/*     */ 
/*     */         
/* 481 */         if (!equalsMayBeNullOrZeroLen(uri, "http://www.w3.org/1999/XSL/Transform"))
/*     */         {
/* 483 */           elemDef = def.m_elementProcessor;
/* 484 */           order = def.getOrder();
/* 485 */           multiAllowed = def.getMultiAllowed();
/*     */         }
/*     */       
/* 488 */       } else if (def.QNameEquals(uri, localName)) {
/*     */         
/* 490 */         if (def.getRequired())
/* 491 */           setRequiredFound(def.getName(), true); 
/* 492 */         order = def.getOrder();
/* 493 */         multiAllowed = def.getMultiAllowed();
/* 494 */         elemDef = def.m_elementProcessor;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 499 */     if (elemDef != null && isOrdered()) {
/*     */       
/* 501 */       int lastOrder = getLastOrder();
/* 502 */       if (order > lastOrder)
/* 503 */       { setLastOrder(order); }
/* 504 */       else { if (order == lastOrder && !multiAllowed)
/*     */         {
/* 506 */           return null;
/*     */         }
/* 508 */         if (order < lastOrder && order > 0)
/*     */         {
/* 510 */           return null;
/*     */         } }
/*     */     
/*     */     } 
/* 514 */     return elemDef;
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
/*     */   XSLTElementProcessor getProcessorForUnknown(String uri, String localName) {
/* 531 */     if (null == this.m_elements) {
/* 532 */       return null;
/*     */     }
/* 534 */     int n = this.m_elements.length;
/*     */     
/* 536 */     for (int i = 0; i < n; i++) {
/*     */       
/* 538 */       XSLTElementDef def = this.m_elements[i];
/*     */       
/* 540 */       if (def.m_name.equals("unknown") && uri.length() > 0)
/*     */       {
/* 542 */         return def.m_elementProcessor;
/*     */       }
/*     */     } 
/*     */     
/* 546 */     return null;
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
/*     */   XSLTAttributeDef[] getAttributes() {
/* 561 */     return this.m_attributes;
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
/*     */   XSLTAttributeDef getAttributeDef(String uri, String localName) {
/* 576 */     XSLTAttributeDef defaultDef = null;
/* 577 */     XSLTAttributeDef[] attrDefs = getAttributes();
/* 578 */     int nAttrDefs = attrDefs.length;
/*     */     
/* 580 */     for (int k = 0; k < nAttrDefs; k++) {
/*     */       
/* 582 */       XSLTAttributeDef attrDef = attrDefs[k];
/* 583 */       String uriDef = attrDef.getNamespace();
/* 584 */       String nameDef = attrDef.getName();
/*     */       
/* 586 */       if (nameDef.equals("*") && (equalsMayBeNullOrZeroLen(uri, uriDef) || (uriDef != null && uriDef.equals("*") && uri != null && uri.length() > 0)))
/*     */       {
/*     */         
/* 589 */         return attrDef;
/*     */       }
/* 591 */       if (nameDef.equals("*") && uriDef == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 596 */         defaultDef = attrDef;
/*     */       }
/* 598 */       else if (equalsMayBeNullOrZeroLen(uri, uriDef) && localName.equals(nameDef)) {
/*     */ 
/*     */         
/* 601 */         return attrDef;
/*     */       } 
/*     */     } 
/*     */     
/* 605 */     if (null == defaultDef)
/*     */     {
/* 607 */       if (uri.length() > 0 && !equalsMayBeNullOrZeroLen(uri, "http://www.w3.org/1999/XSL/Transform"))
/*     */       {
/* 609 */         return XSLTAttributeDef.m_foreignAttr;
/*     */       }
/*     */     }
/*     */     
/* 613 */     return defaultDef;
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
/*     */   XSLTElementProcessor getElementProcessor() {
/* 628 */     return this.m_elementProcessor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setElementProcessor(XSLTElementProcessor handler) {
/* 639 */     if (handler != null) {
/*     */       
/* 641 */       this.m_elementProcessor = handler;
/*     */       
/* 643 */       this.m_elementProcessor.setElemDef(this);
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
/*     */   Class getClassObject() {
/* 661 */     return this.m_classObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m_has_required = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean hasRequired() {
/* 676 */     return this.m_has_required;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m_required = false;
/*     */ 
/*     */ 
/*     */   
/*     */   Hashtable m_requiredFound;
/*     */ 
/*     */ 
/*     */   
/*     */   boolean getRequired() {
/* 691 */     return this.m_required;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setRequiredFound(String elem, boolean found) {
/* 702 */     if (this.m_requiredFound.get(elem) != null) {
/* 703 */       this.m_requiredFound.remove(elem);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean getRequiredFound() {
/* 713 */     if (this.m_requiredFound == null)
/* 714 */       return true; 
/* 715 */     return this.m_requiredFound.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getRequiredElem() {
/* 725 */     if (this.m_requiredFound == null)
/* 726 */       return null; 
/* 727 */     Enumeration elems = this.m_requiredFound.elements();
/* 728 */     String s = "";
/* 729 */     boolean first = true;
/* 730 */     while (elems.hasMoreElements()) {
/*     */       
/* 732 */       if (first) {
/* 733 */         first = false;
/*     */       } else {
/* 735 */         s = s + ", ";
/* 736 */       }  s = s + (String)elems.nextElement();
/*     */     } 
/* 738 */     return s;
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
/*     */   boolean m_isOrdered = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isOrdered() {
/* 770 */     return this.m_isOrdered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 776 */   private int m_order = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getOrder() {
/* 785 */     return this.m_order;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 792 */   private int m_lastOrder = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getLastOrder() {
/* 801 */     return this.m_lastOrder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setLastOrder(int order) {
/* 811 */     this.m_lastOrder = order;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m_multiAllowed = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean getMultiAllowed() {
/* 826 */     return this.m_multiAllowed;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/XSLTElementDef.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */