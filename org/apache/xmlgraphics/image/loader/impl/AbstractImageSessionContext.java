/*     */ package org.apache.xmlgraphics.image.loader.impl;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLDecoder;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.xml.transform.Source;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSessionContext;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSource;
/*     */ import org.apache.xmlgraphics.image.loader.util.ImageUtil;
/*     */ import org.apache.xmlgraphics.image.loader.util.SoftMapCache;
/*     */ import org.apache.xmlgraphics.io.XmlSourceUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractImageSessionContext
/*     */   implements ImageSessionContext
/*     */ {
/*  56 */   private static final Log log = LogFactory.getLog(AbstractImageSessionContext.class);
/*     */   
/*     */   private static boolean noSourceReuse;
/*     */   
/*     */   private final FallbackResolver fallbackResolver;
/*     */   
/*     */   static {
/*  63 */     String noSourceReuseString = System.getProperty(AbstractImageSessionContext.class.getName() + ".no-source-reuse");
/*     */     
/*  65 */     noSourceReuse = Boolean.valueOf(noSourceReuseString).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractImageSessionContext() {
/*  71 */     this.fallbackResolver = new UnrestrictedFallbackResolver();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractImageSessionContext(FallbackResolver fallbackResolver) {
/*  79 */     this.fallbackResolver = fallbackResolver;
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
/*     */   public Source newSource(String uri) {
/*  92 */     Source source = resolveURI(uri);
/*  93 */     if (source instanceof javax.xml.transform.stream.StreamSource || source instanceof javax.xml.transform.sax.SAXSource) {
/*  94 */       return this.fallbackResolver.createSource(source, uri);
/*     */     }
/*     */     
/*  97 */     return source;
/*     */   }
/*     */   
/*     */   protected static ImageInputStream createImageInputStream(InputStream in) throws IOException {
/* 101 */     ImageInputStream iin = ImageIO.createImageInputStream(in);
/* 102 */     return (ImageInputStream)Proxy.newProxyInstance(ImageInputStream.class.getClassLoader(), new Class[] { ImageInputStream.class }, new ObservingImageInputStreamInvocationHandler(iin, in));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class ObservingImageInputStreamInvocationHandler
/*     */     implements InvocationHandler
/*     */   {
/*     */     private ImageInputStream iin;
/*     */     
/*     */     private InputStream in;
/*     */ 
/*     */     
/*     */     public ObservingImageInputStreamInvocationHandler(ImageInputStream iin, InputStream underlyingStream) {
/* 116 */       this.iin = iin;
/* 117 */       this.in = underlyingStream;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
/* 122 */       if ("close".equals(method.getName())) {
/*     */         try {
/* 124 */           return method.invoke(this.iin, args);
/*     */         } finally {
/* 126 */           IOUtils.closeQuietly(this.in);
/* 127 */           this.in = null;
/*     */         } 
/*     */       }
/* 130 */       return method.invoke(this.iin, args);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static File toFile(URL url) {
/* 152 */     if (url == null || !url.getProtocol().equals("file")) {
/* 153 */       return null;
/*     */     }
/*     */     try {
/* 156 */       String filename = "";
/* 157 */       if (url.getHost() != null && url.getHost().length() > 0) {
/* 158 */         filename = filename + Character.toString(File.separatorChar) + Character.toString(File.separatorChar) + url.getHost();
/*     */       }
/*     */ 
/*     */       
/* 162 */       filename = filename + url.getFile().replace('/', File.separatorChar);
/* 163 */       filename = URLDecoder.decode(filename, "UTF-8");
/* 164 */       File f = new File(filename);
/* 165 */       if (!f.isFile()) {
/* 166 */         return null;
/*     */       }
/* 168 */       return f;
/* 169 */     } catch (UnsupportedEncodingException uee) {
/*     */       assert false;
/* 171 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 176 */   private SoftMapCache sessionSources = new SoftMapCache(false);
/*     */ 
/*     */   
/*     */   public Source getSource(String uri) {
/* 180 */     return (Source)this.sessionSources.remove(uri);
/*     */   }
/*     */ 
/*     */   
/*     */   public Source needSource(String uri) throws FileNotFoundException {
/* 185 */     Source src = getSource(uri);
/* 186 */     if (src == null) {
/* 187 */       if (log.isDebugEnabled()) {
/* 188 */         log.debug("Creating new Source for " + uri);
/*     */       }
/*     */       
/* 191 */       src = newSource(uri);
/* 192 */       if (src == null) {
/* 193 */         throw new FileNotFoundException("Image not found: " + uri);
/*     */       }
/*     */     }
/* 196 */     else if (log.isDebugEnabled()) {
/* 197 */       log.debug("Reusing Source for " + uri);
/*     */     } 
/*     */     
/* 200 */     return src;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void returnSource(String uri, Source src) {
/* 206 */     ImageInputStream in = ImageUtil.getImageInputStream(src);
/*     */     try {
/* 208 */       if (in != null && in.getStreamPosition() != 0L) {
/* 209 */         throw new IllegalStateException("ImageInputStream is not reset for: " + uri);
/*     */       }
/* 211 */     } catch (IOException ioe) {
/*     */       
/* 213 */       XmlSourceUtil.closeQuietly(src);
/*     */     } 
/*     */     
/* 216 */     if (isReusable(src)) {
/*     */       
/* 218 */       log.debug("Returning Source for " + uri);
/* 219 */       this.sessionSources.put(uri, src);
/*     */     } else {
/*     */       
/* 222 */       XmlSourceUtil.closeQuietly(src);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isReusable(Source src) {
/* 233 */     if (noSourceReuse) {
/* 234 */       return false;
/*     */     }
/* 236 */     if (src instanceof ImageSource) {
/* 237 */       ImageSource is = (ImageSource)src;
/* 238 */       if (is.getImageInputStream() != null) {
/* 239 */         return true;
/*     */       }
/*     */     } 
/* 242 */     if (src instanceof javax.xml.transform.dom.DOMSource) {
/* 243 */       return true;
/*     */     }
/* 245 */     return false;
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
/*     */   public static interface FallbackResolver
/*     */   {
/*     */     Source createSource(Source param1Source, String param1String);
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
/*     */   public static final class UnrestrictedFallbackResolver
/*     */     implements FallbackResolver
/*     */   {
/*     */     public Source createSource(Source source, String uri) {
/*     */       URL uRL;
/* 275 */       if (source == null) {
/* 276 */         if (AbstractImageSessionContext.log.isDebugEnabled()) {
/* 277 */           AbstractImageSessionContext.log.debug("URI could not be resolved: " + uri);
/*     */         }
/* 279 */         return null;
/*     */       } 
/* 281 */       ImageSource imageSource = null;
/*     */       
/* 283 */       String resolvedURI = source.getSystemId();
/*     */       
/*     */       try {
/* 286 */         uRL = new URL(resolvedURI);
/* 287 */       } catch (MalformedURLException e) {
/* 288 */         uRL = null;
/*     */       } 
/* 290 */       File f = AbstractImageSessionContext.toFile(uRL);
/* 291 */       if (f != null) {
/* 292 */         boolean directFileAccess = true;
/* 293 */         assert source instanceof javax.xml.transform.stream.StreamSource || source instanceof javax.xml.transform.sax.SAXSource;
/* 294 */         InputStream in = XmlSourceUtil.getInputStream(source);
/* 295 */         if (in == null) {
/*     */           try {
/* 297 */             in = new FileInputStream(f);
/* 298 */           } catch (FileNotFoundException fnfe) {
/* 299 */             AbstractImageSessionContext.log.error("Error while opening file. Could not load image from system identifier '" + source.getSystemId() + "' (" + fnfe.getMessage() + ")");
/*     */ 
/*     */             
/* 302 */             return null;
/*     */           } 
/*     */         }
/* 305 */         in = ImageUtil.decorateMarkSupported(in);
/*     */         try {
/* 307 */           if (ImageUtil.isGZIPCompressed(in))
/*     */           {
/* 309 */             directFileAccess = false;
/*     */           }
/* 311 */         } catch (IOException ioe) {
/* 312 */           AbstractImageSessionContext.log.error("Error while checking the InputStream for GZIP compression. Could not load image from system identifier '" + source.getSystemId() + "' (" + ioe.getMessage() + ")");
/*     */ 
/*     */           
/* 315 */           return null;
/*     */         } 
/*     */         
/* 318 */         if (directFileAccess) {
/*     */           
/* 320 */           IOUtils.closeQuietly(in);
/*     */ 
/*     */           
/*     */           try {
/* 324 */             ImageInputStream newInputStream = ImageIO.createImageInputStream(f);
/*     */             
/* 326 */             if (newInputStream == null) {
/* 327 */               AbstractImageSessionContext.log.error("Unable to create ImageInputStream for local file " + f + " from system identifier '" + source.getSystemId() + "'");
/*     */ 
/*     */ 
/*     */               
/* 331 */               return null;
/*     */             } 
/* 333 */             imageSource = new ImageSource(newInputStream, resolvedURI, true);
/*     */           
/*     */           }
/* 336 */           catch (IOException ioe) {
/* 337 */             AbstractImageSessionContext.log.error("Unable to create ImageInputStream for local file from system identifier '" + source.getSystemId() + "' (" + ioe.getMessage() + ")");
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 344 */       if (imageSource == null) {
/* 345 */         if (XmlSourceUtil.hasReader(source) && !ImageUtil.hasInputStream(source))
/*     */         {
/* 347 */           return source;
/*     */         }
/*     */         
/* 350 */         InputStream in = XmlSourceUtil.getInputStream(source);
/* 351 */         if (in == null && uRL != null) {
/*     */           try {
/* 353 */             in = uRL.openStream();
/* 354 */           } catch (Exception ex) {
/* 355 */             AbstractImageSessionContext.log.error("Unable to obtain stream from system identifier '" + source.getSystemId() + "'");
/*     */           } 
/*     */         }
/*     */         
/* 359 */         if (in == null) {
/* 360 */           AbstractImageSessionContext.log.error("The Source that was returned from URI resolution didn't contain an InputStream for URI: " + uri);
/*     */           
/* 362 */           return null;
/*     */         } 
/* 364 */         return (Source)AbstractImageSessionContext.createImageSource(in, source);
/*     */       } 
/* 366 */       return (Source)imageSource;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static ImageSource createImageSource(InputStream in, Source source) {
/*     */     try {
/* 373 */       return new ImageSource(createImageInputStream(ImageUtil.autoDecorateInputStream(in)), source.getSystemId(), false);
/*     */     }
/* 375 */     catch (IOException ioe) {
/* 376 */       log.error("Unable to create ImageInputStream for InputStream from system identifier '" + source.getSystemId() + "' (" + ioe.getMessage() + ")");
/*     */ 
/*     */ 
/*     */       
/* 380 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract Source resolveURI(String paramString);
/*     */ 
/*     */   
/*     */   public static final class RestrictedFallbackResolver
/*     */     implements FallbackResolver
/*     */   {
/*     */     public Source createSource(Source source, String uri) {
/* 392 */       if (source == null) {
/* 393 */         if (AbstractImageSessionContext.log.isDebugEnabled()) {
/* 394 */           AbstractImageSessionContext.log.debug("URI could not be resolved: " + uri);
/*     */         }
/* 396 */         return null;
/*     */       } 
/* 398 */       if (ImageUtil.hasInputStream(source)) {
/* 399 */         return (Source)AbstractImageSessionContext.createImageSource(XmlSourceUtil.getInputStream(source), source);
/*     */       }
/* 401 */       throw new UnsupportedOperationException("There are no contingency mechanisms for I/O.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/AbstractImageSessionContext.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */