package com.ggg.taskplatform.task.operator;

import org.junit.Test;

import com.ggg.taskplatform.task.constant.TaskConstant;


/**
 * Task常量类测试
 *
 * @author justincai
 */
public class TaskConstantTest{
	
	public TaskConstantTest() {
    }
	
	
	@Test
    public void testTaskConstantTest() {
        System.out.println("TaskConstantTest");

    }
	
	
	@Test
    public void getActionTypeName() {
		byte i = 2;
		String string = TaskConstant.getActionTypeName(i);
        System.out.println(string);

    }
}
