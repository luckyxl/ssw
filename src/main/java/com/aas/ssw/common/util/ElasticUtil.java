package com.aas.ssw.common.util;

import com.aas.ssw.common.component.Constant;
import com.aas.ssw.common.component.ElasticSearchBean;
import com.aas.ssw.common.component.Result;
import com.alibaba.fastjson.JSONObject;
import io.searchbox.client.JestClient;
import io.searchbox.core.Bulk;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.Update;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Slf4j
@Component
@ConditionalOnProperty(name = "elasticsearch.enabled")
public class ElasticUtil {

    private static JestClient jestClient;

    /**
     * 向elasticsearch中插入一条记录
     *
     * @param t     数据
     * @param index 索引名
     * @param type  类型名
     * @param <T>
     * @return 插入结果
     */
    public static <T> Result save(T t, String index, String type) {
        if (t == null || StringUtils.isBlank(index) || StringUtils.isBlank(type)) {
            return Result.getResult(Constant.FAIL, "缺少参数");
        }
        Index indexBuilder = new Index.Builder(t).index(index).type(type).build();
        try {
            DocumentResult execute = jestClient.execute(indexBuilder);
            return Result.getResult(Constant.SUCCESS, "插入elasticsearch成功", execute);
        } catch (IOException e) {
            log.error("插入elasticsearch异常", e);
            return Result.getResult(Constant.ERROR, "插入elasticsearch异常");
        }
    }

    /**
     * 向elasticsearch中批量插入多条记录
     *
     * @param list  数据
     * @param index 索引名
     * @param type  类型名
     * @param <T>
     * @return 插入结果
     */
    public static <T> Result saveAll(List<T> list, String index, String type) {
        if (list == null || list.size() == 0 || StringUtils.isBlank(index) || StringUtils.isBlank(type)) {
            return Result.getResult(Constant.FAIL, "缺少参数");
        }
        Bulk.Builder bulkBuilder = new Bulk.Builder();
        /*list.stream().forEach(data -> {
            Index indexBuilder = new Index.Builder(data).index(index).type(type).build();
            bulkBuilder.addAction(indexBuilder);
        });*/
        List<Index> indexList = list.stream().map(data -> new Index.Builder(data).index(index).type(type).build()).collect(toList());
        bulkBuilder.addAction(indexList);
        try {
            jestClient.execute(bulkBuilder.build());
            return Result.getResult(Constant.SUCCESS, "批量插入elasticsearch成功");
        } catch (IOException e) {
            log.error("批量插入elasticsearch异常", e);
            return Result.getResult(Constant.ERROR, "批量插入elasticsearch异常");
        }
    }

    /**
     * 根据id修改文档
     *
     * @param t     修改数据
     * @param index 索引
     * @param type  类型
     * @param id    主键
     * @param <T>
     * @return 修改结果
     */
    public static <T> Result updateById(T t, String index, String type, String id) {
        if (t == null || StringUtils.isBlank(index) || StringUtils.isBlank(type) || StringUtils.isBlank(id)) {
            return Result.getResult(Constant.FAIL, "缺少参数");
        }
        try {
            /*需要的格式
            String script = "{" +
            "    \"doc\" : {" +
            "        \"title\" : \""+article.getTitle()+"\"," +
            "        \"content\" : \""+article.getContent()+"\"," +
            "        \"author\" : \""+article.getAuthor()+"\"," +
            "        \"source\" : \""+article.getSource()+"\"," +
            "        \"url\" : \""+article.getUrl()+"\"," +
            "        \"pubdate\" : \""+new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(article.getPubdate())+"\"" +
            "    }" +
            "}";*/
            String doc = XContentFactory.jsonBuilder().startObject().field("doc", JSONObject.toJSON(t)).endObject().string();
            Update updateBuilder = new Update.Builder(doc).index(index).type(type).id(id).build();
            DocumentResult execute = jestClient.execute(updateBuilder);
            return Result.getResult(Constant.SUCCESS, "修改elasticsearch文档成功", execute);
        } catch (IOException e) {
            log.error("XContentFactory构造JSON异常", e);
            return Result.getResult(Constant.ERROR, "XContentFactory构造JSON异常");
        } catch (Exception e) {
            log.error("修改elasticsearch文档异常", e);
            return Result.getResult(Constant.ERROR, "修改elasticsearch文档异常");
        }
    }

    /**
     * 根据主键删除文档
     *
     * @param index 索引
     * @param type  类型
     * @param id    主键
     * @return
     */
    public static Result deleteById(String index, String type, String id) {
        if (StringUtils.isBlank(index) || StringUtils.isBlank(type) || StringUtils.isBlank(id)) {
            return Result.getResult(Constant.FAIL, "缺少参数");
        }
        try {
            Delete deleteBuilder = new Delete.Builder(id).index(index).type(type).build();
            DocumentResult execute = jestClient.execute(deleteBuilder);
            return Result.getResult(Constant.SUCCESS, "删除elasticsearch文档成功", execute);
        } catch (Exception e) {
            log.error("删除elasticsearch文档异常", e);
            return Result.getResult(Constant.ERROR, "删除elasticsearch文档异常");
        }
    }

    /**
     * 分页查询
     *
     * @param clazz 数据类型
     * @param elasticSearchBean 查询对象
     * @param <T>
     * @return 查询结果
     */
    public static <T> Result get(Class<T> clazz, ElasticSearchBean elasticSearchBean) {
        try {
            /**
             * 使用QueryBuilder
             * termQuery("key", obj) 完全匹配
             * termsQuery("key", obj1, obj2..)   一次匹配多个值
             * matchQuery("key", Obj) 单个匹配, field不支持通配符, 前缀具高级特性
             * multiMatchQuery("text", "field1", "field2"..);  匹配多个字段, field有通配符忒行
             * matchAllQuery();         匹配所有文件
             */
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            AbstractQueryBuilder queryStringQueryBuilder = null;
            if(elasticSearchBean.isFullTextMatching()){
                queryStringQueryBuilder = QueryBuilders.queryStringQuery(elasticSearchBean.getKeyword());
            }else {
                queryStringQueryBuilder = QueryBuilders.multiMatchQuery(elasticSearchBean.getKeyword(), (String[]) elasticSearchBean.getMatchingFields().toArray());
            }
            searchSourceBuilder.query(queryStringQueryBuilder);
            if(null != elasticSearchBean.getHighLightsFields()){
                HighlightBuilder highlightBuilder = new HighlightBuilder();
                elasticSearchBean.getHighLightsFields().stream().forEach(field -> highlightBuilder.field(field));
//                HighlightBuilder highlightBuilder = new HighlightBuilder().field("*").requireFieldMatch(false);
                highlightBuilder.requireFieldMatch(false);
                //高亮标签
                highlightBuilder.preTags("<em>").postTags("</em>");
                //高亮内容长度
                highlightBuilder.fragmentSize(200);
                searchSourceBuilder.highlighter(highlightBuilder);
            }
            Search.Builder searchBuilder = new Search.Builder(
                    searchSourceBuilder.toString())
                    .addIndex(elasticSearchBean.getIndex())
                    .addType(elasticSearchBean.getType());
            if(null != elasticSearchBean.getFrom()){
                searchBuilder.setParameter("from", elasticSearchBean.getFrom());
            }
            if(null != elasticSearchBean.getSize()){
                searchBuilder.setParameter("size", elasticSearchBean.getSize());
            }

            if(null != elasticSearchBean.getSortFields() && elasticSearchBean.getSortFields().size() > 0){
                searchBuilder.addSort(elasticSearchBean.getSortFields());
            }
//            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)  //设置查询类型：1.SearchType.DFS_QUERY_THEN_FETCH 精确查询； 2.SearchType.SCAN 扫描查询,无序
            Search search = searchBuilder.build();
            SearchResult searchResult = jestClient.execute(search);
            if(!searchResult.isSucceeded()){
                return Result.getResult(Constant.FAIL,searchResult.getErrorMessage());
            }
            List<JSONObject> data = searchResult.getHits(clazz).stream().map(hit -> {
                JSONObject json = (JSONObject) JSONObject.toJSON(hit.source);
                json.put("esId", hit.id);
                Map<String, List<String>> highlight = hit.highlight;
                for (String key : highlight.keySet()) {
                    json.put(key, highlight.get(key).get(0));
                }
                return json;
            }).collect(toList());

            return Result.getResult(Constant.SUCCESS, "elasticsearch查询成功", data, searchResult.getTotal().intValue());
        } catch (IOException e) {
            log.error("elasticsearch查询异常", e);
            return Result.getResult(Constant.ERROR, "elasticsearch查询异常");
        }
    }


    @Resource
    public void setJestClient(JestClient jestClient) {
        ElasticUtil.jestClient = jestClient;
    }
}
