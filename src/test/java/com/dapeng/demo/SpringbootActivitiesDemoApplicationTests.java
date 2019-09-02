package com.dapeng.demo;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootActivitiesDemoApplicationTests {

//    private String snippetDir = "target/generated-snippets";
//    private String outputDir  = "target/asciidoc";
//    @Autowired
//    private MockMvc mockMvc;
//    @After
//    public void Test() throws Exception {
//        // 得到swagger.json,写入outputDir目录中
//        mockMvc.perform(get("/v2/api-docs").accept(MediaType.APPLICATION_JSON))
//                .andDo(SwaggerResultHandler.outputDirectory(outputDir).build())
//                .andExpect(status().isOk())
//                .andReturn();
//        // 读取上一步生成的swagger.json转成asciiDoc,写入到outputDir
//        // 这个outputDir必须和插件里面<generated></generated>标签配置一致
//        Swagger2MarkupConverter.from(outputDir + "/swagger.json")
//                .withPathsGroupedBy(GroupBy.TAGS)// 按tag排序
//                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式
//                .withExamples(snippetDir)
//                .build()
//                .intoFolder(outputDir);// 输出
//    }
//
//    @Test
//    public void createProcessTest() throws Exception {
//        mockMvc.perform(//构造一个post请求
//               get("/demo/firstDemo"))
//                .andExpect(status().isOk());
//
//    }
}
