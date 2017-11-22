package Number_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Login {
    // 2.大多系统的登录模块都会接收用户通过键盘输入的登录信息，这些登录信息将会被登录模块验证，通过java编码，写出模拟登录的基本场景；练习 if  else if语法

    private static String readDataFromInput(String prompt) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            System.out.print(prompt);
            input = br.readLine();
        } catch (IOException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return input;
    }

    private static void RegisterLogin() {
        System.out.println("请先注册。");
        String username = readDataFromInput("输入注册用户名：");
        String password = readDataFromInput("输入注册用户密码：");

        System.out.println("注册成功，请登录。");
        int maxTry = 3; // 最大登录失败次数
        int tryCount = 0;
        boolean pass = false;
        do {
            String inputUsername = readDataFromInput("用户名：");
            String inputPassword = readDataFromInput("密码：");
            if (inputUsername.equals(username) && inputPassword.equals(password)) {
                pass = true;
                System.out.println("用户名和密码正确。");
                break;
            } else {
                tryCount++;
                System.out.println("用户名或密码输入错误。");
            }
        } while (maxTry > tryCount);

        if (!pass && maxTry <= tryCount) {
            System.out.println("错误次数过多，登录失败。");
        } else {
            System.out.println("恭喜你，登录成功！");
        }
    }

    public static void main(String[] args) {
        RegisterLogin();
    }
}