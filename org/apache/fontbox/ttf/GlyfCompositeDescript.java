/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GlyfCompositeDescript
/*     */   extends GlyfDescript
/*     */ {
/*  43 */   private static final Log LOG = LogFactory.getLog(GlyfCompositeDescript.class);
/*     */ 
/*     */   
/*     */   private final List<GlyfCompositeComp> components;
/*     */   
/*     */   private final Map<Integer, GlyphDescription> descriptions;
/*     */   
/*     */   private GlyphTable glyphTable;
/*     */   
/*     */   private boolean beingResolved;
/*     */   
/*     */   private boolean resolved;
/*     */   
/*     */   private int pointCount;
/*     */   
/*     */   private int contourCount;
/*     */ 
/*     */   
/*     */   public GlyfCompositeDescript(TTFDataStream bais, GlyphTable glyphTable) throws IOException {
/*  62 */     super((short)-1, bais); GlyfCompositeComp comp; this.components = new ArrayList<GlyfCompositeComp>(); this.descriptions = new HashMap<Integer, GlyphDescription>(); this.glyphTable = null; this.beingResolved = false; this.resolved = false; this.pointCount = -1;
/*     */     this.contourCount = -1;
/*  64 */     this.glyphTable = glyphTable;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  70 */       comp = new GlyfCompositeComp(bais);
/*  71 */       this.components.add(comp);
/*     */     }
/*  73 */     while ((comp.getFlags() & 0x20) != 0);
/*     */ 
/*     */     
/*  76 */     if ((comp.getFlags() & 0x100) != 0)
/*     */     {
/*  78 */       readInstructions(bais, bais.readUnsignedShort());
/*     */     }
/*  80 */     initDescriptions();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resolve() {
/*  89 */     if (this.resolved) {
/*     */       return;
/*     */     }
/*     */     
/*  93 */     if (this.beingResolved) {
/*     */       
/*  95 */       LOG.error("Circular reference in GlyfCompositeDesc");
/*     */       return;
/*     */     } 
/*  98 */     this.beingResolved = true;
/*     */     
/* 100 */     int firstIndex = 0;
/* 101 */     int firstContour = 0;
/*     */     
/* 103 */     for (GlyfCompositeComp comp : this.components) {
/*     */       
/* 105 */       comp.setFirstIndex(firstIndex);
/* 106 */       comp.setFirstContour(firstContour);
/*     */       
/* 108 */       GlyphDescription desc = this.descriptions.get(Integer.valueOf(comp.getGlyphIndex()));
/* 109 */       if (desc != null) {
/*     */         
/* 111 */         desc.resolve();
/* 112 */         firstIndex += desc.getPointCount();
/* 113 */         firstContour += desc.getContourCount();
/*     */       } 
/*     */     } 
/* 116 */     this.resolved = true;
/* 117 */     this.beingResolved = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEndPtOfContours(int i) {
/* 126 */     GlyfCompositeComp c = getCompositeCompEndPt(i);
/* 127 */     if (c != null) {
/*     */       
/* 129 */       GlyphDescription gd = this.descriptions.get(Integer.valueOf(c.getGlyphIndex()));
/* 130 */       return gd.getEndPtOfContours(i - c.getFirstContour()) + c.getFirstIndex();
/*     */     } 
/* 132 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getFlags(int i) {
/* 141 */     GlyfCompositeComp c = getCompositeComp(i);
/* 142 */     if (c != null) {
/*     */       
/* 144 */       GlyphDescription gd = this.descriptions.get(Integer.valueOf(c.getGlyphIndex()));
/* 145 */       return gd.getFlags(i - c.getFirstIndex());
/*     */     } 
/* 147 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getXCoordinate(int i) {
/* 156 */     GlyfCompositeComp c = getCompositeComp(i);
/* 157 */     if (c != null) {
/*     */       
/* 159 */       GlyphDescription gd = this.descriptions.get(Integer.valueOf(c.getGlyphIndex()));
/* 160 */       int n = i - c.getFirstIndex();
/* 161 */       int x = gd.getXCoordinate(n);
/* 162 */       int y = gd.getYCoordinate(n);
/* 163 */       short x1 = (short)c.scaleX(x, y);
/* 164 */       x1 = (short)(x1 + c.getXTranslate());
/* 165 */       return x1;
/*     */     } 
/* 167 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getYCoordinate(int i) {
/* 176 */     GlyfCompositeComp c = getCompositeComp(i);
/* 177 */     if (c != null) {
/*     */       
/* 179 */       GlyphDescription gd = this.descriptions.get(Integer.valueOf(c.getGlyphIndex()));
/* 180 */       int n = i - c.getFirstIndex();
/* 181 */       int x = gd.getXCoordinate(n);
/* 182 */       int y = gd.getYCoordinate(n);
/* 183 */       short y1 = (short)c.scaleY(x, y);
/* 184 */       y1 = (short)(y1 + c.getYTranslate());
/* 185 */       return y1;
/*     */     } 
/* 187 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComposite() {
/* 196 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPointCount() {
/* 205 */     if (!this.resolved)
/*     */     {
/* 207 */       LOG.error("getPointCount called on unresolved GlyfCompositeDescript");
/*     */     }
/* 209 */     if (this.pointCount < 0) {
/*     */       
/* 211 */       GlyfCompositeComp c = this.components.get(this.components.size() - 1);
/* 212 */       GlyphDescription gd = this.descriptions.get(Integer.valueOf(c.getGlyphIndex()));
/* 213 */       if (gd == null) {
/*     */         
/* 215 */         LOG.error("GlyphDescription for index " + c.getGlyphIndex() + " is null, returning 0");
/* 216 */         this.pointCount = 0;
/*     */       }
/*     */       else {
/*     */         
/* 220 */         this.pointCount = c.getFirstIndex() + gd.getPointCount();
/*     */       } 
/*     */     } 
/* 223 */     return this.pointCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getContourCount() {
/* 232 */     if (!this.resolved)
/*     */     {
/* 234 */       LOG.error("getContourCount called on unresolved GlyfCompositeDescript");
/*     */     }
/* 236 */     if (this.contourCount < 0) {
/*     */       
/* 238 */       GlyfCompositeComp c = this.components.get(this.components.size() - 1);
/* 239 */       this.contourCount = c.getFirstContour() + ((GlyphDescription)this.descriptions.get(Integer.valueOf(c.getGlyphIndex()))).getContourCount();
/*     */     } 
/* 241 */     return this.contourCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getComponentCount() {
/* 251 */     return this.components.size();
/*     */   }
/*     */ 
/*     */   
/*     */   private GlyfCompositeComp getCompositeComp(int i) {
/* 256 */     for (GlyfCompositeComp c : this.components) {
/*     */       
/* 258 */       GlyphDescription gd = this.descriptions.get(Integer.valueOf(c.getGlyphIndex()));
/* 259 */       if (c.getFirstIndex() <= i && gd != null && i < c.getFirstIndex() + gd.getPointCount())
/*     */       {
/* 261 */         return c;
/*     */       }
/*     */     } 
/* 264 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private GlyfCompositeComp getCompositeCompEndPt(int i) {
/* 269 */     for (GlyfCompositeComp c : this.components) {
/*     */       
/* 271 */       GlyphDescription gd = this.descriptions.get(Integer.valueOf(c.getGlyphIndex()));
/* 272 */       if (c.getFirstContour() <= i && gd != null && i < c.getFirstContour() + gd.getContourCount())
/*     */       {
/* 274 */         return c;
/*     */       }
/*     */     } 
/* 277 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void initDescriptions() {
/* 282 */     for (GlyfCompositeComp component : this.components) {
/*     */ 
/*     */       
/*     */       try {
/* 286 */         int index = component.getGlyphIndex();
/* 287 */         GlyphData glyph = this.glyphTable.getGlyph(index);
/* 288 */         if (glyph != null)
/*     */         {
/* 290 */           this.descriptions.put(Integer.valueOf(index), glyph.getDescription());
/*     */         }
/*     */       }
/* 293 */       catch (IOException e) {
/*     */         
/* 295 */         LOG.error(e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/GlyfCompositeDescript.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */