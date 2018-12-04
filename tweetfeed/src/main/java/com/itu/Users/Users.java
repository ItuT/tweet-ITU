package com.itu.Users;

import com.itu.Logs.SimpleLog;
import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Users {

    private HashMap<String,String[]> mapUserFollows = new HashMap<>();
    private Map<String, String[]> userFollowersAlphabeticalListMap = null;
    private List<String> userAlphaList = new ArrayList<>();

    public void extractUsers(BufferedReader brUserFile){
        SimpleLog.log("extractUsers(BufferedReader brUserFile)");
        String st;
        try {
            while ((st = brUserFile.readLine()) != null) {
                String[] names = st.split("follows");
                updateFolloweres(names[0].replaceAll("\\s",""),names[1].replaceAll("\\s",""));
            }
        }catch (IOException ioe){
            SimpleLog.log(ioe.getMessage());
        }
    }

    public void updateFolloweres(String user, String usersFollowing){
        SimpleLog.log("updateFolloweres(String user, String usersFollowing)");
        String[] names = usersFollowing.split(",");

        if(mapUserFollows.containsKey(user)){
            String[] currentFollowing = mapUserFollows.get(user);

            String[] both = ArrayUtils.addAll(names, currentFollowing);
            String[] bothNoDups = Arrays.stream(both).distinct().toArray(String[]::new);

            mapUserFollows.put(user,bothNoDups);
        }else{
            mapUserFollows.put(user,names);
        }
    }

    public void updateUsersToAlphabeticalList(){
        SimpleLog.log("updateUsersToAlphabeticalList()");
        userFollowersAlphabeticalListMap = new TreeMap<String, String[]>(mapUserFollows);
        for (String name: userFollowersAlphabeticalListMap.keySet()){

            String key = name.replaceAll("\\s","");
            String[] value = userFollowersAlphabeticalListMap.get(name.replaceAll("\\s",""));

            if(!userAlphaList.contains(key.replaceAll("\\s",""))){
                userAlphaList.add(key.replaceAll("\\s",""));
            }

            for(String nm: value){
                if(!userAlphaList.contains(nm.replaceAll("\\s",""))){
                    userAlphaList.add(nm.replaceAll("\\s",""));
                }
            }
        }
    }


    public Map<String, String[]> getUserFollowersAlphabeticalListMap() {
        return userFollowersAlphabeticalListMap;
    }

    public List<String> getUserAlphaList() {
        return userAlphaList;
    }

}
