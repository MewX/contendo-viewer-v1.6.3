/*     */ package org.apache.batik.anim.timing;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.LinkedList;
/*     */ import org.apache.batik.parser.DefaultTimingSpecifierListHandler;
/*     */ import org.apache.batik.parser.TimingSpecifierListHandler;
/*     */ import org.apache.batik.parser.TimingSpecifierListParser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimingSpecifierListProducer
/*     */   extends DefaultTimingSpecifierListHandler
/*     */ {
/*  40 */   protected LinkedList timingSpecifiers = new LinkedList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected TimedElement owner;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isBegin;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimingSpecifierListProducer(TimedElement owner, boolean isBegin) {
/*  56 */     this.owner = owner;
/*  57 */     this.isBegin = isBegin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimingSpecifier[] getTimingSpecifiers() {
/*  64 */     return (TimingSpecifier[])this.timingSpecifiers.toArray((Object[])new TimingSpecifier[this.timingSpecifiers.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TimingSpecifier[] parseTimingSpecifierList(TimedElement owner, boolean isBegin, String spec, boolean useSVG11AccessKeys, boolean useSVG12AccessKeys) {
/*  73 */     TimingSpecifierListParser p = new TimingSpecifierListParser(useSVG11AccessKeys, useSVG12AccessKeys);
/*     */ 
/*     */     
/*  76 */     TimingSpecifierListProducer pp = new TimingSpecifierListProducer(owner, isBegin);
/*     */     
/*  78 */     p.setTimingSpecifierListHandler((TimingSpecifierListHandler)pp);
/*  79 */     p.parse(spec);
/*  80 */     TimingSpecifier[] specs = pp.getTimingSpecifiers();
/*  81 */     return specs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void offset(float offset) {
/*  90 */     TimingSpecifier ts = new OffsetTimingSpecifier(this.owner, this.isBegin, offset);
/*  91 */     this.timingSpecifiers.add(ts);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void syncbase(float offset, String syncbaseID, String timeSymbol) {
/*  99 */     TimingSpecifier ts = new SyncbaseTimingSpecifier(this.owner, this.isBegin, offset, syncbaseID, (timeSymbol.charAt(0) == 'b'));
/*     */     
/* 101 */     this.timingSpecifiers.add(ts);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eventbase(float offset, String eventbaseID, String eventType) {
/* 109 */     TimingSpecifier ts = new EventbaseTimingSpecifier(this.owner, this.isBegin, offset, eventbaseID, eventType);
/*     */     
/* 111 */     this.timingSpecifiers.add(ts);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void repeat(float offset, String syncbaseID) {
/* 119 */     TimingSpecifier ts = new RepeatTimingSpecifier(this.owner, this.isBegin, offset, syncbaseID);
/*     */     
/* 121 */     this.timingSpecifiers.add(ts);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void repeat(float offset, String syncbaseID, int repeatIteration) {
/* 130 */     TimingSpecifier ts = new RepeatTimingSpecifier(this.owner, this.isBegin, offset, syncbaseID, repeatIteration);
/*     */     
/* 132 */     this.timingSpecifiers.add(ts);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accesskey(float offset, char key) {
/* 139 */     TimingSpecifier ts = new AccesskeyTimingSpecifier(this.owner, this.isBegin, offset, key);
/*     */     
/* 141 */     this.timingSpecifiers.add(ts);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accessKeySVG12(float offset, String keyName) {
/* 148 */     TimingSpecifier ts = new AccesskeyTimingSpecifier(this.owner, this.isBegin, offset, keyName);
/*     */     
/* 150 */     this.timingSpecifiers.add(ts);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mediaMarker(String syncbaseID, String markerName) {
/* 157 */     TimingSpecifier ts = new MediaMarkerTimingSpecifier(this.owner, this.isBegin, syncbaseID, markerName);
/*     */     
/* 159 */     this.timingSpecifiers.add(ts);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void wallclock(Calendar time) {
/* 166 */     TimingSpecifier ts = new WallclockTimingSpecifier(this.owner, this.isBegin, time);
/* 167 */     this.timingSpecifiers.add(ts);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void indefinite() {
/* 174 */     TimingSpecifier ts = new IndefiniteTimingSpecifier(this.owner, this.isBegin);
/* 175 */     this.timingSpecifiers.add(ts);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/TimingSpecifierListProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */