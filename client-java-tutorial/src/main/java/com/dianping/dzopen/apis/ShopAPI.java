package com.dianping.dzopen.apis;

import com.dianping.dzopen.constants.AppConstants;
import com.dianping.dzopen.utils.HttpParamLoader;
import com.dianping.dzopen.utils.RequestUtil;
import com.dianping.dzopen.utils.SignUtil;
import org.apache.http.client.fluent.Request;

import java.util.Map;

import static com.dianping.dzopen.constants.AppConstants.HOST;
import static com.dianping.dzopen.constants.AppConstants.SIGN_METHOD_MD5;

/**
 * 商户API调用示例。
 */
public class ShopAPI {

    /**
     * 获取支持商户搜索的最新城市列表
     *
     * @return 支持商户搜索的最新城市列表
     * @throws Exception e
     */
    public String queryCities() throws Exception {
        Map<String, String> requestParam = HttpParamLoader.loadRequest("httpparams/shop/querycitys.xml");
        requestParam.put("sign", SignUtil.signTopRequest(requestParam, AppConstants.APP_SECRET, SIGN_METHOD_MD5));

        return Request.Get(HOST + "/router/poi/querycitys"
                + "?"
                + RequestUtil.mapToGetParam(requestParam))
                .execute().returnContent().asString();
    }


    /**
     * 当用户在附近的商户列表页中选择访问某一个商户详情页，调用此API获取指定商户的详细信息
     *
     * @return 商户详细信息
     * @throws Exception e
     */
    public String queryPoiDetail() throws Exception {
        Map<String, String> requestParam = HttpParamLoader.loadRequest("httpparams/shop/querypoidetail.xml");
        requestParam.put("sign", SignUtil.signTopRequest(requestParam, AppConstants.APP_SECRET, SIGN_METHOD_MD5));

        return Request.Get(HOST + "/router/poi/querypoidetail"
                + "?"
                + RequestUtil.mapToGetParam(requestParam))
                .execute().returnContent().asString();
    }


    /**
     * 获取支持商户搜索的最新城市下属区域列表
     *
     * @return 城市下属区域信息
     * @throws Exception e
     */
    public String queryRegions() throws Exception {
        Map<String, String> requestParam = HttpParamLoader.loadRequest("httpparams/shop/queryregions.xml");
        requestParam.put("sign", SignUtil.signTopRequest(requestParam, AppConstants.APP_SECRET, SIGN_METHOD_MD5));

        return Request.Get(HOST + "/router/poi/queryregions"
                + "?"
                + RequestUtil.mapToGetParam(requestParam))
                .execute().returnContent().asString();
    }


    /**
     * 获取支持商户搜索的最新城市下属区域列表
     *
     * @return 城市下属区域信息
     * @throws Exception e
     */
    public String queryCategories() throws Exception {
        Map<String, String> requestParam = HttpParamLoader.loadRequest("httpparams/shop/querycategories.xml");
        requestParam.put("sign", SignUtil.signTopRequest(requestParam, AppConstants.APP_SECRET, SIGN_METHOD_MD5));

        return Request.Get(HOST + "/router/poi/querycategories"
                + "?"
                + RequestUtil.mapToGetParam(requestParam))
                .execute().returnContent().asString();
    }

}
