/*      */ package org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf;
/*      */ 
/*      */ import org.apache.pdfbox.cos.COSArray;
/*      */ import org.apache.pdfbox.cos.COSBase;
/*      */ import org.apache.pdfbox.cos.COSDictionary;
/*      */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*      */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDGamma;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PDLayoutAttributeObject
/*      */   extends PDStandardAttributeObject
/*      */ {
/*      */   public static final String OWNER_LAYOUT = "Layout";
/*      */   private static final String PLACEMENT = "Placement";
/*      */   private static final String WRITING_MODE = "WritingMode";
/*      */   private static final String BACKGROUND_COLOR = "BackgroundColor";
/*      */   private static final String BORDER_COLOR = "BorderColor";
/*      */   private static final String BORDER_STYLE = "BorderStyle";
/*      */   private static final String BORDER_THICKNESS = "BorderThickness";
/*      */   private static final String PADDING = "Padding";
/*      */   private static final String COLOR = "Color";
/*      */   private static final String SPACE_BEFORE = "SpaceBefore";
/*      */   private static final String SPACE_AFTER = "SpaceAfter";
/*      */   private static final String START_INDENT = "StartIndent";
/*      */   private static final String END_INDENT = "EndIndent";
/*      */   private static final String TEXT_INDENT = "TextIndent";
/*      */   private static final String TEXT_ALIGN = "TextAlign";
/*      */   private static final String BBOX = "BBox";
/*      */   private static final String WIDTH = "Width";
/*      */   private static final String HEIGHT = "Height";
/*      */   private static final String BLOCK_ALIGN = "BlockAlign";
/*      */   private static final String INLINE_ALIGN = "InlineAlign";
/*      */   private static final String T_BORDER_STYLE = "TBorderStyle";
/*      */   private static final String T_PADDING = "TPadding";
/*      */   private static final String BASELINE_SHIFT = "BaselineShift";
/*      */   private static final String LINE_HEIGHT = "LineHeight";
/*      */   private static final String TEXT_DECORATION_COLOR = "TextDecorationColor";
/*      */   private static final String TEXT_DECORATION_THICKNESS = "TextDecorationThickness";
/*      */   private static final String TEXT_DECORATION_TYPE = "TextDecorationType";
/*      */   private static final String RUBY_ALIGN = "RubyAlign";
/*      */   private static final String RUBY_POSITION = "RubyPosition";
/*      */   private static final String GLYPH_ORIENTATION_VERTICAL = "GlyphOrientationVertical";
/*      */   private static final String COLUMN_COUNT = "ColumnCount";
/*      */   private static final String COLUMN_GAP = "ColumnGap";
/*      */   private static final String COLUMN_WIDTHS = "ColumnWidths";
/*      */   public static final String PLACEMENT_BLOCK = "Block";
/*      */   public static final String PLACEMENT_INLINE = "Inline";
/*      */   public static final String PLACEMENT_BEFORE = "Before";
/*      */   public static final String PLACEMENT_START = "Start";
/*      */   public static final String PLACEMENT_END = "End";
/*      */   public static final String WRITING_MODE_LRTB = "LrTb";
/*      */   public static final String WRITING_MODE_RLTB = "RlTb";
/*      */   public static final String WRITING_MODE_TBRL = "TbRl";
/*      */   public static final String BORDER_STYLE_NONE = "None";
/*      */   public static final String BORDER_STYLE_HIDDEN = "Hidden";
/*      */   public static final String BORDER_STYLE_DOTTED = "Dotted";
/*      */   public static final String BORDER_STYLE_DASHED = "Dashed";
/*      */   public static final String BORDER_STYLE_SOLID = "Solid";
/*      */   public static final String BORDER_STYLE_DOUBLE = "Double";
/*      */   public static final String BORDER_STYLE_GROOVE = "Groove";
/*      */   public static final String BORDER_STYLE_RIDGE = "Ridge";
/*      */   public static final String BORDER_STYLE_INSET = "Inset";
/*      */   public static final String BORDER_STYLE_OUTSET = "Outset";
/*      */   public static final String TEXT_ALIGN_START = "Start";
/*      */   public static final String TEXT_ALIGN_CENTER = "Center";
/*      */   public static final String TEXT_ALIGN_END = "End";
/*      */   public static final String TEXT_ALIGN_JUSTIFY = "Justify";
/*      */   public static final String WIDTH_AUTO = "Auto";
/*      */   public static final String HEIGHT_AUTO = "Auto";
/*      */   public static final String BLOCK_ALIGN_BEFORE = "Before";
/*      */   public static final String BLOCK_ALIGN_MIDDLE = "Middle";
/*      */   public static final String BLOCK_ALIGN_AFTER = "After";
/*      */   public static final String BLOCK_ALIGN_JUSTIFY = "Justify";
/*      */   public static final String INLINE_ALIGN_START = "Start";
/*      */   public static final String INLINE_ALIGN_CENTER = "Center";
/*      */   public static final String INLINE_ALIGN_END = "End";
/*      */   public static final String LINE_HEIGHT_NORMAL = "Normal";
/*      */   public static final String LINE_HEIGHT_AUTO = "Auto";
/*      */   public static final String TEXT_DECORATION_TYPE_NONE = "None";
/*      */   public static final String TEXT_DECORATION_TYPE_UNDERLINE = "Underline";
/*      */   public static final String TEXT_DECORATION_TYPE_OVERLINE = "Overline";
/*      */   public static final String TEXT_DECORATION_TYPE_LINE_THROUGH = "LineThrough";
/*      */   public static final String RUBY_ALIGN_START = "Start";
/*      */   public static final String RUBY_ALIGN_CENTER = "Center";
/*      */   public static final String RUBY_ALIGN_END = "End";
/*      */   public static final String RUBY_ALIGN_JUSTIFY = "Justify";
/*      */   public static final String RUBY_ALIGN_DISTRIBUTE = "Distribute";
/*      */   public static final String RUBY_POSITION_BEFORE = "Before";
/*      */   public static final String RUBY_POSITION_AFTER = "After";
/*      */   public static final String RUBY_POSITION_WARICHU = "Warichu";
/*      */   public static final String RUBY_POSITION_INLINE = "Inline";
/*      */   public static final String GLYPH_ORIENTATION_VERTICAL_AUTO = "Auto";
/*      */   public static final String GLYPH_ORIENTATION_VERTICAL_MINUS_180_DEGREES = "-180";
/*      */   public static final String GLYPH_ORIENTATION_VERTICAL_MINUS_90_DEGREES = "-90";
/*      */   public static final String GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES = "0";
/*      */   public static final String GLYPH_ORIENTATION_VERTICAL_90_DEGREES = "90";
/*      */   public static final String GLYPH_ORIENTATION_VERTICAL_180_DEGREES = "180";
/*      */   public static final String GLYPH_ORIENTATION_VERTICAL_270_DEGREES = "270";
/*      */   public static final String GLYPH_ORIENTATION_VERTICAL_360_DEGREES = "360";
/*      */   
/*      */   public PDLayoutAttributeObject() {
/*  357 */     setOwner("Layout");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDLayoutAttributeObject(COSDictionary dictionary) {
/*  367 */     super(dictionary);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPlacement() {
/*  380 */     return getName("Placement", "Inline");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPlacement(String placement) {
/*  398 */     setName("Placement", placement);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getWritingMode() {
/*  409 */     return getName("WritingMode", "LrTb");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWritingMode(String writingMode) {
/*  424 */     setName("WritingMode", writingMode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDGamma getBackgroundColor() {
/*  434 */     return getColor("BackgroundColor");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBackgroundColor(PDGamma backgroundColor) {
/*  444 */     setColor("BackgroundColor", backgroundColor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getBorderColors() {
/*  455 */     return getColorOrFourColors("BorderColor");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllBorderColors(PDGamma borderColor) {
/*  465 */     setColor("BorderColor", borderColor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBorderColors(PDFourColours borderColors) {
/*  475 */     setFourColors("BorderColor", borderColors);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getBorderStyle() {
/*  486 */     return getNameOrArrayOfName("BorderStyle", "None");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllBorderStyles(String borderStyle) {
/*  509 */     setName("BorderStyle", borderStyle);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBorderStyles(String[] borderStyles) {
/*  532 */     setArrayOfName("BorderStyle", borderStyles);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getBorderThickness() {
/*  542 */     return getNumberOrArrayOfNumber("BorderThickness", -1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllBorderThicknesses(float borderThickness) {
/*  552 */     setNumber("BorderThickness", borderThickness);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllBorderThicknesses(int borderThickness) {
/*  562 */     setNumber("BorderThickness", borderThickness);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBorderThicknesses(float[] borderThicknesses) {
/*  572 */     setArrayOfNumber("BorderThickness", borderThicknesses);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getPadding() {
/*  582 */     return getNumberOrArrayOfNumber("Padding", 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllPaddings(float padding) {
/*  592 */     setNumber("Padding", padding);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllPaddings(int padding) {
/*  602 */     setNumber("Padding", padding);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPaddings(float[] paddings) {
/*  612 */     setArrayOfNumber("Padding", paddings);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDGamma getColor() {
/*  623 */     return getColor("Color");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColor(PDGamma color) {
/*  634 */     setColor("Color", color);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSpaceBefore() {
/*  645 */     return getNumber("SpaceBefore", 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSpaceBefore(float spaceBefore) {
/*  656 */     setNumber("SpaceBefore", spaceBefore);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSpaceBefore(int spaceBefore) {
/*  667 */     setNumber("SpaceBefore", spaceBefore);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSpaceAfter() {
/*  678 */     return getNumber("SpaceAfter", 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSpaceAfter(float spaceAfter) {
/*  689 */     setNumber("SpaceAfter", spaceAfter);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSpaceAfter(int spaceAfter) {
/*  700 */     setNumber("SpaceAfter", spaceAfter);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getStartIndent() {
/*  711 */     return getNumber("StartIndent", 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStartIndent(float startIndent) {
/*  722 */     setNumber("StartIndent", startIndent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStartIndent(int startIndent) {
/*  733 */     setNumber("StartIndent", startIndent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getEndIndent() {
/*  745 */     return getNumber("EndIndent", 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEndIndent(float endIndent) {
/*  756 */     setNumber("EndIndent", endIndent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEndIndent(int endIndent) {
/*  767 */     setNumber("EndIndent", endIndent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getTextIndent() {
/*  779 */     return getNumber("TextIndent", 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextIndent(float textIndent) {
/*  791 */     setNumber("TextIndent", textIndent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextIndent(int textIndent) {
/*  803 */     setNumber("TextIndent", textIndent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTextAlign() {
/*  815 */     return getName("TextAlign", "Start");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextAlign(String textIndent) {
/*  833 */     setName("TextAlign", textIndent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDRectangle getBBox() {
/*  844 */     COSArray array = (COSArray)getCOSObject().getDictionaryObject("BBox");
/*  845 */     if (array != null)
/*      */     {
/*  847 */       return new PDRectangle(array);
/*      */     }
/*  849 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBBox(PDRectangle bbox) {
/*  859 */     String name = "BBox";
/*  860 */     COSBase oldValue = getCOSObject().getDictionaryObject(name);
/*  861 */     getCOSObject().setItem(name, (COSObjectable)bbox);
/*  862 */     COSBase newValue = (bbox == null) ? null : bbox.getCOSObject();
/*  863 */     potentiallyNotifyChanged(oldValue, newValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getWidth() {
/*  875 */     return getNumberOrName("Width", "Auto");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWidthAuto() {
/*  884 */     setName("Width", "Auto");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWidth(float width) {
/*  895 */     setNumber("Width", width);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWidth(int width) {
/*  906 */     setNumber("Width", width);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getHeight() {
/*  918 */     return getNumberOrName("Height", "Auto");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHeightAuto() {
/*  927 */     setName("Height", "Auto");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHeight(float height) {
/*  938 */     setNumber("Height", height);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHeight(int height) {
/*  949 */     setNumber("Height", height);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getBlockAlign() {
/*  961 */     return getName("BlockAlign", "Before");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBlockAlign(String blockAlign) {
/*  978 */     setName("BlockAlign", blockAlign);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getInlineAlign() {
/*  990 */     return getName("InlineAlign", "Start");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInlineAlign(String inlineAlign) {
/* 1006 */     setName("InlineAlign", inlineAlign);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getTBorderStyle() {
/* 1017 */     return getNameOrArrayOfName("TBorderStyle", "None");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllTBorderStyles(String tBorderStyle) {
/* 1040 */     setName("TBorderStyle", tBorderStyle);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTBorderStyles(String[] tBorderStyles) {
/* 1063 */     setArrayOfName("TBorderStyle", tBorderStyles);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getTPadding() {
/* 1075 */     return getNumberOrArrayOfNumber("TPadding", 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllTPaddings(float tPadding) {
/* 1085 */     setNumber("TPadding", tPadding);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllTPaddings(int tPadding) {
/* 1095 */     setNumber("TPadding", tPadding);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTPaddings(float[] tPaddings) {
/* 1105 */     setArrayOfNumber("TPadding", tPaddings);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getBaselineShift() {
/* 1117 */     return getNumber("BaselineShift", 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBaselineShift(float baselineShift) {
/* 1128 */     setNumber("BaselineShift", baselineShift);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBaselineShift(int baselineShift) {
/* 1139 */     setNumber("BaselineShift", baselineShift);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getLineHeight() {
/* 1150 */     return getNumberOrName("LineHeight", "Normal");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLineHeightNormal() {
/* 1159 */     setName("LineHeight", "Normal");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLineHeightAuto() {
/* 1168 */     setName("LineHeight", "Auto");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLineHeight(float lineHeight) {
/* 1179 */     setNumber("LineHeight", lineHeight);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLineHeight(int lineHeight) {
/* 1190 */     setNumber("LineHeight", lineHeight);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDGamma getTextDecorationColor() {
/* 1201 */     return getColor("TextDecorationColor");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextDecorationColor(PDGamma textDecorationColor) {
/* 1212 */     setColor("TextDecorationColor", textDecorationColor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getTextDecorationThickness() {
/* 1223 */     return getNumber("TextDecorationThickness");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextDecorationThickness(float textDecorationThickness) {
/* 1234 */     setNumber("TextDecorationThickness", textDecorationThickness);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextDecorationThickness(int textDecorationThickness) {
/* 1245 */     setNumber("TextDecorationThickness", textDecorationThickness);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTextDecorationType() {
/* 1256 */     return getName("TextDecorationType", "None");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextDecorationType(String textDecorationType) {
/* 1273 */     setName("TextDecorationType", textDecorationType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRubyAlign() {
/* 1284 */     return getName("RubyAlign", "Distribute");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRubyAlign(String rubyAlign) {
/* 1302 */     setName("RubyAlign", rubyAlign);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRubyPosition() {
/* 1314 */     return getName("RubyPosition", "Before");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRubyPosition(String rubyPosition) {
/* 1331 */     setName("RubyPosition", rubyPosition);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getGlyphOrientationVertical() {
/* 1343 */     return getName("GlyphOrientationVertical", "Auto");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGlyphOrientationVertical(String glyphOrientationVertical) {
/* 1366 */     setName("GlyphOrientationVertical", glyphOrientationVertical);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getColumnCount() {
/* 1377 */     return getInteger("ColumnCount", 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColumnCount(int columnCount) {
/* 1388 */     setInteger("ColumnCount", columnCount);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getColumnGap() {
/* 1399 */     return getNumberOrArrayOfNumber("ColumnGap", -1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColumnGap(float columnGap) {
/* 1410 */     setNumber("ColumnGap", columnGap);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColumnGap(int columnGap) {
/* 1421 */     setNumber("ColumnGap", columnGap);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColumnGaps(float[] columnGaps) {
/* 1434 */     setArrayOfNumber("ColumnGap", columnGaps);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getColumnWidths() {
/* 1445 */     return getNumberOrArrayOfNumber("ColumnWidths", -1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllColumnWidths(float columnWidth) {
/* 1455 */     setNumber("ColumnWidths", columnWidth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllColumnWidths(int columnWidth) {
/* 1465 */     setNumber("ColumnWidths", columnWidth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColumnWidths(float[] columnWidths) {
/* 1475 */     setArrayOfNumber("ColumnWidths", columnWidths);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1481 */     StringBuilder sb = (new StringBuilder()).append(super.toString());
/* 1482 */     if (isSpecified("Placement"))
/*      */     {
/* 1484 */       sb.append(", Placement=").append(getPlacement());
/*      */     }
/* 1486 */     if (isSpecified("WritingMode"))
/*      */     {
/* 1488 */       sb.append(", WritingMode=").append(getWritingMode());
/*      */     }
/* 1490 */     if (isSpecified("BackgroundColor"))
/*      */     {
/* 1492 */       sb.append(", BackgroundColor=").append(getBackgroundColor());
/*      */     }
/* 1494 */     if (isSpecified("BorderColor"))
/*      */     {
/* 1496 */       sb.append(", BorderColor=").append(getBorderColors());
/*      */     }
/* 1498 */     if (isSpecified("BorderStyle")) {
/*      */       
/* 1500 */       Object borderStyle = getBorderStyle();
/* 1501 */       sb.append(", BorderStyle=");
/* 1502 */       if (borderStyle instanceof String[]) {
/*      */         
/* 1504 */         sb.append(arrayToString((Object[])borderStyle));
/*      */       }
/*      */       else {
/*      */         
/* 1508 */         sb.append(borderStyle);
/*      */       } 
/*      */     } 
/* 1511 */     if (isSpecified("BorderThickness")) {
/*      */       
/* 1513 */       Object borderThickness = getBorderThickness();
/* 1514 */       sb.append(", BorderThickness=");
/* 1515 */       if (borderThickness instanceof float[]) {
/*      */         
/* 1517 */         sb.append(arrayToString((float[])borderThickness));
/*      */       }
/*      */       else {
/*      */         
/* 1521 */         sb.append(String.valueOf(borderThickness));
/*      */       } 
/*      */     } 
/* 1524 */     if (isSpecified("Padding")) {
/*      */       
/* 1526 */       Object padding = getPadding();
/* 1527 */       sb.append(", Padding=");
/* 1528 */       if (padding instanceof float[]) {
/*      */         
/* 1530 */         sb.append(arrayToString((float[])padding));
/*      */       }
/*      */       else {
/*      */         
/* 1534 */         sb.append(String.valueOf(padding));
/*      */       } 
/*      */     } 
/* 1537 */     if (isSpecified("Color"))
/*      */     {
/* 1539 */       sb.append(", Color=").append(getColor());
/*      */     }
/* 1541 */     if (isSpecified("SpaceBefore"))
/*      */     {
/* 1543 */       sb.append(", SpaceBefore=")
/* 1544 */         .append(String.valueOf(getSpaceBefore()));
/*      */     }
/* 1546 */     if (isSpecified("SpaceAfter"))
/*      */     {
/* 1548 */       sb.append(", SpaceAfter=")
/* 1549 */         .append(String.valueOf(getSpaceAfter()));
/*      */     }
/* 1551 */     if (isSpecified("StartIndent"))
/*      */     {
/* 1553 */       sb.append(", StartIndent=")
/* 1554 */         .append(String.valueOf(getStartIndent()));
/*      */     }
/* 1556 */     if (isSpecified("EndIndent"))
/*      */     {
/* 1558 */       sb.append(", EndIndent=")
/* 1559 */         .append(String.valueOf(getEndIndent()));
/*      */     }
/* 1561 */     if (isSpecified("TextIndent"))
/*      */     {
/* 1563 */       sb.append(", TextIndent=")
/* 1564 */         .append(String.valueOf(getTextIndent()));
/*      */     }
/* 1566 */     if (isSpecified("TextAlign"))
/*      */     {
/* 1568 */       sb.append(", TextAlign=").append(getTextAlign());
/*      */     }
/* 1570 */     if (isSpecified("BBox"))
/*      */     {
/* 1572 */       sb.append(", BBox=").append(getBBox());
/*      */     }
/* 1574 */     if (isSpecified("Width")) {
/*      */       
/* 1576 */       Object width = getWidth();
/* 1577 */       sb.append(", Width=");
/* 1578 */       if (width instanceof Float) {
/*      */         
/* 1580 */         sb.append(String.valueOf(width));
/*      */       }
/*      */       else {
/*      */         
/* 1584 */         sb.append(width);
/*      */       } 
/*      */     } 
/* 1587 */     if (isSpecified("Height")) {
/*      */       
/* 1589 */       Object height = getHeight();
/* 1590 */       sb.append(", Height=");
/* 1591 */       if (height instanceof Float) {
/*      */         
/* 1593 */         sb.append(String.valueOf(height));
/*      */       }
/*      */       else {
/*      */         
/* 1597 */         sb.append(height);
/*      */       } 
/*      */     } 
/* 1600 */     if (isSpecified("BlockAlign"))
/*      */     {
/* 1602 */       sb.append(", BlockAlign=").append(getBlockAlign());
/*      */     }
/* 1604 */     if (isSpecified("InlineAlign"))
/*      */     {
/* 1606 */       sb.append(", InlineAlign=").append(getInlineAlign());
/*      */     }
/* 1608 */     if (isSpecified("TBorderStyle")) {
/*      */       
/* 1610 */       Object tBorderStyle = getTBorderStyle();
/* 1611 */       sb.append(", TBorderStyle=");
/* 1612 */       if (tBorderStyle instanceof String[]) {
/*      */         
/* 1614 */         sb.append(arrayToString((Object[])tBorderStyle));
/*      */       }
/*      */       else {
/*      */         
/* 1618 */         sb.append(tBorderStyle);
/*      */       } 
/*      */     } 
/* 1621 */     if (isSpecified("TPadding")) {
/*      */       
/* 1623 */       Object tPadding = getTPadding();
/* 1624 */       sb.append(", TPadding=");
/* 1625 */       if (tPadding instanceof float[]) {
/*      */         
/* 1627 */         sb.append(arrayToString((float[])tPadding));
/*      */       }
/*      */       else {
/*      */         
/* 1631 */         sb.append(String.valueOf(tPadding));
/*      */       } 
/*      */     } 
/* 1634 */     if (isSpecified("BaselineShift"))
/*      */     {
/* 1636 */       sb.append(", BaselineShift=")
/* 1637 */         .append(String.valueOf(getBaselineShift()));
/*      */     }
/* 1639 */     if (isSpecified("LineHeight")) {
/*      */       
/* 1641 */       Object lineHeight = getLineHeight();
/* 1642 */       sb.append(", LineHeight=");
/* 1643 */       if (lineHeight instanceof Float) {
/*      */         
/* 1645 */         sb.append(String.valueOf(lineHeight));
/*      */       }
/*      */       else {
/*      */         
/* 1649 */         sb.append(lineHeight);
/*      */       } 
/*      */     } 
/* 1652 */     if (isSpecified("TextDecorationColor"))
/*      */     {
/* 1654 */       sb.append(", TextDecorationColor=")
/* 1655 */         .append(getTextDecorationColor());
/*      */     }
/* 1657 */     if (isSpecified("TextDecorationThickness"))
/*      */     {
/* 1659 */       sb.append(", TextDecorationThickness=")
/* 1660 */         .append(String.valueOf(getTextDecorationThickness()));
/*      */     }
/* 1662 */     if (isSpecified("TextDecorationType"))
/*      */     {
/* 1664 */       sb.append(", TextDecorationType=")
/* 1665 */         .append(getTextDecorationType());
/*      */     }
/* 1667 */     if (isSpecified("RubyAlign"))
/*      */     {
/* 1669 */       sb.append(", RubyAlign=").append(getRubyAlign());
/*      */     }
/* 1671 */     if (isSpecified("RubyPosition"))
/*      */     {
/* 1673 */       sb.append(", RubyPosition=").append(getRubyPosition());
/*      */     }
/* 1675 */     if (isSpecified("GlyphOrientationVertical"))
/*      */     {
/* 1677 */       sb.append(", GlyphOrientationVertical=")
/* 1678 */         .append(getGlyphOrientationVertical());
/*      */     }
/* 1680 */     if (isSpecified("ColumnCount"))
/*      */     {
/* 1682 */       sb.append(", ColumnCount=")
/* 1683 */         .append(String.valueOf(getColumnCount()));
/*      */     }
/* 1685 */     if (isSpecified("ColumnGap")) {
/*      */       
/* 1687 */       Object columnGap = getColumnGap();
/* 1688 */       sb.append(", ColumnGap=");
/* 1689 */       if (columnGap instanceof float[]) {
/*      */         
/* 1691 */         sb.append(arrayToString((float[])columnGap));
/*      */       }
/*      */       else {
/*      */         
/* 1695 */         sb.append(String.valueOf(columnGap));
/*      */       } 
/*      */     } 
/* 1698 */     if (isSpecified("ColumnWidths")) {
/*      */       
/* 1700 */       Object columnWidth = getColumnWidths();
/* 1701 */       sb.append(", ColumnWidths=");
/* 1702 */       if (columnWidth instanceof float[]) {
/*      */         
/* 1704 */         sb.append(arrayToString((float[])columnWidth));
/*      */       }
/*      */       else {
/*      */         
/* 1708 */         sb.append(String.valueOf(columnWidth));
/*      */       } 
/*      */     } 
/* 1711 */     return sb.toString();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/taggedpdf/PDLayoutAttributeObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */