/*      */ package org.apache.fontbox.afm;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.StringTokenizer;
/*      */ import org.apache.fontbox.util.BoundingBox;
/*      */ import org.apache.fontbox.util.Charsets;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AFMParser
/*      */ {
/*      */   public static final String COMMENT = "Comment";
/*      */   public static final String START_FONT_METRICS = "StartFontMetrics";
/*      */   public static final String END_FONT_METRICS = "EndFontMetrics";
/*      */   public static final String FONT_NAME = "FontName";
/*      */   public static final String FULL_NAME = "FullName";
/*      */   public static final String FAMILY_NAME = "FamilyName";
/*      */   public static final String WEIGHT = "Weight";
/*      */   public static final String FONT_BBOX = "FontBBox";
/*      */   public static final String VERSION = "Version";
/*      */   public static final String NOTICE = "Notice";
/*      */   public static final String ENCODING_SCHEME = "EncodingScheme";
/*      */   public static final String MAPPING_SCHEME = "MappingScheme";
/*      */   public static final String ESC_CHAR = "EscChar";
/*      */   public static final String CHARACTER_SET = "CharacterSet";
/*      */   public static final String CHARACTERS = "Characters";
/*      */   public static final String IS_BASE_FONT = "IsBaseFont";
/*      */   public static final String V_VECTOR = "VVector";
/*      */   public static final String IS_FIXED_V = "IsFixedV";
/*      */   public static final String CAP_HEIGHT = "CapHeight";
/*      */   public static final String X_HEIGHT = "XHeight";
/*      */   public static final String ASCENDER = "Ascender";
/*      */   public static final String DESCENDER = "Descender";
/*      */   public static final String UNDERLINE_POSITION = "UnderlinePosition";
/*      */   public static final String UNDERLINE_THICKNESS = "UnderlineThickness";
/*      */   public static final String ITALIC_ANGLE = "ItalicAngle";
/*      */   public static final String CHAR_WIDTH = "CharWidth";
/*      */   public static final String IS_FIXED_PITCH = "IsFixedPitch";
/*      */   public static final String START_CHAR_METRICS = "StartCharMetrics";
/*      */   public static final String END_CHAR_METRICS = "EndCharMetrics";
/*      */   public static final String CHARMETRICS_C = "C";
/*      */   public static final String CHARMETRICS_CH = "CH";
/*      */   public static final String CHARMETRICS_WX = "WX";
/*      */   public static final String CHARMETRICS_W0X = "W0X";
/*      */   public static final String CHARMETRICS_W1X = "W1X";
/*      */   public static final String CHARMETRICS_WY = "WY";
/*      */   public static final String CHARMETRICS_W0Y = "W0Y";
/*      */   public static final String CHARMETRICS_W1Y = "W1Y";
/*      */   public static final String CHARMETRICS_W = "W";
/*      */   public static final String CHARMETRICS_W0 = "W0";
/*      */   public static final String CHARMETRICS_W1 = "W1";
/*      */   public static final String CHARMETRICS_VV = "VV";
/*      */   public static final String CHARMETRICS_N = "N";
/*      */   public static final String CHARMETRICS_B = "B";
/*      */   public static final String CHARMETRICS_L = "L";
/*      */   public static final String STD_HW = "StdHW";
/*      */   public static final String STD_VW = "StdVW";
/*      */   public static final String START_TRACK_KERN = "StartTrackKern";
/*      */   public static final String END_TRACK_KERN = "EndTrackKern";
/*      */   public static final String START_KERN_DATA = "StartKernData";
/*      */   public static final String END_KERN_DATA = "EndKernData";
/*      */   public static final String START_KERN_PAIRS = "StartKernPairs";
/*      */   public static final String END_KERN_PAIRS = "EndKernPairs";
/*      */   public static final String START_KERN_PAIRS0 = "StartKernPairs0";
/*      */   public static final String START_KERN_PAIRS1 = "StartKernPairs1";
/*      */   public static final String START_COMPOSITES = "StartComposites";
/*      */   public static final String END_COMPOSITES = "EndComposites";
/*      */   public static final String CC = "CC";
/*      */   public static final String PCC = "PCC";
/*      */   public static final String KERN_PAIR_KP = "KP";
/*      */   public static final String KERN_PAIR_KPH = "KPH";
/*      */   public static final String KERN_PAIR_KPX = "KPX";
/*      */   public static final String KERN_PAIR_KPY = "KPY";
/*      */   private static final int BITS_IN_HEX = 16;
/*      */   private final InputStream input;
/*      */   
/*      */   public AFMParser(InputStream in) {
/*  300 */     this.input = in;
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
/*      */   public FontMetrics parse() throws IOException {
/*  313 */     return parseFontMetric(false);
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
/*      */   public FontMetrics parse(boolean reducedDataset) throws IOException {
/*  327 */     return parseFontMetric(reducedDataset);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private FontMetrics parseFontMetric(boolean reducedDataset) throws IOException {
/*  338 */     FontMetrics fontMetrics = new FontMetrics();
/*  339 */     String startFontMetrics = readString();
/*  340 */     if (!"StartFontMetrics".equals(startFontMetrics))
/*      */     {
/*  342 */       throw new IOException("Error: The AFM file should start with StartFontMetrics and not '" + startFontMetrics + "'");
/*      */     }
/*      */     
/*  345 */     fontMetrics.setAFMVersion(readFloat());
/*      */     
/*  347 */     boolean charMetricsRead = false; String nextCommand;
/*  348 */     while (!"EndFontMetrics".equals(nextCommand = readString())) {
/*      */       
/*  350 */       if ("FontName".equals(nextCommand)) {
/*      */         
/*  352 */         fontMetrics.setFontName(readLine()); continue;
/*      */       } 
/*  354 */       if ("FullName".equals(nextCommand)) {
/*      */         
/*  356 */         fontMetrics.setFullName(readLine()); continue;
/*      */       } 
/*  358 */       if ("FamilyName".equals(nextCommand)) {
/*      */         
/*  360 */         fontMetrics.setFamilyName(readLine()); continue;
/*      */       } 
/*  362 */       if ("Weight".equals(nextCommand)) {
/*      */         
/*  364 */         fontMetrics.setWeight(readLine()); continue;
/*      */       } 
/*  366 */       if ("FontBBox".equals(nextCommand)) {
/*      */         
/*  368 */         BoundingBox bBox = new BoundingBox();
/*  369 */         bBox.setLowerLeftX(readFloat());
/*  370 */         bBox.setLowerLeftY(readFloat());
/*  371 */         bBox.setUpperRightX(readFloat());
/*  372 */         bBox.setUpperRightY(readFloat());
/*  373 */         fontMetrics.setFontBBox(bBox); continue;
/*      */       } 
/*  375 */       if ("Version".equals(nextCommand)) {
/*      */         
/*  377 */         fontMetrics.setFontVersion(readLine()); continue;
/*      */       } 
/*  379 */       if ("Notice".equals(nextCommand)) {
/*      */         
/*  381 */         fontMetrics.setNotice(readLine()); continue;
/*      */       } 
/*  383 */       if ("EncodingScheme".equals(nextCommand)) {
/*      */         
/*  385 */         fontMetrics.setEncodingScheme(readLine()); continue;
/*      */       } 
/*  387 */       if ("MappingScheme".equals(nextCommand)) {
/*      */         
/*  389 */         fontMetrics.setMappingScheme(readInt()); continue;
/*      */       } 
/*  391 */       if ("EscChar".equals(nextCommand)) {
/*      */         
/*  393 */         fontMetrics.setEscChar(readInt()); continue;
/*      */       } 
/*  395 */       if ("CharacterSet".equals(nextCommand)) {
/*      */         
/*  397 */         fontMetrics.setCharacterSet(readLine()); continue;
/*      */       } 
/*  399 */       if ("Characters".equals(nextCommand)) {
/*      */         
/*  401 */         fontMetrics.setCharacters(readInt()); continue;
/*      */       } 
/*  403 */       if ("IsBaseFont".equals(nextCommand)) {
/*      */         
/*  405 */         fontMetrics.setIsBaseFont(readBoolean()); continue;
/*      */       } 
/*  407 */       if ("VVector".equals(nextCommand)) {
/*      */         
/*  409 */         float[] vector = new float[2];
/*  410 */         vector[0] = readFloat();
/*  411 */         vector[1] = readFloat();
/*  412 */         fontMetrics.setVVector(vector); continue;
/*      */       } 
/*  414 */       if ("IsFixedV".equals(nextCommand)) {
/*      */         
/*  416 */         fontMetrics.setIsFixedV(readBoolean()); continue;
/*      */       } 
/*  418 */       if ("CapHeight".equals(nextCommand)) {
/*      */         
/*  420 */         fontMetrics.setCapHeight(readFloat()); continue;
/*      */       } 
/*  422 */       if ("XHeight".equals(nextCommand)) {
/*      */         
/*  424 */         fontMetrics.setXHeight(readFloat()); continue;
/*      */       } 
/*  426 */       if ("Ascender".equals(nextCommand)) {
/*      */         
/*  428 */         fontMetrics.setAscender(readFloat()); continue;
/*      */       } 
/*  430 */       if ("Descender".equals(nextCommand)) {
/*      */         
/*  432 */         fontMetrics.setDescender(readFloat()); continue;
/*      */       } 
/*  434 */       if ("StdHW".equals(nextCommand)) {
/*      */         
/*  436 */         fontMetrics.setStandardHorizontalWidth(readFloat()); continue;
/*      */       } 
/*  438 */       if ("StdVW".equals(nextCommand)) {
/*      */         
/*  440 */         fontMetrics.setStandardVerticalWidth(readFloat()); continue;
/*      */       } 
/*  442 */       if ("Comment".equals(nextCommand)) {
/*      */         
/*  444 */         fontMetrics.addComment(readLine()); continue;
/*      */       } 
/*  446 */       if ("UnderlinePosition".equals(nextCommand)) {
/*      */         
/*  448 */         fontMetrics.setUnderlinePosition(readFloat()); continue;
/*      */       } 
/*  450 */       if ("UnderlineThickness".equals(nextCommand)) {
/*      */         
/*  452 */         fontMetrics.setUnderlineThickness(readFloat()); continue;
/*      */       } 
/*  454 */       if ("ItalicAngle".equals(nextCommand)) {
/*      */         
/*  456 */         fontMetrics.setItalicAngle(readFloat()); continue;
/*      */       } 
/*  458 */       if ("CharWidth".equals(nextCommand)) {
/*      */         
/*  460 */         float[] widths = new float[2];
/*  461 */         widths[0] = readFloat();
/*  462 */         widths[1] = readFloat();
/*  463 */         fontMetrics.setCharWidth(widths); continue;
/*      */       } 
/*  465 */       if ("IsFixedPitch".equals(nextCommand)) {
/*      */         
/*  467 */         fontMetrics.setFixedPitch(readBoolean()); continue;
/*      */       } 
/*  469 */       if ("StartCharMetrics".equals(nextCommand)) {
/*      */         
/*  471 */         int count = readInt();
/*  472 */         List<CharMetric> charMetrics = new ArrayList<CharMetric>(count);
/*  473 */         for (int i = 0; i < count; i++) {
/*      */           
/*  475 */           CharMetric charMetric = parseCharMetric();
/*  476 */           charMetrics.add(charMetric);
/*      */         } 
/*  478 */         String end = readString();
/*  479 */         if (!end.equals("EndCharMetrics"))
/*      */         {
/*  481 */           throw new IOException("Error: Expected 'EndCharMetrics' actual '" + end + "'");
/*      */         }
/*      */         
/*  484 */         charMetricsRead = true;
/*  485 */         fontMetrics.setCharMetrics(charMetrics); continue;
/*      */       } 
/*  487 */       if (!reducedDataset && "StartComposites".equals(nextCommand)) {
/*      */         
/*  489 */         int count = readInt();
/*  490 */         for (int i = 0; i < count; i++) {
/*      */           
/*  492 */           Composite part = parseComposite();
/*  493 */           fontMetrics.addComposite(part);
/*      */         } 
/*  495 */         String end = readString();
/*  496 */         if (!end.equals("EndComposites"))
/*      */         {
/*  498 */           throw new IOException("Error: Expected 'EndComposites' actual '" + end + "'");
/*      */         }
/*      */         continue;
/*      */       } 
/*  502 */       if (!reducedDataset && "StartKernData".equals(nextCommand)) {
/*      */         
/*  504 */         parseKernData(fontMetrics);
/*      */         
/*      */         continue;
/*      */       } 
/*  508 */       if (reducedDataset && charMetricsRead) {
/*      */         break;
/*      */       }
/*      */       
/*  512 */       throw new IOException("Unknown AFM key '" + nextCommand + "'");
/*      */     } 
/*      */     
/*  515 */     return fontMetrics;
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
/*      */   private void parseKernData(FontMetrics fontMetrics) throws IOException {
/*      */     String nextCommand;
/*  528 */     while (!(nextCommand = readString()).equals("EndKernData")) {
/*      */       
/*  530 */       if ("StartTrackKern".equals(nextCommand)) {
/*      */         
/*  532 */         int count = readInt();
/*  533 */         for (int i = 0; i < count; i++) {
/*      */           
/*  535 */           TrackKern kern = new TrackKern();
/*  536 */           kern.setDegree(readInt());
/*  537 */           kern.setMinPointSize(readFloat());
/*  538 */           kern.setMinKern(readFloat());
/*  539 */           kern.setMaxPointSize(readFloat());
/*  540 */           kern.setMaxKern(readFloat());
/*  541 */           fontMetrics.addTrackKern(kern);
/*      */         } 
/*  543 */         String end = readString();
/*  544 */         if (!end.equals("EndTrackKern"))
/*      */         {
/*  546 */           throw new IOException("Error: Expected 'EndTrackKern' actual '" + end + "'");
/*      */         }
/*      */         continue;
/*      */       } 
/*  550 */       if ("StartKernPairs".equals(nextCommand)) {
/*      */         
/*  552 */         int count = readInt();
/*  553 */         for (int i = 0; i < count; i++) {
/*      */           
/*  555 */           KernPair pair = parseKernPair();
/*  556 */           fontMetrics.addKernPair(pair);
/*      */         } 
/*  558 */         String end = readString();
/*  559 */         if (!end.equals("EndKernPairs"))
/*      */         {
/*  561 */           throw new IOException("Error: Expected 'EndKernPairs' actual '" + end + "'");
/*      */         }
/*      */         continue;
/*      */       } 
/*  565 */       if ("StartKernPairs0".equals(nextCommand)) {
/*      */         
/*  567 */         int count = readInt();
/*  568 */         for (int i = 0; i < count; i++) {
/*      */           
/*  570 */           KernPair pair = parseKernPair();
/*  571 */           fontMetrics.addKernPair0(pair);
/*      */         } 
/*  573 */         String end = readString();
/*  574 */         if (!end.equals("EndKernPairs"))
/*      */         {
/*  576 */           throw new IOException("Error: Expected 'EndKernPairs' actual '" + end + "'");
/*      */         }
/*      */         continue;
/*      */       } 
/*  580 */       if ("StartKernPairs1".equals(nextCommand)) {
/*      */         
/*  582 */         int count = readInt();
/*  583 */         for (int i = 0; i < count; i++) {
/*      */           
/*  585 */           KernPair pair = parseKernPair();
/*  586 */           fontMetrics.addKernPair1(pair);
/*      */         } 
/*  588 */         String end = readString();
/*  589 */         if (!end.equals("EndKernPairs"))
/*      */         {
/*  591 */           throw new IOException("Error: Expected 'EndKernPairs' actual '" + end + "'");
/*      */         }
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/*  597 */       throw new IOException("Unknown kerning data type '" + nextCommand + "'");
/*      */     } 
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
/*      */   private KernPair parseKernPair() throws IOException {
/*  611 */     KernPair kernPair = new KernPair();
/*  612 */     String cmd = readString();
/*  613 */     if ("KP".equals(cmd)) {
/*      */       
/*  615 */       kernPair.setFirstKernCharacter(readString());
/*  616 */       kernPair.setSecondKernCharacter(readString());
/*  617 */       kernPair.setX(readFloat());
/*  618 */       kernPair.setY(readFloat());
/*      */     }
/*  620 */     else if ("KPH".equals(cmd)) {
/*      */       
/*  622 */       kernPair.setFirstKernCharacter(hexToString(readString()));
/*  623 */       kernPair.setSecondKernCharacter(hexToString(readString()));
/*  624 */       kernPair.setX(readFloat());
/*  625 */       kernPair.setY(readFloat());
/*      */     }
/*  627 */     else if ("KPX".equals(cmd)) {
/*      */       
/*  629 */       kernPair.setFirstKernCharacter(readString());
/*  630 */       kernPair.setSecondKernCharacter(readString());
/*  631 */       kernPair.setX(readFloat());
/*  632 */       kernPair.setY(0.0F);
/*      */     }
/*  634 */     else if ("KPY".equals(cmd)) {
/*      */       
/*  636 */       kernPair.setFirstKernCharacter(readString());
/*  637 */       kernPair.setSecondKernCharacter(readString());
/*  638 */       kernPair.setX(0.0F);
/*  639 */       kernPair.setY(readFloat());
/*      */     }
/*      */     else {
/*      */       
/*  643 */       throw new IOException("Error expected kern pair command actual='" + cmd + "'");
/*      */     } 
/*  645 */     return kernPair;
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
/*      */   private String hexToString(String hexString) throws IOException {
/*  659 */     if (hexString.length() < 2)
/*      */     {
/*  661 */       throw new IOException("Error: Expected hex string of length >= 2 not='" + hexString);
/*      */     }
/*  663 */     if (hexString.charAt(0) != '<' || hexString
/*  664 */       .charAt(hexString.length() - 1) != '>')
/*      */     {
/*  666 */       throw new IOException("String should be enclosed by angle brackets '" + hexString + "'");
/*      */     }
/*  668 */     hexString = hexString.substring(1, hexString.length() - 1);
/*  669 */     byte[] data = new byte[hexString.length() / 2];
/*  670 */     for (int i = 0; i < hexString.length(); i += 2) {
/*      */       
/*  672 */       String hex = Character.toString(hexString.charAt(i)) + hexString.charAt(i + 1);
/*      */       
/*      */       try {
/*  675 */         data[i / 2] = (byte)Integer.parseInt(hex, 16);
/*      */       }
/*  677 */       catch (NumberFormatException e) {
/*      */         
/*  679 */         throw new IOException("Error parsing AFM file:" + e);
/*      */       } 
/*      */     } 
/*  682 */     return new String(data, Charsets.ISO_8859_1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Composite parseComposite() throws IOException {
/*      */     int partCount;
/*  694 */     Composite composite = new Composite();
/*  695 */     String partData = readLine();
/*  696 */     StringTokenizer tokenizer = new StringTokenizer(partData, " ;");
/*      */ 
/*      */     
/*  699 */     String cc = tokenizer.nextToken();
/*  700 */     if (!cc.equals("CC"))
/*      */     {
/*  702 */       throw new IOException("Expected 'CC' actual='" + cc + "'");
/*      */     }
/*  704 */     String name = tokenizer.nextToken();
/*  705 */     composite.setName(name);
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  710 */       partCount = Integer.parseInt(tokenizer.nextToken());
/*      */     }
/*  712 */     catch (NumberFormatException e) {
/*      */       
/*  714 */       throw new IOException("Error parsing AFM document:" + e);
/*      */     } 
/*  716 */     for (int i = 0; i < partCount; i++) {
/*      */       
/*  718 */       CompositePart part = new CompositePart();
/*  719 */       String pcc = tokenizer.nextToken();
/*  720 */       if (!pcc.equals("PCC"))
/*      */       {
/*  722 */         throw new IOException("Expected 'PCC' actual='" + pcc + "'");
/*      */       }
/*  724 */       String partName = tokenizer.nextToken();
/*      */       
/*      */       try {
/*  727 */         int x = Integer.parseInt(tokenizer.nextToken());
/*  728 */         int y = Integer.parseInt(tokenizer.nextToken());
/*      */         
/*  730 */         part.setName(partName);
/*  731 */         part.setXDisplacement(x);
/*  732 */         part.setYDisplacement(y);
/*  733 */         composite.addPart(part);
/*      */       }
/*  735 */       catch (NumberFormatException e) {
/*      */         
/*  737 */         throw new IOException("Error parsing AFM document:" + e);
/*      */       } 
/*      */     } 
/*  740 */     return composite;
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
/*      */   private CharMetric parseCharMetric() throws IOException {
/*  752 */     CharMetric charMetric = new CharMetric();
/*  753 */     String metrics = readLine();
/*  754 */     StringTokenizer metricsTokenizer = new StringTokenizer(metrics);
/*      */     
/*      */     try {
/*  757 */       while (metricsTokenizer.hasMoreTokens())
/*      */       {
/*  759 */         String nextCommand = metricsTokenizer.nextToken();
/*  760 */         if (nextCommand.equals("C")) {
/*      */           
/*  762 */           String charCode = metricsTokenizer.nextToken();
/*  763 */           charMetric.setCharacterCode(Integer.parseInt(charCode));
/*  764 */           verifySemicolon(metricsTokenizer); continue;
/*      */         } 
/*  766 */         if (nextCommand.equals("CH")) {
/*      */ 
/*      */ 
/*      */           
/*  770 */           String charCode = metricsTokenizer.nextToken();
/*  771 */           charMetric.setCharacterCode(Integer.parseInt(charCode, 16));
/*  772 */           verifySemicolon(metricsTokenizer); continue;
/*      */         } 
/*  774 */         if (nextCommand.equals("WX")) {
/*      */           
/*  776 */           charMetric.setWx(Float.parseFloat(metricsTokenizer.nextToken()));
/*  777 */           verifySemicolon(metricsTokenizer); continue;
/*      */         } 
/*  779 */         if (nextCommand.equals("W0X")) {
/*      */           
/*  781 */           charMetric.setW0x(Float.parseFloat(metricsTokenizer.nextToken()));
/*  782 */           verifySemicolon(metricsTokenizer); continue;
/*      */         } 
/*  784 */         if (nextCommand.equals("W1X")) {
/*      */           
/*  786 */           charMetric.setW1x(Float.parseFloat(metricsTokenizer.nextToken()));
/*  787 */           verifySemicolon(metricsTokenizer); continue;
/*      */         } 
/*  789 */         if (nextCommand.equals("WY")) {
/*      */           
/*  791 */           charMetric.setWy(Float.parseFloat(metricsTokenizer.nextToken()));
/*  792 */           verifySemicolon(metricsTokenizer); continue;
/*      */         } 
/*  794 */         if (nextCommand.equals("W0Y")) {
/*      */           
/*  796 */           charMetric.setW0y(Float.parseFloat(metricsTokenizer.nextToken()));
/*  797 */           verifySemicolon(metricsTokenizer); continue;
/*      */         } 
/*  799 */         if (nextCommand.equals("W1Y")) {
/*      */           
/*  801 */           charMetric.setW1y(Float.parseFloat(metricsTokenizer.nextToken()));
/*  802 */           verifySemicolon(metricsTokenizer); continue;
/*      */         } 
/*  804 */         if (nextCommand.equals("W")) {
/*      */           
/*  806 */           float[] w = new float[2];
/*  807 */           w[0] = Float.parseFloat(metricsTokenizer.nextToken());
/*  808 */           w[1] = Float.parseFloat(metricsTokenizer.nextToken());
/*  809 */           charMetric.setW(w);
/*  810 */           verifySemicolon(metricsTokenizer); continue;
/*      */         } 
/*  812 */         if (nextCommand.equals("W0")) {
/*      */           
/*  814 */           float[] w0 = new float[2];
/*  815 */           w0[0] = Float.parseFloat(metricsTokenizer.nextToken());
/*  816 */           w0[1] = Float.parseFloat(metricsTokenizer.nextToken());
/*  817 */           charMetric.setW0(w0);
/*  818 */           verifySemicolon(metricsTokenizer); continue;
/*      */         } 
/*  820 */         if (nextCommand.equals("W1")) {
/*      */           
/*  822 */           float[] w1 = new float[2];
/*  823 */           w1[0] = Float.parseFloat(metricsTokenizer.nextToken());
/*  824 */           w1[1] = Float.parseFloat(metricsTokenizer.nextToken());
/*  825 */           charMetric.setW1(w1);
/*  826 */           verifySemicolon(metricsTokenizer); continue;
/*      */         } 
/*  828 */         if (nextCommand.equals("VV")) {
/*      */           
/*  830 */           float[] vv = new float[2];
/*  831 */           vv[0] = Float.parseFloat(metricsTokenizer.nextToken());
/*  832 */           vv[1] = Float.parseFloat(metricsTokenizer.nextToken());
/*  833 */           charMetric.setVv(vv);
/*  834 */           verifySemicolon(metricsTokenizer); continue;
/*      */         } 
/*  836 */         if (nextCommand.equals("N")) {
/*      */           
/*  838 */           charMetric.setName(metricsTokenizer.nextToken());
/*  839 */           verifySemicolon(metricsTokenizer); continue;
/*      */         } 
/*  841 */         if (nextCommand.equals("B")) {
/*      */           
/*  843 */           BoundingBox box = new BoundingBox();
/*  844 */           box.setLowerLeftX(Float.parseFloat(metricsTokenizer.nextToken()));
/*  845 */           box.setLowerLeftY(Float.parseFloat(metricsTokenizer.nextToken()));
/*  846 */           box.setUpperRightX(Float.parseFloat(metricsTokenizer.nextToken()));
/*  847 */           box.setUpperRightY(Float.parseFloat(metricsTokenizer.nextToken()));
/*  848 */           charMetric.setBoundingBox(box);
/*  849 */           verifySemicolon(metricsTokenizer); continue;
/*      */         } 
/*  851 */         if (nextCommand.equals("L")) {
/*      */           
/*  853 */           Ligature lig = new Ligature();
/*  854 */           lig.setSuccessor(metricsTokenizer.nextToken());
/*  855 */           lig.setLigature(metricsTokenizer.nextToken());
/*  856 */           charMetric.addLigature(lig);
/*  857 */           verifySemicolon(metricsTokenizer);
/*      */           
/*      */           continue;
/*      */         } 
/*  861 */         throw new IOException("Unknown CharMetrics command '" + nextCommand + "'");
/*      */       }
/*      */     
/*      */     }
/*  865 */     catch (NumberFormatException e) {
/*      */       
/*  867 */       throw new IOException("Error: Corrupt AFM document:" + e);
/*      */     } 
/*  869 */     return charMetric;
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
/*      */   private void verifySemicolon(StringTokenizer tokenizer) throws IOException {
/*  881 */     if (tokenizer.hasMoreTokens()) {
/*      */       
/*  883 */       String semicolon = tokenizer.nextToken();
/*  884 */       if (!";".equals(semicolon))
/*      */       {
/*  886 */         throw new IOException("Error: Expected semicolon in stream actual='" + semicolon + "'");
/*      */       
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  892 */       throw new IOException("CharMetrics is missing a semicolon after a command");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean readBoolean() throws IOException {
/*  903 */     String theBoolean = readString();
/*  904 */     return Boolean.valueOf(theBoolean).booleanValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int readInt() throws IOException {
/*  914 */     String theInt = readString();
/*      */     
/*      */     try {
/*  917 */       return Integer.parseInt(theInt);
/*      */     }
/*  919 */     catch (NumberFormatException e) {
/*      */       
/*  921 */       throw new IOException("Error parsing AFM document:" + e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float readFloat() throws IOException {
/*  932 */     String theFloat = readString();
/*  933 */     return Float.parseFloat(theFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String readLine() throws IOException {
/*  944 */     StringBuilder buf = new StringBuilder(60);
/*  945 */     int nextByte = this.input.read();
/*  946 */     while (isWhitespace(nextByte))
/*      */     {
/*  948 */       nextByte = this.input.read();
/*      */     }
/*      */     
/*  951 */     buf.append((char)nextByte);
/*      */ 
/*      */     
/*  954 */     nextByte = this.input.read();
/*  955 */     while (nextByte != -1 && !isEOL(nextByte)) {
/*      */       
/*  957 */       buf.append((char)nextByte);
/*  958 */       nextByte = this.input.read();
/*      */     } 
/*  960 */     return buf.toString();
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
/*      */   private String readString() throws IOException {
/*  973 */     StringBuilder buf = new StringBuilder(24);
/*  974 */     int nextByte = this.input.read();
/*  975 */     while (isWhitespace(nextByte))
/*      */     {
/*  977 */       nextByte = this.input.read();
/*      */     }
/*      */     
/*  980 */     buf.append((char)nextByte);
/*      */ 
/*      */     
/*  983 */     nextByte = this.input.read();
/*  984 */     while (nextByte != -1 && !isWhitespace(nextByte)) {
/*      */       
/*  986 */       buf.append((char)nextByte);
/*  987 */       nextByte = this.input.read();
/*      */     } 
/*  989 */     return buf.toString();
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
/*      */   private boolean isEOL(int character) {
/* 1001 */     return (character == 13 || character == 10);
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
/*      */   private boolean isWhitespace(int character) {
/* 1014 */     return (character == 32 || character == 9 || character == 13 || character == 10);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/afm/AFMParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */