/*     */ package org.apache.commons.lang3.builder;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DiffResult
/*     */   implements Iterable<Diff<?>>
/*     */ {
/*     */   public static final String OBJECTS_SAME_STRING = "";
/*     */   private static final String DIFFERS_STRING = "differs from";
/*     */   private final List<Diff<?>> diffs;
/*     */   private final Object lhs;
/*     */   private final Object rhs;
/*     */   private final ToStringStyle style;
/*     */   
/*     */   DiffResult(Object lhs, Object rhs, List<Diff<?>> diffs, ToStringStyle style) {
/*  76 */     Validate.isTrue((lhs != null), "Left hand object cannot be null", new Object[0]);
/*  77 */     Validate.isTrue((rhs != null), "Right hand object cannot be null", new Object[0]);
/*  78 */     Validate.isTrue((diffs != null), "List of differences cannot be null", new Object[0]);
/*     */     
/*  80 */     this.diffs = diffs;
/*  81 */     this.lhs = lhs;
/*  82 */     this.rhs = rhs;
/*     */     
/*  84 */     if (style == null) {
/*  85 */       this.style = ToStringStyle.DEFAULT_STYLE;
/*     */     } else {
/*  87 */       this.style = style;
/*     */     } 
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
/*     */   public List<Diff<?>> getDiffs() {
/* 100 */     return Collections.unmodifiableList(this.diffs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfDiffs() {
/* 111 */     return this.diffs.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ToStringStyle getToStringStyle() {
/* 122 */     return this.style;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 158 */     return toString(this.style);
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
/*     */   public String toString(ToStringStyle style) {
/* 173 */     if (this.diffs.isEmpty()) {
/* 174 */       return "";
/*     */     }
/*     */     
/* 177 */     ToStringBuilder lhsBuilder = new ToStringBuilder(this.lhs, style);
/* 178 */     ToStringBuilder rhsBuilder = new ToStringBuilder(this.rhs, style);
/*     */     
/* 180 */     for (Diff<?> diff : this.diffs) {
/* 181 */       lhsBuilder.append(diff.getFieldName(), diff.getLeft());
/* 182 */       rhsBuilder.append(diff.getFieldName(), diff.getRight());
/*     */     } 
/*     */     
/* 185 */     return String.format("%s %s %s", new Object[] { lhsBuilder.build(), "differs from", rhsBuilder
/* 186 */           .build() });
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
/*     */   public Iterator<Diff<?>> iterator() {
/* 198 */     return this.diffs.iterator();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/builder/DiffResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */