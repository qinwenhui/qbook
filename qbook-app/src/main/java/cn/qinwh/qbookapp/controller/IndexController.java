package cn.qinwh.qbookapp.controller;

import cn.qinwh.qbookapp.entity.Swipe;
import cn.qinwh.qbookapp.service.BookService;
import cn.qinwh.qbookapp.service.SwipeService;
import cn.qinwh.qbookcommon.utils.ReturnMsg;
import cn.qinwh.qbooksystem.annotation.NoLogin;
import cn.qinwh.qbooksystem.utils.LoginUserUtils;
import cn.qinwh.qbooksystem.utils.RedisUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: qbook
 * @description: 首页控制器
 * @author: qinwh
 * @create: 2021-01-11 12:55
 **/
@Controller
@ResponseBody
@AllArgsConstructor
@RequestMapping("/app/index")
@NoLogin
public class IndexController {

    private final BookService bookService;

    private final SwipeService swipeService;

    /**
    * @Description: 获取首页轮播图
    * @Param: []
    * @return: cn.qinwh.qbookcommon.utils.ReturnMsg
    * @Author: qinwh
    * @Date: 2021/1/21
    */
    @GetMapping("/swipe")
    public ReturnMsg swipe(){
        Swipe where = new Swipe();
        where.setVisible(true);
        List<Swipe> swipes = swipeService.queryListByWhere(where);
        return ReturnMsg.success("查询成功", swipes);
    }
}
