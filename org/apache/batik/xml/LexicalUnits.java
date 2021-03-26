package org.apache.batik.xml;

public interface LexicalUnits {
  public static final int EOF = 0;
  
  public static final int S = 1;
  
  public static final int XML_DECL_START = 2;
  
  public static final int DOCTYPE_START = 3;
  
  public static final int COMMENT = 4;
  
  public static final int PI_START = 5;
  
  public static final int PI_DATA = 6;
  
  public static final int PI_END = 7;
  
  public static final int CHARACTER_DATA = 8;
  
  public static final int START_TAG = 9;
  
  public static final int END_TAG = 10;
  
  public static final int CDATA_START = 11;
  
  public static final int CHARACTER_REFERENCE = 12;
  
  public static final int ENTITY_REFERENCE = 13;
  
  public static final int NAME = 14;
  
  public static final int EQ = 15;
  
  public static final int FIRST_ATTRIBUTE_FRAGMENT = 16;
  
  public static final int ATTRIBUTE_FRAGMENT = 17;
  
  public static final int LAST_ATTRIBUTE_FRAGMENT = 18;
  
  public static final int EMPTY_ELEMENT_END = 19;
  
  public static final int END_CHAR = 20;
  
  public static final int SECTION_END = 21;
  
  public static final int VERSION_IDENTIFIER = 22;
  
  public static final int ENCODING_IDENTIFIER = 23;
  
  public static final int STANDALONE_IDENTIFIER = 24;
  
  public static final int STRING = 25;
  
  public static final int SYSTEM_IDENTIFIER = 26;
  
  public static final int PUBLIC_IDENTIFIER = 27;
  
  public static final int LSQUARE_BRACKET = 28;
  
  public static final int RSQUARE_BRACKET = 29;
  
  public static final int ELEMENT_DECLARATION_START = 30;
  
  public static final int ATTLIST_START = 31;
  
  public static final int ENTITY_START = 32;
  
  public static final int NOTATION_START = 33;
  
  public static final int PARAMETER_ENTITY_REFERENCE = 34;
  
  public static final int EMPTY_IDENTIFIER = 35;
  
  public static final int ANY_IDENTIFIER = 36;
  
  public static final int QUESTION = 37;
  
  public static final int PLUS = 38;
  
  public static final int STAR = 39;
  
  public static final int LEFT_BRACE = 40;
  
  public static final int RIGHT_BRACE = 41;
  
  public static final int PIPE = 42;
  
  public static final int COMMA = 43;
  
  public static final int PCDATA_IDENTIFIER = 44;
  
  public static final int CDATA_IDENTIFIER = 45;
  
  public static final int ID_IDENTIFIER = 46;
  
  public static final int IDREF_IDENTIFIER = 47;
  
  public static final int IDREFS_IDENTIFIER = 48;
  
  public static final int NMTOKEN_IDENTIFIER = 49;
  
  public static final int NMTOKENS_IDENTIFIER = 50;
  
  public static final int ENTITY_IDENTIFIER = 51;
  
  public static final int ENTITIES_IDENTIFIER = 52;
  
  public static final int REQUIRED_IDENTIFIER = 53;
  
  public static final int IMPLIED_IDENTIFIER = 54;
  
  public static final int FIXED_IDENTIFIER = 55;
  
  public static final int NMTOKEN = 56;
  
  public static final int NOTATION_IDENTIFIER = 57;
  
  public static final int PERCENT = 58;
  
  public static final int NDATA_IDENTIFIER = 59;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/xml/LexicalUnits.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */