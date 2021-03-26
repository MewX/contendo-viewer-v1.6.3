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
/*     */ public class MacRomanEncoding
/*     */   extends Encoding
/*     */ {
/*     */   private static final int CHAR_CODE = 0;
/*     */   private static final int CHAR_NAME = 1;
/*  36 */   private static final Object[][] MAC_ROMAN_ENCODING_TABLE = new Object[][] { 
/*  37 */       { Integer.valueOf(65), "A"
/*  38 */       }, { Integer.valueOf(174), "AE"
/*  39 */       }, { Integer.valueOf(231), "Aacute"
/*  40 */       }, { Integer.valueOf(229), "Acircumflex"
/*  41 */       }, { Integer.valueOf(128), "Adieresis"
/*  42 */       }, { Integer.valueOf(203), "Agrave"
/*  43 */       }, { Integer.valueOf(129), "Aring"
/*  44 */       }, { Integer.valueOf(204), "Atilde"
/*  45 */       }, { Integer.valueOf(66), "B"
/*  46 */       }, { Integer.valueOf(67), "C" }, 
/*  47 */       { Integer.valueOf(130), "Ccedilla"
/*  48 */       }, { Integer.valueOf(68), "D"
/*  49 */       }, { Integer.valueOf(69), "E"
/*  50 */       }, { Integer.valueOf(131), "Eacute"
/*  51 */       }, { Integer.valueOf(230), "Ecircumflex"
/*  52 */       }, { Integer.valueOf(232), "Edieresis"
/*  53 */       }, { Integer.valueOf(233), "Egrave"
/*  54 */       }, { Integer.valueOf(70), "F"
/*  55 */       }, { Integer.valueOf(71), "G"
/*  56 */       }, { Integer.valueOf(72), "H" }, 
/*  57 */       { Integer.valueOf(73), "I"
/*  58 */       }, { Integer.valueOf(234), "Iacute"
/*  59 */       }, { Integer.valueOf(235), "Icircumflex"
/*  60 */       }, { Integer.valueOf(236), "Idieresis"
/*  61 */       }, { Integer.valueOf(237), "Igrave"
/*  62 */       }, { Integer.valueOf(74), "J"
/*  63 */       }, { Integer.valueOf(75), "K"
/*  64 */       }, { Integer.valueOf(76), "L"
/*  65 */       }, { Integer.valueOf(77), "M"
/*  66 */       }, { Integer.valueOf(78), "N" }, 
/*  67 */       { Integer.valueOf(132), "Ntilde"
/*  68 */       }, { Integer.valueOf(79), "O"
/*  69 */       }, { Integer.valueOf(206), "OE"
/*  70 */       }, { Integer.valueOf(238), "Oacute"
/*  71 */       }, { Integer.valueOf(239), "Ocircumflex"
/*  72 */       }, { Integer.valueOf(133), "Odieresis"
/*  73 */       }, { Integer.valueOf(241), "Ograve"
/*  74 */       }, { Integer.valueOf(175), "Oslash"
/*  75 */       }, { Integer.valueOf(205), "Otilde"
/*  76 */       }, { Integer.valueOf(80), "P" }, 
/*  77 */       { Integer.valueOf(81), "Q"
/*  78 */       }, { Integer.valueOf(82), "R"
/*  79 */       }, { Integer.valueOf(83), "S"
/*  80 */       }, { Integer.valueOf(84), "T"
/*  81 */       }, { Integer.valueOf(85), "U"
/*  82 */       }, { Integer.valueOf(242), "Uacute"
/*  83 */       }, { Integer.valueOf(243), "Ucircumflex"
/*  84 */       }, { Integer.valueOf(134), "Udieresis"
/*  85 */       }, { Integer.valueOf(244), "Ugrave"
/*  86 */       }, { Integer.valueOf(86), "V" }, 
/*  87 */       { Integer.valueOf(87), "W"
/*  88 */       }, { Integer.valueOf(88), "X"
/*  89 */       }, { Integer.valueOf(89), "Y"
/*  90 */       }, { Integer.valueOf(217), "Ydieresis"
/*  91 */       }, { Integer.valueOf(90), "Z"
/*  92 */       }, { Integer.valueOf(97), "a"
/*  93 */       }, { Integer.valueOf(135), "aacute"
/*  94 */       }, { Integer.valueOf(137), "acircumflex"
/*  95 */       }, { Integer.valueOf(171), "acute"
/*  96 */       }, { Integer.valueOf(138), "adieresis" }, 
/*  97 */       { Integer.valueOf(190), "ae"
/*  98 */       }, { Integer.valueOf(136), "agrave"
/*  99 */       }, { Integer.valueOf(38), "ampersand"
/* 100 */       }, { Integer.valueOf(140), "aring"
/* 101 */       }, { Integer.valueOf(94), "asciicircum"
/* 102 */       }, { Integer.valueOf(126), "asciitilde"
/* 103 */       }, { Integer.valueOf(42), "asterisk"
/* 104 */       }, { Integer.valueOf(64), "at"
/* 105 */       }, { Integer.valueOf(139), "atilde"
/* 106 */       }, { Integer.valueOf(98), "b" }, 
/* 107 */       { Integer.valueOf(92), "backslash"
/* 108 */       }, { Integer.valueOf(124), "bar"
/* 109 */       }, { Integer.valueOf(123), "braceleft"
/* 110 */       }, { Integer.valueOf(125), "braceright"
/* 111 */       }, { Integer.valueOf(91), "bracketleft"
/* 112 */       }, { Integer.valueOf(93), "bracketright"
/* 113 */       }, { Integer.valueOf(249), "breve"
/* 114 */       }, { Integer.valueOf(165), "bullet"
/* 115 */       }, { Integer.valueOf(99), "c"
/* 116 */       }, { Integer.valueOf(255), "caron" }, 
/* 117 */       { Integer.valueOf(141), "ccedilla"
/* 118 */       }, { Integer.valueOf(252), "cedilla"
/* 119 */       }, { Integer.valueOf(162), "cent"
/* 120 */       }, { Integer.valueOf(246), "circumflex"
/* 121 */       }, { Integer.valueOf(58), "colon"
/* 122 */       }, { Integer.valueOf(44), "comma"
/* 123 */       }, { Integer.valueOf(169), "copyright"
/* 124 */       }, { Integer.valueOf(219), "currency"
/* 125 */       }, { Integer.valueOf(100), "d"
/* 126 */       }, { Integer.valueOf(160), "dagger" }, 
/* 127 */       { Integer.valueOf(224), "daggerdbl"
/* 128 */       }, { Integer.valueOf(161), "degree"
/* 129 */       }, { Integer.valueOf(172), "dieresis"
/* 130 */       }, { Integer.valueOf(214), "divide"
/* 131 */       }, { Integer.valueOf(36), "dollar"
/* 132 */       }, { Integer.valueOf(250), "dotaccent"
/* 133 */       }, { Integer.valueOf(245), "dotlessi"
/* 134 */       }, { Integer.valueOf(101), "e"
/* 135 */       }, { Integer.valueOf(142), "eacute"
/* 136 */       }, { Integer.valueOf(144), "ecircumflex" }, 
/* 137 */       { Integer.valueOf(145), "edieresis"
/* 138 */       }, { Integer.valueOf(143), "egrave"
/* 139 */       }, { Integer.valueOf(56), "eight"
/* 140 */       }, { Integer.valueOf(201), "ellipsis"
/* 141 */       }, { Integer.valueOf(209), "emdash"
/* 142 */       }, { Integer.valueOf(208), "endash"
/* 143 */       }, { Integer.valueOf(61), "equal"
/* 144 */       }, { Integer.valueOf(33), "exclam"
/* 145 */       }, { Integer.valueOf(193), "exclamdown"
/* 146 */       }, { Integer.valueOf(102), "f" }, 
/* 147 */       { Integer.valueOf(222), "fi"
/* 148 */       }, { Integer.valueOf(53), "five"
/* 149 */       }, { Integer.valueOf(223), "fl"
/* 150 */       }, { Integer.valueOf(196), "florin"
/* 151 */       }, { Integer.valueOf(52), "four"
/* 152 */       }, { Integer.valueOf(218), "fraction"
/* 153 */       }, { Integer.valueOf(103), "g"
/* 154 */       }, { Integer.valueOf(167), "germandbls"
/* 155 */       }, { Integer.valueOf(96), "grave"
/* 156 */       }, { Integer.valueOf(62), "greater" }, 
/* 157 */       { Integer.valueOf(199), "guillemotleft"
/* 158 */       }, { Integer.valueOf(200), "guillemotright"
/* 159 */       }, { Integer.valueOf(220), "guilsinglleft"
/* 160 */       }, { Integer.valueOf(221), "guilsinglright"
/* 161 */       }, { Integer.valueOf(104), "h"
/* 162 */       }, { Integer.valueOf(253), "hungarumlaut"
/* 163 */       }, { Integer.valueOf(45), "hyphen"
/* 164 */       }, { Integer.valueOf(105), "i"
/* 165 */       }, { Integer.valueOf(146), "iacute"
/* 166 */       }, { Integer.valueOf(148), "icircumflex" }, 
/* 167 */       { Integer.valueOf(149), "idieresis"
/* 168 */       }, { Integer.valueOf(147), "igrave"
/* 169 */       }, { Integer.valueOf(106), "j"
/* 170 */       }, { Integer.valueOf(107), "k"
/* 171 */       }, { Integer.valueOf(108), "l"
/* 172 */       }, { Integer.valueOf(60), "less"
/* 173 */       }, { Integer.valueOf(194), "logicalnot"
/* 174 */       }, { Integer.valueOf(109), "m"
/* 175 */       }, { Integer.valueOf(248), "macron"
/* 176 */       }, { Integer.valueOf(181), "mu" }, 
/* 177 */       { Integer.valueOf(110), "n"
/* 178 */       }, { Integer.valueOf(57), "nine"
/* 179 */       }, { Integer.valueOf(150), "ntilde"
/* 180 */       }, { Integer.valueOf(35), "numbersign"
/* 181 */       }, { Integer.valueOf(111), "o"
/* 182 */       }, { Integer.valueOf(151), "oacute"
/* 183 */       }, { Integer.valueOf(153), "ocircumflex"
/* 184 */       }, { Integer.valueOf(154), "odieresis"
/* 185 */       }, { Integer.valueOf(207), "oe"
/* 186 */       }, { Integer.valueOf(254), "ogonek" }, 
/* 187 */       { Integer.valueOf(152), "ograve"
/* 188 */       }, { Integer.valueOf(49), "one"
/* 189 */       }, { Integer.valueOf(187), "ordfeminine"
/* 190 */       }, { Integer.valueOf(188), "ordmasculine"
/* 191 */       }, { Integer.valueOf(191), "oslash"
/* 192 */       }, { Integer.valueOf(155), "otilde"
/* 193 */       }, { Integer.valueOf(112), "p"
/* 194 */       }, { Integer.valueOf(166), "paragraph"
/* 195 */       }, { Integer.valueOf(40), "parenleft"
/* 196 */       }, { Integer.valueOf(41), "parenright" }, 
/* 197 */       { Integer.valueOf(37), "percent"
/* 198 */       }, { Integer.valueOf(46), "period"
/* 199 */       }, { Integer.valueOf(225), "periodcentered"
/* 200 */       }, { Integer.valueOf(228), "perthousand"
/* 201 */       }, { Integer.valueOf(43), "plus"
/* 202 */       }, { Integer.valueOf(177), "plusminus"
/* 203 */       }, { Integer.valueOf(113), "q"
/* 204 */       }, { Integer.valueOf(63), "question"
/* 205 */       }, { Integer.valueOf(192), "questiondown"
/* 206 */       }, { Integer.valueOf(34), "quotedbl" }, 
/* 207 */       { Integer.valueOf(227), "quotedblbase"
/* 208 */       }, { Integer.valueOf(210), "quotedblleft"
/* 209 */       }, { Integer.valueOf(211), "quotedblright"
/* 210 */       }, { Integer.valueOf(212), "quoteleft"
/* 211 */       }, { Integer.valueOf(213), "quoteright"
/* 212 */       }, { Integer.valueOf(226), "quotesinglbase"
/* 213 */       }, { Integer.valueOf(39), "quotesingle"
/* 214 */       }, { Integer.valueOf(114), "r"
/* 215 */       }, { Integer.valueOf(168), "registered"
/* 216 */       }, { Integer.valueOf(251), "ring" }, 
/* 217 */       { Integer.valueOf(115), "s"
/* 218 */       }, { Integer.valueOf(164), "section"
/* 219 */       }, { Integer.valueOf(59), "semicolon"
/* 220 */       }, { Integer.valueOf(55), "seven"
/* 221 */       }, { Integer.valueOf(54), "six"
/* 222 */       }, { Integer.valueOf(47), "slash"
/* 223 */       }, { Integer.valueOf(32), "space"
/* 224 */       }, { Integer.valueOf(163), "sterling"
/* 225 */       }, { Integer.valueOf(116), "t"
/* 226 */       }, { Integer.valueOf(51), "three" }, 
/* 227 */       { Integer.valueOf(247), "tilde"
/* 228 */       }, { Integer.valueOf(170), "trademark"
/* 229 */       }, { Integer.valueOf(50), "two"
/* 230 */       }, { Integer.valueOf(117), "u"
/* 231 */       }, { Integer.valueOf(156), "uacute"
/* 232 */       }, { Integer.valueOf(158), "ucircumflex"
/* 233 */       }, { Integer.valueOf(159), "udieresis"
/* 234 */       }, { Integer.valueOf(157), "ugrave"
/* 235 */       }, { Integer.valueOf(95), "underscore"
/* 236 */       }, { Integer.valueOf(118), "v" }, 
/* 237 */       { Integer.valueOf(119), "w"
/* 238 */       }, { Integer.valueOf(120), "x"
/* 239 */       }, { Integer.valueOf(121), "y"
/* 240 */       }, { Integer.valueOf(216), "ydieresis"
/* 241 */       }, { Integer.valueOf(180), "yen"
/* 242 */       }, { Integer.valueOf(122), "z"
/* 243 */       }, { Integer.valueOf(48), "zero"
/*     */       },
/* 245 */       { Integer.valueOf(202), "space" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 253 */   public static final MacRomanEncoding INSTANCE = new MacRomanEncoding();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MacRomanEncoding() {
/* 260 */     for (Object[] encodingEntry : MAC_ROMAN_ENCODING_TABLE)
/*     */     {
/* 262 */       add(((Integer)encodingEntry[0]).intValue(), encodingEntry[1].toString());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/* 269 */     return (COSBase)COSName.MAC_ROMAN_ENCODING;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEncodingName() {
/* 275 */     return "MacRomanEncoding";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/encoding/MacRomanEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */