package com.example.Look01;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest extends ActivityInstrumentationTestCase2<Upload>
{

    private Instrumentation mInstrumentation;
    private FxLoginActivity mLoginTest;

    private EditText userName;
    private EditText passWord;
    private Button login;
    private Button reset;

    public FxLoginActivityTest()
    {
        super(FxLoginActivity.class);
    }

    //重写setUp方法，在该方法中进行相关的初始化操作
    @Override
    protected void setUp() throws Exception
    {
        // TODO Auto-generated method stub
        super.setUp();
        /**这个程序中需要输入用户信息和密码，也就是说需要发送key事件，
         * 所以，必须在调用getActivity之前，调用下面的方法来关闭
         * touch模式，否则key事件会被忽略
         */
        //关闭touch模式
        setActivityInitialTouchMode(false);
        mInstrumentation = getInstrumentation();
        //获取被测试的FxLoginActivity
        mLoginTest = getActivity();

        //获取FxLoginActivity相关的UI组件
        userName = (EditText)mLoginTest.findViewById(com.phicomm.hu.R.id.name);
        passWord = (EditText)mLoginTest.findViewById(com.phicomm.hu.R.id.psd);
        login = (Button)mLoginTest.findViewById(com.phicomm.hu.R.id.login);
        reset = (Button)mLoginTest.findViewById(com.phicomm.hu.R.id.reset);

    }

    //该测试用例实现在测试其他用例之前，测试确保获取的组件不为空
    public void testPreConditions()
    {
        assertNotNull(mLoginTest);
        assertNotNull(userName);
        assertNotNull(passWord);
        assertNotNull(login);
        assertNotNull(reset);
    }

    /**该方法实现在登录界面上输入相关的登录信息。由于UI组件的
     * 相关处理（如此处的请求聚焦）需要在UI线程上实现，
     * 所以需调用Activity的runOnUiThread方法实现。
     */
    public void input()
    {
        mLoginTest.runOnUiThread(new Runnable()
        {

            @Override
            public void run()
            {
                // TODO Auto-generated method stub
                userName.requestFocus();
                userName.performClick();
            }
        });
        /*由于测试用例在单独的线程上执行，所以此处需要同步application，
         * 调用waitForIdleSync等待测试线程和UI线程同步，才能进行输入操作。
         * waitForIdleSync和sendKeys不允许在UI线程里运行
         */
        mInstrumentation.waitForIdleSync();

        //调用sendKeys方法，输入用户名
        sendKeys(KeyEvent.KEYCODE_P, KeyEvent.KEYCODE_H,
                KeyEvent.KEYCODE_I, KeyEvent.KEYCODE_C,
                KeyEvent.KEYCODE_O, KeyEvent.KEYCODE_M,
                KeyEvent.KEYCODE_M);

        mLoginTest.runOnUiThread(new Runnable()
        {

            @Override
            public void run()
            {
                // TODO Auto-generated method stub
                passWord.requestFocus();
                passWord.performClick();
            }
        });

        //调用sendKeys方法，输入密码
        sendKeys(KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_2,
                KeyEvent.KEYCODE_3, KeyEvent.KEYCODE_4);
    }

    //测试输入的用户信息
    public void testInput()
    {
        //调用测试类的input方法，实现输入用户信息(sendKeys实现输入)
        input();
        //测试验证用户信息的预期值是否等于实际值
        assertEquals("123", userName.getText().toString());
        //密码的预期值123与实际值1234不符，Failure;
        assertEquals("123", passWord.getText().toString());
    }

    //测试登录按钮
    public void testLogin()
    {
        input();
        //开新线程，并通过该线程在实现在UI线程上执行操作
        mInstrumentation.runOnMainSync(new Runnable()
        {

            @Override
            public void run()
            {
                // TODO Auto-generated method stub
                login.requestFocus();
                login.performClick();
            }
        });
    }

    //测试重置按钮
    public void testReset()
    {
        input();
        mInstrumentation.runOnMainSync(new Runnable()
        {

            @Override
            public void run()
            {
                // TODO Auto-generated method stub
                reset.requestFocus();
                //点击按钮
                reset.performClick();
            }
        });
        //验证重置按钮的实现功能，是否点击后内容为空
        assertEquals("", userName.getText().toString());
        assertEquals("", passWord.getText().toString());
    }
}
