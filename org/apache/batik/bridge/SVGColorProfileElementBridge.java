/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.color.ICC_Profile;
/*     */ import java.io.IOException;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.apache.batik.ext.awt.color.NamedProfileCache;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.apache.xmlgraphics.java2d.color.ICCColorSpaceWithIntent;
/*     */ import org.apache.xmlgraphics.java2d.color.RenderingIntent;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
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
/*     */ public class SVGColorProfileElementBridge
/*     */   extends AbstractSVGBridge
/*     */   implements ErrorConstants
/*     */ {
/*  48 */   public NamedProfileCache cache = new NamedProfileCache();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  54 */     return "color-profile";
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
/*     */   public ICCColorSpaceWithIntent createICCColorSpaceWithIntent(BridgeContext ctx, Element paintedElement, String iccProfileName) {
/*  74 */     ICCColorSpaceWithIntent cs = this.cache.request(iccProfileName.toLowerCase());
/*  75 */     if (cs != null) {
/*  76 */       return cs;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  81 */     Document doc = paintedElement.getOwnerDocument();
/*  82 */     NodeList list = doc.getElementsByTagNameNS("http://www.w3.org/2000/svg", "color-profile");
/*     */ 
/*     */     
/*  85 */     int n = list.getLength();
/*  86 */     Element profile = null;
/*  87 */     for (int i = 0; i < n; i++) {
/*  88 */       Node node = list.item(i);
/*  89 */       if (node.getNodeType() == 1) {
/*  90 */         Element profileNode = (Element)node;
/*  91 */         String nameAttr = profileNode.getAttributeNS(null, "name");
/*     */ 
/*     */         
/*  94 */         if (iccProfileName.equalsIgnoreCase(nameAttr)) {
/*  95 */           profile = profileNode;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 100 */     if (profile == null) {
/* 101 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 106 */     String href = XLinkSupport.getXLinkHref(profile);
/* 107 */     ICC_Profile p = null;
/* 108 */     if (href != null) {
/* 109 */       String baseURI = ((AbstractNode)profile).getBaseURI();
/* 110 */       ParsedURL pDocURL = null;
/* 111 */       if (baseURI != null) {
/* 112 */         pDocURL = new ParsedURL(baseURI);
/*     */       }
/*     */       
/* 115 */       ParsedURL purl = new ParsedURL(pDocURL, href);
/* 116 */       if (!purl.complete()) {
/* 117 */         BridgeException be = new BridgeException(ctx, paintedElement, "uri.malformed", new Object[] { href });
/*     */         
/* 119 */         ctx.getUserAgent().displayError(be);
/* 120 */         return null;
/*     */       } 
/*     */       try {
/* 123 */         ctx.getUserAgent().checkLoadExternalResource(purl, pDocURL);
/* 124 */         p = ICC_Profile.getInstance(purl.openStream());
/* 125 */       } catch (IOException ioEx) {
/* 126 */         BridgeException be = new BridgeException(ctx, paintedElement, ioEx, "uri.io", new Object[] { href });
/*     */         
/* 128 */         ctx.getUserAgent().displayError(be);
/* 129 */         return null;
/*     */       }
/* 131 */       catch (SecurityException secEx) {
/* 132 */         BridgeException be = new BridgeException(ctx, paintedElement, secEx, "uri.unsecure", new Object[] { href });
/*     */ 
/*     */         
/* 135 */         ctx.getUserAgent().displayError(be);
/* 136 */         return null;
/*     */       } 
/*     */     } 
/* 139 */     if (p == null) {
/* 140 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 144 */     RenderingIntent intent = convertIntent(profile, ctx);
/* 145 */     cs = new ICCColorSpaceWithIntent(p, intent, href, iccProfileName);
/*     */ 
/*     */     
/* 148 */     this.cache.put(iccProfileName.toLowerCase(), cs);
/* 149 */     return cs;
/*     */   }
/*     */ 
/*     */   
/*     */   private static RenderingIntent convertIntent(Element profile, BridgeContext ctx) {
/* 154 */     String intent = profile.getAttributeNS(null, "rendering-intent");
/*     */ 
/*     */     
/* 157 */     if (intent.length() == 0) {
/* 158 */       return RenderingIntent.AUTO;
/*     */     }
/* 160 */     if ("perceptual".equals(intent)) {
/* 161 */       return RenderingIntent.PERCEPTUAL;
/*     */     }
/* 163 */     if ("auto".equals(intent)) {
/* 164 */       return RenderingIntent.AUTO;
/*     */     }
/* 166 */     if ("relative-colorimetric".equals(intent)) {
/* 167 */       return RenderingIntent.RELATIVE_COLORIMETRIC;
/*     */     }
/* 169 */     if ("absolute-colorimetric".equals(intent)) {
/* 170 */       return RenderingIntent.ABSOLUTE_COLORIMETRIC;
/*     */     }
/* 172 */     if ("saturation".equals(intent)) {
/* 173 */       return RenderingIntent.SATURATION;
/*     */     }
/* 175 */     throw new BridgeException(ctx, profile, "attribute.malformed", new Object[] { "rendering-intent", intent });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGColorProfileElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */