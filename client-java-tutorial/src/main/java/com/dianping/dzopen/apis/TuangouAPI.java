package com.dianping.dzopen.apis;

import com.dianping.dzopen.constants.AppConstants;
import com.dianping.dzopen.utils.HttpParamLoader;
import com.dianping.dzopen.utils.RequestUtil;
import com.dianping.dzopen.utils.SignUtil;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import static com.dianping.dzopen.constants.AppConstants.HOST;
import static com.dianping.dzopen.constants.AppConstants.SIGN_METHOD_MD5;

/**
 * 团购API请求示例, 如果要Debug这个接口，请到单元测试中执行。
 */
public class TuangouAPI {

    /**
     * 根据团购券码，查询券码对应的dealid下，该用户可使用的券数据量
     *
     * @return api返回的json字符串
     */
    public String receiptPrepare() throws IOException, NoSuchAlgorithmException {

        Map<String, String> requestParam = HttpParamLoader.loadRequest("httpparams/tuangou/receipt-prepare.xml");
        requestParam.put("sign", SignUtil.signTopRequest(requestParam, AppConstants.APP_SECRET, SIGN_METHOD_MD5));

        // 构造form
        Form form = Form.form();
        for (Map.Entry<String, String> entry : requestParam.entrySet()) {
            form.add(entry.getKey(), entry.getValue());
        }

        // 发起post请求
        return Request.Post("https://openapi.dianping.com/router/tuangou/receipt/prepare")
                .bodyForm(form.build())
                .setHeader("Content-Type", ContentType.create("application/x-www-form-urlencoded", AppConstants.CHARSET_UTF8).toString())
                .execute().returnContent().asString();
    }

    /**
     * 根据团购券码，一次性验证同一订单下的若干团购券 同一个用户，同一个dealid下的券码
     *
     * @return 验券接口返回结果
     * @throws Exception exception
     */
    public String receiptConsume() throws Exception {
        Map<String, String> requestParam = HttpParamLoader.loadRequest("httpparams/tuangou/receipt-consume.xml");
        requestParam.put("sign", SignUtil.signTopRequest(requestParam, AppConstants.APP_SECRET, SIGN_METHOD_MD5));
        // 构造form
        Form form = Form.form();
        for (Map.Entry<String, String> entry : requestParam.entrySet()) {
            form.add(entry.getKey(), entry.getValue());
        }

        // 发起post请求
        return Request.Post("https://openapi.dianping.com/router/tuangou/receipt/consume")
                .bodyForm(form.build())
                .setHeader("Content-Type", ContentType.create("application/x-www-form-urlencoded", AppConstants.CHARSET_UTF8).toString())
                .execute().returnContent().asString();
    }

    /**
     * 查询已经验证的券码信息，仅支持已经验证的券码
     *
     * @return 已验券码明细
     * @throws Exception e
     */
    public String receiptGetConsumed() throws Exception {
        Map<String, String> requestParam = HttpParamLoader.loadRequest("httpparams/tuangou/receipt-getconsumed.xml");
        requestParam.put("sign", SignUtil.signTopRequest(requestParam, AppConstants.APP_SECRET, SIGN_METHOD_MD5));

        return Request.Get(HOST + "/router/tuangou/receipt/getconsumed"
                + "?"
                + RequestUtil.mapToGetParam(requestParam))
                .execute().returnContent().asString();
    }

    /**
     * 查询已经验证的券码信息，仅支持已经验证的券码
     *
     * @return 已验券码明细
     * @throws Exception e
     */
    public String receiptQueryListByDate() throws Exception {
        Map<String, String> requestParam = HttpParamLoader.loadRequest("httpparams/tuangou/receipt-querylistbydate.xml");
        requestParam.put("sign", SignUtil.signTopRequest(requestParam, AppConstants.APP_SECRET, SIGN_METHOD_MD5));

        return Request.Get(HOST + "/router/tuangou/receipt/querylistbydate"
                + "?"
                + RequestUtil.mapToGetParam(requestParam))
                .execute().returnContent().asString();
    }


    /**
     * 根据团购券码，查询券码对应的dealid下，该用户可使用的券数据量
     *
     * @return api返回的json字符串
     */
    public String receiptReverseConsume() throws IOException, NoSuchAlgorithmException {

        Map<String, String> requestParam = HttpParamLoader.loadRequest("httpparams/tuangou/receipt-reverseconsume.xml");
        requestParam.put("sign", SignUtil.signTopRequest(requestParam, AppConstants.APP_SECRET, SIGN_METHOD_MD5));

        // 构造form
        Form form = Form.form();
        for (Map.Entry<String, String> entry : requestParam.entrySet()) {
            form.add(entry.getKey(), entry.getValue());
        }

        // 发起post请求
        return Request.Post(HOST + "/router/tuangou/receipt/reverseconsume")
                .bodyForm(form.build())
                .setHeader("Content-Type", ContentType.create("application/x-www-form-urlencoded", AppConstants.CHARSET_UTF8).toString())
                .execute().returnContent().asString();
    }


    /**
     * 根据二维码code，查询可用的券数据
     *
     * @return api返回的json字符串
     */
    public String receiptScanPrepare() throws IOException, NoSuchAlgorithmException {

        Map<String, String> requestParam = HttpParamLoader.loadRequest("httpparams/tuangou/receipt-scanprepare.xml");
        requestParam.put("sign", SignUtil.signTopRequest(requestParam, AppConstants.APP_SECRET, SIGN_METHOD_MD5));

        // 构造form
        Form form = Form.form();
        for (Map.Entry<String, String> entry : requestParam.entrySet()) {
            form.add(entry.getKey(), entry.getValue());
        }

        // 发起post请求
        return Request.Post(HOST + "/router/tuangou/receipt/scanprepare")
                .bodyForm(form.build())
                .setHeader("Content-Type", ContentType.create("application/x-www-form-urlencoded", AppConstants.CHARSET_UTF8).toString())
                .execute().returnContent().asString();
    }

    /**
     * 根据服务商店铺id，查询当前门店下的所有团购券信息
     *
     * @return 当前门店下的所有团购券信息
     * @throws Exception e
     */
    public String dealQueryShopDeal() throws Exception {
        Map<String, String> requestParam = HttpParamLoader.loadRequest("httpparams/tuangou/deal-queryshopdeal.xml");
        requestParam.put("sign", SignUtil.signTopRequest(requestParam, AppConstants.APP_SECRET, SIGN_METHOD_MD5));

        return Request.Get(HOST + "/tuangou/deal/queryshopdeal"
                + "?"
                + RequestUtil.mapToGetParam(requestParam))
                .execute().returnContent().asString();
    }

}
