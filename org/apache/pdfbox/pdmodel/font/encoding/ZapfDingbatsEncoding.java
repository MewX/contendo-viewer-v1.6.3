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
/*     */ public class ZapfDingbatsEncoding
/*     */   extends Encoding
/*     */ {
/*     */   private static final int CHAR_CODE = 0;
/*     */   private static final int CHAR_NAME = 1;
/*  34 */   private static final Object[][] ZAPFDINGBATS_ENCODING_TABLE = new Object[][] { 
/*  35 */       { Integer.valueOf(32), "space"
/*  36 */       }, { Integer.valueOf(33), "a1"
/*  37 */       }, { Integer.valueOf(34), "a2"
/*  38 */       }, { Integer.valueOf(35), "a202"
/*  39 */       }, { Integer.valueOf(36), "a3"
/*  40 */       }, { Integer.valueOf(37), "a4"
/*  41 */       }, { Integer.valueOf(38), "a5"
/*  42 */       }, { Integer.valueOf(39), "a119"
/*  43 */       }, { Integer.valueOf(40), "a118"
/*  44 */       }, { Integer.valueOf(41), "a117" }, 
/*  45 */       { Integer.valueOf(42), "a11"
/*  46 */       }, { Integer.valueOf(43), "a12"
/*  47 */       }, { Integer.valueOf(44), "a13"
/*  48 */       }, { Integer.valueOf(45), "a14"
/*  49 */       }, { Integer.valueOf(46), "a15"
/*  50 */       }, { Integer.valueOf(47), "a16"
/*  51 */       }, { Integer.valueOf(48), "a105"
/*  52 */       }, { Integer.valueOf(49), "a17"
/*  53 */       }, { Integer.valueOf(50), "a18"
/*  54 */       }, { Integer.valueOf(51), "a19" }, 
/*  55 */       { Integer.valueOf(52), "a20"
/*  56 */       }, { Integer.valueOf(53), "a21"
/*  57 */       }, { Integer.valueOf(54), "a22"
/*  58 */       }, { Integer.valueOf(55), "a23"
/*  59 */       }, { Integer.valueOf(56), "a24"
/*  60 */       }, { Integer.valueOf(57), "a25"
/*  61 */       }, { Integer.valueOf(58), "a26"
/*  62 */       }, { Integer.valueOf(59), "a27"
/*  63 */       }, { Integer.valueOf(60), "a28"
/*  64 */       }, { Integer.valueOf(61), "a6" }, 
/*  65 */       { Integer.valueOf(62), "a7"
/*  66 */       }, { Integer.valueOf(63), "a8"
/*  67 */       }, { Integer.valueOf(64), "a9"
/*  68 */       }, { Integer.valueOf(65), "a10"
/*  69 */       }, { Integer.valueOf(66), "a29"
/*  70 */       }, { Integer.valueOf(67), "a30"
/*  71 */       }, { Integer.valueOf(68), "a31"
/*  72 */       }, { Integer.valueOf(69), "a32"
/*  73 */       }, { Integer.valueOf(70), "a33"
/*  74 */       }, { Integer.valueOf(71), "a34" }, 
/*  75 */       { Integer.valueOf(72), "a35"
/*  76 */       }, { Integer.valueOf(73), "a36"
/*  77 */       }, { Integer.valueOf(74), "a37"
/*  78 */       }, { Integer.valueOf(75), "a38"
/*  79 */       }, { Integer.valueOf(76), "a39"
/*  80 */       }, { Integer.valueOf(77), "a40"
/*  81 */       }, { Integer.valueOf(78), "a41"
/*  82 */       }, { Integer.valueOf(79), "a42"
/*  83 */       }, { Integer.valueOf(80), "a43"
/*  84 */       }, { Integer.valueOf(81), "a44" }, 
/*  85 */       { Integer.valueOf(82), "a45"
/*  86 */       }, { Integer.valueOf(83), "a46"
/*  87 */       }, { Integer.valueOf(84), "a47"
/*  88 */       }, { Integer.valueOf(85), "a48"
/*  89 */       }, { Integer.valueOf(86), "a49"
/*  90 */       }, { Integer.valueOf(87), "a50"
/*  91 */       }, { Integer.valueOf(88), "a51"
/*  92 */       }, { Integer.valueOf(89), "a52"
/*  93 */       }, { Integer.valueOf(90), "a53"
/*  94 */       }, { Integer.valueOf(91), "a54" }, 
/*  95 */       { Integer.valueOf(92), "a55"
/*  96 */       }, { Integer.valueOf(93), "a56"
/*  97 */       }, { Integer.valueOf(94), "a57"
/*  98 */       }, { Integer.valueOf(95), "a58"
/*  99 */       }, { Integer.valueOf(96), "a59"
/* 100 */       }, { Integer.valueOf(97), "a60"
/* 101 */       }, { Integer.valueOf(98), "a61"
/* 102 */       }, { Integer.valueOf(99), "a62"
/* 103 */       }, { Integer.valueOf(100), "a63"
/* 104 */       }, { Integer.valueOf(101), "a64" }, 
/* 105 */       { Integer.valueOf(102), "a65"
/* 106 */       }, { Integer.valueOf(103), "a66"
/* 107 */       }, { Integer.valueOf(104), "a67"
/* 108 */       }, { Integer.valueOf(105), "a68"
/* 109 */       }, { Integer.valueOf(106), "a69"
/* 110 */       }, { Integer.valueOf(107), "a70"
/* 111 */       }, { Integer.valueOf(108), "a71"
/* 112 */       }, { Integer.valueOf(109), "a72"
/* 113 */       }, { Integer.valueOf(110), "a73"
/* 114 */       }, { Integer.valueOf(111), "a74" }, 
/* 115 */       { Integer.valueOf(112), "a203"
/* 116 */       }, { Integer.valueOf(113), "a75"
/* 117 */       }, { Integer.valueOf(114), "a204"
/* 118 */       }, { Integer.valueOf(115), "a76"
/* 119 */       }, { Integer.valueOf(116), "a77"
/* 120 */       }, { Integer.valueOf(117), "a78"
/* 121 */       }, { Integer.valueOf(118), "a79"
/* 122 */       }, { Integer.valueOf(119), "a81"
/* 123 */       }, { Integer.valueOf(120), "a82"
/* 124 */       }, { Integer.valueOf(121), "a83" }, 
/* 125 */       { Integer.valueOf(122), "a84"
/* 126 */       }, { Integer.valueOf(123), "a97"
/* 127 */       }, { Integer.valueOf(124), "a98"
/* 128 */       }, { Integer.valueOf(125), "a99"
/* 129 */       }, { Integer.valueOf(126), "a100"
/* 130 */       }, { Integer.valueOf(161), "a101"
/* 131 */       }, { Integer.valueOf(162), "a102"
/* 132 */       }, { Integer.valueOf(163), "a103"
/* 133 */       }, { Integer.valueOf(164), "a104"
/* 134 */       }, { Integer.valueOf(165), "a106" }, 
/* 135 */       { Integer.valueOf(166), "a107"
/* 136 */       }, { Integer.valueOf(167), "a108"
/* 137 */       }, { Integer.valueOf(168), "a112"
/* 138 */       }, { Integer.valueOf(169), "a111"
/* 139 */       }, { Integer.valueOf(170), "a110"
/* 140 */       }, { Integer.valueOf(171), "a109"
/* 141 */       }, { Integer.valueOf(172), "a120"
/* 142 */       }, { Integer.valueOf(173), "a121"
/* 143 */       }, { Integer.valueOf(174), "a122"
/* 144 */       }, { Integer.valueOf(175), "a123" }, 
/* 145 */       { Integer.valueOf(176), "a124"
/* 146 */       }, { Integer.valueOf(177), "a125"
/* 147 */       }, { Integer.valueOf(178), "a126"
/* 148 */       }, { Integer.valueOf(179), "a127"
/* 149 */       }, { Integer.valueOf(180), "a128"
/* 150 */       }, { Integer.valueOf(181), "a129"
/* 151 */       }, { Integer.valueOf(182), "a130"
/* 152 */       }, { Integer.valueOf(183), "a131"
/* 153 */       }, { Integer.valueOf(184), "a132"
/* 154 */       }, { Integer.valueOf(185), "a133" }, 
/* 155 */       { Integer.valueOf(186), "a134"
/* 156 */       }, { Integer.valueOf(187), "a135"
/* 157 */       }, { Integer.valueOf(188), "a136"
/* 158 */       }, { Integer.valueOf(189), "a137"
/* 159 */       }, { Integer.valueOf(190), "a138"
/* 160 */       }, { Integer.valueOf(191), "a139"
/* 161 */       }, { Integer.valueOf(192), "a140"
/* 162 */       }, { Integer.valueOf(193), "a141"
/* 163 */       }, { Integer.valueOf(194), "a142"
/* 164 */       }, { Integer.valueOf(195), "a143" }, 
/* 165 */       { Integer.valueOf(196), "a144"
/* 166 */       }, { Integer.valueOf(197), "a145"
/* 167 */       }, { Integer.valueOf(198), "a146"
/* 168 */       }, { Integer.valueOf(199), "a147"
/* 169 */       }, { Integer.valueOf(200), "a148"
/* 170 */       }, { Integer.valueOf(201), "a149"
/* 171 */       }, { Integer.valueOf(202), "a150"
/* 172 */       }, { Integer.valueOf(203), "a151"
/* 173 */       }, { Integer.valueOf(204), "a152"
/* 174 */       }, { Integer.valueOf(205), "a153" }, 
/* 175 */       { Integer.valueOf(206), "a154"
/* 176 */       }, { Integer.valueOf(207), "a155"
/* 177 */       }, { Integer.valueOf(208), "a156"
/* 178 */       }, { Integer.valueOf(209), "a157"
/* 179 */       }, { Integer.valueOf(210), "a158"
/* 180 */       }, { Integer.valueOf(211), "a159"
/* 181 */       }, { Integer.valueOf(212), "a160"
/* 182 */       }, { Integer.valueOf(213), "a161"
/* 183 */       }, { Integer.valueOf(214), "a163"
/* 184 */       }, { Integer.valueOf(215), "a164" }, 
/* 185 */       { Integer.valueOf(216), "a196"
/* 186 */       }, { Integer.valueOf(217), "a165"
/* 187 */       }, { Integer.valueOf(218), "a192"
/* 188 */       }, { Integer.valueOf(219), "a166"
/* 189 */       }, { Integer.valueOf(220), "a167"
/* 190 */       }, { Integer.valueOf(221), "a168"
/* 191 */       }, { Integer.valueOf(222), "a169"
/* 192 */       }, { Integer.valueOf(223), "a170"
/* 193 */       }, { Integer.valueOf(224), "a171"
/* 194 */       }, { Integer.valueOf(225), "a172" }, 
/* 195 */       { Integer.valueOf(226), "a173"
/* 196 */       }, { Integer.valueOf(227), "a162"
/* 197 */       }, { Integer.valueOf(228), "a174"
/* 198 */       }, { Integer.valueOf(229), "a175"
/* 199 */       }, { Integer.valueOf(230), "a176"
/* 200 */       }, { Integer.valueOf(231), "a177"
/* 201 */       }, { Integer.valueOf(232), "a178"
/* 202 */       }, { Integer.valueOf(233), "a179"
/* 203 */       }, { Integer.valueOf(234), "a193"
/* 204 */       }, { Integer.valueOf(235), "a180" }, 
/* 205 */       { Integer.valueOf(236), "a199"
/* 206 */       }, { Integer.valueOf(237), "a181"
/* 207 */       }, { Integer.valueOf(238), "a200"
/* 208 */       }, { Integer.valueOf(239), "a182"
/* 209 */       }, { Integer.valueOf(241), "a201"
/* 210 */       }, { Integer.valueOf(242), "a183"
/* 211 */       }, { Integer.valueOf(243), "a184"
/* 212 */       }, { Integer.valueOf(244), "a197"
/* 213 */       }, { Integer.valueOf(245), "a185"
/* 214 */       }, { Integer.valueOf(246), "a194" }, 
/* 215 */       { Integer.valueOf(247), "a198"
/* 216 */       }, { Integer.valueOf(248), "a186"
/* 217 */       }, { Integer.valueOf(249), "a195"
/* 218 */       }, { Integer.valueOf(250), "a187"
/* 219 */       }, { Integer.valueOf(251), "a188"
/* 220 */       }, { Integer.valueOf(252), "a189"
/* 221 */       }, { Integer.valueOf(253), "a190"
/* 222 */       }, { Integer.valueOf(254), "a191" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   public static final ZapfDingbatsEncoding INSTANCE = new ZapfDingbatsEncoding();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ZapfDingbatsEncoding() {
/* 235 */     for (Object[] encodingEntry : ZAPFDINGBATS_ENCODING_TABLE)
/*     */     {
/* 237 */       add(((Integer)encodingEntry[0]).intValue(), encodingEntry[1].toString());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/* 244 */     return (COSBase)COSName.getPDFName("ZapfDingbatsEncoding");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEncodingName() {
/* 250 */     return "ZapfDingbatsEncoding";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/encoding/ZapfDingbatsEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */