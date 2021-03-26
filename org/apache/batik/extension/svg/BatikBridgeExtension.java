/*     */ package org.apache.batik.extension.svg;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.batik.bridge.Bridge;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.BridgeExtension;
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
/*     */ public class BatikBridgeExtension
/*     */   implements BridgeExtension
/*     */ {
/*     */   public float getPriority() {
/*  45 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator getImplementedExtensions() {
/*  54 */     String[] extensions = { "http://xml.apache.org/batik/ext/poly/1.0", "http://xml.apache.org/batik/ext/star/1.0", "http://xml.apache.org/batik/ext/histogramNormalization/1.0", "http://xml.apache.org/batik/ext/colorSwitch/1.0", "http://xml.apache.org/batik/ext/flowText/1.0" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  65 */     List<String> v = Arrays.asList(extensions);
/*  66 */     return Collections.<String>unmodifiableList(v).iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAuthor() {
/*  74 */     return "Thomas DeWeese";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContactAddress() {
/*  81 */     return "deweese@apache.org";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getURL() {
/*  89 */     return "http://xml.apache.org/batik";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  98 */     return "Example extension to standard SVG shape tags";
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
/*     */   public void registerTags(BridgeContext ctx) {
/* 110 */     ctx.putBridge((Bridge)new BatikRegularPolygonElementBridge());
/* 111 */     ctx.putBridge((Bridge)new BatikStarElementBridge());
/* 112 */     ctx.putBridge((Bridge)new BatikHistogramNormalizationElementBridge());
/* 113 */     ctx.putBridge((Bridge)new BatikFlowTextElementBridge());
/* 114 */     ctx.putBridge((Bridge)new ColorSwitchBridge());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDynamicElement(Element e) {
/* 125 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/BatikBridgeExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */