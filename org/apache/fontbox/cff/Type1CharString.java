/*     */ package org.apache.fontbox.cff;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.fontbox.encoding.StandardEncoding;
/*     */ import org.apache.fontbox.type1.Type1CharStringReader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Type1CharString
/*     */ {
/*  41 */   private static final Log LOG = LogFactory.getLog(Type1CharString.class);
/*     */   private Type1CharStringReader font;
/*     */   private final String fontName;
/*     */   private final String glyphName;
/*  45 */   private GeneralPath path = null;
/*  46 */   private int width = 0;
/*  47 */   private Point2D.Float leftSideBearing = null;
/*  48 */   private Point2D.Float current = null;
/*     */   private boolean isFlex = false;
/*  50 */   private final List<Point2D.Float> flexPoints = new ArrayList<Point2D.Float>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<Object> type1Sequence;
/*     */ 
/*     */ 
/*     */   
/*     */   protected int commandCount;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type1CharString(Type1CharStringReader font, String fontName, String glyphName, List<Object> sequence) {
/*  65 */     this(font, fontName, glyphName);
/*  66 */     this.type1Sequence = sequence;
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
/*     */   protected Type1CharString(Type1CharStringReader font, String fontName, String glyphName) {
/*  78 */     this.font = font;
/*  79 */     this.fontName = fontName;
/*  80 */     this.glyphName = glyphName;
/*  81 */     this.current = new Point2D.Float(0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  87 */     return this.glyphName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds() {
/*  96 */     synchronized (LOG) {
/*     */       
/*  98 */       if (this.path == null)
/*     */       {
/* 100 */         render();
/*     */       }
/*     */     } 
/* 103 */     return this.path.getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 112 */     synchronized (LOG) {
/*     */       
/* 114 */       if (this.path == null)
/*     */       {
/* 116 */         render();
/*     */       }
/*     */     } 
/* 119 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPath() {
/* 128 */     synchronized (LOG) {
/*     */       
/* 130 */       if (this.path == null)
/*     */       {
/* 132 */         render();
/*     */       }
/*     */     } 
/* 135 */     return this.path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Object> getType1Sequence() {
/* 144 */     return this.type1Sequence;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void render() {
/* 152 */     this.path = new GeneralPath();
/* 153 */     this.leftSideBearing = new Point2D.Float(0.0F, 0.0F);
/* 154 */     this.width = 0;
/* 155 */     CharStringHandler handler = new CharStringHandler()
/*     */       {
/*     */         public List<Number> handleCommand(List<Number> numbers, CharStringCommand command)
/*     */         {
/* 159 */           return Type1CharString.this.handleCommand(numbers, command);
/*     */         }
/*     */       };
/* 162 */     handler.handleSequence(this.type1Sequence);
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Number> handleCommand(List<Number> numbers, CharStringCommand command) {
/* 167 */     this.commandCount++;
/* 168 */     String name = CharStringCommand.TYPE1_VOCABULARY.get(command.getKey());
/*     */     
/* 170 */     if ("rmoveto".equals(name)) {
/*     */       
/* 172 */       if (numbers.size() >= 2)
/*     */       {
/* 174 */         if (this.isFlex)
/*     */         {
/* 176 */           this.flexPoints.add(new Point2D.Float(((Number)numbers.get(0)).floatValue(), ((Number)numbers.get(1)).floatValue()));
/*     */         }
/*     */         else
/*     */         {
/* 180 */           rmoveTo(numbers.get(0), numbers.get(1));
/*     */         }
/*     */       
/*     */       }
/* 184 */     } else if ("vmoveto".equals(name)) {
/*     */       
/* 186 */       if (numbers.size() >= 1)
/*     */       {
/* 188 */         if (this.isFlex)
/*     */         {
/*     */           
/* 191 */           this.flexPoints.add(new Point2D.Float(0.0F, ((Number)numbers.get(0)).floatValue()));
/*     */         }
/*     */         else
/*     */         {
/* 195 */           rmoveTo(Integer.valueOf(0), numbers.get(0));
/*     */         }
/*     */       
/*     */       }
/* 199 */     } else if ("hmoveto".equals(name)) {
/*     */       
/* 201 */       if (numbers.size() >= 1)
/*     */       {
/* 203 */         if (this.isFlex)
/*     */         {
/*     */           
/* 206 */           this.flexPoints.add(new Point2D.Float(((Number)numbers.get(0)).floatValue(), 0.0F));
/*     */         }
/*     */         else
/*     */         {
/* 210 */           rmoveTo(numbers.get(0), Integer.valueOf(0));
/*     */         }
/*     */       
/*     */       }
/* 214 */     } else if ("rlineto".equals(name)) {
/*     */       
/* 216 */       if (numbers.size() >= 2)
/*     */       {
/* 218 */         rlineTo(numbers.get(0), numbers.get(1));
/*     */       }
/*     */     }
/* 221 */     else if ("hlineto".equals(name)) {
/*     */       
/* 223 */       if (numbers.size() >= 1)
/*     */       {
/* 225 */         rlineTo(numbers.get(0), Integer.valueOf(0));
/*     */       }
/*     */     }
/* 228 */     else if ("vlineto".equals(name)) {
/*     */       
/* 230 */       if (numbers.size() >= 1)
/*     */       {
/* 232 */         rlineTo(Integer.valueOf(0), numbers.get(0));
/*     */       }
/*     */     }
/* 235 */     else if ("rrcurveto".equals(name)) {
/*     */       
/* 237 */       if (numbers.size() >= 6)
/*     */       {
/* 239 */         rrcurveTo(numbers.get(0), numbers.get(1), numbers.get(2), numbers
/* 240 */             .get(3), numbers.get(4), numbers.get(5));
/*     */       }
/*     */     }
/* 243 */     else if ("closepath".equals(name)) {
/*     */       
/* 245 */       closepath();
/*     */     }
/* 247 */     else if ("sbw".equals(name)) {
/*     */       
/* 249 */       if (numbers.size() >= 3)
/*     */       {
/* 251 */         this.leftSideBearing = new Point2D.Float(((Number)numbers.get(0)).floatValue(), ((Number)numbers.get(1)).floatValue());
/* 252 */         this.width = ((Number)numbers.get(2)).intValue();
/* 253 */         this.current.setLocation(this.leftSideBearing);
/*     */       }
/*     */     
/* 256 */     } else if ("hsbw".equals(name)) {
/*     */       
/* 258 */       if (numbers.size() >= 2)
/*     */       {
/* 260 */         this.leftSideBearing = new Point2D.Float(((Number)numbers.get(0)).floatValue(), 0.0F);
/* 261 */         this.width = ((Number)numbers.get(1)).intValue();
/* 262 */         this.current.setLocation(this.leftSideBearing);
/*     */       }
/*     */     
/* 265 */     } else if ("vhcurveto".equals(name)) {
/*     */       
/* 267 */       if (numbers.size() >= 4)
/*     */       {
/* 269 */         rrcurveTo(Integer.valueOf(0), numbers.get(0), numbers.get(1), numbers
/* 270 */             .get(2), numbers.get(3), Integer.valueOf(0));
/*     */       }
/*     */     }
/* 273 */     else if ("hvcurveto".equals(name)) {
/*     */       
/* 275 */       if (numbers.size() >= 4)
/*     */       {
/* 277 */         rrcurveTo(numbers.get(0), Integer.valueOf(0), numbers.get(1), numbers
/* 278 */             .get(2), Integer.valueOf(0), numbers.get(3));
/*     */       }
/*     */     }
/* 281 */     else if ("seac".equals(name)) {
/*     */       
/* 283 */       if (numbers.size() >= 5)
/*     */       {
/* 285 */         seac(numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3), numbers.get(4));
/*     */       }
/*     */     }
/* 288 */     else if ("setcurrentpoint".equals(name)) {
/*     */       
/* 290 */       if (numbers.size() >= 2)
/*     */       {
/* 292 */         setcurrentpoint(numbers.get(0), numbers.get(1));
/*     */       }
/*     */     }
/* 295 */     else if ("callothersubr".equals(name)) {
/*     */       
/* 297 */       if (numbers.size() >= 1)
/*     */       {
/* 299 */         callothersubr(((Number)numbers.get(0)).intValue());
/*     */       }
/*     */     } else {
/* 302 */       if ("div".equals(name)) {
/*     */         
/* 304 */         float b = ((Number)numbers.get(numbers.size() - 1)).floatValue();
/* 305 */         float a = ((Number)numbers.get(numbers.size() - 2)).floatValue();
/*     */         
/* 307 */         float result = a / b;
/*     */         
/* 309 */         List<Number> list = new ArrayList<Number>(numbers);
/* 310 */         list.remove(list.size() - 1);
/* 311 */         list.remove(list.size() - 1);
/* 312 */         list.add(Float.valueOf(result));
/* 313 */         return list;
/*     */       } 
/* 315 */       if (!"hstem".equals(name) && !"vstem".equals(name) && 
/* 316 */         !"hstem3".equals(name) && !"vstem3".equals(name) && !"dotsection".equals(name))
/*     */       {
/*     */ 
/*     */         
/* 320 */         if (!"endchar".equals(name))
/*     */         {
/*     */ 
/*     */           
/* 324 */           if ("return".equals(name)) {
/*     */ 
/*     */             
/* 327 */             LOG.warn("Unexpected charstring command: " + command.getKey() + " in glyph " + this.glyphName + " of font " + this.fontName);
/*     */           } else {
/*     */             
/* 330 */             if (name != null)
/*     */             {
/*     */               
/* 333 */               throw new IllegalArgumentException("Unhandled command: " + name);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 338 */             LOG.warn("Unknown charstring command: " + command.getKey() + " in glyph " + this.glyphName + " of font " + this.fontName);
/*     */           }  }  } 
/*     */     } 
/* 341 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setcurrentpoint(Number x, Number y) {
/* 350 */     this.current.setLocation(x.floatValue(), y.floatValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void callothersubr(int num) {
/* 359 */     if (num == 0) {
/*     */ 
/*     */       
/* 362 */       this.isFlex = false;
/*     */       
/* 364 */       if (this.flexPoints.size() < 7) {
/*     */         
/* 366 */         LOG.warn("flex without moveTo in font " + this.fontName + ", glyph " + this.glyphName + ", command " + this.commandCount);
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 372 */       Point2D.Float reference = this.flexPoints.get(0);
/* 373 */       reference.setLocation(this.current.getX() + reference.getX(), this.current
/* 374 */           .getY() + reference.getY());
/*     */ 
/*     */       
/* 377 */       Point2D.Float first = this.flexPoints.get(1);
/* 378 */       first.setLocation(reference.getX() + first.getX(), reference.getY() + first.getY());
/*     */ 
/*     */       
/* 381 */       first.setLocation(first.getX() - this.current.getX(), first.getY() - this.current.getY());
/*     */       
/* 383 */       rrcurveTo(Double.valueOf(((Point2D.Float)this.flexPoints.get(1)).getX()), Double.valueOf(((Point2D.Float)this.flexPoints.get(1)).getY()), 
/* 384 */           Double.valueOf(((Point2D.Float)this.flexPoints.get(2)).getX()), Double.valueOf(((Point2D.Float)this.flexPoints.get(2)).getY()), 
/* 385 */           Double.valueOf(((Point2D.Float)this.flexPoints.get(3)).getX()), Double.valueOf(((Point2D.Float)this.flexPoints.get(3)).getY()));
/*     */       
/* 387 */       rrcurveTo(Double.valueOf(((Point2D.Float)this.flexPoints.get(4)).getX()), Double.valueOf(((Point2D.Float)this.flexPoints.get(4)).getY()), 
/* 388 */           Double.valueOf(((Point2D.Float)this.flexPoints.get(5)).getX()), Double.valueOf(((Point2D.Float)this.flexPoints.get(5)).getY()), 
/* 389 */           Double.valueOf(((Point2D.Float)this.flexPoints.get(6)).getX()), Double.valueOf(((Point2D.Float)this.flexPoints.get(6)).getY()));
/*     */       
/* 391 */       this.flexPoints.clear();
/*     */     }
/* 393 */     else if (num == 1) {
/*     */ 
/*     */       
/* 396 */       this.isFlex = true;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 401 */       throw new IllegalArgumentException("Unexpected other subroutine: " + num);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void rmoveTo(Number dx, Number dy) {
/* 410 */     float x = (float)this.current.getX() + dx.floatValue();
/* 411 */     float y = (float)this.current.getY() + dy.floatValue();
/* 412 */     this.path.moveTo(x, y);
/* 413 */     this.current.setLocation(x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void rlineTo(Number dx, Number dy) {
/* 421 */     float x = (float)this.current.getX() + dx.floatValue();
/* 422 */     float y = (float)this.current.getY() + dy.floatValue();
/* 423 */     if (this.path.getCurrentPoint() == null) {
/*     */       
/* 425 */       LOG.warn("rlineTo without initial moveTo in font " + this.fontName + ", glyph " + this.glyphName);
/* 426 */       this.path.moveTo(x, y);
/*     */     }
/*     */     else {
/*     */       
/* 430 */       this.path.lineTo(x, y);
/*     */     } 
/* 432 */     this.current.setLocation(x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void rrcurveTo(Number dx1, Number dy1, Number dx2, Number dy2, Number dx3, Number dy3) {
/* 441 */     float x1 = (float)this.current.getX() + dx1.floatValue();
/* 442 */     float y1 = (float)this.current.getY() + dy1.floatValue();
/* 443 */     float x2 = x1 + dx2.floatValue();
/* 444 */     float y2 = y1 + dy2.floatValue();
/* 445 */     float x3 = x2 + dx3.floatValue();
/* 446 */     float y3 = y2 + dy3.floatValue();
/* 447 */     if (this.path.getCurrentPoint() == null) {
/*     */       
/* 449 */       LOG.warn("rrcurveTo without initial moveTo in font " + this.fontName + ", glyph " + this.glyphName);
/* 450 */       this.path.moveTo(x3, y3);
/*     */     }
/*     */     else {
/*     */       
/* 454 */       this.path.curveTo(x1, y1, x2, y2, x3, y3);
/*     */     } 
/* 456 */     this.current.setLocation(x3, y3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void closepath() {
/* 464 */     if (this.path.getCurrentPoint() == null) {
/*     */       
/* 466 */       LOG.warn("closepath without initial moveTo in font " + this.fontName + ", glyph " + this.glyphName);
/*     */     }
/*     */     else {
/*     */       
/* 470 */       this.path.closePath();
/*     */     } 
/* 472 */     this.path.moveTo(this.current.getX(), this.current.getY());
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
/*     */   private void seac(Number asb, Number adx, Number ady, Number bchar, Number achar) {
/* 484 */     String baseName = StandardEncoding.INSTANCE.getName(bchar.intValue());
/*     */     
/*     */     try {
/* 487 */       Type1CharString base = this.font.getType1CharString(baseName);
/* 488 */       this.path.append(base.getPath().getPathIterator((AffineTransform)null), false);
/*     */     }
/* 490 */     catch (IOException e) {
/*     */       
/* 492 */       LOG.warn("invalid seac character in glyph " + this.glyphName + " of font " + this.fontName);
/*     */     } 
/*     */     
/* 495 */     String accentName = StandardEncoding.INSTANCE.getName(achar.intValue());
/*     */     
/*     */     try {
/* 498 */       Type1CharString accent = this.font.getType1CharString(accentName);
/* 499 */       AffineTransform at = AffineTransform.getTranslateInstance(this.leftSideBearing
/* 500 */           .getX() + adx.floatValue() - asb.floatValue(), this.leftSideBearing
/* 501 */           .getY() + ady.floatValue());
/* 502 */       this.path.append(accent.getPath().getPathIterator(at), false);
/*     */     }
/* 504 */     catch (IOException e) {
/*     */       
/* 506 */       LOG.warn("invalid seac character in glyph " + this.glyphName + " of font " + this.fontName);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 513 */     return this.type1Sequence.toString().replace("|", "\n").replace(",", " ");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/Type1CharString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */