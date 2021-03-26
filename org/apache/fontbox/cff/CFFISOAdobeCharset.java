/*     */ package org.apache.fontbox.cff;
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
/*     */ public final class CFFISOAdobeCharset
/*     */   extends CFFCharset
/*     */ {
/*     */   private static final int CHAR_CODE = 0;
/*     */   private static final int CHAR_NAME = 1;
/*  33 */   private static final Object[][] CFF_ISO_ADOBE_CHARSET_TABLE = new Object[][] { 
/*  34 */       { Integer.valueOf(0), ".notdef"
/*  35 */       }, { Integer.valueOf(1), "space"
/*  36 */       }, { Integer.valueOf(2), "exclam"
/*  37 */       }, { Integer.valueOf(3), "quotedbl"
/*  38 */       }, { Integer.valueOf(4), "numbersign"
/*  39 */       }, { Integer.valueOf(5), "dollar"
/*  40 */       }, { Integer.valueOf(6), "percent"
/*  41 */       }, { Integer.valueOf(7), "ampersand"
/*  42 */       }, { Integer.valueOf(8), "quoteright"
/*  43 */       }, { Integer.valueOf(9), "parenleft" }, 
/*  44 */       { Integer.valueOf(10), "parenright"
/*  45 */       }, { Integer.valueOf(11), "asterisk"
/*  46 */       }, { Integer.valueOf(12), "plus"
/*  47 */       }, { Integer.valueOf(13), "comma"
/*  48 */       }, { Integer.valueOf(14), "hyphen"
/*  49 */       }, { Integer.valueOf(15), "period"
/*  50 */       }, { Integer.valueOf(16), "slash"
/*  51 */       }, { Integer.valueOf(17), "zero"
/*  52 */       }, { Integer.valueOf(18), "one"
/*  53 */       }, { Integer.valueOf(19), "two" }, 
/*  54 */       { Integer.valueOf(20), "three"
/*  55 */       }, { Integer.valueOf(21), "four"
/*  56 */       }, { Integer.valueOf(22), "five"
/*  57 */       }, { Integer.valueOf(23), "six"
/*  58 */       }, { Integer.valueOf(24), "seven"
/*  59 */       }, { Integer.valueOf(25), "eight"
/*  60 */       }, { Integer.valueOf(26), "nine"
/*  61 */       }, { Integer.valueOf(27), "colon"
/*  62 */       }, { Integer.valueOf(28), "semicolon"
/*  63 */       }, { Integer.valueOf(29), "less" }, 
/*  64 */       { Integer.valueOf(30), "equal"
/*  65 */       }, { Integer.valueOf(31), "greater"
/*  66 */       }, { Integer.valueOf(32), "question"
/*  67 */       }, { Integer.valueOf(33), "at"
/*  68 */       }, { Integer.valueOf(34), "A"
/*  69 */       }, { Integer.valueOf(35), "B"
/*  70 */       }, { Integer.valueOf(36), "C"
/*  71 */       }, { Integer.valueOf(37), "D"
/*  72 */       }, { Integer.valueOf(38), "E"
/*  73 */       }, { Integer.valueOf(39), "F" }, 
/*  74 */       { Integer.valueOf(40), "G"
/*  75 */       }, { Integer.valueOf(41), "H"
/*  76 */       }, { Integer.valueOf(42), "I"
/*  77 */       }, { Integer.valueOf(43), "J"
/*  78 */       }, { Integer.valueOf(44), "K"
/*  79 */       }, { Integer.valueOf(45), "L"
/*  80 */       }, { Integer.valueOf(46), "M"
/*  81 */       }, { Integer.valueOf(47), "N"
/*  82 */       }, { Integer.valueOf(48), "O"
/*  83 */       }, { Integer.valueOf(49), "P" }, 
/*  84 */       { Integer.valueOf(50), "Q"
/*  85 */       }, { Integer.valueOf(51), "R"
/*  86 */       }, { Integer.valueOf(52), "S"
/*  87 */       }, { Integer.valueOf(53), "T"
/*  88 */       }, { Integer.valueOf(54), "U"
/*  89 */       }, { Integer.valueOf(55), "V"
/*  90 */       }, { Integer.valueOf(56), "W"
/*  91 */       }, { Integer.valueOf(57), "X"
/*  92 */       }, { Integer.valueOf(58), "Y"
/*  93 */       }, { Integer.valueOf(59), "Z" }, 
/*  94 */       { Integer.valueOf(60), "bracketleft"
/*  95 */       }, { Integer.valueOf(61), "backslash"
/*  96 */       }, { Integer.valueOf(62), "bracketright"
/*  97 */       }, { Integer.valueOf(63), "asciicircum"
/*  98 */       }, { Integer.valueOf(64), "underscore"
/*  99 */       }, { Integer.valueOf(65), "quoteleft"
/* 100 */       }, { Integer.valueOf(66), "a"
/* 101 */       }, { Integer.valueOf(67), "b"
/* 102 */       }, { Integer.valueOf(68), "c"
/* 103 */       }, { Integer.valueOf(69), "d" }, 
/* 104 */       { Integer.valueOf(70), "e"
/* 105 */       }, { Integer.valueOf(71), "f"
/* 106 */       }, { Integer.valueOf(72), "g"
/* 107 */       }, { Integer.valueOf(73), "h"
/* 108 */       }, { Integer.valueOf(74), "i"
/* 109 */       }, { Integer.valueOf(75), "j"
/* 110 */       }, { Integer.valueOf(76), "k"
/* 111 */       }, { Integer.valueOf(77), "l"
/* 112 */       }, { Integer.valueOf(78), "m"
/* 113 */       }, { Integer.valueOf(79), "n" }, 
/* 114 */       { Integer.valueOf(80), "o"
/* 115 */       }, { Integer.valueOf(81), "p"
/* 116 */       }, { Integer.valueOf(82), "q"
/* 117 */       }, { Integer.valueOf(83), "r"
/* 118 */       }, { Integer.valueOf(84), "s"
/* 119 */       }, { Integer.valueOf(85), "t"
/* 120 */       }, { Integer.valueOf(86), "u"
/* 121 */       }, { Integer.valueOf(87), "v"
/* 122 */       }, { Integer.valueOf(88), "w"
/* 123 */       }, { Integer.valueOf(89), "x" }, 
/* 124 */       { Integer.valueOf(90), "y"
/* 125 */       }, { Integer.valueOf(91), "z"
/* 126 */       }, { Integer.valueOf(92), "braceleft"
/* 127 */       }, { Integer.valueOf(93), "bar"
/* 128 */       }, { Integer.valueOf(94), "braceright"
/* 129 */       }, { Integer.valueOf(95), "asciitilde"
/* 130 */       }, { Integer.valueOf(96), "exclamdown"
/* 131 */       }, { Integer.valueOf(97), "cent"
/* 132 */       }, { Integer.valueOf(98), "sterling"
/* 133 */       }, { Integer.valueOf(99), "fraction" }, 
/* 134 */       { Integer.valueOf(100), "yen"
/* 135 */       }, { Integer.valueOf(101), "florin"
/* 136 */       }, { Integer.valueOf(102), "section"
/* 137 */       }, { Integer.valueOf(103), "currency"
/* 138 */       }, { Integer.valueOf(104), "quotesingle"
/* 139 */       }, { Integer.valueOf(105), "quotedblleft"
/* 140 */       }, { Integer.valueOf(106), "guillemotleft"
/* 141 */       }, { Integer.valueOf(107), "guilsinglleft"
/* 142 */       }, { Integer.valueOf(108), "guilsinglright"
/* 143 */       }, { Integer.valueOf(109), "fi" }, 
/* 144 */       { Integer.valueOf(110), "fl"
/* 145 */       }, { Integer.valueOf(111), "endash"
/* 146 */       }, { Integer.valueOf(112), "dagger"
/* 147 */       }, { Integer.valueOf(113), "daggerdbl"
/* 148 */       }, { Integer.valueOf(114), "periodcentered"
/* 149 */       }, { Integer.valueOf(115), "paragraph"
/* 150 */       }, { Integer.valueOf(116), "bullet"
/* 151 */       }, { Integer.valueOf(117), "quotesinglbase"
/* 152 */       }, { Integer.valueOf(118), "quotedblbase"
/* 153 */       }, { Integer.valueOf(119), "quotedblright" }, 
/* 154 */       { Integer.valueOf(120), "guillemotright"
/* 155 */       }, { Integer.valueOf(121), "ellipsis"
/* 156 */       }, { Integer.valueOf(122), "perthousand"
/* 157 */       }, { Integer.valueOf(123), "questiondown"
/* 158 */       }, { Integer.valueOf(124), "grave"
/* 159 */       }, { Integer.valueOf(125), "acute"
/* 160 */       }, { Integer.valueOf(126), "circumflex"
/* 161 */       }, { Integer.valueOf(127), "tilde"
/* 162 */       }, { Integer.valueOf(128), "macron"
/* 163 */       }, { Integer.valueOf(129), "breve" }, 
/* 164 */       { Integer.valueOf(130), "dotaccent"
/* 165 */       }, { Integer.valueOf(131), "dieresis"
/* 166 */       }, { Integer.valueOf(132), "ring"
/* 167 */       }, { Integer.valueOf(133), "cedilla"
/* 168 */       }, { Integer.valueOf(134), "hungarumlaut"
/* 169 */       }, { Integer.valueOf(135), "ogonek"
/* 170 */       }, { Integer.valueOf(136), "caron"
/* 171 */       }, { Integer.valueOf(137), "emdash"
/* 172 */       }, { Integer.valueOf(138), "AE"
/* 173 */       }, { Integer.valueOf(139), "ordfeminine" }, 
/* 174 */       { Integer.valueOf(140), "Lslash"
/* 175 */       }, { Integer.valueOf(141), "Oslash"
/* 176 */       }, { Integer.valueOf(142), "OE"
/* 177 */       }, { Integer.valueOf(143), "ordmasculine"
/* 178 */       }, { Integer.valueOf(144), "ae"
/* 179 */       }, { Integer.valueOf(145), "dotlessi"
/* 180 */       }, { Integer.valueOf(146), "lslash"
/* 181 */       }, { Integer.valueOf(147), "oslash"
/* 182 */       }, { Integer.valueOf(148), "oe"
/* 183 */       }, { Integer.valueOf(149), "germandbls" }, 
/* 184 */       { Integer.valueOf(150), "onesuperior"
/* 185 */       }, { Integer.valueOf(151), "logicalnot"
/* 186 */       }, { Integer.valueOf(152), "mu"
/* 187 */       }, { Integer.valueOf(153), "trademark"
/* 188 */       }, { Integer.valueOf(154), "Eth"
/* 189 */       }, { Integer.valueOf(155), "onehalf"
/* 190 */       }, { Integer.valueOf(156), "plusminus"
/* 191 */       }, { Integer.valueOf(157), "Thorn"
/* 192 */       }, { Integer.valueOf(158), "onequarter"
/* 193 */       }, { Integer.valueOf(159), "divide" }, 
/* 194 */       { Integer.valueOf(160), "brokenbar"
/* 195 */       }, { Integer.valueOf(161), "degree"
/* 196 */       }, { Integer.valueOf(162), "thorn"
/* 197 */       }, { Integer.valueOf(163), "threequarters"
/* 198 */       }, { Integer.valueOf(164), "twosuperior"
/* 199 */       }, { Integer.valueOf(165), "registered"
/* 200 */       }, { Integer.valueOf(166), "minus"
/* 201 */       }, { Integer.valueOf(167), "eth"
/* 202 */       }, { Integer.valueOf(168), "multiply"
/* 203 */       }, { Integer.valueOf(169), "threesuperior" }, 
/* 204 */       { Integer.valueOf(170), "copyright"
/* 205 */       }, { Integer.valueOf(171), "Aacute"
/* 206 */       }, { Integer.valueOf(172), "Acircumflex"
/* 207 */       }, { Integer.valueOf(173), "Adieresis"
/* 208 */       }, { Integer.valueOf(174), "Agrave"
/* 209 */       }, { Integer.valueOf(175), "Aring"
/* 210 */       }, { Integer.valueOf(176), "Atilde"
/* 211 */       }, { Integer.valueOf(177), "Ccedilla"
/* 212 */       }, { Integer.valueOf(178), "Eacute"
/* 213 */       }, { Integer.valueOf(179), "Ecircumflex" }, 
/* 214 */       { Integer.valueOf(180), "Edieresis"
/* 215 */       }, { Integer.valueOf(181), "Egrave"
/* 216 */       }, { Integer.valueOf(182), "Iacute"
/* 217 */       }, { Integer.valueOf(183), "Icircumflex"
/* 218 */       }, { Integer.valueOf(184), "Idieresis"
/* 219 */       }, { Integer.valueOf(185), "Igrave"
/* 220 */       }, { Integer.valueOf(186), "Ntilde"
/* 221 */       }, { Integer.valueOf(187), "Oacute"
/* 222 */       }, { Integer.valueOf(188), "Ocircumflex"
/* 223 */       }, { Integer.valueOf(189), "Odieresis" }, 
/* 224 */       { Integer.valueOf(190), "Ograve"
/* 225 */       }, { Integer.valueOf(191), "Otilde"
/* 226 */       }, { Integer.valueOf(192), "Scaron"
/* 227 */       }, { Integer.valueOf(193), "Uacute"
/* 228 */       }, { Integer.valueOf(194), "Ucircumflex"
/* 229 */       }, { Integer.valueOf(195), "Udieresis"
/* 230 */       }, { Integer.valueOf(196), "Ugrave"
/* 231 */       }, { Integer.valueOf(197), "Yacute"
/* 232 */       }, { Integer.valueOf(198), "Ydieresis"
/* 233 */       }, { Integer.valueOf(199), "Zcaron" }, 
/* 234 */       { Integer.valueOf(200), "aacute"
/* 235 */       }, { Integer.valueOf(201), "acircumflex"
/* 236 */       }, { Integer.valueOf(202), "adieresis"
/* 237 */       }, { Integer.valueOf(203), "agrave"
/* 238 */       }, { Integer.valueOf(204), "aring"
/* 239 */       }, { Integer.valueOf(205), "atilde"
/* 240 */       }, { Integer.valueOf(206), "ccedilla"
/* 241 */       }, { Integer.valueOf(207), "eacute"
/* 242 */       }, { Integer.valueOf(208), "ecircumflex"
/* 243 */       }, { Integer.valueOf(209), "edieresis" }, 
/* 244 */       { Integer.valueOf(210), "egrave"
/* 245 */       }, { Integer.valueOf(211), "iacute"
/* 246 */       }, { Integer.valueOf(212), "icircumflex"
/* 247 */       }, { Integer.valueOf(213), "idieresis"
/* 248 */       }, { Integer.valueOf(214), "igrave"
/* 249 */       }, { Integer.valueOf(215), "ntilde"
/* 250 */       }, { Integer.valueOf(216), "oacute"
/* 251 */       }, { Integer.valueOf(217), "ocircumflex"
/* 252 */       }, { Integer.valueOf(218), "odieresis"
/* 253 */       }, { Integer.valueOf(219), "ograve" }, 
/* 254 */       { Integer.valueOf(220), "otilde"
/* 255 */       }, { Integer.valueOf(221), "scaron"
/* 256 */       }, { Integer.valueOf(222), "uacute"
/* 257 */       }, { Integer.valueOf(223), "ucircumflex"
/* 258 */       }, { Integer.valueOf(224), "udieresis"
/* 259 */       }, { Integer.valueOf(225), "ugrave"
/* 260 */       }, { Integer.valueOf(226), "yacute"
/* 261 */       }, { Integer.valueOf(227), "ydieresis"
/* 262 */       }, { Integer.valueOf(228), "zcaron" } };
/*     */ 
/*     */ 
/*     */   
/*     */   private CFFISOAdobeCharset() {
/* 267 */     super(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CFFISOAdobeCharset getInstance() {
/* 276 */     return INSTANCE;
/*     */   }
/*     */   
/* 279 */   private static final CFFISOAdobeCharset INSTANCE = new CFFISOAdobeCharset();
/*     */ 
/*     */   
/*     */   static {
/* 283 */     int gid = 0;
/* 284 */     for (Object[] charsetEntry : CFF_ISO_ADOBE_CHARSET_TABLE)
/*     */     {
/* 286 */       INSTANCE.addSID(gid++, ((Integer)charsetEntry[0]).intValue(), charsetEntry[1].toString());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/CFFISOAdobeCharset.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */