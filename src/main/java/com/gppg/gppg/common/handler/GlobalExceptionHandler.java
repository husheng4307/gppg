package com.gppg.gppg.common.handler;


import com.gppg.gppg.common.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * The type Global exception handler.
 *
 * @author HanJing
 * @date 2019 -11-1
 */
@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Runtime exception handler string.
     * 运行时异常
     * @param ex the ex
     * @return the string
     */

    @ExceptionHandler(RuntimeException.class)
    public String runtimeExceptionHandler(RuntimeException ex) {
        log.error(resultFormat(1, ex));
        return resultFormat(1, ex);
    }

    /**
     * Null pointer exception handler string.
     * 空指针异常
     * @param ex the ex
     * @return the string
     */

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException ex) {
        System.err.println("NullPointerException:");
        log.error(resultFormat(2, ex));
        return resultFormat(2, ex);
    }

    /**
     * Class cast exception handler string.
     * 类型转换异常
     * @param ex the ex
     * @return the string
     */

    @ExceptionHandler(ClassCastException.class)
    public String classCastExceptionHandler(ClassCastException ex) {
        log.error(resultFormat(3, ex));
        return resultFormat(3, ex);
    }

    /**
     * O exception handler string.
     * IO异常
     * @param ex the ex
     * @return the string
     */

    @ExceptionHandler(IOException.class)
    public String iOExceptionHandler(IOException ex) {
        log.error(resultFormat(4, ex));
        return resultFormat(4, ex);
    }

    /**
     * No such method exception handler string.
     *  未知方法异常
     * @param ex the ex
     * @return the string
     */

    @ExceptionHandler(NoSuchMethodException.class)
    public String noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        log.error(resultFormat(5, ex));
        return resultFormat(5, ex);
    }

    /**
     * Index out of bounds exception handler string.
     * 数组越界异常
     * @param ex the ex
     * @return the string
     */

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        log.error(resultFormat(6, ex));
        return resultFormat(6, ex);
    }

    /**
     * Request not readable string.
     *
     * @param ex the ex
     * @return the string
     */

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public String requestNotReadable(HttpMessageNotReadableException ex) {
        log.error(resultFormat(7, ex));
        System.out.println("400..requestNotReadable");
        return resultFormat(7, ex);
    }

    /**
     * Request type mismatch string.
     *
     * @param ex the ex
     * @return the string
     */
//400错误
    @ExceptionHandler({TypeMismatchException.class})
    public String requestTypeMismatch(TypeMismatchException ex) {
        log.error(resultFormat(8, ex));
        System.out.println("400..TypeMismatchException");
        return resultFormat(8, ex);
    }

    /**
     * Request missing servlet request string.
     * 400错误
     * @param ex the ex
     * @return the string
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public String requestMissingServletRequest(MissingServletRequestParameterException ex) {
        log.error(resultFormat(9, ex));
        System.out.println("400..MissingServletRequest");
        return resultFormat(9, ex);
    }

    /**
     * Request 405 string.
     *
     * @param ex the ex
     * @return the string
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public String request405(HttpRequestMethodNotSupportedException ex) {
        log.error(resultFormat(10, ex));
        return resultFormat(10, ex);
    }

    /**
     * Request 406 string.
     *
     * @param ex the ex
     * @return the string
     */

    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public String request406(HttpMediaTypeNotAcceptableException ex) {
        log.error(resultFormat(11, ex));
        System.out.println("406...");
        return resultFormat(11, ex);
    }

    /**
     * Server 500 string.
     *
     * @param ex the ex
     * @return the string
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public String server500(RuntimeException ex) {
        log.error(resultFormat(12, ex));
        System.out.println("500...");
        return resultFormat(12, ex);
    }

    /**
     * Request stack overflow string.
     *
     * @param ex the ex
     * @return the string
     */
//栈溢出
    @ExceptionHandler({StackOverflowError.class})
    public String requestStackOverflow(StackOverflowError ex) {
        log.error(resultFormat(13, ex));
        return resultFormat(13, ex);
    }

    /**
     * Arithmetic exception string.
     *
     * @param ex the ex
     * @return the string
     */
//除数不能为0
    @ExceptionHandler({ArithmeticException.class})
    public String arithmeticException(ArithmeticException ex) {
        log.error(resultFormat(13, ex));
        return resultFormat(13, ex);
    }


    /**
     * Exception string.
     *
     * @param ex the ex
     * @return the string
     */
//其他错误
    @ExceptionHandler({Exception.class})
    public String exception(Exception ex) {
        log.error(resultFormat(14, ex));
        return resultFormat(14, ex);
    }

    private <T extends Throwable> String resultFormat(Integer code, T ex) {
        log.error(JsonResult.failed(code, ex.getMessage()));
        ex.printStackTrace();
        log.error(String.format(logExceptionFormat, code, ex.getMessage()));
        return JsonResult.failed(code, ex.getMessage());
    }




}
