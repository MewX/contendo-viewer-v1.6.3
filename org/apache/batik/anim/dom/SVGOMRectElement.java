/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGRectElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMRectElement
/*     */   extends SVGGraphicsElement
/*     */   implements SVGRectElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedLength x;
/*     */   protected SVGOMAnimatedLength y;
/*     */   protected AbstractSVGAnimatedLength rx;
/*     */   protected AbstractSVGAnimatedLength ry;
/*     */   protected SVGOMAnimatedLength width;
/*     */   protected SVGOMAnimatedLength height;
/*     */   
/*     */   static {
/*  46 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGGraphicsElement.xmlTraitInformation);
/*     */     
/*  48 */     t.put(null, "x", new TraitInformation(true, 3, (short)1));
/*     */     
/*  50 */     t.put(null, "y", new TraitInformation(true, 3, (short)2));
/*     */     
/*  52 */     t.put(null, "rx", new TraitInformation(true, 3, (short)1));
/*     */     
/*  54 */     t.put(null, "ry", new TraitInformation(true, 3, (short)2));
/*     */     
/*  56 */     t.put(null, "width", new TraitInformation(true, 3, (short)1));
/*     */     
/*  58 */     t.put(null, "height", new TraitInformation(true, 3, (short)2));
/*     */     
/*  60 */     xmlTraitInformation = t;
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
/*     */   protected SVGOMRectElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMRectElement(String prefix, AbstractDocument owner) {
/* 105 */     super(prefix, owner);
/* 106 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 113 */     super.initializeAllLiveAttributes();
/* 114 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 121 */     this.x = createLiveAnimatedLength((String)null, "x", "0", (short)2, false);
/*     */ 
/*     */     
/* 124 */     this.y = createLiveAnimatedLength((String)null, "y", "0", (short)1, false);
/*     */ 
/*     */     
/* 127 */     this.width = createLiveAnimatedLength((String)null, "width", (String)null, (short)2, true);
/*     */ 
/*     */ 
/*     */     
/* 131 */     this.height = createLiveAnimatedLength((String)null, "height", (String)null, (short)1, true);
/*     */ 
/*     */ 
/*     */     
/* 135 */     this.rx = new AbstractSVGAnimatedLength(this, null, "rx", (short)2, true)
/*     */       {
/*     */         protected String getDefaultValue()
/*     */         {
/* 139 */           Attr attr = SVGOMRectElement.this.getAttributeNodeNS(null, "ry");
/* 140 */           if (attr == null) {
/* 141 */             return "0";
/*     */           }
/* 143 */           return attr.getValue();
/*     */         }
/*     */         protected void attrChanged() {
/* 146 */           super.attrChanged();
/* 147 */           AbstractSVGAnimatedLength ry = (AbstractSVGAnimatedLength)SVGOMRectElement.this.getRy();
/*     */           
/* 149 */           if (isSpecified() && !ry.isSpecified()) {
/* 150 */             ry.attrChanged();
/*     */           }
/*     */         }
/*     */       };
/* 154 */     this.ry = new AbstractSVGAnimatedLength(this, null, "ry", (short)1, true)
/*     */       {
/*     */         protected String getDefaultValue()
/*     */         {
/* 158 */           Attr attr = SVGOMRectElement.this.getAttributeNodeNS(null, "rx");
/* 159 */           if (attr == null) {
/* 160 */             return "0";
/*     */           }
/* 162 */           return attr.getValue();
/*     */         }
/*     */         protected void attrChanged() {
/* 165 */           super.attrChanged();
/* 166 */           AbstractSVGAnimatedLength rx = (AbstractSVGAnimatedLength)SVGOMRectElement.this.getRx();
/*     */           
/* 168 */           if (isSpecified() && !rx.isSpecified()) {
/* 169 */             rx.attrChanged();
/*     */           }
/*     */         }
/*     */       };
/*     */     
/* 174 */     this.liveAttributeValues.put(null, "rx", this.rx);
/* 175 */     this.liveAttributeValues.put(null, "ry", this.ry);
/* 176 */     AnimatedAttributeListener l = ((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener();
/*     */     
/* 178 */     this.rx.addAnimatedAttributeListener(l);
/* 179 */     this.ry.addAnimatedAttributeListener(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 186 */     return "rect";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getX() {
/* 193 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getY() {
/* 200 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getWidth() {
/* 207 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getHeight() {
/* 214 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getRx() {
/* 221 */     return this.rx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getRy() {
/* 228 */     return this.ry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 235 */     return (Node)new SVGOMRectElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 242 */     return xmlTraitInformation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateAttributeValue(String ns, String ln, AnimatableValue val) {
/* 252 */     if (ns == null) {
/* 253 */       if (ln.equals("rx")) {
/* 254 */         super.updateAttributeValue(ns, ln, val);
/* 255 */         AbstractSVGAnimatedLength ry = (AbstractSVGAnimatedLength)getRy();
/*     */         
/* 257 */         if (!ry.isSpecified())
/* 258 */           super.updateAttributeValue(ns, "ry", val); 
/*     */         return;
/*     */       } 
/* 261 */       if (ln.equals("ry")) {
/* 262 */         super.updateAttributeValue(ns, ln, val);
/* 263 */         AbstractSVGAnimatedLength rx = (AbstractSVGAnimatedLength)getRx();
/*     */         
/* 265 */         if (!rx.isSpecified()) {
/* 266 */           super.updateAttributeValue(ns, "rx", val);
/*     */         }
/*     */         return;
/*     */       } 
/*     */     } 
/* 271 */     super.updateAttributeValue(ns, ln, val);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMRectElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */