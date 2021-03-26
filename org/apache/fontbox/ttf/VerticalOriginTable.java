/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VerticalOriginTable
/*     */   extends TTFTable
/*     */ {
/*     */   public static final String TAG = "VORG";
/*     */   private float version;
/*     */   private int defaultVertOriginY;
/*     */   private Map<Integer, Integer> origins;
/*     */   
/*     */   VerticalOriginTable(TrueTypeFont font) {
/*  51 */     super(font);
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
/*     */   public void read(TrueTypeFont ttf, TTFDataStream data) throws IOException {
/*  64 */     this.version = data.read32Fixed();
/*  65 */     this.defaultVertOriginY = data.readSignedShort();
/*  66 */     int numVertOriginYMetrics = data.readUnsignedShort();
/*  67 */     this.origins = new ConcurrentHashMap<Integer, Integer>(numVertOriginYMetrics);
/*  68 */     for (int i = 0; i < numVertOriginYMetrics; i++) {
/*     */       
/*  70 */       int g = data.readUnsignedShort();
/*  71 */       int y = data.readSignedShort();
/*  72 */       this.origins.put(Integer.valueOf(g), Integer.valueOf(y));
/*     */     } 
/*  74 */     this.initialized = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVersion() {
/*  82 */     return this.version;
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
/*     */   public int getOriginY(int gid) {
/*  94 */     if (this.origins.containsKey(Integer.valueOf(gid)))
/*     */     {
/*  96 */       return ((Integer)this.origins.get(Integer.valueOf(gid))).intValue();
/*     */     }
/*     */ 
/*     */     
/* 100 */     return this.defaultVertOriginY;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/VerticalOriginTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */