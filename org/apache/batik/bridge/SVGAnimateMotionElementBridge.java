/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.anim.AbstractAnimation;
/*     */ import org.apache.batik.anim.MotionAnimation;
/*     */ import org.apache.batik.anim.dom.AnimationTarget;
/*     */ import org.apache.batik.anim.dom.SVGOMElement;
/*     */ import org.apache.batik.anim.dom.SVGOMPathElement;
/*     */ import org.apache.batik.anim.values.AnimatableMotionPointValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.dom.svg.SVGAnimatedPathDataSupport;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.apache.batik.ext.awt.geom.ExtendedGeneralPath;
/*     */ import org.apache.batik.parser.AWTPathProducer;
/*     */ import org.apache.batik.parser.AngleHandler;
/*     */ import org.apache.batik.parser.AngleParser;
/*     */ import org.apache.batik.parser.LengthArrayProducer;
/*     */ import org.apache.batik.parser.LengthListHandler;
/*     */ import org.apache.batik.parser.LengthPairListParser;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.apache.batik.parser.PathHandler;
/*     */ import org.apache.batik.parser.PathParser;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGAnimateMotionElementBridge
/*     */   extends SVGAnimateElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  59 */     return "animateMotion";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  66 */     return new SVGAnimateMotionElementBridge();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractAnimation createAnimation(AnimationTarget target) {
/*  73 */     this.animationType = 2;
/*  74 */     this.attributeLocalName = "motion";
/*     */     
/*  76 */     AnimatableValue from = parseLengthPair("from");
/*  77 */     AnimatableValue to = parseLengthPair("to");
/*  78 */     AnimatableValue by = parseLengthPair("by");
/*     */     
/*  80 */     boolean rotateAuto = false, rotateAutoReverse = false;
/*  81 */     float rotateAngle = 0.0F;
/*  82 */     short rotateAngleUnit = 0;
/*  83 */     String rotateString = this.element.getAttributeNS(null, "rotate");
/*     */     
/*  85 */     if (rotateString.length() != 0)
/*  86 */       if (rotateString.equals("auto"))
/*  87 */       { rotateAuto = true; }
/*  88 */       else if (rotateString.equals("auto-reverse"))
/*  89 */       { rotateAuto = true;
/*  90 */         rotateAutoReverse = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */          }
/*     */       
/*     */       else
/*     */       
/*     */       { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 112 */         AngleParser ap = new AngleParser();
/* 113 */         Handler h = new Handler();
/* 114 */         ap.setAngleHandler(h);
/*     */         try {
/* 116 */           ap.parse(rotateString);
/* 117 */         } catch (ParseException pEx) {
/* 118 */           throw new BridgeException(this.ctx, this.element, pEx, "attribute.malformed", new Object[] { "rotate", rotateString });
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 123 */         rotateAngle = h.theAngle;
/* 124 */         rotateAngleUnit = h.theUnit; }   class Handler implements AngleHandler {
/*     */       float theAngle;
/*     */       short theUnit = 1;
/* 127 */       public void startAngle() throws ParseException {} public void angleValue(float v) throws ParseException { this.theAngle = v; } public void deg() throws ParseException { this.theUnit = 2; } public void grad() throws ParseException { this.theUnit = 4; } public void rad() throws ParseException { this.theUnit = 3; } public void endAngle() throws ParseException {} }; return (AbstractAnimation)new MotionAnimation(this.timedElement, this, parseCalcMode(), parseKeyTimes(), parseKeySplines(), parseAdditive(), parseAccumulate(), parseValues(), from, to, by, parsePath(), parseKeyPoints(), rotateAuto, rotateAutoReverse, rotateAngle, rotateAngleUnit);
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
/*     */   protected ExtendedGeneralPath parsePath() {
/* 151 */     Node n = this.element.getFirstChild();
/* 152 */     while (n != null) {
/* 153 */       if (n.getNodeType() == 1 && "http://www.w3.org/2000/svg".equals(n.getNamespaceURI()) && "mpath".equals(n.getLocalName())) {
/*     */ 
/*     */         
/* 156 */         String uri = XLinkSupport.getXLinkHref((Element)n);
/* 157 */         Element path = this.ctx.getReferencedElement((Element)this.element, uri);
/* 158 */         if (!"http://www.w3.org/2000/svg".equals(path.getNamespaceURI()) || !"path".equals(path.getLocalName()))
/*     */         {
/* 160 */           throw new BridgeException(this.ctx, this.element, "uri.badTarget", new Object[] { uri });
/*     */         }
/*     */ 
/*     */         
/* 164 */         SVGOMPathElement pathElt = (SVGOMPathElement)path;
/* 165 */         AWTPathProducer app = new AWTPathProducer();
/* 166 */         SVGAnimatedPathDataSupport.handlePathSegList(pathElt.getPathSegList(), (PathHandler)app);
/*     */         
/* 168 */         return (ExtendedGeneralPath)app.getShape();
/*     */       } 
/* 170 */       n = n.getNextSibling();
/*     */     } 
/* 172 */     String pathString = this.element.getAttributeNS(null, "path");
/* 173 */     if (pathString.length() == 0) {
/* 174 */       return null;
/*     */     }
/*     */     try {
/* 177 */       AWTPathProducer app = new AWTPathProducer();
/* 178 */       PathParser pp = new PathParser();
/* 179 */       pp.setPathHandler((PathHandler)app);
/* 180 */       pp.parse(pathString);
/* 181 */       return (ExtendedGeneralPath)app.getShape();
/* 182 */     } catch (ParseException pEx) {
/* 183 */       throw new BridgeException(this.ctx, this.element, pEx, "attribute.malformed", new Object[] { "path", pathString });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float[] parseKeyPoints() {
/* 193 */     String keyPointsString = this.element.getAttributeNS(null, "keyPoints");
/*     */     
/* 195 */     int len = keyPointsString.length();
/* 196 */     if (len == 0) {
/* 197 */       return null;
/*     */     }
/* 199 */     List<Float> keyPoints = new ArrayList(7);
/* 200 */     int i = 0, start = 0;
/*     */     
/* 202 */     label34: while (i < len) {
/* 203 */       while (keyPointsString.charAt(i) == ' ') {
/* 204 */         i++;
/* 205 */         if (i == len) {
/*     */           break label34;
/*     */         }
/*     */       } 
/* 209 */       start = i++;
/* 210 */       if (i != len) {
/* 211 */         char c = keyPointsString.charAt(i);
/*     */         
/* 213 */         i++;
/* 214 */         while (c != ' ' && c != ';' && c != ',' && i != len)
/*     */         {
/*     */           
/* 217 */           c = keyPointsString.charAt(i);
/*     */         }
/*     */       } 
/* 220 */       int end = i++;
/*     */       try {
/* 222 */         float keyPointCoord = Float.parseFloat(keyPointsString.substring(start, end));
/*     */         
/* 224 */         keyPoints.add(Float.valueOf(keyPointCoord));
/* 225 */       } catch (NumberFormatException nfEx) {
/* 226 */         throw new BridgeException(this.ctx, this.element, nfEx, "attribute.malformed", new Object[] { "keyPoints", keyPointsString });
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 231 */     len = keyPoints.size();
/* 232 */     float[] ret = new float[len];
/* 233 */     for (int j = 0; j < len; j++) {
/* 234 */       ret[j] = ((Float)keyPoints.get(j)).floatValue();
/*     */     }
/* 236 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDefaultCalcMode() {
/* 243 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimatableValue[] parseValues() {
/* 250 */     String valuesString = this.element.getAttributeNS(null, "values");
/*     */     
/* 252 */     int len = valuesString.length();
/* 253 */     if (len == 0) {
/* 254 */       return null;
/*     */     }
/* 256 */     return parseValues(valuesString);
/*     */   }
/*     */   
/*     */   protected AnimatableValue[] parseValues(String s) {
/*     */     try {
/* 261 */       LengthPairListParser lplp = new LengthPairListParser();
/* 262 */       LengthArrayProducer lap = new LengthArrayProducer();
/* 263 */       lplp.setLengthListHandler((LengthListHandler)lap);
/* 264 */       lplp.parse(s);
/* 265 */       short[] types = lap.getLengthTypeArray();
/* 266 */       float[] values = lap.getLengthValueArray();
/* 267 */       AnimatableValue[] ret = new AnimatableValue[types.length / 2];
/* 268 */       for (int i = 0; i < types.length; i += 2) {
/* 269 */         float x = this.animationTarget.svgToUserSpace(values[i], types[i], (short)1);
/*     */         
/* 271 */         float y = this.animationTarget.svgToUserSpace(values[i + 1], types[i + 1], (short)2);
/*     */         
/* 273 */         ret[i / 2] = (AnimatableValue)new AnimatableMotionPointValue(this.animationTarget, x, y, 0.0F);
/*     */       } 
/* 275 */       return ret;
/* 276 */     } catch (ParseException pEx) {
/* 277 */       throw new BridgeException(this.ctx, this.element, pEx, "attribute.malformed", new Object[] { "values", s });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimatableValue parseLengthPair(String ln) {
/* 287 */     String s = this.element.getAttributeNS(null, ln);
/* 288 */     if (s.length() == 0) {
/* 289 */       return null;
/*     */     }
/* 291 */     return parseValues(s)[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getUnderlyingValue() {
/* 301 */     return (AnimatableValue)new AnimatableMotionPointValue(this.animationTarget, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAnimation() {
/*     */     Node t;
/* 310 */     String uri = XLinkSupport.getXLinkHref((Element)this.element);
/*     */     
/* 312 */     if (uri.length() == 0) {
/* 313 */       t = this.element.getParentNode();
/*     */     } else {
/* 315 */       t = this.ctx.getReferencedElement((Element)this.element, uri);
/* 316 */       if (t.getOwnerDocument() != this.element.getOwnerDocument()) {
/* 317 */         throw new BridgeException(this.ctx, this.element, "uri.badTarget", new Object[] { uri });
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 322 */     this.animationTarget = null;
/* 323 */     if (t instanceof SVGOMElement) {
/* 324 */       this.targetElement = (SVGOMElement)t;
/* 325 */       this.animationTarget = (AnimationTarget)this.targetElement;
/*     */     } 
/* 327 */     if (this.animationTarget == null) {
/* 328 */       throw new BridgeException(this.ctx, this.element, "uri.badTarget", new Object[] { uri });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 334 */     this.timedElement = createTimedElement();
/* 335 */     this.animation = createAnimation(this.animationTarget);
/* 336 */     this.eng.addAnimation(this.animationTarget, (short)2, this.attributeNamespaceURI, this.attributeLocalName, this.animation);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGAnimateMotionElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */