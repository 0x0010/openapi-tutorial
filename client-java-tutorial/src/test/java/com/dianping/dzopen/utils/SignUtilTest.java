package com.dianping.dzopen.utils;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class SignUtilTest {

    @Test
    public void signTopRequestTest() throws IOException, NoSuchAlgorithmException{
        Map<String, String> param = new HashMap<>();
        param.put("app_id", "test001");
        param.put("timestamp", "2017-11-13 14:44:00");
        Assert.assertThat(SignUtil.signTopRequest(param, "", "MD5"), Is.is("277094cded19065755c9021dd36b0554"));
    }

}
