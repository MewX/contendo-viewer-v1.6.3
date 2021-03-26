/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.svg.SVGTestsSupport;
/*     */ import org.apache.batik.dom.util.XMLSupport;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGAnimatedBoolean;
/*     */ import org.w3c.dom.svg.SVGAnimatedEnumeration;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGLength;
/*     */ import org.w3c.dom.svg.SVGPoint;
/*     */ import org.w3c.dom.svg.SVGRect;
/*     */ import org.w3c.dom.svg.SVGStringList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SVGOMTextContentElement
/*     */   extends SVGStylableElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  50 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGStylableElement.xmlTraitInformation);
/*     */     
/*  52 */     t.put(null, "textLength", new TraitInformation(true, 3, (short)3));
/*     */     
/*  54 */     t.put(null, "lengthAdjust", new TraitInformation(true, 15));
/*     */     
/*  56 */     t.put(null, "externalResourcesRequired", new TraitInformation(true, 49));
/*     */     
/*  58 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   protected static final String[] LENGTH_ADJUST_VALUES = new String[] { "", "spacing", "spacingAndGlyphs" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedBoolean externalResourcesRequired;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractSVGAnimatedLength textLength;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration lengthAdjust;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMTextContentElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMTextContentElement(String prefix, AbstractDocument owner) {
/*  97 */     super(prefix, owner);
/*  98 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 105 */     super.initializeAllLiveAttributes();
/* 106 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 113 */     this.externalResourcesRequired = createLiveAnimatedBoolean((String)null, "externalResourcesRequired", false);
/*     */ 
/*     */     
/* 116 */     this.lengthAdjust = createLiveAnimatedEnumeration((String)null, "lengthAdjust", LENGTH_ADJUST_VALUES, (short)1);
/*     */ 
/*     */ 
/*     */     
/* 120 */     this.textLength = new AbstractSVGAnimatedLength(this, null, "textLength", (short)2, true)
/*     */       {
/*     */         boolean usedDefault;
/*     */ 
/*     */         
/*     */         protected String getDefaultValue() {
/* 126 */           this.usedDefault = true;
/* 127 */           return String.valueOf(SVGOMTextContentElement.this.getComputedTextLength());
/*     */         }
/*     */         
/*     */         public SVGLength getBaseVal() {
/* 131 */           if (this.baseVal == null) {
/* 132 */             this.baseVal = new SVGTextLength(this.direction);
/*     */           }
/* 134 */           return this.baseVal;
/*     */         }
/*     */ 
/*     */         
/*     */         class SVGTextLength
/*     */           extends AbstractSVGAnimatedLength.BaseSVGLength
/*     */         {
/*     */           protected void revalidate() {
/* 142 */             SVGOMTextContentElement.null.this.usedDefault = false;
/*     */             
/* 144 */             super.revalidate();
/*     */ 
/*     */ 
/*     */             
/* 148 */             if (SVGOMTextContentElement.null.this.usedDefault) this.valid = false;
/*     */           
/*     */           }
/*     */         }
/*     */       };
/* 153 */     this.liveAttributeValues.put(null, "textLength", this.textLength);
/* 154 */     this.textLength.addAnimatedAttributeListener(((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getTextLength() {
/* 163 */     return this.textLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getLengthAdjust() {
/* 171 */     return this.lengthAdjust;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfChars() {
/* 179 */     return SVGTextContentSupport.getNumberOfChars((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getComputedTextLength() {
/* 187 */     return SVGTextContentSupport.getComputedTextLength((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSubStringLength(int charnum, int nchars) throws DOMException {
/* 196 */     return SVGTextContentSupport.getSubStringLength((Element)this, charnum, nchars);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPoint getStartPositionOfChar(int charnum) throws DOMException {
/* 204 */     return SVGTextContentSupport.getStartPositionOfChar((Element)this, charnum);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPoint getEndPositionOfChar(int charnum) throws DOMException {
/* 212 */     return SVGTextContentSupport.getEndPositionOfChar((Element)this, charnum);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGRect getExtentOfChar(int charnum) throws DOMException {
/* 220 */     return SVGTextContentSupport.getExtentOfChar((Element)this, charnum);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getRotationOfChar(int charnum) throws DOMException {
/* 228 */     return SVGTextContentSupport.getRotationOfChar((Element)this, charnum);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCharNumAtPosition(SVGPoint point) {
/* 236 */     return SVGTextContentSupport.getCharNumAtPosition((Element)this, point.getX(), point.getY());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void selectSubString(int charnum, int nchars) throws DOMException {
/* 246 */     SVGTextContentSupport.selectSubString((Element)this, charnum, nchars);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedBoolean getExternalResourcesRequired() {
/* 256 */     return this.externalResourcesRequired;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLlang() {
/* 265 */     return XMLSupport.getXMLLang((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLlang(String lang) {
/* 272 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:lang", lang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLspace() {
/* 279 */     return XMLSupport.getXMLSpace((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLspace(String space) {
/* 286 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:space", space);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getRequiredFeatures() {
/* 296 */     return SVGTestsSupport.getRequiredFeatures((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getRequiredExtensions() {
/* 304 */     return SVGTestsSupport.getRequiredExtensions((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getSystemLanguage() {
/* 312 */     return SVGTestsSupport.getSystemLanguage((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasExtension(String extension) {
/* 320 */     return SVGTestsSupport.hasExtension((Element)this, extension);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 327 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMTextContentElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */