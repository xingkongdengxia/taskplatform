package com.ggg.taskplatform.task.operator;

import com.ggg.taskplatform.task.dao.model.TpTaskChild;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author justincai
 */
public class TaskOperatorTest {

    public TaskOperatorTest() {
    }

    /**
     * Test of checkFieldLength method, of class TaskOperator.
     */
    @Test
    public void testCheckFieldLength() {
        System.out.println("checkFieldLength");

    }

    /**
     * Test of checkStartAndEndTime method, of class TaskOperator.
     */
    @Test
    public void testCheckStartAndEndTime() {
        System.out.println("checkStartAndEndTime");
        TpTaskChild taskchild = new TpTaskChild();
        TaskOperator instance = new TaskOperator();

        //startTime is null and endTime is null, return should be true 
        boolean expResult = true;
        boolean result = instance.checkStartAndEndTime(taskchild);
        assertEquals(expResult, result);

        //startTime is null and endTime has value, return should be true 
        taskchild.setShowEndtime("2018-06-05");
        expResult = true;
        result = instance.checkStartAndEndTime(taskchild);
        assertEquals(expResult, result);

        //startTime has value and endTime has value, startTime is before endTime ,return should be true 
        taskchild.setShowStarttime("2018-06-02");
        taskchild.setShowEndtime("2018-06-05");
        expResult = true;
        result = instance.checkStartAndEndTime(taskchild);
        assertEquals(expResult, result);

        //startTime and endTime have the same value ,return should be true 
        taskchild.setShowStarttime("2018-06-05");
        taskchild.setShowEndtime("2018-06-05");
        expResult = true;
        result = instance.checkStartAndEndTime(taskchild);
        assertEquals(expResult, result);

        //startTime has value and endTime has value, endTime is before startTime ,return should be false
        taskchild.setShowStarttime("2018-06-02");
        taskchild.setShowEndtime("2018-06-01");
        expResult = false;
        result = instance.checkStartAndEndTime(taskchild);
        assertEquals(expResult, result);

    }

}
