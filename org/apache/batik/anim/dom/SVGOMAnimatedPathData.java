/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.batik.anim.values.AnimatablePathDataValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.dom.svg.AbstractSVGList;
/*     */ import org.apache.batik.dom.svg.AbstractSVGNormPathSegList;
/*     */ import org.apache.batik.dom.svg.AbstractSVGPathSegList;
/*     */ import org.apache.batik.dom.svg.ListBuilder;
/*     */ import org.apache.batik.dom.svg.ListHandler;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.apache.batik.dom.svg.SVGAnimatedPathDataSupport;
/*     */ import org.apache.batik.dom.svg.SVGItem;
/*     */ import org.apache.batik.dom.svg.SVGPathSegItem;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.apache.batik.parser.PathArrayProducer;
/*     */ import org.apache.batik.parser.PathHandler;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGAnimatedPathData;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ import org.w3c.dom.svg.SVGPathSeg;
/*     */ import org.w3c.dom.svg.SVGPathSegList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMAnimatedPathData
/*     */   extends AbstractSVGAnimatedValue
/*     */   implements SVGAnimatedPathData
/*     */ {
/*     */   protected boolean changing;
/*     */   protected BaseSVGPathSegList pathSegs;
/*     */   protected NormalizedBaseSVGPathSegList normalizedPathSegs;
/*     */   protected AnimSVGPathSegList animPathSegs;
/*     */   protected String defaultValue;
/*     */   
/*     */   public SVGOMAnimatedPathData(AbstractElement elt, String ns, String ln, String defaultValue) {
/* 105 */     super(elt, ns, ln);
/* 106 */     this.defaultValue = defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegList getAnimatedNormalizedPathSegList() {
/* 114 */     throw new UnsupportedOperationException("SVGAnimatedPathData.getAnimatedNormalizedPathSegList is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegList getAnimatedPathSegList() {
/* 123 */     if (this.animPathSegs == null) {
/* 124 */       this.animPathSegs = new AnimSVGPathSegList();
/*     */     }
/* 126 */     return (SVGPathSegList)this.animPathSegs;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegList getNormalizedPathSegList() {
/* 155 */     if (this.normalizedPathSegs == null) {
/* 156 */       this.normalizedPathSegs = new NormalizedBaseSVGPathSegList();
/*     */     }
/* 158 */     return (SVGPathSegList)this.normalizedPathSegs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegList getPathSegList() {
/* 166 */     if (this.pathSegs == null) {
/* 167 */       this.pathSegs = new BaseSVGPathSegList();
/*     */     }
/* 169 */     return (SVGPathSegList)this.pathSegs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void check() {
/* 176 */     if (!this.hasAnimVal) {
/* 177 */       if (this.pathSegs == null) {
/* 178 */         this.pathSegs = new BaseSVGPathSegList();
/*     */       }
/* 180 */       this.pathSegs.revalidate();
/* 181 */       if (this.pathSegs.missing) {
/* 182 */         throw new LiveAttributeException(this.element, this.localName, (short)0, null);
/*     */       }
/*     */ 
/*     */       
/* 186 */       if (this.pathSegs.malformed) {
/* 187 */         throw new LiveAttributeException(this.element, this.localName, (short)1, this.pathSegs.getValueAsString());
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
/*     */   public AnimatableValue getUnderlyingValue(AnimationTarget target) {
/* 199 */     SVGPathSegList psl = getPathSegList();
/* 200 */     PathArrayProducer pp = new PathArrayProducer();
/* 201 */     SVGAnimatedPathDataSupport.handlePathSegList(psl, (PathHandler)pp);
/* 202 */     return (AnimatableValue)new AnimatablePathDataValue(target, pp.getPathCommands(), pp.getPathParameters());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAnimatedValue(AnimatableValue val) {
/* 210 */     if (val == null) {
/* 211 */       this.hasAnimVal = false;
/*     */     } else {
/* 213 */       this.hasAnimVal = true;
/* 214 */       AnimatablePathDataValue animPath = (AnimatablePathDataValue)val;
/* 215 */       if (this.animPathSegs == null) {
/* 216 */         this.animPathSegs = new AnimSVGPathSegList();
/*     */       }
/* 218 */       this.animPathSegs.setAnimatedValue(animPath.getCommands(), animPath.getParameters());
/*     */     } 
/*     */     
/* 221 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrAdded(Attr node, String newv) {
/* 228 */     if (!this.changing) {
/* 229 */       if (this.pathSegs != null) {
/* 230 */         this.pathSegs.invalidate();
/*     */       }
/* 232 */       if (this.normalizedPathSegs != null) {
/* 233 */         this.normalizedPathSegs.invalidate();
/*     */       }
/*     */     } 
/* 236 */     fireBaseAttributeListeners();
/* 237 */     if (!this.hasAnimVal) {
/* 238 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrModified(Attr node, String oldv, String newv) {
/* 246 */     if (!this.changing) {
/* 247 */       if (this.pathSegs != null) {
/* 248 */         this.pathSegs.invalidate();
/*     */       }
/* 250 */       if (this.normalizedPathSegs != null) {
/* 251 */         this.normalizedPathSegs.invalidate();
/*     */       }
/*     */     } 
/* 254 */     fireBaseAttributeListeners();
/* 255 */     if (!this.hasAnimVal) {
/* 256 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrRemoved(Attr node, String oldv) {
/* 264 */     if (!this.changing) {
/* 265 */       if (this.pathSegs != null) {
/* 266 */         this.pathSegs.invalidate();
/*     */       }
/* 268 */       if (this.normalizedPathSegs != null) {
/* 269 */         this.normalizedPathSegs.invalidate();
/*     */       }
/*     */     } 
/* 272 */     fireBaseAttributeListeners();
/* 273 */     if (!this.hasAnimVal) {
/* 274 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class BaseSVGPathSegList
/*     */     extends AbstractSVGPathSegList
/*     */   {
/*     */     protected boolean missing;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean malformed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected DOMException createDOMException(short type, String key, Object[] args) {
/* 298 */       return SVGOMAnimatedPathData.this.element.createDOMException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGException createSVGException(short type, String key, Object[] args) {
/* 306 */       return ((SVGOMElement)SVGOMAnimatedPathData.this.element).createSVGException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String getValueAsString() {
/* 313 */       Attr attr = SVGOMAnimatedPathData.this.element.getAttributeNodeNS(SVGOMAnimatedPathData.this.namespaceURI, SVGOMAnimatedPathData.this.localName);
/* 314 */       if (attr == null) {
/* 315 */         return SVGOMAnimatedPathData.this.defaultValue;
/*     */       }
/* 317 */       return attr.getValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAttributeValue(String value) {
/*     */       try {
/* 325 */         SVGOMAnimatedPathData.this.changing = true;
/* 326 */         SVGOMAnimatedPathData.this.element.setAttributeNS(SVGOMAnimatedPathData.this.namespaceURI, SVGOMAnimatedPathData.this.localName, value);
/*     */       } finally {
/* 328 */         SVGOMAnimatedPathData.this.changing = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void resetAttribute() {
/* 336 */       super.resetAttribute();
/* 337 */       this.missing = false;
/* 338 */       this.malformed = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void resetAttribute(SVGItem item) {
/* 347 */       super.resetAttribute(item);
/* 348 */       this.missing = false;
/* 349 */       this.malformed = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void revalidate() {
/* 356 */       if (this.valid) {
/*     */         return;
/*     */       }
/*     */       
/* 360 */       this.valid = true;
/* 361 */       this.missing = false;
/* 362 */       this.malformed = false;
/*     */       
/* 364 */       String s = getValueAsString();
/* 365 */       if (s == null) {
/* 366 */         this.missing = true;
/*     */         return;
/*     */       } 
/*     */       try {
/* 370 */         ListBuilder builder = new ListBuilder((AbstractSVGList)this);
/*     */         
/* 372 */         doParse(s, (ListHandler)builder);
/*     */         
/* 374 */         if (builder.getList() != null) {
/* 375 */           clear(this.itemList);
/*     */         }
/* 377 */         this.itemList = builder.getList();
/* 378 */       } catch (ParseException e) {
/* 379 */         this.itemList = new ArrayList(1);
/* 380 */         this.malformed = true;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class NormalizedBaseSVGPathSegList
/*     */     extends AbstractSVGNormPathSegList
/*     */   {
/*     */     protected boolean missing;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean malformed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected DOMException createDOMException(short type, String key, Object[] args) {
/* 407 */       return SVGOMAnimatedPathData.this.element.createDOMException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGException createSVGException(short type, String key, Object[] args) {
/* 415 */       return ((SVGOMElement)SVGOMAnimatedPathData.this.element).createSVGException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String getValueAsString() throws SVGException {
/* 422 */       Attr attr = SVGOMAnimatedPathData.this.element.getAttributeNodeNS(SVGOMAnimatedPathData.this.namespaceURI, SVGOMAnimatedPathData.this.localName);
/* 423 */       if (attr == null) {
/* 424 */         return SVGOMAnimatedPathData.this.defaultValue;
/*     */       }
/* 426 */       return attr.getValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAttributeValue(String value) {
/*     */       try {
/* 434 */         SVGOMAnimatedPathData.this.changing = true;
/* 435 */         SVGOMAnimatedPathData.this.element.setAttributeNS(SVGOMAnimatedPathData.this.namespaceURI, SVGOMAnimatedPathData.this.localName, value);
/*     */       } finally {
/* 437 */         SVGOMAnimatedPathData.this.changing = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void revalidate() {
/* 445 */       if (this.valid) {
/*     */         return;
/*     */       }
/*     */       
/* 449 */       this.valid = true;
/* 450 */       this.missing = false;
/* 451 */       this.malformed = false;
/*     */       
/* 453 */       String s = getValueAsString();
/* 454 */       if (s == null) {
/* 455 */         this.missing = true;
/*     */         return;
/*     */       } 
/*     */       try {
/* 459 */         ListBuilder builder = new ListBuilder((AbstractSVGList)this);
/*     */         
/* 461 */         doParse(s, (ListHandler)builder);
/*     */         
/* 463 */         if (builder.getList() != null) {
/* 464 */           clear(this.itemList);
/*     */         }
/* 466 */         this.itemList = builder.getList();
/* 467 */       } catch (ParseException e) {
/* 468 */         this.itemList = new ArrayList(1);
/* 469 */         this.malformed = true;
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
/*     */   public class AnimSVGPathSegList
/*     */     extends AbstractSVGPathSegList
/*     */   {
/*     */     protected DOMException createDOMException(short type, String key, Object[] args) {
/* 491 */       return SVGOMAnimatedPathData.this.element.createDOMException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGException createSVGException(short type, String key, Object[] args) {
/* 499 */       return ((SVGOMElement)SVGOMAnimatedPathData.this.element).createSVGException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getNumberOfItems() {
/* 506 */       if (SVGOMAnimatedPathData.this.hasAnimVal) {
/* 507 */         return super.getNumberOfItems();
/*     */       }
/* 509 */       return SVGOMAnimatedPathData.this.getPathSegList().getNumberOfItems();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGPathSeg getItem(int index) throws DOMException {
/* 516 */       if (SVGOMAnimatedPathData.this.hasAnimVal) {
/* 517 */         return super.getItem(index);
/*     */       }
/* 519 */       return SVGOMAnimatedPathData.this.getPathSegList().getItem(index);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String getValueAsString() {
/* 526 */       if (this.itemList.size() == 0) {
/* 527 */         return "";
/*     */       }
/* 529 */       StringBuffer sb = new StringBuffer(this.itemList.size() * 8);
/* 530 */       Iterator<SVGItem> i = this.itemList.iterator();
/* 531 */       if (i.hasNext()) {
/* 532 */         sb.append(((SVGItem)i.next()).getValueAsString());
/*     */       }
/* 534 */       while (i.hasNext()) {
/* 535 */         sb.append(getItemSeparator());
/* 536 */         sb.append(((SVGItem)i.next()).getValueAsString());
/*     */       } 
/* 538 */       return sb.toString();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAttributeValue(String value) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void clear() throws DOMException {
/* 551 */       throw SVGOMAnimatedPathData.this.element.createDOMException((short)7, "readonly.pathseg.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGPathSeg initialize(SVGPathSeg newItem) throws DOMException, SVGException {
/* 561 */       throw SVGOMAnimatedPathData.this.element.createDOMException((short)7, "readonly.pathseg.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGPathSeg insertItemBefore(SVGPathSeg newItem, int index) throws DOMException, SVGException {
/* 572 */       throw SVGOMAnimatedPathData.this.element.createDOMException((short)7, "readonly.pathseg.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGPathSeg replaceItem(SVGPathSeg newItem, int index) throws DOMException, SVGException {
/* 583 */       throw SVGOMAnimatedPathData.this.element.createDOMException((short)7, "readonly.pathseg.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGPathSeg removeItem(int index) throws DOMException {
/* 592 */       throw SVGOMAnimatedPathData.this.element.createDOMException((short)7, "readonly.pathseg.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGPathSeg appendItem(SVGPathSeg newItem) throws DOMException {
/* 601 */       throw SVGOMAnimatedPathData.this.element.createDOMException((short)7, "readonly.pathseg.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 609 */     private int[] parameterIndex = new int[1];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGPathSegItem newItem(short command, float[] parameters, int[] j) {
/* 617 */       switch (command) {
/*     */         case 10:
/*     */         case 11:
/* 620 */           j[0] = j[0] + 1; j[0] = j[0] + 1; j[0] = j[0] + 1; j[0] = j[0] + 1; j[0] = j[0] + 1; j[0] = j[0] + 1; j[0] = j[0] + 1; return (SVGPathSegItem)new AbstractSVGPathSegList.SVGPathSegArcItem(this, command, PATHSEG_LETTERS[command], parameters[j[0]], parameters[j[0]], parameters[j[0]], (parameters[j[0]] != 0.0F), (parameters[j[0]] != 0.0F), parameters[j[0]], parameters[j[0]]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 1:
/* 630 */           return new SVGPathSegItem(command, PATHSEG_LETTERS[command]);
/*     */         
/*     */         case 6:
/*     */         case 7:
/* 634 */           j[0] = j[0] + 1; j[0] = j[0] + 1; j[0] = j[0] + 1; j[0] = j[0] + 1; j[0] = j[0] + 1; j[0] = j[0] + 1; return (SVGPathSegItem)new AbstractSVGPathSegList.SVGPathSegCurvetoCubicItem(this, command, PATHSEG_LETTERS[command], parameters[j[0]], parameters[j[0]], parameters[j[0]], parameters[j[0]], parameters[j[0]], parameters[j[0]]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 16:
/*     */         case 17:
/* 644 */           j[0] = j[0] + 1; j[0] = j[0] + 1; j[0] = j[0] + 1; j[0] = j[0] + 1; return (SVGPathSegItem)new AbstractSVGPathSegList.SVGPathSegCurvetoCubicSmoothItem(this, command, PATHSEG_LETTERS[command], parameters[j[0]], parameters[j[0]], parameters[j[0]], parameters[j[0]]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 8:
/*     */         case 9:
/* 652 */           j[0] = j[0] + 1; j[0] = j[0] + 1; j[0] = j[0] + 1; j[0] = j[0] + 1; return (SVGPathSegItem)new AbstractSVGPathSegList.SVGPathSegCurvetoQuadraticItem(this, command, PATHSEG_LETTERS[command], parameters[j[0]], parameters[j[0]], parameters[j[0]], parameters[j[0]]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 18:
/*     */         case 19:
/* 660 */           j[0] = j[0] + 1; j[0] = j[0] + 1; return (SVGPathSegItem)new AbstractSVGPathSegList.SVGPathSegCurvetoQuadraticSmoothItem(this, command, PATHSEG_LETTERS[command], parameters[j[0]], parameters[j[0]]);
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/*     */         case 3:
/*     */         case 4:
/*     */         case 5:
/* 668 */           j[0] = j[0] + 1; j[0] = j[0] + 1; return (SVGPathSegItem)new AbstractSVGPathSegList.SVGPathSegMovetoLinetoItem(this, command, PATHSEG_LETTERS[command], parameters[j[0]], parameters[j[0]]);
/*     */ 
/*     */ 
/*     */         
/*     */         case 12:
/*     */         case 13:
/* 674 */           j[0] = j[0] + 1; return (SVGPathSegItem)new AbstractSVGPathSegList.SVGPathSegLinetoHorizontalItem(this, command, PATHSEG_LETTERS[command], parameters[j[0]]);
/*     */ 
/*     */         
/*     */         case 14:
/*     */         case 15:
/* 679 */           j[0] = j[0] + 1; return (SVGPathSegItem)new AbstractSVGPathSegList.SVGPathSegLinetoVerticalItem(this, command, PATHSEG_LETTERS[command], parameters[j[0]]);
/*     */       } 
/*     */ 
/*     */       
/* 683 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAnimatedValue(short[] commands, float[] parameters) {
/* 690 */       int size = this.itemList.size();
/* 691 */       int i = 0;
/* 692 */       int[] j = this.parameterIndex;
/* 693 */       j[0] = 0;
/* 694 */       while (i < size && i < commands.length) {
/* 695 */         SVGPathSegItem sVGPathSegItem; SVGPathSeg s = this.itemList.get(i);
/* 696 */         if (s.getPathSegType() != commands[i]) {
/* 697 */           sVGPathSegItem = newItem(commands[i], parameters, j);
/*     */         } else {
/* 699 */           AbstractSVGPathSegList.SVGPathSegArcItem sVGPathSegArcItem; AbstractSVGPathSegList.SVGPathSegCurvetoCubicItem sVGPathSegCurvetoCubicItem; AbstractSVGPathSegList.SVGPathSegCurvetoCubicSmoothItem sVGPathSegCurvetoCubicSmoothItem; AbstractSVGPathSegList.SVGPathSegCurvetoQuadraticItem sVGPathSegCurvetoQuadraticItem; AbstractSVGPathSegList.SVGPathSegCurvetoQuadraticSmoothItem sVGPathSegCurvetoQuadraticSmoothItem; AbstractSVGPathSegList.SVGPathSegMovetoLinetoItem sVGPathSegMovetoLinetoItem; AbstractSVGPathSegList.SVGPathSegLinetoHorizontalItem sVGPathSegLinetoHorizontalItem; AbstractSVGPathSegList.SVGPathSegLinetoVerticalItem ps; switch (commands[i]) {
/*     */             case 10:
/*     */             case 11:
/* 702 */               sVGPathSegArcItem = (AbstractSVGPathSegList.SVGPathSegArcItem)sVGPathSegItem;
/* 703 */               j[0] = j[0] + 1; sVGPathSegArcItem.setR1(parameters[j[0]]);
/* 704 */               j[0] = j[0] + 1; sVGPathSegArcItem.setR2(parameters[j[0]]);
/* 705 */               j[0] = j[0] + 1; sVGPathSegArcItem.setAngle(parameters[j[0]]);
/* 706 */               j[0] = j[0] + 1; sVGPathSegArcItem.setLargeArcFlag((parameters[j[0]] != 0.0F));
/* 707 */               j[0] = j[0] + 1; sVGPathSegArcItem.setSweepFlag((parameters[j[0]] != 0.0F));
/* 708 */               j[0] = j[0] + 1; sVGPathSegArcItem.setX(parameters[j[0]]);
/* 709 */               j[0] = j[0] + 1; sVGPathSegArcItem.setY(parameters[j[0]]);
/*     */               break;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             case 6:
/*     */             case 7:
/* 717 */               sVGPathSegCurvetoCubicItem = (AbstractSVGPathSegList.SVGPathSegCurvetoCubicItem)sVGPathSegItem;
/*     */               
/* 719 */               j[0] = j[0] + 1; sVGPathSegCurvetoCubicItem.setX1(parameters[j[0]]);
/* 720 */               j[0] = j[0] + 1; sVGPathSegCurvetoCubicItem.setY1(parameters[j[0]]);
/* 721 */               j[0] = j[0] + 1; sVGPathSegCurvetoCubicItem.setX2(parameters[j[0]]);
/* 722 */               j[0] = j[0] + 1; sVGPathSegCurvetoCubicItem.setY2(parameters[j[0]]);
/* 723 */               j[0] = j[0] + 1; sVGPathSegCurvetoCubicItem.setX(parameters[j[0]]);
/* 724 */               j[0] = j[0] + 1; sVGPathSegCurvetoCubicItem.setY(parameters[j[0]]);
/*     */               break;
/*     */             
/*     */             case 16:
/*     */             case 17:
/* 729 */               sVGPathSegCurvetoCubicSmoothItem = (AbstractSVGPathSegList.SVGPathSegCurvetoCubicSmoothItem)sVGPathSegItem;
/*     */               
/* 731 */               j[0] = j[0] + 1; sVGPathSegCurvetoCubicSmoothItem.setX2(parameters[j[0]]);
/* 732 */               j[0] = j[0] + 1; sVGPathSegCurvetoCubicSmoothItem.setY2(parameters[j[0]]);
/* 733 */               j[0] = j[0] + 1; sVGPathSegCurvetoCubicSmoothItem.setX(parameters[j[0]]);
/* 734 */               j[0] = j[0] + 1; sVGPathSegCurvetoCubicSmoothItem.setY(parameters[j[0]]);
/*     */               break;
/*     */             
/*     */             case 8:
/*     */             case 9:
/* 739 */               sVGPathSegCurvetoQuadraticItem = (AbstractSVGPathSegList.SVGPathSegCurvetoQuadraticItem)sVGPathSegItem;
/*     */               
/* 741 */               j[0] = j[0] + 1; sVGPathSegCurvetoQuadraticItem.setX1(parameters[j[0]]);
/* 742 */               j[0] = j[0] + 1; sVGPathSegCurvetoQuadraticItem.setY1(parameters[j[0]]);
/* 743 */               j[0] = j[0] + 1; sVGPathSegCurvetoQuadraticItem.setX(parameters[j[0]]);
/* 744 */               j[0] = j[0] + 1; sVGPathSegCurvetoQuadraticItem.setY(parameters[j[0]]);
/*     */               break;
/*     */             
/*     */             case 18:
/*     */             case 19:
/* 749 */               sVGPathSegCurvetoQuadraticSmoothItem = (AbstractSVGPathSegList.SVGPathSegCurvetoQuadraticSmoothItem)sVGPathSegItem;
/*     */               
/* 751 */               j[0] = j[0] + 1; sVGPathSegCurvetoQuadraticSmoothItem.setX(parameters[j[0]]);
/* 752 */               j[0] = j[0] + 1; sVGPathSegCurvetoQuadraticSmoothItem.setY(parameters[j[0]]);
/*     */               break;
/*     */             
/*     */             case 2:
/*     */             case 3:
/*     */             case 4:
/*     */             case 5:
/* 759 */               sVGPathSegMovetoLinetoItem = (AbstractSVGPathSegList.SVGPathSegMovetoLinetoItem)sVGPathSegItem;
/*     */               
/* 761 */               j[0] = j[0] + 1; sVGPathSegMovetoLinetoItem.setX(parameters[j[0]]);
/* 762 */               j[0] = j[0] + 1; sVGPathSegMovetoLinetoItem.setY(parameters[j[0]]);
/*     */               break;
/*     */             
/*     */             case 12:
/*     */             case 13:
/* 767 */               sVGPathSegLinetoHorizontalItem = (AbstractSVGPathSegList.SVGPathSegLinetoHorizontalItem)sVGPathSegItem;
/*     */               
/* 769 */               j[0] = j[0] + 1; sVGPathSegLinetoHorizontalItem.setX(parameters[j[0]]);
/*     */               break;
/*     */             
/*     */             case 14:
/*     */             case 15:
/* 774 */               ps = (AbstractSVGPathSegList.SVGPathSegLinetoVerticalItem)sVGPathSegItem;
/*     */               
/* 776 */               j[0] = j[0] + 1; ps.setY(parameters[j[0]]);
/*     */               break;
/*     */           } 
/*     */         
/*     */         } 
/* 781 */         i++;
/*     */       } 
/* 783 */       while (i < commands.length) {
/* 784 */         appendItemImpl(newItem(commands[i], parameters, j));
/* 785 */         i++;
/*     */       } 
/* 787 */       while (size > commands.length) {
/* 788 */         removeItemImpl(--size);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void resetAttribute() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void resetAttribute(SVGItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void revalidate() {
/* 811 */       this.valid = true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAnimatedPathData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */