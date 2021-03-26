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
/*     */ public class StandardEncoding
/*     */   extends Encoding
/*     */ {
/*     */   private static final int CHAR_CODE = 0;
/*     */   private static final int CHAR_NAME = 1;
/*  34 */   private static final Object[][] STANDARD_ENCODING_TABLE = new Object[][] { 
/*  35 */       { Integer.valueOf(65), "A"
/*  36 */       }, { Integer.valueOf(225), "AE"
/*  37 */       }, { Integer.valueOf(66), "B"
/*  38 */       }, { Integer.valueOf(67), "C"
/*  39 */       }, { Integer.valueOf(68), "D"
/*  40 */       }, { Integer.valueOf(69), "E"
/*  41 */       }, { Integer.valueOf(70), "F"
/*  42 */       }, { Integer.valueOf(71), "G"
/*  43 */       }, { Integer.valueOf(72), "H"
/*  44 */       }, { Integer.valueOf(73), "I" }, 
/*  45 */       { Integer.valueOf(74), "J"
/*  46 */       }, { Integer.valueOf(75), "K"
/*  47 */       }, { Integer.valueOf(76), "L"
/*  48 */       }, { Integer.valueOf(232), "Lslash"
/*  49 */       }, { Integer.valueOf(77), "M"
/*  50 */       }, { Integer.valueOf(78), "N"
/*  51 */       }, { Integer.valueOf(79), "O"
/*  52 */       }, { Integer.valueOf(234), "OE"
/*  53 */       }, { Integer.valueOf(233), "Oslash"
/*  54 */       }, { Integer.valueOf(80), "P" }, 
/*  55 */       { Integer.valueOf(81), "Q"
/*  56 */       }, { Integer.valueOf(82), "R"
/*  57 */       }, { Integer.valueOf(83), "S"
/*  58 */       }, { Integer.valueOf(84), "T"
/*  59 */       }, { Integer.valueOf(85), "U"
/*  60 */       }, { Integer.valueOf(86), "V"
/*  61 */       }, { Integer.valueOf(87), "W"
/*  62 */       }, { Integer.valueOf(88), "X"
/*  63 */       }, { Integer.valueOf(89), "Y"
/*  64 */       }, { Integer.valueOf(90), "Z" }, 
/*  65 */       { Integer.valueOf(97), "a"
/*  66 */       }, { Integer.valueOf(194), "acute"
/*  67 */       }, { Integer.valueOf(241), "ae"
/*  68 */       }, { Integer.valueOf(38), "ampersand"
/*  69 */       }, { Integer.valueOf(94), "asciicircum"
/*  70 */       }, { Integer.valueOf(126), "asciitilde"
/*  71 */       }, { Integer.valueOf(42), "asterisk"
/*  72 */       }, { Integer.valueOf(64), "at"
/*  73 */       }, { Integer.valueOf(98), "b"
/*  74 */       }, { Integer.valueOf(92), "backslash" }, 
/*  75 */       { Integer.valueOf(124), "bar"
/*  76 */       }, { Integer.valueOf(123), "braceleft"
/*  77 */       }, { Integer.valueOf(125), "braceright"
/*  78 */       }, { Integer.valueOf(91), "bracketleft"
/*  79 */       }, { Integer.valueOf(93), "bracketright"
/*  80 */       }, { Integer.valueOf(198), "breve"
/*  81 */       }, { Integer.valueOf(183), "bullet"
/*  82 */       }, { Integer.valueOf(99), "c"
/*  83 */       }, { Integer.valueOf(207), "caron"
/*  84 */       }, { Integer.valueOf(203), "cedilla" }, 
/*  85 */       { Integer.valueOf(162), "cent"
/*  86 */       }, { Integer.valueOf(195), "circumflex"
/*  87 */       }, { Integer.valueOf(58), "colon"
/*  88 */       }, { Integer.valueOf(44), "comma"
/*  89 */       }, { Integer.valueOf(168), "currency"
/*  90 */       }, { Integer.valueOf(100), "d"
/*  91 */       }, { Integer.valueOf(178), "dagger"
/*  92 */       }, { Integer.valueOf(179), "daggerdbl"
/*  93 */       }, { Integer.valueOf(200), "dieresis"
/*  94 */       }, { Integer.valueOf(36), "dollar" }, 
/*  95 */       { Integer.valueOf(199), "dotaccent"
/*  96 */       }, { Integer.valueOf(245), "dotlessi"
/*  97 */       }, { Integer.valueOf(101), "e"
/*  98 */       }, { Integer.valueOf(56), "eight"
/*  99 */       }, { Integer.valueOf(188), "ellipsis"
/* 100 */       }, { Integer.valueOf(208), "emdash"
/* 101 */       }, { Integer.valueOf(177), "endash"
/* 102 */       }, { Integer.valueOf(61), "equal"
/* 103 */       }, { Integer.valueOf(33), "exclam"
/* 104 */       }, { Integer.valueOf(161), "exclamdown" }, 
/* 105 */       { Integer.valueOf(102), "f"
/* 106 */       }, { Integer.valueOf(174), "fi"
/* 107 */       }, { Integer.valueOf(53), "five"
/* 108 */       }, { Integer.valueOf(175), "fl"
/* 109 */       }, { Integer.valueOf(166), "florin"
/* 110 */       }, { Integer.valueOf(52), "four"
/* 111 */       }, { Integer.valueOf(164), "fraction"
/* 112 */       }, { Integer.valueOf(103), "g"
/* 113 */       }, { Integer.valueOf(251), "germandbls"
/* 114 */       }, { Integer.valueOf(193), "grave" }, 
/* 115 */       { Integer.valueOf(62), "greater"
/* 116 */       }, { Integer.valueOf(171), "guillemotleft"
/* 117 */       }, { Integer.valueOf(187), "guillemotright"
/* 118 */       }, { Integer.valueOf(172), "guilsinglleft"
/* 119 */       }, { Integer.valueOf(173), "guilsinglright"
/* 120 */       }, { Integer.valueOf(104), "h"
/* 121 */       }, { Integer.valueOf(205), "hungarumlaut"
/* 122 */       }, { Integer.valueOf(45), "hyphen"
/* 123 */       }, { Integer.valueOf(105), "i"
/* 124 */       }, { Integer.valueOf(106), "j" }, 
/* 125 */       { Integer.valueOf(107), "k"
/* 126 */       }, { Integer.valueOf(108), "l"
/* 127 */       }, { Integer.valueOf(60), "less"
/* 128 */       }, { Integer.valueOf(248), "lslash"
/* 129 */       }, { Integer.valueOf(109), "m"
/* 130 */       }, { Integer.valueOf(197), "macron"
/* 131 */       }, { Integer.valueOf(110), "n"
/* 132 */       }, { Integer.valueOf(57), "nine"
/* 133 */       }, { Integer.valueOf(35), "numbersign"
/* 134 */       }, { Integer.valueOf(111), "o" }, 
/* 135 */       { Integer.valueOf(250), "oe"
/* 136 */       }, { Integer.valueOf(206), "ogonek"
/* 137 */       }, { Integer.valueOf(49), "one"
/* 138 */       }, { Integer.valueOf(227), "ordfeminine"
/* 139 */       }, { Integer.valueOf(235), "ordmasculine"
/* 140 */       }, { Integer.valueOf(249), "oslash"
/* 141 */       }, { Integer.valueOf(112), "p"
/* 142 */       }, { Integer.valueOf(182), "paragraph"
/* 143 */       }, { Integer.valueOf(40), "parenleft"
/* 144 */       }, { Integer.valueOf(41), "parenright" }, 
/* 145 */       { Integer.valueOf(37), "percent"
/* 146 */       }, { Integer.valueOf(46), "period"
/* 147 */       }, { Integer.valueOf(180), "periodcentered"
/* 148 */       }, { Integer.valueOf(189), "perthousand"
/* 149 */       }, { Integer.valueOf(43), "plus"
/* 150 */       }, { Integer.valueOf(113), "q"
/* 151 */       }, { Integer.valueOf(63), "question"
/* 152 */       }, { Integer.valueOf(191), "questiondown"
/* 153 */       }, { Integer.valueOf(34), "quotedbl"
/* 154 */       }, { Integer.valueOf(185), "quotedblbase" }, 
/* 155 */       { Integer.valueOf(170), "quotedblleft"
/* 156 */       }, { Integer.valueOf(186), "quotedblright"
/* 157 */       }, { Integer.valueOf(96), "quoteleft"
/* 158 */       }, { Integer.valueOf(39), "quoteright"
/* 159 */       }, { Integer.valueOf(184), "quotesinglbase"
/* 160 */       }, { Integer.valueOf(169), "quotesingle"
/* 161 */       }, { Integer.valueOf(114), "r"
/* 162 */       }, { Integer.valueOf(202), "ring"
/* 163 */       }, { Integer.valueOf(115), "s"
/* 164 */       }, { Integer.valueOf(167), "section" }, 
/* 165 */       { Integer.valueOf(59), "semicolon"
/* 166 */       }, { Integer.valueOf(55), "seven"
/* 167 */       }, { Integer.valueOf(54), "six"
/* 168 */       }, { Integer.valueOf(47), "slash"
/* 169 */       }, { Integer.valueOf(32), "space"
/* 170 */       }, { Integer.valueOf(163), "sterling"
/* 171 */       }, { Integer.valueOf(116), "t"
/* 172 */       }, { Integer.valueOf(51), "three"
/* 173 */       }, { Integer.valueOf(196), "tilde"
/* 174 */       }, { Integer.valueOf(50), "two" }, 
/* 175 */       { Integer.valueOf(117), "u"
/* 176 */       }, { Integer.valueOf(95), "underscore"
/* 177 */       }, { Integer.valueOf(118), "v"
/* 178 */       }, { Integer.valueOf(119), "w"
/* 179 */       }, { Integer.valueOf(120), "x"
/* 180 */       }, { Integer.valueOf(121), "y"
/* 181 */       }, { Integer.valueOf(165), "yen"
/* 182 */       }, { Integer.valueOf(122), "z"
/* 183 */       }, { Integer.valueOf(48), "zero" } };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public static final StandardEncoding INSTANCE = new StandardEncoding();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StandardEncoding() {
/* 195 */     for (Object[] encodingEntry : STANDARD_ENCODING_TABLE)
/*     */     {
/* 197 */       addCharacterEncoding(((Integer)encodingEntry[0]).intValue(), encodingEntry[1].toString());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/encoding/StandardEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */