package cn.qinwh.qbooksystem.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: qbook
 * @description: mybatis逆向生成
 * @author: qinwh
 * @create: 2020-11-19 20:31
 **/
public class CodeGenerator {

    public static void main(String[] args) throws Exception {
        generator();
    }

    /*
    ** mybatis逆向生成
     */
    private static void generator() throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        // 输出实际的工程路径录
        String projectPath = System.getProperty("user.dir");
        projectPath = projectPath + "\\qbook-system\\src\\test\\java\\cn\\qinwh\\qbooksystem\\generator\\";
        File configFile = new File(projectPath + "generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);

        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        System.out.println("代码生成完毕>>>>>>>>>>>>");
    }
}
