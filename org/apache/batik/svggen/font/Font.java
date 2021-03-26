/*     */ package org.apache.batik.svggen.font;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import org.apache.batik.svggen.font.table.CmapTable;
/*     */ import org.apache.batik.svggen.font.table.GlyfTable;
/*     */ import org.apache.batik.svggen.font.table.GlyphDescription;
/*     */ import org.apache.batik.svggen.font.table.HeadTable;
/*     */ import org.apache.batik.svggen.font.table.HheaTable;
/*     */ import org.apache.batik.svggen.font.table.HmtxTable;
/*     */ import org.apache.batik.svggen.font.table.LocaTable;
/*     */ import org.apache.batik.svggen.font.table.MaxpTable;
/*     */ import org.apache.batik.svggen.font.table.NameTable;
/*     */ import org.apache.batik.svggen.font.table.Os2Table;
/*     */ import org.apache.batik.svggen.font.table.PostTable;
/*     */ import org.apache.batik.svggen.font.table.Table;
/*     */ import org.apache.batik.svggen.font.table.TableDirectory;
/*     */ import org.apache.batik.svggen.font.table.TableFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Font
/*     */ {
/*     */   private String path;
/*  49 */   private TableDirectory tableDirectory = null;
/*     */   
/*     */   private Table[] tables;
/*     */   
/*     */   private Os2Table os2;
/*     */   
/*     */   private CmapTable cmap;
/*     */   
/*     */   private GlyfTable glyf;
/*     */   
/*     */   private HeadTable head;
/*     */   
/*     */   private HheaTable hhea;
/*     */   private HmtxTable hmtx;
/*     */   private LocaTable loca;
/*     */   private MaxpTable maxp;
/*     */   private NameTable name;
/*     */   private PostTable post;
/*     */   
/*     */   public Table getTable(int tableType) {
/*  69 */     for (Table table : this.tables) {
/*  70 */       if (table != null && table.getType() == tableType) {
/*  71 */         return table;
/*     */       }
/*     */     } 
/*  74 */     return null;
/*     */   }
/*     */   
/*     */   public Os2Table getOS2Table() {
/*  78 */     return this.os2;
/*     */   }
/*     */   
/*     */   public CmapTable getCmapTable() {
/*  82 */     return this.cmap;
/*     */   }
/*     */   
/*     */   public HeadTable getHeadTable() {
/*  86 */     return this.head;
/*     */   }
/*     */   
/*     */   public HheaTable getHheaTable() {
/*  90 */     return this.hhea;
/*     */   }
/*     */   
/*     */   public HmtxTable getHmtxTable() {
/*  94 */     return this.hmtx;
/*     */   }
/*     */   
/*     */   public LocaTable getLocaTable() {
/*  98 */     return this.loca;
/*     */   }
/*     */   
/*     */   public MaxpTable getMaxpTable() {
/* 102 */     return this.maxp;
/*     */   }
/*     */   
/*     */   public NameTable getNameTable() {
/* 106 */     return this.name;
/*     */   }
/*     */   
/*     */   public PostTable getPostTable() {
/* 110 */     return this.post;
/*     */   }
/*     */   
/*     */   public int getAscent() {
/* 114 */     return this.hhea.getAscender();
/*     */   }
/*     */   
/*     */   public int getDescent() {
/* 118 */     return this.hhea.getDescender();
/*     */   }
/*     */   
/*     */   public int getNumGlyphs() {
/* 122 */     return this.maxp.getNumGlyphs();
/*     */   }
/*     */   
/*     */   public Glyph getGlyph(int i) {
/* 126 */     return (this.glyf.getDescription(i) != null) ? new Glyph((GlyphDescription)this.glyf.getDescription(i), this.hmtx.getLeftSideBearing(i), this.hmtx.getAdvanceWidth(i)) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPath() {
/* 135 */     return this.path;
/*     */   }
/*     */   
/*     */   public TableDirectory getTableDirectory() {
/* 139 */     return this.tableDirectory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void read(String pathName) {
/* 146 */     this.path = pathName;
/* 147 */     File f = new File(pathName);
/*     */     
/* 149 */     if (!f.exists()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 155 */       RandomAccessFile raf = new RandomAccessFile(f, "r");
/* 156 */       this.tableDirectory = new TableDirectory(raf);
/* 157 */       this.tables = new Table[this.tableDirectory.getNumTables()];
/*     */ 
/*     */       
/* 160 */       for (int i = 0; i < this.tableDirectory.getNumTables(); i++) {
/* 161 */         this.tables[i] = TableFactory.create(this.tableDirectory.getEntry(i), raf);
/*     */       }
/*     */       
/* 164 */       raf.close();
/*     */ 
/*     */       
/* 167 */       this.os2 = (Os2Table)getTable(1330851634);
/* 168 */       this.cmap = (CmapTable)getTable(1668112752);
/* 169 */       this.glyf = (GlyfTable)getTable(1735162214);
/* 170 */       this.head = (HeadTable)getTable(1751474532);
/* 171 */       this.hhea = (HheaTable)getTable(1751672161);
/* 172 */       this.hmtx = (HmtxTable)getTable(1752003704);
/* 173 */       this.loca = (LocaTable)getTable(1819239265);
/* 174 */       this.maxp = (MaxpTable)getTable(1835104368);
/* 175 */       this.name = (NameTable)getTable(1851878757);
/* 176 */       this.post = (PostTable)getTable(1886352244);
/*     */ 
/*     */       
/* 179 */       this.hmtx.init(this.hhea.getNumberOfHMetrics(), this.maxp.getNumGlyphs() - this.hhea.getNumberOfHMetrics());
/*     */       
/* 181 */       this.loca.init(this.maxp.getNumGlyphs(), (this.head.getIndexToLocFormat() == 0));
/* 182 */       this.glyf.init(this.maxp.getNumGlyphs(), this.loca);
/* 183 */     } catch (IOException e) {
/* 184 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Font create() {
/* 189 */     return new Font();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Font create(String pathName) {
/* 196 */     Font f = new Font();
/* 197 */     f.read(pathName);
/* 198 */     return f;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/Font.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */