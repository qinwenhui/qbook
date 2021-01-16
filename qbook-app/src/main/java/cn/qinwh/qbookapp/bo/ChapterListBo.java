package cn.qinwh.qbookapp.bo;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * @program: qbook
 * @description: 获取章节列表的入参
 * @author: qinwh
 * @create: 2021-01-16 16:27
 **/
@Data
public class ChapterListBo {

    @NotNull(message="书籍编号不能为空")
    @Digits(integer = 10, fraction = 10, message = "编号必须为整数")
    private String bookId;

    /**
     * 当前页号
     */
    @NotNull(message="页码不能为空")
    @Digits(integer = 10, fraction = 10, message = "页码必须为整数")
    private String pageNo;

    /**
     * 页大小
     */
    @NotNull(message="页大小不能为空")
    @Digits(integer = 10, fraction = 10, message = "页大小必须为整数")
    private String pageSize;
}
