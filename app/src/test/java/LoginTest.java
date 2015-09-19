import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eoin_a.im_app20.BuildConfig;
import com.example.eoin_a.im_app20.R;
import com.example.eoin_a.im_app20.Views.LoginActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

/**
 * Created by eoin_a on 22/06/2015.
 */
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class LoginTest {

    private LoginActivity loginactivity;
    private Button buttonlogin;
    private TextView tv1;
    private TextView tv2;
    private EditText ed1;
    private EditText ed2;
    private CheckBox checkbox;


    @Before
    public void setup()
    {
       loginactivity = Robolectric.buildActivity(LoginActivity.class).create().get();
        checkbox = (CheckBox)  loginactivity.findViewById(R.id.checkBox);
        buttonlogin = (Button) loginactivity.findViewById(R.id.buttonlogin);
        tv1 = (TextView) loginactivity.findViewById(R.id.tv1login);
        tv2 = (TextView) loginactivity.findViewById(R.id.tv2login);
        ed1 = (EditText) loginactivity.findViewById(R.id.edtxtemaillogin);
        ed2 = (EditText) loginactivity.findViewById(R.id.edtxtpasslogin);
        checkbox = (CheckBox)  loginactivity.findViewById(R.id.checkBox);

    }


    @Test
    public void notNullTest()
    {
        assertTrue(loginactivity != null);
        assertNotNull(buttonlogin);
        assertNotNull(tv1);
        assertNotNull(tv2);
        assertNotNull(ed1);
        assertNotNull(ed2);
        assertNotNull(checkbox);
    }



    @Test
    public void stringTest()
    {
        assertTrue("Contains incorrect text!", "Remember Login?".equalsIgnoreCase(checkbox.getText().toString()));
        assertTrue("Textview contains incorrect text!", loginactivity.getResources()
                .getString(R.string.emailadd).equalsIgnoreCase(tv1.getText().toString()));
        assertTrue("Textview contains incorrect text!", loginactivity.getResources()
                .getString(R.string.password).equalsIgnoreCase(tv2.getText().toString()));
        assertTrue("Button contains incorrect text", loginactivity.getResources()
                .getString(R.string.login).equalsIgnoreCase(buttonlogin.getText().toString()));
    }

    @Test

    public void nextActivityTest()
    {





    }





}
