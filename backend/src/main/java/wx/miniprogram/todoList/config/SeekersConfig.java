package wx.miniprogram.todoList.config;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Godc
 * @description
 */
@Configuration
@MapperScan("wx.miniprogram.todoList.mapper")
public class SeekersConfig {

    //MP分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    //逻辑删除的插件
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

}
