/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.g2d.GraphicContext;
/*     */ import org.w3c.dom.Comment;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DOMTreeManager
/*     */   implements ErrorConstants, SVGSyntax
/*     */ {
/*     */   int maxGCOverrides;
/*  76 */   protected final List groupManagers = Collections.synchronizedList(new ArrayList());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   protected List genericDefSet = new LinkedList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SVGGraphicContext defaultGC;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Element topLevelGroup;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SVGGraphicContextConverter gcConverter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGGeneratorContext generatorContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGBufferedImageOp filterConverter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List otherDefs;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DOMTreeManager(GraphicContext gc, SVGGeneratorContext generatorContext, int maxGCOverrides) {
/* 128 */     if (gc == null) {
/* 129 */       throw new SVGGraphics2DRuntimeException("gc should not be null");
/*     */     }
/* 131 */     if (maxGCOverrides <= 0) {
/* 132 */       throw new SVGGraphics2DRuntimeException("maxGcOverrides should be greater than zero");
/*     */     }
/* 134 */     if (generatorContext == null) {
/* 135 */       throw new SVGGraphics2DRuntimeException("generatorContext should not be null");
/*     */     }
/* 137 */     this.generatorContext = generatorContext;
/* 138 */     this.maxGCOverrides = maxGCOverrides;
/*     */ 
/*     */     
/* 141 */     recycleTopLevelGroup();
/*     */ 
/*     */     
/* 144 */     this.defaultGC = this.gcConverter.toSVG(gc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGroupManager(DOMGroupManager groupManager) {
/* 152 */     if (groupManager != null) {
/* 153 */       this.groupManagers.add(groupManager);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeGroupManager(DOMGroupManager groupManager) {
/* 161 */     if (groupManager != null) {
/* 162 */       this.groupManagers.remove(groupManager);
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
/*     */   public void appendGroup(Element group, DOMGroupManager groupManager) {
/* 174 */     this.topLevelGroup.appendChild(group);
/* 175 */     synchronized (this.groupManagers) {
/*     */ 
/*     */ 
/*     */       
/* 179 */       int nManagers = this.groupManagers.size();
/* 180 */       for (Object groupManager1 : this.groupManagers) {
/* 181 */         DOMGroupManager gm = (DOMGroupManager)groupManager1;
/* 182 */         if (gm != groupManager) {
/* 183 */           gm.recycleCurrentGroup();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void recycleTopLevelGroup() {
/* 192 */     recycleTopLevelGroup(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void recycleTopLevelGroup(boolean recycleConverters) {
/* 201 */     synchronized (this.groupManagers) {
/*     */ 
/*     */ 
/*     */       
/* 205 */       int nManagers = this.groupManagers.size();
/* 206 */       for (Object groupManager : this.groupManagers) {
/* 207 */         DOMGroupManager gm = (DOMGroupManager)groupManager;
/* 208 */         gm.recycleCurrentGroup();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 213 */     this.topLevelGroup = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "g");
/*     */ 
/*     */ 
/*     */     
/* 217 */     if (recycleConverters) {
/* 218 */       this.filterConverter = new SVGBufferedImageOp(this.generatorContext);
/*     */       
/* 220 */       this.gcConverter = new SVGGraphicContextConverter(this.generatorContext);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTopLevelGroup(Element topLevelGroup) {
/* 231 */     if (topLevelGroup == null) {
/* 232 */       throw new SVGGraphics2DRuntimeException("topLevelGroup should not be null");
/*     */     }
/* 234 */     if (!"g".equalsIgnoreCase(topLevelGroup.getTagName())) {
/* 235 */       throw new SVGGraphics2DRuntimeException("topLevelGroup should be a group <g>");
/*     */     }
/* 237 */     recycleTopLevelGroup(false);
/* 238 */     this.topLevelGroup = topLevelGroup;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getRoot() {
/* 246 */     return getRoot(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getRoot(Element svgElement) {
/* 254 */     Element svg = svgElement;
/*     */     
/* 256 */     if (svg == null) {
/* 257 */       svg = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "svg");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 262 */     if (this.gcConverter.getCompositeConverter().getAlphaCompositeConverter().requiresBackgroundAccess())
/*     */     {
/* 264 */       svg.setAttributeNS((String)null, "enable-background", "new");
/*     */     }
/*     */     
/* 267 */     if (this.generatorContext.generatorComment != null) {
/* 268 */       Comment generatorComment = this.generatorContext.domFactory.createComment(this.generatorContext.generatorComment);
/*     */       
/* 270 */       svg.appendChild(generatorComment);
/*     */     } 
/*     */ 
/*     */     
/* 274 */     applyDefaultRenderingStyle(svg);
/*     */     
/* 276 */     svg.appendChild(getGenericDefinitions());
/* 277 */     svg.appendChild(getTopLevelGroup());
/*     */     
/* 279 */     return svg;
/*     */   }
/*     */   
/*     */   public void applyDefaultRenderingStyle(Element element) {
/* 283 */     Map groupDefaults = this.defaultGC.getGroupContext();
/* 284 */     this.generatorContext.styleHandler.setStyle(element, groupDefaults, this.generatorContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getGenericDefinitions() {
/* 294 */     Element genericDefs = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "defs");
/*     */ 
/*     */     
/* 297 */     for (Object aGenericDefSet : this.genericDefSet) {
/* 298 */       genericDefs.appendChild((Element)aGenericDefSet);
/*     */     }
/*     */     
/* 301 */     genericDefs.setAttributeNS((String)null, "id", "genericDefs");
/* 302 */     return genericDefs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtensionHandler getExtensionHandler() {
/* 309 */     return this.generatorContext.getExtensionHandler();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setExtensionHandler(ExtensionHandler extensionHandler) {
/* 318 */     this.generatorContext.setExtensionHandler(extensionHandler);
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
/*     */   public List getDefinitionSet() {
/* 331 */     List defSet = this.gcConverter.getDefinitionSet();
/* 332 */     defSet.removeAll(this.genericDefSet);
/* 333 */     defSet.addAll(this.filterConverter.getDefinitionSet());
/* 334 */     if (this.otherDefs != null) {
/* 335 */       defSet.addAll(this.otherDefs);
/* 336 */       this.otherDefs = null;
/*     */     } 
/*     */ 
/*     */     
/* 340 */     this.filterConverter = new SVGBufferedImageOp(this.generatorContext);
/* 341 */     this.gcConverter = new SVGGraphicContextConverter(this.generatorContext);
/*     */     
/* 343 */     return defSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addOtherDef(Element definition) {
/* 351 */     if (this.otherDefs == null) {
/* 352 */       this.otherDefs = new LinkedList();
/*     */     }
/*     */     
/* 355 */     this.otherDefs.add(definition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getTopLevelGroup() {
/* 366 */     boolean includeDefinitionSet = true;
/* 367 */     return getTopLevelGroup(includeDefinitionSet);
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
/*     */   public Element getTopLevelGroup(boolean includeDefinitionSet) {
/* 381 */     Element topLevelGroup = this.topLevelGroup;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 386 */     if (includeDefinitionSet) {
/* 387 */       List defSet = getDefinitionSet();
/* 388 */       if (defSet.size() > 0) {
/* 389 */         Element defElement = null;
/*     */         
/* 391 */         NodeList defsElements = topLevelGroup.getElementsByTagName("defs");
/*     */         
/* 393 */         if (defsElements.getLength() > 0) {
/* 394 */           defElement = (Element)defsElements.item(0);
/*     */         }
/* 396 */         if (defElement == null) {
/* 397 */           defElement = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "defs");
/*     */ 
/*     */ 
/*     */           
/* 401 */           defElement.setAttributeNS((String)null, "id", this.generatorContext.idGenerator.generateID("defs"));
/*     */ 
/*     */ 
/*     */           
/* 405 */           topLevelGroup.insertBefore(defElement, topLevelGroup.getFirstChild());
/*     */         } 
/*     */ 
/*     */         
/* 409 */         for (Object aDefSet : defSet) defElement.appendChild((Element)aDefSet);
/*     */       
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 416 */     recycleTopLevelGroup(false);
/*     */     
/* 418 */     return topLevelGroup;
/*     */   }
/*     */   
/*     */   public SVGBufferedImageOp getFilterConverter() {
/* 422 */     return this.filterConverter;
/*     */   }
/*     */   
/*     */   public SVGGraphicContextConverter getGraphicContextConverter() {
/* 426 */     return this.gcConverter;
/*     */   }
/*     */   
/*     */   SVGGeneratorContext getGeneratorContext() {
/* 430 */     return this.generatorContext;
/*     */   }
/*     */   
/*     */   Document getDOMFactory() {
/* 434 */     return this.generatorContext.domFactory;
/*     */   }
/*     */   
/*     */   StyleHandler getStyleHandler() {
/* 438 */     return this.generatorContext.styleHandler;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/DOMTreeManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */