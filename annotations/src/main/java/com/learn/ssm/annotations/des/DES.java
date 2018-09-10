package com.learn.ssm.annotations.des;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;

public class DES {
    private static final String CHARSET = "utf-8";

    /**
     * 对message进行des加密
     *
     * @param deskey秘钥，长度必须是8的倍数
     * @param message
     * @return
     */
    public static String desEncrypt(String message, String desKey) {
        String encryptStr = null;
        byte encryptByte[];

        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, genSecretKey(desKey));
            byte[] cipherText = cipher
                    .doFinal(message.trim().getBytes(CHARSET));
            encryptByte = Base64.encodeBase64(cipherText);
            encryptStr = new String(encryptByte, "UTF-8");
        } catch (Throwable e) {
            throw new RuntimeException("des加密发生异常", e);
        }

        return encryptStr;
    }

    /**
     * 对cipherText进行des解密
     *
     * @param cipherText
     * @return
     */
    public static String desDecrypt(String cipherText, String desKey) {
        String decryptStr = null;

        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            byte[] input = Base64.decodeBase64(cipherText.trim().getBytes(
                    "UTF-8"));
            cipher.init(Cipher.DECRYPT_MODE, genSecretKey(desKey));
            byte[] output = cipher.doFinal(input);
            decryptStr = new String(output, CHARSET);
        } catch (Throwable e) {
            throw new RuntimeException("des解密发生异常", e);
        }

        return decryptStr;
    }

    private static SecretKey genSecretKey(String key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException {
        //DESKeySpec desKeySpec = new DESKeySpec(hexStringToByte(key));
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        return secretKey;
    }

    protected static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    public static void main(String[] args) {

        System.out.println(desEncrypt("asdf","NbKBImnyEUsJDdVg"));






//        // 这是一个1000请求body的采样数据
//        String body = "<body><ltype>QGSLTO</ltype><periodnum>2010118</periodnum><merchantuserid>11111111</merchantuserid><username>andy</username><idno>130324198804114517</idno><mobile>13021902050</mobile><records><record><orderno>12313106355756701</orderno><playtype>1</playtype><chipintype>2</chipintype><content>02|11|13|14|22|31-09*1</content><orderamount>200</orderamount></record></records></body>";
//        String key = RandomStringUtils.randomAlphanumeric(16);
////		System.out.println(key);
//        key = RandomStringUtils.randomAlphabetic(16);
//        System.out.println(key);
//        String encryptStr = DES.desEncrypt(body, key);
//        String decryptStr = DES.desDecrypt(encryptStr, key);
//        System.out.println(encryptStr);
//        System.out.println(decryptStr);

    }
}