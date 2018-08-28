package com.zoro.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
        //排除指定加载器
        //SpringApplicationBuilder builder =  new SpringApplicationBuilder(Application.class) ;
        //Set<ApplicationListener<?>> listeners = builder.application().getListeners() ;
        //for (Iterator<ApplicationListener<?>> it = listeners.iterator(); it.hasNext() ;) {
        //    ApplicationListener<?> listener = it.next() ;
        //    if (listener  instanceof RestartApplicationListener) {
        //        it.remove() ;
        //    }
        //}
        //builder.application().setListeners(listeners) ;
        //builder.run(args) ;
    }
}
