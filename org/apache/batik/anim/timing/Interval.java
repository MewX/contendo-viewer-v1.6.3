/*     */ package org.apache.batik.anim.timing;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Interval
/*     */ {
/*     */   protected float begin;
/*     */   protected float end;
/*     */   protected InstanceTime beginInstanceTime;
/*     */   protected InstanceTime endInstanceTime;
/*  55 */   protected LinkedList beginDependents = new LinkedList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   protected LinkedList endDependents = new LinkedList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Interval(float begin, float end, InstanceTime beginInstanceTime, InstanceTime endInstanceTime) {
/*  75 */     this.begin = begin;
/*  76 */     this.end = end;
/*  77 */     this.beginInstanceTime = beginInstanceTime;
/*  78 */     this.endInstanceTime = endInstanceTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  86 */     return TimedElement.toString(this.begin) + ".." + TimedElement.toString(this.end);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBegin() {
/*  93 */     return this.begin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getEnd() {
/* 100 */     return this.end;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InstanceTime getBeginInstanceTime() {
/* 108 */     return this.beginInstanceTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InstanceTime getEndInstanceTime() {
/* 116 */     return this.endInstanceTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addDependent(InstanceTime dependent, boolean forBegin) {
/* 124 */     if (forBegin) {
/* 125 */       this.beginDependents.add(dependent);
/*     */     } else {
/* 127 */       this.endDependents.add(dependent);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeDependent(InstanceTime dependent, boolean forBegin) {
/* 137 */     if (forBegin) {
/* 138 */       this.beginDependents.remove(dependent);
/*     */     } else {
/* 140 */       this.endDependents.remove(dependent);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float setBegin(float begin) {
/* 150 */     float minTime = Float.POSITIVE_INFINITY;
/* 151 */     this.begin = begin;
/* 152 */     for (Object beginDependent : this.beginDependents) {
/* 153 */       InstanceTime it = (InstanceTime)beginDependent;
/* 154 */       float t = it.dependentUpdate(begin);
/* 155 */       if (t < minTime) {
/* 156 */         minTime = t;
/*     */       }
/*     */     } 
/* 159 */     return minTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float setEnd(float end, InstanceTime endInstanceTime) {
/* 168 */     float minTime = Float.POSITIVE_INFINITY;
/* 169 */     this.end = end;
/* 170 */     this.endInstanceTime = endInstanceTime;
/* 171 */     for (Object endDependent : this.endDependents) {
/* 172 */       InstanceTime it = (InstanceTime)endDependent;
/* 173 */       float t = it.dependentUpdate(end);
/* 174 */       if (t < minTime) {
/* 175 */         minTime = t;
/*     */       }
/*     */     } 
/* 178 */     return minTime;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/Interval.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */