/*     */ package com.github.jaiimageio.impl.plugins.png;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.metadata.IIOMetadataFormat;
/*     */ import javax.imageio.metadata.IIOMetadataFormatImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CLibPNGMetadataFormat
/*     */   extends IIOMetadataFormatImpl
/*     */ {
/*  58 */   private static IIOMetadataFormat instance = null;
/*     */   
/*  60 */   private static String VALUE_0 = "0";
/*  61 */   private static String VALUE_1 = "1";
/*  62 */   private static String VALUE_12 = "12";
/*  63 */   private static String VALUE_23 = "23";
/*  64 */   private static String VALUE_31 = "31";
/*  65 */   private static String VALUE_59 = "59";
/*  66 */   private static String VALUE_60 = "60";
/*  67 */   private static String VALUE_255 = "255";
/*  68 */   private static String VALUE_MAX_16 = "65535";
/*  69 */   private static String VALUE_MAX_32 = "2147483647";
/*     */   
/*     */   private CLibPNGMetadataFormat() {
/*  72 */     super("javax_imageio_png_1.0", 2);
/*     */ 
/*     */ 
/*     */     
/*  76 */     addElement("IHDR", "javax_imageio_png_1.0", 0);
/*     */ 
/*     */     
/*  79 */     addAttribute("IHDR", "width", 2, true, null, VALUE_1, VALUE_MAX_32, true, true);
/*     */ 
/*     */ 
/*     */     
/*  83 */     addAttribute("IHDR", "height", 2, true, null, VALUE_1, VALUE_MAX_32, true, true);
/*     */ 
/*     */ 
/*     */     
/*  87 */     addAttribute("IHDR", "bitDepth", 2, true, (String)null, 
/*     */         
/*  89 */         Arrays.asList(CLibPNGMetadata.IHDR_bitDepths));
/*     */     
/*  91 */     String[] colorTypes = { "Grayscale", "RGB", "Palette", "GrayAlpha", "RGBAlpha" };
/*     */ 
/*     */     
/*  94 */     addAttribute("IHDR", "colorType", 0, true, (String)null, 
/*     */         
/*  96 */         Arrays.asList(colorTypes));
/*     */     
/*  98 */     addAttribute("IHDR", "compressionMethod", 0, true, (String)null, 
/*     */         
/* 100 */         Arrays.asList(CLibPNGMetadata.IHDR_compressionMethodNames));
/*     */     
/* 102 */     addAttribute("IHDR", "filterMethod", 0, true, (String)null, 
/*     */         
/* 104 */         Arrays.asList(CLibPNGMetadata.IHDR_filterMethodNames));
/*     */     
/* 106 */     addAttribute("IHDR", "interlaceMethod", 0, true, (String)null, 
/*     */         
/* 108 */         Arrays.asList(CLibPNGMetadata.IHDR_interlaceMethodNames));
/*     */ 
/*     */     
/* 111 */     addElement("PLTE", "javax_imageio_png_1.0", 1, 256);
/*     */ 
/*     */ 
/*     */     
/* 115 */     addElement("PLTEEntry", "PLTE", 0);
/*     */ 
/*     */     
/* 118 */     addAttribute("PLTEEntry", "index", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 122 */     addAttribute("PLTEEntry", "red", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 126 */     addAttribute("PLTEEntry", "green", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 130 */     addAttribute("PLTEEntry", "blue", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     addElement("bKGD", "javax_imageio_png_1.0", 3);
/*     */ 
/*     */ 
/*     */     
/* 139 */     addElement("bKGD_Grayscale", "bKGD", 0);
/*     */ 
/*     */     
/* 142 */     addAttribute("bKGD_Grayscale", "gray", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 147 */     addElement("bKGD_RGB", "bKGD", 0);
/*     */ 
/*     */     
/* 150 */     addAttribute("bKGD_RGB", "red", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */     
/* 154 */     addAttribute("bKGD_RGB", "green", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */     
/* 158 */     addAttribute("bKGD_RGB", "blue", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     addElement("bKGD_Palette", "bKGD", 0);
/*     */ 
/*     */     
/* 166 */     addAttribute("bKGD_Palette", "index", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     addElement("cHRM", "javax_imageio_png_1.0", 0);
/*     */ 
/*     */     
/* 174 */     addAttribute("cHRM", "whitePointX", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */     
/* 178 */     addAttribute("cHRM", "whitePointY", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */     
/* 182 */     addAttribute("cHRM", "redX", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */     
/* 186 */     addAttribute("cHRM", "redY", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */     
/* 190 */     addAttribute("cHRM", "greenX", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */     
/* 194 */     addAttribute("cHRM", "greenY", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */     
/* 198 */     addAttribute("cHRM", "blueX", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */     
/* 202 */     addAttribute("cHRM", "blueY", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 207 */     addElement("gAMA", "javax_imageio_png_1.0", 0);
/*     */ 
/*     */     
/* 210 */     addAttribute("gAMA", "value", 2, true, null, VALUE_0, VALUE_MAX_32, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 215 */     addElement("hIST", "javax_imageio_png_1.0", 1, 256);
/*     */ 
/*     */ 
/*     */     
/* 219 */     addElement("hISTEntry", "hIST", 0);
/*     */ 
/*     */     
/* 222 */     addAttribute("hISTEntry", "index", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 226 */     addAttribute("hISTEntry", "value", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 231 */     addElement("iCCP", "javax_imageio_png_1.0", 0);
/*     */ 
/*     */     
/* 234 */     addAttribute("iCCP", "profileName", 0, true, null);
/*     */ 
/*     */     
/* 237 */     addAttribute("iCCP", "compressionMethod", 0, true, (String)null, 
/*     */         
/* 239 */         Arrays.asList(CLibPNGMetadata.iCCP_compressionMethodNames));
/*     */     
/* 241 */     addObjectValue("iCCP", byte.class, 0, 2147483647);
/*     */ 
/*     */     
/* 244 */     addElement("iTXt", "javax_imageio_png_1.0", 1, 2147483647);
/*     */ 
/*     */ 
/*     */     
/* 248 */     addElement("iTXtEntry", "iTXt", 0);
/*     */ 
/*     */     
/* 251 */     addAttribute("iTXtEntry", "keyword", 0, true, null);
/*     */ 
/*     */     
/* 254 */     addBooleanAttribute("iTXtEntry", "compressionFlag", false, false);
/*     */ 
/*     */     
/* 257 */     addAttribute("iTXtEntry", "compressionMethod", 0, true, null);
/*     */ 
/*     */     
/* 260 */     addAttribute("iTXtEntry", "languageTag", 0, true, null);
/*     */ 
/*     */     
/* 263 */     addAttribute("iTXtEntry", "translatedKeyword", 0, true, null);
/*     */ 
/*     */     
/* 266 */     addAttribute("iTXtEntry", "text", 0, true, null);
/*     */ 
/*     */ 
/*     */     
/* 270 */     addElement("pHYS", "javax_imageio_png_1.0", 0);
/*     */ 
/*     */     
/* 273 */     addAttribute("pHYS", "pixelsPerUnitXAxis", 2, true, null, VALUE_0, VALUE_MAX_32, true, true);
/*     */ 
/*     */     
/* 276 */     addAttribute("pHYS", "pixelsPerUnitYAxis", 2, true, null, VALUE_0, VALUE_MAX_32, true, true);
/*     */ 
/*     */     
/* 279 */     addAttribute("pHYS", "unitSpecifier", 0, true, (String)null, 
/*     */         
/* 281 */         Arrays.asList(CLibPNGMetadata.unitSpecifierNames));
/*     */ 
/*     */     
/* 284 */     addElement("sBIT", "javax_imageio_png_1.0", 3);
/*     */ 
/*     */ 
/*     */     
/* 288 */     addElement("sBIT_Grayscale", "sBIT", 0);
/*     */ 
/*     */     
/* 291 */     addAttribute("sBIT_Grayscale", "gray", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 296 */     addElement("sBIT_GrayAlpha", "sBIT", 0);
/*     */ 
/*     */     
/* 299 */     addAttribute("sBIT_GrayAlpha", "gray", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 303 */     addAttribute("sBIT_GrayAlpha", "alpha", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 308 */     addElement("sBIT_RGB", "sBIT", 0);
/*     */ 
/*     */     
/* 311 */     addAttribute("sBIT_RGB", "red", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 315 */     addAttribute("sBIT_RGB", "green", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 319 */     addAttribute("sBIT_RGB", "blue", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 324 */     addElement("sBIT_RGBAlpha", "sBIT", 0);
/*     */ 
/*     */     
/* 327 */     addAttribute("sBIT_RGBAlpha", "red", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 331 */     addAttribute("sBIT_RGBAlpha", "green", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 335 */     addAttribute("sBIT_RGBAlpha", "blue", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 339 */     addAttribute("sBIT_RGBAlpha", "alpha", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 344 */     addElement("sBIT_Palette", "sBIT", 0);
/*     */ 
/*     */     
/* 347 */     addAttribute("sBIT_Palette", "red", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 351 */     addAttribute("sBIT_Palette", "green", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 355 */     addAttribute("sBIT_Palette", "blue", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 360 */     addElement("sPLT", "javax_imageio_png_1.0", 1, 256);
/*     */ 
/*     */ 
/*     */     
/* 364 */     addElement("sPLTEntry", "sPLT", 0);
/*     */ 
/*     */     
/* 367 */     addAttribute("sPLTEntry", "index", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 371 */     addAttribute("sPLTEntry", "red", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 375 */     addAttribute("sPLTEntry", "green", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 379 */     addAttribute("sPLTEntry", "blue", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 383 */     addAttribute("sPLTEntry", "alpha", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 388 */     addElement("sRGB", "javax_imageio_png_1.0", 0);
/*     */ 
/*     */     
/* 391 */     addAttribute("sRGB", "renderingIntent", 0, true, (String)null, 
/*     */         
/* 393 */         Arrays.asList(CLibPNGMetadata.renderingIntentNames));
/*     */ 
/*     */     
/* 396 */     addElement("tEXt", "javax_imageio_png_1.0", 1, 2147483647);
/*     */ 
/*     */ 
/*     */     
/* 400 */     addElement("tEXtEntry", "tEXt", 0);
/*     */ 
/*     */     
/* 403 */     addAttribute("tEXtEntry", "keyword", 0, true, null);
/*     */ 
/*     */     
/* 406 */     addAttribute("tEXtEntry", "value", 0, true, null);
/*     */ 
/*     */ 
/*     */     
/* 410 */     addElement("tIME", "javax_imageio_png_1.0", 0);
/*     */ 
/*     */     
/* 413 */     addAttribute("tIME", "year", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */     
/* 417 */     addAttribute("tIME", "month", 2, true, null, VALUE_1, VALUE_12, true, true);
/*     */ 
/*     */ 
/*     */     
/* 421 */     addAttribute("tIME", "day", 2, true, null, VALUE_1, VALUE_31, true, true);
/*     */ 
/*     */ 
/*     */     
/* 425 */     addAttribute("tIME", "hour", 2, true, null, VALUE_0, VALUE_23, true, true);
/*     */ 
/*     */ 
/*     */     
/* 429 */     addAttribute("tIME", "minute", 2, true, null, VALUE_0, VALUE_59, true, true);
/*     */ 
/*     */ 
/*     */     
/* 433 */     addAttribute("tIME", "second", 2, true, null, VALUE_0, VALUE_60, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 438 */     addElement("tRNS", "javax_imageio_png_1.0", 3);
/*     */ 
/*     */ 
/*     */     
/* 442 */     addElement("tRNS_Grayscale", "tRNS", 0);
/*     */ 
/*     */     
/* 445 */     addAttribute("tRNS_Grayscale", "gray", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 450 */     addElement("tRNS_RGB", "tRNS", 0);
/*     */ 
/*     */     
/* 453 */     addAttribute("tRNS_RGB", "red", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */     
/* 457 */     addAttribute("tRNS_RGB", "green", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */     
/* 461 */     addAttribute("tRNS_RGB", "blue", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 466 */     addElement("tRNS_Palette", "tRNS", 0);
/*     */ 
/*     */     
/* 469 */     addAttribute("tRNS_Palette", "index", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */     
/* 473 */     addAttribute("tRNS_Palette", "alpha", 2, true, null, VALUE_0, VALUE_255, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 478 */     addElement("zTXt", "javax_imageio_png_1.0", 1, 2147483647);
/*     */ 
/*     */ 
/*     */     
/* 482 */     addElement("zTXtEntry", "zTXt", 0);
/*     */ 
/*     */     
/* 485 */     addAttribute("zTXtEntry", "keyword", 0, true, null);
/*     */ 
/*     */     
/* 488 */     addAttribute("zTXtEntry", "compressionMethod", 0, true, (String)null, 
/*     */         
/* 490 */         Arrays.asList(CLibPNGMetadata.zTXt_compressionMethodNames));
/*     */     
/* 492 */     addAttribute("zTXtEntry", "text", 0, true, null);
/*     */ 
/*     */ 
/*     */     
/* 496 */     addElement("UnknownChunks", "javax_imageio_png_1.0", 1, 2147483647);
/*     */ 
/*     */ 
/*     */     
/* 500 */     addElement("UnknownChunk", "UnknownChunks", 0);
/*     */ 
/*     */     
/* 503 */     addAttribute("UnknownChunk", "type", 0, true, null);
/*     */ 
/*     */     
/* 506 */     addObjectValue("UnknownChunk", byte.class, 0, 2147483647);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canNodeAppear(String elementName, ImageTypeSpecifier imageType) {
/* 511 */     return true;
/*     */   }
/*     */   
/*     */   public static synchronized IIOMetadataFormat getInstance() {
/* 515 */     if (instance == null) {
/* 516 */       instance = new CLibPNGMetadataFormat();
/*     */     }
/* 518 */     return instance;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/png/CLibPNGMetadataFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */