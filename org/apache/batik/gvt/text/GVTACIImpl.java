/*     */ package org.apache.batik.gvt.text;
/*     */ 
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.AttributedString;
/*     */ import java.text.CharacterIterator;
/*     */ import java.text.StringCharacterIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GVTACIImpl
/*     */   implements GVTAttributedCharacterIterator
/*     */ {
/*     */   private String simpleString;
/*     */   private Set allAttributes;
/*     */   private ArrayList mapList;
/*  50 */   private static int START_RUN = 2;
/*  51 */   private static int END_RUN = 3;
/*  52 */   private static int MID_RUN = 1;
/*  53 */   private static int SINGLETON = 0;
/*     */   private int[] charInRun;
/*  55 */   private CharacterIterator iter = null;
/*  56 */   private int currentIndex = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTACIImpl() {
/*  63 */     this.simpleString = "";
/*  64 */     buildAttributeTables();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTACIImpl(AttributedCharacterIterator aci) {
/*  73 */     buildAttributeTables(aci);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setString(String s) {
/*  80 */     this.simpleString = s;
/*  81 */     this.iter = new StringCharacterIterator(this.simpleString);
/*  82 */     buildAttributeTables();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setString(AttributedString s) {
/*  89 */     this.iter = s.getIterator();
/*  90 */     buildAttributeTables((AttributedCharacterIterator)this.iter);
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
/*     */   public void setAttributeArray(GVTAttributedCharacterIterator.TextAttribute attr, Object[] attValues, int beginIndex, int endIndex) {
/* 110 */     beginIndex = Math.max(beginIndex, 0);
/* 111 */     endIndex = Math.min(endIndex, this.simpleString.length());
/* 112 */     if (this.charInRun[beginIndex] == END_RUN) {
/* 113 */       if (this.charInRun[beginIndex - 1] == MID_RUN) {
/* 114 */         this.charInRun[beginIndex - 1] = END_RUN;
/*     */       } else {
/* 116 */         this.charInRun[beginIndex - 1] = SINGLETON;
/*     */       } 
/*     */     }
/* 119 */     if (this.charInRun[endIndex + 1] == END_RUN) {
/* 120 */       this.charInRun[endIndex + 1] = SINGLETON;
/* 121 */     } else if (this.charInRun[endIndex + 1] == MID_RUN) {
/* 122 */       this.charInRun[endIndex + 1] = START_RUN;
/*     */     } 
/* 124 */     for (int i = beginIndex; i <= endIndex; i++) {
/* 125 */       this.charInRun[i] = SINGLETON;
/* 126 */       int n = Math.min(i, attValues.length - 1);
/* 127 */       ((Map<GVTAttributedCharacterIterator.TextAttribute, Object>)this.mapList.get(i)).put(attr, attValues[n]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getAllAttributeKeys() {
/* 137 */     return this.allAttributes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getAttribute(AttributedCharacterIterator.Attribute attribute) {
/* 146 */     return getAttributes().get(attribute);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map getAttributes() {
/* 154 */     return this.mapList.get(this.currentIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunLimit() {
/* 163 */     int ndx = this.currentIndex;
/*     */     while (true) {
/* 165 */       ndx++;
/* 166 */       if (this.charInRun[ndx] != MID_RUN) {
/* 167 */         return ndx;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunLimit(AttributedCharacterIterator.Attribute attribute) {
/* 176 */     int ndx = this.currentIndex;
/* 177 */     Object value = getAttributes().get(attribute);
/*     */ 
/*     */     
/* 180 */     if (value == null) {
/*     */       do {
/* 182 */         ndx++;
/* 183 */       } while (((Map)this.mapList.get(ndx)).get(attribute) == null);
/*     */     } else {
/*     */       do {
/* 186 */         ndx++;
/* 187 */       } while (value.equals(((Map)this.mapList.get(ndx)).get(attribute)));
/*     */     } 
/* 189 */     return ndx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunLimit(Set attributes) {
/* 199 */     int ndx = this.currentIndex;
/*     */     while (true) {
/* 201 */       ndx++;
/* 202 */       if (!attributes.equals(this.mapList.get(ndx))) {
/* 203 */         return ndx;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunStart() {
/* 211 */     int ndx = this.currentIndex;
/* 212 */     for (; this.charInRun[ndx] == MID_RUN; ndx--);
/* 213 */     return ndx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunStart(AttributedCharacterIterator.Attribute attribute) {
/* 223 */     int ndx = this.currentIndex - 1;
/* 224 */     Object value = getAttributes().get(attribute);
/*     */ 
/*     */     
/*     */     try {
/* 228 */       if (value == null) {
/* 229 */         while (((Map)this.mapList.get(ndx - 1)).get(attribute) == null)
/* 230 */           ndx--; 
/*     */       } else {
/* 232 */         while (value.equals(((Map)this.mapList.get(ndx - 1)).get(attribute)))
/*     */         {
/* 234 */           ndx--; } 
/*     */       } 
/* 236 */     } catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
/*     */     
/* 238 */     return ndx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunStart(Set attributes) {
/* 248 */     int ndx = this.currentIndex;
/*     */     try {
/* 250 */       for (; attributes.equals(this.mapList.get(ndx - 1)); ndx--);
/* 251 */     } catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
/*     */     
/* 253 */     return ndx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 262 */     GVTAttributedCharacterIterator cloneACI = new GVTACIImpl(this);
/*     */     
/* 264 */     return cloneACI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char current() {
/* 273 */     return this.iter.current();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char first() {
/* 282 */     return this.iter.first();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBeginIndex() {
/* 290 */     return this.iter.getBeginIndex();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEndIndex() {
/* 298 */     return this.iter.getEndIndex();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 306 */     return this.iter.getIndex();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char last() {
/* 315 */     return this.iter.last();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char next() {
/* 324 */     return this.iter.next();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char previous() {
/* 333 */     return this.iter.previous();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char setIndex(int position) {
/* 343 */     return this.iter.setIndex(position);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void buildAttributeTables() {
/* 349 */     this.allAttributes = new HashSet();
/* 350 */     this.mapList = new ArrayList(this.simpleString.length());
/* 351 */     this.charInRun = new int[this.simpleString.length()];
/* 352 */     for (int i = 0; i < this.charInRun.length; i++) {
/* 353 */       this.charInRun[i] = SINGLETON;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 358 */       this.mapList.set(i, new HashMap<Object, Object>());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void buildAttributeTables(AttributedCharacterIterator aci) {
/* 363 */     this.allAttributes = aci.getAllAttributeKeys();
/* 364 */     int length = aci.getEndIndex() - aci.getBeginIndex();
/* 365 */     this.mapList = new ArrayList(length);
/* 366 */     this.charInRun = new int[length];
/* 367 */     char c = aci.first();
/* 368 */     char[] chars = new char[length];
/* 369 */     for (int i = 0; i < length; i++) {
/* 370 */       chars[i] = c;
/* 371 */       this.charInRun[i] = SINGLETON;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 376 */       this.mapList.set(i, new HashMap<AttributedCharacterIterator.Attribute, Object>(aci.getAttributes()));
/* 377 */       c = aci.next();
/*     */     } 
/* 379 */     this.simpleString = new String(chars);
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
/*     */   public class TransformAttributeFilter
/*     */     implements GVTAttributedCharacterIterator.AttributeFilter
/*     */   {
/*     */     public AttributedCharacterIterator mutateAttributes(AttributedCharacterIterator aci) {
/* 400 */       return aci;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/text/GVTACIImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */