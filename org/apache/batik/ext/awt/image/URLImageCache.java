/*     */ package org.apache.batik.ext.awt.image;
/*     */ 
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.apache.batik.util.SoftReferenceCache;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class URLImageCache
/*     */   extends SoftReferenceCache
/*     */ {
/*  45 */   static URLImageCache theCache = new URLImageCache();
/*     */   public static URLImageCache getDefaultCache() {
/*  47 */     return theCache;
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
/*     */   public synchronized boolean isPresent(ParsedURL purl) {
/*  63 */     return isPresentImpl(purl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean isDone(ParsedURL purl) {
/*  73 */     return isDoneImpl(purl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Filter request(ParsedURL purl) {
/*  81 */     return (Filter)requestImpl(purl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void clear(ParsedURL purl) {
/*  90 */     clearImpl(purl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void put(ParsedURL purl, Filter filt) {
/* 101 */     putImpl(purl, filt);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/URLImageCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */