package cn.qinwh.qbooksystem.annotation.manager;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class NoLoginStoreManager {

    private List<String> noLoginPathList= new ArrayList<>();

    public void addNoLoginPath(String path){
        this.noLoginPathList.add(path);
    }

    public List<String> getNoLoginPaths(){
        return noLoginPathList;
    }
}
