/*     */ package org.apache.batik.anim.timing;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TimeContainer
/*     */   extends TimedElement
/*     */ {
/*  35 */   protected List children = new LinkedList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addChild(TimedElement e) {
/*  41 */     if (e == this) {
/*  42 */       throw new IllegalArgumentException("recursive datastructure not allowed here!");
/*     */     }
/*  44 */     this.children.add(e);
/*  45 */     e.parent = this;
/*  46 */     setRoot(e, this.root);
/*  47 */     this.root.fireElementAdded(e);
/*  48 */     this.root.currentIntervalWillUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setRoot(TimedElement e, TimedDocumentRoot root) {
/*  56 */     e.root = root;
/*  57 */     if (e instanceof TimeContainer) {
/*  58 */       TimeContainer c = (TimeContainer)e;
/*  59 */       for (Object aChildren : c.children) {
/*  60 */         TimedElement te = (TimedElement)aChildren;
/*  61 */         setRoot(te, root);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeChild(TimedElement e) {
/*  70 */     this.children.remove(e);
/*  71 */     e.parent = null;
/*  72 */     setRoot(e, null);
/*  73 */     this.root.fireElementRemoved(e);
/*  74 */     this.root.currentIntervalWillUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimedElement[] getChildren() {
/*  81 */     return (TimedElement[])this.children.toArray((Object[])new TimedElement[this.children.size()]);
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
/*     */   protected float sampleAt(float parentSimpleTime, boolean hyperlinking) {
/*  98 */     super.sampleAt(parentSimpleTime, hyperlinking);
/*     */     
/* 100 */     return sampleChildren(parentSimpleTime, hyperlinking);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float sampleChildren(float parentSimpleTime, boolean hyperlinking) {
/* 108 */     float mint = Float.POSITIVE_INFINITY;
/* 109 */     for (Object aChildren : this.children) {
/* 110 */       TimedElement e = (TimedElement)aChildren;
/* 111 */       float t = e.sampleAt(parentSimpleTime, hyperlinking);
/* 112 */       if (t < mint) {
/* 113 */         mint = t;
/*     */       }
/*     */     } 
/* 116 */     return mint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reset(boolean clearCurrentBegin) {
/* 123 */     super.reset(clearCurrentBegin);
/* 124 */     for (Object aChildren : this.children) {
/* 125 */       TimedElement e = (TimedElement)aChildren;
/* 126 */       e.reset(clearCurrentBegin);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isConstantAnimation() {
/* 135 */     return false;
/*     */   }
/*     */   
/*     */   public abstract float getDefaultBegin(TimedElement paramTimedElement);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/TimeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */