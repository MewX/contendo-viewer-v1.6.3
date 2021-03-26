/*     */ package org.apache.batik.util;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.security.AccessControlException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PreferenceManager
/*     */ {
/*  76 */   protected Properties internal = null;
/*  77 */   protected Map defaults = null;
/*  78 */   protected String prefFileName = null;
/*  79 */   protected String fullName = null;
/*     */   
/*  81 */   protected static final String USER_HOME = getSystemProperty("user.home");
/*  82 */   protected static final String USER_DIR = getSystemProperty("user.dir");
/*  83 */   protected static final String FILE_SEP = getSystemProperty("file.separator");
/*     */   
/*  85 */   private static String PREF_DIR = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String getSystemProperty(String prop) {
/*     */     try {
/*  93 */       return System.getProperty(prop);
/*  94 */     } catch (AccessControlException e) {
/*  95 */       return "";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PreferenceManager(String prefFileName) {
/* 105 */     this(prefFileName, null);
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
/*     */   public PreferenceManager(String prefFileName, Map defaults) {
/* 117 */     this.prefFileName = prefFileName;
/* 118 */     this.defaults = defaults;
/* 119 */     this.internal = new Properties();
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
/*     */   public static void setPreferenceDirectory(String dir) {
/* 132 */     PREF_DIR = dir;
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
/*     */   public static String getPreferenceDirectory() {
/* 144 */     return PREF_DIR;
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
/*     */   public void load() throws IOException {
/* 160 */     FileInputStream fis = null;
/* 161 */     if (this.fullName != null)
/*     */       try {
/* 163 */         fis = new FileInputStream(this.fullName);
/* 164 */       } catch (IOException e1) {
/* 165 */         this.fullName = null;
/*     */       }  
/* 167 */     if (this.fullName == null) {
/* 168 */       if (PREF_DIR != null) {
/*     */         try {
/* 170 */           fis = new FileInputStream(this.fullName = PREF_DIR + FILE_SEP + this.prefFileName);
/*     */         
/*     */         }
/* 173 */         catch (IOException e2) {
/* 174 */           this.fullName = null;
/*     */         } 
/*     */       }
/* 177 */       if (this.fullName == null) {
/*     */         try {
/* 179 */           fis = new FileInputStream(this.fullName = USER_HOME + FILE_SEP + this.prefFileName);
/*     */         
/*     */         }
/* 182 */         catch (IOException e3) {
/*     */           try {
/* 184 */             fis = new FileInputStream(this.fullName = USER_DIR + FILE_SEP + this.prefFileName);
/*     */           }
/* 186 */           catch (IOException e4) {
/* 187 */             this.fullName = null;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 192 */     if (this.fullName != null) {
/*     */       try {
/* 194 */         this.internal.load(fis);
/*     */       } finally {
/* 196 */         fis.close();
/*     */       } 
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
/*     */   public void save() throws IOException {
/* 213 */     FileOutputStream fos = null;
/* 214 */     if (this.fullName != null)
/*     */       try {
/* 216 */         fos = new FileOutputStream(this.fullName);
/* 217 */       } catch (IOException e1) {
/* 218 */         this.fullName = null;
/*     */       }  
/* 220 */     if (this.fullName == null) {
/* 221 */       if (PREF_DIR != null) {
/*     */         try {
/* 223 */           fos = new FileOutputStream(this.fullName = PREF_DIR + FILE_SEP + this.prefFileName);
/*     */         
/*     */         }
/* 226 */         catch (IOException e2) {
/* 227 */           this.fullName = null;
/*     */         } 
/*     */       }
/* 230 */       if (this.fullName == null) {
/*     */         try {
/* 232 */           fos = new FileOutputStream(this.fullName = USER_HOME + FILE_SEP + this.prefFileName);
/*     */         
/*     */         }
/* 235 */         catch (IOException e3) {
/* 236 */           this.fullName = null;
/* 237 */           throw e3;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     try {
/* 242 */       this.internal.store(fos, this.prefFileName);
/*     */     } finally {
/* 244 */       fos.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Object getDefault(String key) {
/* 250 */     if (this.defaults != null) {
/* 251 */       return this.defaults.get(key);
/*     */     }
/* 253 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle getRectangle(String key) {
/* 261 */     Rectangle defaultValue = (Rectangle)getDefault(key);
/* 262 */     String sp = this.internal.getProperty(key);
/* 263 */     if (sp == null) {
/* 264 */       return defaultValue;
/*     */     }
/* 266 */     Rectangle result = new Rectangle();
/*     */ 
/*     */     
/*     */     try {
/* 270 */       StringTokenizer st = new StringTokenizer(sp, " ", false);
/* 271 */       if (!st.hasMoreTokens()) {
/*     */         
/* 273 */         this.internal.remove(key);
/* 274 */         return defaultValue;
/*     */       } 
/* 276 */       String token = st.nextToken();
/* 277 */       int x = Integer.parseInt(token);
/* 278 */       if (!st.hasMoreTokens()) {
/* 279 */         this.internal.remove(key);
/* 280 */         return defaultValue;
/*     */       } 
/* 282 */       token = st.nextToken();
/* 283 */       int y = Integer.parseInt(token);
/* 284 */       if (!st.hasMoreTokens()) {
/* 285 */         this.internal.remove(key);
/* 286 */         return defaultValue;
/*     */       } 
/* 288 */       token = st.nextToken();
/* 289 */       int w = Integer.parseInt(token);
/* 290 */       if (!st.hasMoreTokens()) {
/* 291 */         this.internal.remove(key);
/* 292 */         return defaultValue;
/*     */       } 
/* 294 */       token = st.nextToken();
/* 295 */       int h = Integer.parseInt(token);
/* 296 */       result.setBounds(x, y, w, h);
/* 297 */       return result;
/* 298 */     } catch (NumberFormatException e) {
/* 299 */       this.internal.remove(key);
/* 300 */       return defaultValue;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getDimension(String key) {
/* 309 */     Dimension defaultValue = (Dimension)getDefault(key);
/* 310 */     String sp = this.internal.getProperty(key);
/* 311 */     if (sp == null)
/* 312 */       return defaultValue; 
/* 313 */     Dimension result = new Dimension();
/*     */ 
/*     */     
/*     */     try {
/* 317 */       StringTokenizer st = new StringTokenizer(sp, " ", false);
/* 318 */       if (!st.hasMoreTokens()) {
/*     */         
/* 320 */         this.internal.remove(key);
/* 321 */         return defaultValue;
/*     */       } 
/* 323 */       String token = st.nextToken();
/* 324 */       int w = Integer.parseInt(token);
/* 325 */       if (!st.hasMoreTokens()) {
/* 326 */         this.internal.remove(key);
/* 327 */         return defaultValue;
/*     */       } 
/* 329 */       token = st.nextToken();
/* 330 */       int h = Integer.parseInt(token);
/* 331 */       result.setSize(w, h);
/* 332 */       return result;
/* 333 */     } catch (NumberFormatException e) {
/* 334 */       this.internal.remove(key);
/* 335 */       return defaultValue;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point getPoint(String key) {
/* 344 */     Point defaultValue = (Point)getDefault(key);
/* 345 */     String sp = this.internal.getProperty(key);
/* 346 */     if (sp == null) {
/* 347 */       return defaultValue;
/*     */     }
/* 349 */     Point result = new Point();
/*     */ 
/*     */     
/*     */     try {
/* 353 */       StringTokenizer st = new StringTokenizer(sp, " ", false);
/* 354 */       if (!st.hasMoreTokens()) {
/*     */         
/* 356 */         this.internal.remove(key);
/* 357 */         return defaultValue;
/*     */       } 
/* 359 */       String token = st.nextToken();
/* 360 */       int x = Integer.parseInt(token);
/* 361 */       if (!st.hasMoreTokens()) {
/* 362 */         this.internal.remove(key);
/* 363 */         return defaultValue;
/*     */       } 
/* 365 */       token = st.nextToken();
/* 366 */       int y = Integer.parseInt(token);
/* 367 */       if (!st.hasMoreTokens()) {
/* 368 */         this.internal.remove(key);
/* 369 */         return defaultValue;
/*     */       } 
/* 371 */       result.setLocation(x, y);
/* 372 */       return result;
/* 373 */     } catch (NumberFormatException e) {
/* 374 */       this.internal.remove(key);
/* 375 */       return defaultValue;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getColor(String key) {
/* 384 */     Color defaultValue = (Color)getDefault(key);
/* 385 */     String sp = this.internal.getProperty(key);
/* 386 */     if (sp == null) {
/* 387 */       return defaultValue;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 392 */       StringTokenizer st = new StringTokenizer(sp, " ", false);
/* 393 */       if (!st.hasMoreTokens()) {
/*     */         
/* 395 */         this.internal.remove(key);
/* 396 */         return defaultValue;
/*     */       } 
/* 398 */       String token = st.nextToken();
/* 399 */       int r = Integer.parseInt(token);
/* 400 */       if (!st.hasMoreTokens()) {
/* 401 */         this.internal.remove(key);
/* 402 */         return defaultValue;
/*     */       } 
/* 404 */       token = st.nextToken();
/* 405 */       int g = Integer.parseInt(token);
/* 406 */       if (!st.hasMoreTokens()) {
/* 407 */         this.internal.remove(key);
/* 408 */         return defaultValue;
/*     */       } 
/* 410 */       token = st.nextToken();
/* 411 */       int b = Integer.parseInt(token);
/* 412 */       if (!st.hasMoreTokens()) {
/* 413 */         this.internal.remove(key);
/* 414 */         return defaultValue;
/*     */       } 
/* 416 */       token = st.nextToken();
/* 417 */       int a = Integer.parseInt(token);
/* 418 */       return new Color(r, g, b, a);
/* 419 */     } catch (NumberFormatException e) {
/* 420 */       this.internal.remove(key);
/* 421 */       return defaultValue;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Font getFont(String key) {
/* 430 */     Font defaultValue = (Font)getDefault(key);
/* 431 */     String sp = this.internal.getProperty(key);
/* 432 */     if (sp == null) {
/* 433 */       return defaultValue;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 439 */       StringTokenizer st = new StringTokenizer(sp, " ", false);
/* 440 */       if (!st.hasMoreTokens()) {
/*     */         
/* 442 */         this.internal.remove(key);
/* 443 */         return defaultValue;
/*     */       } 
/* 445 */       String name = st.nextToken();
/* 446 */       if (!st.hasMoreTokens()) {
/* 447 */         this.internal.remove(key);
/* 448 */         return defaultValue;
/*     */       } 
/* 450 */       String token = st.nextToken();
/* 451 */       int size = Integer.parseInt(token);
/* 452 */       if (!st.hasMoreTokens()) {
/* 453 */         this.internal.remove(key);
/* 454 */         return defaultValue;
/*     */       } 
/* 456 */       token = st.nextToken();
/* 457 */       int type = Integer.parseInt(token);
/* 458 */       return new Font(name, type, size);
/* 459 */     } catch (NumberFormatException e) {
/* 460 */       this.internal.remove(key);
/* 461 */       return defaultValue;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(String key) {
/* 471 */     String sp = this.internal.getProperty(key);
/* 472 */     if (sp == null) {
/* 473 */       sp = (String)getDefault(key);
/*     */     }
/* 475 */     return sp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getStrings(String mkey) {
/* 484 */     int i = 0;
/* 485 */     ArrayList<String> v = new ArrayList();
/*     */     while (true) {
/* 487 */       String last = getString(mkey + i);
/* 488 */       i++;
/* 489 */       if (last == null)
/*     */         break; 
/* 491 */       v.add(last);
/*     */     } 
/* 493 */     if (v.size() != 0) {
/* 494 */       String[] str = new String[v.size()];
/* 495 */       return v.<String>toArray(str);
/*     */     } 
/* 497 */     return (String[])getDefault(mkey);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getURL(String key) {
/* 506 */     URL defaultValue = (URL)getDefault(key);
/* 507 */     String sp = this.internal.getProperty(key);
/* 508 */     if (sp == null) {
/* 509 */       return defaultValue;
/*     */     }
/* 511 */     URL url = null;
/*     */     try {
/* 513 */       url = new URL(sp);
/* 514 */     } catch (MalformedURLException ex) {
/* 515 */       this.internal.remove(key);
/* 516 */       return defaultValue;
/*     */     } 
/* 518 */     return url;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL[] getURLs(String mkey) {
/* 527 */     int i = 0;
/* 528 */     ArrayList<URL> v = new ArrayList();
/*     */     while (true) {
/* 530 */       URL last = getURL(mkey + i);
/* 531 */       i++;
/* 532 */       if (last == null)
/*     */         break; 
/* 534 */       v.add(last);
/*     */     } 
/* 536 */     if (v.size() != 0) {
/* 537 */       URL[] path = new URL[v.size()];
/* 538 */       return v.<URL>toArray(path);
/*     */     } 
/* 540 */     return (URL[])getDefault(mkey);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getFile(String key) {
/* 549 */     File defaultValue = (File)getDefault(key);
/* 550 */     String sp = this.internal.getProperty(key);
/* 551 */     if (sp == null) {
/* 552 */       return defaultValue;
/*     */     }
/* 554 */     File file = new File(sp);
/* 555 */     if (file.exists()) {
/* 556 */       return file;
/*     */     }
/* 558 */     this.internal.remove(key);
/* 559 */     return defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File[] getFiles(String mkey) {
/* 569 */     int i = 0;
/* 570 */     ArrayList<File> v = new ArrayList();
/*     */     while (true) {
/* 572 */       File last = getFile(mkey + i);
/* 573 */       i++;
/* 574 */       if (last == null)
/*     */         break; 
/* 576 */       v.add(last);
/*     */     } 
/* 578 */     if (v.size() != 0) {
/* 579 */       File[] path = new File[v.size()];
/* 580 */       return v.<File>toArray(path);
/*     */     } 
/* 582 */     return (File[])getDefault(mkey);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInteger(String key) {
/* 592 */     int value, defaultValue = 0;
/* 593 */     if (getDefault(key) != null)
/* 594 */       defaultValue = ((Integer)getDefault(key)).intValue(); 
/* 595 */     String sp = this.internal.getProperty(key);
/* 596 */     if (sp == null) {
/* 597 */       return defaultValue;
/*     */     }
/*     */     
/*     */     try {
/* 601 */       value = Integer.parseInt(sp);
/* 602 */     } catch (NumberFormatException ex) {
/* 603 */       this.internal.remove(key);
/* 604 */       return defaultValue;
/*     */     } 
/* 606 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloat(String key) {
/* 614 */     float value, defaultValue = 0.0F;
/* 615 */     if (getDefault(key) != null)
/* 616 */       defaultValue = ((Float)getDefault(key)).floatValue(); 
/* 617 */     String sp = this.internal.getProperty(key);
/* 618 */     if (sp == null) {
/* 619 */       return defaultValue;
/*     */     }
/*     */     
/*     */     try {
/* 623 */       value = Float.parseFloat(sp);
/* 624 */     } catch (NumberFormatException ex) {
/* 625 */       setFloat(key, defaultValue);
/* 626 */       return defaultValue;
/*     */     } 
/* 628 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoolean(String key) {
/* 636 */     if (this.internal.getProperty(key) != null) {
/* 637 */       return this.internal.getProperty(key).equals("true");
/*     */     }
/* 639 */     if (getDefault(key) != null) {
/* 640 */       return ((Boolean)getDefault(key)).booleanValue();
/*     */     }
/* 642 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRectangle(String key, Rectangle value) {
/* 650 */     if (value != null && !value.equals(getDefault(key))) {
/* 651 */       this.internal.setProperty(key, value.x + " " + value.y + " " + value.width + ' ' + value.height);
/*     */     } else {
/*     */       
/* 654 */       this.internal.remove(key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDimension(String key, Dimension value) {
/* 662 */     if (value != null && !value.equals(getDefault(key))) {
/* 663 */       this.internal.setProperty(key, value.width + " " + value.height);
/*     */     } else {
/* 665 */       this.internal.remove(key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPoint(String key, Point value) {
/* 673 */     if (value != null && !value.equals(getDefault(key))) {
/* 674 */       this.internal.setProperty(key, value.x + " " + value.y);
/*     */     } else {
/* 676 */       this.internal.remove(key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColor(String key, Color value) {
/* 684 */     if (value != null && !value.equals(getDefault(key))) {
/* 685 */       this.internal.setProperty(key, value.getRed() + " " + value.getGreen() + " " + value.getBlue() + " " + value.getAlpha());
/*     */     }
/*     */     else {
/*     */       
/* 689 */       this.internal.remove(key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFont(String key, Font value) {
/* 697 */     if (value != null && !value.equals(getDefault(key))) {
/* 698 */       this.internal.setProperty(key, value.getName() + " " + value.getSize() + " " + value.getStyle());
/*     */     } else {
/*     */       
/* 701 */       this.internal.remove(key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setString(String key, String value) {
/* 709 */     if (value != null && !value.equals(getDefault(key))) {
/* 710 */       this.internal.setProperty(key, value);
/*     */     } else {
/* 712 */       this.internal.remove(key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrings(String mkey, String[] values) {
/* 721 */     int j = 0;
/* 722 */     if (values != null) {
/* 723 */       for (String value : values) {
/* 724 */         if (value != null) {
/* 725 */           setString(mkey + j, value);
/* 726 */           j++;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     while (true) {
/* 732 */       String last = getString(mkey + j);
/* 733 */       if (last == null)
/*     */         break; 
/* 735 */       setString(mkey + j, null);
/* 736 */       j++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setURL(String key, URL value) {
/* 745 */     if (value != null && !value.equals(getDefault(key))) {
/* 746 */       this.internal.setProperty(key, value.toString());
/*     */     } else {
/* 748 */       this.internal.remove(key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setURLs(String mkey, URL[] values) {
/* 757 */     int j = 0;
/* 758 */     if (values != null) {
/* 759 */       for (URL value : values) {
/* 760 */         if (value != null) {
/* 761 */           setURL(mkey + j, value);
/* 762 */           j++;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     while (true) {
/* 768 */       String last = getString(mkey + j);
/* 769 */       if (last == null)
/*     */         break; 
/* 771 */       setString(mkey + j, null);
/* 772 */       j++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFile(String key, File value) {
/* 781 */     if (value != null && !value.equals(getDefault(key))) {
/* 782 */       this.internal.setProperty(key, value.getAbsolutePath());
/*     */     } else {
/* 784 */       this.internal.remove(key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFiles(String mkey, File[] values) {
/* 793 */     int j = 0;
/* 794 */     if (values != null) {
/* 795 */       for (File value : values) {
/* 796 */         if (value != null) {
/* 797 */           setFile(mkey + j, value);
/* 798 */           j++;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     while (true) {
/* 804 */       String last = getString(mkey + j);
/* 805 */       if (last == null)
/*     */         break; 
/* 807 */       setString(mkey + j, null);
/* 808 */       j++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInteger(String key, int value) {
/* 817 */     if (getDefault(key) != null && ((Integer)getDefault(key)).intValue() != value) {
/*     */       
/* 819 */       this.internal.setProperty(key, Integer.toString(value));
/*     */     } else {
/* 821 */       this.internal.remove(key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFloat(String key, float value) {
/* 829 */     if (getDefault(key) != null && ((Float)getDefault(key)).floatValue() != value) {
/*     */       
/* 831 */       this.internal.setProperty(key, Float.toString(value));
/*     */     } else {
/* 833 */       this.internal.remove(key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBoolean(String key, boolean value) {
/* 841 */     if (getDefault(key) != null && ((Boolean)getDefault(key)).booleanValue() != value) {
/*     */       
/* 843 */       this.internal.setProperty(key, value ? "true" : "false");
/*     */     } else {
/* 845 */       this.internal.remove(key);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/PreferenceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */