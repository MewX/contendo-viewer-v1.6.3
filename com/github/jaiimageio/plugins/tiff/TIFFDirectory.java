/*     */ package com.github.jaiimageio.plugins.tiff;
/*     */ 
/*     */ import com.github.jaiimageio.impl.plugins.tiff.TIFFIFD;
/*     */ import com.github.jaiimageio.impl.plugins.tiff.TIFFImageMetadata;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import javax.imageio.metadata.IIOInvalidTreeException;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFDirectory
/*     */   implements Cloneable
/*     */ {
/*     */   private static final int MAX_LOW_FIELD_TAG_NUM = 532;
/*     */   private List tagSets;
/*     */   private TIFFTag parentTag;
/* 148 */   private TIFFField[] lowFields = new TIFFField[533];
/*     */ 
/*     */   
/* 151 */   private int numLowFields = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   private Map highFields = new TreeMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TIFFDirectory createFromMetadata(IIOMetadata tiffImageMetadata) throws IIOInvalidTreeException {
/*     */     TIFFImageMetadata tim;
/* 184 */     if (tiffImageMetadata == null) {
/* 185 */       throw new IllegalArgumentException("tiffImageMetadata == null");
/*     */     }
/*     */ 
/*     */     
/* 189 */     if (tiffImageMetadata instanceof TIFFImageMetadata) {
/* 190 */       tim = (TIFFImageMetadata)tiffImageMetadata;
/*     */     } else {
/*     */       
/* 193 */       ArrayList<BaselineTIFFTagSet> l = new ArrayList(1);
/* 194 */       l.add(BaselineTIFFTagSet.getInstance());
/* 195 */       tim = new TIFFImageMetadata(l);
/*     */ 
/*     */       
/* 198 */       String formatName = null;
/* 199 */       if ("com_sun_media_imageio_plugins_tiff_image_1.0"
/* 200 */         .equals(tiffImageMetadata.getNativeMetadataFormatName())) {
/* 201 */         formatName = "com_sun_media_imageio_plugins_tiff_image_1.0";
/*     */       } else {
/*     */         
/* 204 */         String[] extraNames = tiffImageMetadata.getExtraMetadataFormatNames();
/* 205 */         if (extraNames != null) {
/* 206 */           for (int i = 0; i < extraNames.length; i++) {
/* 207 */             if ("com_sun_media_imageio_plugins_tiff_image_1.0"
/* 208 */               .equals(extraNames[i])) {
/* 209 */               formatName = extraNames[i];
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         }
/* 215 */         if (formatName == null) {
/* 216 */           if (tiffImageMetadata.isStandardMetadataFormatSupported()) {
/* 217 */             formatName = "javax_imageio_1.0";
/*     */           } else {
/*     */             
/* 220 */             throw new IllegalArgumentException("Parameter does not support required metadata format!");
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 227 */       tim.setFromTree(formatName, tiffImageMetadata
/* 228 */           .getAsTree(formatName));
/*     */     } 
/*     */     
/* 231 */     return (TIFFDirectory)tim.getRootIFD();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static TIFFIFD getDirectoryAsIFD(TIFFDirectory dir) {
/* 238 */     if (dir instanceof TIFFIFD) {
/* 239 */       return (TIFFIFD)dir;
/*     */     }
/*     */ 
/*     */     
/* 243 */     TIFFIFD ifd = new TIFFIFD(Arrays.asList(dir.getTagSets()), dir.getParentTag());
/* 244 */     TIFFField[] fields = dir.getTIFFFields();
/* 245 */     int numFields = fields.length;
/* 246 */     for (int i = 0; i < numFields; i++) {
/* 247 */       TIFFField f = fields[i];
/* 248 */       TIFFTag tag = f.getTag();
/* 249 */       if (tag.isIFDPointer()) {
/*     */         
/* 251 */         TIFFIFD tIFFIFD = getDirectoryAsIFD((TIFFDirectory)f.getData());
/* 252 */         f = new TIFFField(tag, f.getType(), f.getCount(), tIFFIFD);
/*     */       } 
/* 254 */       ifd.addTIFFField(f);
/*     */     } 
/*     */     
/* 257 */     return ifd;
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
/*     */   public TIFFDirectory(TIFFTagSet[] tagSets, TIFFTag parentTag) {
/* 273 */     if (tagSets == null) {
/* 274 */       throw new IllegalArgumentException("tagSets == null!");
/*     */     }
/* 276 */     this.tagSets = new ArrayList(tagSets.length);
/* 277 */     int numTagSets = tagSets.length;
/* 278 */     for (int i = 0; i < numTagSets; i++) {
/* 279 */       this.tagSets.add(tagSets[i]);
/*     */     }
/* 281 */     this.parentTag = parentTag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TIFFTagSet[] getTagSets() {
/* 291 */     return (TIFFTagSet[])this.tagSets.toArray((Object[])new TIFFTagSet[this.tagSets.size()]);
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
/*     */   public void addTagSet(TIFFTagSet tagSet) {
/* 303 */     if (tagSet == null) {
/* 304 */       throw new IllegalArgumentException("tagSet == null");
/*     */     }
/*     */     
/* 307 */     if (!this.tagSets.contains(tagSet)) {
/* 308 */       this.tagSets.add(tagSet);
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
/*     */   public void removeTagSet(TIFFTagSet tagSet) {
/* 321 */     if (tagSet == null) {
/* 322 */       throw new IllegalArgumentException("tagSet == null");
/*     */     }
/*     */     
/* 325 */     if (this.tagSets.contains(tagSet)) {
/* 326 */       this.tagSets.remove(tagSet);
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
/*     */   public TIFFTag getParentTag() {
/* 338 */     return this.parentTag;
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
/*     */   public TIFFTag getTag(int tagNumber) {
/* 351 */     return TIFFIFD.getTag(tagNumber, this.tagSets);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumTIFFFields() {
/* 361 */     return this.numLowFields + this.highFields.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsTIFFField(int tagNumber) {
/* 372 */     return ((tagNumber >= 0 && tagNumber <= 532 && this.lowFields[tagNumber] != null) || this.highFields
/*     */       
/* 374 */       .containsKey(new Integer(tagNumber)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTIFFField(TIFFField f) {
/* 384 */     if (f == null) {
/* 385 */       throw new IllegalArgumentException("f == null");
/*     */     }
/* 387 */     int tagNumber = f.getTagNumber();
/* 388 */     if (tagNumber >= 0 && tagNumber <= 532) {
/* 389 */       if (this.lowFields[tagNumber] == null) {
/* 390 */         this.numLowFields++;
/*     */       }
/* 392 */       this.lowFields[tagNumber] = f;
/*     */     } else {
/* 394 */       this.highFields.put(new Integer(tagNumber), f);
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
/*     */   public TIFFField getTIFFField(int tagNumber) {
/*     */     TIFFField f;
/* 407 */     if (tagNumber >= 0 && tagNumber <= 532) {
/* 408 */       f = this.lowFields[tagNumber];
/*     */     } else {
/* 410 */       f = (TIFFField)this.highFields.get(new Integer(tagNumber));
/*     */     } 
/* 412 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTIFFField(int tagNumber) {
/* 421 */     if (tagNumber >= 0 && tagNumber <= 532) {
/* 422 */       if (this.lowFields[tagNumber] != null) {
/* 423 */         this.numLowFields--;
/* 424 */         this.lowFields[tagNumber] = null;
/*     */       } 
/*     */     } else {
/* 427 */       this.highFields.remove(new Integer(tagNumber));
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
/*     */   public TIFFField[] getTIFFFields() {
/* 439 */     TIFFField[] fields = new TIFFField[this.numLowFields + this.highFields.size()];
/*     */ 
/*     */     
/* 442 */     int nextIndex = 0;
/* 443 */     for (int i = 0; i <= 532; i++) {
/* 444 */       if (this.lowFields[i] != null) {
/* 445 */         fields[nextIndex++] = this.lowFields[i];
/* 446 */         if (nextIndex == this.numLowFields) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/* 451 */     if (!this.highFields.isEmpty()) {
/* 452 */       Iterator keys = this.highFields.keySet().iterator();
/* 453 */       while (keys.hasNext()) {
/* 454 */         fields[nextIndex++] = (TIFFField)this.highFields.get(keys.next());
/*     */       }
/*     */     } 
/*     */     
/* 458 */     return fields;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTIFFFields() {
/* 465 */     Arrays.fill((Object[])this.lowFields, (Object)null);
/* 466 */     this.numLowFields = 0;
/* 467 */     this.highFields.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadata getAsMetadata() {
/* 477 */     return (IIOMetadata)new TIFFImageMetadata(getDirectoryAsIFD(this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 486 */     TIFFDirectory dir = new TIFFDirectory(getTagSets(), getParentTag());
/* 487 */     TIFFField[] fields = getTIFFFields();
/* 488 */     int numFields = fields.length;
/* 489 */     for (int i = 0; i < numFields; i++) {
/* 490 */       dir.addTIFFField(fields[i]);
/*     */     }
/*     */     
/* 493 */     return dir;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/plugins/tiff/TIFFDirectory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */