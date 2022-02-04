package com.github.ybqdren.isendserver;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;



/**
 * <h1> 导入FastDFS-Client组件 </h1>
 *
 * @author zhao wen
 * @since 0.0.1
 **/


@Configuration
@Import(FdfsClientConfig.class)
// 解决jmx重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastdfsImporter {
    // 导入依赖组件
}