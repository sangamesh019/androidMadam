package com.randroid.madam;

public final class CRC32
{
  private static final int[] crc32Lookup;
  private int crc = -1;
  
  static
  {
    int[] arrayOfInt = new int[256];
    arrayOfInt[1] = 79764919;
    arrayOfInt[2] = 159529838;
    arrayOfInt[3] = 222504665;
    arrayOfInt[4] = 319059676;
    arrayOfInt[5] = 398814059;
    arrayOfInt[6] = 445009330;
    arrayOfInt[7] = 507990021;
    arrayOfInt[8] = 638119352;
    arrayOfInt[9] = 583659535;
    arrayOfInt[10] = 797628118;
    arrayOfInt[11] = 726387553;
    arrayOfInt[12] = 890018660;
    arrayOfInt[13] = 835552979;
    arrayOfInt[14] = 1015980042;
    arrayOfInt[15] = 944750013;
    arrayOfInt[16] = 1276238704;
    arrayOfInt[17] = 1221641927;
    arrayOfInt[18] = 1167319070;
    arrayOfInt[19] = 1095957929;
    arrayOfInt[20] = 1595256236;
    arrayOfInt[21] = 1540665371;
    arrayOfInt[22] = 1452775106;
    arrayOfInt[23] = 1381403509;
    arrayOfInt[24] = 1780037320;
    arrayOfInt[25] = 1859660671;
    arrayOfInt[26] = 1671105958;
    arrayOfInt[27] = 1733955601;
    arrayOfInt[28] = 2031960084;
    arrayOfInt[29] = 2111593891;
    arrayOfInt[30] = 1889500026;
    arrayOfInt[31] = 1952343757;
    arrayOfInt[32] = -1742489888;
    arrayOfInt[33] = -1662866601;
    arrayOfInt[34] = -1851683442;
    arrayOfInt[35] = -1788833735;
    arrayOfInt[36] = -1960329156;
    arrayOfInt[37] = -1880695413;
    arrayOfInt[38] = -2103051438;
    arrayOfInt[39] = -2040207643;
    arrayOfInt[40] = -1104454824;
    arrayOfInt[41] = -1159051537;
    arrayOfInt[42] = -1213636554;
    arrayOfInt[43] = -1284997759;
    arrayOfInt[44] = -1389417084;
    arrayOfInt[45] = -1444007885;
    arrayOfInt[46] = -1532160278;
    arrayOfInt[47] = -1603531939;
    arrayOfInt[48] = -734892656;
    arrayOfInt[49] = -789352409;
    arrayOfInt[50] = -575645954;
    arrayOfInt[51] = -646886583;
    arrayOfInt[52] = -952755380;
    arrayOfInt[53] = -1007220997;
    arrayOfInt[54] = -827056094;
    arrayOfInt[55] = -898286187;
    arrayOfInt[56] = -231047128;
    arrayOfInt[57] = -151282273;
    arrayOfInt[58] = -71779514;
    arrayOfInt[59] = -8804623;
    arrayOfInt[60] = -515967244;
    arrayOfInt[61] = -436212925;
    arrayOfInt[62] = -390279782;
    arrayOfInt[63] = -327299027;
    arrayOfInt[64] = 881225847;
    arrayOfInt[65] = 809987520;
    arrayOfInt[66] = 1023691545;
    arrayOfInt[67] = 969234094;
    arrayOfInt[68] = 662832811;
    arrayOfInt[69] = 591600412;
    arrayOfInt[70] = 771767749;
    arrayOfInt[71] = 717299826;
    arrayOfInt[72] = 311336399;
    arrayOfInt[73] = 374308984;
    arrayOfInt[74] = 453813921;
    arrayOfInt[75] = 533576470;
    arrayOfInt[76] = 25881363;
    arrayOfInt[77] = 88864420;
    arrayOfInt[78] = 134795389;
    arrayOfInt[79] = 214552010;
    arrayOfInt[80] = 2023205639;
    arrayOfInt[81] = 2086057648;
    arrayOfInt[82] = 1897238633;
    arrayOfInt[83] = 1976864222;
    arrayOfInt[84] = 1804852699;
    arrayOfInt[85] = 1867694188;
    arrayOfInt[86] = 1645340341;
    arrayOfInt[87] = 1724971778;
    arrayOfInt[88] = 1587496639;
    arrayOfInt[89] = 1516133128;
    arrayOfInt[90] = 1461550545;
    arrayOfInt[91] = 1406951526;
    arrayOfInt[92] = 1302016099;
    arrayOfInt[93] = 1230646740;
    arrayOfInt[94] = 1142491917;
    arrayOfInt[95] = 1087903418;
    arrayOfInt[96] = -1398421865;
    arrayOfInt[97] = -1469785312;
    arrayOfInt[98] = -1524105735;
    arrayOfInt[99] = -1578704818;
    arrayOfInt[100] = -1079922613;
    arrayOfInt[101] = -1151291908;
    arrayOfInt[102] = -1239184603;
    arrayOfInt[103] = -1293773166;
    arrayOfInt[104] = -1968362705;
    arrayOfInt[105] = -1905510760;
    arrayOfInt[106] = -2094067647;
    arrayOfInt[107] = -2014441994;
    arrayOfInt[108] = -1716953613;
    arrayOfInt[109] = -1654112188;
    arrayOfInt[110] = -1876203875;
    arrayOfInt[111] = -1796572374;
    arrayOfInt[112] = -525066777;
    arrayOfInt[113] = -462094256;
    arrayOfInt[114] = -382327159;
    arrayOfInt[115] = -302564546;
    arrayOfInt[116] = -206542021;
    arrayOfInt[117] = -143559028;
    arrayOfInt[118] = -97365931;
    arrayOfInt[119] = -17609246;
    arrayOfInt[120] = -960696225;
    arrayOfInt[121] = -1031934488;
    arrayOfInt[122] = -817968335;
    arrayOfInt[123] = -872425850;
    arrayOfInt[124] = -709327229;
    arrayOfInt[125] = -780559564;
    arrayOfInt[126] = -600130067;
    arrayOfInt[127] = -654598054;
  
    crc32Lookup = arrayOfInt;
  }
  
  public int getCRC()
  {
    return 0xFFFFFFFF ^ this.crc;
  }
  
  public void updateCRC(int paramInt)
  {
    int i = this.crc;
    this.crc = (i << 8 ^ crc32Lookup[(0xFF & (paramInt ^ i >> 24))]);
  }
  
  public void updateCRC(int paramInt1, int paramInt2)
  {
    int i = this.crc;
    int k;
    for (int j = paramInt2;; j = k)
    {
      k = j - 1;
      if (j <= 0)
      {
        this.crc = i;
        return;
      }
      i = i << 8 ^ crc32Lookup[(0xFF & (paramInt1 ^ i >> 24))];
    }
  }
}


/* Location:           D:\SOFTWARE\apk to source  code\work\p_dex2jar.jar
 * Qualified Name:     org.itadaki.bzip2.CRC32
 * JD-Core Version:    0.7.0.1
 */