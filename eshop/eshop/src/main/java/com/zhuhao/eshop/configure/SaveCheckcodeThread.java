package com.zhuhao.eshop.configure;

import javax.servlet.http.HttpSession;

public class SaveCheckcodeThread extends  Thread {

    private String checkcode;

    private HttpSession session;

    public SaveCheckcodeThread(String checkcode, HttpSession session) {
        this.checkcode = checkcode;
        this.session = session;
    }

    public void run() {
        session.setAttribute("checkcodefogetPW",checkcode);
        try {
            System.out.println("保存验证码至session 线程启动 进入睡眠");
            Thread.sleep(60000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        session.removeAttribute("checkcodefogetPW");
        System.out.println("移除验证码 线程启动");
    }
}
