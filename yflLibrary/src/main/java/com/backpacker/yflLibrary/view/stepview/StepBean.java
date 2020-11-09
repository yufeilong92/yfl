package com.backpacker.yflLibrary.view.stepview;

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.example.myapplication.StepView
 * @Email : yufeilong92@163.com
 * @Time :2020/11/9 11:45
 * @Purpose :
 */
public class StepBean {
    public static final int STEP_UNDO = -1;//未完成  undo step
    public static final int STEP_CURRENT = 0;//正在进行 current step
    public static final int STEP_COMPLETED = 1;//已完成 completed step

    private String name;
    private int state;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public StepBean()
    {
    }

    public StepBean(String name, int state)
    {
        this.name = name;
        this.state = state;
    }
}
