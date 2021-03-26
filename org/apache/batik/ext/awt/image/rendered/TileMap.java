/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.image.Raster;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.HashMap;
/*     */ import org.apache.batik.util.CleanerThread;
/*     */ import org.apache.batik.util.HaltingThread;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileMap
/*     */   implements TileStore
/*     */ {
/*     */   private static final boolean DEBUG = false;
/*     */   private static final boolean COUNT = false;
/*  37 */   private HashMap rasters = new HashMap<Object, Object>();
/*     */   
/*     */   static class TileMapLRUMember extends TileLRUMember { public Point pt;
/*     */     public SoftReference parent;
/*     */     
/*     */     class RasterSoftRef extends CleanerThread.SoftReferenceCleared {
/*     */       RasterSoftRef(Object o) {
/*  44 */         super(o);
/*     */       }
/*     */       public void cleared() {
/*  47 */         TileMap tm = TileMap.TileMapLRUMember.this.parent.get();
/*  48 */         if (tm != null)
/*  49 */           tm.rasters.remove(TileMap.TileMapLRUMember.this.pt); 
/*     */       }
/*     */     }
/*     */     
/*     */     TileMapLRUMember(TileMap parent, Point pt, Raster ras) {
/*  54 */       super(ras);
/*  55 */       this.parent = new SoftReference<TileMap>(parent);
/*  56 */       this.pt = pt;
/*     */     }
/*     */     
/*     */     public void setRaster(Raster ras) {
/*  60 */       this.hRaster = ras;
/*  61 */       this.wRaster = (Reference)new RasterSoftRef(ras);
/*     */     } }
/*     */ 
/*     */   
/*  65 */   private TileGenerator source = null;
/*  66 */   private LRUCache cache = null; static int requests;
/*     */   static int misses;
/*     */   
/*     */   public TileMap(TileGenerator source, LRUCache cache) {
/*  70 */     this.cache = cache;
/*  71 */     this.source = source;
/*     */   }
/*     */   public void setTile(int x, int y, Raster ras) {
/*     */     TileMapLRUMember item;
/*  75 */     Point pt = new Point(x, y);
/*     */     
/*  77 */     if (ras == null) {
/*     */       
/*  79 */       Object object = this.rasters.remove(pt);
/*  80 */       if (object != null) {
/*  81 */         this.cache.remove((TileMapLRUMember)object);
/*     */       }
/*     */       return;
/*     */     } 
/*  85 */     Object o = this.rasters.get(pt);
/*     */     
/*  87 */     if (o == null) {
/*  88 */       item = new TileMapLRUMember(this, pt, ras);
/*  89 */       this.rasters.put(pt, item);
/*     */     } else {
/*  91 */       item = (TileMapLRUMember)o;
/*  92 */       item.setRaster(ras);
/*     */     } 
/*     */     
/*  95 */     this.cache.add(item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Raster getTileNoCompute(int x, int y) {
/* 102 */     Point pt = new Point(x, y);
/* 103 */     Object o = this.rasters.get(pt);
/* 104 */     if (o == null) {
/* 105 */       return null;
/*     */     }
/* 107 */     TileMapLRUMember item = (TileMapLRUMember)o;
/* 108 */     Raster ret = item.retrieveRaster();
/* 109 */     if (ret != null)
/* 110 */       this.cache.add(item); 
/* 111 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Raster getTile(int x, int y) {
/* 119 */     Raster ras = null;
/* 120 */     Point pt = new Point(x, y);
/* 121 */     Object o = this.rasters.get(pt);
/* 122 */     TileMapLRUMember item = null;
/* 123 */     if (o != null) {
/* 124 */       item = (TileMapLRUMember)o;
/* 125 */       ras = item.retrieveRaster();
/*     */     } 
/*     */     
/* 128 */     if (ras == null) {
/*     */ 
/*     */ 
/*     */       
/* 132 */       ras = this.source.genTile(x, y);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 137 */       if (HaltingThread.hasBeenHalted()) {
/* 138 */         return ras;
/*     */       }
/* 140 */       if (item != null) {
/* 141 */         item.setRaster(ras);
/*     */       } else {
/* 143 */         item = new TileMapLRUMember(this, pt, ras);
/* 144 */         this.rasters.put(pt, item);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 149 */     this.cache.add(item);
/*     */     
/* 151 */     return ras;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/TileMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */