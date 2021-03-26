/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.svg.SVGOMUseShadowRoot;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGElementInstance;
/*     */ import org.w3c.dom.svg.SVGUseElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMUseElement
/*     */   extends SVGURIReferenceGraphicsElement
/*     */   implements SVGUseElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  48 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGURIReferenceGraphicsElement.xmlTraitInformation);
/*     */     
/*  50 */     t.put(null, "x", new TraitInformation(true, 3, (short)1));
/*     */     
/*  52 */     t.put(null, "y", new TraitInformation(true, 3, (short)2));
/*     */     
/*  54 */     t.put(null, "width", new TraitInformation(true, 3, (short)1));
/*     */     
/*  56 */     t.put(null, "height", new TraitInformation(true, 3, (short)2));
/*     */     
/*  58 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   protected static final AttributeInitializer attributeInitializer = new AttributeInitializer(4); static {
/*  67 */     attributeInitializer.addAttribute("http://www.w3.org/2000/xmlns/", null, "xmlns:xlink", "http://www.w3.org/1999/xlink");
/*     */ 
/*     */     
/*  70 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "type", "simple");
/*     */     
/*  72 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "show", "embed");
/*     */     
/*  74 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "actuate", "onLoad");
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
/*     */   protected SVGOMAnimatedLength y;
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
/*     */   protected SVGOMUseShadowRoot shadowTree;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMUseElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMUseElement(String prefix, AbstractDocument owner) {
/* 115 */     super(prefix, owner);
/* 116 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 123 */     super.initializeAllLiveAttributes();
/* 124 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 131 */     this.x = createLiveAnimatedLength(null, "x", "0", (short)2, false);
/*     */ 
/*     */     
/* 134 */     this.y = createLiveAnimatedLength(null, "y", "0", (short)1, false);
/*     */ 
/*     */     
/* 137 */     this.width = createLiveAnimatedLength(null, "width", null, (short)2, true);
/*     */ 
/*     */ 
/*     */     
/* 141 */     this.height = createLiveAnimatedLength(null, "height", null, (short)1, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 151 */     return "use";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getX() {
/* 158 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getY() {
/* 165 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getWidth() {
/* 172 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getHeight() {
/* 179 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGElementInstance getInstanceRoot() {
/* 186 */     throw new UnsupportedOperationException("SVGUseElement.getInstanceRoot is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGElementInstance getAnimatedInstanceRoot() {
/* 194 */     throw new UnsupportedOperationException("SVGUseElement.getAnimatedInstanceRoot is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCSSFirstChild() {
/* 204 */     if (this.shadowTree != null) {
/* 205 */       return this.shadowTree.getFirstChild();
/*     */     }
/* 207 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCSSLastChild() {
/* 215 */     return getCSSFirstChild();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHiddenFromSelectors() {
/* 225 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUseShadowTree(SVGOMUseShadowRoot r) {
/* 232 */     this.shadowTree = r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/* 240 */     return attributeInitializer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 247 */     return (Node)new SVGOMUseElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 254 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMUseElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */