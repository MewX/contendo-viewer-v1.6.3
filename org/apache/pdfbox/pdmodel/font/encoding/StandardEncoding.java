/*     */ package org.apache.pdfbox.pdmodel.font.encoding;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSName;
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
/*     */ public class StandardEncoding
/*     */   extends Encoding
/*     */ {
/*     */   private static final int CHAR_CODE = 0;
/*     */   private static final int CHAR_NAME = 1;
/*  37 */   private static final Object[][] STANDARD_ENCODING_TABLE = new Object[][] { 
/*  38 */       { Integer.valueOf(65), "A"
/*  39 */       }, { Integer.valueOf(225), "AE"
/*  40 */       }, { Integer.valueOf(66), "B"
/*  41 */       }, { Integer.valueOf(67), "C"
/*  42 */       }, { Integer.valueOf(68), "D"
/*  43 */       }, { Integer.valueOf(69), "E"
/*  44 */       }, { Integer.valueOf(70), "F"
/*  45 */       }, { Integer.valueOf(71), "G"
/*  46 */       }, { Integer.valueOf(72), "H"
/*  47 */       }, { Integer.valueOf(73), "I" }, 
/*  48 */       { Integer.valueOf(74), "J"
/*  49 */       }, { Integer.valueOf(75), "K"
/*  50 */       }, { Integer.valueOf(76), "L"
/*  51 */       }, { Integer.valueOf(232), "Lslash"
/*  52 */       }, { Integer.valueOf(77), "M"
/*  53 */       }, { Integer.valueOf(78), "N"
/*  54 */       }, { Integer.valueOf(79), "O"
/*  55 */       }, { Integer.valueOf(234), "OE"
/*  56 */       }, { Integer.valueOf(233), "Oslash"
/*  57 */       }, { Integer.valueOf(80), "P" }, 
/*  58 */       { Integer.valueOf(81), "Q"
/*  59 */       }, { Integer.valueOf(82), "R"
/*  60 */       }, { Integer.valueOf(83), "S"
/*  61 */       }, { Integer.valueOf(84), "T"
/*  62 */       }, { Integer.valueOf(85), "U"
/*  63 */       }, { Integer.valueOf(86), "V"
/*  64 */       }, { Integer.valueOf(87), "W"
/*  65 */       }, { Integer.valueOf(88), "X"
/*  66 */       }, { Integer.valueOf(89), "Y"
/*  67 */       }, { Integer.valueOf(90), "Z" }, 
/*  68 */       { Integer.valueOf(97), "a"
/*  69 */       }, { Integer.valueOf(194), "acute"
/*  70 */       }, { Integer.valueOf(241), "ae"
/*  71 */       }, { Integer.valueOf(38), "ampersand"
/*  72 */       }, { Integer.valueOf(94), "asciicircum"
/*  73 */       }, { Integer.valueOf(126), "asciitilde"
/*  74 */       }, { Integer.valueOf(42), "asterisk"
/*  75 */       }, { Integer.valueOf(64), "at"
/*  76 */       }, { Integer.valueOf(98), "b"
/*  77 */       }, { Integer.valueOf(92), "backslash" }, 
/*  78 */       { Integer.valueOf(124), "bar"
/*  79 */       }, { Integer.valueOf(123), "braceleft"
/*  80 */       }, { Integer.valueOf(125), "braceright"
/*  81 */       }, { Integer.valueOf(91), "bracketleft"
/*  82 */       }, { Integer.valueOf(93), "bracketright"
/*  83 */       }, { Integer.valueOf(198), "breve"
/*  84 */       }, { Integer.valueOf(183), "bullet"
/*  85 */       }, { Integer.valueOf(99), "c"
/*  86 */       }, { Integer.valueOf(207), "caron"
/*  87 */       }, { Integer.valueOf(203), "cedilla" }, 
/*  88 */       { Integer.valueOf(162), "cent"
/*  89 */       }, { Integer.valueOf(195), "circumflex"
/*  90 */       }, { Integer.valueOf(58), "colon"
/*  91 */       }, { Integer.valueOf(44), "comma"
/*  92 */       }, { Integer.valueOf(168), "currency"
/*  93 */       }, { Integer.valueOf(100), "d"
/*  94 */       }, { Integer.valueOf(178), "dagger"
/*  95 */       }, { Integer.valueOf(179), "daggerdbl"
/*  96 */       }, { Integer.valueOf(200), "dieresis"
/*  97 */       }, { Integer.valueOf(36), "dollar" }, 
/*  98 */       { Integer.valueOf(199), "dotaccent"
/*  99 */       }, { Integer.valueOf(245), "dotlessi"
/* 100 */       }, { Integer.valueOf(101), "e"
/* 101 */       }, { Integer.valueOf(56), "eight"
/* 102 */       }, { Integer.valueOf(188), "ellipsis"
/* 103 */       }, { Integer.valueOf(208), "emdash"
/* 104 */       }, { Integer.valueOf(177), "endash"
/* 105 */       }, { Integer.valueOf(61), "equal"
/* 106 */       }, { Integer.valueOf(33), "exclam"
/* 107 */       }, { Integer.valueOf(161), "exclamdown" }, 
/* 108 */       { Integer.valueOf(102), "f"
/* 109 */       }, { Integer.valueOf(174), "fi"
/* 110 */       }, { Integer.valueOf(53), "five"
/* 111 */       }, { Integer.valueOf(175), "fl"
/* 112 */       }, { Integer.valueOf(166), "florin"
/* 113 */       }, { Integer.valueOf(52), "four"
/* 114 */       }, { Integer.valueOf(164), "fraction"
/* 115 */       }, { Integer.valueOf(103), "g"
/* 116 */       }, { Integer.valueOf(251), "germandbls"
/* 117 */       }, { Integer.valueOf(193), "grave" }, 
/* 118 */       { Integer.valueOf(62), "greater"
/* 119 */       }, { Integer.valueOf(171), "guillemotleft"
/* 120 */       }, { Integer.valueOf(187), "guillemotright"
/* 121 */       }, { Integer.valueOf(172), "guilsinglleft"
/* 122 */       }, { Integer.valueOf(173), "guilsinglright"
/* 123 */       }, { Integer.valueOf(104), "h"
/* 124 */       }, { Integer.valueOf(205), "hungarumlaut"
/* 125 */       }, { Integer.valueOf(45), "hyphen"
/* 126 */       }, { Integer.valueOf(105), "i"
/* 127 */       }, { Integer.valueOf(106), "j" }, 
/* 128 */       { Integer.valueOf(107), "k"
/* 129 */       }, { Integer.valueOf(108), "l"
/* 130 */       }, { Integer.valueOf(60), "less"
/* 131 */       }, { Integer.valueOf(248), "lslash"
/* 132 */       }, { Integer.valueOf(109), "m"
/* 133 */       }, { Integer.valueOf(197), "macron"
/* 134 */       }, { Integer.valueOf(110), "n"
/* 135 */       }, { Integer.valueOf(57), "nine"
/* 136 */       }, { Integer.valueOf(35), "numbersign"
/* 137 */       }, { Integer.valueOf(111), "o" }, 
/* 138 */       { Integer.valueOf(250), "oe"
/* 139 */       }, { Integer.valueOf(206), "ogonek"
/* 140 */       }, { Integer.valueOf(49), "one"
/* 141 */       }, { Integer.valueOf(227), "ordfeminine"
/* 142 */       }, { Integer.valueOf(235), "ordmasculine"
/* 143 */       }, { Integer.valueOf(249), "oslash"
/* 144 */       }, { Integer.valueOf(112), "p"
/* 145 */       }, { Integer.valueOf(182), "paragraph"
/* 146 */       }, { Integer.valueOf(40), "parenleft"
/* 147 */       }, { Integer.valueOf(41), "parenright" }, 
/* 148 */       { Integer.valueOf(37), "percent"
/* 149 */       }, { Integer.valueOf(46), "period"
/* 150 */       }, { Integer.valueOf(180), "periodcentered"
/* 151 */       }, { Integer.valueOf(189), "perthousand"
/* 152 */       }, { Integer.valueOf(43), "plus"
/* 153 */       }, { Integer.valueOf(113), "q"
/* 154 */       }, { Integer.valueOf(63), "question"
/* 155 */       }, { Integer.valueOf(191), "questiondown"
/* 156 */       }, { Integer.valueOf(34), "quotedbl"
/* 157 */       }, { Integer.valueOf(185), "quotedblbase" }, 
/* 158 */       { Integer.valueOf(170), "quotedblleft"
/* 159 */       }, { Integer.valueOf(186), "quotedblright"
/* 160 */       }, { Integer.valueOf(96), "quoteleft"
/* 161 */       }, { Integer.valueOf(39), "quoteright"
/* 162 */       }, { Integer.valueOf(184), "quotesinglbase"
/* 163 */       }, { Integer.valueOf(169), "quotesingle"
/* 164 */       }, { Integer.valueOf(114), "r"
/* 165 */       }, { Integer.valueOf(202), "ring"
/* 166 */       }, { Integer.valueOf(115), "s"
/* 167 */       }, { Integer.valueOf(167), "section" }, 
/* 168 */       { Integer.valueOf(59), "semicolon"
/* 169 */       }, { Integer.valueOf(55), "seven"
/* 170 */       }, { Integer.valueOf(54), "six"
/* 171 */       }, { Integer.valueOf(47), "slash"
/* 172 */       }, { Integer.valueOf(32), "space"
/* 173 */       }, { Integer.valueOf(163), "sterling"
/* 174 */       }, { Integer.valueOf(116), "t"
/* 175 */       }, { Integer.valueOf(51), "three"
/* 176 */       }, { Integer.valueOf(196), "tilde"
/* 177 */       }, { Integer.valueOf(50), "two" }, 
/* 178 */       { Integer.valueOf(117), "u"
/* 179 */       }, { Integer.valueOf(95), "underscore"
/* 180 */       }, { Integer.valueOf(118), "v"
/* 181 */       }, { Integer.valueOf(119), "w"
/* 182 */       }, { Integer.valueOf(120), "x"
/* 183 */       }, { Integer.valueOf(121), "y"
/* 184 */       }, { Integer.valueOf(165), "yen"
/* 185 */       }, { Integer.valueOf(122), "z"
/* 186 */       }, { Integer.valueOf(48), "zero" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public static final StandardEncoding INSTANCE = new StandardEncoding();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StandardEncoding() {
/* 201 */     for (Object[] encodingEntry : STANDARD_ENCODING_TABLE)
/*     */     {
/* 203 */       add(((Integer)encodingEntry[0]).intValue(), encodingEntry[1].toString());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/* 210 */     return (COSBase)COSName.STANDARD_ENCODING;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEncodingName() {
/* 216 */     return "StandardEncoding";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/encoding/StandardEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */