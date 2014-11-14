package ut.com.maprosoft.confluence;

import org.junit.Test;
import com.maprosoft.confluence.MyPluginComponent;
import com.maprosoft.confluence.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}