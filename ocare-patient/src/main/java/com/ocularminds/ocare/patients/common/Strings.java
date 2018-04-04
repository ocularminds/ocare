
package com.ocularminds.ocare.common;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.StringTokenizer;

public class Strings {

    public static String invertCapitals(String other) {
        return other.chars().mapToObj(Strings::flipCap)
                .map(c -> Character.toString(c))
                .reduce("", (s, c) -> s + c);
    }

    public static Character flipCap(int c) {
        if (c >= 'A' && c <= 'Z') {
            return (char) (c - 'A' + 'a');
        } else if (c >= 'a' && c <= 'z') {
            return (char) (c - 'a' + 'A');
        } else {
            return (char) c;
        }
    }

    public static InputStream loadResource(Class cls, String path) {
        return cls.getResourceAsStream(path);
    }

    public static String quadsplit(String pan) {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < pan.length(); ++x) {
            sb.append(pan.charAt(x));
            if ((x + 1) % 4 == 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static String space(int length) {
        StringBuilder buf = new StringBuilder(length);
        for (int i = 0; i < length; ++i) {
            buf.append(" ");
        }
        return buf.toString();
    }

    public static String unspace(String s) {
        StringTokenizer st = new StringTokenizer(s, " ", false);
        StringBuilder buf = new StringBuilder();
        while (st.hasMoreElements()) {
            buf.append(st.nextElement());
        }
        return buf.toString();
    }

    public static String leftPad(String data, char padd, int len) {
        String padddata = data;
        int datalen = data.length();
        while (len - datalen != 0) {
            padddata = "" + padd + padddata;
            datalen = padddata.length();
        }
        return padddata;
    }

    public static String rightPad(String data, char padd, int len) {
        StringBuilder bd = new StringBuilder(data);
        while (bd.length() < len) {
            bd.append(padd);
        }
        return bd.toString();
    }

    public static String removeCRLFTab(String s) {
        StringTokenizer st = new StringTokenizer(s, "\r\n\t", false);
        StringBuilder buf = new StringBuilder();
        while (st.hasMoreElements()) {
            buf.append(st.nextElement());
        }
        return buf.toString();
    }

    public static String encode(byte[] data, short off, short len) {
        char[] ch = new char[len * 2];
        int i = 0;
        while ((len = (short) (len - 1)) > 0) {
            short s = off;
            off = (short) (off + 1);
            short b = (short) (data[s] & 255);
            short d = (short) (b >> 4);
            d = (short) (d >= 10 ? d - 10 + 65 : d + 48);
            int n = i;
            i = (short) (i + 1);
            ch[n] = (char) d;
            d = (short) (b & 15);
            d = (short) (d >= 10 ? d - 10 + 65 : d + 48);
            int n2 = i;
            i = (short) (i + 1);
            ch[n2] = (char) d;
        }
        return new String(ch);
    }

    public static String encode(byte[] data) {
        return Strings.encode(data, (short) 0, (short) data.length);
    }

    public static byte[] copyByteArray(byte[] array2Copy) {
        if (array2Copy == null) {
            throw new IllegalArgumentException("Argument 'array2Copy' cannot be null");
        }
        return Strings.copyByteArray(array2Copy, 0, array2Copy.length);
    }

    public static byte[] copyByteArray(byte[] array2Copy, int startPos, int length) {
        if (array2Copy == null) {
            throw new IllegalArgumentException("Argument 'array2Copy' cannot be null");
        }
        if (array2Copy.length < startPos + length) {
            throw new IllegalArgumentException("startPos(" + startPos + ")+length(" + length + ") > byteArray.length(" + array2Copy.length + ")");
        }
        byte[] copy = new byte[array2Copy.length];
        System.arraycopy(array2Copy, startPos, copy, 0, length);
        return copy;
    }

    public static int crc(String data) {
        int crc;
        int sum = 0;
        String[] command = data.split(",");
        for (int x = 0; x < command.length; ++x) {
            sum += Integer.parseInt(command[x]);
        }
        for (crc = 256 - sum; crc < 0; crc = 256 + crc) {
        }
        return crc;
    }

    public static int calculateCrc16(String hex) {
        byte[] data = Strings.hex2byte(hex);
        return Strings.calculateCrc16(data);
    }

    public static int calculateCrc16(byte[] data) {
        int crc = 65535;
        int polynomial = 4129;
        for (byte b : data) {
            for (int i = 0; i < 8; ++i) {
                boolean bit = (b >> 7 - i & 1) == 1;
                boolean c15 = (crc >> 15 & 1) == 1;
                crc <<= 1;
                if (!(c15 ^ bit)) {
                    continue;
                }
                crc ^= polynomial;
            }
        }
        return crc &= 65535;
    }

    public byte[] calculateSize(int len) {
        byte[] data = new byte[]{(byte) (len >> 8 & 255), (byte) (len & 255)};
        return data;
    }

    public static int hex2decimal(String hex) {
        return Integer.parseInt(hex, 16);
    }

    public static String hex2binary(String hex) {
        String s = Integer.toBinaryString(hex2decimal(hex));
        while (s.length() < 8) {
            s = "0" + s;
        }
        return s;
    }

    public static String byte2Hex(byte b) {
        String[] HEX_DIGITS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        int nb = b & 255;
        int i_1 = nb >> 4 & 15;
        int i_2 = nb & 15;
        return HEX_DIGITS[i_1] + HEX_DIGITS[i_2];
    }

    public static String short2Hex(short s) {
        byte b1 = (byte) (s >> 8);
        byte b2 = (byte) (s & 255);
        return Strings.byte2Hex(b1) + Strings.byte2Hex(b2);
    }

    public static int byteToInt(byte b) {
        return b & 255;
    }

    public static int byteToInt(byte first, byte second) {
        int value = (first & 255) << 8;
        return value += second & 255;
    }

    public static short byte2Short(byte b1, byte b2) {
        return (short) (b1 << 8 | b2 & 255);
    }

    public static String int2Hex(int i) {
        String hex = Integer.toHexString(i);
        if (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
    }

    public static String int2HexZeroPad(int i) {
        String hex = Strings.int2Hex(i);
        if (hex.length() % 2 != 0) {
            hex = "0" + hex;
        }
        return hex;
    }

    public static byte[] bytesFromList(ArrayList data) {
        byte[] t = new byte[data.size()];
        for (int x = 0; x < data.size(); ++x) {
            t[x] = ((Byte) data.get(x)).byteValue();
        }
        return t;
    }

    public static BitSet byteArray2BitSet(byte[] bytes) {
        BitSet bits = new BitSet();
        for (int i = 0; i < bytes.length * 8; ++i) {
            if ((bytes[bytes.length - i / 8 - 1] & 1 << i % 8) <= 0) {
                continue;
            }
            bits.set(i);
        }
        return bits;
    }

    public static byte[] bitSet2ByteArray(BitSet bits) {
        byte[] bytes = new byte[bits.length() / 8 + 1];
        for (int i = 0; i < bits.length(); ++i) {
            if (!bits.get(i)) {
                continue;
            }
            byte[] arrby = bytes;
            int n = bytes.length - i / 8 - 1;
            arrby[n] = (byte) (arrby[n] | 1 << i % 8);
        }
        return bytes;
    }

    public static boolean isBitSet(byte val, int bitPos) {
        if (bitPos < 1 || bitPos > 8) {
            throw new IllegalArgumentException("parameter 'bitPos' must be between 1 and 8. bitPos=" + bitPos);
        }
        if ((val >> bitPos - 1 & 1) == 1) {
            return true;
        }
        return false;
    }

    public static byte[] intToByteArray(int value) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte one = (byte) (value >>> 24);
        byte two = (byte) (value >>> 16);
        byte three = (byte) (value >>> 8);
        byte four = (byte) value;
        boolean found = false;
        if (one > 0) {
            baos.write(one);
            found = true;
        }
        if (found || two > 0) {
            baos.write(two);
            found = true;
        }
        if (found || three > 0) {
            baos.write(three);
            found = true;
        }
        baos.write(four);
        return baos.toByteArray();
    }

    public static byte[] intToByteArray4(int value) {
        return new byte[]{(byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value};
    }

    public static int byteArrayToInt(byte[] byteArray) {
        return Strings.byteArrayToInt(byteArray, 0, byteArray.length);
    }

    public static int byteArrayToInt(byte[] byteArray, int startPos, int length) {
        if (byteArray == null) {
            throw new IllegalArgumentException("Parameter 'byteArray' cannot be null");
        }
        if (length <= 0 || length > 4) {
            throw new IllegalArgumentException("Length must be between 1 and 4. Length = " + length);
        }
        int value = 0;
        for (int i = startPos; i < length; ++i) {
            value += (byteArray[i] & 255) << 8 * (byteArray.length - i - 1);
        }
        return value;
    }

    public static byte int2byte(int a) {
        return (byte) (255 & a);
    }

    public static byte[] resize(byte[] source, int len) {
        if (len == 0) {
            return source;
        }
        byte[] destination = new byte[len];
        System.arraycopy(source, 0, destination, 0, len);
        return destination;
    }

    public static String byte2ascii(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        String hex = Strings.byte2hex(bytes);
        for (int i = 0; i < hex.length() - 1; i += 2) {
            String output = hex.substring(i, i + 2);
            int decimal = Integer.parseInt(output, 16);
            sb.append((char) decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }

    public static String hex2ascii(String hex) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < hex.length() - 1; i += 2) {
            String output = hex.substring(i, i + 2);
            int decimal = Integer.parseInt(output, 16);
            sb.append((char) decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }

    public static byte[] hex2byte(String hex) {
        if (((hex = Strings.unspace(hex)).length() & 1) == 1) {
            throw new IllegalArgumentException();
        }
        byte[] bytes = new byte[hex.length() / 2];
        for (int idx = 0; idx < bytes.length; ++idx) {
            int hi = Character.digit((int) hex.charAt(idx * 2), 16);
            int lo = Character.digit((int) hex.charAt(idx * 2 + 1), 16);
            if (hi < 0 || lo < 0) {
                throw new IllegalArgumentException();
            }
            bytes[idx] = (byte) (hi << 4 | lo);
        }
        return bytes;
    }

    public static String byte2hex(byte[] bytes) {
        char[] hex = new char[bytes.length * 2];
        for (int idx = 0; idx < bytes.length; ++idx) {
            int hi = (bytes[idx] & 240) >>> 4;
            int lo = bytes[idx] & 15;
            hex[idx * 2] = (char) (hi < 10 ? 48 + hi : 55 + hi);
            hex[idx * 2 + 1] = (char) (lo < 10 ? 48 + lo : 55 + lo);
        }
        return new String(hex);
    }

    public static String byte2hex(final byte[] byteArray, int startPos, int length) {
        if (byteArray == null) {
            return "";
        }
        if (byteArray.length < startPos + length) {
            throw new IllegalArgumentException("startPos(" + startPos + ")+length(" + length + ") > byteArray.length(" + byteArray.length + ")");
        }
        StringBuilder hexData = new StringBuilder();
        int onebyte;
        for (int i = 0; i < length; i++) {
            onebyte = ((0x000000ff & byteArray[startPos + i]) | 0xffffff00);
            hexData.append(Integer.toHexString(onebyte).substring(6));
        }
        return hexData.toString();
    }

    public static byte[] int2bcdbyte(int val) {
        String str = String.valueOf(val);
        if (str.length() % 2 != 0) {
            str = "0" + str;
        }
        return Strings.hex2byte(str);
    }

    public static int bcd2int(byte b) {
        String hex = Strings.byte2Hex(b);
        try {
            return Integer.parseInt(hex);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("The hex representation of argument b must be digits", ex);
        }
    }

    public static int bcdhex2int(String hex) {
        if ((hex = Strings.unspace(hex)).length() > 8) {
            throw new IllegalArgumentException("There must be a maximum of 4 hex octets. hex=" + hex);
        }
        try {
            return Integer.parseInt(hex);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Argument hex must be all digits. hex=" + hex, ex);
        }
    }

    public static String byte2BinaryLiteral(byte val) {
        String s = Integer.toBinaryString(Strings.byteToInt(val));
        if (s.length() < 8) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 8 - s.length(); ++i) {
                sb.append('0');
            }
            sb.append(s);
            s = sb.toString();
        }
        return s;
    }

    public String string2hex(String str) {
        char[] chars = str.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < chars.length; ++i) {
            hex.append(Integer.toHexString(chars[i]));
        }
        return hex.toString();
    }

    public String hex2string(String hex) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < hex.length() - 1; i += 2) {
            String output = hex.substring(i, i + 2);
            int decimal = Integer.parseInt(output, 16);
            sb.append((char) decimal);
            temp.append(decimal);
        }
        System.out.println("Decimal : " + temp.toString());
        return sb.toString();
    }

    public static byte setBit(byte data, int bitPos, boolean on) {
        if (bitPos < 1 || bitPos > 8) {
            throw new IllegalArgumentException("parameter 'bitPos' must be between 1 and 8. bitPos=" + bitPos);
        }
        if (on) {
            data = (byte) (data | 1 << bitPos - 1);
            return data;
        }
        data = (byte) (data & ~(1 << bitPos - 1));
        return data;
    }

    public static boolean isNotaNumber(String t) {
        boolean notaNumber = false;
        try {
            Integer.parseInt(t);
        } catch (NumberFormatException e) {
            notaNumber = true;
        }
        return notaNumber;
    }

    public static String format(String text, int size, String pad) {
        String s = "";
        if (text == null) {
            return "";
        }
        if (size == 0) {
            return text;
        }
        if (size > text.length()) {
            StringBuilder sb = new StringBuilder(text);
            int diff = size - text.length();
            for (int x = 0; x < diff; ++x) {
                if (pad.equals("0")) {
                    sb.insert(0, pad);
                    continue;
                }
                sb.append(pad);
            }
            s = sb.toString();
        } else {
            s = text.substring(0, size);
        }
        return s;
    }

    public static String prettyPrintHex(String in, int indent, boolean wrapLines) {
        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            buf.append(c);

            int nextPos = i + 1;
            if (wrapLines && nextPos % 32 == 0 && nextPos != in.length()) {
                buf.append("\n").append(space(indent));
            } else if (nextPos % 2 == 0 && nextPos != in.length()) {
                buf.append(" ");
            }
        }
        return buf.toString();
    }

    public static String prettyPrintHex(String in, int indent) {
        return prettyPrintHex(in, indent, true);
    }

    public static String prettyPrintHex(byte[] data, int indent) {
        return prettyPrintHex(byte2hex(data), indent, true);
    }

    public static String prettyPrintHex(byte[] data) {
        return prettyPrintHex(byte2hex(data), 0, true);
    }

    public static String prettyPrintHex(byte[] data, int startPos, int length) {
        return prettyPrintHex(byte2hex(data, startPos, length), 0, true);
    }

    public static String prettyPrintHexNoWrap(byte[] data) {
        return prettyPrintHex(byte2hex(data), 0, false);
    }

    public static String prettyPrintHexNoWrap(byte[] data, int startPos, int length) {
        return prettyPrintHex(byte2hex(data, startPos, length), 0, false);
    }

    public static String prettyPrintHexNoWrap(String in) {
        return prettyPrintHex(in, 0, false);
    }

    public static String prettyPrintHex(String in) {
        return prettyPrintHex(in, 0, true);
    }

    public static String prettyPrintHex(BigInteger bi) {
        byte[] data = bi.toByteArray();
        if (data[0] == (byte) 0x00) {
            byte[] tmp = new byte[data.length - 1];
            System.arraycopy(data, 1, tmp, 0, data.length - 1);
            data = tmp;
        }
        return prettyPrintHex(data);
    }
}
