package tt.com.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * <pre>
 * tt.com.handler
 *    |_ HdCoBindingInitializer.java
 *
 * DESC : 웹파라미터의 값을 변환<br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author Hyun Joong Kim
 * @Date 2012. 10. 23. 오후 1:28:52
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 6.		Hyun Joong Kim		최초 작성
 *	-----------------------------------------------------------------------
 *
 */
public class HdCoBindingInitializer implements WebBindingInitializer {

    public void initBinder(WebDataBinder binder, WebRequest request) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

        /* StringTrimmerEditor:: 아무것도 입력하지 않으면 기본적으로 null이 아니라 공백문자가 입력된 것으로 인식.
         *  이를 null로 설정하고 싶은 경우는 StringTrimmerEditor 생성시 인자로 true */
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

}
