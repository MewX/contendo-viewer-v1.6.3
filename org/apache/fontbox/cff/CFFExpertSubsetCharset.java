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
/*     */ 
/*     */ public final class CFFExpertSubsetCharset
/*     */   extends CFFCharset
/*     */ {
/*     */   private static final int CHAR_CODE = 0;
/*     */   private static final int CHAR_NAME = 1;
/*  34 */   private static final Object[][] CFF_EXPERT_SUBSET_CHARSET_TABLE = new Object[][] { 
/*  35 */       { Integer.valueOf(0), ".notdef"
/*  36 */       }, { Integer.valueOf(1), "space"
/*  37 */       }, { Integer.valueOf(231), "dollaroldstyle"
/*  38 */       }, { Integer.valueOf(232), "dollarsuperior"
/*  39 */       }, { Integer.valueOf(235), "parenleftsuperior"
/*  40 */       }, { Integer.valueOf(236), "parenrightsuperior"
/*  41 */       }, { Integer.valueOf(237), "twodotenleader"
/*  42 */       }, { Integer.valueOf(238), "onedotenleader"
/*  43 */       }, { Integer.valueOf(13), "comma"
/*  44 */       }, { Integer.valueOf(14), "hyphen" }, 
/*  45 */       { Integer.valueOf(15), "period"
/*  46 */       }, { Integer.valueOf(99), "fraction"
/*  47 */       }, { Integer.valueOf(239), "zerooldstyle"
/*  48 */       }, { Integer.valueOf(240), "oneoldstyle"
/*  49 */       }, { Integer.valueOf(241), "twooldstyle"
/*  50 */       }, { Integer.valueOf(242), "threeoldstyle"
/*  51 */       }, { Integer.valueOf(243), "fouroldstyle"
/*  52 */       }, { Integer.valueOf(244), "fiveoldstyle"
/*  53 */       }, { Integer.valueOf(245), "sixoldstyle"
/*  54 */       }, { Integer.valueOf(246), "sevenoldstyle" }, 
/*  55 */       { Integer.valueOf(247), "eightoldstyle"
/*  56 */       }, { Integer.valueOf(248), "nineoldstyle"
/*  57 */       }, { Integer.valueOf(27), "colon"
/*  58 */       }, { Integer.valueOf(28), "semicolon"
/*  59 */       }, { Integer.valueOf(249), "commasuperior"
/*  60 */       }, { Integer.valueOf(250), "threequartersemdash"
/*  61 */       }, { Integer.valueOf(251), "periodsuperior"
/*  62 */       }, { Integer.valueOf(253), "asuperior"
/*  63 */       }, { Integer.valueOf(254), "bsuperior"
/*  64 */       }, { Integer.valueOf(255), "centsuperior" }, 
/*  65 */       { Integer.valueOf(256), "dsuperior"
/*  66 */       }, { Integer.valueOf(257), "esuperior"
/*  67 */       }, { Integer.valueOf(258), "isuperior"
/*  68 */       }, { Integer.valueOf(259), "lsuperior"
/*  69 */       }, { Integer.valueOf(260), "msuperior"
/*  70 */       }, { Integer.valueOf(261), "nsuperior"
/*  71 */       }, { Integer.valueOf(262), "osuperior"
/*  72 */       }, { Integer.valueOf(263), "rsuperior"
/*  73 */       }, { Integer.valueOf(264), "ssuperior"
/*  74 */       }, { Integer.valueOf(265), "tsuperior" }, 
/*  75 */       { Integer.valueOf(266), "ff"
/*  76 */       }, { Integer.valueOf(109), "fi"
/*  77 */       }, { Integer.valueOf(110), "fl"
/*  78 */       }, { Integer.valueOf(267), "ffi"
/*  79 */       }, { Integer.valueOf(268), "ffl"
/*  80 */       }, { Integer.valueOf(269), "parenleftinferior"
/*  81 */       }, { Integer.valueOf(270), "parenrightinferior"
/*  82 */       }, { Integer.valueOf(272), "hyphensuperior"
/*  83 */       }, { Integer.valueOf(300), "colonmonetary"
/*  84 */       }, { Integer.valueOf(301), "onefitted" }, 
/*  85 */       { Integer.valueOf(302), "rupiah"
/*  86 */       }, { Integer.valueOf(305), "centoldstyle"
/*  87 */       }, { Integer.valueOf(314), "figuredash"
/*  88 */       }, { Integer.valueOf(315), "hypheninferior"
/*  89 */       }, { Integer.valueOf(158), "onequarter"
/*  90 */       }, { Integer.valueOf(155), "onehalf"
/*  91 */       }, { Integer.valueOf(163), "threequarters"
/*  92 */       }, { Integer.valueOf(320), "oneeighth"
/*  93 */       }, { Integer.valueOf(321), "threeeighths"
/*  94 */       }, { Integer.valueOf(322), "fiveeighths" }, 
/*  95 */       { Integer.valueOf(323), "seveneighths"
/*  96 */       }, { Integer.valueOf(324), "onethird"
/*  97 */       }, { Integer.valueOf(325), "twothirds"
/*  98 */       }, { Integer.valueOf(326), "zerosuperior"
/*  99 */       }, { Integer.valueOf(150), "onesuperior"
/* 100 */       }, { Integer.valueOf(164), "twosuperior"
/* 101 */       }, { Integer.valueOf(169), "threesuperior"
/* 102 */       }, { Integer.valueOf(327), "foursuperior"
/* 103 */       }, { Integer.valueOf(328), "fivesuperior"
/* 104 */       }, { Integer.valueOf(329), "sixsuperior" }, 
/* 105 */       { Integer.valueOf(330), "sevensuperior"
/* 106 */       }, { Integer.valueOf(331), "eightsuperior"
/* 107 */       }, { Integer.valueOf(332), "ninesuperior"
/* 108 */       }, { Integer.valueOf(333), "zeroinferior"
/* 109 */       }, { Integer.valueOf(334), "oneinferior"
/* 110 */       }, { Integer.valueOf(335), "twoinferior"
/* 111 */       }, { Integer.valueOf(336), "threeinferior"
/* 112 */       }, { Integer.valueOf(337), "fourinferior"
/* 113 */       }, { Integer.valueOf(338), "fiveinferior"
/* 114 */       }, { Integer.valueOf(339), "sixinferior" }, 
/* 115 */       { Integer.valueOf(340), "seveninferior"
/* 116 */       }, { Integer.valueOf(341), "eightinferior"
/* 117 */       }, { Integer.valueOf(342), "nineinferior"
/* 118 */       }, { Integer.valueOf(343), "centinferior"
/* 119 */       }, { Integer.valueOf(344), "dollarinferior"
/* 120 */       }, { Integer.valueOf(345), "periodinferior"
/* 121 */       }, { Integer.valueOf(346), "commainferior" } };
/*     */ 
/*     */ 
/*     */   
/*     */   private CFFExpertSubsetCharset() {
/* 126 */     super(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CFFExpertSubsetCharset getInstance() {
/* 135 */     return INSTANCE;
/*     */   }
/*     */   
/* 138 */   private static final CFFExpertSubsetCharset INSTANCE = new CFFExpertSubsetCharset();
/*     */ 
/*     */   
/*     */   static {
/* 142 */     int gid = 0;
/* 143 */     for (Object[] charsetEntry : CFF_EXPERT_SUBSET_CHARSET_TABLE)
/*     */     {
/* 145 */       INSTANCE.addSID(gid++, ((Integer)charsetEntry[0]).intValue(), charsetEntry[1].toString());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/CFFExpertSubsetCharset.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */