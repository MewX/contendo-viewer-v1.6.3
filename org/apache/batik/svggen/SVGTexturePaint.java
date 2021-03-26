/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.TexturePaint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import org.apache.batik.ext.awt.g2d.GraphicContext;
/*     */ import org.w3c.dom.Document;
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
/*     */ public class SVGTexturePaint
/*     */   extends AbstractSVGConverter
/*     */ {
/*     */   public SVGTexturePaint(SVGGeneratorContext generatorContext) {
/*  43 */     super(generatorContext);
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
/*     */   public SVGDescriptor toSVG(GraphicContext gc) {
/*  57 */     return toSVG((TexturePaint)gc.getPaint());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPaintDescriptor toSVG(TexturePaint texture) {
/*  68 */     SVGPaintDescriptor patternDesc = (SVGPaintDescriptor)this.descMap.get(texture);
/*  69 */     Document domFactory = this.generatorContext.domFactory;
/*     */     
/*  71 */     if (patternDesc == null) {
/*  72 */       Rectangle2D anchorRect = texture.getAnchorRect();
/*  73 */       Element patternDef = domFactory.createElementNS("http://www.w3.org/2000/svg", "pattern");
/*     */       
/*  75 */       patternDef.setAttributeNS((String)null, "patternUnits", "userSpaceOnUse");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  81 */       patternDef.setAttributeNS((String)null, "x", doubleString(anchorRect.getX()));
/*     */       
/*  83 */       patternDef.setAttributeNS((String)null, "y", doubleString(anchorRect.getY()));
/*     */       
/*  85 */       patternDef.setAttributeNS((String)null, "width", doubleString(anchorRect.getWidth()));
/*     */       
/*  87 */       patternDef.setAttributeNS((String)null, "height", doubleString(anchorRect.getHeight()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  93 */       BufferedImage textureImage = texture.getImage();
/*     */ 
/*     */ 
/*     */       
/*  97 */       if (textureImage.getWidth() > 0 && textureImage.getHeight() > 0)
/*     */       {
/*     */ 
/*     */         
/* 101 */         if (textureImage.getWidth() != anchorRect.getWidth() || textureImage.getHeight() != anchorRect.getHeight())
/*     */         {
/*     */ 
/*     */           
/* 105 */           if (anchorRect.getWidth() > 0.0D && anchorRect.getHeight() > 0.0D) {
/*     */             
/* 107 */             double scaleX = anchorRect.getWidth() / textureImage.getWidth();
/*     */             
/* 109 */             double scaleY = anchorRect.getHeight() / textureImage.getHeight();
/*     */             
/* 111 */             BufferedImage newImage = new BufferedImage((int)(scaleX * textureImage.getWidth()), (int)(scaleY * textureImage.getHeight()), 2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 118 */             Graphics2D g = newImage.createGraphics();
/* 119 */             g.scale(scaleX, scaleY);
/* 120 */             g.drawImage(textureImage, 0, 0, (ImageObserver)null);
/* 121 */             g.dispose();
/*     */             
/* 123 */             textureImage = newImage;
/*     */           } 
/*     */         }
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 132 */       Element patternContent = this.generatorContext.genericImageHandler.createElement(this.generatorContext);
/*     */ 
/*     */ 
/*     */       
/* 136 */       this.generatorContext.genericImageHandler.handleImage(textureImage, patternContent, 0, 0, textureImage.getWidth(), textureImage.getHeight(), this.generatorContext);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 145 */       patternDef.appendChild(patternContent);
/*     */       
/* 147 */       patternDef.setAttributeNS((String)null, "id", this.generatorContext.idGenerator.generateID("pattern"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 155 */       String patternAttrBuf = "url(#" + patternDef.getAttributeNS((String)null, "id") + ")";
/*     */ 
/*     */ 
/*     */       
/* 159 */       patternDesc = new SVGPaintDescriptor(patternAttrBuf, "1", patternDef);
/*     */       
/* 161 */       this.descMap.put(texture, patternDesc);
/* 162 */       this.defSet.add(patternDef);
/*     */     } 
/*     */     
/* 165 */     return patternDesc;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGTexturePaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */