/*     */ package org.apache.fontbox.encoding;
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
/*     */ public class MacRomanEncoding
/*     */   extends Encoding
/*     */ {
/*     */   private static final int CHAR_CODE = 0;
/*     */   private static final int CHAR_NAME = 1;
/*  35 */   private static final Object[][] MAC_ROMAN_ENCODING_TABLE = new Object[][] { 
/*  36 */       { Integer.valueOf(65), "A"
/*  37 */       }, { Integer.valueOf(174), "AE"
/*  38 */       }, { Integer.valueOf(231), "Aacute"
/*  39 */       }, { Integer.valueOf(229), "Acircumflex"
/*  40 */       }, { Integer.valueOf(128), "Adieresis"
/*  41 */       }, { Integer.valueOf(203), "Agrave"
/*  42 */       }, { Integer.valueOf(129), "Aring"
/*  43 */       }, { Integer.valueOf(204), "Atilde"
/*  44 */       }, { Integer.valueOf(66), "B"
/*  45 */       }, { Integer.valueOf(67), "C" }, 
/*  46 */       { Integer.valueOf(130), "Ccedilla"
/*  47 */       }, { Integer.valueOf(68), "D"
/*  48 */       }, { Integer.valueOf(69), "E"
/*  49 */       }, { Integer.valueOf(131), "Eacute"
/*  50 */       }, { Integer.valueOf(230), "Ecircumflex"
/*  51 */       }, { Integer.valueOf(232), "Edieresis"
/*  52 */       }, { Integer.valueOf(233), "Egrave"
/*  53 */       }, { Integer.valueOf(70), "F"
/*  54 */       }, { Integer.valueOf(71), "G"
/*  55 */       }, { Integer.valueOf(72), "H" }, 
/*  56 */       { Integer.valueOf(73), "I"
/*  57 */       }, { Integer.valueOf(234), "Iacute"
/*  58 */       }, { Integer.valueOf(235), "Icircumflex"
/*  59 */       }, { Integer.valueOf(236), "Idieresis"
/*  60 */       }, { Integer.valueOf(237), "Igrave"
/*  61 */       }, { Integer.valueOf(74), "J"
/*  62 */       }, { Integer.valueOf(75), "K"
/*  63 */       }, { Integer.valueOf(76), "L"
/*  64 */       }, { Integer.valueOf(77), "M"
/*  65 */       }, { Integer.valueOf(78), "N" }, 
/*  66 */       { Integer.valueOf(132), "Ntilde"
/*  67 */       }, { Integer.valueOf(79), "O"
/*  68 */       }, { Integer.valueOf(206), "OE"
/*  69 */       }, { Integer.valueOf(238), "Oacute"
/*  70 */       }, { Integer.valueOf(239), "Ocircumflex"
/*  71 */       }, { Integer.valueOf(133), "Odieresis"
/*  72 */       }, { Integer.valueOf(241), "Ograve"
/*  73 */       }, { Integer.valueOf(175), "Oslash"
/*  74 */       }, { Integer.valueOf(205), "Otilde"
/*  75 */       }, { Integer.valueOf(80), "P" }, 
/*  76 */       { Integer.valueOf(81), "Q"
/*  77 */       }, { Integer.valueOf(82), "R"
/*  78 */       }, { Integer.valueOf(83), "S"
/*  79 */       }, { Integer.valueOf(84), "T"
/*  80 */       }, { Integer.valueOf(85), "U"
/*  81 */       }, { Integer.valueOf(242), "Uacute"
/*  82 */       }, { Integer.valueOf(243), "Ucircumflex"
/*  83 */       }, { Integer.valueOf(134), "Udieresis"
/*  84 */       }, { Integer.valueOf(244), "Ugrave"
/*  85 */       }, { Integer.valueOf(86), "V" }, 
/*  86 */       { Integer.valueOf(87), "W"
/*  87 */       }, { Integer.valueOf(88), "X"
/*  88 */       }, { Integer.valueOf(89), "Y"
/*  89 */       }, { Integer.valueOf(217), "Ydieresis"
/*  90 */       }, { Integer.valueOf(90), "Z"
/*  91 */       }, { Integer.valueOf(97), "a"
/*  92 */       }, { Integer.valueOf(135), "aacute"
/*  93 */       }, { Integer.valueOf(137), "acircumflex"
/*  94 */       }, { Integer.valueOf(171), "acute"
/*  95 */       }, { Integer.valueOf(138), "adieresis" }, 
/*  96 */       { Integer.valueOf(190), "ae"
/*  97 */       }, { Integer.valueOf(136), "agrave"
/*  98 */       }, { Integer.valueOf(38), "ampersand"
/*  99 */       }, { Integer.valueOf(140), "aring"
/* 100 */       }, { Integer.valueOf(94), "asciicircum"
/* 101 */       }, { Integer.valueOf(126), "asciitilde"
/* 102 */       }, { Integer.valueOf(42), "asterisk"
/* 103 */       }, { Integer.valueOf(64), "at"
/* 104 */       }, { Integer.valueOf(139), "atilde"
/* 105 */       }, { Integer.valueOf(98), "b" }, 
/* 106 */       { Integer.valueOf(92), "backslash"
/* 107 */       }, { Integer.valueOf(124), "bar"
/* 108 */       }, { Integer.valueOf(123), "braceleft"
/* 109 */       }, { Integer.valueOf(125), "braceright"
/* 110 */       }, { Integer.valueOf(91), "bracketleft"
/* 111 */       }, { Integer.valueOf(93), "bracketright"
/* 112 */       }, { Integer.valueOf(249), "breve"
/* 113 */       }, { Integer.valueOf(165), "bullet"
/* 114 */       }, { Integer.valueOf(99), "c"
/* 115 */       }, { Integer.valueOf(255), "caron" }, 
/* 116 */       { Integer.valueOf(141), "ccedilla"
/* 117 */       }, { Integer.valueOf(252), "cedilla"
/* 118 */       }, { Integer.valueOf(162), "cent"
/* 119 */       }, { Integer.valueOf(246), "circumflex"
/* 120 */       }, { Integer.valueOf(58), "colon"
/* 121 */       }, { Integer.valueOf(44), "comma"
/* 122 */       }, { Integer.valueOf(169), "copyright"
/* 123 */       }, { Integer.valueOf(219), "currency"
/* 124 */       }, { Integer.valueOf(100), "d"
/* 125 */       }, { Integer.valueOf(160), "dagger" }, 
/* 126 */       { Integer.valueOf(224), "daggerdbl"
/* 127 */       }, { Integer.valueOf(161), "degree"
/* 128 */       }, { Integer.valueOf(172), "dieresis"
/* 129 */       }, { Integer.valueOf(214), "divide"
/* 130 */       }, { Integer.valueOf(36), "dollar"
/* 131 */       }, { Integer.valueOf(250), "dotaccent"
/* 132 */       }, { Integer.valueOf(245), "dotlessi"
/* 133 */       }, { Integer.valueOf(101), "e"
/* 134 */       }, { Integer.valueOf(142), "eacute"
/* 135 */       }, { Integer.valueOf(144), "ecircumflex" }, 
/* 136 */       { Integer.valueOf(145), "edieresis"
/* 137 */       }, { Integer.valueOf(143), "egrave"
/* 138 */       }, { Integer.valueOf(56), "eight"
/* 139 */       }, { Integer.valueOf(201), "ellipsis"
/* 140 */       }, { Integer.valueOf(209), "emdash"
/* 141 */       }, { Integer.valueOf(208), "endash"
/* 142 */       }, { Integer.valueOf(61), "equal"
/* 143 */       }, { Integer.valueOf(33), "exclam"
/* 144 */       }, { Integer.valueOf(193), "exclamdown"
/* 145 */       }, { Integer.valueOf(102), "f" }, 
/* 146 */       { Integer.valueOf(222), "fi"
/* 147 */       }, { Integer.valueOf(53), "five"
/* 148 */       }, { Integer.valueOf(223), "fl"
/* 149 */       }, { Integer.valueOf(196), "florin"
/* 150 */       }, { Integer.valueOf(52), "four"
/* 151 */       }, { Integer.valueOf(218), "fraction"
/* 152 */       }, { Integer.valueOf(103), "g"
/* 153 */       }, { Integer.valueOf(167), "germandbls"
/* 154 */       }, { Integer.valueOf(96), "grave"
/* 155 */       }, { Integer.valueOf(62), "greater" }, 
/* 156 */       { Integer.valueOf(199), "guillemotleft"
/* 157 */       }, { Integer.valueOf(200), "guillemotright"
/* 158 */       }, { Integer.valueOf(220), "guilsinglleft"
/* 159 */       }, { Integer.valueOf(221), "guilsinglright"
/* 160 */       }, { Integer.valueOf(104), "h"
/* 161 */       }, { Integer.valueOf(253), "hungarumlaut"
/* 162 */       }, { Integer.valueOf(45), "hyphen"
/* 163 */       }, { Integer.valueOf(105), "i"
/* 164 */       }, { Integer.valueOf(146), "iacute"
/* 165 */       }, { Integer.valueOf(148), "icircumflex" }, 
/* 166 */       { Integer.valueOf(149), "idieresis"
/* 167 */       }, { Integer.valueOf(147), "igrave"
/* 168 */       }, { Integer.valueOf(106), "j"
/* 169 */       }, { Integer.valueOf(107), "k"
/* 170 */       }, { Integer.valueOf(108), "l"
/* 171 */       }, { Integer.valueOf(60), "less"
/* 172 */       }, { Integer.valueOf(194), "logicalnot"
/* 173 */       }, { Integer.valueOf(109), "m"
/* 174 */       }, { Integer.valueOf(248), "macron"
/* 175 */       }, { Integer.valueOf(181), "mu" }, 
/* 176 */       { Integer.valueOf(110), "n"
/* 177 */       }, { Integer.valueOf(57), "nine"
/* 178 */       }, { Integer.valueOf(150), "ntilde"
/* 179 */       }, { Integer.valueOf(35), "numbersign"
/* 180 */       }, { Integer.valueOf(111), "o"
/* 181 */       }, { Integer.valueOf(151), "oacute"
/* 182 */       }, { Integer.valueOf(153), "ocircumflex"
/* 183 */       }, { Integer.valueOf(154), "odieresis"
/* 184 */       }, { Integer.valueOf(207), "oe"
/* 185 */       }, { Integer.valueOf(254), "ogonek" }, 
/* 186 */       { Integer.valueOf(152), "ograve"
/* 187 */       }, { Integer.valueOf(49), "one"
/* 188 */       }, { Integer.valueOf(187), "ordfeminine"
/* 189 */       }, { Integer.valueOf(188), "ordmasculine"
/* 190 */       }, { Integer.valueOf(191), "oslash"
/* 191 */       }, { Integer.valueOf(155), "otilde"
/* 192 */       }, { Integer.valueOf(112), "p"
/* 193 */       }, { Integer.valueOf(166), "paragraph"
/* 194 */       }, { Integer.valueOf(40), "parenleft"
/* 195 */       }, { Integer.valueOf(41), "parenright" }, 
/* 196 */       { Integer.valueOf(37), "percent"
/* 197 */       }, { Integer.valueOf(46), "period"
/* 198 */       }, { Integer.valueOf(225), "periodcentered"
/* 199 */       }, { Integer.valueOf(228), "perthousand"
/* 200 */       }, { Integer.valueOf(43), "plus"
/* 201 */       }, { Integer.valueOf(177), "plusminus"
/* 202 */       }, { Integer.valueOf(113), "q"
/* 203 */       }, { Integer.valueOf(63), "question"
/* 204 */       }, { Integer.valueOf(192), "questiondown"
/* 205 */       }, { Integer.valueOf(34), "quotedbl" }, 
/* 206 */       { Integer.valueOf(227), "quotedblbase"
/* 207 */       }, { Integer.valueOf(210), "quotedblleft"
/* 208 */       }, { Integer.valueOf(211), "quotedblright"
/* 209 */       }, { Integer.valueOf(212), "quoteleft"
/* 210 */       }, { Integer.valueOf(213), "quoteright"
/* 211 */       }, { Integer.valueOf(226), "quotesinglbase"
/* 212 */       }, { Integer.valueOf(39), "quotesingle"
/* 213 */       }, { Integer.valueOf(114), "r"
/* 214 */       }, { Integer.valueOf(168), "registered"
/* 215 */       }, { Integer.valueOf(251), "ring" }, 
/* 216 */       { Integer.valueOf(115), "s"
/* 217 */       }, { Integer.valueOf(164), "section"
/* 218 */       }, { Integer.valueOf(59), "semicolon"
/* 219 */       }, { Integer.valueOf(55), "seven"
/* 220 */       }, { Integer.valueOf(54), "six"
/* 221 */       }, { Integer.valueOf(47), "slash"
/* 222 */       }, { Integer.valueOf(32), "space"
/* 223 */       }, { Integer.valueOf(163), "sterling"
/* 224 */       }, { Integer.valueOf(116), "t"
/* 225 */       }, { Integer.valueOf(51), "three" }, 
/* 226 */       { Integer.valueOf(247), "tilde"
/* 227 */       }, { Integer.valueOf(170), "trademark"
/* 228 */       }, { Integer.valueOf(50), "two"
/* 229 */       }, { Integer.valueOf(117), "u"
/* 230 */       }, { Integer.valueOf(156), "uacute"
/* 231 */       }, { Integer.valueOf(158), "ucircumflex"
/* 232 */       }, { Integer.valueOf(159), "udieresis"
/* 233 */       }, { Integer.valueOf(157), "ugrave"
/* 234 */       }, { Integer.valueOf(95), "underscore"
/* 235 */       }, { Integer.valueOf(118), "v" }, 
/* 236 */       { Integer.valueOf(119), "w"
/* 237 */       }, { Integer.valueOf(120), "x"
/* 238 */       }, { Integer.valueOf(121), "y"
/* 239 */       }, { Integer.valueOf(216), "ydieresis"
/* 240 */       }, { Integer.valueOf(180), "yen"
/* 241 */       }, { Integer.valueOf(122), "z"
/* 242 */       }, { Integer.valueOf(48), "zero" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 250 */   public static final MacRomanEncoding INSTANCE = new MacRomanEncoding();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MacRomanEncoding() {
/* 257 */     for (Object[] encodingEntry : MAC_ROMAN_ENCODING_TABLE)
/*     */     {
/* 259 */       addCharacterEncoding(((Integer)encodingEntry[0]).intValue(), encodingEntry[1].toString());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/encoding/MacRomanEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */