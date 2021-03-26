/*     */ package org.apache.batik.anim;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.anim.dom.AnimationTarget;
/*     */ import org.apache.batik.anim.dom.AnimationTargetListener;
/*     */ import org.apache.batik.anim.timing.TimedDocumentRoot;
/*     */ import org.apache.batik.anim.timing.TimedElement;
/*     */ import org.apache.batik.anim.timing.TimegraphListener;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Document;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AnimationEngine
/*     */ {
/*     */   public static final short ANIM_TYPE_XML = 0;
/*     */   public static final short ANIM_TYPE_CSS = 1;
/*     */   public static final short ANIM_TYPE_OTHER = 2;
/*     */   protected Document document;
/*     */   protected TimedDocumentRoot timedDocumentRoot;
/*     */   protected long pauseTime;
/*  68 */   protected HashMap targets = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   protected HashMap animations = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   protected Listener targetListener = new Listener();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimationEngine(Document doc) {
/*  84 */     this.document = doc;
/*  85 */     this.timedDocumentRoot = createDocumentRoot();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/*  93 */     for (Object o : this.targets.entrySet()) {
/*  94 */       Map.Entry e = (Map.Entry)o;
/*  95 */       AnimationTarget target = (AnimationTarget)e.getKey();
/*  96 */       TargetInfo info = (TargetInfo)e.getValue();
/*     */       
/*  98 */       Iterator<DoublyIndexedTable.Entry> j = info.xmlAnimations.iterator();
/*  99 */       while (j.hasNext()) {
/* 100 */         DoublyIndexedTable.Entry e2 = j.next();
/*     */         
/* 102 */         String namespaceURI = (String)e2.getKey1();
/* 103 */         String localName = (String)e2.getKey2();
/* 104 */         Sandwich sandwich = (Sandwich)e2.getValue();
/* 105 */         if (sandwich.listenerRegistered) {
/* 106 */           target.removeTargetListener(namespaceURI, localName, false, this.targetListener);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 111 */       j = info.cssAnimations.entrySet().iterator();
/* 112 */       while (j.hasNext()) {
/* 113 */         Map.Entry e2 = (Map.Entry)j.next();
/* 114 */         String propertyName = (String)e2.getKey();
/* 115 */         Sandwich sandwich = (Sandwich)e2.getValue();
/* 116 */         if (sandwich.listenerRegistered) {
/* 117 */           target.removeTargetListener(null, propertyName, true, this.targetListener);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pause() {
/* 128 */     if (this.pauseTime == 0L) {
/* 129 */       this.pauseTime = System.currentTimeMillis();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unpause() {
/* 137 */     if (this.pauseTime != 0L) {
/* 138 */       Calendar begin = this.timedDocumentRoot.getDocumentBeginTime();
/* 139 */       int dt = (int)(System.currentTimeMillis() - this.pauseTime);
/* 140 */       begin.add(14, dt);
/* 141 */       this.pauseTime = 0L;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPaused() {
/* 149 */     return (this.pauseTime != 0L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCurrentTime() {
/* 156 */     return this.timedDocumentRoot.getCurrentTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float setCurrentTime(float t) {
/* 163 */     boolean p = (this.pauseTime != 0L);
/* 164 */     unpause();
/* 165 */     Calendar begin = this.timedDocumentRoot.getDocumentBeginTime();
/* 166 */     float now = this.timedDocumentRoot.convertEpochTime(System.currentTimeMillis());
/*     */     
/* 168 */     begin.add(14, (int)((now - t) * 1000.0F));
/* 169 */     if (p) {
/* 170 */       pause();
/*     */     }
/* 172 */     return tick(t, true);
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
/*     */   public void addAnimation(AnimationTarget target, short type, String ns, String an, AbstractAnimation anim) {
/* 190 */     this.timedDocumentRoot.addChild(anim.getTimedElement());
/*     */     
/* 192 */     AnimationInfo animInfo = getAnimationInfo(anim);
/* 193 */     animInfo.type = type;
/* 194 */     animInfo.attributeNamespaceURI = ns;
/* 195 */     animInfo.attributeLocalName = an;
/* 196 */     animInfo.target = target;
/* 197 */     this.animations.put(anim, animInfo);
/*     */     
/* 199 */     Sandwich sandwich = getSandwich(target, type, ns, an);
/* 200 */     if (sandwich.animation == null) {
/* 201 */       anim.lowerAnimation = null;
/* 202 */       anim.higherAnimation = null;
/*     */     } else {
/* 204 */       sandwich.animation.higherAnimation = anim;
/* 205 */       anim.lowerAnimation = sandwich.animation;
/* 206 */       anim.higherAnimation = null;
/*     */     } 
/* 208 */     sandwich.animation = anim;
/* 209 */     if (anim.lowerAnimation == null) {
/* 210 */       sandwich.lowestAnimation = anim;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAnimation(AbstractAnimation anim) {
/* 220 */     this.timedDocumentRoot.removeChild(anim.getTimedElement());
/* 221 */     AbstractAnimation nextHigher = anim.higherAnimation;
/* 222 */     if (nextHigher != null) {
/* 223 */       nextHigher.markDirty();
/*     */     }
/* 225 */     moveToBottom(anim);
/* 226 */     if (anim.higherAnimation != null) {
/* 227 */       anim.higherAnimation.lowerAnimation = null;
/*     */     }
/* 229 */     AnimationInfo animInfo = getAnimationInfo(anim);
/* 230 */     Sandwich sandwich = getSandwich(animInfo.target, animInfo.type, animInfo.attributeNamespaceURI, animInfo.attributeLocalName);
/*     */ 
/*     */     
/* 233 */     if (sandwich.animation == anim) {
/* 234 */       sandwich.animation = null;
/* 235 */       sandwich.lowestAnimation = null;
/* 236 */       sandwich.shouldUpdate = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Sandwich getSandwich(AnimationTarget target, short type, String ns, String an) {
/*     */     Sandwich sandwich;
/* 246 */     TargetInfo info = getTargetInfo(target);
/*     */     
/* 248 */     if (type == 0) {
/* 249 */       sandwich = (Sandwich)info.xmlAnimations.get(ns, an);
/* 250 */       if (sandwich == null) {
/* 251 */         sandwich = new Sandwich();
/* 252 */         info.xmlAnimations.put(ns, an, sandwich);
/*     */       } 
/* 254 */     } else if (type == 1) {
/* 255 */       sandwich = (Sandwich)info.cssAnimations.get(an);
/* 256 */       if (sandwich == null) {
/* 257 */         sandwich = new Sandwich();
/* 258 */         info.cssAnimations.put(an, sandwich);
/*     */       } 
/*     */     } else {
/* 261 */       sandwich = (Sandwich)info.otherAnimations.get(an);
/* 262 */       if (sandwich == null) {
/* 263 */         sandwich = new Sandwich();
/* 264 */         info.otherAnimations.put(an, sandwich);
/*     */       } 
/*     */     } 
/* 267 */     return sandwich;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected TargetInfo getTargetInfo(AnimationTarget target) {
/* 274 */     TargetInfo info = (TargetInfo)this.targets.get(target);
/* 275 */     if (info == null) {
/* 276 */       info = new TargetInfo();
/* 277 */       this.targets.put(target, info);
/*     */     } 
/* 279 */     return info;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimationInfo getAnimationInfo(AbstractAnimation anim) {
/* 286 */     AnimationInfo info = (AnimationInfo)this.animations.get(anim);
/* 287 */     if (info == null) {
/* 288 */       info = new AnimationInfo();
/* 289 */       this.animations.put(anim, info);
/*     */     } 
/* 291 */     return info;
/*     */   }
/*     */   
/* 294 */   protected static final Map.Entry[] MAP_ENTRY_ARRAY = new Map.Entry[0];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float tick(float time, boolean hyperlinking) {
/* 303 */     float waitTime = this.timedDocumentRoot.seekTo(time, hyperlinking);
/* 304 */     Map.Entry[] targetEntries = (Map.Entry[])this.targets.entrySet().toArray((Object[])MAP_ENTRY_ARRAY);
/*     */     
/* 306 */     for (Map.Entry e : targetEntries) {
/* 307 */       AnimationTarget target = (AnimationTarget)e.getKey();
/* 308 */       TargetInfo info = (TargetInfo)e.getValue();
/*     */ 
/*     */       
/* 311 */       Iterator<DoublyIndexedTable.Entry> j = info.xmlAnimations.iterator();
/* 312 */       while (j.hasNext()) {
/* 313 */         DoublyIndexedTable.Entry e2 = j.next();
/*     */         
/* 315 */         String namespaceURI = (String)e2.getKey1();
/* 316 */         String localName = (String)e2.getKey2();
/* 317 */         Sandwich sandwich = (Sandwich)e2.getValue();
/* 318 */         if (sandwich.shouldUpdate || (sandwich.animation != null && sandwich.animation.isDirty)) {
/*     */ 
/*     */           
/* 321 */           AnimatableValue av = null;
/* 322 */           boolean usesUnderlying = false;
/* 323 */           AbstractAnimation anim = sandwich.animation;
/* 324 */           if (anim != null) {
/* 325 */             av = anim.getComposedValue();
/* 326 */             usesUnderlying = sandwich.lowestAnimation.usesUnderlyingValue();
/*     */             
/* 328 */             anim.isDirty = false;
/*     */           } 
/* 330 */           if (usesUnderlying && !sandwich.listenerRegistered) {
/* 331 */             target.addTargetListener(namespaceURI, localName, false, this.targetListener);
/*     */             
/* 333 */             sandwich.listenerRegistered = true;
/* 334 */           } else if (!usesUnderlying && sandwich.listenerRegistered) {
/* 335 */             target.removeTargetListener(namespaceURI, localName, false, this.targetListener);
/*     */             
/* 337 */             sandwich.listenerRegistered = false;
/*     */           } 
/* 339 */           target.updateAttributeValue(namespaceURI, localName, av);
/* 340 */           sandwich.shouldUpdate = false;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 345 */       j = info.cssAnimations.entrySet().iterator();
/* 346 */       while (j.hasNext()) {
/* 347 */         Map.Entry e2 = (Map.Entry)j.next();
/* 348 */         String propertyName = (String)e2.getKey();
/* 349 */         Sandwich sandwich = (Sandwich)e2.getValue();
/* 350 */         if (sandwich.shouldUpdate || (sandwich.animation != null && sandwich.animation.isDirty)) {
/*     */ 
/*     */           
/* 353 */           AnimatableValue av = null;
/* 354 */           boolean usesUnderlying = false;
/* 355 */           AbstractAnimation anim = sandwich.animation;
/* 356 */           if (anim != null) {
/* 357 */             av = anim.getComposedValue();
/* 358 */             usesUnderlying = sandwich.lowestAnimation.usesUnderlyingValue();
/*     */             
/* 360 */             anim.isDirty = false;
/*     */           } 
/* 362 */           if (usesUnderlying && !sandwich.listenerRegistered) {
/* 363 */             target.addTargetListener(null, propertyName, true, this.targetListener);
/*     */             
/* 365 */             sandwich.listenerRegistered = true;
/* 366 */           } else if (!usesUnderlying && sandwich.listenerRegistered) {
/* 367 */             target.removeTargetListener(null, propertyName, true, this.targetListener);
/*     */             
/* 369 */             sandwich.listenerRegistered = false;
/*     */           } 
/* 371 */           if (usesUnderlying) {
/* 372 */             target.updatePropertyValue(propertyName, null);
/*     */           }
/* 374 */           if (!usesUnderlying || av != null) {
/* 375 */             target.updatePropertyValue(propertyName, av);
/*     */           }
/* 377 */           sandwich.shouldUpdate = false;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 382 */       j = info.otherAnimations.entrySet().iterator();
/* 383 */       while (j.hasNext()) {
/* 384 */         Map.Entry e2 = (Map.Entry)j.next();
/* 385 */         String type = (String)e2.getKey();
/* 386 */         Sandwich sandwich = (Sandwich)e2.getValue();
/* 387 */         if (sandwich.shouldUpdate || (sandwich.animation != null && sandwich.animation.isDirty)) {
/*     */ 
/*     */           
/* 390 */           AnimatableValue av = null;
/* 391 */           AbstractAnimation anim = sandwich.animation;
/* 392 */           if (anim != null) {
/* 393 */             av = sandwich.animation.getComposedValue();
/* 394 */             anim.isDirty = false;
/*     */           } 
/* 396 */           target.updateOtherValue(type, av);
/* 397 */           sandwich.shouldUpdate = false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 401 */     return waitTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void toActive(AbstractAnimation anim, float begin) {
/* 411 */     moveToTop(anim);
/* 412 */     anim.isActive = true;
/* 413 */     anim.beginTime = begin;
/* 414 */     anim.isFrozen = false;
/*     */ 
/*     */     
/* 417 */     pushDown(anim);
/* 418 */     anim.markDirty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void pushDown(AbstractAnimation anim) {
/* 426 */     TimedElement e = anim.getTimedElement();
/* 427 */     AbstractAnimation top = null;
/* 428 */     boolean moved = false;
/*     */ 
/*     */ 
/*     */     
/* 432 */     while (anim.lowerAnimation != null && (anim.lowerAnimation.isActive || anim.lowerAnimation.isFrozen) && (anim.lowerAnimation.beginTime > anim.beginTime || (anim.lowerAnimation.beginTime == anim.beginTime && e.isBefore(anim.lowerAnimation.getTimedElement())))) {
/*     */ 
/*     */       
/* 435 */       AbstractAnimation higher = anim.higherAnimation;
/* 436 */       AbstractAnimation lower = anim.lowerAnimation;
/* 437 */       AbstractAnimation lowerLower = lower.lowerAnimation;
/* 438 */       if (higher != null) {
/* 439 */         higher.lowerAnimation = lower;
/*     */       }
/* 441 */       if (lowerLower != null) {
/* 442 */         lowerLower.higherAnimation = anim;
/*     */       }
/* 444 */       lower.lowerAnimation = anim;
/* 445 */       lower.higherAnimation = higher;
/* 446 */       anim.lowerAnimation = lowerLower;
/* 447 */       anim.higherAnimation = lower;
/* 448 */       if (!moved) {
/* 449 */         top = lower;
/* 450 */         moved = true;
/*     */       } 
/*     */     } 
/* 453 */     if (moved) {
/* 454 */       AnimationInfo animInfo = getAnimationInfo(anim);
/* 455 */       Sandwich sandwich = getSandwich(animInfo.target, animInfo.type, animInfo.attributeNamespaceURI, animInfo.attributeLocalName);
/*     */ 
/*     */       
/* 458 */       if (sandwich.animation == anim) {
/* 459 */         sandwich.animation = top;
/*     */       }
/* 461 */       if (anim.lowerAnimation == null) {
/* 462 */         sandwich.lowestAnimation = anim;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void toInactive(AbstractAnimation anim, boolean isFrozen) {
/* 474 */     anim.isActive = false;
/* 475 */     anim.isFrozen = isFrozen;
/* 476 */     anim.markDirty();
/* 477 */     if (!isFrozen) {
/* 478 */       anim.value = null;
/* 479 */       anim.beginTime = Float.NEGATIVE_INFINITY;
/* 480 */       moveToBottom(anim);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFill(AbstractAnimation anim) {
/* 488 */     anim.isActive = false;
/* 489 */     anim.isFrozen = false;
/* 490 */     anim.value = null;
/* 491 */     anim.markDirty();
/* 492 */     moveToBottom(anim);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void moveToTop(AbstractAnimation anim) {
/* 499 */     AnimationInfo animInfo = getAnimationInfo(anim);
/* 500 */     Sandwich sandwich = getSandwich(animInfo.target, animInfo.type, animInfo.attributeNamespaceURI, animInfo.attributeLocalName);
/*     */ 
/*     */     
/* 503 */     sandwich.shouldUpdate = true;
/* 504 */     if (anim.higherAnimation == null) {
/*     */       return;
/*     */     }
/* 507 */     if (anim.lowerAnimation == null) {
/* 508 */       sandwich.lowestAnimation = anim.higherAnimation;
/*     */     } else {
/* 510 */       anim.lowerAnimation.higherAnimation = anim.higherAnimation;
/*     */     } 
/* 512 */     anim.higherAnimation.lowerAnimation = anim.lowerAnimation;
/* 513 */     if (sandwich.animation != null) {
/* 514 */       sandwich.animation.higherAnimation = anim;
/*     */     }
/* 516 */     anim.lowerAnimation = sandwich.animation;
/* 517 */     anim.higherAnimation = null;
/* 518 */     sandwich.animation = anim;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void moveToBottom(AbstractAnimation anim) {
/* 525 */     if (anim.lowerAnimation == null) {
/*     */       return;
/*     */     }
/* 528 */     AnimationInfo animInfo = getAnimationInfo(anim);
/* 529 */     Sandwich sandwich = getSandwich(animInfo.target, animInfo.type, animInfo.attributeNamespaceURI, animInfo.attributeLocalName);
/*     */ 
/*     */     
/* 532 */     AbstractAnimation nextLower = anim.lowerAnimation;
/* 533 */     nextLower.markDirty();
/* 534 */     anim.lowerAnimation.higherAnimation = anim.higherAnimation;
/* 535 */     if (anim.higherAnimation != null) {
/* 536 */       anim.higherAnimation.lowerAnimation = anim.lowerAnimation;
/*     */     } else {
/* 538 */       sandwich.animation = nextLower;
/* 539 */       sandwich.shouldUpdate = true;
/*     */     } 
/* 541 */     sandwich.lowestAnimation.lowerAnimation = anim;
/* 542 */     anim.higherAnimation = sandwich.lowestAnimation;
/* 543 */     anim.lowerAnimation = null;
/* 544 */     sandwich.lowestAnimation = anim;
/* 545 */     if (sandwich.animation.isDirty) {
/* 546 */       sandwich.shouldUpdate = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTimegraphListener(TimegraphListener l) {
/* 554 */     this.timedDocumentRoot.addTimegraphListener(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTimegraphListener(TimegraphListener l) {
/* 561 */     this.timedDocumentRoot.removeTimegraphListener(l);
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
/*     */   public void sampledAt(AbstractAnimation anim, float simpleTime, float simpleDur, int repeatIteration) {
/* 576 */     anim.sampledAt(simpleTime, simpleDur, repeatIteration);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sampledLastValue(AbstractAnimation anim, int repeatIteration) {
/* 586 */     anim.sampledLastValue(repeatIteration);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract TimedDocumentRoot createDocumentRoot();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class Listener
/*     */     implements AnimationTargetListener
/*     */   {
/*     */     public void baseValueChanged(AnimationTarget t, String ns, String ln, boolean isCSS) {
/* 605 */       short type = isCSS ? 1 : 0;
/* 606 */       AnimationEngine.Sandwich sandwich = AnimationEngine.this.getSandwich(t, type, ns, ln);
/* 607 */       sandwich.shouldUpdate = true;
/* 608 */       AbstractAnimation anim = sandwich.animation;
/* 609 */       while (anim.lowerAnimation != null) {
/* 610 */         anim = anim.lowerAnimation;
/*     */       }
/* 612 */       anim.markDirty();
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
/*     */   protected static class TargetInfo
/*     */   {
/* 625 */     public DoublyIndexedTable xmlAnimations = new DoublyIndexedTable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 631 */     public HashMap cssAnimations = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 637 */     public HashMap otherAnimations = new HashMap<Object, Object>();
/*     */   }
/*     */   
/*     */   protected static class Sandwich {
/*     */     public AbstractAnimation animation;
/*     */     public AbstractAnimation lowestAnimation;
/*     */     public boolean shouldUpdate;
/*     */     public boolean listenerRegistered;
/*     */   }
/*     */   
/*     */   protected static class AnimationInfo {
/*     */     public AnimationTarget target;
/*     */     public short type;
/*     */     public String attributeNamespaceURI;
/*     */     public String attributeLocalName;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/AnimationEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */