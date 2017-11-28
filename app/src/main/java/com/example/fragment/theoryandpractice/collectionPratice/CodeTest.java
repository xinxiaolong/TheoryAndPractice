package com.example.fragment.theoryandpractice.collectionPratice;

/**
 * Created by xiaolong on 2016/11/29.
 */

public class CodeTest {
    public static void main(String[] agre){

        //'/'号在ASCII中,十进制为47;二进制为00101111
        char s='a';
        System.out.println((int)s);

        //十六进制的值 http://tool.lu/hexconvert/ 进行取值
        int codePoint=0x8f9b;
        //将十六进制的Uncode解析成String
        String text=new String(Character.toChars(codePoint));
        //需要几个字节码存储
        int charCount=Character.charCount(codePoint);
        //返获取Uncode值
        int getCodePoint=Character.codePointAt(text,0);
        //输入
        System.out.println(charCount+"  "+text+"   "+getCodePoint+"   "+codePoint);

        //当text由一个字符组成时,temp>>12 等于0xf
        char temp=text.charAt(0);
        text=temp+"";
        System.out.println(text+"  "+(temp>>12)+"  "+0xe);

        char unicode='\u8f9b';

        System.out.println(unicode);
    }
}
