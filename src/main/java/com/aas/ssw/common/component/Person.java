package com.aas.ssw.common.component;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * @author xl
 * @excel注解说明 属性            类型	        默认值	            说明
 * name	        String	    null	            列名
 * needMerge	    boolean	    fasle	            纵向合并单元格
 * orderNum	    String	    "0"	                列的排序,支持name_id
 * replace	    String[]	{}	                值得替换 导出是{a_id,b_id} 导入反过来
 * savePath	    String	"   upload"	            导入文件保存路径
 * type	        int	        1	                导出类型 1 是文本 2 是图片,3 是函数,10 是数字 默认是文本
 * width	        double	    10	                列宽
 * height	        double	    10	                列高,后期打算统一使用@ExcelTarget的height,这个会被废弃,注意
 * isStatistics	boolean	    fasle	            自动统计数据,在追加一行统计,把所有数据都和输出这个处理会吞没异常,请注意这一点
 * isHyperlink	boolean	    false	            超链接,如果是需要实现接口返回对象
 * isImportField	boolean	    true	            校验字段,看看这个字段是不是导入的Excel中有,如果没有说明是错误的Excel,读取失败,支持name_id
 * exportFormat	String	    ""	                导出的时间格式,以这个是否为空来判断是否需要格式化日期
 * importFormat	String	    ""	                导入的时间格式,以这个是否为空来判断是否需要格式化日期
 * format	        String	    ""	                时间格式,相当于同时设置了exportFormat 和 importFormat
 * databaseFormat	String	    "yyyyMMddHHmmss"	导出时间设置,如果字段是Date类型则不需要设置 数据库如果是string 类型,这个需要设置这个数据库格式,用以转换时间格式输出
 * numFormat	    String	    ""	                数字格式化,参数是Pattern,使用的对象是DecimalFormat
 * imageType	    int	        1	                导出类型 1 从file读取 2 是从数据库中读取 默认是文件 同样导入也是一样的
 * suffix	        String	    ""	                文字后缀,如% 90 变成90%
 * isWrap	        boolean	    true	            是否换行 即支持\n
 * mergeRely	    int[]	    {}	                合并单元格依赖关系,比如第二列合并是基于第一列 则{1}就可以了
 * mergeVertical	boolean	    fasle	            纵向合并内容相同的单元格
 */
@Data
public class Person {

    @Excel(name = "姓名", orderNum = "0")
    private String name;

    @Excel(name = "性别", replace = {"男_1", "女_2"}, orderNum = "1")
    private String sex;

    @Excel(name = "生日", exportFormat = "yyyy-MM-dd", orderNum = "2")
    private Date birthday;

    public Person() {
    }

    public Person(String name, String sex, Date birthday) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
    }

}
