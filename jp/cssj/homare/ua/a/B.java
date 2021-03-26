/*     */ package jp.cssj.homare.ua.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface B
/*     */ {
/*  13 */   public static final b a = new b("input.property-pi", false);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  18 */   public static final A b = new A("input.filters", "xslt default-to-xhtml loose-html");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  24 */   public static final A c = new A("input.stylesheet.titles", null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   public static final A d = new A("input.default-encoding", "JISUniAutoDetect");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   public static final A e = new A("input.default-stylesheet", null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   public static final A f = new A("input.xslt.default-stylesheet", null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public static final b g = new b("input.http.referer", true);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public static final e h = new e("input.http.connection.timeout", 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public static final e i = new e("input.http.socket.timeout", 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public static final A j = new A("input.http.proxy.host", null);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public static final e k = new e("input.http.proxy.port", 8080);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public static final A l = new A("input.http.proxy.authentication.user", null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public static final A m = new A("input.http.proxy.authentication.password", "");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public static final b n = new b("input.http.authentication.preemptive", false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String o = "input.http.authentication.";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String p = "input.http.cookie.";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String q = "input.http.header.";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public static final b r = new b("input.viewport", false);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public static final A s = new A("output.page-width", "210mm");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public static final A t = new A("output.page-height", "297mm");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public static final A u = new A("output.page-margins", "12.7mm");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public static final A v = new A("output.paper-width", null);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public static final A w = new A("output.paper-height", null);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public static final c x = new c("output.print-mode", new String[] { "single-side", "double-side", "left-side", "right-side" }, (short)2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public static final A y = new A("output.htrim", "1cm");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public static final A z = new A("output.vtrim", "1cm");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public static final A A = new A("output.trims", null);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public static final c B = new c("output.fit-to-paper", new String[] { "false", "true", "preserve-aspect-ratio" }, (short)1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public static final c C = new c("output.auto-rotate", new String[] { "none", "content", "paper" }, (short)1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public static final b D = new b("output.clip", true);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   public static final A E = new A("output.default-font-family", "serif");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 183 */   public static final d F = new d("output.text-size", 1.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public static final b G = new b("output.auto-height", false);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   public static final b H = new b("output.expand-with-content", false);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 198 */   public static final b I = new b("output.no-page-break", false);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 203 */   public static final A J = new A("output.type", "application/pdf");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String K = "input.include";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String L = "input.exclude";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 218 */   public static final f M = new f("output.size-limit", -1L);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 223 */   public static final e N = new e("output.page-limit", -1);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   public static final c O = new c("output.page-limit.abort", new String[] { "force", "normal" }, (short)1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   public static final c P = new c("output.marks", new String[] { "none", "crop", "cross", "both", "hidden" }, (short)1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   public static final A Q = new A("output.media_types", "all print paged visual bitmap static");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 246 */   public static final c R = new c("output.broken-image", new String[] { "none", "hidden", "cross", "annotation" }, (short)1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public static final c S = new c("output.color", new String[] { "rgb", "gray", "cmyk" }, (short)1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 258 */   public static final d T = new d("output.resolution", 96.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 263 */   public static final d U = new d("output.image.resolution", 96.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 269 */   public static final b V = new b("output.image.antialias", true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String W = "output.meta.";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 280 */   public static final A X = new A("output.pdf.fonts.policy", "cid-keyed");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 286 */   public static final c Y = new c("output.pdf.compression", new String[] { "none", "ascii", "binary" }, (short)3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 292 */   public static final c Z = new c("output.pdf.image.compression", new String[] { "flate", "jpeg", "jpeg2000" }, (short)1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 299 */   public static final e aa = new e("output.pdf.image.compression.lossless", 200);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 305 */   public static final e ab = new e("output.pdf.image.max-width", 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 311 */   public static final e ac = new e("output.pdf.image.max-height", 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ad = "output.pdf.attachments.";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 322 */   public static final c ae = new c("output.pdf.version", new String[] { "1.2", "1.3", "1.4", "1.5", "1.6", "1.7", "1.4A-1", "1.4X-1" }, (short)4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 328 */   public static final c af = new c("output.pdf.encryption", new String[] { "none", "v1", "v2", "v4" }, (short)1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 334 */   public static final A ag = new A("output.pdf.encryption.user-password", "");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 340 */   public static final A ah = new A("output.pdf.encryption.owner-password", null);
/*     */ 
/*     */ 
/*     */   
/* 344 */   public static final b ai = new b("output.pdf.encryption.permissions.print", true);
/*     */   
/* 346 */   public static final b aj = new b("output.pdf.encryption.permissions.modify", true);
/*     */   
/* 348 */   public static final b ak = new b("output.pdf.encryption.permissions.copy", true);
/*     */   
/* 350 */   public static final b al = new b("output.pdf.encryption.permissions.add", true);
/*     */   
/* 352 */   public static final b am = new b("output.pdf.encryption.permissions.fill", true);
/*     */   
/* 354 */   public static final b an = new b("output.pdf.encryption.permissions.extract", true);
/*     */   
/* 356 */   public static final b ao = new b("output.pdf.encryption.permissions.assemble", true);
/*     */   
/* 358 */   public static final b ap = new b("output.pdf.encryption.permissions.print-high", true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 364 */   public static final e aq = new e("output.pdf.encryption.length", 128);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 370 */   public static final b ar = new b("output.pdf.bookmarks", false);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 375 */   public static final b as = new b("output.pdf.hyperlinks", false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 381 */   public static final c at = new c("output.pdf.hyperlinks.href", new String[] { "relative", "absolute" }, (short)1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 387 */   public static final A au = new A("output.pdf.hyperlinks.base", null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 393 */   public static final b av = new b("output.pdf.hyperlinks.fragment", true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 399 */   public static final c aw = new c("output.pdf.jpeg-image", new String[] { "raw", "to-flate", "recompress" }, (short)1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 405 */   public static final A ax = new A("output.pdf.platform-encoding", "MS932");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 411 */   public static final e ay = new e("processing.pass-count", 1);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 416 */   public static final b az = new b("processing.middle-pass", false);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 421 */   public static final b aA = new b("processing.page-references", false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 427 */   public static final b aB = new b("processing.fail-on-fatal-error", true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 433 */   public static final A aC = new A("output.pdf.file-id", null);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 438 */   public static final A aD = new A("output.pdf.meta.creation-date", null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 444 */   public static final A aE = new A("output.pdf.meta.mod-date", null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 450 */   public static final A aF = new A("output.marks.spine-width", null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 456 */   public static final c aG = new c("output.pdf.encryption.v4.cfm", new String[] { "v2", "aesv2" }, (short)1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 462 */   public static final A aH = new A("output.pdf.watermark.uri", null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 468 */   public static final c aI = new c("output.pdf.watermark.mode", new String[] { "back", "front" }, (short)1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 474 */   public static final d aJ = new d("output.pdf.watermark.opacity", 1.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 480 */   public static final b aK = new b("output.pdf.watermark.view", true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 486 */   public static final b aL = new b("output.pdf.watermark.print", true);
/*     */ 
/*     */ 
/*     */   
/* 490 */   public static final b aM = new b("output.pdf.viewer-preferences.hide-toolber", false);
/*     */ 
/*     */   
/* 493 */   public static final b aN = new b("output.pdf.viewer-preferences.hide-menubar", false);
/*     */ 
/*     */   
/* 496 */   public static final b aO = new b("output.pdf.viewer-preferences.hide-windowUI", false);
/*     */ 
/*     */   
/* 499 */   public static final b aP = new b("output.pdf.viewer-preferences.fit-window", false);
/*     */ 
/*     */   
/* 502 */   public static final b aQ = new b("output.pdf.viewer-preferences.center-window", false);
/*     */ 
/*     */   
/* 505 */   public static final b aR = new b("output.pdf.viewer-preferences.display-doc-title", false);
/*     */ 
/*     */   
/* 508 */   public static final c aS = new c("output.pdf.viewer-preferences.non-full-screen-page-mode", new String[] { "use-none", "use-outlines", "use-thumbs", "use-oc" }, (short)1);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 513 */   public static final c aT = new c("output.pdf.viewer-preferences.print-scaling", new String[] { "scaling-none", "app-default" }, (short)2);
/*     */ 
/*     */ 
/*     */   
/* 517 */   public static final c aU = new c("output.pdf.viewer-preferences.duplex", new String[] { "none", "simplex", "flip-short-edge", "flip-long-edge" }, (short)1);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 522 */   public static final b aV = new b("output.pdf.viewer-preferences.pick-tray-by-pdf-size", false);
/*     */ 
/*     */   
/* 525 */   public static final A aW = new A("output.pdf.viewer-preferences.print-page-range", null);
/*     */ 
/*     */   
/* 528 */   public static final e aX = new e("output.pdf.viewer-preferences.num-copies", 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 534 */   public static final A aY = new A("output.pdf.open-action.java-script", null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 540 */   public static final b aZ = new b("output.use-meta-info", true);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/ua/a/B.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */