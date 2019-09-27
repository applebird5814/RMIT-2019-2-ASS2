import java.util.*;

import java.util.*;

public class test {
    public static void main(String[] args) {// HashMap mapping employee number to accounts
        Map<String, String> accountsMap = new HashMap<String, String>();
        accountsMap.put("E123", "Homy");
        accountsMap.put("E156", "Alex");
        accountsMap.put("E542", "Rachel");
        accountsMap.put("E174", "Tobin");
        Map<String, String> quotaMap = new HashMap<String, String>();
        quotaMap.put("Homy", "5MB");
        quotaMap.put("Alex", "50MB");
        quotaMap.put("Rachel", "4MB");
        quotaMap.put("Bruce", "8MB");
        for(String str1 : accountsMap.keySet())
        {
            for(String str2:quotaMap.keySet())
            {
                if(accountsMap.get(str1).equals(str2))
                {
                    System.out.print(str1);
                    System.out.print(str2);
                    System.out.println(quotaMap.get(str2));
                }
            }
        }
    }
}