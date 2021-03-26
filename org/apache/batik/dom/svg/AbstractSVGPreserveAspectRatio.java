/*     */ package org.apache.batik.dom.svg;
/*     */ 
/*     */ import org.apache.batik.parser.DefaultPreserveAspectRatioHandler;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.apache.batik.parser.PreserveAspectRatioHandler;
/*     */ import org.apache.batik.parser.PreserveAspectRatioParser;
/*     */ import org.apache.batik.util.SVGConstants;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGPreserveAspectRatio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSVGPreserveAspectRatio
/*     */   implements SVGConstants, SVGPreserveAspectRatio
/*     */ {
/*  44 */   protected static final String[] ALIGN_VALUES = new String[] { null, "none", "xMinYMin", "xMidYMin", "xMaxYMin", "xMinYMid", "xMidYMid", "xMaxYMid", "xMinYMax", "xMidYMax", "xMaxYMax" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   protected static final String[] MEET_OR_SLICE_VALUES = new String[] { null, "meet", "slice" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getValueAsString(short align, short meetOrSlice) {
/*  76 */     if (align < 1 || align > 10) {
/*  77 */       return null;
/*     */     }
/*  79 */     String value = ALIGN_VALUES[align];
/*  80 */     if (align == 1) {
/*  81 */       return value;
/*     */     }
/*  83 */     if (meetOrSlice < 1 || meetOrSlice > 2) {
/*  84 */       return null;
/*     */     }
/*  86 */     return value + ' ' + MEET_OR_SLICE_VALUES[meetOrSlice];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   protected short align = 6;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   protected short meetOrSlice = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getAlign() {
/* 107 */     return this.align;
/*     */   }
/*     */   
/*     */   public short getMeetOrSlice() {
/* 111 */     return this.meetOrSlice;
/*     */   }
/*     */   
/*     */   public void setAlign(short align) {
/* 115 */     this.align = align;
/* 116 */     setAttributeValue(getValueAsString());
/*     */   }
/*     */   
/*     */   public void setMeetOrSlice(short meetOrSlice) {
/* 120 */     this.meetOrSlice = meetOrSlice;
/* 121 */     setAttributeValue(getValueAsString());
/*     */   }
/*     */   
/*     */   public void reset() {
/* 125 */     this.align = 6;
/* 126 */     this.meetOrSlice = 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void setAttributeValue(String paramString) throws DOMException;
/*     */ 
/*     */   
/*     */   protected abstract DOMException createDOMException(short paramShort, String paramString, Object[] paramArrayOfObject);
/*     */ 
/*     */   
/*     */   protected void setValueAsString(String value) throws DOMException {
/* 138 */     PreserveAspectRatioParserHandler ph = new PreserveAspectRatioParserHandler();
/*     */     try {
/* 140 */       PreserveAspectRatioParser p = new PreserveAspectRatioParser();
/* 141 */       p.setPreserveAspectRatioHandler((PreserveAspectRatioHandler)ph);
/* 142 */       p.parse(value);
/* 143 */       this.align = ph.getAlign();
/* 144 */       this.meetOrSlice = ph.getMeetOrSlice();
/* 145 */     } catch (ParseException ex) {
/* 146 */       throw createDOMException((short)13, "preserve.aspect.ratio", new Object[] { value });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValueAsString() {
/* 156 */     if (this.align < 1 || this.align > 10) {
/* 157 */       throw createDOMException((short)13, "preserve.aspect.ratio.align", new Object[] { Integer.valueOf(this.align) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 162 */     String value = ALIGN_VALUES[this.align];
/* 163 */     if (this.align == 1) {
/* 164 */       return value;
/*     */     }
/*     */     
/* 167 */     if (this.meetOrSlice < 1 || this.meetOrSlice > 2) {
/* 168 */       throw createDOMException((short)13, "preserve.aspect.ratio.meet.or.slice", new Object[] { Integer.valueOf(this.meetOrSlice) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 173 */     return value + ' ' + MEET_OR_SLICE_VALUES[this.meetOrSlice];
/*     */   }
/*     */   
/*     */   protected class PreserveAspectRatioParserHandler
/*     */     extends DefaultPreserveAspectRatioHandler {
/* 178 */     public short align = 6;
/*     */     
/* 180 */     public short meetOrSlice = 1;
/*     */     
/*     */     public short getAlign() {
/* 183 */       return this.align;
/*     */     }
/*     */     
/*     */     public short getMeetOrSlice() {
/* 187 */       return this.meetOrSlice;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void none() throws ParseException {
/* 196 */       this.align = 1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMaxYMax() throws ParseException {
/* 205 */       this.align = 10;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMaxYMid() throws ParseException {
/* 214 */       this.align = 7;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMaxYMin() throws ParseException {
/* 223 */       this.align = 4;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMidYMax() throws ParseException {
/* 232 */       this.align = 9;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMidYMid() throws ParseException {
/* 241 */       this.align = 6;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMidYMin() throws ParseException {
/* 250 */       this.align = 3;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMinYMax() throws ParseException {
/* 259 */       this.align = 8;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMinYMid() throws ParseException {
/* 268 */       this.align = 5;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMinYMin() throws ParseException {
/* 277 */       this.align = 2;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void meet() throws ParseException {
/* 286 */       this.meetOrSlice = 1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void slice() throws ParseException {
/* 295 */       this.meetOrSlice = 2;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/AbstractSVGPreserveAspectRatio.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */