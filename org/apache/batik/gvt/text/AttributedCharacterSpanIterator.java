/*     */ package org.apache.batik.gvt.text;
/*     */ 
/*     */ import java.text.AttributedCharacterIterator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AttributedCharacterSpanIterator
/*     */   implements AttributedCharacterIterator
/*     */ {
/*     */   private AttributedCharacterIterator aci;
/*     */   private int begin;
/*     */   private int end;
/*     */   
/*     */   public AttributedCharacterSpanIterator(AttributedCharacterIterator aci, int start, int stop) {
/*  53 */     this.aci = aci;
/*  54 */     this.end = Math.min(aci.getEndIndex(), stop);
/*  55 */     this.begin = Math.max(aci.getBeginIndex(), start);
/*  56 */     this.aci.setIndex(this.begin);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getAllAttributeKeys() {
/*  65 */     return this.aci.getAllAttributeKeys();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getAttribute(AttributedCharacterIterator.Attribute attribute) {
/*  74 */     return this.aci.getAttribute(attribute);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map getAttributes() {
/*  82 */     return this.aci.getAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunLimit() {
/*  91 */     return Math.min(this.aci.getRunLimit(), this.end);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunLimit(AttributedCharacterIterator.Attribute attribute) {
/* 100 */     return Math.min(this.aci.getRunLimit(attribute), this.end);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunLimit(Set<? extends AttributedCharacterIterator.Attribute> attributes) {
/* 109 */     return Math.min(this.aci.getRunLimit(attributes), this.end);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunStart() {
/* 117 */     return Math.max(this.aci.getRunStart(), this.begin);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunStart(AttributedCharacterIterator.Attribute attribute) {
/* 127 */     return Math.max(this.aci.getRunStart(attribute), this.begin);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunStart(Set<? extends AttributedCharacterIterator.Attribute> attributes) {
/* 137 */     return Math.max(this.aci.getRunStart(attributes), this.begin);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 146 */     return new AttributedCharacterSpanIterator((AttributedCharacterIterator)this.aci.clone(), this.begin, this.end);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char current() {
/* 156 */     return this.aci.current();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char first() {
/* 165 */     return this.aci.setIndex(this.begin);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBeginIndex() {
/* 173 */     return this.begin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEndIndex() {
/* 181 */     return this.end;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 189 */     return this.aci.getIndex();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char last() {
/* 198 */     return setIndex(this.end - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char next() {
/* 207 */     if (getIndex() < this.end - 1) {
/* 208 */       return this.aci.next();
/*     */     }
/* 210 */     return setIndex(this.end);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char previous() {
/* 220 */     if (getIndex() > this.begin) {
/* 221 */       return this.aci.previous();
/*     */     }
/* 223 */     return Character.MAX_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char setIndex(int position) {
/* 234 */     int ndx = Math.max(position, this.begin);
/* 235 */     ndx = Math.min(ndx, this.end);
/* 236 */     char c = this.aci.setIndex(ndx);
/* 237 */     if (ndx == this.end) {
/* 238 */       c = Character.MAX_VALUE;
/*     */     }
/* 240 */     return c;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/text/AttributedCharacterSpanIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */