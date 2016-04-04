package myBlog;

import java.util.concurrent.ConcurrentHashMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class LogTemp {
    static ConcurrentHashMap<String,String> chm = new ConcurrentHashMap<String,String>(); 
    private static MessageDigest md;
    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.toString());
        }
    }

    //need tokenIsValid first
    static String findByToken(String token) {
        return chm.get(token);
    }

    //name + current time + random number
    static String generateToken(String name) {
        String s = name + System.currentTimeMillis() + Math.random()*1000;
        md.update(s.getBytes());
        return byteArrayToHex(md.digest());
    }

    static void saveToken(String token, String name) {
        chm.put(token,name);
    }
    
    static boolean tokenIsValid(String token, long registerTime) {
        if (chm.containsKey(token)) {
            long curTime = System.currentTimeMillis();
            if (curTime - registerTime < 7200000) {
                return true;
            }
            chm.remove(token);
        }
        return false;
    }

    



    private static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray =new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b& 0xf];
        }
        return new String(resultCharArray);
    }
        
}

