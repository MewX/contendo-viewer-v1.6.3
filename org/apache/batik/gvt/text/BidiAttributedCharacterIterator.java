/*     */ package org.apache.batik.gvt.text;
/*     */ 
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.TextLayout;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.AttributedString;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BidiAttributedCharacterIterator
/*     */   implements AttributedCharacterIterator
/*     */ {
/*     */   private AttributedCharacterIterator reorderedACI;
/*     */   private FontRenderContext frc;
/*     */   private int chunkStart;
/*     */   private int[] newCharOrder;
/*  45 */   private static final Float FLOAT_NAN = Float.valueOf(Float.NaN);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BidiAttributedCharacterIterator(AttributedCharacterIterator reorderedACI, FontRenderContext frc, int chunkStart, int[] newCharOrder) {
/*  53 */     this.reorderedACI = reorderedACI;
/*  54 */     this.frc = frc;
/*  55 */     this.chunkStart = chunkStart;
/*  56 */     this.newCharOrder = newCharOrder;
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
/*     */   public BidiAttributedCharacterIterator(AttributedCharacterIterator aci, FontRenderContext frc, int chunkStart) {
/*  72 */     this.frc = frc;
/*  73 */     this.chunkStart = chunkStart;
/*  74 */     aci.first();
/*  75 */     int numChars = aci.getEndIndex() - aci.getBeginIndex();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     StringBuffer strB = new StringBuffer(numChars);
/*  87 */     char c = aci.first();
/*  88 */     for (int i = 0; i < numChars; i++) {
/*  89 */       strB.append(c);
/*  90 */       c = aci.next();
/*     */     } 
/*  92 */     AttributedString as = new AttributedString(strB.toString());
/*  93 */     int start = aci.getBeginIndex();
/*  94 */     int end = aci.getEndIndex();
/*  95 */     int index = start;
/*  96 */     while (index < end) {
/*  97 */       aci.setIndex(index);
/*  98 */       Map<AttributedCharacterIterator.Attribute, Object> attrMap = aci.getAttributes();
/*  99 */       int extent = aci.getRunLimit();
/* 100 */       Map<Object, Object> destMap = new HashMap<Object, Object>(attrMap.size());
/* 101 */       for (Map.Entry<AttributedCharacterIterator.Attribute, Object> o : attrMap.entrySet()) {
/*     */ 
/*     */         
/* 104 */         Map.Entry e = o;
/* 105 */         Object key = e.getKey();
/* 106 */         if (key == null)
/* 107 */           continue;  Object value = e.getValue();
/* 108 */         if (value == null)
/* 109 */           continue;  destMap.put(key, value);
/*     */       } 
/*     */ 
/*     */       
/* 113 */       as.addAttributes((Map)destMap, index - start, extent - start);
/* 114 */       index = extent;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     TextLayout tl = new TextLayout(as.getIterator(), frc);
/*     */     
/* 122 */     int[] charIndices = new int[numChars];
/* 123 */     int[] charLevels = new int[numChars];
/*     */     
/* 125 */     int runStart = 0;
/* 126 */     int currBiDi = tl.getCharacterLevel(0);
/* 127 */     charIndices[0] = 0;
/* 128 */     charLevels[0] = currBiDi;
/* 129 */     int maxBiDi = currBiDi;
/*     */     int j;
/* 131 */     for (j = 1; j < numChars; j++) {
/* 132 */       int newBiDi = tl.getCharacterLevel(j);
/* 133 */       charIndices[j] = j;
/* 134 */       charLevels[j] = newBiDi;
/*     */       
/* 136 */       if (newBiDi != currBiDi) {
/* 137 */         as.addAttribute(GVTAttributedCharacterIterator.TextAttribute.BIDI_LEVEL, Integer.valueOf(currBiDi), runStart, j);
/*     */ 
/*     */         
/* 140 */         runStart = j;
/* 141 */         currBiDi = newBiDi;
/* 142 */         if (newBiDi > maxBiDi) maxBiDi = newBiDi; 
/*     */       } 
/*     */     } 
/* 145 */     as.addAttribute(GVTAttributedCharacterIterator.TextAttribute.BIDI_LEVEL, Integer.valueOf(currBiDi), runStart, numChars);
/*     */ 
/*     */ 
/*     */     
/* 149 */     aci = as.getIterator();
/*     */     
/* 151 */     if (runStart == 0 && currBiDi == 0) {
/*     */ 
/*     */ 
/*     */       
/* 155 */       this.reorderedACI = aci;
/* 156 */       this.newCharOrder = new int[numChars];
/* 157 */       for (j = 0; j < numChars; j++) {
/* 158 */         this.newCharOrder[j] = chunkStart + j;
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 163 */     this.newCharOrder = doBidiReorder(charIndices, charLevels, numChars, maxBiDi);
/*     */ 
/*     */ 
/*     */     
/* 167 */     StringBuffer reorderedString = new StringBuffer(numChars);
/* 168 */     int reorderedFirstChar = 0;
/* 169 */     for (int k = 0; k < numChars; k++) {
/* 170 */       int srcIdx = this.newCharOrder[k];
/* 171 */       char c1 = aci.setIndex(srcIdx);
/* 172 */       if (srcIdx == 0) reorderedFirstChar = k;
/*     */ 
/*     */       
/* 175 */       int bidiLevel = tl.getCharacterLevel(srcIdx);
/* 176 */       if ((bidiLevel & 0x1) != 0)
/*     */       {
/*     */ 
/*     */         
/* 180 */         c1 = (char)mirrorChar(c1);
/*     */       }
/*     */       
/* 183 */       reorderedString.append(c1);
/*     */     } 
/*     */ 
/*     */     
/* 187 */     AttributedString reorderedAS = new AttributedString(reorderedString.toString());
/*     */     
/* 189 */     Map[] attrs = new Map[numChars];
/* 190 */     int m = aci.getBeginIndex();
/* 191 */     int n = aci.getEndIndex();
/* 192 */     int i1 = m;
/* 193 */     while (i1 < n) {
/* 194 */       aci.setIndex(i1);
/* 195 */       Map<AttributedCharacterIterator.Attribute, Object> attrMap = aci.getAttributes();
/* 196 */       int extent = aci.getRunLimit();
/* 197 */       for (int i4 = i1; i4 < extent; i4++)
/* 198 */         attrs[i4 - m] = attrMap; 
/* 199 */       i1 = extent;
/*     */     } 
/*     */     
/* 202 */     runStart = 0;
/* 203 */     Map<? extends AttributedCharacterIterator.Attribute, ?> prevAttrMap = attrs[this.newCharOrder[0]];
/* 204 */     for (int i2 = 1; i2 < numChars; i2++) {
/* 205 */       Map<? extends AttributedCharacterIterator.Attribute, ?> attrMap = attrs[this.newCharOrder[i2]];
/* 206 */       if (attrMap != prevAttrMap) {
/*     */         
/* 208 */         reorderedAS.addAttributes(prevAttrMap, runStart, i2);
/* 209 */         prevAttrMap = attrMap;
/* 210 */         runStart = i2;
/*     */       } 
/*     */     } 
/* 213 */     reorderedAS.addAttributes(prevAttrMap, runStart, numChars);
/*     */ 
/*     */     
/* 216 */     aci.first();
/* 217 */     Float x = (Float)aci.getAttribute(GVTAttributedCharacterIterator.TextAttribute.X);
/*     */     
/* 219 */     if (x != null && !x.isNaN()) {
/* 220 */       reorderedAS.addAttribute(GVTAttributedCharacterIterator.TextAttribute.X, FLOAT_NAN, reorderedFirstChar, reorderedFirstChar + 1);
/*     */ 
/*     */       
/* 223 */       reorderedAS.addAttribute(GVTAttributedCharacterIterator.TextAttribute.X, x, 0, 1);
/*     */     } 
/*     */ 
/*     */     
/* 227 */     Float y = (Float)aci.getAttribute(GVTAttributedCharacterIterator.TextAttribute.Y);
/*     */     
/* 229 */     if (y != null && !y.isNaN()) {
/* 230 */       reorderedAS.addAttribute(GVTAttributedCharacterIterator.TextAttribute.Y, FLOAT_NAN, reorderedFirstChar, reorderedFirstChar + 1);
/*     */ 
/*     */       
/* 233 */       reorderedAS.addAttribute(GVTAttributedCharacterIterator.TextAttribute.Y, y, 0, 1);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 238 */     Float dx = (Float)aci.getAttribute(GVTAttributedCharacterIterator.TextAttribute.DX);
/*     */     
/* 240 */     if (dx != null && !dx.isNaN()) {
/* 241 */       reorderedAS.addAttribute(GVTAttributedCharacterIterator.TextAttribute.DX, FLOAT_NAN, reorderedFirstChar, reorderedFirstChar + 1);
/*     */ 
/*     */       
/* 244 */       reorderedAS.addAttribute(GVTAttributedCharacterIterator.TextAttribute.DX, dx, 0, 1);
/*     */     } 
/*     */     
/* 247 */     Float dy = (Float)aci.getAttribute(GVTAttributedCharacterIterator.TextAttribute.DY);
/*     */     
/* 249 */     if (dy != null && !dy.isNaN()) {
/* 250 */       reorderedAS.addAttribute(GVTAttributedCharacterIterator.TextAttribute.DY, FLOAT_NAN, reorderedFirstChar, reorderedFirstChar + 1);
/*     */ 
/*     */       
/* 253 */       reorderedAS.addAttribute(GVTAttributedCharacterIterator.TextAttribute.DY, dy, 0, 1);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 258 */     reorderedAS = ArabicTextHandler.assignArabicForms(reorderedAS);
/*     */ 
/*     */     
/* 261 */     for (int i3 = 0; i3 < this.newCharOrder.length; i3++) {
/* 262 */       this.newCharOrder[i3] = this.newCharOrder[i3] + chunkStart;
/*     */     }
/* 264 */     this.reorderedACI = reorderedAS.getIterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getCharMap() {
/* 269 */     return this.newCharOrder;
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
/*     */   private int[] doBidiReorder(int[] charIndices, int[] charLevels, int numChars, int highestLevel) {
/* 283 */     if (highestLevel == 0) return charIndices;
/*     */ 
/*     */ 
/*     */     
/* 287 */     int currentIndex = 0;
/* 288 */     while (currentIndex < numChars) {
/*     */ 
/*     */       
/* 291 */       while (currentIndex < numChars && charLevels[currentIndex] < highestLevel)
/*     */       {
/* 293 */         currentIndex++;
/*     */       }
/* 295 */       if (currentIndex == numChars) {
/*     */         break;
/*     */       }
/*     */       
/* 299 */       int startIndex = currentIndex;
/*     */       
/* 301 */       currentIndex++;
/*     */       
/* 303 */       while (currentIndex < numChars && charLevels[currentIndex] == highestLevel)
/*     */       {
/* 305 */         currentIndex++;
/*     */       }
/* 307 */       int endIndex = currentIndex - 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 314 */       int middle = (endIndex - startIndex >> 1) + 1;
/* 315 */       for (int i = 0; i < middle; i++) {
/* 316 */         int tmp = charIndices[startIndex + i];
/* 317 */         charIndices[startIndex + i] = charIndices[endIndex - i];
/* 318 */         charIndices[endIndex - i] = tmp;
/*     */         
/* 320 */         charLevels[startIndex + i] = highestLevel - 1;
/* 321 */         charLevels[endIndex - i] = highestLevel - 1;
/*     */       } 
/*     */     } 
/* 324 */     return doBidiReorder(charIndices, charLevels, numChars, highestLevel - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getAllAttributeKeys() {
/* 333 */     return this.reorderedACI.getAllAttributeKeys();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getAttribute(AttributedCharacterIterator.Attribute attribute) {
/* 341 */     return this.reorderedACI.getAttribute(attribute);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map getAttributes() {
/* 349 */     return this.reorderedACI.getAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunLimit() {
/* 358 */     return this.reorderedACI.getRunLimit();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunLimit(AttributedCharacterIterator.Attribute attribute) {
/* 367 */     return this.reorderedACI.getRunLimit(attribute);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunLimit(Set<? extends AttributedCharacterIterator.Attribute> attributes) {
/* 376 */     return this.reorderedACI.getRunLimit(attributes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunStart() {
/* 384 */     return this.reorderedACI.getRunStart();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunStart(AttributedCharacterIterator.Attribute attribute) {
/* 394 */     return this.reorderedACI.getRunStart(attribute);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunStart(Set<? extends AttributedCharacterIterator.Attribute> attributes) {
/* 403 */     return this.reorderedACI.getRunStart(attributes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 410 */     return new BidiAttributedCharacterIterator((AttributedCharacterIterator)this.reorderedACI.clone(), this.frc, this.chunkStart, (int[])this.newCharOrder.clone());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char current() {
/* 419 */     return this.reorderedACI.current();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char first() {
/* 427 */     return this.reorderedACI.first();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBeginIndex() {
/* 434 */     return this.reorderedACI.getBeginIndex();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEndIndex() {
/* 441 */     return this.reorderedACI.getEndIndex();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 448 */     return this.reorderedACI.getIndex();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char last() {
/* 456 */     return this.reorderedACI.last();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char next() {
/* 464 */     return this.reorderedACI.next();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char previous() {
/* 471 */     return this.reorderedACI.previous();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char setIndex(int position) {
/* 478 */     return this.reorderedACI.setIndex(position);
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
/*     */   public static int mirrorChar(int c) {
/* 491 */     switch (c) {
/*     */       case 40:
/* 493 */         return 41;
/* 494 */       case 41: return 40;
/* 495 */       case 60: return 62;
/* 496 */       case 62: return 60;
/* 497 */       case 91: return 93;
/* 498 */       case 93: return 91;
/* 499 */       case 123: return 125;
/* 500 */       case 125: return 123;
/* 501 */       case 171: return 187;
/* 502 */       case 187: return 171;
/* 503 */       case 8249: return 8250;
/* 504 */       case 8250: return 8249;
/* 505 */       case 8261: return 8262;
/* 506 */       case 8262: return 8261;
/* 507 */       case 8317: return 8318;
/* 508 */       case 8318: return 8317;
/* 509 */       case 8333: return 8334;
/* 510 */       case 8334: return 8333;
/* 511 */       case 8712: return 8715;
/* 512 */       case 8713: return 8716;
/* 513 */       case 8714: return 8717;
/* 514 */       case 8715: return 8712;
/* 515 */       case 8716: return 8713;
/* 516 */       case 8717: return 8714;
/* 517 */       case 8764: return 8765;
/* 518 */       case 8765: return 8764;
/* 519 */       case 8771: return 8909;
/* 520 */       case 8786: return 8787;
/* 521 */       case 8787: return 8786;
/* 522 */       case 8788: return 8789;
/* 523 */       case 8789: return 8788;
/* 524 */       case 8804: return 8805;
/* 525 */       case 8805: return 8804;
/* 526 */       case 8806: return 8807;
/* 527 */       case 8807: return 8806;
/* 528 */       case 8808: return 8809;
/* 529 */       case 8809: return 8808;
/* 530 */       case 8810: return 8811;
/* 531 */       case 8811: return 8810;
/* 532 */       case 8814: return 8815;
/* 533 */       case 8815: return 8814;
/* 534 */       case 8816: return 8817;
/* 535 */       case 8817: return 8816;
/* 536 */       case 8818: return 8819;
/* 537 */       case 8819: return 8818;
/* 538 */       case 8820: return 8821;
/* 539 */       case 8821: return 8820;
/* 540 */       case 8822: return 8823;
/* 541 */       case 8823: return 8822;
/* 542 */       case 8824: return 8825;
/* 543 */       case 8825: return 8824;
/* 544 */       case 8826: return 8827;
/* 545 */       case 8827: return 8826;
/* 546 */       case 8828: return 8829;
/* 547 */       case 8829: return 8828;
/* 548 */       case 8830: return 8831;
/* 549 */       case 8831: return 8830;
/* 550 */       case 8832: return 8833;
/* 551 */       case 8833: return 8832;
/* 552 */       case 8834: return 8835;
/* 553 */       case 8835: return 8834;
/* 554 */       case 8836: return 8837;
/* 555 */       case 8837: return 8836;
/* 556 */       case 8838: return 8839;
/* 557 */       case 8839: return 8838;
/* 558 */       case 8840: return 8841;
/* 559 */       case 8841: return 8840;
/* 560 */       case 8842: return 8843;
/* 561 */       case 8843: return 8842;
/* 562 */       case 8847: return 8848;
/* 563 */       case 8848: return 8847;
/* 564 */       case 8849: return 8850;
/* 565 */       case 8850: return 8849;
/* 566 */       case 8866: return 8867;
/* 567 */       case 8867: return 8866;
/* 568 */       case 8880: return 8881;
/* 569 */       case 8881: return 8880;
/* 570 */       case 8882: return 8883;
/* 571 */       case 8883: return 8882;
/* 572 */       case 8884: return 8885;
/* 573 */       case 8885: return 8884;
/* 574 */       case 8886: return 8887;
/* 575 */       case 8887: return 8886;
/* 576 */       case 8905: return 8906;
/* 577 */       case 8906: return 8905;
/* 578 */       case 8907: return 8908;
/* 579 */       case 8908: return 8907;
/* 580 */       case 8909: return 8771;
/* 581 */       case 8912: return 8913;
/* 582 */       case 8913: return 8912;
/* 583 */       case 8918: return 8919;
/* 584 */       case 8919: return 8918;
/* 585 */       case 8920: return 8921;
/* 586 */       case 8921: return 8920;
/* 587 */       case 8922: return 8923;
/* 588 */       case 8923: return 8922;
/* 589 */       case 8924: return 8925;
/* 590 */       case 8925: return 8924;
/* 591 */       case 8926: return 8927;
/* 592 */       case 8927: return 8926;
/* 593 */       case 8928: return 8929;
/* 594 */       case 8929: return 8928;
/* 595 */       case 8930: return 8931;
/* 596 */       case 8931: return 8930;
/* 597 */       case 8932: return 8933;
/* 598 */       case 8933: return 8932;
/* 599 */       case 8934: return 8935;
/* 600 */       case 8935: return 8934;
/* 601 */       case 8936: return 8937;
/* 602 */       case 8937: return 8936;
/* 603 */       case 8938: return 8939;
/* 604 */       case 8939: return 8938;
/* 605 */       case 8940: return 8941;
/* 606 */       case 8941: return 8940;
/* 607 */       case 8944: return 8945;
/* 608 */       case 8945: return 8944;
/* 609 */       case 8968: return 8969;
/* 610 */       case 8969: return 8968;
/* 611 */       case 8970: return 8971;
/* 612 */       case 8971: return 8970;
/* 613 */       case 9001: return 9002;
/* 614 */       case 9002: return 9001;
/* 615 */       case 12296: return 12297;
/* 616 */       case 12297: return 12296;
/* 617 */       case 12298: return 12299;
/* 618 */       case 12299: return 12298;
/* 619 */       case 12300: return 12301;
/* 620 */       case 12301: return 12300;
/* 621 */       case 12302: return 12303;
/* 622 */       case 12303: return 12302;
/* 623 */       case 12304: return 12305;
/* 624 */       case 12305: return 12304;
/* 625 */       case 12308: return 12309;
/* 626 */       case 12309: return 12308;
/* 627 */       case 12310: return 12311;
/* 628 */       case 12311: return 12310;
/* 629 */       case 12312: return 12313;
/* 630 */       case 12313: return 12312;
/* 631 */       case 12314: return 12315;
/* 632 */       case 12315: return 12314;
/*     */     } 
/*     */     
/* 635 */     return c;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/text/BidiAttributedCharacterIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */