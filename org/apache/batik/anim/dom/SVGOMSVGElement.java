/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.List;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.svg.AbstractSVGMatrix;
/*     */ import org.apache.batik.dom.svg.SVGContext;
/*     */ import org.apache.batik.dom.svg.SVGOMAngle;
/*     */ import org.apache.batik.dom.svg.SVGOMPoint;
/*     */ import org.apache.batik.dom.svg.SVGOMRect;
/*     */ import org.apache.batik.dom.svg.SVGOMTransform;
/*     */ import org.apache.batik.dom.svg.SVGSVGContext;
/*     */ import org.apache.batik.dom.svg.SVGTestsSupport;
/*     */ import org.apache.batik.dom.svg.SVGZoomAndPanSupport;
/*     */ import org.apache.batik.dom.util.ListNodeList;
/*     */ import org.apache.batik.dom.util.XMLSupport;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.css.CSSStyleDeclaration;
/*     */ import org.w3c.dom.css.DocumentCSS;
/*     */ import org.w3c.dom.css.ViewCSS;
/*     */ import org.w3c.dom.events.DocumentEvent;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.stylesheets.DocumentStyle;
/*     */ import org.w3c.dom.stylesheets.StyleSheetList;
/*     */ import org.w3c.dom.svg.SVGAngle;
/*     */ import org.w3c.dom.svg.SVGAnimatedBoolean;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGAnimatedPreserveAspectRatio;
/*     */ import org.w3c.dom.svg.SVGAnimatedRect;
/*     */ import org.w3c.dom.svg.SVGElement;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ import org.w3c.dom.svg.SVGLength;
/*     */ import org.w3c.dom.svg.SVGMatrix;
/*     */ import org.w3c.dom.svg.SVGNumber;
/*     */ import org.w3c.dom.svg.SVGPoint;
/*     */ import org.w3c.dom.svg.SVGRect;
/*     */ import org.w3c.dom.svg.SVGSVGElement;
/*     */ import org.w3c.dom.svg.SVGStringList;
/*     */ import org.w3c.dom.svg.SVGTransform;
/*     */ import org.w3c.dom.svg.SVGViewSpec;
/*     */ import org.w3c.dom.views.AbstractView;
/*     */ import org.w3c.dom.views.DocumentView;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMSVGElement
/*     */   extends SVGStylableElement
/*     */   implements SVGSVGElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  85 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGStylableElement.xmlTraitInformation);
/*     */     
/*  87 */     t.put(null, "x", new TraitInformation(true, 3, (short)1));
/*     */     
/*  89 */     t.put(null, "y", new TraitInformation(true, 3, (short)2));
/*     */     
/*  91 */     t.put(null, "width", new TraitInformation(true, 3, (short)1));
/*     */     
/*  93 */     t.put(null, "height", new TraitInformation(true, 3, (short)2));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     t.put(null, "preserveAspectRatio", new TraitInformation(true, 32));
/*     */     
/* 105 */     t.put(null, "viewBox", new TraitInformation(true, 50));
/*     */     
/* 107 */     t.put(null, "externalResourcesRequired", new TraitInformation(true, 49));
/*     */     
/* 109 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   protected static final AttributeInitializer attributeInitializer = new AttributeInitializer(7); static {
/* 118 */     attributeInitializer.addAttribute("http://www.w3.org/2000/xmlns/", null, "xmlns", "http://www.w3.org/2000/svg");
/*     */ 
/*     */ 
/*     */     
/* 122 */     attributeInitializer.addAttribute("http://www.w3.org/2000/xmlns/", "xmlns", "xlink", "http://www.w3.org/1999/xlink");
/*     */ 
/*     */ 
/*     */     
/* 126 */     attributeInitializer.addAttribute(null, null, "preserveAspectRatio", "xMidYMid meet");
/*     */ 
/*     */ 
/*     */     
/* 130 */     attributeInitializer.addAttribute(null, null, "zoomAndPan", "magnify");
/*     */ 
/*     */ 
/*     */     
/* 134 */     attributeInitializer.addAttribute(null, null, "version", "1.0");
/*     */ 
/*     */ 
/*     */     
/* 138 */     attributeInitializer.addAttribute(null, null, "contentScriptType", "text/ecmascript");
/*     */ 
/*     */ 
/*     */     
/* 142 */     attributeInitializer.addAttribute(null, null, "contentStyleType", "text/css");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength x;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength y;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength width;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength height;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedBoolean externalResourcesRequired;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedPreserveAspectRatio preserveAspectRatio;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedRect viewBox;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMSVGElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMSVGElement(String prefix, AbstractDocument owner) {
/* 195 */     super(prefix, owner);
/* 196 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 203 */     super.initializeAllLiveAttributes();
/* 204 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 211 */     this.x = createLiveAnimatedLength((String)null, "x", "0", (short)2, false);
/*     */ 
/*     */     
/* 214 */     this.y = createLiveAnimatedLength((String)null, "y", "0", (short)1, false);
/*     */ 
/*     */     
/* 217 */     this.width = createLiveAnimatedLength((String)null, "width", "100%", (short)2, true);
/*     */ 
/*     */ 
/*     */     
/* 221 */     this.height = createLiveAnimatedLength((String)null, "height", "100%", (short)1, true);
/*     */ 
/*     */ 
/*     */     
/* 225 */     this.externalResourcesRequired = createLiveAnimatedBoolean((String)null, "externalResourcesRequired", false);
/*     */ 
/*     */     
/* 228 */     this.preserveAspectRatio = createLiveAnimatedPreserveAspectRatio();
/* 229 */     this.viewBox = createLiveAnimatedRect((String)null, "viewBox", (String)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 236 */     return "svg";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getX() {
/* 243 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getY() {
/* 250 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getWidth() {
/* 257 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getHeight() {
/* 264 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContentScriptType() {
/* 271 */     return getAttributeNS(null, "contentScriptType");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContentScriptType(String type) {
/* 278 */     setAttributeNS(null, "contentScriptType", type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContentStyleType() {
/* 285 */     return getAttributeNS(null, "contentStyleType");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContentStyleType(String type) {
/* 292 */     setAttributeNS(null, "contentStyleType", type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGRect getViewport() {
/* 299 */     SVGContext ctx = getSVGContext();
/* 300 */     return (SVGRect)new SVGOMRect(0.0F, 0.0F, ctx.getViewportWidth(), ctx.getViewportHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelUnitToMillimeterX() {
/* 308 */     return getSVGContext().getPixelUnitToMillimeter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelUnitToMillimeterY() {
/* 315 */     return getSVGContext().getPixelUnitToMillimeter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getScreenPixelToMillimeterX() {
/* 323 */     return getSVGContext().getPixelUnitToMillimeter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getScreenPixelToMillimeterY() {
/* 331 */     return getSVGContext().getPixelUnitToMillimeter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getUseCurrentView() {
/* 338 */     throw new UnsupportedOperationException("SVGSVGElement.getUseCurrentView is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUseCurrentView(boolean useCurrentView) throws DOMException {
/* 346 */     throw new UnsupportedOperationException("SVGSVGElement.setUseCurrentView is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGViewSpec getCurrentView() {
/* 354 */     throw new UnsupportedOperationException("SVGSVGElement.getCurrentView is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCurrentScale() {
/* 362 */     AffineTransform scrnTrans = getSVGContext().getScreenTransform();
/* 363 */     if (scrnTrans != null) {
/* 364 */       return (float)Math.sqrt(scrnTrans.getDeterminant());
/*     */     }
/* 366 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentScale(float currentScale) throws DOMException {
/* 373 */     SVGContext context = getSVGContext();
/* 374 */     AffineTransform scrnTrans = context.getScreenTransform();
/* 375 */     float scale = 1.0F;
/* 376 */     if (scrnTrans != null) {
/* 377 */       scale = (float)Math.sqrt(scrnTrans.getDeterminant());
/*     */     }
/* 379 */     float delta = currentScale / scale;
/*     */ 
/*     */     
/* 382 */     scrnTrans = new AffineTransform(scrnTrans.getScaleX() * delta, scrnTrans.getShearY() * delta, scrnTrans.getShearX() * delta, scrnTrans.getScaleY() * delta, scrnTrans.getTranslateX(), scrnTrans.getTranslateY());
/*     */ 
/*     */ 
/*     */     
/* 386 */     context.setScreenTransform(scrnTrans);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPoint getCurrentTranslate() {
/* 393 */     return new SVGPoint() {
/*     */         protected AffineTransform getScreenTransform() {
/* 395 */           SVGContext context = SVGOMSVGElement.this.getSVGContext();
/* 396 */           return context.getScreenTransform();
/*     */         }
/*     */         public float getX() {
/* 399 */           AffineTransform scrnTrans = getScreenTransform();
/* 400 */           return (float)scrnTrans.getTranslateX();
/*     */         }
/*     */         public float getY() {
/* 403 */           AffineTransform scrnTrans = getScreenTransform();
/* 404 */           return (float)scrnTrans.getTranslateY();
/*     */         }
/*     */         public void setX(float newX) {
/* 407 */           SVGContext context = SVGOMSVGElement.this.getSVGContext();
/* 408 */           AffineTransform scrnTrans = context.getScreenTransform();
/* 409 */           scrnTrans = new AffineTransform(scrnTrans.getScaleX(), scrnTrans.getShearY(), scrnTrans.getShearX(), scrnTrans.getScaleY(), newX, scrnTrans.getTranslateY());
/*     */ 
/*     */ 
/*     */           
/* 413 */           context.setScreenTransform(scrnTrans);
/*     */         }
/*     */         public void setY(float newY) {
/* 416 */           SVGContext context = SVGOMSVGElement.this.getSVGContext();
/* 417 */           AffineTransform scrnTrans = context.getScreenTransform();
/* 418 */           scrnTrans = new AffineTransform(scrnTrans.getScaleX(), scrnTrans.getShearY(), scrnTrans.getShearX(), scrnTrans.getScaleY(), scrnTrans.getTranslateX(), newY);
/*     */ 
/*     */ 
/*     */           
/* 422 */           context.setScreenTransform(scrnTrans);
/*     */         }
/*     */         public SVGPoint matrixTransform(SVGMatrix mat) {
/* 425 */           AffineTransform scrnTrans = getScreenTransform();
/* 426 */           float x = (float)scrnTrans.getTranslateX();
/* 427 */           float y = (float)scrnTrans.getTranslateY();
/* 428 */           float newX = mat.getA() * x + mat.getC() * y + mat.getE();
/* 429 */           float newY = mat.getB() * x + mat.getD() * y + mat.getF();
/* 430 */           return (SVGPoint)new SVGOMPoint(newX, newY);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int suspendRedraw(int max_wait_milliseconds) {
/* 439 */     if (max_wait_milliseconds > 60000) {
/* 440 */       max_wait_milliseconds = 60000;
/* 441 */     } else if (max_wait_milliseconds < 0) {
/* 442 */       max_wait_milliseconds = 0;
/*     */     } 
/* 444 */     SVGSVGContext ctx = (SVGSVGContext)getSVGContext();
/* 445 */     return ctx.suspendRedraw(max_wait_milliseconds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsuspendRedraw(int suspend_handle_id) throws DOMException {
/* 452 */     SVGSVGContext ctx = (SVGSVGContext)getSVGContext();
/* 453 */     if (!ctx.unsuspendRedraw(suspend_handle_id)) {
/* 454 */       throw createDOMException((short)8, "invalid.suspend.handle", new Object[] { Integer.valueOf(suspend_handle_id) });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsuspendRedrawAll() {
/* 464 */     SVGSVGContext ctx = (SVGSVGContext)getSVGContext();
/* 465 */     ctx.unsuspendRedrawAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void forceRedraw() {
/* 472 */     SVGSVGContext ctx = (SVGSVGContext)getSVGContext();
/* 473 */     ctx.forceRedraw();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pauseAnimations() {
/* 480 */     SVGSVGContext ctx = (SVGSVGContext)getSVGContext();
/* 481 */     ctx.pauseAnimations();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unpauseAnimations() {
/* 488 */     SVGSVGContext ctx = (SVGSVGContext)getSVGContext();
/* 489 */     ctx.unpauseAnimations();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean animationsPaused() {
/* 496 */     SVGSVGContext ctx = (SVGSVGContext)getSVGContext();
/* 497 */     return ctx.animationsPaused();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCurrentTime() {
/* 504 */     SVGSVGContext ctx = (SVGSVGContext)getSVGContext();
/* 505 */     return ctx.getCurrentTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentTime(float seconds) {
/* 512 */     SVGSVGContext ctx = (SVGSVGContext)getSVGContext();
/* 513 */     ctx.setCurrentTime(seconds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeList getIntersectionList(SVGRect rect, SVGElement referenceElement) {
/* 522 */     SVGSVGContext ctx = (SVGSVGContext)getSVGContext();
/* 523 */     List list = ctx.getIntersectionList(rect, (Element)referenceElement);
/* 524 */     return (NodeList)new ListNodeList(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeList getEnclosureList(SVGRect rect, SVGElement referenceElement) {
/* 533 */     SVGSVGContext ctx = (SVGSVGContext)getSVGContext();
/* 534 */     List list = ctx.getEnclosureList(rect, (Element)referenceElement);
/* 535 */     return (NodeList)new ListNodeList(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkIntersection(SVGElement element, SVGRect rect) {
/* 543 */     SVGSVGContext ctx = (SVGSVGContext)getSVGContext();
/* 544 */     return ctx.checkIntersection((Element)element, rect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkEnclosure(SVGElement element, SVGRect rect) {
/* 552 */     SVGSVGContext ctx = (SVGSVGContext)getSVGContext();
/* 553 */     return ctx.checkEnclosure((Element)element, rect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deselectAll() {
/* 560 */     ((SVGSVGContext)getSVGContext()).deselectAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGNumber createSVGNumber() {
/* 567 */     return new SVGNumber() { protected float value;
/*     */         
/*     */         public float getValue() {
/* 570 */           return this.value;
/*     */         }
/*     */         public void setValue(float f) {
/* 573 */           this.value = f;
/*     */         } }
/*     */       ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGLength createSVGLength() {
/* 582 */     return new SVGOMLength(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAngle createSVGAngle() {
/* 589 */     return (SVGAngle)new SVGOMAngle();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPoint createSVGPoint() {
/* 596 */     return (SVGPoint)new SVGOMPoint(0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix createSVGMatrix() {
/* 603 */     return (SVGMatrix)new AbstractSVGMatrix() {
/* 604 */         protected AffineTransform at = new AffineTransform();
/*     */         protected AffineTransform getAffineTransform() {
/* 606 */           return this.at;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGRect createSVGRect() {
/* 615 */     return (SVGRect)new SVGOMRect(0.0F, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGTransform createSVGTransform() {
/* 622 */     SVGOMTransform ret = new SVGOMTransform();
/* 623 */     ret.setType((short)1);
/* 624 */     return (SVGTransform)ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGTransform createSVGTransformFromMatrix(SVGMatrix matrix) {
/* 632 */     SVGOMTransform tr = new SVGOMTransform();
/* 633 */     tr.setMatrix(matrix);
/* 634 */     return (SVGTransform)tr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getElementById(String elementId) {
/* 641 */     return this.ownerDocument.getChildElementById((Node)this, elementId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGElement getNearestViewportElement() {
/* 651 */     return SVGLocatableSupport.getNearestViewportElement((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGElement getFarthestViewportElement() {
/* 659 */     return SVGLocatableSupport.getFarthestViewportElement((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGRect getBBox() {
/* 667 */     return SVGLocatableSupport.getBBox((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix getCTM() {
/* 675 */     return SVGLocatableSupport.getCTM((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix getScreenCTM() {
/* 683 */     return SVGLocatableSupport.getScreenCTM((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix getTransformToElement(SVGElement element) throws SVGException {
/* 692 */     return SVGLocatableSupport.getTransformToElement((Element)this, element);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentView getDocument() {
/* 702 */     return (DocumentView)getOwnerDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSStyleDeclaration getComputedStyle(Element elt, String pseudoElt) {
/* 711 */     AbstractView av = ((DocumentView)getOwnerDocument()).getDefaultView();
/* 712 */     return ((ViewCSS)av).getComputedStyle(elt, pseudoElt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Event createEvent(String eventType) throws DOMException {
/* 722 */     return ((DocumentEvent)getOwnerDocument()).createEvent(eventType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDispatch(String namespaceURI, String type) throws DOMException {
/* 731 */     AbstractDocument doc = (AbstractDocument)getOwnerDocument();
/* 732 */     return doc.canDispatch(namespaceURI, type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StyleSheetList getStyleSheets() {
/* 742 */     return ((DocumentStyle)getOwnerDocument()).getStyleSheets();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSStyleDeclaration getOverrideStyle(Element elt, String pseudoElt) {
/* 751 */     return ((DocumentCSS)getOwnerDocument()).getOverrideStyle(elt, pseudoElt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLlang() {
/* 761 */     return XMLSupport.getXMLLang((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLlang(String lang) {
/* 768 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:lang", lang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLspace() {
/* 775 */     return XMLSupport.getXMLSpace((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLspace(String space) {
/* 782 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:space", space);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getZoomAndPan() {
/* 792 */     return SVGZoomAndPanSupport.getZoomAndPan((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setZoomAndPan(short val) {
/* 800 */     SVGZoomAndPanSupport.setZoomAndPan((Element)this, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedRect getViewBox() {
/* 810 */     return this.viewBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedPreserveAspectRatio getPreserveAspectRatio() {
/* 818 */     return this.preserveAspectRatio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedBoolean getExternalResourcesRequired() {
/* 828 */     return this.externalResourcesRequired;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getRequiredFeatures() {
/* 838 */     return SVGTestsSupport.getRequiredFeatures((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getRequiredExtensions() {
/* 846 */     return SVGTestsSupport.getRequiredExtensions((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getSystemLanguage() {
/* 854 */     return SVGTestsSupport.getSystemLanguage((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasExtension(String extension) {
/* 862 */     return SVGTestsSupport.hasExtension((Element)this, extension);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/* 870 */     return attributeInitializer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 877 */     return (Node)new SVGOMSVGElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 884 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMSVGElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */