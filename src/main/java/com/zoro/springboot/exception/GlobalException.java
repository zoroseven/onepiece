package com.zoro.springboot.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoro.springboot.constant.ErrorCodeEnum;
import com.zoro.springboot.constant.ResultRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @date 2018/8/29  17:02
 */
@ControllerAdvice
public class GlobalException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(Exception e) throws Exception {
        logger.error("全局异常处理，错误信息：{}",e.getMessage(),e);
        ModelAndView mav = new ModelAndView();
        ResultView view = new ResultView();
        view.setResultRes(ResultRes.fail("内部错误",null));
        mav.setView(view);
        return mav;
    }

    //可以定义多个捕获异常的方法，捕获自定义异常也行，感觉比较多余，如果是自定义异常，程序中明确抛出了，还需要全局异常处理？
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultRes runtimeExceptionHandler(RuntimeException e){
        logger.error("全局运行时异常处理，错误信息：{}",e.getMessage(),e);
        ResultRes res = new ResultRes(ErrorCodeEnum.SYS_ERROR,e.getMessage());
        return res;
    }

    protected class ResultView implements View{

        private ResultRes resultRes;

        @Override
        public String getContentType() {
            return "application/json;charset=utf-8";
        }

        @Override
        public void render(Map<String, ?> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
            response.setContentType(getContentType());
            if(resultRes!=null){
                ObjectMapper om = new ObjectMapper();
                PrintWriter writer = response.getWriter();
                writer.write(om.writeValueAsString(this.resultRes));
                writer.flush();
                writer.close();
            }
        }

        public void setResultRes(ResultRes resultRes) {
            this.resultRes = resultRes;
        }
    }
}
