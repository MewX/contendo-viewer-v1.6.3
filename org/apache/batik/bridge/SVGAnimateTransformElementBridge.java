/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.apache.batik.anim.AbstractAnimation;
/*     */ import org.apache.batik.anim.TransformAnimation;
/*     */ import org.apache.batik.anim.dom.AnimationTarget;
/*     */ import org.apache.batik.anim.values.AnimatableTransformListValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.dom.svg.AbstractSVGTransform;
/*     */ import org.apache.batik.dom.svg.SVGOMTransform;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGAnimateTransformElementBridge
/*     */   extends SVGAnimateElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  45 */     return "animateTransform";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  52 */     return new SVGAnimateTransformElementBridge();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractAnimation createAnimation(AnimationTarget target) {
/*  59 */     short type = parseType();
/*  60 */     AnimatableValue from = null, to = null, by = null;
/*  61 */     if (this.element.hasAttributeNS(null, "from")) {
/*  62 */       from = parseValue(this.element.getAttributeNS(null, "from"), type, target);
/*     */     }
/*     */     
/*  65 */     if (this.element.hasAttributeNS(null, "to")) {
/*  66 */       to = parseValue(this.element.getAttributeNS(null, "to"), type, target);
/*     */     }
/*     */     
/*  69 */     if (this.element.hasAttributeNS(null, "by")) {
/*  70 */       by = parseValue(this.element.getAttributeNS(null, "by"), type, target);
/*     */     }
/*     */     
/*  73 */     return (AbstractAnimation)new TransformAnimation(this.timedElement, this, parseCalcMode(), parseKeyTimes(), parseKeySplines(), parseAdditive(), parseAccumulate(), parseValues(type, target), from, to, by, type);
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
/*     */   protected short parseType() {
/*  91 */     String typeString = this.element.getAttributeNS(null, "type");
/*  92 */     if (typeString.equals("translate"))
/*  93 */       return 2; 
/*  94 */     if (typeString.equals("scale"))
/*  95 */       return 3; 
/*  96 */     if (typeString.equals("rotate"))
/*  97 */       return 4; 
/*  98 */     if (typeString.equals("skewX"))
/*  99 */       return 5; 
/* 100 */     if (typeString.equals("skewY")) {
/* 101 */       return 6;
/*     */     }
/* 103 */     throw new BridgeException(this.ctx, this.element, "attribute.malformed", new Object[] { "type", typeString });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimatableValue parseValue(String s, short type, AnimationTarget target) {
/* 113 */     float val2 = 0.0F, val3 = 0.0F;
/* 114 */     int i = 0;
/* 115 */     char c = ',';
/* 116 */     int len = s.length();
/* 117 */     while (i < len) {
/* 118 */       c = s.charAt(i);
/* 119 */       if (c == ' ' || c == ',') {
/*     */         break;
/*     */       }
/* 122 */       i++;
/*     */     } 
/* 124 */     float val1 = Float.parseFloat(s.substring(0, i));
/* 125 */     if (i < len) {
/* 126 */       i++;
/*     */     }
/* 128 */     int count = 1;
/* 129 */     if (i < len && c == ' ') {
/* 130 */       while (i < len) {
/* 131 */         c = s.charAt(i);
/* 132 */         if (c != ' ') {
/*     */           break;
/*     */         }
/* 135 */         i++;
/*     */       } 
/* 137 */       if (c == ',') {
/* 138 */         i++;
/*     */       }
/*     */     } 
/* 141 */     while (i < len && s.charAt(i) == ' ') {
/* 142 */       i++;
/*     */     }
/* 144 */     int j = i;
/* 145 */     if (i < len && type != 5 && type != 6) {
/*     */ 
/*     */       
/* 148 */       while (i < len) {
/* 149 */         c = s.charAt(i);
/* 150 */         if (c == ' ' || c == ',') {
/*     */           break;
/*     */         }
/* 153 */         i++;
/*     */       } 
/* 155 */       val2 = Float.parseFloat(s.substring(j, i));
/* 156 */       if (i < len) {
/* 157 */         i++;
/*     */       }
/* 159 */       count++;
/* 160 */       if (i < len && c == ' ') {
/* 161 */         while (i < len) {
/* 162 */           c = s.charAt(i);
/* 163 */           if (c != ' ') {
/*     */             break;
/*     */           }
/* 166 */           i++;
/*     */         } 
/* 168 */         if (c == ',') {
/* 169 */           i++;
/*     */         }
/*     */       } 
/* 172 */       while (i < len && s.charAt(i) == ' ') {
/* 173 */         i++;
/*     */       }
/* 175 */       j = i;
/* 176 */       if (i < len && type == 4) {
/* 177 */         while (i < len) {
/* 178 */           c = s.charAt(i);
/* 179 */           if (c == ',' || c == ' ') {
/*     */             break;
/*     */           }
/* 182 */           i++;
/*     */         } 
/* 184 */         val3 = Float.parseFloat(s.substring(j, i));
/* 185 */         if (i < len) {
/* 186 */           i++;
/*     */         }
/* 188 */         count++;
/* 189 */         while (i < len && s.charAt(i) == ' ') {
/* 190 */           i++;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 195 */     if (i != len) {
/* 196 */       return null;
/*     */     }
/*     */     
/* 199 */     SVGOMTransform t = new SVGOMTransform();
/* 200 */     switch (type) {
/*     */       case 2:
/* 202 */         if (count == 2) {
/* 203 */           t.setTranslate(val1, val2); break;
/*     */         } 
/* 205 */         t.setTranslate(val1, 0.0F);
/*     */         break;
/*     */       
/*     */       case 3:
/* 209 */         if (count == 2) {
/* 210 */           t.setScale(val1, val2); break;
/*     */         } 
/* 212 */         t.setScale(val1, val1);
/*     */         break;
/*     */       
/*     */       case 4:
/* 216 */         if (count == 3) {
/* 217 */           t.setRotate(val1, val2, val3); break;
/*     */         } 
/* 219 */         t.setRotate(val1, 0.0F, 0.0F);
/*     */         break;
/*     */       
/*     */       case 5:
/* 223 */         t.setSkewX(val1);
/*     */         break;
/*     */       case 6:
/* 226 */         t.setSkewY(val1);
/*     */         break;
/*     */     } 
/* 229 */     return (AnimatableValue)new AnimatableTransformListValue(target, (AbstractSVGTransform)t);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimatableValue[] parseValues(short type, AnimationTarget target) {
/* 237 */     String valuesString = this.element.getAttributeNS(null, "values");
/*     */     
/* 239 */     int len = valuesString.length();
/* 240 */     if (len == 0) {
/* 241 */       return null;
/*     */     }
/* 243 */     ArrayList<AnimatableValue> values = new ArrayList(7);
/* 244 */     int i = 0, start = 0;
/*     */     
/* 246 */     label26: while (i < len) {
/* 247 */       while (valuesString.charAt(i) == ' ') {
/* 248 */         i++;
/* 249 */         if (i == len) {
/*     */           break label26;
/*     */         }
/*     */       } 
/* 253 */       start = i++;
/* 254 */       if (i < len) {
/* 255 */         char c = valuesString.charAt(i);
/*     */         
/* 257 */         i++;
/* 258 */         while (c != ';' && i != len)
/*     */         {
/*     */           
/* 261 */           c = valuesString.charAt(i);
/*     */         }
/*     */       } 
/* 264 */       int end = i++;
/* 265 */       String valueString = valuesString.substring(start, end);
/* 266 */       AnimatableValue value = parseValue(valueString, type, target);
/* 267 */       if (value == null) {
/* 268 */         throw new BridgeException(this.ctx, this.element, "attribute.malformed", new Object[] { "values", valuesString });
/*     */       }
/*     */ 
/*     */       
/* 272 */       values.add(value);
/*     */     } 
/* 274 */     AnimatableValue[] ret = new AnimatableValue[values.size()];
/* 275 */     return values.<AnimatableValue>toArray(ret);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canAnimateType(int type) {
/* 284 */     return (type == 9);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGAnimateTransformElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */