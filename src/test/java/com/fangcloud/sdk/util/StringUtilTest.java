package com.fangcloud.sdk.util;

import com.fangcloud.sdk.exception.ClientValidationException;
import org.junit.Test;

import java.nio.charset.Charset;

import static com.fangcloud.sdk.util.StringUtil.jq;
import static org.junit.Assert.assertEquals;

public class StringUtilTest {
    @Test
    public void base64EncodeTest() {
        // Some examples from Wikipedia.
        checkBase64(
                "Man is distinguished, not only by his reason, but by this singular passion from " +
                        "other animals, which is a lust of the mind, that by a perseverance of delight in " +
                        "the continued and indefatigable generation of knowledge, exceeds the short " +
                        "vehemence of any carnal pleasure.",
                "TWFuIGlzIGRpc3Rpbmd1aXNoZWQsIG5vdCBvbmx5IGJ5IGhpcyByZWFzb24sIGJ1dCBieSB0aGlz" +
                        "IHNpbmd1bGFyIHBhc3Npb24gZnJvbSBvdGhlciBhbmltYWxzLCB3aGljaCBpcyBhIGx1c3Qgb2Yg" +
                        "dGhlIG1pbmQsIHRoYXQgYnkgYSBwZXJzZXZlcmFuY2Ugb2YgZGVsaWdodCBpbiB0aGUgY29udGlu" +
                        "dWVkIGFuZCBpbmRlZmF0aWdhYmxlIGdlbmVyYXRpb24gb2Yga25vd2xlZGdlLCBleGNlZWRzIHRo" +
                        "ZSBzaG9ydCB2ZWhlbWVuY2Ugb2YgYW55IGNhcm5hbCBwbGVhc3VyZS4="
        );

        checkBase64("pleasure.", "cGxlYXN1cmUu");
        checkBase64("leasure.",  "bGVhc3VyZS4=");
        checkBase64("easure.",   "ZWFzdXJlLg==");
        checkBase64("asure.",    "YXN1cmUu");
        checkBase64("sure.",     "c3VyZS4=");
    }

    private void checkBase64(String original, String expected) {
        byte[] raw = original.getBytes(Charset.forName("ASCII"));
        String encoded = StringUtil.base64Encode(raw);
        if (!encoded.equals(expected)) {
            throw new AssertionError("original=" + jq(original) + ", encoded=" +  jq(encoded) + ", expected=" + jq(expected));
        }
    }

    @Test
    public void checkNameValidTest() {
        int result = 0;
        String invalidString = "/?:*\\\"><\\";
        int length = invalidString.length();
        char test;
        for (int i = 0; i < length; i++) {
            test = invalidString.charAt(i);
            try {
                StringUtil.checkNameValid(String.valueOf(test));
            } catch (ClientValidationException e) {
                result++;
            }
        }
        assertEquals(length, result);
    }
}
