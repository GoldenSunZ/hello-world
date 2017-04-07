package com.mapath.bpf;

/**
 * Created by zhouxiaobo on 2017/2/28.
 */
import com.mapath.bpf.controller.NewsController;
import com.mapath.bpf.model.NewsModel;
import com.mapath.bpf.service.AdminService;
import com.mapath.bpf.service.NewsService;
import com.mapath.bpf.utils.DateUtil;
import com.mapath.bpf.utils.UUID;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sun.rmi.runtime.Log;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@WebMvcTest(NewsController.class)
public class NewsServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(NewsServiceTest.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NewsService newsService;

    @MockBean
    private AdminService adminService;

    @Test
    public void testEnterPage() throws Exception{
        RequestBuilder request = null;
        NewsModel newsModel=new NewsModel();
        newsModel.setId(UUID.uuid32());
        newsModel.setTitle("国家");
        newsModel.setPicture("");
        newsModel.setComments("梦想");
        newsModel.setAuthor("于丹");
        newsModel.setCreateDt(DateUtil.getSystemDateTime());
        newsModel.setClickNum(0);
        newsModel.setIsdelete("0");
        request = MockMvcRequestBuilders.get("/newsList",newsModel);
        mvc.perform(request);
    }

    @Test
    public void testExecuteQuery() throws Exception{
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.post("/queryNews")
                .param("keyword","国学");
        mvc.perform(request);
    }
}
