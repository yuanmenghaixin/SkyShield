package com.tdpark.sky.shield.utils;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.tdpark.eutils.MD5Utils;
import com.tdpark.eutils.UUIDUtils;
import com.tdpark.sky.shield.constants.Constants;

public class ConfoundingUtils {

    private static final char [] X_16 = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    private static final Map<Character, Integer> X_16_MAP = new HashMap<Character, Integer>();
    static{
        for(int idx=0;idx<X_16.length;idx++){
            X_16_MAP.put(X_16[idx], idx);
        }
    }
    public static String toToken(String ssToken,String randomCode) throws NoSuchAlgorithmException{
        char[] ks = MD5Utils.toMD5(randomCode + Constants.MD5_KEY).toCharArray();
        char[] ds = ssToken.toCharArray();
        char x = ks[0];
        int y = 0;
        for(int idx=0;idx<ks.length;idx++){
            x = ks[idx];
            y += X_16_MAP.get(x);
            int t = X_16_MAP.get(ds[y % ds.length]) + X_16_MAP.get(x);
            ds[y % ds.length] = X_16[t % X_16.length];
        }
        return new String(ds);
    }
    public static String toSSToken(String token,String randomCode) throws NoSuchAlgorithmException{
        char[] ks = MD5Utils.toMD5(randomCode + Constants.MD5_KEY).toCharArray();
        char[] ds = token.toCharArray();
        char x = ks[0];
        int y = 0;
        for(int idx=0;idx<ks.length;idx++){
            x = ks[idx];
            y += X_16_MAP.get(x);
            int t = X_16_MAP.get(ds[y % ds.length]) - X_16_MAP.get(x);
            if(t < 0){
                t += X_16.length;
            }
            ds[y % ds.length] = X_16[t % X_16.length];
        }
        return new String(ds);
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String ssToken = UUIDUtils.uuid();
        System.out.println(ssToken);
        String randomCode = UUIDUtils.uuid();
        String token = toToken(ssToken, randomCode);
        System.out.println(token);
        System.out.println(toSSToken(token, randomCode));
    }
}
