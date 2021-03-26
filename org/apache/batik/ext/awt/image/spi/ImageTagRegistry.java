/*     */ package org.apache.batik.ext.awt.image.spi;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.StreamCorruptedException;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.batik.ext.awt.image.URLImageCache;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.ProfileRable;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.apache.batik.util.Service;
/*     */ import org.apache.xmlgraphics.java2d.color.ICCColorSpaceWithIntent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageTagRegistry
/*     */   implements ErrorConstants
/*     */ {
/*  48 */   List entries = new LinkedList();
/*  49 */   List extensions = null;
/*  50 */   List mimeTypes = null;
/*     */   
/*     */   URLImageCache rawCache;
/*     */   URLImageCache imgCache;
/*     */   
/*     */   public ImageTagRegistry() {
/*  56 */     this(null, null);
/*     */   }
/*     */   
/*     */   public ImageTagRegistry(URLImageCache rawCache, URLImageCache imgCache) {
/*  60 */     if (rawCache == null)
/*  61 */       rawCache = new URLImageCache(); 
/*  62 */     if (imgCache == null) {
/*  63 */       imgCache = new URLImageCache();
/*     */     }
/*  65 */     this.rawCache = rawCache;
/*  66 */     this.imgCache = imgCache;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flushCache() {
/*  74 */     this.rawCache.flush();
/*  75 */     this.imgCache.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flushImage(ParsedURL purl) {
/*  82 */     this.rawCache.clear(purl);
/*  83 */     this.imgCache.clear(purl);
/*     */   }
/*     */   public Filter checkCache(ParsedURL purl, ICCColorSpaceWithIntent colorSpace) {
/*     */     ProfileRable profileRable;
/*     */     URLImageCache cache;
/*  88 */     boolean needRawData = (colorSpace != null);
/*     */     
/*  90 */     Filter ret = null;
/*     */     
/*  92 */     if (needRawData) { cache = this.rawCache; }
/*  93 */     else { cache = this.imgCache; }
/*     */     
/*  95 */     ret = cache.request(purl);
/*  96 */     if (ret == null) {
/*  97 */       cache.clear(purl);
/*  98 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 102 */     if (colorSpace != null)
/* 103 */       profileRable = new ProfileRable(ret, colorSpace); 
/* 104 */     return (Filter)profileRable;
/*     */   }
/*     */   
/*     */   public Filter readURL(ParsedURL purl) {
/* 108 */     return readURL(null, purl, null, true, true);
/*     */   }
/*     */   
/*     */   public Filter readURL(ParsedURL purl, ICCColorSpaceWithIntent colorSpace) {
/* 112 */     return readURL(null, purl, colorSpace, true, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter readURL(InputStream is, ParsedURL purl, ICCColorSpaceWithIntent colorSpace, boolean allowOpenStream, boolean returnBrokenLink) {
/*     */     ProfileRable profileRable;
/* 119 */     if (is != null && !is.markSupported())
/*     */     {
/*     */       
/* 122 */       is = new BufferedInputStream(is);
/*     */     }
/*     */     
/* 125 */     boolean needRawData = (colorSpace != null);
/*     */     
/* 127 */     Filter ret = null;
/* 128 */     URLImageCache cache = null;
/*     */     
/* 130 */     if (purl != null) {
/* 131 */       if (needRawData) { cache = this.rawCache; }
/* 132 */       else { cache = this.imgCache; }
/*     */       
/* 134 */       ret = cache.request(purl);
/* 135 */       if (ret != null) {
/*     */         
/* 137 */         if (colorSpace != null)
/* 138 */           profileRable = new ProfileRable(ret, colorSpace); 
/* 139 */         return (Filter)profileRable;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 144 */     boolean openFailed = false;
/* 145 */     List mimeTypes = getRegisteredMimeTypes();
/*     */ 
/*     */     
/* 148 */     Iterator<RegistryEntry> i = this.entries.iterator();
/* 149 */     while (i.hasNext()) {
/* 150 */       RegistryEntry re = i.next();
/* 151 */       if (re instanceof URLRegistryEntry) {
/* 152 */         if (purl == null || !allowOpenStream)
/*     */           continue; 
/* 154 */         URLRegistryEntry ure = (URLRegistryEntry)re;
/* 155 */         if (ure.isCompatibleURL(purl)) {
/* 156 */           ret = ure.handleURL(purl, needRawData);
/*     */ 
/*     */           
/* 159 */           if (ret != null)
/*     */             break; 
/*     */         } 
/*     */         continue;
/*     */       } 
/* 164 */       if (re instanceof StreamRegistryEntry) {
/* 165 */         StreamRegistryEntry sre = (StreamRegistryEntry)re;
/*     */ 
/*     */         
/* 168 */         if (openFailed)
/*     */           continue; 
/*     */         try {
/* 171 */           if (is == null) {
/*     */             
/* 173 */             if (purl == null || !allowOpenStream)
/*     */               break; 
/*     */             try {
/* 176 */               is = purl.openStream(mimeTypes.iterator());
/* 177 */             } catch (IOException ioe) {
/*     */               
/* 179 */               openFailed = true;
/*     */               
/*     */               continue;
/*     */             } 
/* 183 */             if (!is.markSupported())
/*     */             {
/*     */               
/* 186 */               is = new BufferedInputStream(is);
/*     */             }
/*     */           } 
/* 189 */           if (sre.isCompatibleStream(is))
/* 190 */           { ret = sre.handleStream(is, purl, needRawData);
/* 191 */             if (ret != null)
/*     */               break;  } 
/* 193 */         } catch (StreamCorruptedException sce) {
/*     */           
/* 195 */           is = null;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 201 */     if (cache != null) {
/* 202 */       cache.put(purl, ret);
/*     */     }
/* 204 */     if (ret == null) {
/* 205 */       if (!returnBrokenLink)
/* 206 */         return null; 
/* 207 */       if (openFailed)
/*     */       {
/*     */ 
/*     */         
/* 211 */         return getBrokenLinkImage(this, "url.unreachable", null);
/*     */       }
/*     */ 
/*     */       
/* 215 */       return getBrokenLinkImage(this, "url.uninterpretable", null);
/*     */     } 
/*     */     
/* 218 */     if (BrokenLinkProvider.hasBrokenLinkProperty(ret))
/*     */     {
/* 220 */       return returnBrokenLink ? ret : null;
/*     */     }
/*     */     
/* 223 */     if (colorSpace != null) {
/* 224 */       profileRable = new ProfileRable(ret, colorSpace);
/*     */     }
/* 226 */     return (Filter)profileRable;
/*     */   }
/*     */   
/*     */   public Filter readStream(InputStream is) {
/* 230 */     return readStream(is, null);
/*     */   }
/*     */   public Filter readStream(InputStream is, ICCColorSpaceWithIntent colorSpace) {
/*     */     ProfileRable profileRable;
/* 234 */     if (!is.markSupported())
/*     */     {
/* 236 */       is = new BufferedInputStream(is);
/*     */     }
/* 238 */     boolean needRawData = (colorSpace != null);
/*     */     
/* 240 */     Filter ret = null;
/*     */     
/* 242 */     for (Object entry : this.entries) {
/* 243 */       RegistryEntry re = (RegistryEntry)entry;
/*     */       
/* 245 */       if (!(re instanceof StreamRegistryEntry))
/*     */         continue; 
/* 247 */       StreamRegistryEntry sre = (StreamRegistryEntry)re;
/*     */       
/*     */       try {
/* 250 */         if (sre.isCompatibleStream(is))
/* 251 */         { ret = sre.handleStream(is, null, needRawData);
/*     */           
/* 253 */           if (ret != null)
/*     */             break;  } 
/* 255 */       } catch (StreamCorruptedException sce) {
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 260 */     if (ret == null) {
/* 261 */       return getBrokenLinkImage(this, "stream.unreadable", null);
/*     */     }
/* 263 */     if (colorSpace != null && !BrokenLinkProvider.hasBrokenLinkProperty(ret))
/*     */     {
/* 265 */       profileRable = new ProfileRable(ret, colorSpace);
/*     */     }
/* 267 */     return (Filter)profileRable;
/*     */   }
/*     */   
/*     */   public synchronized void register(RegistryEntry newRE) {
/* 271 */     float priority = newRE.getPriority();
/*     */ 
/*     */     
/* 274 */     ListIterator<RegistryEntry> li = this.entries.listIterator();
/* 275 */     while (li.hasNext()) {
/* 276 */       RegistryEntry re = li.next();
/* 277 */       if (re.getPriority() > priority) {
/* 278 */         li.previous();
/* 279 */         li.add(newRE);
/*     */         return;
/*     */       } 
/*     */     } 
/* 283 */     li.add(newRE);
/* 284 */     this.extensions = null;
/* 285 */     this.mimeTypes = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized List getRegisteredExtensions() {
/* 294 */     if (this.extensions != null) {
/* 295 */       return this.extensions;
/*     */     }
/* 297 */     this.extensions = new LinkedList();
/* 298 */     for (Object entry : this.entries) {
/* 299 */       RegistryEntry re = (RegistryEntry)entry;
/* 300 */       this.extensions.addAll(re.getStandardExtensions());
/*     */     } 
/* 302 */     this.extensions = Collections.unmodifiableList(this.extensions);
/* 303 */     return this.extensions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized List getRegisteredMimeTypes() {
/* 312 */     if (this.mimeTypes != null) {
/* 313 */       return this.mimeTypes;
/*     */     }
/* 315 */     this.mimeTypes = new LinkedList();
/* 316 */     for (Object entry : this.entries) {
/* 317 */       RegistryEntry re = (RegistryEntry)entry;
/* 318 */       this.mimeTypes.addAll(re.getMimeTypes());
/*     */     } 
/* 320 */     this.mimeTypes = Collections.unmodifiableList(this.mimeTypes);
/* 321 */     return this.mimeTypes;
/*     */   }
/*     */   
/* 324 */   static ImageTagRegistry registry = null;
/*     */   
/*     */   public static synchronized ImageTagRegistry getRegistry() {
/* 327 */     if (registry != null) {
/* 328 */       return registry;
/*     */     }
/* 330 */     registry = new ImageTagRegistry();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 335 */     registry.register(new JDKRegistryEntry());
/*     */     
/* 337 */     Iterator<RegistryEntry> iter = Service.providers(RegistryEntry.class);
/* 338 */     while (iter.hasNext()) {
/* 339 */       RegistryEntry re = iter.next();
/*     */       
/* 341 */       registry.register(re);
/*     */     } 
/*     */     
/* 344 */     return registry;
/*     */   }
/*     */   
/* 347 */   static BrokenLinkProvider defaultProvider = new DefaultBrokenLinkProvider();
/*     */ 
/*     */   
/* 350 */   static BrokenLinkProvider brokenLinkProvider = null;
/*     */ 
/*     */   
/*     */   public static synchronized Filter getBrokenLinkImage(Object base, String code, Object[] params) {
/* 354 */     Filter ret = null;
/* 355 */     if (brokenLinkProvider != null) {
/* 356 */       ret = brokenLinkProvider.getBrokenLinkImage(base, code, params);
/*     */     }
/* 358 */     if (ret == null) {
/* 359 */       ret = defaultProvider.getBrokenLinkImage(base, code, params);
/*     */     }
/* 361 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void setBrokenLinkProvider(BrokenLinkProvider provider) {
/* 367 */     brokenLinkProvider = provider;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/spi/ImageTagRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */