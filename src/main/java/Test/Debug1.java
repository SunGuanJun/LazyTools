package Test;

import java.util.*;

/**
 * Created by hzsunguanjun on 2017/7/5.
 */
public class Debug1 {
    public static void main(String[] args){
        List<String> emailBriefVOs = new ArrayList<>();
        emailBriefVOs.add("email1");
        emailBriefVOs.add("email2");
        HashSet<String> sendUserSet = new HashSet<>();
        List<String> userVOS = new ArrayList<>();
        userVOS.add("user1");
        userVOS.add("user2");
        List<String> buEmailBriefVOs = new ArrayList<>();
        List<String> orgUserList;
        Set<Long> userIdSet = new HashSet<>();
        String tmpVo;
        for (String userVO : userVOS) {
            sendUserSet.clear();
            sendUserSet.add(userVO);

            orgUserList = new ArrayList<>();
            orgUserList.add("orgUser1");
            orgUserList.add("orgUser2");
            userIdSet.clear();
            for (String orgUser : orgUserList){
                userIdSet.add(1L);
                userIdSet.add(2L);
            }
            buEmailBriefVOs.clear();
            for (Iterator iterator = emailBriefVOs.iterator(); iterator.hasNext(); ){
                tmpVo = (String) iterator.next();
                //如果属于该bu
                if (tmpVo.contains("email")){
                    buEmailBriefVOs.add(tmpVo);
                        iterator.remove();
                }
            }

            System.out.println(sendUserSet.toString() + buEmailBriefVOs.toString());
            System.out.println("===================");

        }

    }
}
