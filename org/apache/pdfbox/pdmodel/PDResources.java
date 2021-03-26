/*     */ package org.apache.pdfbox.pdmodel;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFontFactory;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.pattern.PDAbstractPattern;
/*     */ import org.apache.pdfbox.pdmodel.graphics.shading.PDShading;
/*     */ import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PDResources
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary resources;
/*     */   private final ResourceCache cache;
/*  57 */   private final Map<COSName, SoftReference<PDFont>> directFontCache = new HashMap<COSName, SoftReference<PDFont>>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDResources() {
/*  65 */     this.resources = new COSDictionary();
/*  66 */     this.cache = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDResources(COSDictionary resourceDictionary) {
/*  76 */     if (resourceDictionary == null)
/*     */     {
/*  78 */       throw new IllegalArgumentException("resourceDictionary is null");
/*     */     }
/*  80 */     this.resources = resourceDictionary;
/*  81 */     this.cache = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDResources(COSDictionary resourceDictionary, ResourceCache resourceCache) {
/*  92 */     if (resourceDictionary == null)
/*     */     {
/*  94 */       throw new IllegalArgumentException("resourceDictionary is null");
/*     */     }
/*  96 */     this.resources = resourceDictionary;
/*  97 */     this.cache = resourceCache;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/* 106 */     return this.resources;
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
/*     */   public PDFont getFont(COSName name) throws IOException {
/* 120 */     COSObject indirect = getIndirect(COSName.FONT, name);
/* 121 */     if (this.cache != null && indirect != null) {
/*     */       
/* 123 */       PDFont cached = this.cache.getFont(indirect);
/* 124 */       if (cached != null)
/*     */       {
/* 126 */         return cached;
/*     */       }
/*     */     }
/* 129 */     else if (indirect == null) {
/*     */       
/* 131 */       SoftReference<PDFont> ref = this.directFontCache.get(name);
/* 132 */       if (ref != null) {
/*     */         
/* 134 */         PDFont cached = ref.get();
/* 135 */         if (cached != null)
/*     */         {
/* 137 */           return cached;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 142 */     PDFont font = null;
/* 143 */     COSDictionary dict = (COSDictionary)get(COSName.FONT, name);
/* 144 */     if (dict != null)
/*     */     {
/* 146 */       font = PDFontFactory.createFont(dict, this.cache);
/*     */     }
/*     */     
/* 149 */     if (this.cache != null && indirect != null) {
/*     */       
/* 151 */       this.cache.put(indirect, font);
/*     */     }
/* 153 */     else if (indirect == null) {
/*     */       
/* 155 */       this.directFontCache.put(name, new SoftReference<PDFont>(font));
/*     */     } 
/* 157 */     return font;
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
/*     */   public PDColorSpace getColorSpace(COSName name) throws IOException {
/* 169 */     return getColorSpace(name, false);
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
/*     */   public PDColorSpace getColorSpace(COSName name, boolean wasDefault) throws IOException {
/*     */     PDColorSpace colorSpace;
/* 184 */     COSObject indirect = getIndirect(COSName.COLORSPACE, name);
/* 185 */     if (this.cache != null && indirect != null) {
/*     */       
/* 187 */       PDColorSpace cached = this.cache.getColorSpace(indirect);
/* 188 */       if (cached != null)
/*     */       {
/* 190 */         return cached;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 196 */     COSBase object = get(COSName.COLORSPACE, name);
/* 197 */     if (object != null) {
/*     */       
/* 199 */       colorSpace = PDColorSpace.create(object, this, wasDefault);
/*     */     }
/*     */     else {
/*     */       
/* 203 */       colorSpace = PDColorSpace.create((COSBase)name, this, wasDefault);
/*     */     } 
/*     */ 
/*     */     
/* 207 */     if (this.cache != null && !(colorSpace instanceof org.apache.pdfbox.pdmodel.graphics.color.PDPattern))
/*     */     {
/* 209 */       this.cache.put(indirect, colorSpace);
/*     */     }
/* 211 */     return colorSpace;
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
/*     */   public boolean hasColorSpace(COSName name) {
/* 223 */     return (get(COSName.COLORSPACE, name) != null);
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
/*     */   public PDExtendedGraphicsState getExtGState(COSName name) {
/* 235 */     COSObject indirect = getIndirect(COSName.EXT_G_STATE, name);
/* 236 */     if (this.cache != null && indirect != null) {
/*     */       
/* 238 */       PDExtendedGraphicsState cached = this.cache.getExtGState(indirect);
/* 239 */       if (cached != null)
/*     */       {
/* 241 */         return cached;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 246 */     PDExtendedGraphicsState extGState = null;
/* 247 */     COSDictionary dict = (COSDictionary)get(COSName.EXT_G_STATE, name);
/* 248 */     if (dict != null)
/*     */     {
/* 250 */       extGState = new PDExtendedGraphicsState(dict);
/*     */     }
/*     */     
/* 253 */     if (this.cache != null)
/*     */     {
/* 255 */       this.cache.put(indirect, extGState);
/*     */     }
/* 257 */     return extGState;
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
/*     */   public PDShading getShading(COSName name) throws IOException {
/* 271 */     COSObject indirect = getIndirect(COSName.SHADING, name);
/* 272 */     if (this.cache != null && indirect != null) {
/*     */       
/* 274 */       PDShading cached = this.cache.getShading(indirect);
/* 275 */       if (cached != null)
/*     */       {
/* 277 */         return cached;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 282 */     PDShading shading = null;
/* 283 */     COSDictionary dict = (COSDictionary)get(COSName.SHADING, name);
/* 284 */     if (dict != null)
/*     */     {
/* 286 */       shading = PDShading.create(dict);
/*     */     }
/*     */     
/* 289 */     if (this.cache != null)
/*     */     {
/* 291 */       this.cache.put(indirect, shading);
/*     */     }
/* 293 */     return shading;
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
/*     */   public PDAbstractPattern getPattern(COSName name) throws IOException {
/* 307 */     COSObject indirect = getIndirect(COSName.PATTERN, name);
/* 308 */     if (this.cache != null && indirect != null) {
/*     */       
/* 310 */       PDAbstractPattern cached = this.cache.getPattern(indirect);
/* 311 */       if (cached != null)
/*     */       {
/* 313 */         return cached;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 318 */     PDAbstractPattern pattern = null;
/* 319 */     COSDictionary dict = (COSDictionary)get(COSName.PATTERN, name);
/* 320 */     if (dict != null)
/*     */     {
/* 322 */       pattern = PDAbstractPattern.create(dict);
/*     */     }
/*     */     
/* 325 */     if (this.cache != null)
/*     */     {
/* 327 */       this.cache.put(indirect, pattern);
/*     */     }
/* 329 */     return pattern;
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
/*     */   public PDPropertyList getProperties(COSName name) {
/* 341 */     COSObject indirect = getIndirect(COSName.PROPERTIES, name);
/* 342 */     if (this.cache != null && indirect != null) {
/*     */       
/* 344 */       PDPropertyList cached = this.cache.getProperties(indirect);
/* 345 */       if (cached != null)
/*     */       {
/* 347 */         return cached;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 352 */     PDPropertyList propertyList = null;
/* 353 */     COSDictionary dict = (COSDictionary)get(COSName.PROPERTIES, name);
/* 354 */     if (dict != null)
/*     */     {
/* 356 */       propertyList = PDPropertyList.create(dict);
/*     */     }
/*     */     
/* 359 */     if (this.cache != null)
/*     */     {
/* 361 */       this.cache.put(indirect, propertyList);
/*     */     }
/* 363 */     return propertyList;
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
/*     */   public boolean isImageXObject(COSName name) {
/* 375 */     COSBase value = get(COSName.XOBJECT, name);
/* 376 */     if (value == null)
/*     */     {
/* 378 */       return false;
/*     */     }
/* 380 */     if (value instanceof COSObject)
/*     */     {
/* 382 */       value = ((COSObject)value).getObject();
/*     */     }
/* 384 */     if (!(value instanceof COSStream))
/*     */     {
/* 386 */       return false;
/*     */     }
/* 388 */     COSStream stream = (COSStream)value;
/* 389 */     return COSName.IMAGE.equals(stream.getCOSName(COSName.SUBTYPE));
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
/*     */   public PDXObject getXObject(COSName name) throws IOException {
/*     */     PDXObject xobject;
/* 403 */     COSObject indirect = getIndirect(COSName.XOBJECT, name);
/* 404 */     if (this.cache != null && indirect != null) {
/*     */       
/* 406 */       PDXObject cached = this.cache.getXObject(indirect);
/* 407 */       if (cached != null)
/*     */       {
/* 409 */         return cached;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 415 */     COSBase value = get(COSName.XOBJECT, name);
/* 416 */     if (value == null) {
/*     */       
/* 418 */       xobject = null;
/*     */     }
/* 420 */     else if (value instanceof COSObject) {
/*     */       
/* 422 */       xobject = PDXObject.createXObject(((COSObject)value).getObject(), this);
/*     */     }
/*     */     else {
/*     */       
/* 426 */       xobject = PDXObject.createXObject(value, this);
/*     */     } 
/* 428 */     if (this.cache != null && isAllowedCache(xobject))
/*     */     {
/* 430 */       this.cache.put(indirect, xobject);
/*     */     }
/* 432 */     return xobject;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isAllowedCache(PDXObject xobject) {
/* 437 */     if (xobject instanceof PDImageXObject) {
/*     */       
/* 439 */       COSBase colorSpace = xobject.getCOSObject().getDictionaryObject(COSName.COLORSPACE);
/* 440 */       if (colorSpace instanceof COSName) {
/*     */ 
/*     */         
/* 443 */         COSName colorSpaceName = (COSName)colorSpace;
/* 444 */         if (colorSpaceName.equals(COSName.DEVICECMYK) && hasColorSpace(COSName.DEFAULT_CMYK))
/*     */         {
/* 446 */           return false;
/*     */         }
/* 448 */         if (colorSpaceName.equals(COSName.DEVICERGB) && hasColorSpace(COSName.DEFAULT_RGB))
/*     */         {
/* 450 */           return false;
/*     */         }
/* 452 */         if (colorSpaceName.equals(COSName.DEVICEGRAY) && hasColorSpace(COSName.DEFAULT_GRAY))
/*     */         {
/* 454 */           return false;
/*     */         }
/* 456 */         if (hasColorSpace(colorSpaceName))
/*     */         {
/* 458 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/* 462 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSObject getIndirect(COSName kind, COSName name) {
/* 470 */     COSDictionary dict = (COSDictionary)this.resources.getDictionaryObject(kind);
/* 471 */     if (dict == null)
/*     */     {
/* 473 */       return null;
/*     */     }
/* 475 */     COSBase base = dict.getItem(name);
/* 476 */     if (base instanceof COSObject)
/*     */     {
/* 478 */       return (COSObject)base;
/*     */     }
/*     */     
/* 481 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSBase get(COSName kind, COSName name) {
/* 489 */     COSDictionary dict = (COSDictionary)this.resources.getDictionaryObject(kind);
/* 490 */     if (dict == null)
/*     */     {
/* 492 */       return null;
/*     */     }
/* 494 */     return dict.getDictionaryObject(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<COSName> getColorSpaceNames() {
/* 504 */     return getNames(COSName.COLORSPACE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<COSName> getXObjectNames() {
/* 514 */     return getNames(COSName.XOBJECT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<COSName> getFontNames() {
/* 524 */     return getNames(COSName.FONT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<COSName> getPropertiesNames() {
/* 534 */     return getNames(COSName.PROPERTIES);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<COSName> getShadingNames() {
/* 544 */     return getNames(COSName.SHADING);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<COSName> getPatternNames() {
/* 554 */     return getNames(COSName.PATTERN);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<COSName> getExtGStateNames() {
/* 564 */     return getNames(COSName.EXT_G_STATE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Iterable<COSName> getNames(COSName kind) {
/* 574 */     COSDictionary dict = (COSDictionary)this.resources.getDictionaryObject(kind);
/* 575 */     if (dict == null)
/*     */     {
/* 577 */       return Collections.emptySet();
/*     */     }
/* 579 */     return dict.keySet();
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
/*     */   public COSName add(PDFont font) {
/* 591 */     return add(COSName.FONT, "F", (COSObjectable)font);
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
/*     */   public COSName add(PDColorSpace colorSpace) {
/* 603 */     return add(COSName.COLORSPACE, "cs", (COSObjectable)colorSpace);
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
/*     */   public COSName add(PDExtendedGraphicsState extGState) {
/* 615 */     return add(COSName.EXT_G_STATE, "gs", (COSObjectable)extGState);
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
/*     */   public COSName add(PDShading shading) {
/* 627 */     return add(COSName.SHADING, "sh", (COSObjectable)shading);
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
/*     */   public COSName add(PDAbstractPattern pattern) {
/* 639 */     return add(COSName.PATTERN, "p", (COSObjectable)pattern);
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
/*     */   public COSName add(PDPropertyList properties) {
/* 651 */     if (properties instanceof org.apache.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup)
/*     */     {
/* 653 */       return add(COSName.PROPERTIES, "oc", (COSObjectable)properties);
/*     */     }
/*     */ 
/*     */     
/* 657 */     return add(COSName.PROPERTIES, "Prop", (COSObjectable)properties);
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
/*     */   public COSName add(PDImageXObject image) {
/* 670 */     return add(COSName.XOBJECT, "Im", (COSObjectable)image);
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
/*     */   public COSName add(PDFormXObject form) {
/* 682 */     return add(COSName.XOBJECT, "Form", (COSObjectable)form);
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
/*     */   public COSName add(PDXObject xobject, String prefix) {
/* 695 */     return add(COSName.XOBJECT, prefix, (COSObjectable)xobject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSName add(COSName kind, String prefix, COSObjectable object) {
/* 704 */     COSDictionary dict = (COSDictionary)this.resources.getDictionaryObject(kind);
/* 705 */     if (dict != null && dict.containsValue(object.getCOSObject()))
/*     */     {
/* 707 */       return dict.getKeyForValue(object.getCOSObject());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 712 */     if (dict != null && COSName.FONT.equals(kind))
/*     */     {
/* 714 */       for (Map.Entry<COSName, COSBase> entry : (Iterable<Map.Entry<COSName, COSBase>>)dict.entrySet()) {
/*     */         
/* 716 */         if (entry.getValue() instanceof COSObject && object
/* 717 */           .getCOSObject() == ((COSObject)entry.getValue()).getObject())
/*     */         {
/* 719 */           return entry.getKey();
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 725 */     COSName name = createKey(kind, prefix);
/* 726 */     put(kind, name, object);
/* 727 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSName createKey(COSName kind, String prefix) {
/* 735 */     COSDictionary dict = (COSDictionary)this.resources.getDictionaryObject(kind);
/* 736 */     if (dict == null)
/*     */     {
/* 738 */       return COSName.getPDFName(prefix + '\001');
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 743 */     int n = dict.keySet().size();
/*     */     
/*     */     while (true) {
/* 746 */       n++;
/* 747 */       String key = prefix + n;
/*     */       
/* 749 */       if (!dict.containsKey(key)) {
/* 750 */         return COSName.getPDFName(key);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void put(COSName kind, COSName name, COSObjectable object) {
/* 758 */     COSDictionary dict = (COSDictionary)this.resources.getDictionaryObject(kind);
/* 759 */     if (dict == null) {
/*     */       
/* 761 */       dict = new COSDictionary();
/* 762 */       this.resources.setItem(kind, (COSBase)dict);
/*     */     } 
/* 764 */     dict.setItem(name, object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(COSName name, PDFont font) {
/* 775 */     put(COSName.FONT, name, (COSObjectable)font);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(COSName name, PDColorSpace colorSpace) {
/* 786 */     put(COSName.COLORSPACE, name, (COSObjectable)colorSpace);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(COSName name, PDExtendedGraphicsState extGState) {
/* 797 */     put(COSName.EXT_G_STATE, name, (COSObjectable)extGState);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(COSName name, PDShading shading) {
/* 808 */     put(COSName.SHADING, name, (COSObjectable)shading);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(COSName name, PDAbstractPattern pattern) {
/* 819 */     put(COSName.PATTERN, name, (COSObjectable)pattern);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(COSName name, PDPropertyList properties) {
/* 830 */     put(COSName.PROPERTIES, name, (COSObjectable)properties);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(COSName name, PDXObject xobject) {
/* 841 */     put(COSName.XOBJECT, name, (COSObjectable)xobject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceCache getResourceCache() {
/* 851 */     return this.cache;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDResources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */