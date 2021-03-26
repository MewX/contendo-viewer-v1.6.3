/*     */ package org.apache.pdfbox.pdfparser;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSObjectKey;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XrefTrailerResolver
/*     */ {
/*     */   private static class XrefTrailerObj
/*     */   {
/*  64 */     protected COSDictionary trailer = null;
/*     */     
/*     */     private XrefTrailerResolver.XRefType xrefType;
/*     */     
/*  68 */     private final Map<COSObjectKey, Long> xrefTable = new HashMap<COSObjectKey, Long>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private XrefTrailerObj() {
/*  75 */       this.xrefType = XrefTrailerResolver.XRefType.TABLE;
/*     */     }
/*     */ 
/*     */     
/*     */     public void reset() {
/*  80 */       this.xrefTable.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum XRefType
/*     */   {
/*  92 */     TABLE,
/*     */ 
/*     */ 
/*     */     
/*  96 */     STREAM;
/*     */   }
/*     */   
/*  99 */   private final Map<Long, XrefTrailerObj> bytePosToXrefMap = new HashMap<Long, XrefTrailerObj>();
/* 100 */   private XrefTrailerObj curXrefTrailerObj = null;
/* 101 */   private XrefTrailerObj resolvedXrefTrailer = null;
/*     */ 
/*     */   
/* 104 */   private static final Log LOG = LogFactory.getLog(XrefTrailerResolver.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final COSDictionary getFirstTrailer() {
/* 113 */     if (this.bytePosToXrefMap.isEmpty())
/*     */     {
/* 115 */       return null;
/*     */     }
/* 117 */     Set<Long> offsets = this.bytePosToXrefMap.keySet();
/* 118 */     SortedSet<Long> sortedOffset = new TreeSet<Long>(offsets);
/* 119 */     return ((XrefTrailerObj)this.bytePosToXrefMap.get(sortedOffset.first())).trailer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final COSDictionary getLastTrailer() {
/* 129 */     if (this.bytePosToXrefMap.isEmpty())
/*     */     {
/* 131 */       return null;
/*     */     }
/* 133 */     Set<Long> offsets = this.bytePosToXrefMap.keySet();
/* 134 */     SortedSet<Long> sortedOffset = new TreeSet<Long>(offsets);
/* 135 */     return ((XrefTrailerObj)this.bytePosToXrefMap.get(sortedOffset.last())).trailer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getTrailerCount() {
/* 145 */     return this.bytePosToXrefMap.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextXrefObj(long startBytePos, XRefType type) {
/* 155 */     this.bytePosToXrefMap.put(Long.valueOf(startBytePos), this.curXrefTrailerObj = new XrefTrailerObj());
/* 156 */     this.curXrefTrailerObj.xrefType = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XRefType getXrefType() {
/* 166 */     return (this.resolvedXrefTrailer == null) ? null : this.resolvedXrefTrailer.xrefType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXRef(COSObjectKey objKey, long offset) {
/* 177 */     if (this.curXrefTrailerObj == null) {
/*     */ 
/*     */       
/* 180 */       LOG.warn("Cannot add XRef entry for '" + objKey.getNumber() + "' because XRef start was not signalled.");
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 185 */     if (!this.curXrefTrailerObj.xrefTable.containsKey(objKey))
/*     */     {
/* 187 */       this.curXrefTrailerObj.xrefTable.put(objKey, Long.valueOf(offset));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrailer(COSDictionary trailer) {
/* 198 */     if (this.curXrefTrailerObj == null) {
/*     */ 
/*     */       
/* 201 */       LOG.warn("Cannot add trailer because XRef start was not signalled.");
/*     */       return;
/*     */     } 
/* 204 */     this.curXrefTrailerObj.trailer = trailer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCurrentTrailer() {
/* 215 */     return this.curXrefTrailerObj.trailer;
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
/*     */   public void setStartxref(long startxrefBytePosValue) {
/* 234 */     if (this.resolvedXrefTrailer != null) {
/*     */       
/* 236 */       LOG.warn("Method must be called only ones with last startxref value.");
/*     */       
/*     */       return;
/*     */     } 
/* 240 */     this.resolvedXrefTrailer = new XrefTrailerObj();
/* 241 */     this.resolvedXrefTrailer.trailer = new COSDictionary();
/*     */     
/* 243 */     XrefTrailerObj curObj = this.bytePosToXrefMap.get(Long.valueOf(startxrefBytePosValue));
/* 244 */     List<Long> xrefSeqBytePos = new ArrayList<Long>();
/*     */     
/* 246 */     if (curObj == null) {
/*     */ 
/*     */       
/* 249 */       LOG.warn("Did not found XRef object at specified startxref position " + startxrefBytePosValue);
/*     */ 
/*     */       
/* 252 */       xrefSeqBytePos.addAll(this.bytePosToXrefMap.keySet());
/* 253 */       Collections.sort(xrefSeqBytePos);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 258 */       this.resolvedXrefTrailer.xrefType = curObj.xrefType;
/*     */ 
/*     */       
/* 261 */       xrefSeqBytePos.add(Long.valueOf(startxrefBytePosValue));
/* 262 */       while (curObj.trailer != null) {
/*     */         
/* 264 */         long prevBytePos = curObj.trailer.getLong(COSName.PREV, -1L);
/* 265 */         if (prevBytePos == -1L) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 270 */         curObj = this.bytePosToXrefMap.get(Long.valueOf(prevBytePos));
/* 271 */         if (curObj == null) {
/*     */           
/* 273 */           LOG.warn("Did not found XRef object pointed to by 'Prev' key at position " + prevBytePos);
/*     */           break;
/*     */         } 
/* 276 */         xrefSeqBytePos.add(Long.valueOf(prevBytePos));
/*     */ 
/*     */         
/* 279 */         if (xrefSeqBytePos.size() >= this.bytePosToXrefMap.size()) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 285 */       Collections.reverse(xrefSeqBytePos);
/*     */     } 
/*     */ 
/*     */     
/* 289 */     for (Long bPos : xrefSeqBytePos) {
/*     */       
/* 291 */       curObj = this.bytePosToXrefMap.get(bPos);
/* 292 */       if (curObj.trailer != null)
/*     */       {
/* 294 */         this.resolvedXrefTrailer.trailer.addAll(curObj.trailer);
/*     */       }
/* 296 */       this.resolvedXrefTrailer.xrefTable.putAll(curObj.xrefTable);
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
/*     */   public COSDictionary getTrailer() {
/* 309 */     return (this.resolvedXrefTrailer == null) ? null : this.resolvedXrefTrailer.trailer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<COSObjectKey, Long> getXrefTable() {
/* 320 */     return (this.resolvedXrefTrailer == null) ? null : this.resolvedXrefTrailer.xrefTable;
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
/*     */   public Set<Long> getContainedObjectNumbers(int objstmObjNr) {
/* 338 */     if (this.resolvedXrefTrailer == null)
/*     */     {
/* 340 */       return null;
/*     */     }
/* 342 */     Set<Long> refObjNrs = new HashSet<Long>();
/* 343 */     long cmpVal = -objstmObjNr;
/*     */     
/* 345 */     for (Map.Entry<COSObjectKey, Long> xrefEntry : (Iterable<Map.Entry<COSObjectKey, Long>>)this.resolvedXrefTrailer.xrefTable.entrySet()) {
/*     */       
/* 347 */       if (((Long)xrefEntry.getValue()).longValue() == cmpVal)
/*     */       {
/* 349 */         refObjNrs.add(Long.valueOf(((COSObjectKey)xrefEntry.getKey()).getNumber()));
/*     */       }
/*     */     } 
/* 352 */     return refObjNrs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reset() {
/* 361 */     for (XrefTrailerObj trailerObj : this.bytePosToXrefMap.values())
/*     */     {
/* 363 */       trailerObj.reset();
/*     */     }
/* 365 */     this.curXrefTrailerObj = null;
/* 366 */     this.resolvedXrefTrailer = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfparser/XrefTrailerResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */