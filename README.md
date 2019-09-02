# spring-boot-activiti-demo
最近正在学习activities工作流，做了一个和spring boot的demo。实现了：<br>
* 使用druid数据缓冲池进行数据库的连接。<br>
* swagger进行接口文档的规范和页面的代码测试。<br>
* 整合Activiti Modeler。 <br>
* 使用spring profile进行代码生产、上线环境间的切换测试。<br>
* activiti业务的抽象/封装。<br>
* log4j2日志的集成。<br>

## druid数据缓冲池的使用
maven架包的依赖：<br>
```
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.48</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>${druid.version}</version>
        </dependency>
```        
数据库的连接配置
```
@Autowired
  private ActivitiesDataSourceProperties activitiesDataSourceProperties;

  @Bean
  @Primary
  public DataSource activitiesDataSource() {
      DruidDataSource dataSource = new DruidDataSource();
      dataSource.setUrl(activitiesDataSourceProperties.getUrl());
      dataSource.setUsername(activitiesDataSourceProperties.getUserName());
      dataSource.setPassword(activitiesDataSourceProperties.getPassWord());
      dataSource.setDriverClassName(activitiesDataSourceProperties.getDriver());
      dataSource.setMaxWait(activitiesDataSourceProperties.getMaxWait());
      dataSource.setInitialSize(activitiesDataSourceProperties.getInitialSize());

      return dataSource;
  }
```

## swagger的整合和生产、上线环境的配置
```
@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.enable:true}")
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("activities_swagger-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dapeng.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    // api信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("activities_Swagger2")
                .description("spring boot and activities demo")
                .version("1.0")
                .build();

    }
}
```
使用@ConditionalOnExpression注解配置表达式，只有swagger.enable为true才进行swagger集成测试。
在测试的环境中配置：<br>
swagger:<br>
&ensp;&ensp;  enable: true<br>
在实际生产环境中配置为false，实现环境切换。

## 整合Activiti Modeler
maven架包的依赖<br>
```
<dependency><!--org.activiti.engine.ActivitiException: Found Activiti 5 process definition,
                  but no compatibility handler on the classpath-->
          <groupId>org.activiti</groupId>
          <artifactId>activiti5-engine</artifactId>
          <version>${activiti.version}</version>
      </dependency>
      <!--activiti-modeler依赖-->
      <dependency>
          <groupId>org.activiti</groupId>
          <artifactId>activiti-json-converter</artifactId>
          <version>${activiti.version}</version>
          <exclusions>
              <exclusion>
                  <groupId>org.activiti</groupId>
                  <artifactId>activiti-bpmn-model</artifactId>
              </exclusion>
          </exclusions>
      </dependency>

      <dependency>
          <groupId>org.apache.xmlgraphics</groupId>
          <artifactId>batik-codec</artifactId>
          <version>${apache.xmlgraphics.version}</version>
      </dependency>
      <dependency>
          <groupId>org.apache.xmlgraphics</groupId>
          <artifactId>batik-css</artifactId>
          <version>${apache.xmlgraphics.version}</version>
      </dependency>
      <dependency>
          <groupId>org.apache.xmlgraphics</groupId>
          <artifactId>batik-svg-dom</artifactId>
          <version>${apache.xmlgraphics.version}</version>
      </dependency>
      <dependency>
          <groupId>org.apache.xmlgraphics</groupId>
          <artifactId>batik-svggen</artifactId>
          <version>${apache.xmlgraphics.version}</version>
      </dependency>
```
把ModelEditorJsonRestResource类、ModelSaveRestResource类、StencilsetRestResource类添加进项目。
修改@RequestMapping注解为@RequestMapping("service")。<br>
静态文件和stencilset.json存放在resources下，修改static中editor-app的app-cgg:<br>
.jsACTIVITI.CONFIG = {<br>
&ensp;&ensp;    'contextRoot': '/service',<br>
};

## 使用spring profile进行代码生产、上线环境切换
配置文件存放在resources下，使用spring.profile.active=dev或者pro,进行环境的切换

## activiti业务的抽象、封装
在common下的handler包里实现对activiti的封装。
