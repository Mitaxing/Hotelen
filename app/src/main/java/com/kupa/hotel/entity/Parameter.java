package com.kupa.hotel.entity;

/**
 * Created by HM on 2017/3/14 21:33
 */

/** Parameter 保存的是URI中的参数*/
public class Parameter
{
    /** 参数的名字 */
    private String name = new String();
    /** 参数的值 */
    private String value = new String();

    /** 创建一个Parameter */
    public Parameter()
    {
    }

    /** 创建一个Parameter
     * @param  name 参数的名字
     * @param  value 参数的值
     **/
    public Parameter(String name, String value)
    {
        setName(name);
        setValue(value);
    }

    ////////////////////////////////////////////////
    //	name
    ////////////////////////////////////////////////

    /** 设置参数的名字 */
    public void setName(String name)
    {
        this.name = name;
    }

    /** 获取参数的名字 */
    public String getName()
    {
        return name;
    }

    ////////////////////////////////////////////////
    //	value
    ////////////////////////////////////////////////

    /** 设置参数的值 */
    public void setValue(String value)
    {
        this.value = value;
    }

    /** 获取参数的值*/
    public String getValue()
    {
        return value;
    }
}


