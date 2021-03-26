/*     */ package org.apache.batik.anim.timing;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InstanceTime
/*     */   implements Comparable
/*     */ {
/*     */   protected float time;
/*     */   protected TimingSpecifier creator;
/*     */   protected boolean clearOnReset;
/*     */   
/*     */   public InstanceTime(TimingSpecifier creator, float time, boolean clearOnReset) {
/*  57 */     this.creator = creator;
/*     */ 
/*     */ 
/*     */     
/*  61 */     this.time = time;
/*  62 */     this.clearOnReset = clearOnReset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getClearOnReset() {
/*  71 */     return this.clearOnReset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getTime() {
/*  78 */     return this.time;
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
/*     */   float dependentUpdate(float newTime) {
/*  91 */     this.time = newTime;
/*  92 */     if (this.creator != null) {
/*  93 */       return this.creator.handleTimebaseUpdate(this, this.time);
/*     */     }
/*  95 */     return Float.POSITIVE_INFINITY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 103 */     return Float.toString(this.time);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(Object o) {
/* 112 */     InstanceTime it = (InstanceTime)o;
/* 113 */     if (this.time == it.time) return 0; 
/* 114 */     if (this.time > it.time) return 1; 
/* 115 */     return -1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/InstanceTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */