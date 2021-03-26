/*      */ package org.apache.batik.anim.timing;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.MissingResourceException;
/*      */ import java.util.Set;
/*      */ import org.apache.batik.anim.AnimationException;
/*      */ import org.apache.batik.i18n.LocalizableSupport;
/*      */ import org.apache.batik.parser.ClockHandler;
/*      */ import org.apache.batik.parser.ClockParser;
/*      */ import org.apache.batik.parser.ParseException;
/*      */ import org.apache.batik.util.SMILConstants;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.events.Event;
/*      */ import org.w3c.dom.events.EventTarget;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class TimedElement
/*      */   implements SMILConstants
/*      */ {
/*      */   public static final int FILL_REMOVE = 0;
/*      */   public static final int FILL_FREEZE = 1;
/*      */   public static final int RESTART_ALWAYS = 0;
/*      */   public static final int RESTART_WHEN_NOT_ACTIVE = 1;
/*      */   public static final int RESTART_NEVER = 2;
/*      */   public static final float INDEFINITE = InfinityF;
/*      */   public static final float UNRESOLVED = NaNF;
/*      */   protected TimedDocumentRoot root;
/*      */   protected TimeContainer parent;
/*      */   protected TimingSpecifier[] beginTimes;
/*      */   protected TimingSpecifier[] endTimes;
/*      */   protected float simpleDur;
/*      */   protected boolean durMedia;
/*      */   protected float repeatCount;
/*      */   protected float repeatDur;
/*      */   protected int currentRepeatIteration;
/*      */   protected float lastRepeatTime;
/*      */   protected int fillMode;
/*      */   protected int restartMode;
/*      */   protected float min;
/*      */   protected boolean minMedia;
/*      */   protected float max;
/*      */   protected boolean maxMedia;
/*      */   protected boolean isActive;
/*      */   protected boolean isFrozen;
/*      */   protected float lastSampleTime;
/*      */   protected float repeatDuration;
/*  169 */   protected List beginInstanceTimes = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  174 */   protected List endInstanceTimes = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Interval currentInterval;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float lastIntervalEnd;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Interval previousInterval;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  201 */   protected LinkedList beginDependents = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  207 */   protected LinkedList endDependents = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean shouldUpdateCurrentInterval = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hasParsed;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  224 */   protected Map handledEvents = new HashMap<Object, Object>();
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isSampling;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hasPropagated;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String RESOURCES = "org.apache.batik.anim.resources.Messages";
/*      */ 
/*      */ 
/*      */   
/*      */   public TimedElement() {
/*  241 */     this.beginTimes = new TimingSpecifier[0];
/*  242 */     this.endTimes = this.beginTimes;
/*  243 */     this.simpleDur = Float.NaN;
/*  244 */     this.repeatCount = Float.NaN;
/*  245 */     this.repeatDur = Float.NaN;
/*  246 */     this.lastRepeatTime = Float.NaN;
/*  247 */     this.max = Float.POSITIVE_INFINITY;
/*  248 */     this.lastSampleTime = Float.NaN;
/*  249 */     this.lastIntervalEnd = Float.NEGATIVE_INFINITY;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TimedDocumentRoot getRoot() {
/*  256 */     return this.root;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getActiveTime() {
/*  263 */     return this.lastSampleTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSimpleTime() {
/*  270 */     return this.lastSampleTime - this.lastRepeatTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float addInstanceTime(InstanceTime time, boolean isBegin) {
/*      */     float ret;
/*  281 */     this.hasPropagated = true;
/*  282 */     List<? extends Comparable<? super InstanceTime>> instanceTimes = isBegin ? this.beginInstanceTimes : this.endInstanceTimes;
/*  283 */     int index = Collections.binarySearch(instanceTimes, time);
/*  284 */     if (index < 0) {
/*  285 */       index = -(index + 1);
/*      */     }
/*  287 */     instanceTimes.add(index, time);
/*  288 */     this.shouldUpdateCurrentInterval = true;
/*      */     
/*  290 */     if (this.root.isSampling() && !this.isSampling) {
/*  291 */       ret = sampleAt(this.root.getCurrentTime(), this.root.isHyperlinking());
/*      */     } else {
/*  293 */       ret = Float.POSITIVE_INFINITY;
/*      */     } 
/*  295 */     this.hasPropagated = false;
/*  296 */     this.root.currentIntervalWillUpdate();
/*  297 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float removeInstanceTime(InstanceTime time, boolean isBegin) {
/*      */     float ret;
/*  308 */     this.hasPropagated = true;
/*  309 */     List<? extends Comparable<? super InstanceTime>> instanceTimes = isBegin ? this.beginInstanceTimes : this.endInstanceTimes;
/*  310 */     int index = Collections.binarySearch(instanceTimes, time);
/*  311 */     for (int i = index; i >= 0; i--) {
/*  312 */       InstanceTime it = (InstanceTime)instanceTimes.get(i);
/*  313 */       if (it == time) {
/*  314 */         instanceTimes.remove(i);
/*      */         break;
/*      */       } 
/*  317 */       if (it.compareTo(time) != 0) {
/*      */         break;
/*      */       }
/*      */     } 
/*  321 */     int len = instanceTimes.size();
/*  322 */     for (int j = index + 1; j < len; j++) {
/*  323 */       InstanceTime it = (InstanceTime)instanceTimes.get(j);
/*  324 */       if (it == time) {
/*  325 */         instanceTimes.remove(j);
/*      */         break;
/*      */       } 
/*  328 */       if (it.compareTo(time) != 0) {
/*      */         break;
/*      */       }
/*      */     } 
/*  332 */     this.shouldUpdateCurrentInterval = true;
/*      */     
/*  334 */     if (this.root.isSampling() && !this.isSampling) {
/*  335 */       ret = sampleAt(this.root.getCurrentTime(), this.root.isHyperlinking());
/*      */     } else {
/*  337 */       ret = Float.POSITIVE_INFINITY;
/*      */     } 
/*  339 */     this.hasPropagated = false;
/*  340 */     this.root.currentIntervalWillUpdate();
/*  341 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float instanceTimeChanged(InstanceTime time, boolean isBegin) {
/*      */     float ret;
/*  352 */     this.hasPropagated = true;
/*  353 */     this.shouldUpdateCurrentInterval = true;
/*      */     
/*  355 */     if (this.root.isSampling() && !this.isSampling) {
/*  356 */       ret = sampleAt(this.root.getCurrentTime(), this.root.isHyperlinking());
/*      */     } else {
/*  358 */       ret = Float.POSITIVE_INFINITY;
/*      */     } 
/*  360 */     this.hasPropagated = false;
/*  361 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addDependent(TimingSpecifier dependent, boolean forBegin) {
/*  370 */     if (forBegin) {
/*  371 */       this.beginDependents.add(dependent);
/*      */     } else {
/*  373 */       this.endDependents.add(dependent);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeDependent(TimingSpecifier dependent, boolean forBegin) {
/*  384 */     if (forBegin) {
/*  385 */       this.beginDependents.remove(dependent);
/*      */     } else {
/*  387 */       this.endDependents.remove(dependent);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSimpleDur() {
/*  396 */     if (this.durMedia)
/*  397 */       return getImplicitDur(); 
/*  398 */     if (isUnresolved(this.simpleDur)) {
/*  399 */       if (isUnresolved(this.repeatCount) && isUnresolved(this.repeatDur) && this.endTimes.length > 0)
/*      */       {
/*  401 */         return Float.POSITIVE_INFINITY;
/*      */       }
/*  403 */       return getImplicitDur();
/*      */     } 
/*  405 */     return this.simpleDur;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isUnresolved(float t) {
/*  414 */     return Float.isNaN(t);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getActiveDur(float B, float end) {
/*  421 */     float PAD, IAD, d = getSimpleDur();
/*      */     
/*  423 */     if (!isUnresolved(end) && d == Float.POSITIVE_INFINITY) {
/*  424 */       PAD = minusTime(end, B);
/*  425 */       this.repeatDuration = minTime(this.max, maxTime(this.min, PAD));
/*  426 */       return this.repeatDuration;
/*      */     } 
/*      */ 
/*      */     
/*  430 */     if (d == 0.0F) {
/*  431 */       IAD = 0.0F;
/*      */     }
/*  433 */     else if (isUnresolved(this.repeatDur) && isUnresolved(this.repeatCount)) {
/*  434 */       IAD = d;
/*      */     } else {
/*  436 */       float p1 = isUnresolved(this.repeatCount) ? Float.POSITIVE_INFINITY : multiplyTime(d, this.repeatCount);
/*      */ 
/*      */       
/*  439 */       float p2 = isUnresolved(this.repeatDur) ? Float.POSITIVE_INFINITY : this.repeatDur;
/*      */ 
/*      */       
/*  442 */       IAD = minTime(minTime(p1, p2), Float.POSITIVE_INFINITY);
/*      */     } 
/*      */     
/*  445 */     if (isUnresolved(end) || end == Float.POSITIVE_INFINITY) {
/*  446 */       PAD = IAD;
/*      */     } else {
/*  448 */       PAD = minTime(IAD, minusTime(end, B));
/*      */     } 
/*  450 */     this.repeatDuration = IAD;
/*  451 */     return minTime(this.max, maxTime(this.min, PAD));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float minusTime(float t1, float t2) {
/*  458 */     if (isUnresolved(t1) || isUnresolved(t2)) {
/*  459 */       return Float.NaN;
/*      */     }
/*  461 */     if (t1 == Float.POSITIVE_INFINITY || t2 == Float.POSITIVE_INFINITY) {
/*  462 */       return Float.POSITIVE_INFINITY;
/*      */     }
/*  464 */     return t1 - t2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float multiplyTime(float t, float n) {
/*  471 */     if (isUnresolved(t) || t == Float.POSITIVE_INFINITY) {
/*  472 */       return t;
/*      */     }
/*  474 */     return t * n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float minTime(float t1, float t2) {
/*  481 */     if (t1 == 0.0F || t2 == 0.0F) {
/*  482 */       return 0.0F;
/*      */     }
/*  484 */     if ((t1 == Float.POSITIVE_INFINITY || isUnresolved(t1)) && t2 != Float.POSITIVE_INFINITY && !isUnresolved(t2))
/*      */     {
/*  486 */       return t2;
/*      */     }
/*  488 */     if ((t2 == Float.POSITIVE_INFINITY || isUnresolved(t2)) && t1 != Float.POSITIVE_INFINITY && !isUnresolved(t1))
/*      */     {
/*  490 */       return t1;
/*      */     }
/*  492 */     if ((t1 == Float.POSITIVE_INFINITY && isUnresolved(t2)) || (isUnresolved(t1) && t2 == Float.POSITIVE_INFINITY))
/*      */     {
/*  494 */       return Float.POSITIVE_INFINITY;
/*      */     }
/*  496 */     if (t1 < t2) {
/*  497 */       return t1;
/*      */     }
/*  499 */     return t2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float maxTime(float t1, float t2) {
/*  506 */     if ((t1 == Float.POSITIVE_INFINITY || isUnresolved(t1)) && t2 != Float.POSITIVE_INFINITY && !isUnresolved(t2))
/*      */     {
/*  508 */       return t1;
/*      */     }
/*  510 */     if ((t2 == Float.POSITIVE_INFINITY || isUnresolved(t2)) && t1 != Float.POSITIVE_INFINITY && !isUnresolved(t1))
/*      */     {
/*  512 */       return t2;
/*      */     }
/*  514 */     if ((t1 == Float.POSITIVE_INFINITY && isUnresolved(t2)) || (isUnresolved(t1) && t2 == Float.POSITIVE_INFINITY))
/*      */     {
/*  516 */       return Float.NaN;
/*      */     }
/*  518 */     if (t1 > t2) {
/*  519 */       return t1;
/*      */     }
/*  521 */     return t2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float getImplicitDur() {
/*  531 */     return Float.NaN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float notifyNewInterval(Interval interval) {
/*  539 */     float dependentMinTime = Float.POSITIVE_INFINITY;
/*  540 */     Iterator<TimingSpecifier> i = this.beginDependents.iterator();
/*  541 */     while (i.hasNext()) {
/*  542 */       TimingSpecifier ts = i.next();
/*      */       
/*  544 */       float t = ts.newInterval(interval);
/*  545 */       if (t < dependentMinTime) {
/*  546 */         dependentMinTime = t;
/*      */       }
/*      */     } 
/*  549 */     i = this.endDependents.iterator();
/*  550 */     while (i.hasNext()) {
/*  551 */       TimingSpecifier ts = i.next();
/*      */       
/*  553 */       float t = ts.newInterval(interval);
/*  554 */       if (t < dependentMinTime) {
/*  555 */         dependentMinTime = t;
/*      */       }
/*      */     } 
/*  558 */     return dependentMinTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float notifyRemoveInterval(Interval interval) {
/*  567 */     float dependentMinTime = Float.POSITIVE_INFINITY;
/*  568 */     Iterator<TimingSpecifier> i = this.beginDependents.iterator();
/*  569 */     while (i.hasNext()) {
/*  570 */       TimingSpecifier ts = i.next();
/*  571 */       float t = ts.removeInterval(interval);
/*  572 */       if (t < dependentMinTime) {
/*  573 */         dependentMinTime = t;
/*      */       }
/*      */     } 
/*  576 */     i = this.endDependents.iterator();
/*  577 */     while (i.hasNext()) {
/*  578 */       TimingSpecifier ts = i.next();
/*  579 */       float t = ts.removeInterval(interval);
/*  580 */       if (t < dependentMinTime) {
/*  581 */         dependentMinTime = t;
/*      */       }
/*      */     } 
/*  584 */     return dependentMinTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float sampleAt(float parentSimpleTime, boolean hyperlinking) {
/*  603 */     this.isSampling = true;
/*      */     
/*  605 */     float time = parentSimpleTime;
/*      */ 
/*      */ 
/*      */     
/*  609 */     for (Object o : this.handledEvents.entrySet()) {
/*  610 */       boolean useBegin, useEnd; Map.Entry e = (Map.Entry)o;
/*  611 */       Event evt = (Event)e.getKey();
/*  612 */       Set<EventLikeTimingSpecifier> ts = (Set)e.getValue();
/*  613 */       Iterator<EventLikeTimingSpecifier> j = ts.iterator();
/*  614 */       boolean hasBegin = false, hasEnd = false;
/*  615 */       while (j.hasNext() && (!hasBegin || !hasEnd)) {
/*  616 */         EventLikeTimingSpecifier t = j.next();
/*      */         
/*  618 */         if (t.isBegin()) {
/*  619 */           hasBegin = true; continue;
/*      */         } 
/*  621 */         hasEnd = true;
/*      */       } 
/*      */ 
/*      */       
/*  625 */       if (hasBegin && hasEnd) {
/*  626 */         useBegin = (!this.isActive || this.restartMode == 0);
/*  627 */         useEnd = !useBegin;
/*  628 */       } else if (hasBegin && (!this.isActive || this.restartMode == 0)) {
/*      */         
/*  630 */         useBegin = true;
/*  631 */         useEnd = false;
/*  632 */       } else if (hasEnd && this.isActive) {
/*  633 */         useBegin = false;
/*  634 */         useEnd = true;
/*      */       } else {
/*      */         continue;
/*      */       } 
/*  638 */       j = ts.iterator();
/*  639 */       while (j.hasNext()) {
/*  640 */         EventLikeTimingSpecifier t = j.next();
/*      */         
/*  642 */         boolean isBegin = t.isBegin();
/*  643 */         if ((isBegin && useBegin) || (!isBegin && useEnd)) {
/*  644 */           t.resolve(evt);
/*  645 */           this.shouldUpdateCurrentInterval = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*  649 */     this.handledEvents.clear();
/*      */ 
/*      */     
/*  652 */     if (this.currentInterval != null) {
/*  653 */       float begin = this.currentInterval.getBegin();
/*  654 */       if (this.lastSampleTime < begin && time >= begin) {
/*  655 */         if (!this.isActive) {
/*  656 */           toActive(begin);
/*      */         }
/*  658 */         this.isActive = true;
/*  659 */         this.isFrozen = false;
/*  660 */         this.lastRepeatTime = begin;
/*  661 */         fireTimeEvent("beginEvent", this.currentInterval.getBegin(), 0);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  668 */     boolean hasEnded = (this.currentInterval != null && time >= this.currentInterval.getEnd());
/*      */ 
/*      */ 
/*      */     
/*  672 */     if (this.currentInterval != null) {
/*  673 */       float begin = this.currentInterval.getBegin();
/*  674 */       if (time >= begin) {
/*  675 */         float f = getSimpleDur();
/*      */         
/*  677 */         while (time - this.lastRepeatTime >= f && this.lastRepeatTime + f < begin + this.repeatDuration) {
/*  678 */           this.lastRepeatTime += f;
/*  679 */           this.currentRepeatIteration++;
/*  680 */           fireTimeEvent(this.root.getRepeatEventName(), this.lastRepeatTime, this.currentRepeatIteration);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  687 */     float dependentMinTime = Float.POSITIVE_INFINITY;
/*  688 */     if (hyperlinking) {
/*  689 */       this.shouldUpdateCurrentInterval = true;
/*      */     }
/*  691 */     while (this.shouldUpdateCurrentInterval || hasEnded) {
/*  692 */       if (hasEnded) {
/*      */         
/*  694 */         this.previousInterval = this.currentInterval;
/*  695 */         this.isActive = false;
/*  696 */         this.isFrozen = (this.fillMode == 1);
/*  697 */         toInactive(false, this.isFrozen);
/*  698 */         fireTimeEvent("endEvent", this.currentInterval.getEnd(), 0);
/*      */       } 
/*  700 */       boolean first = (this.currentInterval == null && this.previousInterval == null);
/*      */ 
/*      */       
/*  703 */       if (this.currentInterval != null && hyperlinking) {
/*      */ 
/*      */         
/*  706 */         this.isActive = false;
/*  707 */         this.isFrozen = false;
/*  708 */         toInactive(false, false);
/*  709 */         this.currentInterval = null;
/*      */       } 
/*      */       
/*  712 */       if (this.currentInterval == null || hasEnded) {
/*  713 */         if (first || hyperlinking || this.restartMode != 2) {
/*      */           float beginAfter;
/*  715 */           boolean incl = true;
/*  716 */           if (first || hyperlinking) {
/*  717 */             beginAfter = Float.NEGATIVE_INFINITY;
/*      */           } else {
/*      */             
/*  720 */             beginAfter = this.previousInterval.getEnd();
/*  721 */             incl = (beginAfter != this.previousInterval.getBegin());
/*      */           } 
/*  723 */           Interval interval = computeInterval(first, false, beginAfter, incl);
/*      */           
/*  725 */           if (interval == null) {
/*  726 */             this.currentInterval = null;
/*      */           } else {
/*  728 */             float dmt = selectNewInterval(time, interval);
/*  729 */             if (dmt < dependentMinTime) {
/*  730 */               dependentMinTime = dmt;
/*      */             }
/*      */           } 
/*      */         } else {
/*  734 */           this.currentInterval = null;
/*      */         } 
/*      */       } else {
/*  737 */         float currentBegin = this.currentInterval.getBegin();
/*  738 */         if (currentBegin > time) {
/*      */           float beginAfter;
/*      */           
/*  741 */           boolean incl = true;
/*      */           
/*  743 */           if (this.previousInterval == null) {
/*  744 */             beginAfter = Float.NEGATIVE_INFINITY;
/*      */           } else {
/*      */             
/*  747 */             beginAfter = this.previousInterval.getEnd();
/*  748 */             incl = (beginAfter != this.previousInterval.getBegin());
/*      */           } 
/*  750 */           Interval interval = computeInterval(false, false, beginAfter, incl);
/*      */           
/*  752 */           float dmt = notifyRemoveInterval(this.currentInterval);
/*  753 */           if (dmt < dependentMinTime) {
/*  754 */             dependentMinTime = dmt;
/*      */           }
/*  756 */           if (interval == null) {
/*  757 */             this.currentInterval = null;
/*      */           } else {
/*  759 */             dmt = selectNewInterval(time, interval);
/*  760 */             if (dmt < dependentMinTime) {
/*  761 */               dependentMinTime = dmt;
/*      */             }
/*      */           } 
/*      */         } else {
/*      */           
/*  766 */           Interval interval = computeInterval(false, true, currentBegin, true);
/*      */           
/*  768 */           float newEnd = interval.getEnd();
/*  769 */           if (this.currentInterval.getEnd() != newEnd) {
/*  770 */             float dmt = this.currentInterval.setEnd(newEnd, interval.getEndInstanceTime());
/*      */ 
/*      */             
/*  773 */             if (dmt < dependentMinTime) {
/*  774 */               dependentMinTime = dmt;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*  779 */       this.shouldUpdateCurrentInterval = false;
/*  780 */       hyperlinking = false;
/*  781 */       hasEnded = (this.currentInterval != null && time >= this.currentInterval.getEnd());
/*      */     } 
/*      */ 
/*      */     
/*  785 */     float d = getSimpleDur();
/*  786 */     if (this.isActive && !this.isFrozen) {
/*  787 */       if (time - this.currentInterval.getBegin() >= this.repeatDuration) {
/*      */         
/*  789 */         this.isFrozen = (this.fillMode == 1);
/*  790 */         toInactive(true, this.isFrozen);
/*      */       } else {
/*      */         
/*  793 */         sampledAt(time - this.lastRepeatTime, d, this.currentRepeatIteration);
/*      */       } 
/*      */     }
/*  796 */     if (this.isFrozen) {
/*      */       float t;
/*      */       boolean atLast;
/*  799 */       if (this.isActive) {
/*  800 */         t = this.currentInterval.getBegin() + this.repeatDuration - this.lastRepeatTime;
/*  801 */         atLast = (this.lastRepeatTime + d == this.currentInterval.getBegin() + this.repeatDuration);
/*      */       } else {
/*      */         
/*  804 */         t = this.previousInterval.getEnd() - this.lastRepeatTime;
/*  805 */         atLast = (this.lastRepeatTime + d == this.previousInterval.getEnd());
/*      */       } 
/*  807 */       if (atLast) {
/*      */         
/*  809 */         sampledLastValue(this.currentRepeatIteration);
/*      */       } else {
/*      */         
/*  812 */         sampledAt(t % d, d, this.currentRepeatIteration);
/*      */       } 
/*  814 */     } else if (!this.isActive) {
/*      */     
/*      */     } 
/*      */     
/*  818 */     this.isSampling = false;
/*      */     
/*  820 */     this.lastSampleTime = time;
/*  821 */     if (this.currentInterval != null) {
/*  822 */       float t = this.currentInterval.getBegin() - time;
/*  823 */       if (t <= 0.0F) {
/*  824 */         t = (isConstantAnimation() || this.isFrozen) ? (this.currentInterval.getEnd() - time) : 0.0F;
/*      */       }
/*  826 */       if (dependentMinTime < t) {
/*  827 */         return dependentMinTime;
/*      */       }
/*  829 */       return t;
/*      */     } 
/*  831 */     return dependentMinTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean endHasEventConditions() {
/*  840 */     for (TimingSpecifier endTime : this.endTimes) {
/*  841 */       if (endTime.isEventCondition()) {
/*  842 */         return true;
/*      */       }
/*      */     } 
/*  845 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float selectNewInterval(float time, Interval interval) {
/*  858 */     this.currentInterval = interval;
/*  859 */     float dmt = notifyNewInterval(this.currentInterval);
/*  860 */     float beginEventTime = this.currentInterval.getBegin();
/*  861 */     if (time >= beginEventTime) {
/*  862 */       this.lastRepeatTime = beginEventTime;
/*  863 */       if (beginEventTime < 0.0F) {
/*  864 */         beginEventTime = 0.0F;
/*      */       }
/*  866 */       toActive(beginEventTime);
/*  867 */       this.isActive = true;
/*  868 */       this.isFrozen = false;
/*  869 */       fireTimeEvent("beginEvent", beginEventTime, 0);
/*  870 */       float d = getSimpleDur();
/*  871 */       float end = this.currentInterval.getEnd();
/*      */       
/*  873 */       while (time - this.lastRepeatTime >= d && this.lastRepeatTime + d < end) {
/*  874 */         this.lastRepeatTime += d;
/*  875 */         this.currentRepeatIteration++;
/*  876 */         fireTimeEvent(this.root.getRepeatEventName(), this.lastRepeatTime, this.currentRepeatIteration);
/*      */       } 
/*      */     } 
/*      */     
/*  880 */     return dmt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Interval computeInterval(boolean first, boolean fixedBegin, float beginAfter, boolean incl) {
/*  901 */     Iterator<InstanceTime> beginIterator = this.beginInstanceTimes.iterator();
/*  902 */     Iterator<InstanceTime> endIterator = this.endInstanceTimes.iterator();
/*  903 */     float parentSimpleDur = this.parent.getSimpleDur();
/*  904 */     InstanceTime endInstanceTime = endIterator.hasNext() ? endIterator.next() : null;
/*      */ 
/*      */     
/*  907 */     boolean firstEnd = true;
/*  908 */     InstanceTime beginInstanceTime = null;
/*  909 */     InstanceTime nextBeginInstanceTime = null;
/*      */     while (true) {
/*      */       float tempBegin, tempEnd;
/*  912 */       if (fixedBegin) {
/*  913 */         tempBegin = beginAfter;
/*  914 */         while (beginIterator.hasNext()) {
/*  915 */           nextBeginInstanceTime = beginIterator.next();
/*  916 */           if (nextBeginInstanceTime.getTime() > tempBegin) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */       } else {
/*      */         while (true) {
/*  922 */           if (!beginIterator.hasNext())
/*      */           {
/*      */             
/*  925 */             return null;
/*      */           }
/*  927 */           beginInstanceTime = beginIterator.next();
/*  928 */           tempBegin = beginInstanceTime.getTime();
/*  929 */           if ((incl && tempBegin >= beginAfter) || (!incl && tempBegin > beginAfter)) {
/*      */             
/*  931 */             if (beginIterator.hasNext()) {
/*  932 */               nextBeginInstanceTime = beginIterator.next();
/*      */               
/*  934 */               if (beginInstanceTime.getTime() == nextBeginInstanceTime.getTime()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  940 */                 nextBeginInstanceTime = null;
/*      */                 continue;
/*      */               } 
/*      */             } 
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*  948 */       if (tempBegin >= parentSimpleDur)
/*      */       {
/*      */         
/*  951 */         return null;
/*      */       }
/*      */       
/*  954 */       if (this.endTimes.length == 0) {
/*      */         
/*  956 */         tempEnd = tempBegin + getActiveDur(tempBegin, Float.POSITIVE_INFINITY);
/*      */       } else {
/*      */         
/*  959 */         if (this.endInstanceTimes.isEmpty()) {
/*  960 */           tempEnd = Float.NaN;
/*      */         } else {
/*  962 */           tempEnd = endInstanceTime.getTime();
/*  963 */           if ((first && !firstEnd && tempEnd == tempBegin) || (!first && this.currentInterval != null && tempEnd == this.currentInterval.getEnd() && ((incl && beginAfter >= tempEnd) || (!incl && beginAfter > tempEnd)))) {
/*      */             
/*      */             do {
/*      */ 
/*      */ 
/*      */               
/*  969 */               if (!endIterator.hasNext()) {
/*  970 */                 if (endHasEventConditions()) {
/*  971 */                   tempEnd = Float.NaN;
/*      */                   
/*      */                   break;
/*      */                 } 
/*  975 */                 return null;
/*      */               } 
/*  977 */               endInstanceTime = endIterator.next();
/*  978 */               tempEnd = endInstanceTime.getTime();
/*  979 */             } while (tempEnd <= tempBegin);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  984 */           firstEnd = false;
/*      */           
/*  986 */           while (tempEnd < tempBegin) {
/*      */ 
/*      */             
/*  989 */             if (!endIterator.hasNext()) {
/*  990 */               if (endHasEventConditions()) {
/*  991 */                 tempEnd = Float.NaN;
/*      */                 
/*      */                 break;
/*      */               } 
/*  995 */               return null;
/*      */             } 
/*  997 */             endInstanceTime = endIterator.next();
/*  998 */             tempEnd = endInstanceTime.getTime();
/*      */           } 
/*      */         } 
/* 1001 */         float ad = getActiveDur(tempBegin, tempEnd);
/* 1002 */         tempEnd = tempBegin + ad;
/*      */       } 
/* 1004 */       if (!first || tempEnd > 0.0F || (tempBegin == 0.0F && tempEnd == 0.0F) || isUnresolved(tempEnd)) {
/*      */ 
/*      */         
/* 1007 */         if (this.restartMode == 0 && nextBeginInstanceTime != null) {
/*      */           
/* 1009 */           float nextBegin = nextBeginInstanceTime.getTime();
/*      */           
/* 1011 */           if (nextBegin < tempEnd || isUnresolved(tempEnd)) {
/* 1012 */             tempEnd = nextBegin;
/* 1013 */             endInstanceTime = nextBeginInstanceTime;
/*      */           } 
/*      */         } 
/* 1016 */         Interval i = new Interval(tempBegin, tempEnd, beginInstanceTime, endInstanceTime);
/*      */ 
/*      */         
/* 1019 */         return i;
/*      */       } 
/* 1021 */       if (fixedBegin)
/*      */       {
/* 1023 */         return null;
/*      */       }
/* 1025 */       beginAfter = tempEnd;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reset(boolean clearCurrentBegin) {
/* 1034 */     Iterator<InstanceTime> i = this.beginInstanceTimes.iterator();
/* 1035 */     while (i.hasNext()) {
/* 1036 */       InstanceTime it = i.next();
/* 1037 */       if (it.getClearOnReset() && (clearCurrentBegin || this.currentInterval == null || this.currentInterval.getBeginInstanceTime() != it))
/*      */       {
/*      */ 
/*      */         
/* 1041 */         i.remove();
/*      */       }
/*      */     } 
/* 1044 */     i = this.endInstanceTimes.iterator();
/* 1045 */     while (i.hasNext()) {
/* 1046 */       InstanceTime it = i.next();
/* 1047 */       if (it.getClearOnReset()) {
/* 1048 */         i.remove();
/*      */       }
/*      */     } 
/* 1051 */     if (this.isFrozen) {
/* 1052 */       removeFill();
/*      */     }
/* 1054 */     this.currentRepeatIteration = 0;
/* 1055 */     this.lastRepeatTime = Float.NaN;
/* 1056 */     this.isActive = false;
/* 1057 */     this.isFrozen = false;
/* 1058 */     this.lastSampleTime = Float.NaN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void parseAttributes(String begin, String dur, String end, String min, String max, String repeatCount, String repeatDur, String fill, String restart) {
/* 1070 */     if (!this.hasParsed) {
/* 1071 */       parseBegin(begin);
/* 1072 */       parseDur(dur);
/* 1073 */       parseEnd(end);
/* 1074 */       parseMin(min);
/* 1075 */       parseMax(max);
/* 1076 */       if (this.min > this.max) {
/* 1077 */         this.min = 0.0F;
/* 1078 */         this.max = Float.POSITIVE_INFINITY;
/*      */       } 
/* 1080 */       parseRepeatCount(repeatCount);
/* 1081 */       parseRepeatDur(repeatDur);
/* 1082 */       parseFill(fill);
/* 1083 */       parseRestart(restart);
/* 1084 */       this.hasParsed = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseBegin(String begin) {
/*      */     try {
/* 1093 */       if (begin.length() == 0) {
/* 1094 */         begin = "0";
/*      */       }
/* 1096 */       this.beginTimes = TimingSpecifierListProducer.parseTimingSpecifierList(this, true, begin, this.root.useSVG11AccessKeys, this.root.useSVG12AccessKeys);
/*      */     
/*      */     }
/* 1099 */     catch (ParseException ex) {
/* 1100 */       throw createException("attribute.malformed", new Object[] { null, "begin" });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseDur(String dur) {
/* 1110 */     if (dur.equals("media")) {
/* 1111 */       this.durMedia = true;
/* 1112 */       this.simpleDur = Float.NaN;
/*      */     } else {
/* 1114 */       this.durMedia = false;
/* 1115 */       if (dur.length() == 0 || dur.equals("indefinite")) {
/* 1116 */         this.simpleDur = Float.POSITIVE_INFINITY;
/*      */       } else {
/*      */         try {
/* 1119 */           this.simpleDur = parseClockValue(dur, false);
/* 1120 */         } catch (ParseException e) {
/* 1121 */           throw createException("attribute.malformed", new Object[] { null, "dur" });
/*      */         } 
/*      */ 
/*      */         
/* 1125 */         if (this.simpleDur < 0.0F) {
/* 1126 */           this.simpleDur = Float.POSITIVE_INFINITY;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float parseClockValue(String s, boolean parseOffset) throws ParseException {
/* 1137 */     ClockParser p = new ClockParser(parseOffset);
/*      */     class Handler implements ClockHandler {
/* 1139 */       protected float v = 0.0F;
/*      */       public void clockValue(float newClockValue) {
/* 1141 */         this.v = newClockValue;
/*      */       }
/*      */     };
/*      */     
/* 1145 */     Handler h = new Handler();
/* 1146 */     p.setClockHandler(h);
/* 1147 */     p.parse(s);
/* 1148 */     return h.v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseEnd(String end) {
/*      */     try {
/* 1156 */       this.endTimes = TimingSpecifierListProducer.parseTimingSpecifierList(this, false, end, this.root.useSVG11AccessKeys, this.root.useSVG12AccessKeys);
/*      */     
/*      */     }
/* 1159 */     catch (ParseException ex) {
/* 1160 */       throw createException("attribute.malformed", new Object[] { null, "end" });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseMin(String min) {
/* 1170 */     if (min.equals("media")) {
/* 1171 */       this.min = 0.0F;
/* 1172 */       this.minMedia = true;
/*      */     } else {
/* 1174 */       this.minMedia = false;
/* 1175 */       if (min.length() == 0) {
/* 1176 */         this.min = 0.0F;
/*      */       } else {
/*      */         try {
/* 1179 */           this.min = parseClockValue(min, false);
/* 1180 */         } catch (ParseException ex) {
/* 1181 */           this.min = 0.0F;
/*      */         } 
/* 1183 */         if (this.min < 0.0F) {
/* 1184 */           this.min = 0.0F;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseMax(String max) {
/* 1194 */     if (max.equals("media")) {
/* 1195 */       this.max = Float.POSITIVE_INFINITY;
/* 1196 */       this.maxMedia = true;
/*      */     } else {
/* 1198 */       this.maxMedia = false;
/* 1199 */       if (max.length() == 0 || max.equals("indefinite")) {
/* 1200 */         this.max = Float.POSITIVE_INFINITY;
/*      */       } else {
/*      */         try {
/* 1203 */           this.max = parseClockValue(max, false);
/* 1204 */         } catch (ParseException ex) {
/* 1205 */           this.max = Float.POSITIVE_INFINITY;
/*      */         } 
/* 1207 */         if (this.max < 0.0F) {
/* 1208 */           this.max = 0.0F;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseRepeatCount(String repeatCount) {
/* 1218 */     if (repeatCount.length() == 0) {
/* 1219 */       this.repeatCount = Float.NaN;
/* 1220 */     } else if (repeatCount.equals("indefinite")) {
/* 1221 */       this.repeatCount = Float.POSITIVE_INFINITY;
/*      */     } else {
/*      */       try {
/* 1224 */         this.repeatCount = Float.parseFloat(repeatCount);
/* 1225 */         if (this.repeatCount > 0.0F) {
/*      */           return;
/*      */         }
/* 1228 */       } catch (NumberFormatException ex) {
/* 1229 */         throw createException("attribute.malformed", new Object[] { null, "repeatCount" });
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseRepeatDur(String repeatDur) {
/*      */     try {
/* 1241 */       if (repeatDur.length() == 0) {
/* 1242 */         this.repeatDur = Float.NaN;
/* 1243 */       } else if (repeatDur.equals("indefinite")) {
/* 1244 */         this.repeatDur = Float.POSITIVE_INFINITY;
/*      */       } else {
/* 1246 */         this.repeatDur = parseClockValue(repeatDur, false);
/*      */       } 
/* 1248 */     } catch (ParseException ex) {
/* 1249 */       throw createException("attribute.malformed", new Object[] { null, "repeatDur" });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseFill(String fill) {
/* 1259 */     if (fill.length() == 0 || fill.equals("remove")) {
/* 1260 */       this.fillMode = 0;
/* 1261 */     } else if (fill.equals("freeze")) {
/* 1262 */       this.fillMode = 1;
/*      */     } else {
/* 1264 */       throw createException("attribute.malformed", new Object[] { null, "fill" });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseRestart(String restart) {
/* 1274 */     if (restart.length() == 0 || restart.equals("always")) {
/* 1275 */       this.restartMode = 0;
/* 1276 */     } else if (restart.equals("whenNotActive")) {
/* 1277 */       this.restartMode = 1;
/* 1278 */     } else if (restart.equals("never")) {
/* 1279 */       this.restartMode = 2;
/*      */     } else {
/* 1281 */       throw createException("attribute.malformed", new Object[] { null, "restart" });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initialize() {
/* 1291 */     for (TimingSpecifier beginTime : this.beginTimes) {
/* 1292 */       beginTime.initialize();
/*      */     }
/* 1294 */     for (TimingSpecifier endTime : this.endTimes) {
/* 1295 */       endTime.initialize();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void deinitialize() {
/* 1303 */     for (TimingSpecifier beginTime : this.beginTimes) {
/* 1304 */       beginTime.deinitialize();
/*      */     }
/* 1306 */     for (TimingSpecifier endTime : this.endTimes) {
/* 1307 */       endTime.deinitialize();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void beginElement() {
/* 1316 */     beginElement(0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void beginElement(float offset) {
/* 1325 */     float t = this.root.convertWallclockTime(Calendar.getInstance());
/* 1326 */     InstanceTime it = new InstanceTime(null, t + offset, true);
/* 1327 */     addInstanceTime(it, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endElement() {
/* 1335 */     endElement(0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endElement(float offset) {
/* 1344 */     float t = this.root.convertWallclockTime(Calendar.getInstance());
/* 1345 */     InstanceTime it = new InstanceTime(null, t + offset, true);
/* 1346 */     addInstanceTime(it, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getLastSampleTime() {
/* 1353 */     return this.lastSampleTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getCurrentBeginTime() {
/*      */     float begin;
/* 1362 */     if (this.currentInterval == null || (begin = this.currentInterval.getBegin()) < this.lastSampleTime)
/*      */     {
/* 1364 */       return Float.NaN;
/*      */     }
/* 1366 */     return begin;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBegin() {
/* 1373 */     return (this.currentInterval == null || (this.isActive && this.restartMode != 2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canEnd() {
/* 1381 */     return this.isActive;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getHyperlinkBeginTime() {
/* 1390 */     if (this.isActive) {
/* 1391 */       return this.currentInterval.getBegin();
/*      */     }
/* 1393 */     if (!this.beginInstanceTimes.isEmpty()) {
/* 1394 */       return ((InstanceTime)this.beginInstanceTimes.get(0)).getTime();
/*      */     }
/* 1396 */     return Float.NaN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TimingSpecifier[] getBeginTimingSpecifiers() {
/* 1406 */     return (TimingSpecifier[])this.beginTimes.clone();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TimingSpecifier[] getEndTimingSpecifiers() {
/* 1416 */     return (TimingSpecifier[])this.endTimes.clone();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fireTimeEvent(String eventType, float time, int detail) {
/* 1427 */     Calendar t = (Calendar)this.root.getDocumentBeginTime().clone();
/* 1428 */     t.add(14, (int)Math.round(time * 1000.0D));
/* 1429 */     fireTimeEvent(eventType, t, detail);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void eventOccurred(TimingSpecifier t, Event e) {
/* 1438 */     Set<TimingSpecifier> ts = (HashSet)this.handledEvents.get(e);
/* 1439 */     if (ts == null) {
/* 1440 */       ts = new HashSet();
/* 1441 */       this.handledEvents.put(e, ts);
/*      */     } 
/* 1443 */     ts.add(t);
/* 1444 */     this.root.currentIntervalWillUpdate();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract void fireTimeEvent(String paramString, Calendar paramCalendar, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract void toActive(float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract void toInactive(boolean paramBoolean1, boolean paramBoolean2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract void removeFill();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract void sampledAt(float paramFloat1, float paramFloat2, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract void sampledLastValue(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract TimedElement getTimedElementById(String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract EventTarget getEventTargetById(String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract EventTarget getRootEventTarget();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract Element getElement();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract EventTarget getAnimationEventTarget();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract boolean isBefore(TimedElement paramTimedElement);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract boolean isConstantAnimation();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AnimationException createException(String code, Object[] params) {
/* 1540 */     Element e = getElement();
/* 1541 */     if (e != null) {
/* 1542 */       params[0] = e.getNodeName();
/*      */     }
/* 1544 */     return new AnimationException(this, code, params);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1556 */   protected static LocalizableSupport localizableSupport = new LocalizableSupport("org.apache.batik.anim.resources.Messages", TimedElement.class.getClassLoader());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setLocale(Locale l) {
/* 1563 */     localizableSupport.setLocale(l);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Locale getLocale() {
/* 1570 */     return localizableSupport.getLocale();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String formatMessage(String key, Object[] args) throws MissingResourceException {
/* 1579 */     return localizableSupport.formatMessage(key, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toString(float time) {
/* 1586 */     if (Float.isNaN(time))
/* 1587 */       return "UNRESOLVED"; 
/* 1588 */     if (time == Float.POSITIVE_INFINITY) {
/* 1589 */       return "INDEFINITE";
/*      */     }
/* 1591 */     return Float.toString(time);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/TimedElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */