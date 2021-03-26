/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.g2d.GraphicContext;
/*     */ import org.apache.batik.ext.awt.g2d.TransformStackElement;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DOMGroupManager
/*     */   implements ErrorConstants, SVGSyntax
/*     */ {
/*     */   public static final short DRAW = 1;
/*     */   public static final short FILL = 16;
/*     */   protected GraphicContext gc;
/*     */   protected DOMTreeManager domTreeManager;
/*     */   protected SVGGraphicContext groupGC;
/*     */   protected Element currentGroup;
/*     */   
/*     */   public DOMGroupManager(GraphicContext gc, DOMTreeManager domTreeManager) {
/*  93 */     if (gc == null) {
/*  94 */       throw new SVGGraphics2DRuntimeException("gc should not be null");
/*     */     }
/*  96 */     if (domTreeManager == null) {
/*  97 */       throw new SVGGraphics2DRuntimeException("domTreeManager should not be null");
/*     */     }
/*  99 */     this.gc = gc;
/* 100 */     this.domTreeManager = domTreeManager;
/*     */ 
/*     */     
/* 103 */     recycleCurrentGroup();
/*     */ 
/*     */     
/* 106 */     this.groupGC = domTreeManager.gcConverter.toSVG(gc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void recycleCurrentGroup() {
/* 114 */     this.currentGroup = this.domTreeManager.getDOMFactory().createElementNS("http://www.w3.org/2000/svg", "g");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addElement(Element element) {
/* 123 */     addElement(element, (short)17);
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
/*     */   public void addElement(Element element, short method) {
/* 135 */     if (!this.currentGroup.hasChildNodes()) {
/* 136 */       this.currentGroup.appendChild(element);
/*     */       
/* 138 */       this.groupGC = this.domTreeManager.gcConverter.toSVG(this.gc);
/*     */       
/* 140 */       SVGGraphicContext deltaGC = processDeltaGC(this.groupGC, this.domTreeManager.defaultGC);
/*     */       
/* 142 */       this.domTreeManager.getStyleHandler().setStyle(this.currentGroup, deltaGC.getGroupContext(), this.domTreeManager.getGeneratorContext());
/*     */ 
/*     */       
/* 145 */       if ((method & 0x1) == 0)
/*     */       {
/* 147 */         deltaGC.getGraphicElementContext().put("stroke", "none");
/*     */       }
/*     */       
/* 150 */       if ((method & 0x10) == 0)
/*     */       {
/* 152 */         deltaGC.getGraphicElementContext().put("fill", "none");
/*     */       }
/*     */       
/* 155 */       this.domTreeManager.getStyleHandler().setStyle(element, deltaGC.getGraphicElementContext(), this.domTreeManager.getGeneratorContext());
/*     */ 
/*     */       
/* 158 */       setTransform(this.currentGroup, deltaGC.getTransformStack());
/* 159 */       this.domTreeManager.appendGroup(this.currentGroup, this);
/*     */     }
/* 161 */     else if (this.gc.isTransformStackValid()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 167 */       SVGGraphicContext elementGC = this.domTreeManager.gcConverter.toSVG(this.gc);
/*     */       
/* 169 */       SVGGraphicContext deltaGC = processDeltaGC(elementGC, this.groupGC);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 174 */       trimContextForElement(deltaGC, element);
/* 175 */       if (countOverrides(deltaGC) <= this.domTreeManager.maxGCOverrides) {
/* 176 */         this.currentGroup.appendChild(element);
/*     */ 
/*     */         
/* 179 */         if ((method & 0x1) == 0)
/*     */         {
/* 181 */           deltaGC.getContext().put("stroke", "none");
/*     */         }
/*     */         
/* 184 */         if ((method & 0x10) == 0)
/*     */         {
/* 186 */           deltaGC.getContext().put("fill", "none");
/*     */         }
/*     */         
/* 189 */         this.domTreeManager.getStyleHandler().setStyle(element, deltaGC.getContext(), this.domTreeManager.getGeneratorContext());
/*     */ 
/*     */         
/* 192 */         setTransform(element, deltaGC.getTransformStack());
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 197 */         this.currentGroup = this.domTreeManager.getDOMFactory().createElementNS("http://www.w3.org/2000/svg", "g");
/*     */ 
/*     */         
/* 200 */         addElement(element, method);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 207 */       this.currentGroup = this.domTreeManager.getDOMFactory().createElementNS("http://www.w3.org/2000/svg", "g");
/*     */ 
/*     */       
/* 210 */       this.gc.validateTransformStack();
/* 211 */       addElement(element, method);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int countOverrides(SVGGraphicContext deltaGC) {
/* 222 */     return deltaGC.getGroupContext().size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void trimContextForElement(SVGGraphicContext svgGC, Element element) {
/* 229 */     String tag = element.getTagName();
/* 230 */     Map groupAttrMap = svgGC.getGroupContext();
/* 231 */     if (tag != null)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 236 */       for (Object o : groupAttrMap.keySet()) {
/* 237 */         String attrName = (String)o;
/* 238 */         SVGAttribute attr = SVGAttributeMap.get(attrName);
/* 239 */         if (attr != null && !attr.appliesTo(tag)) {
/* 240 */           groupAttrMap.remove(attrName);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setTransform(Element element, TransformStackElement[] transformStack) {
/* 251 */     String transform = this.domTreeManager.gcConverter.toSVG(transformStack).trim();
/*     */     
/* 253 */     if (transform.length() > 0) {
/* 254 */       element.setAttributeNS((String)null, "transform", transform);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static SVGGraphicContext processDeltaGC(SVGGraphicContext gc, SVGGraphicContext referenceGc) {
/* 264 */     Map groupDelta = processDeltaMap(gc.getGroupContext(), referenceGc.getGroupContext());
/*     */     
/* 266 */     Map graphicElementDelta = gc.getGraphicElementContext();
/*     */     
/* 268 */     TransformStackElement[] gcTransformStack = gc.getTransformStack();
/* 269 */     TransformStackElement[] referenceStack = referenceGc.getTransformStack();
/* 270 */     int deltaStackLength = gcTransformStack.length - referenceStack.length;
/* 271 */     TransformStackElement[] deltaTransformStack = new TransformStackElement[deltaStackLength];
/*     */ 
/*     */     
/* 274 */     System.arraycopy(gcTransformStack, referenceStack.length, deltaTransformStack, 0, deltaStackLength);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 300 */     SVGGraphicContext deltaGC = new SVGGraphicContext(groupDelta, graphicElementDelta, deltaTransformStack);
/*     */ 
/*     */ 
/*     */     
/* 304 */     return deltaGC;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Map processDeltaMap(Map map, Map referenceMap) {
/* 315 */     Map<Object, Object> mapDelta = new HashMap<Object, Object>();
/* 316 */     for (Object o : map.keySet()) {
/* 317 */       String key = (String)o;
/* 318 */       String value = (String)map.get(key);
/* 319 */       String refValue = (String)referenceMap.get(key);
/* 320 */       if (!value.equals(refValue))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 328 */         mapDelta.put(key, value);
/*     */       }
/*     */     } 
/* 331 */     return mapDelta;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/DOMGroupManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */