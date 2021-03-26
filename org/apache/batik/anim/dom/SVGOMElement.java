/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import org.apache.batik.anim.values.AnimatableNumberOptionalNumberValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSNavigableNode;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.value.ShorthandManager;
/*     */ import org.apache.batik.css.engine.value.ValueManager;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.AbstractStylableDocument;
/*     */ import org.apache.batik.dom.svg.ExtendedTraitAccess;
/*     */ import org.apache.batik.dom.svg.LiveAttributeValue;
/*     */ import org.apache.batik.dom.svg.SVGContext;
/*     */ import org.apache.batik.dom.svg.SVGOMException;
/*     */ import org.apache.batik.dom.util.DOMUtilities;
/*     */ import org.apache.batik.parser.UnitProcessor;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedInteger;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumber;
/*     */ import org.w3c.dom.svg.SVGElement;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ import org.w3c.dom.svg.SVGSVGElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SVGOMElement
/*     */   extends AbstractElement
/*     */   implements AnimationTarget, ExtendedTraitAccess, SVGElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected transient boolean readonly;
/*     */   protected String prefix;
/*     */   protected transient SVGContext svgContext;
/*     */   protected DoublyIndexedTable targetListeners;
/*     */   protected UnitProcessor.Context unitContext;
/*     */   
/*     */   static {
/*  71 */     DoublyIndexedTable t = new DoublyIndexedTable();
/*  72 */     t.put(null, "id", new TraitInformation(false, 16));
/*     */     
/*  74 */     t.put("http://www.w3.org/XML/1998/namespace", "base", new TraitInformation(false, 10));
/*     */     
/*  76 */     t.put("http://www.w3.org/XML/1998/namespace", "space", new TraitInformation(false, 15));
/*     */     
/*  78 */     t.put("http://www.w3.org/XML/1998/namespace", "id", new TraitInformation(false, 16));
/*     */     
/*  80 */     t.put("http://www.w3.org/XML/1998/namespace", "lang", new TraitInformation(false, 45));
/*     */     
/*  82 */     xmlTraitInformation = t;
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
/*     */   protected SVGOMElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMElement(String prefix, AbstractDocument owner) {
/* 123 */     super(prefix, owner);
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
/*     */   protected void initializeAllLiveAttributes() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/* 147 */     if (((SVGOMDocument)this.ownerDocument).isSVG12) {
/* 148 */       Attr a = getAttributeNodeNS("http://www.w3.org/XML/1998/namespace", "id");
/* 149 */       if (a != null) {
/* 150 */         return a.getNodeValue();
/*     */       }
/*     */     } 
/* 153 */     return getAttributeNS(null, "id");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/* 160 */     if (((SVGOMDocument)this.ownerDocument).isSVG12) {
/* 161 */       setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:id", id);
/* 162 */       Attr a = getAttributeNodeNS(null, "id");
/* 163 */       if (a != null) {
/* 164 */         a.setNodeValue(id);
/*     */       }
/*     */     } else {
/* 167 */       setAttributeNS(null, "id", id);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLbase() {
/* 175 */     return getAttributeNS("http://www.w3.org/XML/1998/namespace", "base");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLbase(String xmlbase) throws DOMException {
/* 182 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:base", xmlbase);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGSVGElement getOwnerSVGElement() {
/* 189 */     CSSStylableElement cSSStylableElement = CSSEngine.getParentCSSStylableElement((Element)this);
/* 190 */     for (; cSSStylableElement != null; 
/* 191 */       cSSStylableElement = CSSEngine.getParentCSSStylableElement((Element)cSSStylableElement)) {
/* 192 */       if (cSSStylableElement instanceof SVGSVGElement) {
/* 193 */         return (SVGSVGElement)cSSStylableElement;
/*     */       }
/*     */     } 
/* 196 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGElement getViewportElement() {
/* 203 */     CSSStylableElement cSSStylableElement = CSSEngine.getParentCSSStylableElement((Element)this);
/* 204 */     for (; cSSStylableElement != null; 
/* 205 */       cSSStylableElement = CSSEngine.getParentCSSStylableElement((Element)cSSStylableElement)) {
/* 206 */       if (cSSStylableElement instanceof org.w3c.dom.svg.SVGFitToViewBox) {
/* 207 */         return (SVGElement)cSSStylableElement;
/*     */       }
/*     */     } 
/* 210 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/* 217 */     if (this.prefix == null || this.prefix.equals("")) {
/* 218 */       return getLocalName();
/*     */     }
/*     */     
/* 221 */     return this.prefix + ':' + getLocalName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/* 228 */     return "http://www.w3.org/2000/svg";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrefix(String prefix) throws DOMException {
/* 235 */     if (isReadonly()) {
/* 236 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 241 */     if (prefix != null && !prefix.equals("") && !DOMUtilities.isValidName(prefix))
/*     */     {
/*     */       
/* 244 */       throw createDOMException((short)5, "prefix", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), prefix });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 250 */     this.prefix = prefix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getCascadedXMLBase(Node node) {
/* 259 */     String base = null;
/* 260 */     Node n = node.getParentNode();
/* 261 */     while (n != null) {
/* 262 */       if (n.getNodeType() == 1) {
/* 263 */         base = getCascadedXMLBase(n);
/*     */         break;
/*     */       } 
/* 266 */       if (n instanceof CSSNavigableNode) {
/* 267 */         n = ((CSSNavigableNode)n).getCSSParentNode(); continue;
/*     */       } 
/* 269 */       n = n.getParentNode();
/*     */     } 
/*     */     
/* 272 */     if (base == null) {
/*     */       AbstractDocument doc;
/* 274 */       if (node.getNodeType() == 9) {
/* 275 */         doc = (AbstractDocument)node;
/*     */       } else {
/* 277 */         doc = (AbstractDocument)node.getOwnerDocument();
/*     */       } 
/* 279 */       base = doc.getDocumentURI();
/*     */     } 
/* 281 */     while (node != null && node.getNodeType() != 1) {
/* 282 */       node = node.getParentNode();
/*     */     }
/* 284 */     if (node == null) {
/* 285 */       return base;
/*     */     }
/* 287 */     Element e = (Element)node;
/* 288 */     Attr attr = e.getAttributeNodeNS("http://www.w3.org/XML/1998/namespace", "base");
/* 289 */     if (attr != null) {
/* 290 */       if (base == null) {
/* 291 */         base = attr.getNodeValue();
/*     */       } else {
/* 293 */         base = (new ParsedURL(base, attr.getNodeValue())).toString();
/*     */       } 
/*     */     }
/* 296 */     return base;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSVGContext(SVGContext ctx) {
/* 307 */     this.svgContext = ctx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGContext getSVGContext() {
/* 314 */     return this.svgContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGException createSVGException(short type, String key, Object[] args) {
/*     */     try {
/* 326 */       return (SVGException)new SVGOMException(type, getCurrentDocument().formatMessage(key, args));
/*     */     }
/* 328 */     catch (Exception e) {
/* 329 */       return (SVGException)new SVGOMException(type, key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadonly() {
/* 337 */     return this.readonly;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReadonly(boolean v) {
/* 344 */     this.readonly = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 351 */     return xmlTraitInformation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedTransformList createLiveAnimatedTransformList(String ns, String ln, String def) {
/* 360 */     SVGOMAnimatedTransformList v = new SVGOMAnimatedTransformList(this, ns, ln, def);
/*     */     
/* 362 */     this.liveAttributeValues.put(ns, ln, v);
/* 363 */     v.addAnimatedAttributeListener(((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener());
/*     */     
/* 365 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedBoolean createLiveAnimatedBoolean(String ns, String ln, boolean def) {
/* 374 */     SVGOMAnimatedBoolean v = new SVGOMAnimatedBoolean(this, ns, ln, def);
/*     */     
/* 376 */     this.liveAttributeValues.put(ns, ln, v);
/* 377 */     v.addAnimatedAttributeListener(((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener());
/*     */     
/* 379 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedString createLiveAnimatedString(String ns, String ln) {
/* 388 */     SVGOMAnimatedString v = new SVGOMAnimatedString(this, ns, ln);
/*     */     
/* 390 */     this.liveAttributeValues.put(ns, ln, v);
/* 391 */     v.addAnimatedAttributeListener(((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener());
/*     */     
/* 393 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedPreserveAspectRatio createLiveAnimatedPreserveAspectRatio() {
/* 402 */     SVGOMAnimatedPreserveAspectRatio v = new SVGOMAnimatedPreserveAspectRatio(this);
/*     */     
/* 404 */     this.liveAttributeValues.put(null, "preserveAspectRatio", v);
/* 405 */     v.addAnimatedAttributeListener(((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener());
/*     */     
/* 407 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedMarkerOrientValue createLiveAnimatedMarkerOrientValue(String ns, String ln) {
/* 416 */     SVGOMAnimatedMarkerOrientValue v = new SVGOMAnimatedMarkerOrientValue(this, ns, ln);
/*     */     
/* 418 */     this.liveAttributeValues.put(ns, ln, v);
/* 419 */     v.addAnimatedAttributeListener(((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener());
/*     */     
/* 421 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedPathData createLiveAnimatedPathData(String ns, String ln, String def) {
/* 430 */     SVGOMAnimatedPathData v = new SVGOMAnimatedPathData(this, ns, ln, def);
/*     */     
/* 432 */     this.liveAttributeValues.put(ns, ln, v);
/* 433 */     v.addAnimatedAttributeListener(((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener());
/*     */     
/* 435 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedNumber createLiveAnimatedNumber(String ns, String ln, float def) {
/* 444 */     return createLiveAnimatedNumber(ns, ln, def, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedNumber createLiveAnimatedNumber(String ns, String ln, float def, boolean allowPercentage) {
/* 453 */     SVGOMAnimatedNumber v = new SVGOMAnimatedNumber(this, ns, ln, def, allowPercentage);
/*     */     
/* 455 */     this.liveAttributeValues.put(ns, ln, v);
/* 456 */     v.addAnimatedAttributeListener(((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener());
/*     */     
/* 458 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedNumberList createLiveAnimatedNumberList(String ns, String ln, String def, boolean canEmpty) {
/* 467 */     SVGOMAnimatedNumberList v = new SVGOMAnimatedNumberList(this, ns, ln, def, canEmpty);
/*     */     
/* 469 */     this.liveAttributeValues.put(ns, ln, v);
/* 470 */     v.addAnimatedAttributeListener(((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener());
/*     */     
/* 472 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedPoints createLiveAnimatedPoints(String ns, String ln, String def) {
/* 481 */     SVGOMAnimatedPoints v = new SVGOMAnimatedPoints(this, ns, ln, def);
/*     */     
/* 483 */     this.liveAttributeValues.put(ns, ln, v);
/* 484 */     v.addAnimatedAttributeListener(((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener());
/*     */     
/* 486 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLengthList createLiveAnimatedLengthList(String ns, String ln, String def, boolean emptyAllowed, short dir) {
/* 496 */     SVGOMAnimatedLengthList v = new SVGOMAnimatedLengthList(this, ns, ln, def, emptyAllowed, dir);
/*     */     
/* 498 */     this.liveAttributeValues.put(ns, ln, v);
/* 499 */     v.addAnimatedAttributeListener(((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener());
/*     */     
/* 501 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedInteger createLiveAnimatedInteger(String ns, String ln, int def) {
/* 510 */     SVGOMAnimatedInteger v = new SVGOMAnimatedInteger(this, ns, ln, def);
/*     */     
/* 512 */     this.liveAttributeValues.put(ns, ln, v);
/* 513 */     v.addAnimatedAttributeListener(((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener());
/*     */     
/* 515 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration createLiveAnimatedEnumeration(String ns, String ln, String[] val, short def) {
/* 524 */     SVGOMAnimatedEnumeration v = new SVGOMAnimatedEnumeration(this, ns, ln, val, def);
/*     */     
/* 526 */     this.liveAttributeValues.put(ns, ln, v);
/* 527 */     v.addAnimatedAttributeListener(((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener());
/*     */     
/* 529 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength createLiveAnimatedLength(String ns, String ln, String val, short dir, boolean nonneg) {
/* 538 */     SVGOMAnimatedLength v = new SVGOMAnimatedLength(this, ns, ln, val, dir, nonneg);
/*     */     
/* 540 */     this.liveAttributeValues.put(ns, ln, v);
/* 541 */     v.addAnimatedAttributeListener(((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener());
/*     */     
/* 543 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedRect createLiveAnimatedRect(String ns, String ln, String value) {
/* 552 */     SVGOMAnimatedRect v = new SVGOMAnimatedRect(this, ns, ln, value);
/* 553 */     this.liveAttributeValues.put(ns, ln, v);
/* 554 */     v.addAnimatedAttributeListener(((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener());
/*     */     
/* 556 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasProperty(String pn) {
/* 565 */     AbstractStylableDocument doc = (AbstractStylableDocument)this.ownerDocument;
/* 566 */     CSSEngine eng = doc.getCSSEngine();
/* 567 */     return (eng.getPropertyIndex(pn) != -1 || eng.getShorthandIndex(pn) != -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTrait(String ns, String ln) {
/* 576 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPropertyAnimatable(String pn) {
/* 583 */     AbstractStylableDocument doc = (AbstractStylableDocument)this.ownerDocument;
/* 584 */     CSSEngine eng = doc.getCSSEngine();
/* 585 */     int idx = eng.getPropertyIndex(pn);
/* 586 */     if (idx != -1) {
/* 587 */       ValueManager[] vms = eng.getValueManagers();
/* 588 */       return vms[idx].isAnimatableProperty();
/*     */     } 
/* 590 */     idx = eng.getShorthandIndex(pn);
/* 591 */     if (idx != -1) {
/* 592 */       ShorthandManager[] sms = eng.getShorthandManagers();
/* 593 */       return sms[idx].isAnimatableProperty();
/*     */     } 
/* 595 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isAttributeAnimatable(String ns, String ln) {
/* 602 */     DoublyIndexedTable t = getTraitInformationTable();
/* 603 */     TraitInformation ti = (TraitInformation)t.get(ns, ln);
/* 604 */     if (ti != null) {
/* 605 */       return ti.isAnimatable();
/*     */     }
/* 607 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPropertyAdditive(String pn) {
/* 614 */     AbstractStylableDocument doc = (AbstractStylableDocument)this.ownerDocument;
/* 615 */     CSSEngine eng = doc.getCSSEngine();
/* 616 */     int idx = eng.getPropertyIndex(pn);
/* 617 */     if (idx != -1) {
/* 618 */       ValueManager[] vms = eng.getValueManagers();
/* 619 */       return vms[idx].isAdditiveProperty();
/*     */     } 
/* 621 */     idx = eng.getShorthandIndex(pn);
/* 622 */     if (idx != -1) {
/* 623 */       ShorthandManager[] sms = eng.getShorthandManagers();
/* 624 */       return sms[idx].isAdditiveProperty();
/*     */     } 
/* 626 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAttributeAdditive(String ns, String ln) {
/* 633 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTraitAnimatable(String ns, String tn) {
/* 641 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTraitAdditive(String ns, String tn) {
/* 649 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType(String pn) {
/* 656 */     AbstractStylableDocument doc = (AbstractStylableDocument)this.ownerDocument;
/*     */     
/* 658 */     CSSEngine eng = doc.getCSSEngine();
/* 659 */     int idx = eng.getPropertyIndex(pn);
/* 660 */     if (idx != -1) {
/* 661 */       ValueManager[] vms = eng.getValueManagers();
/* 662 */       return vms[idx].getPropertyType();
/*     */     } 
/* 664 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getAttributeType(String ns, String ln) {
/* 671 */     DoublyIndexedTable t = getTraitInformationTable();
/* 672 */     TraitInformation ti = (TraitInformation)t.get(ns, ln);
/* 673 */     if (ti != null) {
/* 674 */       return ti.getType();
/*     */     }
/* 676 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getElement() {
/* 685 */     return (Element)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updatePropertyValue(String pn, AnimatableValue val) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateAttributeValue(String ns, String ln, AnimatableValue val) {
/* 701 */     LiveAttributeValue a = getLiveAttributeValue(ns, ln);
/* 702 */     ((AbstractSVGAnimatedValue)a).updateAnimatedValue(val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateOtherValue(String type, AnimatableValue val) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getUnderlyingValue(String ns, String ln) {
/* 716 */     LiveAttributeValue a = getLiveAttributeValue(ns, ln);
/* 717 */     if (!(a instanceof AnimatedLiveAttributeValue)) {
/* 718 */       return null;
/*     */     }
/* 720 */     return ((AnimatedLiveAttributeValue)a).getUnderlyingValue(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimatableValue getBaseValue(SVGAnimatedInteger n, SVGAnimatedInteger on) {
/* 730 */     return (AnimatableValue)new AnimatableNumberOptionalNumberValue(this, n.getBaseVal(), on.getBaseVal());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimatableValue getBaseValue(SVGAnimatedNumber n, SVGAnimatedNumber on) {
/* 740 */     return (AnimatableValue)new AnimatableNumberOptionalNumberValue(this, n.getBaseVal(), on.getBaseVal());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getPercentageInterpretation(String ns, String an, boolean isCSS) {
/* 750 */     if ((isCSS || ns == null) && (
/* 751 */       an.equals("baseline-shift") || an.equals("font-size")))
/*     */     {
/* 753 */       return 0;
/*     */     }
/*     */     
/* 756 */     if (!isCSS) {
/* 757 */       DoublyIndexedTable t = getTraitInformationTable();
/* 758 */       TraitInformation ti = (TraitInformation)t.get(ns, an);
/* 759 */       if (ti != null) {
/* 760 */         return ti.getPercentageInterpretation();
/*     */       }
/* 762 */       return 3;
/*     */     } 
/*     */     
/* 765 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final short getAttributePercentageInterpretation(String ns, String ln) {
/* 772 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useLinearRGBColorInterpolation() {
/* 781 */     return false;
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
/*     */   public float svgToUserSpace(float v, short type, short pcInterp) {
/* 794 */     if (this.unitContext == null) {
/* 795 */       this.unitContext = new UnitContext();
/*     */     }
/* 797 */     if (pcInterp == 0 && type == 2)
/*     */     {
/*     */       
/* 800 */       return 0.0F;
/*     */     }
/* 802 */     return UnitProcessor.svgToUserSpace(v, type, (short)(3 - pcInterp), this.unitContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTargetListener(String ns, String an, boolean isCSS, AnimationTargetListener l) {
/* 812 */     if (!isCSS) {
/* 813 */       if (this.targetListeners == null) {
/* 814 */         this.targetListeners = new DoublyIndexedTable();
/*     */       }
/* 816 */       LinkedList<AnimationTargetListener> ll = (LinkedList)this.targetListeners.get(ns, an);
/* 817 */       if (ll == null) {
/* 818 */         ll = new LinkedList();
/* 819 */         this.targetListeners.put(ns, an, ll);
/*     */       } 
/* 821 */       ll.add(l);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTargetListener(String ns, String an, boolean isCSS, AnimationTargetListener l) {
/* 830 */     if (!isCSS) {
/* 831 */       LinkedList ll = (LinkedList)this.targetListeners.get(ns, an);
/* 832 */       ll.remove(l);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void fireBaseAttributeListeners(String ns, String ln) {
/* 841 */     if (this.targetListeners != null) {
/* 842 */       LinkedList ll = (LinkedList)this.targetListeners.get(ns, ln);
/* 843 */       for (Object aLl : ll) {
/* 844 */         AnimationTargetListener l = (AnimationTargetListener)aLl;
/* 845 */         l.baseValueChanged(this, ns, ln, false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node export(Node n, AbstractDocument d) {
/* 856 */     super.export(n, d);
/* 857 */     SVGOMElement e = (SVGOMElement)n;
/* 858 */     e.prefix = this.prefix;
/* 859 */     e.initializeAllLiveAttributes();
/* 860 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/* 867 */     super.deepExport(n, d);
/* 868 */     SVGOMElement e = (SVGOMElement)n;
/* 869 */     e.prefix = this.prefix;
/* 870 */     e.initializeAllLiveAttributes();
/* 871 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node copyInto(Node n) {
/* 879 */     super.copyInto(n);
/* 880 */     SVGOMElement e = (SVGOMElement)n;
/* 881 */     e.prefix = this.prefix;
/* 882 */     e.initializeAllLiveAttributes();
/* 883 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 891 */     super.deepCopyInto(n);
/* 892 */     SVGOMElement e = (SVGOMElement)n;
/* 893 */     e.prefix = this.prefix;
/* 894 */     e.initializeAllLiveAttributes();
/* 895 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class UnitContext
/*     */     implements UnitProcessor.Context
/*     */   {
/*     */     public Element getElement() {
/* 907 */       return (Element)SVGOMElement.this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getPixelUnitToMillimeter() {
/* 914 */       return SVGOMElement.this.getSVGContext().getPixelUnitToMillimeter();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getPixelToMM() {
/* 923 */       return getPixelUnitToMillimeter();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getFontSize() {
/* 930 */       return SVGOMElement.this.getSVGContext().getFontSize();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getXHeight() {
/* 937 */       return 0.5F;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getViewportWidth() {
/* 944 */       return SVGOMElement.this.getSVGContext().getViewportWidth();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getViewportHeight() {
/* 951 */       return SVGOMElement.this.getSVGContext().getViewportHeight();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */