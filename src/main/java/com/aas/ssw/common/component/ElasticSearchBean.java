package com.aas.ssw.common.component;

import io.searchbox.core.search.sort.Sort;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class ElasticSearchBean implements Serializable {
    private static final long serialVersionUID = -5188975204258390540L;
    /**
     * 索引
     */
    private String index;
    /**
     * 类型
     */
    private String type;
    /**
     * 要查询关键字
     */
    private String keyword;
    /**
     * 从第几行开始
     */
    private Integer from;
    /**
     * 查询几条数据
     */
    private Integer size;
    /**
     * 高亮字段
     */
    private List<String> highLightsFields;
    /**
     * 排序字段
     */
    private List<Sort> sortFields;
    /**
     * 是否是全文匹配,true:是;false;不是
     */
    private boolean isFullTextMatching;
    /**
     * 要匹配的字段
     */
    private List<String> MatchingFields;

}
