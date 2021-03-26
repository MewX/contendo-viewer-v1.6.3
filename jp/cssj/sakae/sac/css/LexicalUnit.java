package jp.cssj.sakae.sac.css;

public interface LexicalUnit {
  public static final short SAC_OPERATOR_COMMA = 0;
  
  public static final short SAC_OPERATOR_PLUS = 1;
  
  public static final short SAC_OPERATOR_MINUS = 2;
  
  public static final short SAC_OPERATOR_MULTIPLY = 3;
  
  public static final short SAC_OPERATOR_SLASH = 4;
  
  public static final short SAC_OPERATOR_MOD = 5;
  
  public static final short SAC_OPERATOR_EXP = 6;
  
  public static final short SAC_OPERATOR_LT = 7;
  
  public static final short SAC_OPERATOR_GT = 8;
  
  public static final short SAC_OPERATOR_LE = 9;
  
  public static final short SAC_OPERATOR_GE = 10;
  
  public static final short SAC_OPERATOR_TILDE = 11;
  
  public static final short SAC_INHERIT = 12;
  
  public static final short SAC_INTEGER = 13;
  
  public static final short SAC_REAL = 14;
  
  public static final short SAC_EM = 15;
  
  public static final short SAC_EX = 16;
  
  public static final short SAC_PIXEL = 17;
  
  public static final short SAC_INCH = 18;
  
  public static final short SAC_CENTIMETER = 19;
  
  public static final short SAC_MILLIMETER = 20;
  
  public static final short SAC_POINT = 21;
  
  public static final short SAC_PICA = 22;
  
  public static final short SAC_PERCENTAGE = 23;
  
  public static final short SAC_URI = 24;
  
  public static final short SAC_COUNTER_FUNCTION = 25;
  
  public static final short SAC_COUNTERS_FUNCTION = 26;
  
  public static final short SAC_RGBCOLOR = 27;
  
  public static final short SAC_DEGREE = 28;
  
  public static final short SAC_GRADIAN = 29;
  
  public static final short SAC_RADIAN = 30;
  
  public static final short SAC_MILLISECOND = 31;
  
  public static final short SAC_SECOND = 32;
  
  public static final short SAC_HERTZ = 33;
  
  public static final short SAC_KILOHERTZ = 34;
  
  public static final short SAC_IDENT = 35;
  
  public static final short SAC_STRING_VALUE = 36;
  
  public static final short SAC_ATTR = 37;
  
  public static final short SAC_RECT_FUNCTION = 38;
  
  public static final short SAC_UNICODERANGE = 39;
  
  public static final short SAC_SUB_EXPRESSION = 40;
  
  public static final short SAC_FUNCTION = 41;
  
  public static final short SAC_DIMENSION = 42;
  
  public static final short SAC_REM = 43;
  
  public static final short SAC_CH = 44;
  
  short getLexicalUnitType();
  
  LexicalUnit getNextLexicalUnit();
  
  LexicalUnit getPreviousLexicalUnit();
  
  int getIntegerValue();
  
  float getFloatValue();
  
  String getDimensionUnitText();
  
  String getFunctionName();
  
  LexicalUnit getParameters();
  
  String getStringValue();
  
  LexicalUnit getSubValues();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/css/LexicalUnit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */