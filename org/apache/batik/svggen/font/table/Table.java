package org.apache.batik.svggen.font.table;

public interface Table {
  public static final int BASE = 1111577413;
  
  public static final int CFF = 1128678944;
  
  public static final int DSIG = 1146308935;
  
  public static final int EBDT = 1161970772;
  
  public static final int EBLC = 1161972803;
  
  public static final int EBSC = 1161974595;
  
  public static final int GDEF = 1195656518;
  
  public static final int GPOS = 1196445523;
  
  public static final int GSUB = 1196643650;
  
  public static final int JSTF = 1246975046;
  
  public static final int LTSH = 1280594760;
  
  public static final int MMFX = 1296909912;
  
  public static final int MMSD = 1296913220;
  
  public static final int OS_2 = 1330851634;
  
  public static final int PCLT = 1346587732;
  
  public static final int VDMX = 1447316824;
  
  public static final int cmap = 1668112752;
  
  public static final int cvt = 1668707360;
  
  public static final int fpgm = 1718642541;
  
  public static final int fvar = 1719034226;
  
  public static final int gasp = 1734439792;
  
  public static final int glyf = 1735162214;
  
  public static final int hdmx = 1751412088;
  
  public static final int head = 1751474532;
  
  public static final int hhea = 1751672161;
  
  public static final int hmtx = 1752003704;
  
  public static final int kern = 1801810542;
  
  public static final int loca = 1819239265;
  
  public static final int maxp = 1835104368;
  
  public static final int name = 1851878757;
  
  public static final int prep = 1886545264;
  
  public static final int post = 1886352244;
  
  public static final int vhea = 1986553185;
  
  public static final int vmtx = 1986884728;
  
  public static final short platformAppleUnicode = 0;
  
  public static final short platformMacintosh = 1;
  
  public static final short platformISO = 2;
  
  public static final short platformMicrosoft = 3;
  
  public static final short encodingUndefined = 0;
  
  public static final short encodingUGL = 1;
  
  public static final short encodingRoman = 0;
  
  public static final short encodingJapanese = 1;
  
  public static final short encodingChinese = 2;
  
  public static final short encodingKorean = 3;
  
  public static final short encodingArabic = 4;
  
  public static final short encodingHebrew = 5;
  
  public static final short encodingGreek = 6;
  
  public static final short encodingRussian = 7;
  
  public static final short encodingRSymbol = 8;
  
  public static final short encodingDevanagari = 9;
  
  public static final short encodingGurmukhi = 10;
  
  public static final short encodingGujarati = 11;
  
  public static final short encodingOriya = 12;
  
  public static final short encodingBengali = 13;
  
  public static final short encodingTamil = 14;
  
  public static final short encodingTelugu = 15;
  
  public static final short encodingKannada = 16;
  
  public static final short encodingMalayalam = 17;
  
  public static final short encodingSinhalese = 18;
  
  public static final short encodingBurmese = 19;
  
  public static final short encodingKhmer = 20;
  
  public static final short encodingThai = 21;
  
  public static final short encodingLaotian = 22;
  
  public static final short encodingGeorgian = 23;
  
  public static final short encodingArmenian = 24;
  
  public static final short encodingMaldivian = 25;
  
  public static final short encodingTibetan = 26;
  
  public static final short encodingMongolian = 27;
  
  public static final short encodingGeez = 28;
  
  public static final short encodingSlavic = 29;
  
  public static final short encodingVietnamese = 30;
  
  public static final short encodingSindhi = 31;
  
  public static final short encodingUninterp = 32;
  
  public static final short encodingASCII = 0;
  
  public static final short encodingISO10646 = 1;
  
  public static final short encodingISO8859_1 = 2;
  
  public static final short languageSQI = 1052;
  
  public static final short languageEUQ = 1069;
  
  public static final short languageBEL = 1059;
  
  public static final short languageBGR = 1026;
  
  public static final short languageCAT = 1027;
  
  public static final short languageSHL = 1050;
  
  public static final short languageCSY = 1029;
  
  public static final short languageDAN = 1030;
  
  public static final short languageNLD = 1043;
  
  public static final short languageNLB = 2067;
  
  public static final short languageENU = 1033;
  
  public static final short languageENG = 2057;
  
  public static final short languageENA = 3081;
  
  public static final short languageENC = 4105;
  
  public static final short languageENZ = 5129;
  
  public static final short languageENI = 6153;
  
  public static final short languageETI = 1061;
  
  public static final short languageFIN = 1035;
  
  public static final short languageFRA = 1036;
  
  public static final short languageFRB = 2060;
  
  public static final short languageFRC = 3084;
  
  public static final short languageFRS = 4108;
  
  public static final short languageFRL = 5132;
  
  public static final short languageDEU = 1031;
  
  public static final short languageDES = 2055;
  
  public static final short languageDEA = 3079;
  
  public static final short languageDEL = 4103;
  
  public static final short languageDEC = 5127;
  
  public static final short languageELL = 1032;
  
  public static final short languageHUN = 1038;
  
  public static final short languageISL = 1039;
  
  public static final short languageITA = 1040;
  
  public static final short languageITS = 2064;
  
  public static final short languageLVI = 1062;
  
  public static final short languageLTH = 1063;
  
  public static final short languageNOR = 1044;
  
  public static final short languageNON = 2068;
  
  public static final short languagePLK = 1045;
  
  public static final short languagePTB = 1046;
  
  public static final short languagePTG = 2070;
  
  public static final short languageROM = 1048;
  
  public static final short languageRUS = 1049;
  
  public static final short languageSKY = 1051;
  
  public static final short languageSLV = 1060;
  
  public static final short languageESP = 1034;
  
  public static final short languageESM = 2058;
  
  public static final short languageESN = 3082;
  
  public static final short languageSVE = 1053;
  
  public static final short languageTRK = 1055;
  
  public static final short languageUKR = 1058;
  
  public static final short languageEnglish = 0;
  
  public static final short languageFrench = 1;
  
  public static final short languageGerman = 2;
  
  public static final short languageItalian = 3;
  
  public static final short languageDutch = 4;
  
  public static final short languageSwedish = 5;
  
  public static final short languageSpanish = 6;
  
  public static final short languageDanish = 7;
  
  public static final short languagePortuguese = 8;
  
  public static final short languageNorwegian = 9;
  
  public static final short languageHebrew = 10;
  
  public static final short languageJapanese = 11;
  
  public static final short languageArabic = 12;
  
  public static final short languageFinnish = 13;
  
  public static final short languageGreek = 14;
  
  public static final short languageIcelandic = 15;
  
  public static final short languageMaltese = 16;
  
  public static final short languageTurkish = 17;
  
  public static final short languageYugoslavian = 18;
  
  public static final short languageChinese = 19;
  
  public static final short languageUrdu = 20;
  
  public static final short languageHindi = 21;
  
  public static final short languageThai = 22;
  
  public static final short nameCopyrightNotice = 0;
  
  public static final short nameFontFamilyName = 1;
  
  public static final short nameFontSubfamilyName = 2;
  
  public static final short nameUniqueFontIdentifier = 3;
  
  public static final short nameFullFontName = 4;
  
  public static final short nameVersionString = 5;
  
  public static final short namePostscriptName = 6;
  
  public static final short nameTrademark = 7;
  
  int getType();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/Table.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */