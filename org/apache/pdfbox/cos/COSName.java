/*     */ package org.apache.pdfbox.cos;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.apache.pdfbox.util.Charsets;
/*     */ import org.apache.pdfbox.util.Hex;
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
/*     */ public final class COSName
/*     */   extends COSBase
/*     */   implements Comparable<COSName>
/*     */ {
/*  36 */   private static Map<String, COSName> nameMap = new ConcurrentHashMap<String, COSName>(8192);
/*     */ 
/*     */ 
/*     */   
/*  40 */   private static Map<String, COSName> commonNameMap = new HashMap<String, COSName>(768);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   public static final COSName A = new COSName("A");
/*  48 */   public static final COSName AA = new COSName("AA");
/*  49 */   public static final COSName AC = new COSName("AC");
/*  50 */   public static final COSName ACRO_FORM = new COSName("AcroForm");
/*  51 */   public static final COSName ACTUAL_TEXT = new COSName("ActualText");
/*  52 */   public static final COSName ADBE_PKCS7_DETACHED = new COSName("adbe.pkcs7.detached");
/*  53 */   public static final COSName ADBE_PKCS7_SHA1 = new COSName("adbe.pkcs7.sha1");
/*  54 */   public static final COSName ADBE_X509_RSA_SHA1 = new COSName("adbe.x509.rsa_sha1");
/*  55 */   public static final COSName ADOBE_PPKLITE = new COSName("Adobe.PPKLite");
/*  56 */   public static final COSName AESV2 = new COSName("AESV2");
/*  57 */   public static final COSName AESV3 = new COSName("AESV3");
/*  58 */   public static final COSName AFTER = new COSName("After");
/*  59 */   public static final COSName AIS = new COSName("AIS");
/*  60 */   public static final COSName ALL_OFF = new COSName("AllOff");
/*  61 */   public static final COSName ALL_ON = new COSName("AllOn");
/*  62 */   public static final COSName ALT = new COSName("Alt");
/*  63 */   public static final COSName ALPHA = new COSName("Alpha");
/*  64 */   public static final COSName ALTERNATE = new COSName("Alternate");
/*  65 */   public static final COSName ANNOT = new COSName("Annot");
/*  66 */   public static final COSName ANNOTS = new COSName("Annots");
/*  67 */   public static final COSName ANTI_ALIAS = new COSName("AntiAlias");
/*  68 */   public static final COSName ANY_OFF = new COSName("AnyOff");
/*  69 */   public static final COSName ANY_ON = new COSName("AnyOn");
/*  70 */   public static final COSName AP = new COSName("AP");
/*  71 */   public static final COSName AP_REF = new COSName("APRef");
/*  72 */   public static final COSName APP = new COSName("App");
/*  73 */   public static final COSName ART_BOX = new COSName("ArtBox");
/*  74 */   public static final COSName ARTIFACT = new COSName("Artifact");
/*  75 */   public static final COSName AS = new COSName("AS");
/*  76 */   public static final COSName ASCENT = new COSName("Ascent");
/*  77 */   public static final COSName ASCII_HEX_DECODE = new COSName("ASCIIHexDecode");
/*  78 */   public static final COSName ASCII_HEX_DECODE_ABBREVIATION = new COSName("AHx");
/*  79 */   public static final COSName ASCII85_DECODE = new COSName("ASCII85Decode");
/*  80 */   public static final COSName ASCII85_DECODE_ABBREVIATION = new COSName("A85");
/*  81 */   public static final COSName ATTACHED = new COSName("Attached");
/*  82 */   public static final COSName AUTHOR = new COSName("Author");
/*  83 */   public static final COSName AVG_WIDTH = new COSName("AvgWidth");
/*     */   
/*  85 */   public static final COSName B = new COSName("B");
/*  86 */   public static final COSName BACKGROUND = new COSName("Background");
/*  87 */   public static final COSName BASE_ENCODING = new COSName("BaseEncoding");
/*  88 */   public static final COSName BASE_FONT = new COSName("BaseFont");
/*  89 */   public static final COSName BASE_STATE = new COSName("BaseState");
/*  90 */   public static final COSName BBOX = new COSName("BBox");
/*  91 */   public static final COSName BC = new COSName("BC");
/*  92 */   public static final COSName BE = new COSName("BE");
/*  93 */   public static final COSName BEFORE = new COSName("Before");
/*  94 */   public static final COSName BG = new COSName("BG");
/*  95 */   public static final COSName BITS_PER_COMPONENT = new COSName("BitsPerComponent");
/*  96 */   public static final COSName BITS_PER_COORDINATE = new COSName("BitsPerCoordinate");
/*  97 */   public static final COSName BITS_PER_FLAG = new COSName("BitsPerFlag");
/*  98 */   public static final COSName BITS_PER_SAMPLE = new COSName("BitsPerSample");
/*  99 */   public static final COSName BLACK_IS_1 = new COSName("BlackIs1");
/* 100 */   public static final COSName BLACK_POINT = new COSName("BlackPoint");
/* 101 */   public static final COSName BLEED_BOX = new COSName("BleedBox");
/* 102 */   public static final COSName BM = new COSName("BM");
/* 103 */   public static final COSName BORDER = new COSName("Border");
/* 104 */   public static final COSName BOUNDS = new COSName("Bounds");
/* 105 */   public static final COSName BPC = new COSName("BPC");
/* 106 */   public static final COSName BS = new COSName("BS");
/*     */   
/* 108 */   public static final COSName BTN = new COSName("Btn");
/* 109 */   public static final COSName BYTERANGE = new COSName("ByteRange");
/*     */   
/* 111 */   public static final COSName C = new COSName("C");
/* 112 */   public static final COSName C0 = new COSName("C0");
/* 113 */   public static final COSName C1 = new COSName("C1");
/* 114 */   public static final COSName CA = new COSName("CA");
/* 115 */   public static final COSName CA_NS = new COSName("ca");
/* 116 */   public static final COSName CALGRAY = new COSName("CalGray");
/* 117 */   public static final COSName CALRGB = new COSName("CalRGB");
/* 118 */   public static final COSName CAP = new COSName("Cap");
/* 119 */   public static final COSName CAP_HEIGHT = new COSName("CapHeight");
/* 120 */   public static final COSName CATALOG = new COSName("Catalog");
/* 121 */   public static final COSName CCITTFAX_DECODE = new COSName("CCITTFaxDecode");
/* 122 */   public static final COSName CCITTFAX_DECODE_ABBREVIATION = new COSName("CCF");
/* 123 */   public static final COSName CENTER_WINDOW = new COSName("CenterWindow");
/* 124 */   public static final COSName CERT = new COSName("Cert");
/* 125 */   public static final COSName CF = new COSName("CF");
/* 126 */   public static final COSName CFM = new COSName("CFM");
/*     */   
/* 128 */   public static final COSName CH = new COSName("Ch");
/* 129 */   public static final COSName CHAR_PROCS = new COSName("CharProcs");
/* 130 */   public static final COSName CHAR_SET = new COSName("CharSet");
/* 131 */   public static final COSName CICI_SIGNIT = new COSName("CICI.SignIt");
/* 132 */   public static final COSName CID_FONT_TYPE0 = new COSName("CIDFontType0");
/* 133 */   public static final COSName CID_FONT_TYPE2 = new COSName("CIDFontType2");
/* 134 */   public static final COSName CID_TO_GID_MAP = new COSName("CIDToGIDMap");
/* 135 */   public static final COSName CID_SET = new COSName("CIDSet");
/* 136 */   public static final COSName CIDSYSTEMINFO = new COSName("CIDSystemInfo");
/* 137 */   public static final COSName CL = new COSName("CL");
/* 138 */   public static final COSName CLR_F = new COSName("ClrF");
/* 139 */   public static final COSName CLR_FF = new COSName("ClrFf");
/* 140 */   public static final COSName CMAP = new COSName("CMap");
/* 141 */   public static final COSName CMAPNAME = new COSName("CMapName");
/* 142 */   public static final COSName CMYK = new COSName("CMYK");
/* 143 */   public static final COSName CO = new COSName("CO");
/* 144 */   public static final COSName COLOR = new COSName("Color");
/* 145 */   public static final COSName COLLECTION = new COSName("Collection");
/* 146 */   public static final COSName COLOR_BURN = new COSName("ColorBurn");
/* 147 */   public static final COSName COLOR_DODGE = new COSName("ColorDodge");
/* 148 */   public static final COSName COLORANTS = new COSName("Colorants");
/* 149 */   public static final COSName COLORS = new COSName("Colors");
/* 150 */   public static final COSName COLORSPACE = new COSName("ColorSpace");
/* 151 */   public static final COSName COLUMNS = new COSName("Columns");
/* 152 */   public static final COSName COMPATIBLE = new COSName("Compatible");
/* 153 */   public static final COSName COMPONENTS = new COSName("Components");
/* 154 */   public static final COSName CONTACT_INFO = new COSName("ContactInfo");
/* 155 */   public static final COSName CONTENTS = new COSName("Contents");
/* 156 */   public static final COSName COORDS = new COSName("Coords");
/* 157 */   public static final COSName COUNT = new COSName("Count");
/* 158 */   public static final COSName CP = new COSName("CP");
/* 159 */   public static final COSName CREATION_DATE = new COSName("CreationDate");
/* 160 */   public static final COSName CREATOR = new COSName("Creator");
/* 161 */   public static final COSName CROP_BOX = new COSName("CropBox");
/* 162 */   public static final COSName CRYPT = new COSName("Crypt");
/* 163 */   public static final COSName CS = new COSName("CS");
/*     */   
/* 165 */   public static final COSName D = new COSName("D");
/* 166 */   public static final COSName DA = new COSName("DA");
/* 167 */   public static final COSName DARKEN = new COSName("Darken");
/* 168 */   public static final COSName DATE = new COSName("Date");
/* 169 */   public static final COSName DCT_DECODE = new COSName("DCTDecode");
/* 170 */   public static final COSName DCT_DECODE_ABBREVIATION = new COSName("DCT");
/* 171 */   public static final COSName DECODE = new COSName("Decode");
/* 172 */   public static final COSName DECODE_PARMS = new COSName("DecodeParms");
/* 173 */   public static final COSName DEFAULT = new COSName("default");
/* 174 */   public static final COSName DEFAULT_CMYK = new COSName("DefaultCMYK");
/* 175 */   public static final COSName DEFAULT_CRYPT_FILTER = new COSName("DefaultCryptFilter");
/* 176 */   public static final COSName DEFAULT_GRAY = new COSName("DefaultGray");
/* 177 */   public static final COSName DEFAULT_RGB = new COSName("DefaultRGB");
/* 178 */   public static final COSName DESC = new COSName("Desc");
/* 179 */   public static final COSName DESCENDANT_FONTS = new COSName("DescendantFonts");
/* 180 */   public static final COSName DESCENT = new COSName("Descent");
/* 181 */   public static final COSName DEST = new COSName("Dest");
/* 182 */   public static final COSName DEST_OUTPUT_PROFILE = new COSName("DestOutputProfile");
/* 183 */   public static final COSName DESTS = new COSName("Dests");
/* 184 */   public static final COSName DEVICECMYK = new COSName("DeviceCMYK");
/* 185 */   public static final COSName DEVICEGRAY = new COSName("DeviceGray");
/* 186 */   public static final COSName DEVICEN = new COSName("DeviceN");
/* 187 */   public static final COSName DEVICERGB = new COSName("DeviceRGB");
/* 188 */   public static final COSName DI = new COSName("Di");
/* 189 */   public static final COSName DIFFERENCE = new COSName("Difference");
/* 190 */   public static final COSName DIFFERENCES = new COSName("Differences");
/* 191 */   public static final COSName DIGEST_METHOD = new COSName("DigestMethod");
/* 192 */   public static final COSName DIGEST_RIPEMD160 = new COSName("RIPEMD160");
/* 193 */   public static final COSName DIGEST_SHA1 = new COSName("SHA1");
/* 194 */   public static final COSName DIGEST_SHA256 = new COSName("SHA256");
/* 195 */   public static final COSName DIGEST_SHA384 = new COSName("SHA384");
/* 196 */   public static final COSName DIGEST_SHA512 = new COSName("SHA512");
/* 197 */   public static final COSName DIRECTION = new COSName("Direction");
/* 198 */   public static final COSName DISPLAY_DOC_TITLE = new COSName("DisplayDocTitle");
/* 199 */   public static final COSName DL = new COSName("DL");
/* 200 */   public static final COSName DM = new COSName("Dm");
/* 201 */   public static final COSName DOC = new COSName("Doc");
/* 202 */   public static final COSName DOC_CHECKSUM = new COSName("DocChecksum");
/* 203 */   public static final COSName DOC_TIME_STAMP = new COSName("DocTimeStamp");
/* 204 */   public static final COSName DOCMDP = new COSName("DocMDP");
/* 205 */   public static final COSName DOCUMENT = new COSName("Document");
/* 206 */   public static final COSName DOMAIN = new COSName("Domain");
/* 207 */   public static final COSName DOS = new COSName("DOS");
/* 208 */   public static final COSName DP = new COSName("DP");
/* 209 */   public static final COSName DR = new COSName("DR");
/* 210 */   public static final COSName DS = new COSName("DS");
/* 211 */   public static final COSName DUPLEX = new COSName("Duplex");
/* 212 */   public static final COSName DUR = new COSName("Dur");
/* 213 */   public static final COSName DV = new COSName("DV");
/* 214 */   public static final COSName DW = new COSName("DW");
/* 215 */   public static final COSName DW2 = new COSName("DW2");
/*     */   
/* 217 */   public static final COSName E = new COSName("E");
/* 218 */   public static final COSName EARLY_CHANGE = new COSName("EarlyChange");
/* 219 */   public static final COSName EF = new COSName("EF");
/* 220 */   public static final COSName EMBEDDED_FDFS = new COSName("EmbeddedFDFs");
/* 221 */   public static final COSName EMBEDDED_FILES = new COSName("EmbeddedFiles");
/* 222 */   public static final COSName EMPTY = new COSName("");
/* 223 */   public static final COSName ENCODE = new COSName("Encode");
/* 224 */   public static final COSName ENCODED_BYTE_ALIGN = new COSName("EncodedByteAlign");
/* 225 */   public static final COSName ENCODING = new COSName("Encoding");
/* 226 */   public static final COSName ENCODING_90MS_RKSJ_H = new COSName("90ms-RKSJ-H");
/* 227 */   public static final COSName ENCODING_90MS_RKSJ_V = new COSName("90ms-RKSJ-V");
/* 228 */   public static final COSName ENCODING_ETEN_B5_H = new COSName("ETen-B5-H");
/* 229 */   public static final COSName ENCODING_ETEN_B5_V = new COSName("ETen-B5-V");
/* 230 */   public static final COSName ENCRYPT = new COSName("Encrypt");
/* 231 */   public static final COSName ENCRYPT_META_DATA = new COSName("EncryptMetadata");
/* 232 */   public static final COSName END_OF_LINE = new COSName("EndOfLine");
/* 233 */   public static final COSName ENTRUST_PPKEF = new COSName("Entrust.PPKEF");
/* 234 */   public static final COSName EXCLUSION = new COSName("Exclusion");
/* 235 */   public static final COSName EXT_G_STATE = new COSName("ExtGState");
/* 236 */   public static final COSName EXTEND = new COSName("Extend");
/* 237 */   public static final COSName EXTENDS = new COSName("Extends");
/*     */   
/* 239 */   public static final COSName F = new COSName("F");
/* 240 */   public static final COSName F_DECODE_PARMS = new COSName("FDecodeParms");
/* 241 */   public static final COSName F_FILTER = new COSName("FFilter");
/* 242 */   public static final COSName FB = new COSName("FB");
/* 243 */   public static final COSName FDF = new COSName("FDF");
/* 244 */   public static final COSName FF = new COSName("Ff");
/* 245 */   public static final COSName FIELDS = new COSName("Fields");
/* 246 */   public static final COSName FILESPEC = new COSName("Filespec");
/* 247 */   public static final COSName FILTER = new COSName("Filter");
/* 248 */   public static final COSName FIRST = new COSName("First");
/* 249 */   public static final COSName FIRST_CHAR = new COSName("FirstChar");
/* 250 */   public static final COSName FIT_WINDOW = new COSName("FitWindow");
/* 251 */   public static final COSName FL = new COSName("FL");
/* 252 */   public static final COSName FLAGS = new COSName("Flags");
/* 253 */   public static final COSName FLATE_DECODE = new COSName("FlateDecode");
/* 254 */   public static final COSName FLATE_DECODE_ABBREVIATION = new COSName("Fl");
/* 255 */   public static final COSName FOLDERS = new COSName("Folders");
/* 256 */   public static final COSName FONT = new COSName("Font");
/* 257 */   public static final COSName FONT_BBOX = new COSName("FontBBox");
/* 258 */   public static final COSName FONT_DESC = new COSName("FontDescriptor");
/* 259 */   public static final COSName FONT_FAMILY = new COSName("FontFamily");
/* 260 */   public static final COSName FONT_FILE = new COSName("FontFile");
/* 261 */   public static final COSName FONT_FILE2 = new COSName("FontFile2");
/* 262 */   public static final COSName FONT_FILE3 = new COSName("FontFile3");
/* 263 */   public static final COSName FONT_MATRIX = new COSName("FontMatrix");
/* 264 */   public static final COSName FONT_NAME = new COSName("FontName");
/* 265 */   public static final COSName FONT_STRETCH = new COSName("FontStretch");
/* 266 */   public static final COSName FONT_WEIGHT = new COSName("FontWeight");
/* 267 */   public static final COSName FORM = new COSName("Form");
/* 268 */   public static final COSName FORMTYPE = new COSName("FormType");
/* 269 */   public static final COSName FRM = new COSName("FRM");
/* 270 */   public static final COSName FT = new COSName("FT");
/* 271 */   public static final COSName FUNCTION = new COSName("Function");
/* 272 */   public static final COSName FUNCTION_TYPE = new COSName("FunctionType");
/* 273 */   public static final COSName FUNCTIONS = new COSName("Functions");
/*     */   
/* 275 */   public static final COSName G = new COSName("G");
/* 276 */   public static final COSName GAMMA = new COSName("Gamma");
/* 277 */   public static final COSName GROUP = new COSName("Group");
/* 278 */   public static final COSName GTS_PDFA1 = new COSName("GTS_PDFA1");
/*     */   
/* 280 */   public static final COSName H = new COSName("H");
/* 281 */   public static final COSName HARD_LIGHT = new COSName("HardLight");
/* 282 */   public static final COSName HEIGHT = new COSName("Height");
/* 283 */   public static final COSName HELV = new COSName("Helv");
/* 284 */   public static final COSName HIDE_MENUBAR = new COSName("HideMenubar");
/* 285 */   public static final COSName HIDE_TOOLBAR = new COSName("HideToolbar");
/* 286 */   public static final COSName HIDE_WINDOWUI = new COSName("HideWindowUI");
/* 287 */   public static final COSName HUE = new COSName("Hue");
/*     */   
/* 289 */   public static final COSName I = new COSName("I");
/* 290 */   public static final COSName IC = new COSName("IC");
/* 291 */   public static final COSName ICCBASED = new COSName("ICCBased");
/* 292 */   public static final COSName ID = new COSName("ID");
/* 293 */   public static final COSName ID_TREE = new COSName("IDTree");
/* 294 */   public static final COSName IDENTITY = new COSName("Identity");
/* 295 */   public static final COSName IDENTITY_H = new COSName("Identity-H");
/* 296 */   public static final COSName IDENTITY_V = new COSName("Identity-V");
/* 297 */   public static final COSName IF = new COSName("IF");
/* 298 */   public static final COSName IM = new COSName("IM");
/* 299 */   public static final COSName IMAGE = new COSName("Image");
/* 300 */   public static final COSName IMAGE_MASK = new COSName("ImageMask");
/* 301 */   public static final COSName INDEX = new COSName("Index");
/* 302 */   public static final COSName INDEXED = new COSName("Indexed");
/* 303 */   public static final COSName INFO = new COSName("Info");
/* 304 */   public static final COSName INKLIST = new COSName("InkList");
/* 305 */   public static final COSName INTERPOLATE = new COSName("Interpolate");
/* 306 */   public static final COSName IT = new COSName("IT");
/* 307 */   public static final COSName ITALIC_ANGLE = new COSName("ItalicAngle");
/* 308 */   public static final COSName ISSUER = new COSName("Issuer");
/* 309 */   public static final COSName IX = new COSName("IX");
/*     */   
/* 311 */   public static final COSName JAVA_SCRIPT = new COSName("JavaScript");
/* 312 */   public static final COSName JBIG2_DECODE = new COSName("JBIG2Decode");
/* 313 */   public static final COSName JBIG2_GLOBALS = new COSName("JBIG2Globals");
/* 314 */   public static final COSName JPX_DECODE = new COSName("JPXDecode");
/* 315 */   public static final COSName JS = new COSName("JS");
/*     */   
/* 317 */   public static final COSName K = new COSName("K");
/* 318 */   public static final COSName KEYWORDS = new COSName("Keywords");
/* 319 */   public static final COSName KEY_USAGE = new COSName("KeyUsage");
/* 320 */   public static final COSName KIDS = new COSName("Kids");
/*     */   
/* 322 */   public static final COSName L = new COSName("L");
/* 323 */   public static final COSName LAB = new COSName("Lab");
/* 324 */   public static final COSName LANG = new COSName("Lang");
/* 325 */   public static final COSName LAST = new COSName("Last");
/* 326 */   public static final COSName LAST_CHAR = new COSName("LastChar");
/* 327 */   public static final COSName LAST_MODIFIED = new COSName("LastModified");
/* 328 */   public static final COSName LC = new COSName("LC");
/* 329 */   public static final COSName LE = new COSName("LE");
/* 330 */   public static final COSName LEADING = new COSName("Leading");
/* 331 */   public static final COSName LEGAL_ATTESTATION = new COSName("LegalAttestation");
/* 332 */   public static final COSName LENGTH = new COSName("Length");
/* 333 */   public static final COSName LENGTH1 = new COSName("Length1");
/* 334 */   public static final COSName LENGTH2 = new COSName("Length2");
/* 335 */   public static final COSName LIGHTEN = new COSName("Lighten");
/* 336 */   public static final COSName LIMITS = new COSName("Limits");
/* 337 */   public static final COSName LJ = new COSName("LJ");
/* 338 */   public static final COSName LL = new COSName("LL");
/* 339 */   public static final COSName LLE = new COSName("LLE");
/* 340 */   public static final COSName LLO = new COSName("LLO");
/* 341 */   public static final COSName LOCATION = new COSName("Location");
/* 342 */   public static final COSName LUMINOSITY = new COSName("Luminosity");
/* 343 */   public static final COSName LW = new COSName("LW");
/* 344 */   public static final COSName LZW_DECODE = new COSName("LZWDecode");
/* 345 */   public static final COSName LZW_DECODE_ABBREVIATION = new COSName("LZW");
/*     */   
/* 347 */   public static final COSName M = new COSName("M");
/* 348 */   public static final COSName MAC = new COSName("Mac");
/* 349 */   public static final COSName MAC_EXPERT_ENCODING = new COSName("MacExpertEncoding");
/* 350 */   public static final COSName MAC_ROMAN_ENCODING = new COSName("MacRomanEncoding");
/* 351 */   public static final COSName MARK_INFO = new COSName("MarkInfo");
/* 352 */   public static final COSName MASK = new COSName("Mask");
/* 353 */   public static final COSName MATRIX = new COSName("Matrix");
/* 354 */   public static final COSName MATTE = new COSName("Matte");
/* 355 */   public static final COSName MAX_LEN = new COSName("MaxLen");
/* 356 */   public static final COSName MAX_WIDTH = new COSName("MaxWidth");
/* 357 */   public static final COSName MCID = new COSName("MCID");
/* 358 */   public static final COSName MDP = new COSName("MDP");
/* 359 */   public static final COSName MEDIA_BOX = new COSName("MediaBox");
/* 360 */   public static final COSName MEASURE = new COSName("Measure");
/* 361 */   public static final COSName METADATA = new COSName("Metadata");
/* 362 */   public static final COSName MISSING_WIDTH = new COSName("MissingWidth");
/* 363 */   public static final COSName MIX = new COSName("Mix");
/* 364 */   public static final COSName MK = new COSName("MK");
/* 365 */   public static final COSName ML = new COSName("ML");
/* 366 */   public static final COSName MM_TYPE1 = new COSName("MMType1");
/* 367 */   public static final COSName MOD_DATE = new COSName("ModDate");
/* 368 */   public static final COSName MULTIPLY = new COSName("Multiply");
/*     */   
/* 370 */   public static final COSName N = new COSName("N");
/* 371 */   public static final COSName NAME = new COSName("Name");
/* 372 */   public static final COSName NAMES = new COSName("Names");
/* 373 */   public static final COSName NAVIGATOR = new COSName("Navigator");
/* 374 */   public static final COSName NEED_APPEARANCES = new COSName("NeedAppearances");
/* 375 */   public static final COSName NEW_WINDOW = new COSName("NewWindow");
/* 376 */   public static final COSName NEXT = new COSName("Next");
/* 377 */   public static final COSName NM = new COSName("NM");
/* 378 */   public static final COSName NON_EFONT_NO_WARN = new COSName("NonEFontNoWarn");
/* 379 */   public static final COSName NON_FULL_SCREEN_PAGE_MODE = new COSName("NonFullScreenPageMode");
/* 380 */   public static final COSName NONE = new COSName("None");
/* 381 */   public static final COSName NORMAL = new COSName("Normal");
/* 382 */   public static final COSName NUMS = new COSName("Nums");
/*     */   
/* 384 */   public static final COSName O = new COSName("O");
/* 385 */   public static final COSName OBJ = new COSName("Obj");
/* 386 */   public static final COSName OBJ_STM = new COSName("ObjStm");
/* 387 */   public static final COSName OC = new COSName("OC");
/* 388 */   public static final COSName OCG = new COSName("OCG");
/* 389 */   public static final COSName OCGS = new COSName("OCGs");
/* 390 */   public static final COSName OCMD = new COSName("OCMD");
/* 391 */   public static final COSName OCPROPERTIES = new COSName("OCProperties");
/* 392 */   public static final COSName OE = new COSName("OE");
/* 393 */   public static final COSName OID = new COSName("OID");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 398 */   public static final COSName OFF = new COSName("OFF");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 403 */   public static final COSName Off = new COSName("Off");
/*     */   
/* 405 */   public static final COSName ON = new COSName("ON");
/* 406 */   public static final COSName OP = new COSName("OP");
/* 407 */   public static final COSName OP_NS = new COSName("op");
/* 408 */   public static final COSName OPEN_ACTION = new COSName("OpenAction");
/* 409 */   public static final COSName OPEN_TYPE = new COSName("OpenType");
/* 410 */   public static final COSName OPM = new COSName("OPM");
/* 411 */   public static final COSName OPT = new COSName("Opt");
/* 412 */   public static final COSName ORDER = new COSName("Order");
/* 413 */   public static final COSName ORDERING = new COSName("Ordering");
/* 414 */   public static final COSName OS = new COSName("OS");
/* 415 */   public static final COSName OUTLINES = new COSName("Outlines");
/* 416 */   public static final COSName OUTPUT_CONDITION = new COSName("OutputCondition");
/* 417 */   public static final COSName OUTPUT_CONDITION_IDENTIFIER = new COSName("OutputConditionIdentifier");
/*     */   
/* 419 */   public static final COSName OUTPUT_INTENT = new COSName("OutputIntent");
/* 420 */   public static final COSName OUTPUT_INTENTS = new COSName("OutputIntents");
/* 421 */   public static final COSName OVERLAY = new COSName("Overlay");
/*     */   
/* 423 */   public static final COSName P = new COSName("P");
/* 424 */   public static final COSName PAGE = new COSName("Page");
/* 425 */   public static final COSName PAGE_LABELS = new COSName("PageLabels");
/* 426 */   public static final COSName PAGE_LAYOUT = new COSName("PageLayout");
/* 427 */   public static final COSName PAGE_MODE = new COSName("PageMode");
/* 428 */   public static final COSName PAGES = new COSName("Pages");
/* 429 */   public static final COSName PAINT_TYPE = new COSName("PaintType");
/* 430 */   public static final COSName PANOSE = new COSName("Panose");
/* 431 */   public static final COSName PARAMS = new COSName("Params");
/* 432 */   public static final COSName PARENT = new COSName("Parent");
/* 433 */   public static final COSName PARENT_TREE = new COSName("ParentTree");
/* 434 */   public static final COSName PARENT_TREE_NEXT_KEY = new COSName("ParentTreeNextKey");
/* 435 */   public static final COSName PATH = new COSName("Path");
/* 436 */   public static final COSName PATTERN = new COSName("Pattern");
/* 437 */   public static final COSName PATTERN_TYPE = new COSName("PatternType");
/* 438 */   public static final COSName PDF_DOC_ENCODING = new COSName("PDFDocEncoding");
/* 439 */   public static final COSName PERMS = new COSName("Perms");
/* 440 */   public static final COSName PG = new COSName("Pg");
/* 441 */   public static final COSName PRE_RELEASE = new COSName("PreRelease");
/* 442 */   public static final COSName PREDICTOR = new COSName("Predictor");
/* 443 */   public static final COSName PREV = new COSName("Prev");
/* 444 */   public static final COSName PRINT_AREA = new COSName("PrintArea");
/* 445 */   public static final COSName PRINT_CLIP = new COSName("PrintClip");
/* 446 */   public static final COSName PRINT_SCALING = new COSName("PrintScaling");
/* 447 */   public static final COSName PROC_SET = new COSName("ProcSet");
/* 448 */   public static final COSName PROCESS = new COSName("Process");
/* 449 */   public static final COSName PRODUCER = new COSName("Producer");
/* 450 */   public static final COSName PROP_BUILD = new COSName("Prop_Build");
/* 451 */   public static final COSName PROPERTIES = new COSName("Properties");
/* 452 */   public static final COSName PS = new COSName("PS");
/* 453 */   public static final COSName PUB_SEC = new COSName("PubSec");
/*     */   
/* 455 */   public static final COSName Q = new COSName("Q");
/* 456 */   public static final COSName QUADPOINTS = new COSName("QuadPoints");
/*     */   
/* 458 */   public static final COSName R = new COSName("R");
/* 459 */   public static final COSName RANGE = new COSName("Range");
/* 460 */   public static final COSName RC = new COSName("RC");
/* 461 */   public static final COSName RD = new COSName("RD");
/* 462 */   public static final COSName REASON = new COSName("Reason");
/* 463 */   public static final COSName REASONS = new COSName("Reasons");
/* 464 */   public static final COSName REPEAT = new COSName("Repeat");
/* 465 */   public static final COSName RECIPIENTS = new COSName("Recipients");
/* 466 */   public static final COSName RECT = new COSName("Rect");
/* 467 */   public static final COSName REGISTRY = new COSName("Registry");
/* 468 */   public static final COSName REGISTRY_NAME = new COSName("RegistryName");
/* 469 */   public static final COSName RENAME = new COSName("Rename");
/* 470 */   public static final COSName RESOURCES = new COSName("Resources");
/* 471 */   public static final COSName RGB = new COSName("RGB");
/* 472 */   public static final COSName RI = new COSName("RI");
/* 473 */   public static final COSName ROLE_MAP = new COSName("RoleMap");
/* 474 */   public static final COSName ROOT = new COSName("Root");
/* 475 */   public static final COSName ROTATE = new COSName("Rotate");
/* 476 */   public static final COSName ROWS = new COSName("Rows");
/* 477 */   public static final COSName RUN_LENGTH_DECODE = new COSName("RunLengthDecode");
/* 478 */   public static final COSName RUN_LENGTH_DECODE_ABBREVIATION = new COSName("RL");
/* 479 */   public static final COSName RV = new COSName("RV");
/*     */   
/* 481 */   public static final COSName S = new COSName("S");
/* 482 */   public static final COSName SA = new COSName("SA");
/* 483 */   public static final COSName SATURATION = new COSName("Saturation");
/* 484 */   public static final COSName SCHEMA = new COSName("Schema");
/* 485 */   public static final COSName SCREEN = new COSName("Screen");
/* 486 */   public static final COSName SE = new COSName("SE");
/* 487 */   public static final COSName SEPARATION = new COSName("Separation");
/* 488 */   public static final COSName SET_F = new COSName("SetF");
/* 489 */   public static final COSName SET_FF = new COSName("SetFf");
/* 490 */   public static final COSName SHADING = new COSName("Shading");
/* 491 */   public static final COSName SHADING_TYPE = new COSName("ShadingType");
/* 492 */   public static final COSName SIG = new COSName("Sig");
/* 493 */   public static final COSName SIG_FLAGS = new COSName("SigFlags");
/* 494 */   public static final COSName SIZE = new COSName("Size");
/* 495 */   public static final COSName SM = new COSName("SM");
/* 496 */   public static final COSName SMASK = new COSName("SMask");
/* 497 */   public static final COSName SOFT_LIGHT = new COSName("SoftLight");
/* 498 */   public static final COSName SORT = new COSName("Sort");
/* 499 */   public static final COSName SOUND = new COSName("Sound");
/* 500 */   public static final COSName SPLIT = new COSName("Split");
/* 501 */   public static final COSName SS = new COSName("SS");
/* 502 */   public static final COSName ST = new COSName("St");
/* 503 */   public static final COSName STANDARD_ENCODING = new COSName("StandardEncoding");
/* 504 */   public static final COSName STATE = new COSName("State");
/* 505 */   public static final COSName STATE_MODEL = new COSName("StateModel");
/* 506 */   public static final COSName STATUS = new COSName("Status");
/* 507 */   public static final COSName STD_CF = new COSName("StdCF");
/* 508 */   public static final COSName STEM_H = new COSName("StemH");
/* 509 */   public static final COSName STEM_V = new COSName("StemV");
/* 510 */   public static final COSName STM_F = new COSName("StmF");
/* 511 */   public static final COSName STR_F = new COSName("StrF");
/* 512 */   public static final COSName STRUCT_ELEM = new COSName("StructElem");
/* 513 */   public static final COSName STRUCT_PARENT = new COSName("StructParent");
/* 514 */   public static final COSName STRUCT_PARENTS = new COSName("StructParents");
/* 515 */   public static final COSName STRUCT_TREE_ROOT = new COSName("StructTreeRoot");
/* 516 */   public static final COSName STYLE = new COSName("Style");
/* 517 */   public static final COSName SUB_FILTER = new COSName("SubFilter");
/* 518 */   public static final COSName SUBJ = new COSName("Subj");
/* 519 */   public static final COSName SUBJECT = new COSName("Subject");
/* 520 */   public static final COSName SUBJECT_DN = new COSName("SubjectDN");
/* 521 */   public static final COSName SUBTYPE = new COSName("Subtype");
/* 522 */   public static final COSName SUPPLEMENT = new COSName("Supplement");
/* 523 */   public static final COSName SV = new COSName("SV");
/* 524 */   public static final COSName SV_CERT = new COSName("SVCert");
/* 525 */   public static final COSName SW = new COSName("SW");
/* 526 */   public static final COSName SY = new COSName("Sy");
/* 527 */   public static final COSName SYNCHRONOUS = new COSName("Synchronous");
/*     */   
/* 529 */   public static final COSName T = new COSName("T");
/* 530 */   public static final COSName TARGET = new COSName("Target");
/* 531 */   public static final COSName TEMPLATES = new COSName("Templates");
/* 532 */   public static final COSName THREADS = new COSName("Threads");
/* 533 */   public static final COSName THUMB = new COSName("Thumb");
/* 534 */   public static final COSName TI = new COSName("TI");
/* 535 */   public static final COSName TILING_TYPE = new COSName("TilingType");
/* 536 */   public static final COSName TIME_STAMP = new COSName("TimeStamp");
/* 537 */   public static final COSName TITLE = new COSName("Title");
/* 538 */   public static final COSName TK = new COSName("TK");
/* 539 */   public static final COSName TM = new COSName("TM");
/* 540 */   public static final COSName TO_UNICODE = new COSName("ToUnicode");
/* 541 */   public static final COSName TR = new COSName("TR");
/* 542 */   public static final COSName TR2 = new COSName("TR2");
/* 543 */   public static final COSName TRAPPED = new COSName("Trapped");
/* 544 */   public static final COSName TRANS = new COSName("Trans");
/* 545 */   public static final COSName TRANSPARENCY = new COSName("Transparency");
/* 546 */   public static final COSName TREF = new COSName("TRef");
/* 547 */   public static final COSName TRIM_BOX = new COSName("TrimBox");
/* 548 */   public static final COSName TRUE_TYPE = new COSName("TrueType");
/* 549 */   public static final COSName TRUSTED_MODE = new COSName("TrustedMode");
/* 550 */   public static final COSName TU = new COSName("TU");
/*     */   
/* 552 */   public static final COSName TX = new COSName("Tx");
/* 553 */   public static final COSName TYPE = new COSName("Type");
/* 554 */   public static final COSName TYPE0 = new COSName("Type0");
/* 555 */   public static final COSName TYPE1 = new COSName("Type1");
/* 556 */   public static final COSName TYPE3 = new COSName("Type3");
/*     */   
/* 558 */   public static final COSName U = new COSName("U");
/* 559 */   public static final COSName UE = new COSName("UE");
/* 560 */   public static final COSName UF = new COSName("UF");
/* 561 */   public static final COSName UNCHANGED = new COSName("Unchanged");
/* 562 */   public static final COSName UNIX = new COSName("Unix");
/* 563 */   public static final COSName URI = new COSName("URI");
/* 564 */   public static final COSName URL = new COSName("URL");
/* 565 */   public static final COSName URL_TYPE = new COSName("URLType");
/*     */   
/* 567 */   public static final COSName V = new COSName("V");
/* 568 */   public static final COSName VE = new COSName("VE");
/* 569 */   public static final COSName VERISIGN_PPKVS = new COSName("VeriSign.PPKVS");
/* 570 */   public static final COSName VERSION = new COSName("Version");
/* 571 */   public static final COSName VERTICES = new COSName("Vertices");
/* 572 */   public static final COSName VERTICES_PER_ROW = new COSName("VerticesPerRow");
/* 573 */   public static final COSName VIEW = new COSName("View");
/* 574 */   public static final COSName VIEW_AREA = new COSName("ViewArea");
/* 575 */   public static final COSName VIEW_CLIP = new COSName("ViewClip");
/* 576 */   public static final COSName VIEWER_PREFERENCES = new COSName("ViewerPreferences");
/* 577 */   public static final COSName VOLUME = new COSName("Volume");
/* 578 */   public static final COSName VP = new COSName("VP");
/*     */   
/* 580 */   public static final COSName W = new COSName("W");
/* 581 */   public static final COSName W2 = new COSName("W2");
/* 582 */   public static final COSName WHITE_POINT = new COSName("WhitePoint");
/* 583 */   public static final COSName WIDGET = new COSName("Widget");
/* 584 */   public static final COSName WIDTH = new COSName("Width");
/* 585 */   public static final COSName WIDTHS = new COSName("Widths");
/* 586 */   public static final COSName WIN_ANSI_ENCODING = new COSName("WinAnsiEncoding");
/*     */   
/* 588 */   public static final COSName XFA = new COSName("XFA");
/* 589 */   public static final COSName X_STEP = new COSName("XStep");
/* 590 */   public static final COSName XHEIGHT = new COSName("XHeight");
/* 591 */   public static final COSName XOBJECT = new COSName("XObject");
/* 592 */   public static final COSName XREF = new COSName("XRef");
/* 593 */   public static final COSName XREF_STM = new COSName("XRefStm");
/*     */   
/* 595 */   public static final COSName Y_STEP = new COSName("YStep");
/* 596 */   public static final COSName YES = new COSName("Yes");
/*     */ 
/*     */   
/* 599 */   public static final COSName ZA_DB = new COSName("ZaDb");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String name;
/*     */ 
/*     */ 
/*     */   
/*     */   private final int hashCode;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static COSName getPDFName(String aName) {
/* 614 */     COSName name = null;
/* 615 */     if (aName != null) {
/*     */ 
/*     */       
/* 618 */       name = commonNameMap.get(aName);
/* 619 */       if (name == null) {
/*     */ 
/*     */         
/* 622 */         name = nameMap.get(aName);
/* 623 */         if (name == null)
/*     */         {
/*     */           
/* 626 */           name = new COSName(aName, false);
/*     */         }
/*     */       } 
/*     */     } 
/* 630 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSName(String aName, boolean staticValue) {
/* 642 */     this.name = aName;
/* 643 */     if (staticValue) {
/*     */       
/* 645 */       commonNameMap.put(aName, this);
/*     */     }
/*     */     else {
/*     */       
/* 649 */       nameMap.put(aName, this);
/*     */     } 
/* 651 */     this.hashCode = this.name.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSName(String aName) {
/* 661 */     this(aName, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 671 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 677 */     return "COSName{" + this.name + "}";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 683 */     return (object instanceof COSName && this.name.equals(((COSName)object).name));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 689 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(COSName other) {
/* 695 */     return this.name.compareTo(other.name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 704 */     return this.name.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object accept(ICOSVisitor visitor) throws IOException {
/* 710 */     return visitor.visitFromName(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePDF(OutputStream output) throws IOException {
/* 721 */     output.write(47);
/* 722 */     byte[] bytes = getName().getBytes(Charsets.UTF_8);
/* 723 */     for (byte b : bytes) {
/*     */       
/* 725 */       int current = b & 0xFF;
/*     */ 
/*     */       
/* 728 */       if ((current >= 65 && current <= 90) || (current >= 97 && current <= 122) || (current >= 48 && current <= 57) || current == 43 || current == 45 || current == 95 || current == 64 || current == 42 || current == 36 || current == 59 || current == 46) {
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
/* 740 */         output.write(current);
/*     */       }
/*     */       else {
/*     */         
/* 744 */         output.write(35);
/* 745 */         Hex.writeHexByte(b, output);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void clearResources() {
/* 756 */     nameMap.clear();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/COSName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */