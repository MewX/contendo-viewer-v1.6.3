/*     */ package org.apache.batik.parser;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimingSpecifierParser
/*     */   extends TimingParser
/*     */ {
/*     */   protected TimingSpecifierHandler timingSpecifierHandler;
/*     */   
/*     */   public TimingSpecifierParser(boolean useSVG11AccessKeys, boolean useSVG12AccessKeys) {
/*  47 */     super(useSVG11AccessKeys, useSVG12AccessKeys);
/*  48 */     this.timingSpecifierHandler = DefaultTimingSpecifierHandler.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimingSpecifierHandler(TimingSpecifierHandler handler) {
/*  55 */     this.timingSpecifierHandler = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimingSpecifierHandler getTimingSpecifierHandler() {
/*  62 */     return this.timingSpecifierHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doParse() throws ParseException, IOException {
/*  69 */     this.current = this.reader.read();
/*  70 */     Object[] spec = parseTimingSpecifier();
/*  71 */     skipSpaces();
/*  72 */     if (this.current != -1) {
/*  73 */       reportError("end.of.stream.expected", new Object[] { Integer.valueOf(this.current) });
/*     */     }
/*     */     
/*  76 */     handleTimingSpecifier(spec);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleTimingSpecifier(Object[] spec) {
/*     */     float offset;
/*     */     String syncbaseID;
/*  84 */     int type = ((Integer)spec[0]).intValue();
/*  85 */     switch (type) {
/*     */       case 0:
/*  87 */         this.timingSpecifierHandler.offset(((Float)spec[1]).floatValue());
/*     */         break;
/*     */       case 1:
/*  90 */         this.timingSpecifierHandler.syncbase(((Float)spec[1]).floatValue(), (String)spec[2], (String)spec[3]);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/*  95 */         this.timingSpecifierHandler.eventbase(((Float)spec[1]).floatValue(), (String)spec[2], (String)spec[3]);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 100 */         offset = ((Float)spec[1]).floatValue();
/* 101 */         syncbaseID = (String)spec[2];
/* 102 */         if (spec[3] == null) {
/* 103 */           this.timingSpecifierHandler.repeat(offset, syncbaseID); break;
/*     */         } 
/* 105 */         this.timingSpecifierHandler.repeat(offset, syncbaseID, ((Integer)spec[3]).intValue());
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 111 */         this.timingSpecifierHandler.accesskey(((Float)spec[1]).floatValue(), ((Character)spec[2]).charValue());
/*     */         break;
/*     */ 
/*     */       
/*     */       case 5:
/* 116 */         this.timingSpecifierHandler.accessKeySVG12(((Float)spec[1]).floatValue(), (String)spec[2]);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 6:
/* 121 */         this.timingSpecifierHandler.mediaMarker((String)spec[1], (String)spec[2]);
/*     */         break;
/*     */       
/*     */       case 7:
/* 125 */         this.timingSpecifierHandler.wallclock((Calendar)spec[1]);
/*     */         break;
/*     */       case 8:
/* 128 */         this.timingSpecifierHandler.indefinite();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/TimingSpecifierParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */