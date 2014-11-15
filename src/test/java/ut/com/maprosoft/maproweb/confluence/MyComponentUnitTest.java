package ut.com.maprosoft.maproweb.confluence;

import org.junit.Test;
import com.maprosoft.maproweb.confluence.MyPluginComponent;
import com.maprosoft.maproweb.confluence.MyPluginComponentImpl;

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