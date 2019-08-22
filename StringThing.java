/*
 * StringThing.java
 *
 * Version:
 *     1
 *
 */

/**
 * This program is to read, understand and write all the method
 * call that would produce true when abcV1 is used as the first
 * argument for that particular method.
 *
 * @author  Ishika Prasad
 */
public class StringThing
{
    /**
     * This method is used to compare the memory reference of
     * two strings
     * @param a first string
     * @param b second string
     * @return true is memory reference are same, Otherwise false
     */
    private static boolean equalityTest1(String a, String b)
    {
        return a == b;
    }

    /**
     * This method is used to compare character by character of
     * two strings.
     * @param a first string
     * @param b second string
     * @return true if all characters in a string is same as another
     * string
     */
    private static boolean equalityTest2(String a, String b)
    {
        return a.equals(b);
    }

    /**
     * This method is used to compare two strings with their locations
     * and not the actual content.
     *
     * @param a first string
     * @param b second string
     * @return true if two strings refer same locations. Otherwise, false.
     */
    private static boolean equalityTest3(String a, String b)
    {
        return System.identityHashCode(a) == System.identityHashCode(b);
    }

    /**
     * This method is used to compare the character of
     * two strings.
     * @param a first string
     * @param b second string
     * @return true is all characters are same. Otherwise, false
     */
    private static boolean equalityTest4(String a, String b)
    {
        if(a.length() == 0 && b.length() == 0)
        {
            return true;
        }
        else
        {
            if(a.length() == 0 || b.length() == 0)
            {
                return false;
            }
            if(a.charAt(0) == b.charAt(0))
            {
                return equalityTest4(a.substring(1), b.substring(1));
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * This method is used to compare two strings with
     * their actual content and generated hashcode.
     * @param a first string
     * @param b second string
     * @return true if two strings has same hashcode. Otherwise, false.
     */
    private static boolean equalityTest5(String a, String b)
    {
        return a.hashCode() == b.hashCode();
    }

    public static void main(String[] args)
    {
        String abcV1 = "abc";
        String abcV2 = "a" + "b" + "c";
        String abcV3 = "abcd".substring(0, abcV1.length());
        String abcV4 = "" + abcV2;
        String abcV5 = "a" + (char)98 + 99;
        String abcV6 = new String("abc");
        String abcV7 = abcV3.intern();
        String abcv8 = abcV6;

        // using abcV1 as first parameter ...
        // all possible trues for equalityTest1
        equalityTest1(abcV1,abcV2); //both strings will be in intern pool as need to match memory reference
        equalityTest1(abcV1, abcV7); //both strings will be in intern pool as need to match memory reference

        // all possible trues for equalityTest2
        equalityTest2(abcV1, abcV2); //both strings comparision via character by character
        equalityTest2(abcV1, abcV3); //both strings comparision via character by character
        equalityTest2(abcV1, abcV4); //both strings comparision via character by character
        equalityTest2(abcV1, abcV6); //both strings comparision via character by character
        equalityTest2(abcV1, abcV7); //both strings comparision via character by character
        equalityTest2(abcV1, abcv8); //both strings comparision via character by character

        // all possible trues for equalityTest3
        equalityTest3(abcV1, abcV2); //as both strings will have same memory location
        equalityTest3(abcV1, abcV7); //as both strings will have same memory location


        // all possible trues for equalityTest4
        equalityTest4(abcV1, abcV2); //both strings comparision via character by character
        equalityTest4(abcV1, abcV3); //both strings comparision via character by character
        equalityTest4(abcV1, abcV4); //both strings comparision via character by character
        equalityTest4(abcV1, abcV6); //both strings comparision via character by character
        equalityTest4(abcV1, abcV7); //both strings comparision via character by character
        equalityTest4(abcV1, abcv8); //both strings comparision via character by character

        // all possible trues for equalityTest5
        equalityTest5(abcV1, abcV2); //if content of both strings are same then same hashcode will be generated
        equalityTest5(abcV1, abcV3); //if content of both strings are same then same hashcode will be generated
        equalityTest5(abcV1, abcV4); //if content of both strings are same then same hashcode will be generated
        equalityTest5(abcV1, abcV6); //if content of both strings are same then same hashcode will be generated
        equalityTest5(abcV1, abcV7); //if content of both strings are same then same hashcode will be generated
        equalityTest5(abcV1, abcv8); //if content of both strings are same then same hashcode will be generated
    }
}