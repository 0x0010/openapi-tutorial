## 点评开放平台API使用示例

Demo描述的是服务商调用开放平台API的场景，提供了点评开放平台API的基本使用方法，供服务商参考。

主要包括如下几大类业务接口：

- 团购接口。接入后，商户可实现在服务商系统内进行团购核销的全部处理流程。服务商可将团单信息与大众点评上的团单信息进行映射，从而实现商户库存的实时管理。用户到店验券的时候，商户可直接在服务商系统完成验券，无须切换系统，验券成功后，直接扣减对应团单的库存，从而提升经营效率；商户验券错误或者多验券的时候，可直接在服务商系统完成撤销验券，撤销验券成功后，直接增加对应团单的库存，从而提升管理效率。
- 商户接口。接入后，服务商的应用可以根据城市以及经纬度信息搜索商户等。

### 示例使用方法
以团购业务为例，`com.dianping.dzopen.apis.TuangouAPI`中包含了所有团购接口的调用示例。
比如二维码验券接口：
````java
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
````
更多接口实例，请在源码中查看。


接口入参可以在`resources/httpparams/tuantou`目录下查看。

比如二维码验券接口的入参配置如下：

````xml
<?xml version="1.0" encoding="UTF-8"?>
<param>
    <!-- 公共参数 -->
    <field name="app_key" value="1000082" desc="开放平台分配给应用的appid"/>
    <field name="timestamp" valuebuilder="com.dianping.dzopen.valuebuilder.TimestampValueBuilder" desc="开放平台分配给应用的appid"/>
    <field name="session" value="11122333" desc="商户授权成功后，点评到综开放平台颁发给应用的授权信息" />
    <field name="format" value="json" desc="响应格式，默认json"/>
    <field name="v" value="1" desc="API协议版本，默认值：1，此后版本升级，会递增"/>
    <field name="sign_method" value="MD5" desc="签名的摘要算法，默认值为：MD5。"/>
    <field name="sign" nullable="true" desc="签名的摘要算法，默认值为：MD5。"/>
    <!-- 接口业务参数 -->
    <field name="qr_code" value="1000002223311" desc="二维码对应code，目前有券码和用户ID两种情形"/>
    <field name="app_shop_id" value="shop_id123" desc="服务商的店铺id"/>
</param>
````

### 示例运行
`com.dianping.dzopen.apis.TuangouAPITest`中包含了团购接口的单元测试，可以直接使用junit运行。
