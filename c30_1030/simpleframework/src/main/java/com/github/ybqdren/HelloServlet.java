package com.github.ybqdren;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/24 12:37
 * @package com.github.ybqdren
 * @description
 *
 *
 * JSP 运行原理：
 *  1. 用户访问 1.jsp 文件
 *  2. 服务器查找 1.jsp 文件，找到后加载文件到服务器中
 *  3. 服务器将 1.jsp 文件翻译为 _1_jsp.java 文件
 *  4. 服务器将 java 文件编译成 class 文件
 *  5. 服务器加载 class 文件并执行，实际上就是输出流
 *  6. 服务器向浏览器发送 html 文件 （发送静态文件）
 **/

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = "我的简易框架";
        req.setAttribute("name",name);
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req,resp);
    }
}
