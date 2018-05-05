package com.marshmallow.beehive;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.marshmallow.beehive", appContext.getPackageName());
    }


    public class WriteConfigurationUtil {
        public static void writeConfiguration(Context ctx ) {
            try (FileOutputStream openFileOutput = ctx.openFileOutput( "config.txt", Context.MODE_PRIVATE);) {
                openFileOutput.write("This is a test1.".getBytes());
                openFileOutput.write("This is a test2.".getBytes());
            } catch (Exception e) {
                // not handled
            }
        }
    }

    public class WriteConfigurationUtilTest {

        @Rule
        public MockitoRule rule = MockitoJUnit.rule();
        @Mock
        Context context;

        @Mock
        FileOutputStream fileOutputStream;


        @Test
        public void writeShouldWriteTwiceToFileSystem() {
            try {
                when(context.openFileOutput(anyString(), anyInt())).thenReturn(fileOutputStream);
                Util.writeConfiguration(context);
                verify(context, times(1)).openFileOutput(anyString(), anyInt());
                verify(fileOutputStream, atLeast(2)).write(any(byte[].class));

            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
    }
}
