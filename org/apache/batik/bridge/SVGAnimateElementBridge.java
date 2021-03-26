/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.anim.AbstractAnimation;
/*     */ import org.apache.batik.anim.SimpleAnimation;
/*     */ import org.apache.batik.anim.dom.AnimationTarget;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
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
/*     */ public class SVGAnimateElementBridge
/*     */   extends SVGAnimationElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  43 */     return "animate";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  50 */     return new SVGAnimateElementBridge();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractAnimation createAnimation(AnimationTarget target) {
/*  57 */     AnimatableValue from = parseAnimatableValue("from");
/*  58 */     AnimatableValue to = parseAnimatableValue("to");
/*  59 */     AnimatableValue by = parseAnimatableValue("by");
/*  60 */     return (AbstractAnimation)new SimpleAnimation(this.timedElement, this, parseCalcMode(), parseKeyTimes(), parseKeySplines(), parseAdditive(), parseAccumulate(), parseValues(), from, to, by);
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
/*     */   protected int parseCalcMode() {
/*  79 */     if ((this.animationType == 1 && !this.targetElement.isPropertyAdditive(this.attributeLocalName)) || (this.animationType == 0 && !this.targetElement.isAttributeAdditive(this.attributeNamespaceURI, this.attributeLocalName)))
/*     */     {
/*     */ 
/*     */ 
/*     */       
/*  84 */       return 0;
/*     */     }
/*     */     
/*  87 */     String calcModeString = this.element.getAttributeNS(null, "calcMode");
/*     */     
/*  89 */     if (calcModeString.length() == 0)
/*  90 */       return getDefaultCalcMode(); 
/*  91 */     if (calcModeString.equals("linear"))
/*  92 */       return 1; 
/*  93 */     if (calcModeString.equals("discrete"))
/*  94 */       return 0; 
/*  95 */     if (calcModeString.equals("paced"))
/*  96 */       return 2; 
/*  97 */     if (calcModeString.equals("spline")) {
/*  98 */       return 3;
/*     */     }
/* 100 */     throw new BridgeException(this.ctx, this.element, "attribute.malformed", new Object[] { "calcMode", calcModeString });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean parseAdditive() {
/* 109 */     String additiveString = this.element.getAttributeNS(null, "additive");
/*     */     
/* 111 */     if (additiveString.length() == 0 || additiveString.equals("replace"))
/*     */     {
/* 113 */       return false; } 
/* 114 */     if (additiveString.equals("sum")) {
/* 115 */       return true;
/*     */     }
/* 117 */     throw new BridgeException(this.ctx, this.element, "attribute.malformed", new Object[] { "additive", additiveString });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean parseAccumulate() {
/* 126 */     String accumulateString = this.element.getAttributeNS(null, "accumulate");
/*     */     
/* 128 */     if (accumulateString.length() == 0 || accumulateString.equals("none"))
/*     */     {
/* 130 */       return false; } 
/* 131 */     if (accumulateString.equals("sum")) {
/* 132 */       return true;
/*     */     }
/* 134 */     throw new BridgeException(this.ctx, this.element, "attribute.malformed", new Object[] { "accumulate", accumulateString });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimatableValue[] parseValues() {
/* 143 */     boolean isCSS = (this.animationType == 1);
/* 144 */     String valuesString = this.element.getAttributeNS(null, "values");
/*     */     
/* 146 */     int len = valuesString.length();
/* 147 */     if (len == 0) {
/* 148 */       return null;
/*     */     }
/* 150 */     ArrayList<AnimatableValue> values = new ArrayList(7);
/* 151 */     int i = 0, start = 0;
/*     */     
/* 153 */     label29: while (i < len) {
/* 154 */       while (valuesString.charAt(i) == ' ') {
/* 155 */         i++;
/* 156 */         if (i == len) {
/*     */           break label29;
/*     */         }
/*     */       } 
/* 160 */       start = i++;
/* 161 */       if (i != len) {
/* 162 */         char c = valuesString.charAt(i);
/*     */         
/* 164 */         i++;
/* 165 */         while (c != ';' && i != len)
/*     */         {
/*     */           
/* 168 */           c = valuesString.charAt(i);
/*     */         }
/*     */       } 
/* 171 */       int end = i++;
/* 172 */       AnimatableValue val = this.eng.parseAnimatableValue((Element)this.element, this.animationTarget, this.attributeNamespaceURI, this.attributeLocalName, isCSS, valuesString.substring(start, end));
/*     */ 
/*     */       
/* 175 */       if (!checkValueType(val)) {
/* 176 */         throw new BridgeException(this.ctx, this.element, "attribute.malformed", new Object[] { "values", valuesString });
/*     */       }
/*     */ 
/*     */       
/* 180 */       values.add(val);
/*     */     } 
/* 182 */     AnimatableValue[] ret = new AnimatableValue[values.size()];
/* 183 */     return values.<AnimatableValue>toArray(ret);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float[] parseKeyTimes() {
/* 190 */     String keyTimesString = this.element.getAttributeNS(null, "keyTimes");
/*     */     
/* 192 */     int len = keyTimesString.length();
/* 193 */     if (len == 0) {
/* 194 */       return null;
/*     */     }
/* 196 */     ArrayList<Float> keyTimes = new ArrayList(7);
/* 197 */     int i = 0, start = 0;
/*     */     
/* 199 */     label33: while (i < len) {
/* 200 */       while (keyTimesString.charAt(i) == ' ') {
/* 201 */         i++;
/* 202 */         if (i == len) {
/*     */           break label33;
/*     */         }
/*     */       } 
/* 206 */       start = i++;
/* 207 */       if (i != len) {
/* 208 */         char c = keyTimesString.charAt(i);
/*     */         
/* 210 */         i++;
/* 211 */         while (c != ' ' && c != ';' && i != len)
/*     */         {
/*     */           
/* 214 */           c = keyTimesString.charAt(i);
/*     */         }
/*     */       } 
/* 217 */       int end = i++;
/*     */       try {
/* 219 */         float keyTime = Float.parseFloat(keyTimesString.substring(start, end));
/*     */         
/* 221 */         keyTimes.add(Float.valueOf(keyTime));
/* 222 */       } catch (NumberFormatException nfEx) {
/* 223 */         throw new BridgeException(this.ctx, this.element, nfEx, "attribute.malformed", new Object[] { "keyTimes", keyTimesString });
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 228 */     len = keyTimes.size();
/* 229 */     float[] ret = new float[len];
/* 230 */     for (int j = 0; j < len; j++) {
/* 231 */       ret[j] = ((Float)keyTimes.get(j)).floatValue();
/*     */     }
/* 233 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float[] parseKeySplines() {
/* 240 */     String keySplinesString = this.element.getAttributeNS(null, "keySplines");
/*     */     
/* 242 */     int len = keySplinesString.length();
/* 243 */     if (len == 0) {
/* 244 */       return null;
/*     */     }
/* 246 */     List<Float> keySplines = new ArrayList(7);
/* 247 */     int count = 0, i = 0, start = 0;
/*     */     
/* 249 */     label51: while (i < len) {
/* 250 */       int end; while (keySplinesString.charAt(i) == ' ') {
/* 251 */         i++;
/* 252 */         if (i == len) {
/*     */           break label51;
/*     */         }
/*     */       } 
/* 256 */       start = i++;
/* 257 */       if (i != len) {
/* 258 */         char c = keySplinesString.charAt(i);
/*     */         
/* 260 */         i++;
/* 261 */         while (c != ' ' && c != ',' && c != ';' && i != len)
/*     */         {
/*     */           
/* 264 */           c = keySplinesString.charAt(i);
/*     */         }
/* 266 */         end = i++;
/* 267 */         if (c == ' ') {
/*     */           
/* 269 */           while (i != len)
/*     */           
/*     */           { 
/* 272 */             c = keySplinesString.charAt(i++);
/* 273 */             if (c != ' ')
/* 274 */               break;  }  if (c != ';' && c != ',') {
/* 275 */             i--;
/*     */           }
/*     */         } 
/* 278 */         if (c == ';') {
/* 279 */           if (count == 3) {
/* 280 */             count = 0;
/*     */           } else {
/* 282 */             throw new BridgeException(this.ctx, this.element, "attribute.malformed", new Object[] { "keySplines", keySplinesString });
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 289 */           count++;
/*     */         } 
/*     */       } else {
/* 292 */         end = i++;
/*     */       } 
/*     */       try {
/* 295 */         float keySplineValue = Float.parseFloat(keySplinesString.substring(start, end));
/*     */         
/* 297 */         keySplines.add(Float.valueOf(keySplineValue));
/* 298 */       } catch (NumberFormatException nfEx) {
/* 299 */         throw new BridgeException(this.ctx, this.element, nfEx, "attribute.malformed", new Object[] { "keySplines", keySplinesString });
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 304 */     len = keySplines.size();
/* 305 */     float[] ret = new float[len];
/* 306 */     for (int j = 0; j < len; j++) {
/* 307 */       ret[j] = ((Float)keySplines.get(j)).floatValue();
/*     */     }
/* 309 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDefaultCalcMode() {
/* 316 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canAnimateType(int type) {
/* 325 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGAnimateElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */