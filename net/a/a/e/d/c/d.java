/*     */ package net.a.a.e.d.c;
/*     */ 
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
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
/*     */ public class d
/*     */   implements AttributedCharacterIterator
/*     */ {
/*  37 */   private final List<AttributedCharacterIterator> a = new ArrayList<AttributedCharacterIterator>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*  51 */     throw new UnsupportedOperationException();
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
/*     */   public void a(AttributedCharacterIterator paramAttributedCharacterIterator) {
/*  64 */     this.a.add(paramAttributedCharacterIterator);
/*  65 */     first();
/*     */   }
/*     */   
/*     */   private int a(int paramInt) {
/*  69 */     int i = 0;
/*  70 */     for (byte b = 0; b < paramInt; b++) {
/*  71 */       AttributedCharacterIterator attributedCharacterIterator = this.a.get(b);
/*  72 */       i += attributedCharacterIterator.getEndIndex() - attributedCharacterIterator.getBeginIndex();
/*     */     } 
/*  74 */     return i;
/*     */   }
/*     */   
/*     */   private int a() {
/*  78 */     int i = a(this.b);
/*  79 */     i -= ((AttributedCharacterIterator)this.a.get(this.b)).getBeginIndex();
/*  80 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<AttributedCharacterIterator.Attribute> getAllAttributeKeys() {
/*  85 */     HashSet<AttributedCharacterIterator.Attribute> hashSet = new HashSet();
/*  86 */     for (AttributedCharacterIterator attributedCharacterIterator : this.a) {
/*  87 */       hashSet.addAll(attributedCharacterIterator.getAllAttributeKeys());
/*     */     }
/*  89 */     return hashSet;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getAttribute(AttributedCharacterIterator.Attribute paramAttribute) {
/*  94 */     return ((AttributedCharacterIterator)this.a.get(this.b)).getAttribute(paramAttribute);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<AttributedCharacterIterator.Attribute, Object> getAttributes() {
/*  99 */     return ((AttributedCharacterIterator)this.a.get(this.b)).getAttributes();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRunLimit() {
/* 104 */     return ((AttributedCharacterIterator)this.a.get(this.b)).getRunLimit() + 
/* 105 */       a();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRunLimit(AttributedCharacterIterator.Attribute paramAttribute) {
/* 110 */     return ((AttributedCharacterIterator)this.a.get(this.b)).getRunLimit(paramAttribute) + 
/* 111 */       a();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRunLimit(Set<? extends AttributedCharacterIterator.Attribute> paramSet) {
/* 116 */     return ((AttributedCharacterIterator)this.a.get(this.b)).getRunLimit(paramSet) + 
/* 117 */       a();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRunStart() {
/* 122 */     return ((AttributedCharacterIterator)this.a.get(this.b)).getRunStart() + 
/* 123 */       a();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRunStart(AttributedCharacterIterator.Attribute paramAttribute) {
/* 128 */     return ((AttributedCharacterIterator)this.a.get(this.b)).getRunStart(paramAttribute) + 
/* 129 */       a();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRunStart(Set<? extends AttributedCharacterIterator.Attribute> paramSet) {
/* 134 */     return ((AttributedCharacterIterator)this.a.get(this.b)).getRunStart(paramSet) + 
/* 135 */       a();
/*     */   }
/*     */ 
/*     */   
/*     */   public char current() {
/* 140 */     return ((AttributedCharacterIterator)this.a.get(this.b)).current();
/*     */   }
/*     */ 
/*     */   
/*     */   public char first() {
/* 145 */     this.b = 0;
/* 146 */     return ((AttributedCharacterIterator)this.a.get(this.b)).first();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBeginIndex() {
/* 151 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEndIndex() {
/* 156 */     return a(this.a.size());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 161 */     return a() + ((AttributedCharacterIterator)this.a
/* 162 */       .get(this.b)).getIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public char last() {
/* 167 */     this.b = this.a.size() - 1;
/* 168 */     return ((AttributedCharacterIterator)this.a.get(this.b)).last();
/*     */   }
/*     */ 
/*     */   
/*     */   public char next() {
/* 173 */     char c = ((AttributedCharacterIterator)this.a.get(this.b)).next();
/* 174 */     while (c == Character.MAX_VALUE && this.b < this.a
/* 175 */       .size() - 1) {
/* 176 */       this.b++;
/* 177 */       c = ((AttributedCharacterIterator)this.a.get(this.b)).first();
/*     */     } 
/* 179 */     return c;
/*     */   }
/*     */ 
/*     */   
/*     */   public char previous() {
/* 184 */     char c = ((AttributedCharacterIterator)this.a.get(this.b)).previous();
/* 185 */     while (c == Character.MAX_VALUE && this.b > 0) {
/* 186 */       this.b--;
/* 187 */       c = ((AttributedCharacterIterator)this.a.get(this.b)).previous();
/*     */     } 
/* 189 */     return c;
/*     */   }
/*     */ 
/*     */   
/*     */   public char setIndex(int paramInt) {
/* 194 */     int i = 0;
/* 195 */     int j = 0;
/* 196 */     for (byte b = 0; b < this.a.size(); b++) {
/* 197 */       AttributedCharacterIterator attributedCharacterIterator = this.a.get(b);
/* 198 */       i = j;
/* 199 */       int k = attributedCharacterIterator.getBeginIndex();
/* 200 */       j += attributedCharacterIterator.getEndIndex() - k;
/* 201 */       if ((i <= paramInt && j > paramInt) || (j == paramInt && b == this.a
/* 202 */         .size() - 1)) {
/* 203 */         this.b = b;
/* 204 */         return attributedCharacterIterator.setIndex(k + paramInt - i);
/*     */       } 
/*     */     } 
/* 207 */     throw new IllegalArgumentException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/c/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */