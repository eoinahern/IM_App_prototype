/**
 * Created by eoin_a on 18/09/2015.
 */

import android.widget.Button;
import android.widget.TextView;

import com.example.eoin_a.im_app20.BuildConfig;
import com.example.eoin_a.im_app20.Views.RegistrationActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class RegActivityTest {

    RegistrationActivity regactivity;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private Button button;




    @Before
    public void setup()
    {
           regactivity = Robolectric.buildActivity(RegistrationActivity.class).create().get();
    }


    @Test
    public void notNullTest()
    {
        assertNotNull(regactivity);
    }

}
