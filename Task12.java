import java.util.*;

public class Task12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        HashSet<Character> vs = new HashSet<>();
        for (char c : vowels) {
            vs.add(c);
        }
        int vc=0;
        int cc=0;
        System.out.println("Enter a Sentence: ");
        String s = sc.nextLine();
        String ds = "";
        HashSet<Character> dup = new HashSet<>();
        for (char c : s.toCharArray())
        {
            if(!dup.contains(c))
            {
                dup.add(c);
                ds += c;
            }
            else
            {
                ds += "";
            }
            if(c == ' ')
                continue;
            if(vs.contains(c))
                vc++;
            else 
                cc++;
        }
        System.out.println("String: " + s);
        System.out.println("Distinct String: " + ds);
        System.out.println("Vowels Count: " + vc);
        System.out.println("Consonants Count: " + cc);

        System.out.print("Enter a two string for anagram check: ");
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        HashSet<Character> set1 = new HashSet<>();
        for (char c : str1.toCharArray()) {
            set1.add(c);
        }
        for (char c : str2.toCharArray()) {
            if (!set1.contains(c)) {
                System.out.println("The strings are not anagrams.");
                return;
            }
        }
        System.out.println("The strings are anagrams.");
    }
}
